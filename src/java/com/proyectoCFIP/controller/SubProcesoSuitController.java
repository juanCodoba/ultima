/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.SubprocesoSuit;
import com.proyectoCFIP.sessions.SubprocesoSuitFacade;
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
public class SubProcesoSuitController implements Serializable{

    @EJB
    private SubprocesoSuitFacade subprocesoSuitFacade;
    private SubprocesoSuit subProcesoSuitActual;
    private List<SubprocesoSuit> listaSubProcesos;
    
    public SubProcesoSuitController() {
    }

    public SubprocesoSuitFacade getSubprocesoSuitFacade() {
        return subprocesoSuitFacade;
    }

    public void setSubprocesoSuitFacade(SubprocesoSuitFacade subprocesoSuitFacade) {
        this.subprocesoSuitFacade = subprocesoSuitFacade;
    }

    public SubprocesoSuit getSubProcesoSuitActual() {
        return subProcesoSuitActual;
    }

    public void setSubProcesoSuitActual(SubprocesoSuit subProcesoSuitActual) {
        this.subProcesoSuitActual = subProcesoSuitActual;
    }

    public List<SubprocesoSuit> getListaSubProcesos() {
        return listaSubProcesos;
    }

    public void setListaSubProcesos(List<SubprocesoSuit> listaSubProcesos) {
        this.listaSubProcesos = listaSubProcesos;
    }

    
    
    
    
    public List<SubprocesoSuit> ListaSubprocesosSelectOne(){
        return getSubprocesoSuitFacade().findAll();
    }
    
    
    public SubprocesoSuit getSubprocesoSuit(java.lang.Integer id) {
        return getSubprocesoSuitFacade().find(id);
    }
    @FacesConverter(forClass = SubprocesoSuit.class)
    public static class SubprocesoSuitControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SubProcesoSuitController controller = (SubProcesoSuitController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "subProcesoSuitController");
            return controller.getSubprocesoSuit(getKey(value));
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
            if (object instanceof SubprocesoSuit) {
                SubprocesoSuit o = (SubprocesoSuit) object;
                return getStringKey(o.getIdSubprocesoSuit());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + SubprocesoSuit.class.getName());
            }
        }

    }
    

    
}
