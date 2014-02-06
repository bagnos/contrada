package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.GestioneGestoreBD;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.GestoreDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.mail.BaseMail;
import it.contrada.web.enumcontrada.TipoGravitaMessage;
import it.contrada.web.util.Configuration;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
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
	private int gruppo;

	public int getGruppo() {
		return gruppo;
	}

	public void setGruppo(int gruppo) {
		this.gruppo = gruppo;
	}

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
		if (from == null || from.isEmpty()) {
			from = Configuration.getProperty("fromMail");
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

	public void inviaMail(ActionEvent e) {
		try {
			// indirizzi manuali;
			String[] addressesManual = to.split(";");

			//indirizzi di gruppo
			List<String> addressesGroup = new ArrayList<String>();
			if (gruppo == 1) {
				// rete
				List<GestoreDTO> anags = GestioneGestoreBD.elencaGestori();
				if (anags != null) {
					for (GestoreDTO g : anags) {
						addressesGroup.add(g.getMail());
					}
				}
			}
			
			// merge con eventuali gruppi;
			for (String s : addressesManual) {
				if (s!=null && !s.isEmpty())
				addressesGroup.add(s);
			}

			if (!addressesGroup.isEmpty()) {
				addressesGroup.toArray(addressesManual);
				BaseMail.inviaMail(addressesManual, subject, content, from);
				writeInfoMessage(TipoGravitaMessage.SUCCESS,
						"Email inviata correttamente");
			}

			else {
				writeInfoMessage(TipoGravitaMessage.INFO,
						"Nessun destinatraio inserito");
			}

		} catch (Exception ex) {
			writeErrorMessage("errore nell'invio mail", ex);
		}

	}

	public void gruppoChange(ValueChangeEvent ev)
			throws ContradaExceptionBloccante {
		if (ev != null) {
			int gruppo = (Integer) ev.getNewValue();

			// rete
			from = Configuration.getProperty("fromMail");
			if (gruppo == 1) {
				from = Configuration.getProperty("fromMailRete");
			}

		}
	}

}
