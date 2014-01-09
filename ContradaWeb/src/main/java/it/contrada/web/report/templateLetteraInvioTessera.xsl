<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/TR/REC-html40" version="1.0">
	<xsl:template match="/">
		<html>
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			</head>
		
			<body>


				<xsl:for-each select="Elenco/Anagrafica">

					<div
						style="font-family:calibri;font-size:16px;width:100%;">
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
									Dalle stanza delle contrada, <xsl:value-of select="Data" />
								</p>
							</div>
						</div>

						<p align="left">
							Carissimo/a
							<xsl:value-of select="Intestatario" />
							,
						</p>


						<p align="left" style="line-height=1.5">
							la Commissione Protettorato Ti invia in allegato la tessere
							dell'anno in corso, per te e
							la tua famiglia.
							<br />
							Con l'occasione Ti rivolge l'invito, se lo desideri, o se
							gi&#224; tu
							non lo fossi, a divenire Grande Protettore della
							Nostra Contrada. <br/> Per il pagamento tramite rid bancario e per altre informazioni puoi visitare l'indirizzo www.nobilecontradadelnicchio.it, sezione Protettorato.			
							<br/>
							Per qualsiasi chiarimento e/o variazione puoi inviare sia una
							mail
							a protettorato@nobilecontradadelnicchio.it
							che contattare i
							seguenti
							nominativi:
							<p>
							Monica Ciacci 3392740249
							<br />
							Paolo Paolini 3477780143
							</p>
						</p>

						<div style="width: 100%;margin-top:50px">
							<p align="left">
								Cordiali saluti,
	</p>
						</div>
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