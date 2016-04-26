// JavaScript Document

var formCheckResult = true;
// var checkTel = '';
var checkMail = '';
var checkRequired = 'Champs obligatoire';
var dateDeb = '';

// var telPattern = new RegExp(/^0[1-9]([-. ]?[0-9]{2}){4}$/);

// version unicode
var mailPattern = new RegExp(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i);

var datePattern = new RegExp(/^(([0-2]\d|[3][0-1])\/([0]\d|[1][0-2])\/[2][0]\d{2})$|^(([0-2]\d|[3][0-1])\/([0]\d|[1][0-2])\/[2][0]\d{2}\s([0-1]\d|[2][0-3])\:[0-5]\d\:[0-5]\d)$/);



var required = [
	"nomMissionnaire",
	"prenomMissionnaire",
	"emailMissionnaire",
	"emailMissionnaireverif",
	"motifMission",
	"lieuDepartAllee",
	"dateDepartAllee",
	"heureDepartAllee",
	"minuteDepartAllee",
	"lieuArriveeAllee",
	"dateArriveeAllee",
	"heureArriveeAllee",
	"minuteArriveeAllee",
	"lieuDepartRetour",
	"dateDepartRetour",
	"heureDepartRetour",
	"minuteDepartRetour",
	"lieuArriveeRetour",
	"dateArriveeRetour",
	"heureArriveeRetour",
	"minuteArriveeRetour",
	"centreFinancier",
	"projet",
	"autoriteHierarchique"
];

var required_vp = [
	"vehiculePersonnelMarque",
	"vehiculePersonnelPuissance",
	"vehiculePersonnelImmatriculation",
	"vehiculePersonnelDateAcquisition",
	"vehiculePersonnelNomAssureur",
	"vehiculeJustificatif",
	"vehiculePersoNombreKilometres"
];

function show_dc(){
	if($("#fraisInscription").val()=='Oui'){$("#OM_hidden_dc").show();}else{$("#OM_hidden_dc").hide();}
}
function restoreCBoxes(){
	for(i=0;i<7;i++){
		$("#checkCtn"+i).removeClass("error")
	}
}
function show_transport(bloc){
	if(bloc=='0'){
		if($("#om_transport_0").prop('checked')){
			for(i=1;i<7;i++){
				var checkId = "om_transport_"+i;
				var checkBlock = "OM_hidden_ta_"+i;
				$("#"+checkId ).prop('checked',false)
				$("#"+checkBlock ).hide();
			}
			restoreCBoxes();
		}
	}
	else{
		var checkId = "om_transport_"+bloc;
		var checkBlock = "OM_hidden_ta_"+bloc;
		if($("#"+checkId ).prop('checked')){
			$("#"+checkBlock ).show();
			$("#om_transport_0").prop('checked',false);
			$("#om_passager_conducteur").val("");
			restoreCBoxes();
		}
		else{$("#"+checkBlock ).hide();}
	}
	if(bloc=='4' || bloc=='6'){
		if($("#"+checkId ).prop('checked')){$("#OM_hidden_ta_annexe").show();}
		else{$("#OM_hidden_ta_annexe").hide();}
	}
	if(bloc=='4' || bloc=='5' || bloc=='6'){
		if($("#om_transport_4").prop('checked') || $("#om_transport_5").prop('checked') || $("#om_transport_6").prop('checked')){$("#OM_hidden_ta_annexe2").show();}
		else{$("#OM_hidden_ta_annexe2").hide();}
	}
}

function addForm(){
	if($("#mission_france_ou_etranger_1").prop('checked')){$("#om_formulaire_etranger").show();}
	else{$("#om_formulaire_etranger").hide();}
}

