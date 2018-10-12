/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.CronogramaMantenimientoMaquina;
import com.proyectoCFIP.entities.DiagnosticoMaquina;
import com.proyectoCFIP.entities.EstadoCronograma;
import com.proyectoCFIP.sessions.CronogramaMantenimientoMaquinaFacade;
import com.proyectoCFIP.sessions.DiagnosticoMaquinaFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class DiagnosticoMaquinaController implements Serializable{

    @EJB
    private DiagnosticoMaquinaFacade diagnosticoMaquinaFacade;
    @EJB
    private CronogramaMantenimientoMaquinaFacade cronogramaMantenimientoFacade;
    private CronogramaMantenimientoMaquina cronogramaMantenimientoMaquinaActual;
    private DiagnosticoMaquina diagnosticoMaquinaActual;
    private List<DiagnosticoMaquina> diagnosticoMaquinaList;
    
    public DiagnosticoMaquinaController() {
    }

    public DiagnosticoMaquinaFacade getDiagnosticoMaquinaFacade() {
        return diagnosticoMaquinaFacade;
    }

    public void setDiagnosticoMaquinaFacade(DiagnosticoMaquinaFacade diagnosticoMaquinaFacade) {
        this.diagnosticoMaquinaFacade = diagnosticoMaquinaFacade;
    }

    public DiagnosticoMaquina getDiagnosticoMaquinaActual() {
        return diagnosticoMaquinaActual;
    }

    public void setDiagnosticoMaquinaActual(DiagnosticoMaquina diagnosticoMaquinaActual) {
        this.diagnosticoMaquinaActual = diagnosticoMaquinaActual;
    }

    public List<DiagnosticoMaquina> getDiagnosticoMaquinaList() {
        return diagnosticoMaquinaList = getDiagnosticoMaquinaFacade().findAll();
    }

    public void setDiagnosticoMaquinaList(List<DiagnosticoMaquina> diagnosticoMaquinaList) {
        this.diagnosticoMaquinaList = diagnosticoMaquinaList;
    }

    public CronogramaMantenimientoMaquinaFacade getCronogramaMantenimientoFacade() {
        return cronogramaMantenimientoFacade;
    }

    public void setCronogramaMantenimientoFacade(CronogramaMantenimientoMaquinaFacade cronogramaMantenimientoFacade) {
        this.cronogramaMantenimientoFacade = cronogramaMantenimientoFacade;
    }

    public CronogramaMantenimientoMaquina getCronogramaMantenimientoMaquinaActual() {
        return cronogramaMantenimientoMaquinaActual;
    }

    public void setCronogramaMantenimientoMaquinaActual(CronogramaMantenimientoMaquina CronogramaMantenimientoMaquinaActual) {
        this.cronogramaMantenimientoMaquinaActual = CronogramaMantenimientoMaquinaActual;
    }
    
    
    
    public String add(){
        diagnosticoMaquinaActual.setIdCronogramaMantenimientoMaquina(cronogramaMantenimientoMaquinaActual);
        getDiagnosticoMaquinaFacade().create(diagnosticoMaquinaActual);
        cronogramaMantenimientoMaquinaActual.setEstado(true);
        cronogramaMantenimientoMaquinaActual.setIdEstadoCronograma(new EstadoCronograma(3));
        updateCronograma();
        //sendMailSolucionUser();
        return "adminListMantenimientoCorrectivo";
    }
    public String prepareCreate(){
        diagnosticoMaquinaActual = new DiagnosticoMaquina();
        return "adminCrearDiagnosticoCorrectivo";
    }
    public String list(){
        return "";
    }
     public void updateCronograma() {
        try {
            getCronogramaMantenimientoFacade().edit(cronogramaMantenimientoMaquinaActual);
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }
    private void addErrorMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    private void addSuccessMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }
}
