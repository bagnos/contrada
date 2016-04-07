<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/TR/REC-html40" version="1.0">
	<xsl:template match="flusso">

		<LMSG:CBISDDReqLogMsg xmlns:LMSG="urn:CBI:xsd:CBISDDReqLogMsg.00.01.00">
			<!-- informazioni generali sulla richiesta di incasso -->
			<LMSG:GrpHdr>
				<LMSG:MsgId>
					<!-- Max35Text -->
					<xsl:value-of select="msgId" />
				</LMSG:MsgId>
				<LMSG:CreDtTm>
					<!-- [ISO DateTime, es. “2014-01-27T18:19:00+01:00”] -->
					<xsl:value-of select="dtCreazione" />
				</LMSG:CreDtTm>
				<LMSG:NbOfTxs>
					<!-- Numero transazioni incluse nella distinta -->
					<xsl:value-of select="nbOfTxs" />
				</LMSG:NbOfTxs>
				<LMSG:CtrlSum>
					<!-- Totale importi delle transazioni incluse nelle distinta es. 180.51 -->
					<xsl:value-of select="ctrlSum" />
				</LMSG:CtrlSum>
				<LMSG:InitgPty>
					<LMSG:Nm>
						<xsl:value-of select="nomeAzienda" />
					</LMSG:Nm>
					<LMSG:Id>
						<LMSG:OrgId>
							<LMSG:Othr>
								<LMSG:Id>
									<!-- ex sia -->
									<xsl:value-of select="codiceUnicoAzienda" />
								</LMSG:Id>
								<LMSG:Issr>CBI</LMSG:Issr>
							</LMSG:Othr>
						</LMSG:OrgId>
					</LMSG:Id>
				</LMSG:InitgPty>
			</LMSG:GrpHdr>

			<!-- Informazioni di accredito sottodistinta -->
			<LMSG:PmtInf>
				<LMSG:PmtInfId>
					<!-- [Max35Text] -->
					<xsl:value-of select="idSottodistinta" />
				</LMSG:PmtInfId>
				<LMSG:PmtMtd>DD</LMSG:PmtMtd>
				<LMSG:PmtTpInf>
					<LMSG:SvcLvl>
						<LMSG:Cd>SEPA</LMSG:Cd>
					</LMSG:SvcLvl>
					<LMSG:LclInstrm>
						<LMSG:Cd>CORE</LMSG:Cd>
					</LMSG:LclInstrm>
					<LMSG:SeqTp>RCUR</LMSG:SeqTp>
				</LMSG:PmtTpInf>
				<LMSG:ReqdColltnDt>
					<xsl:value-of select="dtScadenza" />
				</LMSG:ReqdColltnDt>
				<LMSG:Cdtr>
					<LMSG:Nm>
						<xsl:value-of select="nomeAzienda" />
					</LMSG:Nm>
				</LMSG:Cdtr>
				<LMSG:CdtrAcct>
					<LMSG:Id>
						<LMSG:IBAN>
							<xsl:value-of select="ibanAccredito" />
						</LMSG:IBAN>
					</LMSG:Id>
				</LMSG:CdtrAcct>
				<LMSG:CdtrAgt>
					<LMSG:FinInstnId>
						<LMSG:ClrSysMmbId>
							<LMSG:MmbId>
								<xsl:value-of select="abiBancaAccredito" />
							</LMSG:MmbId>
						</LMSG:ClrSysMmbId>
					</LMSG:FinInstnId>
				</LMSG:CdtrAgt>
				<LMSG:ChrgBr>SLEV</LMSG:ChrgBr>
				<LMSG:CdtrSchmeId>
					<LMSG:Nm>
						<xsl:value-of select="nomeAzienda" />
					</LMSG:Nm>					
					<LMSG:Id>
						<LMSG:PrvtId>
							<LMSG:Othr>
								<!-- soggetto interno SEPA: es IT320010000003053920165 -->
								<LMSG:Id>
									<xsl:value-of select="identificativoCreditore" />
								</LMSG:Id>
							</LMSG:Othr>
						</LMSG:PrvtId>
					</LMSG:Id>					
				</LMSG:CdtrSchmeId>

				<!-- DISPOSIZONE INCASSO -->
				<xsl:for-each select="elenco/disposizione">
					<LMSG:DrctDbtTxInf>
						<LMSG:PmtId>
							<LMSG:InstrId><xsl:value-of select="progressivoDisposizione" /></LMSG:InstrId>
							<!-- fondamentale per la riconciliazione -->
							<LMSG:EndToEndId><xsl:value-of select="idRateizzazione" /></LMSG:EndToEndId>
						</LMSG:PmtId>
						<!-- es 90.80 -->
						<LMSG:InstdAmt Ccy="EUR"><xsl:value-of select="importoDispozione" /></LMSG:InstdAmt>
						<LMSG:DrctDbtTx>
							<LMSG:MndtRltdInf>
								<LMSG:MndtId><xsl:value-of select="idRid" /></LMSG:MndtId>
								<LMSG:DtOfSgntr>2013-31-12</LMSG:DtOfSgntr>								
							</LMSG:MndtRltdInf>
						</LMSG:DrctDbtTx>
						<LMSG:Dbtr>
							<LMSG:Nm><xsl:value-of select="intestatarioConto" /></LMSG:Nm>							
						</LMSG:Dbtr>
						<LMSG:DbtrAcct>
							<LMSG:Id>
								<LMSG:IBAN><xsl:value-of select="iban" /></LMSG:IBAN>
							</LMSG:Id>
						</LMSG:DbtrAcct>
						<LMSG:Purp>
							<LMSG:Cd>GDSV</LMSG:Cd>
						</LMSG:Purp>
						<LMSG:RmtInf>
							<LMSG:Ustrd><xsl:value-of select="causaleDisposizione" /></LMSG:Ustrd>
						</LMSG:RmtInf>

					</LMSG:DrctDbtTxInf>
				</xsl:for-each>

			</LMSG:PmtInf>

		</LMSG:CBISDDReqLogMsg>

	</xsl:template>
</xsl:stylesheet>