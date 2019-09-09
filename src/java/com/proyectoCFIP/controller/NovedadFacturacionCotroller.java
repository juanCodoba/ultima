/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.NovedadFacturacion;
import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.NovedadFacturacionFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author junio
 */
@Named(value = "NovedadFacturacionCotroller")
@SessionScoped
public class NovedadFacturacionCotroller implements Serializable {

    @EJB
    private NovedadFacturacionFacade novedadFacade;
    private NovedadFacturacion NovedadFacturacionActual;
    private List<NovedadFacturacion> listaNovedadFacturacion;
    private List<NovedadFacturacion> listaNovedadFacturacionActual;
    private List<NovedadFacturacion> listaNovedadFacturacionCargo;
    private Usuario usuarioActual;

    private Cargos cargosActual;

    public NovedadFacturacionCotroller() {
    }


    public NovedadFacturacionFacade getNovedadFacturacionFacade() {
        return novedadFacade;
    }

    public void setNovedadFacturacionFacade(NovedadFacturacionFacade novedadFacade) {
        this.novedadFacade = novedadFacade;
    }

    public NovedadFacturacion getNovedadFacturacionActual() {
        return NovedadFacturacionActual;
    }

    public void setNovedadFacturacionActual(NovedadFacturacion NovedadFacturacionActual) {
        this.NovedadFacturacionActual = NovedadFacturacionActual;
    }

    public List<NovedadFacturacion> getListaNovedadFacturacionCargo() {
        return listaNovedadFacturacionCargo = novedadFacade.findAll();
    }

    public void setListaNovedadFacturacionCargo(List<NovedadFacturacion> listaNovedadFacturacionCargo) {
        this.listaNovedadFacturacionCargo = listaNovedadFacturacionCargo;
    }

    public void setListaNovedadFacturacion(List<NovedadFacturacion> listaNovedadFacturacion) {
        this.listaNovedadFacturacion = listaNovedadFacturacion;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public Cargos getCargosActual() {
        return cargosActual;
    }

    public void setCargosActual(Cargos cargosActual) {
        this.cargosActual = cargosActual;
    }

    public List<NovedadFacturacion> getListaNovedadFacturacionActual() {
        return listaNovedadFacturacionActual;
    }

    public void setListaNovedadFacturacionActual(List<NovedadFacturacion> listaNovedadFacturacionActual) {
        this.listaNovedadFacturacionActual = listaNovedadFacturacionActual;
    }

    public void recargarLista() {
        listaNovedadFacturacion = null;
    }

    public NovedadFacturacion getNovedadFacturacion(java.lang.Integer id) {
        return getNovedadFacturacionFacade().find(id);
    }

    public String prepareListNovedadFacturacion() {
        return "/usuario/desayuno/lista";
    }
    
        public String prepareEditNovedadFacturacion() {
        return "/usuario/desayuno/editar";
    }

    public String editar() {
        try {
            NovedadFacturacionActual.setRealConfecciones(NovedadFacturacionActual.getTotalConfecciones().add(NovedadFacturacionActual.getFcrmNrniff()).subtract(NovedadFacturacionActual.getFcrmRniff()));
            getNovedadFacturacionFacade().edit(NovedadFacturacionActual);
            addSuccessMessage("NovedadFacturacion", "La NovedadFacturacion con el codigo " + NovedadFacturacionActual.getIdNovedadFacturacion() + " fue creada editada");
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

    @FacesConverter(forClass = NovedadFacturacion.class)
    public static class NovedadFacturacionCotrollerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NovedadFacturacionCotroller controller = (NovedadFacturacionCotroller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "NovedadFacturacionFacturacionCotroller");
            return controller.getNovedadFacturacion(getKey(value));
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
            if (object instanceof NovedadFacturacion) {
                NovedadFacturacion o = (NovedadFacturacion) object;
                return getStringKey(o.getIdNovedadFacturacion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + NovedadFacturacion.class.getName());
            }
        }

    }

}
