/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensicaen.yousign;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author dp
 */
public class OrdreMissionUtil 
{
    private OrdreMission ordreMission ;
    private String pdfFileName ;        // Nom absolu du fichier pdf à utiliser
    private ArrayList<String> status ;  // Messages d'erreurs éventuels si le formulaire est mal rempli


    public OrdreMissionUtil(OrdreMission ordreMission) {
        this.ordreMission = ordreMission;
    }
    
    public ArrayList<String> getStatus() {
        return status;
    }

    public OrdreMission getOrdreMission() {
        return ordreMission;
    }

    public void setOrdreMission(OrdreMission ordreMission) {
        this.ordreMission = ordreMission;
    }
        
    /**
     *  Recherche si un moyen de locomotion est sollicité
     *  moyensLocomotion contient tous les moyens demandés.
     * 
     * @param moyenLocomotion Valeurs possibles: "Passager", "Train", "Avion_Bateau", "Vehicule_Service", "Vehicule_Personnel"
     * @return true si le moyen de locomotion est sollicité
     */
    public boolean use (String moyenLocomotion)
    {
        if (ordreMission.getMoyensLocomotion() == null) return false ; // Ne doit pas arriver, testé dans le formulaire
        for (String str : ordreMission.getMoyensLocomotion ()) 
            if (str.equalsIgnoreCase(moyenLocomotion)) return true ;
        return false ;
    }
    
    /**
     * Permet d'ajouter des tests d'intégrité non traités par le formulaire.
     * Les messages d'erreurs sont ajoutés dans l'objet status
     * @return si true, le pdf sera envoyé à la signature; si false il faudra revenir en arrière pour compléter le formulaire
     */
    public boolean validate ()
    {
        status = new ArrayList<> () ;
        
        //
        // On verifie que le centre financier saisi est correct
        //
        if (ordreMission.getNumResponsableFinancier() == -1)
        {
            status.add ("le centre financier est incorrect") ;
        }
        //
        // Verification du nombre de signatures, 2 au minimum
        //
        if (ordreMission.getEmailMissionnaire().equalsIgnoreCase(ordreMission.getEmailAutoriteHierarchique()) && ordreMission.getEmailMissionnaire().equals (ordreMission.getEmailResponsableFinancier()))
        {
            status.add ("le nombre de signataires est insuffisant") ;
        }
        //
        // Le missionnaire ne peut être l'autorite hierarchique
        //
        if (ordreMission.getEmailMissionnaire().equalsIgnoreCase(ordreMission.getEmailAutoriteHierarchique()))
        {
            status.add ("le missionnaire ne peut être l'autorité hierarchique") ;
        }
        //
        // Verification des dates
        //
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
                Date dateDepartAller = simpleDateFormat.parse(ordreMission.getDateDepartAller() + " " + ordreMission.getHeureDepartAller() + ":" + ordreMission.getMinuteDepartAller()) ;
                Date dateArriveeAller = simpleDateFormat.parse(ordreMission.getDateArriveeAller() + " " + ordreMission.getHeureArriveeAller() + ":" + ordreMission.getMinuteArriveeAller()) ;
                Date dateDepartRetour = simpleDateFormat.parse(ordreMission.getDateDepartRetour() + " " + ordreMission.getHeureDepartRetour() + ":" + ordreMission.getMinuteDepartRetour()) ;
                Date dateArriveeRetour = simpleDateFormat.parse(ordreMission.getDateArriveeRetour() + " " + ordreMission.getHeureArriveeRetour() + ":" + ordreMission.getMinuteArriveeRetour()) ;
                if (dateArriveeAller.before(dateDepartAller))
                {
                    status.add ("La date d'arrivée du voyage aller est antérieure à la date de départ") ;
                }
                if (dateArriveeRetour.before(dateDepartRetour))
                {
                    status.add ("La date d'arrivée du voyage retour est antérieure à la date de départ") ;
                }  
                if (dateDepartRetour.before(dateDepartAller))
                {
                    status.add ("La date de retour est antérieure à la date du départ") ;
                }
        }catch (ParseException e) {}
        if (status.isEmpty()) return true ;
        return false ;
    } 
    
    public String getPdfFileName() {
        return pdfFileName;
    }

    public void setPdfFileName(String pdfFileName) {
        this.pdfFileName = pdfFileName;
    }
    
    /**
     * Calcul le nom du fichier pdf sous la forme:
     * absolutePathPDF_NomMissionnaire_Prenom_Missionnaire_LieuDepart_LieuArrivee_DateDepartAller.pdf
     * absolutePathPDF est une constante definie dans YousignConfig.java
     * @return le nom absolu du fichier pdf à utiliser
     */
    public String generePDFFilenameToUse ()
    {
        if (this.pdfFileName == null) this.pdfFileName=  YousignConfig.ABSOLUTE_PATH_PDF + cleanString (ordreMission.getNomMissionnaire() + "_" + ordreMission.getPrenomMissionnaire() + "_" + ordreMission.getLieuDepartAller() + "_" + ordreMission.getLieuArriveeAller() + "_" + ordreMission.getDateDepartAller() + ".pdf") ;       
        return this.pdfFileName ;
    }
    
    // 
    // Remplace les espaces et les / par des _
    //
    private String cleanString (String chaine)
    {
        if (chaine == null) return "" ;
        return chaine.replace(' ', '_').replace('/', '_') ;
    }
    

}
