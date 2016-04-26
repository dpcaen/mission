package fr.ensicaen.yousign;

import com.yousign.client.YousignClientConstants;
import com.yousign.client.ws.YousignClientFactory;

/**
 *
 * @author dp
 * Classe de configuration.
 *   --> choix du serveur (plate forme de développement ou serveur de production)
 *   --> plate forme YouSign (test ou de production)
 */
public class YousignConfig 
{
    /**
    * Archivage du document signe sur la plate forme YouSign
    * true  --> on archive
    * false --> on n'archive pas
    */
    public static boolean bArchivage = false ;
    /**
    * Mode de validation (Mail ou SMS) pour le missionnaire et le responsable financier
    * Par mail:   YousignClientConstants.AUTHENTICATION_MODE_MAIL
    * Par SMS:    YousignClientConstants.AUTHENTICATION_MODE_SMS
    */
    public static String missionnaireValidation = YousignClientConstants.AUTHENTICATION_MODE_MAIL ;
    public static String responsableFinancierValidation = YousignClientConstants.AUTHENTICATION_MODE_MAIL ;
    public static String autoriteHierarchiqueValidation = YousignClientConstants.AUTHENTICATION_MODE_MAIL ;

    /**
    * Paramètre CAS
    */
    public static String CASLogin = "https://cas.ensicaen.fr:443/cas/login" ;
    public static String UrlMonService = "http://mission.ensicaen.fr:8180/mission/" ; 
    
    //
    // Chemin absolu ou seront stockés différents documents (image, pdf, données)
    //
    public static final String ABSOLUTE_PATH = "/home/yousign" ;
    
    /**
    * emplacement du logo qui sera affiché sur le document PDF
    */
    public static final String LOGO = ABSOLUTE_PATH + "/images/logo.jpg" ;
   
    /**
    * Dossier pour déposer temporairement les fichiers pdf générés (besoin droit d'écriture)
    */
    public static final String ABSOLUTE_PATH_PDF = ABSOLUTE_PATH + "/pdf/" ;
    
    
    /**
     * Configuration de la base de donnees MySQL
     */
    public static final String DATABASE_SERVER="XXX" ;
    public static final String DATABASE="XXX" ;
    public static final String DATABASE_USER="XXX" ;
    public static final String DATABASE_PASSWORD="XXX" ;
    
    //
    // Configuration plate forme de test YouSign
    //
    public static String AUTHENT_USER = "XXX" ;
    public static String AUTHENT_PASSWORD = "XXX";
    public static String AUTHENT_APIKEY = "XXX";
    public static final String ENVIRONNEMENT = YousignClientFactory.ENV_DEMO;
    public static final Boolean ENCRYPTED_PASSWORD = false;
    
    //
    // Configuration plate forme de production YouSign  
    //
    /*
    public static final String AUTHENT_USER = "" ;
    public static final String AUTHENT_PASSWORD = "";
    public static final String AUTHENT_APIKEY = "";
    public static final String ENVIRONNEMENT = YousignClientFactory.ENV_PROD;
    public static final Boolean ENCRYPTED_PASSWORD = false;
    */
}