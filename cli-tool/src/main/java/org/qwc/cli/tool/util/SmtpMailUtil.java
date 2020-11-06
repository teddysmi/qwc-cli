package org.qwc.cli.tool.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class SmtpMailUtil {

	private static final String SMTP_SERVER = "smtp.gmail.com";

	public static void sendMail(String username, String password, String recipient, Map<String, Double> map) {
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", SMTP_SERVER);
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject("DQMS Daily PPU report on " + DateUtil.getCurrentDate());
			String data = "Hi All,\n";
			if (!map.isEmpty()) {
				data += "There is PPU for report " + DateUtil.getCurrentDate() + "\n";
				for (Entry<String, Double> entry : map.entrySet()) {
					data += "Mobile:" + entry.getKey() + "\n";
					data += "Amount:$" + entry.getValue() + "\n";
				}
			} else {
				data += "There is no new PPU for report " + DateUtil.getCurrentDate() + "\n";
			}

			data += "Thanks.";
			message.setText(data);

			Transport.send(message);

			System.out.println("Email sent!");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
