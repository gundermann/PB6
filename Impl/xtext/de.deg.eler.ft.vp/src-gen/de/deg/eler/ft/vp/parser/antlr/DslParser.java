/*
* generated by Xtext
*/
package de.deg.eler.ft.vp.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import de.deg.eler.ft.vp.services.DslGrammarAccess;

public class DslParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private DslGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected de.deg.eler.ft.vp.parser.antlr.internal.InternalDslParser createParser(XtextTokenStream stream) {
		return new de.deg.eler.ft.vp.parser.antlr.internal.InternalDslParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "Konfiguration";
	}
	
	public DslGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(DslGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}