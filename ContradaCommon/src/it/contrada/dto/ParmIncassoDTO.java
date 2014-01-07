package it.contrada.dto;

import java.io.Serializable;
import java.util.List;

public class ParmIncassoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5382389537436776174L;
	private Integer tipoIncasso;
	private Integer tipoTessera;
	private int annoDa;
	private int annoA;
	private Integer tipoPagamento;
	List<Integer> tipoTessere;
	private Integer idEsattore;
	
	
	
	public Integer getIdEsattore() {
		return idEsattore;
	}
	public void setIdEsattore(Integer idEsattore) {
		this.idEsattore = idEsattore;
	}
	public void setTipoTessere(List<Integer> tipoTessere) {
		this.tipoTessere = tipoTessere;
	}
	public List<Integer> getTipoTessere() {
		return tipoTessere;
	}
	public Integer getTipoIncasso() {
		return tipoIncasso;
	}
	public void setTipoIncasso(Integer tipoIncasso) {
		this.tipoIncasso = tipoIncasso;
	}
	public Integer getTipoTessera() {
		return tipoTessera;
	}
	public void setTipoTessera(Integer tipoTessera) {
		this.tipoTessera = tipoTessera;
	}
	public int getAnnoDa() {
		return annoDa;
	}
	public void setAnnoDa(int annoDa) {
		this.annoDa = annoDa;
	}
	public int getAnnoA() {
		return annoA;
	}
	public void setAnnoA(int annoA) {
		this.annoA = annoA;
	}
	public Integer getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(Integer tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	

}
