package it.contrada.dto;

import java.io.Serializable;

public class TesseraDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1438233599780067670L;
	private int idAnag;
	private int idTessera;	
	private int idTipoRateizzazione;
	private String dsTipoRateizzazione;
	private String dsTipoTessera; 
	private int quota;
	private int idTipoIncasso;
	private String dsIncasso;
	private Integer idTipoEsattore;
	private Integer idRid;
	private String dsRid;
	private Integer idTipoCarica;
	private String dsTipoCarica;
	private String dsNote;
	private int idTipoTessera;
	private Integer idMav;
	private boolean fgAttiva;
	private String nome;
	private Integer quotaIncassata;
	private Integer incasso;
	private boolean modificata;
	private int anno;
	private String nrBollettino;
	private int idFamiglia;	
	private String cognome;
	private String indirizzo;
	private String capProvincia;
	private String intestatario;
	private Integer idTipoCaricaSucc;
	private String dsTipoCaricaSucc;
	private java.util.Date dtNascita;
	
	
	
	public java.util.Date getDtNascita() {
		return dtNascita;
	}
	public void setDtNascita(java.util.Date dtNascita) {
		this.dtNascita = dtNascita;
	}
	public Integer getIdTipoCaricaSucc() {
		return idTipoCaricaSucc;
	}
	public void setIdTipoCaricaSucc(Integer idTipoCaricaSucc) {
		this.idTipoCaricaSucc = idTipoCaricaSucc;
	}
	public String getDsTipoCaricaSucc() {
		return dsTipoCaricaSucc;
	}
	public void setDsTipoCaricaSucc(String dsTipoCaricaSucc) {
		this.dsTipoCaricaSucc = dsTipoCaricaSucc;
	}
	public String getIntestatario() {
		return intestatario;
	}
	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCapProvincia() {
		return capProvincia;
	}
	public void setCapProvincia(String capProvincia) {
		this.capProvincia = capProvincia;
	}
	public int getIdFamiglia() {
		return idFamiglia;
	}
	public void setIdFamiglia(int idFamiglia) {
		this.idFamiglia = idFamiglia;
	}
	public String getNrBollettino() {
		return nrBollettino;
	}
	public void setNrBollettino(String nrBollettino) {
		this.nrBollettino = nrBollettino;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public boolean isModificata() {
		return modificata;
	}
	public boolean getModificata() {
		return modificata;
	}
	public void setModificata(boolean modificata) {
		this.modificata = modificata;
	}
	public Integer getIncasso() {
		return incasso;
	}
	public void setIncasso(Integer incasso) {
		this.incasso = incasso;
	}
	public Integer getQuotaIncassata() {
		return quotaIncassata;
	}
	public void setQuotaIncassata(Integer quotaIncassata) {
		this.quotaIncassata = quotaIncassata;
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
	
	
	public boolean getFgAttiva() {
		return fgAttiva;
	}
	public void setFgAttiva(boolean fgAttiva) {
		this.fgAttiva = fgAttiva;
	}
	public Integer getIdMav() {
		return idMav;
	}
	public void setIdMav(Integer idMav) {
		this.idMav = idMav;
	}
	public int getIdTipoTessera() {
		return idTipoTessera;
	}
	public void setIdTipoTessera(int idTipoTessera) {
		this.idTipoTessera = idTipoTessera;
	}
	public String getDsNote() {
		return dsNote;
	}
	public void setDsNote(String dsNote) {
		this.dsNote = dsNote;
	}
	public Integer getIdTipoCarica() {
		return idTipoCarica;
	}
	public void setIdTipoCarica(Integer idTipoCarica) {
		this.idTipoCarica = idTipoCarica;
	}
	public String getDsTipoCarica() {
		return dsTipoCarica;
	}
	public void setDsTipoCarica(String dsTipoCarica) {
		this.dsTipoCarica = dsTipoCarica;
	}
	public String getDsRid() {
		return dsRid;
	}
	public void setDsRid(String dsRid) {
		this.dsRid = dsRid;
	}
	public Integer getIdRid() {
		return idRid;
	}
	public void setIdRid(Integer idRid) {
		this.idRid = idRid;
	}
	public Integer getIdTipoEsattore() {
		return idTipoEsattore;
	}
	public void setIdTipoEsattore(Integer idTipoEsattore) {
		this.idTipoEsattore = idTipoEsattore;
	}
	public String getDsEsattore() {
		return dsEsattore;
	}
	public void setDsEsattore(String dsEsattore) {
		this.dsEsattore = dsEsattore;
	}
	private String dsEsattore;
	
	
	public String getDsIncasso() {
		return dsIncasso;
	}
	public void setDsIncasso(String dsIncasso) {
		this.dsIncasso = dsIncasso;
	}
	public int getIdTipoIncasso() {
		return idTipoIncasso;
	}
	public void setIdTipoIncasso(int idTipoIncasso) {
		this.idTipoIncasso = idTipoIncasso;
	}
	public int getIdAnag() {
		return idAnag;
	}
	public void setIdAnag(int idAnag) {
		this.idAnag = idAnag;
	}
	public int getQuota() {
		return quota;
	}
	public void setQuota(int quota) {
		this.quota = quota;
	}
	public String getDsTipoTessera() {
		return dsTipoTessera;
	}
	public void setDsTipoTessera(String dsTipoTessera) {
		this.dsTipoTessera = dsTipoTessera;
	}
	
	public int getIdTessera() {
		return idTessera;
	}
	public void setIdTessera(int idTessera) {
		this.idTessera = idTessera;
	}
	public int getIdTipoRateizzazione() {
		return idTipoRateizzazione;
	}
	public void setIdTipoRateizzazione(int idTipoRateizzazione) {
		this.idTipoRateizzazione = idTipoRateizzazione;
	}
	public String getDsTipoRateizzazione() {
		return dsTipoRateizzazione;
	}
	public void setDsTipoRateizzazione(String dsTipoRateizzazione) {
		this.dsTipoRateizzazione = dsTipoRateizzazione;
	}
}
