package org.qwc.cli.tool.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pop3MailUtil {

	private static Logger LOGGER = LoggerFactory.getLogger(Pop3MailUtil.class);

	public static String fetch(String user, String password) {

		String pop3Host = user.contains("gmail.com") ? "pop.gmail.com"
				: user.contains("outlook.com") ? "pop3.outlook.com" : "outlook.office365.com";

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
			emailFolder.open(Folder.READ_WRITE);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();

			for (int i = messages.length - 1; i >= 0; i--) {
				System.out.println("Looping inbox " + (messages.length - i) + "/" + messages.length);
				Message message = messages[i];
				System.out.println("email subject" + message.getSubject());

				if (message.getSubject().contains("DQMS Daily PPU report on " + DateUtil.getCurrentDate())) {

					if (!(message.getContent() instanceof Multipart)) {
						continue;
					}
					Multipart multipart = (Multipart) message.getContent();
					message.setFlag(Flags.Flag.SEEN, true);

					for (int j = 0; j < multipart.getCount(); j++) {
						BodyPart bodyPart = multipart.getBodyPart(j);

						if (!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())
								&& StringUtils.isBlank(bodyPart.getFileName())) {
							continue; // dealing with attachments only
						}
						InputStream is = bodyPart.getInputStream();

						String pathname = new File("").getAbsolutePath();

						File f = new File(pathname + "/" + bodyPart.getFileName());
						FileOutputStream fos = new FileOutputStream(f);
						byte[] buf = new byte[4096];
						int bytesRead;
						while ((bytesRead = is.read(buf)) != -1) {
							fos.write(buf, 0, bytesRead);
						}
						fos.close();

						// close the store and folder objects
						emailFolder.close(false);
						store.close();

						return f.getPath();

					}

				}

			}

			// close the store and folder objects
			emailFolder.close(false);
			store.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error downloading");
		}

		return null;
	}

}
