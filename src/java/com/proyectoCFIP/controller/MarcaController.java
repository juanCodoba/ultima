/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Marca;
import com.proyectoCFIP.sessions.MarcaFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class MarcaController implements Serializable{
    
    @EJB 
    private MarcaFacade marcaFacade;
    private Marca marcaActual;
    private List<Marca> listaMarca;
    
    public MarcaController() {
    }

    public MarcaFacade getMarcaFacade() {
        return marcaFacade;
    }

    public void setMarcaFacade(MarcaFacade marcaFacade) {
        this.marcaFacade = marcaFacade;
    }

    public Marca getMarcaActual() {
        return marcaActual;
    }

    public void setMarcaActual(Marca marcaActual) {
        this.marcaActual = marcaActual;
    }

    public List<Marca> getListaMarca() {
        return listaMarca = getMarcaFacade().findAll();
    }

    public void setListaMarca(List<Marca> listaMarca) {
        this.listaMarca = listaMarca;
    }
    public Marca getMarca(java.lang.Integer id) {
        return getMarcaFacade().find(id);
    }
    
     private void recargarLista() {
        listaMarca = null;
    }

    public String prepareCreate() {
        marcaActual = new Marca();
        return "crear";
    }

    public String prepareEdit() {
        return "editar";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "marca/lista";
    }

    public String addMarca() {
        try {
            getMarcaFacade().create(marcaActual);
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateMarca() {
        try {
            getMarcaFacade().edit(marcaActual);
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
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
    @FacesConverter(forClass = Marca.class)
    public static class MarcaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MarcaController controller = (MarcaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "marcaController");
            return controller.getMarca(getKey(value));
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
            if (object instanceof Marca) {
                Marca o = (Marca) object;
                return getStringKey(o.getIdMarca());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Marca.class.getName());
            }
        }

    }
    
}
