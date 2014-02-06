package it.contrada.backingbeans.pagecodes;

import it.contrada.web.util.Errori;
import it.contrada.web.util.Javascript;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.icesoft.faces.context.effects.JavascriptContext;

public class ErrorPage {

	private static Log log = LogFactory.getLog(ErrorPage.class);

	public ErrorPage() {
		apppendJavaScript();
	}

	private String errorMessage;
	private String viewFrom;
	private String stackTrace;
	private boolean showStack = false;
	private String link = "Mostra Log";

	public String getLink() {
		return link;
	}

	public boolean isShowStack() {
		return showStack;
	}

	public void setShowStack(boolean showStack) {
		this.showStack = showStack;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public String getViewFrom() {
		return viewFrom;
	}

	public String getErrorMessage() {
		setErrors();
		return errorMessage;
	}

	public void showLog(ActionEvent e) {
		showStack = !showStack;
		link = "Mostra Log";
		if (showStack) {
			link = "Nascondi Log";
		}
	}

	private void apppendJavaScript() {
		StringBuilder sb = new StringBuilder();
		sb.append("top.document.getElementById('loadingSpan').style.visibility = 'hidden';");
		sb.append("top.document.getElementById('backgroundTransparency').style.display = 'none';");		
		JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), sb.toString());

	}

	public void setErrors() {
		// Get the current JSF context
		try {

			FacesContext context = FacesContext.getCurrentInstance();
			Map requestMap = context.getExternalContext().getRequestMap();
			String src = FacesContext.getCurrentInstance().getViewRoot()
					.getViewId();
			
			apppendJavaScript();

			// Fetch the exception

			Object exc = requestMap.get("javax.servlet.error.exception");

			if (exc == null) {
				exc = "Temporanei probolemi di collegamento";
				return;
			}

			if (exc instanceof Throwable) {

				Throwable ex = ((Throwable) requestMap
						.get("javax.servlet.error.exception"));

				errorMessage = Errori.TEMP_PROB_COLL;

				StringWriter writer = new StringWriter();
				PrintWriter pw = new PrintWriter(writer);

				// Fill the stack trace into the write
				fillStackTrace(ex, pw);

				ex.printStackTrace(pw);

				stackTrace = writer.toString();
			} else {
				stackTrace = exc.toString();
			}

			log.error(stackTrace);
	
		} catch (Throwable e) {
			log.error("errore nel caricamento della pagina di errore", e);
		}

	}

	/**
	 * Write the stack trace from an exception into a writer.
	 * 
	 * @param ex
	 *            Exception for which to get the stack trace
	 * @param pw
	 *            PrintWriter to write the stack trace
	 */
	private void fillStackTrace(Throwable ex, PrintWriter pw) {
		if (null == ex) {
			return;
		}

		// ex.printStackTrace(pw);

		// The first time fillStackTrace is called it will always
		// be a ServletException
		if (ex instanceof ServletException) {
			Throwable cause = ((ServletException) ex).getRootCause();
			if (null != cause) {
				pw.println("Root Cause:");
				fillStackTrace(cause, pw);
			}
		} else {
			// Embedded cause inside the ServletException
			Throwable cause = ex.getCause();

			if (null != cause) {
				pw.println("Cause:");
				fillStackTrace(cause, pw);
			}
		}

	}

}
