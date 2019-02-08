package ir.mostashar.util;


import com.kavenegar.sdk.KavenegarApi;
import com.kavenegar.sdk.models.SendResult;
import lombok.Data;

import java.util.List;

@Data
public class SmsSender {

    private String message;
    private String token;
    private String reciever;
    private String sender;
    private List<String> listReciver;

    public SmsSender(String token, String sender,String reciever, String message) {
        this.message = message;
        this.token = token;
        this.reciever = reciever;
        this.sender = sender;
    }

    public void sendListSms() {
        String phoneList = "";
        for (String phone : listReciver) {
            phoneList += phone + ",";
        }
        KavenegarApi api = new KavenegarApi(token);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //api.send(listReciver, reciever, message);
            }
        });
        t.start();
    }

    public void sendSingleSms() {
        System.out.println("Log-------------reciever" + reciever);
        System.out.println("Log-------------reciever" + message);

        KavenegarApi api = new KavenegarApi(token);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                SendResult send = api.send(sender, reciever, message);
                System.out.println(send.toString());
                System.out.println("Log---------sended");
            }
        });
        t.start();
    }

}
