package it.contrada.backingbeans.pagecodes;

import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.mail.BaseMail;
import it.contrada.web.enumcontrada.TipoGravitaMessage;
import it.contrada.web.util.Configuration;

import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;
import javax.naming.NamingException;

public class InvioMailView extends BaseView {
	private String to;
	private String subject;
	private String content;
	private String from;
	private String username;
	private String psw;
	private String serverSMPT;
	private String port;
	
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getServerSMPT() {
		return serverSMPT;
	}

	public void setServerSMPT(String serverSMPT) {
		this.serverSMPT = serverSMPT;
	}

	public String getFrom() throws ContradaExceptionBloccante {
		if (from==null || from.isEmpty())
		{
			from=Configuration.getProperty("fromMail");
		}
		
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
	public void inviaMail(ActionEvent e) throws  MessagingException, NamingException, ContradaExceptionBloccante
	{
		String[] addresses=to.split(";");
			
		BaseMail.inviaMail(addresses, subject, content,from);
		
		writeInfoMessage(TipoGravitaMessage.SUCCESS, "Email inviata correttamente");
	
	}
	
	
}
