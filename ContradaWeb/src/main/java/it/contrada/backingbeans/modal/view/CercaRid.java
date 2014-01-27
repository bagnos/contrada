package it.contrada.backingbeans.modal.view;

import it.contrada.businessdelegate.RicercaRidBD;
import it.contrada.dto.MembroRidDTO;
import it.contrada.dto.RidDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.Costante;
import it.contrada.web.util.FacesUtils;
import it.contrada.web.util.HelperSession;

import java.io.IOException;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.RowSelectorEvent;

public class CercaRid {
	private List<MembroRidDTO> rids;
	private boolean isVisibleListRid;
	private boolean isVisibleUsa;
	private String nome;
	private String clientClickUsa;
	private String clientClickAnnulla;
	private int cdAnag;
	private Integer idRid;
	private String messaggio;
	
	

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public Integer getIdRid() {
		return idRid;
	}

	public void setIdRid(Integer idRid) {
		this.idRid = idRid;
	}

	public int getCdAnag() {
		return cdAnag;
	}

	public void setCdAnag(int cdAnag) {
		this.cdAnag = cdAnag;
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

	public boolean isVisibleListRid() {
		return isVisibleListRid;
	}

	public void setVisibleListRid(boolean isVisibleListRid) {
		this.isVisibleListRid = isVisibleListRid;
	}

	public List<MembroRidDTO> getRids() {
		return rids;
	}

	public void setRids(List<MembroRidDTO> rids) {
		this.rids = rids;
	}

	public CercaRid() {
		// TODO Auto-generated constructor stub
		// this.clientClickAnnulla="window.returnValue='';window.close();";

	}

	public void cercaRidOnClick(ActionEvent event)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

	
	
		setMessaggio("");
		rids = ricercaRid();
		setVisibleListRid(rids != null && !rids.isEmpty());
		if (!isVisibleListRid)
		{
			setMessaggio("Dati non Presenti");			
		}
	}

	public void ridValueChange(ValueChangeEvent e) {
		if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			e.queue();
		} else {
			isVisibleUsa = false;

			for (MembroRidDTO rid : rids) {
				if (rid.isSelected()) {
					isVisibleUsa = true;
					return;
				}
			}
		}

	}

	public void selezionaRidOnClick(ActionEvent e) throws IOException, ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (e != null) {
			
			
			MembroRidDTO membro=(MembroRidDTO)e.getComponent().getAttributes().get("rid");
			
			RidDTO rid = RicercaRidBD.ricercaPerId(membro.getIdRid());
			HelperSession.putInRequest(Costante.PARM_MOD_RID, "true");
			HelperSession.putInRequest(Costante.SESSION_RID_DTO, rid);
			FacesUtils.navigationToView("MODIFCA_RID");
		}
		// FacesUtils.redirectToUrl("InsAnagrafica.iface");
	}

	public void rowSelectionListener(RowSelectorEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (e != null) {
			MembroRidDTO membro = (MembroRidDTO) FacesUtils.getValueExpression(
					"rid", e);
			RidDTO rid = RicercaRidBD.ricercaPerId(membro.getIdRid());
			HelperSession.putInRequest(Costante.PARM_MOD_RID, "true");
			HelperSession.putInRequest(Costante.SESSION_RID_DTO, rid);
			FacesUtils.navigationToView("MODIFCA_RID");
		}
		/*
		 * 
		 * 
		 * if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
		 * e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES); e.queue(); } else {
		 * isVisibleUsa = false; int row = e.getRow(); for (int i = 0; i <=
		 * rids.size() - 1; i++) { if (i != row) {
		 * rids.get(i).setSelected(false); } else {
		 * 
		 * rids.get(i).setSelected(true); //
		 * setCdAnag(rids.get(i).getIdAnagrafica());
		 * setIdRid(rids.get(i).getIdRid());
		 * 
		 * isVisibleUsa = true;
		 * 
		 * // PortletSession portletSession = //
		 * (PortletSession)FacesContext.getCurrentInstance
		 * ().getExternalContext().getSession(false);
		 * 
		 * // portletSession.setAttribute("nrRid", //
		 * rids.get(i).getIdRid(),PortletSession.APPLICATION_SCOPE);
		 * 
		 * // clientClickUsa = "window.returnValue='"+ // rids.get(i).getRid() +
		 * "';window.close();"; }
		 * 
		 * }
		 * 
		 * }
		 */
	}

	private List<MembroRidDTO> ricercaRid() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		List<MembroRidDTO> membriDTO = null;

		if (getIdRid() != null && getIdRid() != 0) {
			membriDTO = RicercaRidBD.ricercaPerRid(getIdRid());
		} else if (!getNome().equalsIgnoreCase("")
				&& !getCognome().equalsIgnoreCase("")) {
			membriDTO = RicercaRidBD.ricercaPerNomeCognome(getNome(),
					getCognome());
		} else if (!getCognome().equalsIgnoreCase("")) {
			membriDTO = RicercaRidBD.ricercaPerCognome(getCognome());
		} else {
			membriDTO = null;

		}

		return membriDTO;

	}

	public String usaRidOnClick() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		RidDTO rid = RicercaRidBD.ricercaPerId(getIdRid());
		HelperSession.putInRequest(Costante.PARM_MOD_RID, "true");
		HelperSession.putInRequest(Costante.SESSION_RID_DTO, rid);
		return "MODIFCA_RID";
		/*
		 * SessionRenderer.render("NrRid");
		 * 
		 * intnrRid=Integer.parseInt(FacesContext.getCurrentInstance().
		 * getExternalContext().getSessionMap().get("nrRid").toString());
		 * Map<String
		 * ,Object>ss=FacesContext.getCurrentInstance().getExternalContext
		 * ().getSessionMap();
		 * ss=FacesContext.getCurrentInstance().getExternalContext
		 * ().getApplicationMap(); RidPush
		 * rp=(RidPush)FacesContext.getCurrentInstance
		 * ().getExternalContext().getSessionMap().get("ridPush");
		 */

	}

}
