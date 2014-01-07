package it.contrada.enumcontrada;

public enum TipoFlusso {
	RID(1), BOLLETTINO(2),PREAUTORIZZAZIONE(3);

	private int flusso;

	private TipoFlusso(int flusso) {
		this.flusso = flusso;
	}

	public int getFlusso() {
		return flusso;
	}

}
