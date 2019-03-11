package ir.mostashar.utils;


import com.kavenegar.sdk.KavenegarApi;
import com.kavenegar.sdk.models.SendResult;
import lombok.Data;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;

@Data
public class SmsSender {

    private static String baseUrl = "https://api.kavenegar.com/v1/";
    private String message;
    private String token;
    private String reciever;
    private String sender;
    private List<String> listReciver;

    public SmsSender(String token, String sender, String reciever, String message) {
        this.message = message;
        this.token = token;
        this.reciever = reciever;
        this.sender = sender;
    }

    public void sendVerifiedCode() {

        System.out.println("Log-------------sendVerifiedCode " + reciever + " " + message);
        //  send();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    KavenegarApi api = new KavenegarApi(token);
//                    SendResult send = api.send(sender, reciever, message);
                    SendResult send = api.verifyLookup(reciever, message, "validationcode");
                    System.out.println("Sender: " + send.getSender());
                    System.out.println("Message: " + send.getMessage());
                    System.out.println("Date: " + send.getDate());
                    System.out.println("Status: " + send.getStatus());
                    System.out.println("Cost: " + send.getCost());
                    System.out.println("Log---------sended");
                } catch (Exception e1) {
                    return;
                }
            }
        });
        t.start();
    }

    public void sendHttpPost() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                baseUrl += token + "/verify/lookup.json?receptor=" + reciever + "&token=" + message + "&template=validationcode";
                CloseableHttpClient client = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(baseUrl);
                try {
                    CloseableHttpResponse response = client.execute(httpPost);
                    System.out.println("Log---send--------------------:" + response.getEntity());
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }
}
