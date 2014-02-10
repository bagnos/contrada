package it.contrada.mail;

import it.contrada.dto.MembroRidDTO;
import it.contrada.dto.RidDTO;
import it.contrada.enumcontrada.TipoStatoRid;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.incassorid.dto.DisposizioneIncassoRidRicezioneDTO;
import it.contrada.web.util.ConverterContrada;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class RidMail extends BaseMail {

	private final static String SUBJECT_RID_SOSPESO = "Protettorato Nicchio - incasso RID";
	private final static String FILE_MAIL_RID_SOSPESO_RATA = "MailRidSospesiRata.txt";
	private final static String FILE_MAIL_RID_SOSPESO = "MailRidSospesi.txt";
	private final static String MAIL_RID_INVIATA = "INVIATA";
	private final static String MAIL_RID_NON_PRESENTE = "MAIL NON PRESENTE";

	private static String getContentRidSospesiRata(String cognome, String nome,
			String abi, String cab, String conto, String intestatario,
			String importo, String motivazione, String content)
			throws ContradaExceptionBloccante, IOException {

		content = content.replaceAll("<COGNOME>", cognome).replaceAll("<NOME>",
				nome);

		conto = String.format("%s %s %s", abi.trim(), cab.trim(), conto.trim());

		content = content.replaceAll("<CONTO>", conto);

		content = content.replaceAll("<INTESTATARIO>", intestatario.trim());

		content = content.replaceAll("<IMPORTO>",
				ConverterContrada.convertToImporto(Integer.valueOf(importo)));

		content = content.replaceAll("<MOTIVAZIONE>", motivazione);

		return content;
	}

	private static String getContentRidSospesi(String cognome, String nome,
			String abi, String cab, String conto, String intestatario,
			String content, String tessera) throws ContradaExceptionBloccante,
			IOException {

		content = content.replaceAll("<COGNOME>", cognome).replaceAll("<NOME>",
				nome);

		conto = String.format("%s %s %s", abi.trim(), cab.trim(), conto.trim());

		content = content.replaceAll("<CONTO>", conto);

		content = content.replaceAll("<INTESTATARIO>", intestatario.trim());

		content = content.replaceAll("<TESSERA>", tessera);

		return content;
	}

	public static int InviaMailSospesiRid(List<RidDTO> rids, String tessera)
			throws IOException, ContradaExceptionBloccante, AddressException,
			MessagingException {
		String[] address = new String[1];
		String contentFILE = getContenFile(FILE_MAIL_RID_SOSPESO, true);
		String content = null;
		int emailRidInviati = 0;

		for (RidDTO rid : rids) {
			rid.setEsitoMail(MAIL_RID_NON_PRESENTE);
			for (MembroRidDTO ridM : rid.getMembri()) {
				if (ridM.getMail() != null && !ridM.getMail().trim().isEmpty()) {

					address[0] = ridM.getMail();
					content = contentFILE;
					content = getContentRidSospesi(ridM.getCognome(),
							ridM.getNome(), rid.getAbi().toString(), rid
									.getCab().toString(), rid.getNumeroCC(),
							rid.getIntestatarioRid(), content, tessera);

					inviaMail(address, SUBJECT_RID_SOSPESO, content, null);
					rid.setEsitoMail(MAIL_RID_INVIATA);

				}
			}
			if (rid.getEsitoMail()!=null && !rid.getEsitoMail().equalsIgnoreCase(MAIL_RID_NON_PRESENTE))
			{
				emailRidInviati++;
			}
		}
		return emailRidInviati;
	}

	public static int InviaMailSospesiRataRid(
			List<DisposizioneIncassoRidRicezioneDTO> incassi)
			throws IOException, ContradaExceptionBloccante, AddressException,
			MessagingException {
		String[] address = new String[1];
	
		String contentFILE = getContenFile(FILE_MAIL_RID_SOSPESO_RATA, true);
		String content = null;
		int emailRidInviati = 0;

		for (DisposizioneIncassoRidRicezioneDTO incasso : incassi) {
			
			if (incasso.getTipoStatoRid() != null
					&& incasso.getTipoStatoRid().getStatoRid() == TipoStatoRid.Sospesa
							.getStatoRid())
				incasso.setEsitoMail(MAIL_RID_NON_PRESENTE);
				for (MembroRidDTO ridM : incasso.getMembri()) {
					if (ridM.getMail() != null
							&& !ridM.getMail().trim().isEmpty()) {
						address[0] = ridM.getMail();
						content = contentFILE;
						content = getContentRidSospesiRata(ridM.getCognome(),
								ridM.getNome(), incasso.getAbi(),
								incasso.getCab(), incasso.getConto(), incasso
										.getRec20().getSegmento1(), incasso
										.getRec10().getImporto(), incasso
										.getRec50().getSegmento1(), content);

						inviaMail(address, SUBJECT_RID_SOSPESO, content, null);
						incasso.setEsitoMail(MAIL_RID_INVIATA);
					}					
				}
				if (incasso.getEsitoMail()!=null && !incasso.getEsitoMail().equalsIgnoreCase(MAIL_RID_NON_PRESENTE))
				{
					emailRidInviati++;
				}
		}
		return emailRidInviati;
	}
}
