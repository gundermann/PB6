package de.deg.eler.ft.vp.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.deg.eler.ft.vp.dsl.DslPackage;
import de.deg.eler.ft.vp.dsl.Konfiguration;
import de.deg.eler.ft.vp.services.DslGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;

@SuppressWarnings("all")
public class DslSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private DslGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == DslPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case DslPackage.KONFIGURATION:
				if(context == grammarAccess.getKonfigurationRule()) {
					sequence_Konfiguration(context, (Konfiguration) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (
	 *         usedids+=UsedIDs 
	 *         spezantragszuweisung+=SPEZ_ANTRAGSZUWEISUNG* 
	 *         antragszuweisung+=Zuweisung* 
	 *         (
	 *             vwkpkonfigurationfueraktion+=vwkpaktionkonfiguraktion | 
	 *             pruefungsaktion+=PRUEFUNGSAKTION | 
	 *             pruefungsklassenname+=PRUEFUNGSKLASSENNAME | 
	 *             pruefungskurzbezeichnung+=PRUEFUNGSKURZTEXT | 
	 *             pruefungslangtext+=PRUEFUNGSLANGTEXT | 
	 *             pruefungswirkung+=PRUEFUNGSWIRKUNG | 
	 *             pruefungsichtbarkeit+=PRUEFUNGSICHTBARKEIT
	 *         )*
	 *     )
	 */
	protected void sequence_Konfiguration(EObject context, Konfiguration semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
