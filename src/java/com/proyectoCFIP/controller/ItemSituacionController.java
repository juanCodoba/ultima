/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.AuAspectoEvaluar;
import com.proyectoCFIP.entities.ItemSituacion;
import com.proyectoCFIP.sessions.ItemSituacionFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
public class ItemSituacionController implements Serializable{

    @EJB
    private ItemSituacionFacade itemSituacionFacade;
    private List<ItemSituacion> listaItemSituacion = null;
    private ItemSituacion itemSituacionActual;
    
    public ItemSituacionController() {

    }

    public List<ItemSituacion> getListaItemSituacion() {
        listaItemSituacion = new ArrayList<>();
        listaItemSituacion = getItemSituacionFacade().findAll();
        return listaItemSituacion;
    }


    

    public ItemSituacionFacade getItemSituacionFacade() {
        return itemSituacionFacade;
    }

    public ItemSituacion getItemSituacionActual() {
        if (itemSituacionActual == null) {
            itemSituacionActual = new ItemSituacion();
        }
        return itemSituacionActual;
    }

    public void setItemSituacionActual(ItemSituacion itemSituacionActual) {
        this.itemSituacionActual = itemSituacionActual;
    }


    private void recargarLista() {
        listaItemSituacion = null;
    }

    public String prepareCreate() {
        itemSituacionActual = new ItemSituacion();
        return "adminCrearItemSituacionComputador";
    }

    public String prepareEdit() {
        return "";
    }

    public String prepareView() {
        return "";
    }

    public String prepareList() {
        recargarLista();
        return "";
    }

        public void prepareAspecto() {
        recargarLista();
        itemSituacionActual = new ItemSituacion();
    }
    public void addItemSituacion() {
        try {
            getItemSituacionFacade().create(itemSituacionActual);
            recargarLista();
            
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            
        }
    }
    public String updateItemSituacion() {
        try {
            getItemSituacionFacade().edit(itemSituacionActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteItemSituacion() {
        try {
            getItemSituacionFacade().remove(itemSituacionActual);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        return "List";
    }

    private void addErrorMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    private void addSuccessMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }
    
    public ItemSituacion getItemSituacion(java.lang.Integer id) {
        return getItemSituacionFacade().find(id);
    }
    
    @FacesConverter(forClass = ItemSituacion.class)
    public static class ItemSituacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ItemSituacionController controller = (ItemSituacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "itemSituacionController");
            return controller.getItemSituacion(getKey(value));
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
            if (object instanceof ItemSituacion) {
                ItemSituacion o = (ItemSituacion) object;
                return getStringKey(o.getIdItemsSituacion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ItemSituacion.class.getName());
            }
        }
    }
}
