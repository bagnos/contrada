package it.contrada.listeners;

import it.contrada.web.util.FacesUtils;
import it.contrada.web.util.HelperSession;

import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ContradaNavHandler extends NavigationHandler {

	private static transient Log logger;

	static {
		logger = LogFactory.getLog(ContradaNavHandler.class);
	}
	
	private NavigationHandler baseHandler;

	public ContradaNavHandler(NavigationHandler baseHandler) {

		this.baseHandler = baseHandler;
	}

	@Override
	public void handleNavigation(FacesContext context, String act,
			String outcome) {
		// TODO Auto-generated method stub
		String src = FacesContext.getCurrentInstance().getViewRoot()
				.getViewId();

		if (logger.isDebugEnabled()) {
			logger.debug("fromAction: " + act + ", outcome: " + outcome
					+ ", oldViewID: " + src);
		}
		if (outcome!=null && outcome.equalsIgnoreCase("BACK")) {
			if (logger.isDebugEnabled()) {
				logger.debug("Navigazione all'indietro!");
			}

			
			
			UIViewRoot backViewRoot = context.getApplication().getViewHandler()
					.createView(context, FacesUtils.getErrorBean().getViewSource());

			context.setViewRoot(backViewRoot);

		
		} else {
			// TODO NAVAIGAZIONE TRA URL
			// if(outcome.startsWith("/group"))
			// try {
			// context.getExternalContext().redirect(outcome);
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// else{

			logger.debug("Navigazione standard!!");
			
			baseHandler.handleNavigation(context, act, outcome);

		
			// }
		}
		
	}

}
