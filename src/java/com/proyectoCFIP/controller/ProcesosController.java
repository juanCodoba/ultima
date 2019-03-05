/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.ProcesosFacade;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class ProcesosController implements Serializable {

    @EJB
    private ProcesosFacade procesosFacade;
    private Procesos procesosActual;
    private List<Procesos> listaProcesosUser;
    private List<Procesos> listaProcesosActual;
    
    private Usuario usuarioActual;

    public ProcesosController() {
    }

    public ProcesosFacade getProcesosFacade() {
        return procesosFacade;
    }

    public void setProcesosFacade(ProcesosFacade procesosFacade) {
        this.procesosFacade = procesosFacade;
    }

    public Procesos getProcesosActual() {
        return procesosActual;
    }

    public void setProcesosActual(Procesos procesosActual) {
        this.procesosActual = procesosActual;
    }

    public List<Procesos> getListaProcesosActual() {
        return listaProcesosActual;
    }

    public List<Procesos> getListaProcesos() {
        return getProcesosFacade().findAll();
    }

    public void setListaProcesosActual(List<Procesos> listaProcesosActual) {
        this.listaProcesosActual = listaProcesosActual;
    }

    public List<Procesos> getListaProcesosUser() {
        return listaProcesosUser;
    }

    public void setListaProcesosUser(List<Procesos> listaProcesosUser) {
        this.listaProcesosUser = listaProcesosUser;
    }



    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    
    
    

    public List<Procesos> getListaProcesoUser() {
        listaProcesosUser = new ArrayList<>();
        listaProcesosUser = getProcesosFacade().consultaUsuario(usuarioActual);
        return listaProcesosUser;
    }

    public void edit() {
        try {

//            factoresActual.setIdCargo(cargosActual);
//            factoresActual.setEstado(Boolean.TRUE);
//            factoresActual.setPuntuacion(ObtenerPuntuacion(importanciaActual.getIdImportancia(),riesgoActual.getIdRiesgo(), oportunidadActual.getIdOportunidad()));
            getProcesosFacade().edit(procesosActual);
            addSuccessMessage("Causa creada", "La causa fue creada");
recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }

        public void recargarLista() {
        listaProcesosActual = null;
    }
    public String add2() {
        try {

//            factoresActual.setIdCargo(cargosActual);
//            factoresActual.setEstado(Boolean.TRUE);
//            factoresActual.setPuntuacion(ObtenerPuntuacion(importanciaActual.getIdImportancia(),riesgoActual.getIdRiesgo(), oportunidadActual.getIdOportunidad()));
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Actualizado", "El Registro fue Actualizado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "list";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no se Actualizado", "El Registro no fue Actualizado correctamente intentalo de nuevo!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "list";
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

    public Procesos getProcesos(java.lang.Integer id) {
        return getProcesosFacade().find(id);
    }

    @FacesConverter(forClass = Procesos.class)
    public static class ProcesosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProcesosController controller = (ProcesosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "procesosController");
            return controller.getProcesos(getKey(value));
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
            if (object instanceof Procesos) {
                Procesos o = (Procesos) object;
                return getStringKey(o.getIdProceso());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Procesos.class.getName());
            }
        }

    }

}
