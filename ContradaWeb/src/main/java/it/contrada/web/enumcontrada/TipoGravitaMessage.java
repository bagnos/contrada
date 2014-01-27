package it.contrada.web.enumcontrada;

public enum TipoGravitaMessage {
	SUCCESS("success"),
	ERROR("error"),
	INFO("info"),
	WARNING("warning");
	
	private String tipoGravitaMessage;

	private TipoGravitaMessage(String tipoGravitaMessage) {
		this.tipoGravitaMessage = tipoGravitaMessage;
	}

	public String getTipoGravitaMessage() {
		return tipoGravitaMessage;
	}
}
