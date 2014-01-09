package it.contrada.web.enumcontrada;

public enum TipoDocumento {
	Sottoscrizioni("D1");

	private String tipoDocumento;

	private TipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
}
