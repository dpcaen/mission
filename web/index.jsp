<%@page import="fr.ensicaen.yousign.GestionBase"%>
<%@page isErrorPage="false" errorPage="/erreur.jsp" %>
<%@page import="fr.ensicaen.yousign.CentreFinancier"%>
<%@page import="fr.ensicaen.yousign.Responsable"%>
<%@page import="fr.ensicaen.yousign.VehiculeService"%>
<%@page import="java.util.LinkedList"%>
<%@page import="fr.ensicaen.yousign.YousignConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Demande ordre mission</title>
<link rel="stylesheet" href="css/datepicker.css">
<link rel="stylesheet" href="css/style.css">

<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/datepicker-fr.min.js"></script>
<script src="js/script-ordre-mission.js"></script>

</head>

<body>

<%
	String CASLogin = YousignConfig.CASLogin ;
        String UrlMonService = YousignConfig.UrlMonService ;

	 
  	String ticket = request.getParameter( "ticket");
  	if (ticket == null )
  	{     
  		response.sendRedirect( CASLogin + "?service=" +UrlMonService);
  	}
%>

<%
    GestionBase base = new GestionBase (YousignConfig.DATABASE_SERVER,YousignConfig.DATABASE,YousignConfig.DATABASE_USER,YousignConfig.DATABASE_PASSWORD) ;
    request.getSession().setAttribute("base", base);
%>

<!-- 
======================================================================

ENTETE FORMULAIRE

======================================================================
-->

<div class="header">
    <div class="logo"><img src="images/logo.png" /></div>
    <div class="titre">
        DEMANDE D'ORDRE DE MISSION / ESTIMATION DES FRAIS<br />
        <span class="avertissement">À effectuer : 8 jours avant le début d'une mission en France,<br />
4 semaines avant le début d'une mission à l'étranger.</span>
    </div>
</div>

<form action="process.jsp" method="post" enctype="application/x-www-form-urlencoded" name="form1" id="form1">

<div class="OM_gen">
	<div class="OM_gen_col1">Ordre de mission : </div>
	<div class="OM_gen_cols">
    	<div class="OM_gen_cols_radio">
            <input type="radio" name="frais" value="avec frais" id="om_frais_0" checked />
            <label>Avec frais</label>
        </div>
    	<div class="OM_gen_cols_radio">
            <input type="radio" name="frais" value="sans frais" id="om_frais_1" />
            <label>Sans frais</label>
        </div>
	</div>
	<div class="OM_gen_cols">
    	<div class="OM_gen_cols_radio">
            <input type="radio" name="pays" value="en France" id="mission_france_ou_etranger_0" checked onchange="addForm();" />
            <label>Mission en France</label>
        </div>
    	<div class="OM_gen_cols_radio">
            <input type="radio" name="pays" value="a l'étranger" id="mission_france_ou_etranger_1" onchange="addForm();" />
            <label>Mission à l'étranger</label>
        </div>
	</div>
</div>
<div id="om_formulaire_etranger">
<a href="AutorisationAbsenceEtranger.doc" target="_blank">formulaire de demande d'autorisation d'absence &agrave; l'&eacute;tranger</a>
</div>

<div id="errors" class="required">Des champs requis sont manquants ou erronés. Veuillez vérifier la saisie des champs obligatoires, la saisie de votre adresse de courriel, le format des dates (jj/mm/aaaa)...</div>
<!-- 
======================================================================

INFORMATIONS PERSONNELLES

======================================================================
-->


