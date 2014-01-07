package it.contrada.enumcontrada;

public enum TipoCausaleIncasso {
	CAUSALE_90210(90210),CAUSALE_50010(50010);

	private int causaleIncasso;

	private TipoCausaleIncasso(int causaleIncasso) {
		this.causaleIncasso = causaleIncasso;
	}

	public int getTipoCausaleIncasso() {
		return causaleIncasso;
	}
}
