package it.contrada.preautrid.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RicezioneFlussoPreautorizzazioneDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6077691867179766262L;
	private List<DisposizionePreautRicezioneDTO> disposizioni;
	private RecordEFDTO recordEF;
	private RecordALDTO recordAL;

	public RecordALDTO getRecordAL() {
		return recordAL;
	}

	public void setRecordAL(RecordALDTO recordAL) {
		this.recordAL = recordAL;
	}

	

	public RecordEFDTO getRecordEF() {
		return recordEF;
	}

	public void setRecordEF(RecordEFDTO recordEF) {
		this.recordEF = recordEF;
	}

	public List<DisposizionePreautRicezioneDTO> getDisposizioni() {
		return disposizioni;
	}

	public void setDisposizioni(
			List<DisposizionePreautRicezioneDTO> disposizioni) {
		this.disposizioni = disposizioni;
	}
	
	public RicezioneFlussoPreautorizzazioneDTO()
	{
		disposizioni=new ArrayList<DisposizionePreautRicezioneDTO>();
	}
	 
}
