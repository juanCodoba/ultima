/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Bloque;
import com.proyectoCFIP.sessions.BloqueFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior Cabal
 */
@Named(value = "bloqueController")
@SessionScoped
public class BloqueController implements Serializable {

    @EJB
    private BloqueFacade bloqueFacade;
    private Bloque bloqueActual;
    private List<Bloque> listaBloque;

    public BloqueController() {
    }

    public BloqueFacade getBloqueFacade() {
        return bloqueFacade;
    }

    public void setBloqueFacade(BloqueFacade bloqueFacade) {
        this.bloqueFacade = bloqueFacade;
    }

    public Bloque getBloqueActual() {
        return bloqueActual;
    }

    public void setBloqueActual(Bloque bloqueActual) {
        this.bloqueActual = bloqueActual;
    }

    public List<Bloque> getListaBloque() {
        return listaBloque = getBloqueFacade().findAll();
    }

    public void setListaBloque(List<Bloque> listaBloque) {
        this.listaBloque = listaBloque;
    }
    public Bloque getBloque(java.lang.Integer id) {
        return bloqueFacade.find(id);
    }

    @FacesConverter(forClass = Bloque.class)
    public static class BloqueControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BloqueController controller = (BloqueController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "bloqueController");
            return controller.getBloque(getKey(value));
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
            if (object instanceof Bloque) {
                Bloque o = (Bloque) object;
                return getStringKey(o.getIdBloque());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Bloque.class.getName());
            }
        }

    }

}
