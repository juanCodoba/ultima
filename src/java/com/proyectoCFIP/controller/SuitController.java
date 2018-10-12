/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Suit;
import com.proyectoCFIP.sessions.SuitFacade;
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
public class SuitController {

    @EJB
    private SuitFacade suitFacade;
    private Suit suitActual;
    private List<Suit> listaSuit;
    
    public SuitController() {
    }

    public SuitFacade getSuitFacade() {
        return suitFacade;
    }

    public void setSuitFacade(SuitFacade suitFacade) {
        this.suitFacade = suitFacade;
    }

    public Suit getSuitActual() {
        return suitActual;
    }

    public void setSuitActual(Suit suitActual) {
        this.suitActual = suitActual;
    }

    public List<Suit> getListaSuit() {
        listaSuit = getSuitFacade().findAll();
        return listaSuit;
    }

    public void setListaSuit(List<Suit> listaSuit) {
        this.listaSuit = listaSuit;
    }
    public Suit getSuit(java.lang.Integer id) {
        return getSuitFacade().find(id);
    }
    
    
    @FacesConverter(forClass = Suit.class)
    public static class SuitControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SuitController controller = (SuitController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "suitController");
            return controller.getSuit(getKey(value));
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
            if (object instanceof Suit) {
                Suit o = (Suit) object;
                return getStringKey(o.getIdSuit());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Suit.class.getName());
            }
        }

    }

    
}
