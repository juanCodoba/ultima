/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipoDiagnostico;
import com.proyectoCFIP.sessions.TipoDiagnosticoFacade;
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
public class TipoDiagnosticoController implements Serializable {

    @EJB
    private TipoDiagnosticoFacade tipoDiagnosticoFacade;
    private TipoDiagnostico tipoDiagnostico;
    private List<TipoDiagnostico> listaTipoDiagnostico;
    public TipoDiagnosticoController() {
    }

    public TipoDiagnosticoFacade getTipoDiagnosticoFacade() {
        return tipoDiagnosticoFacade;
    }

    public void setTipoDiagnosticoFacade(TipoDiagnosticoFacade tipoDiagnosticoFacade) {
        this.tipoDiagnosticoFacade = tipoDiagnosticoFacade;
    }

    public TipoDiagnostico getTipoDiagnostico() {
        return tipoDiagnostico;
    }

    public void setTipoDiagnostico(TipoDiagnostico tipoDiagnostico) {
        this.tipoDiagnostico = tipoDiagnostico;
    }

    public List<TipoDiagnostico> getListaTipoDiagnostico() {
        return listaTipoDiagnostico = getTipoDiagnosticoFacade().findAll();
    }

    public void setListaTipoDiagnostico(List<TipoDiagnostico> listaTipoDiagnostico) {
        this.listaTipoDiagnostico = listaTipoDiagnostico;
    }
     public TipoDiagnostico getTipoDiagnostico(java.lang.Integer id) {
        return getTipoDiagnosticoFacade().find(id);
    }
    
    @FacesConverter(forClass = TipoDiagnostico.class, value="tipoDiagnosticoConverter")
    public static class TipoDiagnosticoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoDiagnosticoController controller = (TipoDiagnosticoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoDiagnosticoController");
            return controller.getTipoDiagnostico(getKey(value));
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
            if (object instanceof TipoDiagnostico) {
                TipoDiagnostico o = (TipoDiagnostico) object;
                return getStringKey(o.getIdTipoDiagnostico());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoDiagnostico.class.getName());
            }
        }
    }
}
