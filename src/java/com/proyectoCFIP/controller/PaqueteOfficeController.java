/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.PaqueteOffice;
import com.proyectoCFIP.sessions.PaqueteOfficeFacade;
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
public class PaqueteOfficeController {

    @EJB
    private PaqueteOfficeFacade paqueteOfficeFacade;
    private PaqueteOffice paqueteOfficeActual;
    private List<PaqueteOffice> listaPaqueteOffice;
    
    public PaqueteOfficeController() {
    }
    
    public List<PaqueteOffice> getListaPaqueteOfficeSelectOne(){
         return getPaqueteOfficeFacade().findAll();
    }

    public PaqueteOfficeFacade getPaqueteOfficeFacade() {
        return paqueteOfficeFacade;
    }

    public void setPaqueteOfficeFacade(PaqueteOfficeFacade paqueteOfficeFacade) {
        this.paqueteOfficeFacade = paqueteOfficeFacade;
    }

    public PaqueteOffice getPaqueteOfficeActual() {
        return paqueteOfficeActual;
    }

    public void setPaqueteOfficeActual(PaqueteOffice paqueteOfficeActual) {
        this.paqueteOfficeActual = paqueteOfficeActual;
    }

    public List<PaqueteOffice> getListaPaqueteOffice() {
        return listaPaqueteOffice;
    }

    public void setListaPaqueteOffice(List<PaqueteOffice> listaPaqueteOffice) {
        this.listaPaqueteOffice = listaPaqueteOffice;
    }
    
     private void recargarLista() {
        listaPaqueteOffice = null;
    }
    public String prepareCreate() {
        paqueteOfficeActual = new PaqueteOffice();
        return "/Admin/configuracionesGenerales/adminCrearPaqueteOffice";
    }

    public String prepareEdit() {
        return "";
    }

    public String prepareView() {
        return "";
    }

    public String prepareList() {
        return "";
    }

    public String addPaquete() {
        try {
            getPaqueteOfficeFacade().create(paqueteOfficeActual);
            addSuccessMessage("Paquete office guardado", "paquete office guardado exitosamente");
            recargarLista();
            return "/User/paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateSistemaOperativo() {
        try {
            getPaqueteOfficeFacade().edit(paqueteOfficeActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteSistemaOperativo() {
        try {
            getPaqueteOfficeFacade().remove(paqueteOfficeActual);
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
    
    public PaqueteOffice getPaqueteOffice(java.lang.Integer id) {
        return getPaqueteOfficeFacade().find(id);
    }
      @FacesConverter(forClass = PaqueteOffice.class)
    public static class PaqueteOfficeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PaqueteOfficeController controller = (PaqueteOfficeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "paqueteOfficeController");
            return controller.getPaqueteOffice(getKey(value));
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
            if (object instanceof PaqueteOffice) {
                PaqueteOffice o = (PaqueteOffice) object;
                return getStringKey(o.getIdPaqueteOffice());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PaqueteOffice.class.getName());
            }
        }

    }
    
    
}
