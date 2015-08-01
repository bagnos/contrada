package it.contrada.dto;

import java.io.Serializable;
import java.util.List;

public class RidDTO implements Serializable {
	private Integer nrRid;
	private int tipoStatoRid;
	private String dsStatoRid;
	private String intestatarioRid;
	private String cdFiscale;
	private int provinciaResidenza;
	private int comuneResidenza;
	private String capPost;
	private int idStrada;
	private Integer numeroCivico;
	private Integer abi;
	private String paese;
	private Integer cab;
	private String cin;
	private String cinAbi;
	private String numeroCC;
	private String note;
	private Long idOperazione;
	private short idTipoIncassoRid;
	private String dsTipoIncassoRid;

	private List<MembroRidDTO> membri;
	private boolean nuovoRid;
	private String via;
	private String localita;
	private String provincia;
	private String iban;
	StringBuilder buildIban = new StringBuilder();
	private String noteInvioPreaut;
	private boolean invioPreaut;
	private String anagraficaMembro;
	private String recapitoMembro;
	private Integer quota;
	private String dsTessera;
	private OperazioneDTO operazione;
	private String user;
	private java.util.Date dtCensimento;
	private String esitoMail;
	private boolean selezionato;
	private String nrIban;
	

	public String getNrIban() {
		return nrIban;
	}

	public void setNrIban(String nrIban) {
		this.nrIban = nrIban;
	}

	public boolean isSelezionato() {
		return selezionato;
	}

	public void setSelezionato(boolean selezionato) {
		this.selezionato = selezionato;
	}

	public String getEsitoMail() {
		return esitoMail;
	}

	public void setEsitoMail(String esitoMail) {
		this.esitoMail = esitoMail;
	}

	public java.util.Date getDtCensimento() {
		return dtCensimento;
	}

