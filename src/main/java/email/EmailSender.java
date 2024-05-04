package email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.PropertiesFileReader;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


// This class is responsible for sending emails.
public class EmailSender {

    private static final Logger logger = LogManager.getLogger(EmailSender.class);

    String[] cc = {
            "imranullahust@gmail.com"
    };
    private final PropertiesFileReader fileReader;

    public EmailSender() {
        fileReader = new PropertiesFileReader();
    }

    public void sendEmail(String recipients, String subject) {
        logger.info("Preparing to send email with subject: " + subject);
        Properties properties = new Properties();
        String host = fileReader.getHost();
        String port = fileReader.getPort();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        System.out.println("properties set.");

        String email = fileReader.getEmail();
        String pass = fileReader.getEmailPassword();

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pass);
            }

        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
            System.out.println("recipient added.");
            Address[] ccAddresses = new Address[cc.length];
            for (int i = 0; i < cc.length; i++) {
                ccAddresses[i] = new InternetAddress(cc[i]);
            }
            message.addRecipients(Message.RecipientType.CC, ccAddresses);

            //message.setSubject(subject);
            System.out.println("subject added.");
            // Create the message body part
            message.setSubject("TripShepherd Automation Report");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("body");
            System.out.println("Body Added");
//
            // Create the multipart message
            Multipart multipart = new MimeMultipart();
            MimeBodyPart attachmentPart = new MimeBodyPart();
            MimeBodyPart textPart = new MimeBodyPart();

            System.out.println("Message In Body Added");
            // Add the attachment if provided

            try {
                File file = new File("src/test/java/reports/report.html");
                attachmentPart.attachFile(file);
                String filePath = "src/main/java/email/email_body.README";
                String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

                // this is the body of the email
                textPart.setText(fileContent);


                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);
                System.out.println("Report Attached.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Set the content of the message to the multipart
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully.");
            logger.info("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

