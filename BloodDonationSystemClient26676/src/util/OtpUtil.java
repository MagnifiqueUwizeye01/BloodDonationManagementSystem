package view;

import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

public class OtpUtil {

    // ✅ Generate 6-digit OTP
    public static String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // ✅ Send OTP email
    public static void sendOtpToEmail(String toEmail, String otp) throws MessagingException {
        // Sender email credentials 
        final String fromEmail = "uwizeyemagnifique@gmail.com";
        final String password = "suqj cmxe fmrf vyvg"; // 

        // SMTP server configuration
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "587");           
        props.put("mail.smtp.auth", "true");           
        props.put("mail.smtp.starttls.enable", "true"); 

        // create Authenticator object
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        // prepare the message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Your OTP Code");
        message.setText("Dear User,\n\nYour OTP code is: " + otp + "\n\nUse this to log in.\n\nRegards,\nBlood Donation System");

        // send the message
        Transport.send(message);
        System.out.println("✅ OTP sent successfully to " + toEmail);
    }
}
