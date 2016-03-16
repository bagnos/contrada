package it.othala.payment.paypal.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DeliveryCostDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public BigDecimal getImportoSpese() {
		return importoSpese;
	}
	public void setImportoSpese(BigDecimal importoSpese) {
		this.importoSpese = importoSpese;
	}
	public Integer getIdDeliveryCost() {
		return idDeliveryCost;
	}
	public void setIdDeliveryCost(Integer idDeliveryCost) {
		this.idDeliveryCost = idDeliveryCost;
	}
	private Integer idDeliveryCost;
	private String descrizione;
	private BigDecimal importoSpese;
	
	public String getDescrizioneExt() {
		return descrizioneExt;
	}
	public void setDescrizioneExt(String descrizioneExt) {
		this.descrizioneExt = descrizioneExt;
	}
	public String getLinkTracking() {
		return linkTracking;
	}
	public void setLinkTracking(String linkTracking) {
		this.linkTracking = linkTracking;
	}
	public Integer getIdAddress() {
		return idAddress;
	}
	public void setIdAddress(Integer idAddress) {
		this.idAddress = idAddress;
	}

	public DeliveryAddressDTO getDeliverySede() {
		return deliverySede;
	}
	public void setDeliverySede(DeliveryAddressDTO deliverySede) {
		this.deliverySede = deliverySede;
	}
	private String descrizioneExt;
	private String linkTracking;
	private Integer idAddress;
	
	private DeliveryAddressDTO deliverySede;
	

}
