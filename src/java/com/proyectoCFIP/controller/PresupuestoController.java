/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Presupuesto;
import com.proyectoCFIP.sessions.PresupuestoFacade;
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
public class PresupuestoController {

    @EJB
    private PresupuestoFacade presupuestoFacade;
    private Presupuesto presupuestoActual;
    private List<Presupuesto> listaPresupuesto;
    
    public PresupuestoController() {
    }

    public PresupuestoFacade getPresupuestoFacade() {
        return presupuestoFacade;
    }

    public void setPresupuestoFacade(PresupuestoFacade presupuestoFacade) {
        this.presupuestoFacade = presupuestoFacade;
    }

    public Presupuesto getPresupuestoActual() {
        return presupuestoActual;
    }

    public void setPresupuestoActual(Presupuesto presupuestoActual) {
        this.presupuestoActual = presupuestoActual;
    }

    public List<Presupuesto> getListaPresupuesto() {
        listaPresupuesto = getPresupuestoFacade().findAll();
        return listaPresupuesto;
    }

    public void setListaPresupuesto(List<Presupuesto> listaPresupuesto) {
        this.listaPresupuesto = listaPresupuesto;
    }
    public Presupuesto getPresupuesto(java.lang.Integer id) {
        return getPresupuestoFacade().find(id);
    }
    
    
    @FacesConverter(forClass = Presupuesto.class)
    public static class PresupuestoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PresupuestoController controller = (PresupuestoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "presupuestoController");
            return controller.getPresupuesto(getKey(value));
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
            if (object instanceof Presupuesto) {
                Presupuesto o = (Presupuesto) object;
                return getStringKey(o.getIdPresupuesto());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Presupuesto.class.getName());
            }
        }

    }

    
}
