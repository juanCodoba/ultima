/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Modulo;
import com.proyectoCFIP.sessions.ModuloFacade;
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
public class ModuloController implements Serializable{

    @EJB
    private ModuloFacade moduloFacade;
    private Modulo moduloActual;
    private List<Modulo> moduloList;
            
    public ModuloController() {
    }

    public ModuloFacade getModuloFacade() {
        return moduloFacade;
    }

    public void setModuloFacade(ModuloFacade moduloFacade) {
        this.moduloFacade = moduloFacade;
    }

    public Modulo getModuloActual() {
        return moduloActual;
    }

    public void setModuloActual(Modulo moduloActual) {
        this.moduloActual = moduloActual;
    }

    public List<Modulo> getModuloList() {
        return moduloList = getModuloFacade().findAll();
    }

    public void setModuloList(List<Modulo> moduloList) {
        this.moduloList = moduloList;
    }
    
        
    public Modulo getModulo(java.lang.Integer id) {
        return getModuloFacade().find(id);
    }
    @FacesConverter(forClass = Modulo.class, value = "moduloConverter")
    public static class ModuloControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ModuloController controller = (ModuloController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "moduloController");
            return controller.getModulo(getKey(value));
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
            if (object instanceof Modulo) {
                Modulo o = (Modulo) object;
                return getStringKey(o.getIdModulo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Modulo.class.getName());
            }
        }

    }
    
}
