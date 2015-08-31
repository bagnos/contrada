package it.contrada.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AnagraficaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idAnagrafica;
	private String nome;
	private String cognome;
	private String sesso;
	private Date dtNascita;
	private Date dtMorte;
	private String cdFiscale;
	private Integer idFamiglia;
	private Long idOperazione;
	private Date dtUltCens;
	private String note;
	private String nrCivico;
	private Integer idStrada;
	private boolean nuovaFamiglia;
	private String statoNascita = null;
	private String statoResidenza = null;
	private Integer provinciaNascita;
	private Integer provinciaResidenza;
	private String capPost;
	private Integer comuneNascita;
	private Integer comuneResidenza;
	private List<TesseraDTO> tessere;
	
	private RidDTO rid;
	private String dsStrada;
	private String dsLocalita;
	private Date dtFazzoletto;
	private Integer idStatoAnagrafica;
	private Boolean capoFamiglia;
	private String intestatario;
	private String indirizzo;
	private String capProvincia;
	private java.sql.Date dtInserimento;
	private String utente;
	private Long codProtettore;
	private Integer idGestore;
	private String dsGestore;
	private boolean modicaGestore;
	private Integer idLocalita;
	private String mail;
	private String telefono;
	private String tessera;
	private String carica;
	private String incasso;
	private String esattore;
	private int quota;
	private String txMail;
	private String txCell;
	private String txFisso;	
	private OperazioneDTO operazione;
	private Date dtCessazione;	
	private String tipoStatoAnagrafica;
	private int eta;
	private String recapito;
	private int idAnaPrinc;
	private String intestatarioPrinc;
	private List<AnagraficaDTO> anagrafiche;
	private String siglaProv;
	private Date dtRitiroTessera;
	
	
	
	public Date getDtRitiroTessera() {
		return dtRitiroTessera;
	}

	public void setDtRitiroTessera(Date dtRitiroTessera) {
		this.dtRitiroTessera = dtRitiroTessera;
	}

	public String getSiglaProv() {
		return siglaProv;
	}

	public void setSiglaProv(String siglaProv) {
		this.siglaProv = siglaProv;
	}

	public List<AnagraficaDTO> getAnagrafiche() {
		return anagrafiche;
	}

	public void setAnagrafiche(List<AnagraficaDTO> anagrafiche) {
		this.anagrafiche = anagrafiche;
	}

	public String getIntestatarioPrinc() {
		return intestatarioPrinc;
	}

	public void setIntestatarioPrinc(String intestatarioPrinc) {
		this.intestatarioPrinc = intestatarioPrinc;
	}

	public int getIdAnaPrinc() {
		return idAnaPrinc;
	}

	public void setIdAnaPrinc(int idAnaPrinc) {
		this.idAnaPrinc = idAnaPrinc;
	}

	public String getRecapito() {
		return recapito;
	}

	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String getTipoStatoAnagrafica() {
		return tipoStatoAnagrafica;
	}

	public void setTipoStatoAnagrafica(String tipoStatoAnagrafica) {
		this.tipoStatoAnagrafica = tipoStatoAnagrafica;
	}

	public Date getDtCessazione() {
		return dtCessazione;
	}

	public void setDtCessazione(Date dtCessazione) {
		this.dtCessazione = dtCessazione;
	}

	public OperazioneDTO getOperazione() {
		return operazione;
	}

	public void setOperazione(OperazioneDTO operazione) {
		this.operazione = operazione;
	}

	public String getTxMail() {
		return txMail;
	}

	public void setTxMail(String txMail) {
		this.txMail = txMail;
	}

	public String getTxCell() {
		return txCell;
	}

	public void setTxCell(String txCell) {
		this.txCell = txCell;
	}

	public String getTxFisso() {
		return txFisso;
	}

	public void setTxFisso(String txFisso) {
		this.txFisso = txFisso;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public String getEsattore() {
		return esattore;
	}

	public void setEsattore(String esattore) {
		this.esattore = esattore;
	}

	public String getIncasso() {
		return incasso;
	}

	public void setIncasso(String incasso) {
		this.incasso = incasso;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTessera() {
		return tessera;
	}

	public void setTessera(String tessera) {
		this.tessera = tessera;
	}

	public String getCarica() {
		return carica;
	}

	public void setCarica(String carica) {
		this.carica = carica;
	}

	public Integer getIdLocalita() {
		return idLocalita;
	}

	public void setIdLocalita(Integer idLocalita) {
		this.idLocalita = idLocalita;
	}

	public boolean isModicaGestore() {
		return modicaGestore;
	}

	public void setModicaGestore(boolean modicaGestore) {
		this.modicaGestore = modicaGestore;
	}

	public String getDsGestore() {
		return dsGestore;
	}

	public void setDsGestore(String dsGestore) {
		this.dsGestore = dsGestore;
	}

	public Integer getIdGestore() {
		return idGestore;
	}

	public void setIdGestore(Integer idGestore) {
		this.idGestore = idGestore;
	}

	public Long getCodProtettore() {
		return codProtettore;
	}

	public void setCodProtettore(Long codProtettore) {
		this.codProtettore = codProtettore;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public java.sql.Date getDtInserimento() {
		return dtInserimento;
	}

	public void setDtInserimento(java.sql.Date dtInserimento) {
		this.dtInserimento = dtInserimento;
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

	public boolean isCapoFamiglia() {
		return capoFamiglia;
	}

	public void setCapoFamiglia(boolean capoFamiglia) {
		this.capoFamiglia = capoFamiglia;
	}

	public int getIdStatoAnagrafica() {
		return idStatoAnagrafica;
	}

	public void setIdStatoAnagrafica(int idStatoAnagrafica) {
		this.idStatoAnagrafica = idStatoAnagrafica;
	}

	public Date getDtFazzoletto() {
		return dtFazzoletto;
	}

	public void setDtFazzoletto(Date dtFazzoletto) {
		this.dtFazzoletto = dtFazzoletto;
	}

	public String getDsLocalita() {
		return dsLocalita;
	}

	public void setDsLocalita(String dsLocalita) {
		this.dsLocalita = dsLocalita;
	}

	public String getDsStrada() {
		return dsStrada;
	}

	public void setDsStrada(String dsStrada) {
		this.dsStrada = dsStrada;
	}

	public void setNuovaFamiglia(boolean nuovaFamiglia) {
		this.nuovaFamiglia = nuovaFamiglia;
	}

	public RidDTO getRid() {
		return rid;
	}

	public void setRid(RidDTO rid) {
		this.rid = rid;
	}

	public List<TesseraDTO> getTessere() {
		if (tessere==null)
		{
			tessere=new ArrayList<TesseraDTO>();
		}
		return tessere;
	}

	public void setTessere(List<TesseraDTO> tessere) {
		this.tessere = tessere;
	}

	public String getStatoNascita() {
		return statoNascita;
	}

	public void setStatoNascita(String statoNascita) {
		this.statoNascita = statoNascita;
	}

	public String getStatoResidenza() {
		return statoResidenza;
	}

	public void setStatoResidenza(String statoResidenza) {
		this.statoResidenza = statoResidenza;
	}

	public Integer getProvinciaNascita() {
		return provinciaNascita;
	}

	public void setProvinciaNascita(Integer provinciaNascita) {
		this.provinciaNascita = provinciaNascita;
	}

	public Integer getProvinciaResidenza() {
		return provinciaResidenza;
	}

	public void setProvinciaResidenza(Integer provinciaResidenza) {
		this.provinciaResidenza = provinciaResidenza;
	}

	public String getCapPost() {
		return capPost;
	}

	public void setCapPost(String capPost) {
		this.capPost = capPost;
	}

	public Integer getComuneNascita() {
		return comuneNascita;
	}

	public void setComuneNascita(Integer comuneNascita) {
		this.comuneNascita = comuneNascita;
	}

	public Integer getComuneResidenza() {
		return comuneResidenza;
	}

	public void setComuneResidenza(Integer comuneResidenza) {
		this.comuneResidenza = comuneResidenza;
	}

	public boolean isNuovaFamiglia() {
		return nuovaFamiglia;
	}

	public void setIdAnagrafica(int idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}

	public int getIdAnagrafica() {
		return idAnagrafica;
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

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public Date getDtNascita() {
		return dtNascita;
	}

	public void setDtNascita(Date dtNascita) {
		this.dtNascita = dtNascita;
	}

	public Date getDtMorte() {
		return dtMorte;
	}

	public void setDtMorte(Date dtMorte) {
		this.dtMorte = dtMorte;
	}

	public String getCdFiscale() {
		return cdFiscale;
	}

	public void setCdFiscale(String cdFiscale) {
		this.cdFiscale = cdFiscale;
	}

	public Integer getIdFamiglia() {
		return idFamiglia;
	}

	public void setIdFamiglia(Integer idFamiglia) {
		this.idFamiglia = idFamiglia;
	}

	public Long getIdOperazione() {
		return idOperazione;
	}

	public void setIdOperazione(Long idOperazione) {
		this.idOperazione = idOperazione;
	}

	public Date getDtUltCens() {
		return dtUltCens;
	}

	public void setDtUltCens(Date dtUltCens) {
		this.dtUltCens = dtUltCens;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNrCivico() {
		return nrCivico;
	}

	public void setNrCivico(String nrCivico) {
		this.nrCivico = nrCivico;
	}

	public Integer getIdStrada() {
		return idStrada;
	}

	public void setIdStrada(Integer idStrada) {
		this.idStrada = idStrada;
	}

}
