/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensicaen.yousign;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dp
 */
public class GestionBase 
{
private Connection connection = null ;
    
    /**
     *
     * Le constructeur charge le driver jdbc MySQL et se connecte à la base
     * en initialisant l'objet connection
     * @param server    Nom du serveur hébergeant la base de données
     * @param dataBase  Nom de la base
     * @param user      Nom de l'utilisateur
     * @param password  Mot de passe de l'utilisateur
     * @throws ClassNotFoundException   le driver JDBC est incorrect ou non trouve
     * @throws InstantiationException   Erreur en essaynt de créer le driver JDBC
     * @throws IllegalAccessException   Accès interdit à la base de donnees
     * @throws SQLException             Erreur SQL lors de la conenxion à la base de donnees
     */
    public GestionBase(String server, String dataBase, String user, String password) throws ClassNotFoundException,InstantiationException,IllegalAccessException,SQLException
    {               
        Class.forName("com.mysql.jdbc.Driver").newInstance();        
        connection =  DriverManager.getConnection("jdbc:mysql://" + server + "/" + dataBase + "?" +  "user=" + user + "&password=" + password + "&useSSL=false");
    }
  
    /**
     * Retourne dans une liste chainee la liste des responsables financiers habilités à signer
     * @return
     * @throws java.sql.SQLException
     */
    public LinkedList<Responsable> getListeResponsablesFinanciersActifs () throws SQLException
    {
        Statement statement ; 
        ResultSet resultSet ;
        LinkedList<Responsable> liste = new LinkedList<> () ;
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from responsablesfinanciers where actif=true");
        while (resultSet.next())
        {
            liste.add ( new Responsable ( resultSet.getInt ("numero"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("telephone"), resultSet.getString("courriel"), true) ) ;
        } 
        return liste ;
    }
 
    /**
     * Retourne un Responsable financier en fonction de son numéro
     * @param numResponsableFinancier le numéro du responsable financier tel que défini dans la base
     * @return toutes les coordonnees du reponsable financier
     * @throws java.sql.SQLException
     */
    public Responsable getResponsableFinancier (int numResponsableFinancier) throws SQLException
    {
        Responsable responsableFinancier = null ;
        Statement statement ;
        ResultSet resultSet ;
        statement = connection.createStatement() ;
        resultSet = statement.executeQuery("SELECT * from responsablesfinanciers where numero = " + numResponsableFinancier + " AND actif=true ;") ;
        if (resultSet.next ())
        {
            responsableFinancier = new Responsable ( numResponsableFinancier, 
                                                     resultSet.getString ("nom"), resultSet.getString ("prenom"),
                                                     resultSet.getString ("telephone"), resultSet.getString ("courriel"), true) ;
        }
        return responsableFinancier ;
    }
    /**
     * Retourne dans une liste chainee la liste des responsables de services habilités à signer
     * @return
     * @throws java.sql.SQLException
     */
    public LinkedList<Responsable> getListeAutoritesHierarchiquesActifs () throws SQLException
    {
        Statement statement ; 
        ResultSet resultSet ;
        LinkedList<Responsable> liste = new LinkedList<> () ;
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from autoriteshierarchiques where actif=true");
        while (resultSet.next())
        {
            liste.add ( new Responsable (resultSet.getInt("numero"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("telephone"), resultSet.getString("courriel"), resultSet.getBoolean("actif")) ) ;
        }
        Collections.sort (liste) ;
        return liste ;
    }    
    /**
     * Retourne dans une liste chainee la liste des vehicules de service disponibles
     * @return
     * @throws java.sql.SQLException
     */
    public LinkedList<VehiculeService> getListeVehiculesServices () throws SQLException
    {
        Statement statement ; 
        ResultSet resultSet ;
        LinkedList<VehiculeService> liste = new LinkedList<> () ;
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from vehiculesservices");
        while (resultSet.next())
        {
            liste.add ( new VehiculeService (resultSet.getInt("numVehiculeService"), resultSet.getString("libelleVehiculeService"), resultSet.getString("commentaireVehiculeService")));
        }
        return liste ;
    }   
    
    //
    // Ajoute la mission dans la table missions et retourne le numero de mission genere
    // Le missionnaire sera ajoute dans la table des missionnaires s'il n'est pas deja present

    /**
     * Ajoute la mission dans la table missions et retourne le numero de mission genere
     * Le missionnaire sera ajoute dans la table des missionnaires s'il n'est pas deja present
     * 
     * @param missionnaire                  Les informations relatives au missionnaire (nom, prenom, courriel, telephone)
     * @param motif                         Motif de la mission
     * @param lieuDepartAller               Lieu de départ pour le voyage aller
     * @param lieuArriveeAller              Lieu d'arrivée pour le voyage aller
     * @param dateDemande                   Date à laquelle est réalisée la demande de mission
     * @param dateDepartAller               Date de départ du voyage aller
     * @param dateArriveeAller              Date d'arrivée du voyage aller
     * @param lieuDepartRetour              Lieu départ du voyage retour
     * @param lieuArriveeRetour             Lieu d'arrivée du voyage retour
     * @param dateDepartRetour              Date de départ du voyage retour
     * @param dateArriveeRetour             Date d'arrivée du voyage retour
     * @param centreFinancier               Centre financier prenant en charge les frais de mission
     * @param projet                        Détails plus spécifiques pour les frais (eOTP, contrat, ...)
     * @param numeroAutoriteHierarchique    Numéro du responsable hierarchique tel que défini dans la base de donnéees
     * @param moyensLocomotion              Quel(s) moyen(s) de locimotion utilise(s)
     * @return le numero de mission         Retourne le numéro de mission (autoincrément) tel que défini dans la base de données
     * @throws java.sql.SQLException        En cas d'erreur de communication avec la base de données
     */
    
    public long setMission (Personne missionnaire, String dateDemande, String motif, 
                            String lieuDepartAller, String lieuArriveeAller, String dateDepartAller, String dateArriveeAller, 
                            String lieuDepartRetour, String lieuArriveeRetour, String dateDepartRetour, String dateArriveeRetour, 
                            String centreFinancier, String projet, int numeroAutoriteHierarchique, String [] moyensLocomotion) throws SQLException
    {
        long numeroMission = 0 ;
        int numeroMissionnaire = setMissionnaire (missionnaire) ;
        String query = "insert into missions (numMissionnaire,dateDemande,motif,lieuDepartAller,lieuArriveeAller,dateDepartAller,dateArriveeAller,lieuDepartRetour,lieuArriveeRetour,dateDepartRetour,dateArriveeRetour,centreFinancier,projet,numAutoriteHierarchique) values (" +
                                              numeroMissionnaire + ",'" +
                                              Date.valueOf (convertDate (dateDemande)) + "','" +
                                              motif.replace ("'","\\'") + "','" +
                                              lieuDepartAller.replace ("'","\\'") + "','" +
                                              lieuArriveeAller.replace ("'","\\'") + "','" +                                                 
                                              Date.valueOf (convertDate (dateDepartAller)) + "','" +
                                              Date.valueOf (convertDate (dateArriveeAller)) + "','" +
                                              lieuDepartRetour.replace ("'","\\'") + "','" +
                                              lieuArriveeRetour.replace ("'","\\'") + "','" +
                                              Date.valueOf (convertDate (dateDepartRetour)) + "','" +
                                              Date.valueOf (convertDate (dateArriveeRetour)) + "','" +
                                              centreFinancier + "','" +
                                              projet.replace ("'","\\'") + "'," +
                                              numeroAutoriteHierarchique + ");" ;
      
        Statement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.execute(query, Statement.RETURN_GENERATED_KEYS) ;
        ResultSet resultSet = statement.getGeneratedKeys() ;
        if (resultSet.next ())
        {
            numeroMission = resultSet.getLong(1) ;
            for (String moyenLocomotion : moyensLocomotion)
                  setMoyenLocomotion (moyenLocomotion, numeroMission) ;
        }                                     
        return numeroMission ;
    }
    
    /**
     * Retourne la liste triee de tous les centres financiers
     * @return la liste triée des centres financiers
     * @throws SQLException
     */
    public LinkedList<CentreFinancier> getListeCentresFinanciersActifs () throws SQLException
    {
        LinkedList<CentreFinancier> liste = new LinkedList<> () ;
        
        Statement statement = connection.createStatement() ;
        ResultSet resultSet = statement.executeQuery("select * from centresfinanciers where actif=true;") ;
        while (resultSet.next())
        {
            Responsable responsableFinancier = this.getResponsableFinancier(resultSet.getInt("numResponsableFinancier")) ;
            liste.add ( new CentreFinancier (resultSet.getString ("libelle"), resultSet.getString("libelleLong"),
                                             new Responsable (responsableFinancier))) ;
        }
        Collections.sort (liste) ;
        return liste ;
    }
    
    /**
     * Retourne la liste des centres financiers pour un responsable financier donné
     * @param numResponsableFinancier Le numéro du responsable financier
     * @return la liste des centres financiers dont il est responsable
     * @throws SQLException
     */
    public LinkedList<CentreFinancier> getListeCentresFinanciersPourResponsable (int numResponsableFinancier) throws SQLException
    {
        LinkedList<CentreFinancier> liste = new LinkedList<> () ;
        Statement statement = connection.createStatement() ;
        ResultSet resultSet = statement.executeQuery("select * from centresfinanciers where numResponsableFinancier=" + numResponsableFinancier + " and actif=1;") ;
        while (resultSet.next ())
        {
            liste.add ( new CentreFinancier (resultSet.getString ("libelle"), resultSet.getString("libelleLong"), this.getResponsableFinancier(numResponsableFinancier))) ;
        }
        return liste ;
    }
    
    /**
     * Pour un centre financier donné, retourne le numéro du responsable
     * financier tel que défini dans la table responsablesfinanciers. Retourne -1 si la ligne n'existe pas
     * @param centreFinancier le nom du centre financier dans l'outil de gestion du budget
     * @return le numéro du responsable financier, -1 si le centre financier n'existe pas.
     * @throws SQLException
     */
    public int getNumResponsableFinancier (String centreFinancier) throws SQLException
    {
        Statement statement = connection.createStatement() ;
        ResultSet resultSet = statement.executeQuery("select numResponsableFinancier from centresfinanciers where libelle ='" + centreFinancier.toUpperCase() +  "' and actif=1;") ;
        if (resultSet.next ())
        {
            return resultSet.getInt("numResponsableFinancier") ;
        }
        else 
            return -1 ;
    }
    
    /**
     * Ajout d'un responsable financier dans la base de données. S'il s'y trouvait déjà,
     * son état devient actif.
     * @param responsable Les informations relatives au responsable financier 
     * @throws SQLException
     */
    public void ajouteResponsableFinancier (Personne responsable) throws SQLException
    {
        ajoute (responsable,"responsablesfinanciers") ;
    }

     /**
     * Supprime le responsable financier en le désactivant dans la base de données (l'entrée
     * n'est pas détruite).
     * @param responsable les informations relatives au responsable financier
     * @throws SQLException
     */
    public void supprimeResponsableFinancier (Personne responsable) throws SQLException
    {
        supprime (responsable,"responsablesfinanciers") ;
    }
    
    /**
     * Ajout d'une autorite hierarchique dans la base de données. Si elle s'y trouvait déjà,
     * son état devient actif.
     * @param autorite Les informations relatives à l'autorite hierarchique
     * @throws SQLException
     */
    public void ajouteAutoriteHierarchique(Personne autorite) throws SQLException
    {
        ajoute (autorite,"autoriteshierarchiques") ;
    }
    
    /**
     * Supprime l'autorite hierarchique en la désactivant dans la base de données (l'entrée
     * n'est pas détruite).
     * @param autorite les informations relatives à l'autorité hierarchique
     * @throws SQLException
     */
    public void supprimeAutoriteHierarchique (Personne autorite) throws SQLException
    {
        supprime (autorite,"autoriteshierarchiques") ;
    }
    
    /**
     * Ajoute un nouveau centre financier dans la base de données (le réactive s'il existait déjà)
     * @param centre
     * @throws SQLException
     */
    public void ajouteCentreFinancier (CentreFinancier centre) throws SQLException
    {
        Statement statement = connection.createStatement() ;
        ResultSet resultSet = statement.executeQuery("select libelle from centresfinanciers where libelle='" + centre.getLibelle() + "'") ;
        if (resultSet.next ()) // Il existait déjà, on l'active
        {
            statement = connection.createStatement() ;
            statement.executeUpdate("update centresfinanciers set libelleLong='" + centre.getLibelleLong() +"',numResponsableFinancier=" + centre.getResponsableFinancier().getNumero() +",actif=true where libelle='" + centre.getLibelle().toUpperCase() +"'") ;            
        }
        else // Il n'existait pas, on l'ajoute
        {
            statement = connection.createStatement() ;
            statement.execute("insert into centresfinanciers values ('" +
                                                                            centre.getLibelle() + "','" +
                                                                            centre.getLibelleLong() + "'," +
                                                                            centre.getResponsableFinancier().getNumero() + ",true)"           
                                                                    ) ;
        }
    }
    
    /**
     * Supprime le centre financier en le désactivant dans la base de données (l'entrée
     * n'est pas détruite).
     * @param centre            Le centre financier à désactiver
     * @throws SQLException
     */
    public void supprimeCentreFinancier (CentreFinancier centre) throws SQLException
    {
        Statement statement = connection.createStatement() ;
        statement.executeUpdate("update centresfinanciers set actif=false where libelle='" + centre.getLibelle().toUpperCase() +"'") ;
    }
    
    /**
     * Ajoute un vehicule (libelle + commentaire) dans la base de données
     * @param vehicule le vehicule à ajouter
     * @throws SQLException
     */
    public void ajouteVehiculeService (VehiculeService vehicule) throws SQLException
    {
        int numero = 1 ;
        Statement statement = connection.createStatement() ;
        ResultSet resultSet = statement.executeQuery("select max(numVehiculeService) from vehiculesservices") ;
        if (resultSet.next ()) numero = resultSet.getInt(1) + 1 ;
        statement = connection.createStatement() ;
        statement.execute ("insert into vehiculesservices values (" + numero + ",'" + vehicule.getLibelleVehiculeService() + "','" + vehicule.getCommentaireVehiculeService() +"')") ;
    }
    
    /**
     * Supprime un vehicule de service de la base de données
     * @param vehicule le vehicule à supprimer
     * @throws SQLException
     */
    public void supprimeVehiculeService (VehiculeService vehicule) throws SQLException
    {
        Statement statement = connection.createStatement() ;
        statement.execute("delete from vehiculesservices where libelleVehiculeService='" + vehicule.getLibelleVehiculeService() +"'") ;
    }
    
    /**
     * Fermeture de la base de données
     * @throws SQLException
     */
    public void close () throws SQLException
    {
        connection.close();
    }
    
    //
    //
    // @param missionnaire Le missionnaire qui est soit dans la table, soit que l'on ajoute a la table
    // @return Le numero existant ou genere du missionnaire, 0 en cas de problemes
    // @throws SQLException
    //
    private int setMissionnaire (Personne missionnaire) throws SQLException
    {
        Statement statement ;
        ResultSet resultSet ;
        int numeroMissionnaire = 0 ;
        statement = connection.createStatement() ;
        
        //
        // Le missionnaire est-il deja dans la base ? si oui, on renvoie le numero
        //
        resultSet = statement.executeQuery("select numero from missionnaires where courriel='" + missionnaire.getCourriel() + "';") ;
        if (resultSet.next()) return resultSet.getInt("numero") ;
        
        //
        // S'il n'est pas dans la base, on l'ajoute et on renvoie le numero auto genere
        //
        
        String query = "insert into missionnaires ( nom,prenom,telephone,courriel) values ('" + 
                                                       missionnaire.getNom ().replace ("'","\\'") + "','" +
                                                       missionnaire.getPrenom ().replace ("'","\\'") + "','" +
                                                       missionnaire.getTelephone () + "','" +
                                                       missionnaire.getCourriel () +"');" ;
        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);       
        statement.execute(query, Statement.RETURN_GENERATED_KEYS) ;
        resultSet = statement.getGeneratedKeys() ;
        if (resultSet.next ())
        {
            numeroMissionnaire = resultSet.getInt(1) ;
        }
        //
        // On retourne le numero de missionnaire, 0 si probleme
        //
        return numeroMissionnaire ;
    }
    
    //
    // Ajout d'un moyen de locomotion dans la base pour une mission donnée
    //
    private void setMoyenLocomotion (String moyenLocomotion, long numeroMission) throws SQLException
    {
        Statement statement = connection.createStatement() ;
        ResultSet resultSet = statement.executeQuery("select numMoyenLocomotion from moyenslocomotion where nomMoyenLocomotion='" + moyenLocomotion.toLowerCase() + "';") ;
        if (resultSet.next())
        {
            int numeroMoyenLocomotion = resultSet.getInt("numMoyenLocomotion") ;
            statement = connection.createStatement() ;
            statement.execute ("insert into missionsmoyenslocomotion values (" + numeroMission + "," + numeroMoyenLocomotion + ");") ;
        }
    }
        
    //
    // On ajoute une personne dans la table fournie en parametre (responsablesfinanciers ou autoriteshierarchiques)
    //
    private void ajoute (Personne personne, String tableSQL) throws SQLException
    {
        Statement statement = connection.createStatement()  ;
        int numPersonne ;
        //
        // La personne est-elle deja dans la table ?
        //
        if ( (numPersonne = getNumeroPersonne (personne,tableSQL)) != -1)  // Si oui, on change l'état à Actif
        {
            statement.executeUpdate ("update " + tableSQL + " set actif=true,nom='" + personne.getNom() + "',prenom='" + personne.getPrenom() + "',courriel='" + personne.getCourriel() + "',telephone='" + personne.getTelephone() + "' where numero=" + numPersonne) ;
        }
        else // Si non, on ajoute la personne à la table
        {
            statement.execute ("insert into " + tableSQL +  " values (" +   getNumeroToUse (tableSQL) +",'" +
                                                                            personne.getNom() + "','" +
                                                                            personne.getPrenom() + "','" +
                                                                            personne.getCourriel() + "','" +
                                                                            personne.getTelephone() +"',1)") ;
        }
    }
    
    //
    // On désactive une personne dans la table fournie en parametres (responsablesfinanciers ou autoriteshierarchiques)
    //
    private void supprime (Personne personne, String tableSQL) throws SQLException
    {
        Statement statement = connection.createStatement() ;
        ResultSet resultSet = statement.executeQuery("select numero from " + tableSQL + " where courriel='" + personne.getCourriel().toLowerCase() +"'") ;
        if (resultSet.next ())
        {
            statement = connection.createStatement() ;
            statement.executeUpdate("update " + tableSQL + " set actif=false where numero=" + resultSet.getInt("numero")) ;
        }
    }
    //
    // retourne le numéro de la personne dans la table (responsablesfinanciers ou autoriteshierarchiques)
    // retourne -1 sinon
    private int getNumeroPersonne (Personne personne, String tableSQL) throws SQLException
    {
        int numPersonne = -1 ;
        Statement statement = connection.createStatement() ;
        ResultSet resultSet = statement.executeQuery ("select * from " + tableSQL + " where courriel='" + personne.getCourriel().toLowerCase() + "'") ;
        if (resultSet.next ()) numPersonne = resultSet.getInt("numero") ;
        return numPersonne ;
    }
    
    //
    // retourne le numero à utiliser dans la table pour inserer une nouvelle personne (responsablesfinanciers ou autoriteshierarchiques)
    //
    private int getNumeroToUse (String tableSQL) throws SQLException
    {
        int max = 1 ;
        Statement statement = connection.createStatement() ;
        ResultSet resultSet = statement.executeQuery ("select max(numero) from " + tableSQL) ;
        if (resultSet.next ()) max = resultSet.getInt (1) + 1 ;
        return max ;     
    }
        
    //
    // On convertit la date au format JJ/MM/AAAA au format SQL AAAA-MM-JJ
    //
    private String convertDate (String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date data = null ;
        try {
                data = sdf.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(OrdreMission.class.getName()).log(Level.SEVERE, null, ex);
        }
        String formattedTime = output.format(data);
        return formattedTime;
    }
}
