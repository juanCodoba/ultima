/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.FtFichaTecnica;
import com.proyectoCFIP.entities.FtModificaciones;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.FtFichaTecnicaFacade;
import com.proyectoCFIP.sessions.FtModificacionesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author junio
 */
@Named(value = "ftModificaciones")
@SessionScoped
public class FtModificacionesController implements Serializable {

    @EJB
    private FtModificacionesFacade modificacionesFacade;
    private FtModificaciones modificacionesActual;
    private List<FtModificaciones> listaModificaciones;
    @EJB
    private FtFichaTecnicaFacade fichaTecnicaFacade;
    private FtFichaTecnica fichaTecnicaActual;
    private List<FtFichaTecnica> listaFichaTecnica;
    
    private Usuario usuarioActual;
    public FtModificacionesController() {
    }

    public FtModificacionesFacade getModificacionesFacade() {
        return modificacionesFacade;
    }

    public void setModificacionesFacade(FtModificacionesFacade modificacionesFacade) {
        this.modificacionesFacade = modificacionesFacade;
    }

    public FtModificaciones getModificacionesActual() {
        return modificacionesActual;
    }

    public void setModificacionesActual(FtModificaciones modificacionesActual) {
        this.modificacionesActual = modificacionesActual;
    }

   

    public List<FtModificaciones> getListaModificaciones() {
        return listaModificaciones;
    }

    public void setListaModificaciones(List<FtModificaciones> listaModificaciones) {
        this.listaModificaciones = listaModificaciones;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public FtFichaTecnicaFacade getFichaTecnicaFacade() {
        return fichaTecnicaFacade;
    }

    public void setFichaTecnicaFacade(FtFichaTecnicaFacade fichaTecnicaFacade) {
        this.fichaTecnicaFacade = fichaTecnicaFacade;
    }

    public FtFichaTecnica getFichaTecnicaActual() {
        return fichaTecnicaActual;
    }

    public void setFichaTecnicaActual(FtFichaTecnica fichaTecnicaActual) {
        this.fichaTecnicaActual = fichaTecnicaActual;
    }

    public List<FtFichaTecnica> getListaFichaTecnica() {
        return listaFichaTecnica;
    }

    public void setListaFichaTecnica(List<FtFichaTecnica> listaFichaTecnica) {
        this.listaFichaTecnica = listaFichaTecnica;
    }
    
     public void recargarLista(){
        listaModificaciones=null;
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
