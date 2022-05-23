package mail;

import geheim.MailPassword;
import org.apache.commons.mail.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class SendMail {

    public static Session connect(){
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);

        prop.put("mail.smtp.host", "mail.sensivity.team");
        prop.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MailPassword.username, MailPassword.password);
                    }
                });

        return session;
    }

    public static void verifyEmail(String user) throws EmailException, IOException {
        Path currentDirectoryPath = Paths.get("").toAbsolutePath();
        String currentPath = currentDirectoryPath.toString();

        String html = Files.readString(Path.of(currentPath + "/src/main/java/mail/VerifyEmail.html"));

        try {
            Message message = new MimeMessage(connect());
            message.setFrom(new InternetAddress(MailPassword.username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user));
            message.setSubject("E-Mail bestätigen");

            message.setContent(html, "text/html");
            Transport.send(message);
            System.out.println("Yo it has been sent..");
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
