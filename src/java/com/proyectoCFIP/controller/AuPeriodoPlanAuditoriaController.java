/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.AuPeriodoPlanAuditoria;
import com.proyectoCFIP.sessions.AuPeriodoPlanAuditoriaFacade;
import com.proyectoCFIP.sessions.AuPlanAuditoriaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;

/**
 *
 * @author junio
 */
@Named(value = "auPeriodoPlanAuditoriaController")
@SessionScoped
public class AuPeriodoPlanAuditoriaController implements Serializable {

    @EJB
    private AuPeriodoPlanAuditoriaFacade periodoPlanAuditoriaFacade;
    private AuPeriodoPlanAuditoria periodoPlanAuditoriaActual;
    private List<AuPeriodoPlanAuditoria> listaPeriodoPlanAuditoria;
    
    public AuPeriodoPlanAuditoriaController() {
    }

    public AuPeriodoPlanAuditoriaFacade getPeriodoPlanAuditoriaFacade() {
        return periodoPlanAuditoriaFacade;
    }

    public void setPeriodoPlanAuditoriaFacade(AuPeriodoPlanAuditoriaFacade periodoPlanAuditoriaFacade) {
        this.periodoPlanAuditoriaFacade = periodoPlanAuditoriaFacade;
    }

    public AuPeriodoPlanAuditoria getPeriodoPlanAuditoriaActual() {
        return periodoPlanAuditoriaActual;
    }

    public void setPeriodoPlanAuditoriaActual(AuPeriodoPlanAuditoria periodoPlanAuditoriaActual) {
        this.periodoPlanAuditoriaActual = periodoPlanAuditoriaActual;
    }

    public List<AuPeriodoPlanAuditoria> getListaPeriodoPlanAuditoria() {
        return listaPeriodoPlanAuditoria =  getPeriodoPlanAuditoriaFacade().findAll();
    }

    public void setListaPeriodoPlanAuditoria(List<AuPeriodoPlanAuditoria> listaPeriodoPlanAuditoria) {
        this.listaPeriodoPlanAuditoria = listaPeriodoPlanAuditoria;
    }
    public AuPeriodoPlanAuditoria getAuPeriodoPlanAuditoria(java.lang.Integer id) {
        return getPeriodoPlanAuditoriaFacade().find(id);
    }
    
    private void recargarLista() {
        listaPeriodoPlanAuditoria = null;
    }
    public String add(){
        try {
            getPeriodoPlanAuditoriaFacade().create(periodoPlanAuditoriaActual);
            return "lista";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo completar la acción","");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "";
        }
    }

    public String prepareCreate() {
        periodoPlanAuditoriaActual = new AuPeriodoPlanAuditoria();
        return "crear";
    }

    public String prepareEdit() {
        return "editar";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modAuditoria/periodoPlanAuditoria/lista";
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
    
    @FacesConverter(forClass = AuPeriodoPlanAuditoria.class)
    public static class AuPeriodoPlanAuditoriaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AuPeriodoPlanAuditoriaController controller = (AuPeriodoPlanAuditoriaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "auPeriodoPlanAuditoriaController");
            return controller.getAuPeriodoPlanAuditoria(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof AuPeriodoPlanAuditoria) {
                AuPeriodoPlanAuditoria o = (AuPeriodoPlanAuditoria) object;
                return getStringKey(o.getIdAuPeriodoPlanAuditoria());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + AuPeriodoPlanAuditoria.class.getName());
            }
        }
    }
}