	public void setDtCensimento(java.util.Date dtCensimento) {
		this.dtCensimento = dtCensimento;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public OperazioneDTO getOperazione() {
		return operazione;
	}

	public void setOperazione(OperazioneDTO operazione) {
		this.operazione = operazione;
	}

	public String getRecapitoMembro() {
		return recapitoMembro;
	}

	public void setRecapitoMembro(String recapitoMembro) {
		this.recapitoMembro = recapitoMembro;
	}

	public Integer getQuota() {
		return quota;
	}

	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	public String getDsTessera() {
		return dsTessera;
	}

	public void setDsTessera(String dsTessera) {
		this.dsTessera = dsTessera;
	}

	public String getAnagraficaMembro() {
		return anagraficaMembro;
	}

	public void setAnagraficaMembro(String anagraficaMembro) {
		this.anagraficaMembro = anagraficaMembro;
	}

	public boolean isInvioPreaut() {
		return invioPreaut;
	}

	public void setInvioPreaut(boolean invioPreaut) {
		this.invioPreaut = invioPreaut;
	}

	public String getNoteInvioPreaut() {
		return noteInvioPreaut;
	}

	public void setNoteInvioPreaut(String noteInvioPreaut) {
		this.noteInvioPreaut = noteInvioPreaut;
	}

	public String getIban() {

		buildIban.setLength(0);
		if (paese != null) {
			buildIban.append(paese.trim());
		}
		if (cin != null) {
			buildIban.append(String.format("%2s", cin.trim()).replaceAll(" ",
					"0"));
		}
		if (cinAbi != null) {
			buildIban.append(cinAbi.trim());
		}
		if (abi != null) {
			buildIban.append(String.format("%05d", abi));
		}
		if (cab != null) {
			buildIban.append(String.format("%05d", cab));
		}
		if (numeroCC != null) {
			buildIban.append(String.format("%12s", numeroCC.trim()).replaceAll(
					" ", "0"));
		}
		iban = buildIban.toString().toUpperCase().trim();
		return iban;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public boolean isNuovoRid() {
		return nuovoRid;
	}

	public void setNuovoRid(boolean nuovoRid) {
		this.nuovoRid = nuovoRid;
	}

	public List<MembroRidDTO> getMembri() {
		return membri;
	}

	public void setMembri(List<MembroRidDTO> membri) {
		this.membri = membri;
	}

	public String getDsTipoIncassoRid() {
		return dsTipoIncassoRid;
	}

	public void setDsTipoIncassoRid(String dsTipoIncassoRid) {
		this.dsTipoIncassoRid = dsTipoIncassoRid;
	}

	public String getDsStatoRid() {
		return dsStatoRid;
	}

	public void setDsStatoRid(String dsStatoRid) {
		this.dsStatoRid = dsStatoRid;
	}

	public short getIdTipoIncassoRid() {
		return idTipoIncassoRid;
	}

	public void setIdTipoIncassoRid(short idTipoIncassoRid) {
		this.idTipoIncassoRid = idTipoIncassoRid;
	}

	public Long getIdOperazione() {
		return idOperazione;
	}

	public void setIdOperazione(Long idOper) {
		this.idOperazione = idOper;
	}

	/*
	 * private boolean isSelected;
	 * 
	 * public boolean isSelected() { return isSelected; }
	 * 
	 * public void setSelected(boolean isSelected) { this.isSelected =
	 * isSelected; }
	 */
	public Integer getNrRid() {
		return nrRid;
	}

	public void setNrRid(Integer nrRid) {
		this.nrRid = nrRid;
	}

	public int getTipoStatoRid() {
		return tipoStatoRid;
	}

	public void setTipoStatoRid(int tipoStatoRid) {
		this.tipoStatoRid = tipoStatoRid;
	}

	public String getIntestatarioRid() {
		return intestatarioRid;
	}

	public void setIntestatarioRid(String intestatarioRid) {
		this.intestatarioRid = intestatarioRid;
	}

	public String getCdFiscale() {
		return cdFiscale;
	}

	public void setCdFiscale(String cdFiscale) {
		this.cdFiscale = cdFiscale;
	}

	public int getProvinciaResidenza() {
		return provinciaResidenza;
	}

	public void setProvinciaResidenza(int provinciaResidenza) {
		this.provinciaResidenza = provinciaResidenza;
	}

	public int getComuneResidenza() {
		return comuneResidenza;
	}

	public void setComuneResidenza(int comuneResidenza) {
		this.comuneResidenza = comuneResidenza;
	}

	public String getCapPost() {
		return capPost;
	}

	public void setCapPost(String capPost) {
		this.capPost = capPost;
	}

	public int getIdStrada() {
		return idStrada;
	}

	public void setIdStrada(int idStrada) {
		this.idStrada = idStrada;
	}

	public Integer getNumeroCivico() {
		return numeroCivico;
	}

	public void setNumeroCivico(Integer numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	public Integer getAbi() {
		return abi;
	}

	public void setAbi(Integer abi) {
		this.abi = abi;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}

	public Integer getCab() {
		return cab;
	}

	public void setCab(Integer cab) {
		this.cab = cab;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getCinAbi() {
		return cinAbi;
	}

	public void setCinAbi(String cinAbi) {
		this.cinAbi = cinAbi;
	}

	public String getNumeroCC() {
		return numeroCC;
	}

	public void setNumeroCC(String numeroCC) {
		this.numeroCC = numeroCC;
	}

	/*
	 * public int getModalitaPag() { return modalitaPag; }
	 * 
	 * public void setModalitaPag(int modalitaPag) { this.modalitaPag =
	 * modalitaPag; }
	 * 
	 * 
	 * public Integer getNrAnag() { return nrAnag; }
	 * 
	 * public void setNrAnag(Integer nrAnag) { this.nrAnag = nrAnag; }
	 * 
	 * public String getDesAnag() { return desAnag; }
	 * 
	 * public void setDesAnag(String desAnag) { this.desAnag = desAnag; }
	 * 
	 * public String getQuota() { return quota; }
	 * 
	 * public void setQuota(String quota) { this.quota = quota; }
	 */
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	/*
	 * public String getLastNote() { return lastNote; }
	 * 
	 * public void setLastNote(String lastNote) { this.lastNote = lastNote; }
	 * 
	 * public String getOperUltVar() { return operUltVar; }
	 * 
	 * public void setOperUltVar(String operUltVar) { this.operUltVar =
	 * operUltVar; }
	 * 
	 * public String getDateUltVar() { return dateUltVar; }
	 * 
	 * public void setDateUltVar(String dateUltVar) { this.dateUltVar =
	 * dateUltVar; }
	 */
	/*
	 * public boolean isVisibleRid() { return visibleRid; }
	 * 
	 * public void setVisibleRid(boolean visibleRid) { this.visibleRid =
	 * visibleRid; }
	 * 
	 * public List<MembroRidDTO> getAnagrafiche() { return anagrafiche; }
	 * 
	 * public void setAnagrafiche(List<MembroRidDTO> anagrafiche) {
	 * this.anagrafiche = anagrafiche; }
	 */
	public boolean isRidFromInsertAnag() {
		return ridFromInsertAnag;
	}

	public void setRidFromInsertAnag(boolean ridFromInsertAnag) {
		this.ridFromInsertAnag = ridFromInsertAnag;
	}

	public boolean isStatoDisabled() {
		return statoDisabled;
	}

	public void setStatoDisabled(boolean statoDisabled) {
		this.statoDisabled = statoDisabled;
	}

	public boolean isVisibleAnagrafiche() {
		return visibleAnagrafiche;
	}

	public void setVisibleAnagrafiche(boolean visibleAnagrafiche) {
		this.visibleAnagrafiche = visibleAnagrafiche;
	}

	private boolean ridFromInsertAnag;
	private boolean statoDisabled;

	private boolean visibleAnagrafiche;
	/**
	 * @uml.property  name="test"
	 */
	private String test;
	/**
	 * @uml.property  name="idTest"
	 */
	private String idTest;







	/**
	 * Getter of the property <tt>idTest</tt>
	 * @return  Returns the idTest.
	 * @uml.property  name="idTest"
	 */
	public String getIdTest() {
		return idTest;
	}

	/**
	 * Setter of the property <tt>idTest</tt>
	 * @param idTest  The idTest to set.
	 * @uml.property  name="idTest"
	 */
	public void setIdTest(String idTest) {
		this.idTest = idTest;
	}
	







	

}
