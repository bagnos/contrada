package it.contrada.dto;

import java.io.Serializable;

public class MembroRidDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3713060614037506397L;
	private String nome;
	private String cognome;	
	private int idAnagrafica;	
	private String intestatario;
	private int quota;
	private String tessera;
	private String modalita;
	private int idRid;
	private boolean selected;
	private int idTessera;
	private int idTipoIncassoRid;
	private String dsTipoIncassoRid;
	private String nominativo;
	private String mail;
	private int idStatoRid;
	private Integer idTipoTessera;
	
	

	public Integer getIdTipoTessera() {
		return idTipoTessera;
	}
	public void setIdTipoTessera(Integer idTipoTessera) {
		this.idTipoTessera = idTipoTessera;
	}
	public int getIdStatoRid() {
		return idStatoRid;
	}
	public void setIdStatoRid(int idStatoRid) {
		this.idStatoRid = idStatoRid;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNominativo() {
		return nominativo;
	}
	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}
	public String getDsTipoIncassoRid() {
		return dsTipoIncassoRid;
	}
	public void setDsTipoIncassoRid(String dsTipoIncassoRid) {
		this.dsTipoIncassoRid = dsTipoIncassoRid;
	}
	public int getIdTipoIncassoRid() {
		return idTipoIncassoRid;
	}
	public void setIdTipoIncassoRid(int idTipoIncassoRid) {
		this.idTipoIncassoRid = idTipoIncassoRid;
	}
	public int getIdTessera() {
		return idTessera;
	}
	public void setIdTessera(int idTessera) {
		this.idTessera = idTessera;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getIntestatario() {
		return intestatario;
	}
	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}
	public int getIdAnagrafica() {
		return idAnagrafica;
	}
	public void setIdAnagrafica(int idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}
	public int getQuota() {
		return quota;
	}
	public void setQuota(int quota) {
		this.quota = quota;
	}
	public String getTessera() {
		return tessera;
	}
	public void setTessera(String tessera) {
		this.tessera = tessera;
	}
	public String getModalita() {
		return modalita;
	}
	public void setModalita(String modalita) {
		this.modalita = modalita;
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
	
	public int getIdRid() {
		return idRid;
	}
	public void setIdRid(int idRid) {
		this.idRid = idRid;
	}
}
