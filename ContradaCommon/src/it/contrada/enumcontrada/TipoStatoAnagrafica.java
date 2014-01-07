package it.contrada.enumcontrada;

public enum TipoStatoAnagrafica {
	Attiva(1), Cessata(2), Deceduta(3), Sospesa(4);

	private int statoAnag;

	private TipoStatoAnagrafica(int statoAnag) {
		this.statoAnag = statoAnag;
	}

	public int getStatoAnagrafica() {
		return statoAnag;
	}
	
	public static TipoStatoAnagrafica lookUpMeseByOrdinal(int i) {   
        return TipoStatoAnagrafica.values()[i-1];   
    }
}