function copy_depart(){
	$("#lieuArriveeRetour").val($("#lieuDepartAllee").val());
	$("#lieuArriveeRetour").removeClass("error");
}
function copy_arrivee(){
	$("#lieuDepartRetour").val($("#lieuArriveeAllee").val());
	$("#lieuDepartRetour").removeClass("error");
}
function CheckDate(d) {
      // Cette fonction vérifie le format du séparateur jj/mm/aaaa saisi 
      var separateur="/"; // separateur entre jour/mois/annee
      var j=(d.substring(0,2));
      var m=(d.substring(3,5));
      var a=(d.substring(6));
      var ok=1;
      if ( ((d.substring(2,3)!=separateur)||(d.substring(5,6)!=separateur)) && (ok==1) ) {
         alert("Les séparateurs doivent être des "+separateur); ok=0;
      }
      return ok;
   }


//fonction de recopie de la date départ désactivée le 04/03/2016
//function copy_date(){
//	var date2copy = $("#dateDepartAllee").val();
//	if(dateDeb==''){
//		$("#dateArriveeAllee").val(date2copy);
//		$("#dateDepartRetour").val(date2copy);
//		$("#dateArriveeRetour").val(date2copy);
//		$("#dateArriveeAllee").removeClass("error");
//		$("#dateDepartRetour").removeClass("error");
//		$("#dateArriveeRetour").removeClass("error");
//		dateDeb = date2copy;
//	}
//}



function copy_marque(){
	$("#om_retour_perso_marque").val($("#om_aller_perso_marque").val());
	$("#om_retour_perso_marque").removeClass("error");
}
function copy_puissance(){
	$("#om_retour_perso_puissance").val($("#om_aller_perso_marque").val());
	$("#om_retour_perso_puissance").removeClass("error");
}
function copy_immatriculation(){
	$("#om_retour_perso_immatriculation").val($("#om_aller_perso_immatriculation").val());
	$("#om_retour_perso_immatriculation").removeClass("error");
}
function copy_dateacquisition(){
	$("#om_retour_perso_dateacquisition").val($("#om_aller_perso_dateacquisition").val());
	$("#om_retour_perso_dateacquisition").removeClass("error");
}
function copy_compagnie(){
	$("#om_retour_perso_compagnie").val($("#om_aller_perso_compagnie").val());
	$("#om_retour_perso_compagnie").removeClass("error");
}
function copy_assurance(){
	if($('#om_aller_perso_assurance').prop('checked')){
		$("#om_retour_perso_assurance").prop('checked',true)
	} else{
		$("#om_retour_perso_assurance").prop('checked',false)
	}
}
function copy_rapport(){
	$("#om_retour_rapport").val($("#om_aller_rapport").val());
	$("#om_retour_rapport").removeClass("error");
}
function copy_certif_permis(){
	if($('#om_aller_certif_permis').prop('checked')){
		$("#om_retour_certif_permis").prop('checked',true)
	} else{
		$("#om_retour_certif_permis").prop('checked',false)
	}
}
function removeSpaces(str){
	str = str.replace(/\s+/g, '');
	return str;
}

function checkFieldFormat(fieldValue,pattern){
	var result = pattern.test(fieldValue);
	return result;
}
function getFieldValue(fieldname){
	var fVal = document.forms["form1"].elements[fieldname].value;
	return fVal;
}

