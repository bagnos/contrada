<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body style="font-size: 12px;font-family: Arial, Helvetica, sans-serif;">
				<p style="font-size:24px;font-weight:bold;">
					Nobile Contrada del Nicchio - Commissione Protettorato
				
					<span style="font-size:18px;font-weight:normal;">
						Distinta
						<xsl:value-of select="distinta/nrDistinta" />
						per l'anno
						<xsl:value-of select="distinta/nrAnno" />
						del
						<xsl:value-of select="distinta/data" />
					</span>
				</p>
				<p>
				<span style="font-size:18px;font-weight:normal;">
				Quota incassata: <xsl:value-of select="distinta/quotaTotale" />
				</span>
				<span style="font-size:16px;font-weight:normal;margin-left:14px;">
				Utente: <xsl:value-of select="distinta/utente" />
				</span>
				</p>
				
				<table cellpadding="2px"
					style="margin-top:10px;padding: 0px;border:none;font-size:12px;text-align:left;width:100%">

					<tr style="font-weight:bold">
						<td>Cod.</td>
						<td>Cognome</td>
						<td>Nome</td>
						<td>Tessera</td>
						<td>Pagamento</td>
						<td>Quota</td>
						<td>Quota Incassata</td>
					</tr>
					<xsl:for-each select="distinta/tessere/tessera">
						<tr>
							<td><xsl:value-of select="idAnag" /></td>
							<td><xsl:value-of select="cognome" /></td>
							<td><xsl:value-of select="nome" /></td>
							<td><xsl:value-of select="dsTipoTessera" /></td>
							<td><xsl:value-of select="dsIncasso" /></td>
							<td><xsl:value-of select="quota" /></td>
							<td><xsl:value-of select="quotaIncassata" /></td>
						</tr>
					</xsl:for-each>
					<tr style="font-weight:bold">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>Totale
						</td>
						<td>
							<xsl:value-of select="distinta/quotaPrivistaTotale" />
						</td>
						<td>
							<xsl:value-of select="distinta/quotaTotale" />
						</td>
					</tr>
				</table>
				

			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>