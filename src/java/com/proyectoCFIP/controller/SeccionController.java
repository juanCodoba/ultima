/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Seccion;
import com.proyectoCFIP.entities.TipoSeccion;
import com.proyectoCFIP.sessions.SeccionFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@RequestScoped
public class SeccionController implements Serializable{

    @EJB
    private SeccionFacade seccionFacade;
    private List<Seccion> listaSeccion = null;
    private Seccion seccionActual;
    
    public SeccionController() {

    }

    public List<Seccion> getListaSeccion() {
        listaSeccion = new ArrayList<>();
        listaSeccion = getSeccionFacade().findAll();
        return listaSeccion;
    }
    
    public List<Seccion> getListaSeccionEspecial() {
        listaSeccion = new ArrayList<>();
        listaSeccion = getSeccionFacade().consultaSeccionEspecial(new TipoSeccion(1,"Sala especial"),new TipoSeccion(2,"Auditorio"));
        return listaSeccion;
    }

    

    public SeccionFacade getSeccionFacade() {
        return seccionFacade;
    }

    public Seccion getSeccionActual() {
        if (seccionActual == null) {
            seccionActual = new Seccion();
        }
        return seccionActual;
    }

    public void setSeccionActual(Seccion seccionActual) {
        this.seccionActual = seccionActual;
    }


    private void recargarLista() {
        listaSeccion = null;
    }

    public String prepareCreate() {
        seccionActual = new Seccion();
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
        return "seccion/lista";
    }

    public String addSeccion() {
        try {
            getSeccionFacade().create(seccionActual);
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateSeccion() {
        getSeccionFacade().edit(seccionActual);
        return "lista";
    }

    public String deleteSeccion() {
        try {
            getSeccionFacade().remove(seccionActual);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        return "lista";
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
    
    public Seccion getSeccion(java.lang.Integer id) {
        return getSeccionFacade().find(id);
    }
    @FacesConverter(forClass = Seccion.class, value="seccionConverter")
    public static class SeccionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SeccionController controller = (SeccionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "seccionController");
            return controller.getSeccion(getKey(value));
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
            if (object instanceof Seccion) {
                Seccion o = (Seccion) object;
                return getStringKey(o.getIdSeccion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Seccion.class.getName());
            }
        }
    }
}
