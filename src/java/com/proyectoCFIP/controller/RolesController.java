/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Roles;
import com.proyectoCFIP.sessions.RolesFacade;
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
public class RolesController {

    @EJB
    private RolesFacade rolFacade;
    public RolesController() {
    }

    public RolesFacade getRolFacade() {
        return rolFacade;
    }

    public void setRolFacade(RolesFacade rolFacade) {
        this.rolFacade = rolFacade;
    }
    
    
    public List<Roles> getItemsAvailableSelectOne() {
        return getRolFacade().consultaRol();
    }
    public List<Roles> getListaRolCalidad() {
        return getRolFacade().consultaRolCalidad();
    }
     public Roles getRoles (java.lang.String id) {
        return getRolFacade().find(id);
    }
     @FacesConverter(forClass = Roles.class, value="rolConverter")
    public static class RolesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RolesController controller = (RolesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rolesController");
            return controller.getRoles(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Roles) {
                Roles o = (Roles) object;
                return getStringKey(o.getIdRol());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Roles.class.getName());
            }
        }

    }

}
