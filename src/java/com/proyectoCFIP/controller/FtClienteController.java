/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.FtCliente;
import com.proyectoCFIP.sessions.FtClienteFacade;
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
@Named(value = "ftClienteController")
@SessionScoped
public class FtClienteController implements Serializable {

    @EJB
    private FtClienteFacade clienteFacade;
    private FtCliente clienteActual;
    private List<FtCliente> listaCliente;
    public FtClienteController() {
    }
    
    
    
    public FtClienteFacade getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(FtClienteFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    public FtCliente getClienteActual() {
        return clienteActual;
    }

    public void setClienteActual(FtCliente clienteActual) {
        this.clienteActual = clienteActual;
    }

    public List<FtCliente> getListaCliente() {
        return listaCliente = getClienteFacade().findAll();
    }

    public void setListaCliente(List<FtCliente> listaCliente) {
        this.listaCliente = listaCliente;
    }
    
    public void recargarLista(){
        listaCliente=null;
    }
    public FtCliente getFtCliente(java.lang.Integer id) {
        return getClienteFacade().find(id);
    }
     public String prepareEdit() {
        return "/administrador/modFichaTecnica/cliente/editar";
    }
      public String prepareCreate() {
        clienteActual = new FtCliente();
        return "/administrador/modFichaTecnica/cliente/crear";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modFichaTecnica/cliente/lista";
    }

    public String prepareListusuario() {
        recargarLista();
        return "/usuario/modSaludOcupacional/reporte/lista";
    }

    public String add() {
        try { 
            getClienteFacade().create(clienteActual);
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String update() {
        try {
            getClienteFacade().edit(clienteActual);
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
    
    @FacesConverter(forClass = FtCliente.class)
    public static class ftClienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FtClienteController controller = (FtClienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ftClienteController");
            return controller.getFtCliente(getKey(value));
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
            if (object instanceof FtCliente) {
                FtCliente o = (FtCliente) object;
                return getStringKey(o.getIdFtCliente());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FtCliente.class.getName());
            }
        }
    }
}
