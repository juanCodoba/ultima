/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.SistemaOperativo;
import com.proyectoCFIP.entities.Subprocesos;
import com.proyectoCFIP.sessions.SubprocesosFacade;
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
public class SubprocesosController implements Serializable{

    @EJB
    private SubprocesosFacade subprocesosFacade;
    private Subprocesos subProcesosActual;
    private List<Subprocesos> listaSubProcesos;
    
    public SubprocesosController() {
    }

    

    public Subprocesos getSubProcesosActual() {
        return subProcesosActual;
    }

    public void setSubProcesosActual(Subprocesos subProcesosActual) {
        this.subProcesosActual = subProcesosActual;
    }

    public List<Subprocesos> getListaSubProcesos() {
        return listaSubProcesos;
    }
    public List<Subprocesos> getListaSubProcesosTotal() {
        return listaSubProcesos = getSubprocesosFacade().findAll();
    }

    public void setListaSubProcesos(List<Subprocesos> listaSubProcesos) {
        this.listaSubProcesos = listaSubProcesos;
    }

    public SubprocesosFacade getSubprocesosFacade() {
        return subprocesosFacade;
    }

    public void setSubprocesosFacade(SubprocesosFacade subprocesosFacade) {
        this.subprocesosFacade = subprocesosFacade;
    }
    
    
    
    public List<Subprocesos> getListaSubprocesosSelectOne(){
        return getSubprocesosFacade().findAll();
    }
    
    
    public Subprocesos getSubprocesos(java.lang.Integer id) {
        return getSubprocesosFacade().find(id);
    }
    
    @FacesConverter(forClass = Subprocesos.class,value = "subprocesoConverter")
    public static class SubprocesosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SubprocesosController controller = (SubprocesosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "subprocesosController");
            return controller.getSubprocesos(getKey(value));
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
            if (object instanceof Subprocesos) {
                Subprocesos o = (Subprocesos) object;
                return getStringKey(o.getIdSubproceso());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Subprocesos.class.getName());
            }
        }

    }

    
}
