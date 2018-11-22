/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipoAuditoria;
import com.proyectoCFIP.sessions.TipoAuditoriaFacade;
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

/**
 *
 * @author junio
 */
@Named(value = "TipoAuditoriaController")
@SessionScoped
public class TipoAuditoriaController implements Serializable {

    @EJB
    private TipoAuditoriaFacade tipoAuditoriaFacade;
    private TipoAuditoria tipoAuditoriaActual;
    private List<TipoAuditoria> listaTipoAuditoria;
    public TipoAuditoriaController() {
    }
    
    
    
    public TipoAuditoriaFacade getTipoAuditoriaFacade() {
        return tipoAuditoriaFacade;
    }

    public void setTipoAuditoriaFacade(TipoAuditoriaFacade tipoAuditoriaFacade) {
        this.tipoAuditoriaFacade = tipoAuditoriaFacade;
    }

    public TipoAuditoria getTipoAuditoriaActual() {
        return tipoAuditoriaActual;
    }

    public void setTipoAuditoriaActual(TipoAuditoria tipoAuditoriaActual) {
        this.tipoAuditoriaActual = tipoAuditoriaActual;
    }

    public List<TipoAuditoria> getListaTipoAuditoria() {
        return listaTipoAuditoria = getTipoAuditoriaFacade().findAll();
    }

    public void setListaTipoAuditoria(List<TipoAuditoria> listaTipoAuditoria) {
        this.listaTipoAuditoria = listaTipoAuditoria;
    }
    
    public void recargarLista(){
        listaTipoAuditoria=null;
    }
    public TipoAuditoria getTipoAuditoria(java.lang.Integer id) {
        return getTipoAuditoriaFacade().find(id);
    }
     public String prepareEdit() {
        return "/administrador/modFichaTecnica/tipoAuditoria/editar";
    }
      public String prepareCreate() {
        tipoAuditoriaActual = new TipoAuditoria();
        return "/administrador/modFichaTecnica/tipoAuditoria/crear";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modFichaTecnica/tipoAuditoria/lista";
    }

    public String prepareListusuario() {
        recargarLista();
        return "/usuario/modSaludOcupacional/reporte/lista";
    }

    public String add() {
        try { 
            getTipoAuditoriaFacade().create(tipoAuditoriaActual);
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String update() {
        try {
            getTipoAuditoriaFacade().edit(tipoAuditoriaActual);
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
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
    
    @FacesConverter(forClass = TipoAuditoria.class)
    public static class TipoAuditoriaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoAuditoriaController controller = (TipoAuditoriaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "TipoAuditoriaController");
            return controller.getTipoAuditoria(getKey(value));
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
            if (object instanceof TipoAuditoria) {
                TipoAuditoria o = (TipoAuditoria) object;
                return getStringKey(o.getIdTipoAuditoria());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoAuditoria.class.getName());
            }
        }
    }
}