<fieldset>
    <legend>INFORMATIONS PERSONNELLES :</legend>
	<div class="OM_field">
        <label for="nomMissionnaire">Nom :</label>
        <input name="nomMissionnaire" type="text" id="nomMissionnaire" class="OM_input" maxlength="100" /><span class="oblig"> (1)</span>
    </div>
    <div class="OM_field">
        <label for="prenomMissionnaire">Prénom :</label>
        <input name="prenomMissionnaire" type="text" id="prenomMissionnaire" class="OM_input" maxlength="100" /><span class="oblig"> (1)</span>
    </div>
    
    <div class="precision">Seulement si vous avez changé d'adresse depuis la dernière mission effectuée :</div>
    
    <div class="adresse">
    <div class="label_adresse">Adresse</div>
        <div class="fields_adresse">
            <div class="OM_field">
                <label for="rueMissionnaire">Rue :</label>
                <input name="rueMissionnaire" type="text" id="rueMissionnaire" class="OM_input" maxlength="100" />
            </div>
            <div class="OM_field">
                <label for="codepostalMissionnaire">CP :</label>
                <input name="codepostalMissionnaire" type="text" id="codepostalMissionnaire" class="OM_input" size="5" maxlength="5" />
            </div>
            <div class="OM_field">
                <label for="villeMissionnaire">Ville :</label>
                <input name="villeMissionnaire" type="text" id="villeMissionnaire" class="OM_input" maxlength="100" />
            </div>
            <div class="OM_field">
                <label for="paysMissionnaire">Pays :</label>
                <input name="paysMissionnaire" type="text" id="paysMissionnaire" class="OM_input" maxlength="100" />
            </div>
        </div>
    </div>
    <div class="OM_field">
        <label for="emailMissionnaire">Courriel :</label>
        <input name="emailMissionnaire" type="text" id="emailMissionnaire" class="OM_input" maxlength="100" />
        <span class="oblig">(1)</span>
    </div>
    <div class="OM_field">
        <label for="emailMissionnaireverif">Confirmez Courriel :</label>
        <input name="emailMissionnaireverif" type="text" id="emailMissionnaireverif" class="OM_input" maxlength="100" />
        <span class="oblig">(1)</span>
    </div>

</fieldset>


<!-- 
======================================================================

DOMICILIATION BANCAIRE

======================================================================
-->
<fieldset>
    <legend>DOMICILIATION BANCAIRE :</legend>
    
    <div class="precision2">Seulement si vous avez changé de banque depuis la dernière mission effectuée (merci de faire parvenir votre nouveau RIB au service financier) :</div>
    
    <div class="OM_field">
        <label>IBAN :</label>
        <div class="iban">
            <input name="iban1" id="iban1" type="text" size="4" maxlength="4" />
            <input name="iban2" id="iban2" type="text" size="4" maxlength="4" />
            <input name="iban3" id="iban3" type="text" size="4" maxlength="4" />
            <input name="iban4" id="iban4" type="text" size="4" maxlength="4" />
        </div>
        <div class="iban">
            <input name="iban5" id="iban5" type="text" size="4" maxlength="4" />
            <input name="iban6" id="iban6" type="text" size="4" maxlength="4" />
            <input name="iban7" id="iban7" type="text" size="3" maxlength="3" />
        </div>
    </div>
    
</fieldset>

<!-- 
======================================================================

INFORMATIONS MISSION

