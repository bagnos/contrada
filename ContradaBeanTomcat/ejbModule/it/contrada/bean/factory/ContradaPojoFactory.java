package it.contrada.bean.factory;

import it.contrada.interfaces.IGestioneAnagrafica;
import it.contrada.interfaces.IGestioneCarica;
import it.contrada.interfaces.IGestioneDistinta;
import it.contrada.interfaces.IGestioneFamiglia;
import it.contrada.interfaces.IGestioneFlusso;
import it.contrada.interfaces.IGestioneGestore;
import it.contrada.interfaces.IGestionePoste;
import it.contrada.interfaces.IGestioneRateizzazione;
import it.contrada.interfaces.IGestioneRid;
import it.contrada.interfaces.IGestioneStradario;
import it.contrada.interfaces.IGestioneTessera;
import it.contrada.interfaces.IGestioneUtente;
import it.contrada.interfaces.IRicercaAnagrafica;
import it.contrada.interfaces.IRicercaCap;
import it.contrada.interfaces.IRicercaCarica;
import it.contrada.interfaces.IRicercaComune;
import it.contrada.interfaces.IRicercaEsattore;
import it.contrada.interfaces.IRicercaFamiglia;
import it.contrada.interfaces.IRicercaFlusso;
import it.contrada.interfaces.IRicercaIncasso;
import it.contrada.interfaces.IRicercaMese;
import it.contrada.interfaces.IRicercaNazione;
import it.contrada.interfaces.IRicercaOperazione;
import it.contrada.interfaces.IRicercaProvincia;
import it.contrada.interfaces.IRicercaRateizzazione;
import it.contrada.interfaces.IRicercaRegione;
import it.contrada.interfaces.IRicercaRid;
import it.contrada.interfaces.IRicercaStrada;
import it.contrada.interfaces.IRicercaTessera;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContradaPojoFactory {

	private static ApplicationContext appContext = null;
	private static final String appContextCompletePath = "it/contrada/bean/factory/spring/applicationContext_ContradaBeanLibPojo.xml";
	private static Object mutex = new Object();

	private static ApplicationContext getApplicationContext() {

		if (appContext == null) {
			synchronized (mutex) {
				if (appContext == null)

				{
					appContext = new ClassPathXmlApplicationContext(
							appContextCompletePath);
				}

			}
		}
		return appContext;
	}

	public static IGestioneAnagrafica getGestioneAnagraficaInstance() {
		IGestioneAnagrafica gestioneAnagrafica = (IGestioneAnagrafica) getApplicationContext()
				.getBean("gestioneAnagrafica");
		return gestioneAnagrafica;

	}

	public static IGestioneCarica getGestioneCaricaInstance() {
		IGestioneCarica gestioneCarica = (IGestioneCarica) getApplicationContext()
				.getBean("gestioneCarica");
		return gestioneCarica;

	}

	public static IRicercaOperazione getRicercaOperazioneInstance() {
		IRicercaOperazione ricercaOperazione = (IRicercaOperazione) getApplicationContext()
				.getBean("ricercaOperazione");
		return ricercaOperazione;

	}

	public static IGestioneDistinta getGestioneDistintaInstance() {
		IGestioneDistinta gestioneDistinta = (IGestioneDistinta) getApplicationContext()
				.getBean("gestioneDistinta");
		return gestioneDistinta;

	}

	public static IGestioneFamiglia getGestioneFamigliaInstance() {
		IGestioneFamiglia gestioneFamiglia = (IGestioneFamiglia) getApplicationContext()
				.getBean("gestioneFamiglia");
		return gestioneFamiglia;

	}

	public static IGestioneFlusso getGestioneFlussoInstance() {
		IGestioneFlusso gestioneFlusso = (IGestioneFlusso) getApplicationContext()
				.getBean("gestioneFlusso");
		return gestioneFlusso;

	}

	public static IGestioneGestore getGestioneGestoreInstance() {
		IGestioneGestore gestioneGestore = (IGestioneGestore) getApplicationContext()
				.getBean("gestioneGestore");
		return gestioneGestore;

	}

	public static IGestionePoste getGestionePosteInstance() {
		IGestionePoste gestionePoste = (IGestionePoste) getApplicationContext()
				.getBean("gestionePoste");
		return gestionePoste;

	}

	public static IGestioneRateizzazione getGestioneRateizzazioneInstance() {
		IGestioneRateizzazione gestioneRateizzazione = (IGestioneRateizzazione) getApplicationContext()
				.getBean("gestioneRateizzazione");
		return gestioneRateizzazione;

	}

	public static IGestioneRid getGestioneRid() {
		IGestioneRid gestioneRid = (IGestioneRid) getApplicationContext()
				.getBean("gestioneRid");
		return gestioneRid;

	}

	public static IGestioneStradario getGestioneStradarioInstance() {
		IGestioneStradario gestioneStradario = (IGestioneStradario) getApplicationContext()
				.getBean("gestioneStradario");
		return gestioneStradario;

	}

	public static IGestioneTessera getGestioneTesseraIstance() {
		IGestioneTessera gestioneTessera = (IGestioneTessera) getApplicationContext()
				.getBean("gestioneTessera");
		return gestioneTessera;

	}

	public static IGestioneUtente getGestioneUtenteIstance() {
		IGestioneUtente gestioneUtente = (IGestioneUtente) getApplicationContext()
				.getBean("gestioneUtente");
		return gestioneUtente;

	}

	public static IRicercaAnagrafica getRicercaAnagraficaIstance() {
		IRicercaAnagrafica ricercaAnagrafica = (IRicercaAnagrafica) getApplicationContext()
				.getBean("ricercaAnagrafica");
		return ricercaAnagrafica;

	}

	public static IRicercaCap getRicercaCapIstance() {
		IRicercaCap ricercaCap = (IRicercaCap) getApplicationContext().getBean(
				"ricercaCap");
		return ricercaCap;

	}

	public static IRicercaCarica getRicercaCaricaIstance() {
		IRicercaCarica ricercaCarica = (IRicercaCarica) getApplicationContext()
				.getBean("ricercaCarica");
		return ricercaCarica;

	}

	public static IRicercaComune getRicercaComuneIstance() {
		IRicercaComune ricercaComune = (IRicercaComune) getApplicationContext()
				.getBean("ricercaComune");
		return ricercaComune;

	}

	public static IRicercaEsattore getRicercaEsattoreIstance() {
		IRicercaEsattore ricercaEsattore = (IRicercaEsattore) getApplicationContext()
				.getBean("ricercaEsattore");
		return ricercaEsattore;

	}

	public static IRicercaFamiglia getRicercaFamigliaIstance() {
		IRicercaFamiglia ricercaFamiglia = (IRicercaFamiglia) getApplicationContext()
				.getBean("ricercaFamiglia");
		return ricercaFamiglia;

	}

	public static IRicercaFlusso getRicercaFlussoIstance() {
		IRicercaFlusso ricercaFlusso = (IRicercaFlusso) getApplicationContext()
				.getBean("ricercaFlusso");
		return ricercaFlusso;

	}

	public static IRicercaIncasso getRicercaIncassoIstance() {
		IRicercaIncasso ricercaIncasso = (IRicercaIncasso) getApplicationContext()
				.getBean("ricercaIncasso");
		return ricercaIncasso;

	}

	public static IRicercaMese getRicercaMeseIstance() {
		IRicercaMese ricercaMese = (IRicercaMese) getApplicationContext()
				.getBean("ricercaMese");
		return ricercaMese;
	}

	public static IRicercaNazione getRicercaNazioneIstance() {
		IRicercaNazione ricercaNazione = (IRicercaNazione) getApplicationContext()
				.getBean("ricercaNazione");
		return ricercaNazione;

	}

	public static IRicercaProvincia getRicercaProvinciaIstance() {
		IRicercaProvincia ricercaProvincia = (IRicercaProvincia) getApplicationContext()
				.getBean("ricercaProvincia");
		return ricercaProvincia;

	}

	public static IRicercaRateizzazione getRicercaRateizzazioneIstance() {
		IRicercaRateizzazione ricercaRateizzazione = (IRicercaRateizzazione) getApplicationContext()
				.getBean("ricercaRateizzazione");
		return ricercaRateizzazione;

	}

	public static IRicercaRegione getRicercaRegioneIstance() {
		IRicercaRegione ricercaRegione = (IRicercaRegione) getApplicationContext()
				.getBean("ricercaRegione");
		return ricercaRegione;

	}

	public static IRicercaRid getRicercaRidIstance() {
		IRicercaRid ricercaRid = (IRicercaRid) getApplicationContext().getBean(
				"ricercaRid");
		return ricercaRid;

	}

	public static IRicercaStrada getRicercaStradaIstance() {
		IRicercaStrada ricercaStrada = (IRicercaStrada) getApplicationContext()
				.getBean("ricercaStrada");
		return ricercaStrada;

	}

	public static IRicercaTessera getRicercaTesseraIstance() {
		IRicercaTessera ricercaTessera = (IRicercaTessera) getApplicationContext()
				.getBean("ricercaTessera");
		return ricercaTessera;

	}

}