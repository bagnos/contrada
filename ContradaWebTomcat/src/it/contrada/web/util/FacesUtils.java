package it.contrada.web.util;

import it.contrada.backingbeans.ErrorBean;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.FacesEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FacesUtils {

	/**
	 * Get servlet context.
	 * 
	 * @return the servlet context
	 */

	public static String getUser() {
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getUserPrincipal() != null) {
			return FacesContext.getCurrentInstance().getExternalContext()
					.getUserPrincipal().toString();
		} else {
			return "UNKNOWN";
		}
	}

	public static Map<String, String> getQueryString() {
		Map<String, String> queryMap = new Hashtable<String, String>();
		HttpServletRequest theRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String fullQueryString =  theRequest.getQueryString();
				

		if (fullQueryString == null) {
			return queryMap;
		}

		String[] keyValuePairs = fullQueryString.split("&");

		for (String pair : keyValuePairs) {
			String[] keyAndValue = pair.split("=");
			queryMap.put(keyAndValue[0], keyAndValue[1]);

		}

		return queryMap;
	}

	public static ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
	}

	public static ExternalContext getExternalContext() {
		FacesContext fc = FacesContext.getCurrentInstance();
		return fc.getExternalContext();
	}

	public static HttpSession getHttpSession(boolean create) {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(create);
	}

	public static Object getValueExpression(String idValueExpressione,
			FacesEvent e) {
		return e.getComponent().getValueExpression(idValueExpressione)
				.getValue(FacesContext.getCurrentInstance().getELContext());
	}

	public static void redirectToUrl(String url) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(url);
	}

	@SuppressWarnings("unchecked")
	public static <T> T findBean(String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
		return (T) context.getApplication().evaluateExpressionGet(context,
				"#{" + beanName + "}", Object.class);
	}

	public static UIComponent findComponent(String id) {

		UIComponent result = null;
		UIComponent root = FacesContext.getCurrentInstance().getViewRoot();
		if (root != null) {
			result = findComponent(root, id);
		}
		return result;

	}

	private static UIComponent findComponent(UIComponent root, String id) {

		UIComponent result = null;
		if (root.getId().equals(id))
			return root;

		for (UIComponent child : root.getChildren()) {
			if (child.getId().equals(id)) {
				result = child;
				break;
			}
			result = findComponent(child, id);
			if (result != null)
				break;
		}
		return result;
	}

	public static ErrorBean getErrorBean() {
		return (ErrorBean) findBean("errorBean");
	}

	public static void navigationToView(String action) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		NavigationHandler navigationHandler = application
				.getNavigationHandler();
		navigationHandler.handleNavigation(facesContext, null, action);

		facesContext.renderResponse();
	}

	/**
	 * Store the managed bean inside the session scope.
	 * 
	 * @param beanName
	 *            the name of the managed bean to be stored
	 * @param managedBean
	 *            the managed bean to be stored
	 */
	public static void setManagedBeanInSession(String beanName,
			Object managedBean) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(beanName, managedBean);
	}

	/**
	 * Get parameter value from request scope.
	 * 
	 * @param name
	 *            the name of the parameter
	 * @return the parameter value
	 */
	public static String getRequestParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(name);
	}

	/**
	 * Add information message.
	 * 
	 * @param msg
	 *            the information message
	 */
	public static void addInfoMessage(String msg) {
		addInfoMessage(null, msg);
	}

	/**
	 * Add information message to a specific client.
	 * 
	 * @param clientId
	 *            the client id
	 * @param msg
	 *            the information message
	 */
	public static void addInfoMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	/**
	 * Add error message.
	 * 
	 * @param msg
	 *            the error message
	 */
	public static void addErrorMessage(String msg) {
		addErrorMessage(null, msg);
	}

	/**
	 * Add error message to a specific client.
	 * 
	 * @param clientId
	 *            the client id
	 * @param msg
	 *            the error message
	 */
	public static void addErrorMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	private static Application getApplication() {
		ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder
				.getFactory(FactoryFinder.APPLICATION_FACTORY);
		return appFactory.getApplication();
	}

	private static HttpServletRequest getServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	private static String getJsfEl(String value) {
		return "#{" + value + "}";
	}
}
