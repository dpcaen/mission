/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensicaen.yousign;

import com.yousign.client.YousignClient;
import com.yousign.client.YousignClientConstants;
import com.yousign.client.YousignClientException;
import com.yousign.client.YousignIFrameURL;
import com.yousign.client.stub.cosign.CosignFileParams;
import com.yousign.client.stub.cosign.CosignerInfosParams;
import com.yousign.client.stub.cosign.InitCosignResp;
import com.yousign.client.stub.cosign.TokenInfosResp;
import com.yousign.client.stub.cosign.VisibleOptionsParams;
import com.yousign.client.ws.YousignClientFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

/**
 *
 * @author dp
 */
public class YousignInitSignature 
{
    private OrdreMissionUtil util ;
    
    public YousignInitSignature ()
    {
        
    }

    public String sign ()
    {
        InitCosignResp cosignResp = null ;
        try { 
                cosignResp = initCosign () ;
            } catch (YousignClientException ex) 
            {
                return "Desole, une erreur est survenue: " + ex.getMessage() ;           
            } 
        
        for (TokenInfosResp token : cosignResp.getTokens()) 
        {
            YousignIFrameURL ysIframeURL = new YousignIFrameURL(YousignConfig.ENVIRONNEMENT, token.getToken());
           // ysIframeURL.setUrlcallback("http://www.ensicaen.fr");
            
        }
        return null ;
    }
    
    private InitCosignResp initCosign() throws YousignClientException {
        // create the yousign client instance
        YousignClient client = YousignClientFactory.getInstance().createYousignClient(YousignConfig.AUTHENT_USER, YousignConfig.AUTHENT_PASSWORD, YousignConfig.AUTHENT_APIKEY, YousignConfig.ENVIRONNEMENT, YousignConfig.ENCRYPTED_PASSWORD);
        //init cosignature
        List<CosignFileParams> lstCosignedFile = new ArrayList<>();
        List<CosignerInfosParams> lstCosignerInfos = new ArrayList<>();
        // cosign infos
        // TODO set fileToSign (full path), cosignerVisibleOptionRef, firstname, name and phone
        //
        // Contenu des courriels qui seront envoyés
        //
        String message = "Ordre de mission à signer";
        String title = "titre";
        String initMailSubject = "Ordre de mission à signer";
        String initMail = "Vous avez un ordre de mission à signer : {yousignUrl}";
        String endMailSubject = "Ordre de mission signé";
        String endMail = "Votre ordre de mission a été signé; cliquer sur le lien pour le télécharger : {yousignUrl}";
        String language = "FR";
  //      String mode = YousignClientConstants.COSIGN_MODE_IFRAME;
        String mode = YousignClientConstants.COSIGN_MODE_CLASSIC ;
        //
        // On genere le nom du fichier PDF
        //
        String fileToSign = util.generePDFFilenameToUse() ;
        //
        // On place le fichier dans la liste des fichiers à signer (fichier unique ici)
        //
        CosignFileParams file1 = new CosignFileParams();
        FileDataSource file1DataSource = new FileDataSource(new File(fileToSign));

        DataHandler file1Data = new DataHandler(file1DataSource);

        file1.setContent(file1Data);
        file1.setName(fileToSign);


        //
        // Informations pour la signature du missionnaire
        //
        VisibleOptionsParams file1VisibleOptions = new VisibleOptionsParams();
        file1VisibleOptions.setIsVisibleSignature(true);
        file1VisibleOptions.setVisibleSignaturePage(1);
        file1VisibleOptions.setVisibleRectangleSignature("50,20,150,60");
        file1VisibleOptions.setMail(util.getOrdreMission().getEmailMissionnaire ());
        file1.getVisibleOptions().add(file1VisibleOptions);
 //       lstCosignedFile.add(file1);
        
        CosignerInfosParams cosigner1 = new CosignerInfosParams();
        cosigner1.setFirstName(util.getOrdreMission().getPrenomMissionnaire());
        cosigner1.setLastName(util.getOrdreMission().getNomMissionnaire());
        cosigner1.setMail(util.getOrdreMission().getEmailMissionnaire());
        cosigner1.setPhone(util.getOrdreMission().getTelephoneMissionnaire());
        cosigner1.setProofLevel(YousignClientConstants.PROOF_LEVEL_LOW);
        cosigner1.setAuthenticationMode(YousignConfig.missionnaireValidation);
        lstCosignerInfos.add(cosigner1);

        //
        // Informations pour la signature du responsable financier (si différent du missionnaire)
        //
        if ( ! util.getOrdreMission().getEmailResponsableFinancier().equalsIgnoreCase(util.getOrdreMission().getEmailMissionnaire()))
        {
            VisibleOptionsParams file2VisibleOptions = new VisibleOptionsParams();
            file2VisibleOptions.setIsVisibleSignature(true);
            file2VisibleOptions.setVisibleSignaturePage(1);
            file2VisibleOptions.setVisibleRectangleSignature("450,20,550,60");
            file2VisibleOptions.setMail(util.getOrdreMission().getEmailResponsableFinancier());
            file1.getVisibleOptions().add(file2VisibleOptions);
       
            CosignerInfosParams cosigner2 = new CosignerInfosParams();
            cosigner2.setFirstName(util.getOrdreMission().getPrenomResponsableFinancier());
            cosigner2.setLastName(util.getOrdreMission().getNomResponsableFinancier());
            cosigner2.setMail(util.getOrdreMission().getEmailResponsableFinancier());
            cosigner2.setPhone(util.getOrdreMission().getTelephoneResponsableFinancier());
            cosigner2.setProofLevel(YousignClientConstants.PROOF_LEVEL_LOW);
            cosigner2.setAuthenticationMode(YousignConfig.responsableFinancierValidation);
            lstCosignerInfos.add(cosigner2);
        }
        //
        // Informations pour la signature du responsable de service (si différent du responsable financier)
        //
            if ( ! util.getOrdreMission().getEmailResponsableFinancier().equalsIgnoreCase(util.getOrdreMission().getEmailAutoriteHierarchique()))
        {
            VisibleOptionsParams file3VisibleOptions = new VisibleOptionsParams();
            file3VisibleOptions.setIsVisibleSignature(true);
            file3VisibleOptions.setVisibleSignaturePage(1);
            file3VisibleOptions.setVisibleRectangleSignature("250,20,350,60");
            file3VisibleOptions.setMail(util.getOrdreMission().getEmailAutoriteHierarchique());
            file1.getVisibleOptions().add(file3VisibleOptions);
       
            CosignerInfosParams cosigner3 = new CosignerInfosParams();
            cosigner3.setFirstName(util.getOrdreMission().getPrenomAutoriteHierarchique());
            cosigner3.setLastName(util.getOrdreMission().getNomAutoriteHierarchique());
            cosigner3.setMail(util.getOrdreMission().getEmailAutoriteHierarchique());
            cosigner3.setPhone(util.getOrdreMission().getTelephoneAutoriteHierarchique());
            cosigner3.setProofLevel(YousignClientConstants.PROOF_LEVEL_LOW);
            cosigner3.setAuthenticationMode(YousignConfig.autoriteHierarchiqueValidation);
            lstCosignerInfos.add(cosigner3);
        }    
        
        lstCosignedFile.add(file1);
        
        //send the cosign demand
        return client.initCosign(lstCosignedFile, lstCosignerInfos, message, title, initMailSubject, initMail, endMailSubject, endMail, language, mode, YousignConfig.bArchivage);
    }

    public void setUtil(OrdreMissionUtil util) {
        this.util = util;
    }
    


}
