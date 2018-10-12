/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipSaludOcupacional;
import com.proyectoCFIP.sessions.TipSaludOcupacionalFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Luis Carlos Cabal
 */
@Named(value = "tipSaludOcupacionalController")
@SessionScoped
public class TipSaludOcupacionalController implements Serializable {

    @EJB
    private TipSaludOcupacionalFacade tipSaludOcupacionalFacade;
    private TipSaludOcupacional tipSaludOcupacionalActual;
    private List<TipSaludOcupacional> listaTipSaludOcupacional;
    public TipSaludOcupacionalController() {
    }

    public TipSaludOcupacionalFacade getTipSaludOcupacionalFacade() {
        return tipSaludOcupacionalFacade;
    }

    public void setTipSaludOcupacionalFacade(TipSaludOcupacionalFacade tipSaludOcupacionalFacade) {
        this.tipSaludOcupacionalFacade = tipSaludOcupacionalFacade;
    }

    public TipSaludOcupacional getTipSaludOcupacionalActual() {
        return tipSaludOcupacionalActual;
    }

    public void setTipSaludOcupacionalActual(TipSaludOcupacional tipSaludOcupacionalActual) {
        this.tipSaludOcupacionalActual = tipSaludOcupacionalActual;
    }

    public List<TipSaludOcupacional> getListaTipSaludOcupacional() {
        return listaTipSaludOcupacional = getTipSaludOcupacionalFacade().consulta();
    }

    public void setListaTipSaludOcupacional(List<TipSaludOcupacional> listaTipSaludOcupacional) {
        this.listaTipSaludOcupacional = listaTipSaludOcupacional;
    }
    
    public String prepareCreate(){
        tipSaludOcupacionalActual = new TipSaludOcupacional();
        return "/administrador/modSaludOcupacional/tip/crear";
    }
    public String prepareEdit(){
        return "admin/adminEditTip";
    }
    public String prepareView(){
        return "";
    }
    public String prepareList(){
        return "/listaTip";
    }
    public String update(){
         try {
            getTipSaludOcupacionalFacade().edit(tipSaludOcupacionalActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "tip editado","El tip fue editado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            tipSaludOcupacionalActual = null;
            return "/User/saludOcupacional/userListFormato";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "tip no editado","El tip no fue editado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
    }
    public String add(){
        try {
            getTipSaludOcupacionalFacade().create(tipSaludOcupacionalActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "tip guardado","El tip fue guardado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            tipSaludOcupacionalActual = null;
            return "/listaTip";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "tip no guardado","El tip no fue guardado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
    }
    public void delete(){
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
