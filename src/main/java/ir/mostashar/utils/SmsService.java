package ir.mostashar.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${sms.token}")
    private String token;

    @Value("${sms.sender}")
    private String sender;


    public void sendSms(int typeToken, String reciver, String message) {
        SmsSender smsSender=  new SmsSender(token,sender,reciver,message);
        smsSender.sendVerifiedCode(typeToken);

    }

}
