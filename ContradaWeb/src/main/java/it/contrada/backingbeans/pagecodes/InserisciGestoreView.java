package it.contrada.backingbeans.pagecodes;

import it.contrada.bean.GestioneGestore;
import it.contrada.businessdelegate.GestioneGestoreBD;
import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.GestoreDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.RicercaAnagraficaUtil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.selectinputtext.SelectInputText;

public class InserisciGestoreView extends BaseView {

	private Integer idAnagrafica;
	private List<SelectItem> anagraficheItems;
	private String dsAnagrafica;
		private AnagraficaDTO anagrafeSel;
	private List<AnagraficaDTO> anagrafiche;
	private boolean visibleAnagrafiche;
	private List<GestoreDTO> anagraficheTable = new ArrayList<GestoreDTO>();
	private String note;
	
	public void setDsAnagrafica(String dsAnagrafica) {
		this.dsAnagrafica = dsAnagrafica;
	}



	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<GestoreDTO> getAnagraficheTable() {
		return anagraficheTable;
	}

	public boolean isVisibleAnagrafiche() {
		visibleAnagrafiche = anagraficheTable != null
				&& !anagraficheTable.isEmpty();
		return visibleAnagrafiche;
	}

	public String getDsAnagrafica() {
		return dsAnagrafica;
	}

	public List<SelectItem> getAnagraficheItems() {
		return anagraficheItems;
	}

	public Integer getIdAnagrafica() {
		return idAnagrafica;
	}

	public void setIdAnagrafica(Integer idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}

	public void addAnagraficaOnClick(ActionEvent ev)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		AnagraficaDTO anagManuale = null;
		GestoreDTO gestoreManuale = null;
		if (anagrafeSel == null && idAnagrafica != null) {
			// autocompletamento
			anagManuale = RicercaAnagraficaBD.ricercaAnagrafica(idAnagrafica);

		}
		else if (anagrafeSel!=null)
		{
			anagManuale=anagrafeSel;
		}
		else
		{
			anagManuale=null;
		}

		// verifica se già presente
		if (anagManuale != null) {
			for (GestoreDTO a : anagraficheTable) {
				if (a.getIdGestore() == anagManuale.getIdAnagrafica()) {
					return;
				}
			}
			
			gestoreManuale = new GestoreDTO();
			gestoreManuale.setNote(note);
			gestoreManuale.setCognome(anagManuale.getCognome());
			gestoreManuale.setNome(anagManuale.getNome());
			gestoreManuale.setIdGestore(anagManuale.getIdAnagrafica());
			gestoreManuale.setMail(anagManuale.getMail());
			gestoreManuale.setCell(anagManuale.getTxCell());


			// aggiungo anagrafica alle tessere da stampare
			GestioneGestoreBD.insertGestore(gestoreManuale);
			anagraficheTable.add(0, gestoreManuale);
			anagrafeSel = null;
			dsAnagrafica = null;
			idAnagrafica = null;

		}

	}
	
	public void eliminaAnagraficaOnClick(ActionEvent e) throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		GestoreDTO anag = (GestoreDTO) e.getComponent().getAttributes()
				.get("anagrafica");
		GestioneGestoreBD.deleteGestore(anag.getIdGestore());
		anagraficheTable.remove(anag);
		
	}


	public InserisciGestoreView() throws ContradaExceptionNonBloccante,
			ContradaExceptionBloccante {
		anagraficheTable = GestioneGestoreBD.elencaGestori();
	}

	public void updateListAnagrafiche(ValueChangeEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			e.queue();
		} else {

			if (e.getNewValue() != null
					&& !e.getNewValue().toString().isEmpty()) {
				SelectInputText autoComplete = (SelectInputText) e
						.getComponent();
				if (autoComplete.getSelectedItem() != null) {
					AnagraficaDTO anagrafica = (AnagraficaDTO) autoComplete
							.getSelectedItem().getValue();

					anagrafeSel = anagrafica;
					idAnagrafica = anagrafeSel.getIdAnagrafica();

				} else {
					anagrafiche = RicercaAnagraficaUtil
							.getAnagraficaByAutocomplete(e.getNewValue()
									.toString());
					anagraficheItems = new ArrayList<SelectItem>();
					if (anagrafiche.isEmpty()) {
						anagrafeSel = null;
						idAnagrafica = null;
					}
					for (AnagraficaDTO anagrafica : anagrafiche) {
						anagraficheItems.add(new SelectItem(anagrafica,
								anagrafica.getIntestatario()));

					}

				}
			} else {
				anagraficheItems = null;
				anagrafeSel = null;
			}
		}
	}

}
