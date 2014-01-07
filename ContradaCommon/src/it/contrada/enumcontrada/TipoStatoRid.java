package it.contrada.enumcontrada;

public enum TipoStatoRid {
	Attiva(1), Cessata(2), Spedita_in_Banca(3), Censita(4), Sospesa(5);

	private int statoRid;

	private TipoStatoRid(int statoRid) {
		this.statoRid = statoRid;
	}

	public int getStatoRid() {
		return statoRid;
	}
	
	public static TipoStatoRid lookUpMeseByOrdinal(int i) {   
        return TipoStatoRid.values()[i-1];   
    }
	
}
