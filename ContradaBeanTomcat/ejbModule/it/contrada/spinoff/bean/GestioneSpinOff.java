package it.contrada.spinoff.bean;

import it.contrada.dao.interfaces.IAnagrafeDAO;
import it.contrada.dao.interfaces.ISpinOffDAO;
import it.contrada.dao.interfaces.IStradaDAO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.StradaDTO;
import it.contrada.interfaces.IGestioneSpinOff;
import it.contrada.spinoff.dto.ComuneDTO;
import it.contrada.spinoff.dto.ComuneNewDTO;
import it.contrada.spinoff.dto.ProtettoreDTO;
import it.contrada.spinoff.dto.ViaDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class GestioneSpinOff
 */

public class GestioneSpinOff implements IGestioneSpinOff {


	/**
	 * @uml.property  name="spinoffDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	ISpinOffDAO spinoffDao;

	/**
	 * @uml.property  name="stradaDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	IStradaDAO stradaDao;

	/**
	 * @uml.property  name="anagDAO"
	 * @uml.associationEnd  
	 */
	@Autowired
	IAnagrafeDAO anagDAO;

	/**
	 * Default constructor.
	 */
	public GestioneSpinOff() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IGestioneSpinOff#inserisciProtettori()
	 */
	public void inserisciProtettori() {
		// TODO Auto-generated method stub
		try {

			List<ProtettoreDTO> protKo = new ArrayList<ProtettoreDTO>();
			List<ProtettoreDTO> protOk = new ArrayList<ProtettoreDTO>();
			
			ViaDTO via;
			ComuneDTO comune;
			
			boolean protok = false;
			
			Integer idProvincia;
			Long idComune;
			Integer idStrada;
			String cdCap;
			boolean isCapNew = true;
			Integer capProg = null;
			String capNew = null;
			Long capOld = null;
			Long capProgOld = null;
			int j = 0;
			int i=0;
			List<ProtettoreDTO> prots = spinoffDao.getProtettori();

			for (ProtettoreDTO prot : prots) {

				j++;
				protok = false;
				via = spinoffDao.getVia(prot.getCodVia());
				if (via == null) {
					protKo.add(prot);
					System.out.println(i++ + "," + prot.getCodProtettore()
							+ "," + prot.getCodVia()
							+ ":<via vecchia non presente>:");

					continue;
				}

				comune = spinoffDao.getComune(prot.getCodCap(), prot
						.getCodCapProg());
				if (comune == null) {
					protKo.add(prot);
					System.out.println(i++ + "," + prot.getCodProtettore()
							+ "," + prot.getCodCap() + ","
							+ prot.getCodCapProg()
							+ ":<cap vecchio non presente>:");
					continue;
				}

				idProvincia = spinoffDao.getProvincia(comune.getProvincia());
				if (idProvincia == null) {
					System.out.println(i++ + " " + prot.getCodProtettore()
							+ ":<provincia non presente>:"
							+ comune.getProvincia());
					protKo.add(prot);
					continue;
				}
				List<ComuneNewDTO> comuni = spinoffDao.getComune(comune
						.getComune(), idProvincia);
				ComuneNewDTO com = null;
				if (comuni == null || comuni.isEmpty()) {
					System.out.println(i++ + " " + prot.getCodProtettore()
							+ ":<comune non presente>:" + comune.getComune());
					protKo.add(prot);
					continue;
				} else {
					int idComuneInt = Integer.parseInt(Long.toString(comuni
							.get(0).getIdComune()));
					int rows = spinoffDao.getCapByComProv(idProvincia,
							idComuneInt, String.format("%5d", comune.getCap())
									.replaceAll(" ", "0"));
					if (rows == 0) {
						System.out.println(i++
								+ " "
								+ prot.getCodProtettore()
								+ ":<cap non presente>:"
								+ idProvincia
								+ ","
								+ idComuneInt
								+ ","
								+ comune.getComune()
								+ ","
								+ String.format("%5d", comune.getCap())
										.replaceAll(" ", "0") + ","
								+ prot.getCodCapProg());

						// mi faccio ridare il primo nuovo cap disponibile
						capNew = spinoffDao.getCapByComProv(idProvincia,
								idComuneInt);

						// recupero il nuovo progcap
						capProg = spinoffDao.getMaxCapProg(Long
								.parseLong(capNew));
						if (capProg == null) {
							capProg = 1;
						}

						// inserisco il nuovo cap nella vecchia tabelle dei
						// comuni
						int rowCap = spinoffDao.insertCapOld(Long
								.parseLong(capNew), capProg, comune
								.getLocalita(), comune.getComune(), comune
								.getProvincia());

						// aggiorno la tabella prot,
						int prorCap = spinoffDao.updateCapProtettori(capNew,
								capProg, prot.getCodCap().toString(), Integer
										.parseInt(prot.getCodCapProg()
												.toString()));

						capOld = prot.getCodCap();
						capProgOld = prot.getCodCapProg();

						// aggiorno la lista in corso

						for (ProtettoreDTO prot1 : prots) {
							if (prot1.getCodCap().equals(capOld)
									&& prot1.getCodCapProg().equals(capProgOld)) {
								prot1.setCodCap(Long.parseLong(capNew));
								prot1.setCodCapProg((Long.parseLong(capProg
										.toString())));

							}

						}

						// aggiorno il nuovo cap sulla vecchia tabella
						// comuni,capProg=0
						/*
						 * try { spinoffDao.updateCapComune(capNew,
						 * prot.getCodCap() .toString(), Integer.parseInt(prot
						 * .getCodCapProg().toString())); } catch
						 * (DataAccessException e) {
						 * 
						 * System.out.println(capNew + " e " +
						 * " capProg già presenti"); }
						 */

						System.out.println(i++
								+ " "
								+ prot.getCodProtettore()
								+ ":<aggiunto nuovo cap>:"
								+ idProvincia
								+ ","
								+ idComuneInt
								+ ","
								+ comune.getComune()
								+ ","
								+ String.format("%5d", comune.getCap())
										.replaceAll(" ", "0") + ","
								+ prot.getCodCapProg() + "," + "capNew="
								+ capNew + " capProg=" + capProg + " j=" + j);

						com = new ComuneNewDTO();
						com.setCdCap(capNew);
						com.setIdComune(idComuneInt);
						protKo.add(prot);
						isCapNew = true;
						continue;
					}

					else {
						com = comuni.get(0);

					}
				}

				idComune = com.getIdComune();
				cdCap = String.format("%5s", com.getCdCap()).replaceAll(" ",
						"0");
				String dsStrada = via.getDescrizione();
				if (via.getTipoVia() != null) {
					dsStrada = via.getTipoVia() + " " + dsStrada;
				}
				dsStrada = dsStrada.trim();

				idStrada = spinoffDao.getStrada(dsStrada, comune.getLocalita(),
						String.format("%5s", com.getCdCap()).replaceAll(" ",
								"0"), idProvincia, com.getIdComune());
				if (idStrada == null) {

					System.out.println(i++ + " " + prot.getCodProtettore()
							+ ":<via non presente>:" + dsStrada + ",comune:"
							+ comune.getComune() + ",prov="
							+ comune.getProvincia() + "cap=" + comune.getCap()
							+ ",idProd=" + idProvincia + ",idComune="
							+ com.getIdComune());

					protKo.add(prot);

					// inserimento via e salvataggio in idstrada;

					StradaDTO strada = new StradaDTO();
					strada.setCdCap(cdCap);
					strada.setCdComune(Integer
							.parseInt(Long.toString(idComune)));
					strada.setCdProvincia(idProvincia);
					strada.setDsStrada(dsStrada);
					if (comune.getLocalita() != null
							&& !(comune.getLocalita().equalsIgnoreCase(comune
									.getComune()))) {
						strada.setDsLocalita(comune.getLocalita().toUpperCase()
								.replace("LOC.", ""));
					}
					stradaDao.insertStrada(strada);
					idStrada = strada.getIdStrada();

					System.out.println(i++ + " " + prot.getCodProtettore()
							+ ":<inserita via>:" + strada.getDsStrada()
							+ ",comune:" + strada.getCdComune() + ",prov="
							+ strada.getCdProvincia() + "cap="
							+ strada.getCdCap());

					continue;

				}

				prot.setIdStrada(idStrada);
				protOk.add(prot);

			}

			System.out.println(protOk.size());
			System.out.println(protKo.size());

			if (protKo.isEmpty()) {
				for (ProtettoreDTO prot : protOk) {
					AnagraficaDTO anag = insertAnagrafe(prot);
				}
			} else {
				System.out.println("NESSUN PROTETTORE INSERITO");
			}

			// inserimento tessera

			// inserimento storico e rateizzazioni direttamente sotto sql;
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}
	}

