/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensicaen.yousign;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dp
 */
public class OrdreMission 
{
   
    private String dateDemande ;
    private long numeroMission ;
    
    private String frais ;
    private String pays ;
    
    private String nomMissionnaire ;
    private String prenomMissionnaire ;
    private String rueMissionnaire = "" ;
    private String codepostalMissionnaire = "" ;
    private String villeMissionnaire = "" ;
    private String paysMissionnaire = "" ;
    private String telephoneMissionnaire ;
    private String emailMissionnaire ;
    
    private String iban1 = "" ;
    private String iban2 = "" ;
    private String iban3 = "" ;
    private String iban4 = "" ;
    private String iban5 = "" ;
    private String iban6 = "" ;
    private String iban7= ""  ;
    
    private String motifMission ;
    private String dateDepartAller ;
    private String lieuDepartAller ;
    private String heureDepartAller ;
    private String minuteDepartAller ;
    private String lieuArriveeAller ;
    private String dateArriveeAller ;
    private String heureArriveeAller ;
    private String minuteArriveeAller ;
    private String dateDepartRetour ;
    private String lieuDepartRetour ;
    private String heureDepartRetour ;
    private String minuteDepartRetour ;
    private String lieuArriveeRetour ;
    private String dateArriveeRetour ;
    private String heureArriveeRetour ;
    private String minuteArriveeRetour ;
    
    private String [] moyensLocomotion ;
    private String passager ;
    private String nomConducteur = "" ;
    private String trainClasse ;
    private String trainPrixBillet = "" ;
    private String trainBonCommande ;
    private String trainCarteAffaire ;  
    private String avionBateauClasse ;
    private String avionBateauPrixBillet = "" ;
    private String avionBateauBonCommande ;
    private String avionBateauCarteAffaire ;
    // private String taxi ;
    // private String locationVehicule ;
    private String vehiculeService ;
    private String vehiculeServiceNomPassagers = "" ;
    private String vehiculeServiceNombreKilometres = ""  ;
    private String vehiculePersonnelMarque = "" ;
    private String vehiculePersonnelPuissance = "" ;
    private String vehiculePersonnelImmatriculation = "" ;
    private String vehiculePersonnelDateAcquisition = "" ;
    private String vehiculePersonnelNomAssureur = "" ;
    private String vehiculeJustificatif = "" ;
    
    private String fraisInscription ;
    private String montantInscription = "---" ;
    private String bonCommandeInscription ;
    private String avance ;
    
    private int    numResponsableFinancier ;
    private String nomResponsableFinancier ;
    private String prenomResponsableFinancier ;
    private String telephoneResponsableFinancier ;
    private String emailResponsableFinancier ;
    
    private int    numAutoriteHierarchique ;
    private String nomAutoriteHierarchique ;
    private String prenomAutoriteHierarchique ;
    private String telephoneAutoriteHierarchique ;
    private String emailAutoriteHierarchique ;

    private String centreFinancier ;
    private String projet ;
    private String informationsComplementaires ;
    
    
    public OrdreMission ()
    {
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        dateDemande = output.format (new Date()) ;        
    }

    public long getNumeroMission() {
        return numeroMission;
    }

    public void setNumeroMission(long numeroMission) {
        this.numeroMission = numeroMission;
    }

    public String getFrais() {
        return frais;
    }

