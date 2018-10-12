/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.AuPeriodoPlanAuditoria;
import com.proyectoCFIP.entities.CalidadGestionConocimiento;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.CalidadGestionConocimientoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author junio
 */
@Named(value = "calidadGestionConocimientoController")
@SessionScoped
public class CalidadGestionConocimientoController implements Serializable {

    @EJB
    private CalidadGestionConocimientoFacade gestionConocimientoFacade;
    private CalidadGestionConocimiento gestionConocimientoActual;
    private List<CalidadGestionConocimiento> listaGestionConocimiento;
    
    private Usuario usuarioActual;
    
    public CalidadGestionConocimientoController() {
    }

    public CalidadGestionConocimientoFacade getGestionConocimientoFacade() {
        return gestionConocimientoFacade;
    }

    public void setGestionConocimientoFacade(CalidadGestionConocimientoFacade gestionConocimientoFacade) {
        this.gestionConocimientoFacade = gestionConocimientoFacade;
    }

    public CalidadGestionConocimiento getGestionConocimientoActual() {
        return gestionConocimientoActual;
    }

    public void setGestionConocimientoActual(CalidadGestionConocimiento gestionConocimientoActual) {
        this.gestionConocimientoActual = gestionConocimientoActual;
    }

    public List<CalidadGestionConocimiento> getListaGestionConocimiento() {
        return listaGestionConocimiento = getGestionConocimientoFacade().findAll();
    }

    public void setListaGestionConocimiento(List<CalidadGestionConocimiento> listaGestionConocimiento) {
        this.listaGestionConocimiento = listaGestionConocimiento;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    
    
    private void recargarLista() {
        listaGestionConocimiento = null;
    }
    public String add(){
         try {
            gestionConocimientoActual.setIdUsuario(usuarioActual);
            getGestionConocimientoFacade().create(gestionConocimientoActual);
            addSuccessMessage("Nuevo conocimiento guardado", gestionConocimientoActual.getTitulo());
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String prepareCreate() {
        gestionConocimientoActual = new CalidadGestionConocimiento();
        return "crear";
    }
    public String update() {
        try {
            getGestionConocimientoFacade().edit(gestionConocimientoActual);
            addSuccessMessage("Conocimiento editado", gestionConocimientoActual.getTitulo());
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String delete() {
        try {
            getGestionConocimientoFacade().remove(gestionConocimientoActual);
            addSuccessMessage("Conocimiento borrado", gestionConocimientoActual.getTitulo());
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String prepareEdit() {
        return "editar";
    }

    public String prepareView() {
        return "ver";
    }
    public String prepareListAdmin() {
        recargarLista();
        return "/administrador/modCalidad/gestionConocimiento/lista";
    }

    public String prepareList() {
        recargarLista();
        gestionConocimientoActual = new CalidadGestionConocimiento();
        return "/usuario/modCalidad/gestionConocimiento/lista";
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
