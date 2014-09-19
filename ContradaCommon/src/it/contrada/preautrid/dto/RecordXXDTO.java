package it.contrada.preautrid.dto;

import java.io.Serializable;

public class RecordXXDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1607319780521300470L;
	private String idSeda;
	private String nomeCreditore;
	private String numeroProgressivo;	
	private String tipoRecord = "XX";
	
	

	public String getIdSeda() {
		return idSeda;
	}

	public void setIdSeda(String idSeda) {
		this.idSeda = idSeda;
	}

	public String getNomeCreditore() {
		return nomeCreditore;
	}

	public void setNomeCreditore(String nomeCreditore) {
		this.nomeCreditore = nomeCreditore;
	}

	public String getNumeroProgressivo() {
		return numeroProgressivo;
	}

	public void setNumeroProgressivo(String numeroProgressivo) {
		this.numeroProgressivo = numeroProgressivo;
	}

	
	@Override
	public String toString() {
		StringBuilder recXX = new StringBuilder();
		recXX.append(" ");
		recXX.append(tipoRecord);
		recXX.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
				"0"));
		recXX.append(String.format("%-35s",idSeda));
		recXX.append(String.format("%-70s",nomeCreditore));
		recXX.append(String.format("%5s", ""));
		

		return recXX.toString();
	}

}
