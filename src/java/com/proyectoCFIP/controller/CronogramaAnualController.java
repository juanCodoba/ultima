/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.CronogramaAnual;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.CronogramaAnualFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class CronogramaAnualController implements Serializable{

    @EJB
    private CronogramaAnualFacade cronogramaAnualFacade;
    private CronogramaAnual cronogramaAnualActual;
    private List<CronogramaAnual> cronogramaAnualList;
    public CronogramaAnualController() {
    }

    public CronogramaAnualFacade getCronogramaAnualFacade() {
        return cronogramaAnualFacade;
    }

    public void setCronogramaAnualFacade(CronogramaAnualFacade cronogramaAnualFacade) {
        this.cronogramaAnualFacade = cronogramaAnualFacade;
    }

    public CronogramaAnual getCronogramaAnualActual() {
        return cronogramaAnualActual;
    }

    public void setCronogramaAnualActual(CronogramaAnual cronogramaAnualActual) {
        this.cronogramaAnualActual = cronogramaAnualActual;
    }

    public List<CronogramaAnual> getCronogramaAnualList() {
        return cronogramaAnualList = getCronogramaAnualFacade().findAll();
    }

    public void setCronogramaAnualList(List<CronogramaAnual> cronogramaAnualList) {
        this.cronogramaAnualList = cronogramaAnualList;
    }
    public void prepareCreate(){
        cronogramaAnualActual = new CronogramaAnual();    
    }
    public void add(){
        try {
            recargarLista();
            cronogramaAnualActual.setEstado(false);
            cronogramaAnualActual.setIdUsuario(new Usuario(275));
            getCronogramaAnualFacade().create(cronogramaAnualActual);
            //ticket="CFIPMC-C";
            //sendMailRegistroTec();
            //sendMailRegistroUser();
            
            getCronogramaAnualList();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actividad creada", "La actividad fue creada satisfactoriamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al crear actividad", "No se pudo crear la actividad");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    public void update(){
        try {
            recargarLista();
            getCronogramaAnualFacade().edit(cronogramaAnualActual);
            //ticket="CFIPMC-C";
            //sendMailRegistroTec();
            //sendMailRegistroUser();
            getCronogramaAnualList();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actividad editada", "La actividad fue editada satisfactoriamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al crear actividad", "No se pudo crear la actividad");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    public String prepareList(){
        return "/administrador/modManteEdificios/calendarioActividades/verCalendarioAnual";
    }
    public void recargarLista(){
        cronogramaAnualList = null;
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
