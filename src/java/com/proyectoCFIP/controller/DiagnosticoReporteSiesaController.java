/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.DiagnosticoReporteSiesa;
import com.proyectoCFIP.sessions.DiagnosticoReporteSiesaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class DiagnosticoReporteSiesaController {

    @EJB
    private DiagnosticoReporteSiesaFacade diagnosticoReporteSiesaFacade;
    private DiagnosticoReporteSiesa diagnosticoReporteActual;
    private List<DiagnosticoReporteSiesa> diagnosticoReporteSiesaList;
    
    public DiagnosticoReporteSiesaController() {
    }

    public DiagnosticoReporteSiesaFacade getDiagnosticoReporteSiesaFacade() {
        return diagnosticoReporteSiesaFacade;
    }

    public void setDiagnosticoReporteFacade(DiagnosticoReporteSiesaFacade diagnosticoReporteFacade) {
        this.diagnosticoReporteSiesaFacade = diagnosticoReporteFacade;
    }

    public DiagnosticoReporteSiesa getDiagnosticoReporteActual() {
        return diagnosticoReporteActual;
    }

    public void setDiagnosticoReporteActual(DiagnosticoReporteSiesa diagnosticoReporteActual) {
        this.diagnosticoReporteActual = diagnosticoReporteActual;
    }

    public List<DiagnosticoReporteSiesa> getDiagnosticoReporteSiesaList() {
        return diagnosticoReporteSiesaList = getDiagnosticoReporteSiesaFacade().findAll();
    }

    public void setDiagnosticoReporteSiesaList(List<DiagnosticoReporteSiesa> diagnosticoReporteSiesaList) {
        this.diagnosticoReporteSiesaList = diagnosticoReporteSiesaList;
    }
    
    public String prepareCreate(){
        return "";
    }
    public String prepareList(){
        return "/administrador/modSoporteIT/calendarioMantenimiento/diagnosticos/listaDiagnosticosERP";
    }
    public String add(){
        return "";
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
    
    public DiagnosticoReporteSiesa getDiagnosticoReporteSiesa(java.lang.Integer id) {
        return getDiagnosticoReporteSiesaFacade().find(id);
    }
    @FacesConverter(forClass = DiagnosticoReporteSiesa.class)
    public static class DiagnosticoReporteSiesaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DiagnosticoReporteSiesaController controller = (DiagnosticoReporteSiesaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "diagnosticoReporteSiesaController");
            return controller.getDiagnosticoReporteSiesa(getKey(value));
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
            if (object instanceof DiagnosticoReporteSiesa) {
                DiagnosticoReporteSiesa o = (DiagnosticoReporteSiesa) object;
                return getStringKey(o.getIdDiagnosticoReporteSiesa());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DiagnosticoReporteSiesa.class.getName());
            }
        }

    }
    
}
