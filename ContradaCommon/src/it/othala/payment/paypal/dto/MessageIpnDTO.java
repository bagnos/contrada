package it.othala.payment.paypal.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageIpnDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrder;

	private String idTransaction;
	private Timestamp tsInserimento;
	private String txMessage;
	private String txStato;
	private String txNote;
	private boolean fgElaborato;

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}

	public Timestamp getTsInserimento() {
		return tsInserimento;
	}

	public void setTsInserimento(Timestamp tsInserimento) {
		this.tsInserimento = tsInserimento;
	}

	public String getTxMessage() {
		return txMessage;
	}

	public void setTxMessage(String txMessage) {
		this.txMessage = txMessage;
	}

	public String getTxStato() {
		return txStato;
	}

	public void setTxStato(String txStato) {
		this.txStato = txStato;
	}

	public String getTxNote() {
		return txNote;
	}

	public void setTxNote(String txNote) {
		this.txNote = txNote;
	}

	public boolean isFgElaborato() {
		return fgElaborato;
	}

	public void setFgElaborato(boolean fgElaborato) {
		this.fgElaborato = fgElaborato;
	}
}
