package it.contrada.bean;

import it.contrada.dao.interfaces.IAnagrafeDAO;
import it.contrada.dao.interfaces.IRateizzazioneDAO;
import it.contrada.dao.interfaces.ITesseraDAO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.RateizzazioneDTO;
import it.contrada.enumcontrada.TipoIncasso;
import it.contrada.enumcontrada.TipoRata;
import it.contrada.enumcontrada.TipoStatoAnagrafica;
import it.contrada.enumcontrada.TipoStatoRata;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneTessera;
import it.contrada.util.DecodificaErrore;
import it.othala.payment.paypal.dto.DoExpressCheckoutPaymentDTO;
import it.othala.payment.paypal.dto.GetExpressCheckoutDetailsDTO;
import it.othala.payment.paypal.dto.MessageIpnDTO;
import it.othala.payment.paypal.dto.OrderDTO;
import it.othala.payment.paypal.dto.OrderPayPalDTO;
import it.othala.payment.paypal.dto.ProfilePayPalDTO;
import it.othala.payment.paypal.dto.RedirectDTO;
import it.othala.payment.paypal.dto.RichiestaIncassoDTO;
import it.othala.payment.paypal.dto.SetExpressCheckoutDTO;
import it.othala.payment.paypal.dto.TesseraPaymentDTO;
import it.othala.paymentservice.paypal.PayPalService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class GestioneTessera
 */

public class GestioneTessera implements IGestioneTessera {

	private static Log log = LogFactory.getLog(GestioneTessera.class);

	/**
	 * @uml.property name="tesseraDao"
	 * @uml.associationEnd
	 */
	@Autowired
	private ITesseraDAO tesseraDao;
	private IAnagrafeDAO anagrafeDao;
	private IRateizzazioneDAO rateizzazioneDao;

	public void setRateizzazioneDao(IRateizzazioneDAO rateizzazioneDao) {
		this.rateizzazioneDao = rateizzazioneDao;
	}

	public void setAnagrafeDao(IAnagrafeDAO anagrafeDao) {
		this.anagrafeDao = anagrafeDao;
	}

	/**
	 * @param tesseraDao
	 * @uml.property name="tesseraDao"
	 */
	public void setTesseraDao(ITesseraDAO tesseraDao) {
		this.tesseraDao = tesseraDao;
	}

	/**
	 * Default constructor.
	 */
	public GestioneTessera() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IGestioneTessera#preparaTesseraAnnoInCorso()
	 */

