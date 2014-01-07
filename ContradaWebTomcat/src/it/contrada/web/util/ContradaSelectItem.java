package it.contrada.web.util;

import it.contrada.dominio.dto.TipoCasualiIncassoRidDTO;
import it.contrada.dominio.dto.TipoCasualiPreautDTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class ContradaSelectItem {
	
	private ContradaSelectItem()
	{}
	
	public static List<SelectItem> valueCausaliItemOf(List<TipoCasualiPreautDTO> causali) {
		SelectItem causaleItem = null;

		List<SelectItem> causaliItem = new ArrayList<SelectItem>();

		if (causali != null && !causali.isEmpty()) {
			for (TipoCasualiPreautDTO causaleDTO : causali) {
				causaleItem = new SelectItem(causaleDTO.getCdCausale(),
						causaleDTO.getCdCausale()+"-"+causaleDTO.getDsCausale());
				causaliItem.add(causaleItem);

			}
			causaliItem.add(new SelectItem(Costante.CD_CAUSALE_ESITI_TUTTI, Costante.DS_CAUSALE_ESITI_TUTTI));
		}
		return causaliItem;

	}
	
	public static List<SelectItem> valueCausaliIncassoItemOf(List<TipoCasualiIncassoRidDTO> causali) {
		SelectItem causaleItem = null;

		List<SelectItem> causaliItem = new ArrayList<SelectItem>();

		if (causali != null && !causali.isEmpty()) {
			for (TipoCasualiIncassoRidDTO causaleDTO : causali) {
				causaleItem = new SelectItem(causaleDTO.getCdCausale(),
						causaleDTO.getCdCausale()+"-"+causaleDTO.getDsCausale());
				causaliItem.add(causaleItem);

			}
			causaliItem.add(new SelectItem(Costante.CD_CAUSALE_ESITI_TUTTI, Costante.DS_CAUSALE_ESITI_TUTTI));
		}
		return causaliItem;

	}
}
