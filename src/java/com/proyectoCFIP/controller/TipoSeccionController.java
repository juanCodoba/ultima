/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipoSeccion;
import com.proyectoCFIP.sessions.TipoSeccionFacade;
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
@Named(value = "tipoSeccionController")
@SessionScoped
public class TipoSeccionController implements Serializable {

    @EJB
    private TipoSeccionFacade tipoSeccionFacade;
    private TipoSeccion tipoSeccionActual;
    private List<TipoSeccion> listaTipoSeccion;
    public TipoSeccionController() {
    }

    public TipoSeccionFacade getTipoSeccionFacade() {
        return tipoSeccionFacade;
    }

    public void setTipoSeccionFacade(TipoSeccionFacade tipoSeccionFacade) {
        this.tipoSeccionFacade = tipoSeccionFacade;
    }

    public TipoSeccion getTipoSeccionActual() {
        return tipoSeccionActual;
    }

    public void setTipoSeccionActual(TipoSeccion tipoSeccionActual) {
        this.tipoSeccionActual = tipoSeccionActual;
    }

    public List<TipoSeccion> getListaTipoSeccion() {
        return listaTipoSeccion = getTipoSeccionFacade().findAll();
    }

    public void setListaTipoSeccion(List<TipoSeccion> listaTipoSeccion) {
        this.listaTipoSeccion = listaTipoSeccion;
    }

    public TipoSeccion getTipoSeccion(java.lang.Integer id) {
        return tipoSeccionFacade.find(id);
    }
    @FacesConverter(forClass = TipoSeccion.class)
    public static class TipoSeccionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoSeccionController controller = (TipoSeccionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoSeccionController");
            return controller.getTipoSeccion(getKey(value));
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
            if (object instanceof TipoSeccion) {
                TipoSeccion o = (TipoSeccion) object;
                return getStringKey(o.getIdTipoSeccion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoSeccion.class.getName());
            }
        }

    }

}
