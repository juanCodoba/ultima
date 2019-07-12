/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Cargos;

import com.proyectoCFIP.entities.Requisito;
import com.proyectoCFIP.sessions.CargosFacade;

import com.proyectoCFIP.sessions.RequisitoFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class RequisitosController implements Serializable {

    @EJB
    private RequisitoFacade requisitosFacade;
    private List<Requisito> listaRequisitoActual;
    private List<Requisito> listaRequisito;
    private List<Requisito> listreq;
    private Requisito requisitoActual;
    private Requisito requisitos;

    @EJB
    private CargosFacade cargosFacade;
    private Cargos cargosActual;
    private List<Cargos> listaCaegos;

    public RequisitosController() {

    }

    public RequisitoFacade getRequisitosFacade() {
        return requisitosFacade;
    }

    public void setRequisitosFacade(RequisitoFacade requisitosFacade) {
        this.requisitosFacade = requisitosFacade;
    }

    public List<Requisito> getListaRequisitoActual() {
        return listaRequisitoActual;
    }

    public void setListaRequisitoActual(List<Requisito> listaRequisitoActual) {
        this.listaRequisitoActual = listaRequisitoActual;
    }

    public List<Requisito> getListaRequisito() {
        return listaRequisito = requisitosFacade.findAll();
    }

    public void setListaRequisito(List<Requisito> listaRequisito) {
        this.listaRequisito = listaRequisito;
    }

    public Requisito getRequisitoActual() {
        return requisitoActual;
    }

    public void setRequisitoActual(Requisito requisitoActual) {
        this.requisitoActual = requisitoActual;
    }

    public List<Requisito> getListreq() {
        return listreq = requisitosFacade.consultaCargo(cargosActual);
    }

    public void setListreq(List<Requisito> listreq) {
        this.listreq = listreq;
    }

    public String prepareAspecto() {
        requisitoActual = new Requisito();
        listaRequisitoActual = new ArrayList<>();
        return "/administrador/modCalidad/cargos/editCargo/editRequisito";

    }

    public String prepareEdit() {
        return "/administrador/modCalidad/cargos/editCargo/editRequisito";
    }

    public String prepareView() {
        return "";
    }

    public String list() {
        return "/administrador/modCalidad/maestrosCargos/tipoRequisito/list";
    }

    public String prepareCreate() {
        requisitoActual = new Requisito();
        listaRequisitoActual = new ArrayList<>();
        return "/administrador/modCalidad/cargos/create2";
    }

    public Requisito getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(Requisito requisitos) {
        this.requisitos = requisitos;
    }

    public void deleteRequisitoLista() {
        try {
            listaRequisitoActual.remove(requisitoActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "El Registro fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no se elimino", "El Registro no fue eliminado correctamente intentalo de nuevo!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void recargarLista() {
        listaRequisitoActual = null;
    }

    public CargosFacade getCargosFacade() {
        return cargosFacade;
    }

    public void setCargosFacade(CargosFacade cargosFacade) {
        this.cargosFacade = cargosFacade;
    }

    public Cargos getCargosActual() {
        return cargosActual;
    }

    public void setCargosActual(Cargos cargosActual) {
        this.cargosActual = cargosActual;
    }

    public List<Cargos> getListaCaegos() {
        return listaCaegos;
    }

    public void setListaCaegos(List<Cargos> listaCaegos) {
        this.listaCaegos = listaCaegos;
    }

    public void add() {
        try {
            requisitoActual.setIdCargo(cargosActual);
            requisitoActual.setEstado(Boolean.TRUE);
            getRequisitosFacade().create(requisitoActual);
            listaRequisitoActual.add(requisitoActual);
            requisitoActual = new Requisito();
            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }

    public void add2() {
        try {
            requisitoActual.setIdCargo(cargosActual);
            requisitoActual.setEstado(Boolean.TRUE);
            getRequisitosFacade().create(requisitoActual);
            requisitoActual = new Requisito();

            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public String editRequisito() {
        try {
            requisitoActual.setEstado(Boolean.TRUE);
            getRequisitosFacade().edit(requisitoActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Requisito editado", "se asignado el requisito al cargo");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "/administrador/modCalidad/maestrosCargos/tipoRequisito/list";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/administrador/modCalidad/maestrosCargos/tipoRequisito/list";

        }
    }

    public void delete() {
        try {
            getRequisitosFacade().remove(requisitoActual);
            listaRequisitoActual.remove(requisitoActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "El Registro fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no se elimino", "El Registro no fue eliminado correctamente intentalo de nuevo!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public String prepareList2() {
        return "/administrador/modCalidad/maestrosCargos/tipoRequisito/list";
    }

    public String prepareEdit2() {
        return "/administrador/modCalidad/maestrosRequisito/tipoRequisito/edit";
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

    public Requisito getRequisito(java.lang.Integer id) {
        return getRequisitosFacade().find(id);
    }

    @FacesConverter(forClass = Requisito.class)
    public static class RequisitoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RequisitosController controller = (RequisitosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "requisitosController");
            return controller.getRequisito(getKey(value));
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
            if (object instanceof Requisito) {
                Requisito o = (Requisito) object;
                return getStringKey(o.getIdRequisito());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Requisito.class.getName());
            }
        }
    }
}
