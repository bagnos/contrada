package it.contrada.mail;

import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.web.util.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseMail {

	private static Session session = null;	
	private static String footerMail = null;

	protected final static Log log = LogFactory.getLog(BaseMail.class);
	
	protected static Session getSession() throws ContradaExceptionBloccante {
		if (session == null) {

			final String username = Configuration.getProperty("userMail");
			final String password = Configuration.getProperty("pswMail");

			Properties props = new Properties();
			//props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", Configuration.getProperty("serverSMTP"));
			props.put("mail.smtp.port", Configuration.getProperty("port"));

			session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});

		}
		return session;
	}
	
	protected static String getContenFile(String nameFile,
			boolean appendRegards) throws IOException,
			ContradaExceptionBloccante {

		String urlFile = BaseMail.class.getResource(nameFile).getPath();
		File flRidRataSospesi = new File(urlFile);
		String contentFile = FileUtils.readFileToString(flRidRataSospesi);
		if (appendRegards) {
			contentFile += getFooterMail();
		}

		return contentFile;

	}

	protected static String getFooterMail() throws ContradaExceptionBloccante {
		if (footerMail == null) {
			String telefoni = Configuration.getProperty("telCommisione");
			String posta = Configuration.getProperty("casellaCommisione");
			String pres = Configuration.getProperty("presCommisione");
			String lineSep = System.getProperty("line.separator");

			StringBuilder builder = new StringBuilder();
			builder.append(String
					.format("%s%SLa Commissione Protettorato è a disposizione per qualsiasi chiarimento e/o variazione dei Tuoi dati anagrafici tramite la casella di posta elettronica ",
							lineSep, lineSep));
			builder.append(String.format(
					"%s e tramite i numeri telefonici %s.", posta, telefoni));

			builder.append(String.format(
					"%s%sTi porgiamo i nostri più cordiali saluti.", lineSep,
					lineSep));

			builder.append(String.format("%s%sCommissione Protettorato",
					lineSep, lineSep));

			builder.append(String.format("%sIl Presidente", lineSep));

			builder.append(String.format("%s%s", lineSep, pres));

			footerMail = builder.toString();
		}
		return footerMail;
	}

	public static void inviaMail(String[] tos, String subject, String content,
			String from) throws ContradaExceptionBloccante, AddressException,
			MessagingException {
		Message message = new MimeMessage(getSession());
		InternetAddress to[] = new InternetAddress[tos.length];
		int i = 0;
		for (String address : tos) {
			to[i] = new InternetAddress(address);
			i++;
		}

		if (from == null || from.trim().isEmpty()) {
			from = Configuration.getProperty("fromMail");
		}

		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, to);
		message.setSubject(subject);
		message.setContent(content, "text/plain");

		Transport.send(message);
	}

	public static void inviaMail(String[] tos, String subject, String content)
			throws MessagingException, ContradaExceptionBloccante {

		inviaMail(tos, subject, content, null);

	}

	
}
