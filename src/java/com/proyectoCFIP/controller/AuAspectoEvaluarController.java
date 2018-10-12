/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.AuAspectoEvaluar;
import com.proyectoCFIP.entities.AuPlanAuditoria;
import com.proyectoCFIP.entities.AuProcesoEvaluado;
import com.proyectoCFIP.sessions.AuAspectoEvaluarFacade;
import com.proyectoCFIP.sessions.AuPlanAuditoriaFacade;
import com.proyectoCFIP.sessions.AuProcesoEvaluadoFacade;
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
@Named(value = "AuAspectoEvaluarController")
@SessionScoped
public class AuAspectoEvaluarController implements Serializable {

    @EJB
    private AuAspectoEvaluarFacade auAspectoEvaluarFacade;
    private AuAspectoEvaluar auAspectoEvaluarActual;
    private List<AuAspectoEvaluar> listaauAspectoEvaluar;
    
         @EJB
    private AuPlanAuditoriaFacade planAuditoriaFacade;
    private AuPlanAuditoria planAuditoriaActual;
    private List<AuPlanAuditoria> listaPlanAuditoria;
    
        @EJB
    private AuProcesoEvaluadoFacade procesoEvaluadoFacade;
    private AuProcesoEvaluado procesoEvaluadoActual;
    private AuProcesoEvaluado procesoEvaluadoActualAspecto;
    private List<AuProcesoEvaluado> listaProcesoEvaluado;
    
    public AuAspectoEvaluarController() {
    }

    public AuAspectoEvaluarFacade getauAspectoEvaluarFacade() {
        return auAspectoEvaluarFacade;
    }

    public void setauAspectoEvaluarFacade(AuAspectoEvaluarFacade auAspectoEvaluarFacade) {
        this.auAspectoEvaluarFacade = auAspectoEvaluarFacade;
    }

    public AuAspectoEvaluar getauAspectoEvaluarActual() {
        return auAspectoEvaluarActual;
    }

    public void setauAspectoEvaluarActual(AuAspectoEvaluar auAspectoEvaluarActual) {
        this.auAspectoEvaluarActual = auAspectoEvaluarActual;
    }

    public List<AuAspectoEvaluar> getListaauAspectoEvaluar() {
        return listaauAspectoEvaluar =  getauAspectoEvaluarFacade().findAll();
    }

    public void setListaauAspectoEvaluar(List<AuAspectoEvaluar> listaauAspectoEvaluar) {
        this.listaauAspectoEvaluar = listaauAspectoEvaluar;
    }
    public AuAspectoEvaluar getAuAspectoEvaluar(java.lang.Integer id) {
        return getauAspectoEvaluarFacade().find(id);
    }

    public AuAspectoEvaluarFacade getAuAspectoEvaluarFacade() {
        return auAspectoEvaluarFacade;
    }

    public void setAuAspectoEvaluarFacade(AuAspectoEvaluarFacade auAspectoEvaluarFacade) {
        this.auAspectoEvaluarFacade = auAspectoEvaluarFacade;
    }

    public AuAspectoEvaluar getAuAspectoEvaluarActual() {
        return auAspectoEvaluarActual;
    }

    public void setAuAspectoEvaluarActual(AuAspectoEvaluar auAspectoEvaluarActual) {
        this.auAspectoEvaluarActual = auAspectoEvaluarActual;
    }

    public AuPlanAuditoriaFacade getPlanAuditoriaFacade() {
        return planAuditoriaFacade;
    }

    public void setPlanAuditoriaFacade(AuPlanAuditoriaFacade planAuditoriaFacade) {
        this.planAuditoriaFacade = planAuditoriaFacade;
    }

    public AuPlanAuditoria getPlanAuditoriaActual() {
        return planAuditoriaActual;
    }

    public void setPlanAuditoriaActual(AuPlanAuditoria planAuditoriaActual) {
        this.planAuditoriaActual = planAuditoriaActual;
    }

    public List<AuPlanAuditoria> getListaPlanAuditoria() {
        return listaPlanAuditoria;
    }

    public AuProcesoEvaluadoFacade getProcesoEvaluadoFacade() {
        return procesoEvaluadoFacade;
    }

    public void setProcesoEvaluadoFacade(AuProcesoEvaluadoFacade procesoEvaluadoFacade) {
        this.procesoEvaluadoFacade = procesoEvaluadoFacade;
    }

    public AuProcesoEvaluado getProcesoEvaluadoActual() {
        return procesoEvaluadoActual;
    }

    public void setProcesoEvaluadoActual(AuProcesoEvaluado procesoEvaluadoActual) {
        this.procesoEvaluadoActual = procesoEvaluadoActual;
    }

    public AuProcesoEvaluado getProcesoEvaluadoActualAspecto() {
        return procesoEvaluadoActualAspecto;
    }

    public void setProcesoEvaluadoActualAspecto(AuProcesoEvaluado procesoEvaluadoActualAspecto) {
        this.procesoEvaluadoActualAspecto = procesoEvaluadoActualAspecto;
    }

    public List<AuProcesoEvaluado> getListaProcesoEvaluado() {
        return listaProcesoEvaluado;
    }

    public void setListaProcesoEvaluado(List<AuProcesoEvaluado> listaProcesoEvaluado) {
        this.listaProcesoEvaluado = listaProcesoEvaluado;
    }
    
    
    
    
    
    
    
    
    

    public void setListaPlanAuditoria(List<AuPlanAuditoria> listaPlanAuditoria) {
        this.listaPlanAuditoria = listaPlanAuditoria;
    }
    
        public String prepareVievAspectoEvaluar1() {
                    recargarLista();

        return "/administrador/modAuditoria/AspectoEvaluar/planAuditoria/crear";
    }
    
    private void recargarLista() {
        listaauAspectoEvaluar = null;
    }



    
    
    
    
    public String prepareCreate() {
        auAspectoEvaluarActual = new AuAspectoEvaluar();
        return "crear";
    }

    public String prepareEdit() {
        return "editar";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modAuditoria/auAspectoEvaluar/lista";
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
    
    @FacesConverter(forClass = AuAspectoEvaluar.class)
    public static class AuAspectoEvaluarControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AuAspectoEvaluarController controller = (AuAspectoEvaluarController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "AuAspectoEvaluarController");
            return controller.getAuAspectoEvaluar(getKey(value));
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
            if (object instanceof AuAspectoEvaluar) {
                AuAspectoEvaluar o = (AuAspectoEvaluar) object;
                return getStringKey(o.getIdAuAspectoEvaluar());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + AuAspectoEvaluar.class.getName());
            }
        }
    }
}