function checkForm(){
	
	//alert("check !");
	
	formCheckResult = true;
	
	
	for(i=0;i<required.length;i++){
		
		var fieldName = required[i];
		var targetField = $("#"+fieldName );
		
		if(removeSpaces(targetField.val())==""){
			$("#"+fieldName ).addClass("error");
			formCheckResult = false;
		}
	}
	
	if(!checkFieldFormat($("#emailMissionnaire").val(),mailPattern)){
		$("#emailMissionnaire").addClass("error");
		formCheckResult = false;
	}
	if(!checkFieldFormat($("#emailMissionnaireverif").val(),mailPattern)){
		$("#emailMissionnaireverif").addClass("error");
		formCheckResult = false;
	}
	else if($("#emailMissionnaire").val()!=$("#emailMissionnaireverif").val()){
		$("#emailMissionnaireverif").addClass("error");
		formCheckResult = false;
	}
	
	if(!checkFieldFormat($("#dateDepartAllee").val(),datePattern)){
		$("#dateDepartAllee").addClass("error");
		formCheckResult = false;
	}
	if(!checkFieldFormat($("#dateArriveeAllee").val(),datePattern)){
		$("#dateArriveeAllee").addClass("error");
		formCheckResult = false;
	}
	if(!checkFieldFormat($("#dateDepartRetour").val(),datePattern)){
		$("#dateDepartRetour").addClass("error");
		formCheckResult = false;
	}
	if(!checkFieldFormat($("#dateArriveeRetour").val(),datePattern)){
		$("#dateArriveeRetour").addClass("error");
		formCheckResult = false;
	}
	
	
	
	var atLeastOne = 0;
	for(i=0;i<7;i++){
		var checkId = "om_transport_"+i;
		if($("#"+checkId ).prop('checked')){
			atLeastOne++;
		}
	}
	if(atLeastOne==0){
		formCheckResult = false;
		for(i=0;i<7;i++){
			$("#checkCtn"+i ).addClass("error");
		}
	}
	
	if($('#om_transport_6').prop('checked')){
		for(i=0;i<required_vp.length;i++){
			
			var fieldName = required_vp[i];
			var targetField = $("#"+fieldName );
			
			if(removeSpaces(targetField.val())==""){
				$("#"+fieldName ).addClass("error");
				formCheckResult = false;
			}
		}
		if(!$('#om_perso_assurance').prop('checked')){
			$("#checkCtn10").addClass("error");
			formCheckResult = false;
		}
	}
	

	if($('#om_transport_4').prop('checked') || $('#om_transport_5').prop('checked') || $('#om_transport_6').prop('checked')){
		if(!$('#om_certif_permis').prop('checked')){
			$("#checkCtn11").addClass("error");
			formCheckResult = false;
		}
	}

	if(!formCheckResult){
		$("#errors").show();
		$("html, body").animate({ scrollTop: 0 }, "slow");
	}
	
	
	else{
		$("#form1").submit();
	}
	
}

function init(){

	//alert("init");
	show_dc();
	show_transport('a');
	show_transport('r');
	
	$(function() {$( ".datepicker" ).datepicker();});
	
	for(i=0;i<required.length;i++){
		var fieldName = required[i];
		var fieldObj = document.getElementById(fieldName);
		fieldObj.addEventListener("blur", function(e) {
			var id2jq = e.target.id;
			$( "#"+id2jq ).removeClass("error");
		});
	}
	for(i=0;i<required_vp.length;i++){
		var fieldName = required_vp[i];
		var fieldObj = document.getElementById(fieldName);
		fieldObj.addEventListener("blur", function(e) {
			var id2jq = e.target.id;
			$( "#"+id2jq ).removeClass("error");
		});
	}
	
	for(i=1;i<7;i++){
		var checkId = "om_transport_"+i;
		var checkBlock = "OM_hidden_ta_"+i;
		if($("#"+checkId ).prop('checked')){
			$("#"+checkBlock ).show();
		}
	}
	
	$('#om_certif_permis').click( function(e) {
			$("#checkCtn11").removeClass("error");
		});
	$('#om_perso_assurance').click( function(e) {
			$("#checkCtn10").removeClass("error");
		});
	
	if($("#mission_france_ou_etranger_1").prop('checked')){$("#om_formulaire_etranger").show();}
	
	if($("#om_transport_4").prop('checked') || $("#om_transport_5").prop('checked') || $("#om_transport_6").prop('checked')){$("#OM_hidden_ta_annexe2").show();}
	
	if($("#om_transport_4").prop('checked')){$("#OM_hidden_ta_annexe").show();}
}


$(document).ready(function() { init() });