package it.contrada.poste;

import java.io.Serializable;

public class IncassoPostaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2131210247333167621L;
	private int idAnagrafica;
	private int idFamiglia;
	private int idTessera;
	private int quota;
	private String VCAMPOT;
	private String RIGADESTINATARIO1;
	private String RIGADESTINATARIO2;
	private String RIGADESTINATARIO3;
	private String RIGADESTINATARIO4;
	private String NOME1;
	private String NOME;
	private String INDIRIZZO;
	private String CAP;
	private String DEST;
	private String PROV;
	private String VAR01D;
	private String VAR01S;
	private String VAR02S;	
	private String VAR01A;
	private String VAR02A;
	private String VAR03A;
	private String VAR04A;
	private String VAR05A;
	private String VAR06A;
	private String VAR07A;
	private String VAR08A;
	private String VAR09A;
	private String VAR10A;
	private String VAR11A;
	private String VAR12A;
	private String VAR13A;
	private String XRATAT;
	private String SCADET;
	private String IBAN;
	private Long idFlusso;
	
	
	
	public String getNOME() {
		return NOME;
	}
	public void setNOME(String nOME) {
		NOME = nOME;
	}
	public int getQuota() {
		return quota;
	}
	public void setQuota(int quota) {
		this.quota = quota;
	}
	public int getIdTessera() {
		return idTessera;
	}
	public void setIdTessera(int idTessera) {
		this.idTessera = idTessera;
	}
	public Long getIdFlusso() {
		return idFlusso;
	}
	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}
	public int getIdAnagrafica() {
		return idAnagrafica;
	}
	public void setIdAnagrafica(int idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}
	public int getIdFamiglia() {
		return idFamiglia;
	}
	public void setIdFamiglia(int idFamiglia) {
		this.idFamiglia = idFamiglia;
	}
	public String getRIGADESTINATARIO1() {
		return RIGADESTINATARIO1;
	}
	public void setRIGADESTINATARIO1(String rIGADESTINATARIO1) {
		RIGADESTINATARIO1 = rIGADESTINATARIO1;
	}
	public String getRIGADESTINATARIO2() {
		return RIGADESTINATARIO2;
	}
	public void setRIGADESTINATARIO2(String rIGADESTINATARIO2) {
		RIGADESTINATARIO2 = rIGADESTINATARIO2;
	}
	public String getRIGADESTINATARIO3() {
		return RIGADESTINATARIO3;
	}
	public void setRIGADESTINATARIO3(String rIGADESTINATARIO3) {
		RIGADESTINATARIO3 = rIGADESTINATARIO3;
	}
	public String getRIGADESTINATARIO4() {
		return RIGADESTINATARIO4;
	}
	public void setRIGADESTINATARIO4(String rIGADESTINATARIO4) {
		RIGADESTINATARIO4 = rIGADESTINATARIO4;
	}
	public String getNOME1() {
		return NOME1;
	}
	public void setNOME1(String nOME1) {
		NOME1 = nOME1;
	}
	public String getINDIRIZZO() {
		return INDIRIZZO;
	}
	public void setINDIRIZZO(String iNDIRIZZO) {
		INDIRIZZO = iNDIRIZZO;
	}
	public String getCAP() {
		return CAP;
	}
	public void setCAP(String cAP) {
		CAP = cAP;
	}
	public String getDEST() {
		return DEST;
	}
	public void setDEST(String dEST) {
		DEST = dEST;
	}
	public String getPROV() {
		return PROV;
	}
	public void setPROV(String pROV) {
		PROV = pROV;
	}
	public String getVAR01D() {
		return VAR01D;
	}
	public void setVAR01D(String vAR01D) {
		VAR01D = vAR01D;
	}
	public String getVAR01S() {
		return VAR01S;
	}
	public void setVAR01S(String vAR01S) {
		VAR01S = vAR01S;
	}
	public String getVAR02S() {
		return VAR02S;
	}
	public void setVAR02S(String vAR02S) {
		VAR02S = vAR02S;
	}
	public String getVCAMPOT() {
		return VCAMPOT;
	}
	public void setVCAMPOT(String vCAMPOT) {
		VCAMPOT = vCAMPOT;
	}
	public String getXRATAT() {
		return XRATAT;
	}
	public void setXRATAT(String xRATAT) {
		XRATAT = xRATAT;
	}
	public String getSCADET() {
		return SCADET;
	}
	public void setSCADET(String sCADET) {
		SCADET = sCADET;
	}
	public String getIBAN() {
		return IBAN;
	}
	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	public String getVAR01A() {
		return VAR01A;
	}
	public void setVAR01A(String vAR01A) {
		VAR01A = vAR01A;
	}
	public String getVAR02A() {
		return VAR02A;
	}
	public void setVAR02A(String vAR02A) {
		VAR02A = vAR02A;
	}
	public String getVAR03A() {
		return VAR03A;
	}
	public void setVAR03A(String vAR03A) {
		VAR03A = vAR03A;
	}
	public String getVAR04A() {
		return VAR04A;
	}
	public void setVAR04A(String vAR04A) {
		VAR04A = vAR04A;
	}
	public String getVAR05A() {
		return VAR05A;
	}
	public void setVAR05A(String vAR05A) {
		VAR05A = vAR05A;
	}
	public String getVAR06A() {
		return VAR06A;
	}
	public void setVAR06A(String vAR06A) {
		VAR06A = vAR06A;
	}
	public String getVAR07A() {
		return VAR07A;
	}
	public void setVAR07A(String vAR07A) {
		VAR07A = vAR07A;
	}
	public String getVAR08A() {
		return VAR08A;
	}
	public void setVAR08A(String vAR08A) {
		VAR08A = vAR08A;
	}
	public String getVAR09A() {
		return VAR09A;
	}
	public void setVAR09A(String vAR09A) {
		VAR09A = vAR09A;
	}
	public String getVAR10A() {
		return VAR10A;
	}
	public void setVAR10A(String vAR10A) {
		VAR10A = vAR10A;
	}
	public String getVAR11A() {
		return VAR11A;
	}
	public void setVAR11A(String vAR11A) {
		VAR11A = vAR11A;
	}
	public String getVAR12A() {
		return VAR12A;
	}
	public void setVAR12A(String vAR12A) {
		VAR12A = vAR12A;
	}
	public String getVAR13A() {
		return VAR13A;
	}
	public void setVAR13A(String vAR13A) {
		VAR13A = vAR13A;
	}
	

}
