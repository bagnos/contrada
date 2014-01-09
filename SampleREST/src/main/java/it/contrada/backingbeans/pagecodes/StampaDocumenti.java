package it.contrada.backingbeans.pagecodes;

import it.contrada.backingbeans.PrintFile;
import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.enumcontrada.TipoStatoAnagrafica;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.enumcontrada.TipoDocumento;
import it.contrada.web.report.PdfReport;
import it.contrada.web.util.Costante;
import it.contrada.web.util.LoadBundleLanguage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.TransformerException;

import com.icesoft.faces.context.effects.JavascriptContext;
import com.itextpdf.text.DocumentException;

public class StampaDocumenti {

	private List<SelectItem> tipoDocumenti;
	private String tipoDocumento;

	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<SelectItem> getTipoDocumenti() {
		return tipoDocumenti;
	}

	public StampaDocumenti() {
		// inizializzazione tipo lettere
		tipoDocumenti = new ArrayList<SelectItem>();
		for (TipoDocumento tl : TipoDocumento.values()) {
			tipoDocumenti.add(new SelectItem(tl.getTipoDocumento(), LoadBundleLanguage.getMessage(tl.getTipoDocumento())));
		}
	}

	public void generaDocumento(ActionEvent e) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante, IOException, TransformerException,
			DocumentException, URISyntaxException {
		if (tipoDocumento != null) {
			PdfReport report = new PdfReport();
			List<Integer> stati=new ArrayList<Integer>();
			stati.add(TipoStatoAnagrafica.Attiva.getStatoAnagrafica());
			List<AnagraficaDTO> anags = RicercaAnagraficaBD.ricercaAnagraficheConPrincipale(stati);
					
			PrintFile file = report.generaPdfLetteraSottoscrizioni(anags);

			((HttpServletRequest) FacesContext.getCurrentInstance()
					.getExternalContext().getRequest()).getSession()
					.setAttribute(file.getNomeFile(), anags);

			JavascriptContext.addJavascriptCall(FacesContext
					.getCurrentInstance(),
					"window.open('../StampaLettera?nomeFile=" + file.getNomeFile()
							+ "&"+Costante.TIPO_DOCUMENTO+"=" + getTipoDocumento() + "')");
		}
	}

}
