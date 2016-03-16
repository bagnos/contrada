package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneTessera;
import it.othala.payment.paypal.dto.MessageIpnDTO;
import it.othala.payment.paypal.dto.OrderDTO;
import it.othala.payment.paypal.dto.ProfilePayPalDTO;
import it.othala.payment.paypal.dto.RedirectDTO;
import it.othala.payment.paypal.dto.RichiestaIncassoDTO;
import it.othala.payment.paypal.dto.SetExpressCheckoutDTO;

import java.sql.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GestioneTesseraBD {
	private static IGestioneTessera gestioneTessera;
	private static Log log = LogFactory.getLog(GestioneTesseraBD.class);

	private GestioneTesseraBD() {
	}

	static {
		/*
		 * if (gestioneTessera == null) { InitialContext ctx; try { ctx = new
		 * InitialContext(); gestioneTessera = (IGestioneTessera) ctx
		 * .lookup("ContradaEAR/GestioneTessera/local"); } catch
		 * (NamingException e) { // TODO Auto-generated catch block
		 * log.error(e.getMessage(), e); throw new RuntimeException(e); }
		 * 
		 * }
		 */
		gestioneTessera = ContradaPojoFactory.getGestioneTesseraIstance();
	}

	public static int allineaQuota(java.util.Date dtRiferimento)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		java.sql.Date dataRif = new Date(dtRiferimento.getTime());
		return gestioneTessera.allineaQuotaTessere(dataRif);

	}

	public static int aggiornaTipoTessera(List<TipoTesseraDTO> tessere,
			boolean aggiornaTessereInCorso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return gestioneTessera.aggiornaTipoTessera(tessere,
				aggiornaTessereInCorso);

	}

	public static SetExpressCheckoutDTO inserisciOrderPayment(RichiestaIncassoDTO richiesta,ProfilePayPalDTO profile)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		return gestioneTessera.inserisciOrderPayment(richiesta,profile);
	}
	
	public static RedirectDTO confermaOrderPayment(String token, ProfilePayPalDTO prof)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante 
			{
		return gestioneTessera.confermaOrderPayment(token, prof);
			}
	
	public static boolean existIdTrasaction(String idTransaction,String stato) throws ContradaExceptionBloccante
	{
		return gestioneTessera.existIdTrasaction(idTransaction,stato);
	}
	
	public static OrderDTO recuperaOrdineOnline(int idOrder) throws ContradaExceptionBloccante
	{
		return gestioneTessera.recuperaOrdineOnline(idOrder);
	}
	
	public static int aggiornaOrdineOnline(int idOrder,String stato,MessageIpnDTO message) throws ContradaExceptionBloccante
	{
		return gestioneTessera.aggiornaOrdineOnline(idOrder,stato,message);
	}
	
	public static int inserisceMessaggioIPN(MessageIpnDTO message) throws ContradaExceptionBloccante
	{
		return gestioneTessera.inserisceMessaggioIPN(message);
	}
	
	public static String confermaOrderPayment(OrderDTO order, String status,MessageIpnDTO message) throws ContradaExceptionBloccante
	{
		return gestioneTessera.confermaOrderPayment(order, status, message);
	}

}
