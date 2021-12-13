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
			
			// First, go to https://myaccount.google.com/lesssecureapps and switch on the button.
			final String USERNAME = "dipdrhup@gmail.com";
			final String PASSWORD = "dip4dm1n";
			
			Properties properties = new Properties();
			
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.eneble", "true");
			properties.put("mail.smtp.starttls.required", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			
			Session session = Session.getInstance(properties, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(USERNAME, PASSWORD);
				}
			});
			
			MimeMessage email = new MimeMessage(session);
			Multipart content = new MimeMultipart();
			MimeBodyPart body = new MimeBodyPart();
			MimeBodyPart pdf = new MimeBodyPart();
			
			body.setText("Nada importante. Apenas um e-mail com anexo.");
			pdf.attachFile("/home/aderito/Documents/SGCOF.pdf");
			
			content.addBodyPart(body);
			content.addBodyPart(pdf);
			
			email.setFrom(new InternetAddress(USERNAME));
			email.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("amanjate@outlook.com"));
			email.setSubject("E-Mail com anexo");
			email.setContent(content);
			
			Transport.send(email);
			
			System.out.println("Mail sent!");
			
	}

}
