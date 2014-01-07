package it.contrada.enumcontrada;

public enum TipoIncasso {
	RID(1), CASSA(2), MAV(3), BOLLETTINO(4), ESATTORE(5),BONIFICO(6);

	private int incasso;

	private TipoIncasso(int incasso) {
		this.incasso = incasso;
	}

	public int getIncasso() {
		return incasso;
	}

}
