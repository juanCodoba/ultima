

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;


import com.proyectoCFIP.entities.EstadoSolicitudRefrigerio;
import com.proyectoCFIP.sessions.EstadoSolicitudRefrigerioFacade;
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
public class EstadoSolicitudRefrigerioController implements Serializable {
    
    @EJB
    private EstadoSolicitudRefrigerioFacade estadoSolicitudRegrigerioFacade;  
    private EstadoSolicitudRefrigerio estadosolicitudRefrigerioActual;
    private List<EstadoSolicitudRefrigerio> estadoSolicitudRefrigerioList;
    public EstadoSolicitudRefrigerioController() {
    }

    public EstadoSolicitudRefrigerioFacade getEstadoSolicitudRegrigerioFacade() {
        return estadoSolicitudRegrigerioFacade;
    }

    public void setEstadoSolicitudRegrigerioFacade(EstadoSolicitudRefrigerioFacade estadoSolicitudRegrigerioFacade) {
        this.estadoSolicitudRegrigerioFacade = estadoSolicitudRegrigerioFacade;
    }

    public EstadoSolicitudRefrigerio getEstadosolicitudRefrigerioActual() {
        return estadosolicitudRefrigerioActual;
    }

    public void setEstadosolicitudRefrigerioActual(EstadoSolicitudRefrigerio estadosolicitudRefrigerioActual) {
        this.estadosolicitudRefrigerioActual = estadosolicitudRefrigerioActual;
    }

    public List<EstadoSolicitudRefrigerio> getEstadoSolicitudRefrigerioList() {
        return estadoSolicitudRefrigerioList = getEstadoSolicitudRegrigerioFacade().findAll();
    }

    public void setEstadoSolicitudRefrigerioList(List<EstadoSolicitudRefrigerio> estadoSolicitudRefrigerioList) {
        this.estadoSolicitudRefrigerioList = estadoSolicitudRefrigerioList;
    }
    public EstadoSolicitudRefrigerio getEstadoSolicitudRefrigerio(java.lang.Integer id) {
        return getEstadoSolicitudRegrigerioFacade().find(id);
    }
    @FacesConverter(forClass = EstadoSolicitudRefrigerio.class)
    public static class EstadoSolicitudRefrigerioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoSolicitudRefrigerioController controller = (EstadoSolicitudRefrigerioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estadoSolicitudRefrigerioController");
            return controller.getEstadoSolicitudRefrigerio(getKey(value));
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
            if (object instanceof EstadoSolicitudRefrigerio) {
                EstadoSolicitudRefrigerio o = (EstadoSolicitudRefrigerio) object;
                return getStringKey(o.getIdEstadoSolicitudRefrigerio());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EstadoSolicitudRefrigerio.class.getName());
            }
        }

    }

}