	public int allineaQuotaTessere(java.sql.Date dataRif)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		int rows = 0;
		try {
			// riattivo le anagrafiche sopsese
			anagrafeDao.aggiornaStatoAnagrafica(
					TipoStatoAnagrafica.Attiva.getStatoAnagrafica(),
					TipoStatoAnagrafica.Sospesa.getStatoAnagrafica());

			// si aggiornano le tessere con la nuova carica ed importo
			rows = tesseraDao.allineaQuoteTessera(dataRif);
			if (rows > 0) {
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(dataRif);

				tesseraDao.deleteStoricoAnno(calendar
						.get(GregorianCalendar.YEAR));

				tesseraDao.insertStoricoAnno(calendar
						.get(GregorianCalendar.YEAR));

			}

		} catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return rows;
	}

	public int aggiornaImportoTessera(List<TipoTesseraDTO> tessere)
			throws ContradaExceptionBloccante {
		int rows = 0;

		try {

			if (tessere == null) {
				return 0;
			}

			rows += tesseraDao.aggiornaImportoTessera(tessere);
			rows += tesseraDao.aggiornaImportoTesseraStorico(tessere);

			return rows;
		} catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		// TODO Auto-generated catch block
		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public int aggiornaTipoTessera(List<TipoTesseraDTO> tessere,
			boolean aggiornaTessereInCorso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int rows = 0;

		try {

			if (tessere == null) {
				return 0;
			}

			rows = tesseraDao.aggiornaTipoTessera(tessere);
			if (aggiornaTessereInCorso) {
				aggiornaImportoTessera(tessere);
			}

			return rows;
		} catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		// TODO Auto-generated catch block
		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public SetExpressCheckoutDTO inserisciOrderPayment(
			RichiestaIncassoDTO richiesta, ProfilePayPalDTO prof)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {

			OrderDTO order = new OrderDTO();
			order.setUser(richiesta.getUser());
			order.setReturnUrl(richiesta.getReturnUrl());
			tesseraDao.insertOrderPayment(order);
			for (TesseraPaymentDTO tes : richiesta.getTessere()) {
				tes.setIdOrder(order.getIdOrder());
				tesseraDao.insertTesseraOrderPayment(tes);
			}

			order.setCart(richiesta.getTessere());
			PayPalService pay = new PayPalService(prof);

			try {
				SetExpressCheckoutDTO checkout = pay.setExpressCheckout(order);
				return checkout;
			} catch (Exception ex) {
				throw new ContradaExceptionBloccante(
						"Errore nel pagamento vesro paypal", ex);

			}

		}

		catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		// TODO Auto-generated catch block
		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public String confermaOrderPayment(OrderDTO order, String status,
			MessageIpnDTO message) throws ContradaExceptionBloccante {
		try {
			RateizzazioneDTO rat = null;
			String messaggio = "";
			boolean okPayment = false;
			boolean koPostPayPal = false;
			BigDecimal amount = BigDecimal.ZERO;
			List<RateizzazioneDTO> rateizzazioni = new ArrayList<RateizzazioneDTO>();
			for (TesseraPaymentDTO tessera : order.getCart()) {
				rat = new RateizzazioneDTO();
				rat.setIdTessera(tessera.getIdTessera());
				rat.setIdRata(TipoRata.ESTEMPORANEA.getRata());
				rat.setImRata((tessera.getImTessera().multiply(new BigDecimal(
						100))).intValue());
				rat.setNrAnno(tessera.getNrAnno());
				rat.setIdIncasso(TipoIncasso.PAYPAL.getIncasso());
				rat.setTipoStatoRata(TipoStatoRata.Pagata.getStatoRata());
				rateizzazioni.add(rat);
				amount = amount.add(tessera.getImTessera());
			}
			
			

			try {
				if (PayPalService.isPaymentCompleted(status)) {
					okPayment = true;
					for (RateizzazioneDTO rateizzazione : rateizzazioni) {
						rateizzazioneDao.insertRateizzazione(rateizzazione);
					}

					messaggio = "Il Pagamento mediante PayPal di importo "
							+ amount.toString()
							+ " per il Protettorato della Nobile Contrada del Nicchio si è concluso correttamente. \nNella sua area riservata potrà verificare il pagamento delle tessere.";

				} else if (PayPalService.isPaymentPending(status)) {

					messaggio = "Il pagamento è attualmente in elaborazione sul sistema PayPal, riceverà una email al termine della elaborazione";
					// invio mail al cliente

				} else if (PayPalService.isPaymentPending(status)) {
					messaggio = "Siamo spiacenti ma il sistema PayPal ha rifiutato la sua richiesta di pagamento";
				}
				else
				{
					messaggio=null;
					log.warn("nessuna attività da fare per lo stato " + status);
				}
				
				aggiornaOrdineOnline(order.getIdOrder(), status, null);
			} catch (Exception e) {
				koPostPayPal = true;
				log.error("errore elaborazione post payPal", e);
			}
			if (okPayment && koPostPayPal) {
				messaggio = "Il pagamento è andato a buon fine ma si è generato un errore sulla registazione del pagamento, contattare la Commissione Protettorato per registrare l'operazione";
			}

			

			return messaggio;

		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public RedirectDTO confermaOrderPayment(String token, ProfilePayPalDTO prof)
			throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		boolean koPostPayPal = false;
		boolean okPayment = false;
		String messaggio = "";
		try {

			PayPalService pay = new PayPalService(prof);
			GetExpressCheckoutDetailsDTO details = pay
					.getExpressCheckoutDetails(token);

			int idOrder = Integer.valueOf(details.getCustom());
			OrderDTO order = tesseraDao.getOrderPaymentDTO(idOrder);

			DoExpressCheckoutPaymentDTO checkDTO = pay
					.doExpressCheckoutPayment(details.getToken());

			messaggio = confermaOrderPayment(order,
					checkDTO.getPAYMENTINFO_0_PAYMENTSTATUS(), null);

			RedirectDTO redirect = new RedirectDTO();
			redirect.setContentMail(messaggio);
			redirect.setRedirectUrl(order.getReturnUrl() + "?okPayment="
					+ okPayment + "&messaggio=" + messaggio);
			redirect.setSubjectsMail("Pagamento Tessere Online ProtettoratoNicchio");
			redirect.setTosMail(order.getUser());

			// invia mail
			return redirect;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public boolean existIdTrasaction(String idTransaction, String stato)
			throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		int rows = tesseraDao.getIdTrasanction(idTransaction, stato);
		boolean exist = rows > 0 ? true : false;
		return true;
	}

	public OrderDTO recuperaOrdineOnline(int idOrder)
			throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		return tesseraDao.getOrderPaymentDTO(idOrder);
	}

	public int aggiornaOrdineOnline(int idOrder, String stato,
			MessageIpnDTO message) throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		if (message != null) {
			inserisceMessaggioIPN(message);
		}
		return tesseraDao.aggiornaStatoOrdine(idOrder, stato);
	}

	public int inserisceMessaggioIPN(MessageIpnDTO message)
			throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		return tesseraDao.insertMessageIpn(message);
	}

}
