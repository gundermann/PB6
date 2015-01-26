package de.deg.eler.ft.vp.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import de.deg.eler.ft.vp.services.DslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalDslParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_STRING", "RULE_SL_COMMENT", "RULE_ID", "RULE_ML_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Automatisch'", "'Manuell'", "'VERHINDERT_AKTION'", "'OHNE'", "'WARNUNG'", "'AUSZANTRAG'", "'ERWANTRAG'", "'NEUANTRAG'", "'VERLANTRAG'", "'BerechnenUndPruefen'", "'DokumentBearbeitungBeginnen'", "'Zurueckziehen'", "'NachberechnungStornieren'", "'ZurueckziehenZuruecknehmen'", "'AntragFreigeben'", "'AntragFreigabeZurueck'", "'DokumentBearbeitungBeenden'", "'AntragBewilligen'", "'AntragAblehnen'", "'AntragZahlungAnweisen'", "'AntragEntscheidungZurueck'", "'AntragNeuBearbeiten'", "'AntragWidersprechen'", "'AntragWiderspruchAblZurueck'", "'AntragWiderspruchZurueck'", "'AntragWiderspruchAblehnen'", "'AntragWiderspruchZulassen'", "'AntragWiderspruchZulZurueck'", "'AntragWiderspruchStattgeben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusAusRechenschritt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusBagatellbetragTeilmassnahmen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PA4AugenPrinzip'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAblehnungsgruendeVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenArt17Abs1'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenBezugVerstoesseVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngaben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngabenStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalschGemachteUnregelmaessigkeit'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbtretungenInZahlungVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzuegeErhoehung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaft'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngaben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngabenBeendetOhneAenderungsblatt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAltverpflichtungUeberschritten'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenAUM'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenErsterfassungBeendetAJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitungStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageTierhaltung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilBluehflaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreide'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreideAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGruenland'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilKkLeguminosenAL5Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosenAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingang'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangNichtNachAusschlussTermin'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsflaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsteller'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstellerKeineJuristischePerson'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstermin'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsterminNichtVerfristet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJBewilligt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJNichtAbgelehnt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragZurueckziehen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenInklusiveLeguminosen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtGroesserAls100ProHa'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtKleinerAls30'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappe'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappeAktuellUndDurchgefuehrt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlatt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaUZWAktuellUndBeendet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweis'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweisBeendet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusbringungstechnik'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuellt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusschluss'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragAuszahlung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungBeruecksichtigungHalbeZahlung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2002'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2007'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungUeberGesamteLaufzeit'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragNeuAntrag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragRueckforderung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindungStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragteFlaecheKleiner80Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtGLGleichGesamtGL'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtMindestens5ProzAckerflaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner10ProzAckerflaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner2ha'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner5ProzAckerflaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzAusHITUnterBeruecksichtigungGruenland'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_2'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_3'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHIT'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABescheidInAktuellerBerechnung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABestaetigungsVermerk'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABewilligterNAImEAJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuell'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuellUndBeendet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADatumEingangGroesserAntrag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzBewilligtVJUndBeantragtGroesserBagatellbetrag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaecheWiederholt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCC'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCV'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCVImVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGL'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGLSchwellwert'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangVerpflichtungsflaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEntscheidungenBescheidart'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErstantragsjahr'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10bzw2haOdGroesser50ProzentVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10odGroesser50ProzentVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50Proz'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50ProzOder2Ha'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungLE50ProzentVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungsflaecheVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAExtensiveBewirtschaftungGLGleichGesamtGL'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngaben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngabenStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs5'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6inAnderemFP'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt18Abs3'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschGemachteAngaben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerderfaehigGroesserNull'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerdergebietGroesserGleich3Hektar'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM141Mindestens5ProzentAFAusEaj'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM14Mindestens5ProzentAFAusEaj'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheStreuobstwiesen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGebuehrenrechnung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung30Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung50Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtrueckforderung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtsanktionierung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuGross'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuKlein'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrfImAA'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrobFahrlaessigeGemachteAngaben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGuellemenge'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAHofuebergabe'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerBestaetigungVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerVereinbarungVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKapitalbeteiligungOeffentlHandGroesser25Proz'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinAntragVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinBescheidInAktuellerBerechnung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinDungAufnahmeOderAbgabe'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhandenTm'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineParalleBeantragungM5UndM6'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinePheromonGemeinschaft'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAktiengesellschaft'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAnstaltDesOeffentlRechts'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformGmbH'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformJuristischePersonOeffentlRecht'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformKoerperschaftDesOeffentlichenRechts'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformOeffentlRechtlStiftung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformPrivatRechtlStiftung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonOeffentlRecht'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonPrivatRecht'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeNatuerlichePerson'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRelevantenEntscheidungenOffen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformAnerkannteWeidegemeinschaft'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformEinzelantragstellerMeka'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformPheromongemeinschaft'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformWaldgemeinschaft'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinFC104anBindungVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinGLAusErzeugungGenommenNutzung592'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinKlaerschlammAusgebracht'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB2'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKombinierteGLAntraege'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollkostenzuschussMitND2Teilmassnahme'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollprotokoll'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollvertrag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung20Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung30Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50Prozent'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentB1610'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALaborbeanstandungenLiegenNichtVor'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALandwirt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogenStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaschinelleBerechnungVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusHIT'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF2010'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusHIT'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestFlaeche'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestumfangWinterbegruenung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFFAusHIT'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3GveProHaLNF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaGL'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusHIT'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_5RgvProHaGL'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_6GVEProHaFF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMittelverwaltung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANachOeffnenBerechnet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtAlleChecksWurdenBearbeitet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtZuWenigAckerfutterAngebaut'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANurNachberechnungStornierbar'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelBewilligterNABeiEajGleichAj'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAAEntschieden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragFP773'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragZuFP774'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendig'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendigLZJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungFlaechenmappeVj'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungVj'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleich'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichBeendetVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichDurchgefuehrtVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaenger'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaengerAlsEinzelunternehmer'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeit'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitEinJahr'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitVNS'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVJVokAJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVok'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokCc'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungenOderNullzahlung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASGAbgleich'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstFeststellungenAbsichtlUnregelmaessigkeiten'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASperrvermerkNichtVergeben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStammdatenAktuell'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStichtagHelper'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATeilmassnahmeND1UndND2Beantragt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbesatzGesamt'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFundGveProHaLF'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFZusNCs'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUmwandlungALInGL'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenAusserhalbDerEU'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenOhneSchafeZiegenMitVertragVereinbarung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenssitzInBw'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschiedBerechneteUndManuelleVerpflFlFJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschreitungTierbestzDurchFeststellung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNC'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNcVNS'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheAckerfutter'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentimVJVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18Beanstandungen'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18BeanstandungenVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJFuerMindEineBindung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsNrFuerAlleFlaechenVergeben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsnummer'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVeterinaerBestaetigungVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKCCBeendet'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVokNichtVerweigert'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKPEnthaeltBenutzerdefPruefkonf'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKReferenzflaechenAbgleich'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragsteller'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragstellerStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWeidetagebuchImPebVorhanden'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWiderspruchInVorherigerBerechnung'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFK'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKStichtag'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKVJ'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PASperrvermerkNichtVergeben'", "'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet'", "'PruefungSichtbar.'", "' ='", "'VWKPKonfiguration.'", "'.Aktion.'", "' = '", "','", "'PruefungLangtext.'", "'PruefungKurzbezeichnung.'", "'PruefungKlassenname.'", "'PruefungAktion.'", "'.'", "'PruefungWirkung.'", "'DvAntragsArt.'", "'CodesAlle = '"
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
    public static final int T__322=322;
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
    public static final int T__323=323;
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
    public String getGrammarFileName() { return "../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g"; }


     
     	private DslGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(DslGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }
        
        @Override
        protected String getValueForTokenName(String tokenName) {
        	return tokenName;
        }




    // $ANTLR start "entryRuleKonfiguration"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:60:1: entryRuleKonfiguration : ruleKonfiguration EOF ;
    public final void entryRuleKonfiguration() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:61:1: ( ruleKonfiguration EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:62:1: ruleKonfiguration EOF
            {
             before(grammarAccess.getKonfigurationRule()); 
            pushFollow(FOLLOW_ruleKonfiguration_in_entryRuleKonfiguration61);
            ruleKonfiguration();

            state._fsp--;

             after(grammarAccess.getKonfigurationRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleKonfiguration68); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleKonfiguration"


    // $ANTLR start "ruleKonfiguration"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:69:1: ruleKonfiguration : ( ( rule__Konfiguration__Group__0 ) ) ;
    public final void ruleKonfiguration() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:73:2: ( ( ( rule__Konfiguration__Group__0 ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:74:1: ( ( rule__Konfiguration__Group__0 ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:74:1: ( ( rule__Konfiguration__Group__0 ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:75:1: ( rule__Konfiguration__Group__0 )
            {
             before(grammarAccess.getKonfigurationAccess().getGroup()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:76:1: ( rule__Konfiguration__Group__0 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:76:2: rule__Konfiguration__Group__0
            {
            pushFollow(FOLLOW_rule__Konfiguration__Group__0_in_ruleKonfiguration94);
            rule__Konfiguration__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getKonfigurationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleKonfiguration"


    // $ANTLR start "entryRulePRUEFUNGSICHTBARKEIT"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:88:1: entryRulePRUEFUNGSICHTBARKEIT : rulePRUEFUNGSICHTBARKEIT EOF ;
    public final void entryRulePRUEFUNGSICHTBARKEIT() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:89:1: ( rulePRUEFUNGSICHTBARKEIT EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:90:1: rulePRUEFUNGSICHTBARKEIT EOF
            {
             before(grammarAccess.getPRUEFUNGSICHTBARKEITRule()); 
            pushFollow(FOLLOW_rulePRUEFUNGSICHTBARKEIT_in_entryRulePRUEFUNGSICHTBARKEIT121);
            rulePRUEFUNGSICHTBARKEIT();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSICHTBARKEITRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePRUEFUNGSICHTBARKEIT128); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePRUEFUNGSICHTBARKEIT"


    // $ANTLR start "rulePRUEFUNGSICHTBARKEIT"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:97:1: rulePRUEFUNGSICHTBARKEIT : ( ( rule__PRUEFUNGSICHTBARKEIT__Group__0 ) ) ;
    public final void rulePRUEFUNGSICHTBARKEIT() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:101:2: ( ( ( rule__PRUEFUNGSICHTBARKEIT__Group__0 ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:102:1: ( ( rule__PRUEFUNGSICHTBARKEIT__Group__0 ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:102:1: ( ( rule__PRUEFUNGSICHTBARKEIT__Group__0 ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:103:1: ( rule__PRUEFUNGSICHTBARKEIT__Group__0 )
            {
             before(grammarAccess.getPRUEFUNGSICHTBARKEITAccess().getGroup()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:104:1: ( rule__PRUEFUNGSICHTBARKEIT__Group__0 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:104:2: rule__PRUEFUNGSICHTBARKEIT__Group__0
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSICHTBARKEIT__Group__0_in_rulePRUEFUNGSICHTBARKEIT154);
            rule__PRUEFUNGSICHTBARKEIT__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPRUEFUNGSICHTBARKEITAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePRUEFUNGSICHTBARKEIT"


    // $ANTLR start "entryRulevwkpaktionkonfiguraktion"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:116:1: entryRulevwkpaktionkonfiguraktion : rulevwkpaktionkonfiguraktion EOF ;
    public final void entryRulevwkpaktionkonfiguraktion() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:117:1: ( rulevwkpaktionkonfiguraktion EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:118:1: rulevwkpaktionkonfiguraktion EOF
            {
             before(grammarAccess.getVwkpaktionkonfiguraktionRule()); 
            pushFollow(FOLLOW_rulevwkpaktionkonfiguraktion_in_entryRulevwkpaktionkonfiguraktion181);
            rulevwkpaktionkonfiguraktion();

            state._fsp--;

             after(grammarAccess.getVwkpaktionkonfiguraktionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulevwkpaktionkonfiguraktion188); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulevwkpaktionkonfiguraktion"


    // $ANTLR start "rulevwkpaktionkonfiguraktion"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:125:1: rulevwkpaktionkonfiguraktion : ( ( rule__Vwkpaktionkonfiguraktion__Group__0 ) ) ;
    public final void rulevwkpaktionkonfiguraktion() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:129:2: ( ( ( rule__Vwkpaktionkonfiguraktion__Group__0 ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:130:1: ( ( rule__Vwkpaktionkonfiguraktion__Group__0 ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:130:1: ( ( rule__Vwkpaktionkonfiguraktion__Group__0 ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:131:1: ( rule__Vwkpaktionkonfiguraktion__Group__0 )
            {
             before(grammarAccess.getVwkpaktionkonfiguraktionAccess().getGroup()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:132:1: ( rule__Vwkpaktionkonfiguraktion__Group__0 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:132:2: rule__Vwkpaktionkonfiguraktion__Group__0
            {
            pushFollow(FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__0_in_rulevwkpaktionkonfiguraktion214);
            rule__Vwkpaktionkonfiguraktion__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVwkpaktionkonfiguraktionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulevwkpaktionkonfiguraktion"


    // $ANTLR start "entryRuleVWKPTYP"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:144:1: entryRuleVWKPTYP : ruleVWKPTYP EOF ;
    public final void entryRuleVWKPTYP() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:145:1: ( ruleVWKPTYP EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:146:1: ruleVWKPTYP EOF
            {
             before(grammarAccess.getVWKPTYPRule()); 
            pushFollow(FOLLOW_ruleVWKPTYP_in_entryRuleVWKPTYP241);
            ruleVWKPTYP();

            state._fsp--;

             after(grammarAccess.getVWKPTYPRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVWKPTYP248); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVWKPTYP"


    // $ANTLR start "ruleVWKPTYP"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:153:1: ruleVWKPTYP : ( ( rule__VWKPTYP__Alternatives ) ) ;
    public final void ruleVWKPTYP() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:157:2: ( ( ( rule__VWKPTYP__Alternatives ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:158:1: ( ( rule__VWKPTYP__Alternatives ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:158:1: ( ( rule__VWKPTYP__Alternatives ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:159:1: ( rule__VWKPTYP__Alternatives )
            {
             before(grammarAccess.getVWKPTYPAccess().getAlternatives()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:160:1: ( rule__VWKPTYP__Alternatives )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:160:2: rule__VWKPTYP__Alternatives
            {
            pushFollow(FOLLOW_rule__VWKPTYP__Alternatives_in_ruleVWKPTYP274);
            rule__VWKPTYP__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getVWKPTYPAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVWKPTYP"


    // $ANTLR start "entryRuleSPEZ_ANTRAGSZUWEISUNG"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:172:1: entryRuleSPEZ_ANTRAGSZUWEISUNG : ruleSPEZ_ANTRAGSZUWEISUNG EOF ;
    public final void entryRuleSPEZ_ANTRAGSZUWEISUNG() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:173:1: ( ruleSPEZ_ANTRAGSZUWEISUNG EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:174:1: ruleSPEZ_ANTRAGSZUWEISUNG EOF
            {
             before(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGRule()); 
            pushFollow(FOLLOW_ruleSPEZ_ANTRAGSZUWEISUNG_in_entryRuleSPEZ_ANTRAGSZUWEISUNG301);
            ruleSPEZ_ANTRAGSZUWEISUNG();

            state._fsp--;

             after(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSPEZ_ANTRAGSZUWEISUNG308); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSPEZ_ANTRAGSZUWEISUNG"


    // $ANTLR start "ruleSPEZ_ANTRAGSZUWEISUNG"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:181:1: ruleSPEZ_ANTRAGSZUWEISUNG : ( ( rule__SPEZ_ANTRAGSZUWEISUNG__Group__0 ) ) ;
    public final void ruleSPEZ_ANTRAGSZUWEISUNG() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:185:2: ( ( ( rule__SPEZ_ANTRAGSZUWEISUNG__Group__0 ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:186:1: ( ( rule__SPEZ_ANTRAGSZUWEISUNG__Group__0 ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:186:1: ( ( rule__SPEZ_ANTRAGSZUWEISUNG__Group__0 ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:187:1: ( rule__SPEZ_ANTRAGSZUWEISUNG__Group__0 )
            {
             before(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getGroup()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:188:1: ( rule__SPEZ_ANTRAGSZUWEISUNG__Group__0 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:188:2: rule__SPEZ_ANTRAGSZUWEISUNG__Group__0
            {
            pushFollow(FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__0_in_ruleSPEZ_ANTRAGSZUWEISUNG334);
            rule__SPEZ_ANTRAGSZUWEISUNG__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSPEZ_ANTRAGSZUWEISUNG"


    // $ANTLR start "entryRulePRUEFUNGSLANGTEXT"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:200:1: entryRulePRUEFUNGSLANGTEXT : rulePRUEFUNGSLANGTEXT EOF ;
    public final void entryRulePRUEFUNGSLANGTEXT() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:201:1: ( rulePRUEFUNGSLANGTEXT EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:202:1: rulePRUEFUNGSLANGTEXT EOF
            {
             before(grammarAccess.getPRUEFUNGSLANGTEXTRule()); 
            pushFollow(FOLLOW_rulePRUEFUNGSLANGTEXT_in_entryRulePRUEFUNGSLANGTEXT361);
            rulePRUEFUNGSLANGTEXT();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSLANGTEXTRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePRUEFUNGSLANGTEXT368); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePRUEFUNGSLANGTEXT"


    // $ANTLR start "rulePRUEFUNGSLANGTEXT"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:209:1: rulePRUEFUNGSLANGTEXT : ( ( rule__PRUEFUNGSLANGTEXT__Group__0 ) ) ;
    public final void rulePRUEFUNGSLANGTEXT() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:213:2: ( ( ( rule__PRUEFUNGSLANGTEXT__Group__0 ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:214:1: ( ( rule__PRUEFUNGSLANGTEXT__Group__0 ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:214:1: ( ( rule__PRUEFUNGSLANGTEXT__Group__0 ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:215:1: ( rule__PRUEFUNGSLANGTEXT__Group__0 )
            {
             before(grammarAccess.getPRUEFUNGSLANGTEXTAccess().getGroup()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:216:1: ( rule__PRUEFUNGSLANGTEXT__Group__0 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:216:2: rule__PRUEFUNGSLANGTEXT__Group__0
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__0_in_rulePRUEFUNGSLANGTEXT394);
            rule__PRUEFUNGSLANGTEXT__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPRUEFUNGSLANGTEXTAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePRUEFUNGSLANGTEXT"


    // $ANTLR start "entryRulePRUEFUNGSKURZTEXT"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:228:1: entryRulePRUEFUNGSKURZTEXT : rulePRUEFUNGSKURZTEXT EOF ;
    public final void entryRulePRUEFUNGSKURZTEXT() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:229:1: ( rulePRUEFUNGSKURZTEXT EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:230:1: rulePRUEFUNGSKURZTEXT EOF
            {
             before(grammarAccess.getPRUEFUNGSKURZTEXTRule()); 
            pushFollow(FOLLOW_rulePRUEFUNGSKURZTEXT_in_entryRulePRUEFUNGSKURZTEXT421);
            rulePRUEFUNGSKURZTEXT();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSKURZTEXTRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePRUEFUNGSKURZTEXT428); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePRUEFUNGSKURZTEXT"


    // $ANTLR start "rulePRUEFUNGSKURZTEXT"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:237:1: rulePRUEFUNGSKURZTEXT : ( ( rule__PRUEFUNGSKURZTEXT__Group__0 ) ) ;
    public final void rulePRUEFUNGSKURZTEXT() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:241:2: ( ( ( rule__PRUEFUNGSKURZTEXT__Group__0 ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:242:1: ( ( rule__PRUEFUNGSKURZTEXT__Group__0 ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:242:1: ( ( rule__PRUEFUNGSKURZTEXT__Group__0 ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:243:1: ( rule__PRUEFUNGSKURZTEXT__Group__0 )
            {
             before(grammarAccess.getPRUEFUNGSKURZTEXTAccess().getGroup()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:244:1: ( rule__PRUEFUNGSKURZTEXT__Group__0 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:244:2: rule__PRUEFUNGSKURZTEXT__Group__0
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__0_in_rulePRUEFUNGSKURZTEXT454);
            rule__PRUEFUNGSKURZTEXT__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPRUEFUNGSKURZTEXTAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePRUEFUNGSKURZTEXT"


    // $ANTLR start "entryRulePRUEFUNGSKLASSENNAME"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:256:1: entryRulePRUEFUNGSKLASSENNAME : rulePRUEFUNGSKLASSENNAME EOF ;
    public final void entryRulePRUEFUNGSKLASSENNAME() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:257:1: ( rulePRUEFUNGSKLASSENNAME EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:258:1: rulePRUEFUNGSKLASSENNAME EOF
            {
             before(grammarAccess.getPRUEFUNGSKLASSENNAMERule()); 
            pushFollow(FOLLOW_rulePRUEFUNGSKLASSENNAME_in_entryRulePRUEFUNGSKLASSENNAME481);
            rulePRUEFUNGSKLASSENNAME();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSKLASSENNAMERule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePRUEFUNGSKLASSENNAME488); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePRUEFUNGSKLASSENNAME"


    // $ANTLR start "rulePRUEFUNGSKLASSENNAME"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:265:1: rulePRUEFUNGSKLASSENNAME : ( ( rule__PRUEFUNGSKLASSENNAME__Group__0 ) ) ;
    public final void rulePRUEFUNGSKLASSENNAME() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:269:2: ( ( ( rule__PRUEFUNGSKLASSENNAME__Group__0 ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:270:1: ( ( rule__PRUEFUNGSKLASSENNAME__Group__0 ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:270:1: ( ( rule__PRUEFUNGSKLASSENNAME__Group__0 ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:271:1: ( rule__PRUEFUNGSKLASSENNAME__Group__0 )
            {
             before(grammarAccess.getPRUEFUNGSKLASSENNAMEAccess().getGroup()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:272:1: ( rule__PRUEFUNGSKLASSENNAME__Group__0 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:272:2: rule__PRUEFUNGSKLASSENNAME__Group__0
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__0_in_rulePRUEFUNGSKLASSENNAME514);
            rule__PRUEFUNGSKLASSENNAME__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPRUEFUNGSKLASSENNAMEAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePRUEFUNGSKLASSENNAME"


    // $ANTLR start "entryRulePRUEFUNGSAKTION"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:284:1: entryRulePRUEFUNGSAKTION : rulePRUEFUNGSAKTION EOF ;
    public final void entryRulePRUEFUNGSAKTION() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:285:1: ( rulePRUEFUNGSAKTION EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:286:1: rulePRUEFUNGSAKTION EOF
            {
             before(grammarAccess.getPRUEFUNGSAKTIONRule()); 
            pushFollow(FOLLOW_rulePRUEFUNGSAKTION_in_entryRulePRUEFUNGSAKTION541);
            rulePRUEFUNGSAKTION();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSAKTIONRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePRUEFUNGSAKTION548); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePRUEFUNGSAKTION"


    // $ANTLR start "rulePRUEFUNGSAKTION"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:293:1: rulePRUEFUNGSAKTION : ( ( rule__PRUEFUNGSAKTION__Group__0 ) ) ;
    public final void rulePRUEFUNGSAKTION() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:297:2: ( ( ( rule__PRUEFUNGSAKTION__Group__0 ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:298:1: ( ( rule__PRUEFUNGSAKTION__Group__0 ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:298:1: ( ( rule__PRUEFUNGSAKTION__Group__0 ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:299:1: ( rule__PRUEFUNGSAKTION__Group__0 )
            {
             before(grammarAccess.getPRUEFUNGSAKTIONAccess().getGroup()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:300:1: ( rule__PRUEFUNGSAKTION__Group__0 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:300:2: rule__PRUEFUNGSAKTION__Group__0
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSAKTION__Group__0_in_rulePRUEFUNGSAKTION574);
            rule__PRUEFUNGSAKTION__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPRUEFUNGSAKTIONAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePRUEFUNGSAKTION"


    // $ANTLR start "entryRuleAKTIONSID"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:312:1: entryRuleAKTIONSID : ruleAKTIONSID EOF ;
    public final void entryRuleAKTIONSID() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:313:1: ( ruleAKTIONSID EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:314:1: ruleAKTIONSID EOF
            {
             before(grammarAccess.getAKTIONSIDRule()); 
            pushFollow(FOLLOW_ruleAKTIONSID_in_entryRuleAKTIONSID601);
            ruleAKTIONSID();

            state._fsp--;

             after(grammarAccess.getAKTIONSIDRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAKTIONSID608); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAKTIONSID"


    // $ANTLR start "ruleAKTIONSID"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:321:1: ruleAKTIONSID : ( ( rule__AKTIONSID__Group__0 ) ) ;
    public final void ruleAKTIONSID() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:325:2: ( ( ( rule__AKTIONSID__Group__0 ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:326:1: ( ( rule__AKTIONSID__Group__0 ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:326:1: ( ( rule__AKTIONSID__Group__0 ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:327:1: ( rule__AKTIONSID__Group__0 )
            {
             before(grammarAccess.getAKTIONSIDAccess().getGroup()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:328:1: ( rule__AKTIONSID__Group__0 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:328:2: rule__AKTIONSID__Group__0
            {
            pushFollow(FOLLOW_rule__AKTIONSID__Group__0_in_ruleAKTIONSID634);
            rule__AKTIONSID__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAKTIONSIDAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAKTIONSID"


    // $ANTLR start "entryRulePRUEFUNGSWIRKUNG"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:340:1: entryRulePRUEFUNGSWIRKUNG : rulePRUEFUNGSWIRKUNG EOF ;
    public final void entryRulePRUEFUNGSWIRKUNG() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:341:1: ( rulePRUEFUNGSWIRKUNG EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:342:1: rulePRUEFUNGSWIRKUNG EOF
            {
             before(grammarAccess.getPRUEFUNGSWIRKUNGRule()); 
            pushFollow(FOLLOW_rulePRUEFUNGSWIRKUNG_in_entryRulePRUEFUNGSWIRKUNG661);
            rulePRUEFUNGSWIRKUNG();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSWIRKUNGRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePRUEFUNGSWIRKUNG668); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePRUEFUNGSWIRKUNG"


    // $ANTLR start "rulePRUEFUNGSWIRKUNG"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:349:1: rulePRUEFUNGSWIRKUNG : ( ( rule__PRUEFUNGSWIRKUNG__Group__0 ) ) ;
    public final void rulePRUEFUNGSWIRKUNG() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:353:2: ( ( ( rule__PRUEFUNGSWIRKUNG__Group__0 ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:354:1: ( ( rule__PRUEFUNGSWIRKUNG__Group__0 ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:354:1: ( ( rule__PRUEFUNGSWIRKUNG__Group__0 ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:355:1: ( rule__PRUEFUNGSWIRKUNG__Group__0 )
            {
             before(grammarAccess.getPRUEFUNGSWIRKUNGAccess().getGroup()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:356:1: ( rule__PRUEFUNGSWIRKUNG__Group__0 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:356:2: rule__PRUEFUNGSWIRKUNG__Group__0
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__0_in_rulePRUEFUNGSWIRKUNG694);
            rule__PRUEFUNGSWIRKUNG__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPRUEFUNGSWIRKUNGAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePRUEFUNGSWIRKUNG"


    // $ANTLR start "entryRuleWIRKUNGSID"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:368:1: entryRuleWIRKUNGSID : ruleWIRKUNGSID EOF ;
    public final void entryRuleWIRKUNGSID() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:369:1: ( ruleWIRKUNGSID EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:370:1: ruleWIRKUNGSID EOF
            {
             before(grammarAccess.getWIRKUNGSIDRule()); 
            pushFollow(FOLLOW_ruleWIRKUNGSID_in_entryRuleWIRKUNGSID721);
            ruleWIRKUNGSID();

            state._fsp--;

             after(grammarAccess.getWIRKUNGSIDRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWIRKUNGSID728); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleWIRKUNGSID"


    // $ANTLR start "ruleWIRKUNGSID"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:377:1: ruleWIRKUNGSID : ( ( rule__WIRKUNGSID__Group__0 ) ) ;
    public final void ruleWIRKUNGSID() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:381:2: ( ( ( rule__WIRKUNGSID__Group__0 ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:382:1: ( ( rule__WIRKUNGSID__Group__0 ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:382:1: ( ( rule__WIRKUNGSID__Group__0 ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:383:1: ( rule__WIRKUNGSID__Group__0 )
            {
             before(grammarAccess.getWIRKUNGSIDAccess().getGroup()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:384:1: ( rule__WIRKUNGSID__Group__0 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:384:2: rule__WIRKUNGSID__Group__0
            {
            pushFollow(FOLLOW_rule__WIRKUNGSID__Group__0_in_ruleWIRKUNGSID754);
            rule__WIRKUNGSID__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getWIRKUNGSIDAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWIRKUNGSID"


    // $ANTLR start "entryRuleZuweisung"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:396:1: entryRuleZuweisung : ruleZuweisung EOF ;
    public final void entryRuleZuweisung() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:397:1: ( ruleZuweisung EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:398:1: ruleZuweisung EOF
            {
             before(grammarAccess.getZuweisungRule()); 
            pushFollow(FOLLOW_ruleZuweisung_in_entryRuleZuweisung781);
            ruleZuweisung();

            state._fsp--;

             after(grammarAccess.getZuweisungRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleZuweisung788); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleZuweisung"


    // $ANTLR start "ruleZuweisung"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:405:1: ruleZuweisung : ( ( rule__Zuweisung__Group__0 ) ) ;
    public final void ruleZuweisung() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:409:2: ( ( ( rule__Zuweisung__Group__0 ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:410:1: ( ( rule__Zuweisung__Group__0 ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:410:1: ( ( rule__Zuweisung__Group__0 ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:411:1: ( rule__Zuweisung__Group__0 )
            {
             before(grammarAccess.getZuweisungAccess().getGroup()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:412:1: ( rule__Zuweisung__Group__0 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:412:2: rule__Zuweisung__Group__0
            {
            pushFollow(FOLLOW_rule__Zuweisung__Group__0_in_ruleZuweisung814);
            rule__Zuweisung__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getZuweisungAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleZuweisung"


    // $ANTLR start "entryRuleUsedIDs"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:424:1: entryRuleUsedIDs : ruleUsedIDs EOF ;
    public final void entryRuleUsedIDs() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:425:1: ( ruleUsedIDs EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:426:1: ruleUsedIDs EOF
            {
             before(grammarAccess.getUsedIDsRule()); 
            pushFollow(FOLLOW_ruleUsedIDs_in_entryRuleUsedIDs841);
            ruleUsedIDs();

            state._fsp--;

             after(grammarAccess.getUsedIDsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUsedIDs848); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleUsedIDs"


    // $ANTLR start "ruleUsedIDs"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:433:1: ruleUsedIDs : ( ( rule__UsedIDs__Group__0 ) ) ;
    public final void ruleUsedIDs() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:437:2: ( ( ( rule__UsedIDs__Group__0 ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:438:1: ( ( rule__UsedIDs__Group__0 ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:438:1: ( ( rule__UsedIDs__Group__0 ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:439:1: ( rule__UsedIDs__Group__0 )
            {
             before(grammarAccess.getUsedIDsAccess().getGroup()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:440:1: ( rule__UsedIDs__Group__0 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:440:2: rule__UsedIDs__Group__0
            {
            pushFollow(FOLLOW_rule__UsedIDs__Group__0_in_ruleUsedIDs874);
            rule__UsedIDs__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getUsedIDsAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUsedIDs"


    // $ANTLR start "entryRulePRUEFUNG"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:452:1: entryRulePRUEFUNG : rulePRUEFUNG EOF ;
    public final void entryRulePRUEFUNG() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:453:1: ( rulePRUEFUNG EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:454:1: rulePRUEFUNG EOF
            {
             before(grammarAccess.getPRUEFUNGRule()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_entryRulePRUEFUNG901);
            rulePRUEFUNG();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePRUEFUNG908); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePRUEFUNG"


    // $ANTLR start "rulePRUEFUNG"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:461:1: rulePRUEFUNG : ( RULE_INT ) ;
    public final void rulePRUEFUNG() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:465:2: ( ( RULE_INT ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:466:1: ( RULE_INT )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:466:1: ( RULE_INT )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:467:1: RULE_INT
            {
             before(grammarAccess.getPRUEFUNGAccess().getINTTerminalRuleCall()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rulePRUEFUNG934); 
             after(grammarAccess.getPRUEFUNGAccess().getINTTerminalRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePRUEFUNG"


    // $ANTLR start "entryRuleWIRKUNG"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:480:1: entryRuleWIRKUNG : ruleWIRKUNG EOF ;
    public final void entryRuleWIRKUNG() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:481:1: ( ruleWIRKUNG EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:482:1: ruleWIRKUNG EOF
            {
             before(grammarAccess.getWIRKUNGRule()); 
            pushFollow(FOLLOW_ruleWIRKUNG_in_entryRuleWIRKUNG960);
            ruleWIRKUNG();

            state._fsp--;

             after(grammarAccess.getWIRKUNGRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWIRKUNG967); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleWIRKUNG"


    // $ANTLR start "ruleWIRKUNG"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:489:1: ruleWIRKUNG : ( ( rule__WIRKUNG__Alternatives ) ) ;
    public final void ruleWIRKUNG() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:493:2: ( ( ( rule__WIRKUNG__Alternatives ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:494:1: ( ( rule__WIRKUNG__Alternatives ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:494:1: ( ( rule__WIRKUNG__Alternatives ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:495:1: ( rule__WIRKUNG__Alternatives )
            {
             before(grammarAccess.getWIRKUNGAccess().getAlternatives()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:496:1: ( rule__WIRKUNG__Alternatives )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:496:2: rule__WIRKUNG__Alternatives
            {
            pushFollow(FOLLOW_rule__WIRKUNG__Alternatives_in_ruleWIRKUNG993);
            rule__WIRKUNG__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getWIRKUNGAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWIRKUNG"


    // $ANTLR start "entryRuleANTRAGSART"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:508:1: entryRuleANTRAGSART : ruleANTRAGSART EOF ;
    public final void entryRuleANTRAGSART() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:509:1: ( ruleANTRAGSART EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:510:1: ruleANTRAGSART EOF
            {
             before(grammarAccess.getANTRAGSARTRule()); 
            pushFollow(FOLLOW_ruleANTRAGSART_in_entryRuleANTRAGSART1020);
            ruleANTRAGSART();

            state._fsp--;

             after(grammarAccess.getANTRAGSARTRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleANTRAGSART1027); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleANTRAGSART"


    // $ANTLR start "ruleANTRAGSART"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:517:1: ruleANTRAGSART : ( ( rule__ANTRAGSART__Alternatives ) ) ;
    public final void ruleANTRAGSART() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:521:2: ( ( ( rule__ANTRAGSART__Alternatives ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:522:1: ( ( rule__ANTRAGSART__Alternatives ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:522:1: ( ( rule__ANTRAGSART__Alternatives ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:523:1: ( rule__ANTRAGSART__Alternatives )
            {
             before(grammarAccess.getANTRAGSARTAccess().getAlternatives()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:524:1: ( rule__ANTRAGSART__Alternatives )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:524:2: rule__ANTRAGSART__Alternatives
            {
            pushFollow(FOLLOW_rule__ANTRAGSART__Alternatives_in_ruleANTRAGSART1053);
            rule__ANTRAGSART__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getANTRAGSARTAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleANTRAGSART"


    // $ANTLR start "entryRuleAKTION"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:536:1: entryRuleAKTION : ruleAKTION EOF ;
    public final void entryRuleAKTION() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:537:1: ( ruleAKTION EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:538:1: ruleAKTION EOF
            {
             before(grammarAccess.getAKTIONRule()); 
            pushFollow(FOLLOW_ruleAKTION_in_entryRuleAKTION1080);
            ruleAKTION();

            state._fsp--;

             after(grammarAccess.getAKTIONRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAKTION1087); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAKTION"


    // $ANTLR start "ruleAKTION"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:545:1: ruleAKTION : ( ( rule__AKTION__Alternatives ) ) ;
    public final void ruleAKTION() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:549:2: ( ( ( rule__AKTION__Alternatives ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:550:1: ( ( rule__AKTION__Alternatives ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:550:1: ( ( rule__AKTION__Alternatives ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:551:1: ( rule__AKTION__Alternatives )
            {
             before(grammarAccess.getAKTIONAccess().getAlternatives()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:552:1: ( rule__AKTION__Alternatives )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:552:2: rule__AKTION__Alternatives
            {
            pushFollow(FOLLOW_rule__AKTION__Alternatives_in_ruleAKTION1113);
            rule__AKTION__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getAKTIONAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAKTION"


    // $ANTLR start "entryRuleKLASSE"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:564:1: entryRuleKLASSE : ruleKLASSE EOF ;
    public final void entryRuleKLASSE() throws RecognitionException {
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:565:1: ( ruleKLASSE EOF )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:566:1: ruleKLASSE EOF
            {
             before(grammarAccess.getKLASSERule()); 
            pushFollow(FOLLOW_ruleKLASSE_in_entryRuleKLASSE1140);
            ruleKLASSE();

            state._fsp--;

             after(grammarAccess.getKLASSERule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleKLASSE1147); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleKLASSE"


    // $ANTLR start "ruleKLASSE"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:573:1: ruleKLASSE : ( ( rule__KLASSE__Alternatives ) ) ;
    public final void ruleKLASSE() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:577:2: ( ( ( rule__KLASSE__Alternatives ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:578:1: ( ( rule__KLASSE__Alternatives ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:578:1: ( ( rule__KLASSE__Alternatives ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:579:1: ( rule__KLASSE__Alternatives )
            {
             before(grammarAccess.getKLASSEAccess().getAlternatives()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:580:1: ( rule__KLASSE__Alternatives )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:580:2: rule__KLASSE__Alternatives
            {
            pushFollow(FOLLOW_rule__KLASSE__Alternatives_in_ruleKLASSE1173);
            rule__KLASSE__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getKLASSEAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleKLASSE"


    // $ANTLR start "rule__Konfiguration__Alternatives_3"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:592:1: rule__Konfiguration__Alternatives_3 : ( ( ( rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_0 ) ) | ( ( rule__Konfiguration__PruefungsaktionAssignment_3_1 ) ) | ( ( rule__Konfiguration__PruefungsklassennameAssignment_3_2 ) ) | ( ( rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_3 ) ) | ( ( rule__Konfiguration__PruefungslangtextAssignment_3_4 ) ) | ( ( rule__Konfiguration__PruefungswirkungAssignment_3_5 ) ) | ( ( rule__Konfiguration__PruefungsichtbarkeitAssignment_3_6 ) ) );
    public final void rule__Konfiguration__Alternatives_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:596:1: ( ( ( rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_0 ) ) | ( ( rule__Konfiguration__PruefungsaktionAssignment_3_1 ) ) | ( ( rule__Konfiguration__PruefungsklassennameAssignment_3_2 ) ) | ( ( rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_3 ) ) | ( ( rule__Konfiguration__PruefungslangtextAssignment_3_4 ) ) | ( ( rule__Konfiguration__PruefungswirkungAssignment_3_5 ) ) | ( ( rule__Konfiguration__PruefungsichtbarkeitAssignment_3_6 ) ) )
            int alt1=7;
            switch ( input.LA(1) ) {
            case 312:
                {
                alt1=1;
                }
                break;
            case 319:
                {
                alt1=2;
                }
                break;
            case 318:
                {
                alt1=3;
                }
                break;
            case 317:
                {
                alt1=4;
                }
                break;
            case 316:
                {
                alt1=5;
                }
                break;
            case 321:
                {
                alt1=6;
                }
                break;
            case 310:
                {
                alt1=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:597:1: ( ( rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_0 ) )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:597:1: ( ( rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_0 ) )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:598:1: ( rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_0 )
                    {
                     before(grammarAccess.getKonfigurationAccess().getVwkpkonfigurationfueraktionAssignment_3_0()); 
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:599:1: ( rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_0 )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:599:2: rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_0
                    {
                    pushFollow(FOLLOW_rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_0_in_rule__Konfiguration__Alternatives_31209);
                    rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getKonfigurationAccess().getVwkpkonfigurationfueraktionAssignment_3_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:603:6: ( ( rule__Konfiguration__PruefungsaktionAssignment_3_1 ) )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:603:6: ( ( rule__Konfiguration__PruefungsaktionAssignment_3_1 ) )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:604:1: ( rule__Konfiguration__PruefungsaktionAssignment_3_1 )
                    {
                     before(grammarAccess.getKonfigurationAccess().getPruefungsaktionAssignment_3_1()); 
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:605:1: ( rule__Konfiguration__PruefungsaktionAssignment_3_1 )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:605:2: rule__Konfiguration__PruefungsaktionAssignment_3_1
                    {
                    pushFollow(FOLLOW_rule__Konfiguration__PruefungsaktionAssignment_3_1_in_rule__Konfiguration__Alternatives_31227);
                    rule__Konfiguration__PruefungsaktionAssignment_3_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getKonfigurationAccess().getPruefungsaktionAssignment_3_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:609:6: ( ( rule__Konfiguration__PruefungsklassennameAssignment_3_2 ) )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:609:6: ( ( rule__Konfiguration__PruefungsklassennameAssignment_3_2 ) )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:610:1: ( rule__Konfiguration__PruefungsklassennameAssignment_3_2 )
                    {
                     before(grammarAccess.getKonfigurationAccess().getPruefungsklassennameAssignment_3_2()); 
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:611:1: ( rule__Konfiguration__PruefungsklassennameAssignment_3_2 )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:611:2: rule__Konfiguration__PruefungsklassennameAssignment_3_2
                    {
                    pushFollow(FOLLOW_rule__Konfiguration__PruefungsklassennameAssignment_3_2_in_rule__Konfiguration__Alternatives_31245);
                    rule__Konfiguration__PruefungsklassennameAssignment_3_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getKonfigurationAccess().getPruefungsklassennameAssignment_3_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:615:6: ( ( rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_3 ) )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:615:6: ( ( rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_3 ) )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:616:1: ( rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_3 )
                    {
                     before(grammarAccess.getKonfigurationAccess().getPruefungskurzbezeichnungAssignment_3_3()); 
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:617:1: ( rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_3 )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:617:2: rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_3
                    {
                    pushFollow(FOLLOW_rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_3_in_rule__Konfiguration__Alternatives_31263);
                    rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getKonfigurationAccess().getPruefungskurzbezeichnungAssignment_3_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:621:6: ( ( rule__Konfiguration__PruefungslangtextAssignment_3_4 ) )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:621:6: ( ( rule__Konfiguration__PruefungslangtextAssignment_3_4 ) )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:622:1: ( rule__Konfiguration__PruefungslangtextAssignment_3_4 )
                    {
                     before(grammarAccess.getKonfigurationAccess().getPruefungslangtextAssignment_3_4()); 
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:623:1: ( rule__Konfiguration__PruefungslangtextAssignment_3_4 )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:623:2: rule__Konfiguration__PruefungslangtextAssignment_3_4
                    {
                    pushFollow(FOLLOW_rule__Konfiguration__PruefungslangtextAssignment_3_4_in_rule__Konfiguration__Alternatives_31281);
                    rule__Konfiguration__PruefungslangtextAssignment_3_4();

                    state._fsp--;


                    }

                     after(grammarAccess.getKonfigurationAccess().getPruefungslangtextAssignment_3_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:627:6: ( ( rule__Konfiguration__PruefungswirkungAssignment_3_5 ) )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:627:6: ( ( rule__Konfiguration__PruefungswirkungAssignment_3_5 ) )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:628:1: ( rule__Konfiguration__PruefungswirkungAssignment_3_5 )
                    {
                     before(grammarAccess.getKonfigurationAccess().getPruefungswirkungAssignment_3_5()); 
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:629:1: ( rule__Konfiguration__PruefungswirkungAssignment_3_5 )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:629:2: rule__Konfiguration__PruefungswirkungAssignment_3_5
                    {
                    pushFollow(FOLLOW_rule__Konfiguration__PruefungswirkungAssignment_3_5_in_rule__Konfiguration__Alternatives_31299);
                    rule__Konfiguration__PruefungswirkungAssignment_3_5();

                    state._fsp--;


                    }

                     after(grammarAccess.getKonfigurationAccess().getPruefungswirkungAssignment_3_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:633:6: ( ( rule__Konfiguration__PruefungsichtbarkeitAssignment_3_6 ) )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:633:6: ( ( rule__Konfiguration__PruefungsichtbarkeitAssignment_3_6 ) )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:634:1: ( rule__Konfiguration__PruefungsichtbarkeitAssignment_3_6 )
                    {
                     before(grammarAccess.getKonfigurationAccess().getPruefungsichtbarkeitAssignment_3_6()); 
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:635:1: ( rule__Konfiguration__PruefungsichtbarkeitAssignment_3_6 )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:635:2: rule__Konfiguration__PruefungsichtbarkeitAssignment_3_6
                    {
                    pushFollow(FOLLOW_rule__Konfiguration__PruefungsichtbarkeitAssignment_3_6_in_rule__Konfiguration__Alternatives_31317);
                    rule__Konfiguration__PruefungsichtbarkeitAssignment_3_6();

                    state._fsp--;


                    }

                     after(grammarAccess.getKonfigurationAccess().getPruefungsichtbarkeitAssignment_3_6()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__Alternatives_3"


    // $ANTLR start "rule__VWKPTYP__Alternatives"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:644:1: rule__VWKPTYP__Alternatives : ( ( 'Automatisch' ) | ( 'Manuell' ) );
    public final void rule__VWKPTYP__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:648:1: ( ( 'Automatisch' ) | ( 'Manuell' ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==11) ) {
                alt2=1;
            }
            else if ( (LA2_0==12) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:649:1: ( 'Automatisch' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:649:1: ( 'Automatisch' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:650:1: 'Automatisch'
                    {
                     before(grammarAccess.getVWKPTYPAccess().getAutomatischKeyword_0()); 
                    match(input,11,FOLLOW_11_in_rule__VWKPTYP__Alternatives1351); 
                     after(grammarAccess.getVWKPTYPAccess().getAutomatischKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:657:6: ( 'Manuell' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:657:6: ( 'Manuell' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:658:1: 'Manuell'
                    {
                     before(grammarAccess.getVWKPTYPAccess().getManuellKeyword_1()); 
                    match(input,12,FOLLOW_12_in_rule__VWKPTYP__Alternatives1371); 
                     after(grammarAccess.getVWKPTYPAccess().getManuellKeyword_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VWKPTYP__Alternatives"


    // $ANTLR start "rule__WIRKUNG__Alternatives"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:670:1: rule__WIRKUNG__Alternatives : ( ( 'VERHINDERT_AKTION' ) | ( 'OHNE' ) | ( 'WARNUNG' ) );
    public final void rule__WIRKUNG__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:674:1: ( ( 'VERHINDERT_AKTION' ) | ( 'OHNE' ) | ( 'WARNUNG' ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt3=1;
                }
                break;
            case 14:
                {
                alt3=2;
                }
                break;
            case 15:
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:675:1: ( 'VERHINDERT_AKTION' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:675:1: ( 'VERHINDERT_AKTION' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:676:1: 'VERHINDERT_AKTION'
                    {
                     before(grammarAccess.getWIRKUNGAccess().getVERHINDERT_AKTIONKeyword_0()); 
                    match(input,13,FOLLOW_13_in_rule__WIRKUNG__Alternatives1406); 
                     after(grammarAccess.getWIRKUNGAccess().getVERHINDERT_AKTIONKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:683:6: ( 'OHNE' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:683:6: ( 'OHNE' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:684:1: 'OHNE'
                    {
                     before(grammarAccess.getWIRKUNGAccess().getOHNEKeyword_1()); 
                    match(input,14,FOLLOW_14_in_rule__WIRKUNG__Alternatives1426); 
                     after(grammarAccess.getWIRKUNGAccess().getOHNEKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:691:6: ( 'WARNUNG' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:691:6: ( 'WARNUNG' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:692:1: 'WARNUNG'
                    {
                     before(grammarAccess.getWIRKUNGAccess().getWARNUNGKeyword_2()); 
                    match(input,15,FOLLOW_15_in_rule__WIRKUNG__Alternatives1446); 
                     after(grammarAccess.getWIRKUNGAccess().getWARNUNGKeyword_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WIRKUNG__Alternatives"


    // $ANTLR start "rule__ANTRAGSART__Alternatives"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:704:1: rule__ANTRAGSART__Alternatives : ( ( 'AUSZANTRAG' ) | ( 'ERWANTRAG' ) | ( 'NEUANTRAG' ) | ( 'VERLANTRAG' ) );
    public final void rule__ANTRAGSART__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:708:1: ( ( 'AUSZANTRAG' ) | ( 'ERWANTRAG' ) | ( 'NEUANTRAG' ) | ( 'VERLANTRAG' ) )
            int alt4=4;
            switch ( input.LA(1) ) {
            case 16:
                {
                alt4=1;
                }
                break;
            case 17:
                {
                alt4=2;
                }
                break;
            case 18:
                {
                alt4=3;
                }
                break;
            case 19:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:709:1: ( 'AUSZANTRAG' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:709:1: ( 'AUSZANTRAG' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:710:1: 'AUSZANTRAG'
                    {
                     before(grammarAccess.getANTRAGSARTAccess().getAUSZANTRAGKeyword_0()); 
                    match(input,16,FOLLOW_16_in_rule__ANTRAGSART__Alternatives1481); 
                     after(grammarAccess.getANTRAGSARTAccess().getAUSZANTRAGKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:717:6: ( 'ERWANTRAG' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:717:6: ( 'ERWANTRAG' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:718:1: 'ERWANTRAG'
                    {
                     before(grammarAccess.getANTRAGSARTAccess().getERWANTRAGKeyword_1()); 
                    match(input,17,FOLLOW_17_in_rule__ANTRAGSART__Alternatives1501); 
                     after(grammarAccess.getANTRAGSARTAccess().getERWANTRAGKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:725:6: ( 'NEUANTRAG' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:725:6: ( 'NEUANTRAG' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:726:1: 'NEUANTRAG'
                    {
                     before(grammarAccess.getANTRAGSARTAccess().getNEUANTRAGKeyword_2()); 
                    match(input,18,FOLLOW_18_in_rule__ANTRAGSART__Alternatives1521); 
                     after(grammarAccess.getANTRAGSARTAccess().getNEUANTRAGKeyword_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:733:6: ( 'VERLANTRAG' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:733:6: ( 'VERLANTRAG' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:734:1: 'VERLANTRAG'
                    {
                     before(grammarAccess.getANTRAGSARTAccess().getVERLANTRAGKeyword_3()); 
                    match(input,19,FOLLOW_19_in_rule__ANTRAGSART__Alternatives1541); 
                     after(grammarAccess.getANTRAGSARTAccess().getVERLANTRAGKeyword_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ANTRAGSART__Alternatives"


    // $ANTLR start "rule__AKTION__Alternatives"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:746:1: rule__AKTION__Alternatives : ( ( 'BerechnenUndPruefen' ) | ( 'DokumentBearbeitungBeginnen' ) | ( 'Zurueckziehen' ) | ( 'NachberechnungStornieren' ) | ( 'ZurueckziehenZuruecknehmen' ) | ( 'AntragFreigeben' ) | ( 'AntragFreigabeZurueck' ) | ( 'DokumentBearbeitungBeenden' ) | ( 'AntragBewilligen' ) | ( 'AntragAblehnen' ) | ( 'AntragZahlungAnweisen' ) | ( 'AntragEntscheidungZurueck' ) | ( 'AntragNeuBearbeiten' ) | ( 'AntragWidersprechen' ) | ( 'AntragWiderspruchAblZurueck' ) | ( 'AntragWiderspruchZurueck' ) | ( 'AntragWiderspruchAblehnen' ) | ( 'AntragWiderspruchZulassen' ) | ( 'AntragWiderspruchZulZurueck' ) | ( 'AntragWiderspruchStattgeben' ) );
    public final void rule__AKTION__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:750:1: ( ( 'BerechnenUndPruefen' ) | ( 'DokumentBearbeitungBeginnen' ) | ( 'Zurueckziehen' ) | ( 'NachberechnungStornieren' ) | ( 'ZurueckziehenZuruecknehmen' ) | ( 'AntragFreigeben' ) | ( 'AntragFreigabeZurueck' ) | ( 'DokumentBearbeitungBeenden' ) | ( 'AntragBewilligen' ) | ( 'AntragAblehnen' ) | ( 'AntragZahlungAnweisen' ) | ( 'AntragEntscheidungZurueck' ) | ( 'AntragNeuBearbeiten' ) | ( 'AntragWidersprechen' ) | ( 'AntragWiderspruchAblZurueck' ) | ( 'AntragWiderspruchZurueck' ) | ( 'AntragWiderspruchAblehnen' ) | ( 'AntragWiderspruchZulassen' ) | ( 'AntragWiderspruchZulZurueck' ) | ( 'AntragWiderspruchStattgeben' ) )
            int alt5=20;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt5=1;
                }
                break;
            case 21:
                {
                alt5=2;
                }
                break;
            case 22:
                {
                alt5=3;
                }
                break;
            case 23:
                {
                alt5=4;
                }
                break;
            case 24:
                {
                alt5=5;
                }
                break;
            case 25:
                {
                alt5=6;
                }
                break;
            case 26:
                {
                alt5=7;
                }
                break;
            case 27:
                {
                alt5=8;
                }
                break;
            case 28:
                {
                alt5=9;
                }
                break;
            case 29:
                {
                alt5=10;
                }
                break;
            case 30:
                {
                alt5=11;
                }
                break;
            case 31:
                {
                alt5=12;
                }
                break;
            case 32:
                {
                alt5=13;
                }
                break;
            case 33:
                {
                alt5=14;
                }
                break;
            case 34:
                {
                alt5=15;
                }
                break;
            case 35:
                {
                alt5=16;
                }
                break;
            case 36:
                {
                alt5=17;
                }
                break;
            case 37:
                {
                alt5=18;
                }
                break;
            case 38:
                {
                alt5=19;
                }
                break;
            case 39:
                {
                alt5=20;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:751:1: ( 'BerechnenUndPruefen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:751:1: ( 'BerechnenUndPruefen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:752:1: 'BerechnenUndPruefen'
                    {
                     before(grammarAccess.getAKTIONAccess().getBerechnenUndPruefenKeyword_0()); 
                    match(input,20,FOLLOW_20_in_rule__AKTION__Alternatives1576); 
                     after(grammarAccess.getAKTIONAccess().getBerechnenUndPruefenKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:759:6: ( 'DokumentBearbeitungBeginnen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:759:6: ( 'DokumentBearbeitungBeginnen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:760:1: 'DokumentBearbeitungBeginnen'
                    {
                     before(grammarAccess.getAKTIONAccess().getDokumentBearbeitungBeginnenKeyword_1()); 
                    match(input,21,FOLLOW_21_in_rule__AKTION__Alternatives1596); 
                     after(grammarAccess.getAKTIONAccess().getDokumentBearbeitungBeginnenKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:767:6: ( 'Zurueckziehen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:767:6: ( 'Zurueckziehen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:768:1: 'Zurueckziehen'
                    {
                     before(grammarAccess.getAKTIONAccess().getZurueckziehenKeyword_2()); 
                    match(input,22,FOLLOW_22_in_rule__AKTION__Alternatives1616); 
                     after(grammarAccess.getAKTIONAccess().getZurueckziehenKeyword_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:775:6: ( 'NachberechnungStornieren' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:775:6: ( 'NachberechnungStornieren' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:776:1: 'NachberechnungStornieren'
                    {
                     before(grammarAccess.getAKTIONAccess().getNachberechnungStornierenKeyword_3()); 
                    match(input,23,FOLLOW_23_in_rule__AKTION__Alternatives1636); 
                     after(grammarAccess.getAKTIONAccess().getNachberechnungStornierenKeyword_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:783:6: ( 'ZurueckziehenZuruecknehmen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:783:6: ( 'ZurueckziehenZuruecknehmen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:784:1: 'ZurueckziehenZuruecknehmen'
                    {
                     before(grammarAccess.getAKTIONAccess().getZurueckziehenZuruecknehmenKeyword_4()); 
                    match(input,24,FOLLOW_24_in_rule__AKTION__Alternatives1656); 
                     after(grammarAccess.getAKTIONAccess().getZurueckziehenZuruecknehmenKeyword_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:791:6: ( 'AntragFreigeben' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:791:6: ( 'AntragFreigeben' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:792:1: 'AntragFreigeben'
                    {
                     before(grammarAccess.getAKTIONAccess().getAntragFreigebenKeyword_5()); 
                    match(input,25,FOLLOW_25_in_rule__AKTION__Alternatives1676); 
                     after(grammarAccess.getAKTIONAccess().getAntragFreigebenKeyword_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:799:6: ( 'AntragFreigabeZurueck' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:799:6: ( 'AntragFreigabeZurueck' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:800:1: 'AntragFreigabeZurueck'
                    {
                     before(grammarAccess.getAKTIONAccess().getAntragFreigabeZurueckKeyword_6()); 
                    match(input,26,FOLLOW_26_in_rule__AKTION__Alternatives1696); 
                     after(grammarAccess.getAKTIONAccess().getAntragFreigabeZurueckKeyword_6()); 

                    }


                    }
                    break;
                case 8 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:807:6: ( 'DokumentBearbeitungBeenden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:807:6: ( 'DokumentBearbeitungBeenden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:808:1: 'DokumentBearbeitungBeenden'
                    {
                     before(grammarAccess.getAKTIONAccess().getDokumentBearbeitungBeendenKeyword_7()); 
                    match(input,27,FOLLOW_27_in_rule__AKTION__Alternatives1716); 
                     after(grammarAccess.getAKTIONAccess().getDokumentBearbeitungBeendenKeyword_7()); 

                    }


                    }
                    break;
                case 9 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:815:6: ( 'AntragBewilligen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:815:6: ( 'AntragBewilligen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:816:1: 'AntragBewilligen'
                    {
                     before(grammarAccess.getAKTIONAccess().getAntragBewilligenKeyword_8()); 
                    match(input,28,FOLLOW_28_in_rule__AKTION__Alternatives1736); 
                     after(grammarAccess.getAKTIONAccess().getAntragBewilligenKeyword_8()); 

                    }


                    }
                    break;
                case 10 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:823:6: ( 'AntragAblehnen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:823:6: ( 'AntragAblehnen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:824:1: 'AntragAblehnen'
                    {
                     before(grammarAccess.getAKTIONAccess().getAntragAblehnenKeyword_9()); 
                    match(input,29,FOLLOW_29_in_rule__AKTION__Alternatives1756); 
                     after(grammarAccess.getAKTIONAccess().getAntragAblehnenKeyword_9()); 

                    }


                    }
                    break;
                case 11 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:831:6: ( 'AntragZahlungAnweisen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:831:6: ( 'AntragZahlungAnweisen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:832:1: 'AntragZahlungAnweisen'
                    {
                     before(grammarAccess.getAKTIONAccess().getAntragZahlungAnweisenKeyword_10()); 
                    match(input,30,FOLLOW_30_in_rule__AKTION__Alternatives1776); 
                     after(grammarAccess.getAKTIONAccess().getAntragZahlungAnweisenKeyword_10()); 

                    }


                    }
                    break;
                case 12 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:839:6: ( 'AntragEntscheidungZurueck' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:839:6: ( 'AntragEntscheidungZurueck' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:840:1: 'AntragEntscheidungZurueck'
                    {
                     before(grammarAccess.getAKTIONAccess().getAntragEntscheidungZurueckKeyword_11()); 
                    match(input,31,FOLLOW_31_in_rule__AKTION__Alternatives1796); 
                     after(grammarAccess.getAKTIONAccess().getAntragEntscheidungZurueckKeyword_11()); 

                    }


                    }
                    break;
                case 13 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:847:6: ( 'AntragNeuBearbeiten' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:847:6: ( 'AntragNeuBearbeiten' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:848:1: 'AntragNeuBearbeiten'
                    {
                     before(grammarAccess.getAKTIONAccess().getAntragNeuBearbeitenKeyword_12()); 
                    match(input,32,FOLLOW_32_in_rule__AKTION__Alternatives1816); 
                     after(grammarAccess.getAKTIONAccess().getAntragNeuBearbeitenKeyword_12()); 

                    }


                    }
                    break;
                case 14 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:855:6: ( 'AntragWidersprechen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:855:6: ( 'AntragWidersprechen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:856:1: 'AntragWidersprechen'
                    {
                     before(grammarAccess.getAKTIONAccess().getAntragWidersprechenKeyword_13()); 
                    match(input,33,FOLLOW_33_in_rule__AKTION__Alternatives1836); 
                     after(grammarAccess.getAKTIONAccess().getAntragWidersprechenKeyword_13()); 

                    }


                    }
                    break;
                case 15 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:863:6: ( 'AntragWiderspruchAblZurueck' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:863:6: ( 'AntragWiderspruchAblZurueck' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:864:1: 'AntragWiderspruchAblZurueck'
                    {
                     before(grammarAccess.getAKTIONAccess().getAntragWiderspruchAblZurueckKeyword_14()); 
                    match(input,34,FOLLOW_34_in_rule__AKTION__Alternatives1856); 
                     after(grammarAccess.getAKTIONAccess().getAntragWiderspruchAblZurueckKeyword_14()); 

                    }


                    }
                    break;
                case 16 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:871:6: ( 'AntragWiderspruchZurueck' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:871:6: ( 'AntragWiderspruchZurueck' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:872:1: 'AntragWiderspruchZurueck'
                    {
                     before(grammarAccess.getAKTIONAccess().getAntragWiderspruchZurueckKeyword_15()); 
                    match(input,35,FOLLOW_35_in_rule__AKTION__Alternatives1876); 
                     after(grammarAccess.getAKTIONAccess().getAntragWiderspruchZurueckKeyword_15()); 

                    }


                    }
                    break;
                case 17 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:879:6: ( 'AntragWiderspruchAblehnen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:879:6: ( 'AntragWiderspruchAblehnen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:880:1: 'AntragWiderspruchAblehnen'
                    {
                     before(grammarAccess.getAKTIONAccess().getAntragWiderspruchAblehnenKeyword_16()); 
                    match(input,36,FOLLOW_36_in_rule__AKTION__Alternatives1896); 
                     after(grammarAccess.getAKTIONAccess().getAntragWiderspruchAblehnenKeyword_16()); 

                    }


                    }
                    break;
                case 18 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:887:6: ( 'AntragWiderspruchZulassen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:887:6: ( 'AntragWiderspruchZulassen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:888:1: 'AntragWiderspruchZulassen'
                    {
                     before(grammarAccess.getAKTIONAccess().getAntragWiderspruchZulassenKeyword_17()); 
                    match(input,37,FOLLOW_37_in_rule__AKTION__Alternatives1916); 
                     after(grammarAccess.getAKTIONAccess().getAntragWiderspruchZulassenKeyword_17()); 

                    }


                    }
                    break;
                case 19 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:895:6: ( 'AntragWiderspruchZulZurueck' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:895:6: ( 'AntragWiderspruchZulZurueck' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:896:1: 'AntragWiderspruchZulZurueck'
                    {
                     before(grammarAccess.getAKTIONAccess().getAntragWiderspruchZulZurueckKeyword_18()); 
                    match(input,38,FOLLOW_38_in_rule__AKTION__Alternatives1936); 
                     after(grammarAccess.getAKTIONAccess().getAntragWiderspruchZulZurueckKeyword_18()); 

                    }


                    }
                    break;
                case 20 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:903:6: ( 'AntragWiderspruchStattgeben' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:903:6: ( 'AntragWiderspruchStattgeben' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:904:1: 'AntragWiderspruchStattgeben'
                    {
                     before(grammarAccess.getAKTIONAccess().getAntragWiderspruchStattgebenKeyword_19()); 
                    match(input,39,FOLLOW_39_in_rule__AKTION__Alternatives1956); 
                     after(grammarAccess.getAKTIONAccess().getAntragWiderspruchStattgebenKeyword_19()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AKTION__Alternatives"


    // $ANTLR start "rule__KLASSE__Alternatives"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:916:1: rule__KLASSE__Alternatives : ( ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusAusRechenschritt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusBagatellbetragTeilmassnahmen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PA4AugenPrinzip' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAblehnungsgruendeVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenArt17Abs1' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenBezugVerstoesseVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngaben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngabenStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalschGemachteUnregelmaessigkeit' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbtretungenInZahlungVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzuegeErhoehung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaft' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngaben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngabenBeendetOhneAenderungsblatt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAltverpflichtungUeberschritten' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenAUM' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenErsterfassungBeendetAJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitungStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageTierhaltung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilBluehflaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreide' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreideAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGruenland' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilKkLeguminosenAL5Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosenAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingang' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangNichtNachAusschlussTermin' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsflaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsteller' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstellerKeineJuristischePerson' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstermin' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsterminNichtVerfristet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJBewilligt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJNichtAbgelehnt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragZurueckziehen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenInklusiveLeguminosen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtGroesserAls100ProHa' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtKleinerAls30' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappe' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappeAktuellUndDurchgefuehrt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlatt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaUZWAktuellUndBeendet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweis' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweisBeendet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusbringungstechnik' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuellt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusschluss' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragAuszahlung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungBeruecksichtigungHalbeZahlung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2002' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2007' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungUeberGesamteLaufzeit' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragNeuAntrag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragRueckforderung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindungStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragteFlaecheKleiner80Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtGLGleichGesamtGL' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtMindestens5ProzAckerflaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner10ProzAckerflaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner2ha' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner5ProzAckerflaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzAusHITUnterBeruecksichtigungGruenland' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_2' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_3' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHIT' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABescheidInAktuellerBerechnung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABestaetigungsVermerk' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABewilligterNAImEAJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuell' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuellUndBeendet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADatumEingangGroesserAntrag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzBewilligtVJUndBeantragtGroesserBagatellbetrag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaecheWiederholt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCC' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCV' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCVImVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGL' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGLSchwellwert' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangVerpflichtungsflaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEntscheidungenBescheidart' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErstantragsjahr' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10bzw2haOdGroesser50ProzentVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10odGroesser50ProzentVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50Proz' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50ProzOder2Ha' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungLE50ProzentVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungsflaecheVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAExtensiveBewirtschaftungGLGleichGesamtGL' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngaben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngabenStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs5' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6inAnderemFP' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt18Abs3' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschGemachteAngaben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerderfaehigGroesserNull' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerdergebietGroesserGleich3Hektar' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM141Mindestens5ProzentAFAusEaj' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM14Mindestens5ProzentAFAusEaj' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheStreuobstwiesen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGebuehrenrechnung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung30Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung50Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtrueckforderung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtsanktionierung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuGross' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuKlein' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrfImAA' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrobFahrlaessigeGemachteAngaben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGuellemenge' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAHofuebergabe' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerBestaetigungVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerVereinbarungVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKapitalbeteiligungOeffentlHandGroesser25Proz' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinAntragVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinBescheidInAktuellerBerechnung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinDungAufnahmeOderAbgabe' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhandenTm' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineParalleBeantragungM5UndM6' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinePheromonGemeinschaft' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAktiengesellschaft' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAnstaltDesOeffentlRechts' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformGmbH' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformJuristischePersonOeffentlRecht' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformKoerperschaftDesOeffentlichenRechts' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformOeffentlRechtlStiftung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformPrivatRechtlStiftung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonOeffentlRecht' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonPrivatRecht' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeNatuerlichePerson' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRelevantenEntscheidungenOffen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformAnerkannteWeidegemeinschaft' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformEinzelantragstellerMeka' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformPheromongemeinschaft' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformWaldgemeinschaft' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinFC104anBindungVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinGLAusErzeugungGenommenNutzung592' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinKlaerschlammAusgebracht' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB2' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKombinierteGLAntraege' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollkostenzuschussMitND2Teilmassnahme' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollprotokoll' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollvertrag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung20Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung30Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentB1610' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALaborbeanstandungenLiegenNichtVor' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALandwirt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogenStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaschinelleBerechnungVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusHIT' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF2010' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusHIT' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestFlaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestumfangWinterbegruenung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFFAusHIT' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3GveProHaLNF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaGL' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusHIT' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_5RgvProHaGL' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_6GVEProHaFF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMittelverwaltung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANachOeffnenBerechnet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtAlleChecksWurdenBearbeitet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtZuWenigAckerfutterAngebaut' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANurNachberechnungStornierbar' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelBewilligterNABeiEajGleichAj' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAAEntschieden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragFP773' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragZuFP774' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendig' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendigLZJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungFlaechenmappeVj' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungVj' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleich' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichBeendetVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichDurchgefuehrtVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaenger' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaengerAlsEinzelunternehmer' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeit' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitEinJahr' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitVNS' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVJVokAJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVok' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokCc' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungenOderNullzahlung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASGAbgleich' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstFeststellungenAbsichtlUnregelmaessigkeiten' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASperrvermerkNichtVergeben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStammdatenAktuell' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStichtagHelper' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATeilmassnahmeND1UndND2Beantragt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbesatzGesamt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFundGveProHaLF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFZusNCs' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUmwandlungALInGL' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenAusserhalbDerEU' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenOhneSchafeZiegenMitVertragVereinbarung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenssitzInBw' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschiedBerechneteUndManuelleVerpflFlFJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschreitungTierbestzDurchFeststellung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNC' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNcVNS' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheAckerfutter' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentimVJVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18Beanstandungen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18BeanstandungenVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJFuerMindEineBindung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsNrFuerAlleFlaechenVergeben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsnummer' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVeterinaerBestaetigungVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKCCBeendet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVokNichtVerweigert' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKPEnthaeltBenutzerdefPruefkonf' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKReferenzflaechenAbgleich' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragsteller' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragstellerStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWeidetagebuchImPebVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWiderspruchInVorherigerBerechnung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFK' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PASperrvermerkNichtVergeben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' ) );
    public final void rule__KLASSE__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:920:1: ( ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusAusRechenschritt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusBagatellbetragTeilmassnahmen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PA4AugenPrinzip' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAblehnungsgruendeVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenArt17Abs1' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenBezugVerstoesseVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngaben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngabenStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalschGemachteUnregelmaessigkeit' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbtretungenInZahlungVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzuegeErhoehung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaft' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngaben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngabenBeendetOhneAenderungsblatt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAltverpflichtungUeberschritten' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenAUM' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenErsterfassungBeendetAJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitungStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageTierhaltung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilBluehflaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreide' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreideAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGruenland' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilKkLeguminosenAL5Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosenAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingang' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangNichtNachAusschlussTermin' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsflaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsteller' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstellerKeineJuristischePerson' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstermin' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsterminNichtVerfristet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJBewilligt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJNichtAbgelehnt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragZurueckziehen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenInklusiveLeguminosen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtGroesserAls100ProHa' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtKleinerAls30' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappe' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappeAktuellUndDurchgefuehrt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlatt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaUZWAktuellUndBeendet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweis' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweisBeendet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusbringungstechnik' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuellt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusschluss' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragAuszahlung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungBeruecksichtigungHalbeZahlung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2002' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2007' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungUeberGesamteLaufzeit' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragNeuAntrag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragRueckforderung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindungStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragteFlaecheKleiner80Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtGLGleichGesamtGL' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtMindestens5ProzAckerflaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner10ProzAckerflaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner2ha' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner5ProzAckerflaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzAusHITUnterBeruecksichtigungGruenland' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_2' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_3' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHIT' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABescheidInAktuellerBerechnung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABestaetigungsVermerk' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABewilligterNAImEAJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuell' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuellUndBeendet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADatumEingangGroesserAntrag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzBewilligtVJUndBeantragtGroesserBagatellbetrag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaecheWiederholt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCC' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCV' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCVImVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGL' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGLSchwellwert' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangVerpflichtungsflaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEntscheidungenBescheidart' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErstantragsjahr' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10bzw2haOdGroesser50ProzentVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10odGroesser50ProzentVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50Proz' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50ProzOder2Ha' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungLE50ProzentVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungsflaecheVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAExtensiveBewirtschaftungGLGleichGesamtGL' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngaben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngabenStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs5' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6inAnderemFP' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt18Abs3' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschGemachteAngaben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerderfaehigGroesserNull' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerdergebietGroesserGleich3Hektar' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM141Mindestens5ProzentAFAusEaj' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM14Mindestens5ProzentAFAusEaj' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheStreuobstwiesen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGebuehrenrechnung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung30Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung50Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtrueckforderung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtsanktionierung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuGross' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuKlein' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrfImAA' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrobFahrlaessigeGemachteAngaben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGuellemenge' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAHofuebergabe' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerBestaetigungVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerVereinbarungVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKapitalbeteiligungOeffentlHandGroesser25Proz' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinAntragVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinBescheidInAktuellerBerechnung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinDungAufnahmeOderAbgabe' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhandenTm' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineParalleBeantragungM5UndM6' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinePheromonGemeinschaft' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAktiengesellschaft' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAnstaltDesOeffentlRechts' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformGmbH' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformJuristischePersonOeffentlRecht' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformKoerperschaftDesOeffentlichenRechts' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformOeffentlRechtlStiftung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformPrivatRechtlStiftung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonOeffentlRecht' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonPrivatRecht' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeNatuerlichePerson' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRelevantenEntscheidungenOffen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformAnerkannteWeidegemeinschaft' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformEinzelantragstellerMeka' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformPheromongemeinschaft' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformWaldgemeinschaft' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinFC104anBindungVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinGLAusErzeugungGenommenNutzung592' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinKlaerschlammAusgebracht' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB2' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKombinierteGLAntraege' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollkostenzuschussMitND2Teilmassnahme' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollprotokoll' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollvertrag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung20Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung30Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50Prozent' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentB1610' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALaborbeanstandungenLiegenNichtVor' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALandwirt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogenStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaschinelleBerechnungVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusHIT' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF2010' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusHIT' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestFlaeche' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestumfangWinterbegruenung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFFAusHIT' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3GveProHaLNF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaGL' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusHIT' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_5RgvProHaGL' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_6GVEProHaFF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMittelverwaltung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANachOeffnenBerechnet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtAlleChecksWurdenBearbeitet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtZuWenigAckerfutterAngebaut' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANurNachberechnungStornierbar' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelBewilligterNABeiEajGleichAj' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAAEntschieden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragFP773' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragZuFP774' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendig' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendigLZJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungFlaechenmappeVj' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungVj' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleich' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichBeendetVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichDurchgefuehrtVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaenger' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaengerAlsEinzelunternehmer' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeit' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitEinJahr' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitVNS' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVJVokAJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVok' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokCc' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungenOderNullzahlung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASGAbgleich' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstFeststellungenAbsichtlUnregelmaessigkeiten' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASperrvermerkNichtVergeben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStammdatenAktuell' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStichtagHelper' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATeilmassnahmeND1UndND2Beantragt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbesatzGesamt' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFundGveProHaLF' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFZusNCs' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUmwandlungALInGL' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenAusserhalbDerEU' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenOhneSchafeZiegenMitVertragVereinbarung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenssitzInBw' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschiedBerechneteUndManuelleVerpflFlFJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschreitungTierbestzDurchFeststellung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNC' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNcVNS' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheAckerfutter' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentimVJVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18Beanstandungen' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18BeanstandungenVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJFuerMindEineBindung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsNrFuerAlleFlaechenVergeben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsnummer' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVeterinaerBestaetigungVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKCCBeendet' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVokNichtVerweigert' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKPEnthaeltBenutzerdefPruefkonf' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKReferenzflaechenAbgleich' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragsteller' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragstellerStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWeidetagebuchImPebVorhanden' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWiderspruchInVorherigerBerechnung' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFK' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKStichtag' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKVJ' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PASperrvermerkNichtVergeben' ) | ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' ) )
            int alt6=270;
            switch ( input.LA(1) ) {
            case 40:
                {
                alt6=1;
                }
                break;
            case 41:
                {
                alt6=2;
                }
                break;
            case 42:
                {
                alt6=3;
                }
                break;
            case 43:
                {
                alt6=4;
                }
                break;
            case 44:
                {
                alt6=5;
                }
                break;
            case 45:
                {
                alt6=6;
                }
                break;
            case 46:
                {
                alt6=7;
                }
                break;
            case 47:
                {
                alt6=8;
                }
                break;
            case 48:
                {
                alt6=9;
                }
                break;
            case 49:
                {
                alt6=10;
                }
                break;
            case 50:
                {
                alt6=11;
                }
                break;
            case 51:
                {
                alt6=12;
                }
                break;
            case 52:
                {
                alt6=13;
                }
                break;
            case 53:
                {
                alt6=14;
                }
                break;
            case 54:
                {
                alt6=15;
                }
                break;
            case 55:
                {
                alt6=16;
                }
                break;
            case 56:
                {
                alt6=17;
                }
                break;
            case 57:
                {
                alt6=18;
                }
                break;
            case 58:
                {
                alt6=19;
                }
                break;
            case 59:
                {
                alt6=20;
                }
                break;
            case 60:
                {
                alt6=21;
                }
                break;
            case 61:
                {
                alt6=22;
                }
                break;
            case 62:
                {
                alt6=23;
                }
                break;
            case 63:
                {
                alt6=24;
                }
                break;
            case 64:
                {
                alt6=25;
                }
                break;
            case 65:
                {
                alt6=26;
                }
                break;
            case 66:
                {
                alt6=27;
                }
                break;
            case 67:
                {
                alt6=28;
                }
                break;
            case 68:
                {
                alt6=29;
                }
                break;
            case 69:
                {
                alt6=30;
                }
                break;
            case 70:
                {
                alt6=31;
                }
                break;
            case 71:
                {
                alt6=32;
                }
                break;
            case 72:
                {
                alt6=33;
                }
                break;
            case 73:
                {
                alt6=34;
                }
                break;
            case 74:
                {
                alt6=35;
                }
                break;
            case 75:
                {
                alt6=36;
                }
                break;
            case 76:
                {
                alt6=37;
                }
                break;
            case 77:
                {
                alt6=38;
                }
                break;
            case 78:
                {
                alt6=39;
                }
                break;
            case 79:
                {
                alt6=40;
                }
                break;
            case 80:
                {
                alt6=41;
                }
                break;
            case 81:
                {
                alt6=42;
                }
                break;
            case 82:
                {
                alt6=43;
                }
                break;
            case 83:
                {
                alt6=44;
                }
                break;
            case 84:
                {
                alt6=45;
                }
                break;
            case 85:
                {
                alt6=46;
                }
                break;
            case 86:
                {
                alt6=47;
                }
                break;
            case 87:
                {
                alt6=48;
                }
                break;
            case 88:
                {
                alt6=49;
                }
                break;
            case 89:
                {
                alt6=50;
                }
                break;
            case 90:
                {
                alt6=51;
                }
                break;
            case 91:
                {
                alt6=52;
                }
                break;
            case 92:
                {
                alt6=53;
                }
                break;
            case 93:
                {
                alt6=54;
                }
                break;
            case 94:
                {
                alt6=55;
                }
                break;
            case 95:
                {
                alt6=56;
                }
                break;
            case 96:
                {
                alt6=57;
                }
                break;
            case 97:
                {
                alt6=58;
                }
                break;
            case 98:
                {
                alt6=59;
                }
                break;
            case 99:
                {
                alt6=60;
                }
                break;
            case 100:
                {
                alt6=61;
                }
                break;
            case 101:
                {
                alt6=62;
                }
                break;
            case 102:
                {
                alt6=63;
                }
                break;
            case 103:
                {
                alt6=64;
                }
                break;
            case 104:
                {
                alt6=65;
                }
                break;
            case 105:
                {
                alt6=66;
                }
                break;
            case 106:
                {
                alt6=67;
                }
                break;
            case 107:
                {
                alt6=68;
                }
                break;
            case 108:
                {
                alt6=69;
                }
                break;
            case 109:
                {
                alt6=70;
                }
                break;
            case 110:
                {
                alt6=71;
                }
                break;
            case 111:
                {
                alt6=72;
                }
                break;
            case 112:
                {
                alt6=73;
                }
                break;
            case 113:
                {
                alt6=74;
                }
                break;
            case 114:
                {
                alt6=75;
                }
                break;
            case 115:
                {
                alt6=76;
                }
                break;
            case 116:
                {
                alt6=77;
                }
                break;
            case 117:
                {
                alt6=78;
                }
                break;
            case 118:
                {
                alt6=79;
                }
                break;
            case 119:
                {
                alt6=80;
                }
                break;
            case 120:
                {
                alt6=81;
                }
                break;
            case 121:
                {
                alt6=82;
                }
                break;
            case 122:
                {
                alt6=83;
                }
                break;
            case 123:
                {
                alt6=84;
                }
                break;
            case 124:
                {
                alt6=85;
                }
                break;
            case 125:
                {
                alt6=86;
                }
                break;
            case 126:
                {
                alt6=87;
                }
                break;
            case 127:
                {
                alt6=88;
                }
                break;
            case 128:
                {
                alt6=89;
                }
                break;
            case 129:
                {
                alt6=90;
                }
                break;
            case 130:
                {
                alt6=91;
                }
                break;
            case 131:
                {
                alt6=92;
                }
                break;
            case 132:
                {
                alt6=93;
                }
                break;
            case 133:
                {
                alt6=94;
                }
                break;
            case 134:
                {
                alt6=95;
                }
                break;
            case 135:
                {
                alt6=96;
                }
                break;
            case 136:
                {
                alt6=97;
                }
                break;
            case 137:
                {
                alt6=98;
                }
                break;
            case 138:
                {
                alt6=99;
                }
                break;
            case 139:
                {
                alt6=100;
                }
                break;
            case 140:
                {
                alt6=101;
                }
                break;
            case 141:
                {
                alt6=102;
                }
                break;
            case 142:
                {
                alt6=103;
                }
                break;
            case 143:
                {
                alt6=104;
                }
                break;
            case 144:
                {
                alt6=105;
                }
                break;
            case 145:
                {
                alt6=106;
                }
                break;
            case 146:
                {
                alt6=107;
                }
                break;
            case 147:
                {
                alt6=108;
                }
                break;
            case 148:
                {
                alt6=109;
                }
                break;
            case 149:
                {
                alt6=110;
                }
                break;
            case 150:
                {
                alt6=111;
                }
                break;
            case 151:
                {
                alt6=112;
                }
                break;
            case 152:
                {
                alt6=113;
                }
                break;
            case 153:
                {
                alt6=114;
                }
                break;
            case 154:
                {
                alt6=115;
                }
                break;
            case 155:
                {
                alt6=116;
                }
                break;
            case 156:
                {
                alt6=117;
                }
                break;
            case 157:
                {
                alt6=118;
                }
                break;
            case 158:
                {
                alt6=119;
                }
                break;
            case 159:
                {
                alt6=120;
                }
                break;
            case 160:
                {
                alt6=121;
                }
                break;
            case 161:
                {
                alt6=122;
                }
                break;
            case 162:
                {
                alt6=123;
                }
                break;
            case 163:
                {
                alt6=124;
                }
                break;
            case 164:
                {
                alt6=125;
                }
                break;
            case 165:
                {
                alt6=126;
                }
                break;
            case 166:
                {
                alt6=127;
                }
                break;
            case 167:
                {
                alt6=128;
                }
                break;
            case 168:
                {
                alt6=129;
                }
                break;
            case 169:
                {
                alt6=130;
                }
                break;
            case 170:
                {
                alt6=131;
                }
                break;
            case 171:
                {
                alt6=132;
                }
                break;
            case 172:
                {
                alt6=133;
                }
                break;
            case 173:
                {
                alt6=134;
                }
                break;
            case 174:
                {
                alt6=135;
                }
                break;
            case 175:
                {
                alt6=136;
                }
                break;
            case 176:
                {
                alt6=137;
                }
                break;
            case 177:
                {
                alt6=138;
                }
                break;
            case 178:
                {
                alt6=139;
                }
                break;
            case 179:
                {
                alt6=140;
                }
                break;
            case 180:
                {
                alt6=141;
                }
                break;
            case 181:
                {
                alt6=142;
                }
                break;
            case 182:
                {
                alt6=143;
                }
                break;
            case 183:
                {
                alt6=144;
                }
                break;
            case 184:
                {
                alt6=145;
                }
                break;
            case 185:
                {
                alt6=146;
                }
                break;
            case 186:
                {
                alt6=147;
                }
                break;
            case 187:
                {
                alt6=148;
                }
                break;
            case 188:
                {
                alt6=149;
                }
                break;
            case 189:
                {
                alt6=150;
                }
                break;
            case 190:
                {
                alt6=151;
                }
                break;
            case 191:
                {
                alt6=152;
                }
                break;
            case 192:
                {
                alt6=153;
                }
                break;
            case 193:
                {
                alt6=154;
                }
                break;
            case 194:
                {
                alt6=155;
                }
                break;
            case 195:
                {
                alt6=156;
                }
                break;
            case 196:
                {
                alt6=157;
                }
                break;
            case 197:
                {
                alt6=158;
                }
                break;
            case 198:
                {
                alt6=159;
                }
                break;
            case 199:
                {
                alt6=160;
                }
                break;
            case 200:
                {
                alt6=161;
                }
                break;
            case 201:
                {
                alt6=162;
                }
                break;
            case 202:
                {
                alt6=163;
                }
                break;
            case 203:
                {
                alt6=164;
                }
                break;
            case 204:
                {
                alt6=165;
                }
                break;
            case 205:
                {
                alt6=166;
                }
                break;
            case 206:
                {
                alt6=167;
                }
                break;
            case 207:
                {
                alt6=168;
                }
                break;
            case 208:
                {
                alt6=169;
                }
                break;
            case 209:
                {
                alt6=170;
                }
                break;
            case 210:
                {
                alt6=171;
                }
                break;
            case 211:
                {
                alt6=172;
                }
                break;
            case 212:
                {
                alt6=173;
                }
                break;
            case 213:
                {
                alt6=174;
                }
                break;
            case 214:
                {
                alt6=175;
                }
                break;
            case 215:
                {
                alt6=176;
                }
                break;
            case 216:
                {
                alt6=177;
                }
                break;
            case 217:
                {
                alt6=178;
                }
                break;
            case 218:
                {
                alt6=179;
                }
                break;
            case 219:
                {
                alt6=180;
                }
                break;
            case 220:
                {
                alt6=181;
                }
                break;
            case 221:
                {
                alt6=182;
                }
                break;
            case 222:
                {
                alt6=183;
                }
                break;
            case 223:
                {
                alt6=184;
                }
                break;
            case 224:
                {
                alt6=185;
                }
                break;
            case 225:
                {
                alt6=186;
                }
                break;
            case 226:
                {
                alt6=187;
                }
                break;
            case 227:
                {
                alt6=188;
                }
                break;
            case 228:
                {
                alt6=189;
                }
                break;
            case 229:
                {
                alt6=190;
                }
                break;
            case 230:
                {
                alt6=191;
                }
                break;
            case 231:
                {
                alt6=192;
                }
                break;
            case 232:
                {
                alt6=193;
                }
                break;
            case 233:
                {
                alt6=194;
                }
                break;
            case 234:
                {
                alt6=195;
                }
                break;
            case 235:
                {
                alt6=196;
                }
                break;
            case 236:
                {
                alt6=197;
                }
                break;
            case 237:
                {
                alt6=198;
                }
                break;
            case 238:
                {
                alt6=199;
                }
                break;
            case 239:
                {
                alt6=200;
                }
                break;
            case 240:
                {
                alt6=201;
                }
                break;
            case 241:
                {
                alt6=202;
                }
                break;
            case 242:
                {
                alt6=203;
                }
                break;
            case 243:
                {
                alt6=204;
                }
                break;
            case 244:
                {
                alt6=205;
                }
                break;
            case 245:
                {
                alt6=206;
                }
                break;
            case 246:
                {
                alt6=207;
                }
                break;
            case 247:
                {
                alt6=208;
                }
                break;
            case 248:
                {
                alt6=209;
                }
                break;
            case 249:
                {
                alt6=210;
                }
                break;
            case 250:
                {
                alt6=211;
                }
                break;
            case 251:
                {
                alt6=212;
                }
                break;
            case 252:
                {
                alt6=213;
                }
                break;
            case 253:
                {
                alt6=214;
                }
                break;
            case 254:
                {
                alt6=215;
                }
                break;
            case 255:
                {
                alt6=216;
                }
                break;
            case 256:
                {
                alt6=217;
                }
                break;
            case 257:
                {
                alt6=218;
                }
                break;
            case 258:
                {
                alt6=219;
                }
                break;
            case 259:
                {
                alt6=220;
                }
                break;
            case 260:
                {
                alt6=221;
                }
                break;
            case 261:
                {
                alt6=222;
                }
                break;
            case 262:
                {
                alt6=223;
                }
                break;
            case 263:
                {
                alt6=224;
                }
                break;
            case 264:
                {
                alt6=225;
                }
                break;
            case 265:
                {
                alt6=226;
                }
                break;
            case 266:
                {
                alt6=227;
                }
                break;
            case 267:
                {
                alt6=228;
                }
                break;
            case 268:
                {
                alt6=229;
                }
                break;
            case 269:
                {
                alt6=230;
                }
                break;
            case 270:
                {
                alt6=231;
                }
                break;
            case 271:
                {
                alt6=232;
                }
                break;
            case 272:
                {
                alt6=233;
                }
                break;
            case 273:
                {
                alt6=234;
                }
                break;
            case 274:
                {
                alt6=235;
                }
                break;
            case 275:
                {
                alt6=236;
                }
                break;
            case 276:
                {
                alt6=237;
                }
                break;
            case 277:
                {
                alt6=238;
                }
                break;
            case 278:
                {
                alt6=239;
                }
                break;
            case 279:
                {
                alt6=240;
                }
                break;
            case 280:
                {
                alt6=241;
                }
                break;
            case 281:
                {
                alt6=242;
                }
                break;
            case 282:
                {
                alt6=243;
                }
                break;
            case 283:
                {
                alt6=244;
                }
                break;
            case 284:
                {
                alt6=245;
                }
                break;
            case 285:
                {
                alt6=246;
                }
                break;
            case 286:
                {
                alt6=247;
                }
                break;
            case 287:
                {
                alt6=248;
                }
                break;
            case 288:
                {
                alt6=249;
                }
                break;
            case 289:
                {
                alt6=250;
                }
                break;
            case 290:
                {
                alt6=251;
                }
                break;
            case 291:
                {
                alt6=252;
                }
                break;
            case 292:
                {
                alt6=253;
                }
                break;
            case 293:
                {
                alt6=254;
                }
                break;
            case 294:
                {
                alt6=255;
                }
                break;
            case 295:
                {
                alt6=256;
                }
                break;
            case 296:
                {
                alt6=257;
                }
                break;
            case 297:
                {
                alt6=258;
                }
                break;
            case 298:
                {
                alt6=259;
                }
                break;
            case 299:
                {
                alt6=260;
                }
                break;
            case 300:
                {
                alt6=261;
                }
                break;
            case 301:
                {
                alt6=262;
                }
                break;
            case 302:
                {
                alt6=263;
                }
                break;
            case 303:
                {
                alt6=264;
                }
                break;
            case 304:
                {
                alt6=265;
                }
                break;
            case 305:
                {
                alt6=266;
                }
                break;
            case 306:
                {
                alt6=267;
                }
                break;
            case 307:
                {
                alt6=268;
                }
                break;
            case 308:
                {
                alt6=269;
                }
                break;
            case 309:
                {
                alt6=270;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:921:1: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusAusRechenschritt' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:921:1: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusAusRechenschritt' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:922:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusAusRechenschritt'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenAbstractAumPruefalgorithmusAusRechenschrittKeyword_0()); 
                    match(input,40,FOLLOW_40_in_rule__KLASSE__Alternatives1991); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenAbstractAumPruefalgorithmusAusRechenschrittKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:929:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusBagatellbetragTeilmassnahmen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:929:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusBagatellbetragTeilmassnahmen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:930:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.AbstractAumPruefalgorithmusBagatellbetragTeilmassnahmen'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenAbstractAumPruefalgorithmusBagatellbetragTeilmassnahmenKeyword_1()); 
                    match(input,41,FOLLOW_41_in_rule__KLASSE__Alternatives2011); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenAbstractAumPruefalgorithmusBagatellbetragTeilmassnahmenKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:937:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PA4AugenPrinzip' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:937:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PA4AugenPrinzip' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:938:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PA4AugenPrinzip'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPA4AugenPrinzipKeyword_2()); 
                    match(input,42,FOLLOW_42_in_rule__KLASSE__Alternatives2031); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPA4AugenPrinzipKeyword_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:945:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAblehnungsgruendeVorhanden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:945:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAblehnungsgruendeVorhanden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:946:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAblehnungsgruendeVorhanden'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAblehnungsgruendeVorhandenKeyword_3()); 
                    match(input,43,FOLLOW_43_in_rule__KLASSE__Alternatives2051); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAblehnungsgruendeVorhandenKeyword_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:953:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenArt17Abs1' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:953:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenArt17Abs1' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:954:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenArt17Abs1'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlicheFalschangabenArt17Abs1Keyword_4()); 
                    match(input,44,FOLLOW_44_in_rule__KLASSE__Alternatives2071); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlicheFalschangabenArt17Abs1Keyword_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:961:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenBezugVerstoesseVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:961:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenBezugVerstoesseVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:962:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlicheFalschangabenBezugVerstoesseVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlicheFalschangabenBezugVerstoesseVJKeyword_5()); 
                    match(input,45,FOLLOW_45_in_rule__KLASSE__Alternatives2091); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlicheFalschangabenBezugVerstoesseVJKeyword_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:969:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngaben' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:969:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngaben' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:970:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngaben'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlichFalscheAngabenKeyword_6()); 
                    match(input,46,FOLLOW_46_in_rule__KLASSE__Alternatives2111); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlichFalscheAngabenKeyword_6()); 

                    }


                    }
                    break;
                case 8 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:977:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngabenStichtag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:977:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngabenStichtag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:978:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalscheAngabenStichtag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlichFalscheAngabenStichtagKeyword_7()); 
                    match(input,47,FOLLOW_47_in_rule__KLASSE__Alternatives2131); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlichFalscheAngabenStichtagKeyword_7()); 

                    }


                    }
                    break;
                case 9 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:985:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalschGemachteUnregelmaessigkeit' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:985:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalschGemachteUnregelmaessigkeit' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:986:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbsichtlichFalschGemachteUnregelmaessigkeit'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlichFalschGemachteUnregelmaessigkeitKeyword_8()); 
                    match(input,48,FOLLOW_48_in_rule__KLASSE__Alternatives2151); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbsichtlichFalschGemachteUnregelmaessigkeitKeyword_8()); 

                    }


                    }
                    break;
                case 10 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:993:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbtretungenInZahlungVorhanden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:993:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbtretungenInZahlungVorhanden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:994:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbtretungenInZahlungVorhanden'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbtretungenInZahlungVorhandenKeyword_9()); 
                    match(input,49,FOLLOW_49_in_rule__KLASSE__Alternatives2171); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbtretungenInZahlungVorhandenKeyword_9()); 

                    }


                    }
                    break;
                case 11 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1001:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzuegeErhoehung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1001:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzuegeErhoehung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1002:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzuegeErhoehung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbzuegeErhoehungKeyword_10()); 
                    match(input,50,FOLLOW_50_in_rule__KLASSE__Alternatives2191); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbzuegeErhoehungKeyword_10()); 

                    }


                    }
                    break;
                case 12 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1009:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaft' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1009:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaft' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1010:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaft'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaftKeyword_11()); 
                    match(input,51,FOLLOW_51_in_rule__KLASSE__Alternatives2211); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAbzugNichtfoerderfAnteileGefuelltBeiUnternehmensformWaldgemeinschaftKeyword_11()); 

                    }


                    }
                    break;
                case 13 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1017:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngaben' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1017:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngaben' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1018:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngaben'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAllgemeineAngabenKeyword_12()); 
                    match(input,52,FOLLOW_52_in_rule__KLASSE__Alternatives2231); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAllgemeineAngabenKeyword_12()); 

                    }


                    }
                    break;
                case 14 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1025:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngabenBeendetOhneAenderungsblatt' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1025:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngabenBeendetOhneAenderungsblatt' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1026:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAllgemeineAngabenBeendetOhneAenderungsblatt'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAllgemeineAngabenBeendetOhneAenderungsblattKeyword_13()); 
                    match(input,53,FOLLOW_53_in_rule__KLASSE__Alternatives2251); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAllgemeineAngabenBeendetOhneAenderungsblattKeyword_13()); 

                    }


                    }
                    break;
                case 15 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1033:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAltverpflichtungUeberschritten' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1033:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAltverpflichtungUeberschritten' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1034:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAltverpflichtungUeberschritten'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAltverpflichtungUeberschrittenKeyword_14()); 
                    match(input,54,FOLLOW_54_in_rule__KLASSE__Alternatives2271); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAltverpflichtungUeberschrittenKeyword_14()); 

                    }


                    }
                    break;
                case 16 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1041:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenAUM' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1041:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenAUM' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1042:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenAUM'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenAUMKeyword_15()); 
                    match(input,55,FOLLOW_55_in_rule__KLASSE__Alternatives2291); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenAUMKeyword_15()); 

                    }


                    }
                    break;
                case 17 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1049:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendet' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1049:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendet' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1050:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendet'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenBeendetKeyword_16()); 
                    match(input,56,FOLLOW_56_in_rule__KLASSE__Alternatives2311); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenBeendetKeyword_16()); 

                    }


                    }
                    break;
                case 18 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1057:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetStichtag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1057:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetStichtag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1058:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetStichtag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenBeendetStichtagKeyword_17()); 
                    match(input,57,FOLLOW_57_in_rule__KLASSE__Alternatives2331); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenBeendetStichtagKeyword_17()); 

                    }


                    }
                    break;
                case 19 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1065:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1065:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1066:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenBeendetVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenBeendetVJKeyword_18()); 
                    match(input,58,FOLLOW_58_in_rule__KLASSE__Alternatives2351); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenBeendetVJKeyword_18()); 

                    }


                    }
                    break;
                case 20 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1073:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenErsterfassungBeendetAJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1073:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenErsterfassungBeendetAJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1074:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenErsterfassungBeendetAJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenErsterfassungBeendetAJKeyword_19()); 
                    match(input,59,FOLLOW_59_in_rule__KLASSE__Alternatives2371); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenErsterfassungBeendetAJKeyword_19()); 

                    }


                    }
                    break;
                case 21 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1081:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1081:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1082:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenInBearbeitungKeyword_20()); 
                    match(input,60,FOLLOW_60_in_rule__KLASSE__Alternatives2391); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenInBearbeitungKeyword_20()); 

                    }


                    }
                    break;
                case 22 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1089:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitungStichtag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1089:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitungStichtag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1090:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageFlaechenInBearbeitungStichtag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenInBearbeitungStichtagKeyword_21()); 
                    match(input,61,FOLLOW_61_in_rule__KLASSE__Alternatives2411); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageFlaechenInBearbeitungStichtagKeyword_21()); 

                    }


                    }
                    break;
                case 23 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1097:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageTierhaltung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1097:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageTierhaltung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1098:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnlageTierhaltung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageTierhaltungKeyword_22()); 
                    match(input,62,FOLLOW_62_in_rule__KLASSE__Alternatives2431); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnlageTierhaltungKeyword_22()); 

                    }


                    }
                    break;
                case 24 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1105:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilBluehflaeche' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1105:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilBluehflaeche' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1106:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilBluehflaeche'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilBluehflaecheKeyword_23()); 
                    match(input,63,FOLLOW_63_in_rule__KLASSE__Alternatives2451); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilBluehflaecheKeyword_23()); 

                    }


                    }
                    break;
                case 25 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1113:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreide' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1113:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreide' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1114:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreide'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilGetreideKeyword_24()); 
                    match(input,64,FOLLOW_64_in_rule__KLASSE__Alternatives2471); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilGetreideKeyword_24()); 

                    }


                    }
                    break;
                case 26 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1121:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreideAusnahmeArt18' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1121:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreideAusnahmeArt18' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1122:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGetreideAusnahmeArt18'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilGetreideAusnahmeArt18Keyword_25()); 
                    match(input,65,FOLLOW_65_in_rule__KLASSE__Alternatives2491); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilGetreideAusnahmeArt18Keyword_25()); 

                    }


                    }
                    break;
                case 27 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1129:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGruenland' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1129:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGruenland' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1130:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilGruenland'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilGruenlandKeyword_26()); 
                    match(input,66,FOLLOW_66_in_rule__KLASSE__Alternatives2511); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilGruenlandKeyword_26()); 

                    }


                    }
                    break;
                case 28 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1137:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30Prozent' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1137:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30Prozent' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1138:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30Prozent'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilHauptfruchtartenMax30ProzentKeyword_27()); 
                    match(input,67,FOLLOW_67_in_rule__KLASSE__Alternatives2531); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilHauptfruchtartenMax30ProzentKeyword_27()); 

                    }


                    }
                    break;
                case 29 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1145:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1145:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1146:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18Keyword_28()); 
                    match(input,68,FOLLOW_68_in_rule__KLASSE__Alternatives2551); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilHauptfruchtartenMax30ProzentAusnahmeArt18Keyword_28()); 

                    }


                    }
                    break;
                case 30 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1153:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10Prozent' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1153:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10Prozent' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1154:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10Prozent'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilHauptfruchtartenMin10ProzentKeyword_29()); 
                    match(input,69,FOLLOW_69_in_rule__KLASSE__Alternatives2571); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilHauptfruchtartenMin10ProzentKeyword_29()); 

                    }


                    }
                    break;
                case 31 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1161:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1161:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1162:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18Keyword_30()); 
                    match(input,70,FOLLOW_70_in_rule__KLASSE__Alternatives2591); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilHauptfruchtartenMin10ProzentAusnahmeArt18Keyword_30()); 

                    }


                    }
                    break;
                case 32 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1169:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilKkLeguminosenAL5Prozent' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1169:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilKkLeguminosenAL5Prozent' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1170:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilKkLeguminosenAL5Prozent'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilKkLeguminosenAL5ProzentKeyword_31()); 
                    match(input,71,FOLLOW_71_in_rule__KLASSE__Alternatives2611); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilKkLeguminosenAL5ProzentKeyword_31()); 

                    }


                    }
                    break;
                case 33 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1177:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1177:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1178:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosen'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilLeguminosenKeyword_32()); 
                    match(input,72,FOLLOW_72_in_rule__KLASSE__Alternatives2631); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilLeguminosenKeyword_32()); 

                    }


                    }
                    break;
                case 34 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1185:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosenAusnahmeArt18' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1185:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosenAusnahmeArt18' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1186:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnteilLeguminosenAusnahmeArt18'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilLeguminosenAusnahmeArt18Keyword_33()); 
                    match(input,73,FOLLOW_73_in_rule__KLASSE__Alternatives2651); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnteilLeguminosenAusnahmeArt18Keyword_33()); 

                    }


                    }
                    break;
                case 35 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1193:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingang' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1193:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingang' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1194:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingang'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragseingangKeyword_34()); 
                    match(input,74,FOLLOW_74_in_rule__KLASSE__Alternatives2671); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragseingangKeyword_34()); 

                    }


                    }
                    break;
                case 36 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1201:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangNichtNachAusschlussTermin' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1201:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangNichtNachAusschlussTermin' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1202:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangNichtNachAusschlussTermin'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragseingangNichtNachAusschlussTerminKeyword_35()); 
                    match(input,75,FOLLOW_75_in_rule__KLASSE__Alternatives2691); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragseingangNichtNachAusschlussTerminKeyword_35()); 

                    }


                    }
                    break;
                case 37 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1209:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangStichtag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1209:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangStichtag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1210:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragseingangStichtag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragseingangStichtagKeyword_36()); 
                    match(input,76,FOLLOW_76_in_rule__KLASSE__Alternatives2711); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragseingangStichtagKeyword_36()); 

                    }


                    }
                    break;
                case 38 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1217:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsflaeche' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1217:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsflaeche' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1218:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsflaeche'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragsflaecheKeyword_37()); 
                    match(input,77,FOLLOW_77_in_rule__KLASSE__Alternatives2731); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragsflaecheKeyword_37()); 

                    }


                    }
                    break;
                case 39 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1225:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsteller' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1225:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsteller' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1226:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsteller'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragstellerKeyword_38()); 
                    match(input,78,FOLLOW_78_in_rule__KLASSE__Alternatives2751); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragstellerKeyword_38()); 

                    }


                    }
                    break;
                case 40 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1233:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstellerKeineJuristischePerson' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1233:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstellerKeineJuristischePerson' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1234:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstellerKeineJuristischePerson'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragstellerKeineJuristischePersonKeyword_39()); 
                    match(input,79,FOLLOW_79_in_rule__KLASSE__Alternatives2771); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragstellerKeineJuristischePersonKeyword_39()); 

                    }


                    }
                    break;
                case 41 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1241:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstermin' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1241:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstermin' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1242:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragstermin'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragsterminKeyword_40()); 
                    match(input,80,FOLLOW_80_in_rule__KLASSE__Alternatives2791); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragsterminKeyword_40()); 

                    }


                    }
                    break;
                case 42 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1249:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsterminNichtVerfristet' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1249:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsterminNichtVerfristet' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1250:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragsterminNichtVerfristet'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragsterminNichtVerfristetKeyword_41()); 
                    match(input,81,FOLLOW_81_in_rule__KLASSE__Alternatives2811); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragsterminNichtVerfristetKeyword_41()); 

                    }


                    }
                    break;
                case 43 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1257:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJBewilligt' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1257:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJBewilligt' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1258:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJBewilligt'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragVJBewilligtKeyword_42()); 
                    match(input,82,FOLLOW_82_in_rule__KLASSE__Alternatives2831); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragVJBewilligtKeyword_42()); 

                    }


                    }
                    break;
                case 44 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1265:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJNichtAbgelehnt' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1265:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJNichtAbgelehnt' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1266:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragVJNichtAbgelehnt'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragVJNichtAbgelehntKeyword_43()); 
                    match(input,83,FOLLOW_83_in_rule__KLASSE__Alternatives2851); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragVJNichtAbgelehntKeyword_43()); 

                    }


                    }
                    break;
                case 45 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1273:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragZurueckziehen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1273:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragZurueckziehen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1274:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAntragZurueckziehen'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragZurueckziehenKeyword_44()); 
                    match(input,84,FOLLOW_84_in_rule__KLASSE__Alternatives2871); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAntragZurueckziehenKeyword_44()); 

                    }


                    }
                    break;
                case 46 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1281:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenAusnahmeArt18' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1281:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenAusnahmeArt18' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1282:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenAusnahmeArt18'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnzahlHauptfruchtartenAusnahmeArt18Keyword_45()); 
                    match(input,85,FOLLOW_85_in_rule__KLASSE__Alternatives2891); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnzahlHauptfruchtartenAusnahmeArt18Keyword_45()); 

                    }


                    }
                    break;
                case 47 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1289:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenInklusiveLeguminosen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1289:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenInklusiveLeguminosen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1290:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlHauptfruchtartenInklusiveLeguminosen'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnzahlHauptfruchtartenInklusiveLeguminosenKeyword_46()); 
                    match(input,86,FOLLOW_86_in_rule__KLASSE__Alternatives2911); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnzahlHauptfruchtartenInklusiveLeguminosenKeyword_46()); 

                    }


                    }
                    break;
                case 48 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1297:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtGroesserAls100ProHa' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1297:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtGroesserAls100ProHa' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1298:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtGroesserAls100ProHa'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnzahlStreuobstBaeumeNichtGroesserAls100ProHaKeyword_47()); 
                    match(input,87,FOLLOW_87_in_rule__KLASSE__Alternatives2931); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnzahlStreuobstBaeumeNichtGroesserAls100ProHaKeyword_47()); 

                    }


                    }
                    break;
                case 49 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1305:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtKleinerAls30' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1305:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtKleinerAls30' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1306:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAnzahlStreuobstBaeumeNichtKleinerAls30'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnzahlStreuobstBaeumeNichtKleinerAls30Keyword_48()); 
                    match(input,88,FOLLOW_88_in_rule__KLASSE__Alternatives2951); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAnzahlStreuobstBaeumeNichtKleinerAls30Keyword_48()); 

                    }


                    }
                    break;
                case 50 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1313:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1313:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1314:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenAbgleichAJKeyword_49()); 
                    match(input,89,FOLLOW_89_in_rule__KLASSE__Alternatives2971); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenAbgleichAJKeyword_49()); 

                    }


                    }
                    break;
                case 51 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1321:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJStichtag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1321:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJStichtag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1322:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichAJStichtag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenAbgleichAJStichtagKeyword_50()); 
                    match(input,90,FOLLOW_90_in_rule__KLASSE__Alternatives2991); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenAbgleichAJStichtagKeyword_50()); 

                    }


                    }
                    break;
                case 52 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1329:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1329:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1330:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenAbgleichVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenAbgleichVJKeyword_51()); 
                    match(input,91,FOLLOW_91_in_rule__KLASSE__Alternatives3011); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenAbgleichVJKeyword_51()); 

                    }


                    }
                    break;
                case 53 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1337:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappe' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1337:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappe' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1338:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappe'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenmappeKeyword_52()); 
                    match(input,92,FOLLOW_92_in_rule__KLASSE__Alternatives3031); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenmappeKeyword_52()); 

                    }


                    }
                    break;
                case 54 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1345:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappeAktuellUndDurchgefuehrt' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1345:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappeAktuellUndDurchgefuehrt' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1346:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFlaechenmappeAktuellUndDurchgefuehrt'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenmappeAktuellUndDurchgefuehrtKeyword_53()); 
                    match(input,93,FOLLOW_93_in_rule__KLASSE__Alternatives3051); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFlaechenmappeAktuellUndDurchgefuehrtKeyword_53()); 

                    }


                    }
                    break;
                case 55 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1353:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlatt' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1353:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlatt' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1354:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlatt'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlattKeyword_54()); 
                    match(input,94,FOLLOW_94_in_rule__KLASSE__Alternatives3071); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumFNNichtUnvollstaendigUndHatKeinAnederungsBlattKeyword_54()); 

                    }


                    }
                    break;
                case 56 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1361:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1361:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1362:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumKuliZuMaAZLAktuellUndBeendetKeyword_55()); 
                    match(input,95,FOLLOW_95_in_rule__KLASSE__Alternatives3091); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumKuliZuMaAZLAktuellUndBeendetKeyword_55()); 

                    }


                    }
                    break;
                case 57 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1369:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaUZWAktuellUndBeendet' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1369:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaUZWAktuellUndBeendet' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1370:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumKuliZuMaUZWAktuellUndBeendet'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumKuliZuMaUZWAktuellUndBeendetKeyword_56()); 
                    match(input,96,FOLLOW_96_in_rule__KLASSE__Alternatives3111); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumKuliZuMaUZWAktuellUndBeendetKeyword_56()); 

                    }


                    }
                    break;
                case 58 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1377:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweis' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1377:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweis' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1378:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweis'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumTierbestandsnachweisKeyword_57()); 
                    match(input,97,FOLLOW_97_in_rule__KLASSE__Alternatives3131); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumTierbestandsnachweisKeyword_57()); 

                    }


                    }
                    break;
                case 59 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1385:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweisBeendet' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1385:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweisBeendet' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1386:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAumTierbestandsnachweisBeendet'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumTierbestandsnachweisBeendetKeyword_58()); 
                    match(input,98,FOLLOW_98_in_rule__KLASSE__Alternatives3151); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAumTierbestandsnachweisBeendetKeyword_58()); 

                    }


                    }
                    break;
                case 60 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1393:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusbringungstechnik' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1393:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusbringungstechnik' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1394:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusbringungstechnik'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAusbringungstechnikKeyword_59()); 
                    match(input,99,FOLLOW_99_in_rule__KLASSE__Alternatives3171); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAusbringungstechnikKeyword_59()); 

                    }


                    }
                    break;
                case 61 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1401:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuellt' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1401:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuellt' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1402:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuellt'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuelltKeyword_60()); 
                    match(input,100,FOLLOW_100_in_rule__KLASSE__Alternatives3191); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAusgleichsleistungenInAnderenBLUndFlaecheNichtGefuelltKeyword_60()); 

                    }


                    }
                    break;
                case 62 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1409:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusschluss' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1409:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusschluss' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1410:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAAusschluss'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAusschlussKeyword_61()); 
                    match(input,101,FOLLOW_101_in_rule__KLASSE__Alternatives3211); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAAusschlussKeyword_61()); 

                    }


                    }
                    break;
                case 63 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1417:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragAuszahlung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1417:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragAuszahlung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1418:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragAuszahlung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragAuszahlungKeyword_62()); 
                    match(input,102,FOLLOW_102_in_rule__KLASSE__Alternatives3231); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragAuszahlungKeyword_62()); 

                    }


                    }
                    break;
                case 64 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1425:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1425:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1426:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungKeyword_63()); 
                    match(input,103,FOLLOW_103_in_rule__KLASSE__Alternatives3251); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungKeyword_63()); 

                    }


                    }
                    break;
                case 65 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1433:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungBeruecksichtigungHalbeZahlung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1433:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungBeruecksichtigungHalbeZahlung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1434:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungBeruecksichtigungHalbeZahlung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungBeruecksichtigungHalbeZahlungKeyword_64()); 
                    match(input,104,FOLLOW_104_in_rule__KLASSE__Alternatives3271); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungBeruecksichtigungHalbeZahlungKeyword_64()); 

                    }


                    }
                    break;
                case 66 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1441:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2002' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1441:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2002' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1442:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2002'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungEVP_RL2002Keyword_65()); 
                    match(input,105,FOLLOW_105_in_rule__KLASSE__Alternatives3291); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungEVP_RL2002Keyword_65()); 

                    }


                    }
                    break;
                case 67 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1449:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2007' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1449:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2007' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1450:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungEVP_RL2007'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungEVP_RL2007Keyword_66()); 
                    match(input,106,FOLLOW_106_in_rule__KLASSE__Alternatives3311); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungEVP_RL2007Keyword_66()); 

                    }


                    }
                    break;
                case 68 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1457:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungUeberGesamteLaufzeit' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1457:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungUeberGesamteLaufzeit' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1458:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragBerechnungUeberGesamteLaufzeit'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungUeberGesamteLaufzeitKeyword_67()); 
                    match(input,107,FOLLOW_107_in_rule__KLASSE__Alternatives3331); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragBerechnungUeberGesamteLaufzeitKeyword_67()); 

                    }


                    }
                    break;
                case 69 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1465:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragNeuAntrag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1465:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragNeuAntrag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1466:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragNeuAntrag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragNeuAntragKeyword_68()); 
                    match(input,108,FOLLOW_108_in_rule__KLASSE__Alternatives3351); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragNeuAntragKeyword_68()); 

                    }


                    }
                    break;
                case 70 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1473:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragRueckforderung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1473:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragRueckforderung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1474:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABagatellbetragRueckforderung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragRueckforderungKeyword_69()); 
                    match(input,109,FOLLOW_109_in_rule__KLASSE__Alternatives3371); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABagatellbetragRueckforderungKeyword_69()); 

                    }


                    }
                    break;
                case 71 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1481:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1481:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1482:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABankverbindungKeyword_70()); 
                    match(input,110,FOLLOW_110_in_rule__KLASSE__Alternatives3391); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABankverbindungKeyword_70()); 

                    }


                    }
                    break;
                case 72 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1489:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindungStichtag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1489:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindungStichtag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1490:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABankverbindungStichtag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABankverbindungStichtagKeyword_71()); 
                    match(input,111,FOLLOW_111_in_rule__KLASSE__Alternatives3411); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABankverbindungStichtagKeyword_71()); 

                    }


                    }
                    break;
                case 73 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1497:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragteFlaecheKleiner80Prozent' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1497:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragteFlaecheKleiner80Prozent' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1498:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragteFlaecheKleiner80Prozent'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragteFlaecheKleiner80ProzentKeyword_72()); 
                    match(input,112,FOLLOW_112_in_rule__KLASSE__Alternatives3431); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragteFlaecheKleiner80ProzentKeyword_72()); 

                    }


                    }
                    break;
                case 74 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1505:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtGLGleichGesamtGL' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1505:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtGLGleichGesamtGL' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1506:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtGLGleichGesamtGL'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtGLGleichGesamtGLKeyword_73()); 
                    match(input,113,FOLLOW_113_in_rule__KLASSE__Alternatives3451); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtGLGleichGesamtGLKeyword_73()); 

                    }


                    }
                    break;
                case 75 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1513:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtMindestens5ProzAckerflaeche' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1513:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtMindestens5ProzAckerflaeche' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1514:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtMindestens5ProzAckerflaeche'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtMindestens5ProzAckerflaecheKeyword_74()); 
                    match(input,114,FOLLOW_114_in_rule__KLASSE__Alternatives3471); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtMindestens5ProzAckerflaecheKeyword_74()); 

                    }


                    }
                    break;
                case 76 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1521:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner10ProzAckerflaeche' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1521:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner10ProzAckerflaeche' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1522:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner10ProzAckerflaeche'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtNichtKleiner10ProzAckerflaecheKeyword_75()); 
                    match(input,115,FOLLOW_115_in_rule__KLASSE__Alternatives3491); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtNichtKleiner10ProzAckerflaecheKeyword_75()); 

                    }


                    }
                    break;
                case 77 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1529:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner2ha' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1529:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner2ha' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1530:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner2ha'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtNichtKleiner2haKeyword_76()); 
                    match(input,116,FOLLOW_116_in_rule__KLASSE__Alternatives3511); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtNichtKleiner2haKeyword_76()); 

                    }


                    }
                    break;
                case 78 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1537:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner5ProzAckerflaeche' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1537:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner5ProzAckerflaeche' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1538:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABeantragtNichtKleiner5ProzAckerflaeche'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtNichtKleiner5ProzAckerflaecheKeyword_77()); 
                    match(input,117,FOLLOW_117_in_rule__KLASSE__Alternatives3531); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABeantragtNichtKleiner5ProzAckerflaecheKeyword_77()); 

                    }


                    }
                    break;
                case 79 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1545:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzAusHITUnterBeruecksichtigungGruenland' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1545:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzAusHITUnterBeruecksichtigungGruenland' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1546:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzAusHITUnterBeruecksichtigungGruenland'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABesatzAusHITUnterBeruecksichtigungGruenlandKeyword_78()); 
                    match(input,118,FOLLOW_118_in_rule__KLASSE__Alternatives3551); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABesatzAusHITUnterBeruecksichtigungGruenlandKeyword_78()); 

                    }


                    }
                    break;
                case 80 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1553:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_2' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1553:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_2' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1554:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_2'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABesatzRgvDglMinBeantragtFestgestelltGrEq0_2Keyword_79()); 
                    match(input,119,FOLLOW_119_in_rule__KLASSE__Alternatives3571); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABesatzRgvDglMinBeantragtFestgestelltGrEq0_2Keyword_79()); 

                    }


                    }
                    break;
                case 81 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1561:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_3' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1561:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_3' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1562:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzRgvDglMinBeantragtFestgestelltGrEq0_3'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABesatzRgvDglMinBeantragtFestgestelltGrEq0_3Keyword_80()); 
                    match(input,120,FOLLOW_120_in_rule__KLASSE__Alternatives3591); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABesatzRgvDglMinBeantragtFestgestelltGrEq0_3Keyword_80()); 

                    }


                    }
                    break;
                case 82 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1569:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHIT' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1569:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHIT' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1570:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHIT'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHITKeyword_81()); 
                    match(input,121,FOLLOW_121_in_rule__KLASSE__Alternatives3611); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABesatzZwischen0_2Und1_0RgvProHaHFFInklBestNCAusHITKeyword_81()); 

                    }


                    }
                    break;
                case 83 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1577:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABescheidInAktuellerBerechnung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1577:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABescheidInAktuellerBerechnung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1578:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABescheidInAktuellerBerechnung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABescheidInAktuellerBerechnungKeyword_82()); 
                    match(input,122,FOLLOW_122_in_rule__KLASSE__Alternatives3631); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABescheidInAktuellerBerechnungKeyword_82()); 

                    }


                    }
                    break;
                case 84 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1585:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABestaetigungsVermerk' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1585:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABestaetigungsVermerk' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1586:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABestaetigungsVermerk'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABestaetigungsVermerkKeyword_83()); 
                    match(input,123,FOLLOW_123_in_rule__KLASSE__Alternatives3651); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABestaetigungsVermerkKeyword_83()); 

                    }


                    }
                    break;
                case 85 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1593:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABewilligterNAImEAJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1593:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABewilligterNAImEAJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1594:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PABewilligterNAImEAJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABewilligterNAImEAJKeyword_84()); 
                    match(input,124,FOLLOW_124_in_rule__KLASSE__Alternatives3671); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPABewilligterNAImEAJKeyword_84()); 

                    }


                    }
                    break;
                case 86 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1601:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuell' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1601:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuell' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1602:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuell'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPACCGesamtbewertungsmappeAktuellKeyword_85()); 
                    match(input,125,FOLLOW_125_in_rule__KLASSE__Alternatives3691); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPACCGesamtbewertungsmappeAktuellKeyword_85()); 

                    }


                    }
                    break;
                case 87 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1609:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuellUndBeendet' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1609:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuellUndBeendet' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1610:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PACCGesamtbewertungsmappeAktuellUndBeendet'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPACCGesamtbewertungsmappeAktuellUndBeendetKeyword_86()); 
                    match(input,126,FOLLOW_126_in_rule__KLASSE__Alternatives3711); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPACCGesamtbewertungsmappeAktuellUndBeendetKeyword_86()); 

                    }


                    }
                    break;
                case 88 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1617:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADatumEingangGroesserAntrag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1617:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADatumEingangGroesserAntrag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1618:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADatumEingangGroesserAntrag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPADatumEingangGroesserAntragKeyword_87()); 
                    match(input,127,FOLLOW_127_in_rule__KLASSE__Alternatives3731); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPADatumEingangGroesserAntragKeyword_87()); 

                    }


                    }
                    break;
                case 89 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1625:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzBewilligtVJUndBeantragtGroesserBagatellbetrag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1625:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzBewilligtVJUndBeantragtGroesserBagatellbetrag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1626:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzBewilligtVJUndBeantragtGroesserBagatellbetrag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPADifferenzBewilligtVJUndBeantragtGroesserBagatellbetragKeyword_88()); 
                    match(input,128,FOLLOW_128_in_rule__KLASSE__Alternatives3751); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPADifferenzBewilligtVJUndBeantragtGroesserBagatellbetragKeyword_88()); 

                    }


                    }
                    break;
                case 90 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1633:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaeche' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1633:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaeche' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1634:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaeche'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPADifferenzVerpflichtungsflaecheKeyword_89()); 
                    match(input,129,FOLLOW_129_in_rule__KLASSE__Alternatives3771); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPADifferenzVerpflichtungsflaecheKeyword_89()); 

                    }


                    }
                    break;
                case 91 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1641:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaecheWiederholt' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1641:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaecheWiederholt' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1642:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PADifferenzVerpflichtungsflaecheWiederholt'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPADifferenzVerpflichtungsflaecheWiederholtKeyword_90()); 
                    match(input,130,FOLLOW_130_in_rule__KLASSE__Alternatives3791); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPADifferenzVerpflichtungsflaecheWiederholtKeyword_90()); 

                    }


                    }
                    break;
                case 92 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1649:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCC' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1649:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCC' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1650:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCC'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungCCKeyword_91()); 
                    match(input,131,FOLLOW_131_in_rule__KLASSE__Alternatives3811); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungCCKeyword_91()); 

                    }


                    }
                    break;
                case 93 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1657:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCV' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1657:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCV' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1658:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCV'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungCCVKeyword_92()); 
                    match(input,132,FOLLOW_132_in_rule__KLASSE__Alternatives3831); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungCCVKeyword_92()); 

                    }


                    }
                    break;
                case 94 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1665:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCVImVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1665:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCVImVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1666:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungCCVImVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungCCVImVJKeyword_93()); 
                    match(input,133,FOLLOW_133_in_rule__KLASSE__Alternatives3851); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungCCVImVJKeyword_93()); 

                    }


                    }
                    break;
                case 95 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1673:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGL' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1673:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGL' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1674:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGL'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungUmfangDGLKeyword_94()); 
                    match(input,134,FOLLOW_134_in_rule__KLASSE__Alternatives3871); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungUmfangDGLKeyword_94()); 

                    }


                    }
                    break;
                case 96 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1681:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGLSchwellwert' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1681:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGLSchwellwert' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1682:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangDGLSchwellwert'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungUmfangDGLSchwellwertKeyword_95()); 
                    match(input,135,FOLLOW_135_in_rule__KLASSE__Alternatives3891); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungUmfangDGLSchwellwertKeyword_95()); 

                    }


                    }
                    break;
                case 97 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1689:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangVerpflichtungsflaeche' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1689:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangVerpflichtungsflaeche' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1690:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEinhaltungUmfangVerpflichtungsflaeche'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungUmfangVerpflichtungsflaecheKeyword_96()); 
                    match(input,136,FOLLOW_136_in_rule__KLASSE__Alternatives3911); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEinhaltungUmfangVerpflichtungsflaecheKeyword_96()); 

                    }


                    }
                    break;
                case 98 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1697:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEntscheidungenBescheidart' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1697:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEntscheidungenBescheidart' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1698:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAEntscheidungenBescheidart'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEntscheidungenBescheidartKeyword_97()); 
                    match(input,137,FOLLOW_137_in_rule__KLASSE__Alternatives3931); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAEntscheidungenBescheidartKeyword_97()); 

                    }


                    }
                    break;
                case 99 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1705:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErstantragsjahr' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1705:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErstantragsjahr' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1706:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErstantragsjahr'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErstantragsjahrKeyword_98()); 
                    match(input,138,FOLLOW_138_in_rule__KLASSE__Alternatives3951); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErstantragsjahrKeyword_98()); 

                    }


                    }
                    break;
                case 100 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1713:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10bzw2haOdGroesser50ProzentVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1713:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10bzw2haOdGroesser50ProzentVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1714:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10bzw2haOdGroesser50ProzentVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungKleiner10bzw2haOdGroesser50ProzentVJKeyword_99()); 
                    match(input,139,FOLLOW_139_in_rule__KLASSE__Alternatives3971); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungKleiner10bzw2haOdGroesser50ProzentVJKeyword_99()); 

                    }


                    }
                    break;
                case 101 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1721:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10odGroesser50ProzentVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1721:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10odGroesser50ProzentVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1722:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleiner10odGroesser50ProzentVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungKleiner10odGroesser50ProzentVJKeyword_100()); 
                    match(input,140,FOLLOW_140_in_rule__KLASSE__Alternatives3991); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungKleiner10odGroesser50ProzentVJKeyword_100()); 

                    }


                    }
                    break;
                case 102 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1729:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50Proz' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1729:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50Proz' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1730:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50Proz'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungKleinerGleich50ProzKeyword_101()); 
                    match(input,141,FOLLOW_141_in_rule__KLASSE__Alternatives4011); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungKleinerGleich50ProzKeyword_101()); 

                    }


                    }
                    break;
                case 103 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1737:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50ProzOder2Ha' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1737:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50ProzOder2Ha' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1738:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungKleinerGleich50ProzOder2Ha'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungKleinerGleich50ProzOder2HaKeyword_102()); 
                    match(input,142,FOLLOW_142_in_rule__KLASSE__Alternatives4031); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungKleinerGleich50ProzOder2HaKeyword_102()); 

                    }


                    }
                    break;
                case 104 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1745:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungLE50ProzentVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1745:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungLE50ProzentVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1746:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungLE50ProzentVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungLE50ProzentVJKeyword_103()); 
                    match(input,143,FOLLOW_143_in_rule__KLASSE__Alternatives4051); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungLE50ProzentVJKeyword_103()); 

                    }


                    }
                    break;
                case 105 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1753:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungsflaecheVorhanden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1753:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungsflaecheVorhanden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1754:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAErweiterungsflaecheVorhanden'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungsflaecheVorhandenKeyword_104()); 
                    match(input,144,FOLLOW_144_in_rule__KLASSE__Alternatives4071); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAErweiterungsflaecheVorhandenKeyword_104()); 

                    }


                    }
                    break;
                case 106 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1761:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAExtensiveBewirtschaftungGLGleichGesamtGL' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1761:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAExtensiveBewirtschaftungGLGleichGesamtGL' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1762:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAExtensiveBewirtschaftungGLGleichGesamtGL'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAExtensiveBewirtschaftungGLGleichGesamtGLKeyword_105()); 
                    match(input,145,FOLLOW_145_in_rule__KLASSE__Alternatives4091); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAExtensiveBewirtschaftungGLGleichGesamtGLKeyword_105()); 

                    }


                    }
                    break;
                case 107 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1769:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngaben' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1769:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngaben' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1770:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngaben'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFahrlaessigFalscheAngabenKeyword_106()); 
                    match(input,146,FOLLOW_146_in_rule__KLASSE__Alternatives4111); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFahrlaessigFalscheAngabenKeyword_106()); 

                    }


                    }
                    break;
                case 108 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1777:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngabenStichtag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1777:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngabenStichtag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1778:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFahrlaessigFalscheAngabenStichtag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFahrlaessigFalscheAngabenStichtagKeyword_107()); 
                    match(input,147,FOLLOW_147_in_rule__KLASSE__Alternatives4131); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFahrlaessigFalscheAngabenStichtagKeyword_107()); 

                    }


                    }
                    break;
                case 109 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1785:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs5' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1785:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs5' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1786:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs5'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschangabenArt16Abs5Keyword_108()); 
                    match(input,148,FOLLOW_148_in_rule__KLASSE__Alternatives4151); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschangabenArt16Abs5Keyword_108()); 

                    }


                    }
                    break;
                case 110 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1793:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1793:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1794:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschangabenArt16Abs6Keyword_109()); 
                    match(input,149,FOLLOW_149_in_rule__KLASSE__Alternatives4171); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschangabenArt16Abs6Keyword_109()); 

                    }


                    }
                    break;
                case 111 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1801:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6inAnderemFP' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1801:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6inAnderemFP' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1802:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt16Abs6inAnderemFP'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschangabenArt16Abs6inAnderemFPKeyword_110()); 
                    match(input,150,FOLLOW_150_in_rule__KLASSE__Alternatives4191); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschangabenArt16Abs6inAnderemFPKeyword_110()); 

                    }


                    }
                    break;
                case 112 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1809:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt18Abs3' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1809:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt18Abs3' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1810:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschangabenArt18Abs3'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschangabenArt18Abs3Keyword_111()); 
                    match(input,151,FOLLOW_151_in_rule__KLASSE__Alternatives4211); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschangabenArt18Abs3Keyword_111()); 

                    }


                    }
                    break;
                case 113 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1817:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschGemachteAngaben' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1817:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschGemachteAngaben' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1818:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFalschGemachteAngaben'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschGemachteAngabenKeyword_112()); 
                    match(input,152,FOLLOW_152_in_rule__KLASSE__Alternatives4231); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFalschGemachteAngabenKeyword_112()); 

                    }


                    }
                    break;
                case 114 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1825:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerderfaehigGroesserNull' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1825:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerderfaehigGroesserNull' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1826:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerderfaehigGroesserNull'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheFoerderfaehigGroesserNullKeyword_113()); 
                    match(input,153,FOLLOW_153_in_rule__KLASSE__Alternatives4251); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheFoerderfaehigGroesserNullKeyword_113()); 

                    }


                    }
                    break;
                case 115 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1833:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerdergebietGroesserGleich3Hektar' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1833:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerdergebietGroesserGleich3Hektar' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1834:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheFoerdergebietGroesserGleich3Hektar'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheFoerdergebietGroesserGleich3HektarKeyword_114()); 
                    match(input,154,FOLLOW_154_in_rule__KLASSE__Alternatives4271); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheFoerdergebietGroesserGleich3HektarKeyword_114()); 

                    }


                    }
                    break;
                case 116 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1841:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM141Mindestens5ProzentAFAusEaj' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1841:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM141Mindestens5ProzentAFAusEaj' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1842:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM141Mindestens5ProzentAFAusEaj'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheM141Mindestens5ProzentAFAusEajKeyword_115()); 
                    match(input,155,FOLLOW_155_in_rule__KLASSE__Alternatives4291); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheM141Mindestens5ProzentAFAusEajKeyword_115()); 

                    }


                    }
                    break;
                case 117 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1849:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM14Mindestens5ProzentAFAusEaj' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1849:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM14Mindestens5ProzentAFAusEaj' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1850:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheM14Mindestens5ProzentAFAusEaj'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheM14Mindestens5ProzentAFAusEajKeyword_116()); 
                    match(input,156,FOLLOW_156_in_rule__KLASSE__Alternatives4311); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheM14Mindestens5ProzentAFAusEajKeyword_116()); 

                    }


                    }
                    break;
                case 118 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1857:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheStreuobstwiesen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1857:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheStreuobstwiesen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1858:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAFlaecheStreuobstwiesen'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheStreuobstwiesenKeyword_117()); 
                    match(input,157,FOLLOW_157_in_rule__KLASSE__Alternatives4331); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAFlaecheStreuobstwiesenKeyword_117()); 

                    }


                    }
                    break;
                case 119 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1865:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGebuehrenrechnung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1865:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGebuehrenrechnung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1866:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGebuehrenrechnung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGebuehrenrechnungKeyword_118()); 
                    match(input,158,FOLLOW_158_in_rule__KLASSE__Alternatives4351); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGebuehrenrechnungKeyword_118()); 

                    }


                    }
                    break;
                case 120 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1873:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung30Prozent' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1873:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung30Prozent' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1874:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung30Prozent'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGesamtabweichung30ProzentKeyword_119()); 
                    match(input,159,FOLLOW_159_in_rule__KLASSE__Alternatives4371); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGesamtabweichung30ProzentKeyword_119()); 

                    }


                    }
                    break;
                case 121 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1881:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung50Prozent' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1881:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung50Prozent' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1882:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtabweichung50Prozent'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGesamtabweichung50ProzentKeyword_120()); 
                    match(input,160,FOLLOW_160_in_rule__KLASSE__Alternatives4391); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGesamtabweichung50ProzentKeyword_120()); 

                    }


                    }
                    break;
                case 122 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1889:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtrueckforderung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1889:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtrueckforderung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1890:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtrueckforderung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGesamtrueckforderungKeyword_121()); 
                    match(input,161,FOLLOW_161_in_rule__KLASSE__Alternatives4411); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGesamtrueckforderungKeyword_121()); 

                    }


                    }
                    break;
                case 123 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1897:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtsanktionierung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1897:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtsanktionierung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1898:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGesamtsanktionierung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGesamtsanktionierungKeyword_122()); 
                    match(input,162,FOLLOW_162_in_rule__KLASSE__Alternatives4431); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGesamtsanktionierungKeyword_122()); 

                    }


                    }
                    break;
                case 124 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1905:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuGross' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1905:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuGross' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1906:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuGross'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGLAnteilNichtZuGrossKeyword_123()); 
                    match(input,163,FOLLOW_163_in_rule__KLASSE__Alternatives4451); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGLAnteilNichtZuGrossKeyword_123()); 

                    }


                    }
                    break;
                case 125 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1913:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuKlein' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1913:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuKlein' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1914:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGLAnteilNichtZuKlein'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGLAnteilNichtZuKleinKeyword_124()); 
                    match(input,164,FOLLOW_164_in_rule__KLASSE__Alternatives4471); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGLAnteilNichtZuKleinKeyword_124()); 

                    }


                    }
                    break;
                case 126 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1921:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrfImAA' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1921:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrfImAA' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1922:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrfImAA'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGrfImAAKeyword_125()); 
                    match(input,165,FOLLOW_165_in_rule__KLASSE__Alternatives4491); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGrfImAAKeyword_125()); 

                    }


                    }
                    break;
                case 127 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1929:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrobFahrlaessigeGemachteAngaben' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1929:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrobFahrlaessigeGemachteAngaben' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1930:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGrobFahrlaessigeGemachteAngaben'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGrobFahrlaessigeGemachteAngabenKeyword_126()); 
                    match(input,166,FOLLOW_166_in_rule__KLASSE__Alternatives4511); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGrobFahrlaessigeGemachteAngabenKeyword_126()); 

                    }


                    }
                    break;
                case 128 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1937:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGuellemenge' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1937:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGuellemenge' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1938:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAGuellemenge'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGuellemengeKeyword_127()); 
                    match(input,167,FOLLOW_167_in_rule__KLASSE__Alternatives4531); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAGuellemengeKeyword_127()); 

                    }


                    }
                    break;
                case 129 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1945:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAHofuebergabe' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1945:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAHofuebergabe' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1946:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAHofuebergabe'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAHofuebergabeKeyword_128()); 
                    match(input,168,FOLLOW_168_in_rule__KLASSE__Alternatives4551); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAHofuebergabeKeyword_128()); 

                    }


                    }
                    break;
                case 130 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1953:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerBestaetigungVorhanden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1953:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerBestaetigungVorhanden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1954:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerBestaetigungVorhanden'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAImkerBestaetigungVorhandenKeyword_129()); 
                    match(input,169,FOLLOW_169_in_rule__KLASSE__Alternatives4571); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAImkerBestaetigungVorhandenKeyword_129()); 

                    }


                    }
                    break;
                case 131 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1961:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerVereinbarungVorhanden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1961:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerVereinbarungVorhanden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1962:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAImkerVereinbarungVorhanden'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAImkerVereinbarungVorhandenKeyword_130()); 
                    match(input,170,FOLLOW_170_in_rule__KLASSE__Alternatives4591); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAImkerVereinbarungVorhandenKeyword_130()); 

                    }


                    }
                    break;
                case 132 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1969:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKapitalbeteiligungOeffentlHandGroesser25Proz' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1969:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKapitalbeteiligungOeffentlHandGroesser25Proz' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1970:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKapitalbeteiligungOeffentlHandGroesser25Proz'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKapitalbeteiligungOeffentlHandGroesser25ProzKeyword_131()); 
                    match(input,171,FOLLOW_171_in_rule__KLASSE__Alternatives4611); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKapitalbeteiligungOeffentlHandGroesser25ProzKeyword_131()); 

                    }


                    }
                    break;
                case 133 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1977:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinAntragVorhanden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1977:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinAntragVorhanden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1978:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinAntragVorhanden'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinAntragVorhandenKeyword_132()); 
                    match(input,172,FOLLOW_172_in_rule__KLASSE__Alternatives4631); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinAntragVorhandenKeyword_132()); 

                    }


                    }
                    break;
                case 134 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1985:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinBescheidInAktuellerBerechnung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1985:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinBescheidInAktuellerBerechnung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1986:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinBescheidInAktuellerBerechnung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinBescheidInAktuellerBerechnungKeyword_133()); 
                    match(input,173,FOLLOW_173_in_rule__KLASSE__Alternatives4651); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinBescheidInAktuellerBerechnungKeyword_133()); 

                    }


                    }
                    break;
                case 135 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1993:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinDungAufnahmeOderAbgabe' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1993:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinDungAufnahmeOderAbgabe' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:1994:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinDungAufnahmeOderAbgabe'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinDungAufnahmeOderAbgabeKeyword_134()); 
                    match(input,174,FOLLOW_174_in_rule__KLASSE__Alternatives4671); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinDungAufnahmeOderAbgabeKeyword_134()); 

                    }


                    }
                    break;
                case 136 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2001:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhanden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2001:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhanden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2002:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhanden'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineAblehnungsgruendeVorhandenKeyword_135()); 
                    match(input,175,FOLLOW_175_in_rule__KLASSE__Alternatives4691); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineAblehnungsgruendeVorhandenKeyword_135()); 

                    }


                    }
                    break;
                case 137 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2009:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhandenTm' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2009:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhandenTm' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2010:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineAblehnungsgruendeVorhandenTm'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineAblehnungsgruendeVorhandenTmKeyword_136()); 
                    match(input,176,FOLLOW_176_in_rule__KLASSE__Alternatives4711); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineAblehnungsgruendeVorhandenTmKeyword_136()); 

                    }


                    }
                    break;
                case 138 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2017:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineParalleBeantragungM5UndM6' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2017:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineParalleBeantragungM5UndM6' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2018:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineParalleBeantragungM5UndM6'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineParalleBeantragungM5UndM6Keyword_137()); 
                    match(input,177,FOLLOW_177_in_rule__KLASSE__Alternatives4731); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineParalleBeantragungM5UndM6Keyword_137()); 

                    }


                    }
                    break;
                case 139 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2025:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinePheromonGemeinschaft' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2025:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinePheromonGemeinschaft' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2026:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinePheromonGemeinschaft'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinePheromonGemeinschaftKeyword_138()); 
                    match(input,178,FOLLOW_178_in_rule__KLASSE__Alternatives4751); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinePheromonGemeinschaftKeyword_138()); 

                    }


                    }
                    break;
                case 140 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2033:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAktiengesellschaft' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2033:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAktiengesellschaft' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2034:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAktiengesellschaft'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformAktiengesellschaftKeyword_139()); 
                    match(input,179,FOLLOW_179_in_rule__KLASSE__Alternatives4771); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformAktiengesellschaftKeyword_139()); 

                    }


                    }
                    break;
                case 141 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2041:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAnstaltDesOeffentlRechts' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2041:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAnstaltDesOeffentlRechts' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2042:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformAnstaltDesOeffentlRechts'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformAnstaltDesOeffentlRechtsKeyword_140()); 
                    match(input,180,FOLLOW_180_in_rule__KLASSE__Alternatives4791); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformAnstaltDesOeffentlRechtsKeyword_140()); 

                    }


                    }
                    break;
                case 142 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2049:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformGmbH' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2049:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformGmbH' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2050:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformGmbH'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformGmbHKeyword_141()); 
                    match(input,181,FOLLOW_181_in_rule__KLASSE__Alternatives4811); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformGmbHKeyword_141()); 

                    }


                    }
                    break;
                case 143 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2057:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformJuristischePersonOeffentlRecht' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2057:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformJuristischePersonOeffentlRecht' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2058:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformJuristischePersonOeffentlRecht'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformJuristischePersonOeffentlRechtKeyword_142()); 
                    match(input,182,FOLLOW_182_in_rule__KLASSE__Alternatives4831); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformJuristischePersonOeffentlRechtKeyword_142()); 

                    }


                    }
                    break;
                case 144 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2065:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformKoerperschaftDesOeffentlichenRechts' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2065:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformKoerperschaftDesOeffentlichenRechts' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2066:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformKoerperschaftDesOeffentlichenRechts'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformKoerperschaftDesOeffentlichenRechtsKeyword_143()); 
                    match(input,183,FOLLOW_183_in_rule__KLASSE__Alternatives4851); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformKoerperschaftDesOeffentlichenRechtsKeyword_143()); 

                    }


                    }
                    break;
                case 145 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2073:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformOeffentlRechtlStiftung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2073:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformOeffentlRechtlStiftung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2074:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformOeffentlRechtlStiftung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformOeffentlRechtlStiftungKeyword_144()); 
                    match(input,184,FOLLOW_184_in_rule__KLASSE__Alternatives4871); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformOeffentlRechtlStiftungKeyword_144()); 

                    }


                    }
                    break;
                case 146 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2081:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformPrivatRechtlStiftung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2081:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformPrivatRechtlStiftung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2082:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformPrivatRechtlStiftung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformPrivatRechtlStiftungKeyword_145()); 
                    match(input,185,FOLLOW_185_in_rule__KLASSE__Alternatives4891); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformPrivatRechtlStiftungKeyword_145()); 

                    }


                    }
                    break;
                case 147 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2089:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonOeffentlRecht' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2089:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonOeffentlRecht' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2090:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonOeffentlRecht'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformSonstigeJuristischePersonOeffentlRechtKeyword_146()); 
                    match(input,186,FOLLOW_186_in_rule__KLASSE__Alternatives4911); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformSonstigeJuristischePersonOeffentlRechtKeyword_146()); 

                    }


                    }
                    break;
                case 148 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2097:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonPrivatRecht' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2097:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonPrivatRecht' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2098:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeJuristischePersonPrivatRecht'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformSonstigeJuristischePersonPrivatRechtKeyword_147()); 
                    match(input,187,FOLLOW_187_in_rule__KLASSE__Alternatives4931); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformSonstigeJuristischePersonPrivatRechtKeyword_147()); 

                    }


                    }
                    break;
                case 149 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2105:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeNatuerlichePerson' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2105:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeNatuerlichePerson' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2106:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRechtsformSonstigeNatuerlichePerson'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformSonstigeNatuerlichePersonKeyword_148()); 
                    match(input,188,FOLLOW_188_in_rule__KLASSE__Alternatives4951); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRechtsformSonstigeNatuerlichePersonKeyword_148()); 

                    }


                    }
                    break;
                case 150 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2113:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRelevantenEntscheidungenOffen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2113:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRelevantenEntscheidungenOffen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2114:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineRelevantenEntscheidungenOffen'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRelevantenEntscheidungenOffenKeyword_149()); 
                    match(input,189,FOLLOW_189_in_rule__KLASSE__Alternatives4971); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineRelevantenEntscheidungenOffenKeyword_149()); 

                    }


                    }
                    break;
                case 151 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2121:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformAnerkannteWeidegemeinschaft' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2121:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformAnerkannteWeidegemeinschaft' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2122:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformAnerkannteWeidegemeinschaft'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineUnternehmensformAnerkannteWeidegemeinschaftKeyword_150()); 
                    match(input,190,FOLLOW_190_in_rule__KLASSE__Alternatives4991); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineUnternehmensformAnerkannteWeidegemeinschaftKeyword_150()); 

                    }


                    }
                    break;
                case 152 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2129:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformEinzelantragstellerMeka' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2129:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformEinzelantragstellerMeka' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2130:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformEinzelantragstellerMeka'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineUnternehmensformEinzelantragstellerMekaKeyword_151()); 
                    match(input,191,FOLLOW_191_in_rule__KLASSE__Alternatives5011); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineUnternehmensformEinzelantragstellerMekaKeyword_151()); 

                    }


                    }
                    break;
                case 153 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2137:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformPheromongemeinschaft' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2137:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformPheromongemeinschaft' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2138:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformPheromongemeinschaft'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineUnternehmensformPheromongemeinschaftKeyword_152()); 
                    match(input,192,FOLLOW_192_in_rule__KLASSE__Alternatives5031); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineUnternehmensformPheromongemeinschaftKeyword_152()); 

                    }


                    }
                    break;
                case 154 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2145:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformWaldgemeinschaft' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2145:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformWaldgemeinschaft' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2146:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeineUnternehmensformWaldgemeinschaft'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineUnternehmensformWaldgemeinschaftKeyword_153()); 
                    match(input,193,FOLLOW_193_in_rule__KLASSE__Alternatives5051); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeineUnternehmensformWaldgemeinschaftKeyword_153()); 

                    }


                    }
                    break;
                case 155 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2153:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinFC104anBindungVorhanden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2153:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinFC104anBindungVorhanden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2154:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinFC104anBindungVorhanden'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinFC104anBindungVorhandenKeyword_154()); 
                    match(input,194,FOLLOW_194_in_rule__KLASSE__Alternatives5071); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinFC104anBindungVorhandenKeyword_154()); 

                    }


                    }
                    break;
                case 156 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2161:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinGLAusErzeugungGenommenNutzung592' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2161:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinGLAusErzeugungGenommenNutzung592' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2162:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinGLAusErzeugungGenommenNutzung592'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinGLAusErzeugungGenommenNutzung592Keyword_155()); 
                    match(input,195,FOLLOW_195_in_rule__KLASSE__Alternatives5091); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinGLAusErzeugungGenommenNutzung592Keyword_155()); 

                    }


                    }
                    break;
                case 157 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2169:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinKlaerschlammAusgebracht' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2169:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinKlaerschlammAusgebracht' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2170:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinKlaerschlammAusgebracht'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinKlaerschlammAusgebrachtKeyword_156()); 
                    match(input,196,FOLLOW_196_in_rule__KLASSE__Alternatives5111); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinKlaerschlammAusgebrachtKeyword_156()); 

                    }


                    }
                    break;
                case 158 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2177:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2177:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2178:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinWiderspruchImPEBKeyword_157()); 
                    match(input,197,FOLLOW_197_in_rule__KLASSE__Alternatives5131); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinWiderspruchImPEBKeyword_157()); 

                    }


                    }
                    break;
                case 159 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2185:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB2' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2185:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB2' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2186:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKeinWiderspruchImPEB2'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinWiderspruchImPEB2Keyword_158()); 
                    match(input,198,FOLLOW_198_in_rule__KLASSE__Alternatives5151); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKeinWiderspruchImPEB2Keyword_158()); 

                    }


                    }
                    break;
                case 160 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2193:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKombinierteGLAntraege' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2193:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKombinierteGLAntraege' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2194:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKombinierteGLAntraege'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKombinierteGLAntraegeKeyword_159()); 
                    match(input,199,FOLLOW_199_in_rule__KLASSE__Alternatives5171); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKombinierteGLAntraegeKeyword_159()); 

                    }


                    }
                    break;
                case 161 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2201:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollkostenzuschussMitND2Teilmassnahme' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2201:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollkostenzuschussMitND2Teilmassnahme' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2202:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollkostenzuschussMitND2Teilmassnahme'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKontrollkostenzuschussMitND2TeilmassnahmeKeyword_160()); 
                    match(input,200,FOLLOW_200_in_rule__KLASSE__Alternatives5191); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKontrollkostenzuschussMitND2TeilmassnahmeKeyword_160()); 

                    }


                    }
                    break;
                case 162 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2209:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollprotokoll' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2209:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollprotokoll' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2210:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollprotokoll'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKontrollprotokollKeyword_161()); 
                    match(input,201,FOLLOW_201_in_rule__KLASSE__Alternatives5211); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKontrollprotokollKeyword_161()); 

                    }


                    }
                    break;
                case 163 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2217:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollvertrag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2217:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollvertrag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2218:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKontrollvertrag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKontrollvertragKeyword_162()); 
                    match(input,202,FOLLOW_202_in_rule__KLASSE__Alternatives5231); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKontrollvertragKeyword_162()); 

                    }


                    }
                    break;
                case 164 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2225:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung20Prozent' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2225:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung20Prozent' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2226:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung20Prozent'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung20ProzentKeyword_163()); 
                    match(input,203,FOLLOW_203_in_rule__KLASSE__Alternatives5251); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung20ProzentKeyword_163()); 

                    }


                    }
                    break;
                case 165 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2233:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung30Prozent' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2233:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung30Prozent' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2234:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung30Prozent'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung30ProzentKeyword_164()); 
                    match(input,204,FOLLOW_204_in_rule__KLASSE__Alternatives5271); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung30ProzentKeyword_164()); 

                    }


                    }
                    break;
                case 166 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2241:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50Prozent' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2241:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50Prozent' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2242:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50Prozent'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung50ProzentKeyword_165()); 
                    match(input,205,FOLLOW_205_in_rule__KLASSE__Alternatives5291); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung50ProzentKeyword_165()); 

                    }


                    }
                    break;
                case 167 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2249:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentB1610' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2249:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentB1610' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2250:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentB1610'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung50ProzentB1610Keyword_166()); 
                    match(input,206,FOLLOW_206_in_rule__KLASSE__Alternatives5311); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung50ProzentB1610Keyword_166()); 

                    }


                    }
                    break;
                case 168 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2257:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2257:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2258:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAKulturgruppenAbweichung50ProzentVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung50ProzentVJKeyword_167()); 
                    match(input,207,FOLLOW_207_in_rule__KLASSE__Alternatives5331); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAKulturgruppenAbweichung50ProzentVJKeyword_167()); 

                    }


                    }
                    break;
                case 169 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2265:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALaborbeanstandungenLiegenNichtVor' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2265:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALaborbeanstandungenLiegenNichtVor' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2266:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALaborbeanstandungenLiegenNichtVor'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPALaborbeanstandungenLiegenNichtVorKeyword_168()); 
                    match(input,208,FOLLOW_208_in_rule__KLASSE__Alternatives5351); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPALaborbeanstandungenLiegenNichtVorKeyword_168()); 

                    }


                    }
                    break;
                case 170 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2273:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALandwirt' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2273:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALandwirt' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2274:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PALandwirt'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPALandwirtKeyword_169()); 
                    match(input,209,FOLLOW_209_in_rule__KLASSE__Alternatives5371); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPALandwirtKeyword_169()); 

                    }


                    }
                    break;
                case 171 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2281:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2281:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2282:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogen'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMantelbogenKeyword_170()); 
                    match(input,210,FOLLOW_210_in_rule__KLASSE__Alternatives5391); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMantelbogenKeyword_170()); 

                    }


                    }
                    break;
                case 172 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2289:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogenStichtag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2289:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogenStichtag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2290:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMantelbogenStichtag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMantelbogenStichtagKeyword_171()); 
                    match(input,211,FOLLOW_211_in_rule__KLASSE__Alternatives5411); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMantelbogenStichtagKeyword_171()); 

                    }


                    }
                    break;
                case 173 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2297:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaschinelleBerechnungVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2297:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaschinelleBerechnungVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2298:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaschinelleBerechnungVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaschinelleBerechnungVJKeyword_172()); 
                    match(input,212,FOLLOW_212_in_rule__KLASSE__Alternatives5431); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaschinelleBerechnungVJKeyword_172()); 

                    }


                    }
                    break;
                case 174 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2305:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFF' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2305:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFF' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2306:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFF'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz1_4RgvProHaHFFKeyword_173()); 
                    match(input,213,FOLLOW_213_in_rule__KLASSE__Alternatives5451); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz1_4RgvProHaHFFKeyword_173()); 

                    }


                    }
                    break;
                case 175 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2313:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusHIT' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2313:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusHIT' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2314:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusHIT'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz1_4RgvProHaHFFAusHITKeyword_174()); 
                    match(input,214,FOLLOW_214_in_rule__KLASSE__Alternatives5471); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz1_4RgvProHaHFFAusHITKeyword_174()); 

                    }


                    }
                    break;
                case 176 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2321:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2321:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2322:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18Keyword_175()); 
                    match(input,215,FOLLOW_215_in_rule__KLASSE__Alternatives5491); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz1_4RgvProHaHFFAusnahmeArt18Keyword_175()); 

                    }


                    }
                    break;
                case 177 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2329:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2329:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2330:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz2GveProHaLNFKeyword_176()); 
                    match(input,216,FOLLOW_216_in_rule__KLASSE__Alternatives5511); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz2GveProHaLNFKeyword_176()); 

                    }


                    }
                    break;
                case 178 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2337:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF2010' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2337:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF2010' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2338:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNF2010'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz2GveProHaLNF2010Keyword_177()); 
                    match(input,217,FOLLOW_217_in_rule__KLASSE__Alternatives5531); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz2GveProHaLNF2010Keyword_177()); 

                    }


                    }
                    break;
                case 179 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2345:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusHIT' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2345:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusHIT' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2346:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusHIT'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz2GveProHaLNFAusHITKeyword_178()); 
                    match(input,218,FOLLOW_218_in_rule__KLASSE__Alternatives5551); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz2GveProHaLNFAusHITKeyword_178()); 

                    }


                    }
                    break;
                case 180 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2353:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusnahmeArt18' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2353:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusnahmeArt18' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2354:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMaximalbesatz2GveProHaLNFAusnahmeArt18'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz2GveProHaLNFAusnahmeArt18Keyword_179()); 
                    match(input,219,FOLLOW_219_in_rule__KLASSE__Alternatives5571); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMaximalbesatz2GveProHaLNFAusnahmeArt18Keyword_179()); 

                    }


                    }
                    break;
                case 181 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2361:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestFlaeche' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2361:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestFlaeche' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2362:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestFlaeche'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMindestFlaecheKeyword_180()); 
                    match(input,220,FOLLOW_220_in_rule__KLASSE__Alternatives5591); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMindestFlaecheKeyword_180()); 

                    }


                    }
                    break;
                case 182 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2369:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestumfangWinterbegruenung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2369:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestumfangWinterbegruenung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2370:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMindestumfangWinterbegruenung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMindestumfangWinterbegruenungKeyword_181()); 
                    match(input,221,FOLLOW_221_in_rule__KLASSE__Alternatives5611); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMindestumfangWinterbegruenungKeyword_181()); 

                    }


                    }
                    break;
                case 183 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2377:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFF' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2377:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFF' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2378:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFF'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_2RgvProHaHFFKeyword_182()); 
                    match(input,222,FOLLOW_222_in_rule__KLASSE__Alternatives5631); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_2RgvProHaHFFKeyword_182()); 

                    }


                    }
                    break;
                case 184 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2385:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFFAusHIT' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2385:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFFAusHIT' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2386:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_2RgvProHaHFFAusHIT'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_2RgvProHaHFFAusHITKeyword_183()); 
                    match(input,223,FOLLOW_223_in_rule__KLASSE__Alternatives5651); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_2RgvProHaHFFAusHITKeyword_183()); 

                    }


                    }
                    break;
                case 185 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2393:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3GveProHaLNF' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2393:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3GveProHaLNF' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2394:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3GveProHaLNF'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3GveProHaLNFKeyword_184()); 
                    match(input,224,FOLLOW_224_in_rule__KLASSE__Alternatives5671); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3GveProHaLNFKeyword_184()); 

                    }


                    }
                    break;
                case 186 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2401:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaGL' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2401:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaGL' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2402:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaGL'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3RgvProHaGLKeyword_185()); 
                    match(input,225,FOLLOW_225_in_rule__KLASSE__Alternatives5691); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3RgvProHaGLKeyword_185()); 

                    }


                    }
                    break;
                case 187 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2409:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFF' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2409:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFF' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2410:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFF'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3RgvProHaHFFKeyword_186()); 
                    match(input,226,FOLLOW_226_in_rule__KLASSE__Alternatives5711); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3RgvProHaHFFKeyword_186()); 

                    }


                    }
                    break;
                case 188 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2417:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusHIT' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2417:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusHIT' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2418:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusHIT'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3RgvProHaHFFAusHITKeyword_187()); 
                    match(input,227,FOLLOW_227_in_rule__KLASSE__Alternatives5731); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3RgvProHaHFFAusHITKeyword_187()); 

                    }


                    }
                    break;
                case 189 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2425:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2425:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2426:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18Keyword_188()); 
                    match(input,228,FOLLOW_228_in_rule__KLASSE__Alternatives5751); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_3RgvProHaHFFAusnahmeArt18Keyword_188()); 

                    }


                    }
                    break;
                case 190 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2433:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_5RgvProHaGL' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2433:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_5RgvProHaGL' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2434:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_5RgvProHaGL'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_5RgvProHaGLKeyword_189()); 
                    match(input,229,FOLLOW_229_in_rule__KLASSE__Alternatives5771); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_5RgvProHaGLKeyword_189()); 

                    }


                    }
                    break;
                case 191 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2441:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_6GVEProHaFF' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2441:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_6GVEProHaFF' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2442:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMinimalbesatz0_6GVEProHaFF'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_6GVEProHaFFKeyword_190()); 
                    match(input,230,FOLLOW_230_in_rule__KLASSE__Alternatives5791); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMinimalbesatz0_6GVEProHaFFKeyword_190()); 

                    }


                    }
                    break;
                case 192 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2449:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMittelverwaltung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2449:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMittelverwaltung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2450:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAMittelverwaltung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMittelverwaltungKeyword_191()); 
                    match(input,231,FOLLOW_231_in_rule__KLASSE__Alternatives5811); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAMittelverwaltungKeyword_191()); 

                    }


                    }
                    break;
                case 193 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2457:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANachOeffnenBerechnet' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2457:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANachOeffnenBerechnet' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2458:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANachOeffnenBerechnet'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPANachOeffnenBerechnetKeyword_192()); 
                    match(input,232,FOLLOW_232_in_rule__KLASSE__Alternatives5831); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPANachOeffnenBerechnetKeyword_192()); 

                    }


                    }
                    break;
                case 194 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2465:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtAlleChecksWurdenBearbeitet' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2465:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtAlleChecksWurdenBearbeitet' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2466:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtAlleChecksWurdenBearbeitet'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPANichtAlleChecksWurdenBearbeitetKeyword_193()); 
                    match(input,233,FOLLOW_233_in_rule__KLASSE__Alternatives5851); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPANichtAlleChecksWurdenBearbeitetKeyword_193()); 

                    }


                    }
                    break;
                case 195 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2473:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtZuWenigAckerfutterAngebaut' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2473:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtZuWenigAckerfutterAngebaut' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2474:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANichtZuWenigAckerfutterAngebaut'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPANichtZuWenigAckerfutterAngebautKeyword_194()); 
                    match(input,234,FOLLOW_234_in_rule__KLASSE__Alternatives5871); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPANichtZuWenigAckerfutterAngebautKeyword_194()); 

                    }


                    }
                    break;
                case 196 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2481:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANurNachberechnungStornierbar' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2481:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANurNachberechnungStornierbar' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2482:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PANurNachberechnungStornierbar'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPANurNachberechnungStornierbarKeyword_195()); 
                    match(input,235,FOLLOW_235_in_rule__KLASSE__Alternatives5891); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPANurNachberechnungStornierbarKeyword_195()); 

                    }


                    }
                    break;
                case 197 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2489:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelBewilligterNABeiEajGleichAj' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2489:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelBewilligterNABeiEajGleichAj' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2490:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelBewilligterNABeiEajGleichAj'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAParallelBewilligterNABeiEajGleichAjKeyword_196()); 
                    match(input,236,FOLLOW_236_in_rule__KLASSE__Alternatives5911); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAParallelBewilligterNABeiEajGleichAjKeyword_196()); 

                    }


                    }
                    break;
                case 198 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2497:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAAEntschieden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2497:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAAEntschieden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2498:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAAEntschieden'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAParallelerAAEntschiedenKeyword_197()); 
                    match(input,237,FOLLOW_237_in_rule__KLASSE__Alternatives5931); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAParallelerAAEntschiedenKeyword_197()); 

                    }


                    }
                    break;
                case 199 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2505:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragFP773' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2505:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragFP773' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2506:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragFP773'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAParallelerAntragFP773Keyword_198()); 
                    match(input,238,FOLLOW_238_in_rule__KLASSE__Alternatives5951); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAParallelerAntragFP773Keyword_198()); 

                    }


                    }
                    break;
                case 200 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2513:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragZuFP774' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2513:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragZuFP774' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2514:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAParallelerAntragZuFP774'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAParallelerAntragZuFP774Keyword_199()); 
                    match(input,239,FOLLOW_239_in_rule__KLASSE__Alternatives5971); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAParallelerAntragZuFP774Keyword_199()); 

                    }


                    }
                    break;
                case 201 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2521:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendig' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2521:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendig' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2522:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendig'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAPebVollstaendigKeyword_200()); 
                    match(input,240,FOLLOW_240_in_rule__KLASSE__Alternatives5991); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAPebVollstaendigKeyword_200()); 

                    }


                    }
                    break;
                case 202 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2529:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendigLZJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2529:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendigLZJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2530:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAPebVollstaendigLZJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAPebVollstaendigLZJKeyword_201()); 
                    match(input,241,FOLLOW_241_in_rule__KLASSE__Alternatives6011); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAPebVollstaendigLZJKeyword_201()); 

                    }


                    }
                    break;
                case 203 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2537:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2537:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2538:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAQualitaetssicherungKeyword_202()); 
                    match(input,242,FOLLOW_242_in_rule__KLASSE__Alternatives6031); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAQualitaetssicherungKeyword_202()); 

                    }


                    }
                    break;
                case 204 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2545:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungFlaechenmappeVj' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2545:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungFlaechenmappeVj' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2546:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungFlaechenmappeVj'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAQualitaetssicherungFlaechenmappeVjKeyword_203()); 
                    match(input,243,FOLLOW_243_in_rule__KLASSE__Alternatives6051); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAQualitaetssicherungFlaechenmappeVjKeyword_203()); 

                    }


                    }
                    break;
                case 205 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2553:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungVj' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2553:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungVj' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2554:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAQualitaetssicherungVj'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAQualitaetssicherungVjKeyword_204()); 
                    match(input,244,FOLLOW_244_in_rule__KLASSE__Alternatives6071); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAQualitaetssicherungVjKeyword_204()); 

                    }


                    }
                    break;
                case 206 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2561:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleich' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2561:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleich' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2562:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleich'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAReferenzflaechenAbgleichKeyword_205()); 
                    match(input,245,FOLLOW_245_in_rule__KLASSE__Alternatives6091); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAReferenzflaechenAbgleichKeyword_205()); 

                    }


                    }
                    break;
                case 207 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2569:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichBeendetVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2569:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichBeendetVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2570:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichBeendetVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAReferenzflaechenAbgleichBeendetVJKeyword_206()); 
                    match(input,246,FOLLOW_246_in_rule__KLASSE__Alternatives6111); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAReferenzflaechenAbgleichBeendetVJKeyword_206()); 

                    }


                    }
                    break;
                case 208 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2577:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichDurchgefuehrtVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2577:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichDurchgefuehrtVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2578:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAReferenzflaechenAbgleichDurchgefuehrtVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAReferenzflaechenAbgleichDurchgefuehrtVJKeyword_207()); 
                    match(input,247,FOLLOW_247_in_rule__KLASSE__Alternatives6131); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAReferenzflaechenAbgleichDurchgefuehrtVJKeyword_207()); 

                    }


                    }
                    break;
                case 209 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2585:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaenger' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2585:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaenger' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2586:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaenger'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARentenempfaengerKeyword_208()); 
                    match(input,248,FOLLOW_248_in_rule__KLASSE__Alternatives6151); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARentenempfaengerKeyword_208()); 

                    }


                    }
                    break;
                case 210 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2593:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaengerAlsEinzelunternehmer' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2593:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaengerAlsEinzelunternehmer' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2594:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARentenempfaengerAlsEinzelunternehmer'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARentenempfaengerAlsEinzelunternehmerKeyword_209()); 
                    match(input,249,FOLLOW_249_in_rule__KLASSE__Alternatives6171); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARentenempfaengerAlsEinzelunternehmerKeyword_209()); 

                    }


                    }
                    break;
                case 211 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2601:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeit' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2601:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeit' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2602:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeit'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARestlaufzeitKeyword_210()); 
                    match(input,250,FOLLOW_250_in_rule__KLASSE__Alternatives6191); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARestlaufzeitKeyword_210()); 

                    }


                    }
                    break;
                case 212 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2609:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitEinJahr' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2609:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitEinJahr' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2610:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitEinJahr'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARestlaufzeitEinJahrKeyword_211()); 
                    match(input,251,FOLLOW_251_in_rule__KLASSE__Alternatives6211); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARestlaufzeitEinJahrKeyword_211()); 

                    }


                    }
                    break;
                case 213 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2617:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitVNS' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2617:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitVNS' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2618:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARestlaufzeitVNS'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARestlaufzeitVNSKeyword_212()); 
                    match(input,252,FOLLOW_252_in_rule__KLASSE__Alternatives6231); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARestlaufzeitVNSKeyword_212()); 

                    }


                    }
                    break;
                case 214 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2625:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVJVokAJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2625:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVJVokAJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2626:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVJVokAJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVJVokAJKeyword_213()); 
                    match(input,253,FOLLOW_253_in_rule__KLASSE__Alternatives6251); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVJVokAJKeyword_213()); 

                    }


                    }
                    break;
                case 215 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2633:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVok' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2633:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVok' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2634:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVok'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVokKeyword_214()); 
                    match(input,254,FOLLOW_254_in_rule__KLASSE__Alternatives6271); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVokKeyword_214()); 

                    }


                    }
                    break;
                case 216 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2641:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokCc' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2641:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokCc' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2642:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokCc'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVokCcKeyword_215()); 
                    match(input,255,FOLLOW_255_in_rule__KLASSE__Alternatives6291); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVokCcKeyword_215()); 

                    }


                    }
                    break;
                case 217 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2649:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokStichtag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2649:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokStichtag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2650:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokStichtag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVokStichtagKeyword_216()); 
                    match(input,256,FOLLOW_256_in_rule__KLASSE__Alternatives6311); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVokStichtagKeyword_216()); 

                    }


                    }
                    break;
                case 218 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2657:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2657:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2658:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARiaVokVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVokVJKeyword_217()); 
                    match(input,257,FOLLOW_257_in_rule__KLASSE__Alternatives6331); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARiaVokVJKeyword_217()); 

                    }


                    }
                    break;
                case 219 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2665:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2665:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2666:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungen'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARueckforderungenKeyword_218()); 
                    match(input,258,FOLLOW_258_in_rule__KLASSE__Alternatives6351); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARueckforderungenKeyword_218()); 

                    }


                    }
                    break;
                case 220 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2673:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungenOderNullzahlung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2673:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungenOderNullzahlung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2674:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PARueckforderungenOderNullzahlung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARueckforderungenOderNullzahlungKeyword_219()); 
                    match(input,259,FOLLOW_259_in_rule__KLASSE__Alternatives6371); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPARueckforderungenOderNullzahlungKeyword_219()); 

                    }


                    }
                    break;
                case 221 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2681:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASGAbgleich' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2681:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASGAbgleich' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2682:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASGAbgleich'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASGAbgleichKeyword_220()); 
                    match(input,260,FOLLOW_260_in_rule__KLASSE__Alternatives6391); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASGAbgleichKeyword_220()); 

                    }


                    }
                    break;
                case 222 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2689:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstFeststellungenAbsichtlUnregelmaessigkeiten' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2689:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstFeststellungenAbsichtlUnregelmaessigkeiten' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2690:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstFeststellungenAbsichtlUnregelmaessigkeiten'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASonstFeststellungenAbsichtlUnregelmaessigkeitenKeyword_221()); 
                    match(input,261,FOLLOW_261_in_rule__KLASSE__Alternatives6411); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASonstFeststellungenAbsichtlUnregelmaessigkeitenKeyword_221()); 

                    }


                    }
                    break;
                case 223 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2697:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2697:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2698:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungen'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASonstigeFeststellungenKeyword_222()); 
                    match(input,262,FOLLOW_262_in_rule__KLASSE__Alternatives6431); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASonstigeFeststellungenKeyword_222()); 

                    }


                    }
                    break;
                case 224 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2705:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenStichtag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2705:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenStichtag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2706:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenStichtag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASonstigeFeststellungenStichtagKeyword_223()); 
                    match(input,263,FOLLOW_263_in_rule__KLASSE__Alternatives6451); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASonstigeFeststellungenStichtagKeyword_223()); 

                    }


                    }
                    break;
                case 225 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2713:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2713:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2714:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASonstigeFeststellungenVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASonstigeFeststellungenVJKeyword_224()); 
                    match(input,264,FOLLOW_264_in_rule__KLASSE__Alternatives6471); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASonstigeFeststellungenVJKeyword_224()); 

                    }


                    }
                    break;
                case 226 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2721:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASperrvermerkNichtVergeben' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2721:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASperrvermerkNichtVergeben' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2722:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PASperrvermerkNichtVergeben'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASperrvermerkNichtVergebenKeyword_225()); 
                    match(input,265,FOLLOW_265_in_rule__KLASSE__Alternatives6491); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPASperrvermerkNichtVergebenKeyword_225()); 

                    }


                    }
                    break;
                case 227 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2729:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStammdatenAktuell' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2729:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStammdatenAktuell' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2730:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStammdatenAktuell'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAStammdatenAktuellKeyword_226()); 
                    match(input,266,FOLLOW_266_in_rule__KLASSE__Alternatives6511); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAStammdatenAktuellKeyword_226()); 

                    }


                    }
                    break;
                case 228 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2737:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStichtagHelper' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2737:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStichtagHelper' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2738:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAStichtagHelper'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAStichtagHelperKeyword_227()); 
                    match(input,267,FOLLOW_267_in_rule__KLASSE__Alternatives6531); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAStichtagHelperKeyword_227()); 

                    }


                    }
                    break;
                case 229 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2745:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATeilmassnahmeND1UndND2Beantragt' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2745:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATeilmassnahmeND1UndND2Beantragt' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2746:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATeilmassnahmeND1UndND2Beantragt'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATeilmassnahmeND1UndND2BeantragtKeyword_228()); 
                    match(input,268,FOLLOW_268_in_rule__KLASSE__Alternatives6551); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATeilmassnahmeND1UndND2BeantragtKeyword_228()); 

                    }


                    }
                    break;
                case 230 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2753:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbesatzGesamt' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2753:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbesatzGesamt' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2754:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbesatzGesamt'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATierbesatzGesamtKeyword_229()); 
                    match(input,269,FOLLOW_269_in_rule__KLASSE__Alternatives6571); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATierbesatzGesamtKeyword_229()); 

                    }


                    }
                    break;
                case 231 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2761:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFF' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2761:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFF' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2762:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFF'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATierbestandEingehaltenRgvProHaHFFKeyword_230()); 
                    match(input,270,FOLLOW_270_in_rule__KLASSE__Alternatives6591); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATierbestandEingehaltenRgvProHaHFFKeyword_230()); 

                    }


                    }
                    break;
                case 232 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2769:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFundGveProHaLF' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2769:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFundGveProHaLF' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2770:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFundGveProHaLF'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATierbestandEingehaltenRgvProHaHFFundGveProHaLFKeyword_231()); 
                    match(input,271,FOLLOW_271_in_rule__KLASSE__Alternatives6611); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATierbestandEingehaltenRgvProHaHFFundGveProHaLFKeyword_231()); 

                    }


                    }
                    break;
                case 233 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2777:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFZusNCs' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2777:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFZusNCs' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2778:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PATierbestandEingehaltenRgvProHaHFFZusNCs'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATierbestandEingehaltenRgvProHaHFFZusNCsKeyword_232()); 
                    match(input,272,FOLLOW_272_in_rule__KLASSE__Alternatives6631); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPATierbestandEingehaltenRgvProHaHFFZusNCsKeyword_232()); 

                    }


                    }
                    break;
                case 234 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2785:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUmwandlungALInGL' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2785:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUmwandlungALInGL' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2786:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUmwandlungALInGL'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUmwandlungALInGLKeyword_233()); 
                    match(input,273,FOLLOW_273_in_rule__KLASSE__Alternatives6651); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUmwandlungALInGLKeyword_233()); 

                    }


                    }
                    break;
                case 235 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2793:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenAusserhalbDerEU' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2793:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenAusserhalbDerEU' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2794:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenAusserhalbDerEU'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnternehmenAusserhalbDerEUKeyword_234()); 
                    match(input,274,FOLLOW_274_in_rule__KLASSE__Alternatives6671); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnternehmenAusserhalbDerEUKeyword_234()); 

                    }


                    }
                    break;
                case 236 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2801:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenOhneSchafeZiegenMitVertragVereinbarung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2801:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenOhneSchafeZiegenMitVertragVereinbarung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2802:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenOhneSchafeZiegenMitVertragVereinbarung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnternehmenOhneSchafeZiegenMitVertragVereinbarungKeyword_235()); 
                    match(input,275,FOLLOW_275_in_rule__KLASSE__Alternatives6691); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnternehmenOhneSchafeZiegenMitVertragVereinbarungKeyword_235()); 

                    }


                    }
                    break;
                case 237 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2809:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenssitzInBw' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2809:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenssitzInBw' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2810:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnternehmenssitzInBw'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnternehmenssitzInBwKeyword_236()); 
                    match(input,276,FOLLOW_276_in_rule__KLASSE__Alternatives6711); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnternehmenssitzInBwKeyword_236()); 

                    }


                    }
                    break;
                case 238 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2817:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschiedBerechneteUndManuelleVerpflFlFJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2817:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschiedBerechneteUndManuelleVerpflFlFJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2818:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschiedBerechneteUndManuelleVerpflFlFJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnterschiedBerechneteUndManuelleVerpflFlFJKeyword_237()); 
                    match(input,277,FOLLOW_277_in_rule__KLASSE__Alternatives6731); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnterschiedBerechneteUndManuelleVerpflFlFJKeyword_237()); 

                    }


                    }
                    break;
                case 239 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2825:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschreitungTierbestzDurchFeststellung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2825:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschreitungTierbestzDurchFeststellung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2826:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnterschreitungTierbestzDurchFeststellung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnterschreitungTierbestzDurchFeststellungKeyword_238()); 
                    match(input,278,FOLLOW_278_in_rule__KLASSE__Alternatives6751); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnterschreitungTierbestzDurchFeststellungKeyword_238()); 

                    }


                    }
                    break;
                case 240 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2833:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNC' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2833:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNC' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2834:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNC'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnzulaessigeNCKeyword_239()); 
                    match(input,279,FOLLOW_279_in_rule__KLASSE__Alternatives6771); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnzulaessigeNCKeyword_239()); 

                    }


                    }
                    break;
                case 241 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2841:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNcVNS' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2841:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNcVNS' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2842:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeNcVNS'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnzulaessigeNcVNSKeyword_240()); 
                    match(input,280,FOLLOW_280_in_rule__KLASSE__Alternatives6791); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnzulaessigeNcVNSKeyword_240()); 

                    }


                    }
                    break;
                case 242 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2849:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheAckerfutter' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2849:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheAckerfutter' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2850:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheAckerfutter'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnzulaessigeVerringerungVerpflFlaecheAckerfutterKeyword_241()); 
                    match(input,281,FOLLOW_281_in_rule__KLASSE__Alternatives6811); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnzulaessigeVerringerungVerpflFlaecheAckerfutterKeyword_241()); 

                    }


                    }
                    break;
                case 243 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2857:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2857:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2858:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenungKeyword_242()); 
                    match(input,282,FOLLOW_282_in_rule__KLASSE__Alternatives6831); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAUnzulaessigeVerringerungVerpflFlaecheWinterbegruenungKeyword_242()); 

                    }


                    }
                    break;
                case 244 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2865:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentimVJVorhanden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2865:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentimVJVorhanden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2866:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentimVJVorhanden'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerlaengerungsdokumentimVJVorhandenKeyword_243()); 
                    match(input,283,FOLLOW_283_in_rule__KLASSE__Alternatives6851); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerlaengerungsdokumentimVJVorhandenKeyword_243()); 

                    }


                    }
                    break;
                case 245 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2873:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentVorhanden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2873:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentVorhanden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2874:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerlaengerungsdokumentVorhanden'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerlaengerungsdokumentVorhandenKeyword_244()); 
                    match(input,284,FOLLOW_284_in_rule__KLASSE__Alternatives6871); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerlaengerungsdokumentVorhandenKeyword_244()); 

                    }


                    }
                    break;
                case 246 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2881:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2881:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2882:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18Keyword_245()); 
                    match(input,285,FOLLOW_285_in_rule__KLASSE__Alternatives6891); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18Keyword_245()); 

                    }


                    }
                    break;
                case 247 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2889:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18Beanstandungen' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2889:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18Beanstandungen' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2890:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18Beanstandungen'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18NurArt18BeanstandungenKeyword_246()); 
                    match(input,286,FOLLOW_286_in_rule__KLASSE__Alternatives6911); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18NurArt18BeanstandungenKeyword_246()); 

                    }


                    }
                    break;
                case 248 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2897:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18BeanstandungenVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2897:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18BeanstandungenVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2898:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18NurArt18BeanstandungenVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18NurArt18BeanstandungenVJKeyword_247()); 
                    match(input,287,FOLLOW_287_in_rule__KLASSE__Alternatives6931); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18NurArt18BeanstandungenVJKeyword_247()); 

                    }


                    }
                    break;
                case 249 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2905:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2905:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2906:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18VJKeyword_248()); 
                    match(input,288,FOLLOW_288_in_rule__KLASSE__Alternatives6951); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18VJKeyword_248()); 

                    }


                    }
                    break;
                case 250 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2913:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJFuerMindEineBindung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2913:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJFuerMindEineBindung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2914:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVerstossArt18VJFuerMindEineBindung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18VJFuerMindEineBindungKeyword_249()); 
                    match(input,289,FOLLOW_289_in_rule__KLASSE__Alternatives6971); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVerstossArt18VJFuerMindEineBindungKeyword_249()); 

                    }


                    }
                    break;
                case 251 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2921:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsNrFuerAlleFlaechenVergeben' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2921:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsNrFuerAlleFlaechenVergeben' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2922:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsNrFuerAlleFlaechenVergeben'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVertragsNrFuerAlleFlaechenVergebenKeyword_250()); 
                    match(input,290,FOLLOW_290_in_rule__KLASSE__Alternatives6991); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVertragsNrFuerAlleFlaechenVergebenKeyword_250()); 

                    }


                    }
                    break;
                case 252 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2929:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsnummer' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2929:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsnummer' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2930:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragsnummer'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVertragsnummerKeyword_251()); 
                    match(input,291,FOLLOW_291_in_rule__KLASSE__Alternatives7011); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVertragsnummerKeyword_251()); 

                    }


                    }
                    break;
                case 253 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2937:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragVorhanden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2937:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragVorhanden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2938:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVertragVorhanden'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVertragVorhandenKeyword_252()); 
                    match(input,292,FOLLOW_292_in_rule__KLASSE__Alternatives7031); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVertragVorhandenKeyword_252()); 

                    }


                    }
                    break;
                case 254 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2945:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVeterinaerBestaetigungVorhanden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2945:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVeterinaerBestaetigungVorhanden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2946:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVeterinaerBestaetigungVorhanden'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVeterinaerBestaetigungVorhandenKeyword_253()); 
                    match(input,293,FOLLOW_293_in_rule__KLASSE__Alternatives7051); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVeterinaerBestaetigungVorhandenKeyword_253()); 

                    }


                    }
                    break;
                case 255 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2953:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendet' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2953:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendet' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2954:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendet'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVOKBeendetKeyword_254()); 
                    match(input,294,FOLLOW_294_in_rule__KLASSE__Alternatives7071); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVOKBeendetKeyword_254()); 

                    }


                    }
                    break;
                case 256 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2961:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetStichtag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2961:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetStichtag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2962:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetStichtag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVOKBeendetStichtagKeyword_255()); 
                    match(input,295,FOLLOW_295_in_rule__KLASSE__Alternatives7091); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVOKBeendetStichtagKeyword_255()); 

                    }


                    }
                    break;
                case 257 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2969:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2969:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2970:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKBeendetVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVOKBeendetVJKeyword_256()); 
                    match(input,296,FOLLOW_296_in_rule__KLASSE__Alternatives7111); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVOKBeendetVJKeyword_256()); 

                    }


                    }
                    break;
                case 258 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2977:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKCCBeendet' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2977:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKCCBeendet' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2978:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVOKCCBeendet'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVOKCCBeendetKeyword_257()); 
                    match(input,297,FOLLOW_297_in_rule__KLASSE__Alternatives7131); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVOKCCBeendetKeyword_257()); 

                    }


                    }
                    break;
                case 259 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2985:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVokNichtVerweigert' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2985:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVokNichtVerweigert' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2986:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVokNichtVerweigert'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVokNichtVerweigertKeyword_258()); 
                    match(input,298,FOLLOW_298_in_rule__KLASSE__Alternatives7151); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVokNichtVerweigertKeyword_258()); 

                    }


                    }
                    break;
                case 260 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2993:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKPEnthaeltBenutzerdefPruefkonf' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2993:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKPEnthaeltBenutzerdefPruefkonf' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:2994:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKPEnthaeltBenutzerdefPruefkonf'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVWKPEnthaeltBenutzerdefPruefkonfKeyword_259()); 
                    match(input,299,FOLLOW_299_in_rule__KLASSE__Alternatives7171); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVWKPEnthaeltBenutzerdefPruefkonfKeyword_259()); 

                    }


                    }
                    break;
                case 261 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3001:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKReferenzflaechenAbgleich' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3001:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKReferenzflaechenAbgleich' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3002:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVWKReferenzflaechenAbgleich'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVWKReferenzflaechenAbgleichKeyword_260()); 
                    match(input,300,FOLLOW_300_in_rule__KLASSE__Alternatives7191); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVWKReferenzflaechenAbgleichKeyword_260()); 

                    }


                    }
                    break;
                case 262 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3009:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragsteller' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3009:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragsteller' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3010:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragsteller'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVwkZIDAntragstellerKeyword_261()); 
                    match(input,301,FOLLOW_301_in_rule__KLASSE__Alternatives7211); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVwkZIDAntragstellerKeyword_261()); 

                    }


                    }
                    break;
                case 263 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3017:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragstellerStichtag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3017:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragstellerStichtag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3018:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAVwkZIDAntragstellerStichtag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVwkZIDAntragstellerStichtagKeyword_262()); 
                    match(input,302,FOLLOW_302_in_rule__KLASSE__Alternatives7231); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAVwkZIDAntragstellerStichtagKeyword_262()); 

                    }


                    }
                    break;
                case 264 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3025:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWeidetagebuchImPebVorhanden' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3025:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWeidetagebuchImPebVorhanden' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3026:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWeidetagebuchImPebVorhanden'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAWeidetagebuchImPebVorhandenKeyword_263()); 
                    match(input,303,FOLLOW_303_in_rule__KLASSE__Alternatives7251); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAWeidetagebuchImPebVorhandenKeyword_263()); 

                    }


                    }
                    break;
                case 265 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3033:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWiderspruchInVorherigerBerechnung' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3033:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWiderspruchInVorherigerBerechnung' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3034:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAWiderspruchInVorherigerBerechnung'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAWiderspruchInVorherigerBerechnungKeyword_264()); 
                    match(input,304,FOLLOW_304_in_rule__KLASSE__Alternatives7271); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAWiderspruchInVorherigerBerechnungKeyword_264()); 

                    }


                    }
                    break;
                case 266 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3041:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFK' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3041:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFK' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3042:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFK'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAZFKKeyword_265()); 
                    match(input,305,FOLLOW_305_in_rule__KLASSE__Alternatives7291); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAZFKKeyword_265()); 

                    }


                    }
                    break;
                case 267 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3049:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKStichtag' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3049:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKStichtag' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3050:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKStichtag'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAZFKStichtagKeyword_266()); 
                    match(input,306,FOLLOW_306_in_rule__KLASSE__Alternatives7311); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAZFKStichtagKeyword_266()); 

                    }


                    }
                    break;
                case 268 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3057:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKVJ' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3057:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKVJ' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3058:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.pruefungen.PAZFKVJ'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAZFKVJKeyword_267()); 
                    match(input,307,FOLLOW_307_in_rule__KLASSE__Alternatives7331); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgPruefungenPAZFKVJKeyword_267()); 

                    }


                    }
                    break;
                case 269 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3065:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PASperrvermerkNichtVergeben' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3065:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PASperrvermerkNichtVergeben' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3066:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PASperrvermerkNichtVergeben'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgBusinessPruefungenPASperrvermerkNichtVergebenKeyword_268()); 
                    match(input,308,FOLLOW_308_in_rule__KLASSE__Alternatives7351); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgBusinessPruefungenPASperrvermerkNichtVergebenKeyword_268()); 

                    }


                    }
                    break;
                case 270 :
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3073:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' )
                    {
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3073:6: ( 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet' )
                    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3074:1: 'DE.data_experts.profi.profilcs.antrag.aum.allg.business.pruefungen.PAAumKuliZuMaAZLAktuellUndBeendet'
                    {
                     before(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgBusinessPruefungenPAAumKuliZuMaAZLAktuellUndBeendetKeyword_269()); 
                    match(input,309,FOLLOW_309_in_rule__KLASSE__Alternatives7371); 
                     after(grammarAccess.getKLASSEAccess().getDEData_expertsProfiProfilcsAntragAumAllgBusinessPruefungenPAAumKuliZuMaAZLAktuellUndBeendetKeyword_269()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KLASSE__Alternatives"


    // $ANTLR start "rule__Konfiguration__Group__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3088:1: rule__Konfiguration__Group__0 : rule__Konfiguration__Group__0__Impl rule__Konfiguration__Group__1 ;
    public final void rule__Konfiguration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3092:1: ( rule__Konfiguration__Group__0__Impl rule__Konfiguration__Group__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3093:2: rule__Konfiguration__Group__0__Impl rule__Konfiguration__Group__1
            {
            pushFollow(FOLLOW_rule__Konfiguration__Group__0__Impl_in_rule__Konfiguration__Group__07403);
            rule__Konfiguration__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Konfiguration__Group__1_in_rule__Konfiguration__Group__07406);
            rule__Konfiguration__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__Group__0"


    // $ANTLR start "rule__Konfiguration__Group__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3100:1: rule__Konfiguration__Group__0__Impl : ( ( rule__Konfiguration__UsedidsAssignment_0 ) ) ;
    public final void rule__Konfiguration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3104:1: ( ( ( rule__Konfiguration__UsedidsAssignment_0 ) ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3105:1: ( ( rule__Konfiguration__UsedidsAssignment_0 ) )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3105:1: ( ( rule__Konfiguration__UsedidsAssignment_0 ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3106:1: ( rule__Konfiguration__UsedidsAssignment_0 )
            {
             before(grammarAccess.getKonfigurationAccess().getUsedidsAssignment_0()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3107:1: ( rule__Konfiguration__UsedidsAssignment_0 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3107:2: rule__Konfiguration__UsedidsAssignment_0
            {
            pushFollow(FOLLOW_rule__Konfiguration__UsedidsAssignment_0_in_rule__Konfiguration__Group__0__Impl7433);
            rule__Konfiguration__UsedidsAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getKonfigurationAccess().getUsedidsAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__Group__0__Impl"


    // $ANTLR start "rule__Konfiguration__Group__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3117:1: rule__Konfiguration__Group__1 : rule__Konfiguration__Group__1__Impl rule__Konfiguration__Group__2 ;
    public final void rule__Konfiguration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3121:1: ( rule__Konfiguration__Group__1__Impl rule__Konfiguration__Group__2 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3122:2: rule__Konfiguration__Group__1__Impl rule__Konfiguration__Group__2
            {
            pushFollow(FOLLOW_rule__Konfiguration__Group__1__Impl_in_rule__Konfiguration__Group__17463);
            rule__Konfiguration__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Konfiguration__Group__2_in_rule__Konfiguration__Group__17466);
            rule__Konfiguration__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__Group__1"


    // $ANTLR start "rule__Konfiguration__Group__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3129:1: rule__Konfiguration__Group__1__Impl : ( ( rule__Konfiguration__SpezantragszuweisungAssignment_1 )* ) ;
    public final void rule__Konfiguration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3133:1: ( ( ( rule__Konfiguration__SpezantragszuweisungAssignment_1 )* ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3134:1: ( ( rule__Konfiguration__SpezantragszuweisungAssignment_1 )* )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3134:1: ( ( rule__Konfiguration__SpezantragszuweisungAssignment_1 )* )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3135:1: ( rule__Konfiguration__SpezantragszuweisungAssignment_1 )*
            {
             before(grammarAccess.getKonfigurationAccess().getSpezantragszuweisungAssignment_1()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3136:1: ( rule__Konfiguration__SpezantragszuweisungAssignment_1 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_INT) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3136:2: rule__Konfiguration__SpezantragszuweisungAssignment_1
            	    {
            	    pushFollow(FOLLOW_rule__Konfiguration__SpezantragszuweisungAssignment_1_in_rule__Konfiguration__Group__1__Impl7493);
            	    rule__Konfiguration__SpezantragszuweisungAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getKonfigurationAccess().getSpezantragszuweisungAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__Group__1__Impl"


    // $ANTLR start "rule__Konfiguration__Group__2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3146:1: rule__Konfiguration__Group__2 : rule__Konfiguration__Group__2__Impl rule__Konfiguration__Group__3 ;
    public final void rule__Konfiguration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3150:1: ( rule__Konfiguration__Group__2__Impl rule__Konfiguration__Group__3 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3151:2: rule__Konfiguration__Group__2__Impl rule__Konfiguration__Group__3
            {
            pushFollow(FOLLOW_rule__Konfiguration__Group__2__Impl_in_rule__Konfiguration__Group__27524);
            rule__Konfiguration__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Konfiguration__Group__3_in_rule__Konfiguration__Group__27527);
            rule__Konfiguration__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__Group__2"


    // $ANTLR start "rule__Konfiguration__Group__2__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3158:1: rule__Konfiguration__Group__2__Impl : ( ( rule__Konfiguration__AntragszuweisungAssignment_2 )* ) ;
    public final void rule__Konfiguration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3162:1: ( ( ( rule__Konfiguration__AntragszuweisungAssignment_2 )* ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3163:1: ( ( rule__Konfiguration__AntragszuweisungAssignment_2 )* )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3163:1: ( ( rule__Konfiguration__AntragszuweisungAssignment_2 )* )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3164:1: ( rule__Konfiguration__AntragszuweisungAssignment_2 )*
            {
             before(grammarAccess.getKonfigurationAccess().getAntragszuweisungAssignment_2()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3165:1: ( rule__Konfiguration__AntragszuweisungAssignment_2 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==322) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3165:2: rule__Konfiguration__AntragszuweisungAssignment_2
            	    {
            	    pushFollow(FOLLOW_rule__Konfiguration__AntragszuweisungAssignment_2_in_rule__Konfiguration__Group__2__Impl7554);
            	    rule__Konfiguration__AntragszuweisungAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

             after(grammarAccess.getKonfigurationAccess().getAntragszuweisungAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__Group__2__Impl"


    // $ANTLR start "rule__Konfiguration__Group__3"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3175:1: rule__Konfiguration__Group__3 : rule__Konfiguration__Group__3__Impl ;
    public final void rule__Konfiguration__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3179:1: ( rule__Konfiguration__Group__3__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3180:2: rule__Konfiguration__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Konfiguration__Group__3__Impl_in_rule__Konfiguration__Group__37585);
            rule__Konfiguration__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__Group__3"


    // $ANTLR start "rule__Konfiguration__Group__3__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3186:1: rule__Konfiguration__Group__3__Impl : ( ( rule__Konfiguration__Alternatives_3 )* ) ;
    public final void rule__Konfiguration__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3190:1: ( ( ( rule__Konfiguration__Alternatives_3 )* ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3191:1: ( ( rule__Konfiguration__Alternatives_3 )* )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3191:1: ( ( rule__Konfiguration__Alternatives_3 )* )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3192:1: ( rule__Konfiguration__Alternatives_3 )*
            {
             before(grammarAccess.getKonfigurationAccess().getAlternatives_3()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3193:1: ( rule__Konfiguration__Alternatives_3 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==310||LA9_0==312||(LA9_0>=316 && LA9_0<=319)||LA9_0==321) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3193:2: rule__Konfiguration__Alternatives_3
            	    {
            	    pushFollow(FOLLOW_rule__Konfiguration__Alternatives_3_in_rule__Konfiguration__Group__3__Impl7612);
            	    rule__Konfiguration__Alternatives_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

             after(grammarAccess.getKonfigurationAccess().getAlternatives_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__Group__3__Impl"


    // $ANTLR start "rule__PRUEFUNGSICHTBARKEIT__Group__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3211:1: rule__PRUEFUNGSICHTBARKEIT__Group__0 : rule__PRUEFUNGSICHTBARKEIT__Group__0__Impl rule__PRUEFUNGSICHTBARKEIT__Group__1 ;
    public final void rule__PRUEFUNGSICHTBARKEIT__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3215:1: ( rule__PRUEFUNGSICHTBARKEIT__Group__0__Impl rule__PRUEFUNGSICHTBARKEIT__Group__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3216:2: rule__PRUEFUNGSICHTBARKEIT__Group__0__Impl rule__PRUEFUNGSICHTBARKEIT__Group__1
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSICHTBARKEIT__Group__0__Impl_in_rule__PRUEFUNGSICHTBARKEIT__Group__07651);
            rule__PRUEFUNGSICHTBARKEIT__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSICHTBARKEIT__Group__1_in_rule__PRUEFUNGSICHTBARKEIT__Group__07654);
            rule__PRUEFUNGSICHTBARKEIT__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSICHTBARKEIT__Group__0"


    // $ANTLR start "rule__PRUEFUNGSICHTBARKEIT__Group__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3223:1: rule__PRUEFUNGSICHTBARKEIT__Group__0__Impl : ( 'PruefungSichtbar.' ) ;
    public final void rule__PRUEFUNGSICHTBARKEIT__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3227:1: ( ( 'PruefungSichtbar.' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3228:1: ( 'PruefungSichtbar.' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3228:1: ( 'PruefungSichtbar.' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3229:1: 'PruefungSichtbar.'
            {
             before(grammarAccess.getPRUEFUNGSICHTBARKEITAccess().getPruefungSichtbarKeyword_0()); 
            match(input,310,FOLLOW_310_in_rule__PRUEFUNGSICHTBARKEIT__Group__0__Impl7682); 
             after(grammarAccess.getPRUEFUNGSICHTBARKEITAccess().getPruefungSichtbarKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSICHTBARKEIT__Group__0__Impl"


    // $ANTLR start "rule__PRUEFUNGSICHTBARKEIT__Group__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3242:1: rule__PRUEFUNGSICHTBARKEIT__Group__1 : rule__PRUEFUNGSICHTBARKEIT__Group__1__Impl rule__PRUEFUNGSICHTBARKEIT__Group__2 ;
    public final void rule__PRUEFUNGSICHTBARKEIT__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3246:1: ( rule__PRUEFUNGSICHTBARKEIT__Group__1__Impl rule__PRUEFUNGSICHTBARKEIT__Group__2 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3247:2: rule__PRUEFUNGSICHTBARKEIT__Group__1__Impl rule__PRUEFUNGSICHTBARKEIT__Group__2
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSICHTBARKEIT__Group__1__Impl_in_rule__PRUEFUNGSICHTBARKEIT__Group__17713);
            rule__PRUEFUNGSICHTBARKEIT__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSICHTBARKEIT__Group__2_in_rule__PRUEFUNGSICHTBARKEIT__Group__17716);
            rule__PRUEFUNGSICHTBARKEIT__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSICHTBARKEIT__Group__1"


    // $ANTLR start "rule__PRUEFUNGSICHTBARKEIT__Group__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3254:1: rule__PRUEFUNGSICHTBARKEIT__Group__1__Impl : ( rulePRUEFUNG ) ;
    public final void rule__PRUEFUNGSICHTBARKEIT__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3258:1: ( ( rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3259:1: ( rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3259:1: ( rulePRUEFUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3260:1: rulePRUEFUNG
            {
             before(grammarAccess.getPRUEFUNGSICHTBARKEITAccess().getPRUEFUNGParserRuleCall_1()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_rule__PRUEFUNGSICHTBARKEIT__Group__1__Impl7743);
            rulePRUEFUNG();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSICHTBARKEITAccess().getPRUEFUNGParserRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSICHTBARKEIT__Group__1__Impl"


    // $ANTLR start "rule__PRUEFUNGSICHTBARKEIT__Group__2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3271:1: rule__PRUEFUNGSICHTBARKEIT__Group__2 : rule__PRUEFUNGSICHTBARKEIT__Group__2__Impl ;
    public final void rule__PRUEFUNGSICHTBARKEIT__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3275:1: ( rule__PRUEFUNGSICHTBARKEIT__Group__2__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3276:2: rule__PRUEFUNGSICHTBARKEIT__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSICHTBARKEIT__Group__2__Impl_in_rule__PRUEFUNGSICHTBARKEIT__Group__27772);
            rule__PRUEFUNGSICHTBARKEIT__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSICHTBARKEIT__Group__2"


    // $ANTLR start "rule__PRUEFUNGSICHTBARKEIT__Group__2__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3282:1: rule__PRUEFUNGSICHTBARKEIT__Group__2__Impl : ( ' =' ) ;
    public final void rule__PRUEFUNGSICHTBARKEIT__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3286:1: ( ( ' =' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3287:1: ( ' =' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3287:1: ( ' =' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3288:1: ' ='
            {
             before(grammarAccess.getPRUEFUNGSICHTBARKEITAccess().getSpaceEqualsSignKeyword_2()); 
            match(input,311,FOLLOW_311_in_rule__PRUEFUNGSICHTBARKEIT__Group__2__Impl7800); 
             after(grammarAccess.getPRUEFUNGSICHTBARKEITAccess().getSpaceEqualsSignKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSICHTBARKEIT__Group__2__Impl"


    // $ANTLR start "rule__Vwkpaktionkonfiguraktion__Group__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3307:1: rule__Vwkpaktionkonfiguraktion__Group__0 : rule__Vwkpaktionkonfiguraktion__Group__0__Impl rule__Vwkpaktionkonfiguraktion__Group__1 ;
    public final void rule__Vwkpaktionkonfiguraktion__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3311:1: ( rule__Vwkpaktionkonfiguraktion__Group__0__Impl rule__Vwkpaktionkonfiguraktion__Group__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3312:2: rule__Vwkpaktionkonfiguraktion__Group__0__Impl rule__Vwkpaktionkonfiguraktion__Group__1
            {
            pushFollow(FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__0__Impl_in_rule__Vwkpaktionkonfiguraktion__Group__07837);
            rule__Vwkpaktionkonfiguraktion__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__1_in_rule__Vwkpaktionkonfiguraktion__Group__07840);
            rule__Vwkpaktionkonfiguraktion__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vwkpaktionkonfiguraktion__Group__0"


    // $ANTLR start "rule__Vwkpaktionkonfiguraktion__Group__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3319:1: rule__Vwkpaktionkonfiguraktion__Group__0__Impl : ( 'VWKPKonfiguration.' ) ;
    public final void rule__Vwkpaktionkonfiguraktion__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3323:1: ( ( 'VWKPKonfiguration.' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3324:1: ( 'VWKPKonfiguration.' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3324:1: ( 'VWKPKonfiguration.' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3325:1: 'VWKPKonfiguration.'
            {
             before(grammarAccess.getVwkpaktionkonfiguraktionAccess().getVWKPKonfigurationKeyword_0()); 
            match(input,312,FOLLOW_312_in_rule__Vwkpaktionkonfiguraktion__Group__0__Impl7868); 
             after(grammarAccess.getVwkpaktionkonfiguraktionAccess().getVWKPKonfigurationKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vwkpaktionkonfiguraktion__Group__0__Impl"


    // $ANTLR start "rule__Vwkpaktionkonfiguraktion__Group__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3338:1: rule__Vwkpaktionkonfiguraktion__Group__1 : rule__Vwkpaktionkonfiguraktion__Group__1__Impl rule__Vwkpaktionkonfiguraktion__Group__2 ;
    public final void rule__Vwkpaktionkonfiguraktion__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3342:1: ( rule__Vwkpaktionkonfiguraktion__Group__1__Impl rule__Vwkpaktionkonfiguraktion__Group__2 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3343:2: rule__Vwkpaktionkonfiguraktion__Group__1__Impl rule__Vwkpaktionkonfiguraktion__Group__2
            {
            pushFollow(FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__1__Impl_in_rule__Vwkpaktionkonfiguraktion__Group__17899);
            rule__Vwkpaktionkonfiguraktion__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__2_in_rule__Vwkpaktionkonfiguraktion__Group__17902);
            rule__Vwkpaktionkonfiguraktion__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vwkpaktionkonfiguraktion__Group__1"


    // $ANTLR start "rule__Vwkpaktionkonfiguraktion__Group__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3350:1: rule__Vwkpaktionkonfiguraktion__Group__1__Impl : ( ruleVWKPTYP ) ;
    public final void rule__Vwkpaktionkonfiguraktion__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3354:1: ( ( ruleVWKPTYP ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3355:1: ( ruleVWKPTYP )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3355:1: ( ruleVWKPTYP )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3356:1: ruleVWKPTYP
            {
             before(grammarAccess.getVwkpaktionkonfiguraktionAccess().getVWKPTYPParserRuleCall_1()); 
            pushFollow(FOLLOW_ruleVWKPTYP_in_rule__Vwkpaktionkonfiguraktion__Group__1__Impl7929);
            ruleVWKPTYP();

            state._fsp--;

             after(grammarAccess.getVwkpaktionkonfiguraktionAccess().getVWKPTYPParserRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vwkpaktionkonfiguraktion__Group__1__Impl"


    // $ANTLR start "rule__Vwkpaktionkonfiguraktion__Group__2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3367:1: rule__Vwkpaktionkonfiguraktion__Group__2 : rule__Vwkpaktionkonfiguraktion__Group__2__Impl rule__Vwkpaktionkonfiguraktion__Group__3 ;
    public final void rule__Vwkpaktionkonfiguraktion__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3371:1: ( rule__Vwkpaktionkonfiguraktion__Group__2__Impl rule__Vwkpaktionkonfiguraktion__Group__3 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3372:2: rule__Vwkpaktionkonfiguraktion__Group__2__Impl rule__Vwkpaktionkonfiguraktion__Group__3
            {
            pushFollow(FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__2__Impl_in_rule__Vwkpaktionkonfiguraktion__Group__27958);
            rule__Vwkpaktionkonfiguraktion__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__3_in_rule__Vwkpaktionkonfiguraktion__Group__27961);
            rule__Vwkpaktionkonfiguraktion__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vwkpaktionkonfiguraktion__Group__2"


    // $ANTLR start "rule__Vwkpaktionkonfiguraktion__Group__2__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3379:1: rule__Vwkpaktionkonfiguraktion__Group__2__Impl : ( '.Aktion.' ) ;
    public final void rule__Vwkpaktionkonfiguraktion__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3383:1: ( ( '.Aktion.' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3384:1: ( '.Aktion.' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3384:1: ( '.Aktion.' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3385:1: '.Aktion.'
            {
             before(grammarAccess.getVwkpaktionkonfiguraktionAccess().getAktionKeyword_2()); 
            match(input,313,FOLLOW_313_in_rule__Vwkpaktionkonfiguraktion__Group__2__Impl7989); 
             after(grammarAccess.getVwkpaktionkonfiguraktionAccess().getAktionKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vwkpaktionkonfiguraktion__Group__2__Impl"


    // $ANTLR start "rule__Vwkpaktionkonfiguraktion__Group__3"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3398:1: rule__Vwkpaktionkonfiguraktion__Group__3 : rule__Vwkpaktionkonfiguraktion__Group__3__Impl rule__Vwkpaktionkonfiguraktion__Group__4 ;
    public final void rule__Vwkpaktionkonfiguraktion__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3402:1: ( rule__Vwkpaktionkonfiguraktion__Group__3__Impl rule__Vwkpaktionkonfiguraktion__Group__4 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3403:2: rule__Vwkpaktionkonfiguraktion__Group__3__Impl rule__Vwkpaktionkonfiguraktion__Group__4
            {
            pushFollow(FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__3__Impl_in_rule__Vwkpaktionkonfiguraktion__Group__38020);
            rule__Vwkpaktionkonfiguraktion__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__4_in_rule__Vwkpaktionkonfiguraktion__Group__38023);
            rule__Vwkpaktionkonfiguraktion__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vwkpaktionkonfiguraktion__Group__3"


    // $ANTLR start "rule__Vwkpaktionkonfiguraktion__Group__3__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3410:1: rule__Vwkpaktionkonfiguraktion__Group__3__Impl : ( rulePRUEFUNG ) ;
    public final void rule__Vwkpaktionkonfiguraktion__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3414:1: ( ( rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3415:1: ( rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3415:1: ( rulePRUEFUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3416:1: rulePRUEFUNG
            {
             before(grammarAccess.getVwkpaktionkonfiguraktionAccess().getPRUEFUNGParserRuleCall_3()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_rule__Vwkpaktionkonfiguraktion__Group__3__Impl8050);
            rulePRUEFUNG();

            state._fsp--;

             after(grammarAccess.getVwkpaktionkonfiguraktionAccess().getPRUEFUNGParserRuleCall_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vwkpaktionkonfiguraktion__Group__3__Impl"


    // $ANTLR start "rule__Vwkpaktionkonfiguraktion__Group__4"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3427:1: rule__Vwkpaktionkonfiguraktion__Group__4 : rule__Vwkpaktionkonfiguraktion__Group__4__Impl rule__Vwkpaktionkonfiguraktion__Group__5 ;
    public final void rule__Vwkpaktionkonfiguraktion__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3431:1: ( rule__Vwkpaktionkonfiguraktion__Group__4__Impl rule__Vwkpaktionkonfiguraktion__Group__5 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3432:2: rule__Vwkpaktionkonfiguraktion__Group__4__Impl rule__Vwkpaktionkonfiguraktion__Group__5
            {
            pushFollow(FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__4__Impl_in_rule__Vwkpaktionkonfiguraktion__Group__48079);
            rule__Vwkpaktionkonfiguraktion__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__5_in_rule__Vwkpaktionkonfiguraktion__Group__48082);
            rule__Vwkpaktionkonfiguraktion__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vwkpaktionkonfiguraktion__Group__4"


    // $ANTLR start "rule__Vwkpaktionkonfiguraktion__Group__4__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3439:1: rule__Vwkpaktionkonfiguraktion__Group__4__Impl : ( ' = ' ) ;
    public final void rule__Vwkpaktionkonfiguraktion__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3443:1: ( ( ' = ' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3444:1: ( ' = ' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3444:1: ( ' = ' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3445:1: ' = '
            {
             before(grammarAccess.getVwkpaktionkonfiguraktionAccess().getSpaceEqualsSignSpaceKeyword_4()); 
            match(input,314,FOLLOW_314_in_rule__Vwkpaktionkonfiguraktion__Group__4__Impl8110); 
             after(grammarAccess.getVwkpaktionkonfiguraktionAccess().getSpaceEqualsSignSpaceKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vwkpaktionkonfiguraktion__Group__4__Impl"


    // $ANTLR start "rule__Vwkpaktionkonfiguraktion__Group__5"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3458:1: rule__Vwkpaktionkonfiguraktion__Group__5 : rule__Vwkpaktionkonfiguraktion__Group__5__Impl ;
    public final void rule__Vwkpaktionkonfiguraktion__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3462:1: ( rule__Vwkpaktionkonfiguraktion__Group__5__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3463:2: rule__Vwkpaktionkonfiguraktion__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__5__Impl_in_rule__Vwkpaktionkonfiguraktion__Group__58141);
            rule__Vwkpaktionkonfiguraktion__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vwkpaktionkonfiguraktion__Group__5"


    // $ANTLR start "rule__Vwkpaktionkonfiguraktion__Group__5__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3469:1: rule__Vwkpaktionkonfiguraktion__Group__5__Impl : ( ruleAKTION ) ;
    public final void rule__Vwkpaktionkonfiguraktion__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3473:1: ( ( ruleAKTION ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3474:1: ( ruleAKTION )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3474:1: ( ruleAKTION )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3475:1: ruleAKTION
            {
             before(grammarAccess.getVwkpaktionkonfiguraktionAccess().getAKTIONParserRuleCall_5()); 
            pushFollow(FOLLOW_ruleAKTION_in_rule__Vwkpaktionkonfiguraktion__Group__5__Impl8168);
            ruleAKTION();

            state._fsp--;

             after(grammarAccess.getVwkpaktionkonfiguraktionAccess().getAKTIONParserRuleCall_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vwkpaktionkonfiguraktion__Group__5__Impl"


    // $ANTLR start "rule__SPEZ_ANTRAGSZUWEISUNG__Group__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3498:1: rule__SPEZ_ANTRAGSZUWEISUNG__Group__0 : rule__SPEZ_ANTRAGSZUWEISUNG__Group__0__Impl rule__SPEZ_ANTRAGSZUWEISUNG__Group__1 ;
    public final void rule__SPEZ_ANTRAGSZUWEISUNG__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3502:1: ( rule__SPEZ_ANTRAGSZUWEISUNG__Group__0__Impl rule__SPEZ_ANTRAGSZUWEISUNG__Group__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3503:2: rule__SPEZ_ANTRAGSZUWEISUNG__Group__0__Impl rule__SPEZ_ANTRAGSZUWEISUNG__Group__1
            {
            pushFollow(FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__0__Impl_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__08209);
            rule__SPEZ_ANTRAGSZUWEISUNG__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__1_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__08212);
            rule__SPEZ_ANTRAGSZUWEISUNG__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SPEZ_ANTRAGSZUWEISUNG__Group__0"


    // $ANTLR start "rule__SPEZ_ANTRAGSZUWEISUNG__Group__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3510:1: rule__SPEZ_ANTRAGSZUWEISUNG__Group__0__Impl : ( RULE_INT ) ;
    public final void rule__SPEZ_ANTRAGSZUWEISUNG__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3514:1: ( ( RULE_INT ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3515:1: ( RULE_INT )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3515:1: ( RULE_INT )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3516:1: RULE_INT
            {
             before(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getINTTerminalRuleCall_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__0__Impl8239); 
             after(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getINTTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SPEZ_ANTRAGSZUWEISUNG__Group__0__Impl"


    // $ANTLR start "rule__SPEZ_ANTRAGSZUWEISUNG__Group__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3527:1: rule__SPEZ_ANTRAGSZUWEISUNG__Group__1 : rule__SPEZ_ANTRAGSZUWEISUNG__Group__1__Impl rule__SPEZ_ANTRAGSZUWEISUNG__Group__2 ;
    public final void rule__SPEZ_ANTRAGSZUWEISUNG__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3531:1: ( rule__SPEZ_ANTRAGSZUWEISUNG__Group__1__Impl rule__SPEZ_ANTRAGSZUWEISUNG__Group__2 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3532:2: rule__SPEZ_ANTRAGSZUWEISUNG__Group__1__Impl rule__SPEZ_ANTRAGSZUWEISUNG__Group__2
            {
            pushFollow(FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__1__Impl_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__18268);
            rule__SPEZ_ANTRAGSZUWEISUNG__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__2_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__18271);
            rule__SPEZ_ANTRAGSZUWEISUNG__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SPEZ_ANTRAGSZUWEISUNG__Group__1"


    // $ANTLR start "rule__SPEZ_ANTRAGSZUWEISUNG__Group__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3539:1: rule__SPEZ_ANTRAGSZUWEISUNG__Group__1__Impl : ( ' = ' ) ;
    public final void rule__SPEZ_ANTRAGSZUWEISUNG__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3543:1: ( ( ' = ' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3544:1: ( ' = ' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3544:1: ( ' = ' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3545:1: ' = '
            {
             before(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getSpaceEqualsSignSpaceKeyword_1()); 
            match(input,314,FOLLOW_314_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__1__Impl8299); 
             after(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getSpaceEqualsSignSpaceKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SPEZ_ANTRAGSZUWEISUNG__Group__1__Impl"


    // $ANTLR start "rule__SPEZ_ANTRAGSZUWEISUNG__Group__2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3558:1: rule__SPEZ_ANTRAGSZUWEISUNG__Group__2 : rule__SPEZ_ANTRAGSZUWEISUNG__Group__2__Impl rule__SPEZ_ANTRAGSZUWEISUNG__Group__3 ;
    public final void rule__SPEZ_ANTRAGSZUWEISUNG__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3562:1: ( rule__SPEZ_ANTRAGSZUWEISUNG__Group__2__Impl rule__SPEZ_ANTRAGSZUWEISUNG__Group__3 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3563:2: rule__SPEZ_ANTRAGSZUWEISUNG__Group__2__Impl rule__SPEZ_ANTRAGSZUWEISUNG__Group__3
            {
            pushFollow(FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__2__Impl_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__28330);
            rule__SPEZ_ANTRAGSZUWEISUNG__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__3_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__28333);
            rule__SPEZ_ANTRAGSZUWEISUNG__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SPEZ_ANTRAGSZUWEISUNG__Group__2"


    // $ANTLR start "rule__SPEZ_ANTRAGSZUWEISUNG__Group__2__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3570:1: rule__SPEZ_ANTRAGSZUWEISUNG__Group__2__Impl : ( ( rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0 )* ) ;
    public final void rule__SPEZ_ANTRAGSZUWEISUNG__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3574:1: ( ( ( rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0 )* ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3575:1: ( ( rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0 )* )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3575:1: ( ( rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0 )* )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3576:1: ( rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0 )*
            {
             before(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getGroup_2()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3577:1: ( rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_INT) ) {
                    int LA10_1 = input.LA(2);

                    if ( (LA10_1==315) ) {
                        alt10=1;
                    }


                }


                switch (alt10) {
            	case 1 :
            	    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3577:2: rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__2__Impl8360);
            	    rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SPEZ_ANTRAGSZUWEISUNG__Group__2__Impl"


    // $ANTLR start "rule__SPEZ_ANTRAGSZUWEISUNG__Group__3"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3587:1: rule__SPEZ_ANTRAGSZUWEISUNG__Group__3 : rule__SPEZ_ANTRAGSZUWEISUNG__Group__3__Impl ;
    public final void rule__SPEZ_ANTRAGSZUWEISUNG__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3591:1: ( rule__SPEZ_ANTRAGSZUWEISUNG__Group__3__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3592:2: rule__SPEZ_ANTRAGSZUWEISUNG__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__3__Impl_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__38391);
            rule__SPEZ_ANTRAGSZUWEISUNG__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SPEZ_ANTRAGSZUWEISUNG__Group__3"


    // $ANTLR start "rule__SPEZ_ANTRAGSZUWEISUNG__Group__3__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3598:1: rule__SPEZ_ANTRAGSZUWEISUNG__Group__3__Impl : ( rulePRUEFUNG ) ;
    public final void rule__SPEZ_ANTRAGSZUWEISUNG__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3602:1: ( ( rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3603:1: ( rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3603:1: ( rulePRUEFUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3604:1: rulePRUEFUNG
            {
             before(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getPRUEFUNGParserRuleCall_3()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__3__Impl8418);
            rulePRUEFUNG();

            state._fsp--;

             after(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getPRUEFUNGParserRuleCall_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SPEZ_ANTRAGSZUWEISUNG__Group__3__Impl"


    // $ANTLR start "rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3623:1: rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0 : rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0__Impl rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1 ;
    public final void rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3627:1: ( rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0__Impl rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3628:2: rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0__Impl rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1
            {
            pushFollow(FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0__Impl_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__08455);
            rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__08458);
            rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0"


    // $ANTLR start "rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3635:1: rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0__Impl : ( rulePRUEFUNG ) ;
    public final void rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3639:1: ( ( rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3640:1: ( rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3640:1: ( rulePRUEFUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3641:1: rulePRUEFUNG
            {
             before(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getPRUEFUNGParserRuleCall_2_0()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0__Impl8485);
            rulePRUEFUNG();

            state._fsp--;

             after(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getPRUEFUNGParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0__Impl"


    // $ANTLR start "rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3652:1: rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1 : rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1__Impl ;
    public final void rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3656:1: ( rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3657:2: rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1__Impl_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__18514);
            rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1"


    // $ANTLR start "rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3663:1: rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1__Impl : ( ',' ) ;
    public final void rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3667:1: ( ( ',' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3668:1: ( ',' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3668:1: ( ',' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3669:1: ','
            {
             before(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getCommaKeyword_2_1()); 
            match(input,315,FOLLOW_315_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1__Impl8542); 
             after(grammarAccess.getSPEZ_ANTRAGSZUWEISUNGAccess().getCommaKeyword_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1__Impl"


    // $ANTLR start "rule__PRUEFUNGSLANGTEXT__Group__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3686:1: rule__PRUEFUNGSLANGTEXT__Group__0 : rule__PRUEFUNGSLANGTEXT__Group__0__Impl rule__PRUEFUNGSLANGTEXT__Group__1 ;
    public final void rule__PRUEFUNGSLANGTEXT__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3690:1: ( rule__PRUEFUNGSLANGTEXT__Group__0__Impl rule__PRUEFUNGSLANGTEXT__Group__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3691:2: rule__PRUEFUNGSLANGTEXT__Group__0__Impl rule__PRUEFUNGSLANGTEXT__Group__1
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__0__Impl_in_rule__PRUEFUNGSLANGTEXT__Group__08577);
            rule__PRUEFUNGSLANGTEXT__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__1_in_rule__PRUEFUNGSLANGTEXT__Group__08580);
            rule__PRUEFUNGSLANGTEXT__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSLANGTEXT__Group__0"


    // $ANTLR start "rule__PRUEFUNGSLANGTEXT__Group__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3698:1: rule__PRUEFUNGSLANGTEXT__Group__0__Impl : ( 'PruefungLangtext.' ) ;
    public final void rule__PRUEFUNGSLANGTEXT__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3702:1: ( ( 'PruefungLangtext.' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3703:1: ( 'PruefungLangtext.' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3703:1: ( 'PruefungLangtext.' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3704:1: 'PruefungLangtext.'
            {
             before(grammarAccess.getPRUEFUNGSLANGTEXTAccess().getPruefungLangtextKeyword_0()); 
            match(input,316,FOLLOW_316_in_rule__PRUEFUNGSLANGTEXT__Group__0__Impl8608); 
             after(grammarAccess.getPRUEFUNGSLANGTEXTAccess().getPruefungLangtextKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSLANGTEXT__Group__0__Impl"


    // $ANTLR start "rule__PRUEFUNGSLANGTEXT__Group__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3717:1: rule__PRUEFUNGSLANGTEXT__Group__1 : rule__PRUEFUNGSLANGTEXT__Group__1__Impl rule__PRUEFUNGSLANGTEXT__Group__2 ;
    public final void rule__PRUEFUNGSLANGTEXT__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3721:1: ( rule__PRUEFUNGSLANGTEXT__Group__1__Impl rule__PRUEFUNGSLANGTEXT__Group__2 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3722:2: rule__PRUEFUNGSLANGTEXT__Group__1__Impl rule__PRUEFUNGSLANGTEXT__Group__2
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__1__Impl_in_rule__PRUEFUNGSLANGTEXT__Group__18639);
            rule__PRUEFUNGSLANGTEXT__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__2_in_rule__PRUEFUNGSLANGTEXT__Group__18642);
            rule__PRUEFUNGSLANGTEXT__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSLANGTEXT__Group__1"


    // $ANTLR start "rule__PRUEFUNGSLANGTEXT__Group__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3729:1: rule__PRUEFUNGSLANGTEXT__Group__1__Impl : ( rulePRUEFUNG ) ;
    public final void rule__PRUEFUNGSLANGTEXT__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3733:1: ( ( rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3734:1: ( rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3734:1: ( rulePRUEFUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3735:1: rulePRUEFUNG
            {
             before(grammarAccess.getPRUEFUNGSLANGTEXTAccess().getPRUEFUNGParserRuleCall_1()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_rule__PRUEFUNGSLANGTEXT__Group__1__Impl8669);
            rulePRUEFUNG();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSLANGTEXTAccess().getPRUEFUNGParserRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSLANGTEXT__Group__1__Impl"


    // $ANTLR start "rule__PRUEFUNGSLANGTEXT__Group__2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3746:1: rule__PRUEFUNGSLANGTEXT__Group__2 : rule__PRUEFUNGSLANGTEXT__Group__2__Impl rule__PRUEFUNGSLANGTEXT__Group__3 ;
    public final void rule__PRUEFUNGSLANGTEXT__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3750:1: ( rule__PRUEFUNGSLANGTEXT__Group__2__Impl rule__PRUEFUNGSLANGTEXT__Group__3 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3751:2: rule__PRUEFUNGSLANGTEXT__Group__2__Impl rule__PRUEFUNGSLANGTEXT__Group__3
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__2__Impl_in_rule__PRUEFUNGSLANGTEXT__Group__28698);
            rule__PRUEFUNGSLANGTEXT__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__3_in_rule__PRUEFUNGSLANGTEXT__Group__28701);
            rule__PRUEFUNGSLANGTEXT__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSLANGTEXT__Group__2"


    // $ANTLR start "rule__PRUEFUNGSLANGTEXT__Group__2__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3758:1: rule__PRUEFUNGSLANGTEXT__Group__2__Impl : ( ' = ' ) ;
    public final void rule__PRUEFUNGSLANGTEXT__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3762:1: ( ( ' = ' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3763:1: ( ' = ' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3763:1: ( ' = ' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3764:1: ' = '
            {
             before(grammarAccess.getPRUEFUNGSLANGTEXTAccess().getSpaceEqualsSignSpaceKeyword_2()); 
            match(input,314,FOLLOW_314_in_rule__PRUEFUNGSLANGTEXT__Group__2__Impl8729); 
             after(grammarAccess.getPRUEFUNGSLANGTEXTAccess().getSpaceEqualsSignSpaceKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSLANGTEXT__Group__2__Impl"


    // $ANTLR start "rule__PRUEFUNGSLANGTEXT__Group__3"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3777:1: rule__PRUEFUNGSLANGTEXT__Group__3 : rule__PRUEFUNGSLANGTEXT__Group__3__Impl ;
    public final void rule__PRUEFUNGSLANGTEXT__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3781:1: ( rule__PRUEFUNGSLANGTEXT__Group__3__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3782:2: rule__PRUEFUNGSLANGTEXT__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__3__Impl_in_rule__PRUEFUNGSLANGTEXT__Group__38760);
            rule__PRUEFUNGSLANGTEXT__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSLANGTEXT__Group__3"


    // $ANTLR start "rule__PRUEFUNGSLANGTEXT__Group__3__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3788:1: rule__PRUEFUNGSLANGTEXT__Group__3__Impl : ( RULE_STRING ) ;
    public final void rule__PRUEFUNGSLANGTEXT__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3792:1: ( ( RULE_STRING ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3793:1: ( RULE_STRING )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3793:1: ( RULE_STRING )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3794:1: RULE_STRING
            {
             before(grammarAccess.getPRUEFUNGSLANGTEXTAccess().getSTRINGTerminalRuleCall_3()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__PRUEFUNGSLANGTEXT__Group__3__Impl8787); 
             after(grammarAccess.getPRUEFUNGSLANGTEXTAccess().getSTRINGTerminalRuleCall_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSLANGTEXT__Group__3__Impl"


    // $ANTLR start "rule__PRUEFUNGSKURZTEXT__Group__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3813:1: rule__PRUEFUNGSKURZTEXT__Group__0 : rule__PRUEFUNGSKURZTEXT__Group__0__Impl rule__PRUEFUNGSKURZTEXT__Group__1 ;
    public final void rule__PRUEFUNGSKURZTEXT__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3817:1: ( rule__PRUEFUNGSKURZTEXT__Group__0__Impl rule__PRUEFUNGSKURZTEXT__Group__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3818:2: rule__PRUEFUNGSKURZTEXT__Group__0__Impl rule__PRUEFUNGSKURZTEXT__Group__1
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__0__Impl_in_rule__PRUEFUNGSKURZTEXT__Group__08824);
            rule__PRUEFUNGSKURZTEXT__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__1_in_rule__PRUEFUNGSKURZTEXT__Group__08827);
            rule__PRUEFUNGSKURZTEXT__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKURZTEXT__Group__0"


    // $ANTLR start "rule__PRUEFUNGSKURZTEXT__Group__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3825:1: rule__PRUEFUNGSKURZTEXT__Group__0__Impl : ( 'PruefungKurzbezeichnung.' ) ;
    public final void rule__PRUEFUNGSKURZTEXT__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3829:1: ( ( 'PruefungKurzbezeichnung.' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3830:1: ( 'PruefungKurzbezeichnung.' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3830:1: ( 'PruefungKurzbezeichnung.' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3831:1: 'PruefungKurzbezeichnung.'
            {
             before(grammarAccess.getPRUEFUNGSKURZTEXTAccess().getPruefungKurzbezeichnungKeyword_0()); 
            match(input,317,FOLLOW_317_in_rule__PRUEFUNGSKURZTEXT__Group__0__Impl8855); 
             after(grammarAccess.getPRUEFUNGSKURZTEXTAccess().getPruefungKurzbezeichnungKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKURZTEXT__Group__0__Impl"


    // $ANTLR start "rule__PRUEFUNGSKURZTEXT__Group__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3844:1: rule__PRUEFUNGSKURZTEXT__Group__1 : rule__PRUEFUNGSKURZTEXT__Group__1__Impl rule__PRUEFUNGSKURZTEXT__Group__2 ;
    public final void rule__PRUEFUNGSKURZTEXT__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3848:1: ( rule__PRUEFUNGSKURZTEXT__Group__1__Impl rule__PRUEFUNGSKURZTEXT__Group__2 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3849:2: rule__PRUEFUNGSKURZTEXT__Group__1__Impl rule__PRUEFUNGSKURZTEXT__Group__2
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__1__Impl_in_rule__PRUEFUNGSKURZTEXT__Group__18886);
            rule__PRUEFUNGSKURZTEXT__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__2_in_rule__PRUEFUNGSKURZTEXT__Group__18889);
            rule__PRUEFUNGSKURZTEXT__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKURZTEXT__Group__1"


    // $ANTLR start "rule__PRUEFUNGSKURZTEXT__Group__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3856:1: rule__PRUEFUNGSKURZTEXT__Group__1__Impl : ( rulePRUEFUNG ) ;
    public final void rule__PRUEFUNGSKURZTEXT__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3860:1: ( ( rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3861:1: ( rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3861:1: ( rulePRUEFUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3862:1: rulePRUEFUNG
            {
             before(grammarAccess.getPRUEFUNGSKURZTEXTAccess().getPRUEFUNGParserRuleCall_1()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_rule__PRUEFUNGSKURZTEXT__Group__1__Impl8916);
            rulePRUEFUNG();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSKURZTEXTAccess().getPRUEFUNGParserRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKURZTEXT__Group__1__Impl"


    // $ANTLR start "rule__PRUEFUNGSKURZTEXT__Group__2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3873:1: rule__PRUEFUNGSKURZTEXT__Group__2 : rule__PRUEFUNGSKURZTEXT__Group__2__Impl rule__PRUEFUNGSKURZTEXT__Group__3 ;
    public final void rule__PRUEFUNGSKURZTEXT__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3877:1: ( rule__PRUEFUNGSKURZTEXT__Group__2__Impl rule__PRUEFUNGSKURZTEXT__Group__3 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3878:2: rule__PRUEFUNGSKURZTEXT__Group__2__Impl rule__PRUEFUNGSKURZTEXT__Group__3
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__2__Impl_in_rule__PRUEFUNGSKURZTEXT__Group__28945);
            rule__PRUEFUNGSKURZTEXT__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__3_in_rule__PRUEFUNGSKURZTEXT__Group__28948);
            rule__PRUEFUNGSKURZTEXT__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKURZTEXT__Group__2"


    // $ANTLR start "rule__PRUEFUNGSKURZTEXT__Group__2__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3885:1: rule__PRUEFUNGSKURZTEXT__Group__2__Impl : ( ' = ' ) ;
    public final void rule__PRUEFUNGSKURZTEXT__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3889:1: ( ( ' = ' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3890:1: ( ' = ' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3890:1: ( ' = ' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3891:1: ' = '
            {
             before(grammarAccess.getPRUEFUNGSKURZTEXTAccess().getSpaceEqualsSignSpaceKeyword_2()); 
            match(input,314,FOLLOW_314_in_rule__PRUEFUNGSKURZTEXT__Group__2__Impl8976); 
             after(grammarAccess.getPRUEFUNGSKURZTEXTAccess().getSpaceEqualsSignSpaceKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKURZTEXT__Group__2__Impl"


    // $ANTLR start "rule__PRUEFUNGSKURZTEXT__Group__3"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3904:1: rule__PRUEFUNGSKURZTEXT__Group__3 : rule__PRUEFUNGSKURZTEXT__Group__3__Impl ;
    public final void rule__PRUEFUNGSKURZTEXT__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3908:1: ( rule__PRUEFUNGSKURZTEXT__Group__3__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3909:2: rule__PRUEFUNGSKURZTEXT__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__3__Impl_in_rule__PRUEFUNGSKURZTEXT__Group__39007);
            rule__PRUEFUNGSKURZTEXT__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKURZTEXT__Group__3"


    // $ANTLR start "rule__PRUEFUNGSKURZTEXT__Group__3__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3915:1: rule__PRUEFUNGSKURZTEXT__Group__3__Impl : ( RULE_STRING ) ;
    public final void rule__PRUEFUNGSKURZTEXT__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3919:1: ( ( RULE_STRING ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3920:1: ( RULE_STRING )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3920:1: ( RULE_STRING )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3921:1: RULE_STRING
            {
             before(grammarAccess.getPRUEFUNGSKURZTEXTAccess().getSTRINGTerminalRuleCall_3()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__PRUEFUNGSKURZTEXT__Group__3__Impl9034); 
             after(grammarAccess.getPRUEFUNGSKURZTEXTAccess().getSTRINGTerminalRuleCall_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKURZTEXT__Group__3__Impl"


    // $ANTLR start "rule__PRUEFUNGSKLASSENNAME__Group__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3940:1: rule__PRUEFUNGSKLASSENNAME__Group__0 : rule__PRUEFUNGSKLASSENNAME__Group__0__Impl rule__PRUEFUNGSKLASSENNAME__Group__1 ;
    public final void rule__PRUEFUNGSKLASSENNAME__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3944:1: ( rule__PRUEFUNGSKLASSENNAME__Group__0__Impl rule__PRUEFUNGSKLASSENNAME__Group__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3945:2: rule__PRUEFUNGSKLASSENNAME__Group__0__Impl rule__PRUEFUNGSKLASSENNAME__Group__1
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__0__Impl_in_rule__PRUEFUNGSKLASSENNAME__Group__09071);
            rule__PRUEFUNGSKLASSENNAME__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__1_in_rule__PRUEFUNGSKLASSENNAME__Group__09074);
            rule__PRUEFUNGSKLASSENNAME__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKLASSENNAME__Group__0"


    // $ANTLR start "rule__PRUEFUNGSKLASSENNAME__Group__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3952:1: rule__PRUEFUNGSKLASSENNAME__Group__0__Impl : ( 'PruefungKlassenname.' ) ;
    public final void rule__PRUEFUNGSKLASSENNAME__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3956:1: ( ( 'PruefungKlassenname.' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3957:1: ( 'PruefungKlassenname.' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3957:1: ( 'PruefungKlassenname.' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3958:1: 'PruefungKlassenname.'
            {
             before(grammarAccess.getPRUEFUNGSKLASSENNAMEAccess().getPruefungKlassennameKeyword_0()); 
            match(input,318,FOLLOW_318_in_rule__PRUEFUNGSKLASSENNAME__Group__0__Impl9102); 
             after(grammarAccess.getPRUEFUNGSKLASSENNAMEAccess().getPruefungKlassennameKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKLASSENNAME__Group__0__Impl"


    // $ANTLR start "rule__PRUEFUNGSKLASSENNAME__Group__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3971:1: rule__PRUEFUNGSKLASSENNAME__Group__1 : rule__PRUEFUNGSKLASSENNAME__Group__1__Impl rule__PRUEFUNGSKLASSENNAME__Group__2 ;
    public final void rule__PRUEFUNGSKLASSENNAME__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3975:1: ( rule__PRUEFUNGSKLASSENNAME__Group__1__Impl rule__PRUEFUNGSKLASSENNAME__Group__2 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3976:2: rule__PRUEFUNGSKLASSENNAME__Group__1__Impl rule__PRUEFUNGSKLASSENNAME__Group__2
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__1__Impl_in_rule__PRUEFUNGSKLASSENNAME__Group__19133);
            rule__PRUEFUNGSKLASSENNAME__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__2_in_rule__PRUEFUNGSKLASSENNAME__Group__19136);
            rule__PRUEFUNGSKLASSENNAME__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKLASSENNAME__Group__1"


    // $ANTLR start "rule__PRUEFUNGSKLASSENNAME__Group__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3983:1: rule__PRUEFUNGSKLASSENNAME__Group__1__Impl : ( rulePRUEFUNG ) ;
    public final void rule__PRUEFUNGSKLASSENNAME__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3987:1: ( ( rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3988:1: ( rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3988:1: ( rulePRUEFUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:3989:1: rulePRUEFUNG
            {
             before(grammarAccess.getPRUEFUNGSKLASSENNAMEAccess().getPRUEFUNGParserRuleCall_1()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_rule__PRUEFUNGSKLASSENNAME__Group__1__Impl9163);
            rulePRUEFUNG();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSKLASSENNAMEAccess().getPRUEFUNGParserRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKLASSENNAME__Group__1__Impl"


    // $ANTLR start "rule__PRUEFUNGSKLASSENNAME__Group__2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4000:1: rule__PRUEFUNGSKLASSENNAME__Group__2 : rule__PRUEFUNGSKLASSENNAME__Group__2__Impl rule__PRUEFUNGSKLASSENNAME__Group__3 ;
    public final void rule__PRUEFUNGSKLASSENNAME__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4004:1: ( rule__PRUEFUNGSKLASSENNAME__Group__2__Impl rule__PRUEFUNGSKLASSENNAME__Group__3 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4005:2: rule__PRUEFUNGSKLASSENNAME__Group__2__Impl rule__PRUEFUNGSKLASSENNAME__Group__3
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__2__Impl_in_rule__PRUEFUNGSKLASSENNAME__Group__29192);
            rule__PRUEFUNGSKLASSENNAME__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__3_in_rule__PRUEFUNGSKLASSENNAME__Group__29195);
            rule__PRUEFUNGSKLASSENNAME__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKLASSENNAME__Group__2"


    // $ANTLR start "rule__PRUEFUNGSKLASSENNAME__Group__2__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4012:1: rule__PRUEFUNGSKLASSENNAME__Group__2__Impl : ( ' = ' ) ;
    public final void rule__PRUEFUNGSKLASSENNAME__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4016:1: ( ( ' = ' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4017:1: ( ' = ' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4017:1: ( ' = ' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4018:1: ' = '
            {
             before(grammarAccess.getPRUEFUNGSKLASSENNAMEAccess().getSpaceEqualsSignSpaceKeyword_2()); 
            match(input,314,FOLLOW_314_in_rule__PRUEFUNGSKLASSENNAME__Group__2__Impl9223); 
             after(grammarAccess.getPRUEFUNGSKLASSENNAMEAccess().getSpaceEqualsSignSpaceKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKLASSENNAME__Group__2__Impl"


    // $ANTLR start "rule__PRUEFUNGSKLASSENNAME__Group__3"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4031:1: rule__PRUEFUNGSKLASSENNAME__Group__3 : rule__PRUEFUNGSKLASSENNAME__Group__3__Impl ;
    public final void rule__PRUEFUNGSKLASSENNAME__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4035:1: ( rule__PRUEFUNGSKLASSENNAME__Group__3__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4036:2: rule__PRUEFUNGSKLASSENNAME__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__3__Impl_in_rule__PRUEFUNGSKLASSENNAME__Group__39254);
            rule__PRUEFUNGSKLASSENNAME__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKLASSENNAME__Group__3"


    // $ANTLR start "rule__PRUEFUNGSKLASSENNAME__Group__3__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4042:1: rule__PRUEFUNGSKLASSENNAME__Group__3__Impl : ( ruleKLASSE ) ;
    public final void rule__PRUEFUNGSKLASSENNAME__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4046:1: ( ( ruleKLASSE ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4047:1: ( ruleKLASSE )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4047:1: ( ruleKLASSE )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4048:1: ruleKLASSE
            {
             before(grammarAccess.getPRUEFUNGSKLASSENNAMEAccess().getKLASSEParserRuleCall_3()); 
            pushFollow(FOLLOW_ruleKLASSE_in_rule__PRUEFUNGSKLASSENNAME__Group__3__Impl9281);
            ruleKLASSE();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSKLASSENNAMEAccess().getKLASSEParserRuleCall_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSKLASSENNAME__Group__3__Impl"


    // $ANTLR start "rule__PRUEFUNGSAKTION__Group__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4067:1: rule__PRUEFUNGSAKTION__Group__0 : rule__PRUEFUNGSAKTION__Group__0__Impl rule__PRUEFUNGSAKTION__Group__1 ;
    public final void rule__PRUEFUNGSAKTION__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4071:1: ( rule__PRUEFUNGSAKTION__Group__0__Impl rule__PRUEFUNGSAKTION__Group__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4072:2: rule__PRUEFUNGSAKTION__Group__0__Impl rule__PRUEFUNGSAKTION__Group__1
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSAKTION__Group__0__Impl_in_rule__PRUEFUNGSAKTION__Group__09318);
            rule__PRUEFUNGSAKTION__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSAKTION__Group__1_in_rule__PRUEFUNGSAKTION__Group__09321);
            rule__PRUEFUNGSAKTION__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSAKTION__Group__0"


    // $ANTLR start "rule__PRUEFUNGSAKTION__Group__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4079:1: rule__PRUEFUNGSAKTION__Group__0__Impl : ( 'PruefungAktion.' ) ;
    public final void rule__PRUEFUNGSAKTION__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4083:1: ( ( 'PruefungAktion.' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4084:1: ( 'PruefungAktion.' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4084:1: ( 'PruefungAktion.' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4085:1: 'PruefungAktion.'
            {
             before(grammarAccess.getPRUEFUNGSAKTIONAccess().getPruefungAktionKeyword_0()); 
            match(input,319,FOLLOW_319_in_rule__PRUEFUNGSAKTION__Group__0__Impl9349); 
             after(grammarAccess.getPRUEFUNGSAKTIONAccess().getPruefungAktionKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSAKTION__Group__0__Impl"


    // $ANTLR start "rule__PRUEFUNGSAKTION__Group__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4098:1: rule__PRUEFUNGSAKTION__Group__1 : rule__PRUEFUNGSAKTION__Group__1__Impl rule__PRUEFUNGSAKTION__Group__2 ;
    public final void rule__PRUEFUNGSAKTION__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4102:1: ( rule__PRUEFUNGSAKTION__Group__1__Impl rule__PRUEFUNGSAKTION__Group__2 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4103:2: rule__PRUEFUNGSAKTION__Group__1__Impl rule__PRUEFUNGSAKTION__Group__2
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSAKTION__Group__1__Impl_in_rule__PRUEFUNGSAKTION__Group__19380);
            rule__PRUEFUNGSAKTION__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSAKTION__Group__2_in_rule__PRUEFUNGSAKTION__Group__19383);
            rule__PRUEFUNGSAKTION__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSAKTION__Group__1"


    // $ANTLR start "rule__PRUEFUNGSAKTION__Group__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4110:1: rule__PRUEFUNGSAKTION__Group__1__Impl : ( ruleAKTIONSID ) ;
    public final void rule__PRUEFUNGSAKTION__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4114:1: ( ( ruleAKTIONSID ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4115:1: ( ruleAKTIONSID )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4115:1: ( ruleAKTIONSID )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4116:1: ruleAKTIONSID
            {
             before(grammarAccess.getPRUEFUNGSAKTIONAccess().getAKTIONSIDParserRuleCall_1()); 
            pushFollow(FOLLOW_ruleAKTIONSID_in_rule__PRUEFUNGSAKTION__Group__1__Impl9410);
            ruleAKTIONSID();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSAKTIONAccess().getAKTIONSIDParserRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSAKTION__Group__1__Impl"


    // $ANTLR start "rule__PRUEFUNGSAKTION__Group__2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4127:1: rule__PRUEFUNGSAKTION__Group__2 : rule__PRUEFUNGSAKTION__Group__2__Impl rule__PRUEFUNGSAKTION__Group__3 ;
    public final void rule__PRUEFUNGSAKTION__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4131:1: ( rule__PRUEFUNGSAKTION__Group__2__Impl rule__PRUEFUNGSAKTION__Group__3 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4132:2: rule__PRUEFUNGSAKTION__Group__2__Impl rule__PRUEFUNGSAKTION__Group__3
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSAKTION__Group__2__Impl_in_rule__PRUEFUNGSAKTION__Group__29439);
            rule__PRUEFUNGSAKTION__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSAKTION__Group__3_in_rule__PRUEFUNGSAKTION__Group__29442);
            rule__PRUEFUNGSAKTION__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSAKTION__Group__2"


    // $ANTLR start "rule__PRUEFUNGSAKTION__Group__2__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4139:1: rule__PRUEFUNGSAKTION__Group__2__Impl : ( ' = ' ) ;
    public final void rule__PRUEFUNGSAKTION__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4143:1: ( ( ' = ' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4144:1: ( ' = ' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4144:1: ( ' = ' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4145:1: ' = '
            {
             before(grammarAccess.getPRUEFUNGSAKTIONAccess().getSpaceEqualsSignSpaceKeyword_2()); 
            match(input,314,FOLLOW_314_in_rule__PRUEFUNGSAKTION__Group__2__Impl9470); 
             after(grammarAccess.getPRUEFUNGSAKTIONAccess().getSpaceEqualsSignSpaceKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSAKTION__Group__2__Impl"


    // $ANTLR start "rule__PRUEFUNGSAKTION__Group__3"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4158:1: rule__PRUEFUNGSAKTION__Group__3 : rule__PRUEFUNGSAKTION__Group__3__Impl ;
    public final void rule__PRUEFUNGSAKTION__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4162:1: ( rule__PRUEFUNGSAKTION__Group__3__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4163:2: rule__PRUEFUNGSAKTION__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSAKTION__Group__3__Impl_in_rule__PRUEFUNGSAKTION__Group__39501);
            rule__PRUEFUNGSAKTION__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSAKTION__Group__3"


    // $ANTLR start "rule__PRUEFUNGSAKTION__Group__3__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4169:1: rule__PRUEFUNGSAKTION__Group__3__Impl : ( ruleAKTION ) ;
    public final void rule__PRUEFUNGSAKTION__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4173:1: ( ( ruleAKTION ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4174:1: ( ruleAKTION )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4174:1: ( ruleAKTION )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4175:1: ruleAKTION
            {
             before(grammarAccess.getPRUEFUNGSAKTIONAccess().getAKTIONParserRuleCall_3()); 
            pushFollow(FOLLOW_ruleAKTION_in_rule__PRUEFUNGSAKTION__Group__3__Impl9528);
            ruleAKTION();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSAKTIONAccess().getAKTIONParserRuleCall_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSAKTION__Group__3__Impl"


    // $ANTLR start "rule__AKTIONSID__Group__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4194:1: rule__AKTIONSID__Group__0 : rule__AKTIONSID__Group__0__Impl rule__AKTIONSID__Group__1 ;
    public final void rule__AKTIONSID__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4198:1: ( rule__AKTIONSID__Group__0__Impl rule__AKTIONSID__Group__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4199:2: rule__AKTIONSID__Group__0__Impl rule__AKTIONSID__Group__1
            {
            pushFollow(FOLLOW_rule__AKTIONSID__Group__0__Impl_in_rule__AKTIONSID__Group__09565);
            rule__AKTIONSID__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AKTIONSID__Group__1_in_rule__AKTIONSID__Group__09568);
            rule__AKTIONSID__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AKTIONSID__Group__0"


    // $ANTLR start "rule__AKTIONSID__Group__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4206:1: rule__AKTIONSID__Group__0__Impl : ( rulePRUEFUNG ) ;
    public final void rule__AKTIONSID__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4210:1: ( ( rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4211:1: ( rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4211:1: ( rulePRUEFUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4212:1: rulePRUEFUNG
            {
             before(grammarAccess.getAKTIONSIDAccess().getPRUEFUNGParserRuleCall_0()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_rule__AKTIONSID__Group__0__Impl9595);
            rulePRUEFUNG();

            state._fsp--;

             after(grammarAccess.getAKTIONSIDAccess().getPRUEFUNGParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AKTIONSID__Group__0__Impl"


    // $ANTLR start "rule__AKTIONSID__Group__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4223:1: rule__AKTIONSID__Group__1 : rule__AKTIONSID__Group__1__Impl rule__AKTIONSID__Group__2 ;
    public final void rule__AKTIONSID__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4227:1: ( rule__AKTIONSID__Group__1__Impl rule__AKTIONSID__Group__2 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4228:2: rule__AKTIONSID__Group__1__Impl rule__AKTIONSID__Group__2
            {
            pushFollow(FOLLOW_rule__AKTIONSID__Group__1__Impl_in_rule__AKTIONSID__Group__19624);
            rule__AKTIONSID__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AKTIONSID__Group__2_in_rule__AKTIONSID__Group__19627);
            rule__AKTIONSID__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AKTIONSID__Group__1"


    // $ANTLR start "rule__AKTIONSID__Group__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4235:1: rule__AKTIONSID__Group__1__Impl : ( '.' ) ;
    public final void rule__AKTIONSID__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4239:1: ( ( '.' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4240:1: ( '.' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4240:1: ( '.' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4241:1: '.'
            {
             before(grammarAccess.getAKTIONSIDAccess().getFullStopKeyword_1()); 
            match(input,320,FOLLOW_320_in_rule__AKTIONSID__Group__1__Impl9655); 
             after(grammarAccess.getAKTIONSIDAccess().getFullStopKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AKTIONSID__Group__1__Impl"


    // $ANTLR start "rule__AKTIONSID__Group__2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4254:1: rule__AKTIONSID__Group__2 : rule__AKTIONSID__Group__2__Impl ;
    public final void rule__AKTIONSID__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4258:1: ( rule__AKTIONSID__Group__2__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4259:2: rule__AKTIONSID__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__AKTIONSID__Group__2__Impl_in_rule__AKTIONSID__Group__29686);
            rule__AKTIONSID__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AKTIONSID__Group__2"


    // $ANTLR start "rule__AKTIONSID__Group__2__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4265:1: rule__AKTIONSID__Group__2__Impl : ( RULE_INT ) ;
    public final void rule__AKTIONSID__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4269:1: ( ( RULE_INT ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4270:1: ( RULE_INT )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4270:1: ( RULE_INT )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4271:1: RULE_INT
            {
             before(grammarAccess.getAKTIONSIDAccess().getINTTerminalRuleCall_2()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__AKTIONSID__Group__2__Impl9713); 
             after(grammarAccess.getAKTIONSIDAccess().getINTTerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AKTIONSID__Group__2__Impl"


    // $ANTLR start "rule__PRUEFUNGSWIRKUNG__Group__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4288:1: rule__PRUEFUNGSWIRKUNG__Group__0 : rule__PRUEFUNGSWIRKUNG__Group__0__Impl rule__PRUEFUNGSWIRKUNG__Group__1 ;
    public final void rule__PRUEFUNGSWIRKUNG__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4292:1: ( rule__PRUEFUNGSWIRKUNG__Group__0__Impl rule__PRUEFUNGSWIRKUNG__Group__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4293:2: rule__PRUEFUNGSWIRKUNG__Group__0__Impl rule__PRUEFUNGSWIRKUNG__Group__1
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__0__Impl_in_rule__PRUEFUNGSWIRKUNG__Group__09748);
            rule__PRUEFUNGSWIRKUNG__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__1_in_rule__PRUEFUNGSWIRKUNG__Group__09751);
            rule__PRUEFUNGSWIRKUNG__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSWIRKUNG__Group__0"


    // $ANTLR start "rule__PRUEFUNGSWIRKUNG__Group__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4300:1: rule__PRUEFUNGSWIRKUNG__Group__0__Impl : ( 'PruefungWirkung.' ) ;
    public final void rule__PRUEFUNGSWIRKUNG__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4304:1: ( ( 'PruefungWirkung.' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4305:1: ( 'PruefungWirkung.' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4305:1: ( 'PruefungWirkung.' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4306:1: 'PruefungWirkung.'
            {
             before(grammarAccess.getPRUEFUNGSWIRKUNGAccess().getPruefungWirkungKeyword_0()); 
            match(input,321,FOLLOW_321_in_rule__PRUEFUNGSWIRKUNG__Group__0__Impl9779); 
             after(grammarAccess.getPRUEFUNGSWIRKUNGAccess().getPruefungWirkungKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSWIRKUNG__Group__0__Impl"


    // $ANTLR start "rule__PRUEFUNGSWIRKUNG__Group__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4319:1: rule__PRUEFUNGSWIRKUNG__Group__1 : rule__PRUEFUNGSWIRKUNG__Group__1__Impl rule__PRUEFUNGSWIRKUNG__Group__2 ;
    public final void rule__PRUEFUNGSWIRKUNG__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4323:1: ( rule__PRUEFUNGSWIRKUNG__Group__1__Impl rule__PRUEFUNGSWIRKUNG__Group__2 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4324:2: rule__PRUEFUNGSWIRKUNG__Group__1__Impl rule__PRUEFUNGSWIRKUNG__Group__2
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__1__Impl_in_rule__PRUEFUNGSWIRKUNG__Group__19810);
            rule__PRUEFUNGSWIRKUNG__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__2_in_rule__PRUEFUNGSWIRKUNG__Group__19813);
            rule__PRUEFUNGSWIRKUNG__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSWIRKUNG__Group__1"


    // $ANTLR start "rule__PRUEFUNGSWIRKUNG__Group__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4331:1: rule__PRUEFUNGSWIRKUNG__Group__1__Impl : ( ruleWIRKUNGSID ) ;
    public final void rule__PRUEFUNGSWIRKUNG__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4335:1: ( ( ruleWIRKUNGSID ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4336:1: ( ruleWIRKUNGSID )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4336:1: ( ruleWIRKUNGSID )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4337:1: ruleWIRKUNGSID
            {
             before(grammarAccess.getPRUEFUNGSWIRKUNGAccess().getWIRKUNGSIDParserRuleCall_1()); 
            pushFollow(FOLLOW_ruleWIRKUNGSID_in_rule__PRUEFUNGSWIRKUNG__Group__1__Impl9840);
            ruleWIRKUNGSID();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSWIRKUNGAccess().getWIRKUNGSIDParserRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSWIRKUNG__Group__1__Impl"


    // $ANTLR start "rule__PRUEFUNGSWIRKUNG__Group__2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4348:1: rule__PRUEFUNGSWIRKUNG__Group__2 : rule__PRUEFUNGSWIRKUNG__Group__2__Impl rule__PRUEFUNGSWIRKUNG__Group__3 ;
    public final void rule__PRUEFUNGSWIRKUNG__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4352:1: ( rule__PRUEFUNGSWIRKUNG__Group__2__Impl rule__PRUEFUNGSWIRKUNG__Group__3 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4353:2: rule__PRUEFUNGSWIRKUNG__Group__2__Impl rule__PRUEFUNGSWIRKUNG__Group__3
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__2__Impl_in_rule__PRUEFUNGSWIRKUNG__Group__29869);
            rule__PRUEFUNGSWIRKUNG__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__3_in_rule__PRUEFUNGSWIRKUNG__Group__29872);
            rule__PRUEFUNGSWIRKUNG__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSWIRKUNG__Group__2"


    // $ANTLR start "rule__PRUEFUNGSWIRKUNG__Group__2__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4360:1: rule__PRUEFUNGSWIRKUNG__Group__2__Impl : ( ' = ' ) ;
    public final void rule__PRUEFUNGSWIRKUNG__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4364:1: ( ( ' = ' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4365:1: ( ' = ' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4365:1: ( ' = ' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4366:1: ' = '
            {
             before(grammarAccess.getPRUEFUNGSWIRKUNGAccess().getSpaceEqualsSignSpaceKeyword_2()); 
            match(input,314,FOLLOW_314_in_rule__PRUEFUNGSWIRKUNG__Group__2__Impl9900); 
             after(grammarAccess.getPRUEFUNGSWIRKUNGAccess().getSpaceEqualsSignSpaceKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSWIRKUNG__Group__2__Impl"


    // $ANTLR start "rule__PRUEFUNGSWIRKUNG__Group__3"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4379:1: rule__PRUEFUNGSWIRKUNG__Group__3 : rule__PRUEFUNGSWIRKUNG__Group__3__Impl ;
    public final void rule__PRUEFUNGSWIRKUNG__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4383:1: ( rule__PRUEFUNGSWIRKUNG__Group__3__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4384:2: rule__PRUEFUNGSWIRKUNG__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__3__Impl_in_rule__PRUEFUNGSWIRKUNG__Group__39931);
            rule__PRUEFUNGSWIRKUNG__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSWIRKUNG__Group__3"


    // $ANTLR start "rule__PRUEFUNGSWIRKUNG__Group__3__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4390:1: rule__PRUEFUNGSWIRKUNG__Group__3__Impl : ( ruleWIRKUNG ) ;
    public final void rule__PRUEFUNGSWIRKUNG__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4394:1: ( ( ruleWIRKUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4395:1: ( ruleWIRKUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4395:1: ( ruleWIRKUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4396:1: ruleWIRKUNG
            {
             before(grammarAccess.getPRUEFUNGSWIRKUNGAccess().getWIRKUNGParserRuleCall_3()); 
            pushFollow(FOLLOW_ruleWIRKUNG_in_rule__PRUEFUNGSWIRKUNG__Group__3__Impl9958);
            ruleWIRKUNG();

            state._fsp--;

             after(grammarAccess.getPRUEFUNGSWIRKUNGAccess().getWIRKUNGParserRuleCall_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PRUEFUNGSWIRKUNG__Group__3__Impl"


    // $ANTLR start "rule__WIRKUNGSID__Group__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4415:1: rule__WIRKUNGSID__Group__0 : rule__WIRKUNGSID__Group__0__Impl rule__WIRKUNGSID__Group__1 ;
    public final void rule__WIRKUNGSID__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4419:1: ( rule__WIRKUNGSID__Group__0__Impl rule__WIRKUNGSID__Group__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4420:2: rule__WIRKUNGSID__Group__0__Impl rule__WIRKUNGSID__Group__1
            {
            pushFollow(FOLLOW_rule__WIRKUNGSID__Group__0__Impl_in_rule__WIRKUNGSID__Group__09995);
            rule__WIRKUNGSID__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WIRKUNGSID__Group__1_in_rule__WIRKUNGSID__Group__09998);
            rule__WIRKUNGSID__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WIRKUNGSID__Group__0"


    // $ANTLR start "rule__WIRKUNGSID__Group__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4427:1: rule__WIRKUNGSID__Group__0__Impl : ( rulePRUEFUNG ) ;
    public final void rule__WIRKUNGSID__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4431:1: ( ( rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4432:1: ( rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4432:1: ( rulePRUEFUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4433:1: rulePRUEFUNG
            {
             before(grammarAccess.getWIRKUNGSIDAccess().getPRUEFUNGParserRuleCall_0()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_rule__WIRKUNGSID__Group__0__Impl10025);
            rulePRUEFUNG();

            state._fsp--;

             after(grammarAccess.getWIRKUNGSIDAccess().getPRUEFUNGParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WIRKUNGSID__Group__0__Impl"


    // $ANTLR start "rule__WIRKUNGSID__Group__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4444:1: rule__WIRKUNGSID__Group__1 : rule__WIRKUNGSID__Group__1__Impl rule__WIRKUNGSID__Group__2 ;
    public final void rule__WIRKUNGSID__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4448:1: ( rule__WIRKUNGSID__Group__1__Impl rule__WIRKUNGSID__Group__2 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4449:2: rule__WIRKUNGSID__Group__1__Impl rule__WIRKUNGSID__Group__2
            {
            pushFollow(FOLLOW_rule__WIRKUNGSID__Group__1__Impl_in_rule__WIRKUNGSID__Group__110054);
            rule__WIRKUNGSID__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__WIRKUNGSID__Group__2_in_rule__WIRKUNGSID__Group__110057);
            rule__WIRKUNGSID__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WIRKUNGSID__Group__1"


    // $ANTLR start "rule__WIRKUNGSID__Group__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4456:1: rule__WIRKUNGSID__Group__1__Impl : ( '.' ) ;
    public final void rule__WIRKUNGSID__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4460:1: ( ( '.' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4461:1: ( '.' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4461:1: ( '.' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4462:1: '.'
            {
             before(grammarAccess.getWIRKUNGSIDAccess().getFullStopKeyword_1()); 
            match(input,320,FOLLOW_320_in_rule__WIRKUNGSID__Group__1__Impl10085); 
             after(grammarAccess.getWIRKUNGSIDAccess().getFullStopKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WIRKUNGSID__Group__1__Impl"


    // $ANTLR start "rule__WIRKUNGSID__Group__2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4475:1: rule__WIRKUNGSID__Group__2 : rule__WIRKUNGSID__Group__2__Impl ;
    public final void rule__WIRKUNGSID__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4479:1: ( rule__WIRKUNGSID__Group__2__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4480:2: rule__WIRKUNGSID__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__WIRKUNGSID__Group__2__Impl_in_rule__WIRKUNGSID__Group__210116);
            rule__WIRKUNGSID__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WIRKUNGSID__Group__2"


    // $ANTLR start "rule__WIRKUNGSID__Group__2__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4486:1: rule__WIRKUNGSID__Group__2__Impl : ( RULE_INT ) ;
    public final void rule__WIRKUNGSID__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4490:1: ( ( RULE_INT ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4491:1: ( RULE_INT )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4491:1: ( RULE_INT )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4492:1: RULE_INT
            {
             before(grammarAccess.getWIRKUNGSIDAccess().getINTTerminalRuleCall_2()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__WIRKUNGSID__Group__2__Impl10143); 
             after(grammarAccess.getWIRKUNGSIDAccess().getINTTerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WIRKUNGSID__Group__2__Impl"


    // $ANTLR start "rule__Zuweisung__Group__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4509:1: rule__Zuweisung__Group__0 : rule__Zuweisung__Group__0__Impl rule__Zuweisung__Group__1 ;
    public final void rule__Zuweisung__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4513:1: ( rule__Zuweisung__Group__0__Impl rule__Zuweisung__Group__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4514:2: rule__Zuweisung__Group__0__Impl rule__Zuweisung__Group__1
            {
            pushFollow(FOLLOW_rule__Zuweisung__Group__0__Impl_in_rule__Zuweisung__Group__010178);
            rule__Zuweisung__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Zuweisung__Group__1_in_rule__Zuweisung__Group__010181);
            rule__Zuweisung__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Zuweisung__Group__0"


    // $ANTLR start "rule__Zuweisung__Group__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4521:1: rule__Zuweisung__Group__0__Impl : ( 'DvAntragsArt.' ) ;
    public final void rule__Zuweisung__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4525:1: ( ( 'DvAntragsArt.' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4526:1: ( 'DvAntragsArt.' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4526:1: ( 'DvAntragsArt.' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4527:1: 'DvAntragsArt.'
            {
             before(grammarAccess.getZuweisungAccess().getDvAntragsArtKeyword_0()); 
            match(input,322,FOLLOW_322_in_rule__Zuweisung__Group__0__Impl10209); 
             after(grammarAccess.getZuweisungAccess().getDvAntragsArtKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Zuweisung__Group__0__Impl"


    // $ANTLR start "rule__Zuweisung__Group__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4540:1: rule__Zuweisung__Group__1 : rule__Zuweisung__Group__1__Impl rule__Zuweisung__Group__2 ;
    public final void rule__Zuweisung__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4544:1: ( rule__Zuweisung__Group__1__Impl rule__Zuweisung__Group__2 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4545:2: rule__Zuweisung__Group__1__Impl rule__Zuweisung__Group__2
            {
            pushFollow(FOLLOW_rule__Zuweisung__Group__1__Impl_in_rule__Zuweisung__Group__110240);
            rule__Zuweisung__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Zuweisung__Group__2_in_rule__Zuweisung__Group__110243);
            rule__Zuweisung__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Zuweisung__Group__1"


    // $ANTLR start "rule__Zuweisung__Group__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4552:1: rule__Zuweisung__Group__1__Impl : ( ruleANTRAGSART ) ;
    public final void rule__Zuweisung__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4556:1: ( ( ruleANTRAGSART ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4557:1: ( ruleANTRAGSART )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4557:1: ( ruleANTRAGSART )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4558:1: ruleANTRAGSART
            {
             before(grammarAccess.getZuweisungAccess().getANTRAGSARTParserRuleCall_1()); 
            pushFollow(FOLLOW_ruleANTRAGSART_in_rule__Zuweisung__Group__1__Impl10270);
            ruleANTRAGSART();

            state._fsp--;

             after(grammarAccess.getZuweisungAccess().getANTRAGSARTParserRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Zuweisung__Group__1__Impl"


    // $ANTLR start "rule__Zuweisung__Group__2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4569:1: rule__Zuweisung__Group__2 : rule__Zuweisung__Group__2__Impl rule__Zuweisung__Group__3 ;
    public final void rule__Zuweisung__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4573:1: ( rule__Zuweisung__Group__2__Impl rule__Zuweisung__Group__3 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4574:2: rule__Zuweisung__Group__2__Impl rule__Zuweisung__Group__3
            {
            pushFollow(FOLLOW_rule__Zuweisung__Group__2__Impl_in_rule__Zuweisung__Group__210299);
            rule__Zuweisung__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Zuweisung__Group__3_in_rule__Zuweisung__Group__210302);
            rule__Zuweisung__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Zuweisung__Group__2"


    // $ANTLR start "rule__Zuweisung__Group__2__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4581:1: rule__Zuweisung__Group__2__Impl : ( ' = ' ) ;
    public final void rule__Zuweisung__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4585:1: ( ( ' = ' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4586:1: ( ' = ' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4586:1: ( ' = ' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4587:1: ' = '
            {
             before(grammarAccess.getZuweisungAccess().getSpaceEqualsSignSpaceKeyword_2()); 
            match(input,314,FOLLOW_314_in_rule__Zuweisung__Group__2__Impl10330); 
             after(grammarAccess.getZuweisungAccess().getSpaceEqualsSignSpaceKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Zuweisung__Group__2__Impl"


    // $ANTLR start "rule__Zuweisung__Group__3"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4600:1: rule__Zuweisung__Group__3 : rule__Zuweisung__Group__3__Impl rule__Zuweisung__Group__4 ;
    public final void rule__Zuweisung__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4604:1: ( rule__Zuweisung__Group__3__Impl rule__Zuweisung__Group__4 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4605:2: rule__Zuweisung__Group__3__Impl rule__Zuweisung__Group__4
            {
            pushFollow(FOLLOW_rule__Zuweisung__Group__3__Impl_in_rule__Zuweisung__Group__310361);
            rule__Zuweisung__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Zuweisung__Group__4_in_rule__Zuweisung__Group__310364);
            rule__Zuweisung__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Zuweisung__Group__3"


    // $ANTLR start "rule__Zuweisung__Group__3__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4612:1: rule__Zuweisung__Group__3__Impl : ( ( rule__Zuweisung__Group_3__0 )* ) ;
    public final void rule__Zuweisung__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4616:1: ( ( ( rule__Zuweisung__Group_3__0 )* ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4617:1: ( ( rule__Zuweisung__Group_3__0 )* )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4617:1: ( ( rule__Zuweisung__Group_3__0 )* )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4618:1: ( rule__Zuweisung__Group_3__0 )*
            {
             before(grammarAccess.getZuweisungAccess().getGroup_3()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4619:1: ( rule__Zuweisung__Group_3__0 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_INT) ) {
                    int LA11_1 = input.LA(2);

                    if ( (LA11_1==315) ) {
                        alt11=1;
                    }


                }


                switch (alt11) {
            	case 1 :
            	    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4619:2: rule__Zuweisung__Group_3__0
            	    {
            	    pushFollow(FOLLOW_rule__Zuweisung__Group_3__0_in_rule__Zuweisung__Group__3__Impl10391);
            	    rule__Zuweisung__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

             after(grammarAccess.getZuweisungAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Zuweisung__Group__3__Impl"


    // $ANTLR start "rule__Zuweisung__Group__4"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4629:1: rule__Zuweisung__Group__4 : rule__Zuweisung__Group__4__Impl ;
    public final void rule__Zuweisung__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4633:1: ( rule__Zuweisung__Group__4__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4634:2: rule__Zuweisung__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__Zuweisung__Group__4__Impl_in_rule__Zuweisung__Group__410422);
            rule__Zuweisung__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Zuweisung__Group__4"


    // $ANTLR start "rule__Zuweisung__Group__4__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4640:1: rule__Zuweisung__Group__4__Impl : ( rulePRUEFUNG ) ;
    public final void rule__Zuweisung__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4644:1: ( ( rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4645:1: ( rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4645:1: ( rulePRUEFUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4646:1: rulePRUEFUNG
            {
             before(grammarAccess.getZuweisungAccess().getPRUEFUNGParserRuleCall_4()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_rule__Zuweisung__Group__4__Impl10449);
            rulePRUEFUNG();

            state._fsp--;

             after(grammarAccess.getZuweisungAccess().getPRUEFUNGParserRuleCall_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Zuweisung__Group__4__Impl"


    // $ANTLR start "rule__Zuweisung__Group_3__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4667:1: rule__Zuweisung__Group_3__0 : rule__Zuweisung__Group_3__0__Impl rule__Zuweisung__Group_3__1 ;
    public final void rule__Zuweisung__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4671:1: ( rule__Zuweisung__Group_3__0__Impl rule__Zuweisung__Group_3__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4672:2: rule__Zuweisung__Group_3__0__Impl rule__Zuweisung__Group_3__1
            {
            pushFollow(FOLLOW_rule__Zuweisung__Group_3__0__Impl_in_rule__Zuweisung__Group_3__010488);
            rule__Zuweisung__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Zuweisung__Group_3__1_in_rule__Zuweisung__Group_3__010491);
            rule__Zuweisung__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Zuweisung__Group_3__0"


    // $ANTLR start "rule__Zuweisung__Group_3__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4679:1: rule__Zuweisung__Group_3__0__Impl : ( rulePRUEFUNG ) ;
    public final void rule__Zuweisung__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4683:1: ( ( rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4684:1: ( rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4684:1: ( rulePRUEFUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4685:1: rulePRUEFUNG
            {
             before(grammarAccess.getZuweisungAccess().getPRUEFUNGParserRuleCall_3_0()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_rule__Zuweisung__Group_3__0__Impl10518);
            rulePRUEFUNG();

            state._fsp--;

             after(grammarAccess.getZuweisungAccess().getPRUEFUNGParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Zuweisung__Group_3__0__Impl"


    // $ANTLR start "rule__Zuweisung__Group_3__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4696:1: rule__Zuweisung__Group_3__1 : rule__Zuweisung__Group_3__1__Impl ;
    public final void rule__Zuweisung__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4700:1: ( rule__Zuweisung__Group_3__1__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4701:2: rule__Zuweisung__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__Zuweisung__Group_3__1__Impl_in_rule__Zuweisung__Group_3__110547);
            rule__Zuweisung__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Zuweisung__Group_3__1"


    // $ANTLR start "rule__Zuweisung__Group_3__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4707:1: rule__Zuweisung__Group_3__1__Impl : ( ',' ) ;
    public final void rule__Zuweisung__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4711:1: ( ( ',' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4712:1: ( ',' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4712:1: ( ',' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4713:1: ','
            {
             before(grammarAccess.getZuweisungAccess().getCommaKeyword_3_1()); 
            match(input,315,FOLLOW_315_in_rule__Zuweisung__Group_3__1__Impl10575); 
             after(grammarAccess.getZuweisungAccess().getCommaKeyword_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Zuweisung__Group_3__1__Impl"


    // $ANTLR start "rule__UsedIDs__Group__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4730:1: rule__UsedIDs__Group__0 : rule__UsedIDs__Group__0__Impl rule__UsedIDs__Group__1 ;
    public final void rule__UsedIDs__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4734:1: ( rule__UsedIDs__Group__0__Impl rule__UsedIDs__Group__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4735:2: rule__UsedIDs__Group__0__Impl rule__UsedIDs__Group__1
            {
            pushFollow(FOLLOW_rule__UsedIDs__Group__0__Impl_in_rule__UsedIDs__Group__010610);
            rule__UsedIDs__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UsedIDs__Group__1_in_rule__UsedIDs__Group__010613);
            rule__UsedIDs__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UsedIDs__Group__0"


    // $ANTLR start "rule__UsedIDs__Group__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4742:1: rule__UsedIDs__Group__0__Impl : ( 'CodesAlle = ' ) ;
    public final void rule__UsedIDs__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4746:1: ( ( 'CodesAlle = ' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4747:1: ( 'CodesAlle = ' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4747:1: ( 'CodesAlle = ' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4748:1: 'CodesAlle = '
            {
             before(grammarAccess.getUsedIDsAccess().getCodesAlleKeyword_0()); 
            match(input,323,FOLLOW_323_in_rule__UsedIDs__Group__0__Impl10641); 
             after(grammarAccess.getUsedIDsAccess().getCodesAlleKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UsedIDs__Group__0__Impl"


    // $ANTLR start "rule__UsedIDs__Group__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4761:1: rule__UsedIDs__Group__1 : rule__UsedIDs__Group__1__Impl rule__UsedIDs__Group__2 ;
    public final void rule__UsedIDs__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4765:1: ( rule__UsedIDs__Group__1__Impl rule__UsedIDs__Group__2 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4766:2: rule__UsedIDs__Group__1__Impl rule__UsedIDs__Group__2
            {
            pushFollow(FOLLOW_rule__UsedIDs__Group__1__Impl_in_rule__UsedIDs__Group__110672);
            rule__UsedIDs__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UsedIDs__Group__2_in_rule__UsedIDs__Group__110675);
            rule__UsedIDs__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UsedIDs__Group__1"


    // $ANTLR start "rule__UsedIDs__Group__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4773:1: rule__UsedIDs__Group__1__Impl : ( ( rule__UsedIDs__Group_1__0 )* ) ;
    public final void rule__UsedIDs__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4777:1: ( ( ( rule__UsedIDs__Group_1__0 )* ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4778:1: ( ( rule__UsedIDs__Group_1__0 )* )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4778:1: ( ( rule__UsedIDs__Group_1__0 )* )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4779:1: ( rule__UsedIDs__Group_1__0 )*
            {
             before(grammarAccess.getUsedIDsAccess().getGroup_1()); 
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4780:1: ( rule__UsedIDs__Group_1__0 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==RULE_INT) ) {
                    int LA12_1 = input.LA(2);

                    if ( (LA12_1==315) ) {
                        alt12=1;
                    }


                }


                switch (alt12) {
            	case 1 :
            	    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4780:2: rule__UsedIDs__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__UsedIDs__Group_1__0_in_rule__UsedIDs__Group__1__Impl10702);
            	    rule__UsedIDs__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

             after(grammarAccess.getUsedIDsAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UsedIDs__Group__1__Impl"


    // $ANTLR start "rule__UsedIDs__Group__2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4790:1: rule__UsedIDs__Group__2 : rule__UsedIDs__Group__2__Impl ;
    public final void rule__UsedIDs__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4794:1: ( rule__UsedIDs__Group__2__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4795:2: rule__UsedIDs__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__UsedIDs__Group__2__Impl_in_rule__UsedIDs__Group__210733);
            rule__UsedIDs__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UsedIDs__Group__2"


    // $ANTLR start "rule__UsedIDs__Group__2__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4801:1: rule__UsedIDs__Group__2__Impl : ( rulePRUEFUNG ) ;
    public final void rule__UsedIDs__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4805:1: ( ( rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4806:1: ( rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4806:1: ( rulePRUEFUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4807:1: rulePRUEFUNG
            {
             before(grammarAccess.getUsedIDsAccess().getPRUEFUNGParserRuleCall_2()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_rule__UsedIDs__Group__2__Impl10760);
            rulePRUEFUNG();

            state._fsp--;

             after(grammarAccess.getUsedIDsAccess().getPRUEFUNGParserRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UsedIDs__Group__2__Impl"


    // $ANTLR start "rule__UsedIDs__Group_1__0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4824:1: rule__UsedIDs__Group_1__0 : rule__UsedIDs__Group_1__0__Impl rule__UsedIDs__Group_1__1 ;
    public final void rule__UsedIDs__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4828:1: ( rule__UsedIDs__Group_1__0__Impl rule__UsedIDs__Group_1__1 )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4829:2: rule__UsedIDs__Group_1__0__Impl rule__UsedIDs__Group_1__1
            {
            pushFollow(FOLLOW_rule__UsedIDs__Group_1__0__Impl_in_rule__UsedIDs__Group_1__010795);
            rule__UsedIDs__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__UsedIDs__Group_1__1_in_rule__UsedIDs__Group_1__010798);
            rule__UsedIDs__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UsedIDs__Group_1__0"


    // $ANTLR start "rule__UsedIDs__Group_1__0__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4836:1: rule__UsedIDs__Group_1__0__Impl : ( rulePRUEFUNG ) ;
    public final void rule__UsedIDs__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4840:1: ( ( rulePRUEFUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4841:1: ( rulePRUEFUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4841:1: ( rulePRUEFUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4842:1: rulePRUEFUNG
            {
             before(grammarAccess.getUsedIDsAccess().getPRUEFUNGParserRuleCall_1_0()); 
            pushFollow(FOLLOW_rulePRUEFUNG_in_rule__UsedIDs__Group_1__0__Impl10825);
            rulePRUEFUNG();

            state._fsp--;

             after(grammarAccess.getUsedIDsAccess().getPRUEFUNGParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UsedIDs__Group_1__0__Impl"


    // $ANTLR start "rule__UsedIDs__Group_1__1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4853:1: rule__UsedIDs__Group_1__1 : rule__UsedIDs__Group_1__1__Impl ;
    public final void rule__UsedIDs__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4857:1: ( rule__UsedIDs__Group_1__1__Impl )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4858:2: rule__UsedIDs__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__UsedIDs__Group_1__1__Impl_in_rule__UsedIDs__Group_1__110854);
            rule__UsedIDs__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UsedIDs__Group_1__1"


    // $ANTLR start "rule__UsedIDs__Group_1__1__Impl"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4864:1: rule__UsedIDs__Group_1__1__Impl : ( ',' ) ;
    public final void rule__UsedIDs__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4868:1: ( ( ',' ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4869:1: ( ',' )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4869:1: ( ',' )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4870:1: ','
            {
             before(grammarAccess.getUsedIDsAccess().getCommaKeyword_1_1()); 
            match(input,315,FOLLOW_315_in_rule__UsedIDs__Group_1__1__Impl10882); 
             after(grammarAccess.getUsedIDsAccess().getCommaKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UsedIDs__Group_1__1__Impl"


    // $ANTLR start "rule__Konfiguration__UsedidsAssignment_0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4888:1: rule__Konfiguration__UsedidsAssignment_0 : ( ruleUsedIDs ) ;
    public final void rule__Konfiguration__UsedidsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4892:1: ( ( ruleUsedIDs ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4893:1: ( ruleUsedIDs )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4893:1: ( ruleUsedIDs )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4894:1: ruleUsedIDs
            {
             before(grammarAccess.getKonfigurationAccess().getUsedidsUsedIDsParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleUsedIDs_in_rule__Konfiguration__UsedidsAssignment_010922);
            ruleUsedIDs();

            state._fsp--;

             after(grammarAccess.getKonfigurationAccess().getUsedidsUsedIDsParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__UsedidsAssignment_0"


    // $ANTLR start "rule__Konfiguration__SpezantragszuweisungAssignment_1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4903:1: rule__Konfiguration__SpezantragszuweisungAssignment_1 : ( ruleSPEZ_ANTRAGSZUWEISUNG ) ;
    public final void rule__Konfiguration__SpezantragszuweisungAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4907:1: ( ( ruleSPEZ_ANTRAGSZUWEISUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4908:1: ( ruleSPEZ_ANTRAGSZUWEISUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4908:1: ( ruleSPEZ_ANTRAGSZUWEISUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4909:1: ruleSPEZ_ANTRAGSZUWEISUNG
            {
             before(grammarAccess.getKonfigurationAccess().getSpezantragszuweisungSPEZ_ANTRAGSZUWEISUNGParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleSPEZ_ANTRAGSZUWEISUNG_in_rule__Konfiguration__SpezantragszuweisungAssignment_110953);
            ruleSPEZ_ANTRAGSZUWEISUNG();

            state._fsp--;

             after(grammarAccess.getKonfigurationAccess().getSpezantragszuweisungSPEZ_ANTRAGSZUWEISUNGParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__SpezantragszuweisungAssignment_1"


    // $ANTLR start "rule__Konfiguration__AntragszuweisungAssignment_2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4918:1: rule__Konfiguration__AntragszuweisungAssignment_2 : ( ruleZuweisung ) ;
    public final void rule__Konfiguration__AntragszuweisungAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4922:1: ( ( ruleZuweisung ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4923:1: ( ruleZuweisung )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4923:1: ( ruleZuweisung )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4924:1: ruleZuweisung
            {
             before(grammarAccess.getKonfigurationAccess().getAntragszuweisungZuweisungParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleZuweisung_in_rule__Konfiguration__AntragszuweisungAssignment_210984);
            ruleZuweisung();

            state._fsp--;

             after(grammarAccess.getKonfigurationAccess().getAntragszuweisungZuweisungParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__AntragszuweisungAssignment_2"


    // $ANTLR start "rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_0"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4933:1: rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_0 : ( rulevwkpaktionkonfiguraktion ) ;
    public final void rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4937:1: ( ( rulevwkpaktionkonfiguraktion ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4938:1: ( rulevwkpaktionkonfiguraktion )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4938:1: ( rulevwkpaktionkonfiguraktion )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4939:1: rulevwkpaktionkonfiguraktion
            {
             before(grammarAccess.getKonfigurationAccess().getVwkpkonfigurationfueraktionVwkpaktionkonfiguraktionParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_rulevwkpaktionkonfiguraktion_in_rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_011015);
            rulevwkpaktionkonfiguraktion();

            state._fsp--;

             after(grammarAccess.getKonfigurationAccess().getVwkpkonfigurationfueraktionVwkpaktionkonfiguraktionParserRuleCall_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_0"


    // $ANTLR start "rule__Konfiguration__PruefungsaktionAssignment_3_1"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4948:1: rule__Konfiguration__PruefungsaktionAssignment_3_1 : ( rulePRUEFUNGSAKTION ) ;
    public final void rule__Konfiguration__PruefungsaktionAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4952:1: ( ( rulePRUEFUNGSAKTION ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4953:1: ( rulePRUEFUNGSAKTION )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4953:1: ( rulePRUEFUNGSAKTION )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4954:1: rulePRUEFUNGSAKTION
            {
             before(grammarAccess.getKonfigurationAccess().getPruefungsaktionPRUEFUNGSAKTIONParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_rulePRUEFUNGSAKTION_in_rule__Konfiguration__PruefungsaktionAssignment_3_111046);
            rulePRUEFUNGSAKTION();

            state._fsp--;

             after(grammarAccess.getKonfigurationAccess().getPruefungsaktionPRUEFUNGSAKTIONParserRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__PruefungsaktionAssignment_3_1"


    // $ANTLR start "rule__Konfiguration__PruefungsklassennameAssignment_3_2"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4963:1: rule__Konfiguration__PruefungsklassennameAssignment_3_2 : ( rulePRUEFUNGSKLASSENNAME ) ;
    public final void rule__Konfiguration__PruefungsklassennameAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4967:1: ( ( rulePRUEFUNGSKLASSENNAME ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4968:1: ( rulePRUEFUNGSKLASSENNAME )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4968:1: ( rulePRUEFUNGSKLASSENNAME )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4969:1: rulePRUEFUNGSKLASSENNAME
            {
             before(grammarAccess.getKonfigurationAccess().getPruefungsklassennamePRUEFUNGSKLASSENNAMEParserRuleCall_3_2_0()); 
            pushFollow(FOLLOW_rulePRUEFUNGSKLASSENNAME_in_rule__Konfiguration__PruefungsklassennameAssignment_3_211077);
            rulePRUEFUNGSKLASSENNAME();

            state._fsp--;

             after(grammarAccess.getKonfigurationAccess().getPruefungsklassennamePRUEFUNGSKLASSENNAMEParserRuleCall_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__PruefungsklassennameAssignment_3_2"


    // $ANTLR start "rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_3"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4978:1: rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_3 : ( rulePRUEFUNGSKURZTEXT ) ;
    public final void rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4982:1: ( ( rulePRUEFUNGSKURZTEXT ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4983:1: ( rulePRUEFUNGSKURZTEXT )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4983:1: ( rulePRUEFUNGSKURZTEXT )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4984:1: rulePRUEFUNGSKURZTEXT
            {
             before(grammarAccess.getKonfigurationAccess().getPruefungskurzbezeichnungPRUEFUNGSKURZTEXTParserRuleCall_3_3_0()); 
            pushFollow(FOLLOW_rulePRUEFUNGSKURZTEXT_in_rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_311108);
            rulePRUEFUNGSKURZTEXT();

            state._fsp--;

             after(grammarAccess.getKonfigurationAccess().getPruefungskurzbezeichnungPRUEFUNGSKURZTEXTParserRuleCall_3_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_3"


    // $ANTLR start "rule__Konfiguration__PruefungslangtextAssignment_3_4"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4993:1: rule__Konfiguration__PruefungslangtextAssignment_3_4 : ( rulePRUEFUNGSLANGTEXT ) ;
    public final void rule__Konfiguration__PruefungslangtextAssignment_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4997:1: ( ( rulePRUEFUNGSLANGTEXT ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4998:1: ( rulePRUEFUNGSLANGTEXT )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4998:1: ( rulePRUEFUNGSLANGTEXT )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:4999:1: rulePRUEFUNGSLANGTEXT
            {
             before(grammarAccess.getKonfigurationAccess().getPruefungslangtextPRUEFUNGSLANGTEXTParserRuleCall_3_4_0()); 
            pushFollow(FOLLOW_rulePRUEFUNGSLANGTEXT_in_rule__Konfiguration__PruefungslangtextAssignment_3_411139);
            rulePRUEFUNGSLANGTEXT();

            state._fsp--;

             after(grammarAccess.getKonfigurationAccess().getPruefungslangtextPRUEFUNGSLANGTEXTParserRuleCall_3_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__PruefungslangtextAssignment_3_4"


    // $ANTLR start "rule__Konfiguration__PruefungswirkungAssignment_3_5"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:5008:1: rule__Konfiguration__PruefungswirkungAssignment_3_5 : ( rulePRUEFUNGSWIRKUNG ) ;
    public final void rule__Konfiguration__PruefungswirkungAssignment_3_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:5012:1: ( ( rulePRUEFUNGSWIRKUNG ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:5013:1: ( rulePRUEFUNGSWIRKUNG )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:5013:1: ( rulePRUEFUNGSWIRKUNG )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:5014:1: rulePRUEFUNGSWIRKUNG
            {
             before(grammarAccess.getKonfigurationAccess().getPruefungswirkungPRUEFUNGSWIRKUNGParserRuleCall_3_5_0()); 
            pushFollow(FOLLOW_rulePRUEFUNGSWIRKUNG_in_rule__Konfiguration__PruefungswirkungAssignment_3_511170);
            rulePRUEFUNGSWIRKUNG();

            state._fsp--;

             after(grammarAccess.getKonfigurationAccess().getPruefungswirkungPRUEFUNGSWIRKUNGParserRuleCall_3_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__PruefungswirkungAssignment_3_5"


    // $ANTLR start "rule__Konfiguration__PruefungsichtbarkeitAssignment_3_6"
    // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:5023:1: rule__Konfiguration__PruefungsichtbarkeitAssignment_3_6 : ( rulePRUEFUNGSICHTBARKEIT ) ;
    public final void rule__Konfiguration__PruefungsichtbarkeitAssignment_3_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:5027:1: ( ( rulePRUEFUNGSICHTBARKEIT ) )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:5028:1: ( rulePRUEFUNGSICHTBARKEIT )
            {
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:5028:1: ( rulePRUEFUNGSICHTBARKEIT )
            // ../de.deg.eler.ft.vp.ui/src-gen/de/deg/eler/ft/vp/ui/contentassist/antlr/internal/InternalDsl.g:5029:1: rulePRUEFUNGSICHTBARKEIT
            {
             before(grammarAccess.getKonfigurationAccess().getPruefungsichtbarkeitPRUEFUNGSICHTBARKEITParserRuleCall_3_6_0()); 
            pushFollow(FOLLOW_rulePRUEFUNGSICHTBARKEIT_in_rule__Konfiguration__PruefungsichtbarkeitAssignment_3_611201);
            rulePRUEFUNGSICHTBARKEIT();

            state._fsp--;

             after(grammarAccess.getKonfigurationAccess().getPruefungsichtbarkeitPRUEFUNGSICHTBARKEITParserRuleCall_3_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Konfiguration__PruefungsichtbarkeitAssignment_3_6"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleKonfiguration_in_entryRuleKonfiguration61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleKonfiguration68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Konfiguration__Group__0_in_ruleKonfiguration94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSICHTBARKEIT_in_entryRulePRUEFUNGSICHTBARKEIT121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePRUEFUNGSICHTBARKEIT128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSICHTBARKEIT__Group__0_in_rulePRUEFUNGSICHTBARKEIT154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulevwkpaktionkonfiguraktion_in_entryRulevwkpaktionkonfiguraktion181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulevwkpaktionkonfiguraktion188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__0_in_rulevwkpaktionkonfiguraktion214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVWKPTYP_in_entryRuleVWKPTYP241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVWKPTYP248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__VWKPTYP__Alternatives_in_ruleVWKPTYP274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSPEZ_ANTRAGSZUWEISUNG_in_entryRuleSPEZ_ANTRAGSZUWEISUNG301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSPEZ_ANTRAGSZUWEISUNG308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__0_in_ruleSPEZ_ANTRAGSZUWEISUNG334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSLANGTEXT_in_entryRulePRUEFUNGSLANGTEXT361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePRUEFUNGSLANGTEXT368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__0_in_rulePRUEFUNGSLANGTEXT394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSKURZTEXT_in_entryRulePRUEFUNGSKURZTEXT421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePRUEFUNGSKURZTEXT428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__0_in_rulePRUEFUNGSKURZTEXT454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSKLASSENNAME_in_entryRulePRUEFUNGSKLASSENNAME481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePRUEFUNGSKLASSENNAME488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__0_in_rulePRUEFUNGSKLASSENNAME514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSAKTION_in_entryRulePRUEFUNGSAKTION541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePRUEFUNGSAKTION548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSAKTION__Group__0_in_rulePRUEFUNGSAKTION574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAKTIONSID_in_entryRuleAKTIONSID601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAKTIONSID608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AKTIONSID__Group__0_in_ruleAKTIONSID634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSWIRKUNG_in_entryRulePRUEFUNGSWIRKUNG661 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePRUEFUNGSWIRKUNG668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__0_in_rulePRUEFUNGSWIRKUNG694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWIRKUNGSID_in_entryRuleWIRKUNGSID721 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWIRKUNGSID728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WIRKUNGSID__Group__0_in_ruleWIRKUNGSID754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleZuweisung_in_entryRuleZuweisung781 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleZuweisung788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Zuweisung__Group__0_in_ruleZuweisung814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUsedIDs_in_entryRuleUsedIDs841 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUsedIDs848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UsedIDs__Group__0_in_ruleUsedIDs874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_entryRulePRUEFUNG901 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePRUEFUNG908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rulePRUEFUNG934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWIRKUNG_in_entryRuleWIRKUNG960 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWIRKUNG967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WIRKUNG__Alternatives_in_ruleWIRKUNG993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleANTRAGSART_in_entryRuleANTRAGSART1020 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleANTRAGSART1027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ANTRAGSART__Alternatives_in_ruleANTRAGSART1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAKTION_in_entryRuleAKTION1080 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAKTION1087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AKTION__Alternatives_in_ruleAKTION1113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKLASSE_in_entryRuleKLASSE1140 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleKLASSE1147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KLASSE__Alternatives_in_ruleKLASSE1173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_0_in_rule__Konfiguration__Alternatives_31209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Konfiguration__PruefungsaktionAssignment_3_1_in_rule__Konfiguration__Alternatives_31227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Konfiguration__PruefungsklassennameAssignment_3_2_in_rule__Konfiguration__Alternatives_31245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_3_in_rule__Konfiguration__Alternatives_31263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Konfiguration__PruefungslangtextAssignment_3_4_in_rule__Konfiguration__Alternatives_31281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Konfiguration__PruefungswirkungAssignment_3_5_in_rule__Konfiguration__Alternatives_31299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Konfiguration__PruefungsichtbarkeitAssignment_3_6_in_rule__Konfiguration__Alternatives_31317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__VWKPTYP__Alternatives1351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__VWKPTYP__Alternatives1371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__WIRKUNG__Alternatives1406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__WIRKUNG__Alternatives1426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__WIRKUNG__Alternatives1446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__ANTRAGSART__Alternatives1481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__ANTRAGSART__Alternatives1501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__ANTRAGSART__Alternatives1521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__ANTRAGSART__Alternatives1541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__AKTION__Alternatives1576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__AKTION__Alternatives1596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__AKTION__Alternatives1616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__AKTION__Alternatives1636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__AKTION__Alternatives1656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__AKTION__Alternatives1676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__AKTION__Alternatives1696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__AKTION__Alternatives1716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__AKTION__Alternatives1736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__AKTION__Alternatives1756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__AKTION__Alternatives1776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__AKTION__Alternatives1796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__AKTION__Alternatives1816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__AKTION__Alternatives1836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__AKTION__Alternatives1856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__AKTION__Alternatives1876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__AKTION__Alternatives1896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__AKTION__Alternatives1916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__AKTION__Alternatives1936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rule__AKTION__Alternatives1956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_rule__KLASSE__Alternatives1991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rule__KLASSE__Alternatives2011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_rule__KLASSE__Alternatives2031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_rule__KLASSE__Alternatives2051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_rule__KLASSE__Alternatives2071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_rule__KLASSE__Alternatives2091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_rule__KLASSE__Alternatives2111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_rule__KLASSE__Alternatives2131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_rule__KLASSE__Alternatives2151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_rule__KLASSE__Alternatives2171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_rule__KLASSE__Alternatives2191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_rule__KLASSE__Alternatives2211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_rule__KLASSE__Alternatives2231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_rule__KLASSE__Alternatives2251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_rule__KLASSE__Alternatives2271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_rule__KLASSE__Alternatives2291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_rule__KLASSE__Alternatives2311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_rule__KLASSE__Alternatives2331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_rule__KLASSE__Alternatives2351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_rule__KLASSE__Alternatives2371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_rule__KLASSE__Alternatives2391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_rule__KLASSE__Alternatives2411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_rule__KLASSE__Alternatives2431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_rule__KLASSE__Alternatives2451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_rule__KLASSE__Alternatives2471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_rule__KLASSE__Alternatives2491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_rule__KLASSE__Alternatives2511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_rule__KLASSE__Alternatives2531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_rule__KLASSE__Alternatives2551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_rule__KLASSE__Alternatives2571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_rule__KLASSE__Alternatives2591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_rule__KLASSE__Alternatives2611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_rule__KLASSE__Alternatives2631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_rule__KLASSE__Alternatives2651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_rule__KLASSE__Alternatives2671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_rule__KLASSE__Alternatives2691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_rule__KLASSE__Alternatives2711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_rule__KLASSE__Alternatives2731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_rule__KLASSE__Alternatives2751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_rule__KLASSE__Alternatives2771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_rule__KLASSE__Alternatives2791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_rule__KLASSE__Alternatives2811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_rule__KLASSE__Alternatives2831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_rule__KLASSE__Alternatives2851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_rule__KLASSE__Alternatives2871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_rule__KLASSE__Alternatives2891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_rule__KLASSE__Alternatives2911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_rule__KLASSE__Alternatives2931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_rule__KLASSE__Alternatives2951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_rule__KLASSE__Alternatives2971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_90_in_rule__KLASSE__Alternatives2991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_rule__KLASSE__Alternatives3011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_rule__KLASSE__Alternatives3031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_rule__KLASSE__Alternatives3051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_94_in_rule__KLASSE__Alternatives3071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_rule__KLASSE__Alternatives3091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_rule__KLASSE__Alternatives3111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_rule__KLASSE__Alternatives3131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_rule__KLASSE__Alternatives3151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_rule__KLASSE__Alternatives3171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_100_in_rule__KLASSE__Alternatives3191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_101_in_rule__KLASSE__Alternatives3211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_102_in_rule__KLASSE__Alternatives3231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_103_in_rule__KLASSE__Alternatives3251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_104_in_rule__KLASSE__Alternatives3271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_105_in_rule__KLASSE__Alternatives3291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_106_in_rule__KLASSE__Alternatives3311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_107_in_rule__KLASSE__Alternatives3331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_108_in_rule__KLASSE__Alternatives3351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_109_in_rule__KLASSE__Alternatives3371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_110_in_rule__KLASSE__Alternatives3391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_111_in_rule__KLASSE__Alternatives3411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_112_in_rule__KLASSE__Alternatives3431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_113_in_rule__KLASSE__Alternatives3451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_114_in_rule__KLASSE__Alternatives3471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_115_in_rule__KLASSE__Alternatives3491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_116_in_rule__KLASSE__Alternatives3511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_117_in_rule__KLASSE__Alternatives3531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_118_in_rule__KLASSE__Alternatives3551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_119_in_rule__KLASSE__Alternatives3571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_120_in_rule__KLASSE__Alternatives3591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_121_in_rule__KLASSE__Alternatives3611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_122_in_rule__KLASSE__Alternatives3631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_123_in_rule__KLASSE__Alternatives3651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_124_in_rule__KLASSE__Alternatives3671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_125_in_rule__KLASSE__Alternatives3691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_126_in_rule__KLASSE__Alternatives3711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_127_in_rule__KLASSE__Alternatives3731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_128_in_rule__KLASSE__Alternatives3751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_129_in_rule__KLASSE__Alternatives3771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_130_in_rule__KLASSE__Alternatives3791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_131_in_rule__KLASSE__Alternatives3811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_132_in_rule__KLASSE__Alternatives3831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_133_in_rule__KLASSE__Alternatives3851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_134_in_rule__KLASSE__Alternatives3871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_135_in_rule__KLASSE__Alternatives3891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_136_in_rule__KLASSE__Alternatives3911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_137_in_rule__KLASSE__Alternatives3931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_138_in_rule__KLASSE__Alternatives3951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_139_in_rule__KLASSE__Alternatives3971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_140_in_rule__KLASSE__Alternatives3991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_141_in_rule__KLASSE__Alternatives4011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_142_in_rule__KLASSE__Alternatives4031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_143_in_rule__KLASSE__Alternatives4051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_144_in_rule__KLASSE__Alternatives4071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_145_in_rule__KLASSE__Alternatives4091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_146_in_rule__KLASSE__Alternatives4111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_147_in_rule__KLASSE__Alternatives4131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_148_in_rule__KLASSE__Alternatives4151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_149_in_rule__KLASSE__Alternatives4171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_150_in_rule__KLASSE__Alternatives4191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_151_in_rule__KLASSE__Alternatives4211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_152_in_rule__KLASSE__Alternatives4231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_153_in_rule__KLASSE__Alternatives4251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_154_in_rule__KLASSE__Alternatives4271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_155_in_rule__KLASSE__Alternatives4291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_156_in_rule__KLASSE__Alternatives4311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_157_in_rule__KLASSE__Alternatives4331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_158_in_rule__KLASSE__Alternatives4351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_159_in_rule__KLASSE__Alternatives4371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_160_in_rule__KLASSE__Alternatives4391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_161_in_rule__KLASSE__Alternatives4411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_162_in_rule__KLASSE__Alternatives4431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_163_in_rule__KLASSE__Alternatives4451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_164_in_rule__KLASSE__Alternatives4471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_165_in_rule__KLASSE__Alternatives4491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_166_in_rule__KLASSE__Alternatives4511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_167_in_rule__KLASSE__Alternatives4531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_168_in_rule__KLASSE__Alternatives4551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_169_in_rule__KLASSE__Alternatives4571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_170_in_rule__KLASSE__Alternatives4591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_171_in_rule__KLASSE__Alternatives4611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_172_in_rule__KLASSE__Alternatives4631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_173_in_rule__KLASSE__Alternatives4651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_174_in_rule__KLASSE__Alternatives4671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_175_in_rule__KLASSE__Alternatives4691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_176_in_rule__KLASSE__Alternatives4711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_177_in_rule__KLASSE__Alternatives4731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_178_in_rule__KLASSE__Alternatives4751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_179_in_rule__KLASSE__Alternatives4771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_180_in_rule__KLASSE__Alternatives4791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_181_in_rule__KLASSE__Alternatives4811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_182_in_rule__KLASSE__Alternatives4831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_183_in_rule__KLASSE__Alternatives4851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_184_in_rule__KLASSE__Alternatives4871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_185_in_rule__KLASSE__Alternatives4891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_186_in_rule__KLASSE__Alternatives4911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_187_in_rule__KLASSE__Alternatives4931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_188_in_rule__KLASSE__Alternatives4951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_189_in_rule__KLASSE__Alternatives4971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_190_in_rule__KLASSE__Alternatives4991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_191_in_rule__KLASSE__Alternatives5011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_192_in_rule__KLASSE__Alternatives5031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_193_in_rule__KLASSE__Alternatives5051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_194_in_rule__KLASSE__Alternatives5071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_195_in_rule__KLASSE__Alternatives5091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_196_in_rule__KLASSE__Alternatives5111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_197_in_rule__KLASSE__Alternatives5131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_198_in_rule__KLASSE__Alternatives5151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_199_in_rule__KLASSE__Alternatives5171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_200_in_rule__KLASSE__Alternatives5191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_201_in_rule__KLASSE__Alternatives5211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_202_in_rule__KLASSE__Alternatives5231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_203_in_rule__KLASSE__Alternatives5251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_204_in_rule__KLASSE__Alternatives5271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_205_in_rule__KLASSE__Alternatives5291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_206_in_rule__KLASSE__Alternatives5311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_207_in_rule__KLASSE__Alternatives5331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_208_in_rule__KLASSE__Alternatives5351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_209_in_rule__KLASSE__Alternatives5371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_210_in_rule__KLASSE__Alternatives5391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_211_in_rule__KLASSE__Alternatives5411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_212_in_rule__KLASSE__Alternatives5431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_213_in_rule__KLASSE__Alternatives5451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_214_in_rule__KLASSE__Alternatives5471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_215_in_rule__KLASSE__Alternatives5491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_216_in_rule__KLASSE__Alternatives5511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_217_in_rule__KLASSE__Alternatives5531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_218_in_rule__KLASSE__Alternatives5551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_219_in_rule__KLASSE__Alternatives5571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_220_in_rule__KLASSE__Alternatives5591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_221_in_rule__KLASSE__Alternatives5611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_222_in_rule__KLASSE__Alternatives5631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_223_in_rule__KLASSE__Alternatives5651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_224_in_rule__KLASSE__Alternatives5671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_225_in_rule__KLASSE__Alternatives5691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_226_in_rule__KLASSE__Alternatives5711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_227_in_rule__KLASSE__Alternatives5731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_228_in_rule__KLASSE__Alternatives5751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_229_in_rule__KLASSE__Alternatives5771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_230_in_rule__KLASSE__Alternatives5791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_231_in_rule__KLASSE__Alternatives5811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_232_in_rule__KLASSE__Alternatives5831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_233_in_rule__KLASSE__Alternatives5851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_234_in_rule__KLASSE__Alternatives5871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_235_in_rule__KLASSE__Alternatives5891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_236_in_rule__KLASSE__Alternatives5911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_237_in_rule__KLASSE__Alternatives5931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_238_in_rule__KLASSE__Alternatives5951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_239_in_rule__KLASSE__Alternatives5971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_240_in_rule__KLASSE__Alternatives5991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_241_in_rule__KLASSE__Alternatives6011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_242_in_rule__KLASSE__Alternatives6031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_243_in_rule__KLASSE__Alternatives6051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_244_in_rule__KLASSE__Alternatives6071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_245_in_rule__KLASSE__Alternatives6091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_246_in_rule__KLASSE__Alternatives6111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_247_in_rule__KLASSE__Alternatives6131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_248_in_rule__KLASSE__Alternatives6151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_249_in_rule__KLASSE__Alternatives6171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_250_in_rule__KLASSE__Alternatives6191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_251_in_rule__KLASSE__Alternatives6211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_252_in_rule__KLASSE__Alternatives6231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_253_in_rule__KLASSE__Alternatives6251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_254_in_rule__KLASSE__Alternatives6271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_255_in_rule__KLASSE__Alternatives6291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_256_in_rule__KLASSE__Alternatives6311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_257_in_rule__KLASSE__Alternatives6331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_258_in_rule__KLASSE__Alternatives6351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_259_in_rule__KLASSE__Alternatives6371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_260_in_rule__KLASSE__Alternatives6391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_261_in_rule__KLASSE__Alternatives6411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_262_in_rule__KLASSE__Alternatives6431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_263_in_rule__KLASSE__Alternatives6451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_264_in_rule__KLASSE__Alternatives6471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_265_in_rule__KLASSE__Alternatives6491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_266_in_rule__KLASSE__Alternatives6511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_267_in_rule__KLASSE__Alternatives6531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_268_in_rule__KLASSE__Alternatives6551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_269_in_rule__KLASSE__Alternatives6571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_270_in_rule__KLASSE__Alternatives6591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_271_in_rule__KLASSE__Alternatives6611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_272_in_rule__KLASSE__Alternatives6631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_273_in_rule__KLASSE__Alternatives6651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_274_in_rule__KLASSE__Alternatives6671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_275_in_rule__KLASSE__Alternatives6691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_276_in_rule__KLASSE__Alternatives6711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_277_in_rule__KLASSE__Alternatives6731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_278_in_rule__KLASSE__Alternatives6751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_279_in_rule__KLASSE__Alternatives6771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_280_in_rule__KLASSE__Alternatives6791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_281_in_rule__KLASSE__Alternatives6811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_282_in_rule__KLASSE__Alternatives6831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_283_in_rule__KLASSE__Alternatives6851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_284_in_rule__KLASSE__Alternatives6871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_285_in_rule__KLASSE__Alternatives6891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_286_in_rule__KLASSE__Alternatives6911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_287_in_rule__KLASSE__Alternatives6931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_288_in_rule__KLASSE__Alternatives6951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_289_in_rule__KLASSE__Alternatives6971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_290_in_rule__KLASSE__Alternatives6991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_291_in_rule__KLASSE__Alternatives7011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_292_in_rule__KLASSE__Alternatives7031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_293_in_rule__KLASSE__Alternatives7051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_294_in_rule__KLASSE__Alternatives7071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_295_in_rule__KLASSE__Alternatives7091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_296_in_rule__KLASSE__Alternatives7111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_297_in_rule__KLASSE__Alternatives7131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_298_in_rule__KLASSE__Alternatives7151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_299_in_rule__KLASSE__Alternatives7171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_300_in_rule__KLASSE__Alternatives7191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_301_in_rule__KLASSE__Alternatives7211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_302_in_rule__KLASSE__Alternatives7231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_303_in_rule__KLASSE__Alternatives7251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_304_in_rule__KLASSE__Alternatives7271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_305_in_rule__KLASSE__Alternatives7291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_306_in_rule__KLASSE__Alternatives7311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_307_in_rule__KLASSE__Alternatives7331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_308_in_rule__KLASSE__Alternatives7351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_309_in_rule__KLASSE__Alternatives7371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Konfiguration__Group__0__Impl_in_rule__Konfiguration__Group__07403 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0xF140000000000000L,0x0000000000000006L});
    public static final BitSet FOLLOW_rule__Konfiguration__Group__1_in_rule__Konfiguration__Group__07406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Konfiguration__UsedidsAssignment_0_in_rule__Konfiguration__Group__0__Impl7433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Konfiguration__Group__1__Impl_in_rule__Konfiguration__Group__17463 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0xF140000000000000L,0x0000000000000006L});
    public static final BitSet FOLLOW_rule__Konfiguration__Group__2_in_rule__Konfiguration__Group__17466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Konfiguration__SpezantragszuweisungAssignment_1_in_rule__Konfiguration__Group__1__Impl7493 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_rule__Konfiguration__Group__2__Impl_in_rule__Konfiguration__Group__27524 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0xF140000000000000L,0x0000000000000006L});
    public static final BitSet FOLLOW_rule__Konfiguration__Group__3_in_rule__Konfiguration__Group__27527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Konfiguration__AntragszuweisungAssignment_2_in_rule__Konfiguration__Group__2__Impl7554 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_rule__Konfiguration__Group__3__Impl_in_rule__Konfiguration__Group__37585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Konfiguration__Alternatives_3_in_rule__Konfiguration__Group__3__Impl7612 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0xF140000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSICHTBARKEIT__Group__0__Impl_in_rule__PRUEFUNGSICHTBARKEIT__Group__07651 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSICHTBARKEIT__Group__1_in_rule__PRUEFUNGSICHTBARKEIT__Group__07654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_310_in_rule__PRUEFUNGSICHTBARKEIT__Group__0__Impl7682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSICHTBARKEIT__Group__1__Impl_in_rule__PRUEFUNGSICHTBARKEIT__Group__17713 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSICHTBARKEIT__Group__2_in_rule__PRUEFUNGSICHTBARKEIT__Group__17716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rule__PRUEFUNGSICHTBARKEIT__Group__1__Impl7743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSICHTBARKEIT__Group__2__Impl_in_rule__PRUEFUNGSICHTBARKEIT__Group__27772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_311_in_rule__PRUEFUNGSICHTBARKEIT__Group__2__Impl7800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__0__Impl_in_rule__Vwkpaktionkonfiguraktion__Group__07837 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__1_in_rule__Vwkpaktionkonfiguraktion__Group__07840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_312_in_rule__Vwkpaktionkonfiguraktion__Group__0__Impl7868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__1__Impl_in_rule__Vwkpaktionkonfiguraktion__Group__17899 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
    public static final BitSet FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__2_in_rule__Vwkpaktionkonfiguraktion__Group__17902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVWKPTYP_in_rule__Vwkpaktionkonfiguraktion__Group__1__Impl7929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__2__Impl_in_rule__Vwkpaktionkonfiguraktion__Group__27958 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__3_in_rule__Vwkpaktionkonfiguraktion__Group__27961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_313_in_rule__Vwkpaktionkonfiguraktion__Group__2__Impl7989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__3__Impl_in_rule__Vwkpaktionkonfiguraktion__Group__38020 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__4_in_rule__Vwkpaktionkonfiguraktion__Group__38023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rule__Vwkpaktionkonfiguraktion__Group__3__Impl8050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__4__Impl_in_rule__Vwkpaktionkonfiguraktion__Group__48079 = new BitSet(new long[]{0x000000FFFFF00000L});
    public static final BitSet FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__5_in_rule__Vwkpaktionkonfiguraktion__Group__48082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_314_in_rule__Vwkpaktionkonfiguraktion__Group__4__Impl8110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Vwkpaktionkonfiguraktion__Group__5__Impl_in_rule__Vwkpaktionkonfiguraktion__Group__58141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAKTION_in_rule__Vwkpaktionkonfiguraktion__Group__5__Impl8168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__0__Impl_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__08209 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__1_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__08212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__0__Impl8239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__1__Impl_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__18268 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__2_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__18271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_314_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__1__Impl8299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__2__Impl_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__28330 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__3_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__28333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__2__Impl8360 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group__3__Impl_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__38391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group__3__Impl8418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0__Impl_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__08455 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__08458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__0__Impl8485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1__Impl_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__18514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_315_in_rule__SPEZ_ANTRAGSZUWEISUNG__Group_2__1__Impl8542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__0__Impl_in_rule__PRUEFUNGSLANGTEXT__Group__08577 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__1_in_rule__PRUEFUNGSLANGTEXT__Group__08580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_316_in_rule__PRUEFUNGSLANGTEXT__Group__0__Impl8608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__1__Impl_in_rule__PRUEFUNGSLANGTEXT__Group__18639 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__2_in_rule__PRUEFUNGSLANGTEXT__Group__18642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rule__PRUEFUNGSLANGTEXT__Group__1__Impl8669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__2__Impl_in_rule__PRUEFUNGSLANGTEXT__Group__28698 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__3_in_rule__PRUEFUNGSLANGTEXT__Group__28701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_314_in_rule__PRUEFUNGSLANGTEXT__Group__2__Impl8729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSLANGTEXT__Group__3__Impl_in_rule__PRUEFUNGSLANGTEXT__Group__38760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__PRUEFUNGSLANGTEXT__Group__3__Impl8787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__0__Impl_in_rule__PRUEFUNGSKURZTEXT__Group__08824 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__1_in_rule__PRUEFUNGSKURZTEXT__Group__08827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_317_in_rule__PRUEFUNGSKURZTEXT__Group__0__Impl8855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__1__Impl_in_rule__PRUEFUNGSKURZTEXT__Group__18886 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__2_in_rule__PRUEFUNGSKURZTEXT__Group__18889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rule__PRUEFUNGSKURZTEXT__Group__1__Impl8916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__2__Impl_in_rule__PRUEFUNGSKURZTEXT__Group__28945 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__3_in_rule__PRUEFUNGSKURZTEXT__Group__28948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_314_in_rule__PRUEFUNGSKURZTEXT__Group__2__Impl8976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKURZTEXT__Group__3__Impl_in_rule__PRUEFUNGSKURZTEXT__Group__39007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__PRUEFUNGSKURZTEXT__Group__3__Impl9034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__0__Impl_in_rule__PRUEFUNGSKLASSENNAME__Group__09071 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__1_in_rule__PRUEFUNGSKLASSENNAME__Group__09074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_318_in_rule__PRUEFUNGSKLASSENNAME__Group__0__Impl9102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__1__Impl_in_rule__PRUEFUNGSKLASSENNAME__Group__19133 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__2_in_rule__PRUEFUNGSKLASSENNAME__Group__19136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rule__PRUEFUNGSKLASSENNAME__Group__1__Impl9163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__2__Impl_in_rule__PRUEFUNGSKLASSENNAME__Group__29192 = new BitSet(new long[]{0xFFFFFF0000000000L,0xFFFFFFFFFFFFFFFFL,0xFFFFFFFFFFFFFFFFL,0xFFFFFFFFFFFFFFFFL,0x003FFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__3_in_rule__PRUEFUNGSKLASSENNAME__Group__29195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_314_in_rule__PRUEFUNGSKLASSENNAME__Group__2__Impl9223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSKLASSENNAME__Group__3__Impl_in_rule__PRUEFUNGSKLASSENNAME__Group__39254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKLASSE_in_rule__PRUEFUNGSKLASSENNAME__Group__3__Impl9281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSAKTION__Group__0__Impl_in_rule__PRUEFUNGSAKTION__Group__09318 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSAKTION__Group__1_in_rule__PRUEFUNGSAKTION__Group__09321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_319_in_rule__PRUEFUNGSAKTION__Group__0__Impl9349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSAKTION__Group__1__Impl_in_rule__PRUEFUNGSAKTION__Group__19380 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSAKTION__Group__2_in_rule__PRUEFUNGSAKTION__Group__19383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAKTIONSID_in_rule__PRUEFUNGSAKTION__Group__1__Impl9410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSAKTION__Group__2__Impl_in_rule__PRUEFUNGSAKTION__Group__29439 = new BitSet(new long[]{0x000000FFFFF00000L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSAKTION__Group__3_in_rule__PRUEFUNGSAKTION__Group__29442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_314_in_rule__PRUEFUNGSAKTION__Group__2__Impl9470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSAKTION__Group__3__Impl_in_rule__PRUEFUNGSAKTION__Group__39501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAKTION_in_rule__PRUEFUNGSAKTION__Group__3__Impl9528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AKTIONSID__Group__0__Impl_in_rule__AKTIONSID__Group__09565 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_rule__AKTIONSID__Group__1_in_rule__AKTIONSID__Group__09568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rule__AKTIONSID__Group__0__Impl9595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AKTIONSID__Group__1__Impl_in_rule__AKTIONSID__Group__19624 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__AKTIONSID__Group__2_in_rule__AKTIONSID__Group__19627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_320_in_rule__AKTIONSID__Group__1__Impl9655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AKTIONSID__Group__2__Impl_in_rule__AKTIONSID__Group__29686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__AKTIONSID__Group__2__Impl9713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__0__Impl_in_rule__PRUEFUNGSWIRKUNG__Group__09748 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__1_in_rule__PRUEFUNGSWIRKUNG__Group__09751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_321_in_rule__PRUEFUNGSWIRKUNG__Group__0__Impl9779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__1__Impl_in_rule__PRUEFUNGSWIRKUNG__Group__19810 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__2_in_rule__PRUEFUNGSWIRKUNG__Group__19813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWIRKUNGSID_in_rule__PRUEFUNGSWIRKUNG__Group__1__Impl9840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__2__Impl_in_rule__PRUEFUNGSWIRKUNG__Group__29869 = new BitSet(new long[]{0x000000000000E000L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__3_in_rule__PRUEFUNGSWIRKUNG__Group__29872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_314_in_rule__PRUEFUNGSWIRKUNG__Group__2__Impl9900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PRUEFUNGSWIRKUNG__Group__3__Impl_in_rule__PRUEFUNGSWIRKUNG__Group__39931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWIRKUNG_in_rule__PRUEFUNGSWIRKUNG__Group__3__Impl9958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WIRKUNGSID__Group__0__Impl_in_rule__WIRKUNGSID__Group__09995 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_rule__WIRKUNGSID__Group__1_in_rule__WIRKUNGSID__Group__09998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rule__WIRKUNGSID__Group__0__Impl10025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WIRKUNGSID__Group__1__Impl_in_rule__WIRKUNGSID__Group__110054 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__WIRKUNGSID__Group__2_in_rule__WIRKUNGSID__Group__110057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_320_in_rule__WIRKUNGSID__Group__1__Impl10085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__WIRKUNGSID__Group__2__Impl_in_rule__WIRKUNGSID__Group__210116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__WIRKUNGSID__Group__2__Impl10143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Zuweisung__Group__0__Impl_in_rule__Zuweisung__Group__010178 = new BitSet(new long[]{0x00000000000F0000L});
    public static final BitSet FOLLOW_rule__Zuweisung__Group__1_in_rule__Zuweisung__Group__010181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_322_in_rule__Zuweisung__Group__0__Impl10209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Zuweisung__Group__1__Impl_in_rule__Zuweisung__Group__110240 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_rule__Zuweisung__Group__2_in_rule__Zuweisung__Group__110243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleANTRAGSART_in_rule__Zuweisung__Group__1__Impl10270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Zuweisung__Group__2__Impl_in_rule__Zuweisung__Group__210299 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Zuweisung__Group__3_in_rule__Zuweisung__Group__210302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_314_in_rule__Zuweisung__Group__2__Impl10330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Zuweisung__Group__3__Impl_in_rule__Zuweisung__Group__310361 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Zuweisung__Group__4_in_rule__Zuweisung__Group__310364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Zuweisung__Group_3__0_in_rule__Zuweisung__Group__3__Impl10391 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_rule__Zuweisung__Group__4__Impl_in_rule__Zuweisung__Group__410422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rule__Zuweisung__Group__4__Impl10449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Zuweisung__Group_3__0__Impl_in_rule__Zuweisung__Group_3__010488 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_rule__Zuweisung__Group_3__1_in_rule__Zuweisung__Group_3__010491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rule__Zuweisung__Group_3__0__Impl10518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Zuweisung__Group_3__1__Impl_in_rule__Zuweisung__Group_3__110547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_315_in_rule__Zuweisung__Group_3__1__Impl10575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UsedIDs__Group__0__Impl_in_rule__UsedIDs__Group__010610 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__UsedIDs__Group__1_in_rule__UsedIDs__Group__010613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_323_in_rule__UsedIDs__Group__0__Impl10641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UsedIDs__Group__1__Impl_in_rule__UsedIDs__Group__110672 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__UsedIDs__Group__2_in_rule__UsedIDs__Group__110675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UsedIDs__Group_1__0_in_rule__UsedIDs__Group__1__Impl10702 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_rule__UsedIDs__Group__2__Impl_in_rule__UsedIDs__Group__210733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rule__UsedIDs__Group__2__Impl10760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UsedIDs__Group_1__0__Impl_in_rule__UsedIDs__Group_1__010795 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_rule__UsedIDs__Group_1__1_in_rule__UsedIDs__Group_1__010798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNG_in_rule__UsedIDs__Group_1__0__Impl10825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__UsedIDs__Group_1__1__Impl_in_rule__UsedIDs__Group_1__110854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_315_in_rule__UsedIDs__Group_1__1__Impl10882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUsedIDs_in_rule__Konfiguration__UsedidsAssignment_010922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSPEZ_ANTRAGSZUWEISUNG_in_rule__Konfiguration__SpezantragszuweisungAssignment_110953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleZuweisung_in_rule__Konfiguration__AntragszuweisungAssignment_210984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulevwkpaktionkonfiguraktion_in_rule__Konfiguration__VwkpkonfigurationfueraktionAssignment_3_011015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSAKTION_in_rule__Konfiguration__PruefungsaktionAssignment_3_111046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSKLASSENNAME_in_rule__Konfiguration__PruefungsklassennameAssignment_3_211077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSKURZTEXT_in_rule__Konfiguration__PruefungskurzbezeichnungAssignment_3_311108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSLANGTEXT_in_rule__Konfiguration__PruefungslangtextAssignment_3_411139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSWIRKUNG_in_rule__Konfiguration__PruefungswirkungAssignment_3_511170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePRUEFUNGSICHTBARKEIT_in_rule__Konfiguration__PruefungsichtbarkeitAssignment_3_611201 = new BitSet(new long[]{0x0000000000000002L});

}