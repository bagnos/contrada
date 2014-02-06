<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/TR/REC-html40" version="1.0">
	<xsl:template match="/">
		<html>
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
			</head>

			<body>


				<xsl:for-each select="Elenco/Anagrafica">

					<div style="font-family:calibri;font-size:16px;width:100%;">
						<!-- HEADER LOGO COMMISSIONE -->
						<div width="100%" style="text-align:center">
							<p align="center" style="margin-right:40px">

								<img width="450" height="45">

									<xsl:attribute name="src">
    								<xsl:value-of select="Immagine" />
    								</xsl:attribute>
								</img>


							</p>
						</div>




						<p align="left">
							<xsl:if test="Sesso='F'">
								Cara Protettrice
							</xsl:if>
							<xsl:if test="Sesso='M' or Sesso=''">
								Caro Protettore
							</xsl:if>
							<xsl:value-of select="Intestatario" />
							,
						</p>

						<!-- CORPO LETTERA -->
						<p align="left" style="line-height=1.5">
							la Commissione Protettorato della Nobile Contrada del Nicchio si
							rivolge a te, all’inizio di questo nuovo anno, per condividere
							assieme la passione, le speranze e l’amore per la nostra
							meravigliosa Contrada.
							<br />
							<br />
							In questa ottica e nella speranza che, finalmente, l’anno in
							corso sarà ricco di soddisfazioni per i nostri colori, siamo a
							richiedere il tuo impegno finanziario che, ovviamente, ti sarà
							richiesto di onorare solo nel caso in cui conquisteremo
							l’agognata Vittoria.
							<br />
							<br />
							Alla fine di questa lettera troverai una scheda che potrai
							comodamente staccare lungo la linea tratteggiata, contenente i
							tuoi dati anagrafici e quelli dei Protettori collegati al tuo
							cod. famiglia, che ti preghiamo di controllare ed, eventualmente,
							completare e/o correggere; la scheda contiene anche lo spazio per
							indicare il contributo che deciderai di donare e le modalità di
							corresponsione.
							<br />
							<br />
							Assieme a questa lettera, troverai anche la busta preaffrancata,
							completa dell’indirizzo della Contrada, dove potrai inserire la
							scheda e rispedircela entro la fine maggio.
							Se preferisci, potrai
							anche restituircela via e-mail all’indirizzo
							protettorato@nobilecontradadelnicchio.it.
							<br />
							Per qualsiasi informazione contattare:
							<p>
								Monica Ciacci 3392740249
								<br />
								Paolo Paolini 3477780143
							</p>
							<br />
							Ti ringraziamo per il tempo che ci hai regalato e, certi del tuo
							generoso contributo, ti abbracciamo con affetto.
						</p>
						<div style="width: 100%;margin-top:20px;margin-right:20px">
							<div style="float:right;text-align:center;">
								<p>
									La Commissione Protettorato
								</p>
							</div>
						</div>
						<p style="width: 100%;margin-top:20px;margin-bottom:20px">
							- - - - - - - - - - - - - - - - - - - - - - - - - - - -
							- - - -
							-
							- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
							- - -
							-
							- - - - - - -
						</p>
						<p>
							Sottoscrizione a Vittoria : €________ Modalità: [] unica
							soluzione []
							rateizzato (da 1 a 12 mesi)
						</p>
						<!-- ELENCO PROTETTORI -->
						<p align="left">
							<label>Codice Famiglia: </label>
							<xsl:value-of select="Famiglia" />
							<br />
							<br />
							<label>Intestatario: </label>
							<xsl:value-of select="Intestatario" />
							<br />
							<label>Indirizzo: </label>
							<xsl:value-of select="Indirizzo" />
							,
							<xsl:value-of select="Cap" />
							<br />
							<label>Numero tel./Cell: </label>
							<xsl:value-of select="Telefono" />
							<br />
							<label>E-mail: </label>
							<xsl:value-of select="Mail" />

							<p>
								<label>Protettori:</label>
							</p>
							<p>
								<table>
									<tr>
										<th></th>
										<th>Email</th>
										<th>Cell.</th>
										<th>Professione</th>
										<th>Azienda</th>
										<th>Titolo Studio</th>
									</tr>

									<xsl:for-each select="Anagrafiche/Protettore">
										<table>
											<tr>
												<td>
													<xsl:value-of select="Nominativo" />
												</td>
												<td>
												</td>
												<td>
												</td>
												<td>
												</td>
												<td>
												</td>
												<td>
												</td>
											</tr>
										</table>


										<br />
									</xsl:for-each>
								</table>

							</p>

						</p>
						<div style="float:right;text-align:center;page-break-after: always;">
						</div>

					</div>
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>