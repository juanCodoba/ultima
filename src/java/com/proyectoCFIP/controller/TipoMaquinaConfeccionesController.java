/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipoMaquinaConfecciones;
import com.proyectoCFIP.sessions.TipoMaquinaConfeccionesFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
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
public class TipoMaquinaConfeccionesController implements Serializable{

    @EJB
    private TipoMaquinaConfeccionesFacade tipoMaquinaConfeccionesFacade;
    private TipoMaquinaConfecciones tipoMaquinaConfeccionesActual;
    private List<TipoMaquinaConfecciones> tipoMaquinaConfeccionesList;
    public TipoMaquinaConfeccionesController() {
    }

    public TipoMaquinaConfeccionesFacade getTipoMaquinaConfeccionesFacade() {
        return tipoMaquinaConfeccionesFacade;
    }

    public void setTipoMaquinaConfeccionesFacade(TipoMaquinaConfeccionesFacade tipoMaquinaConfeccionesFacade) {
        this.tipoMaquinaConfeccionesFacade = tipoMaquinaConfeccionesFacade;
    }

    public TipoMaquinaConfecciones getTipoMaquinaConfeccionesActual() {
        return tipoMaquinaConfeccionesActual;
    }

    public void setTipoMaquinaConfeccionesActual(TipoMaquinaConfecciones tipoMaquinaConfeccionesActual) {
        this.tipoMaquinaConfeccionesActual = tipoMaquinaConfeccionesActual;
    }

    public List<TipoMaquinaConfecciones> getTipoMaquinaConfeccionesList() {
        return tipoMaquinaConfeccionesList = getTipoMaquinaConfeccionesFacade().findAll();
    }

    public void setTipoMaquinaConfeccionesList(List<TipoMaquinaConfecciones> tipoMaquinaConfeccionesList) {
        this.tipoMaquinaConfeccionesList = tipoMaquinaConfeccionesList;
    }
    
    public TipoMaquinaConfecciones getTipoMaquinaConfecciones(java.lang.Integer id) {
        return getTipoMaquinaConfeccionesFacade().find(id);
    }
    
        @FacesConverter(forClass = TipoMaquinaConfecciones.class, value="tipoMaquinaConfeccionesConverter")
    public static class TipoMaquinaConfeccionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoMaquinaConfeccionesController controller = (TipoMaquinaConfeccionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoMaquinaConfeccionesController");
            return controller.getTipoMaquinaConfecciones(getKey(value));
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
            if (object instanceof TipoMaquinaConfecciones) {
                TipoMaquinaConfecciones o = (TipoMaquinaConfecciones) object;
                return getStringKey(o.getIdTipoMaquinaConfecciones());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoMaquinaConfecciones.class.getName());
            }
        }

    }

}
