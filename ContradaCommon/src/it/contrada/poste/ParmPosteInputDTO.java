package it.contrada.poste;

import java.util.List;

public class ParmPosteInputDTO {
	
	private int annoDa;
	private int annoA;
	private List<Integer> tipoIncassi;
	
	public int getAnnoDa() {
		return annoDa;
	}
	public void setAnnoDa(int annoDa) {
		this.annoDa = annoDa;
	}
	public int getAnnoA() {
		return annoA;
	}
	public void setAnnoA(int annoA) {
		this.annoA = annoA;
	}
	public List<Integer> getTipoIncassi() {
		return tipoIncassi;
	}
	public void setTipoIncassi(List<Integer> tipoIncassi) {
		this.tipoIncassi = tipoIncassi;
	}

}
