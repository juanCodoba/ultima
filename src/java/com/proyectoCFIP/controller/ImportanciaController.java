/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Importancia;
import com.proyectoCFIP.sessions.ImportanciaFacade;
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
public class ImportanciaController implements Serializable{

    @EJB
    private ImportanciaFacade importanciaFacade;
    private List<Importancia> listaImportancia = null;
    private Importancia importanciaActual;
    
    public ImportanciaController() {

    }

    public List<Importancia> getListaImportancia() {
        listaImportancia = new ArrayList<>();
        listaImportancia = getImportanciaFacade().findAll();
        return listaImportancia;
    }


    

    public ImportanciaFacade getImportanciaFacade() {
        return importanciaFacade;
    }

    public Importancia getImportanciaActual() {
        if (importanciaActual == null) {
            importanciaActual = new Importancia();
        }
        return importanciaActual;
    }

    public void setImportanciaActual(Importancia importanciaActual) {
        this.importanciaActual = importanciaActual;
    }


    private void recargarLista() {
        listaImportancia = null;
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

    public String addImportancia() {
        try {
            getImportanciaFacade().create(importanciaActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateImportancia() {
        try {
            getImportanciaFacade().edit(importanciaActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteImportancia() {
        try {
            getImportanciaFacade().remove(importanciaActual);
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
    
    public Importancia getImportancia(java.lang.Integer id) {
        return getImportanciaFacade().find(id);
    }
    
    @FacesConverter(forClass = Importancia.class,value="importanciaConverter")
    public static class ImportanciaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImportanciaController controller = (ImportanciaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "importanciaController");
            return controller.getImportancia(getKey(value));
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
            if (object instanceof Importancia) {
                Importancia o = (Importancia) object;
                return getStringKey(o.getIdImportancia());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Importancia.class.getName());
            }
        }
    }
}
