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
public class Responsable extends Personne 
{
    private int numero ;
    private boolean actif ;

    public Responsable(int numero, String nom, String prenom, String telephone, String courriel, boolean actif) {
        super(nom, prenom, telephone, courriel);
        this.numero = numero;
        this.actif = actif;
    }
    
    public Responsable (int numero, Personne personne, boolean actif)
    {
        super (personne) ;
        this.numero = numero ;
        this.actif = actif ;
    }

    public Responsable (Responsable responsable)
    {
        super (responsable) ;
        this.numero = responsable.numero ;
        this.actif = responsable.actif ;
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    @Override
    public String toString() {
        return "Responsable{" + super.toString() + "numero=" + numero + ", actif=" + actif + '}';
    }
}
