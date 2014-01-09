package it.contrada.backingbeans.reversecooking.pagecodes;

import it.contrada.dao.sqlite.ReverseCookingDAO;
import it.contrada.reversecooking.dto.IngredienteDTO;
import it.contrada.reversecooking.dto.PortataDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class InserimentoRicetta {

	private List<IngredienteDTO> ingredienti = null;
	private List<SelectItem> ingredientiItem = null;
	private int ingredienteSelezionato;
	private String titoloRicetta;
	private int portataSelezionata;
	private List<SelectItem> portateItem = null;
	private List<PortataDTO> portate;

	public int getPortataSelezionata() {
		return portataSelezionata;
	}

	public void setPortataSelezionata(int portataSelezionata) {
		this.portataSelezionata = portataSelezionata;
	}

	public List<SelectItem> getPortateItem() {
		return portateItem;
	}

	public String getTitoloRicetta() {
		return titoloRicetta;
	}

	public void setTitoloRicetta(String titoloRicetta) {
		this.titoloRicetta = titoloRicetta;
	}

	public int getIngredienteSelezionato() {
		return ingredienteSelezionato;
	}

	public void setIngredienteSelezionato(int ingredienteSelezionato) {
		this.ingredienteSelezionato = ingredienteSelezionato;
	}

	public List<SelectItem> getIngredientiItem() {
		return ingredientiItem;
	}

	public InserimentoRicetta() throws ClassNotFoundException, SQLException {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("ReverseCooking.db").getFile();
		ReverseCookingDAO daoRC = new ReverseCookingDAO(path);

		ingredienti = daoRC.getIngredienti();
		portate=daoRC.getPortate();
		initIngredientiItem();
		initPortateItem();

	}

	private void initIngredientiItem() {
		ingredientiItem = new ArrayList<SelectItem>();
		for (IngredienteDTO ing : ingredienti) {
			SelectItem itemIng = new SelectItem(ing.getIdIngrediente(), ing
					.getNomeIngrediente());
			ingredientiItem.add(itemIng);

		}

	}
	
	private void initPortateItem() {
		portateItem = new ArrayList<SelectItem>();
		for (PortataDTO por : portate) {
			SelectItem itemPor = new SelectItem(por.getIdPortata(), por.getTxPortata());
			portateItem.add(itemPor);

		}

	}

}
