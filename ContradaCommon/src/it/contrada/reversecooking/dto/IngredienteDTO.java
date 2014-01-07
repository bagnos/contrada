package it.contrada.reversecooking.dto;

import java.io.Serializable;

public class IngredienteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7800200364428342830L;
	private int idIngrediente;
	private String nomeIngrediente;

	private String nomeImmagine;
	private String unitaMisura;
	private String note;
	private Integer qta;
	private Integer idUnitaMisura;

	public Integer getIdUnitaMisura() {
		return idUnitaMisura;
	}

	public void setIdUnitaMisura(Integer idUnitaMisura) {
		this.idUnitaMisura = idUnitaMisura;
	}

	public String getUnitaMisura() {
		return unitaMisura;
	}

	public void setUnitaMisura(String unitaMisura) {
		this.unitaMisura = unitaMisura;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getQta() {
		return qta;
	}

	public void setQta(Integer qta) {
		this.qta = qta;
	}

	public String getNomeImmagine() {
		return nomeImmagine;
	}

	public void setNomeImmagine(String nomeImmagine) {
		this.nomeImmagine = nomeImmagine;
	}

	public int getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(int idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getNomeIngrediente() {
		return nomeIngrediente;
	}

	public void setNomeIngrediente(String nomeIngrediente) {
		this.nomeIngrediente = nomeIngrediente;
	}

}
