package it.contrada.backingbeans;

import java.io.File;
import java.io.Serializable;

public class PrintFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1823036561999052727L;
	private String nomeFile;
	private String nomeFileCompleto;
	private File file;
	
	
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	public String getNomeFileCompleto() {
		return nomeFileCompleto;
	}
	public void setNomeFileCompleto(String nomeFileCompleto) {
		this.nomeFileCompleto = nomeFileCompleto;
	}
	
	
}
