/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.Habilidad;
import com.proyectoCFIP.sessions.HabilidadFacade;
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
public class HabilidadController implements Serializable {

    @EJB
    private HabilidadFacade HabilidadFacade;
    private List<Habilidad> listaHabilidad = null;
    private List<Habilidad> listHabilidadActual;
    private List<Habilidad> listHabilidadCargo;
    private Habilidad HabilidadActual;

    private Cargos cargosActual;

    public HabilidadController() {

    }

    public List<Habilidad> getListaHabilidad() {
        listaHabilidad = new ArrayList<>();
        listaHabilidad = getHabilidadFacade().findAll();
        return listaHabilidad;
    }

    public HabilidadFacade getHabilidadFacade() {
        return HabilidadFacade;
    }

    public Habilidad getHabilidadActual() {
        if (HabilidadActual == null) {
            HabilidadActual = new Habilidad();
        }
        return HabilidadActual;
    }

    public void setHabilidadActual(Habilidad HabilidadActual) {
        this.HabilidadActual = HabilidadActual;
    }

    public List<Habilidad> getListHabilidadActual() {
        return listHabilidadActual;
    }

    public void setListHabilidadActual(List<Habilidad> listHabilidadActual) {
        this.listHabilidadActual = listHabilidadActual;
    }

    public List<Habilidad> getListHabilidadCargo() {
        return listHabilidadCargo;
    }

    public void setListHabilidadCargo(List<Habilidad> listHabilidadCargo) {
        this.listHabilidadCargo = listHabilidadCargo;
    }

    public Cargos getCargosActual() {
        return cargosActual;
    }

    public void setCargosActual(Cargos cargosActual) {
        this.cargosActual = cargosActual;
    }

    public List<Habilidad> getListreq() {
        return listHabilidadCargo = HabilidadFacade.consultaCargo(cargosActual);
    }

    private void recargarLista() {
        listaHabilidad = null;
    }

    public String prepareCreate() {
        HabilidadActual = new Habilidad();
        listHabilidadActual = new ArrayList<>();
        return "/administrador/modCalidad/cargos/extraHabilidades";
    }

    public String prepareAspecto() {
        HabilidadActual = new Habilidad();
        listHabilidadActual = new ArrayList<>();
        return "/administrador/modCalidad/cargos/editCargo/editExtraHabilidades";

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

    public void add() {
        try {
            HabilidadActual.setIdCargo(cargosActual);
            getHabilidadFacade().create(HabilidadActual);
            listHabilidadActual.add(HabilidadActual);
            HabilidadActual = new Habilidad();
            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }

    public void add2() {
        try {
            HabilidadActual.setIdCargo(cargosActual);
            getHabilidadFacade().create(HabilidadActual);
            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no se creo", "El Registro no fue creado correctamente intentalo de nuevo!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public String updateHabilidad() {
        try {
            getHabilidadFacade().edit(HabilidadActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteHabilidad() {
        try {
            getHabilidadFacade().remove(HabilidadActual);
            recargarLista();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado", "El Registro fue creado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no se Elimino", "El Registro no fue Eliminado correctamente intentalo de nuevo!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
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

    public Habilidad getHabilidad(java.lang.Integer id) {
        return getHabilidadFacade().find(id);
    }

    @FacesConverter(forClass = HabilidadController.class, value = "habilidadControllerConverter")
    public static class HabilidadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HabilidadController controller = (HabilidadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "habilidadControllerController");
            return controller.getHabilidad(getKey(value));
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
            if (object instanceof Habilidad) {
                Habilidad o = (Habilidad) object;
                return getStringKey(o.getIdHabilidad());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Habilidad.class.getName());
            }
        }
    }
}
