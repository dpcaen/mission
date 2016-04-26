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

public class VehiculeService 
{
    private int numeroVehiculeService ;
    private String libelleVehiculeService ;
    private String commentaireVehiculeService ;

    public VehiculeService(int numeroVehiculeService, String libelleVehiculeService, String commentaireVehiculeService) {
        this.numeroVehiculeService = numeroVehiculeService;
        this.libelleVehiculeService = libelleVehiculeService;
        this.commentaireVehiculeService = commentaireVehiculeService;
    }
    
    public VehiculeService (String libelleVehiculeService, String commentaireVehiculeService) {
        this.libelleVehiculeService = libelleVehiculeService;
        this.commentaireVehiculeService = commentaireVehiculeService;
    }

    public int getNumeroVehiculeService() {
        return numeroVehiculeService;
    }

    public void setNumeroVehiculeService(int numeroVehiculeService) {
        this.numeroVehiculeService = numeroVehiculeService;
    }

    public String getLibelleVehiculeService() {
        return libelleVehiculeService;
    }

    public void setLibelleVehiculeService(String libelleVehiculeService) {
        this.libelleVehiculeService = libelleVehiculeService;
    }

    public String getCommentaireVehiculeService() {
        return commentaireVehiculeService;
    }

    public void setCommentaireVehiculeService(String commentaireVehiculeService) {
        this.commentaireVehiculeService = commentaireVehiculeService;
    }

    @Override
    public String toString() {
        return "VehiculeService{" + "numeroVehiculeService=" + numeroVehiculeService + ", libelleVehiculeService=" + libelleVehiculeService + ", commentaireVehiculeService=" + commentaireVehiculeService + '}';
    }
}

