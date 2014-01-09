package it.contrada.listeners;

import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.Errori;
import it.contrada.web.util.FacesUtils;
import it.contrada.web.util.HelperSession;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.faces.application.ActionListenerImpl;

public class ContradaActionListener extends ActionListenerImpl 
		 {

	private static transient Log logger;

	static {
		logger = LogFactory.getLog(ContradaActionListener.class);
	}

	@Override
	public void processAction(ActionEvent arg0) {

		// if(errBean.getIsError())
		// {
		// errBean.setErrorMessage(null);
		// errBean.setViewFrom(null);
		// }
		String source = null;
		String dest = null;
		try {
			source = FacesContext.getCurrentInstance().getViewRoot()
					.getViewId();

			if (logger.isDebugEnabled()) {
				logger.debug("Prima di process Action - " + source);
			}

			super.processAction(arg0);
			dest = FacesContext.getCurrentInstance().getViewRoot().getViewId();

			if (logger.isDebugEnabled()) {
				logger.debug("Dopo process Action - " + dest);
			}
		}

		catch (Exception e) {

			it.contrada.backingbeans.ErrorBean errBean = FacesUtils
					.getErrorBean();
			errBean.setViewSource(source);
			errBean.setViewDest(dest);
			logger.error("EXCEPTION: " + e.getMessage(), e);

			if (e.getCause().getCause() instanceof ContradaExceptionBloccante) {
				errBean.setMessage(((ContradaExceptionBloccante) e.getCause()
						.getCause()).getMessage());
			}
			else if (e.getCause().getCause() instanceof ContradaExceptionNonBloccante) {
				errBean.setMessage(((ContradaExceptionNonBloccante) e
						.getCause().getCause()).getMessage());
			} else {
				errBean.setMessage(Errori.TEMP_PROB_COLL);
			}
			FacesContext facesContext = FacesContext.getCurrentInstance();
			Application application = facesContext.getApplication();
			NavigationHandler navigationHandler = application
					.getNavigationHandler();
			navigationHandler.handleNavigation(facesContext, null,
					"exceptionNavigation");
			facesContext.renderResponse();
		}
	}

}
