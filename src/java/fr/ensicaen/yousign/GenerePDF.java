/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensicaen.yousign;
 
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dp
 */
public class GenerePDF 
{
    private final String title = "Ordre de mission numéro: " ;
    
    private final Font fontTitleDocument = new Font (Font.FontFamily.TIMES_ROMAN,30,Font.BOLD) ;
    private final Font fontTitleSection = new Font (Font.FontFamily.TIMES_ROMAN,14,Font.BOLDITALIC) ;
    private final Font fontItemTitleSection = new Font (Font.FontFamily.TIMES_ROMAN,12,Font.ITALIC) ;
    private final Font fontItemSection = new Font (Font.FontFamily.TIMES_ROMAN,12,Font.NORMAL) ;
    private final Font fontBoldItemSection = new Font (Font.FontFamily.TIMES_ROMAN,12,Font.BOLD) ;
    private final Font fontItemSectionItalic = new Font (Font.FontFamily.TIMES_ROMAN,12,Font.ITALIC) ;
    
    private OrdreMissionUtil util ;
    
    public GenerePDF ()
    {
      
    }

    public void setUtil(OrdreMissionUtil util) {
        this.util = util;
    }
    
    public void genere ()
    {
        try {
                OutputStream file = new FileOutputStream(util.generePDFFilenameToUse());
                
                Document document = new Document();
                PdfWriter.getInstance(document, file);
                document.open();
                //
                // Affichage du logo, de la date et l'heure et avec ou sans frais
                //
                document.add (addEntete ()) ;
                //
                // Caractéristiques du missionnaire (table: nom, prenom, courriel = 1ère colonne, adresse = 2ème colonne
                //
                PdfPTable tableIdentite = new PdfPTable(2);
                tableIdentite.setWidthPercentage(100) ;
                
                PdfPCell nomCell = new PdfPCell (new Phrase (new Chunk ("Nom: " + util.getOrdreMission().getNomMissionnaire() + "\n" + "Prenom: " + util.getOrdreMission().getPrenomMissionnaire() + "\n\n" + "Courriel: " + util.getOrdreMission().getEmailMissionnaire(),fontItemSection))) ;
                nomCell.setBorder (Rectangle.NO_BORDER) ;
                tableIdentite.addCell (nomCell);
                
                Chunk adresseChunk = new Chunk ("Adresse: " + "\n" + util.getOrdreMission().getRueMissionnaire() + "\n" + util.getOrdreMission().getCodepostalMissionnaire() + " " + util.getOrdreMission().getVilleMissionnaire() + "\n" + util.getOrdreMission().getPaysMissionnaire(),fontItemSection) ;
                PdfPCell adresseCell = new PdfPCell (new Phrase (adresseChunk)) ;
                adresseCell.setBorder(Rectangle.NO_BORDER);
                tableIdentite.addCell (adresseCell) ;
                
                Paragraph identite ;
                identite = new Paragraph () ;
                addEmptyLine (identite,1) ;
                identite.add (tableIdentite) ;
                document.add (identite) ;
                //
                // Numero IBAN
                //
                Paragraph iban = new Paragraph () ;
                addEmptyLine (iban,1) ;
                iban.add (new Chunk ("Numéro IBAN: " + util.getOrdreMission().getIban1() + " " + util.getOrdreMission().getIban2() + " " + util.getOrdreMission().getIban3() + " " + util.getOrdreMission().getIban4() + " " + util.getOrdreMission().getIban5() + " " + util.getOrdreMission().getIban6() + " " + util.getOrdreMission().getIban7(),fontItemSection)) ;              
                document.add (iban) ;
                //
                // Motif de la mission
                //
                Paragraph mission = new Paragraph () ;
                addEmptyLine (mission,1) ;
                mission.add (new Chunk ("Motif de la mission: " + util.getOrdreMission().getMotifMission (),fontItemSection)) ;
                addEmptyLine (mission,1) ;
                //
                // Détails de la mission (lieu, dates, ...)
                //
                PdfPTable tableMission = new PdfPTable (7) ;
                tableMission.setWidthPercentage(100) ;
                float [] columnWidths = { 1f,2f,1.5f,1f,2f,1.5f,1f } ;
                tableMission.setWidths(columnWidths);
                PdfPCell cell ;
                //
                // Ligne d'entete du tableau des détails de la mission
                //
                tableMission.addCell ("") ;
                cell = new PdfPCell (new Phrase ("Lieu de départ")); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase ("Date")); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase ("Heure")); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase ("Lieu d'arrivée")); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase ("Date")); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase ("Heure")); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                //
                // description du voyage aller
                //
                tableMission.addCell ("Aller") ; 
                cell = new PdfPCell (new Phrase (util.getOrdreMission().getLieuDepartAller())); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase (util.getOrdreMission().getDateDepartAller())); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase (util.getOrdreMission().getHeureDepartAller() + ":" + util.getOrdreMission().getMinuteDepartAller())); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase (util.getOrdreMission().getLieuArriveeAller())); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase (util.getOrdreMission().getDateArriveeAller())); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase (util.getOrdreMission().getHeureArriveeAller() + ":" + util.getOrdreMission().getMinuteArriveeAller())); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                
                //
                // Description du voyage retour
                //                
                tableMission.addCell ("Retour") ; 
                cell = new PdfPCell (new Phrase (util.getOrdreMission().getLieuDepartRetour())); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase (util.getOrdreMission().getDateDepartRetour())); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase (util.getOrdreMission().getHeureDepartRetour() + ":" + util.getOrdreMission().getMinuteDepartRetour())); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase (util.getOrdreMission().getLieuArriveeRetour())); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase (util.getOrdreMission().getDateArriveeRetour())); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                cell = new PdfPCell (new Phrase (util.getOrdreMission().getHeureArriveeRetour() + ":" + util.getOrdreMission().getMinuteArriveeRetour())); cell.setHorizontalAlignment(Element.ALIGN_CENTER); tableMission.addCell (cell) ;
                                    
                mission.add (tableMission) ;
                document.add (mission) ;
                //
                // Moyens de transport
                //
                Paragraph moyensTransport = new Paragraph () ;
                addEmptyLine (moyensTransport,1) ;
                moyensTransport.add (new Chunk ("Moyens de transport utilisés\n",fontTitleSection)) ;
                if (util.use ("Passager"))
                {
                    moyensTransport.add (new Chunk ("Pas de frais de transport comme passager de la voiture de: " + util.getOrdreMission().getNomConducteur() + "\n", fontItemSection)) ;                    
                }
                if (util.use ("Train"))
                {
                    moyensTransport.add (new Chunk ("Train:   Classe: " + util.getOrdreMission().getTrainClasse() + "   Prix des billets: " + util.getOrdreMission().getTrainPrixBillet() + " €   Bon de commande: " + util.getOrdreMission().getTrainBonCommande() + "   Carte affaire: " + util.getOrdreMission().getTrainCarteAffaire() + "\n", fontItemSection)) ;
                }
                if (util.use ("Avion_Bateau"))
                {
                    moyensTransport.add (new Chunk ("Avion ou Bateau:   Classe: " + util.getOrdreMission().getAvionBateauClasse() + "   Prix des billets: " + util.getOrdreMission().getAvionBateauPrixBillet() + " €  Bon de commande: " + util.getOrdreMission().getAvionBateauBonCommande() + "   Carte affaire: " + util.getOrdreMission().getAvionBateauCarteAffaire() + "\n", fontItemSection)) ;
                }
                if (util.use ("Vehicule_Service"))
                {
                    moyensTransport.add (new Chunk ("Voiture de service: " + util.getOrdreMission().getVehiculeService() + "  Nombre de kilomètres: " + util.getOrdreMission().getVehiculeServiceNombreKilometres() + "\n", fontItemSection)) ;
                    moyensTransport.add (new Chunk ("Nom des passagers: " + util.getOrdreMission().getVehiculeServiceNomPassagers() + "\n",fontItemSection)) ;
                }
                if (util.use ("Vehicule_Personnel"))
                {
                    moyensTransport.add (new Chunk ("En vertu du décret No 2006-781 du 3 juillet 2006: je soussigné, " + util.getOrdreMission().getPrenomMissionnaire() + " " + util.getOrdreMission().getNomMissionnaire() + ", sollicite l'autorisation ",fontItemSection)) ;
                    moyensTransport.add (new Chunk ("d'utiliser mon véhicule de marque " + util.getOrdreMission().getVehiculePersonnelMarque() + ", de puissance " + util.getOrdreMission().getVehiculePersonnelPuissance() + ", d'immatriculation: " + util.getOrdreMission().getVehiculePersonnelImmatriculation(),fontItemSection)) ;
                    moyensTransport.add (new Chunk (", de date d'acquisition " + util.getOrdreMission().getVehiculePersonnelDateAcquisition() + " pour me rendre à " + util.getOrdreMission().getLieuArriveeAller(),fontItemSection)) ;
                    moyensTransport.add (new Chunk (" et déclare avoir souscrit auprès de " + util.getOrdreMission().getVehiculePersonnelNomAssureur() + " une police d'assurance garantissant de manière illimitée ma responsabilité personnelle aux termes ", fontItemSection)) ;
                    moyensTransport.add (new Chunk ("des articles 1382, 1383, 1384 du Code Civil, ainsi qu'éventuellement la responsabilité de l'Etat, y compris le cas où celle-ci est engagée vis-à-vis des personnes transportées. ",fontItemSection)) ;
                    moyensTransport.add (new Chunk ("Cette police comprend l'assurance contentieuse.\n",fontItemSection)) ;
                    moyensTransport.add (new Chunk ("DECLARATION COMPLEMENTAIRE: je certifie avoir contracté l'assurance complémentaire couvrant tous les risques non compris dans l'assurance obligatoire.\n", fontItemSection)) ;                    
                }
                addEmptyLine (moyensTransport,1) ;
                if (util.use ("taxi"))
                {
                    moyensTransport.add (new Chunk ("Le Directeur autorise le remboursement d'un taxi " + "\n", fontItemSection)) ;
                }
                if (util.use ("vehicule_location"))
                {
                    moyensTransport.add (new Chunk ("Le Directeur autorise le remboursement d'un véhicule de location " + "\n", fontItemSection)) ;
                }
                if (util.use ("Vehicule_Service") || util.use ("Vehicule_Personnel") || util.use ("vehicule_location"))
                {
                    moyensTransport.add (new Chunk ("\nJustication de l'utilisation du véhicule personnel ou de la location:" + util.getOrdreMission().getVehiculeJustificatif() + "\n", fontItemSection)) ;
                    moyensTransport.add (new Chunk ("\nJe certifie être en possession du permis de conduire depuis plus d'un an\n",fontBoldItemSection)) ;
                }

                document.add (moyensTransport) ;
                //
                // Frais annexe
                //
                Paragraph fraisAnnexe = new Paragraph () ;
                addEmptyLine (fraisAnnexe,1) ;
                fraisAnnexe.add (new Chunk ("Frais Annexe\n",fontTitleSection)) ;
                fraisAnnexe.add (new Chunk ("Frais d'inscription: " + util.getOrdreMission().getFraisInscription() + "    Montant: " + util.getOrdreMission().getMontantInscription() + " €",fontItemSection)) ;               
                document.add (fraisAnnexe) ;
                
                Paragraph avance = new Paragraph () ;
                addEmptyLine (avance,1) ;
                avance.add (new Chunk ("Avance: ",fontTitleSection)) ;
                avance.add (new Chunk (util.getOrdreMission().getAvance (),fontItemSection)) ;
                document.add (avance) ;
                //
                // Informations financieres
                //
                Paragraph finance = new Paragraph () ;
                addEmptyLine (finance,1) ;
                finance.add (new Chunk ("Informations financières\n", fontTitleSection));
                finance.add (new Chunk ("Centre financier: " + util.getOrdreMission().getCentreFinancier() + "   Projet ou eOTP: " + util.getOrdreMission().getProjet() + "\n", fontItemSection)) ;
                finance.add (new Chunk ("Responsable financier: " + util.getOrdreMission().getPrenomResponsableFinancier() + " " + util.getOrdreMission().getNomResponsableFinancier() + "\n", fontItemSection)) ;
                finance.add (new Chunk ("Autorité hiérarchique: " + util.getOrdreMission().getPrenomAutoriteHierarchique() + " " + util.getOrdreMission().getNomAutoriteHierarchique(), fontItemSection)) ;
                document.add (finance) ;
                //
                // Informations complementaires eventuelles
                //              
                if (util.getOrdreMission().getInformationsComplementaires() !=  null)
                {
                    Paragraph informationsComplementaires = new Paragraph () ;
                    informationsComplementaires.add (new Chunk ("\nInformations complementaires: ", fontTitleSection)) ;
                    informationsComplementaires.add (new Chunk (util.getOrdreMission().getInformationsComplementaires(),fontItemSection)) ;
                    document.add (informationsComplementaires) ;
                }
                             
                document.close();
                file.close();
            } catch (DocumentException | IOException e) {
                System.err.println ("Erreur a la generation du fichier " + util.getPdfFileName() + ": " + e.getMessage()) ;
            }       
    }
        
    private Paragraph addEntete ()
    {
	Paragraph paragraph = new Paragraph () ;
	PdfPTable table = new PdfPTable(2);
	table.setWidthPercentage(100) ;
        try {
                //
                // Affichage du logo
                //
                PdfPCell logoCell = new PdfPCell (Image.getInstance (YousignConfig.LOGO),false) ; // false: le logo ne remplit pas toute la cellule
                logoCell.setBorder (Rectangle.NO_BORDER) ;
                table.addCell (logoCell) ;
                //
                // Affichage de la  date et du titre
                //
                Date date = new Date( );        
                DateFormat format_fr = DateFormat.getDateInstance(DateFormat.FULL, Locale.FRENCH);                
                PdfPCell titleCell = new PdfPCell (new Phrase (new Chunk (format_fr.format(date) + "\n\n" + title +  util.getOrdreMission().getNumeroMission() + "\n" + util.getOrdreMission().getFrais(),fontItemTitleSection))) ;
                titleCell.setBorder (Rectangle.NO_BORDER) ;
                titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell (titleCell);
        } catch (BadElementException | IOException ex) {
            Logger.getLogger(GenerePDF.class.getName()).log(Level.SEVERE, null, ex);
        }
	paragraph.add(table) ;
	return paragraph ;
    }
    
    private void addEmptyLine (Paragraph paragraph, int number)
    {
        for (int i = 0 ; i < number ; i++) paragraph.add (new Paragraph (" ")) ;
    }
}