	private AnagraficaDTO insertAnagrafe(ProtettoreDTO prot) throws Exception {

		AnagraficaDTO anag = new AnagraficaDTO();
		anag.setCodProtettore(prot.getCodProtettore());
		anag.setNome(prot.getNome());
		anag.setCognome(prot.getCognome());
		if (prot.getSesso() == 1) {
			anag.setSesso("M");
		} else if (prot.getSesso() == 2) {
			anag.setSesso("F");
		} else {
			// LASCIAMO IL SESSO NULL
		}
		anag.setDtNascita(prot.getDataNasc());
		if (prot.getCodFisc() != null
				&& prot.getCodFisc().trim().replaceAll(" ", "").length() > 16) {
			anag.setCdFiscale(prot.getCodFisc().trim().substring(0, 16));
		} else {
			anag.setCdFiscale(prot.getCodFisc().trim().replaceAll(" ", ""));
		}
		anag.setDtInserimento(prot.getDataIns());
		// attivo
		anag.setIdStatoAnagrafica(1);
		if (prot.getCodCarica() == 9 || prot.getCodCarica() == 19
				|| prot.getCodCarica() == 23 || prot.getCodCarica() == 32
				|| prot.getCodCarica() == 7 || prot.getCodCarica() == 17) {
			// deceduto
			anag.setIdStatoAnagrafica(3);
		}

		if (prot.getCodCarica() == 8 || prot.getCodCarica() == 18) {
			// cessato
			anag.setIdStatoAnagrafica(2);
		}

		if (prot.getCodCarica() == 5) {
			// sospeso
			anag.setIdStatoAnagrafica(4);
		}

		anag.setIdFamiglia(prot.getCodFamiglia());
		anag.setNote(prot.getNote());
		anag.setNrCivico(prot.getNumCivico());
		anag.setIdStrada(prot.getIdStrada());

		anag.setDtFazzoletto(prot.getDataFazz());

		anag.setCodProtettore(prot.getCodProtettore());

		spinoffDao.inserisciAnagrafica(anag);
		return anag;
	}
}
