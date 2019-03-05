/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.MaeHerramienta;

import com.proyectoCFIP.entities.Usuario;

import com.proyectoCFIP.sessions.MaeHerramientaFacade;

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

import org.primefaces.context.RequestContext;


/**
 *
 * @author junio
 */
@Named(value = "MaeHerramientaController")
@SessionScoped
public class HerramientaController implements Serializable {

    @EJB
    private MaeHerramientaFacade maeHerramientaFacade;
    private MaeHerramienta MaeHerramientaActual;
    private List<MaeHerramienta> listaMaeHer;
    private List<MaeHerramienta> listaMaeHerramienta;

    private List<MaeHerramienta> listaMaeHerramientaTipo = null;

    private Cargos cargosActual;

    private Usuario usuarioActual;

    public HerramientaController() {
    }

    public MaeHerramientaFacade getMaeHerramientaFacade() {
        return maeHerramientaFacade;
    }

    public void setMaeHerramientaFacade(MaeHerramientaFacade maeHerramientaFacade) {
        this.maeHerramientaFacade = maeHerramientaFacade;
    }

    public MaeHerramienta getMaeHerramientaActual() {
        return MaeHerramientaActual;
    }

    public void setMaeHerramientaActual(MaeHerramienta MaeHerramientaActual) {
        this.MaeHerramientaActual = MaeHerramientaActual;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public List<MaeHerramienta> getListaMaeHerramienta() {
        return listaMaeHerramienta = getMaeHerramientaFacade().findAll();
    }

    public List<MaeHerramienta> getListaMaeHerramientaTipo() {
        return listaMaeHerramientaTipo;
    }

    public void setListaMaeHerramientaTipo(List<MaeHerramienta> listaMaeHerramientaTipo) {
        this.listaMaeHerramientaTipo = listaMaeHerramientaTipo;
    }

    //Consulta para agilizar el  select dependiente
//    public void listaPorTipoDeMaeHerramienta() {
//        listaMaeHerramientaTipo = getMaeHerramientaFacade().consultaUsuarioTipo(cargosActual);
//    }
    //Cierre de Consulta para agilizar el  select dependiente

    public void setListaMaeHerramienta(List<MaeHerramienta> listaMaeHerramienta) {
        this.listaMaeHerramienta = listaMaeHerramienta;
    }

    public void recargarLista() {
        listaMaeHerramienta = null;
    }

    public Cargos getCargosActual() {
        return cargosActual;
    }

    public void setCargosActual(Cargos cargosActual) {
        this.cargosActual = cargosActual;
    }

    public List<MaeHerramienta> getListaMaeHer() {
        return listaMaeHer;
    }

    public void setListaMaeHer(List<MaeHerramienta> listaMaeHer) {
        this.listaMaeHer = listaMaeHer;
    }
    
    

    
    
    public MaeHerramienta getMaeHerramienta(java.lang.Integer id) {
        return getMaeHerramientaFacade().find(id);
    }

    public String prepareEdit() {
        return "/administrador/modCalidad/maestroCargos/funcion/edit";
    }

    public String prepareCreate() {
        MaeHerramientaActual = new MaeHerramienta();
        return "/administrador/modCalidad/cargos/create5";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modBiblioteca/listar/lista";
    }

    public void add() {

        try {
            MaeHerramientaActual.setEstado(Boolean.TRUE);
            getMaeHerramientaFacade().create(MaeHerramientaActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "MaeHerramienta creado", "El MaeHerramienta fue creado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();

        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }

    public String pasarSig() {

        try {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Listado de requisitos ", "Guardos correctamente correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "/administrador/modCalidad/cargos/list";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "";

        }
    }

    public String update() {
        try {
            getMaeHerramientaFacade().edit(MaeHerramientaActual);
            recargarLista();
            return "/administrador/modCalidad/cargos/list";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "";
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

    @FacesConverter(forClass = MaeHerramienta.class, value = "maeHerramientaConverter")
    public static class MaeHerramientaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HerramientaController controller = (HerramientaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "MaeHerramientaController");
            return controller.getMaeHerramienta(getKey(value));
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
            if (object instanceof MaeHerramienta) {
                MaeHerramienta o = (MaeHerramienta) object;
                return getStringKey(o.getIdMaeHerramienta());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MaeHerramienta.class.getName());
            }
        }

    }

}
