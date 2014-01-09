package it.contrada.backingbeans;

import java.io.Serializable;

public class ErrorBean implements Serializable{

	private String message;
	private String viewSource;
	private String viewDest;
	
	
	public String getViewSource() {
		return viewSource;
	}


	public void setViewSource(String viewSource) {
		this.viewSource = viewSource;
	}


	public String getViewDest() {
		return viewDest;
	}


	public void setViewDest(String viewDest) {
		this.viewDest = viewDest;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public ErrorBean() {
		// TODO Auto-generated constructor stub
	}

}
