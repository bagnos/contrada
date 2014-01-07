package it.contrada.enumcontrada;

public enum TipoRata {
	ESTEMPORANEA(70);

	private int rata;

	private TipoRata(int rata) {
		this.rata = rata;
	}

	public int getRata() {
		return rata;
	}
}
