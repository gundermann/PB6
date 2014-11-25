/**
 */
package de.deg.eler.ft.vp.dsl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.deg.eler.ft.vp.dsl.DslFactory
 * @model kind="package"
 * @generated
 */
public interface DslPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "dsl";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.deg.de/eler/ft/vp/Dsl";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "dsl";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DslPackage eINSTANCE = de.deg.eler.ft.vp.dsl.impl.DslPackageImpl.init();

  /**
   * The meta object id for the '{@link de.deg.eler.ft.vp.dsl.impl.KonfigurationImpl <em>Konfiguration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.deg.eler.ft.vp.dsl.impl.KonfigurationImpl
   * @see de.deg.eler.ft.vp.dsl.impl.DslPackageImpl#getKonfiguration()
   * @generated
   */
  int KONFIGURATION = 0;

  /**
   * The feature id for the '<em><b>Usedids</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KONFIGURATION__USEDIDS = 0;

  /**
   * The feature id for the '<em><b>Spezantragszuweisung</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KONFIGURATION__SPEZANTRAGSZUWEISUNG = 1;

  /**
   * The feature id for the '<em><b>Antragszuweisung</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KONFIGURATION__ANTRAGSZUWEISUNG = 2;

  /**
   * The feature id for the '<em><b>Vwkpkonfigurationfueraktion</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KONFIGURATION__VWKPKONFIGURATIONFUERAKTION = 3;

  /**
   * The feature id for the '<em><b>Pruefungsaktion</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KONFIGURATION__PRUEFUNGSAKTION = 4;

  /**
   * The feature id for the '<em><b>Pruefungsklassenname</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KONFIGURATION__PRUEFUNGSKLASSENNAME = 5;

  /**
   * The feature id for the '<em><b>Pruefungskurzbezeichnung</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KONFIGURATION__PRUEFUNGSKURZBEZEICHNUNG = 6;

  /**
   * The feature id for the '<em><b>Pruefungslangtext</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KONFIGURATION__PRUEFUNGSLANGTEXT = 7;

  /**
   * The feature id for the '<em><b>Pruefungswirkung</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KONFIGURATION__PRUEFUNGSWIRKUNG = 8;

  /**
   * The number of structural features of the '<em>Konfiguration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KONFIGURATION_FEATURE_COUNT = 9;


  /**
   * Returns the meta object for class '{@link de.deg.eler.ft.vp.dsl.Konfiguration <em>Konfiguration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Konfiguration</em>'.
   * @see de.deg.eler.ft.vp.dsl.Konfiguration
   * @generated
   */
  EClass getKonfiguration();

  /**
   * Returns the meta object for the attribute list '{@link de.deg.eler.ft.vp.dsl.Konfiguration#getUsedids <em>Usedids</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Usedids</em>'.
   * @see de.deg.eler.ft.vp.dsl.Konfiguration#getUsedids()
   * @see #getKonfiguration()
   * @generated
   */
  EAttribute getKonfiguration_Usedids();

  /**
   * Returns the meta object for the attribute list '{@link de.deg.eler.ft.vp.dsl.Konfiguration#getSpezantragszuweisung <em>Spezantragszuweisung</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Spezantragszuweisung</em>'.
   * @see de.deg.eler.ft.vp.dsl.Konfiguration#getSpezantragszuweisung()
   * @see #getKonfiguration()
   * @generated
   */
  EAttribute getKonfiguration_Spezantragszuweisung();

  /**
   * Returns the meta object for the attribute list '{@link de.deg.eler.ft.vp.dsl.Konfiguration#getAntragszuweisung <em>Antragszuweisung</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Antragszuweisung</em>'.
   * @see de.deg.eler.ft.vp.dsl.Konfiguration#getAntragszuweisung()
   * @see #getKonfiguration()
   * @generated
   */
  EAttribute getKonfiguration_Antragszuweisung();

  /**
   * Returns the meta object for the attribute list '{@link de.deg.eler.ft.vp.dsl.Konfiguration#getVwkpkonfigurationfueraktion <em>Vwkpkonfigurationfueraktion</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Vwkpkonfigurationfueraktion</em>'.
   * @see de.deg.eler.ft.vp.dsl.Konfiguration#getVwkpkonfigurationfueraktion()
   * @see #getKonfiguration()
   * @generated
   */
  EAttribute getKonfiguration_Vwkpkonfigurationfueraktion();

  /**
   * Returns the meta object for the attribute list '{@link de.deg.eler.ft.vp.dsl.Konfiguration#getPruefungsaktion <em>Pruefungsaktion</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Pruefungsaktion</em>'.
   * @see de.deg.eler.ft.vp.dsl.Konfiguration#getPruefungsaktion()
   * @see #getKonfiguration()
   * @generated
   */
  EAttribute getKonfiguration_Pruefungsaktion();

  /**
   * Returns the meta object for the attribute list '{@link de.deg.eler.ft.vp.dsl.Konfiguration#getPruefungsklassenname <em>Pruefungsklassenname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Pruefungsklassenname</em>'.
   * @see de.deg.eler.ft.vp.dsl.Konfiguration#getPruefungsklassenname()
   * @see #getKonfiguration()
   * @generated
   */
  EAttribute getKonfiguration_Pruefungsklassenname();

  /**
   * Returns the meta object for the attribute list '{@link de.deg.eler.ft.vp.dsl.Konfiguration#getPruefungskurzbezeichnung <em>Pruefungskurzbezeichnung</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Pruefungskurzbezeichnung</em>'.
   * @see de.deg.eler.ft.vp.dsl.Konfiguration#getPruefungstext()
   * @see #getKonfiguration()
   * @generated
   */
  EAttribute getKonfiguration_Pruefungskurzbezeichnung();

  /**
   * Returns the meta object for the attribute list '{@link de.deg.eler.ft.vp.dsl.Konfiguration#getPruefungslangtext <em>Pruefungslangtext</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Pruefungslangtext</em>'.
   * @see de.deg.eler.ft.vp.dsl.Konfiguration#getPruefungslangtext()
   * @see #getKonfiguration()
   * @generated
   */
  EAttribute getKonfiguration_Pruefungslangtext();

  /**
   * Returns the meta object for the attribute list '{@link de.deg.eler.ft.vp.dsl.Konfiguration#getPruefungswirkung <em>Pruefungswirkung</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Pruefungswirkung</em>'.
   * @see de.deg.eler.ft.vp.dsl.Konfiguration#getPruefungswirkung()
   * @see #getKonfiguration()
   * @generated
   */
  EAttribute getKonfiguration_Pruefungswirkung();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  DslFactory getDslFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link de.deg.eler.ft.vp.dsl.impl.KonfigurationImpl <em>Konfiguration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.deg.eler.ft.vp.dsl.impl.KonfigurationImpl
     * @see de.deg.eler.ft.vp.dsl.impl.DslPackageImpl#getKonfiguration()
     * @generated
     */
    EClass KONFIGURATION = eINSTANCE.getKonfiguration();

    /**
     * The meta object literal for the '<em><b>Usedids</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute KONFIGURATION__USEDIDS = eINSTANCE.getKonfiguration_Usedids();

    /**
     * The meta object literal for the '<em><b>Spezantragszuweisung</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute KONFIGURATION__SPEZANTRAGSZUWEISUNG = eINSTANCE.getKonfiguration_Spezantragszuweisung();

    /**
     * The meta object literal for the '<em><b>Antragszuweisung</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute KONFIGURATION__ANTRAGSZUWEISUNG = eINSTANCE.getKonfiguration_Antragszuweisung();

    /**
     * The meta object literal for the '<em><b>Vwkpkonfigurationfueraktion</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute KONFIGURATION__VWKPKONFIGURATIONFUERAKTION = eINSTANCE.getKonfiguration_Vwkpkonfigurationfueraktion();

    /**
     * The meta object literal for the '<em><b>Pruefungsaktion</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute KONFIGURATION__PRUEFUNGSAKTION = eINSTANCE.getKonfiguration_Pruefungsaktion();

    /**
     * The meta object literal for the '<em><b>Pruefungsklassenname</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute KONFIGURATION__PRUEFUNGSKLASSENNAME = eINSTANCE.getKonfiguration_Pruefungsklassenname();

    /**
     * The meta object literal for the '<em><b>Pruefungskurzbezeichnung</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute KONFIGURATION__PRUEFUNGSKURZBEZEICHNUNG = eINSTANCE.getKonfiguration_Pruefungskurzbezeichnung();

    /**
     * The meta object literal for the '<em><b>Pruefungslangtext</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute KONFIGURATION__PRUEFUNGSLANGTEXT = eINSTANCE.getKonfiguration_Pruefungslangtext();

    /**
     * The meta object literal for the '<em><b>Pruefungswirkung</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute KONFIGURATION__PRUEFUNGSWIRKUNG = eINSTANCE.getKonfiguration_Pruefungswirkung();

  }

} //DslPackage
