package it.contrada.dto;

import java.io.Serializable;

public class RateizzazioneDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8662240360890624223L;
	private int idTessera;
	int nrAnno;
	private int idRata;
	private int imRata;
	private java.sql.Timestamp dtRiscossione;
	private int tipoStatoRata;
	private Long idFlusso;
	private Integer nrAnnoDistinta;
	private String dsIncasso;
	private int idAnagrafica;
	private String anagrafica;
	private int imRataIncassata;
	private String dsDistinta;
	private String dsTessera;
	private String dsStatoRata;
	private String idRateizzazione;
	private int idIncasso;
	private Integer nrMeseRata;
	private String dsRateizzazione;
	private String statoPagamento;
	private String idFlussoPoste;
	private Integer idRid;
	private Integer idEsattore;
	private String note;
	private Integer idFamiglia;
	private String nrBollettinoManuale;
	private String dsEsattore;

	public String getDsEsattore() {
		return dsEsattore;
	}

	public void setDsEsattore(String dsEsattore) {
		this.dsEsattore = dsEsattore;
	}

	public String getNrBollettinoManuale() {
		return nrBollettinoManuale;
	}

	public void setNrBollettinoManuale(String nrBollettinoManuale) {
		this.nrBollettinoManuale = nrBollettinoManuale;
	}

	public Integer getIdFamiglia() {
		return idFamiglia;
	}

	public void setIdFamiglia(Integer idFamiglia) {
		this.idFamiglia = idFamiglia;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getIdRid() {
		return idRid;
	}

	public void setIdRid(Integer idRid) {
		this.idRid = idRid;
	}

	public Integer getIdEsattore() {
		return idEsattore;
	}

	public void setIdEsattore(Integer idEsattore) {
		this.idEsattore = idEsattore;
	}

	public String getIdFlussoPoste() {
		return idFlussoPoste;
	}

	public void setIdFlussoPoste(String idFlussoPoste) {
		this.idFlussoPoste = idFlussoPoste;
	}

	public String getStatoPagamento() {
		return statoPagamento;
	}

	public void setStatoPagamento(String statoPagamento) {
		this.statoPagamento = statoPagamento;
	}

	public String getDsRateizzazione() {
		return dsRateizzazione;
	}

	public void setDsRateizzazione(String dsRateizzazione) {
		this.dsRateizzazione = dsRateizzazione;
	}

	public Integer getNrMeseRata() {
		return nrMeseRata;
	}

	public void setNrMeseRata(Integer nrMeseRata) {
		this.nrMeseRata = nrMeseRata;
	}

	public int getIdIncasso() {
		return idIncasso;
	}

	public void setIdIncasso(int idIncasso) {
		this.idIncasso = idIncasso;
	}

	public String getIdRateizzazione() {
		return idRateizzazione;
	}

	public void setIdRateizzazione(String idRateizzazione) {
		this.idRateizzazione = idRateizzazione;
	}

	public String getDsStatoRata() {
		return dsStatoRata;
	}

	public void setDsStatoRata(String dsStatoRata) {
		this.dsStatoRata = dsStatoRata;
	}

	public String getDsTessera() {
		return dsTessera;
	}

	public void setDsTessera(String dsTessera) {
		this.dsTessera = dsTessera;
	}

	public String getDsDistinta() {
		return dsDistinta;
	}

	public void setDsDistinta(String dsDistinta) {
		this.dsDistinta = dsDistinta;
	}

	public int getImRataIncassata() {
		return imRataIncassata;
	}

	public void setImRataIncassata(int imRataIncassata) {
		this.imRataIncassata = imRataIncassata;
	}

	public String getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(String anagrafica) {
		this.anagrafica = anagrafica;
	}

	public int getIdAnagrafica() {
		return idAnagrafica;
	}

	public void setIdAnagrafica(int idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}

	public String getDsIncasso() {
		return dsIncasso;
	}

	public void setDsIncasso(String dsIncasso) {
		this.dsIncasso = dsIncasso;
	}

	public Integer getNrAnnoDistinta() {
		return nrAnnoDistinta;
	}

	public void setNrAnnoDistinta(Integer nrAnnoDistinta) {
		this.nrAnnoDistinta = nrAnnoDistinta;
	}

	public Integer getNrDistinta() {
		return nrDistinta;
	}

	public void setNrDistinta(Integer nrDistinta) {
		this.nrDistinta = nrDistinta;
	}

	private Integer nrDistinta;

	public int getIdTessera() {
		return idTessera;
	}

	public void setIdTessera(int idTessera) {
		this.idTessera = idTessera;
	}

	public int getNrAnno() {
		return nrAnno;
	}

	public void setNrAnno(int nrAnno) {
		this.nrAnno = nrAnno;
	}

	public int getIdRata() {
		return idRata;
	}

	public void setIdRata(int idRata) {
		this.idRata = idRata;
	}

	public int getImRata() {
		return imRata;
	}

	public void setImRata(int imRata) {
		this.imRata = imRata;
	}

	public java.sql.Timestamp getDtRiscossione() {
		return dtRiscossione;
	}

	public void setDtRiscossione(java.sql.Timestamp dtRiscossione) {
		this.dtRiscossione = dtRiscossione;
	}

	public int getTipoStatoRata() {
		return tipoStatoRata;
	}

	public void setTipoStatoRata(int tipoStatoRata) {
		this.tipoStatoRata = tipoStatoRata;
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

}
