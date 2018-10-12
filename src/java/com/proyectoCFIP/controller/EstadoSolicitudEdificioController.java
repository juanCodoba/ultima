
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.EstadoSolicitudEdificio;
import com.proyectoCFIP.sessions.EstadoSolicitudEdificioFacade;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Luis Carlos Cabal
 */
@ManagedBean
@SessionScoped
public class EstadoSolicitudEdificioController implements Serializable {
    
    @EJB
    private EstadoSolicitudEdificioFacade estadoSolicitudEdificioFacade;
    private EstadoSolicitudEdificio estadoSolicitudEdificioActual;
    private List<EstadoSolicitudEdificio> estadoSolicitudEdificioList;
    public EstadoSolicitudEdificioController() {
    }

    public EstadoSolicitudEdificioFacade getEstadoSolicitudEdificioFacade() {
        return estadoSolicitudEdificioFacade;
    }

    public void setEstadoSolicitudEdificioFacade(EstadoSolicitudEdificioFacade estadoSolicitudEdificioFacade) {
        this.estadoSolicitudEdificioFacade = estadoSolicitudEdificioFacade;
    }

    public EstadoSolicitudEdificio getEstadoSolicitudEdificioActual() {
        return estadoSolicitudEdificioActual;
    }

    public void setEstadoSolicitudEdificioActual(EstadoSolicitudEdificio estadoSolicitudEdificioActual) {
        this.estadoSolicitudEdificioActual = estadoSolicitudEdificioActual;
    }

    public List<EstadoSolicitudEdificio> getEstadoSolicitudEdificioList() {
        return estadoSolicitudEdificioList = getEstadoSolicitudEdificioFacade().findAll();
    }

    public void setEstadoSolicitudEdificioList(List<EstadoSolicitudEdificio> estadoSolicitudEdificioList) {
        this.estadoSolicitudEdificioList = estadoSolicitudEdificioList;
    }
    
    public EstadoSolicitudEdificio getEstadoSolicitudEdificio(java.lang.Integer id) {
        return getEstadoSolicitudEdificioFacade().find(id);
    }
    @FacesConverter(forClass = EstadoSolicitudEdificio.class, value = "estadoSolicitudEdificioConverter")
    public static class EstadoSolicitudEdificioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoSolicitudEdificioController controller = (EstadoSolicitudEdificioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estadoSolicitudEdificioController");
            return controller.getEstadoSolicitudEdificio(getKey(value));
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
            if (object instanceof EstadoSolicitudEdificio) {
                EstadoSolicitudEdificio o = (EstadoSolicitudEdificio) object;
                return getStringKey(o.getIdEstadoSolicitudEdificio());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EstadoSolicitudEdificio.class.getName());
            }
        }

    }

    
    
}
