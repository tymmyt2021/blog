package www.wzb.activemq;

import org.springframework.stereotype.Component;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class SmsAuthenCode implements MessageListener {
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        // 调用SMS服务发送短信

        try {
            //
            SendMessage.Send(mapMessage.getString("telephone"),mapMessage.getString("code"));
            System.out.println( "-----发送消息成功..."+mapMessage.getString("code"));
        } catch (Exception e) {//JMS
            e.printStackTrace();
        }
    }
}
