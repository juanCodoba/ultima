
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.EstadoTicket;
import com.proyectoCFIP.entities.FabricanteLicencia;
import com.proyectoCFIP.sessions.EstadoTicketFacade;
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
public class EstadoTicketController implements Serializable{

    @EJB
    private EstadoTicketFacade estadoTicketFacade;
    private EstadoTicket estadoTicketActual;
    private List<EstadoTicket> estadoTicketList;
    public EstadoTicketController() {
    }

    public EstadoTicketFacade getEstadoTicketFacade() {
        return estadoTicketFacade;
    }

    public void setEstadoTicketFacade(EstadoTicketFacade estadoTicketFacade) {
        this.estadoTicketFacade = estadoTicketFacade;
    }

    public EstadoTicket getEstadoTicketActual() {
        return estadoTicketActual;
    }

    public void setEstadoTicketActual(EstadoTicket estadoTicketActual) {
        this.estadoTicketActual = estadoTicketActual;
    }

    public List<EstadoTicket> getEstadoTicketList() {
        return estadoTicketList = getEstadoTicketFacade().consultaEstado();
    }

    public void setEstadoTicketList(List<EstadoTicket> estadoTicketList) {
        this.estadoTicketList = estadoTicketList;
    }
    
    
    public EstadoTicket getEstadoTicket(java.lang.Integer id) {
        return getEstadoTicketFacade().find(id);
    }
    @FacesConverter(forClass = EstadoTicket.class, value="estadoTicketConverter")
    public static class EstadoTicketControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoTicketController controller = (EstadoTicketController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estadoTicketController");
            return controller.getEstadoTicket(getKey(value));
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
            if (object instanceof EstadoTicket) {
                EstadoTicket o = (EstadoTicket) object;
                return getStringKey(o.getIdEstadoTicket());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EstadoTicket.class.getName());
            }
        }

    }

    
    
}
