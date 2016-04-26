/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensicaen.yousign;

/**
 *
 * @author dp
 */
public class CentreFinancier implements Comparable
{
    private String libelle ;
    private String libelleLong ;
    Responsable responsableFinancier ;

    public CentreFinancier(String libelle, String libelleLong, Responsable responsableFinancier) {
        this.libelle = libelle;
        this.libelleLong = libelleLong;
        this.responsableFinancier = responsableFinancier;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelleLong() {
        return libelleLong;
    }

    public void setLibelleLong(String libelleLong) {
        this.libelleLong = libelleLong;
    }

    public Responsable getResponsableFinancier() {
        return responsableFinancier;
    }

    public void setResponsableFinancier(Responsable responsableFinancier) {
        this.responsableFinancier = responsableFinancier;
    }

    @Override
    public String toString() {
        return "CentreFinancier{" + "libelle=" + libelle + ", libelleLong=" + libelleLong + ", responsableFinancier=" + responsableFinancier + '}';
    }
   
    @Override
    public int compareTo(Object o) {
        if (o instanceof CentreFinancier)
        {
            CentreFinancier centre = (CentreFinancier) o ;
            return this.libelle.compareToIgnoreCase(centre.getLibelle ()) ;
        }
        return 0 ;
    }
    
}
