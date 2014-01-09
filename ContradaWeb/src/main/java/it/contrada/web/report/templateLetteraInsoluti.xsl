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
						<!-- HEADER LOGO COMMISSIONE-->
						<div width="100%" style="text-align:center">
							<p align="center" style="margin-right:40px">

								<img width="450" height="45">

									<xsl:attribute name="src">
    								<xsl:value-of select="Immagine" />
    								</xsl:attribute>
								</img>


							</p>
						</div>

						<!-- INZIO CONTENUTO LETTERA-->
						<div style="width: 100%;padding-right:50px">


							<div style="float:right;">
								<p>&#160;</p>
								<p>&#160;</p>
								<p align="left">
									<xsl:value-of select="Intestatario" />
									<br />
									<xsl:value-of select="Indirizzo" />
									<br />
									<xsl:value-of select="Cap" />
									<br />
								</p>
							</div>
						</div>

						<div style="width: 100%;">
							<div style="float:right;">
								<p>&#160;</p>
								<p>&#160;</p>
								<p>
									<p>
										Codice Famiglia :
										<xsl:value-of select="Famiglia" />
									</p>
									Dalle stanza delle contrada,
									<xsl:value-of select="Data" />
								</p>
							</div>
						</div>

						<p align="left">
							Carissimo/a
							<xsl:value-of select="Intestatario" />
							,
						</p>


						<p align="left" style="line-height=1.5">
							da un controllo della nostra contabilità risulta, che alla data
							odierna, per alcuni membri della sua famiglia,
							<xsl:if test="AnnoDA!=AnnoA">
								non sono state ancora da voi pagate le tessere del protettorato
								dall'anno
								<xsl:value-of select="AnnoDA" />
								all'anno
								<xsl:value-of select="AnnoA" />
								.
							</xsl:if>
							<xsl:if test="AnnoDA=AnnoA">
								non sono state ancora da voi pagate le tessere del protettorato
								per l'anno
								<xsl:value-of select="AnnoA" />.
							</xsl:if>

							<br />
							<br />
							Di seguito l'elenco dei nominativi:
							<xsl:for-each select="Tessere/Protettore">
								<br />
								<xsl:value-of select="Nominativo" />
								-
								<xsl:value-of select="Quota" />
							</xsl:for-each>
							<br />
							<br />
							<xsl:if test="recuperoAnno='false'">
								Vi preghiamo di provvedere al pagamento contattando al più presto:
							</xsl:if>
							<xsl:if test="recuperoAnno='true'">
								Per qualsiasi informazione contattare:
							</xsl:if>
							<p>
								Monica Ciacci 3392740249
								<br />
								Paolo Paolini 3477780143
								<br />
								<br />
								<xsl:if test="recuperoAnno='true'">
									<p>
										In allegato il bollettino postale per il pagamento:
										<br />		
										C/C postale: 11918539
										<br />
										Intestato a: NOBILE CONTRADA DEL NICCHIO
										<br />
										Nella causale dovranno essere indicati sia i nominativi che l'anno di riferimento.
										

									</p>
								</xsl:if>
								<xsl:if test="recuperoAnno='false'">
									<p>
										In caso contrario ci vedremo costretti ad eliminare il suo nominativo dall'elenco dei protettori.
								</p>
								</xsl:if>
							</p>

							<div style="width: 100%;margin-top:50px">
								<p align="left">
									Cordiali saluti,
								</p>
							</div>
						</p>
						<div style="width: 100%;margin-top:50px;margin-right:50px">
							<div style="float:right;text-align:center;page-break-after: always;">
								<p>
									Commissione Protettorato
									<br />
									Il Presidente
									<br />
									Monica Ciacci
								</p>
							</div>
						</div>

						<!-- footer contatti commissione -->
						<!--
							<div style="position:absolute;bottom: 0;width: 100%"> <p
							align="center" style="page-break-after: always;"> Commissione
							Protettorato <br /> preotettorato@nobilecontradadelnicchio.it
							</p> </div>
						-->
					</div>
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>