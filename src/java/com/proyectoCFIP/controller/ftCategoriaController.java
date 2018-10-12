/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.FtCategoria;
import com.proyectoCFIP.sessions.FtCategoriaFacade;
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
@Named(value = "ftCategoriaController")
@SessionScoped
public class ftCategoriaController implements Serializable {

    @EJB
    private FtCategoriaFacade ftCategoriaFacade;
    private List<FtCategoria> ftCategoriaActualList;
    private FtCategoria ftCategoriaActual;

    public ftCategoriaController() {
    }

    public FtCategoriaFacade getFtCategoriaFacade() {
        return ftCategoriaFacade;
    }

    public void setFtCategoriaFacade(FtCategoriaFacade ftCategoriaFacade) {
        this.ftCategoriaFacade = ftCategoriaFacade;
    }

    public List<FtCategoria> getListaCategoria() {
        return ftCategoriaActualList = getFtCategoriaFacade().findAll();
    }

    public void setListaCategoria(List<FtCategoria> ftCategoriaActualList) {
        this.ftCategoriaActualList = ftCategoriaActualList;
    }

    public List<FtCategoria> getFtCategoriaActualList() {
        return ftCategoriaActualList;
    }


    public void setFtCategoriaActualList(List<FtCategoria> ftCategoriaActualList) {
        this.ftCategoriaActualList = ftCategoriaActualList;
    }

    public FtCategoria getFtCategoriaActual() {
        return ftCategoriaActual;
    }

    public void setFtCategoriaActual(FtCategoria ftCategoriaActual) {
        this.ftCategoriaActual = ftCategoriaActual;
    }

    public FtCategoria getFtCategoria(java.lang.Integer id) {
        return getFtCategoriaFacade().find(id);
    }

    public void recargarLista() {
        ftCategoriaActualList = null;
    }

    @FacesConverter(forClass = FtCategoria.class)
    public static class ftCategoriaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ftCategoriaController controller = (ftCategoriaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ftCategoriaController");
            return controller.getFtCategoria(getKey(value));
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
            if (object instanceof FtCategoria) {
                FtCategoria o = (FtCategoria) object;
                return getStringKey(o.getIdFtCategoria());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FtCategoria.class.getName());
            }
        }
    }

}
