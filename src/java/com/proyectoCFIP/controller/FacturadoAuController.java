/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Facturado;
import com.proyectoCFIP.entities.FacturadoAu;
import com.proyectoCFIP.sessions.FacturadoAuFacade;
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
public class FacturadoAuController implements Serializable {

    @EJB
    private FacturadoAuFacade facturadoAuFacade;
    private List<FacturadoAu> listaFacturadoAu = null;
    private FacturadoAu facturadoAuActual;

    private Facturado facturadoActual;

    public FacturadoAuController() {

    }

    public List<FacturadoAu> getListaFacturadoAu() {
        listaFacturadoAu = new ArrayList<>();
        listaFacturadoAu = getFacturadoAuFacade().findAll();
        return listaFacturadoAu;
    }
//    public List<FacturadoAu> getListaFacturadoAuConsulta() {
//        return getFacturadoAuFacade().consultaFacturadoAu();
//    }

    public List<FacturadoAu> getListreq() {
        return listaFacturadoAu = facturadoAuFacade.consultaCargo(facturadoActual);
    }

    public FacturadoAuFacade getFacturadoAuFacade() {
        return facturadoAuFacade;
    }

    public FacturadoAu getFacturadoAuActual() {
        if (facturadoAuActual == null) {
            facturadoAuActual = new FacturadoAu();
        }
        return facturadoAuActual;
    }

    public void setFacturadoAuActual(FacturadoAu facturadoAuActual) {
        this.facturadoAuActual = facturadoAuActual;
    }

    private void recargarLista() {
        listaFacturadoAu = null;
    }

    public String prepareCreate() {
        facturadoAuActual = new FacturadoAu();
        return "adminCrearFacturadoAuComputador";
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

    public String addFacturadoAu() {
        try {
            getFacturadoAuFacade().create(facturadoAuActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String updateFacturadoAu() {
        try {
            facturadoAuActual.setActivo(Boolean.FALSE);
            getFacturadoAuFacade().edit(facturadoAuActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public Facturado getFacturadoActual() {
        return facturadoActual;
    }

    public void setFacturadoActual(Facturado facturadoActual) {
        this.facturadoActual = facturadoActual;
    }
    

    public String deleteFacturadoAu() {
        try {
            getFacturadoAuFacade().remove(facturadoAuActual);
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

    public FacturadoAu getFacturadoAu(java.lang.Integer id) {
        return getFacturadoAuFacade().find(id);
    }

    @FacesConverter(forClass = FacturadoAu.class)
    public static class FacturadoAuControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FacturadoAuController controller = (FacturadoAuController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "facturadoAuController");
            return controller.getFacturadoAu(getKey(value));
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
            if (object instanceof FacturadoAu) {
                FacturadoAu o = (FacturadoAu) object;
                return getStringKey(o.getIdfacturadoAU());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FacturadoAu.class.getName());
            }
        }
    }
}
