/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.ItemComponente;
import com.proyectoCFIP.sessions.ItemComponenteFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author junio
 */
@Named(value = "itemComponenteController")
@SessionScoped
public class ItemComponenteController implements Serializable {

    @EJB
    private ItemComponenteFacade itemComponenteFacade;
    private ItemComponente itemComponenteActual;
    private List<ItemComponente> listaItemComponente;
    
    public ItemComponenteController() {
    }

    public ItemComponenteFacade getItemComponenteFacade() {
        return itemComponenteFacade;
    }

    public void setItemComponenteFacade(ItemComponenteFacade itemComponenteFacade) {
        this.itemComponenteFacade = itemComponenteFacade;
    }

    public ItemComponente getItemComponenteActual() {
        return itemComponenteActual;
    }

    public void setItemComponenteActual(ItemComponente itemComponenteActual) {
        this.itemComponenteActual = itemComponenteActual;
    }

    public List<ItemComponente> getListaItemComponente() {
        return listaItemComponente = getItemComponenteFacade().findAll();
    }

    public void setListaItemComponente(List<ItemComponente> listaItemComponente) {
        this.listaItemComponente = listaItemComponente;
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

    public ItemComponente getItemComponente(java.lang.Integer id) {
        return getItemComponenteFacade().find(id);
    }

    @FacesConverter(forClass = ItemComponente.class, value="itemComponenteConverter")
    public static class ItemComponenteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ItemComponenteController controller = (ItemComponenteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "itemComponenteController");
            return controller.getItemComponente(getKey(value));
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
            if (object instanceof ItemComponente) {
                ItemComponente o = (ItemComponente) object;
                return getStringKey(o.getIdItemComponente());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ItemComponente.class.getName());
            }
        }
    
    }
}
