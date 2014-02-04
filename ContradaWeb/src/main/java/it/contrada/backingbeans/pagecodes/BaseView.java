package it.contrada.backingbeans.pagecodes;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import it.contrada.exceptions.ContradaException;
import it.contrada.web.enumcontrada.TipoGravitaMessage;
import it.contrada.web.util.FacesUtils;

import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseView {

	private String messaggioInfoMessage;
	private String styleClassInfoMessage;
	private boolean renderInfoMessage;
	protected final Log log = LogFactory.getLog(this.getClass());
	private String stackTrace;
	private boolean renderStackTrace;
	private boolean renderImgLog;
	private String titleLog;

	public String getTitleLog() {
		return titleLog;
	}

	public boolean isRenderImgLog() {
		return renderImgLog;
	}

	public boolean isRenderStackTrace() {
		return renderStackTrace;

	}

	public String getStackTrace() {
		return stackTrace;
	}

	public boolean isRenderInfoMessage() {
		return renderInfoMessage;
	}

	public String getMessaggioInfoMessage() {
		return messaggioInfoMessage;
	}

	public String getStyleClassInfoMessage() {
		return styleClassInfoMessage;
	}

	public void showLog(ActionEvent e) {
		renderStackTrace = !renderStackTrace;
		titleLog = "Mostra Log";
		if (renderStackTrace) {
			titleLog = "Nascondi Log";
		}
	}

	public void writeInfoMessage(TipoGravitaMessage gravita, String messaggio) {
		renderInfoMessage = false;
		renderImgLog = false;
		renderStackTrace = false;
		if (messaggio != null && !messaggio.isEmpty()) {
			styleClassInfoMessage = gravita.getTipoGravitaMessage();
			this.messaggioInfoMessage = messaggio;
			renderInfoMessage = true;
		}
	}

	/**
	 * scrive l'ecezione nel log e mostra il messaggio dell'eccezione nella
	 * infoMessage se l'eccezione è di tipo ContradaException altrimenti scrive
	 * Temporanei Problemi Di Collegamento
	 * 
	 * @param messageLog
	 *            messaggio di errore da inserire nel log insiema allo stack
	 *            trace
	 * @param e
	 *            eccezione
	 */
	public void writeErrorMessage(String messageLog, Exception e) {
		renderStackTrace = false;
		renderImgLog = true;
		titleLog = "Mostra Log";
		styleClassInfoMessage = TipoGravitaMessage.ERROR
				.getTipoGravitaMessage();
		if (e instanceof ContradaException) {
			this.messaggioInfoMessage = e.getMessage();
		} else {
			this.messaggioInfoMessage = "TEMPORANEI PROBLEMI DI COLLEGAMENTO";
		}
		renderInfoMessage = true;

		stackTrace = getStackTrace(e);
		renderStackTrace = true;
		if (stackTrace != null) {
			showLog(null);
		}

		log.error(messageLog, e);

	}

	public void hideInfoMessage() {
		renderInfoMessage = false;
		messaggioInfoMessage = "";
		renderStackTrace=false;
	}

	public void chiudiInfoMessage(ActionEvent e) {
		hideInfoMessage();
	}

	// Fill the stack trace into the write

	private String getStackTrace(Throwable ex) {
		String exceptionAsString = null;
		if (ex != null) {
			try {
				StringWriter sw = new StringWriter();
				ex.printStackTrace(new PrintWriter(sw));
				exceptionAsString = sw.toString();
			} catch (Exception e) {
				log.error("errore nella generazone dello stackTrace", e);
			}
		}
		return exceptionAsString;

	}

}
