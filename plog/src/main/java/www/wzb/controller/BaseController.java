package www.wzb.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import www.wzb.entity.User;
import www.wzb.entity.UserContent;
import www.wzb.service.UserContentService;
import www.wzb.service.UserService;
import www.wzb.common.PageHelper.Page;
import www.wzb.entity.Comment;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import org.springframework.security.core.Authentication;

@Component
public class BaseController {
    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR",
            "X-Real-IP"};

    @Autowired
    private UserContentService userContentService;
    @Autowired
    private UserService userService;

    public boolean isLogin(Long id){
        if(id != null ){
            User user = userService.findById( id );
            if(user!=null){
                return true;
            }
        }
        return false;
    }

    public User getUser(Long id){
        User user = userService.findById( id );
        return user;
    }

    public List<UserContent> getUserContentList(Long uid){
        List<UserContent> list = userContentService.findByUserId( uid );
        return list;
    }

    public List<UserContent> getAllUserContentList(){
        List<UserContent> list = userContentService.findAll();
        return list;
    }
    public Page<UserContent> findAll(Integer pageNum, Integer pageSize){
        Page<UserContent> page = userContentService.findAll(pageNum ,pageSize);
        return page;
    }

    public Page<UserContent> findMyBlog(UserContent userContent,Integer pageNum, Integer pageSize){
        Page<UserContent> page = userContentService.findMyBlog(userContent,pageNum ,pageSize);
        return page;
    }

    public Page<UserContent> findAll(UserContent content, Integer pageNum, Integer pageSize){
        Page<UserContent> page = userContentService.findAll( content,pageNum ,pageSize);
        return page;
    }

    public Page<UserContent> findAll(UserContent content, Comment comment, Integer pageNum, Integer pageSize){
        Page<UserContent> page = userContentService.findAll( content,comment,pageNum ,pageSize);
        return page;
    }

    public Page<UserContent> findAllByUpvote(UserContent userContent,Integer pageNum, Integer pageSize){
        Page<UserContent> page = userContentService.findAllByUpvote(userContent,pageNum ,pageSize);
        return page;
    }
    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

    /**
     * 获取response
     * @return
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    /**
     * 获取session
     * @return
     */
    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {}
        return session;
    }

    /***
     * 获取客户端ip地址(可以穿透代理)
     * @return
     */
    public static String getClientIpAddress() {
        HttpServletRequest request = getRequest();
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取当前用户
     * @return
     */
    public User getCurrentUser(){
        User user = null;
        Authentication authentication = null;
        SecurityContext context = SecurityContextHolder.getContext();
        if(context!=null){
            authentication = context.getAuthentication();
        }
        if(authentication!=null){
            Object principal = authentication.getPrincipal();
            //如果是匿名用户
            if(authentication.getPrincipal().toString().equals( "anonymousUser" )){
                return null;
            }else {
                user = (User)principal;
            }

        }
        return user;
    }


}
