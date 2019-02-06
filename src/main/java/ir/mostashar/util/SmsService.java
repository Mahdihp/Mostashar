package ir.mostashar.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${sms.token}")
    private String token;

    @Value("${sms.receiver}")
    private String receiver;


    public void sendSms(String phoneNumber, String message) {
        SmsSender smsSender=  new SmsSender(phoneNumber, message, token, receiver);
        smsSender.sendSingleSms();

    }

}
