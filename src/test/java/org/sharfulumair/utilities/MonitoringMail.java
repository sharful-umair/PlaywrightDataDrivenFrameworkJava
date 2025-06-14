package org.sharfulumair.utilities;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MonitoringMail
{

    //public void sendMail(String mailServer, String from, String[] to, String subject, String messageBody, String attachmentPath, String attachmentName) throws MessagingException, AddressException

    public void sendMail(String mailServer, String from, String[] to, String subject, String messageBody)
            throws MessagingException, AddressException {

        boolean debug = false;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mailServer);
        props.put("mail.smtp.port", "587");
        props.put("mail.debug", "true");

        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getInstance(props, auth);
        session.setDebug(debug);

        try {
            Message message = new MimeMessage(session);

            // Priority: 1 (highest), 3 (normal), 5 (lowest)
            message.addHeader("X-Priority", "1");
            message.setFrom(new InternetAddress(from));

            InternetAddress[] addressTo = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
                addressTo[i] = new InternetAddress(to[i]);
            }

            message.setRecipients(Message.RecipientType.TO, addressTo);
            message.setSubject(subject);

            BodyPart body = new MimeBodyPart();
            body.setContent(messageBody, "text/html");

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);

            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Successfully sent mail to all users");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator
    {

        public PasswordAuthentication getPasswordAuthentication()
        {
            String username = TestConfig.from;
            String password = TestConfig.password;
            return new PasswordAuthentication(username, password);
        }
    }

}
