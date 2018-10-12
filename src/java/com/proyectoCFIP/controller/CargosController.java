/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.sessions.CargosFacade;
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
public class CargosController implements  Serializable{

    
    @EJB
    private CargosFacade cargosFacade;
    private List<Cargos> listaCargos = null;
    private Cargos cargosActual;
    
    public CargosController() {

    }

    public List<Cargos> getListaCargos() {
        listaCargos = new ArrayList<>();
        listaCargos = getCargosFacade().findAll();
        return listaCargos;
    }

    

    public CargosFacade getCargosFacade() {
        return cargosFacade;
    }

    public Cargos getCargosActual() {
        if (cargosActual == null) {
            cargosActual = new Cargos();
        }
        return cargosActual;
    }

    public void setCargosActual(Cargos cargosActual) {
        this.cargosActual = cargosActual;
    }


    private void recargarLista() {
        listaCargos = null;
    }

    public String prepareCreate() {
        cargosActual = new Cargos();
        return "adminCrearTipoComputador";
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

    public String addCargos() {
        try {
            getCargosFacade().create(cargosActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateCargos() {
        try {
            getCargosFacade().edit(cargosActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteCargos() {
        try {
            getCargosFacade().remove(cargosActual);
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
    
    public Cargos getCargos(java.lang.Integer id) {
        return getCargosFacade().find(id);
    }
    @FacesConverter(forClass = Cargos.class)
    public static class CargosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CargosController controller = (CargosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cargosController");
            return controller.getCargos(getKey(value));
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
            if (object instanceof Cargos) {
                Cargos o = (Cargos) object;
                return getStringKey(o.getIdCargo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Cargos.class.getName());
            }
        }
    }
}
