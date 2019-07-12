/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.AccionCorrectiva;
import com.proyectoCFIP.entities.CalidadPlanAccion;
import com.proyectoCFIP.entities.CalidadSeguimientoCorrectiva;
import com.proyectoCFIP.sessions.CalidadSeguimientoCorrectivaFacade;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Junior Cabal
 */
@Named(value = "calidadSeguimientoController")@SessionScoped
public class calidadSeguimientoController implements Serializable {

    private CalidadSeguimientoCorrectiva calidadSeguimientoCorrectivaActual;

    private List<CalidadSeguimientoCorrectiva> listCalidadSeguimientoCorrectiva;
    private CalidadPlanAccion planAccionActual;

    @EJB
    private CalidadSeguimientoCorrectivaFacade calidadSeguimientoCorrectivaFacade;

    private AccionCorrectiva accionCorrectivaActual;

    public calidadSeguimientoController() {
    }

    public CalidadPlanAccion getPlanAccionActual() {
        return planAccionActual;
    }

    public void setPlanAccionActual(CalidadPlanAccion planAccionActual) {
        this.planAccionActual = planAccionActual;
    }

    public AccionCorrectiva getAccionCorrectivaActual() {
        return accionCorrectivaActual;
    }

    public void setAccionCorrectivaActual(AccionCorrectiva accionCorrectivaActual) {
        this.accionCorrectivaActual = accionCorrectivaActual;
    }

    public CalidadSeguimientoCorrectiva getCalidadSeguimientoCorrectivaActual() {
        return calidadSeguimientoCorrectivaActual;
    }

    public void setCalidadSeguimientoCorrectivaActual(CalidadSeguimientoCorrectiva calidadSeguimientoCorrectivaActual) {
        this.calidadSeguimientoCorrectivaActual = calidadSeguimientoCorrectivaActual;
    }

    public CalidadSeguimientoCorrectivaFacade getCalidadSeguimientoCorrectivaFacade() {
        return calidadSeguimientoCorrectivaFacade;
    }

    public void setCalidadSeguimientoCorrectivaFacade(CalidadSeguimientoCorrectivaFacade calidadSeguimientoCorrectivaFacade) {
        this.calidadSeguimientoCorrectivaFacade = calidadSeguimientoCorrectivaFacade;
    }

    public List<CalidadSeguimientoCorrectiva> getListCalidadSeguimientoCorrectiva() {
        return listCalidadSeguimientoCorrectiva;
    }

    public void setListCalidadSeguimientoCorrectiva(List<CalidadSeguimientoCorrectiva> listCalidadSeguimientoCorrectiva) {
        this.listCalidadSeguimientoCorrectiva = listCalidadSeguimientoCorrectiva;
    }

    public void prepareSeguimientoAcCo() {
        calidadSeguimientoCorrectivaActual = new CalidadSeguimientoCorrectiva();
    }

    public void listSeguimiento() {
        listCalidadSeguimientoCorrectiva = new ArrayList<>();
    }

    public String prepareListaSeguimientos() {
        calidadSeguimientoCorrectivaActual = new CalidadSeguimientoCorrectiva();

        return "prueba";
    }

    public void addSeguimientoCorre() {
        try {
            getCalidadSeguimientoCorrectivaActual().setIdaccionCorrectiva(accionCorrectivaActual);
            getCalidadSeguimientoCorrectivaFacade().create(calidadSeguimientoCorrectivaActual);
            calidadSeguimientoCorrectivaActual = new CalidadSeguimientoCorrectiva();
            addSuccessMessage("Seguimiento creado", "El seguimiento fue creado");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
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

    public CalidadSeguimientoCorrectiva getCalidadSeguimientoCorrectiva(java.lang.Integer id) {
        return getCalidadSeguimientoCorrectivaFacade().find(id);
    }

    @FacesConverter(forClass = CalidadSeguimientoCorrectiva.class)
    public static class CalidadSeguimientoCorrectivaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            calidadSeguimientoController controller = (calidadSeguimientoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "calidadSeguimientoController");
            return controller.getCalidadSeguimientoCorrectiva(getKey(value));
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
            if (object instanceof CalidadSeguimientoCorrectiva) {
                CalidadSeguimientoCorrectiva o = (CalidadSeguimientoCorrectiva) object;
                return getStringKey(o.getIdCalidadSeguimientoCorrectiva());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CalidadSeguimientoCorrectiva.class.getName());
            }
        }
    }
}
