package org.qwc.cli.tool.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.commons.lang3.StringUtils;

public class Pop3MailUtil {

	public static String fetch(String pop3Host, String storeType, String user, String password) {
		try {
			// create properties field
			Properties properties = new Properties();
			properties.put("mail.store.protocol", "pop3");
			properties.put("mail.pop3.host", pop3Host);
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);
			// emailSession.setDebug(true);

			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("pop3s");

			store.connect(pop3Host, user, password);

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();

			for (int i = 0; i < messages.length; i++) {
				System.out.println("Looping inbox " + (i + 1) + "/" + messages.length);
				Message message = messages[i];

				if (message.getSubject().contains("PPU_" + DateUtil.getCurrentDate())) {

					Multipart multipart = (Multipart) message.getContent();

					for (int j = 0; j < multipart.getCount(); j++) {
						BodyPart bodyPart = multipart.getBodyPart(j);
						if (!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())
								&& StringUtils.isBlank(bodyPart.getFileName())) {
							continue; // dealing with attachments only
						}
						InputStream is = bodyPart.getInputStream();

						File f = new File("/tmp/" + bodyPart.getFileName());
						FileOutputStream fos = new FileOutputStream(f);
						byte[] buf = new byte[4096];
						int bytesRead;
						while ((bytesRead = is.read(buf)) != -1) {
							fos.write(buf, 0, bytesRead);
						}
						fos.close();

						return f.getPath();

					}

				}

			}

			// close the store and folder objects
			emailFolder.close(false);
			store.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
