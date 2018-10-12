/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.MaquinaConfecciones;
import com.proyectoCFIP.sessions.MaquinaConfeccionesFacade;
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
public class MaquinaConfeccionesController implements Serializable{

    @EJB
    private MaquinaConfeccionesFacade maquinaConfeccionesFacade;
    private MaquinaConfecciones maquinaConfeccionesActual;
    private List<MaquinaConfecciones> maquinaConfeccionesList;
    public MaquinaConfeccionesController() {
    }

    public MaquinaConfeccionesFacade getMaquinaConfeccionesFacade() {
        return maquinaConfeccionesFacade;
    }

    public void setMaquinaConfeccionesFacade(MaquinaConfeccionesFacade maquinaConfeccionesFacade) {
        this.maquinaConfeccionesFacade = maquinaConfeccionesFacade;
    }

    public MaquinaConfecciones getMaquinaConfeccionesActual() {
        return maquinaConfeccionesActual;
    }

    public void setMaquinaConfeccionesActual(MaquinaConfecciones maquinaConfeccionesActual) {
        this.maquinaConfeccionesActual = maquinaConfeccionesActual;
    }

    public List<MaquinaConfecciones> getMaquinaConfeccionesList() {
        return maquinaConfeccionesList = getMaquinaConfeccionesFacade().findAll();
    }

    public void setMaquinaConfeccionesList(List<MaquinaConfecciones> maquinaConfeccionesList) {
        this.maquinaConfeccionesList = maquinaConfeccionesList;
    }

   
    
     public MaquinaConfecciones getMaquinaConfecciones(java.lang.Integer id) {
        return getMaquinaConfeccionesFacade().find(id);
    }
    public String prepareCreate(){
        maquinaConfeccionesActual = new MaquinaConfecciones();
        return "/MaquinasConfecciones/inventarioMaquina/adminCrearMaquina";
    }
     
    public String add(){
         try {
             maquinaConfeccionesActual.setEstado(true);
            getMaquinaConfeccionesFacade().create(maquinaConfeccionesActual);
            return "/User/paginaPrincipalMaquinasConfecciones";
        } catch (Exception e) {
            return null;
        }
    }
    
     public String prepareList(){
        return "/MaquinasConfecciones/inventarioMaquina/adminListMaquina";
    }
     
     
     
    @FacesConverter(forClass = MaquinaConfecciones.class, value = "maquinaConfeccionesConverter")
    public static class MaquinaConfeccionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaquinaConfeccionesController controller = (MaquinaConfeccionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maquinaConfeccionesController");
            return controller.getMaquinaConfecciones(getKey(value));
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
            if (object instanceof MaquinaConfecciones) {
                MaquinaConfecciones o = (MaquinaConfecciones) object;
                return getStringKey(o.getIdMaquinaConfecciones());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MaquinaConfecciones.class.getName());
            }
        }

    }

}
