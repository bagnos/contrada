package it.othala.payment.paypal.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TesseraPaymentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private BigDecimal imTessera;	
	private String txTessera;
	private int idTessera;
	private int nrAnno;
	private int idOrder;
	private int idAnagrafica;
	private int idTipoTessera;
	
	public String getTxTessera() {
		return txTessera;
	}

	public void setTxTessera(String txTessera) {
		this.txTessera = txTessera;
	}

	
	
	public int getIdTipoTessera() {
		return idTipoTessera;
	}

	public void setIdTipoTessera(int idTipoTessera) {
		this.idTipoTessera = idTipoTessera;
	}

	public int getIdAnagrafica() {
		return idAnagrafica;
	}

	public void setIdAnagrafica(int idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

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

	public BigDecimal getImTessera() {
		return imTessera;
	}

	public void setImTessera(BigDecimal imTessera) {
		this.imTessera = imTessera;
	}

	
	

}