======================================================================
-->
<fieldset>
    <legend>INFORMATIONS MISSION :</legend>
    
    <div class="OM_field">
        <label for="motifMission">Motif :</label>
        <input name="motifMission" type="text" id="motifMission" class="OM_input" maxlength="200" /> <span class="oblig"> (1)</span>
    </div>
    <table class="trajets" cellspacing="0">
        <tbody>
          <tr class="firstrow">
            <td class="firstcol">
                
            </td>
            <td>
                Lieu de départ
            </td>
            <td>
                Date
            </td>
            <td>
                Heure
            </td>
            <td>
                Lieu d'arrivée
            </td>
            <td>
                Date
            </td>
            <td>
                Heure
            </td>
          </tr>
          <tr>
            <td class="firstcol">
                ALLER
            </td>
            <td>
                <input name="lieuDepartAller" type="text" id="lieuDepartAllee" class="OM_input" maxlength="50" onChange="copy_depart();" /><span class="oblig"> (1)</span>
            </td>
            <td>
            <div class="dateshoraires">
                <input name="dateDepartAller" type="text" id="dateDepartAllee" class="datepicker" size="10" maxlength="10" onChange="CheckDate(this.form.dateDepartAllee.value);" /><span class="oblig"> (1)</span>
            </div>
            </td>
            <td>
            <div class="dateshoraires">
                <select name="heureDepartAller" id="heureDepartAllee">
                    <option value="">--</option>
                    <option value="06">06</option>
                    <option value="07">07</option>
                    <option value="08">08</option>
                    <option value="09">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                    <option value="00">00</option>
                    <option value="01">01</option>
                    <option value="02">02</option>
                    <option value="03">03</option>
                    <option value="04">04</option>
                    <option value="05">05</option>
                </select> h 
                <select name="minuteDepartAller" id="minuteDepartAllee" size="1">
                    <option value="">--</option>
                    <option value="00">00</option>
                    <option value="05">05</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                    <option value="20">20</option>
                    <option value="25">25</option>
                    <option value="30">30</option>
                    <option value="35">35</option>
                    <option value="40">40</option>
                    <option value="45">45</option>
                    <option value="50">50</option>
                    <option value="55">55</option>
                </select><span class="oblig"> (1)</span>
            </div>
            </td>
            <td>
                <input name="lieuArriveeAller" type="text" id="lieuArriveeAllee" class="OM_input" maxlength="50" onChange="copy_arrivee();" /><span class="oblig"> (1)</span>
            </td>
            <td>
            <div class="dateshoraires">
                <input name="dateArriveeAller" type="text" id="dateArriveeAllee" class="datepicker" size="10" maxlength="10" onChange="CheckDate(this.form.dateArriveeAllee.value);" /><span class="oblig"> (1)</span>
            </div>
            </td>
            <td>
            <div class="dateshoraires">
                <select name="heureArriveeAller" id="heureArriveeAllee">
                    <option value="">--</option>
                    <option value="06">06</option>
                    <option value="07">07</option>
                    <option value="08">08</option>
                    <option value="09">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                    <option value="00">00</option>
                    <option value="01">01</option>
                    <option value="02">02</option>
                    <option value="03">03</option>
                    <option value="04">04</option>
                    <option value="05">05</option>
                </select> h 
                <select name="minuteArriveeAller" id="minuteArriveeAllee" size="1">
                    <option value="">--</option>
                    <option value="00">00</option>
                    <option value="05">05</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                    <option value="20">20</option>
                    <option value="25">25</option>
                    <option value="30">30</option>
                    <option value="35">35</option>
                    <option value="40">40</option>
                    <option value="45">45</option>
                    <option value="50">50</option>
                    <option value="55">55</option>
                </select><span class="oblig"> (1)</span>
            </div>
            </td>
          </tr>
          <tr>
            <td class="firstcol">
                RETOUR
            </td>
            <td>
                <input name="lieuDepartRetour" type="text" id="lieuDepartRetour" class="OM_input" maxlength="50"></input><span class="oblig"> (1)</span>
            </td>
            <td>
            <div class="dateshoraires">
                <input name="dateDepartRetour" type="text" id="dateDepartRetour" class="datepicker" size="10" maxlength="10" onChange="CheckDate(this.form.dateDepartRetour.value);"></input><span class="oblig"> (1)</span>
            </div>
            </td>
            <td>
            <div class="dateshoraires">
                <select name="heureDepartRetour" id="heureDepartRetour">
                    <option value="">--</option>
                    <option value="06">06</option>
                    <option value="07">07</option>
                    <option value="08">08</option>
                    <option value="09">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                    <option value="00">00</option>
                    <option value="01">01</option>
                    <option value="02">02</option>
                    <option value="03">03</option>
                    <option value="04">04</option>
                    <option value="05">05</option>
                </select> h 
                <select name="minuteDepartRetour" id="minuteDepartRetour" size="1">
                    <option value="">--</option>
                    <option value="00">00</option>
                    <option value="05">05</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                    <option value="20">20</option>
                    <option value="25">25</option>
                    <option value="30">30</option>
                    <option value="35">35</option>
                    <option value="40">40</option>
                    <option value="45">45</option>
                    <option value="50">50</option>
                    <option value="55">55</option>
                </select><span class="oblig"> (1)</span>
            </div>
            </td>
            <td>
                <input name="lieuArriveeRetour" type="text" id="lieuArriveeRetour" class="OM_input" maxlength="50"></input><span class="oblig"> (1)</span>
            </td>
            <td>
            <div class="dateshoraires">
                <input name="dateArriveeRetour" type="text" id="dateArriveeRetour" class="datepicker" size="10" maxlength="10" onChange="CheckDate(this.form.dateArriveeRetour.value);"></input><span class="oblig"> (1)</span>
            </div>
            </td>
            <td>
            <div class="dateshoraires">
                <select name="heureArriveeRetour" id="heureArriveeRetour">
                    <option value="">--</option>
                    <option value="06">06</option>
                    <option value="07">07</option>
                    <option value="08">08</option>
                    <option value="09">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                    <option value="00">00</option>
                    <option value="01">01</option>
                    <option value="02">02</option>
                    <option value="03">03</option>
                    <option value="04">04</option>
                    <option value="05">05</option>
                </select> h 
                <select name="minuteArriveeRetour" id="minuteArriveeRetour" size="1">
                    <option value="">--</option>
                    <option value="00">00</option>
                    <option value="05">05</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                    <option value="20">20</option>
                    <option value="25">25</option>
                    <option value="30">30</option>
                    <option value="35">35</option>
                    <option value="40">40</option>
                    <option value="45">45</option>
                    <option value="50">50</option>
                    <option value="55">55</option>
                </select><span class="oblig"> (1)</span>
            </div>
            </td>
          </tr>
    	</tbody>
    </table>

