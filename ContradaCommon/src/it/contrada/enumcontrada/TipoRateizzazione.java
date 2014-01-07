package it.contrada.enumcontrada;

public enum TipoRateizzazione {
	ESTEMPORANEA(22);

	private int rateizzazione;

	private TipoRateizzazione(int rateizzazione) {
		this.rateizzazione = rateizzazione;
	}

	public int getRateizzazione() {
		return rateizzazione;
	}
}
