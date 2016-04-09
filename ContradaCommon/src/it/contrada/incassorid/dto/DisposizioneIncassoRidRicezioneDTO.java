package it.contrada.incassorid.dto;

import it.contrada.dto.MembroRidDTO;
import it.contrada.enumcontrada.TipoStatoRid;

import java.io.Serializable;
import java.util.List;

public class DisposizioneIncassoRidRicezioneDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5100732177246151325L;
	private Record30DTO rec30;
	private Record10DTO rec10;
	private Record20DTO rec20;
	private Record50DTO rec50;
	private Record70DTO rec70;
	private int cdCausale;
	private String dsCausale;
	private TipoStatoRid tipoStatoRid;
	private int idRid;
	private String stato;
	private List<MembroRidDTO> membri;
	private String esitoMail;
	private int idStatoRid;
	private String abi;
	private String cab;
	private String conto;
	private boolean selezionato;
	private String dtValuta;
	
	
	public String getDtValuta() {
		return dtValuta;
	}
	public void setDtValuta(String dtValuta) {
		this.dtValuta = dtValuta;
	}
	public boolean isSelezionato() {
		return selezionato;
	}
	public void setSelezionato(boolean selezionato) {
		this.selezionato = selezionato;
	}
	public int getIdStatoRid() {
		return idStatoRid;
	}
	public void setIdStatoRid(int idStatoRid) {
		this.idStatoRid = idStatoRid;
	}
	public String getAbi() {
		return abi;
	}
	public void setAbi(String abi) {
		this.abi = abi;
	}
	public String getCab() {
		return cab;
	}
	public void setCab(String cab) {
		this.cab = cab;
	}
	public String getConto() {
		return conto;
	}
	public void setConto(String conto) {
		this.conto = conto;
	}
	public String getEsitoMail() {
		return esitoMail;
	}
	public void setEsitoMail(String esitoMail) {
		this.esitoMail = esitoMail;
	}
	public List<MembroRidDTO> getMembri() {
		return membri;
	}
	public void setMembri(List<MembroRidDTO> membri) {
		this.membri = membri;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public int getIdRid() {
		return idRid;
	}
	public void setIdRid(int idRid) {
		this.idRid = idRid;
	}
	public TipoStatoRid getTipoStatoRid() {
		return tipoStatoRid;
	}
	public void setTipoStatoRid(TipoStatoRid tipoStatoRid) {
		this.tipoStatoRid = tipoStatoRid;
	}
	public int getCdCausale() {
		return cdCausale;
	}
	public void setCdCausale(int cdCausale) {
		this.cdCausale = cdCausale;
	}
	public String getDsCausale() {
		return dsCausale;
	}
	public void setDsCausale(String dsCausale) {
		this.dsCausale = dsCausale;
	}
	public Record30DTO getRec30() {
		return rec30;
	}
	public void setRec30(Record30DTO rec30) {
		this.rec30 = rec30;
	}
	public Record10DTO getRec10() {
		return rec10;
	}
	public void setRec10(Record10DTO rec10) {
		this.rec10 = rec10;
	}
	public Record20DTO getRec20() {
		return rec20;
	}
	public void setRec20(Record20DTO rec20) {
		this.rec20 = rec20;
	}
	public Record50DTO getRec50() {
		return rec50;
	}
	public void setRec50(Record50DTO rec50) {
		this.rec50 = rec50;
	}
	public Record70DTO getRec70() {
		return rec70;
	}
	public void setRec70(Record70DTO rec70) {
		this.rec70 = rec70;
	}
	
	
}
