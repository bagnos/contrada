package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.RicercaRidBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dominio.dto.TipoStatoRidDTO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.MembroRidDTO;
import it.contrada.dto.RidDTO;
import it.contrada.enumcontrada.TipoStatoRid;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.mail.RidMail;
import it.contrada.web.enumcontrada.TipoGravitaMessage;
import it.contrada.web.util.ConverterContrada;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class ListaRidView extends BaseView {
	private int cdStato;
	private List<SelectItem> stati;
	private List<RidDTO> rid;
	private boolean visibleRid;
	private int nrAnagrafiche;
	private Integer impTotale;
	private List<String> idStati;
	private List<SelectItem> tipoTessere;
	private Integer tipoTessera;
	private boolean renderInvia;
	private String dsTessera;
	private boolean selectAll;

	public boolean isSelectAll() {
		return selectAll;
	}

	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}

	public boolean isRenderInvia() {
		return renderInvia;
	}

	public void setRenderInvia(boolean renderInvia) {
		this.renderInvia = renderInvia;
	}

	public Integer getTipoTessera() {
		return tipoTessera;
	}

	public void setTipoTessera(Integer tipoTessera) {
		this.tipoTessera = tipoTessera;
	}

	public List<SelectItem> getTipoTessere() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		if (tipoTessere == null) {

			tipoTessere = new ArrayList<SelectItem>();

			List<TipoTesseraDTO> tipoTessereDTO = null;
			tipoTessereDTO = RicercaTipoTesseraBD.elencaTipoTessera();

			tipoTessere.add(new SelectItem("-1", "-- Selezionare --"));
			for (TipoTesseraDTO tt : tipoTessereDTO) {
				tipoTessere.add(new SelectItem(tt.getIdTipoTessera(), tt
						.getDsTipoTessera()));

			}
			// protettorato
			tipoTessera = 1;
		}
		return tipoTessere;
	}

	public List<String> getIdStati() {
		return idStati;
	}

	public void setIdStati(List<String> idStati) {
		this.idStati = idStati;
	}

	public String getImpTotale() {
		if (impTotale != null) {
			return ConverterContrada.convertToImporto(impTotale);
		}
		return ConverterContrada.convertToImporto(Integer.valueOf("0"));

	}

	public int getNrAnagrafiche() {
		nrAnagrafiche = 0;
		if (rid != null) {
			nrAnagrafiche = rid.size();
		}
		return nrAnagrafiche;
	}

	public boolean isVisibleRid() {
		return visibleRid;
	}

	public int getCdStato() {
		return cdStato;
	}

	public List<RidDTO> getRid() {
		return rid;
	}

	public void setCdStato(int cdStato) {
		this.cdStato = cdStato;
	}

	public List<SelectItem> getStati() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		if (stati == null) {
			List<TipoStatoRidDTO> statiRid = RicercaRidBD.elencaStati();
			stati = new ArrayList<SelectItem>();
			for (TipoStatoRidDTO rid : statiRid) {
				stati.add(new SelectItem(rid.getIdStatoRid(), rid
						.getDsStatoRid()));
			}
		}
		return stati;
	}

	public void setStati(List<SelectItem> stati) {
		this.stati = stati;
	}

	public void inviaMailClick(ActionEvent e1)
			throws ContradaExceptionBloccante, IOException {
		try {
			List<RidDTO> ridMail = new ArrayList<RidDTO>();
			for (RidDTO ridDTO : rid) {
				if (ridDTO.isSelezionato()) {
					ridMail.add(ridDTO);
				}
			}

			if (!ridMail.isEmpty()) {
				RidMail.InviaMailSospesiRid(rid, dsTessera);
				writeInfoMessage(TipoGravitaMessage.SUCCESS,
						"Mail inviata con successo");
			} else {
				writeInfoMessage(TipoGravitaMessage.ERROR,
						"Nessun Rid Selezionato");
			}

		} catch (Exception e) {
			writeErrorMessage("Errore nell'invio della mail", e);
		}
	}

	public void cercaClick(ActionEvent e) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		if (tipoTessera == -1) {
			tipoTessera = null;
			dsTessera = "Protettorato";
		} else {
			for (SelectItem item : tipoTessere) {
				if (Integer.valueOf(item.getValue().toString()).intValue() == tipoTessera
						.intValue()) {
					dsTessera = item.getLabel();
				}
			}
		}
		List<Integer> idIntStati = new ArrayList<Integer>();
		for (String idStato : idStati) {
			idIntStati.add(Integer.parseInt(idStato));
		}
		rid = RicercaRidBD.ricercaPerStato(idIntStati, tipoTessera);

		visibleRid = !rid.isEmpty();
		impTotale = 0;
		for (RidDTO ridDTO : rid) {
			if (ridDTO.getMembri() != null) {
				for (MembroRidDTO m : ridDTO.getMembri()) {
					impTotale += m.getQuota();
				}
			}
		}

		for (Integer idintStato : idIntStati) {
			renderInvia = (idintStato.intValue() == TipoStatoRid.Sospesa
					.getStatoRid()) ? true : false;

		}
	}

	public void selectAllClick(ValueChangeEvent e) {
		if (e != null) {
			boolean sel = (Boolean) e.getNewValue();
			for (RidDTO disp : rid) {
				disp.setSelezionato(sel);
			}
		}
	}

}
