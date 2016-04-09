package it.contrada.pojo;

import it.contrada.dto.FlussoEsitoDTO;
import it.contrada.enumcontrada.TipoFlusso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.util.DecodificaErrore;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FlussoUtil {

	private FlussoUtil() {
	}

	public static FlussoEsitoDTO getFromNomeFile(String nomeFile,
			TipoFlusso tipoFlusso, java.util.Date dtDa, java.util.Date dtA)
			throws ContradaExceptionBloccante {
		// 01-01-10_02-04-10_XRD_TUTTI_STDosso16733892
		/*
		 * SimpleDateFormat formatDDMMAA=new SimpleDateFormat("dd-MM-yy");
		 * FlussoEsitoDTO flusso=new FlussoEsitoDTO(); String[]
		 * tokenizer=nomeFile.split("_");
		 * 
		 * if (tokenizer.length==0) { throw new
		 * ContradaExceptionBloccante(DecodificaErrore.getError("25")); }
		 */
		FlussoEsitoDTO flusso = new FlussoEsitoDTO();
		flusso.setTxNomeFile(nomeFile);

		flusso.setDtDa(new java.sql.Date(dtDa.getTime()));
		flusso.setDtA(new java.sql.Date(dtA.getTime()));

		flusso.setTipoFlusso(tipoFlusso.getFlusso());
		return flusso;
	}
	
	public static FlussoEsitoDTO getFromNomeFile(String nomeFile,TipoFlusso tipoFlusso) throws  ContradaExceptionBloccante
	{
		//01-01-10_02-04-10_XRD_TUTTI_STDosso16733892
		SimpleDateFormat formatDDMMAA=new SimpleDateFormat("dd-MM-yy");
		FlussoEsitoDTO flusso=new FlussoEsitoDTO();
		String[] tokenizer=nomeFile.split("_");
		
		if (tokenizer.length==0)
		{
			throw new ContradaExceptionBloccante(DecodificaErrore.getError("25"));
		}
		
		flusso.setTxNomeFile(nomeFile);
		try {
			flusso.setDtDa(new java.sql.Date(formatDDMMAA.parse(tokenizer[0]).getTime()));
			flusso.setDtA(new java.sql.Date(formatDDMMAA.parse(tokenizer[1]).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new ContradaExceptionBloccante(DecodificaErrore.getError("25"),e);
		}
	
		flusso.setTipoFlusso(tipoFlusso.getFlusso());
		return flusso;
	}
}
