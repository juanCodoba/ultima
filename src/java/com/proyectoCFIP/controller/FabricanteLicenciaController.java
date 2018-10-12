

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.FabricanteLicencia;
import com.proyectoCFIP.sessions.FabricanteLicenciaFacade;
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
public class FabricanteLicenciaController {
    
    @EJB
    private FabricanteLicenciaFacade fabricanteLicenciaFacade;
    private FabricanteLicencia FabricanteLicenciaActual;
    private List<FabricanteLicencia> fabricanteLicencia;
    
    
    
    public FabricanteLicenciaController() {
        
    }

    public FabricanteLicenciaFacade getFabricanteLicenciaFacade() {
        return fabricanteLicenciaFacade;
    }

    public void setFabricanteLicenciaFacade(FabricanteLicenciaFacade fabricanteLicenciaFacade) {
        this.fabricanteLicenciaFacade = fabricanteLicenciaFacade;
    }

    public FabricanteLicencia getFabricanteLicenciaActual() {
        return FabricanteLicenciaActual;
    }

    public void setFabricanteLicenciaActual(FabricanteLicencia FabricanteLicenciaActual) {
        this.FabricanteLicenciaActual = FabricanteLicenciaActual;
    }

    public List<FabricanteLicencia> getFabricanteLicencia() {
        return fabricanteLicencia;
    }

    public void setFabricanteLicencia(List<FabricanteLicencia> fabricanteLicencia) {
        this.fabricanteLicencia = fabricanteLicencia;
    }
    public List<FabricanteLicencia> getListaFabricanteLicenciaSelectOne() {
        return  getFabricanteLicenciaFacade().findAll();
    }
    
    
    public FabricanteLicencia getFabricanteLicencia(java.lang.Integer id) {
        return getFabricanteLicenciaFacade().find(id);
    }
    @FacesConverter(forClass = FabricanteLicencia.class)
    public static class FabricanteLicenciaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FabricanteLicenciaController controller = (FabricanteLicenciaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fabricanteLicenciaController");
            return controller.getFabricanteLicencia(getKey(value));
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
            if (object instanceof FabricanteLicencia) {
                FabricanteLicencia o = (FabricanteLicencia) object;
                return getStringKey(o.getIdFabricanteLicencia());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FabricanteLicencia.class.getName());
            }
        }

    }

}