</fieldset>

<!-- 
======================================================================

TRANSPORTS

======================================================================
-->
<fieldset>
    <legend>TRANSPORTS :</legend>
    
    
        <div class="precision2">Moyen de transport<span class="oblig"> (1)</span> :</div>
        
        <div class="OM_field">
            <div class="checkCtn" id="checkCtn0">
            	<input type="checkbox" name="moyensLocomotion" id="om_transport_0" value="passager" onChange="show_transport('0');" />
            </div>
            
            <div class="passager">
                Pas de frais de transport comme passager de la voiture  de 
                <input type="text" name="nomConducteur" id="om_passager_conducteur" maxlength="100" />
            </div>
        </div>
        <div class="OM_field">
            <div class="checkCtn" id="checkCtn1">
        		<input type="checkbox" name="moyensLocomotion" id="om_transport_1" value="train" onChange="show_transport('1');" />
            </div>
            <label for="om_passager" class="label_transport">Train</label>
        </div>
        <div id="OM_hidden_ta_1">
            <div class="OM_field">
                <label for="trainClasse">Classe </label> 
                <select name="trainClasse" id="trainClasse">
                    <option value="2">Seconde classe</option>
                    <option value="1">Premiere classe</option>
                </select>
            </div>
            <div class="OM_field">
                <label for="trainPrixBillet">Prix du billet </label> 
                <input name="trainPrixBillet" type="text" id="trainPrixBillet" class="OM_input" />
            </div>
            <div class="OM_field">
                <label for="trainBonCommande" class="label_long">Un bon de commande a-t-il été établi ? </label> 
                <select name="trainBonCommande" id="trainBonCommande">
                    <option value="NON">NON</option>
                    <option value="OUI">OUI</option>
                </select>
            </div>
            <div class="OM_field">
                <label for="trainCarteAffaire" class="label_long">Payé par carte affaire </label> 
                <select name="trainCarteAffaire" id="trainCarteAffaire">
                    <option value="NON">NON</option>
                    <option value="OUI">OUI</option>
                </select>
            </div>
        </div>
        <div class="OM_field">
            <div class="checkCtn" id="checkCtn2">
        		<input type="checkbox" name="moyensLocomotion" id="om_transport_2" value="avion_bateau" onChange="show_transport('2');" />
            </div>
            <label for="om_passager" class="label_transport">Avion ou bateau</label>
        </div>
        <div id="OM_hidden_ta_2">
            <div class="OM_field">
                <label for="avionBateauClasse">Classe </label> 
                <select name="avionBateauClasse" id="avionBateauClasse">
                    <option value="2">Seconde classe</option>
                    <option value="1">Premiere classe</option>
                </select>
            </div>
            <div class="OM_field">
                <label for="avionBateauPrixBillet">Prix du billet </label> 
                <input name="avionBateauPrixBillet" type="text" id="avionBateauPrixBillet" class="OM_input" />
            </div>
            <div class="OM_field">
                <label for="avionBateauBonCommande" class="label_long">Un bon de commande a-t-il été établi ? </label> 
                <select name="avionBateauBonCommande" id="avionBateauBonCommande">
                    <option value="NON">NON</option>
                    <option value="OUI">OUI</option>
                </select>
            </div>
            <div class="OM_field">
                <label for="avionBateauCarteAffaire" class="label_long">Payé par carte affaire </label> 
                <select name="avionBateauCarteAffaire" id="avionBateauCarteAffaire">
                    <option value="NON">NON</option>
                    <option value="OUI">OUI</option>
                </select>
            </div>
        </div>




        <div class="OM_field">
            <div class="checkCtn" id="checkCtn3">
        		<input type="checkbox" name="moyensLocomotion" id="om_transport_3" value="taxi" onChange="show_transport('3');" />
            </div>
          <label for="om_passager" class="label_transport">Taxi (Dans l'intérêt du service, le Directeur autorise le remboursement du taxi)</label>
<!--            <select name="taxi" id="taxi">
              <option value="OUI">OUI</option>
              <option value="NON">NON</option>
            </select>
-->          <div class="OM_field"></div>
    </div>
        



    <div class="OM_field">
            <div class="checkCtn" id="checkCtn4">
        		<input type="checkbox" name="moyensLocomotion" id="om_transport_4" value="vehicule_location" onChange="show_transport('4');" />
            </div>
            <label for="om_passager" class="label_transport">Location de véhicule (Dans l'intérêt du service, le Directeur autorise le remboursement de la location de véhicule)</label>
<!--            <select name="locationVehicule" id="locationVehicule">
                    <option value="Oui">OUI</option>
                    <option value="Non">NON</option>
            </select>
-->    </div>

<!--
        <div id="OM_hidden_ta_4">
            <div class="OM_field">
                <label for="locationVehicule" class="label_verylong"></label> 
                
            </div>
        </div>
-->




        <div class="OM_field">
            <div class="checkCtn" id="checkCtn5">
        		<input type="checkbox" name="moyensLocomotion" id="om_transport_5" value="vehicule_service" onChange="show_transport('5');" />
            </div>
            <label for="om_passager" class="label_transport">Véhicule de service</label>
        </div>
        <div id="OM_hidden_ta_5">
            <div class="OM_field">
                <label for="vehiculeService">Véhicule de service :</label> 
                <select name="vehiculeService" id="vehiculeService">
           			<option value="">Choisir</option>
                                
                                <% LinkedList<VehiculeService> listeVehiculesServices = base.getListeVehiculesServices() ;
                                   for (VehiculeService vehicule : listeVehiculesServices)
                                        out.println ("<option value=\"" + vehicule.getLibelleVehiculeService() + "\">" + vehicule.getLibelleVehiculeService() + " " + vehicule.getCommentaireVehiculeService() + "</option>") ;
                                %>
                                
                </select>
            </div>
            <div class="OM_field">
                <label for="vehiculeServiceNomPassagers" class="label_long">Noms des passagers :</label> 
                <input name="vehiculeServiceNomPassagers" type="text" id="vehiculeServiceNomPassagers" class="OM_input" />
            </div>

            <div class="OM_field">
                <label for="vehiculeServiceNombreKilometres" class="label_long">Distance parcourue :</label> 
                <input name="vehiculeServiceNombreKilometres" type="text" id="vehiculeServiceNombreKilometres" class="OM_input" /> km
            </div>
        </div>

        <div class="OM_field">
            <div class="checkCtn" id="checkCtn6">
        		<input type="checkbox" name="moyensLocomotion" id="om_transport_6" value="vehicule_personnel" onChange="show_transport('6');" />
            </div>
            <label for="om_passager" class="label_transport">Véhicule personnel</label>
        </div>
        <div id="OM_hidden_ta_6">
            <b>DECLARATION (Décret n° 2006-781 du 3 juillet 2006)</b><br/>
            Je sollicite l'autorisation d'utiliser mon véhicule personnel pour les besoins du service.
            <div class="OM_field">
                <label for="vehiculePersonnelMarque" class="label_long">Marque : </label> 
                <input name="vehiculePersonnelMarque" type="text" id="vehiculePersonnelMarque" class="OM_input" onChange="copy_marque();" />
            </div>
            <div class="OM_field">
                <label for="vehiculePersonnelPuissance" class="label_long">Puissance : </label> 
                <input name="vehiculePersonnelPuissance" type="text" id="vehiculePersonnelPuissance" class="OM_input" onChange="copy_puissance();" />
            </div>
            <div class="OM_field">
                <label for="vehiculePersonnelImmatriculation" class="label_long">Immatriculation : </label> 
                <input name="vehiculePersonnelImmatriculation" type="text" id="vehiculePersonnelImmatriculation" class="OM_input" onChange="copy_immatriculation();" />
            </div>
            <div class="OM_field">
                <label for="vehiculePersonnelDateAcquisition" class="label_long">Date d'acquisition : </label> 
                <input name="vehiculePersonnelDateAcquisition" type="text" id="vehiculePersonnelDateAcquisition" class="OM_input" onChange="copy_dateacquisition();" />
            </div>
            et déclare avoir souscrit auprès de <input name="vehiculePersonnelNomAssureur" type="text" id="vehiculePersonnelNomAssureur" onChange="copy_compagnie();" />, une police d'assurance garantissant de manière illimitée ma responsabilité personnelle aux termes des articles 1382, 1383, 1384 du Code Civil, ainsi que éventuellement, la responsabilité de l'Etat, y compris le cas où celle-ci est engagée vis-à-vis des personnes transportées. Cette police comprend l'assurance contentieuse.<br /><br />
            
            <label class="label_verylong">
            <div class="checkCtn" id="checkCtn10"><input type="checkbox" id="om_perso_assurance" name="om_perso_assurance" onclick="copy_assurance();"></div> Je certifie avoir contracté l'assurance complémentaire couvrant tous les risques non compris dans l'assurance obligatoire.</label>
<p>&nbsp;</p>
            <div class="OM_field">
                <label for="vehiculePersoNombreKilometres" class="label_long">Distance parcourue :</label> 
                <input name="vehiculePersoNombreKilometres" type="text" id="vehiculePersoNombreKilometres" class="OM_input" /> km
            </div>
    </div>
    </div>
    <div id="OM_hidden_ta_annexe">
        <label for="om_serv_distance" class="label_verylong">Rapport justifiant l'utilisation du véhicule personnel ou de location</label><br />
        <textarea name="vehiculeJustificatif" rows="3" id="vehiculeJustificatif" size="100" maxlength="100"></textarea>
    </div>
    <div id="OM_hidden_ta_annexe2">
    	<label class="label_verylong">
        <div class="checkCtn" id="checkCtn11"><input type="checkbox" id="om_certif_permis" name="om_certif_permis" onclick="copy_certif_permis();"></div>Je certifie être en possession du permis de conduire depuis plus d'un an.</label>
    </div>
    
</fieldset>

<!-- 
======================================================================

FRAIS ANNEXES

======================================================================
-->
<fieldset>
    <legend>FRAIS ANNEXES :</legend>
    <div class="OM_field">
        <label for="fraisInscription" class="label_long">Frais d'inscription :</label> 
        <select name="fraisInscription" id="fraisInscription" onChange="show_dc();">
            <option value="Non">NON</option>
            <option value="Oui">OUI</option>
        </select>
    </div>
    <div id="OM_hidden_dc">
        <div class="OM_field">
            <label for="montantInscription" class="label_long">Montant :</label>
            <input name="montantInscription" type="text" id="montantInscription" class="OM_input_long" maxlength="10" />
        </div>
        <div class="OM_field">
            <label for="bonCommandeInscription" class="label_long">Un bon de commande a été établi :</label> 
            <select name="bonCommandeInscription" id="bonCommandeInscription">
                <option value="Non">NON</option>
                <option value="Oui">OUI</option>
            </select>
        </div>
    </div>
    <p>&nbsp;</p>
    <div class="OM_field">
      <label for="avance" class="label_long">Avance :</label> 
            <select name="avance" id="avance">
                <option value="Non">NON</option>
                <option value="Oui">OUI</option>
    </select>    </div>

</fieldset>



<!-- 
======================================================================

INFORMATIONS FINANCIÈRES

======================================================================
-->
<fieldset>
    <legend>INFORMATIONS FINANCIÈRES :</legend>
    <div class="OM_field">
        <label for="centreFinancier" class="label_verylong">Centre financier :</label>   	
        <select name="centreFinancier" id="centreFinancier">
	<option value="">Choisir</option>      
        <% LinkedList<CentreFinancier> listeCentresFinanciers = base.getListeCentresFinanciersActifs() ;
           for (CentreFinancier centre : listeCentresFinanciers)
           {
               out.println ("<option value=\"" + centre.getLibelle() + "\">" + centre.getLibelle () + " - " + centre.getLibelleLong() + " (" + centre.getResponsableFinancier().getPrenom() + " " + centre.getResponsableFinancier().getNom() + ")" + "</option>") ;
           }
        %>
        </select> <span class="oblig"> (1)</span>
    </div>

    <div class="OM_field">
        <label for="eOTP" class="label_verylong">Projet ou eOTP :</label>
    	<input type="text" class="OM_input" name="projet" id="projet" maxlength="20"/><span class="oblig"> (1)</span>
    </div>

    <div class="OM_field">
        <label for="autoriteHierarchique" class="label_verylong">Autorit&eacute; hi&eacute;rarchique  :</label>
        <select name="autoriteHierarchique" id="autoriteHierarchique">
	<option value="">Choisir</option>       
        <% LinkedList<Responsable> listeAutoritesHierarchiques = base.getListeAutoritesHierarchiquesActifs();
           for (Responsable autoriteHierarchique : listeAutoritesHierarchiques)
           {
               out.println ("<option value=\"" + autoriteHierarchique.getNumero() + ";" + autoriteHierarchique.getNom() + ";" + autoriteHierarchique.getPrenom () + ";" + autoriteHierarchique.getTelephone() + ";" + autoriteHierarchique.getCourriel () + "\">" + autoriteHierarchique.getNom() + " " + autoriteHierarchique.getPrenom() + "</option>") ;
           }
        %>  
        </select> <span class="oblig"> (1)</span>
    </div>
</fieldset>

<!-- 
======================================================================

INFORMATIONS COMPLÉMENTAIRES

======================================================================
-->
<fieldset>
    <legend>INFORMATIONS COMPLÉMENTAIRES :</legend>
	<textarea id="informationsComplementaires" name="informationsComplementaires" size="100" maxlength="100"></textarea>
</fieldset>

</form>
<div class="oblig_exp">(1) champs obligatoires</div>
<div class="OM_submit"><a class="OM_submit_bt" href="javascript:checkForm();">Envoi pour signature</a>
</div>


</body>
</html>