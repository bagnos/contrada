package it.contrada.interfaces;

import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.othala.payment.paypal.dto.MessageIpnDTO;
import it.othala.payment.paypal.dto.OrderDTO;
import it.othala.payment.paypal.dto.ProfilePayPalDTO;
import it.othala.payment.paypal.dto.RedirectDTO;
import it.othala.payment.paypal.dto.RichiestaIncassoDTO;
import it.othala.payment.paypal.dto.SetExpressCheckoutDTO;

import java.util.List;

public interface IGestioneTessera {
	
	public int allineaQuotaTessere(java.sql.Date dataRif) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	public int aggiornaTipoTessera(List<TipoTesseraDTO> tessere,boolean aggiornaTessereInCorso) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	public int aggiornaImportoTessera(List<TipoTesseraDTO> tessere) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	public SetExpressCheckoutDTO inserisciOrderPayment(RichiestaIncassoDTO richiesta,ProfilePayPalDTO prof) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	public RedirectDTO confermaOrderPayment(String token,ProfilePayPalDTO prof) throws ContradaExceptionBloccante;
	public boolean existIdTrasaction(String idTransaction,String stato) throws ContradaExceptionBloccante;
	public OrderDTO recuperaOrdineOnline(int idOrder) throws ContradaExceptionBloccante;
	public int aggiornaOrdineOnline(int idOrder, String stato,MessageIpnDTO message) throws ContradaExceptionBloccante;
	public int inserisceMessaggioIPN(MessageIpnDTO message) throws ContradaExceptionBloccante;
	public String confermaOrderPayment(OrderDTO order, String status,MessageIpnDTO message) throws ContradaExceptionBloccante;
	
}

