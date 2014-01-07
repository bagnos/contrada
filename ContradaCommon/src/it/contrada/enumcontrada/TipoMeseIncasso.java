package it.contrada.enumcontrada;

public enum TipoMeseIncasso {
	GENNAIO(1),FEBBRAIO(2),MARZO(3),APRILE(4),MAGGIO(5),GIUGNO(6),
	LUGLIO(7),AGOSTO(8),SETTEMBRE(9),OTTOBRE(10),NOVEMBRE(11),DICEMBRE(12),
	RECUPERO(13);

	private int meseIncasso;

	private TipoMeseIncasso(int meseIncasso) {
		this.meseIncasso = meseIncasso;
	}

	public int getMeseIncasso() {
		return meseIncasso;
	}
	
	
	
	public static TipoMeseIncasso lookUpMeseByOrdinal(int i) {   
        return TipoMeseIncasso.values()[i-1];   
    }
}
