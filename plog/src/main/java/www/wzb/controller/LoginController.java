package www.wzb.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import www.wzb.common.Constants;
import www.wzb.common.MD5Util;
import www.wzb.common.RandStringUtils;
import www.wzb.entity.User;
import www.wzb.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MapMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController extends BaseController{
    private final static Logger log = Logger.getLogger( LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired// redis数据库操作模板
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsTemplate;// mq消息模板.




    /*@RequestMapping("/phoneLogin")
    public String doLogin(Model model, @RequestParam(value = "username",required = false) String email,
                          @RequestParam(value = "password",required = false) String password,
                          @RequestParam(value = "code",required = false) String code,
                          @RequestParam(value = "telephone",required = false) String telephone,
                          @RequestParam(value = "phone_code",required = false) String phone_code,
                          @RequestParam(value = "state",required = false) String state,
                          @RequestParam(value = "pageNum",required = false) Integer pageNum ,
                          @RequestParam(value = "pageSize",required = false) Integer pageSize) {

        //判断是否是手机登录
        if (StringUtils.isNotBlank(telephone)) {

            //手机登录
            String yzm = redisTemplate.opsForValue().get( telephone );//从redis获取验证码
            if(phone_code.equals(yzm)){
                //验证码正确
                User user = userService.findByPhone(telephone);
                getSession().setAttribute("user", user);
                model.addAttribute("user", user);
                log.info("手机快捷登录成功");
                return "/personal/personal";

            }else {
                //验证码错误或过期
                model.addAttribute("error","phone_fail");
                return  "../login";
            }

        } else {
            //账号登录

            if (StringUtils.isBlank(code)) {
                model.addAttribute("error", "fail");
                return "../login";
            }
            int b = checkValidateCode(code);
            if (b == -1) {
                model.addAttribute("error", "fail");
                return "../login";
            } else if (b == 0) {
                model.addAttribute("error", "fail");
                return "../login";
            }
            password = MD5Util.encodeToHex(Constants.SALT + password);
            User user = userService.login(email, password);
            if (user != null) {
                if ("0".equals(user.getState())) {
                    //未激活
                    model.addAttribute("email", email);
                    model.addAttribute("error", "active");
                    return "../login";
                }
                log.info("用户登录登录成功");
                getSession().setAttribute("user", user);
                model.addAttribute("user", user);
                return "/personal/personal";
            } else {
                log.info("用户登录登录失败");
                model.addAttribute("email", email);
                model.addAttribute("error", "fail");
                return "../login";
            }

        }

    }*/

    @RequestMapping("/login")
    public String login(Model model) {

        User user = getCurrentUser();
        if(user!=null){
            return "redirect:/list";
        }
        return "../login";
    }


    // 匹对验证码的正确性

    public int checkValidateCode(String code) {

        Object vercode = getRequest().getSession().getAttribute("VERCODE_KEY");
        if (null == vercode) {
            return -1;
        }
        if (!code.equalsIgnoreCase(vercode.toString())) {
            return 0;
        }
        return 1;
    }

    /**
     * 退出登录
     * @param model
     * @return
     */
    @RequestMapping("/loginout")
    public String exit(Model model) {
        log.info( "退出登录" );
        getSession().removeAttribute( "user" );
        getSession().invalidate();
        return "../login";
    }

    /**
     * 发送手机验证码
     * @param model
     * @param telephone
     * @return
     */
    @RequestMapping("/sendSms")
    @ResponseBody
    public Map<String,Object> index(Model model, @RequestParam(value = "telephone",required = false) final String telephone ) {
        Map map = new HashMap<String,Object>(  );

        try { //  发送验证码操作
            //System.out.println("55555");
            final String code = RandStringUtils.getCode();
            redisTemplate.opsForValue().set(telephone, code, 60, TimeUnit.SECONDS);// 60秒 有效 redis保存验证码
            log.debug("--------短信验证码为："+code);
            // 调用ActiveMQ jmsTemplate，发送一条消息给MQ
            jmsTemplate.send("login_msg", new MessageCreator() {
                public Message createMessage(javax.jms.Session session) throws JMSException {
                    MapMessage mapMessage = session.createMapMessage();
                    mapMessage.setString("telephone",telephone);
                    mapMessage.setString("code", code);
                    return mapMessage;
                }
            });
        } catch (Exception e) {
            map.put( "msg",false );
        }
        map.put( "msg",true );
        return map;

    }

}

