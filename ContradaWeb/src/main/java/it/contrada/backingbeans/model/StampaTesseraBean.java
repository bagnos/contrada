package it.contrada.backingbeans.model;

import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.TesseraStampataDTO;
import it.contrada.web.util.Costante;

import java.util.GregorianCalendar;
import java.util.List;

public class StampaTesseraBean {
	private int tipoTessera;
	private int anno;
	private Integer esattore;
	private Integer idAnagrafica;
	private String dsAnagrafica;
	private boolean renderNote;
	private String note;
	private AnagraficaDTO anagSel;
	private Integer tipoIncasso;
	private boolean visibleEsattore;
	private Integer tipoStampa;
	private boolean visibleTessere;
	private List<TesseraStampataDTO> tessere;
	
	
	
	
	

	public void setRenderNote(boolean renderNote) {
		this.renderNote = renderNote;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<TesseraStampataDTO> getTessere() {
		return tessere;
	}

	public void setTessere(List<TesseraStampataDTO> tessere) {
		this.tessere = tessere;
	}

	public boolean isVisibleTessere() {
		return visibleTessere;
	}

	public void setVisibleTessere(boolean visibleTessere) {
		this.visibleTessere = visibleTessere;
	}

	public int getTipoStampa() {
		if (tipoStampa==null)
		{
			tipoStampa=1;
		}
		return tipoStampa;
	}

	public void setTipoStampa(int tipoStampa) {
		this.tipoStampa = tipoStampa;
	}

	public void setVisibleEsattore(boolean visibleEsattore) {
		this.visibleEsattore = visibleEsattore;
	}

	public boolean isVisibleEsattore() {
		
		return visibleEsattore;
	}

	public Integer getTipoIncasso() {
		if (tipoIncasso == null) {
			tipoIncasso = -1;
		}
		return tipoIncasso;
	}

	public void setTipoIncasso(Integer tipoIncasso) {
		this.tipoIncasso = tipoIncasso;
	}

	public AnagraficaDTO getAnagSel() {
		return anagSel;
	}

	public void setAnagSel(AnagraficaDTO anagSel) {
		this.anagSel = anagSel;
	}

	public boolean isRenderNote() {
		return renderNote;
	}

	public String getNote() {
		return note;
	}

	public String getDsAnagrafica() {
		return dsAnagrafica;
	}

	public void setDsAnagrafica(String dsAnagrafica) {
		this.dsAnagrafica = dsAnagrafica;
	}

	public Integer getIdAnagrafica() {
		return idAnagrafica;
	}

	public void setIdAnagrafica(Integer idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}

	public Integer getEsattore() {
		return esattore;
	}

	public void setEsattore(Integer esattore) {
		this.esattore = esattore;
	}

	public int getTipoTessera() {
		if (tipoTessera == 0) {
			tipoTessera = Costante.TIPO_TESSERA_PROTETTORATO;
		}
		return tipoTessera;
	}

	public void setTipoTessera(int tipoTessera) {
		this.tipoTessera = tipoTessera;
	}

	public int getAnno() {
		if (anno == 0) {
			GregorianCalendar gCalendar = new GregorianCalendar();
			anno = gCalendar.get(GregorianCalendar.YEAR);
		}
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

}
