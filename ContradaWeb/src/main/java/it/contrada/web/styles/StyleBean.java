/**
 * 
 */
package it.contrada.web.styles;

import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.web.util.Configuration;

import java.util.TimeZone;

/**
 * @author S506273
 * 
 */
public class StyleBean {
	private String activeCss;
	private int paginatorMaxPages = 6;
	private String first = "//images//portlet//arrow-first.gif";
	private String last = "/images/portlet/arrow-last.gif";
	private String previous = "/images/portlet/arrow-previous.gif";
	private String next = "/images/portlet/arrow-next.gif";
	private int MaxRowsTables = 6;
	private String scrollableHeigth = "100px";
	private String first_dis = "//images//portlet//arrow-first.dis.gif";
	private String modifica = "/images/portlet/edit.png";
	private String dettagli = "/images/portlet/details.png";
	private String elimina = "/images/portlet/cestino.gif";
	private String last_dis = "/images/portlet/arrow-last-dis.gif";
	private String previous_dis = "/images/portlet/arrow-previous-dis.gif";
	private String next_dis = "/images/portlet/arrow-next-dis.gif";
	private int fastStep = 4;
	private String fastforward = "/images/portlet/arrow-ff.gif";
	private String imgExcel = "/images/portlet/page_excel.png";
	private String labelExcel = "Esporta in Excel";
	private TimeZone generalTimeZone = TimeZone.getDefault();
	private String show = "/images/portlet/tree_nav_bottom_open.gif";
	private String hide = "/images/portlet/tree_nav_bottom_close.gif";
	private String imgPrint = "/images/portlet/print.png";
	private String imgOpenTree = "/images/portlet/tree_nav_middle_open.gif";
	private String imgCloseTree = "/images/portlet/tree_nav_middle_close.gif";
	private String imgLogoHome = "/images/portlet/scritta-nobile.gif";
	private String imgOminoVerde = "/images/portlet/omino_verde.gif";
	private String imgUser = "/images/portlet/user_male_portrait.png";
	private String exit = "/images/portlet/exit.png";
	private String imgLabel = "/images/portlet/label.png";
	private String imgPdf="/images/portlet/pdf_document.png";
	private String imgLetter="/images/portlet/allegato.gif";
	private String imgTessera="/images/portlet/contact_new.png";
	private String imgClose="/images/portlet/close_white_cross.png";
	private String imgConferma="/images/portlet/success.png";
	private String imgAnnulla="/images/portlet/undo.png";
	
	

	public String getImgConferma() {
		return imgConferma;
	}

	public String getImgAnnulla() {
		return imgAnnulla;
	}

	public String getImgClose() {
		return imgClose;
	}

	public String getImgTessera() {
		return imgTessera;
	}

	public String getImgLetter() {
		return imgLetter;
	}

	public String getImgPdf() {
		return imgPdf;
	}

	public void setImgPdf(String imgPdf) {
		this.imgPdf = imgPdf;
	}

	public String getImgLabel() {
		return imgLabel;
	}

	public String getExit() {
		return exit;
	}

	public String getImgUser() {
		return imgUser;
	}

	public String getImgOminoVerde() {
		return imgOminoVerde;
	}

	public String getImgLogoHome() {
		return imgLogoHome;
	}

	public String getImgOpenTree() {
		return imgOpenTree;
	}

	public String getImgCloseTree() {
		return imgCloseTree;
	}

	public String getImgPrint() {
		return imgPrint;
	}

	public String getHide() {
		return hide;
	}

	public String getShow() {
		return show;
	}

	public TimeZone getGeneralTimeZone() {
		return generalTimeZone;
	}

	public void setGeneralTimeZone(TimeZone generalTimeZone) {
		this.generalTimeZone = generalTimeZone;
	}

	public String getElimina() {
		return elimina;
	}

	public String getDettagli() {
		return dettagli;
	}

	public String getModifica() {
		return modifica;
	}

	public String getFirst_dis() {
		return first_dis;
	}

	public String getLast_dis() {
		return last_dis;
	}

	public String getPrevious_dis() {
		return previous_dis;
	}

	public String getNext_dis() {
		return next_dis;
	}

	public String getLabelExcel() {
		return labelExcel;
	}

	public String getImgExcel() {
		return imgExcel;
	}

	public String getFastforward() {
		return fastforward;
	}

	private String fastrewind = "/images/portlet/arrow-fr.gif";

	// /css/xp/xp-portlet.css

	public String getFastrewind() {
		return fastrewind;
	}

	public int getFastStep() {
		return fastStep;
	}

	public String getScrollableHeigth() {
		return scrollableHeigth;
	}

	public int getMaxRowsTables() {
		return MaxRowsTables;
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	public String getPrevious() {
		return previous;
	}

	public String getNext() {
		return next;
	}

	public int getPaginatorMaxPages() {
		return paginatorMaxPages;
	}

	public String getActiveCss() throws ContradaExceptionBloccante {
		return Configuration.getConfiguration().getProperty("cssStyle");
	}

	public StyleBean() {
	}
}
