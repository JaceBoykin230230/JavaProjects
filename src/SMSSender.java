import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SMSSender {

    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_ACCOUNT_TOKEN");
    public static void testSendSMS(String to, String body){
        System.out.println("Sending SMS to " + to + " with body: " + body);
    }

    public static void sendSMS(String to, String body) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber(to), // to
                        new PhoneNumber("+18449343655"), // from
                        body)
                .create();

        System.out.println(message.getSid());
    }
}
