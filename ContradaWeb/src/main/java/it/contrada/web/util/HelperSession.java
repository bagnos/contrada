package it.contrada.web.util;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HelperSession {

	private HelperSession() {
	}

	private static Log log = LogFactory.getLog(HelperSession.class);

	

	public static Object getFromSession(String key) {

		if (FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().containsKey(key))

		{
			if (log.isTraceEnabled()) {
				log.trace("letto dalla session <key,value> "
						+ key
						+ ","
						+ FacesContext.getCurrentInstance()
								.getExternalContext().getSessionMap().get(key));
			}
			return FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().get(key);
		} else {
			if (log.isTraceEnabled()) {
				log.trace("chiave non presente in session key: " + key);
			}
			return null;
		}
	}

	public static void putInSession(String key, Object value) {
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().containsKey(key))

		{
			if (log.isTraceEnabled()) {
				log.trace("valore key già presente in session, si rimuove: "
						+ key);
			}
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().remove(key);

		}

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(key, value);

		if (log.isTraceEnabled()) {
			log.trace("inserita in session  <key,value>: " + key + "," + value);
		}

	}

	public static void removeFromSession(String key) {
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().containsKey(key))

		{
			if (log.isTraceEnabled()) {
				log.trace("valore key presente in session, si rimuove: " + key);
			}
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().remove(key);

		}

	}

	public static Object getInitParameter(String parameter) {
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getInitParameterMap().containsKey(parameter))

		{
			return FacesContext.getCurrentInstance().getExternalContext()
					.getInitParameter(parameter);
		} else {
			return null;
		}
	}

	/*
	public static void putRequestParameter(String key, String value) {
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().containsKey(key))

		{
			if (log.isTraceEnabled()) {
				log.trace("valore key già presente in request, si rimuove: "
						+ key);
			}
			FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap().remove(key);

		}

		FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().put(key, value);

		if (log.isTraceEnabled()) {
			log.trace("inserita in request  <key,value>: " + key + "," + value);
		}
	}*/

	public static void putInRequest(String key, Object value) {
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().containsKey(key))

		{
			if (log.isTraceEnabled()) {
				log.trace("valore key già presente in request, si rimuove: "
						+ key);
			}
			FacesContext.getCurrentInstance().getExternalContext()
					.getRequestMap().remove(key);

		}

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(key, value);

		if (log.isTraceEnabled()) {
			log.trace("inserita in request  <key,value>: " + key + "," + value);
		}
	}

	public static Object getFromRequest(String key) {

		if (FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().containsKey(key))

		{
			if (log.isTraceEnabled()) {
				log.trace("letto dalla request <key,value> "
						+ key
						+ ","
						+ FacesContext.getCurrentInstance()
								.getExternalContext().getRequestMap().get(key));
			}
			return FacesContext.getCurrentInstance().getExternalContext()
					.getRequestMap().get(key);
		} else {
			if (log.isTraceEnabled()) {
				log.trace("chiave non presente in request key: " + key);
			}
			return null;
		}
	}

	public static Object getBeanFromRequest(String nomeBean) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get(nomeBean);
	}

	public static <T> T findManagedBean(String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
		return (T) context.getApplication().evaluateExpressionGet(context,
				"#{" + beanName + "}", Object.class);

	}

	public static void removeBeanFromRequest(String nomeBean) {
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().containsKey(nomeBean)) {
			FacesContext.getCurrentInstance().getExternalContext()
					.getRequestMap().put(nomeBean, null);
		}
	}

}
