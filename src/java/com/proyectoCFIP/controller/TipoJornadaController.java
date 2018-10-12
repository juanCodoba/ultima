/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipoJornada;
import com.proyectoCFIP.sessions.TipoJornadaFacade;
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
public class TipoJornadaController implements Serializable{

    @EJB
    private TipoJornadaFacade tipoJornadaFacade;
    private TipoJornada tipoJornadaActual;
    private List<TipoJornada> tipoJornadaList;
    public TipoJornadaController() {
    }

    public TipoJornadaFacade getTipoJornadaFacade() {
        return tipoJornadaFacade;
    }

    public void setTipoJornadaFacade(TipoJornadaFacade tipoJornadaFacade) {
        this.tipoJornadaFacade = tipoJornadaFacade;
    }

    public TipoJornada getTipoJornadaActual() {
        return tipoJornadaActual;
    }

    public void setTipoJornadaActual(TipoJornada tipoJornadaActual) {
        this.tipoJornadaActual = tipoJornadaActual;
    }

    public List<TipoJornada> getTipoJornadaList() {
        return tipoJornadaList = getTipoJornadaFacade().findAll();
    }

    public void setTipoJornadaList(List<TipoJornada> tipoJornadaList) {
        this.tipoJornadaList = tipoJornadaList;
    }
    public TipoJornada getTipoJornada(java.lang.Integer id) {
        return getTipoJornadaFacade().find(id);
    }
    
    @FacesConverter(forClass = TipoJornada.class, value="tipoJornadaConverter")
    public static class TipoJornadaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoJornadaController controller = (TipoJornadaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoJornadaController");
            return controller.getTipoJornada(getKey(value));
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
            if (object instanceof TipoJornada) {
                TipoJornada o = (TipoJornada) object;
                return getStringKey(o.getIdTipoJornada());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoJornada.class.getName());
            }
        }

    }
}
