package it.contrada.backingbeans.modal.view;

import it.contrada.backingbeans.Famiglia;
import it.contrada.businessdelegate.RicercaFamigliaBD;
import it.contrada.dto.MembroFamigliaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

import javax.faces.event.ActionEvent;

public class CercaFamiglia {
	private List<MembroFamigliaDTO> famiglie;
	private boolean isVisibleListFamiglia;
	private boolean isVisibleUsa;
	private String nome;
	private String clientClickUsa;
	private String clientClickAnnulla;
	private Integer cdFamiglia;

	public Integer getCdFamiglia() {
		return cdFamiglia;
	}

	public void setCdFamiglia(Integer cdFamiglia) {
		this.cdFamiglia = cdFamiglia;
	}

	public String getClientClickAnnulla() {
		return clientClickAnnulla;
	}

	public String getClientClickUsa() {
		return clientClickUsa;
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

	private String cognome;

	public boolean isVisibleUsa() {
		return isVisibleUsa;
	}

	public void setVisibleUsa(boolean isVisibleUsa) {
		this.isVisibleUsa = isVisibleUsa;
	}

	public boolean isVisibleListFamiglia() {
		return isVisibleListFamiglia;
	}

	public void setVisibleListFamiglia(boolean isVisibleListFamiglia) {
		this.isVisibleListFamiglia = isVisibleListFamiglia;
	}

	public List<MembroFamigliaDTO> getFamiglie() {
		return famiglie;
	}

	public void setFamiglie(List<MembroFamigliaDTO> famiglie) {
		this.famiglie = famiglie;
	}

	public CercaFamiglia() {
		// TODO Auto-generated constructor stub
		this.clientClickAnnulla = "window.returnValue='';window.close();";
		setCdFamiglia(null);
	}

	public void cercaFamigliaOnClick(ActionEvent event)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		setFamiglie(ricercaFamiglia(getCdFamiglia(), getNome(), getCognome()));
		setVisibleListFamiglia(true);
	}

	private List<MembroFamigliaDTO> ricercaFamiglia(Integer cdFamiglia,
			String Nome, String Cognome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		Famiglia membroItem = null;

		List<MembroFamigliaDTO> membriDTO = null;

		if (cdFamiglia != null && cdFamiglia != 0) {
			membriDTO = RicercaFamigliaBD.ricercaPerCdFamiglia(cdFamiglia);
		} else if ((nome != null && !nome.equalsIgnoreCase(""))
				&& (cognome != null && !cognome.equalsIgnoreCase(""))) {
			membriDTO = RicercaFamigliaBD.ricercaPerNomeCognome(nome, cognome);
		} else {
			membriDTO = RicercaFamigliaBD.ricercaPerCognome(cognome);
		}

		return membriDTO;

	}

}
