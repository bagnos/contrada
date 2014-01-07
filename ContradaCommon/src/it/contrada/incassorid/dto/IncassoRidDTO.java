package it.contrada.incassorid.dto;

import java.io.Serializable;

public class IncassoRidDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7554247388272451685L;
	private Long idFlussoAddebito;
	private Integer nrAnno;
	private Integer nrMese;
	private Integer idRid;
	private String txIntestatario;
	private java.sql.Date dtValuta; 
	private java.sql.Date dtEsito;
	private Integer imRichiesto;
	private String cdIban;
	private Integer cdAbi;
	private Integer cdCab; 
	private String nrConto;
	private Integer idRidEsito;
	private String cdFisc;
	private String txIndirizzo;
	private String cdCap;
	private String dsComune;
	private String cdProv;
	private Integer idIncassoRid;
	private String txRifDebito;
	private java.sql.Date dtInvio;
	private String dsEsitoRid;
	private String dsCausaleRid;
	private String txNomeFile;
	private String dsIncassoRid;
	private String dsMese;
	
	
	
	
	
	public String getDsMese() {
		return dsMese;
	}
	public void setDsMese(String dsMese) {
		this.dsMese = dsMese;
	}
	public String getDsIncassoRid() {
		return dsIncassoRid;
	}
	public void setDsIncassoRid(String dsIncassoRid) {
		this.dsIncassoRid = dsIncassoRid;
	}
	public String getTxNomeFile() {
		return txNomeFile;
	}
	public void setTxNomeFile(String txNomeFile) {
		this.txNomeFile = txNomeFile;
	}
	public String getDsCausaleRid() {
		return dsCausaleRid;
	}
	public void setDsCausaleRid(String dsCausaleRid) {
		this.dsCausaleRid = dsCausaleRid;
	}
	public String getDsEsitoRid() {
		return dsEsitoRid;
	}
	public void setDsEsitoRid(String dsEsitoRid) {
		this.dsEsitoRid = dsEsitoRid;
	}
	public java.sql.Date getDtInvio() {
		return dtInvio;
	}
	public void setDtInvio(java.sql.Date dtInvio) {
		this.dtInvio = dtInvio;
	}
	public Integer getIdIncassoRid() {
		return idIncassoRid;
	}
	public void setIdIncassoRid(Integer idIncassoRid) {
		this.idIncassoRid = idIncassoRid;
	}
	public String getTxRifDebito() {
		return txRifDebito;
	}
	public void setTxRifDebito(String txRifDebito) {
		this.txRifDebito = txRifDebito;
	}
	public String getTxIndirizzo() {
		return txIndirizzo;
	}
	public void setTxIndirizzo(String txIndirizzo) {
		this.txIndirizzo = txIndirizzo;
	}
	public String getCdCap() {
		return cdCap;
	}
	public void setCdCap(String cdCap) {
		this.cdCap = cdCap;
	}
	public String getDsComune() {
		return dsComune;
	}
	public void setDsComune(String dsComune) {
		this.dsComune = dsComune;
	}
	public String getCdProv() {
		return cdProv;
	}
	public void setCdProv(String cdProv) {
		this.cdProv = cdProv;
	}
	public String getCdFisc() {
		return cdFisc;
	}
	public void setCdFisc(String cdFisc) {
		this.cdFisc = cdFisc;
	}
	public Long getIdFlussoAddebito() {
		return idFlussoAddebito;
	}
	public void setIdFlussoAddebito(Long idFlussoAddebito) {
		this.idFlussoAddebito = idFlussoAddebito;
	}
	public Integer getNrAnno() {
		return nrAnno;
	}
	public void setNrAnno(Integer nrAnno) {
		this.nrAnno = nrAnno;
	}
	public Integer getNrMese() {
		return nrMese;
	}
	public void setNrMese(Integer nrMese) {
		this.nrMese = nrMese;
	}
	public Integer getIdRid() {
		return idRid;
	}
	public void setIdRid(Integer idRid) {
		this.idRid = idRid;
	}
	public String getTxIntestatario() {
		return txIntestatario;
	}
	public void setTxIntestatario(String txIntestatario) {
		this.txIntestatario = txIntestatario;
	}
	public java.sql.Date getDtValuta() {
		return dtValuta;
	}
	public void setDtValuta(java.sql.Date dtValuta) {
		this.dtValuta = dtValuta;
	}
	public java.sql.Date getDtEsito() {
		return dtEsito;
	}
	public void setDtEsito(java.sql.Date dtEsito) {
		this.dtEsito = dtEsito;
	}
	public Integer getImRichiesto() {
		return imRichiesto;
	}
	public void setImRichiesto(Integer imRichiesto) {
		this.imRichiesto = imRichiesto;
	}
	public String getCdIban() {
		return cdIban;
	}
	public void setCdIban(String cdIban) {
		this.cdIban = cdIban;
	}
	public Integer getCdAbi() {
		return cdAbi;
	}
	public void setCdAbi(Integer cdAbi) {
		this.cdAbi = cdAbi;
	}
	public Integer getCdCab() {
		return cdCab;
	}
	public void setCdCab(Integer cdCab) {
		this.cdCab = cdCab;
	}
	public String getNrConto() {
		return nrConto;
	}
	public void setNrConto(String nrConto) {
		this.nrConto = nrConto;
	}
	public Integer getIdRidEsito() {
		return idRidEsito;
	}
	public void setIdRidEsito(Integer idRidEsito) {
		this.idRidEsito = idRidEsito;
	}
	
}
