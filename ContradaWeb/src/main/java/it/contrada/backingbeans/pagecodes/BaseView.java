package it.contrada.backingbeans.pagecodes;

import it.contrada.web.enumcontrada.TipoGravitaMessage;

import javax.faces.event.ActionEvent;

public class BaseView {

	private String messaggioInfoMessage;
	private String styleClassInfoMessage;
	private  boolean renderInfoMessage;

	public  boolean isRenderInfoMessage() {
		return renderInfoMessage;
	}

	public  String getMessaggioInfoMessage() {
		return messaggioInfoMessage;
	}

	public  String getStyleClassInfoMessage() {
		return styleClassInfoMessage;
	}

	public   void writeInfoMessage(TipoGravitaMessage gravita, String messaggio) {
		renderInfoMessage = false;
		if (messaggio != null && !messaggio.isEmpty()) {
			styleClassInfoMessage = gravita.getTipoGravitaMessage();
			this.messaggioInfoMessage = messaggio;
			renderInfoMessage = true;
		}
	}

	public void hideInfoMessage() {
		renderInfoMessage = false;
		messaggioInfoMessage = "";
	}

	public void chiudiInfoMessage(ActionEvent e) {
		hideInfoMessage();
	}

}
