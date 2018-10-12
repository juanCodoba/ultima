/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipoFicha;
import com.proyectoCFIP.sessions.TipoFichaFacade;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Luis Carlos Cabal
 */
@Named(value = "ftTipoFichaController")
@SessionScoped
public class ftTipoFichaController implements Serializable {

    @EJB
    private TipoFichaFacade ftTipoFichaFacade;
    private List<TipoFicha> ftTipoFichaActualList;
    private TipoFicha ftTipoFichaActual;

    public ftTipoFichaController() {
    }

    public TipoFichaFacade getTipoFichaFacade() {
        return ftTipoFichaFacade;
    }

    public void setTipoFichaFacade(TipoFichaFacade ftTipoFichaFacade) {
        this.ftTipoFichaFacade = ftTipoFichaFacade;
    }

    public List<TipoFicha> getListaTipoF() {
        return ftTipoFichaActualList = getTipoFichaFacade().findAll();
    }

    public void setListaTipoF(List<TipoFicha> ftTipoFichaActualList) {
        this.ftTipoFichaActualList = ftTipoFichaActualList;
    }

    public List<TipoFicha> getTipoFichaActualList() {
        return ftTipoFichaActualList;
    }


    public void setTipoFichaActualList(List<TipoFicha> ftTipoFichaActualList) {
        this.ftTipoFichaActualList = ftTipoFichaActualList;
    }

    public TipoFicha getTipoFichaActual() {
        return ftTipoFichaActual;
    }

    public void setTipoFichaActual(TipoFicha ftTipoFichaActual) {
        this.ftTipoFichaActual = ftTipoFichaActual;
    }

    public TipoFicha getTipoFicha(java.lang.Integer id) {
        return getTipoFichaFacade().find(id);
    }

    public void recargarLista() {
        ftTipoFichaActualList = null;
    }

    @FacesConverter(forClass = TipoFicha.class)
    public static class ftTipoFichaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ftTipoFichaController controller = (ftTipoFichaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ftTipoFichaController");
            return controller.getTipoFicha(getKey(value));
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
            if (object instanceof TipoFicha) {
                TipoFicha o = (TipoFicha) object;
                return getStringKey(o.getIdTipoFicha());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoFicha.class.getName());
            }
        }
    }

}
