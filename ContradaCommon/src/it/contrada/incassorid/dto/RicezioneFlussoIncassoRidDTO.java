package it.contrada.incassorid.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RicezioneFlussoIncassoRidDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6812792818229424235L;
	private RecordEFDTO recEF;
	private RecordIRDTO recIR;
	private List<DisposizioneIncassoRidRicezioneDTO> disposizioni;
	public RecordEFDTO getRecEF() {
		return recEF;
	}
	public void setRecEF(RecordEFDTO recEF) {
		this.recEF = recEF;
	}
	public RecordIRDTO getRecIR() {
		return recIR;
	}
	public void setRecIR(RecordIRDTO recIR) {
		this.recIR = recIR;
	}
	public List<DisposizioneIncassoRidRicezioneDTO> getDisposizioni() {
		return disposizioni;
	}
	public void setDisposizioni(
			List<DisposizioneIncassoRidRicezioneDTO> disposizioni) {
		this.disposizioni = disposizioni;
	}
	
	public RicezioneFlussoIncassoRidDTO()
	{
		disposizioni=new ArrayList<DisposizioneIncassoRidRicezioneDTO>();
	}
	
}
