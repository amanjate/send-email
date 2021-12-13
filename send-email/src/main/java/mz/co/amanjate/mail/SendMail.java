package mz.co.amanjate.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	
	public static void main(String[] args) throws AddressException, MessagingException, IOException {
			

			// This is your gmail account
			// First, go to https://myaccount.google.com/lesssecureapps and activate access.
			final String FROM = "your_account@gmail.com";


			final String PASSWORD = "your_password";
			
			// This is the destination address
			final String TO = "to@gmail.com";

			Properties properties = new Properties();


			// If you have to use something else than gmail, you may needd to change the following configurations
			
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.eneble", "true");
			properties.put("mail.smtp.starttls.required", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			
			Session session = Session.getInstance(properties, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(FROM, PASSWORD);
				}
			});
			
			MimeMessage email = new MimeMessage(session);
			Multipart content = new MimeMultipart();
			MimeBodyPart body = new MimeBodyPart();
			MimeBodyPart pdf = new MimeBodyPart();
			
			body.setText("This is the text you want to send.");
			pdf.attachFile("/absolute/path/to/document.pdf");
			
			content.addBodyPart(body);
			content.addBodyPart(pdf);
			
			email.setFrom(new InternetAddress(FROM));
			email.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(TO));
			email.setSubject("E-Mail with attachment");
			email.setContent(content);
			
			Transport.send(email);
			
			System.out.println("Mail sent!");
			
	}

}
