package www.wzb.activemq;


import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

public class SendMessage {
	private static final String secretId = "AKIDVpu8YkHfnumRxqntm6E7mZAXO5bdcyaz";
	private static final String secretKey = "NYWGGApjQw0nnbAVxNasvzpZ4qUnJZEg";
	private static final String TemplateID = "880814";
	private static final String Sign = "tym123公众号";
	//时间参数
	private static final String SmsTime = "1";
	private static final String SmsSdkAppid = "1400488108";

	public static String Send(String SendPhone,String code) {
		String TemplateParamSet = null;
		System.out.println("456");

		try {
			Credential cred = new Credential(secretId, secretKey);

			HttpProfile httpProfile = new HttpProfile();
			httpProfile.setEndpoint("sms.tencentcloudapi.com");

			ClientProfile clientProfile = new ClientProfile();
			clientProfile.setHttpProfile(httpProfile);

			SmsClient client = new SmsClient(cred, "ap-shanghai", clientProfile);


			//System.out.println(code);
			TemplateParamSet = code;

			//模板参数
			String[] templateParams = {TemplateParamSet,SmsTime};

			String[] phoneNumbers = {"+86"+SendPhone};

			SendSmsRequest req = new SendSmsRequest();

			req.setPhoneNumberSet(phoneNumbers);
			req.setSmsSdkAppid(SmsSdkAppid);
			req.setSign(Sign);
			req.setTemplateID(TemplateID);
			req.setTemplateParamSet(templateParams);


			SendSmsResponse resp = client.SendSms(req);


		} catch (TencentCloudSDKException e) {
			System.out.println(e.toString());
		}
		return TemplateParamSet;
	}


}

