/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.SistemaOperativo;
import com.proyectoCFIP.sessions.SistemaOperativoFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class SistemaOperativoController implements Serializable{

    @EJB
    private SistemaOperativoFacade sistemaOperativoFacade;
    private List<SistemaOperativo> listaSistemaOperativo = null;
    private SistemaOperativo sistemaOperativoActual;
    
    public SistemaOperativoController() {

    }

    public List<SistemaOperativo> getListaSistemaOperativo() {
        listaSistemaOperativo = new ArrayList<>();
        listaSistemaOperativo = getSistemaOperativoFacade().findAll();
        return listaSistemaOperativo;
    }

    

    public SistemaOperativoFacade getSistemaOperativoFacade() {
        return sistemaOperativoFacade;
    }

    public SistemaOperativo getSistemaOperativoActual() {
        if (sistemaOperativoActual == null) {
            sistemaOperativoActual = new SistemaOperativo();
        }
        return sistemaOperativoActual;
    }

    public void setSistemaOperativoActual(SistemaOperativo sistemaOperativoActual) {
        this.sistemaOperativoActual = sistemaOperativoActual;
    }


    private void recargarLista() {
        listaSistemaOperativo = null;
    }

    public String prepareCreate() {
        sistemaOperativoActual = new SistemaOperativo();
        return "/Admin/configuracionesGenerales/adminCrearSistemaOperativo";
    }

    public String prepareEdit() {
        return "";
    }

    public String prepareView() {
        return "";
    }

    public String prepareList() {
        recargarLista();
        return "";
    }

    public String addSistemaOperativo() {
        try {
            getSistemaOperativoFacade().create(sistemaOperativoActual);
            addSuccessMessage("Sistema operativo guardado", "Sistema operativo guardado exitosamente");
            recargarLista();
            return "/User/paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateSistemaOperativo() {
        try {
            getSistemaOperativoFacade().edit(sistemaOperativoActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteSistemaOperativo() {
        try {
            getSistemaOperativoFacade().remove(sistemaOperativoActual);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        return "List";
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
    
    public SistemaOperativo getSistemaOperativo(java.lang.Integer id) {
        return getSistemaOperativoFacade().find(id);
    }
    
    @FacesConverter(forClass = SistemaOperativo.class)
    public static class SistemaOperativoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SistemaOperativoController controller = (SistemaOperativoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sistemaOperativoController");
            return controller.getSistemaOperativo(getKey(value));
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
            if (object instanceof SistemaOperativo) {
                SistemaOperativo o = (SistemaOperativo) object;
                return getStringKey(o.getIdSistemaOperativo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + SistemaOperativo.class.getName());
            }
        }

    }

}
