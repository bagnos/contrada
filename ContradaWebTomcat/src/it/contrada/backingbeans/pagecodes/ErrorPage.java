package it.contrada.backingbeans.pagecodes;

import it.contrada.bean.GestioneAnagrafica;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.Errori;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ErrorPage {

	private static Log log = LogFactory.getLog(ErrorPage.class);

	public ErrorPage() {

	}

	private String errorMessage;
	private String viewFrom;
	private String stackTrace;

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

	public void setErrors() {
		// Get the current JSF context
		FacesContext context = FacesContext.getCurrentInstance();
		Map requestMap = context.getExternalContext().getRequestMap();
		String src = FacesContext.getCurrentInstance().getViewRoot()
				.getViewId();

		// Fetch the exception

		Object exc = requestMap.get("javax.servlet.error.exception");

		if (exc==null)
		{
			exc="Temporanei probolemi di collegamento";
			return;
		}
		
		if (exc instanceof Throwable) {

			Throwable ex = ((Throwable) requestMap
					.get("javax.servlet.error.exception"));

			if (ex instanceof ContradaExceptionNonBloccante) {
				errorMessage = ((ContradaExceptionNonBloccante) ex)
						.getMessage();
			} else if (ex instanceof ContradaExceptionBloccante) {
				errorMessage = ((ContradaExceptionBloccante) ex).getMessage();
			} else {
				errorMessage = Errori.TEMP_PROB_COLL;
			}

			StringWriter writer = new StringWriter();
			PrintWriter pw = new PrintWriter(writer);

			// Fill the stack trace into the write
			fillStackTrace(ex, pw);

			ex.printStackTrace(pw);

			stackTrace = writer.toString();
		} else {
			stackTrace=exc.toString();
		}

		log.error(stackTrace);

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
