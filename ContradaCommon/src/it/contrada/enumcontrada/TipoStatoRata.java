package it.contrada.enumcontrada;

public enum TipoStatoRata {
	Pagata(1), Insoluta(2),Inviata(3);

	private int statoRata;

	private TipoStatoRata(int statoRata) {
		this.statoRata = statoRata;
	}

	public int getStatoRata() {
		return statoRata;
	}
}
