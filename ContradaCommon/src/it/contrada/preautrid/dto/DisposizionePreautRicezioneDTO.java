package it.contrada.preautrid.dto;

import java.io.Serializable;

import it.contrada.enumcontrada.TipoStatoRid;

public class DisposizionePreautRicezioneDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4062075624485029424L;
	private Record12DTO records12;
	private Record30DTO records30;
	private Record40DTO records40;
	private Record45DTO records45;
	private Record50DTO records50;
	private Record70DTO records70;
	private String dsCausale;
	private TipoStatoRid tipoStatoRid;
	private int idCausale;
	private String stato;
	private String intestatario;
	

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public int getIdCausale() {
		return idCausale;
	}

	public void setIdCausale(int idCausale) {
		this.idCausale = idCausale;
	}

	public TipoStatoRid getTipoStatoRid() {
		return tipoStatoRid;
	}

	public void setTipoStatoRid(TipoStatoRid tipoStatoRid) {
		this.tipoStatoRid = tipoStatoRid;
	}

	public Record50DTO getRecords50() {
		return records50;
	}

	public void setRecords50(Record50DTO records50) {
		this.records50 = records50;
	}

	public Record45DTO getRecords45() {
		return records45;
	}

	public void setRecords45(Record45DTO records45) {
		this.records45 = records45;
	}

	public Record70DTO getRecords70() {
		return records70;
	}

	public void setRecords70(Record70DTO records70) {
		this.records70 = records70;
	}

	public Record40DTO getRecords40() {
		return records40;
	}

	public void setRecords40(Record40DTO records40) {
		this.records40 = records40;
	}

	public Record30DTO getRecords30() {
		return records30;
	}

	public void setRecords30(Record30DTO records30) {
		this.records30 = records30;
	}

	public Record12DTO getRecords12() {
		return records12;
	}

	public void setRecords12(Record12DTO records12) {
		this.records12 = records12;
	}

	public String getDsCausale() {
		return dsCausale;
	}

	public void setDsCausale(String dsCausale) {
		this.dsCausale = dsCausale;
	}

}
