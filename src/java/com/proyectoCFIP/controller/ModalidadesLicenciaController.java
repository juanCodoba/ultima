/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.ModalidadesLicencia;
import com.proyectoCFIP.sessions.ModalidadesLicenciaFacade;
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
public class ModalidadesLicenciaController {
    
    @EJB
    private ModalidadesLicenciaFacade modalidadesLicenciaFacade; 
    private ModalidadesLicencia ModalidadesLicenciaActual;
    private List<ModalidadesLicencia> listaModalidadesLicencia;
    /**
     * Creates a new instance of ModalidadesLicenciaController
     */
    public ModalidadesLicenciaController() {
    }

    public ModalidadesLicenciaFacade getModalidadesLicenciaFacade() {
        return modalidadesLicenciaFacade;
    }

    public void setModalidadesLicenciaFacade(ModalidadesLicenciaFacade modalidadesLicenciaFacade) {
        this.modalidadesLicenciaFacade = modalidadesLicenciaFacade;
    }

    public ModalidadesLicencia getModalidadesLicenciaActual() {
        return ModalidadesLicenciaActual;
    }

    public void setModalidadesLicenciaActual(ModalidadesLicencia ModalidadesLicenciaActual) {
        this.ModalidadesLicenciaActual = ModalidadesLicenciaActual;
    }

    public List<ModalidadesLicencia> getListaModalidadesLicencia() {
        return listaModalidadesLicencia;
    }

    public void setListaModalidadesLicencia(List<ModalidadesLicencia> listaModalidadesLicencia) {
        this.listaModalidadesLicencia = listaModalidadesLicencia;
    }
    
    public ModalidadesLicencia getModalidadesLicencia(java.lang.Integer id) {
        return getModalidadesLicenciaFacade().find(id);
    }
    public List<ModalidadesLicencia> getListaModalidadesLicenciasSelectOne() {
        return  getModalidadesLicenciaFacade().findAll();
         
    }
    
   @FacesConverter(forClass = ModalidadesLicencia.class)
    public static class ModalidadesLicenciaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ModalidadesLicenciaController controller = (ModalidadesLicenciaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "modalidadesLicenciaController");
            return controller.getModalidadesLicencia(getKey(value));
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
            if (object instanceof ModalidadesLicencia) {
                ModalidadesLicencia o = (ModalidadesLicencia) object;
                return getStringKey(o.getIdModalidadesLicencia());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ModalidadesLicencia.class.getName());
            }
        }

    }   
}
