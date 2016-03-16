package it.othala.payment.paypal.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemCartDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	private BigDecimal price;
	private int qta;
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getQta() {
		return qta;
	}
	public void setQta(int qta) {
		this.qta = qta;
	}
	
	
	

}
