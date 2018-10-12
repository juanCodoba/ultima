/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipoLicencia;
import com.proyectoCFIP.sessions.TipoLicenciaFacade;
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
public class TipoLicenciaController implements Serializable{
    
    @EJB
    private TipoLicenciaFacade tipoLicenciaFacade;
    private TipoLicencia tipoLicenciaActual; 
    private List<TipoLicencia> listaTipoLicencia;
    public TipoLicenciaController() {
    }
    
    //Select one  Tipo licencia

   
    public List<TipoLicencia> getListaTipoLicenciaSelectOne() {
        return  getTipoLicenciaFacade().findAll();
         
    }
    public TipoLicenciaFacade getTipoLicenciaFacade() {
        return tipoLicenciaFacade;
    }

    public void setTipoLicenciaFacade(TipoLicenciaFacade tipoLicenciaFacade) {
        this.tipoLicenciaFacade = tipoLicenciaFacade;
    }

    public TipoLicencia getTipoLicenciaActual() {
        return tipoLicenciaActual;
    }

    public void setTipoLicenciaActual(TipoLicencia tipoLicenciaActual) {
        this.tipoLicenciaActual = tipoLicenciaActual;
    }

    public List<TipoLicencia> getListaTipoLicencia() {
        return listaTipoLicencia;
    }

    public void setListaTipoLicencia(List<TipoLicencia> listaTipoLicencia) {
        this.listaTipoLicencia = listaTipoLicencia;
    }
    public TipoLicencia getTipoLicencia(java.lang.Integer id) {
        return getTipoLicenciaFacade().find(id);
    }

     @FacesConverter(forClass = TipoLicencia.class)
    public static class TipoLicenciaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoLicenciaController controller = (TipoLicenciaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoLicenciaController");
            return controller.getTipoLicencia(getKey(value));
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
            if (object instanceof TipoLicencia) {
                TipoLicencia o = (TipoLicencia) object;
                return getStringKey(o.getIdTipoLicencia());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoLicencia.class.getName());
            }
        }

    }

}
