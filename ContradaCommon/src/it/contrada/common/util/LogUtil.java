package it.contrada.common.util;

import org.apache.commons.logging.Log;

public class LogUtil {

	private LogUtil() {
	}

	public static void logTraceMessage(Log log, String Message) {
		if (log.isTraceEnabled()) {
			log.trace(Message);
		}
	}
}
