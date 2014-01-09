package it.contrada.web.enumcontrada;

public enum TipoRicercaIncassi {
	Anagrafica(1);

	private int ricercaIncasso;

	private TipoRicercaIncassi(int ricercaIncasso) {
		this.ricercaIncasso = ricercaIncasso;
	}

	public int getRicerca() {
		return ricercaIncasso;
	}
	
}
