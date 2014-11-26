/**
 */
package de.deg.eler.ft.vp.dsl.impl;

import de.deg.eler.ft.vp.dsl.DslPackage;
import de.deg.eler.ft.vp.dsl.Konfiguration;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Konfiguration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.deg.eler.ft.vp.dsl.impl.KonfigurationImpl#getUsedids <em>Usedids</em>}</li>
 *   <li>{@link de.deg.eler.ft.vp.dsl.impl.KonfigurationImpl#getSpezantragszuweisung <em>Spezantragszuweisung</em>}</li>
 *   <li>{@link de.deg.eler.ft.vp.dsl.impl.KonfigurationImpl#getAntragszuweisung <em>Antragszuweisung</em>}</li>
 *   <li>{@link de.deg.eler.ft.vp.dsl.impl.KonfigurationImpl#getVwkpkonfigurationfueraktion <em>Vwkpkonfigurationfueraktion</em>}</li>
 *   <li>{@link de.deg.eler.ft.vp.dsl.impl.KonfigurationImpl#getPruefungsaktion <em>Pruefungsaktion</em>}</li>
 *   <li>{@link de.deg.eler.ft.vp.dsl.impl.KonfigurationImpl#getPruefungsklassenname <em>Pruefungsklassenname</em>}</li>
 *   <li>{@link de.deg.eler.ft.vp.dsl.impl.KonfigurationImpl#getPruefungskurzbezeichnung <em>Pruefungskurzbezeichnung</em>}</li>
 *   <li>{@link de.deg.eler.ft.vp.dsl.impl.KonfigurationImpl#getPruefungslangtext <em>Pruefungslangtext</em>}</li>
 *   <li>{@link de.deg.eler.ft.vp.dsl.impl.KonfigurationImpl#getPruefungswirkung <em>Pruefungswirkung</em>}</li>
 *   <li>{@link de.deg.eler.ft.vp.dsl.impl.KonfigurationImpl#getPruefungsichtbarkeit <em>Pruefungsichtbarkeit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KonfigurationImpl extends MinimalEObjectImpl.Container implements Konfiguration
{
  /**
   * The cached value of the '{@link #getUsedids() <em>Usedids</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUsedids()
   * @generated
   * @ordered
   */
  protected EList<String> usedids;

  /**
   * The cached value of the '{@link #getSpezantragszuweisung() <em>Spezantragszuweisung</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSpezantragszuweisung()
   * @generated
   * @ordered
   */
  protected EList<String> spezantragszuweisung;

  /**
   * The cached value of the '{@link #getAntragszuweisung() <em>Antragszuweisung</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAntragszuweisung()
   * @generated
   * @ordered
   */
  protected EList<String> antragszuweisung;

  /**
   * The cached value of the '{@link #getVwkpkonfigurationfueraktion() <em>Vwkpkonfigurationfueraktion</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVwkpkonfigurationfueraktion()
   * @generated
   * @ordered
   */
  protected EList<String> vwkpkonfigurationfueraktion;

  /**
   * The cached value of the '{@link #getPruefungsaktion() <em>Pruefungsaktion</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPruefungsaktion()
   * @generated
   * @ordered
   */
  protected EList<String> pruefungsaktion;

  /**
   * The cached value of the '{@link #getPruefungsklassenname() <em>Pruefungsklassenname</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPruefungsklassenname()
   * @generated
   * @ordered
   */
  protected EList<String> pruefungsklassenname;

  /**
   * The cached value of the '{@link #getPruefungskurzbezeichnung() <em>Pruefungskurzbezeichnung</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPruefungskurzbezeichnung()
   * @generated
   * @ordered
   */
  protected EList<String> pruefungskurzbezeichnung;

  /**
   * The cached value of the '{@link #getPruefungslangtext() <em>Pruefungslangtext</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPruefungslangtext()
   * @generated
   * @ordered
   */
  protected EList<String> pruefungslangtext;

  /**
   * The cached value of the '{@link #getPruefungswirkung() <em>Pruefungswirkung</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPruefungswirkung()
   * @generated
   * @ordered
   */
  protected EList<String> pruefungswirkung;

  /**
   * The cached value of the '{@link #getPruefungsichtbarkeit() <em>Pruefungsichtbarkeit</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPruefungsichtbarkeit()
   * @generated
   * @ordered
   */
  protected EList<String> pruefungsichtbarkeit;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected KonfigurationImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return DslPackage.Literals.KONFIGURATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getUsedids()
  {
    if (usedids == null)
    {
      usedids = new EDataTypeEList<String>(String.class, this, DslPackage.KONFIGURATION__USEDIDS);
    }
    return usedids;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getSpezantragszuweisung()
  {
    if (spezantragszuweisung == null)
    {
      spezantragszuweisung = new EDataTypeEList<String>(String.class, this, DslPackage.KONFIGURATION__SPEZANTRAGSZUWEISUNG);
    }
    return spezantragszuweisung;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getAntragszuweisung()
  {
    if (antragszuweisung == null)
    {
      antragszuweisung = new EDataTypeEList<String>(String.class, this, DslPackage.KONFIGURATION__ANTRAGSZUWEISUNG);
    }
    return antragszuweisung;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getVwkpkonfigurationfueraktion()
  {
    if (vwkpkonfigurationfueraktion == null)
    {
      vwkpkonfigurationfueraktion = new EDataTypeEList<String>(String.class, this, DslPackage.KONFIGURATION__VWKPKONFIGURATIONFUERAKTION);
    }
    return vwkpkonfigurationfueraktion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getPruefungsaktion()
  {
    if (pruefungsaktion == null)
    {
      pruefungsaktion = new EDataTypeEList<String>(String.class, this, DslPackage.KONFIGURATION__PRUEFUNGSAKTION);
    }
    return pruefungsaktion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getPruefungsklassenname()
  {
    if (pruefungsklassenname == null)
    {
      pruefungsklassenname = new EDataTypeEList<String>(String.class, this, DslPackage.KONFIGURATION__PRUEFUNGSKLASSENNAME);
    }
    return pruefungsklassenname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getPruefungskurzbezeichnung()
  {
    if (pruefungskurzbezeichnung == null)
    {
      pruefungskurzbezeichnung = new EDataTypeEList<String>(String.class, this, DslPackage.KONFIGURATION__PRUEFUNGSKURZBEZEICHNUNG);
    }
    return pruefungskurzbezeichnung;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getPruefungslangtext()
  {
    if (pruefungslangtext == null)
    {
      pruefungslangtext = new EDataTypeEList<String>(String.class, this, DslPackage.KONFIGURATION__PRUEFUNGSLANGTEXT);
    }
    return pruefungslangtext;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getPruefungswirkung()
  {
    if (pruefungswirkung == null)
    {
      pruefungswirkung = new EDataTypeEList<String>(String.class, this, DslPackage.KONFIGURATION__PRUEFUNGSWIRKUNG);
    }
    return pruefungswirkung;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getPruefungsichtbarkeit()
  {
    if (pruefungsichtbarkeit == null)
    {
      pruefungsichtbarkeit = new EDataTypeEList<String>(String.class, this, DslPackage.KONFIGURATION__PRUEFUNGSICHTBARKEIT);
    }
    return pruefungsichtbarkeit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case DslPackage.KONFIGURATION__USEDIDS:
        return getUsedids();
      case DslPackage.KONFIGURATION__SPEZANTRAGSZUWEISUNG:
        return getSpezantragszuweisung();
      case DslPackage.KONFIGURATION__ANTRAGSZUWEISUNG:
        return getAntragszuweisung();
      case DslPackage.KONFIGURATION__VWKPKONFIGURATIONFUERAKTION:
        return getVwkpkonfigurationfueraktion();
      case DslPackage.KONFIGURATION__PRUEFUNGSAKTION:
        return getPruefungsaktion();
      case DslPackage.KONFIGURATION__PRUEFUNGSKLASSENNAME:
        return getPruefungsklassenname();
      case DslPackage.KONFIGURATION__PRUEFUNGSKURZBEZEICHNUNG:
        return getPruefungskurzbezeichnung();
      case DslPackage.KONFIGURATION__PRUEFUNGSLANGTEXT:
        return getPruefungslangtext();
      case DslPackage.KONFIGURATION__PRUEFUNGSWIRKUNG:
        return getPruefungswirkung();
      case DslPackage.KONFIGURATION__PRUEFUNGSICHTBARKEIT:
        return getPruefungsichtbarkeit();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case DslPackage.KONFIGURATION__USEDIDS:
        getUsedids().clear();
        getUsedids().addAll((Collection<? extends String>)newValue);
        return;
      case DslPackage.KONFIGURATION__SPEZANTRAGSZUWEISUNG:
        getSpezantragszuweisung().clear();
        getSpezantragszuweisung().addAll((Collection<? extends String>)newValue);
        return;
      case DslPackage.KONFIGURATION__ANTRAGSZUWEISUNG:
        getAntragszuweisung().clear();
        getAntragszuweisung().addAll((Collection<? extends String>)newValue);
        return;
      case DslPackage.KONFIGURATION__VWKPKONFIGURATIONFUERAKTION:
        getVwkpkonfigurationfueraktion().clear();
        getVwkpkonfigurationfueraktion().addAll((Collection<? extends String>)newValue);
        return;
      case DslPackage.KONFIGURATION__PRUEFUNGSAKTION:
        getPruefungsaktion().clear();
        getPruefungsaktion().addAll((Collection<? extends String>)newValue);
        return;
      case DslPackage.KONFIGURATION__PRUEFUNGSKLASSENNAME:
        getPruefungsklassenname().clear();
        getPruefungsklassenname().addAll((Collection<? extends String>)newValue);
        return;
      case DslPackage.KONFIGURATION__PRUEFUNGSKURZBEZEICHNUNG:
        getPruefungskurzbezeichnung().clear();
        getPruefungskurzbezeichnung().addAll((Collection<? extends String>)newValue);
        return;
      case DslPackage.KONFIGURATION__PRUEFUNGSLANGTEXT:
        getPruefungslangtext().clear();
        getPruefungslangtext().addAll((Collection<? extends String>)newValue);
        return;
      case DslPackage.KONFIGURATION__PRUEFUNGSWIRKUNG:
        getPruefungswirkung().clear();
        getPruefungswirkung().addAll((Collection<? extends String>)newValue);
        return;
      case DslPackage.KONFIGURATION__PRUEFUNGSICHTBARKEIT:
        getPruefungsichtbarkeit().clear();
        getPruefungsichtbarkeit().addAll((Collection<? extends String>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case DslPackage.KONFIGURATION__USEDIDS:
        getUsedids().clear();
        return;
      case DslPackage.KONFIGURATION__SPEZANTRAGSZUWEISUNG:
        getSpezantragszuweisung().clear();
        return;
      case DslPackage.KONFIGURATION__ANTRAGSZUWEISUNG:
        getAntragszuweisung().clear();
        return;
      case DslPackage.KONFIGURATION__VWKPKONFIGURATIONFUERAKTION:
        getVwkpkonfigurationfueraktion().clear();
        return;
      case DslPackage.KONFIGURATION__PRUEFUNGSAKTION:
        getPruefungsaktion().clear();
        return;
      case DslPackage.KONFIGURATION__PRUEFUNGSKLASSENNAME:
        getPruefungsklassenname().clear();
        return;
      case DslPackage.KONFIGURATION__PRUEFUNGSKURZBEZEICHNUNG:
        getPruefungskurzbezeichnung().clear();
        return;
      case DslPackage.KONFIGURATION__PRUEFUNGSLANGTEXT:
        getPruefungslangtext().clear();
        return;
      case DslPackage.KONFIGURATION__PRUEFUNGSWIRKUNG:
        getPruefungswirkung().clear();
        return;
      case DslPackage.KONFIGURATION__PRUEFUNGSICHTBARKEIT:
        getPruefungsichtbarkeit().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case DslPackage.KONFIGURATION__USEDIDS:
        return usedids != null && !usedids.isEmpty();
      case DslPackage.KONFIGURATION__SPEZANTRAGSZUWEISUNG:
        return spezantragszuweisung != null && !spezantragszuweisung.isEmpty();
      case DslPackage.KONFIGURATION__ANTRAGSZUWEISUNG:
        return antragszuweisung != null && !antragszuweisung.isEmpty();
      case DslPackage.KONFIGURATION__VWKPKONFIGURATIONFUERAKTION:
        return vwkpkonfigurationfueraktion != null && !vwkpkonfigurationfueraktion.isEmpty();
      case DslPackage.KONFIGURATION__PRUEFUNGSAKTION:
        return pruefungsaktion != null && !pruefungsaktion.isEmpty();
      case DslPackage.KONFIGURATION__PRUEFUNGSKLASSENNAME:
        return pruefungsklassenname != null && !pruefungsklassenname.isEmpty();
      case DslPackage.KONFIGURATION__PRUEFUNGSKURZBEZEICHNUNG:
        return pruefungskurzbezeichnung != null && !pruefungskurzbezeichnung.isEmpty();
      case DslPackage.KONFIGURATION__PRUEFUNGSLANGTEXT:
        return pruefungslangtext != null && !pruefungslangtext.isEmpty();
      case DslPackage.KONFIGURATION__PRUEFUNGSWIRKUNG:
        return pruefungswirkung != null && !pruefungswirkung.isEmpty();
      case DslPackage.KONFIGURATION__PRUEFUNGSICHTBARKEIT:
        return pruefungsichtbarkeit != null && !pruefungsichtbarkeit.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (usedids: ");
    result.append(usedids);
    result.append(", spezantragszuweisung: ");
    result.append(spezantragszuweisung);
    result.append(", antragszuweisung: ");
    result.append(antragszuweisung);
    result.append(", vwkpkonfigurationfueraktion: ");
    result.append(vwkpkonfigurationfueraktion);
    result.append(", pruefungsaktion: ");
    result.append(pruefungsaktion);
    result.append(", pruefungsklassenname: ");
    result.append(pruefungsklassenname);
    result.append(", pruefungskurzbezeichnung: ");
    result.append(pruefungskurzbezeichnung);
    result.append(", pruefungslangtext: ");
    result.append(pruefungslangtext);
    result.append(", pruefungswirkung: ");
    result.append(pruefungswirkung);
    result.append(", pruefungsichtbarkeit: ");
    result.append(pruefungsichtbarkeit);
    result.append(')');
    return result.toString();
  }

} //KonfigurationImpl
