/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Grado;
import com.proyectoCFIP.sessions.GradoFacade;
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
public class GradoController implements Serializable{

    @EJB
    private GradoFacade gradoFacade;
    private List<Grado> listaGrado = null;
    private Grado gradoActual;
    
    public GradoController() {

    }

    public List<Grado> getListaGrado() {
        listaGrado = new ArrayList<>();
        listaGrado = getGradoFacade().findAll();
        return listaGrado;
    }
//    public List<Grado> getListaGradoConsulta() {
//        return getGradoFacade().consultaGrado();
//    }

    

    public GradoFacade getGradoFacade() {
        return gradoFacade;
    }

    public Grado getGradoActual() {
        if (gradoActual == null) {
            gradoActual = new Grado();
        }
        return gradoActual;
    }

    public void setGradoActual(Grado gradoActual) {
        this.gradoActual = gradoActual;
    }


    private void recargarLista() {
        listaGrado = null;
    }

    public String prepareCreate() {
        gradoActual = new Grado();
        return "adminCrearGradoComputador";
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

    public String addGrado() {
        try {
            getGradoFacade().create(gradoActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateGrado() {
        try {
            getGradoFacade().edit(gradoActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteGrado() {
        try {
            getGradoFacade().remove(gradoActual);
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
    
    public Grado getGrado(java.lang.Integer id) {
        return getGradoFacade().find(id);
    }
    
    @FacesConverter(forClass = Grado.class)
    public static class GradoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GradoController controller = (GradoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "gradoController");
            return controller.getGrado(getKey(value));
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
            if (object instanceof Grado) {
                Grado o = (Grado) object;
                return getStringKey(o.getIdGrado());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Grado.class.getName());
            }
        }
    }
}
