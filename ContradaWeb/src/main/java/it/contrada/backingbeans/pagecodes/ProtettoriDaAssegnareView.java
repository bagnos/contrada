package it.contrada.backingbeans.pagecodes;

import it.contrada.backingbeans.model.StradarioBean;
import it.contrada.businessdelegate.GestioneAnagraficaBD;
import it.contrada.businessdelegate.GestioneGestoreBD;
import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.businessdelegate.RicercaIncassoBD;
import it.contrada.businessdelegate.RicercaProvinciaBD;
import it.contrada.businessdelegate.RicercaStradaBD;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.CapDTO;
import it.contrada.dto.GestoreDTO;
import it.contrada.dto.LocalitaDTO;
import it.contrada.dto.ParmResidenzaDTO;
import it.contrada.dto.ProvinciaDTO;
import it.contrada.dto.StradaDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.enumcontrada.TipoGravitaMessage;
import it.contrada.web.util.FacesUtils;
import it.contrada.web.util.RicercaAnagraficaUtil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.selectinputtext.SelectInputText;

public class ProtettoriDaAssegnareView extends BaseView {


	private List<AnagraficaDTO> anags;

	private Integer nrAnags;
    private boolean visibleAnagrafiche;
	
	
	
	


	public boolean isVisibleAnagrafiche() {
		return visibleAnagrafiche;
	}


	public Integer getNrAnags() {
		return nrAnags;
	}


	public List<AnagraficaDTO> getAnags() {
		return anags;
	}

	

	public ProtettoriDaAssegnareView()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		visibleAnagrafiche=true;
		anags = RicercaAnagraficaBD
				.ricercaAnagrafichePerGestore(0);
		nrAnags=anags.size();
		for (AnagraficaDTO a : anags) {
			for (TesseraDTO t : a.getTessere()) {
				// tessere solo protettorato
				if (t.getIdTipoTessera() == 1) {
					a.setQuota(t.getQuota());
					a.setTessera(String.format("%s %s", t.getDsTipoCarica(),
							t.getDsIncasso()));
					break;
				}
			}
		}
	}





}
