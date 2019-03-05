/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.MaeFuncion;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.MaeFuncionFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;

/**
 *
 * @author junio
 */
@Named(value = "MaeFuncionController")
@SessionScoped
public class funcionController implements Serializable {

    @EJB
    private MaeFuncionFacade maeFuncionFacade;
    private MaeFuncion MaeFuncionActual;
    private List<MaeFuncion> listaMaeFuncion;
    private List<MaeFuncion> listaMaeFuncionActual;
    private List<MaeFuncion> listaMaeFuncionTipo = null;

    private Cargos cargosActual;

    private Usuario usuarioActual;

    public funcionController() {
    }

    public MaeFuncionFacade getMaeFuncionFacade() {
        return maeFuncionFacade;
    }

    public void setMaeFuncionFacade(MaeFuncionFacade maeFuncionFacade) {
        this.maeFuncionFacade = maeFuncionFacade;
    }

    public MaeFuncion getMaeFuncionActual() {
        return MaeFuncionActual;
    }

    public void setMaeFuncionActual(MaeFuncion MaeFuncionActual) {
        this.MaeFuncionActual = MaeFuncionActual;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public List<MaeFuncion> getListaMaeFuncion() {
        return listaMaeFuncion = getMaeFuncionFacade().findAll();
    }

    public List<MaeFuncion> getListaMaeFuncionActual() {
        return listaMaeFuncionActual;
    }

    public void setListaMaeFuncionActual(List<MaeFuncion> listaMaeFuncionActual) {
        this.listaMaeFuncionActual = listaMaeFuncionActual;
    }

    public List<MaeFuncion> getListaMaeFuncionTipo() {
        return listaMaeFuncionTipo;
    }

    public void setListaMaeFuncionTipo(List<MaeFuncion> listaMaeFuncionTipo) {
        this.listaMaeFuncionTipo = listaMaeFuncionTipo;

    }

    public void prepareVer() {

        recargarLista();
        MaeFuncionActual = new MaeFuncion();

    }

    //Consulta para agilizar el  select dependiente
    public void listaPorTipoDeMaeFuncion() {
        listaMaeFuncionTipo = getMaeFuncionFacade().consultaUsuarioTipo(cargosActual);
    }

    public List<MaeFuncion> getListaPorTipoDeMaeFuncion2() {
        return listaMaeFuncion = getMaeFuncionFacade().consultaUsuarioTipo(cargosActual);
    }
    //Cierre de Consulta para agilizar el  select dependiente

    public void setListaMaeFuncion(List<MaeFuncion> listaMaeFuncion) {
        this.listaMaeFuncion = listaMaeFuncion;
    }

    public void recargarLista() {
        listaMaeFuncion = null;
    }

    public Cargos getCargosActual() {
        return cargosActual;
    }

    public void setCargosActual(Cargos cargosActual) {
        this.cargosActual = cargosActual;
    }

    public MaeFuncion getMaeFuncion(java.lang.Integer id) {
        return getMaeFuncionFacade().find(id);
    }

    public String prepareEdit() {
        return "/administrador/modCalidad/maestroCargos/funcion/edit";
    }

    public String prepareAspecto() {
        MaeFuncionActual = new MaeFuncion();
        listaMaeFuncionActual = new ArrayList<>();
        return "/administrador/modCalidad/cargos/editCargo/editFuncion";

    }

    public String prepareCreate() {
        MaeFuncionActual = new MaeFuncion();
        listaMaeFuncionActual = new ArrayList<>();
        return "/administrador/modCalidad/cargos/create3";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modCalidad/maestrosCargos/funcion/list";
    }

    public void add() {

        try {
            MaeFuncionActual.setIdCargo(cargosActual);
            MaeFuncionActual.setEstado(Boolean.TRUE);
            getMaeFuncionFacade().create(MaeFuncionActual);
            listaMaeFuncionActual.add(MaeFuncionActual);
            MaeFuncionActual = new MaeFuncion();
            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }

//    public void add2() {
//
//        try {
//            MaeFuncionActual.setIdCargo(cargosActual);
//            add();            
//            addSuccessMessage("Causa creada", "La causa fue creada");
//        } catch (Exception e) {
//            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
//
//        }
//    }
    public String pasarSig() {

        try {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Funciones Asignadas ", "las funciones fueron asginada al cargo correspondiente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "/administrador/modCalidad/cargos/create4";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "";

        }
    }

    public String update() {
        try {
            getMaeFuncionFacade().edit(MaeFuncionActual);
            recargarLista();
            addSuccessMessage("Causa creada", "La causa fue creada");
            return "/administrador/modCalidad/cargos/list";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "";
        }
    }

    public void delete() {
        try {
            getMaeFuncionFacade().remove(MaeFuncionActual);
            listaMaeFuncionActual.remove(MaeFuncionActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "El Registro fue creado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no se Eliminado", "El Registro no fue Eliminado correctamente intentalo de nuevo!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void delete2() {
        try {
            getMaeFuncionFacade().remove(MaeFuncionActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "El Registro fue creado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no se Eliminado", "El Registro no fue Eliminado correctamente intentalo de nuevo!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
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

    @FacesConverter(forClass = MaeFuncion.class, value = "maeFuncionConverter")
    public static class MaeFuncionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            funcionController controller = (funcionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "MaeFuncionController");
            return controller.getMaeFuncion(getKey(value));
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
            if (object instanceof MaeFuncion) {
                MaeFuncion o = (MaeFuncion) object;
                return getStringKey(o.getIdMaeFuncion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MaeFuncion.class.getName());
            }
        }

    }

}
