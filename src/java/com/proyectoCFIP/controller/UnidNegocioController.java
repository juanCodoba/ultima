/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.UnidNegocio;
import com.proyectoCFIP.sessions.UnidNegocioFacade;
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
public class UnidNegocioController {

    @EJB
    private UnidNegocioFacade unidNegocioFacade;
    private UnidNegocio unidNegocioActual;
    private List<UnidNegocio> listaUnidNegocio;
    
    public UnidNegocioController() {
    }

    public UnidNegocioFacade getUnidNegocioFacade() {
        return unidNegocioFacade;
    }

    public void setUnidNegocioFacade(UnidNegocioFacade unidNegocioFacade) {
        this.unidNegocioFacade = unidNegocioFacade;
    }

    public UnidNegocio getUnidNegocioActual() {
        return unidNegocioActual;
    }

    public void setUnidNegocioActual(UnidNegocio unidNegocioActual) {
        this.unidNegocioActual = unidNegocioActual;
    }

    public List<UnidNegocio> getListaUnidNegocio() {
        listaUnidNegocio = getUnidNegocioFacade().findAll();
        return listaUnidNegocio;
    }

    public void setListaUnidNegocio(List<UnidNegocio> listaUnidNegocio) {
        this.listaUnidNegocio = listaUnidNegocio;
    }
    public UnidNegocio getUnidNegocio(java.lang.Integer id) {
        return getUnidNegocioFacade().find(id);
    }
    
    
    @FacesConverter(forClass = UnidNegocio.class)
    public static class UnidNegocioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UnidNegocioController controller = (UnidNegocioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "unidNegocioController");
            return controller.getUnidNegocio(getKey(value));
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
            if (object instanceof UnidNegocio) {
                UnidNegocio o = (UnidNegocio) object;
                return getStringKey(o.getIdUnidadNegocio());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + UnidNegocio.class.getName());
            }
        }

    }

    
}
