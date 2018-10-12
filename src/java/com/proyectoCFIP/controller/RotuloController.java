/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.FtCliente;
import com.proyectoCFIP.entities.Rotulo;
import com.proyectoCFIP.sessions.RotuloFacade;
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
@Named(value = "RotuloController")
@SessionScoped
public class RotuloController implements Serializable {

    @EJB
    private RotuloFacade rotuloFacade;
    private Rotulo rotuloActual;
    private List<Rotulo> listaRotulo;
    
    
    public RotuloController() {
    }
    public RotuloFacade getRotuloFacade() {
        return rotuloFacade;
    }

    public void setRotuloFacade(RotuloFacade rotuloFacade) {
        this.rotuloFacade = rotuloFacade;
    }

    public Rotulo getRotuloActual() {
        return rotuloActual;
    }

    public void setRotuloActual(Rotulo rotuloActual) {
        this.rotuloActual = rotuloActual;
    }

    public List<Rotulo> getListaRotulo() {
        return listaRotulo = getRotuloFacade().findAll();
    }

    public void setListaRotulo(List<Rotulo> listaRotulo) {
        this.listaRotulo = listaRotulo;
    }
    
    public void recargarLista(){
        listaRotulo=null;
    }
    public Rotulo getRotulo(java.lang.Integer id) {
        return getRotuloFacade().find(id);
    }
     public String prepareEdit() {
        return "/administrador/modBiblioteca/editar/editarLibroBi";
    }
      public String prepareCreate() {
        rotuloActual = new Rotulo();
        return "/administrador/modBiblioteca/crear/crearLibroBi";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modBiblioteca/rotulo/lista";
    }

    public String prepareListusuario() {
        recargarLista();
        return "/usuario/modSaludOcupacional/reporte/lista";
    }

    public String add() {
        try { 
            getRotuloFacade().create(rotuloActual);
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String update() {
        try {
            getRotuloFacade().edit(rotuloActual);
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
    
    @FacesConverter(forClass = Rotulo.class)
    public static class RotuloControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RotuloController controller = (RotuloController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "RotuloController");
            return controller.getRotulo(getKey(value));
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
            if (object instanceof Rotulo) {
                Rotulo o = (Rotulo) object;
                return getStringKey(o.getIdRotulo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Rotulo.class.getName());
            }
        }
    }
}