    public void setFrais(String frais) {
        this.frais = frais ;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getDateDemande() {
        return dateDemande;
    }
 
    public String getNomMissionnaire() {
        return nomMissionnaire;
    }

    public void setNomMissionnaire(String nomMissionnaire) {
        this.nomMissionnaire = nomMissionnaire;
    }

    public String getPrenomMissionnaire() {
        return prenomMissionnaire;
    }

    public void setPrenomMissionnaire(String prenomMissionnaire) {
        this.prenomMissionnaire = prenomMissionnaire;
    }

    public String getRueMissionnaire() {
        return rueMissionnaire;
    }

    public void setRueMissionnaire(String rueMissionnaire) {
         this.rueMissionnaire = rueMissionnaire;
    }

    public String getCodepostalMissionnaire() {
        return codepostalMissionnaire;
    }

    public void setCodepostalMissionnaire(String codepostalMissionnaire) {
        this.codepostalMissionnaire = codepostalMissionnaire;
    }

    public String getVilleMissionnaire() {
        return villeMissionnaire;
    }

    public void setVilleMissionnaire(String villeMissionnaire) {
         this.villeMissionnaire = villeMissionnaire;
    }

    public String getPaysMissionnaire() {
        return paysMissionnaire;
    }

    public void setPaysMissionnaire(String paysMissionnaire) {
         this.paysMissionnaire = paysMissionnaire;
    }

    public String getTelephoneMissionnaire() {
        return telephoneMissionnaire;
    }

    public void setTelephoneMissionnaire(String telephoneMissionnaire) {
        if (telephoneMissionnaire.startsWith("0")) telephoneMissionnaire = "+33" + telephoneMissionnaire.substring(1) ;
        this.telephoneMissionnaire = telephoneMissionnaire;
    }

    public String getEmailMissionnaire() {
        return emailMissionnaire;
    }

    public void setEmailMissionnaire(String emailMissionnaire) {
        this.emailMissionnaire = emailMissionnaire.toLowerCase();
    }

    public String getIban1() {
        return iban1;
    }

    public void setIban1(String iban1) {
        this.iban1 = iban1;
    }

    public String getIban2() {
        return iban2;
    }

    public void setIban2(String iban2) {
        this.iban2 = iban2;
    }

    public String getIban3() {
        return iban3;
    }

    public void setIban3(String iban3) {
        this.iban3 = iban3;
    }

    public String getIban4() {
        return iban4;
    }

    public void setIban4(String iban4) {
        this.iban4 = iban4;
    }

    public String getIban5() {
        return iban5;
    }

    public void setIban5(String iban5) {
        this.iban5 = iban5;
    }

    public String getIban6() {
        return iban6;
    }

    public void setIban6(String iban6) {
        this.iban6 = iban6;
    }

    public String getIban7() {
        return iban7;
    }

    public void setIban7(String iban7) {
        this.iban7 = iban7;
    }

    public String getMotifMission() {
        return motifMission;
    }

    public void setMotifMission(String motifMission) {
        this.motifMission = motifMission;
    }

    public String getDateDepartAller() {
        return dateDepartAller ;
    }

    public void setDateDepartAller(String dateDepartAller) {
        this.dateDepartAller = dateDepartAller;
    }

    public String getLieuDepartAller() {
        return lieuDepartAller;
    }

    public void setLieuDepartAller(String lieuDepartAller) {
        this.lieuDepartAller = lieuDepartAller;
    }

    public String getHeureDepartAller() {
        return heureDepartAller;
    }

    public void setHeureDepartAller(String heureDepartAller) {
        this.heureDepartAller = heureDepartAller;
    }

    public String getMinuteDepartAller() {
        return minuteDepartAller;
    }

    public void setMinuteDepartAller(String minuteDepartAller) {
        this.minuteDepartAller = minuteDepartAller;
    }

    public String getLieuArriveeAller() {
        return lieuArriveeAller;
    }

    public void setLieuArriveeAller(String lieuArriveeAller) {
        this.lieuArriveeAller = lieuArriveeAller;
    }

    public String getDateArriveeAller() {
        return dateArriveeAller ;
    }

    public void setDateArriveeAller(String dateArriveeAller) {
        this.dateArriveeAller = dateArriveeAller;
    }

    public String getHeureArriveeAller() {
        return heureArriveeAller;
    }

    public void setHeureArriveeAller(String heureArriveeAller) {
        this.heureArriveeAller = heureArriveeAller;
    }

    public String getMinuteArriveeAller() {
        return minuteArriveeAller;
    }

    public void setMinuteArriveeAller(String minuteArriveeAller) {
        this.minuteArriveeAller = minuteArriveeAller;
    }

    public String getDateDepartRetour() {
        return dateDepartRetour ;
    }

    public void setDateDepartRetour(String dateDepartRetour) {
        this.dateDepartRetour = dateDepartRetour;
    }

    public String getLieuDepartRetour() {
        return lieuDepartRetour;
    }

    public void setLieuDepartRetour(String lieuDepartRetour) {
        this.lieuDepartRetour = lieuDepartRetour;
    }

    public String getHeureDepartRetour() {
        return heureDepartRetour;
    }

    public void setHeureDepartRetour(String heureDepartRetour) {
        this.heureDepartRetour = heureDepartRetour;
    }

    public String getMinuteDepartRetour() {
        return minuteDepartRetour;
    }

    public void setMinuteDepartRetour(String minuteDepartRetour) {
        this.minuteDepartRetour = minuteDepartRetour;
    }

    public String getLieuArriveeRetour() {
        return lieuArriveeRetour;
    }

    public void setLieuArriveeRetour(String lieuArriveeRetour) {
        this.lieuArriveeRetour = lieuArriveeRetour;
    }

    public String getDateArriveeRetour() {
        return dateArriveeRetour ;
    }

    public void setDateArriveeRetour(String dateArriveeRetour) {
        this.dateArriveeRetour = dateArriveeRetour;
    }

    public String getHeureArriveeRetour() {
        return heureArriveeRetour;
    }

    public void setHeureArriveeRetour(String heureArriveeRetour) {
        this.heureArriveeRetour = heureArriveeRetour;
    }

    public String getMinuteArriveeRetour() {
        return minuteArriveeRetour;
    }

    public void setMinuteArriveeRetour(String minuteArriveeRetour) {
        this.minuteArriveeRetour = minuteArriveeRetour;
    }

    public String [] getMoyensLocomotion() {
        return moyensLocomotion;
    }

    public void setMoyensLocomotion(String [] moyensLocomotion) {
        this.moyensLocomotion = moyensLocomotion;
    }

    public String getPassager() {
        return passager;
    }

    public void setPassager(String passager) {
        this.passager = passager;
    }

    public String getNomConducteur() {
        return nomConducteur;
    }

    public void setNomConducteur(String nomConducteur) {
        this.nomConducteur = nomConducteur;
    }

    public String getTrainClasse() {
        return trainClasse;
    }

    public void setTrainClasse(String trainClasse) {
        this.trainClasse = trainClasse;
    }

    public String getTrainPrixBillet() {
        return trainPrixBillet;
    }

    public void setTrainPrixBillet(String trainPrixBillet) {
        this.trainPrixBillet = trainPrixBillet;
    }

    public String getTrainBonCommande() {
        return trainBonCommande;
    }

    public void setTrainBonCommande(String trainBonCommande) {
        this.trainBonCommande = trainBonCommande;
    }

    public String getTrainCarteAffaire() {
        return trainCarteAffaire;
    }

    public void setTrainCarteAffaire(String trainCarteAffaire) {
        this.trainCarteAffaire = trainCarteAffaire;
    }

    public String getAvionBateauClasse() {
        return avionBateauClasse;
    }

    public void setAvionBateauClasse(String avionBateauClasse) {
        this.avionBateauClasse = avionBateauClasse;
    }

    public String getAvionBateauPrixBillet() {
        return avionBateauPrixBillet;
    }

    public void setAvionBateauPrixBillet(String avionBateauPrixBillet) {
        this.avionBateauPrixBillet = avionBateauPrixBillet;
    }

    public String getAvionBateauBonCommande() {
        return avionBateauBonCommande;
    }

    public void setAvionBateauBonCommande(String avionBateauBonCommande) {
        this.avionBateauBonCommande = avionBateauBonCommande;
    }

    public String getAvionBateauCarteAffaire() {
        return avionBateauCarteAffaire;
    }

    public void setAvionBateauCarteAffaire(String avionBateauCarteAffaire) {
        this.avionBateauCarteAffaire = avionBateauCarteAffaire;
    }
/*
    public String getTaxi() {
        return taxi;
    }

    public void setTaxi(String taxi) {
        this.taxi = taxi;
    }

    public String getLocationVehicule() {
        return locationVehicule;
    }

    public void setLocationVehicule(String locationVehicule) {
        this.locationVehicule = locationVehicule;
    }
*/
    public String getVehiculeService() {
        return vehiculeService;
    }

    public void setVehiculeService(String vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    public String getVehiculeServiceNomPassagers() {
        return vehiculeServiceNomPassagers;
    }

    public void setVehiculeServiceNomPassagers(String vehiculeServiceNomPassagers) {
        this.vehiculeServiceNomPassagers = vehiculeServiceNomPassagers;
    }

    public String getVehiculeServiceNombreKilometres() {
        return vehiculeServiceNombreKilometres;
    }

    public void setVehiculeServiceNombreKilometres(String vehiculeServiceNombreKilometres) {
        this.vehiculeServiceNombreKilometres = vehiculeServiceNombreKilometres;
    }

    public String getVehiculePersonnelMarque() {
        return vehiculePersonnelMarque;
    }

    public void setVehiculePersonnelMarque(String vehiculePersonnelMarque) {
        this.vehiculePersonnelMarque = vehiculePersonnelMarque;
    }

    public String getVehiculePersonnelPuissance() {
        return vehiculePersonnelPuissance;
    }

    public void setVehiculePersonnelPuissance(String vehiculePersonnelPuissance) {
        this.vehiculePersonnelPuissance = vehiculePersonnelPuissance;
    }

    public String getVehiculePersonnelImmatriculation() {
        return vehiculePersonnelImmatriculation;
    }

    public void setVehiculePersonnelImmatriculation(String vehiculePersonnelImmatriculation) {
        this.vehiculePersonnelImmatriculation = vehiculePersonnelImmatriculation;
    }

    public String getVehiculePersonnelDateAcquisition() {
        return vehiculePersonnelDateAcquisition;
    }

    public void setVehiculePersonnelDateAcquisition(String vehiculePersonnelDateAcquisition) {
        this.vehiculePersonnelDateAcquisition = vehiculePersonnelDateAcquisition;
    }

    public String getVehiculePersonnelNomAssureur() {
        return vehiculePersonnelNomAssureur;
    }

    public void setVehiculePersonnelNomAssureur(String vehiculePersonnelNomAssureur) {
        this.vehiculePersonnelNomAssureur = vehiculePersonnelNomAssureur;
    }

    public String getVehiculeJustificatif() {
        return vehiculeJustificatif;
    }

    public void setVehiculeJustificatif(String vehiculeJustificatif) {
        this.vehiculeJustificatif = vehiculeJustificatif;
    }

    public String getFraisInscription() {
        return fraisInscription;
    }

    public void setFraisInscription(String fraisInscription) {
        this.fraisInscription = fraisInscription;
    }

    public String getMontantInscription() {
        return montantInscription;
    }

    public void setMontantInscription(String montantInscription) {
        this.montantInscription = montantInscription;
    }

    public String getBonCommandeInscription() {
        return bonCommandeInscription;
    }

    public void setBonCommandeInscription(String bonCommandeInscription) {
        this.bonCommandeInscription = bonCommandeInscription;
    }

    public String getAvance() {
        return avance;
    }

    public void setAvance(String avance) {
        this.avance = avance;
    }

    //
    // On recoit la valeur "numero;nom;prenom;telephone;email" pour l'autorité hierarchique
    //
    public void setAutoriteHierarchique(String chaine) {
        String infosAutoriteHierarchique [] = chaine.split(";") ;
        if (infosAutoriteHierarchique.length == 5) // Pour le cas ou aucun signataire n'a été désigné
        {
            this.numAutoriteHierarchique = Integer.parseInt (infosAutoriteHierarchique [0]) ;
            this.nomAutoriteHierarchique = infosAutoriteHierarchique [1] ;
            this.prenomAutoriteHierarchique = infosAutoriteHierarchique[2] ;
            this.telephoneAutoriteHierarchique = infosAutoriteHierarchique[3] ;
            this.emailAutoriteHierarchique = infosAutoriteHierarchique [4] ;
        }
    }

    public int getNumAutoriteHierarchique() {
        return numAutoriteHierarchique;
    }

    public void setNumAutoriteHierarchique(int numAutoriteHierarchique) {
        this.numAutoriteHierarchique = numAutoriteHierarchique;
    }

    public String getNomAutoriteHierarchique() {
        return nomAutoriteHierarchique;
    }

    public void setNomAutoriteHierarchique(String nomAutoriteHierarchique) {
        this.nomAutoriteHierarchique = nomAutoriteHierarchique;
    }

    public String getPrenomAutoriteHierarchique() {
        return prenomAutoriteHierarchique;
    }

    public void setPrenomAutoriteHierarchique(String prenomAutoriteHierarchique) {
        this.prenomAutoriteHierarchique = prenomAutoriteHierarchique;
    }

    public String getTelephoneAutoriteHierarchique() {
        return telephoneAutoriteHierarchique;
    }

    public void setTelephoneAutoriteHierarchique(String telephoneAutoriteHierarchique) {
        this.telephoneAutoriteHierarchique = telephoneAutoriteHierarchique;
    }

    public String getEmailAutoriteHierarchique() {
        return emailAutoriteHierarchique;
    }

    public void setEmailAutoriteHierarchique(String emailAutoriteHierarchique) {
        this.emailAutoriteHierarchique = emailAutoriteHierarchique;
    }
    
    public void setNomResponsableFinancier (String nomResponsableFinancier) {
        this.nomResponsableFinancier = nomResponsableFinancier ;
    }
    
    public String getNomResponsableFinancier() {
        return nomResponsableFinancier ;
    }
    
    public int getNumResponsableFinancier() {
        return numResponsableFinancier;
    }

    public void setNumresponsableFinancier(int numresponsableFinancier) {
        this.numResponsableFinancier = numresponsableFinancier;
    }
    
    public String getPrenomResponsableFinancier() {
        return prenomResponsableFinancier;
    }

    public void setPrenomResponsableFinancier(String prenomResponsableFinancier) {
        this.prenomResponsableFinancier = prenomResponsableFinancier;
    }

    public String getTelephoneResponsableFinancier() {
        return telephoneResponsableFinancier;
    }

    public void setTelephoneResponsableFinancier(String telephoneResponsableFinancier) {
        this.telephoneResponsableFinancier = telephoneResponsableFinancier;
    }

    public String getEmailResponsableFinancier() {
        return emailResponsableFinancier;
    }

    public void setEmailResponsableFinancier(String emailResponsableFinancier) {
        this.emailResponsableFinancier = emailResponsableFinancier;
    }

    public String getProjet() {
        return projet;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }

    public String getCentreFinancier() {
        return centreFinancier;
    }

    public void setCentreFinancier(String centreFinancier) {
        this.centreFinancier = centreFinancier;
    }

    public String getInformationsComplementaires() {
        return informationsComplementaires;
    }

    public void setInformationsComplementaires(String informationsComplementaires) {
        this.informationsComplementaires = informationsComplementaires;
    }

    @Override
    public String toString() {
        return "OrdreMission{" + "dateDemande=" + dateDemande + ", numeroMission=" + numeroMission + ", frais=" + frais + ", pays=" + pays + ", nomMissionnaire=" + nomMissionnaire + ", prenomMissionnaire=" + prenomMissionnaire + ", rueMissionnaire=" + rueMissionnaire + ", codepostalMissionnaire=" + codepostalMissionnaire + ", villeMissionnaire=" + villeMissionnaire + ", paysMissionnaire=" + paysMissionnaire + ", telephoneMissionnaire=" + telephoneMissionnaire + ", emailMissionnaire=" + emailMissionnaire + ", iban1=" + iban1 + ", iban2=" + iban2 + ", iban3=" + iban3 + ", iban4=" + iban4 + ", iban5=" + iban5 + ", iban6=" + iban6 + ", iban7=" + iban7 + ", motifMission=" + motifMission + ", dateDepartAller=" + dateDepartAller + ", lieuDepartAller=" + lieuDepartAller + ", heureDepartAller=" + heureDepartAller + ", minuteDepartAller=" + minuteDepartAller + ", lieuArriveeAller=" + lieuArriveeAller + ", dateArriveeAller=" + dateArriveeAller + ", heureArriveeAller=" + heureArriveeAller + ", minuteArriveeAller=" + minuteArriveeAller + ", dateDepartRetour=" + dateDepartRetour + ", lieuDepartRetour=" + lieuDepartRetour + ", heureDepartRetour=" + heureDepartRetour + ", minuteDepartRetour=" + minuteDepartRetour + ", lieuArriveeRetour=" + lieuArriveeRetour + ", dateArriveeRetour=" + dateArriveeRetour + ", heureArriveeRetour=" + heureArriveeRetour + ", minuteArriveeRetour=" + minuteArriveeRetour + ", moyensLocomotion=" + moyensLocomotion + ", passager=" + passager + ", nomConducteur=" + nomConducteur + ", trainClasse=" + trainClasse + ", trainPrixBillet=" + trainPrixBillet + ", trainBonCommande=" + trainBonCommande + ", trainCarteAffaire=" + trainCarteAffaire + ", avionBateauClasse=" + avionBateauClasse + ", avionBateauPrixBillet=" + avionBateauPrixBillet + ", avionBateauBonCommande=" + avionBateauBonCommande + ", avionBateauCarteAffaire=" + avionBateauCarteAffaire + ", vehiculeService=" + vehiculeService + ", vehiculeServiceNomPassagers=" + vehiculeServiceNomPassagers + ", vehiculeServiceNombreKilometres=" + vehiculeServiceNombreKilometres + ", vehiculePersonnelMarque=" + vehiculePersonnelMarque + ", vehiculePersonnelPuissance=" + vehiculePersonnelPuissance + ", vehiculePersonnelImmatriculation=" + vehiculePersonnelImmatriculation + ", vehiculePersonnelDateAcquisition=" + vehiculePersonnelDateAcquisition + ", vehiculePersonnelNomAssureur=" + vehiculePersonnelNomAssureur + ", vehiculeJustificatif=" + vehiculeJustificatif + ", fraisInscription=" + fraisInscription + ", montantInscription=" + montantInscription + ", bonCommandeInscription=" + bonCommandeInscription + ", avance=" + avance + ", numResponsableFinancier=" + numResponsableFinancier + ", nomResponsableFinancier=" + nomResponsableFinancier + ", prenomResponsableFinancier=" + prenomResponsableFinancier + ", telephoneResponsableFinancier=" + telephoneResponsableFinancier + ", emailResponsableFinancier=" + emailResponsableFinancier + ", numAutoriteHierarchique=" + numAutoriteHierarchique + ", nomAutoriteHierarchique=" + nomAutoriteHierarchique + ", prenomAutoriteHierarchique=" + prenomAutoriteHierarchique + ", telephoneAutoriteHierarchique=" + telephoneAutoriteHierarchique + ", emailAutoriteHierarchique=" + emailAutoriteHierarchique + ", centreFinancier=" + centreFinancier + ", projet=" + projet + ", informationsComplementaires=" + informationsComplementaires + '}';
    }
}
