package it.contrada.backingbeans;


public class Famiglia {
	private int cdFamiglia;
	private boolean isSelected;	
	private String nome;
	private String cognome;
	private String capoFamiglia;
	private int idAnag;
	
	public int getIdAnag() {
		return idAnag;
	}
	public void setIdAnag(int idAnag) {
		this.idAnag = idAnag;
	}
	public String getCapoFamiglia() {
		return capoFamiglia;
	}
	public void setCapoFamiglia(String capoFamiglia) {
		this.capoFamiglia = capoFamiglia;
	}
	public int getCdFamiglia() {
		return cdFamiglia;
	}
	public void setCdFamiglia(int cdFamiglia) {
		this.cdFamiglia = cdFamiglia;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	


}
