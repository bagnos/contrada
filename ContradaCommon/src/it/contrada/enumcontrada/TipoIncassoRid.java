package it.contrada.enumcontrada;

public enum TipoIncassoRid {
	PROTETTORATO(1), PALCHI(2), SOTTOSCRIZIONI(3), TUTTE(4);

	private int incasso;

	private TipoIncassoRid(int incasso) {
		this.incasso = incasso;
	}

	public int getIncasso() {
		return incasso;
	}

	public static TipoIncassoRid valueOf(int incasso) {
		switch (incasso) {
		case 1: {
			return TipoIncassoRid.PROTETTORATO;

		}
		case 2: {
			return TipoIncassoRid.PALCHI;

		}
		case 3: {
			return TipoIncassoRid.SOTTOSCRIZIONI;
		}
		case 4: {
			return TipoIncassoRid.TUTTE;

		}

		}
		return null;
	}
}
