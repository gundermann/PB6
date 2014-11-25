package de.deg.eler.ft.vp.validation

import de.deg.eler.ft.vp.dsl.DslPackage
import de.deg.eler.ft.vp.dsl.Konfiguration
import java.util.ArrayList
import java.util.HashMap
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.validation.Check

/**
 * Custom validation rules. 
 *
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
class DslValidator extends AbstractDslValidator {

	String WARNUNG_KEINE_LAUFENDE_NUMMER = "Bei der laufenden Nummer für die Definition der %s wurde bei der Prüfung %s die Nummer %s übersprungen";

	String WARNUNG_KEINE_ID_DEKLARIERT = "Die Prüfung %s wurde nicht deklariert."

	String WARNUNG_ID_NICHT_VERWENDET = "Die ID %s wird bei der Definition der %s nicht verwendet."

	String WARUNUNG_DOPPELT_GENUTZTE_PRUEFUNG = "%s ist für die Prüfung %s schon konfiguriert."

	String WARNUNG_PRUEFUNG_KEINEM_ANTRAG_ZUGEWIESEN = "Die Prüfung %s wurde keinem Antrag zugewiesen."

	@Check
	def checkIdsFuerKurztextDeklariert(Konfiguration konfiguration) {
		checkIdsDeklariert(konfiguration.pruefungskurztext, konfiguration.usedids.get(0),
			DslPackage.Literals.KONFIGURATION__PRUEFUNGSKURZBEZEICHNUNG)
	}

	@Check
	def checkIdsFuerLangtextDeklariert(Konfiguration konfiguration) {
		checkIdsDeklariert(konfiguration.pruefungslangtext, konfiguration.usedids.get(0),
			DslPackage.Literals.KONFIGURATION__PRUEFUNGSLANGTEXT)
	}

	@Check
	def checkIdsFuerAktionDeklariert(Konfiguration konfiguration) {
		checkIdsDeklariert(konfiguration.pruefungsaktion, konfiguration.usedids.get(0),
			DslPackage.Literals.KONFIGURATION__PRUEFUNGSAKTION)
	}

	@Check
	def checkIdsFuerKlassennameDeklariert(Konfiguration konfiguration) {
		checkIdsDeklariert(konfiguration.pruefungsklassenname, konfiguration.usedids.get(0),
			DslPackage.Literals.KONFIGURATION__PRUEFUNGSKLASSENNAME)
	}

	@Check
	def checkIdsFuerWirkungDeklariert(Konfiguration konfiguration) {
		checkIdsDeklariert(konfiguration.pruefungswirkung, konfiguration.usedids.get(0),
			DslPackage.Literals.KONFIGURATION__PRUEFUNGSWIRKUNG)
	}

	@Check
	def checkIdsFuerAntragszuweisungDeklariert(Konfiguration konfiguration) {
		checkIdsVonZuweisungDeklariert(konfiguration.spezantragszuweisung, konfiguration.usedids.get(0),
			DslPackage.Literals.KONFIGURATION__SPEZANTRAGSZUWEISUNG)
	}

	@Check
	def checkIdsFuerAntragsArtzuweisungDeklariert(Konfiguration konfiguration) {
		checkIdsVonZuweisungDeklariert(konfiguration.antragszuweisung, konfiguration.usedids.get(0),
			DslPackage.Literals.KONFIGURATION__ANTRAGSZUWEISUNG)
	}

	def checkIdsVonZuweisungDeklariert(EList<String> konfigurationen, String konfigFuerDeklarierteIDs,
		EStructuralFeature feature) {
		var index = 0
		for (konfiguration : konfigurationen) {
			var zugewiesenePruefungen = extrahiereDeklariertePruefungen(konfiguration)
			for (pruefung : zugewiesenePruefungen) {
				var deklariertePruefungen = extrahiereDeklariertePruefungen(konfigFuerDeklarierteIDs)
				if (!deklariertePruefungen.contains(pruefung.trim)) {
					warning(String.format(WARNUNG_KEINE_ID_DEKLARIERT, pruefung), feature, index)
				}
			}
				index++
		}
	}

	def checkIdsDeklariert(EList<String> konfigurationen, String konfigFuerDeklarierteIDs, EStructuralFeature feature) {
		var index = 0
		for (konfiguration : konfigurationen) {
			var pruefung = extrahierePruefung(konfiguration)
			var deklariertePruefungen = extrahiereDeklariertePruefungen(konfigFuerDeklarierteIDs)
			if (!deklariertePruefungen.contains(pruefung)) {
				warning(String.format(WARNUNG_KEINE_ID_DEKLARIERT, pruefung), feature, index)
			}
			index++
		}

	}

	def extrahiereDeklariertePruefungen(String konfigFuerDeklarierteIDs) {
		return konfigFuerDeklarierteIDs.substring(konfigFuerDeklarierteIDs.indexOf("=") + 1,
			konfigFuerDeklarierteIDs.length).split('\\,')
	}

	def extrahierePruefung(String konfigString) {
		extrahierePart(konfigString, 1)
	}

	def extrahierePart(String konfigString, Integer part) {
		var geteilteKonfig = konfigString.split('\\.')
		var zuExtrahierenderTeil = geteilteKonfig.get(part)
		if (zuExtrahierenderTeil.contains(" "))
			return zuExtrahierenderTeil.substring(0, zuExtrahierenderTeil.indexOf(" "))
		else
			return zuExtrahierenderTeil
	}

	@Check
	def checkLaufendeNummerWirkung(Konfiguration konfiguration) {
		checkFehltZifferInLaufenderNummer(
			extrahierePruefungZuLaufendeNummerMapFuerEigenschaft(konfiguration.pruefungswirkung), "Wirkung",
			DslPackage.Literals.KONFIGURATION__PRUEFUNGSWIRKUNG);
	}

	@Check
	def checkLaufendeNummerAktion(Konfiguration konfiguration) {
		checkFehltZifferInLaufenderNummer(
			extrahierePruefungZuLaufendeNummerMapFuerEigenschaft(konfiguration.pruefungsaktion), "Aktion",
			DslPackage.Literals.KONFIGURATION__PRUEFUNGSAKTION);
	}

	def extrahierePruefungZuLaufendeNummerMapFuerEigenschaft(EList<String> eigenschaften) {
		var pruefungZuEigenschaftMap = new HashMap<String, ArrayList<Integer>>()
		for (eigenschaft : eigenschaften) {
			var pruefung = extrahierePruefung(eigenschaft)
			var laufendeNummer = extrahiereLaufendeNummer(eigenschaft)
			var listeMitLaufenderNummer = new ArrayList<Integer>()
			if (!pruefungZuEigenschaftMap.containsKey(pruefung)) {
			} else {
				listeMitLaufenderNummer = pruefungZuEigenschaftMap.get(pruefung)
			}
			listeMitLaufenderNummer.add(Integer.parseInt(laufendeNummer))
			pruefungZuEigenschaftMap.put(pruefung, listeMitLaufenderNummer)
		}
		return pruefungZuEigenschaftMap
	}

	def extrahiereLaufendeNummer(String konfigString) {
		extrahierePart(konfigString, 2)
	}

	def checkFehltZifferInLaufenderNummer(HashMap<String, ArrayList<Integer>> map, String eigenschaft,
		EStructuralFeature feature) {
		var index = 0
		for (pruefung : map.keySet) {
			var nummern = map.get(pruefung)
			for (var ln = 1; ln < nummern.size; ln++) {
				if (!nummern.contains(ln)) {
					warning(String.format(WARNUNG_KEINE_LAUFENDE_NUMMER, eigenschaft, pruefung, ln), feature, index)
				}
				index++
			}
		}
	}

	@Check
	def checkIdVerwendung(Konfiguration konfig) {
		var deklariertePruefungen = extrahiereDeklariertePruefungen(konfig.usedids.get(0))
		var index = 0;
		for (String pruefung : deklariertePruefungen) {
			checkVerwendungDerPruefung(konfig.pruefungsaktion, pruefung, "PruefungAktion", index)
			checkVerwendungDerPruefung(konfig.pruefungsklassenname, pruefung, "PruefungKlassenname", index)
			checkVerwendungDerPruefung(konfig.pruefungskurztext, pruefung, "PruefungKurztext", index)
			checkVerwendungDerPruefung(konfig.pruefungslangtext, pruefung, "PruefungLangtext", index)
			checkVerwendungDerPruefung(konfig.pruefungswirkung, pruefung, "PruefungWirkung", index)
			checkDoppelteVerwendungDerPruefung(konfig.pruefungsklassenname, pruefung,
				DslPackage.Literals.KONFIGURATION__PRUEFUNGSKLASSENNAME)
			checkDoppelteVerwendungDerPruefung(konfig.pruefungskurztext, pruefung,
				DslPackage.Literals.KONFIGURATION__PRUEFUNGSKURZBEZEICHNUNG)
			checkDoppelteVerwendungDerPruefung(konfig.pruefungslangtext, pruefung,
				DslPackage.Literals.KONFIGURATION__PRUEFUNGSLANGTEXT)
			checkVerwendungInAntrag(konfig.antragszuweisung, konfig.spezantragszuweisung, pruefung)
		}
	}

	def checkVerwendungInAntrag(EList<String> zuweisungenZuAntragsart, EList<String> zuweisungenZuAntrag,
		String pruefung) {
		for (antragsartZuweisung : zuweisungenZuAntragsart) {
			var zugewiesenePreufungen = extrahiereDeklariertePruefungen(antragsartZuweisung)
			if (zugewiesenePreufungen.contains(pruefung)) {
				return
			}
		}
		for (antragsZuweisung : zuweisungenZuAntrag) {
			var zugewiesenePreufungen = extrahiereDeklariertePruefungen(antragsZuweisung)
			if (zugewiesenePreufungen.contains(pruefung)) {
				return
			}
		}
		warning(String.format(WARNUNG_PRUEFUNG_KEINEM_ANTRAG_ZUGEWIESEN, pruefung),
			DslPackage.Literals.KONFIGURATION__USEDIDS)

	}

	def checkDoppelteVerwendungDerPruefung(EList<String> list, String pruefung, EAttribute attribute) {
		var index = 0
		var gefundeneKonfigurationen = 0
		for (konfiguration : list) {
			var pruefungBenutzt = extrahierePruefung(konfiguration)
			if (pruefungBenutzt.equals(pruefung)) {
				if (gefundeneKonfigurationen == 0)
					gefundeneKonfigurationen = 1
				else
					warning(String.format(WARUNUNG_DOPPELT_GENUTZTE_PRUEFUNG, attribute.name, pruefung), attribute,
						index)
			}
			index++
		}
	}

	def checkVerwendungDerPruefung(EList<String> eigenschaften, String pruefung, String eigenschaftsBezeichnung,
		int index) {
		for (eigenschaft : eigenschaften) {
		}
		warning(String.format(WARNUNG_ID_NICHT_VERWENDET, pruefung, eigenschaftsBezeichnung),
			DslPackage.Literals.KONFIGURATION__USEDIDS, index)
	}

}
