package de.deg.eler.ft.vp.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.deg.eler.ft.vp.services.DslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalDslParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_STRING", "RULE_SL_COMMENT", "RULE_ID", "RULE_ML_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'VWKPKonfiguration.'", "'.Aktion.'", "' = '", "'Automatisch'", "'Manuell'", "','", "'PruefungLangtext.'", "'PruefungKurzbezeichnung.'", "'PruefungKlassenname.'", "'PruefungAktion.'", "'.'", "'PruefungWirkung.'", "'DvAntragsArt.'", "'CodesAlle = '", "'VERHINDERT_AKTION'", "'OHNE'", "'WARNUNG'", "'AUSZANTRAG'", "'ERWANTRAG'", "'NEUANTRAG'", "'VERLANTRAG'", "'BerechnenUndPruefen'", "'DokumentBearbeitungBeginnen'", "'Zurueckziehen'", "'NachberechnungStornieren'", "'ZurueckziehenZuruecknehmen'", "'AntragFreigeben'", "'AntragFreigabeZurueck'", "'DokumentBearbeitungBeenden'", "'AntragBewilligen'", "'AntragAblehnen'", "'AntragZahlungAnweisen'", "'AntragEntscheidungZurueck'", "'AntragNeuBearbeiten'", "'AntragWidersprechen'", "'AntragWiderspruchAblZurueck'", "'AntragWiderspruchZurueck'", "'AntragWiderspruchAblehnen'", "'AntragWiderspruchZulassen'", "'AntragWiderspruchZulZurueck'", "'AntragWiderspruchStattgeben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusAusRechenschritt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusBagatellbetragTeilmassnahmen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PA4AugenPrinzip'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAblehnungsgruendeVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenArt17Abs1'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenBezugVerstoesseVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngaben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngabenStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalschGemachteUnregelmaessigkeit'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbtretungenInZahlungVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzuegeErhoehung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaft'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngaben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngabenBeendetOhneAenderungsblatt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAltverpflichtungUeberschritten'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenAUM'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenErsterfassungBeendetAJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitungStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageTierhaltung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilBluehflaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreide'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreideAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGruenland'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilKkLeguminosenAL5Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosenAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingang'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangNichtNachAusschlussTermin'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsflaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsteller'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstellerKeineJuristischePerson'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstermin'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsterminNichtVerfristet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJBewilligt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJNichtAbgelehnt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragZurueckziehen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenInklusiveLeguminosen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtGroesserAls100ProHa'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtKleinerAls30'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappe'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappeAktuellUndDurchgefuehrt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlatt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaUZWAktuellUndBeendet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweis'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweisBeendet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusbringungstechnik'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuellt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusschluss'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragAuszahlung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungBeruecksichtigungHalbeZahlung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2002'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2007'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungUeberGesamteLaufzeit'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragNeuAntrag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragRueckforderung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindungStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragteFlaecheKleiner80Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtGLGleichGesamtGL'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtMindestens5ProzAckerflaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner10ProzAckerflaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner2ha'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner5ProzAckerflaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzAusHITUnterBeruecksichtigungGruenland'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_2'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_3'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHIT'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABescheidInAktuellerBerechnung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABestaetigungsVermerk'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABewilligterNAImEAJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuell'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuellUndBeendet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADatumEingangGroesserAntrag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzBewilligtVJUndBeantragtGroesserBagatellbetrag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaecheWiederholt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCC'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCV'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCVImVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGL'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGLSchwellwert'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangVerpflichtungsflaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEntscheidungenBescheidart'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErstantragsjahr'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10bzw2haOdGroesser50ProzentVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10odGroesser50ProzentVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50Proz'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50ProzOder2Ha'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungLE50ProzentVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungsflaecheVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAExtensiveBewirtschaftungGLGleichGesamtGL'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngaben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngabenStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs5'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6inAnderemFP'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt18Abs3'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschGemachteAngaben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerderfaehigGroesserNull'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerdergebietGroesserGleich3Hektar'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM141Mindestens5ProzentAFAusEaj'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM14Mindestens5ProzentAFAusEaj'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheStreuobstwiesen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGebuehrenrechnung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung30Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung50Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtrueckforderung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtsanktionierung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuGross'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuKlein'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrfImAA'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrobFahrlaessigeGemachteAngaben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGuellemenge'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAHofuebergabe'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerBestaetigungVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerVereinbarungVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKapitalbeteiligungOeffentlHandGroesser25Proz'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinAntragVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinBescheidInAktuellerBerechnung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinDungAufnahmeOderAbgabe'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhandenTm'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineParalleBeantragungM5UndM6'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinePheromonGemeinschaft'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAktiengesellschaft'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAnstaltDesOeffentlRechts'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformGmbH'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformJuristischePersonOeffentlRecht'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformKoerperschaftDesOeffentlichenRechts'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformOeffentlRechtlStiftung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformPrivatRechtlStiftung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonOeffentlRecht'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonPrivatRecht'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeNatuerlichePerson'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRelevantenEntscheidungenOffen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformAnerkannteWeidegemeinschaft'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformEinzelantragstellerMeka'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformPheromongemeinschaft'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformWaldgemeinschaft'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinFC104anBindungVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinGLAusErzeugungGenommenNutzung592'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinKlaerschlammAusgebracht'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB2'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKombinierteGLAntraege'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollkostenzuschussMitND2Teilmassnahme'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollprotokoll'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollvertrag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung20Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung30Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentB1610'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALaborbeanstandungenLiegenNichtVor'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALandwirt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogenStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaschinelleBerechnungVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusHIT'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF2010'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusHIT'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestFlaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestumfangWinterbegruenung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFFAusHIT'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3GveProHaLNF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaGL'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusHIT'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_5RgvProHaGL'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_6GVEProHaFF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMittelverwaltung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANachOeffnenBerechnet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtAlleChecksWurdenBearbeitet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtZuWenigAckerfutterAngebaut'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANurNachberechnungStornierbar'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelBewilligterNABeiEajGleichAj'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAAEntschieden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragFP773'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragZuFP774'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendig'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendigLZJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungFlaechenmappeVj'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungVj'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleich'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichBeendetVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichDurchgefuehrtVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaenger'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaengerAlsEinzelunternehmer'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeit'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitEinJahr'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitVNS'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVJVokAJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVok'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokCc'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungenOderNullzahlung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASGAbgleich'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstFeststellungenAbsichtlUnregelmaessigkeiten'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASperrvermerkNichtVergeben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStammdatenAktuell'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStichtagHelper'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATeilmassnahmeND1UndND2Beantragt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbesatzGesamt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFundGveProHaLF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFZusNCs'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUmwandlungALInGL'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenAusserhalbDerEU'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenOhneSchafeZiegenMitVertragVereinbarung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenssitzInBw'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschiedBerechneteUndManuelleVerpflFlFJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschreitungTierbestzDurchFeststellung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNC'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNcVNS'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheAckerfutter'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentimVJVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18Beanstandungen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18BeanstandungenVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJFuerMindEineBindung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsNrFuerAlleFlaechenVergeben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsnummer'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVeterinaerBestaetigungVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKCCBeendet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVokNichtVerweigert'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKPEnthaeltBenutzerdefPruefkonf'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKReferenzflaechenAbgleich'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragsteller'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragstellerStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWeidetagebuchImPebVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWiderspruchInVorherigerBerechnung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFK'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PASperrvermerkNichtVergeben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet'"
    };
    public static final int RULE_ID=7;
    public static final int T__159=159;
    public static final int T__158=158;
    public static final int T__259=259;
    public static final int T__258=258;
    public static final int T__257=257;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__262=262;
    public static final int T__263=263;
    public static final int T__160=160;
    public static final int T__260=260;
    public static final int T__261=261;
    public static final int T__266=266;
    public static final int T__267=267;
    public static final int T__264=264;
    public static final int T__265=265;
    public static final int T__167=167;
    public static final int EOF=-1;
    public static final int T__168=168;
    public static final int T__165=165;
    public static final int T__166=166;
    public static final int T__300=300;
    public static final int T__163=163;
    public static final int T__164=164;
    public static final int T__161=161;
    public static final int T__162=162;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__148=148;
    public static final int T__90=90;
    public static final int T__147=147;
    public static final int T__149=149;
    public static final int T__247=247;
    public static final int T__246=246;
    public static final int T__249=249;
    public static final int T__248=248;
    public static final int T__250=250;
    public static final int T__251=251;
    public static final int T__252=252;
    public static final int T__253=253;
    public static final int T__254=254;
    public static final int T__255=255;
    public static final int T__256=256;
    public static final int T__154=154;
    public static final int T__155=155;
    public static final int T__156=156;
    public static final int T__99=99;
    public static final int T__157=157;
    public static final int T__98=98;
    public static final int T__150=150;
    public static final int T__97=97;
    public static final int T__151=151;
    public static final int T__96=96;
    public static final int T__152=152;
    public static final int T__95=95;
    public static final int T__153=153;
    public static final int T__318=318;
    public static final int T__139=139;
    public static final int T__319=319;
    public static final int T__138=138;
    public static final int T__316=316;
    public static final int T__137=137;
    public static final int T__317=317;
    public static final int T__136=136;
    public static final int T__314=314;
    public static final int T__80=80;
    public static final int T__315=315;
    public static final int T__81=81;
    public static final int T__312=312;
    public static final int T__82=82;
    public static final int T__313=313;
    public static final int T__83=83;
    public static final int T__279=279;
    public static final int T__288=288;
    public static final int T__289=289;
    public static final int T__286=286;
    public static final int T__287=287;
    public static final int T__284=284;
    public static final int T__285=285;
    public static final int T__282=282;
    public static final int T__283=283;
    public static final int T__280=280;
    public static final int T__85=85;
    public static final int T__141=141;
    public static final int T__321=321;
    public static final int T__281=281;
    public static final int T__84=84;
    public static final int T__142=142;
    public static final int T__320=320;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int T__140=140;
    public static final int T__89=89;
    public static final int T__145=145;
    public static final int T__88=88;
    public static final int T__146=146;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__143=143;
    public static final int T__144=144;
    public static final int T__305=305;
    public static final int T__126=126;
    public static final int T__306=306;
    public static final int T__125=125;
    public static final int T__307=307;
    public static final int T__128=128;
    public static final int RULE_STRING=5;
    public static final int T__308=308;
    public static final int T__127=127;
    public static final int T__301=301;
    public static final int T__71=71;
    public static final int T__302=302;
    public static final int T__72=72;
    public static final int T__129=129;
    public static final int T__303=303;
    public static final int T__304=304;
    public static final int T__70=70;
    public static final int T__309=309;
    public static final int T__269=269;
    public static final int T__268=268;
    public static final int T__275=275;
    public static final int T__276=276;
    public static final int T__277=277;
    public static final int T__278=278;
    public static final int T__271=271;
    public static final int T__272=272;
    public static final int T__273=273;
    public static final int T__274=274;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int T__311=311;
    public static final int T__74=74;
    public static final int T__130=130;
    public static final int T__310=310;
    public static final int T__270=270;
    public static final int T__73=73;
    public static final int T__131=131;
    public static final int T__132=132;
    public static final int T__79=79;
    public static final int T__133=133;
    public static final int T__78=78;
    public static final int T__134=134;
    public static final int T__77=77;
    public static final int T__135=135;
    public static final int T__215=215;
    public static final int T__216=216;
    public static final int T__213=213;
    public static final int T__214=214;
    public static final int T__219=219;
    public static final int T__217=217;
    public static final int T__218=218;
    public static final int T__118=118;
    public static final int T__119=119;
    public static final int T__116=116;
    public static final int T__117=117;
    public static final int T__114=114;
    public static final int T__115=115;
    public static final int T__124=124;
    public static final int T__123=123;
    public static final int T__122=122;
    public static final int T__121=121;
    public static final int T__120=120;
    public static final int T__223=223;
    public static final int T__222=222;
    public static final int T__221=221;
    public static final int T__220=220;
    public static final int T__202=202;
    public static final int T__203=203;
    public static final int T__204=204;
    public static final int T__205=205;
    public static final int T__206=206;
    public static final int T__207=207;
    public static final int T__208=208;
    public static final int T__209=209;
    public static final int T__107=107;
    public static final int T__108=108;
    public static final int T__109=109;
    public static final int T__103=103;
    public static final int T__104=104;
    public static final int T__105=105;
    public static final int T__106=106;
    public static final int T__111=111;
    public static final int T__110=110;
    public static final int T__113=113;
    public static final int T__112=112;
    public static final int T__210=210;
    public static final int T__212=212;
    public static final int T__211=211;
    public static final int T__239=239;
    public static final int T__237=237;
    public static final int T__238=238;
    public static final int T__235=235;
    public static final int T__236=236;
    public static final int T__102=102;
    public static final int T__101=101;
    public static final int T__100=100;
    public static final int T__245=245;
    public static final int RULE_SL_COMMENT=6;
    public static final int T__244=244;
    public static final int T__243=243;
    public static final int T__242=242;
    public static final int T__241=241;
    public static final int T__240=240;
    public static final int T__228=228;
    public static final int T__229=229;
    public static final int T__224=224;
    public static final int T__225=225;
    public static final int T__226=226;
    public static final int T__227=227;
    public static final int T__232=232;
    public static final int T__231=231;
    public static final int T__234=234;
    public static final int T__233=233;
    public static final int T__230=230;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__200=200;
    public static final int T__201=201;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__61=61;
    public static final int T__60=60;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__199=199;
    public static final int T__57=57;
    public static final int T__198=198;
    public static final int T__58=58;
    public static final int T__292=292;
    public static final int T__197=197;
    public static final int T__51=51;
    public static final int T__291=291;
    public static final int T__196=196;
    public static final int T__52=52;
    public static final int T__290=290;
    public static final int T__195=195;
    public static final int T__53=53;
    public static final int T__194=194;
    public static final int T__54=54;
    public static final int T__296=296;
    public static final int T__193=193;
    public static final int T__295=295;
    public static final int T__192=192;
    public static final int T__294=294;
    public static final int T__191=191;
    public static final int T__293=293;
    public static final int T__190=190;
    public static final int T__59=59;
    public static final int T__299=299;
    public static final int T__298=298;
    public static final int T__297=297;
    public static final int RULE_INT=4;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int T__184=184;
    public static final int T__43=43;
    public static final int T__183=183;
    public static final int T__40=40;
    public static final int T__186=186;
    public static final int T__41=41;
    public static final int T__185=185;
    public static final int T__46=46;
    public static final int T__188=188;
    public static final int T__47=47;
    public static final int T__187=187;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__189=189;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__180=180;
    public static final int T__182=182;
    public static final int T__181=181;
    public static final int T__175=175;
    public static final int T__30=30;
    public static final int T__174=174;
    public static final int T__31=31;
    public static final int T__173=173;
    public static final int T__32=32;
    public static final int T__172=172;
    public static final int T__33=33;
    public static final int T__179=179;
    public static final int T__34=34;
    public static final int T__178=178;
    public static final int T__35=35;
    public static final int T__177=177;
    public static final int T__36=36;
    public static final int T__176=176;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__171=171;
    public static final int T__170=170;
    public static final int RULE_WS=9;
    public static final int T__169=169;

    // delegates
    // delegators


        public InternalDslParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalDslParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalDslParser.tokenNames; }
    public String getGrammarFileName() { return "../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g"; }



     	private DslGrammarAccess grammarAccess;
     	
        public InternalDslParser(TokenStream input, DslGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Konfiguration";	
       	}
       	
       	@Override
       	protected DslGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleKonfiguration"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:67:1: entryRuleKonfiguration returns [EObject current=null] : iv_ruleKonfiguration= ruleKonfiguration EOF ;
    public final EObject entryRuleKonfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKonfiguration = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:68:2: (iv_ruleKonfiguration= ruleKonfiguration EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:69:2: iv_ruleKonfiguration= ruleKonfiguration EOF
            {
             newCompositeNode(grammarAccess.getKonfigurationRule()); 
            pushFollow(FOLLOW_ruleKonfiguration_in_entryRuleKonfiguration75);
            iv_ruleKonfiguration=ruleKonfiguration();

            state._fsp--;

             current =iv_ruleKonfiguration; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleKonfiguration85); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKonfiguration"


    // $ANTLR start "ruleKonfiguration"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:76:1: ruleKonfiguration returns [EObject current=null] : ( ( (lv_usedids_0_0= ruleUsedIDs ) ) ( (lv_spezantragszuweisung_1_0= ruleSPEZ_ANTRAGSZUWEISUNG ) )* ( (lv_antragszuweisung_2_0= ruleZuweisung ) )* ( ( (lv_vwkpkonfigurationfueraktion_3_0= rulevwkpaktionkonfiguraktion ) ) | ( (lv_pruefungsaktion_4_0= rulePRUEFUNGSAKTION ) ) | ( (lv_pruefungsklassenname_5_0= rulePRUEFUNGSKLASSENNAME ) ) | ( (lv_pruefungskurzbezeichnung_6_0= rulePRUEFUNGSKURZTEXT ) ) | ( (lv_pruefungslangtext_7_0= rulePRUEFUNGSLANGTEXT ) ) | ( (lv_pruefungswirkung_8_0= rulePRUEFUNGSWIRKUNG ) ) )* ) ;
    public final EObject ruleKonfiguration() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_usedids_0_0 = null;

        AntlrDatatypeRuleToken lv_spezantragszuweisung_1_0 = null;

        AntlrDatatypeRuleToken lv_antragszuweisung_2_0 = null;

        AntlrDatatypeRuleToken lv_vwkpkonfigurationfueraktion_3_0 = null;

        AntlrDatatypeRuleToken lv_pruefungsaktion_4_0 = null;

        AntlrDatatypeRuleToken lv_pruefungsklassenname_5_0 = null;

        AntlrDatatypeRuleToken lv_pruefungskurzbezeichnung_6_0 = null;

        AntlrDatatypeRuleToken lv_pruefungslangtext_7_0 = null;

        AntlrDatatypeRuleToken lv_pruefungswirkung_8_0 = null;


         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:79:28: ( ( ( (lv_usedids_0_0= ruleUsedIDs ) ) ( (lv_spezantragszuweisung_1_0= ruleSPEZ_ANTRAGSZUWEISUNG ) )* ( (lv_antragszuweisung_2_0= ruleZuweisung ) )* ( ( (lv_vwkpkonfigurationfueraktion_3_0= rulevwkpaktionkonfiguraktion ) ) | ( (lv_pruefungsaktion_4_0= rulePRUEFUNGSAKTION ) ) | ( (lv_pruefungsklassenname_5_0= rulePRUEFUNGSKLASSENNAME ) ) | ( (lv_pruefungskurzbezeichnung_6_0= rulePRUEFUNGSKURZTEXT ) ) | ( (lv_pruefungslangtext_7_0= rulePRUEFUNGSLANGTEXT ) ) | ( (lv_pruefungswirkung_8_0= rulePRUEFUNGSWIRKUNG ) ) )* ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:80:1: ( ( (lv_usedids_0_0= ruleUsedIDs ) ) ( (lv_spezantragszuweisung_1_0= ruleSPEZ_ANTRAGSZUWEISUNG ) )* ( (lv_antragszuweisung_2_0= ruleZuweisung ) )* ( ( (lv_vwkpkonfigurationfueraktion_3_0= rulevwkpaktionkonfiguraktion ) ) | ( (lv_pruefungsaktion_4_0= rulePRUEFUNGSAKTION ) ) | ( (lv_pruefungsklassenname_5_0= rulePRUEFUNGSKLASSENNAME ) ) | ( (lv_pruefungskurzbezeichnung_6_0= rulePRUEFUNGSKURZTEXT ) ) | ( (lv_pruefungslangtext_7_0= rulePRUEFUNGSLANGTEXT ) ) | ( (lv_pruefungswirkung_8_0= rulePRUEFUNGSWIRKUNG ) ) )* )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:80:1: ( ( (lv_usedids_0_0= ruleUsedIDs ) ) ( (lv_spezantragszuweisung_1_0= ruleSPEZ_ANTRAGSZUWEISUNG ) )* ( (lv_antragszuweisung_2_0= ruleZuweisung ) )* ( ( (lv_vwkpkonfigurationfueraktion_3_0= rulevwkpaktionkonfiguraktion ) ) | ( (lv_pruefungsaktion_4_0= rulePRUEFUNGSAKTION ) ) | ( (lv_pruefungsklassenname_5_0= rulePRUEFUNGSKLASSENNAME ) ) | ( (lv_pruefungskurzbezeichnung_6_0= rulePRUEFUNGSKURZTEXT ) ) | ( (lv_pruefungslangtext_7_0= rulePRUEFUNGSLANGTEXT ) ) | ( (lv_pruefungswirkung_8_0= rulePRUEFUNGSWIRKUNG ) ) )* )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:80:2: ( (lv_usedids_0_0= ruleUsedIDs ) ) ( (lv_spezantragszuweisung_1_0= ruleSPEZ_ANTRAGSZUWEISUNG ) )* ( (lv_antragszuweisung_2_0= ruleZuweisung ) )* ( ( (lv_vwkpkonfigurationfueraktion_3_0= rulevwkpaktionkonfiguraktion ) ) | ( (lv_pruefungsaktion_4_0= rulePRUEFUNGSAKTION ) ) | ( (lv_pruefungsklassenname_5_0= rulePRUEFUNGSKLASSENNAME ) ) | ( (lv_pruefungskurzbezeichnung_6_0= rulePRUEFUNGSKURZTEXT ) ) | ( (lv_pruefungslangtext_7_0= rulePRUEFUNGSLANGTEXT ) ) | ( (lv_pruefungswirkung_8_0= rulePRUEFUNGSWIRKUNG ) ) )*
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:80:2: ( (lv_usedids_0_0= ruleUsedIDs ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:81:1: (lv_usedids_0_0= ruleUsedIDs )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:81:1: (lv_usedids_0_0= ruleUsedIDs )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:82:3: lv_usedids_0_0= ruleUsedIDs
            {
             
            	        newCompositeNode(grammarAccess.getKonfigurationAccess().getUsedidsUsedIDsParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleUsedIDs_in_ruleKonfiguration131);
            lv_usedids_0_0=ruleUsedIDs();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKonfigurationRule());
            	        }
                   		add(
                   			current, 
                   			"usedids",
                    		lv_usedids_0_0, 
                    		"UsedIDs");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:98:2: ( (lv_spezantragszuweisung_1_0= ruleSPEZ_ANTRAGSZUWEISUNG ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_INT) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:99:1: (lv_spezantragszuweisung_1_0= ruleSPEZ_ANTRAGSZUWEISUNG )
            	    {
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:99:1: (lv_spezantragszuweisung_1_0= ruleSPEZ_ANTRAGSZUWEISUNG )
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:100:3: lv_spezantragszuweisung_1_0= ruleSPEZ_ANTRAGSZUWEISUNG
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKonfigurationAccess().getSpezantragszuweisungSPEZ_ANTRAGSZUWEISUNGParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleSPEZ_ANTRAGSZUWEISUNG_in_ruleKonfiguration152);
            	    lv_spezantragszuweisung_1_0=ruleSPEZ_ANTRAGSZUWEISUNG();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getKonfigurationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"spezantragszuweisung",
            	            		lv_spezantragszuweisung_1_0, 
            	            		"SPEZ_ANTRAGSZUWEISUNG");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:116:3: ( (lv_antragszuweisung_2_0= ruleZuweisung ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==23) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:117:1: (lv_antragszuweisung_2_0= ruleZuweisung )
            	    {
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:117:1: (lv_antragszuweisung_2_0= ruleZuweisung )
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:118:3: lv_antragszuweisung_2_0= ruleZuweisung
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKonfigurationAccess().getAntragszuweisungZuweisungParserRuleCall_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleZuweisung_in_ruleKonfiguration174);
            	    lv_antragszuweisung_2_0=ruleZuweisung();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getKonfigurationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"antragszuweisung",
            	            		lv_antragszuweisung_2_0, 
            	            		"Zuweisung");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:134:3: ( ( (lv_vwkpkonfigurationfueraktion_3_0= rulevwkpaktionkonfiguraktion ) ) | ( (lv_pruefungsaktion_4_0= rulePRUEFUNGSAKTION ) ) | ( (lv_pruefungsklassenname_5_0= rulePRUEFUNGSKLASSENNAME ) ) | ( (lv_pruefungskurzbezeichnung_6_0= rulePRUEFUNGSKURZTEXT ) ) | ( (lv_pruefungslangtext_7_0= rulePRUEFUNGSLANGTEXT ) ) | ( (lv_pruefungswirkung_8_0= rulePRUEFUNGSWIRKUNG ) ) )*
            loop3:
            do {
                int alt3=7;
                switch ( input.LA(1) ) {
                case 11:
                    {
                    alt3=1;
                    }
                    break;
                case 20:
                    {
                    alt3=2;
                    }
                    break;
                case 19:
                    {
                    alt3=3;
                    }
                    break;
                case 18:
                    {
                    alt3=4;
                    }
                    break;
                case 17:
                    {
                    alt3=5;
                    }
                    break;
                case 22:
                    {
                    alt3=6;
                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:134:4: ( (lv_vwkpkonfigurationfueraktion_3_0= rulevwkpaktionkonfiguraktion ) )
            	    {
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:134:4: ( (lv_vwkpkonfigurationfueraktion_3_0= rulevwkpaktionkonfiguraktion ) )
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:135:1: (lv_vwkpkonfigurationfueraktion_3_0= rulevwkpaktionkonfiguraktion )
            	    {
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:135:1: (lv_vwkpkonfigurationfueraktion_3_0= rulevwkpaktionkonfiguraktion )
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:136:3: lv_vwkpkonfigurationfueraktion_3_0= rulevwkpaktionkonfiguraktion
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKonfigurationAccess().getVwkpkonfigurationfueraktionVwkpaktionkonfiguraktionParserRuleCall_3_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulevwkpaktionkonfiguraktion_in_ruleKonfiguration197);
            	    lv_vwkpkonfigurationfueraktion_3_0=rulevwkpaktionkonfiguraktion();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getKonfigurationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"vwkpkonfigurationfueraktion",
            	            		lv_vwkpkonfigurationfueraktion_3_0, 
            	            		"vwkpaktionkonfiguraktion");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:153:6: ( (lv_pruefungsaktion_4_0= rulePRUEFUNGSAKTION ) )
            	    {
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:153:6: ( (lv_pruefungsaktion_4_0= rulePRUEFUNGSAKTION ) )
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:154:1: (lv_pruefungsaktion_4_0= rulePRUEFUNGSAKTION )
            	    {
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:154:1: (lv_pruefungsaktion_4_0= rulePRUEFUNGSAKTION )
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:155:3: lv_pruefungsaktion_4_0= rulePRUEFUNGSAKTION
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKonfigurationAccess().getPruefungsaktionPRUEFUNGSAKTIONParserRuleCall_3_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulePRUEFUNGSAKTION_in_ruleKonfiguration224);
            	    lv_pruefungsaktion_4_0=rulePRUEFUNGSAKTION();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getKonfigurationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"pruefungsaktion",
            	            		lv_pruefungsaktion_4_0, 
            	            		"PRUEFUNGSAKTION");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:172:6: ( (lv_pruefungsklassenname_5_0= rulePRUEFUNGSKLASSENNAME ) )
            	    {
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:172:6: ( (lv_pruefungsklassenname_5_0= rulePRUEFUNGSKLASSENNAME ) )
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:173:1: (lv_pruefungsklassenname_5_0= rulePRUEFUNGSKLASSENNAME )
            	    {
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:173:1: (lv_pruefungsklassenname_5_0= rulePRUEFUNGSKLASSENNAME )
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:174:3: lv_pruefungsklassenname_5_0= rulePRUEFUNGSKLASSENNAME
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKonfigurationAccess().getPruefungsklassennamePRUEFUNGSKLASSENNAMEParserRuleCall_3_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulePRUEFUNGSKLASSENNAME_in_ruleKonfiguration251);
            	    lv_pruefungsklassenname_5_0=rulePRUEFUNGSKLASSENNAME();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getKonfigurationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"pruefungsklassenname",
            	            		lv_pruefungsklassenname_5_0, 
            	            		"PRUEFUNGSKLASSENNAME");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:191:6: ( (lv_pruefungskurzbezeichnung_6_0= rulePRUEFUNGSKURZTEXT ) )
            	    {
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:191:6: ( (lv_pruefungskurzbezeichnung_6_0= rulePRUEFUNGSKURZTEXT ) )
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:192:1: (lv_pruefungskurzbezeichnung_6_0= rulePRUEFUNGSKURZTEXT )
            	    {
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:192:1: (lv_pruefungskurzbezeichnung_6_0= rulePRUEFUNGSKURZTEXT )
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:193:3: lv_pruefungskurzbezeichnung_6_0= rulePRUEFUNGSKURZTEXT
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKonfigurationAccess().getPruefungskurzbezeichnungPRUEFUNGSKURZTEXTParserRuleCall_3_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulePRUEFUNGSKURZTEXT_in_ruleKonfiguration278);
            	    lv_pruefungskurzbezeichnung_6_0=rulePRUEFUNGSKURZTEXT();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getKonfigurationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"pruefungskurzbezeichnung",
            	            		lv_pruefungskurzbezeichnung_6_0, 
            	            		"PRUEFUNGSKURZTEXT");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:210:6: ( (lv_pruefungslangtext_7_0= rulePRUEFUNGSLANGTEXT ) )
            	    {
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:210:6: ( (lv_pruefungslangtext_7_0= rulePRUEFUNGSLANGTEXT ) )
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:211:1: (lv_pruefungslangtext_7_0= rulePRUEFUNGSLANGTEXT )
            	    {
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:211:1: (lv_pruefungslangtext_7_0= rulePRUEFUNGSLANGTEXT )
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:212:3: lv_pruefungslangtext_7_0= rulePRUEFUNGSLANGTEXT
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKonfigurationAccess().getPruefungslangtextPRUEFUNGSLANGTEXTParserRuleCall_3_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulePRUEFUNGSLANGTEXT_in_ruleKonfiguration305);
            	    lv_pruefungslangtext_7_0=rulePRUEFUNGSLANGTEXT();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getKonfigurationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"pruefungslangtext",
            	            		lv_pruefungslangtext_7_0, 
            	            		"PRUEFUNGSLANGTEXT");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:229:6: ( (lv_pruefungswirkung_8_0= rulePRUEFUNGSWIRKUNG ) )
            	    {
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:229:6: ( (lv_pruefungswirkung_8_0= rulePRUEFUNGSWIRKUNG ) )
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:230:1: (lv_pruefungswirkung_8_0= rulePRUEFUNGSWIRKUNG )
            	    {
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:230:1: (lv_pruefungswirkung_8_0= rulePRUEFUNGSWIRKUNG )
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:231:3: lv_pruefungswirkung_8_0= rulePRUEFUNGSWIRKUNG
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKonfigurationAccess().getPruefungswirkungPRUEFUNGSWIRKUNGParserRuleCall_3_5_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulePRUEFUNGSWIRKUNG_in_ruleKonfiguration332);
            	    lv_pruefungswirkung_8_0=rulePRUEFUNGSWIRKUNG();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getKonfigurationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"pruefungswirkung",
            	            		lv_pruefungswirkung_8_0, 
            	            		"PRUEFUNGSWIRKUNG");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKonfiguration"


    // $ANTLR start "entryRulevwkpaktionkonfiguraktion"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:255:1: entryRulevwkpaktionkonfiguraktion returns [String current=null] : iv_rulevwkpaktionkonfiguraktion= rulevwkpaktionkonfiguraktion EOF ;
    public final String entryRulevwkpaktionkonfiguraktion() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulevwkpaktionkonfiguraktion = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:256:2: (iv_rulevwkpaktionkonfiguraktion= rulevwkpaktionkonfiguraktion EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:257:2: iv_rulevwkpaktionkonfiguraktion= rulevwkpaktionkonfiguraktion EOF
            {
             newCompositeNode(grammarAccess.getVwkpaktionkonfiguraktionRule()); 
            pushFollow(FOLLOW_rulevwkpaktionkonfiguraktion_in_entryRulevwkpaktionkonfiguraktion371);
            iv_rulevwkpaktionkonfiguraktion=rulevwkpaktionkonfiguraktion();

            state._fsp--;

             current =iv_rulevwkpaktionkonfiguraktion.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulevwkpaktionkonfiguraktion382); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulevwkpaktionkonfiguraktion"


    // $ANTLR start "rulevwkpaktionkonfiguraktion"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:264:1: rulevwkpaktionkonfiguraktion returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'VWKPKonfiguration.' this_VWKPTYP_1= ruleVWKPTYP kw= '.Aktion.' this_PRUEFUNG_3= rulePRUEFUNG kw= ' = ' this_AKTION_5= ruleAKTION ) ;
    public final AntlrDatatypeRuleToken rulevwkpaktionkonfiguraktion() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_VWKPTYP_1 = null;

        AntlrDatatypeRuleToken this_PRUEFUNG_3 = null;

        AntlrDatatypeRuleToken this_AKTION_5 = null;


         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:267:28: ( (kw= 'VWKPKonfiguration.' this_VWKPTYP_1= ruleVWKPTYP kw= '.Aktion.' this_PRUEFUNG_3= rulePRUEFUNG kw= ' = ' this_AKTION_5= ruleAKTION ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:268:1: (kw= 'VWKPKonfiguration.' this_VWKPTYP_1= ruleVWKPTYP kw= '.Aktion.' this_PRUEFUNG_3= rulePRUEFUNG kw= ' = ' this_AKTION_5= ruleAKTION )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:268:1: (kw= 'VWKPKonfiguration.' this_VWKPTYP_1= ruleVWKPTYP kw= '.Aktion.' this_PRUEFUNG_3= rulePRUEFUNG kw= ' = ' this_AKTION_5= ruleAKTION )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:269:2: kw= 'VWKPKonfiguration.' this_VWKPTYP_1= ruleVWKPTYP kw= '.Aktion.' this_PRUEFUNG_3= rulePRUEFUNG kw= ' = ' this_AKTION_5= ruleAKTION
            {
            kw=(Token)match(input,11,FOLLOW_11_in_rulevwkpaktionkonfiguraktion420); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getVwkpaktionkonfiguraktionAccess().getVWKPKonfigurationKeyword_0()); 
                
             
                    newCompositeNode(grammarAccess.getVwkpaktionkonfiguraktionAccess().getVWKPTYPParserRuleCall_1()); 
                
            pushFollow(FOLLOW_ruleVWKPTYP_in_rulevwkpaktionkonfiguraktion442);
            this_VWKPTYP_1=ruleVWKPTYP();

            state._fsp--;


            		current.merge(this_VWKPTYP_1);
                
             
                    afterParserOrEnumRuleCall();
                
            kw=(Token)match(input,12,FOLLOW_12_in_rulevwkpaktionkonfiguraktion460); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getVwkpaktionkonfiguraktionAccess().getAktionKeyword_2()); 
                
             
                    newCompositeNode(grammarAccess.getVwkpaktionkonfiguraktionAccess().getPRUEFUNGParserRuleCall_3()); 
                
            pushFollow(FOLLOW_rulePRUEFUNG_in_rulevwkpaktionkonfiguraktion482);
            this_PRUEFUNG_3=rulePRUEFUNG();

            state._fsp--;


            		current.merge(this_PRUEFUNG_3);
                
             
                    afterParserOrEnumRuleCall();
                
            kw=(Token)match(input,13,FOLLOW_13_in_rulevwkpaktionkonfiguraktion500); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getVwkpaktionkonfiguraktionAccess().getSpaceEqualsSignSpaceKeyword_4()); 
                
             
                    newCompositeNode(grammarAccess.getVwkpaktionkonfiguraktionAccess().getAKTIONParserRuleCall_5()); 
                
            pushFollow(FOLLOW_ruleAKTION_in_rulevwkpaktionkonfiguraktion522);
            this_AKTION_5=ruleAKTION();

            state._fsp--;


            		current.merge(this_AKTION_5);
                
             
                    afterParserOrEnumRuleCall();
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulevwkpaktionkonfiguraktion"


    // $ANTLR start "entryRuleVWKPTYP"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:327:1: entryRuleVWKPTYP returns [String current=null] : iv_ruleVWKPTYP= ruleVWKPTYP EOF ;
    public final String entryRuleVWKPTYP() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleVWKPTYP = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:328:2: (iv_ruleVWKPTYP= ruleVWKPTYP EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:329:2: iv_ruleVWKPTYP= ruleVWKPTYP EOF
            {
             newCompositeNode(grammarAccess.getVWKPTYPRule()); 
            pushFollow(FOLLOW_ruleVWKPTYP_in_entryRuleVWKPTYP568);
            iv_ruleVWKPTYP=ruleVWKPTYP();

            state._fsp--;

             current =iv_ruleVWKPTYP.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVWKPTYP579); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVWKPTYP"


    // $ANTLR start "ruleVWKPTYP"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:336:1: ruleVWKPTYP returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'Automatisch' | kw= 'Manuell' ) ;
    public final AntlrDatatypeRuleToken ruleVWKPTYP() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:339:28: ( (kw= 'Automatisch' | kw= 'Manuell' ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:340:1: (kw= 'Automatisch' | kw= 'Manuell' )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:340:1: (kw= 'Automatisch' | kw= 'Manuell' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==14) ) {
                alt4=1;
            }
            else if ( (LA4_0==15) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:341:2: kw= 'Automatisch'
                    {
                    kw=(Token)match(input,14,FOLLOW_14_in_ruleVWKPTYP617); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getVWKPTYPAccess().getAutomatischKeyword_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:348:2: kw= 'Manuell'
                    {
                    kw=(Token)match(input,15,FOLLOW_15_in_ruleVWKPTYP636); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getVWKPTYPAccess().getManuellKeyword_1()); 
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVWKPTYP"


    // $ANTLR start "entryRuleSPEZ_ANTRAGSZUWEISUNG"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:361:1: entryRuleSPEZ_ANTRAGSZUWEISUNG returns [String current=null] : iv_ruleSPEZ_ANTRAGSZUWEISUNG= ruleSPEZ_ANTRAGSZUWEISUNG EOF ;
    public final String entryRuleSPEZ_ANTRAGSZUWEISUNG() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSPEZ_ANTRAGSZUWEISUNG = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:362:2: (iv_ruleSPEZ_ANTRAGSZUWEISUNG= ruleSPEZ_ANTRAGSZUWEISUNG EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:363:2: iv_ruleSPEZ_ANTRAGSZUWEISUNG= ruleSPEZ_ANTRAGSZUWEISUNG EOF
            {
             newCompositeNode(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGRule()); 
            pushFollow(FOLLOW_ruleSPEZ_ANTRAGSZUWEISUNG_in_entryRuleSPEZ_ANTRAGSZUWEISUNG677);
            iv_ruleSPEZ_ANTRAGSZUWEISUNG=ruleSPEZ_ANTRAGSZUWEISUNG();

            state._fsp--;

             current =iv_ruleSPEZ_ANTRAGSZUWEISUNG.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSPEZ_ANTRAGSZUWEISUNG688); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSPEZ_ANTRAGSZUWEISUNG"


    // $ANTLR start "ruleSPEZ_ANTRAGSZUWEISUNG"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:370:1: ruleSPEZ_ANTRAGSZUWEISUNG returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT kw= ' = ' (this_PRUEFUNG_2= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_4= rulePRUEFUNG ) ;
    public final AntlrDatatypeRuleToken ruleSPEZ_ANTRAGSZUWEISUNG() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token kw=null;
        AntlrDatatypeRuleToken this_PRUEFUNG_2 = null;

        AntlrDatatypeRuleToken this_PRUEFUNG_4 = null;


         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:373:28: ( (this_INT_0= RULE_INT kw= ' = ' (this_PRUEFUNG_2= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_4= rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:374:1: (this_INT_0= RULE_INT kw= ' = ' (this_PRUEFUNG_2= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_4= rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:374:1: (this_INT_0= RULE_INT kw= ' = ' (this_PRUEFUNG_2= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_4= rulePRUEFUNG )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:374:6: this_INT_0= RULE_INT kw= ' = ' (this_PRUEFUNG_2= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_4= rulePRUEFUNG
            {
            this_INT_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleSPEZ_ANTRAGSZUWEISUNG728); 

            		current.merge(this_INT_0);
                
             
                newLeafNode(this_INT_0, grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getINTTerminalRuleCall_0()); 
                
            kw=(Token)match(input,13,FOLLOW_13_in_ruleSPEZ_ANTRAGSZUWEISUNG746); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getSpaceEqualsSignSpaceKeyword_1()); 
                
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:387:1: (this_PRUEFUNG_2= rulePRUEFUNG kw= ',' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==RULE_INT) ) {
                    int LA5_1 = input.LA(2);

                    if ( (LA5_1==16) ) {
                        alt5=1;
                    }


                }


                switch (alt5) {
            	case 1 :
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:388:5: this_PRUEFUNG_2= rulePRUEFUNG kw= ','
            	    {
            	     
            	            newCompositeNode(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getPRUEFUNGParserRuleCall_2_0()); 
            	        
            	    pushFollow(FOLLOW_rulePRUEFUNG_in_ruleSPEZ_ANTRAGSZUWEISUNG769);
            	    this_PRUEFUNG_2=rulePRUEFUNG();

            	    state._fsp--;


            	    		current.merge(this_PRUEFUNG_2);
            	        
            	     
            	            afterParserOrEnumRuleCall();
            	        
            	    kw=(Token)match(input,16,FOLLOW_16_in_ruleSPEZ_ANTRAGSZUWEISUNG787); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getCommaKeyword_2_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

             
                    newCompositeNode(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getPRUEFUNGParserRuleCall_3()); 
                
            pushFollow(FOLLOW_rulePRUEFUNG_in_ruleSPEZ_ANTRAGSZUWEISUNG811);
            this_PRUEFUNG_4=rulePRUEFUNG();

            state._fsp--;


            		current.merge(this_PRUEFUNG_4);
                
             
                    afterParserOrEnumRuleCall();
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSPEZ_ANTRAGSZUWEISUNG"


    // $ANTLR start "entryRulePRUEFUNGSLANGTEXT"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:423:1: entryRulePRUEFUNGSLANGTEXT returns [String current=null] : iv_rulePRUEFUNGSLANGTEXT= rulePRUEFUNGSLANGTEXT EOF ;
    public final String entryRulePRUEFUNGSLANGTEXT() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePRUEFUNGSLANGTEXT = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:424:2: (iv_rulePRUEFUNGSLANGTEXT= rulePRUEFUNGSLANGTEXT EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:425:2: iv_rulePRUEFUNGSLANGTEXT= rulePRUEFUNGSLANGTEXT EOF
            {
             newCompositeNode(grammarAccess.getPRUEFUNGSLANGTEXTRule()); 
            pushFollow(FOLLOW_rulePRUEFUNGSLANGTEXT_in_entryRulePRUEFUNGSLANGTEXT857);
            iv_rulePRUEFUNGSLANGTEXT=rulePRUEFUNGSLANGTEXT();

            state._fsp--;

             current =iv_rulePRUEFUNGSLANGTEXT.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePRUEFUNGSLANGTEXT868); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePRUEFUNGSLANGTEXT"


    // $ANTLR start "rulePRUEFUNGSLANGTEXT"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:432:1: rulePRUEFUNGSLANGTEXT returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'PruefungLangtext.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_STRING_3= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken rulePRUEFUNGSLANGTEXT() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_STRING_3=null;
        AntlrDatatypeRuleToken this_PRUEFUNG_1 = null;


         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:435:28: ( (kw= 'PruefungLangtext.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_STRING_3= RULE_STRING ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:436:1: (kw= 'PruefungLangtext.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_STRING_3= RULE_STRING )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:436:1: (kw= 'PruefungLangtext.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_STRING_3= RULE_STRING )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:437:2: kw= 'PruefungLangtext.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_STRING_3= RULE_STRING
            {
            kw=(Token)match(input,17,FOLLOW_17_in_rulePRUEFUNGSLANGTEXT906); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getPRUEFUNGSLANGTEXTAccess().getPruefungLangtextKeyword_0()); 
                
             
                    newCompositeNode(grammarAccess.getPRUEFUNGSLANGTEXTAccess().getPRUEFUNGParserRuleCall_1()); 
                
            pushFollow(FOLLOW_rulePRUEFUNG_in_rulePRUEFUNGSLANGTEXT928);
            this_PRUEFUNG_1=rulePRUEFUNG();

            state._fsp--;


            		current.merge(this_PRUEFUNG_1);
                
             
                    afterParserOrEnumRuleCall();
                
            kw=(Token)match(input,13,FOLLOW_13_in_rulePRUEFUNGSLANGTEXT946); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getPRUEFUNGSLANGTEXTAccess().getSpaceEqualsSignSpaceKeyword_2()); 
                
            this_STRING_3=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulePRUEFUNGSLANGTEXT961); 

            		current.merge(this_STRING_3);
                
             
                newLeafNode(this_STRING_3, grammarAccess.getPRUEFUNGSLANGTEXTAccess().getSTRINGTerminalRuleCall_3()); 
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePRUEFUNGSLANGTEXT"


    // $ANTLR start "entryRulePRUEFUNGSKURZTEXT"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:474:1: entryRulePRUEFUNGSKURZTEXT returns [String current=null] : iv_rulePRUEFUNGSKURZTEXT= rulePRUEFUNGSKURZTEXT EOF ;
    public final String entryRulePRUEFUNGSKURZTEXT() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePRUEFUNGSKURZTEXT = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:475:2: (iv_rulePRUEFUNGSKURZTEXT= rulePRUEFUNGSKURZTEXT EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:476:2: iv_rulePRUEFUNGSKURZTEXT= rulePRUEFUNGSKURZTEXT EOF
            {
             newCompositeNode(grammarAccess.getPRUEFUNGSKURZTEXTRule()); 
            pushFollow(FOLLOW_rulePRUEFUNGSKURZTEXT_in_entryRulePRUEFUNGSKURZTEXT1007);
            iv_rulePRUEFUNGSKURZTEXT=rulePRUEFUNGSKURZTEXT();

            state._fsp--;

             current =iv_rulePRUEFUNGSKURZTEXT.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePRUEFUNGSKURZTEXT1018); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePRUEFUNGSKURZTEXT"


    // $ANTLR start "rulePRUEFUNGSKURZTEXT"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:483:1: rulePRUEFUNGSKURZTEXT returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'PruefungKurzbezeichnung.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_STRING_3= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken rulePRUEFUNGSKURZTEXT() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_STRING_3=null;
        AntlrDatatypeRuleToken this_PRUEFUNG_1 = null;


         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:486:28: ( (kw= 'PruefungKurzbezeichnung.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_STRING_3= RULE_STRING ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:487:1: (kw= 'PruefungKurzbezeichnung.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_STRING_3= RULE_STRING )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:487:1: (kw= 'PruefungKurzbezeichnung.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_STRING_3= RULE_STRING )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:488:2: kw= 'PruefungKurzbezeichnung.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_STRING_3= RULE_STRING
            {
            kw=(Token)match(input,18,FOLLOW_18_in_rulePRUEFUNGSKURZTEXT1056); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getPRUEFUNGSKURZTEXTAccess().getPruefungKurzbezeichnungKeyword_0()); 
                
             
                    newCompositeNode(grammarAccess.getPRUEFUNGSKURZTEXTAccess().getPRUEFUNGParserRuleCall_1()); 
                
            pushFollow(FOLLOW_rulePRUEFUNG_in_rulePRUEFUNGSKURZTEXT1078);
            this_PRUEFUNG_1=rulePRUEFUNG();

            state._fsp--;


            		current.merge(this_PRUEFUNG_1);
                
             
                    afterParserOrEnumRuleCall();
                
            kw=(Token)match(input,13,FOLLOW_13_in_rulePRUEFUNGSKURZTEXT1096); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getPRUEFUNGSKURZTEXTAccess().getSpaceEqualsSignSpaceKeyword_2()); 
                
            this_STRING_3=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulePRUEFUNGSKURZTEXT1111); 

            		current.merge(this_STRING_3);
                
             
                newLeafNode(this_STRING_3, grammarAccess.getPRUEFUNGSKURZTEXTAccess().getSTRINGTerminalRuleCall_3()); 
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePRUEFUNGSKURZTEXT"


    // $ANTLR start "entryRulePRUEFUNGSKLASSENNAME"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:525:1: entryRulePRUEFUNGSKLASSENNAME returns [String current=null] : iv_rulePRUEFUNGSKLASSENNAME= rulePRUEFUNGSKLASSENNAME EOF ;
    public final String entryRulePRUEFUNGSKLASSENNAME() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePRUEFUNGSKLASSENNAME = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:526:2: (iv_rulePRUEFUNGSKLASSENNAME= rulePRUEFUNGSKLASSENNAME EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:527:2: iv_rulePRUEFUNGSKLASSENNAME= rulePRUEFUNGSKLASSENNAME EOF
            {
             newCompositeNode(grammarAccess.getPRUEFUNGSKLASSENNAMERule()); 
            pushFollow(FOLLOW_rulePRUEFUNGSKLASSENNAME_in_entryRulePRUEFUNGSKLASSENNAME1157);
            iv_rulePRUEFUNGSKLASSENNAME=rulePRUEFUNGSKLASSENNAME();

            state._fsp--;

             current =iv_rulePRUEFUNGSKLASSENNAME.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePRUEFUNGSKLASSENNAME1168); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePRUEFUNGSKLASSENNAME"


    // $ANTLR start "rulePRUEFUNGSKLASSENNAME"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:534:1: rulePRUEFUNGSKLASSENNAME returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'PruefungKlassenname.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_KLASSE_3= ruleKLASSE ) ;
    public final AntlrDatatypeRuleToken rulePRUEFUNGSKLASSENNAME() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_PRUEFUNG_1 = null;

        AntlrDatatypeRuleToken this_KLASSE_3 = null;


         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:537:28: ( (kw= 'PruefungKlassenname.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_KLASSE_3= ruleKLASSE ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:538:1: (kw= 'PruefungKlassenname.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_KLASSE_3= ruleKLASSE )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:538:1: (kw= 'PruefungKlassenname.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_KLASSE_3= ruleKLASSE )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:539:2: kw= 'PruefungKlassenname.' this_PRUEFUNG_1= rulePRUEFUNG kw= ' = ' this_KLASSE_3= ruleKLASSE
            {
            kw=(Token)match(input,19,FOLLOW_19_in_rulePRUEFUNGSKLASSENNAME1206); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getPRUEFUNGSKLASSENNAMEAccess().getPruefungKlassennameKeyword_0()); 
                
             
                    newCompositeNode(grammarAccess.getPRUEFUNGSKLASSENNAMEAccess().getPRUEFUNGParserRuleCall_1()); 
                
            pushFollow(FOLLOW_rulePRUEFUNG_in_rulePRUEFUNGSKLASSENNAME1228);
            this_PRUEFUNG_1=rulePRUEFUNG();

            state._fsp--;


            		current.merge(this_PRUEFUNG_1);
                
             
                    afterParserOrEnumRuleCall();
                
            kw=(Token)match(input,13,FOLLOW_13_in_rulePRUEFUNGSKLASSENNAME1246); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getPRUEFUNGSKLASSENNAMEAccess().getSpaceEqualsSignSpaceKeyword_2()); 
                
             
                    newCompositeNode(grammarAccess.getPRUEFUNGSKLASSENNAMEAccess().getKLASSEParserRuleCall_3()); 
                
            pushFollow(FOLLOW_ruleKLASSE_in_rulePRUEFUNGSKLASSENNAME1268);
            this_KLASSE_3=ruleKLASSE();

            state._fsp--;


            		current.merge(this_KLASSE_3);
                
             
                    afterParserOrEnumRuleCall();
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePRUEFUNGSKLASSENNAME"


    // $ANTLR start "entryRulePRUEFUNGSAKTION"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:580:1: entryRulePRUEFUNGSAKTION returns [String current=null] : iv_rulePRUEFUNGSAKTION= rulePRUEFUNGSAKTION EOF ;
    public final String entryRulePRUEFUNGSAKTION() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePRUEFUNGSAKTION = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:581:2: (iv_rulePRUEFUNGSAKTION= rulePRUEFUNGSAKTION EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:582:2: iv_rulePRUEFUNGSAKTION= rulePRUEFUNGSAKTION EOF
            {
             newCompositeNode(grammarAccess.getPRUEFUNGSAKTIONRule()); 
            pushFollow(FOLLOW_rulePRUEFUNGSAKTION_in_entryRulePRUEFUNGSAKTION1314);
            iv_rulePRUEFUNGSAKTION=rulePRUEFUNGSAKTION();

            state._fsp--;

             current =iv_rulePRUEFUNGSAKTION.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePRUEFUNGSAKTION1325); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePRUEFUNGSAKTION"


    // $ANTLR start "rulePRUEFUNGSAKTION"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:589:1: rulePRUEFUNGSAKTION returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'PruefungAktion.' this_AKTIONSID_1= ruleAKTIONSID kw= ' = ' this_AKTION_3= ruleAKTION ) ;
    public final AntlrDatatypeRuleToken rulePRUEFUNGSAKTION() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_AKTIONSID_1 = null;

        AntlrDatatypeRuleToken this_AKTION_3 = null;


         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:592:28: ( (kw= 'PruefungAktion.' this_AKTIONSID_1= ruleAKTIONSID kw= ' = ' this_AKTION_3= ruleAKTION ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:593:1: (kw= 'PruefungAktion.' this_AKTIONSID_1= ruleAKTIONSID kw= ' = ' this_AKTION_3= ruleAKTION )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:593:1: (kw= 'PruefungAktion.' this_AKTIONSID_1= ruleAKTIONSID kw= ' = ' this_AKTION_3= ruleAKTION )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:594:2: kw= 'PruefungAktion.' this_AKTIONSID_1= ruleAKTIONSID kw= ' = ' this_AKTION_3= ruleAKTION
            {
            kw=(Token)match(input,20,FOLLOW_20_in_rulePRUEFUNGSAKTION1363); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getPRUEFUNGSAKTIONAccess().getPruefungAktionKeyword_0()); 
                
             
                    newCompositeNode(grammarAccess.getPRUEFUNGSAKTIONAccess().getAKTIONSIDParserRuleCall_1()); 
                
            pushFollow(FOLLOW_ruleAKTIONSID_in_rulePRUEFUNGSAKTION1385);
            this_AKTIONSID_1=ruleAKTIONSID();

            state._fsp--;


            		current.merge(this_AKTIONSID_1);
                
             
                    afterParserOrEnumRuleCall();
                
            kw=(Token)match(input,13,FOLLOW_13_in_rulePRUEFUNGSAKTION1403); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getPRUEFUNGSAKTIONAccess().getSpaceEqualsSignSpaceKeyword_2()); 
                
             
                    newCompositeNode(grammarAccess.getPRUEFUNGSAKTIONAccess().getAKTIONParserRuleCall_3()); 
                
            pushFollow(FOLLOW_ruleAKTION_in_rulePRUEFUNGSAKTION1425);
            this_AKTION_3=ruleAKTION();

            state._fsp--;


            		current.merge(this_AKTION_3);
                
             
                    afterParserOrEnumRuleCall();
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePRUEFUNGSAKTION"


    // $ANTLR start "entryRuleAKTIONSID"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:635:1: entryRuleAKTIONSID returns [String current=null] : iv_ruleAKTIONSID= ruleAKTIONSID EOF ;
    public final String entryRuleAKTIONSID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAKTIONSID = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:636:2: (iv_ruleAKTIONSID= ruleAKTIONSID EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:637:2: iv_ruleAKTIONSID= ruleAKTIONSID EOF
            {
             newCompositeNode(grammarAccess.getAKTIONSIDRule()); 
            pushFollow(FOLLOW_ruleAKTIONSID_in_entryRuleAKTIONSID1471);
            iv_ruleAKTIONSID=ruleAKTIONSID();

            state._fsp--;

             current =iv_ruleAKTIONSID.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAKTIONSID1482); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAKTIONSID"


    // $ANTLR start "ruleAKTIONSID"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:644:1: ruleAKTIONSID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_PRUEFUNG_0= rulePRUEFUNG kw= '.' this_INT_2= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleAKTIONSID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_2=null;
        AntlrDatatypeRuleToken this_PRUEFUNG_0 = null;


         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:647:28: ( (this_PRUEFUNG_0= rulePRUEFUNG kw= '.' this_INT_2= RULE_INT ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:648:1: (this_PRUEFUNG_0= rulePRUEFUNG kw= '.' this_INT_2= RULE_INT )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:648:1: (this_PRUEFUNG_0= rulePRUEFUNG kw= '.' this_INT_2= RULE_INT )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:649:5: this_PRUEFUNG_0= rulePRUEFUNG kw= '.' this_INT_2= RULE_INT
            {
             
                    newCompositeNode(grammarAccess.getAKTIONSIDAccess().getPRUEFUNGParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulePRUEFUNG_in_ruleAKTIONSID1529);
            this_PRUEFUNG_0=rulePRUEFUNG();

            state._fsp--;


            		current.merge(this_PRUEFUNG_0);
                
             
                    afterParserOrEnumRuleCall();
                
            kw=(Token)match(input,21,FOLLOW_21_in_ruleAKTIONSID1547); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getAKTIONSIDAccess().getFullStopKeyword_1()); 
                
            this_INT_2=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleAKTIONSID1562); 

            		current.merge(this_INT_2);
                
             
                newLeafNode(this_INT_2, grammarAccess.getAKTIONSIDAccess().getINTTerminalRuleCall_2()); 
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAKTIONSID"


    // $ANTLR start "entryRulePRUEFUNGSWIRKUNG"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:680:1: entryRulePRUEFUNGSWIRKUNG returns [String current=null] : iv_rulePRUEFUNGSWIRKUNG= rulePRUEFUNGSWIRKUNG EOF ;
    public final String entryRulePRUEFUNGSWIRKUNG() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePRUEFUNGSWIRKUNG = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:681:2: (iv_rulePRUEFUNGSWIRKUNG= rulePRUEFUNGSWIRKUNG EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:682:2: iv_rulePRUEFUNGSWIRKUNG= rulePRUEFUNGSWIRKUNG EOF
            {
             newCompositeNode(grammarAccess.getPRUEFUNGSWIRKUNGRule()); 
            pushFollow(FOLLOW_rulePRUEFUNGSWIRKUNG_in_entryRulePRUEFUNGSWIRKUNG1608);
            iv_rulePRUEFUNGSWIRKUNG=rulePRUEFUNGSWIRKUNG();

            state._fsp--;

             current =iv_rulePRUEFUNGSWIRKUNG.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePRUEFUNGSWIRKUNG1619); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePRUEFUNGSWIRKUNG"


    // $ANTLR start "rulePRUEFUNGSWIRKUNG"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:689:1: rulePRUEFUNGSWIRKUNG returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'PruefungWirkung.' this_WIRKUNGSID_1= ruleWIRKUNGSID kw= ' = ' this_WIRKUNG_3= ruleWIRKUNG ) ;
    public final AntlrDatatypeRuleToken rulePRUEFUNGSWIRKUNG() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_WIRKUNGSID_1 = null;

        AntlrDatatypeRuleToken this_WIRKUNG_3 = null;


         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:692:28: ( (kw= 'PruefungWirkung.' this_WIRKUNGSID_1= ruleWIRKUNGSID kw= ' = ' this_WIRKUNG_3= ruleWIRKUNG ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:693:1: (kw= 'PruefungWirkung.' this_WIRKUNGSID_1= ruleWIRKUNGSID kw= ' = ' this_WIRKUNG_3= ruleWIRKUNG )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:693:1: (kw= 'PruefungWirkung.' this_WIRKUNGSID_1= ruleWIRKUNGSID kw= ' = ' this_WIRKUNG_3= ruleWIRKUNG )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:694:2: kw= 'PruefungWirkung.' this_WIRKUNGSID_1= ruleWIRKUNGSID kw= ' = ' this_WIRKUNG_3= ruleWIRKUNG
            {
            kw=(Token)match(input,22,FOLLOW_22_in_rulePRUEFUNGSWIRKUNG1657); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getPRUEFUNGSWIRKUNGAccess().getPruefungWirkungKeyword_0()); 
                
             
                    newCompositeNode(grammarAccess.getPRUEFUNGSWIRKUNGAccess().getWIRKUNGSIDParserRuleCall_1()); 
                
            pushFollow(FOLLOW_ruleWIRKUNGSID_in_rulePRUEFUNGSWIRKUNG1679);
            this_WIRKUNGSID_1=ruleWIRKUNGSID();

            state._fsp--;


            		current.merge(this_WIRKUNGSID_1);
                
             
                    afterParserOrEnumRuleCall();
                
            kw=(Token)match(input,13,FOLLOW_13_in_rulePRUEFUNGSWIRKUNG1697); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getPRUEFUNGSWIRKUNGAccess().getSpaceEqualsSignSpaceKeyword_2()); 
                
             
                    newCompositeNode(grammarAccess.getPRUEFUNGSWIRKUNGAccess().getWIRKUNGParserRuleCall_3()); 
                
            pushFollow(FOLLOW_ruleWIRKUNG_in_rulePRUEFUNGSWIRKUNG1719);
            this_WIRKUNG_3=ruleWIRKUNG();

            state._fsp--;


            		current.merge(this_WIRKUNG_3);
                
             
                    afterParserOrEnumRuleCall();
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePRUEFUNGSWIRKUNG"


    // $ANTLR start "entryRuleWIRKUNGSID"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:735:1: entryRuleWIRKUNGSID returns [String current=null] : iv_ruleWIRKUNGSID= ruleWIRKUNGSID EOF ;
    public final String entryRuleWIRKUNGSID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleWIRKUNGSID = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:736:2: (iv_ruleWIRKUNGSID= ruleWIRKUNGSID EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:737:2: iv_ruleWIRKUNGSID= ruleWIRKUNGSID EOF
            {
             newCompositeNode(grammarAccess.getWIRKUNGSIDRule()); 
            pushFollow(FOLLOW_ruleWIRKUNGSID_in_entryRuleWIRKUNGSID1765);
            iv_ruleWIRKUNGSID=ruleWIRKUNGSID();

            state._fsp--;

             current =iv_ruleWIRKUNGSID.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWIRKUNGSID1776); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWIRKUNGSID"


    // $ANTLR start "ruleWIRKUNGSID"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:744:1: ruleWIRKUNGSID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_PRUEFUNG_0= rulePRUEFUNG kw= '.' this_INT_2= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleWIRKUNGSID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_2=null;
        AntlrDatatypeRuleToken this_PRUEFUNG_0 = null;


         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:747:28: ( (this_PRUEFUNG_0= rulePRUEFUNG kw= '.' this_INT_2= RULE_INT ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:748:1: (this_PRUEFUNG_0= rulePRUEFUNG kw= '.' this_INT_2= RULE_INT )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:748:1: (this_PRUEFUNG_0= rulePRUEFUNG kw= '.' this_INT_2= RULE_INT )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:749:5: this_PRUEFUNG_0= rulePRUEFUNG kw= '.' this_INT_2= RULE_INT
            {
             
                    newCompositeNode(grammarAccess.getWIRKUNGSIDAccess().getPRUEFUNGParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulePRUEFUNG_in_ruleWIRKUNGSID1823);
            this_PRUEFUNG_0=rulePRUEFUNG();

            state._fsp--;


            		current.merge(this_PRUEFUNG_0);
                
             
                    afterParserOrEnumRuleCall();
                
            kw=(Token)match(input,21,FOLLOW_21_in_ruleWIRKUNGSID1841); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getWIRKUNGSIDAccess().getFullStopKeyword_1()); 
                
            this_INT_2=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleWIRKUNGSID1856); 

            		current.merge(this_INT_2);
                
             
                newLeafNode(this_INT_2, grammarAccess.getWIRKUNGSIDAccess().getINTTerminalRuleCall_2()); 
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWIRKUNGSID"


    // $ANTLR start "entryRuleZuweisung"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:780:1: entryRuleZuweisung returns [String current=null] : iv_ruleZuweisung= ruleZuweisung EOF ;
    public final String entryRuleZuweisung() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleZuweisung = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:781:2: (iv_ruleZuweisung= ruleZuweisung EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:782:2: iv_ruleZuweisung= ruleZuweisung EOF
            {
             newCompositeNode(grammarAccess.getZuweisungRule()); 
            pushFollow(FOLLOW_ruleZuweisung_in_entryRuleZuweisung1902);
            iv_ruleZuweisung=ruleZuweisung();

            state._fsp--;

             current =iv_ruleZuweisung.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleZuweisung1913); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleZuweisung"


    // $ANTLR start "ruleZuweisung"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:789:1: ruleZuweisung returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'DvAntragsArt.' this_ANTRAGSART_1= ruleANTRAGSART kw= ' = ' (this_PRUEFUNG_3= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_5= rulePRUEFUNG ) ;
    public final AntlrDatatypeRuleToken ruleZuweisung() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_ANTRAGSART_1 = null;

        AntlrDatatypeRuleToken this_PRUEFUNG_3 = null;

        AntlrDatatypeRuleToken this_PRUEFUNG_5 = null;


         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:792:28: ( (kw= 'DvAntragsArt.' this_ANTRAGSART_1= ruleANTRAGSART kw= ' = ' (this_PRUEFUNG_3= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_5= rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:793:1: (kw= 'DvAntragsArt.' this_ANTRAGSART_1= ruleANTRAGSART kw= ' = ' (this_PRUEFUNG_3= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_5= rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:793:1: (kw= 'DvAntragsArt.' this_ANTRAGSART_1= ruleANTRAGSART kw= ' = ' (this_PRUEFUNG_3= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_5= rulePRUEFUNG )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:794:2: kw= 'DvAntragsArt.' this_ANTRAGSART_1= ruleANTRAGSART kw= ' = ' (this_PRUEFUNG_3= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_5= rulePRUEFUNG
            {
            kw=(Token)match(input,23,FOLLOW_23_in_ruleZuweisung1951); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getZuweisungAccess().getDvAntragsArtKeyword_0()); 
                
             
                    newCompositeNode(grammarAccess.getZuweisungAccess().getANTRAGSARTParserRuleCall_1()); 
                
            pushFollow(FOLLOW_ruleANTRAGSART_in_ruleZuweisung1973);
            this_ANTRAGSART_1=ruleANTRAGSART();

            state._fsp--;


            		current.merge(this_ANTRAGSART_1);
                
             
                    afterParserOrEnumRuleCall();
                
            kw=(Token)match(input,13,FOLLOW_13_in_ruleZuweisung1991); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getZuweisungAccess().getSpaceEqualsSignSpaceKeyword_2()); 
                
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:816:1: (this_PRUEFUNG_3= rulePRUEFUNG kw= ',' )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==RULE_INT) ) {
                    int LA6_1 = input.LA(2);

                    if ( (LA6_1==16) ) {
                        alt6=1;
                    }


                }


                switch (alt6) {
            	case 1 :
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:817:5: this_PRUEFUNG_3= rulePRUEFUNG kw= ','
            	    {
            	     
            	            newCompositeNode(grammarAccess.getZuweisungAccess().getPRUEFUNGParserRuleCall_3_0()); 
            	        
            	    pushFollow(FOLLOW_rulePRUEFUNG_in_ruleZuweisung2014);
            	    this_PRUEFUNG_3=rulePRUEFUNG();

            	    state._fsp--;


            	    		current.merge(this_PRUEFUNG_3);
            	        
            	     
            	            afterParserOrEnumRuleCall();
            	        
            	    kw=(Token)match(input,16,FOLLOW_16_in_ruleZuweisung2032); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getZuweisungAccess().getCommaKeyword_3_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

             
                    newCompositeNode(grammarAccess.getZuweisungAccess().getPRUEFUNGParserRuleCall_4()); 
                
            pushFollow(FOLLOW_rulePRUEFUNG_in_ruleZuweisung2056);
            this_PRUEFUNG_5=rulePRUEFUNG();

            state._fsp--;


            		current.merge(this_PRUEFUNG_5);
                
             
                    afterParserOrEnumRuleCall();
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleZuweisung"


    // $ANTLR start "entryRuleUsedIDs"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:852:1: entryRuleUsedIDs returns [String current=null] : iv_ruleUsedIDs= ruleUsedIDs EOF ;
    public final String entryRuleUsedIDs() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUsedIDs = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:853:2: (iv_ruleUsedIDs= ruleUsedIDs EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:854:2: iv_ruleUsedIDs= ruleUsedIDs EOF
            {
             newCompositeNode(grammarAccess.getUsedIDsRule()); 
            pushFollow(FOLLOW_ruleUsedIDs_in_entryRuleUsedIDs2102);
            iv_ruleUsedIDs=ruleUsedIDs();

            state._fsp--;

             current =iv_ruleUsedIDs.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUsedIDs2113); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUsedIDs"


    // $ANTLR start "ruleUsedIDs"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:861:1: ruleUsedIDs returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'CodesAlle = ' (this_PRUEFUNG_1= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_3= rulePRUEFUNG ) ;
    public final AntlrDatatypeRuleToken ruleUsedIDs() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_PRUEFUNG_1 = null;

        AntlrDatatypeRuleToken this_PRUEFUNG_3 = null;


         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:864:28: ( (kw= 'CodesAlle = ' (this_PRUEFUNG_1= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_3= rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:865:1: (kw= 'CodesAlle = ' (this_PRUEFUNG_1= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_3= rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:865:1: (kw= 'CodesAlle = ' (this_PRUEFUNG_1= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_3= rulePRUEFUNG )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:866:2: kw= 'CodesAlle = ' (this_PRUEFUNG_1= rulePRUEFUNG kw= ',' )* this_PRUEFUNG_3= rulePRUEFUNG
            {
            kw=(Token)match(input,24,FOLLOW_24_in_ruleUsedIDs2151); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getUsedIDsAccess().getCodesAlleKeyword_0()); 
                
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:871:1: (this_PRUEFUNG_1= rulePRUEFUNG kw= ',' )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_INT) ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1==16) ) {
                        alt7=1;
                    }


                }


                switch (alt7) {
            	case 1 :
            	    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:872:5: this_PRUEFUNG_1= rulePRUEFUNG kw= ','
            	    {
            	     
            	            newCompositeNode(grammarAccess.getUsedIDsAccess().getPRUEFUNGParserRuleCall_1_0()); 
            	        
            	    pushFollow(FOLLOW_rulePRUEFUNG_in_ruleUsedIDs2174);
            	    this_PRUEFUNG_1=rulePRUEFUNG();

            	    state._fsp--;


            	    		current.merge(this_PRUEFUNG_1);
            	        
            	     
            	            afterParserOrEnumRuleCall();
            	        
            	    kw=(Token)match(input,16,FOLLOW_16_in_ruleUsedIDs2192); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getUsedIDsAccess().getCommaKeyword_1_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             
                    newCompositeNode(grammarAccess.getUsedIDsAccess().getPRUEFUNGParserRuleCall_2()); 
                
            pushFollow(FOLLOW_rulePRUEFUNG_in_ruleUsedIDs2216);
            this_PRUEFUNG_3=rulePRUEFUNG();

            state._fsp--;


            		current.merge(this_PRUEFUNG_3);
                
             
                    afterParserOrEnumRuleCall();
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUsedIDs"


    // $ANTLR start "entryRulePRUEFUNG"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:907:1: entryRulePRUEFUNG returns [String current=null] : iv_rulePRUEFUNG= rulePRUEFUNG EOF ;
    public final String entryRulePRUEFUNG() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePRUEFUNG = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:908:2: (iv_rulePRUEFUNG= rulePRUEFUNG EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:909:2: iv_rulePRUEFUNG= rulePRUEFUNG EOF
            {
             newCompositeNode(grammarAccess.getPRUEFUNGRule()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_entryRulePRUEFUNG2262);
            iv_rulePRUEFUNG=rulePRUEFUNG();

            state._fsp--;

             current =iv_rulePRUEFUNG.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePRUEFUNG2273); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePRUEFUNG"


    // $ANTLR start "rulePRUEFUNG"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:916:1: rulePRUEFUNG returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INT_0= RULE_INT ;
    public final AntlrDatatypeRuleToken rulePRUEFUNG() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;

         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:919:28: (this_INT_0= RULE_INT )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:920:5: this_INT_0= RULE_INT
            {
            this_INT_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_rulePRUEFUNG2312); 

            		current.merge(this_INT_0);
                
             
                newLeafNode(this_INT_0, grammarAccess.getPRUEFUNGAccess().getINTTerminalRuleCall()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePRUEFUNG"


    // $ANTLR start "entryRuleWIRKUNG"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:935:1: entryRuleWIRKUNG returns [String current=null] : iv_ruleWIRKUNG= ruleWIRKUNG EOF ;
    public final String entryRuleWIRKUNG() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleWIRKUNG = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:936:2: (iv_ruleWIRKUNG= ruleWIRKUNG EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:937:2: iv_ruleWIRKUNG= ruleWIRKUNG EOF
            {
             newCompositeNode(grammarAccess.getWIRKUNGRule()); 
            pushFollow(FOLLOW_ruleWIRKUNG_in_entryRuleWIRKUNG2357);
            iv_ruleWIRKUNG=ruleWIRKUNG();

            state._fsp--;

             current =iv_ruleWIRKUNG.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWIRKUNG2368); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWIRKUNG"


    // $ANTLR start "ruleWIRKUNG"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:944:1: ruleWIRKUNG returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'VERHINDERT_AKTION' | kw= 'OHNE' | kw= 'WARNUNG' ) ;
    public final AntlrDatatypeRuleToken ruleWIRKUNG() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:947:28: ( (kw= 'VERHINDERT_AKTION' | kw= 'OHNE' | kw= 'WARNUNG' ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:948:1: (kw= 'VERHINDERT_AKTION' | kw= 'OHNE' | kw= 'WARNUNG' )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:948:1: (kw= 'VERHINDERT_AKTION' | kw= 'OHNE' | kw= 'WARNUNG' )
            int alt8=3;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt8=1;
                }
                break;
            case 26:
                {
                alt8=2;
                }
                break;
            case 27:
                {
                alt8=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:949:2: kw= 'VERHINDERT_AKTION'
                    {
                    kw=(Token)match(input,25,FOLLOW_25_in_ruleWIRKUNG2406); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getWIRKUNGAccess().getVERHINDERT_AKTIONKeyword_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:956:2: kw= 'OHNE'
                    {
                    kw=(Token)match(input,26,FOLLOW_26_in_ruleWIRKUNG2425); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getWIRKUNGAccess().getOHNEKeyword_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:963:2: kw= 'WARNUNG'
                    {
                    kw=(Token)match(input,27,FOLLOW_27_in_ruleWIRKUNG2444); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getWIRKUNGAccess().getWARNUNGKeyword_2()); 
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWIRKUNG"


    // $ANTLR start "entryRuleANTRAGSART"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:976:1: entryRuleANTRAGSART returns [String current=null] : iv_ruleANTRAGSART= ruleANTRAGSART EOF ;
    public final String entryRuleANTRAGSART() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleANTRAGSART = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:977:2: (iv_ruleANTRAGSART= ruleANTRAGSART EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:978:2: iv_ruleANTRAGSART= ruleANTRAGSART EOF
            {
             newCompositeNode(grammarAccess.getANTRAGSARTRule()); 
            pushFollow(FOLLOW_ruleANTRAGSART_in_entryRuleANTRAGSART2485);
            iv_ruleANTRAGSART=ruleANTRAGSART();

            state._fsp--;

             current =iv_ruleANTRAGSART.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleANTRAGSART2496); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleANTRAGSART"


    // $ANTLR start "ruleANTRAGSART"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:985:1: ruleANTRAGSART returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'AUSZANTRAG' | kw= 'ERWANTRAG' | kw= 'NEUANTRAG' | kw= 'VERLANTRAG' ) ;
    public final AntlrDatatypeRuleToken ruleANTRAGSART() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:988:28: ( (kw= 'AUSZANTRAG' | kw= 'ERWANTRAG' | kw= 'NEUANTRAG' | kw= 'VERLANTRAG' ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:989:1: (kw= 'AUSZANTRAG' | kw= 'ERWANTRAG' | kw= 'NEUANTRAG' | kw= 'VERLANTRAG' )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:989:1: (kw= 'AUSZANTRAG' | kw= 'ERWANTRAG' | kw= 'NEUANTRAG' | kw= 'VERLANTRAG' )
            int alt9=4;
            switch ( input.LA(1) ) {
            case 28:
                {
                alt9=1;
                }
                break;
            case 29:
                {
                alt9=2;
                }
                break;
            case 30:
                {
                alt9=3;
                }
                break;
            case 31:
                {
                alt9=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:990:2: kw= 'AUSZANTRAG'
                    {
                    kw=(Token)match(input,28,FOLLOW_28_in_ruleANTRAGSART2534); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getANTRAGSARTAccess().getAUSZANTRAGKeyword_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:997:2: kw= 'ERWANTRAG'
                    {
                    kw=(Token)match(input,29,FOLLOW_29_in_ruleANTRAGSART2553); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getANTRAGSARTAccess().getERWANTRAGKeyword_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1004:2: kw= 'NEUANTRAG'
                    {
                    kw=(Token)match(input,30,FOLLOW_30_in_ruleANTRAGSART2572); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getANTRAGSARTAccess().getNEUANTRAGKeyword_2()); 
                        

                    }
                    break;
                case 4 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1011:2: kw= 'VERLANTRAG'
                    {
                    kw=(Token)match(input,31,FOLLOW_31_in_ruleANTRAGSART2591); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getANTRAGSARTAccess().getVERLANTRAGKeyword_3()); 
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleANTRAGSART"


    // $ANTLR start "entryRuleAKTION"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1024:1: entryRuleAKTION returns [String current=null] : iv_ruleAKTION= ruleAKTION EOF ;
    public final String entryRuleAKTION() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAKTION = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1025:2: (iv_ruleAKTION= ruleAKTION EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1026:2: iv_ruleAKTION= ruleAKTION EOF
            {
             newCompositeNode(grammarAccess.getAKTIONRule()); 
            pushFollow(FOLLOW_ruleAKTION_in_entryRuleAKTION2632);
            iv_ruleAKTION=ruleAKTION();

            state._fsp--;

             current =iv_ruleAKTION.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAKTION2643); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAKTION"


    // $ANTLR start "ruleAKTION"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1033:1: ruleAKTION returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'BerechnenUndPruefen' | kw= 'DokumentBearbeitungBeginnen' | kw= 'Zurueckziehen' | kw= 'NachberechnungStornieren' | kw= 'ZurueckziehenZuruecknehmen' | kw= 'AntragFreigeben' | kw= 'AntragFreigabeZurueck' | kw= 'DokumentBearbeitungBeenden' | kw= 'AntragBewilligen' | kw= 'AntragAblehnen' | kw= 'AntragZahlungAnweisen' | kw= 'AntragEntscheidungZurueck' | kw= 'AntragNeuBearbeiten' | kw= 'AntragWidersprechen' | kw= 'AntragWiderspruchAblZurueck' | kw= 'AntragWiderspruchZurueck' | kw= 'AntragWiderspruchAblehnen' | kw= 'AntragWiderspruchZulassen' | kw= 'AntragWiderspruchZulZurueck' | kw= 'AntragWiderspruchStattgeben' ) ;
    public final AntlrDatatypeRuleToken ruleAKTION() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1036:28: ( (kw= 'BerechnenUndPruefen' | kw= 'DokumentBearbeitungBeginnen' | kw= 'Zurueckziehen' | kw= 'NachberechnungStornieren' | kw= 'ZurueckziehenZuruecknehmen' | kw= 'AntragFreigeben' | kw= 'AntragFreigabeZurueck' | kw= 'DokumentBearbeitungBeenden' | kw= 'AntragBewilligen' | kw= 'AntragAblehnen' | kw= 'AntragZahlungAnweisen' | kw= 'AntragEntscheidungZurueck' | kw= 'AntragNeuBearbeiten' | kw= 'AntragWidersprechen' | kw= 'AntragWiderspruchAblZurueck' | kw= 'AntragWiderspruchZurueck' | kw= 'AntragWiderspruchAblehnen' | kw= 'AntragWiderspruchZulassen' | kw= 'AntragWiderspruchZulZurueck' | kw= 'AntragWiderspruchStattgeben' ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1037:1: (kw= 'BerechnenUndPruefen' | kw= 'DokumentBearbeitungBeginnen' | kw= 'Zurueckziehen' | kw= 'NachberechnungStornieren' | kw= 'ZurueckziehenZuruecknehmen' | kw= 'AntragFreigeben' | kw= 'AntragFreigabeZurueck' | kw= 'DokumentBearbeitungBeenden' | kw= 'AntragBewilligen' | kw= 'AntragAblehnen' | kw= 'AntragZahlungAnweisen' | kw= 'AntragEntscheidungZurueck' | kw= 'AntragNeuBearbeiten' | kw= 'AntragWidersprechen' | kw= 'AntragWiderspruchAblZurueck' | kw= 'AntragWiderspruchZurueck' | kw= 'AntragWiderspruchAblehnen' | kw= 'AntragWiderspruchZulassen' | kw= 'AntragWiderspruchZulZurueck' | kw= 'AntragWiderspruchStattgeben' )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1037:1: (kw= 'BerechnenUndPruefen' | kw= 'DokumentBearbeitungBeginnen' | kw= 'Zurueckziehen' | kw= 'NachberechnungStornieren' | kw= 'ZurueckziehenZuruecknehmen' | kw= 'AntragFreigeben' | kw= 'AntragFreigabeZurueck' | kw= 'DokumentBearbeitungBeenden' | kw= 'AntragBewilligen' | kw= 'AntragAblehnen' | kw= 'AntragZahlungAnweisen' | kw= 'AntragEntscheidungZurueck' | kw= 'AntragNeuBearbeiten' | kw= 'AntragWidersprechen' | kw= 'AntragWiderspruchAblZurueck' | kw= 'AntragWiderspruchZurueck' | kw= 'AntragWiderspruchAblehnen' | kw= 'AntragWiderspruchZulassen' | kw= 'AntragWiderspruchZulZurueck' | kw= 'AntragWiderspruchStattgeben' )
            int alt10=20;
            switch ( input.LA(1) ) {
            case 32:
                {
                alt10=1;
                }
                break;
            case 33:
                {
                alt10=2;
                }
                break;
            case 34:
                {
                alt10=3;
                }
                break;
            case 35:
                {
                alt10=4;
                }
                break;
            case 36:
                {
                alt10=5;
                }
                break;
            case 37:
                {
                alt10=6;
                }
                break;
            case 38:
                {
                alt10=7;
                }
                break;
            case 39:
                {
                alt10=8;
                }
                break;
            case 40:
                {
                alt10=9;
                }
                break;
            case 41:
                {
                alt10=10;
                }
                break;
            case 42:
                {
                alt10=11;
                }
                break;
            case 43:
                {
                alt10=12;
                }
                break;
            case 44:
                {
                alt10=13;
                }
                break;
            case 45:
                {
                alt10=14;
                }
                break;
            case 46:
                {
                alt10=15;
                }
                break;
            case 47:
                {
                alt10=16;
                }
                break;
            case 48:
                {
                alt10=17;
                }
                break;
            case 49:
                {
                alt10=18;
                }
                break;
            case 50:
                {
                alt10=19;
                }
                break;
            case 51:
                {
                alt10=20;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1038:2: kw= 'BerechnenUndPruefen'
                    {
                    kw=(Token)match(input,32,FOLLOW_32_in_ruleAKTION2681); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getBerechnenUndPruefenKeyword_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1045:2: kw= 'DokumentBearbeitungBeginnen'
                    {
                    kw=(Token)match(input,33,FOLLOW_33_in_ruleAKTION2700); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getDokumentBearbeitungBeginnenKeyword_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1052:2: kw= 'Zurueckziehen'
                    {
                    kw=(Token)match(input,34,FOLLOW_34_in_ruleAKTION2719); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getZurueckziehenKeyword_2()); 
                        

                    }
                    break;
                case 4 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1059:2: kw= 'NachberechnungStornieren'
                    {
                    kw=(Token)match(input,35,FOLLOW_35_in_ruleAKTION2738); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getNachberechnungStornierenKeyword_3()); 
                        

                    }
                    break;
                case 5 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1066:2: kw= 'ZurueckziehenZuruecknehmen'
                    {
                    kw=(Token)match(input,36,FOLLOW_36_in_ruleAKTION2757); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getZurueckziehenZuruecknehmenKeyword_4()); 
                        

                    }
                    break;
                case 6 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1073:2: kw= 'AntragFreigeben'
                    {
                    kw=(Token)match(input,37,FOLLOW_37_in_ruleAKTION2776); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getAntragFreigebenKeyword_5()); 
                        

                    }
                    break;
                case 7 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1080:2: kw= 'AntragFreigabeZurueck'
                    {
                    kw=(Token)match(input,38,FOLLOW_38_in_ruleAKTION2795); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getAntragFreigabeZurueckKeyword_6()); 
                        

                    }
                    break;
                case 8 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1087:2: kw= 'DokumentBearbeitungBeenden'
                    {
                    kw=(Token)match(input,39,FOLLOW_39_in_ruleAKTION2814); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getDokumentBearbeitungBeendenKeyword_7()); 
                        

                    }
                    break;
                case 9 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1094:2: kw= 'AntragBewilligen'
                    {
                    kw=(Token)match(input,40,FOLLOW_40_in_ruleAKTION2833); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getAntragBewilligenKeyword_8()); 
                        

                    }
                    break;
                case 10 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1101:2: kw= 'AntragAblehnen'
                    {
                    kw=(Token)match(input,41,FOLLOW_41_in_ruleAKTION2852); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getAntragAblehnenKeyword_9()); 
                        

                    }
                    break;
                case 11 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1108:2: kw= 'AntragZahlungAnweisen'
                    {
                    kw=(Token)match(input,42,FOLLOW_42_in_ruleAKTION2871); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getAntragZahlungAnweisenKeyword_10()); 
                        

                    }
                    break;
                case 12 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1115:2: kw= 'AntragEntscheidungZurueck'
                    {
                    kw=(Token)match(input,43,FOLLOW_43_in_ruleAKTION2890); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getAntragEntscheidungZurueckKeyword_11()); 
                        

                    }
                    break;
                case 13 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1122:2: kw= 'AntragNeuBearbeiten'
                    {
                    kw=(Token)match(input,44,FOLLOW_44_in_ruleAKTION2909); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getAntragNeuBearbeitenKeyword_12()); 
                        

                    }
                    break;
                case 14 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1129:2: kw= 'AntragWidersprechen'
                    {
                    kw=(Token)match(input,45,FOLLOW_45_in_ruleAKTION2928); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getAntragWidersprechenKeyword_13()); 
                        

                    }
                    break;
                case 15 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1136:2: kw= 'AntragWiderspruchAblZurueck'
                    {
                    kw=(Token)match(input,46,FOLLOW_46_in_ruleAKTION2947); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getAntragWiderspruchAblZurueckKeyword_14()); 
                        

                    }
                    break;
                case 16 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1143:2: kw= 'AntragWiderspruchZurueck'
                    {
                    kw=(Token)match(input,47,FOLLOW_47_in_ruleAKTION2966); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getAntragWiderspruchZurueckKeyword_15()); 
                        

                    }
                    break;
                case 17 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1150:2: kw= 'AntragWiderspruchAblehnen'
                    {
                    kw=(Token)match(input,48,FOLLOW_48_in_ruleAKTION2985); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getAntragWiderspruchAblehnenKeyword_16()); 
                        

                    }
                    break;
                case 18 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1157:2: kw= 'AntragWiderspruchZulassen'
                    {
                    kw=(Token)match(input,49,FOLLOW_49_in_ruleAKTION3004); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getAntragWiderspruchZulassenKeyword_17()); 
                        

                    }
                    break;
                case 19 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1164:2: kw= 'AntragWiderspruchZulZurueck'
                    {
                    kw=(Token)match(input,50,FOLLOW_50_in_ruleAKTION3023); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getAntragWiderspruchZulZurueckKeyword_18()); 
                        

                    }
                    break;
                case 20 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1171:2: kw= 'AntragWiderspruchStattgeben'
                    {
                    kw=(Token)match(input,51,FOLLOW_51_in_ruleAKTION3042); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getAKTIONAccess().getAntragWiderspruchStattgebenKeyword_19()); 
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAKTION"


    // $ANTLR start "entryRuleKLASSE"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1184:1: entryRuleKLASSE returns [String current=null] : iv_ruleKLASSE= ruleKLASSE EOF ;
    public final String entryRuleKLASSE() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleKLASSE = null;


        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1185:2: (iv_ruleKLASSE= ruleKLASSE EOF )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1186:2: iv_ruleKLASSE= ruleKLASSE EOF
            {
             newCompositeNode(grammarAccess.getKLASSERule()); 
            pushFollow(FOLLOW_ruleKLASSE_in_entryRuleKLASSE3083);
            iv_ruleKLASSE=ruleKLASSE();

            state._fsp--;

             current =iv_ruleKLASSE.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleKLASSE3094); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKLASSE"


    // $ANTLR start "ruleKLASSE"
    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1193:1: ruleKLASSE returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusAusRechenschritt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusBagatellbetragTeilmassnahmen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PA4AugenPrinzip' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAblehnungsgruendeVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenArt17Abs1' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenBezugVerstoesseVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngabenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalschGemachteUnregelmaessigkeit' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbtretungenInZahlungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzuegeErhoehung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngabenBeendetOhneAenderungsblatt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAltverpflichtungUeberschritten' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenAUM' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenErsterfassungBeendetAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitungStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageTierhaltung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilBluehflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreide' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreideAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGruenland' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilKkLeguminosenAL5Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosenAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingang' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangNichtNachAusschlussTermin' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsteller' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstellerKeineJuristischePerson' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstermin' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsterminNichtVerfristet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJBewilligt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJNichtAbgelehnt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragZurueckziehen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenInklusiveLeguminosen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtGroesserAls100ProHa' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtKleinerAls30' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappe' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappeAktuellUndDurchgefuehrt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlatt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaUZWAktuellUndBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweis' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweisBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusbringungstechnik' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuellt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusschluss' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragAuszahlung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungBeruecksichtigungHalbeZahlung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2002' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2007' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungUeberGesamteLaufzeit' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragNeuAntrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragRueckforderung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindungStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragteFlaecheKleiner80Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtGLGleichGesamtGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtMindestens5ProzAckerflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner10ProzAckerflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner2ha' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner5ProzAckerflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzAusHITUnterBeruecksichtigungGruenland' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_2' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_3' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABescheidInAktuellerBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABestaetigungsVermerk' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABewilligterNAImEAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuell' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuellUndBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADatumEingangGroesserAntrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzBewilligtVJUndBeantragtGroesserBagatellbetrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaecheWiederholt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCC' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCV' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCVImVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGLSchwellwert' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangVerpflichtungsflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEntscheidungenBescheidart' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErstantragsjahr' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10bzw2haOdGroesser50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10odGroesser50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50Proz' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50ProzOder2Ha' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungLE50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungsflaecheVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAExtensiveBewirtschaftungGLGleichGesamtGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngabenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs5' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6inAnderemFP' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt18Abs3' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschGemachteAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerderfaehigGroesserNull' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerdergebietGroesserGleich3Hektar' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM141Mindestens5ProzentAFAusEaj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM14Mindestens5ProzentAFAusEaj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheStreuobstwiesen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGebuehrenrechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung30Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung50Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtrueckforderung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtsanktionierung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuGross' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuKlein' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrfImAA' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrobFahrlaessigeGemachteAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGuellemenge' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAHofuebergabe' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerBestaetigungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerVereinbarungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKapitalbeteiligungOeffentlHandGroesser25Proz' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinAntragVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinBescheidInAktuellerBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinDungAufnahmeOderAbgabe' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhandenTm' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineParalleBeantragungM5UndM6' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinePheromonGemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAktiengesellschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAnstaltDesOeffentlRechts' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformGmbH' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformJuristischePersonOeffentlRecht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformKoerperschaftDesOeffentlichenRechts' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformOeffentlRechtlStiftung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformPrivatRechtlStiftung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonOeffentlRecht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonPrivatRecht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeNatuerlichePerson' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRelevantenEntscheidungenOffen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformAnerkannteWeidegemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformEinzelantragstellerMeka' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformPheromongemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformWaldgemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinFC104anBindungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinGLAusErzeugungGenommenNutzung592' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinKlaerschlammAusgebracht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB2' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKombinierteGLAntraege' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollkostenzuschussMitND2Teilmassnahme' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollprotokoll' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollvertrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung20Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung30Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentB1610' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALaborbeanstandungenLiegenNichtVor' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALandwirt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaschinelleBerechnungVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF2010' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestFlaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestumfangWinterbegruenung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3GveProHaLNF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_5RgvProHaGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_6GVEProHaFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMittelverwaltung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANachOeffnenBerechnet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtAlleChecksWurdenBearbeitet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtZuWenigAckerfutterAngebaut' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANurNachberechnungStornierbar' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelBewilligterNABeiEajGleichAj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAAEntschieden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragFP773' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragZuFP774' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendig' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendigLZJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungFlaechenmappeVj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungVj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleich' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichBeendetVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichDurchgefuehrtVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaenger' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaengerAlsEinzelunternehmer' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeit' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitEinJahr' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitVNS' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVJVokAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVok' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokCc' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungenOderNullzahlung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASGAbgleich' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstFeststellungenAbsichtlUnregelmaessigkeiten' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASperrvermerkNichtVergeben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStammdatenAktuell' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStichtagHelper' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATeilmassnahmeND1UndND2Beantragt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbesatzGesamt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFundGveProHaLF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFZusNCs' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUmwandlungALInGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenAusserhalbDerEU' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenOhneSchafeZiegenMitVertragVereinbarung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenssitzInBw' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschiedBerechneteUndManuelleVerpflFlFJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschreitungTierbestzDurchFeststellung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNC' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNcVNS' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheAckerfutter' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentimVJVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18Beanstandungen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18BeanstandungenVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJFuerMindEineBindung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsNrFuerAlleFlaechenVergeben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsnummer' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVeterinaerBestaetigungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKCCBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVokNichtVerweigert' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKPEnthaeltBenutzerdefPruefkonf' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKReferenzflaechenAbgleich' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragsteller' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragstellerStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWeidetagebuchImPebVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWiderspruchInVorherigerBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFK' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PASperrvermerkNichtVergeben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' ) ;
    public final AntlrDatatypeRuleToken ruleKLASSE() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1196:28: ( (kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusAusRechenschritt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusBagatellbetragTeilmassnahmen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PA4AugenPrinzip' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAblehnungsgruendeVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenArt17Abs1' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenBezugVerstoesseVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngabenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalschGemachteUnregelmaessigkeit' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbtretungenInZahlungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzuegeErhoehung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngabenBeendetOhneAenderungsblatt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAltverpflichtungUeberschritten' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenAUM' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenErsterfassungBeendetAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitungStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageTierhaltung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilBluehflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreide' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreideAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGruenland' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilKkLeguminosenAL5Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosenAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingang' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangNichtNachAusschlussTermin' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsteller' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstellerKeineJuristischePerson' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstermin' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsterminNichtVerfristet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJBewilligt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJNichtAbgelehnt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragZurueckziehen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenInklusiveLeguminosen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtGroesserAls100ProHa' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtKleinerAls30' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappe' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappeAktuellUndDurchgefuehrt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlatt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaUZWAktuellUndBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweis' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweisBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusbringungstechnik' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuellt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusschluss' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragAuszahlung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungBeruecksichtigungHalbeZahlung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2002' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2007' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungUeberGesamteLaufzeit' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragNeuAntrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragRueckforderung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindungStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragteFlaecheKleiner80Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtGLGleichGesamtGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtMindestens5ProzAckerflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner10ProzAckerflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner2ha' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner5ProzAckerflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzAusHITUnterBeruecksichtigungGruenland' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_2' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_3' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABescheidInAktuellerBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABestaetigungsVermerk' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABewilligterNAImEAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuell' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuellUndBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADatumEingangGroesserAntrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzBewilligtVJUndBeantragtGroesserBagatellbetrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaecheWiederholt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCC' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCV' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCVImVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGLSchwellwert' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangVerpflichtungsflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEntscheidungenBescheidart' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErstantragsjahr' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10bzw2haOdGroesser50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10odGroesser50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50Proz' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50ProzOder2Ha' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungLE50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungsflaecheVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAExtensiveBewirtschaftungGLGleichGesamtGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngabenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs5' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6inAnderemFP' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt18Abs3' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschGemachteAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerderfaehigGroesserNull' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerdergebietGroesserGleich3Hektar' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM141Mindestens5ProzentAFAusEaj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM14Mindestens5ProzentAFAusEaj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheStreuobstwiesen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGebuehrenrechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung30Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung50Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtrueckforderung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtsanktionierung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuGross' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuKlein' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrfImAA' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrobFahrlaessigeGemachteAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGuellemenge' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAHofuebergabe' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerBestaetigungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerVereinbarungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKapitalbeteiligungOeffentlHandGroesser25Proz' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinAntragVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinBescheidInAktuellerBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinDungAufnahmeOderAbgabe' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhandenTm' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineParalleBeantragungM5UndM6' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinePheromonGemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAktiengesellschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAnstaltDesOeffentlRechts' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformGmbH' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformJuristischePersonOeffentlRecht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformKoerperschaftDesOeffentlichenRechts' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformOeffentlRechtlStiftung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformPrivatRechtlStiftung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonOeffentlRecht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonPrivatRecht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeNatuerlichePerson' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRelevantenEntscheidungenOffen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformAnerkannteWeidegemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformEinzelantragstellerMeka' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformPheromongemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformWaldgemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinFC104anBindungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinGLAusErzeugungGenommenNutzung592' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinKlaerschlammAusgebracht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB2' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKombinierteGLAntraege' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollkostenzuschussMitND2Teilmassnahme' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollprotokoll' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollvertrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung20Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung30Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentB1610' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALaborbeanstandungenLiegenNichtVor' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALandwirt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaschinelleBerechnungVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF2010' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestFlaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestumfangWinterbegruenung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3GveProHaLNF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_5RgvProHaGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_6GVEProHaFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMittelverwaltung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANachOeffnenBerechnet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtAlleChecksWurdenBearbeitet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtZuWenigAckerfutterAngebaut' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANurNachberechnungStornierbar' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelBewilligterNABeiEajGleichAj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAAEntschieden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragFP773' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragZuFP774' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendig' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendigLZJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungFlaechenmappeVj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungVj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleich' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichBeendetVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichDurchgefuehrtVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaenger' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaengerAlsEinzelunternehmer' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeit' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitEinJahr' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitVNS' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVJVokAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVok' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokCc' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungenOderNullzahlung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASGAbgleich' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstFeststellungenAbsichtlUnregelmaessigkeiten' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASperrvermerkNichtVergeben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStammdatenAktuell' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStichtagHelper' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATeilmassnahmeND1UndND2Beantragt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbesatzGesamt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFundGveProHaLF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFZusNCs' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUmwandlungALInGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenAusserhalbDerEU' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenOhneSchafeZiegenMitVertragVereinbarung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenssitzInBw' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschiedBerechneteUndManuelleVerpflFlFJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschreitungTierbestzDurchFeststellung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNC' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNcVNS' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheAckerfutter' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentimVJVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18Beanstandungen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18BeanstandungenVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJFuerMindEineBindung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsNrFuerAlleFlaechenVergeben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsnummer' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVeterinaerBestaetigungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKCCBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVokNichtVerweigert' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKPEnthaeltBenutzerdefPruefkonf' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKReferenzflaechenAbgleich' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragsteller' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragstellerStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWeidetagebuchImPebVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWiderspruchInVorherigerBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFK' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PASperrvermerkNichtVergeben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' ) )
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1197:1: (kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusAusRechenschritt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusBagatellbetragTeilmassnahmen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PA4AugenPrinzip' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAblehnungsgruendeVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenArt17Abs1' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenBezugVerstoesseVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngabenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalschGemachteUnregelmaessigkeit' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbtretungenInZahlungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzuegeErhoehung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngabenBeendetOhneAenderungsblatt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAltverpflichtungUeberschritten' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenAUM' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenErsterfassungBeendetAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitungStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageTierhaltung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilBluehflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreide' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreideAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGruenland' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilKkLeguminosenAL5Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosenAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingang' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangNichtNachAusschlussTermin' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsteller' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstellerKeineJuristischePerson' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstermin' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsterminNichtVerfristet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJBewilligt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJNichtAbgelehnt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragZurueckziehen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenInklusiveLeguminosen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtGroesserAls100ProHa' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtKleinerAls30' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappe' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappeAktuellUndDurchgefuehrt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlatt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaUZWAktuellUndBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweis' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweisBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusbringungstechnik' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuellt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusschluss' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragAuszahlung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungBeruecksichtigungHalbeZahlung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2002' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2007' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungUeberGesamteLaufzeit' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragNeuAntrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragRueckforderung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindungStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragteFlaecheKleiner80Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtGLGleichGesamtGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtMindestens5ProzAckerflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner10ProzAckerflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner2ha' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner5ProzAckerflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzAusHITUnterBeruecksichtigungGruenland' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_2' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_3' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABescheidInAktuellerBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABestaetigungsVermerk' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABewilligterNAImEAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuell' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuellUndBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADatumEingangGroesserAntrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzBewilligtVJUndBeantragtGroesserBagatellbetrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaecheWiederholt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCC' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCV' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCVImVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGLSchwellwert' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangVerpflichtungsflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEntscheidungenBescheidart' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErstantragsjahr' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10bzw2haOdGroesser50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10odGroesser50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50Proz' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50ProzOder2Ha' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungLE50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungsflaecheVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAExtensiveBewirtschaftungGLGleichGesamtGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngabenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs5' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6inAnderemFP' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt18Abs3' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschGemachteAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerderfaehigGroesserNull' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerdergebietGroesserGleich3Hektar' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM141Mindestens5ProzentAFAusEaj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM14Mindestens5ProzentAFAusEaj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheStreuobstwiesen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGebuehrenrechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung30Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung50Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtrueckforderung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtsanktionierung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuGross' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuKlein' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrfImAA' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrobFahrlaessigeGemachteAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGuellemenge' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAHofuebergabe' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerBestaetigungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerVereinbarungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKapitalbeteiligungOeffentlHandGroesser25Proz' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinAntragVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinBescheidInAktuellerBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinDungAufnahmeOderAbgabe' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhandenTm' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineParalleBeantragungM5UndM6' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinePheromonGemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAktiengesellschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAnstaltDesOeffentlRechts' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformGmbH' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformJuristischePersonOeffentlRecht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformKoerperschaftDesOeffentlichenRechts' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformOeffentlRechtlStiftung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformPrivatRechtlStiftung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonOeffentlRecht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonPrivatRecht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeNatuerlichePerson' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRelevantenEntscheidungenOffen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformAnerkannteWeidegemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformEinzelantragstellerMeka' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformPheromongemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformWaldgemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinFC104anBindungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinGLAusErzeugungGenommenNutzung592' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinKlaerschlammAusgebracht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB2' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKombinierteGLAntraege' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollkostenzuschussMitND2Teilmassnahme' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollprotokoll' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollvertrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung20Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung30Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentB1610' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALaborbeanstandungenLiegenNichtVor' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALandwirt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaschinelleBerechnungVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF2010' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestFlaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestumfangWinterbegruenung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3GveProHaLNF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_5RgvProHaGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_6GVEProHaFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMittelverwaltung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANachOeffnenBerechnet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtAlleChecksWurdenBearbeitet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtZuWenigAckerfutterAngebaut' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANurNachberechnungStornierbar' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelBewilligterNABeiEajGleichAj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAAEntschieden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragFP773' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragZuFP774' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendig' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendigLZJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungFlaechenmappeVj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungVj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleich' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichBeendetVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichDurchgefuehrtVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaenger' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaengerAlsEinzelunternehmer' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeit' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitEinJahr' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitVNS' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVJVokAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVok' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokCc' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungenOderNullzahlung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASGAbgleich' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstFeststellungenAbsichtlUnregelmaessigkeiten' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASperrvermerkNichtVergeben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStammdatenAktuell' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStichtagHelper' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATeilmassnahmeND1UndND2Beantragt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbesatzGesamt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFundGveProHaLF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFZusNCs' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUmwandlungALInGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenAusserhalbDerEU' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenOhneSchafeZiegenMitVertragVereinbarung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenssitzInBw' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschiedBerechneteUndManuelleVerpflFlFJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschreitungTierbestzDurchFeststellung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNC' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNcVNS' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheAckerfutter' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentimVJVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18Beanstandungen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18BeanstandungenVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJFuerMindEineBindung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsNrFuerAlleFlaechenVergeben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsnummer' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVeterinaerBestaetigungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKCCBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVokNichtVerweigert' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKPEnthaeltBenutzerdefPruefkonf' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKReferenzflaechenAbgleich' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragsteller' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragstellerStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWeidetagebuchImPebVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWiderspruchInVorherigerBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFK' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PASperrvermerkNichtVergeben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' )
            {
            // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1197:1: (kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusAusRechenschritt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusBagatellbetragTeilmassnahmen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PA4AugenPrinzip' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAblehnungsgruendeVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenArt17Abs1' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenBezugVerstoesseVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngabenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalschGemachteUnregelmaessigkeit' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbtretungenInZahlungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzuegeErhoehung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngabenBeendetOhneAenderungsblatt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAltverpflichtungUeberschritten' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenAUM' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenErsterfassungBeendetAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitungStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageTierhaltung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilBluehflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreide' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreideAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGruenland' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilKkLeguminosenAL5Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosenAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingang' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangNichtNachAusschlussTermin' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsteller' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstellerKeineJuristischePerson' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstermin' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsterminNichtVerfristet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJBewilligt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJNichtAbgelehnt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragZurueckziehen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenInklusiveLeguminosen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtGroesserAls100ProHa' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtKleinerAls30' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappe' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappeAktuellUndDurchgefuehrt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlatt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaUZWAktuellUndBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweis' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweisBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusbringungstechnik' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuellt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusschluss' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragAuszahlung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungBeruecksichtigungHalbeZahlung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2002' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2007' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungUeberGesamteLaufzeit' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragNeuAntrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragRueckforderung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindungStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragteFlaecheKleiner80Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtGLGleichGesamtGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtMindestens5ProzAckerflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner10ProzAckerflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner2ha' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner5ProzAckerflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzAusHITUnterBeruecksichtigungGruenland' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_2' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_3' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABescheidInAktuellerBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABestaetigungsVermerk' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABewilligterNAImEAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuell' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuellUndBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADatumEingangGroesserAntrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzBewilligtVJUndBeantragtGroesserBagatellbetrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaecheWiederholt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCC' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCV' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCVImVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGLSchwellwert' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangVerpflichtungsflaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEntscheidungenBescheidart' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErstantragsjahr' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10bzw2haOdGroesser50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10odGroesser50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50Proz' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50ProzOder2Ha' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungLE50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungsflaecheVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAExtensiveBewirtschaftungGLGleichGesamtGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngabenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs5' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6inAnderemFP' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt18Abs3' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschGemachteAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerderfaehigGroesserNull' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerdergebietGroesserGleich3Hektar' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM141Mindestens5ProzentAFAusEaj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM14Mindestens5ProzentAFAusEaj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheStreuobstwiesen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGebuehrenrechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung30Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung50Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtrueckforderung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtsanktionierung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuGross' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuKlein' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrfImAA' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrobFahrlaessigeGemachteAngaben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGuellemenge' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAHofuebergabe' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerBestaetigungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerVereinbarungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKapitalbeteiligungOeffentlHandGroesser25Proz' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinAntragVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinBescheidInAktuellerBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinDungAufnahmeOderAbgabe' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhandenTm' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineParalleBeantragungM5UndM6' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinePheromonGemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAktiengesellschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAnstaltDesOeffentlRechts' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformGmbH' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformJuristischePersonOeffentlRecht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformKoerperschaftDesOeffentlichenRechts' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformOeffentlRechtlStiftung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformPrivatRechtlStiftung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonOeffentlRecht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonPrivatRecht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeNatuerlichePerson' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRelevantenEntscheidungenOffen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformAnerkannteWeidegemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformEinzelantragstellerMeka' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformPheromongemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformWaldgemeinschaft' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinFC104anBindungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinGLAusErzeugungGenommenNutzung592' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinKlaerschlammAusgebracht' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB2' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKombinierteGLAntraege' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollkostenzuschussMitND2Teilmassnahme' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollprotokoll' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollvertrag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung20Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung30Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50Prozent' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentB1610' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALaborbeanstandungenLiegenNichtVor' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALandwirt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaschinelleBerechnungVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF2010' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestFlaeche' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestumfangWinterbegruenung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3GveProHaLNF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusHIT' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_5RgvProHaGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_6GVEProHaFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMittelverwaltung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANachOeffnenBerechnet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtAlleChecksWurdenBearbeitet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtZuWenigAckerfutterAngebaut' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANurNachberechnungStornierbar' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelBewilligterNABeiEajGleichAj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAAEntschieden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragFP773' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragZuFP774' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendig' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendigLZJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungFlaechenmappeVj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungVj' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleich' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichBeendetVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichDurchgefuehrtVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaenger' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaengerAlsEinzelunternehmer' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeit' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitEinJahr' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitVNS' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVJVokAJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVok' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokCc' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungenOderNullzahlung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASGAbgleich' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstFeststellungenAbsichtlUnregelmaessigkeiten' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASperrvermerkNichtVergeben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStammdatenAktuell' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStichtagHelper' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATeilmassnahmeND1UndND2Beantragt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbesatzGesamt' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFundGveProHaLF' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFZusNCs' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUmwandlungALInGL' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenAusserhalbDerEU' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenOhneSchafeZiegenMitVertragVereinbarung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenssitzInBw' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschiedBerechneteUndManuelleVerpflFlFJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschreitungTierbestzDurchFeststellung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNC' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNcVNS' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheAckerfutter' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentimVJVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18Beanstandungen' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18BeanstandungenVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJFuerMindEineBindung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsNrFuerAlleFlaechenVergeben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsnummer' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVeterinaerBestaetigungVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKCCBeendet' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVokNichtVerweigert' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKPEnthaeltBenutzerdefPruefkonf' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKReferenzflaechenAbgleich' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragsteller' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragstellerStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWeidetagebuchImPebVorhanden' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWiderspruchInVorherigerBerechnung' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFK' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKStichtag' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKVJ' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PASperrvermerkNichtVergeben' | kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' )
            int alt11=270;
            switch ( input.LA(1) ) {
            case 52:
                {
                alt11=1;
                }
                break;
            case 53:
                {
                alt11=2;
                }
                break;
            case 54:
                {
                alt11=3;
                }
                break;
            case 55:
                {
                alt11=4;
                }
                break;
            case 56:
                {
                alt11=5;
                }
                break;
            case 57:
                {
                alt11=6;
                }
                break;
            case 58:
                {
                alt11=7;
                }
                break;
            case 59:
                {
                alt11=8;
                }
                break;
            case 60:
                {
                alt11=9;
                }
                break;
            case 61:
                {
                alt11=10;
                }
                break;
            case 62:
                {
                alt11=11;
                }
                break;
            case 63:
                {
                alt11=12;
                }
                break;
            case 64:
                {
                alt11=13;
                }
                break;
            case 65:
                {
                alt11=14;
                }
                break;
            case 66:
                {
                alt11=15;
                }
                break;
            case 67:
                {
                alt11=16;
                }
                break;
            case 68:
                {
                alt11=17;
                }
                break;
            case 69:
                {
                alt11=18;
                }
                break;
            case 70:
                {
                alt11=19;
                }
                break;
            case 71:
                {
                alt11=20;
                }
                break;
            case 72:
                {
                alt11=21;
                }
                break;
            case 73:
                {
                alt11=22;
                }
                break;
            case 74:
                {
                alt11=23;
                }
                break;
            case 75:
                {
                alt11=24;
                }
                break;
            case 76:
                {
                alt11=25;
                }
                break;
            case 77:
                {
                alt11=26;
                }
                break;
            case 78:
                {
                alt11=27;
                }
                break;
            case 79:
                {
                alt11=28;
                }
                break;
            case 80:
                {
                alt11=29;
                }
                break;
            case 81:
                {
                alt11=30;
                }
                break;
            case 82:
                {
                alt11=31;
                }
                break;
            case 83:
                {
                alt11=32;
                }
                break;
            case 84:
                {
                alt11=33;
                }
                break;
            case 85:
                {
                alt11=34;
                }
                break;
            case 86:
                {
                alt11=35;
                }
                break;
            case 87:
                {
                alt11=36;
                }
                break;
            case 88:
                {
                alt11=37;
                }
                break;
            case 89:
                {
                alt11=38;
                }
                break;
            case 90:
                {
                alt11=39;
                }
                break;
            case 91:
                {
                alt11=40;
                }
                break;
            case 92:
                {
                alt11=41;
                }
                break;
            case 93:
                {
                alt11=42;
                }
                break;
            case 94:
                {
                alt11=43;
                }
                break;
            case 95:
                {
                alt11=44;
                }
                break;
            case 96:
                {
                alt11=45;
                }
                break;
            case 97:
                {
                alt11=46;
                }
                break;
            case 98:
                {
                alt11=47;
                }
                break;
            case 99:
                {
                alt11=48;
                }
                break;
            case 100:
                {
                alt11=49;
                }
                break;
            case 101:
                {
                alt11=50;
                }
                break;
            case 102:
                {
                alt11=51;
                }
                break;
            case 103:
                {
                alt11=52;
                }
                break;
            case 104:
                {
                alt11=53;
                }
                break;
            case 105:
                {
                alt11=54;
                }
                break;
            case 106:
                {
                alt11=55;
                }
                break;
            case 107:
                {
                alt11=56;
                }
                break;
            case 108:
                {
                alt11=57;
                }
                break;
            case 109:
                {
                alt11=58;
                }
                break;
            case 110:
                {
                alt11=59;
                }
                break;
            case 111:
                {
                alt11=60;
                }
                break;
            case 112:
                {
                alt11=61;
                }
                break;
            case 113:
                {
                alt11=62;
                }
                break;
            case 114:
                {
                alt11=63;
                }
                break;
            case 115:
                {
                alt11=64;
                }
                break;
            case 116:
                {
                alt11=65;
                }
                break;
            case 117:
                {
                alt11=66;
                }
                break;
            case 118:
                {
                alt11=67;
                }
                break;
            case 119:
                {
                alt11=68;
                }
                break;
            case 120:
                {
                alt11=69;
                }
                break;
            case 121:
                {
                alt11=70;
                }
                break;
            case 122:
                {
                alt11=71;
                }
                break;
            case 123:
                {
                alt11=72;
                }
                break;
            case 124:
                {
                alt11=73;
                }
                break;
            case 125:
                {
                alt11=74;
                }
                break;
            case 126:
                {
                alt11=75;
                }
                break;
            case 127:
                {
                alt11=76;
                }
                break;
            case 128:
                {
                alt11=77;
                }
                break;
            case 129:
                {
                alt11=78;
                }
                break;
            case 130:
                {
                alt11=79;
                }
                break;
            case 131:
                {
                alt11=80;
                }
                break;
            case 132:
                {
                alt11=81;
                }
                break;
            case 133:
                {
                alt11=82;
                }
                break;
            case 134:
                {
                alt11=83;
                }
                break;
            case 135:
                {
                alt11=84;
                }
                break;
            case 136:
                {
                alt11=85;
                }
                break;
            case 137:
                {
                alt11=86;
                }
                break;
            case 138:
                {
                alt11=87;
                }
                break;
            case 139:
                {
                alt11=88;
                }
                break;
            case 140:
                {
                alt11=89;
                }
                break;
            case 141:
                {
                alt11=90;
                }
                break;
            case 142:
                {
                alt11=91;
                }
                break;
            case 143:
                {
                alt11=92;
                }
                break;
            case 144:
                {
                alt11=93;
                }
                break;
            case 145:
                {
                alt11=94;
                }
                break;
            case 146:
                {
                alt11=95;
                }
                break;
            case 147:
                {
                alt11=96;
                }
                break;
            case 148:
                {
                alt11=97;
                }
                break;
            case 149:
                {
                alt11=98;
                }
                break;
            case 150:
                {
                alt11=99;
                }
                break;
            case 151:
                {
                alt11=100;
                }
                break;
            case 152:
                {
                alt11=101;
                }
                break;
            case 153:
                {
                alt11=102;
                }
                break;
            case 154:
                {
                alt11=103;
                }
                break;
            case 155:
                {
                alt11=104;
                }
                break;
            case 156:
                {
                alt11=105;
                }
                break;
            case 157:
                {
                alt11=106;
                }
                break;
            case 158:
                {
                alt11=107;
                }
                break;
            case 159:
                {
                alt11=108;
                }
                break;
            case 160:
                {
                alt11=109;
                }
                break;
            case 161:
                {
                alt11=110;
                }
                break;
            case 162:
                {
                alt11=111;
                }
                break;
            case 163:
                {
                alt11=112;
                }
                break;
            case 164:
                {
                alt11=113;
                }
                break;
            case 165:
                {
                alt11=114;
                }
                break;
            case 166:
                {
                alt11=115;
                }
                break;
            case 167:
                {
                alt11=116;
                }
                break;
            case 168:
                {
                alt11=117;
                }
                break;
            case 169:
                {
                alt11=118;
                }
                break;
            case 170:
                {
                alt11=119;
                }
                break;
            case 171:
                {
                alt11=120;
                }
                break;
            case 172:
                {
                alt11=121;
                }
                break;
            case 173:
                {
                alt11=122;
                }
                break;
            case 174:
                {
                alt11=123;
                }
                break;
            case 175:
                {
                alt11=124;
                }
                break;
            case 176:
                {
                alt11=125;
                }
                break;
            case 177:
                {
                alt11=126;
                }
                break;
            case 178:
                {
                alt11=127;
                }
                break;
            case 179:
                {
                alt11=128;
                }
                break;
            case 180:
                {
                alt11=129;
                }
                break;
            case 181:
                {
                alt11=130;
                }
                break;
            case 182:
                {
                alt11=131;
                }
                break;
            case 183:
                {
                alt11=132;
                }
                break;
            case 184:
                {
                alt11=133;
                }
                break;
            case 185:
                {
                alt11=134;
                }
                break;
            case 186:
                {
                alt11=135;
                }
                break;
            case 187:
                {
                alt11=136;
                }
                break;
            case 188:
                {
                alt11=137;
                }
                break;
            case 189:
                {
                alt11=138;
                }
                break;
            case 190:
                {
                alt11=139;
                }
                break;
            case 191:
                {
                alt11=140;
                }
                break;
            case 192:
                {
                alt11=141;
                }
                break;
            case 193:
                {
                alt11=142;
                }
                break;
            case 194:
                {
                alt11=143;
                }
                break;
            case 195:
                {
                alt11=144;
                }
                break;
            case 196:
                {
                alt11=145;
                }
                break;
            case 197:
                {
                alt11=146;
                }
                break;
            case 198:
                {
                alt11=147;
                }
                break;
            case 199:
                {
                alt11=148;
                }
                break;
            case 200:
                {
                alt11=149;
                }
                break;
            case 201:
                {
                alt11=150;
                }
                break;
            case 202:
                {
                alt11=151;
                }
                break;
            case 203:
                {
                alt11=152;
                }
                break;
            case 204:
                {
                alt11=153;
                }
                break;
            case 205:
                {
                alt11=154;
                }
                break;
            case 206:
                {
                alt11=155;
                }
                break;
            case 207:
                {
                alt11=156;
                }
                break;
            case 208:
                {
                alt11=157;
                }
                break;
            case 209:
                {
                alt11=158;
                }
                break;
            case 210:
                {
                alt11=159;
                }
                break;
            case 211:
                {
                alt11=160;
                }
                break;
            case 212:
                {
                alt11=161;
                }
                break;
            case 213:
                {
                alt11=162;
                }
                break;
            case 214:
                {
                alt11=163;
                }
                break;
            case 215:
                {
                alt11=164;
                }
                break;
            case 216:
                {
                alt11=165;
                }
                break;
            case 217:
                {
                alt11=166;
                }
                break;
            case 218:
                {
                alt11=167;
                }
                break;
            case 219:
                {
                alt11=168;
                }
                break;
            case 220:
                {
                alt11=169;
                }
                break;
            case 221:
                {
                alt11=170;
                }
                break;
            case 222:
                {
                alt11=171;
                }
                break;
            case 223:
                {
                alt11=172;
                }
                break;
            case 224:
                {
                alt11=173;
                }
                break;
            case 225:
                {
                alt11=174;
                }
                break;
            case 226:
                {
                alt11=175;
                }
                break;
            case 227:
                {
                alt11=176;
                }
                break;
            case 228:
                {
                alt11=177;
                }
                break;
            case 229:
                {
                alt11=178;
                }
                break;
            case 230:
                {
                alt11=179;
                }
                break;
            case 231:
                {
                alt11=180;
                }
                break;
            case 232:
                {
                alt11=181;
                }
                break;
            case 233:
                {
                alt11=182;
                }
                break;
            case 234:
                {
                alt11=183;
                }
                break;
            case 235:
                {
                alt11=184;
                }
                break;
            case 236:
                {
                alt11=185;
                }
                break;
            case 237:
                {
                alt11=186;
                }
                break;
            case 238:
                {
                alt11=187;
                }
                break;
            case 239:
                {
                alt11=188;
                }
                break;
            case 240:
                {
                alt11=189;
                }
                break;
            case 241:
                {
                alt11=190;
                }
                break;
            case 242:
                {
                alt11=191;
                }
                break;
            case 243:
                {
                alt11=192;
                }
                break;
            case 244:
                {
                alt11=193;
                }
                break;
            case 245:
                {
                alt11=194;
                }
                break;
            case 246:
                {
                alt11=195;
                }
                break;
            case 247:
                {
                alt11=196;
                }
                break;
            case 248:
                {
                alt11=197;
                }
                break;
            case 249:
                {
                alt11=198;
                }
                break;
            case 250:
                {
                alt11=199;
                }
                break;
            case 251:
                {
                alt11=200;
                }
                break;
            case 252:
                {
                alt11=201;
                }
                break;
            case 253:
                {
                alt11=202;
                }
                break;
            case 254:
                {
                alt11=203;
                }
                break;
            case 255:
                {
                alt11=204;
                }
                break;
            case 256:
                {
                alt11=205;
                }
                break;
            case 257:
                {
                alt11=206;
                }
                break;
            case 258:
                {
                alt11=207;
                }
                break;
            case 259:
                {
                alt11=208;
                }
                break;
            case 260:
                {
                alt11=209;
                }
                break;
            case 261:
                {
                alt11=210;
                }
                break;
            case 262:
                {
                alt11=211;
                }
                break;
            case 263:
                {
                alt11=212;
                }
                break;
            case 264:
                {
                alt11=213;
                }
                break;
            case 265:
                {
                alt11=214;
                }
                break;
            case 266:
                {
                alt11=215;
                }
                break;
            case 267:
                {
                alt11=216;
                }
                break;
            case 268:
                {
                alt11=217;
                }
                break;
            case 269:
                {
                alt11=218;
                }
                break;
            case 270:
                {
                alt11=219;
                }
                break;
            case 271:
                {
                alt11=220;
                }
                break;
            case 272:
                {
                alt11=221;
                }
                break;
            case 273:
                {
                alt11=222;
                }
                break;
            case 274:
                {
                alt11=223;
                }
                break;
            case 275:
                {
                alt11=224;
                }
                break;
            case 276:
                {
                alt11=225;
                }
                break;
            case 277:
                {
                alt11=226;
                }
                break;
            case 278:
                {
                alt11=227;
                }
                break;
            case 279:
                {
                alt11=228;
                }
                break;
            case 280:
                {
                alt11=229;
                }
                break;
            case 281:
                {
                alt11=230;
                }
                break;
            case 282:
                {
                alt11=231;
                }
                break;
            case 283:
                {
                alt11=232;
                }
                break;
            case 284:
                {
                alt11=233;
                }
                break;
            case 285:
                {
                alt11=234;
                }
                break;
            case 286:
                {
                alt11=235;
                }
                break;
            case 287:
                {
                alt11=236;
                }
                break;
            case 288:
                {
                alt11=237;
                }
                break;
            case 289:
                {
                alt11=238;
                }
                break;
            case 290:
                {
                alt11=239;
                }
                break;
            case 291:
                {
                alt11=240;
                }
                break;
            case 292:
                {
                alt11=241;
                }
                break;
            case 293:
                {
                alt11=242;
                }
                break;
            case 294:
                {
                alt11=243;
                }
                break;
            case 295:
                {
                alt11=244;
                }
                break;
            case 296:
                {
                alt11=245;
                }
                break;
            case 297:
                {
                alt11=246;
                }
                break;
            case 298:
                {
                alt11=247;
                }
                break;
            case 299:
                {
                alt11=248;
                }
                break;
            case 300:
                {
                alt11=249;
                }
                break;
            case 301:
                {
                alt11=250;
                }
                break;
            case 302:
                {
                alt11=251;
                }
                break;
            case 303:
                {
                alt11=252;
                }
                break;
            case 304:
                {
                alt11=253;
                }
                break;
            case 305:
                {
                alt11=254;
                }
                break;
            case 306:
                {
                alt11=255;
                }
                break;
            case 307:
                {
                alt11=256;
                }
                break;
            case 308:
                {
                alt11=257;
                }
                break;
            case 309:
                {
                alt11=258;
                }
                break;
            case 310:
                {
                alt11=259;
                }
                break;
            case 311:
                {
                alt11=260;
                }
                break;
            case 312:
                {
                alt11=261;
                }
                break;
            case 313:
                {
                alt11=262;
                }
                break;
            case 314:
                {
                alt11=263;
                }
                break;
            case 315:
                {
                alt11=264;
                }
                break;
            case 316:
                {
                alt11=265;
                }
                break;
            case 317:
                {
                alt11=266;
                }
                break;
            case 318:
                {
                alt11=267;
                }
                break;
            case 319:
                {
                alt11=268;
                }
                break;
            case 320:
                {
                alt11=269;
                }
                break;
            case 321:
                {
                alt11=270;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1198:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusAusRechenschritt'
                    {
                    kw=(Token)match(input,52,FOLLOW_52_in_ruleKLASSE3132); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenAbstractAumPruefalgorithmusAusRechenschrittKeyword_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1205:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusBagatellbetragTeilmassnahmen'
                    {
                    kw=(Token)match(input,53,FOLLOW_53_in_ruleKLASSE3151); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenAbstractAumPruefalgorithmusBagatellbetragTeilmassnahmenKeyword_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1212:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PA4AugenPrinzip'
                    {
                    kw=(Token)match(input,54,FOLLOW_54_in_ruleKLASSE3170); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPA4AugenPrinzipKeyword_2()); 
                        

                    }
                    break;
                case 4 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1219:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAblehnungsgruendeVorhanden'
                    {
                    kw=(Token)match(input,55,FOLLOW_55_in_ruleKLASSE3189); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAblehnungsgruendeVorhandenKeyword_3()); 
                        

                    }
                    break;
                case 5 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1226:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenArt17Abs1'
                    {
                    kw=(Token)match(input,56,FOLLOW_56_in_ruleKLASSE3208); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlicheFalschangabenArt17Abs1Keyword_4()); 
                        

                    }
                    break;
                case 6 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1233:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenBezugVerstoesseVJ'
                    {
                    kw=(Token)match(input,57,FOLLOW_57_in_ruleKLASSE3227); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlicheFalschangabenBezugVerstoesseVJKeyword_5()); 
                        

                    }
                    break;
                case 7 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1240:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngaben'
                    {
                    kw=(Token)match(input,58,FOLLOW_58_in_ruleKLASSE3246); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlichFalscheAngabenKeyword_6()); 
                        

                    }
                    break;
                case 8 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1247:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngabenStichtag'
                    {
                    kw=(Token)match(input,59,FOLLOW_59_in_ruleKLASSE3265); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlichFalscheAngabenStichtagKeyword_7()); 
                        

                    }
                    break;
                case 9 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1254:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalschGemachteUnregelmaessigkeit'
                    {
                    kw=(Token)match(input,60,FOLLOW_60_in_ruleKLASSE3284); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlichFalschGemachteUnregelmaessigkeitKeyword_8()); 
                        

                    }
                    break;
                case 10 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1261:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbtretungenInZahlungVorhanden'
                    {
                    kw=(Token)match(input,61,FOLLOW_61_in_ruleKLASSE3303); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbtretungenInZahlungVorhandenKeyword_9()); 
                        

                    }
                    break;
                case 11 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1268:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzuegeErhoehung'
                    {
                    kw=(Token)match(input,62,FOLLOW_62_in_ruleKLASSE3322); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbzuegeErhoehungKeyword_10()); 
                        

                    }
                    break;
                case 12 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1275:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaft'
                    {
                    kw=(Token)match(input,63,FOLLOW_63_in_ruleKLASSE3341); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaftKeyword_11()); 
                        

                    }
                    break;
                case 13 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1282:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngaben'
                    {
                    kw=(Token)match(input,64,FOLLOW_64_in_ruleKLASSE3360); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAllgemeineAngabenKeyword_12()); 
                        

                    }
                    break;
                case 14 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1289:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngabenBeendetOhneAenderungsblatt'
                    {
                    kw=(Token)match(input,65,FOLLOW_65_in_ruleKLASSE3379); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAllgemeineAngabenBeendetOhneAenderungsblattKeyword_13()); 
                        

                    }
                    break;
                case 15 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1296:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAltverpflichtungUeberschritten'
                    {
                    kw=(Token)match(input,66,FOLLOW_66_in_ruleKLASSE3398); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAltverpflichtungUeberschrittenKeyword_14()); 
                        

                    }
                    break;
                case 16 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1303:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenAUM'
                    {
                    kw=(Token)match(input,67,FOLLOW_67_in_ruleKLASSE3417); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenAUMKeyword_15()); 
                        

                    }
                    break;
                case 17 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1310:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendet'
                    {
                    kw=(Token)match(input,68,FOLLOW_68_in_ruleKLASSE3436); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenBeendetKeyword_16()); 
                        

                    }
                    break;
                case 18 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1317:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetStichtag'
                    {
                    kw=(Token)match(input,69,FOLLOW_69_in_ruleKLASSE3455); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenBeendetStichtagKeyword_17()); 
                        

                    }
                    break;
                case 19 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1324:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetVJ'
                    {
                    kw=(Token)match(input,70,FOLLOW_70_in_ruleKLASSE3474); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenBeendetVJKeyword_18()); 
                        

                    }
                    break;
                case 20 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1331:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenErsterfassungBeendetAJ'
                    {
                    kw=(Token)match(input,71,FOLLOW_71_in_ruleKLASSE3493); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenErsterfassungBeendetAJKeyword_19()); 
                        

                    }
                    break;
                case 21 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1338:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitung'
                    {
                    kw=(Token)match(input,72,FOLLOW_72_in_ruleKLASSE3512); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenInBearbeitungKeyword_20()); 
                        

                    }
                    break;
                case 22 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1345:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitungStichtag'
                    {
                    kw=(Token)match(input,73,FOLLOW_73_in_ruleKLASSE3531); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenInBearbeitungStichtagKeyword_21()); 
                        

                    }
                    break;
                case 23 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1352:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageTierhaltung'
                    {
                    kw=(Token)match(input,74,FOLLOW_74_in_ruleKLASSE3550); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageTierhaltungKeyword_22()); 
                        

                    }
                    break;
                case 24 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1359:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilBluehflaeche'
                    {
                    kw=(Token)match(input,75,FOLLOW_75_in_ruleKLASSE3569); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilBluehflaecheKeyword_23()); 
                        

                    }
                    break;
                case 25 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1366:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreide'
                    {
                    kw=(Token)match(input,76,FOLLOW_76_in_ruleKLASSE3588); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilGetreideKeyword_24()); 
                        

                    }
                    break;
                case 26 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1373:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreideAusnahmeArt18'
                    {
                    kw=(Token)match(input,77,FOLLOW_77_in_ruleKLASSE3607); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilGetreideAusnahmeArt18Keyword_25()); 
                        

                    }
                    break;
                case 27 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1380:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGruenland'
                    {
                    kw=(Token)match(input,78,FOLLOW_78_in_ruleKLASSE3626); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilGruenlandKeyword_26()); 
                        

                    }
                    break;
                case 28 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1387:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30Prozent'
                    {
                    kw=(Token)match(input,79,FOLLOW_79_in_ruleKLASSE3645); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilHauptfruchtartenMax30ProzentKeyword_27()); 
                        

                    }
                    break;
                case 29 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1394:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18'
                    {
                    kw=(Token)match(input,80,FOLLOW_80_in_ruleKLASSE3664); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18Keyword_28()); 
                        

                    }
                    break;
                case 30 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1401:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10Prozent'
                    {
                    kw=(Token)match(input,81,FOLLOW_81_in_ruleKLASSE3683); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilHauptfruchtartenMin10ProzentKeyword_29()); 
                        

                    }
                    break;
                case 31 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1408:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18'
                    {
                    kw=(Token)match(input,82,FOLLOW_82_in_ruleKLASSE3702); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18Keyword_30()); 
                        

                    }
                    break;
                case 32 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1415:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilKkLeguminosenAL5Prozent'
                    {
                    kw=(Token)match(input,83,FOLLOW_83_in_ruleKLASSE3721); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilKkLeguminosenAL5ProzentKeyword_31()); 
                        

                    }
                    break;
                case 33 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1422:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosen'
                    {
                    kw=(Token)match(input,84,FOLLOW_84_in_ruleKLASSE3740); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilLeguminosenKeyword_32()); 
                        

                    }
                    break;
                case 34 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1429:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosenAusnahmeArt18'
                    {
                    kw=(Token)match(input,85,FOLLOW_85_in_ruleKLASSE3759); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilLeguminosenAusnahmeArt18Keyword_33()); 
                        

                    }
                    break;
                case 35 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1436:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingang'
                    {
                    kw=(Token)match(input,86,FOLLOW_86_in_ruleKLASSE3778); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragseingangKeyword_34()); 
                        

                    }
                    break;
                case 36 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1443:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangNichtNachAusschlussTermin'
                    {
                    kw=(Token)match(input,87,FOLLOW_87_in_ruleKLASSE3797); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragseingangNichtNachAusschlussTerminKeyword_35()); 
                        

                    }
                    break;
                case 37 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1450:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangStichtag'
                    {
                    kw=(Token)match(input,88,FOLLOW_88_in_ruleKLASSE3816); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragseingangStichtagKeyword_36()); 
                        

                    }
                    break;
                case 38 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1457:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsflaeche'
                    {
                    kw=(Token)match(input,89,FOLLOW_89_in_ruleKLASSE3835); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragsflaecheKeyword_37()); 
                        

                    }
                    break;
                case 39 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1464:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsteller'
                    {
                    kw=(Token)match(input,90,FOLLOW_90_in_ruleKLASSE3854); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragstellerKeyword_38()); 
                        

                    }
                    break;
                case 40 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1471:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstellerKeineJuristischePerson'
                    {
                    kw=(Token)match(input,91,FOLLOW_91_in_ruleKLASSE3873); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragstellerKeineJuristischePersonKeyword_39()); 
                        

                    }
                    break;
                case 41 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1478:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstermin'
                    {
                    kw=(Token)match(input,92,FOLLOW_92_in_ruleKLASSE3892); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragsterminKeyword_40()); 
                        

                    }
                    break;
                case 42 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1485:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsterminNichtVerfristet'
                    {
                    kw=(Token)match(input,93,FOLLOW_93_in_ruleKLASSE3911); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragsterminNichtVerfristetKeyword_41()); 
                        

                    }
                    break;
                case 43 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1492:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJBewilligt'
                    {
                    kw=(Token)match(input,94,FOLLOW_94_in_ruleKLASSE3930); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragVJBewilligtKeyword_42()); 
                        

                    }
                    break;
                case 44 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1499:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJNichtAbgelehnt'
                    {
                    kw=(Token)match(input,95,FOLLOW_95_in_ruleKLASSE3949); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragVJNichtAbgelehntKeyword_43()); 
                        

                    }
                    break;
                case 45 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1506:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragZurueckziehen'
                    {
                    kw=(Token)match(input,96,FOLLOW_96_in_ruleKLASSE3968); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragZurueckziehenKeyword_44()); 
                        

                    }
                    break;
                case 46 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1513:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenAusnahmeArt18'
                    {
                    kw=(Token)match(input,97,FOLLOW_97_in_ruleKLASSE3987); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnzahlHauptfruchtartenAusnahmeArt18Keyword_45()); 
                        

                    }
                    break;
                case 47 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1520:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenInklusiveLeguminosen'
                    {
                    kw=(Token)match(input,98,FOLLOW_98_in_ruleKLASSE4006); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnzahlHauptfruchtartenInklusiveLeguminosenKeyword_46()); 
                        

                    }
                    break;
                case 48 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1527:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtGroesserAls100ProHa'
                    {
                    kw=(Token)match(input,99,FOLLOW_99_in_ruleKLASSE4025); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnzahlStreuobstBaeumeNichtGroesserAls100ProHaKeyword_47()); 
                        

                    }
                    break;
                case 49 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1534:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtKleinerAls30'
                    {
                    kw=(Token)match(input,100,FOLLOW_100_in_ruleKLASSE4044); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnzahlStreuobstBaeumeNichtKleinerAls30Keyword_48()); 
                        

                    }
                    break;
                case 50 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1541:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJ'
                    {
                    kw=(Token)match(input,101,FOLLOW_101_in_ruleKLASSE4063); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenAbgleichAJKeyword_49()); 
                        

                    }
                    break;
                case 51 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1548:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJStichtag'
                    {
                    kw=(Token)match(input,102,FOLLOW_102_in_ruleKLASSE4082); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenAbgleichAJStichtagKeyword_50()); 
                        

                    }
                    break;
                case 52 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1555:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichVJ'
                    {
                    kw=(Token)match(input,103,FOLLOW_103_in_ruleKLASSE4101); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenAbgleichVJKeyword_51()); 
                        

                    }
                    break;
                case 53 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1562:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappe'
                    {
                    kw=(Token)match(input,104,FOLLOW_104_in_ruleKLASSE4120); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenmappeKeyword_52()); 
                        

                    }
                    break;
                case 54 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1569:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappeAktuellUndDurchgefuehrt'
                    {
                    kw=(Token)match(input,105,FOLLOW_105_in_ruleKLASSE4139); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenmappeAktuellUndDurchgefuehrtKeyword_53()); 
                        

                    }
                    break;
                case 55 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1576:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlatt'
                    {
                    kw=(Token)match(input,106,FOLLOW_106_in_ruleKLASSE4158); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlattKeyword_54()); 
                        

                    }
                    break;
                case 56 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1583:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet'
                    {
                    kw=(Token)match(input,107,FOLLOW_107_in_ruleKLASSE4177); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumKuliZuMaAZLAktuellUndBeendetKeyword_55()); 
                        

                    }
                    break;
                case 57 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1590:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaUZWAktuellUndBeendet'
                    {
                    kw=(Token)match(input,108,FOLLOW_108_in_ruleKLASSE4196); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumKuliZuMaUZWAktuellUndBeendetKeyword_56()); 
                        

                    }
                    break;
                case 58 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1597:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweis'
                    {
                    kw=(Token)match(input,109,FOLLOW_109_in_ruleKLASSE4215); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumTierbestandsnachweisKeyword_57()); 
                        

                    }
                    break;
                case 59 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1604:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweisBeendet'
                    {
                    kw=(Token)match(input,110,FOLLOW_110_in_ruleKLASSE4234); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumTierbestandsnachweisBeendetKeyword_58()); 
                        

                    }
                    break;
                case 60 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1611:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusbringungstechnik'
                    {
                    kw=(Token)match(input,111,FOLLOW_111_in_ruleKLASSE4253); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAusbringungstechnikKeyword_59()); 
                        

                    }
                    break;
                case 61 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1618:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuellt'
                    {
                    kw=(Token)match(input,112,FOLLOW_112_in_ruleKLASSE4272); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuelltKeyword_60()); 
                        

                    }
                    break;
                case 62 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1625:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusschluss'
                    {
                    kw=(Token)match(input,113,FOLLOW_113_in_ruleKLASSE4291); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAusschlussKeyword_61()); 
                        

                    }
                    break;
                case 63 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1632:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragAuszahlung'
                    {
                    kw=(Token)match(input,114,FOLLOW_114_in_ruleKLASSE4310); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragAuszahlungKeyword_62()); 
                        

                    }
                    break;
                case 64 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1639:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnung'
                    {
                    kw=(Token)match(input,115,FOLLOW_115_in_ruleKLASSE4329); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungKeyword_63()); 
                        

                    }
                    break;
                case 65 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1646:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungBeruecksichtigungHalbeZahlung'
                    {
                    kw=(Token)match(input,116,FOLLOW_116_in_ruleKLASSE4348); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungBeruecksichtigungHalbeZahlungKeyword_64()); 
                        

                    }
                    break;
                case 66 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1653:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2002'
                    {
                    kw=(Token)match(input,117,FOLLOW_117_in_ruleKLASSE4367); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungEVP_RL2002Keyword_65()); 
                        

                    }
                    break;
                case 67 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1660:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2007'
                    {
                    kw=(Token)match(input,118,FOLLOW_118_in_ruleKLASSE4386); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungEVP_RL2007Keyword_66()); 
                        

                    }
                    break;
                case 68 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1667:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungUeberGesamteLaufzeit'
                    {
                    kw=(Token)match(input,119,FOLLOW_119_in_ruleKLASSE4405); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungUeberGesamteLaufzeitKeyword_67()); 
                        

                    }
                    break;
                case 69 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1674:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragNeuAntrag'
                    {
                    kw=(Token)match(input,120,FOLLOW_120_in_ruleKLASSE4424); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragNeuAntragKeyword_68()); 
                        

                    }
                    break;
                case 70 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1681:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragRueckforderung'
                    {
                    kw=(Token)match(input,121,FOLLOW_121_in_ruleKLASSE4443); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragRueckforderungKeyword_69()); 
                        

                    }
                    break;
                case 71 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1688:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindung'
                    {
                    kw=(Token)match(input,122,FOLLOW_122_in_ruleKLASSE4462); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABankverbindungKeyword_70()); 
                        

                    }
                    break;
                case 72 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1695:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindungStichtag'
                    {
                    kw=(Token)match(input,123,FOLLOW_123_in_ruleKLASSE4481); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABankverbindungStichtagKeyword_71()); 
                        

                    }
                    break;
                case 73 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1702:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragteFlaecheKleiner80Prozent'
                    {
                    kw=(Token)match(input,124,FOLLOW_124_in_ruleKLASSE4500); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragteFlaecheKleiner80ProzentKeyword_72()); 
                        

                    }
                    break;
                case 74 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1709:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtGLGleichGesamtGL'
                    {
                    kw=(Token)match(input,125,FOLLOW_125_in_ruleKLASSE4519); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtGLGleichGesamtGLKeyword_73()); 
                        

                    }
                    break;
                case 75 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1716:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtMindestens5ProzAckerflaeche'
                    {
                    kw=(Token)match(input,126,FOLLOW_126_in_ruleKLASSE4538); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtMindestens5ProzAckerflaecheKeyword_74()); 
                        

                    }
                    break;
                case 76 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1723:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner10ProzAckerflaeche'
                    {
                    kw=(Token)match(input,127,FOLLOW_127_in_ruleKLASSE4557); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtNichtKleiner10ProzAckerflaecheKeyword_75()); 
                        

                    }
                    break;
                case 77 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1730:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner2ha'
                    {
                    kw=(Token)match(input,128,FOLLOW_128_in_ruleKLASSE4576); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtNichtKleiner2haKeyword_76()); 
                        

                    }
                    break;
                case 78 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1737:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner5ProzAckerflaeche'
                    {
                    kw=(Token)match(input,129,FOLLOW_129_in_ruleKLASSE4595); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtNichtKleiner5ProzAckerflaecheKeyword_77()); 
                        

                    }
                    break;
                case 79 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1744:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzAusHITUnterBeruecksichtigungGruenland'
                    {
                    kw=(Token)match(input,130,FOLLOW_130_in_ruleKLASSE4614); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABesatzAusHITUnterBeruecksichtigungGruenlandKeyword_78()); 
                        

                    }
                    break;
                case 80 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1751:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_2'
                    {
                    kw=(Token)match(input,131,FOLLOW_131_in_ruleKLASSE4633); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABesatzRgvDglMinBeantragtFestgestelltGrEq0_2Keyword_79()); 
                        

                    }
                    break;
                case 81 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1758:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_3'
                    {
                    kw=(Token)match(input,132,FOLLOW_132_in_ruleKLASSE4652); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABesatzRgvDglMinBeantragtFestgestelltGrEq0_3Keyword_80()); 
                        

                    }
                    break;
                case 82 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1765:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHIT'
                    {
                    kw=(Token)match(input,133,FOLLOW_133_in_ruleKLASSE4671); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHITKeyword_81()); 
                        

                    }
                    break;
                case 83 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1772:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABescheidInAktuellerBerechnung'
                    {
                    kw=(Token)match(input,134,FOLLOW_134_in_ruleKLASSE4690); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABescheidInAktuellerBerechnungKeyword_82()); 
                        

                    }
                    break;
                case 84 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1779:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABestaetigungsVermerk'
                    {
                    kw=(Token)match(input,135,FOLLOW_135_in_ruleKLASSE4709); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABestaetigungsVermerkKeyword_83()); 
                        

                    }
                    break;
                case 85 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1786:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABewilligterNAImEAJ'
                    {
                    kw=(Token)match(input,136,FOLLOW_136_in_ruleKLASSE4728); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABewilligterNAImEAJKeyword_84()); 
                        

                    }
                    break;
                case 86 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1793:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuell'
                    {
                    kw=(Token)match(input,137,FOLLOW_137_in_ruleKLASSE4747); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPACCGesamtbewertungsmappeAktuellKeyword_85()); 
                        

                    }
                    break;
                case 87 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1800:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuellUndBeendet'
                    {
                    kw=(Token)match(input,138,FOLLOW_138_in_ruleKLASSE4766); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPACCGesamtbewertungsmappeAktuellUndBeendetKeyword_86()); 
                        

                    }
                    break;
                case 88 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1807:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADatumEingangGroesserAntrag'
                    {
                    kw=(Token)match(input,139,FOLLOW_139_in_ruleKLASSE4785); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPADatumEingangGroesserAntragKeyword_87()); 
                        

                    }
                    break;
                case 89 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1814:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzBewilligtVJUndBeantragtGroesserBagatellbetrag'
                    {
                    kw=(Token)match(input,140,FOLLOW_140_in_ruleKLASSE4804); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPADifferenzBewilligtVJUndBeantragtGroesserBagatellbetragKeyword_88()); 
                        

                    }
                    break;
                case 90 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1821:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaeche'
                    {
                    kw=(Token)match(input,141,FOLLOW_141_in_ruleKLASSE4823); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPADifferenzVerpflichtungsflaecheKeyword_89()); 
                        

                    }
                    break;
                case 91 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1828:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaecheWiederholt'
                    {
                    kw=(Token)match(input,142,FOLLOW_142_in_ruleKLASSE4842); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPADifferenzVerpflichtungsflaecheWiederholtKeyword_90()); 
                        

                    }
                    break;
                case 92 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1835:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCC'
                    {
                    kw=(Token)match(input,143,FOLLOW_143_in_ruleKLASSE4861); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungCCKeyword_91()); 
                        

                    }
                    break;
                case 93 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1842:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCV'
                    {
                    kw=(Token)match(input,144,FOLLOW_144_in_ruleKLASSE4880); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungCCVKeyword_92()); 
                        

                    }
                    break;
                case 94 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1849:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCVImVJ'
                    {
                    kw=(Token)match(input,145,FOLLOW_145_in_ruleKLASSE4899); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungCCVImVJKeyword_93()); 
                        

                    }
                    break;
                case 95 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1856:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGL'
                    {
                    kw=(Token)match(input,146,FOLLOW_146_in_ruleKLASSE4918); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungUmfangDGLKeyword_94()); 
                        

                    }
                    break;
                case 96 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1863:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGLSchwellwert'
                    {
                    kw=(Token)match(input,147,FOLLOW_147_in_ruleKLASSE4937); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungUmfangDGLSchwellwertKeyword_95()); 
                        

                    }
                    break;
                case 97 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1870:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangVerpflichtungsflaeche'
                    {
                    kw=(Token)match(input,148,FOLLOW_148_in_ruleKLASSE4956); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungUmfangVerpflichtungsflaecheKeyword_96()); 
                        

                    }
                    break;
                case 98 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1877:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEntscheidungenBescheidart'
                    {
                    kw=(Token)match(input,149,FOLLOW_149_in_ruleKLASSE4975); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEntscheidungenBescheidartKeyword_97()); 
                        

                    }
                    break;
                case 99 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1884:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErstantragsjahr'
                    {
                    kw=(Token)match(input,150,FOLLOW_150_in_ruleKLASSE4994); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErstantragsjahrKeyword_98()); 
                        

                    }
                    break;
                case 100 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1891:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10bzw2haOdGroesser50ProzentVJ'
                    {
                    kw=(Token)match(input,151,FOLLOW_151_in_ruleKLASSE5013); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungKleiner10bzw2haOdGroesser50ProzentVJKeyword_99()); 
                        

                    }
                    break;
                case 101 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1898:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10odGroesser50ProzentVJ'
                    {
                    kw=(Token)match(input,152,FOLLOW_152_in_ruleKLASSE5032); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungKleiner10odGroesser50ProzentVJKeyword_100()); 
                        

                    }
                    break;
                case 102 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1905:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50Proz'
                    {
                    kw=(Token)match(input,153,FOLLOW_153_in_ruleKLASSE5051); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungKleinerGleich50ProzKeyword_101()); 
                        

                    }
                    break;
                case 103 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1912:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50ProzOder2Ha'
                    {
                    kw=(Token)match(input,154,FOLLOW_154_in_ruleKLASSE5070); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungKleinerGleich50ProzOder2HaKeyword_102()); 
                        

                    }
                    break;
                case 104 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1919:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungLE50ProzentVJ'
                    {
                    kw=(Token)match(input,155,FOLLOW_155_in_ruleKLASSE5089); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungLE50ProzentVJKeyword_103()); 
                        

                    }
                    break;
                case 105 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1926:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungsflaecheVorhanden'
                    {
                    kw=(Token)match(input,156,FOLLOW_156_in_ruleKLASSE5108); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungsflaecheVorhandenKeyword_104()); 
                        

                    }
                    break;
                case 106 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1933:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAExtensiveBewirtschaftungGLGleichGesamtGL'
                    {
                    kw=(Token)match(input,157,FOLLOW_157_in_ruleKLASSE5127); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAExtensiveBewirtschaftungGLGleichGesamtGLKeyword_105()); 
                        

                    }
                    break;
                case 107 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1940:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngaben'
                    {
                    kw=(Token)match(input,158,FOLLOW_158_in_ruleKLASSE5146); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFahrlaessigFalscheAngabenKeyword_106()); 
                        

                    }
                    break;
                case 108 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1947:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngabenStichtag'
                    {
                    kw=(Token)match(input,159,FOLLOW_159_in_ruleKLASSE5165); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFahrlaessigFalscheAngabenStichtagKeyword_107()); 
                        

                    }
                    break;
                case 109 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1954:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs5'
                    {
                    kw=(Token)match(input,160,FOLLOW_160_in_ruleKLASSE5184); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschangabenArt16Abs5Keyword_108()); 
                        

                    }
                    break;
                case 110 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1961:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6'
                    {
                    kw=(Token)match(input,161,FOLLOW_161_in_ruleKLASSE5203); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschangabenArt16Abs6Keyword_109()); 
                        

                    }
                    break;
                case 111 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1968:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6inAnderemFP'
                    {
                    kw=(Token)match(input,162,FOLLOW_162_in_ruleKLASSE5222); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschangabenArt16Abs6inAnderemFPKeyword_110()); 
                        

                    }
                    break;
                case 112 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1975:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt18Abs3'
                    {
                    kw=(Token)match(input,163,FOLLOW_163_in_ruleKLASSE5241); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschangabenArt18Abs3Keyword_111()); 
                        

                    }
                    break;
                case 113 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1982:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschGemachteAngaben'
                    {
                    kw=(Token)match(input,164,FOLLOW_164_in_ruleKLASSE5260); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschGemachteAngabenKeyword_112()); 
                        

                    }
                    break;
                case 114 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1989:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerderfaehigGroesserNull'
                    {
                    kw=(Token)match(input,165,FOLLOW_165_in_ruleKLASSE5279); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheFoerderfaehigGroesserNullKeyword_113()); 
                        

                    }
                    break;
                case 115 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:1996:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerdergebietGroesserGleich3Hektar'
                    {
                    kw=(Token)match(input,166,FOLLOW_166_in_ruleKLASSE5298); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheFoerdergebietGroesserGleich3HektarKeyword_114()); 
                        

                    }
                    break;
                case 116 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2003:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM141Mindestens5ProzentAFAusEaj'
                    {
                    kw=(Token)match(input,167,FOLLOW_167_in_ruleKLASSE5317); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheM141Mindestens5ProzentAFAusEajKeyword_115()); 
                        

                    }
                    break;
                case 117 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2010:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM14Mindestens5ProzentAFAusEaj'
                    {
                    kw=(Token)match(input,168,FOLLOW_168_in_ruleKLASSE5336); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheM14Mindestens5ProzentAFAusEajKeyword_116()); 
                        

                    }
                    break;
                case 118 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2017:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheStreuobstwiesen'
                    {
                    kw=(Token)match(input,169,FOLLOW_169_in_ruleKLASSE5355); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheStreuobstwiesenKeyword_117()); 
                        

                    }
                    break;
                case 119 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2024:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGebuehrenrechnung'
                    {
                    kw=(Token)match(input,170,FOLLOW_170_in_ruleKLASSE5374); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGebuehrenrechnungKeyword_118()); 
                        

                    }
                    break;
                case 120 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2031:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung30Prozent'
                    {
                    kw=(Token)match(input,171,FOLLOW_171_in_ruleKLASSE5393); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGesamtabweichung30ProzentKeyword_119()); 
                        

                    }
                    break;
                case 121 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2038:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung50Prozent'
                    {
                    kw=(Token)match(input,172,FOLLOW_172_in_ruleKLASSE5412); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGesamtabweichung50ProzentKeyword_120()); 
                        

                    }
                    break;
                case 122 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2045:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtrueckforderung'
                    {
                    kw=(Token)match(input,173,FOLLOW_173_in_ruleKLASSE5431); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGesamtrueckforderungKeyword_121()); 
                        

                    }
                    break;
                case 123 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2052:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtsanktionierung'
                    {
                    kw=(Token)match(input,174,FOLLOW_174_in_ruleKLASSE5450); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGesamtsanktionierungKeyword_122()); 
                        

                    }
                    break;
                case 124 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2059:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuGross'
                    {
                    kw=(Token)match(input,175,FOLLOW_175_in_ruleKLASSE5469); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGLAnteilNichtZuGrossKeyword_123()); 
                        

                    }
                    break;
                case 125 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2066:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuKlein'
                    {
                    kw=(Token)match(input,176,FOLLOW_176_in_ruleKLASSE5488); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGLAnteilNichtZuKleinKeyword_124()); 
                        

                    }
                    break;
                case 126 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2073:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrfImAA'
                    {
                    kw=(Token)match(input,177,FOLLOW_177_in_ruleKLASSE5507); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGrfImAAKeyword_125()); 
                        

                    }
                    break;
                case 127 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2080:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrobFahrlaessigeGemachteAngaben'
                    {
                    kw=(Token)match(input,178,FOLLOW_178_in_ruleKLASSE5526); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGrobFahrlaessigeGemachteAngabenKeyword_126()); 
                        

                    }
                    break;
                case 128 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2087:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGuellemenge'
                    {
                    kw=(Token)match(input,179,FOLLOW_179_in_ruleKLASSE5545); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGuellemengeKeyword_127()); 
                        

                    }
                    break;
                case 129 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2094:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAHofuebergabe'
                    {
                    kw=(Token)match(input,180,FOLLOW_180_in_ruleKLASSE5564); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAHofuebergabeKeyword_128()); 
                        

                    }
                    break;
                case 130 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2101:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerBestaetigungVorhanden'
                    {
                    kw=(Token)match(input,181,FOLLOW_181_in_ruleKLASSE5583); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAImkerBestaetigungVorhandenKeyword_129()); 
                        

                    }
                    break;
                case 131 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2108:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerVereinbarungVorhanden'
                    {
                    kw=(Token)match(input,182,FOLLOW_182_in_ruleKLASSE5602); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAImkerVereinbarungVorhandenKeyword_130()); 
                        

                    }
                    break;
                case 132 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2115:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKapitalbeteiligungOeffentlHandGroesser25Proz'
                    {
                    kw=(Token)match(input,183,FOLLOW_183_in_ruleKLASSE5621); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKapitalbeteiligungOeffentlHandGroesser25ProzKeyword_131()); 
                        

                    }
                    break;
                case 133 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2122:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinAntragVorhanden'
                    {
                    kw=(Token)match(input,184,FOLLOW_184_in_ruleKLASSE5640); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinAntragVorhandenKeyword_132()); 
                        

                    }
                    break;
                case 134 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2129:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinBescheidInAktuellerBerechnung'
                    {
                    kw=(Token)match(input,185,FOLLOW_185_in_ruleKLASSE5659); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinBescheidInAktuellerBerechnungKeyword_133()); 
                        

                    }
                    break;
                case 135 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2136:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinDungAufnahmeOderAbgabe'
                    {
                    kw=(Token)match(input,186,FOLLOW_186_in_ruleKLASSE5678); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinDungAufnahmeOderAbgabeKeyword_134()); 
                        

                    }
                    break;
                case 136 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2143:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhanden'
                    {
                    kw=(Token)match(input,187,FOLLOW_187_in_ruleKLASSE5697); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineAblehnungsgruendeVorhandenKeyword_135()); 
                        

                    }
                    break;
                case 137 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2150:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhandenTm'
                    {
                    kw=(Token)match(input,188,FOLLOW_188_in_ruleKLASSE5716); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineAblehnungsgruendeVorhandenTmKeyword_136()); 
                        

                    }
                    break;
                case 138 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2157:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineParalleBeantragungM5UndM6'
                    {
                    kw=(Token)match(input,189,FOLLOW_189_in_ruleKLASSE5735); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineParalleBeantragungM5UndM6Keyword_137()); 
                        

                    }
                    break;
                case 139 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2164:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinePheromonGemeinschaft'
                    {
                    kw=(Token)match(input,190,FOLLOW_190_in_ruleKLASSE5754); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinePheromonGemeinschaftKeyword_138()); 
                        

                    }
                    break;
                case 140 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2171:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAktiengesellschaft'
                    {
                    kw=(Token)match(input,191,FOLLOW_191_in_ruleKLASSE5773); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformAktiengesellschaftKeyword_139()); 
                        

                    }
                    break;
                case 141 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2178:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAnstaltDesOeffentlRechts'
                    {
                    kw=(Token)match(input,192,FOLLOW_192_in_ruleKLASSE5792); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformAnstaltDesOeffentlRechtsKeyword_140()); 
                        

                    }
                    break;
                case 142 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2185:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformGmbH'
                    {
                    kw=(Token)match(input,193,FOLLOW_193_in_ruleKLASSE5811); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformGmbHKeyword_141()); 
                        

                    }
                    break;
                case 143 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2192:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformJuristischePersonOeffentlRecht'
                    {
                    kw=(Token)match(input,194,FOLLOW_194_in_ruleKLASSE5830); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformJuristischePersonOeffentlRechtKeyword_142()); 
                        

                    }
                    break;
                case 144 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2199:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformKoerperschaftDesOeffentlichenRechts'
                    {
                    kw=(Token)match(input,195,FOLLOW_195_in_ruleKLASSE5849); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformKoerperschaftDesOeffentlichenRechtsKeyword_143()); 
                        

                    }
                    break;
                case 145 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2206:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformOeffentlRechtlStiftung'
                    {
                    kw=(Token)match(input,196,FOLLOW_196_in_ruleKLASSE5868); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformOeffentlRechtlStiftungKeyword_144()); 
                        

                    }
                    break;
                case 146 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2213:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformPrivatRechtlStiftung'
                    {
                    kw=(Token)match(input,197,FOLLOW_197_in_ruleKLASSE5887); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformPrivatRechtlStiftungKeyword_145()); 
                        

                    }
                    break;
                case 147 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2220:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonOeffentlRecht'
                    {
                    kw=(Token)match(input,198,FOLLOW_198_in_ruleKLASSE5906); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformSonstigeJuristischePersonOeffentlRechtKeyword_146()); 
                        

                    }
                    break;
                case 148 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2227:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonPrivatRecht'
                    {
                    kw=(Token)match(input,199,FOLLOW_199_in_ruleKLASSE5925); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformSonstigeJuristischePersonPrivatRechtKeyword_147()); 
                        

                    }
                    break;
                case 149 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2234:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeNatuerlichePerson'
                    {
                    kw=(Token)match(input,200,FOLLOW_200_in_ruleKLASSE5944); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformSonstigeNatuerlichePersonKeyword_148()); 
                        

                    }
                    break;
                case 150 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2241:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRelevantenEntscheidungenOffen'
                    {
                    kw=(Token)match(input,201,FOLLOW_201_in_ruleKLASSE5963); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRelevantenEntscheidungenOffenKeyword_149()); 
                        

                    }
                    break;
                case 151 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2248:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformAnerkannteWeidegemeinschaft'
                    {
                    kw=(Token)match(input,202,FOLLOW_202_in_ruleKLASSE5982); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineUnternehmensformAnerkannteWeidegemeinschaftKeyword_150()); 
                        

                    }
                    break;
                case 152 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2255:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformEinzelantragstellerMeka'
                    {
                    kw=(Token)match(input,203,FOLLOW_203_in_ruleKLASSE6001); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineUnternehmensformEinzelantragstellerMekaKeyword_151()); 
                        

                    }
                    break;
                case 153 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2262:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformPheromongemeinschaft'
                    {
                    kw=(Token)match(input,204,FOLLOW_204_in_ruleKLASSE6020); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineUnternehmensformPheromongemeinschaftKeyword_152()); 
                        

                    }
                    break;
                case 154 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2269:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformWaldgemeinschaft'
                    {
                    kw=(Token)match(input,205,FOLLOW_205_in_ruleKLASSE6039); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineUnternehmensformWaldgemeinschaftKeyword_153()); 
                        

                    }
                    break;
                case 155 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2276:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinFC104anBindungVorhanden'
                    {
                    kw=(Token)match(input,206,FOLLOW_206_in_ruleKLASSE6058); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinFC104anBindungVorhandenKeyword_154()); 
                        

                    }
                    break;
                case 156 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2283:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinGLAusErzeugungGenommenNutzung592'
                    {
                    kw=(Token)match(input,207,FOLLOW_207_in_ruleKLASSE6077); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinGLAusErzeugungGenommenNutzung592Keyword_155()); 
                        

                    }
                    break;
                case 157 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2290:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinKlaerschlammAusgebracht'
                    {
                    kw=(Token)match(input,208,FOLLOW_208_in_ruleKLASSE6096); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinKlaerschlammAusgebrachtKeyword_156()); 
                        

                    }
                    break;
                case 158 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2297:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB'
                    {
                    kw=(Token)match(input,209,FOLLOW_209_in_ruleKLASSE6115); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinWiderspruchImPEBKeyword_157()); 
                        

                    }
                    break;
                case 159 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2304:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB2'
                    {
                    kw=(Token)match(input,210,FOLLOW_210_in_ruleKLASSE6134); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinWiderspruchImPEB2Keyword_158()); 
                        

                    }
                    break;
                case 160 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2311:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKombinierteGLAntraege'
                    {
                    kw=(Token)match(input,211,FOLLOW_211_in_ruleKLASSE6153); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKombinierteGLAntraegeKeyword_159()); 
                        

                    }
                    break;
                case 161 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2318:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollkostenzuschussMitND2Teilmassnahme'
                    {
                    kw=(Token)match(input,212,FOLLOW_212_in_ruleKLASSE6172); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKontrollkostenzuschussMitND2TeilmassnahmeKeyword_160()); 
                        

                    }
                    break;
                case 162 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2325:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollprotokoll'
                    {
                    kw=(Token)match(input,213,FOLLOW_213_in_ruleKLASSE6191); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKontrollprotokollKeyword_161()); 
                        

                    }
                    break;
                case 163 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2332:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollvertrag'
                    {
                    kw=(Token)match(input,214,FOLLOW_214_in_ruleKLASSE6210); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKontrollvertragKeyword_162()); 
                        

                    }
                    break;
                case 164 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2339:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung20Prozent'
                    {
                    kw=(Token)match(input,215,FOLLOW_215_in_ruleKLASSE6229); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung20ProzentKeyword_163()); 
                        

                    }
                    break;
                case 165 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2346:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung30Prozent'
                    {
                    kw=(Token)match(input,216,FOLLOW_216_in_ruleKLASSE6248); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung30ProzentKeyword_164()); 
                        

                    }
                    break;
                case 166 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2353:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50Prozent'
                    {
                    kw=(Token)match(input,217,FOLLOW_217_in_ruleKLASSE6267); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung50ProzentKeyword_165()); 
                        

                    }
                    break;
                case 167 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2360:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentB1610'
                    {
                    kw=(Token)match(input,218,FOLLOW_218_in_ruleKLASSE6286); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung50ProzentB1610Keyword_166()); 
                        

                    }
                    break;
                case 168 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2367:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentVJ'
                    {
                    kw=(Token)match(input,219,FOLLOW_219_in_ruleKLASSE6305); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung50ProzentVJKeyword_167()); 
                        

                    }
                    break;
                case 169 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2374:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALaborbeanstandungenLiegenNichtVor'
                    {
                    kw=(Token)match(input,220,FOLLOW_220_in_ruleKLASSE6324); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPALaborbeanstandungenLiegenNichtVorKeyword_168()); 
                        

                    }
                    break;
                case 170 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2381:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALandwirt'
                    {
                    kw=(Token)match(input,221,FOLLOW_221_in_ruleKLASSE6343); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPALandwirtKeyword_169()); 
                        

                    }
                    break;
                case 171 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2388:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogen'
                    {
                    kw=(Token)match(input,222,FOLLOW_222_in_ruleKLASSE6362); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMantelbogenKeyword_170()); 
                        

                    }
                    break;
                case 172 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2395:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogenStichtag'
                    {
                    kw=(Token)match(input,223,FOLLOW_223_in_ruleKLASSE6381); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMantelbogenStichtagKeyword_171()); 
                        

                    }
                    break;
                case 173 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2402:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaschinelleBerechnungVJ'
                    {
                    kw=(Token)match(input,224,FOLLOW_224_in_ruleKLASSE6400); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaschinelleBerechnungVJKeyword_172()); 
                        

                    }
                    break;
                case 174 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2409:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFF'
                    {
                    kw=(Token)match(input,225,FOLLOW_225_in_ruleKLASSE6419); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz1_4RgvProHaHFFKeyword_173()); 
                        

                    }
                    break;
                case 175 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2416:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusHIT'
                    {
                    kw=(Token)match(input,226,FOLLOW_226_in_ruleKLASSE6438); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz1_4RgvProHaHFFAusHITKeyword_174()); 
                        

                    }
                    break;
                case 176 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2423:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18'
                    {
                    kw=(Token)match(input,227,FOLLOW_227_in_ruleKLASSE6457); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18Keyword_175()); 
                        

                    }
                    break;
                case 177 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2430:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF'
                    {
                    kw=(Token)match(input,228,FOLLOW_228_in_ruleKLASSE6476); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz2GveProHaLNFKeyword_176()); 
                        

                    }
                    break;
                case 178 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2437:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF2010'
                    {
                    kw=(Token)match(input,229,FOLLOW_229_in_ruleKLASSE6495); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz2GveProHaLNF2010Keyword_177()); 
                        

                    }
                    break;
                case 179 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2444:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusHIT'
                    {
                    kw=(Token)match(input,230,FOLLOW_230_in_ruleKLASSE6514); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz2GveProHaLNFAusHITKeyword_178()); 
                        

                    }
                    break;
                case 180 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2451:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusnahmeArt18'
                    {
                    kw=(Token)match(input,231,FOLLOW_231_in_ruleKLASSE6533); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz2GveProHaLNFAusnahmeArt18Keyword_179()); 
                        

                    }
                    break;
                case 181 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2458:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestFlaeche'
                    {
                    kw=(Token)match(input,232,FOLLOW_232_in_ruleKLASSE6552); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMindestFlaecheKeyword_180()); 
                        

                    }
                    break;
                case 182 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2465:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestumfangWinterbegruenung'
                    {
                    kw=(Token)match(input,233,FOLLOW_233_in_ruleKLASSE6571); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMindestumfangWinterbegruenungKeyword_181()); 
                        

                    }
                    break;
                case 183 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2472:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFF'
                    {
                    kw=(Token)match(input,234,FOLLOW_234_in_ruleKLASSE6590); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_2RgvProHaHFFKeyword_182()); 
                        

                    }
                    break;
                case 184 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2479:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFFAusHIT'
                    {
                    kw=(Token)match(input,235,FOLLOW_235_in_ruleKLASSE6609); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_2RgvProHaHFFAusHITKeyword_183()); 
                        

                    }
                    break;
                case 185 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2486:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3GveProHaLNF'
                    {
                    kw=(Token)match(input,236,FOLLOW_236_in_ruleKLASSE6628); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3GveProHaLNFKeyword_184()); 
                        

                    }
                    break;
                case 186 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2493:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaGL'
                    {
                    kw=(Token)match(input,237,FOLLOW_237_in_ruleKLASSE6647); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3RgvProHaGLKeyword_185()); 
                        

                    }
                    break;
                case 187 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2500:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFF'
                    {
                    kw=(Token)match(input,238,FOLLOW_238_in_ruleKLASSE6666); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3RgvProHaHFFKeyword_186()); 
                        

                    }
                    break;
                case 188 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2507:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusHIT'
                    {
                    kw=(Token)match(input,239,FOLLOW_239_in_ruleKLASSE6685); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3RgvProHaHFFAusHITKeyword_187()); 
                        

                    }
                    break;
                case 189 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2514:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18'
                    {
                    kw=(Token)match(input,240,FOLLOW_240_in_ruleKLASSE6704); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18Keyword_188()); 
                        

                    }
                    break;
                case 190 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2521:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_5RgvProHaGL'
                    {
                    kw=(Token)match(input,241,FOLLOW_241_in_ruleKLASSE6723); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_5RgvProHaGLKeyword_189()); 
                        

                    }
                    break;
                case 191 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2528:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_6GVEProHaFF'
                    {
                    kw=(Token)match(input,242,FOLLOW_242_in_ruleKLASSE6742); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_6GVEProHaFFKeyword_190()); 
                        

                    }
                    break;
                case 192 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2535:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMittelverwaltung'
                    {
                    kw=(Token)match(input,243,FOLLOW_243_in_ruleKLASSE6761); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMittelverwaltungKeyword_191()); 
                        

                    }
                    break;
                case 193 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2542:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANachOeffnenBerechnet'
                    {
                    kw=(Token)match(input,244,FOLLOW_244_in_ruleKLASSE6780); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPANachOeffnenBerechnetKeyword_192()); 
                        

                    }
                    break;
                case 194 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2549:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtAlleChecksWurdenBearbeitet'
                    {
                    kw=(Token)match(input,245,FOLLOW_245_in_ruleKLASSE6799); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPANichtAlleChecksWurdenBearbeitetKeyword_193()); 
                        

                    }
                    break;
                case 195 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2556:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtZuWenigAckerfutterAngebaut'
                    {
                    kw=(Token)match(input,246,FOLLOW_246_in_ruleKLASSE6818); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPANichtZuWenigAckerfutterAngebautKeyword_194()); 
                        

                    }
                    break;
                case 196 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2563:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANurNachberechnungStornierbar'
                    {
                    kw=(Token)match(input,247,FOLLOW_247_in_ruleKLASSE6837); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPANurNachberechnungStornierbarKeyword_195()); 
                        

                    }
                    break;
                case 197 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2570:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelBewilligterNABeiEajGleichAj'
                    {
                    kw=(Token)match(input,248,FOLLOW_248_in_ruleKLASSE6856); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAParallelBewilligterNABeiEajGleichAjKeyword_196()); 
                        

                    }
                    break;
                case 198 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2577:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAAEntschieden'
                    {
                    kw=(Token)match(input,249,FOLLOW_249_in_ruleKLASSE6875); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAParallelerAAEntschiedenKeyword_197()); 
                        

                    }
                    break;
                case 199 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2584:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragFP773'
                    {
                    kw=(Token)match(input,250,FOLLOW_250_in_ruleKLASSE6894); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAParallelerAntragFP773Keyword_198()); 
                        

                    }
                    break;
                case 200 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2591:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragZuFP774'
                    {
                    kw=(Token)match(input,251,FOLLOW_251_in_ruleKLASSE6913); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAParallelerAntragZuFP774Keyword_199()); 
                        

                    }
                    break;
                case 201 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2598:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendig'
                    {
                    kw=(Token)match(input,252,FOLLOW_252_in_ruleKLASSE6932); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAPebVollstaendigKeyword_200()); 
                        

                    }
                    break;
                case 202 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2605:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendigLZJ'
                    {
                    kw=(Token)match(input,253,FOLLOW_253_in_ruleKLASSE6951); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAPebVollstaendigLZJKeyword_201()); 
                        

                    }
                    break;
                case 203 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2612:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherung'
                    {
                    kw=(Token)match(input,254,FOLLOW_254_in_ruleKLASSE6970); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAQualitaetssicherungKeyword_202()); 
                        

                    }
                    break;
                case 204 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2619:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungFlaechenmappeVj'
                    {
                    kw=(Token)match(input,255,FOLLOW_255_in_ruleKLASSE6989); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAQualitaetssicherungFlaechenmappeVjKeyword_203()); 
                        

                    }
                    break;
                case 205 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2626:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungVj'
                    {
                    kw=(Token)match(input,256,FOLLOW_256_in_ruleKLASSE7008); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAQualitaetssicherungVjKeyword_204()); 
                        

                    }
                    break;
                case 206 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2633:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleich'
                    {
                    kw=(Token)match(input,257,FOLLOW_257_in_ruleKLASSE7027); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAReferenzflaechenAbgleichKeyword_205()); 
                        

                    }
                    break;
                case 207 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2640:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichBeendetVJ'
                    {
                    kw=(Token)match(input,258,FOLLOW_258_in_ruleKLASSE7046); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAReferenzflaechenAbgleichBeendetVJKeyword_206()); 
                        

                    }
                    break;
                case 208 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2647:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichDurchgefuehrtVJ'
                    {
                    kw=(Token)match(input,259,FOLLOW_259_in_ruleKLASSE7065); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAReferenzflaechenAbgleichDurchgefuehrtVJKeyword_207()); 
                        

                    }
                    break;
                case 209 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2654:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaenger'
                    {
                    kw=(Token)match(input,260,FOLLOW_260_in_ruleKLASSE7084); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARentenempfaengerKeyword_208()); 
                        

                    }
                    break;
                case 210 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2661:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaengerAlsEinzelunternehmer'
                    {
                    kw=(Token)match(input,261,FOLLOW_261_in_ruleKLASSE7103); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARentenempfaengerAlsEinzelunternehmerKeyword_209()); 
                        

                    }
                    break;
                case 211 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2668:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeit'
                    {
                    kw=(Token)match(input,262,FOLLOW_262_in_ruleKLASSE7122); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARestlaufzeitKeyword_210()); 
                        

                    }
                    break;
                case 212 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2675:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitEinJahr'
                    {
                    kw=(Token)match(input,263,FOLLOW_263_in_ruleKLASSE7141); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARestlaufzeitEinJahrKeyword_211()); 
                        

                    }
                    break;
                case 213 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2682:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitVNS'
                    {
                    kw=(Token)match(input,264,FOLLOW_264_in_ruleKLASSE7160); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARestlaufzeitVNSKeyword_212()); 
                        

                    }
                    break;
                case 214 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2689:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVJVokAJ'
                    {
                    kw=(Token)match(input,265,FOLLOW_265_in_ruleKLASSE7179); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVJVokAJKeyword_213()); 
                        

                    }
                    break;
                case 215 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2696:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVok'
                    {
                    kw=(Token)match(input,266,FOLLOW_266_in_ruleKLASSE7198); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVokKeyword_214()); 
                        

                    }
                    break;
                case 216 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2703:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokCc'
                    {
                    kw=(Token)match(input,267,FOLLOW_267_in_ruleKLASSE7217); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVokCcKeyword_215()); 
                        

                    }
                    break;
                case 217 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2710:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokStichtag'
                    {
                    kw=(Token)match(input,268,FOLLOW_268_in_ruleKLASSE7236); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVokStichtagKeyword_216()); 
                        

                    }
                    break;
                case 218 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2717:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokVJ'
                    {
                    kw=(Token)match(input,269,FOLLOW_269_in_ruleKLASSE7255); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVokVJKeyword_217()); 
                        

                    }
                    break;
                case 219 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2724:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungen'
                    {
                    kw=(Token)match(input,270,FOLLOW_270_in_ruleKLASSE7274); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARueckforderungenKeyword_218()); 
                        

                    }
                    break;
                case 220 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2731:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungenOderNullzahlung'
                    {
                    kw=(Token)match(input,271,FOLLOW_271_in_ruleKLASSE7293); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARueckforderungenOderNullzahlungKeyword_219()); 
                        

                    }
                    break;
                case 221 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2738:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASGAbgleich'
                    {
                    kw=(Token)match(input,272,FOLLOW_272_in_ruleKLASSE7312); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASGAbgleichKeyword_220()); 
                        

                    }
                    break;
                case 222 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2745:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstFeststellungenAbsichtlUnregelmaessigkeiten'
                    {
                    kw=(Token)match(input,273,FOLLOW_273_in_ruleKLASSE7331); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASonstFeststellungenAbsichtlUnregelmaessigkeitenKeyword_221()); 
                        

                    }
                    break;
                case 223 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2752:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungen'
                    {
                    kw=(Token)match(input,274,FOLLOW_274_in_ruleKLASSE7350); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASonstigeFeststellungenKeyword_222()); 
                        

                    }
                    break;
                case 224 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2759:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenStichtag'
                    {
                    kw=(Token)match(input,275,FOLLOW_275_in_ruleKLASSE7369); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASonstigeFeststellungenStichtagKeyword_223()); 
                        

                    }
                    break;
                case 225 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2766:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenVJ'
                    {
                    kw=(Token)match(input,276,FOLLOW_276_in_ruleKLASSE7388); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASonstigeFeststellungenVJKeyword_224()); 
                        

                    }
                    break;
                case 226 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2773:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASperrvermerkNichtVergeben'
                    {
                    kw=(Token)match(input,277,FOLLOW_277_in_ruleKLASSE7407); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASperrvermerkNichtVergebenKeyword_225()); 
                        

                    }
                    break;
                case 227 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2780:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStammdatenAktuell'
                    {
                    kw=(Token)match(input,278,FOLLOW_278_in_ruleKLASSE7426); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAStammdatenAktuellKeyword_226()); 
                        

                    }
                    break;
                case 228 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2787:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStichtagHelper'
                    {
                    kw=(Token)match(input,279,FOLLOW_279_in_ruleKLASSE7445); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAStichtagHelperKeyword_227()); 
                        

                    }
                    break;
                case 229 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2794:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATeilmassnahmeND1UndND2Beantragt'
                    {
                    kw=(Token)match(input,280,FOLLOW_280_in_ruleKLASSE7464); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATeilmassnahmeND1UndND2BeantragtKeyword_228()); 
                        

                    }
                    break;
                case 230 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2801:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbesatzGesamt'
                    {
                    kw=(Token)match(input,281,FOLLOW_281_in_ruleKLASSE7483); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATierbesatzGesamtKeyword_229()); 
                        

                    }
                    break;
                case 231 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2808:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFF'
                    {
                    kw=(Token)match(input,282,FOLLOW_282_in_ruleKLASSE7502); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATierbestandEingehaltenRgvProHaHFFKeyword_230()); 
                        

                    }
                    break;
                case 232 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2815:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFundGveProHaLF'
                    {
                    kw=(Token)match(input,283,FOLLOW_283_in_ruleKLASSE7521); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATierbestandEingehaltenRgvProHaHFFundGveProHaLFKeyword_231()); 
                        

                    }
                    break;
                case 233 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2822:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFZusNCs'
                    {
                    kw=(Token)match(input,284,FOLLOW_284_in_ruleKLASSE7540); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATierbestandEingehaltenRgvProHaHFFZusNCsKeyword_232()); 
                        

                    }
                    break;
                case 234 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2829:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUmwandlungALInGL'
                    {
                    kw=(Token)match(input,285,FOLLOW_285_in_ruleKLASSE7559); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUmwandlungALInGLKeyword_233()); 
                        

                    }
                    break;
                case 235 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2836:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenAusserhalbDerEU'
                    {
                    kw=(Token)match(input,286,FOLLOW_286_in_ruleKLASSE7578); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnternehmenAusserhalbDerEUKeyword_234()); 
                        

                    }
                    break;
                case 236 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2843:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenOhneSchafeZiegenMitVertragVereinbarung'
                    {
                    kw=(Token)match(input,287,FOLLOW_287_in_ruleKLASSE7597); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnternehmenOhneSchafeZiegenMitVertragVereinbarungKeyword_235()); 
                        

                    }
                    break;
                case 237 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2850:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenssitzInBw'
                    {
                    kw=(Token)match(input,288,FOLLOW_288_in_ruleKLASSE7616); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnternehmenssitzInBwKeyword_236()); 
                        

                    }
                    break;
                case 238 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2857:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschiedBerechneteUndManuelleVerpflFlFJ'
                    {
                    kw=(Token)match(input,289,FOLLOW_289_in_ruleKLASSE7635); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnterschiedBerechneteUndManuelleVerpflFlFJKeyword_237()); 
                        

                    }
                    break;
                case 239 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2864:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschreitungTierbestzDurchFeststellung'
                    {
                    kw=(Token)match(input,290,FOLLOW_290_in_ruleKLASSE7654); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnterschreitungTierbestzDurchFeststellungKeyword_238()); 
                        

                    }
                    break;
                case 240 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2871:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNC'
                    {
                    kw=(Token)match(input,291,FOLLOW_291_in_ruleKLASSE7673); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnzulaessigeNCKeyword_239()); 
                        

                    }
                    break;
                case 241 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2878:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNcVNS'
                    {
                    kw=(Token)match(input,292,FOLLOW_292_in_ruleKLASSE7692); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnzulaessigeNcVNSKeyword_240()); 
                        

                    }
                    break;
                case 242 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2885:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheAckerfutter'
                    {
                    kw=(Token)match(input,293,FOLLOW_293_in_ruleKLASSE7711); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnzulaessigeVerringerungVerpflFlaecheAckerfutterKeyword_241()); 
                        

                    }
                    break;
                case 243 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2892:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenung'
                    {
                    kw=(Token)match(input,294,FOLLOW_294_in_ruleKLASSE7730); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenungKeyword_242()); 
                        

                    }
                    break;
                case 244 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2899:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentimVJVorhanden'
                    {
                    kw=(Token)match(input,295,FOLLOW_295_in_ruleKLASSE7749); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerlaengerungsdokumentimVJVorhandenKeyword_243()); 
                        

                    }
                    break;
                case 245 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2906:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentVorhanden'
                    {
                    kw=(Token)match(input,296,FOLLOW_296_in_ruleKLASSE7768); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerlaengerungsdokumentVorhandenKeyword_244()); 
                        

                    }
                    break;
                case 246 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2913:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18'
                    {
                    kw=(Token)match(input,297,FOLLOW_297_in_ruleKLASSE7787); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18Keyword_245()); 
                        

                    }
                    break;
                case 247 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2920:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18Beanstandungen'
                    {
                    kw=(Token)match(input,298,FOLLOW_298_in_ruleKLASSE7806); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18NurArt18BeanstandungenKeyword_246()); 
                        

                    }
                    break;
                case 248 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2927:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18BeanstandungenVJ'
                    {
                    kw=(Token)match(input,299,FOLLOW_299_in_ruleKLASSE7825); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18NurArt18BeanstandungenVJKeyword_247()); 
                        

                    }
                    break;
                case 249 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2934:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJ'
                    {
                    kw=(Token)match(input,300,FOLLOW_300_in_ruleKLASSE7844); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18VJKeyword_248()); 
                        

                    }
                    break;
                case 250 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2941:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJFuerMindEineBindung'
                    {
                    kw=(Token)match(input,301,FOLLOW_301_in_ruleKLASSE7863); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18VJFuerMindEineBindungKeyword_249()); 
                        

                    }
                    break;
                case 251 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2948:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsNrFuerAlleFlaechenVergeben'
                    {
                    kw=(Token)match(input,302,FOLLOW_302_in_ruleKLASSE7882); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVertragsNrFuerAlleFlaechenVergebenKeyword_250()); 
                        

                    }
                    break;
                case 252 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2955:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsnummer'
                    {
                    kw=(Token)match(input,303,FOLLOW_303_in_ruleKLASSE7901); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVertragsnummerKeyword_251()); 
                        

                    }
                    break;
                case 253 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2962:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragVorhanden'
                    {
                    kw=(Token)match(input,304,FOLLOW_304_in_ruleKLASSE7920); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVertragVorhandenKeyword_252()); 
                        

                    }
                    break;
                case 254 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2969:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVeterinaerBestaetigungVorhanden'
                    {
                    kw=(Token)match(input,305,FOLLOW_305_in_ruleKLASSE7939); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVeterinaerBestaetigungVorhandenKeyword_253()); 
                        

                    }
                    break;
                case 255 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2976:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendet'
                    {
                    kw=(Token)match(input,306,FOLLOW_306_in_ruleKLASSE7958); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVOKBeendetKeyword_254()); 
                        

                    }
                    break;
                case 256 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2983:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetStichtag'
                    {
                    kw=(Token)match(input,307,FOLLOW_307_in_ruleKLASSE7977); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVOKBeendetStichtagKeyword_255()); 
                        

                    }
                    break;
                case 257 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2990:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetVJ'
                    {
                    kw=(Token)match(input,308,FOLLOW_308_in_ruleKLASSE7996); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVOKBeendetVJKeyword_256()); 
                        

                    }
                    break;
                case 258 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:2997:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKCCBeendet'
                    {
                    kw=(Token)match(input,309,FOLLOW_309_in_ruleKLASSE8015); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVOKCCBeendetKeyword_257()); 
                        

                    }
                    break;
                case 259 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:3004:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVokNichtVerweigert'
                    {
                    kw=(Token)match(input,310,FOLLOW_310_in_ruleKLASSE8034); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVokNichtVerweigertKeyword_258()); 
                        

                    }
                    break;
                case 260 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:3011:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKPEnthaeltBenutzerdefPruefkonf'
                    {
                    kw=(Token)match(input,311,FOLLOW_311_in_ruleKLASSE8053); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVWKPEnthaeltBenutzerdefPruefkonfKeyword_259()); 
                        

                    }
                    break;
                case 261 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:3018:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKReferenzflaechenAbgleich'
                    {
                    kw=(Token)match(input,312,FOLLOW_312_in_ruleKLASSE8072); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVWKReferenzflaechenAbgleichKeyword_260()); 
                        

                    }
                    break;
                case 262 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:3025:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragsteller'
                    {
                    kw=(Token)match(input,313,FOLLOW_313_in_ruleKLASSE8091); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVwkZIDAntragstellerKeyword_261()); 
                        

                    }
                    break;
                case 263 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:3032:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragstellerStichtag'
                    {
                    kw=(Token)match(input,314,FOLLOW_314_in_ruleKLASSE8110); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVwkZIDAntragstellerStichtagKeyword_262()); 
                        

                    }
                    break;
                case 264 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:3039:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWeidetagebuchImPebVorhanden'
                    {
                    kw=(Token)match(input,315,FOLLOW_315_in_ruleKLASSE8129); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAWeidetagebuchImPebVorhandenKeyword_263()); 
                        

                    }
                    break;
                case 265 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:3046:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWiderspruchInVorherigerBerechnung'
                    {
                    kw=(Token)match(input,316,FOLLOW_316_in_ruleKLASSE8148); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAWiderspruchInVorherigerBerechnungKeyword_264()); 
                        

                    }
                    break;
                case 266 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:3053:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFK'
                    {
                    kw=(Token)match(input,317,FOLLOW_317_in_ruleKLASSE8167); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAZFKKeyword_265()); 
                        

                    }
                    break;
                case 267 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:3060:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKStichtag'
                    {
                    kw=(Token)match(input,318,FOLLOW_318_in_ruleKLASSE8186); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAZFKStichtagKeyword_266()); 
                        

                    }
                    break;
                case 268 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:3067:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKVJ'
                    {
                    kw=(Token)match(input,319,FOLLOW_319_in_ruleKLASSE8205); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAZFKVJKeyword_267()); 
                        

                    }
                    break;
                case 269 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:3074:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PASperrvermerkNichtVergeben'
                    {
                    kw=(Token)match(input,320,FOLLOW_320_in_ruleKLASSE8224); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgBusinessPruefungenPASperrvermerkNichtVergebenKeyword_268()); 
                        

                    }
                    break;
                case 270 :
                    // ../de.deg.eler.ft.vp/src-gen/de/deg/eler/ft/vp/parser/antlr/internal/InternalDsl.g:3081:2: kw= 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet'
                    {
                    kw=(Token)match(input,321,FOLLOW_321_in_ruleKLASSE8243); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgBusinessPruefungenPAAumKuliZuMaAZLAktuellUndBeendetKeyword_269()); 
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKLASSE"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleKonfiguration_in_entryRuleKonfiguration75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleKonfiguration85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUsedIDs_in_ruleKonfiguration131 = new BitSet(new long[]{0x0000000000DE0812L});
    public static final BitSet FOLLOW_ruleSPEZ_ANTRAGSZUWEISUNG_in_ruleKonfiguration152 = new BitSet(new long[]{0x0000000000DE0812L});
    public static final BitSet FOLLOW_ruleZuweisung_in_ruleKonfiguration174 = new BitSet(new long[]{0x0000000000DE0802L});
    public static final BitSet FOLLOW_rulevwkpaktionkonfiguraktion_in_ruleKonfiguration197 = new BitSet(new long[]{0x00000000005E0802L});
    public static final BitSet FOLLOW_rulePRUEFUNGSAKTION_in_ruleKonfiguration224 = new BitSet(new long[]{0x00000000005E0802L});
    public static final BitSet FOLLOW_rulePRUEFUNGSKLASSENNAME_in_ruleKonfiguration251 = new BitSet(new long[]{0x00000000005E0802L});
    public static final BitSet FOLLOW_rulePRUEFUNGSKURZTEXT_in_ruleKonfiguration278 = new BitSet(new long[]{0x00000000005E0802L});
    public static final BitSet FOLLOW_rulePRUEFUNGSLANGTEXT_in_ruleKonfiguration305 = new BitSet(new long[]{0x00000000005E0802L});
    public static final BitSet FOLLOW_rulePRUEFUNGSWIRKUNG_in_ruleKonfiguration332 = new BitSet(new long[]{0x00000000005E0802L});
    public static final BitSet FOLLOW_rulevwkpaktionkonfiguraktion_in_entryRulevwkpaktionkonfiguraktion371 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulevwkpaktionkonfiguraktion382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rulevwkpaktionkonfiguraktion420 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_ruleVWKPTYP_in_rulevwkpaktionkonfiguraktion442 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_rulevwkpaktionkonfiguraktion460 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rulevwkpaktionkonfiguraktion482 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_rulevwkpaktionkonfiguraktion500 = new BitSet(new long[]{0x000FFFFF00000000L});
    public static final BitSet FOLLOW_ruleAKTION_in_rulevwkpaktionkonfiguraktion522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVWKPTYP_in_entryRuleVWKPTYP568 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVWKPTYP579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleVWKPTYP617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleVWKPTYP636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSPEZ_ANTRAGSZUWEISUNG_in_entryRuleSPEZ_ANTRAGSZUWEISUNG677 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSPEZ_ANTRAGSZUWEISUNG688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleSPEZ_ANTRAGSZUWEISUNG728 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleSPEZ_ANTRAGSZUWEISUNG746 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_ruleSPEZ_ANTRAGSZUWEISUNG769 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleSPEZ_ANTRAGSZUWEISUNG787 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_ruleSPEZ_ANTRAGSZUWEISUNG811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSLANGTEXT_in_entryRulePRUEFUNGSLANGTEXT857 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePRUEFUNGSLANGTEXT868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rulePRUEFUNGSLANGTEXT906 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rulePRUEFUNGSLANGTEXT928 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_rulePRUEFUNGSLANGTEXT946 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulePRUEFUNGSLANGTEXT961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSKURZTEXT_in_entryRulePRUEFUNGSKURZTEXT1007 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePRUEFUNGSKURZTEXT1018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rulePRUEFUNGSKURZTEXT1056 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rulePRUEFUNGSKURZTEXT1078 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_rulePRUEFUNGSKURZTEXT1096 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulePRUEFUNGSKURZTEXT1111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSKLASSENNAME_in_entryRulePRUEFUNGSKLASSENNAME1157 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePRUEFUNGSKLASSENNAME1168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rulePRUEFUNGSKLASSENNAME1206 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rulePRUEFUNGSKLASSENNAME1228 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_rulePRUEFUNGSKLASSENNAME1246 = new BitSet(new long[]{0xFFF0000000000000L,0xFFFFFFFFFFFFFFFFL,0xFFFFFFFFFFFFFFFFL,0xFFFFFFFFFFFFFFFFL,0xFFFFFFFFFFFFFFFFL,0x0000000000000003L});
    public static final BitSet FOLLOW_ruleKLASSE_in_rulePRUEFUNGSKLASSENNAME1268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSAKTION_in_entryRulePRUEFUNGSAKTION1314 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePRUEFUNGSAKTION1325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rulePRUEFUNGSAKTION1363 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAKTIONSID_in_rulePRUEFUNGSAKTION1385 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_rulePRUEFUNGSAKTION1403 = new BitSet(new long[]{0x000FFFFF00000000L});
    public static final BitSet FOLLOW_ruleAKTION_in_rulePRUEFUNGSAKTION1425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAKTIONSID_in_entryRuleAKTIONSID1471 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAKTIONSID1482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_ruleAKTIONSID1529 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleAKTIONSID1547 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleAKTIONSID1562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSWIRKUNG_in_entryRulePRUEFUNGSWIRKUNG1608 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePRUEFUNGSWIRKUNG1619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rulePRUEFUNGSWIRKUNG1657 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleWIRKUNGSID_in_rulePRUEFUNGSWIRKUNG1679 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_rulePRUEFUNGSWIRKUNG1697 = new BitSet(new long[]{0x000000000E000000L});
    public static final BitSet FOLLOW_ruleWIRKUNG_in_rulePRUEFUNGSWIRKUNG1719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWIRKUNGSID_in_entryRuleWIRKUNGSID1765 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWIRKUNGSID1776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_ruleWIRKUNGSID1823 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleWIRKUNGSID1841 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleWIRKUNGSID1856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleZuweisung_in_entryRuleZuweisung1902 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleZuweisung1913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleZuweisung1951 = new BitSet(new long[]{0x00000000F0000000L});
    public static final BitSet FOLLOW_ruleANTRAGSART_in_ruleZuweisung1973 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleZuweisung1991 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_ruleZuweisung2014 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleZuweisung2032 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_ruleZuweisung2056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUsedIDs_in_entryRuleUsedIDs2102 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUsedIDs2113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleUsedIDs2151 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_ruleUsedIDs2174 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleUsedIDs2192 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_ruleUsedIDs2216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_entryRulePRUEFUNG2262 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePRUEFUNG2273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rulePRUEFUNG2312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWIRKUNG_in_entryRuleWIRKUNG2357 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWIRKUNG2368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleWIRKUNG2406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleWIRKUNG2425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleWIRKUNG2444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleANTRAGSART_in_entryRuleANTRAGSART2485 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleANTRAGSART2496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleANTRAGSART2534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleANTRAGSART2553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleANTRAGSART2572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleANTRAGSART2591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAKTION_in_entryRuleAKTION2632 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAKTION2643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleAKTION2681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleAKTION2700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleAKTION2719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleAKTION2738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleAKTION2757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleAKTION2776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleAKTION2795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleAKTION2814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleAKTION2833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_ruleAKTION2852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_ruleAKTION2871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleAKTION2890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleAKTION2909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_ruleAKTION2928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleAKTION2947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleAKTION2966 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_ruleAKTION2985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleAKTION3004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleAKTION3023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_ruleAKTION3042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKLASSE_in_entryRuleKLASSE3083 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleKLASSE3094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_ruleKLASSE3132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_ruleKLASSE3151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_ruleKLASSE3170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_ruleKLASSE3189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_ruleKLASSE3208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ruleKLASSE3227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_ruleKLASSE3246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_ruleKLASSE3265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ruleKLASSE3284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_ruleKLASSE3303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleKLASSE3322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_ruleKLASSE3341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_ruleKLASSE3360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleKLASSE3379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_ruleKLASSE3398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_ruleKLASSE3417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_ruleKLASSE3436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_ruleKLASSE3455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_ruleKLASSE3474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_ruleKLASSE3493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_ruleKLASSE3512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_ruleKLASSE3531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ruleKLASSE3550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_ruleKLASSE3569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_ruleKLASSE3588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_ruleKLASSE3607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_ruleKLASSE3626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_ruleKLASSE3645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_ruleKLASSE3664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_ruleKLASSE3683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_ruleKLASSE3702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_ruleKLASSE3721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_ruleKLASSE3740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_ruleKLASSE3759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_ruleKLASSE3778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_ruleKLASSE3797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_ruleKLASSE3816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_ruleKLASSE3835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_90_in_ruleKLASSE3854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_ruleKLASSE3873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_ruleKLASSE3892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_ruleKLASSE3911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_94_in_ruleKLASSE3930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_ruleKLASSE3949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_ruleKLASSE3968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_ruleKLASSE3987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_ruleKLASSE4006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_ruleKLASSE4025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_100_in_ruleKLASSE4044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_101_in_ruleKLASSE4063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_102_in_ruleKLASSE4082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_103_in_ruleKLASSE4101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_104_in_ruleKLASSE4120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_105_in_ruleKLASSE4139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_106_in_ruleKLASSE4158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_107_in_ruleKLASSE4177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_108_in_ruleKLASSE4196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_109_in_ruleKLASSE4215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_110_in_ruleKLASSE4234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_111_in_ruleKLASSE4253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_112_in_ruleKLASSE4272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_113_in_ruleKLASSE4291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_114_in_ruleKLASSE4310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_115_in_ruleKLASSE4329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_116_in_ruleKLASSE4348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_117_in_ruleKLASSE4367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_118_in_ruleKLASSE4386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_119_in_ruleKLASSE4405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_120_in_ruleKLASSE4424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_121_in_ruleKLASSE4443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_122_in_ruleKLASSE4462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_123_in_ruleKLASSE4481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_124_in_ruleKLASSE4500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_125_in_ruleKLASSE4519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_126_in_ruleKLASSE4538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_127_in_ruleKLASSE4557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_128_in_ruleKLASSE4576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_129_in_ruleKLASSE4595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_130_in_ruleKLASSE4614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_131_in_ruleKLASSE4633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_132_in_ruleKLASSE4652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_133_in_ruleKLASSE4671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_134_in_ruleKLASSE4690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_135_in_ruleKLASSE4709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_136_in_ruleKLASSE4728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_137_in_ruleKLASSE4747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_138_in_ruleKLASSE4766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_139_in_ruleKLASSE4785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_140_in_ruleKLASSE4804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_141_in_ruleKLASSE4823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_142_in_ruleKLASSE4842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_143_in_ruleKLASSE4861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_144_in_ruleKLASSE4880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_145_in_ruleKLASSE4899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_146_in_ruleKLASSE4918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_147_in_ruleKLASSE4937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_148_in_ruleKLASSE4956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_149_in_ruleKLASSE4975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_150_in_ruleKLASSE4994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_151_in_ruleKLASSE5013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_152_in_ruleKLASSE5032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_153_in_ruleKLASSE5051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_154_in_ruleKLASSE5070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_155_in_ruleKLASSE5089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_156_in_ruleKLASSE5108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_157_in_ruleKLASSE5127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_158_in_ruleKLASSE5146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_159_in_ruleKLASSE5165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_160_in_ruleKLASSE5184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_161_in_ruleKLASSE5203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_162_in_ruleKLASSE5222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_163_in_ruleKLASSE5241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_164_in_ruleKLASSE5260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_165_in_ruleKLASSE5279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_166_in_ruleKLASSE5298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_167_in_ruleKLASSE5317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_168_in_ruleKLASSE5336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_169_in_ruleKLASSE5355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_170_in_ruleKLASSE5374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_171_in_ruleKLASSE5393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_172_in_ruleKLASSE5412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_173_in_ruleKLASSE5431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_174_in_ruleKLASSE5450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_175_in_ruleKLASSE5469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_176_in_ruleKLASSE5488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_177_in_ruleKLASSE5507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_178_in_ruleKLASSE5526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_179_in_ruleKLASSE5545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_180_in_ruleKLASSE5564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_181_in_ruleKLASSE5583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_182_in_ruleKLASSE5602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_183_in_ruleKLASSE5621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_184_in_ruleKLASSE5640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_185_in_ruleKLASSE5659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_186_in_ruleKLASSE5678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_187_in_ruleKLASSE5697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_188_in_ruleKLASSE5716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_189_in_ruleKLASSE5735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_190_in_ruleKLASSE5754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_191_in_ruleKLASSE5773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_192_in_ruleKLASSE5792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_193_in_ruleKLASSE5811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_194_in_ruleKLASSE5830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_195_in_ruleKLASSE5849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_196_in_ruleKLASSE5868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_197_in_ruleKLASSE5887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_198_in_ruleKLASSE5906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_199_in_ruleKLASSE5925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_200_in_ruleKLASSE5944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_201_in_ruleKLASSE5963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_202_in_ruleKLASSE5982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_203_in_ruleKLASSE6001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_204_in_ruleKLASSE6020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_205_in_ruleKLASSE6039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_206_in_ruleKLASSE6058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_207_in_ruleKLASSE6077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_208_in_ruleKLASSE6096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_209_in_ruleKLASSE6115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_210_in_ruleKLASSE6134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_211_in_ruleKLASSE6153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_212_in_ruleKLASSE6172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_213_in_ruleKLASSE6191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_214_in_ruleKLASSE6210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_215_in_ruleKLASSE6229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_216_in_ruleKLASSE6248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_217_in_ruleKLASSE6267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_218_in_ruleKLASSE6286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_219_in_ruleKLASSE6305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_220_in_ruleKLASSE6324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_221_in_ruleKLASSE6343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_222_in_ruleKLASSE6362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_223_in_ruleKLASSE6381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_224_in_ruleKLASSE6400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_225_in_ruleKLASSE6419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_226_in_ruleKLASSE6438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_227_in_ruleKLASSE6457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_228_in_ruleKLASSE6476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_229_in_ruleKLASSE6495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_230_in_ruleKLASSE6514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_231_in_ruleKLASSE6533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_232_in_ruleKLASSE6552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_233_in_ruleKLASSE6571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_234_in_ruleKLASSE6590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_235_in_ruleKLASSE6609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_236_in_ruleKLASSE6628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_237_in_ruleKLASSE6647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_238_in_ruleKLASSE6666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_239_in_ruleKLASSE6685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_240_in_ruleKLASSE6704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_241_in_ruleKLASSE6723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_242_in_ruleKLASSE6742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_243_in_ruleKLASSE6761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_244_in_ruleKLASSE6780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_245_in_ruleKLASSE6799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_246_in_ruleKLASSE6818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_247_in_ruleKLASSE6837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_248_in_ruleKLASSE6856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_249_in_ruleKLASSE6875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_250_in_ruleKLASSE6894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_251_in_ruleKLASSE6913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_252_in_ruleKLASSE6932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_253_in_ruleKLASSE6951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_254_in_ruleKLASSE6970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_255_in_ruleKLASSE6989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_256_in_ruleKLASSE7008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_257_in_ruleKLASSE7027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_258_in_ruleKLASSE7046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_259_in_ruleKLASSE7065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_260_in_ruleKLASSE7084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_261_in_ruleKLASSE7103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_262_in_ruleKLASSE7122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_263_in_ruleKLASSE7141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_264_in_ruleKLASSE7160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_265_in_ruleKLASSE7179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_266_in_ruleKLASSE7198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_267_in_ruleKLASSE7217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_268_in_ruleKLASSE7236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_269_in_ruleKLASSE7255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_270_in_ruleKLASSE7274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_271_in_ruleKLASSE7293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_272_in_ruleKLASSE7312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_273_in_ruleKLASSE7331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_274_in_ruleKLASSE7350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_275_in_ruleKLASSE7369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_276_in_ruleKLASSE7388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_277_in_ruleKLASSE7407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_278_in_ruleKLASSE7426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_279_in_ruleKLASSE7445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_280_in_ruleKLASSE7464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_281_in_ruleKLASSE7483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_282_in_ruleKLASSE7502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_283_in_ruleKLASSE7521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_284_in_ruleKLASSE7540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_285_in_ruleKLASSE7559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_286_in_ruleKLASSE7578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_287_in_ruleKLASSE7597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_288_in_ruleKLASSE7616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_289_in_ruleKLASSE7635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_290_in_ruleKLASSE7654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_291_in_ruleKLASSE7673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_292_in_ruleKLASSE7692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_293_in_ruleKLASSE7711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_294_in_ruleKLASSE7730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_295_in_ruleKLASSE7749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_296_in_ruleKLASSE7768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_297_in_ruleKLASSE7787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_298_in_ruleKLASSE7806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_299_in_ruleKLASSE7825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_300_in_ruleKLASSE7844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_301_in_ruleKLASSE7863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_302_in_ruleKLASSE7882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_303_in_ruleKLASSE7901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_304_in_ruleKLASSE7920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_305_in_ruleKLASSE7939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_306_in_ruleKLASSE7958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_307_in_ruleKLASSE7977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_308_in_ruleKLASSE7996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_309_in_ruleKLASSE8015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_310_in_ruleKLASSE8034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_311_in_ruleKLASSE8053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_312_in_ruleKLASSE8072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_313_in_ruleKLASSE8091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_314_in_ruleKLASSE8110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_315_in_ruleKLASSE8129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_316_in_ruleKLASSE8148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_317_in_ruleKLASSE8167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_318_in_ruleKLASSE8186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_319_in_ruleKLASSE8205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_320_in_ruleKLASSE8224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_321_in_ruleKLASSE8243 = new BitSet(new long[]{0x0000000000000002L});

}