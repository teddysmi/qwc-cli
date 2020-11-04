package org.qwc.cli.tool.util;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SmtpMailUtil {

	private static final String SMTP_SERVER = "smtp server ";

	private static final String EMAIL_TO = "johndoe.dev99@gmail.com";

	public static void sendMail(String username, String password) {
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", SMTP_SERVER); // optional, defined in SMTPTransport
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "25"); // default port 25

		Session session = Session.getInstance(prop, null);
		Message msg = new MimeMessage(session);

		try {

			// from
			msg.setFrom(new InternetAddress(username));

			// to
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO, false));

			// subject
			msg.setSubject("DQMS Daily PPU report on " + DateUtil.getCurrentDate());

			// content
			// TODO: add N/A ids here
			msg.setText("Hello World");

			msg.setSentDate(new Date());

			// Get SMTPTransport
			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

			// connect
			t.connect(SMTP_SERVER, username, password);

			// send
			t.sendMessage(msg, msg.getAllRecipients());

			System.out.println("Response: " + t.getLastServerResponse());

			t.close();

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
