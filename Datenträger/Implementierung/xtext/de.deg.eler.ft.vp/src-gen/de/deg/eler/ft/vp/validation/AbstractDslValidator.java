/*
 * generated by Xtext
 */
package de.deg.eler.ft.vp.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;

public class AbstractDslValidator extends org.eclipse.xtext.validation.AbstractDeclarativeValidator {

	@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(de.deg.eler.ft.vp.dsl.DslPackage.eINSTANCE);
		return result;
	}
}
