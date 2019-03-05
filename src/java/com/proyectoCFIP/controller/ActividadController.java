/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Actividad;
import com.proyectoCFIP.entities.AuAspectoEvaluar;
import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.CronogramaMantenimientos;
import com.proyectoCFIP.entities.EstadoCronograma;
import com.proyectoCFIP.entities.MaeFuncion;
import com.proyectoCFIP.sessions.ActividadFacade;
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
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author junio
 */
@Named(value = "ActividadController")
@SessionScoped
public class ActividadController implements Serializable {

    @EJB
    private ActividadFacade actividadFacade;
    private Actividad ActividadActual;
    private List<Actividad> listaActividad;
    private List<Actividad> listaActividadActual;
    private List<Actividad> listaActividadCargo;
    private MaeFuncion maeFuncionActual;

    private Cargos cargosActual;

    public ActividadController() {
    }

    public ActividadFacade getActividadFacade() {
        return actividadFacade;
    }

    public void setActividadFacade(ActividadFacade actividadFacade) {
        this.actividadFacade = actividadFacade;
    }

    public Actividad getActividadActual() {
        return ActividadActual;
    }

    public void setActividadActual(Actividad ActividadActual) {
        this.ActividadActual = ActividadActual;
    }

    public List<Actividad> getListaActividad() {
        return listaActividad = getActividadFacade().findAll();
    }

    public List<Actividad> getListaActividadCargo() {
        return listaActividadCargo;
    }

    public void setListaActividadCargo(List<Actividad> listaActividadCargo) {
        this.listaActividadCargo = listaActividadCargo;
    }

    public List<Actividad> getListaPorTipoDeMaeFuncion2() {
        return listaActividadCargo = getActividadFacade().consultaUsuarioTipo(cargosActual);
    }

    public void setListaActividad(List<Actividad> listaActividad) {
        this.listaActividad = listaActividad;
    }

    public MaeFuncion getMaeFuncionActual() {
        return maeFuncionActual;
    }

    public void setMaeFuncionActual(MaeFuncion maeFuncionActual) {
        this.maeFuncionActual = maeFuncionActual;
    }

    public Cargos getCargosActual() {
        return cargosActual;
    }

    public void setCargosActual(Cargos cargosActual) {
        this.cargosActual = cargosActual;
    }

    public List<Actividad> getListaActividadActual() {
        return listaActividadActual;
    }

    public void setListaActividadActual(List<Actividad> listaActividadActual) {
        this.listaActividadActual = listaActividadActual;
    }

    public void recargarLista() {
        listaActividad = null;
    }

    public Actividad getActividad(java.lang.Integer id) {
        return getActividadFacade().find(id);
    }

    public String prepareEdit() {
        return "/administrador/modBiblioteca/editar/editarActividadBi";
    }

    public String prepareEditLibRes() {
        return "/administrador/modBiblioteca/editar/editarActividadBi";
    }

    public String prepareEditP() {
        return "/administrador/reservaActividad/listar/LibOcupR";
    }

    public String prepareCreate() {
        ActividadActual = new Actividad();
        listaActividadActual = new ArrayList<>();
        return "/administrador/modCalidad/cargos/create4";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modBiblioteca/listar/lista";
    }

    public String prepareListusuario() {
        recargarLista();
        return "/usuario/modSaludOcupacional/reporte/lista";
    }

    public String prepareListPreven() {
        recargarLista();
        return "/administrador/modBiblioteca/mantenimientos/preventivo/preventivo";
    }

    public String prepareListCorrect() {
        recargarLista();
        return "/administrador/modBiblioteca/mantenimientos/correctivo/correctivo";
    }

    public String prepareUpdateMant() {
        recargarLista();
        return "/administrador/modBiblioteca/mantenimientos/preventivo/editarP";
    }

    public String prepareListDeBaja() {
        recargarLista();
        return "/administrador/modBiblioteca/mantenimientos/deBaja/deBaja";
    }

    public String prepareCreateMan() {
        recargarLista();
        return "/administrador/modBiblioteca/mantenimientos/preventivo/crearMaP";
    }

    public void add() {

        try {
            ActividadActual.setIdCargo(cargosActual);
            ActividadActual.setEstado(Boolean.TRUE);
            getActividadFacade().create(ActividadActual);
            listaActividadActual.add(ActividadActual);
            ActividadActual = new Actividad();
            addSuccessMessage("Actividad", "La Actividad fue creada exitosamente");

            //recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }

    public void add2() {

        try {
            ActividadActual.setIdCargo(cargosActual);
            ActividadActual.setEstado(Boolean.TRUE);
            getActividadFacade().create(ActividadActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado", "El Registro fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            //recargarLista();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no Eliminado", "El Registro no fue eliminado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public String prepareAspecto() {
        ActividadActual = new Actividad();
        listaActividadActual = new ArrayList<>();
                return "/administrador/modCalidad/cargos/editCargo/editActividad";


    }

    public String GuardarTodo() {

        try {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actividad creado", "El Actividad fue creado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "/administrador/modCalidad/cargos/list";

        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/administrador/modCalidad/cargos/list";

        }
    }

    public String update() {
        try {

            getActividadFacade().edit(ActividadActual);
            recargarLista();
            return "/usuario/modBiblioteca/ListarActividad/lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/usuario/modBiblioteca/ListarActividad/lista";
        }
    }

    public String updateMantenimiento() {
        try {

            getActividadFacade().edit(ActividadActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "el estado del libro", "ha sido editado satisfactoriamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();

            return "/administrador/reservaActividad/editar/editar";
        } catch (Exception e) {
            addErrorMessage("Error Al Editar " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/administrador/reservaActividad/editar/editar";
        }
    }

    public void deleteActividad() {
        try {
            getActividadFacade().remove(ActividadActual);
            listaActividadActual.remove(ActividadActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "El Registro fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no Eliminado", "El Registro no fue eliminado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void delete() {
        try {
            getActividadFacade().remove(ActividadActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "El Registro fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no Eliminado", "El Registro no fue eliminado correctamente!!");
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

    @FacesConverter(forClass = Actividad.class)
    public static class ActividadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ActividadController controller = (ActividadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ActividadController");
            return controller.getActividad(getKey(value));
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
            if (object instanceof Actividad) {
                Actividad o = (Actividad) object;
                return getStringKey(o.getIdActividad());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Actividad.class.getName());
            }
        }

    }

}
