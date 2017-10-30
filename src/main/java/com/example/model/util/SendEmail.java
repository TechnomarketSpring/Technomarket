package com.example.model.util;
//
//import java.util.HashSet;
//import java.util.Properties;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//import com.example.model.User;
//
//import javafx.scene.shape.Line;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import java.util.Properties;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;


//	import java.util.Properties;
//
//	import javax.mail.Message;
//	import javax.mail.MessagingException;
//	import javax.mail.Session;
//	import javax.mail.Transport;
//	import javax.mail.internet.InternetAddress;
//	import javax.mail.internet.MimeMessage;
//
//	import com.example.model.User;

	public class SendEmail{
	private static final String MEDELIN_EMAIL = "technomarkettalents@gmail.com";
	private static final String MEDELIN_PASS = "ittechnomarket1234";
	
	//email gateways to costruct email subject and text:
	
	//1 - forgotten password -> email send to specific user by the system on request by the same user
	public static void forgottenPassEmail(String email, String userPassword){
		final String subject = "Technmatker - Forgotten password";
		final String text = "Hello, dear customer " +  System.lineSeparator() + "on your request, we send you your forgotten password, witch is on Technomarket: " + userPassword + System.lineSeparator() + "If this doesn't consernt you, please ignore this message." + System.lineSeparator() + "Have a nice day," + System.lineSeparator() + "The Technomarket Team";
		sendSimpleEmail(email, subject, text);
	}
	
 	public static void sendSimpleEmail(String receiverEmail, String subjectText, String msgText) {
 System.out.println("==============================================================1");
 		Properties props = new Properties();
 		props.put("mail.smtp.auth", "true");
 		props.put("mail.smtp.starttls.enable", "true");
 		props.put("mail.smtp.host", "smtp.gmail.com");
 		props.put("mail.smtp.port", "587");
 
 		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
 			protected PasswordAuthentication getPasswordAuthentication() {
 				return new PasswordAuthentication(MEDELIN_EMAIL, MEDELIN_PASS);
 			}
 		});
 System.out.println("=============================================================2");
 		try {
 			Message message = new MimeMessage(session);
 			message.setFrom(new InternetAddress("technomarkettalents@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
 			message.setSubject(subjectText);
 			message.setText(msgText);
 			System.out.println("=================================================================================3");
 			Transport.send(message);
 
 			System.out.println("Email sent.");
 
 		} catch (MessagingException e) {
 			throw new RuntimeException(e);
 		}
 
 	}
 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	
//	
//	public class SendEmail {
//		private static String host = "smtp.gmail.com";
//		private static String port = "465";
//		private static String from = "technomarkettalents@gmail.com";
//		private static String pass = "ittechnomarket1234";
//
//		private static String subject = "www.pisi.bg";
//
//		
//		public static void toPromotion() {
//			Properties props = new Properties();
//			
//			props.put("mail.smtp.host", host);
//			props.put("mail.smtp.user", from);
//			props.put("mail.smtp.password", pass);
//			props.put("mail.smtp.port", port);
//			props.put("mail.smtp.auth", "true");
//
//			Session session = Session.getDefaultInstance(props);
//
//			Message msg = new MimeMessage(session);
//			try {
//
//				msg.setFrom(new InternetAddress(from));
//				msg.setRecipient(Message.RecipientType.TO, new InternetAddress("technomarkettalents@gmail.com"));
//				msg.setSubject(subject);
//				
//				// to be added properly
//				String emailText = "<a href=\"http://localhost:8080/ProjectPisi/products/productdetail/productId/3\">"
//						+ " <img src=\"http://www.petmd.com/sites/default/files/scared-kitten-shutterstock_191443322.jpg\">"
//						+ "</a>";
//				msg.setContent(emailText, "text/html; charset=utf-8");
//
//				Transport transport = session.getTransport("smtps");
//				transport.connect(host, from, pass);
//				transport.sendMessage(msg, msg.getAllRecipients());
//				transport.close();			
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
//		}	
//		
//		public static void passwordTo(User u) {
//			Properties props = new Properties();
//			
//			props.put("mail.smtp.host", host);
//			props.put("mail.smtp.user", from);
//			props.put("mail.smtp.password", pass);
//			props.put("mail.smtp.port", port);
//			props.put("mail.smtp.auth", "true");
//
//			Session session = Session.getDefaultInstance(props);
//
//			Message msg = new MimeMessage(session);
//			try {
//
//				msg.setFrom(new InternetAddress(from));
//				msg.setRecipient(Message.RecipientType.TO, new InternetAddress("technomarkettalents@gmail.com"));
//				msg.setSubject(subject);
//
//				String emailText = String.format("<h2>Здравей, приятелю на домашните любимци!</h2><br>"
//						+ "кофти е да си забравиш паролата, затова от екипа на pisi.bg проявяваме разбиране и предоставяме тази информация за теб.<br>"
//						+ "Твоите данни са:<br><br>"
//						+ "<strong>email:</strong> %s <br>"
//						+ "<strong>password:</strong> %s<br><br>"
//						+ "<h3>От екипа на нашия сайт, ти пожелаваме приятно пазаруване.</h3>"
//						+ "<a>"
//						+ " <img src=\"http://media.pennlive.com/midstate_impact/photo/dog-paw-genericjpg-829eca230b8dc4f1.jpg\" width=\"100px\" heigth=\"auto\" >"
//						+ "</a>",u.getEmail(), u.getPassword());
//
//				msg.setContent(emailText, "text/html; charset=utf-8");
//
//				Transport transport = session.getTransport("smtps");
//				transport.connect(host, from, pass);
//				transport.sendMessage(msg, msg.getAllRecipients());
//				transport.close();
//
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
//	
//	
//	
//	
//	
//	
//
//	
//	
//






	
	//2 - new promo products -> email send to all subscribers by they system on request by the admin
	//3 - technomarket news -> email send to all subscribers by the system on request of the system itself
		 
		 
//		 public static void sendwithAttachmentEmail(String receiverEmail, String subjectText, String msgText) {
//		 
//		 		Properties props = new Properties();
//		 		props.put("mail.smtp.auth", "true");
//		 		props.put("mail.smtp.starttls.enable", "true");
//		 		props.put("mail.smtp.host", "smtp.gmail.com");
//		 		props.put("mail.smtp.port", "587");
//		 
//		 		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//		 			protected PasswordAuthentication getPasswordAuthentication() {
//		 				return new PasswordAuthentication(MEDELIN_EMAIL, MEDELIN_PASS);
//		 			}
//		 		});
//		 
//		 		try {
//		 
//		 			Message message = new MimeMessage(session);
//		 			message.setFrom(new InternetAddress(MEDELIN_EMAIL));
//		 			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
//		 			message.setSubject(subjectText);
//		 
//		 			BodyPart messageBodyPart = new MimeBodyPart();
//		 
//		 			messageBodyPart.setText(msgText);
//		 
//		 			Multipart multipart = new MimeMultipart();
//		 
//		 			multipart.addBodyPart(messageBodyPart);
//		 
//		 			messageBodyPart = new MimeBodyPart();
//		 			DataSource source = new FileDataSource(fileName); // the path
//		 			messageBodyPart.setDataHandler(new DataHandler(source));
//		 			messageBodyPart.setFileName(fileName); // the path
//		 			multipart.addBodyPart(messageBodyPart);
//		 
//		 			message.setContent(multipart);
//		 
//		 			Transport.send(message);
//		 
//		 			System.out.println("Email sent.");
//		 
//		 		} catch (MessagingException e) {
//		 			throw new RuntimeException(e);
//		 		}
//		 	}
	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public static void sendEmail(String email, String subject, String text) {
//		final String username = "technomarkettalents@gmail.com";
//		final String password = "ittechnomarket1234";
//		Properties props = new Properties();
////	props.put("mail.smtp.auth", "true");
////	props.put("mail.smtp.starttls.enable", "true");
////	props.put("mail.smtp.host", "smtp.gmail.com");
////	props.put("mail.smtp.port", "587");
//	props.put("mail.smtp.host", "smtp.gmail.com");
//	props.put("mail.smtp.socketFactory.port", "465");
//	props.put("mail.smtp.socketFactory.class",
//			"javax.net.ssl.SSLSocketFactory");
//	props.put("mail.smtp.auth", "true");
//	props.put("mail.smtp.port", "465");
//	
//	Session session = Session.getInstance(props,
//	  new javax.mail.Authenticator() {
//		protected PasswordAuthentication getPasswordAuthentication() {
//			return new PasswordAuthentication(username, password);
//		}
//	  });
//	System.out.println(text + "==================================================================");
//	try {
//		Message message = new MimeMessage(session);
//		message.setFrom(new InternetAddress("technomarkettalents@gmail.com"));
//		message.setRecipients(Message.RecipientType.TO,
//			InternetAddress.parse(email));
//		System.out.println(text + "==================================================================");
//		message.setSubject(subject);
//		message.setText(text);
//
//		Transport.send(message);
//
//		System.out.println("Done================================================================================");
//
//	} catch (MessagingException e) {
//		throw new RuntimeException(e);
//	}
//}
//
//}	
	
	
	
	
	
	
	
	
	
	
	
	
//private static String getSubject(int reasonCode) {
//	if(reasonCode == 1) {
//		return "Technmatker - Forgotten password";
//	}else if(reasonCode == 2){
//		return "Technmatker - Promo products!";
//	}else{
//		return "Technmatker - New at Technomarket!";
//	}
//}
//}
		
		
		
		
//		final String username = "technomarkettalents@gmail.com";
//		final String password = "talents1234";
//		final String host = "smtp.gmail.com";
//		final String port = "888";
//		
//		Properties props = new Properties();
////		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", host);
//		props.put("mail.smtp.user", username);
//		props.put("mail.smtp.password", password);
//		props.put("mail.smtp.port", port);
//		props.put("mail.smtp.auth", "true");
//		
////		props.put("mail.transport.protocol", "smtps");
////		props.put("mail.password", password);
////		props.put("mail.smtp.debug", "true");
////		props.put("mail.smtp.starttls.enable", "true");
////		props.put("mail.smtp.socketFactory.port", port);
////		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
////		props.put("mail.smtp.socketFactory.fallback", "false");
//		
//		
//		Session session = Session.getDefaultInstance(props);
//		
//		Message msg = new MimeMessage(session);
//		try {
//			
//			msg.setFrom(new InternetAddress(username));
//			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
//			msg.setSubject(subject);
//			
//			String emailText = String.format(text);
//			
//			msg.setContent(emailText, "text/html; charset=utf-8");
//
//			Transport transport = session.getTransport("smtps");
//			transport.connect(host, username, password);
//			transport.sendMessage(msg, msg.getAllRecipients());
//			transport.close();
//			System.out.println("DONE-=----=================================================================");
//
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//	}
//}		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		



//public class SendEmail {
//	
//	final String username = "technomarket@gmail.com";
//	final String password = "talents1234";
//	
//	Properties props = new Properties();
//	props.put("mail.smtp.auth", "true");
//	props.put("mail.smtp.starttls.enable", "true");
//	props.put("mail.smtp.host", "smtp.gmail.com");
//	props.put("mail.smtp.port", "587");
//
	
//	private static final String host = "smtp.gmail.com";
//	private static final String port = "465";
//	private static final String from = "e@mail.com";
//	private static final String pass = "xxx";
//
//	private static final String SUBJECT_PASS = "Technmatker - Forgotten password";
//	private static String SUBJECT_PROMO = "Technmatker - Promo products!";
//	private static String SUBJECT_NEWS = "Technmatker - New at Technomarket!";
//
//	public static void to(User u) {
//		Properties props = new Properties();
//		//props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", host);
//		props.put("mail.smtp.user", from);
//		props.put("mail.smtp.password", pass);
//		props.put("mail.smtp.port", port);
//		props.put("mail.smtp.auth", "true");
//		//props.put("mail.transport.protocol", "smtps");
//		// props.put("mail.password", pass);
//		// props.put("mail.smtp.debug", "true");
//		// props.put("mail.smtp.starttls.enable", "true");
//		// props.put("mail.smtp.socketFactory.port", port);
//		// props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		// props.put("mail.smtp.socketFactory.fallback", "false");
//		Session session = Session.getDefaultInstance(props);
//		
//		Message msg = new MimeMessage(session);
//		try {
//			
//			msg.setFrom(new InternetAddress(from));
//			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(u.getEmail()));
//			msg.setSubject(subject);
//			
//			String emailText = String.format("%s, wellcome to <strong>www.technomarket.com</strong><br>"
//					+ "Your email is <strong>%s</strong><br>"
//					+ "Your hashed password is <strong>%s</strong>",
//					u.getFirstName(),u.getEmail(), u.getPassword());
//			
//			msg.setContent(emailText, "text/html; charset=utf-8");
//
//			Transport transport = session.getTransport("smtps");
//			transport.connect(host, from, pass);
//			transport.sendMessage(msg, msg.getAllRecipients());
//			transport.close();
//
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args)  {
//		//da izpratq
//	}

//}
