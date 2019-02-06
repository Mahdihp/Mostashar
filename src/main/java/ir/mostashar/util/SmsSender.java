package ir.mostashar.util;


import com.kavenegar.sdk.KavenegarApi;
import lombok.Data;

import java.util.List;

@Data
public class SmsSender {

    private String phoneNumber;
    private String message;
    private String token;
    private String reciever;
    private List<String> listPhoneNumber;

    public SmsSender(String phoneNumber, String message, String token, String reciever) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.token = token;
        this.reciever = reciever;
    }

    public SmsSender(String message, String token, String reciever, List<String> listPhoneNumber) {
        this.message = message;
        this.token = token;
        this.reciever = reciever;
        this.listPhoneNumber = listPhoneNumber;
    }

    public void sendListSms() {
        String phoneList = "";
        for (String phone : listPhoneNumber) {
            phoneList += phone + ",";
        }
        KavenegarApi api = new KavenegarApi(token);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //api.send(listPhoneNumber, reciever, message);
            }
        });
        t.start();
    }

    public void sendSingleSms() {
        System.out.println("Log-------------reciever" + phoneNumber);
        System.out.println("Log-------------reciever" + message);

        KavenegarApi api = new KavenegarApi(token);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
              //  api.send(phoneNumber, reciever, message);
                System.out.println("Log---------sended");
            }
        });
        t.start();
    }

}
