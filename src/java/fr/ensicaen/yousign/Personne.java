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
public class Personne implements Comparable
{
    private String nom;
    private String prenom ;
    private String telephone ;
    private String courriel ;

    public Personne(String nom, String prenom, String telephone, String courriel) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.courriel = courriel;
    }

    public Personne (Personne personne)
    {
        this.nom = personne.nom ;
        this.prenom = personne.prenom ;
        this.telephone = personne.telephone ;
        this.courriel = personne.courriel ;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    @Override
    public String toString() {
        return "Personne{" + "nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", courriel=" + courriel + '}';
    }
    
    @Override
    public int compareTo(Object o) {
        if (o instanceof Personne)
        {
            Personne personne = (Personne) o ;
            return this.getNom().compareToIgnoreCase(personne.getNom()) ;
        }
        return 0 ;
    }
}
