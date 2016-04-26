<%--
    Document   : process.jsp
    Created on : 14 déc. 2015, 17:01:41
    Author     : dp
--%>

<%@page import="fr.ensicaen.yousign.GestionBase"%>
<%@page import="fr.ensicaen.yousign.Responsable"%>
<%@page import="fr.ensicaen.yousign.Personne"%>
<%@page import="fr.ensicaen.yousign.YousignInitSignature"%>
<%@page import="fr.ensicaen.yousign.GenerePDF"%>
<%@page import="fr.ensicaen.yousign.OrdreMissionUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page isErrorPage="false" errorPage="erreur.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signature de l'ordre de mission</title>
    </head>
    <body>
        <image src="images/logo.png" /><P>
            
        <jsp:useBean id="ordreMission" class = "fr.ensicaen.yousign.OrdreMission" scope="page" >
            <jsp:setProperty name="ordreMission" property="*" />
        </jsp:useBean>
            
        <%  
            GestionBase base = (GestionBase) request.getSession().getAttribute("base") ;
            GenerePDF generePDF = new GenerePDF () ;
            YousignInitSignature yousign = new YousignInitSignature () ;
            OrdreMissionUtil util = new OrdreMissionUtil (ordreMission) ;
            
            int numResponsableFinancier = base.getNumResponsableFinancier(ordreMission.getCentreFinancier()) ;
            ordreMission.setNumresponsableFinancier(numResponsableFinancier);
            //
            // Si numResponsableFinancier = -1, le centre financier est incorrect
            //
            if (numResponsableFinancier != -1)
            {
                Responsable responsableFinancier = base.getResponsableFinancier(numResponsableFinancier) ;
                if (responsableFinancier != null)
                {
                    ordreMission.setNomResponsableFinancier(responsableFinancier.getNom ());
                    ordreMission.setPrenomResponsableFinancier(responsableFinancier.getPrenom ());
                    ordreMission.setEmailResponsableFinancier(responsableFinancier.getCourriel ());
                    ordreMission.setTelephoneResponsableFinancier(responsableFinancier.getTelephone ());
                }
            }
            //
            // On verifie que le formulaire est correctement rempli
            //
            if (util.validate() == true)
            {
                //
                // On transmet l'objet OrdreMissionUtil aux objets generePDF et yousign
                //
                generePDF.setUtil(util);
                yousign.setUtil(util);
                //
                // On enregistre l'ordre de mission dans la base de donnees
                //
                Personne missionnaire = new Personne (ordreMission.getNomMissionnaire(), 
                                                              ordreMission.getPrenomMissionnaire(),
                                                              ordreMission.getTelephoneMissionnaire(),
                                                              ordreMission.getEmailMissionnaire()) ;
                long numMission = base.setMission (missionnaire, ordreMission.getDateDemande (), ordreMission.getMotifMission(), 
                                                   ordreMission.getLieuDepartAller(), ordreMission.getLieuArriveeAller(), 
                                                   ordreMission.getDateDepartAller(), ordreMission.getDateArriveeAller(),
                                                   ordreMission.getLieuDepartRetour(), ordreMission.getLieuArriveeRetour(),
                                                   ordreMission.getDateDepartRetour(), ordreMission.getDateArriveeRetour(),
                                                   ordreMission.getCentreFinancier(), ordreMission.getProjet(), ordreMission.getNumAutoriteHierarchique(), ordreMission.getMoyensLocomotion()) ;
                //
                // On sauvegarde le numero de mission
                //
                ordreMission.setNumeroMission(numMission);
                //
                // On génère le fichier PDF
                //
                generePDF.genere(); 
                //
                // On transmet le PDF sur la plate forme YouSign pour signature
                //
                String status = yousign.sign();
                //
                // On verifie que tout s'est bien passé ....
                //
                if (status == null)
                {
                    out.println ("Votre ordre de mission a été envoyé pour signature: <BR>") ;
                    out.println (" au missionnaire: " + ordreMission.getPrenomMissionnaire () + " " + ordreMission.getNomMissionnaire () + "(" + ordreMission.getEmailMissionnaire () + ")<BR>") ;
                    if ( ! ordreMission.getEmailAutoriteHierarchique().equalsIgnoreCase(ordreMission.getEmailResponsableFinancier()))
                    {
                        out.println (" à l'autorité hierarchique: " + ordreMission.getPrenomAutoriteHierarchique() + " " + ordreMission.getNomAutoriteHierarchique() + " (" + ordreMission.getEmailAutoriteHierarchique() + ")<BR>") ;                   
                    }
                    if ( ! ordreMission.getEmailMissionnaire().equalsIgnoreCase(ordreMission.getEmailResponsableFinancier()))
                    {
                        out.println (" au responsable financier: " + ordreMission.getPrenomResponsableFinancier() + " " + ordreMission.getNomResponsableFinancier() + " (" + ordreMission.getEmailResponsableFinancier() + ")") ;                   
                    }
                    base.close();
                }
                else
                {
                    out.println (status) ;
                }
            }
           else
            {
               //
               // On affiche toutes les erreurs détectées dans le formulaire
               //
               out.println ("Des erreurs ont été détectées:<B><P>") ;
               for (String str : util.getStatus())
               {
                   out.println (str + "<BR>") ;
               }
               out.println ("</B><P>Revenir à la page précédente pour corriger") ;
            }

        %>
    </body>
</html>
