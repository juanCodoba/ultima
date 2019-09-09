/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.AccionCorrectiva;
import com.proyectoCFIP.entities.AuAspectoEvaluar;
import com.proyectoCFIP.entities.AuPlanAuditoria;
import com.proyectoCFIP.entities.AuProcesoEvaluado;
import com.proyectoCFIP.entities.CalidadAccionImplementar;
import com.proyectoCFIP.entities.CalidadCausa;
import com.proyectoCFIP.entities.CalidadPlanAccion;
import com.proyectoCFIP.entities.CalidadSeguimientoAccion;
import com.proyectoCFIP.entities.CalidadSeguimientoCorrectiva;
import com.proyectoCFIP.entities.Subprocesos;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.AccionCorrectivaFacade;
import com.proyectoCFIP.sessions.AuAspectoEvaluarFacade;
import com.proyectoCFIP.sessions.AuPlanAuditoriaFacade;
import com.proyectoCFIP.sessions.AuProcesoEvaluadoFacade;
import com.proyectoCFIP.sessions.CalidadAccionImplementarFacade;
import com.proyectoCFIP.sessions.CalidadCausaFacade;
import com.proyectoCFIP.sessions.CalidadPlanAccionFacade;
import com.proyectoCFIP.sessions.CalidadSeguimientoAccionFacade;
import com.proyectoCFIP.sessions.CalidadSeguimientoCorrectivaFacade;
import com.proyectoCFIP.sessions.EmailSessionBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author Junior Cabal
 */
@Named(value = "calidadPlanAccionController")
@SessionScoped
public class CalidadPlanAccionController implements Serializable {

    @EJB
    private CalidadPlanAccionFacade planAccionFacade;
    @EJB
    private CalidadCausaFacade causaActualFacade;
    @EJB
    private CalidadSeguimientoAccionFacade seguimientoAccionActualFacade;
    @EJB
    private CalidadAccionImplementarFacade accionImplementarActualFacade;
    @EJB
    EmailSessionBean emailBean;
    @EJB
    private AccionCorrectivaFacade accionCorrectivaActualFacade;
    @EJB
    private CalidadSeguimientoCorrectivaFacade calidadSeguimientoCorrectivaFacade;

    private CalidadPlanAccion planAccionActual;
    private CalidadCausa causaActual;
    private CalidadSeguimientoAccion seguimientoAccionActual;
    private CalidadAccionImplementar accionImplementarActual;
    private Subprocesos suprocesoActual;

    private AccionCorrectiva accionCorrectivaActual;
    private List<AccionCorrectiva> accionCorrectivaList;
    private List<AccionCorrectiva> listaAccionCorrectivaActual;

    private CalidadSeguimientoCorrectiva calidadSeguimientoCorrectivaActual;

    private List<Subprocesos> listaSuprocesoActual;

    private List<CalidadPlanAccion> listaPlanAccionActual;
    private List<CalidadCausa> listaCausaActual;
    private List<CalidadSeguimientoAccion> listaSeguimientoAccionActual;
    private List<CalidadSeguimientoCorrectiva> listaSeguimientoSinCerrarCor;
    private List<CalidadSeguimientoCorrectiva> listaSeguimientoSinCerrarCorActual;

    private List<CalidadAccionImplementar> listaAccionImplementarActual;
    private List<CalidadSeguimientoAccion> listaSeguimientoSinCerrar;

    private Usuario usuarioActual;

    @EJB
    private AuPlanAuditoriaFacade planAuditoriaFacade;
    private AuPlanAuditoria planAuditoriaActual;
    private List<AuPlanAuditoria> listaPlanAuditoria;

    @EJB
    private AuProcesoEvaluadoFacade procesoEvaluadoFacade;
    private AuProcesoEvaluado procesoEvaluadoActual;
    private AuProcesoEvaluado procesoEvaluadoActualAspecto;
    private List<AuProcesoEvaluado> listaProcesoEvaluado;

    @EJB
    private AuAspectoEvaluarFacade aspectoEvaluarFacade;
    private AuAspectoEvaluar aspectoEvaluarActual;
    private List<AuAspectoEvaluar> listaAspectoEvaluar;
    private List<AuAspectoEvaluar> listaAspectoEvaluarTotal;

    public CalidadPlanAccionController() {
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

    public List<AccionCorrectiva> getListaAccionCorrectivaActual() {
        return listaAccionCorrectivaActual;
    }

    public void setListaAccionCorrectivaActual(List<AccionCorrectiva> listaAccionCorrectivaActual) {
        this.listaAccionCorrectivaActual = listaAccionCorrectivaActual;
    }

    public List<AccionCorrectiva> getAccionCorrectivaList() {
        return accionCorrectivaList;
    }

    public void setAccionCorrectivaList(List<AccionCorrectiva> accionCorrectivaList) {
        this.accionCorrectivaList = accionCorrectivaList;
    }

    public EmailSessionBean getEmailBean() {
        return emailBean;
    }

    public void setEmailBean(EmailSessionBean emailBean) {
        this.emailBean = emailBean;
    }

    public AccionCorrectiva getAccionCorrectivaActual() {
        return accionCorrectivaActual;
    }

    public void setAccionCorrectivaActual(AccionCorrectiva accionCorrectivaActual) {
        this.accionCorrectivaActual = accionCorrectivaActual;
    }

    public AccionCorrectivaFacade getAccionCorrectivaActualFacade() {
        return accionCorrectivaActualFacade;
    }

    public void setAccionCorrectivaActualFacade(AccionCorrectivaFacade accionCorrectivaActualFacade) {
        this.accionCorrectivaActualFacade = accionCorrectivaActualFacade;
    }

    public CalidadPlanAccionFacade getPlanAccionFacade() {
        return planAccionFacade;
    }

    public void setPlanAccionFacade(CalidadPlanAccionFacade planAccionFacade) {
        this.planAccionFacade = planAccionFacade;
    }

    public CalidadCausaFacade getCausaActualFacade() {
        return causaActualFacade;
    }

    public void setCausaActualFacade(CalidadCausaFacade causaActualFacade) {
        this.causaActualFacade = causaActualFacade;
    }

    public CalidadSeguimientoAccionFacade getSeguimientoAccionActualFacade() {
        return seguimientoAccionActualFacade;
    }

    public void setSeguimientoAccionActualFacade(CalidadSeguimientoAccionFacade seguimientoAccionActualFacade) {
        this.seguimientoAccionActualFacade = seguimientoAccionActualFacade;
    }

    public CalidadAccionImplementarFacade getAccionImplementarActualFacade() {
        return accionImplementarActualFacade;
    }

    public void setAccionImplementarActualFacade(CalidadAccionImplementarFacade accionImplementarActualFacade) {
        this.accionImplementarActualFacade = accionImplementarActualFacade;
    }

    public CalidadPlanAccion getPlanAccionActual() {
        return planAccionActual;
    }

    public void setPlanAccionActual(CalidadPlanAccion planAccionActual) {
        this.planAccionActual = planAccionActual;
    }

    public List<CalidadPlanAccion> getListaPlanAccionActualAdmin() {
        return listaPlanAccionActual = getPlanAccionFacade().ordenarAccion();
    }

    public List<CalidadPlanAccion> getListaPlanAccionActual() {
        return listaPlanAccionActual = getPlanAccionFacade().ordenarAccion();
    }

    public void setListaPlanAccionActual(List<CalidadPlanAccion> listaPlanAccionActual) {
        this.listaPlanAccionActual = listaPlanAccionActual;
    }

    public CalidadCausa getCausaActual() {
        return causaActual;
    }

    public void setCausaActual(CalidadCausa causaActual) {
        this.causaActual = causaActual;
    }

    public CalidadSeguimientoAccion getSeguimientoAccionActual() {
        return seguimientoAccionActual;
    }

    public void setSeguimientoAccionActual(CalidadSeguimientoAccion seguimientoAccionActual) {
        this.seguimientoAccionActual = seguimientoAccionActual;
    }

    public CalidadAccionImplementar getAccionImplementarActual() {
        return accionImplementarActual;
    }

    public void setAccionImplementarActual(CalidadAccionImplementar accionImplementarActual) {
        this.accionImplementarActual = accionImplementarActual;
    }

    public List<CalidadCausa> getListaCausaActual() {
        return listaCausaActual;
    }

    public void setListaCausaActual(List<CalidadCausa> listaCausaActual) {
        this.listaCausaActual = listaCausaActual;
    }

    public List<CalidadSeguimientoAccion> getListaSeguimientoAccionActual() {
        return listaSeguimientoAccionActual = getSeguimientoAccionActualFacade().consultaSeguimiento(accionImplementarActual);
    }

    public void setListaSeguimientoAccionActual(List<CalidadSeguimientoAccion> listaSeguimientoAccionActual) {
        this.listaSeguimientoAccionActual = listaSeguimientoAccionActual;
    }

    public List<CalidadAccionImplementar> getListaAccionImplementarActual() {
        return listaAccionImplementarActual;
    }

    public List<CalidadAccionImplementar> getListaAccionImplementarTotal() {
        return getAccionImplementarActualFacade().findAll();
    }

    public void setListaAccionImplementarActual(List<CalidadAccionImplementar> listaAccionImplementarActual) {
        this.listaAccionImplementarActual = listaAccionImplementarActual;
    }

    public List<CalidadSeguimientoCorrectiva> getListaSeguimientoSinCerrarCor() {
        return listaSeguimientoSinCerrarCor = getCalidadSeguimientoCorrectivaFacade().consultaSeguimientoSinCerrar();
    }

    public void setListaSeguimientoSinCerrarCor(List<CalidadSeguimientoCorrectiva> listaSeguimientoSinCerrarCor) {
        this.listaSeguimientoSinCerrarCor = listaSeguimientoSinCerrarCor;
    }

    public List<CalidadSeguimientoCorrectiva> getListaSeguimientoSinCerrarCorActual() {
        return listaSeguimientoSinCerrarCorActual = getCalidadSeguimientoCorrectivaFacade().consultaSeguimiento(accionCorrectivaActual);
    }

    public void setListaSeguimientoSinCerrarCorActual(List<CalidadSeguimientoCorrectiva> listaSeguimientoSinCerrarCorActual) {
        this.listaSeguimientoSinCerrarCorActual = listaSeguimientoSinCerrarCorActual;
    }

    public List<CalidadSeguimientoAccion> getListaSeguimientoSinCerrar() {
        return listaSeguimientoSinCerrar = getSeguimientoAccionActualFacade().consultaSeguimientoSinCerrar("abierto");
    }

    public void setListaSeguimientoSinCerrar(List<CalidadSeguimientoAccion> listaSeguimientoSinCerrar) {
        this.listaSeguimientoSinCerrar = listaSeguimientoSinCerrar;
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

    public void setListaPlanAuditoria(List<AuPlanAuditoria> listaPlanAuditoria) {
        this.listaPlanAuditoria = listaPlanAuditoria;
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

    public AuAspectoEvaluarFacade getAspectoEvaluarFacade() {
        return aspectoEvaluarFacade;
    }

    public void setAspectoEvaluarFacade(AuAspectoEvaluarFacade aspectoEvaluarFacade) {
        this.aspectoEvaluarFacade = aspectoEvaluarFacade;
    }

    public AuAspectoEvaluar getAspectoEvaluarActual() {
        return aspectoEvaluarActual;
    }

    public void setAspectoEvaluarActual(AuAspectoEvaluar aspectoEvaluarActual) {
        this.aspectoEvaluarActual = aspectoEvaluarActual;
    }

    public List<AuAspectoEvaluar> getListaAspectoEvaluar() {
        return listaAspectoEvaluar;
    }

    public void setListaAspectoEvaluar(List<AuAspectoEvaluar> listaAspectoEvaluar) {
        this.listaAspectoEvaluar = listaAspectoEvaluar;
    }

    public List<AuAspectoEvaluar> getListaAspectoEvaluarTotal() {
        return listaAspectoEvaluarTotal;
    }

    public void setListaAspectoEvaluarTotal(List<AuAspectoEvaluar> listaAspectoEvaluarTotal) {
        this.listaAspectoEvaluarTotal = listaAspectoEvaluarTotal;
    }

    public Subprocesos getSuprocesoActual() {
        return suprocesoActual;
    }

    public void setSuprocesoActual(Subprocesos suprocesoActual) {
        this.suprocesoActual = suprocesoActual;
    }

    public List<Subprocesos> getListaSuprocesoActual() {
        return listaSuprocesoActual;
    }

    public void setListaSuprocesoActual(List<Subprocesos> listaSuprocesoActual) {
        this.listaSuprocesoActual = listaSuprocesoActual;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public String prepareListaSeguimientos() {
        calidadSeguimientoCorrectivaActual = new CalidadSeguimientoCorrectiva();

        return "prueba";
    }

    public String prepareView() {
        listaAccionCorrectivaActual = planAccionActual.getAccionCorrectivaList();
        listaCausaActual = planAccionActual.getCalidadCausaList();
        listaAccionImplementarActual = planAccionActual.getCalidadAccionImplementarList();
        return "/usuario/modCalidad/accionMejora/verAccion";
    }

    public String prepareViewAdmin() {
        listaAccionCorrectivaActual = planAccionActual.getAccionCorrectivaList();
        listaCausaActual = planAccionActual.getCalidadCausaList();
        listaAccionImplementarActual = planAccionActual.getCalidadAccionImplementarList();
        return "/administrador/modCalidad/accionMejora/verAccion";
    }

    public String prepareCreate() {
        causaActual = new CalidadCausa();
        accionImplementarActual = new CalidadAccionImplementar();
        planAccionActual = new CalidadPlanAccion();
        accionCorrectivaActual = new AccionCorrectiva();
        listaAccionCorrectivaActual = new ArrayList<>();
        listaAccionImplementarActual = new ArrayList<>();
        listaCausaActual = new ArrayList<>();

        if (aspectoEvaluarActual != null) {
            planAccionActual.setIdSubproceso(aspectoEvaluarActual.getIdAuProcesoEvaluado().getIdSubproceso());
            planAccionActual.setResponsable(aspectoEvaluarActual.getIdAuProcesoEvaluado().getIdAuditado());
            planAccionActual.setIdAuAspectoEvaluar(aspectoEvaluarActual);
            planAccionActual.setIdPlanAuditoria(planAuditoriaActual);
            planAccionActual.setOrigenAccion(planAuditoriaActual.getTipoAuditoria());
        }
        return "/usuario/modCalidad/accionMejora/crearAccion";
    }

    public String prepareCreateAuditoria() {
        causaActual = new CalidadCausa();
        accionImplementarActual = new CalidadAccionImplementar();
        planAccionActual = new CalidadPlanAccion();
        listaAccionImplementarActual = new ArrayList<>();
        listaCausaActual = new ArrayList<>();
        accionCorrectivaActual = new AccionCorrectiva();

        if (aspectoEvaluarActual != null) {
            planAccionActual.setIdSubproceso(aspectoEvaluarActual.getIdAuProcesoEvaluado().getIdSubproceso());
            planAccionActual.setResponsable(aspectoEvaluarActual.getIdAuProcesoEvaluado().getIdAuditado());
            planAccionActual.setIdAuAspectoEvaluar(aspectoEvaluarActual);
            planAccionActual.setIdPlanAuditoria(planAuditoriaActual);
            planAccionActual.setOrigenAccion(planAuditoriaActual.getTipoAuditoria());
        }
        return "/administrador/modAuditoria/planAuditoria/crearAccion";
    }

    public String prepareEdit() {
        causaActual = new CalidadCausa();
        accionImplementarActual = new CalidadAccionImplementar();
        calidadSeguimientoCorrectivaActual = new CalidadSeguimientoCorrectiva();
        listaAccionCorrectivaActual = planAccionActual.getAccionCorrectivaList();
        listaCausaActual = planAccionActual.getCalidadCausaList();
        listaAccionImplementarActual = planAccionActual.getCalidadAccionImplementarList();
        return "/administrador/modCalidad/accionMejora/editarAccion";
    }

    public String prepareAdminEdit() {
        causaActual = new CalidadCausa();
        suprocesoActual = new Subprocesos();
        accionImplementarActual = new CalidadAccionImplementar();
        calidadSeguimientoCorrectivaActual = new CalidadSeguimientoCorrectiva();
        accionCorrectivaActual = new AccionCorrectiva();
        listaAccionCorrectivaActual = planAccionActual.getAccionCorrectivaList();
        listaCausaActual = planAccionActual.getCalidadCausaList();
        listaAccionImplementarActual = planAccionActual.getCalidadAccionImplementarList();
        return "/administrador/modCalidad/accionMejora/adminAccion";
    }

    public String list() {
        return "/usuario/modCalidad/accionMejora/listaAccion";
    }

    public String listAdmin() {
        return "/administrador/modCalidad/accionMejora/listaAccion";
    }

    public String view() {
        return "";
    }

    public String add() {
        try {

            if (listaCausaActual.isEmpty()) {
                addErrorMessage("Plan de acción no creado", "No contiene causas");
                return null;
            }
            if (listaAccionImplementarActual.isEmpty()) {
                addErrorMessage("Plan de acción no creado", "No contiene acciones a implementar");
                return null;
//            }
//            if (listaAccionCorrectivaActual.isEmpty()) {
//                addErrorMessage("Plan accion no creado", "No contiene acciones correctivas");
//                return null;

//            } else if (planAccionActual.getOrigenAccion().matches("Auditoria")) {
//                addErrorMessage("Plan accion no creado", "Obligatorio para correcciones de auditoria");
//                return null;
            } else {
                planAccionActual.setResponsable(usuarioActual);
                planAccionActual.setEstadoPlan("abierto");
                planAccionActual.setFechaAnalisis(new Date());
                planAccionActual.setCalidadCausaList(listaCausaActual);
                planAccionActual.setCalidadAccionImplementarList(listaAccionImplementarActual);
                planAccionActual.setAccionCorrectivaList(listaAccionCorrectivaActual);
                getPlanAccionFacade().create(planAccionActual);
                addSuccessMessage("Plan de acción creado", "El plan de acción fue creado con consecutivo No. " + planAccionActual.getIdPlanAccion());
                sendMailRegistroTec();
                sendMailRegistroUser();
                return "/usuario/modCalidad/accionMejora/listaAccion";
            }
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String addAuditoria() {
        try {
            if (listaCausaActual.isEmpty()) {
                addErrorMessage("Plan de acción no creado", "No contiene causas");
                return null;
            }
            if (listaAccionImplementarActual.isEmpty()) {
                addErrorMessage("Plan de acción no creado", "No contiene acciones a implementar");
                return null;
            } else {
                planAccionActual.setEstadoPlan("abierto");
                planAccionActual.setFechaAnalisis(new Date());
                planAccionActual.setCalidadCausaList(listaCausaActual);
                planAccionActual.setCalidadAccionImplementarList(listaAccionImplementarActual);
                getPlanAccionFacade().create(planAccionActual);
                addSuccessMessage("Plan de acción creado", "El plan de acción fue creado con consecutivo No. " + planAccionActual.getIdPlanAccion());
                sendMailRegistroTec();
                sendMailRegistroUser();
                return "/usuario/modCalidad/accionMejora/listaAccion";
            }
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String delete() {
        return "";
    }

    public String adminUpdate() {
        try {
            planAccionActual.setFechaCierre(new Date());
            planAccionActual.setRevisor(usuarioActual.toString());
            getPlanAccionFacade().edit(planAccionActual);
            addSuccessMessage("Plan de acción editado", "El plan de acción de mejora fue editado con numero No. " + planAccionActual.getIdPlanAccion());
            return "/administrador/modCalidad/accionMejora/listaAccion";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String update() {
        try {
            if (listaCausaActual.isEmpty()) {
                addErrorMessage("Plan de acción no creado", "No contiene causas");
                return null;
            }
            if (listaAccionImplementarActual.isEmpty()) {
                addErrorMessage("Plan de acción no creado", "No contiene acciones a implementar");
                return null;
            } else {
                planAccionActual.setCalidadCausaList(listaCausaActual);
                planAccionActual.setCalidadAccionImplementarList(listaAccionImplementarActual);
                planAccionActual.setAccionCorrectivaList(listaAccionCorrectivaActual);

                getPlanAccionFacade().edit(planAccionActual);
                addSuccessMessage("Plan de acción editado", "El plan de acción de mejora fue editado con numero No. " + planAccionActual.getIdPlanAccion());
                return "/administrador/modCalidad/accionMejora/listaAccion";
            }
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public void addCausa() {
        try {
            getCausaActualFacade().create(causaActual);
            listaCausaActual.add(causaActual);
            causaActual = new CalidadCausa();
            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void prepareSeguimiento() {
        seguimientoAccionActual = new CalidadSeguimientoAccion();
    }

    public void prepareSeguimientoAcCo() {
        calidadSeguimientoCorrectivaActual = new CalidadSeguimientoCorrectiva();
    }

    public void listSeguimiento() {
        listaSeguimientoAccionActual = new ArrayList<>();
    }

    public void listSeguimientoAc() {
        listaSeguimientoSinCerrarCorActual = new ArrayList<>();
    }

    public String prepareListaSeguimientoSinCerrar() {
        return "listaSeguimientos";
    }

    public String prepareListaSeguimientoSinCerrarCo() {
        return "listaSeguimientosCorrectiva";
    }

    public String prepareListaAccionesImplemetar() {
        return "listaAccionesImplementar";
    }

    public void addAccionCorrectiva() {
        try {
            getAccionCorrectivaActualFacade().create(accionCorrectivaActual);
            listaAccionCorrectivaActual.add(accionCorrectivaActual);
            accionCorrectivaActual = new AccionCorrectiva();

            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void addAccionCorrectivaList(CalidadPlanAccion calidadPlanAccionActual) {
        try {
            getAccionCorrectivaActualFacade().edit(accionCorrectivaActual);
            listaAccionCorrectivaActual.add(accionCorrectivaActual);
            accionCorrectivaActual = new AccionCorrectiva();

            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void updateAccionCorrectiva() {
        try {
            getAccionCorrectivaActualFacade().edit(accionCorrectivaActual);
            accionCorrectivaActual = new AccionCorrectiva();
            addSuccessMessage("la accion fue editada", "");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void addSeguimiento() {
        try {
            getSeguimientoAccionActual().setIdAccionImplementar(accionImplementarActual);
            getSeguimientoAccionActualFacade().create(seguimientoAccionActual);
            seguimientoAccionActual = new CalidadSeguimientoAccion();
            addSuccessMessage("Seguimiento creado", "El seguimiento fue creado");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
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

    public void updateSeguimiento() {
        try {
            getSeguimientoAccionActualFacade().edit(seguimientoAccionActual);
            seguimientoAccionActual = new CalidadSeguimientoAccion();
            addSuccessMessage("Seguimiento editado", "El seguimiento fue editado");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void updateSeguimientoCorr() {
        try {
            getCalidadSeguimientoCorrectivaFacade().edit(calidadSeguimientoCorrectivaActual);
            calidadSeguimientoCorrectivaActual = new CalidadSeguimientoCorrectiva();
            addSuccessMessage("Seguimiento editado", "El seguimiento fue editado");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void deleteCausa() {
        try {
            listaCausaActual.remove(causaActual);
            addSuccessMessage("Causa eliminada", "La causa fue eliminada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void addAccion() {
        try {
            getAccionImplementarActualFacade().create(accionImplementarActual);
            listaAccionImplementarActual.add(accionImplementarActual);
            accionImplementarActual = new CalidadAccionImplementar();
            addSuccessMessage("Acción creada", "La acción fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void deleteAccion() {
        try {
            listaAccionImplementarActual.remove(accionImplementarActual);
            addSuccessMessage("Acción eliminada", "La acción fue eliminada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void deleteAccionCorretiva() {
        try {
            listaAccionCorrectivaActual.remove(accionCorrectivaActual);
            addSuccessMessage("Acción eliminada", "La acción fue eliminada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void deleteSeguimiento() {
        try {
            listaSeguimientoAccionActual.remove(seguimientoAccionActual);
            addSuccessMessage("Seguimiento eliminado", "El seguimiento fue eliminado");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void deleteSeguimientoCorrectivo() {
        try {
            listaSeguimientoSinCerrarCorActual.remove(calidadSeguimientoCorrectivaActual);
            addSuccessMessage("Seguimiento eliminado", "El seguimiento fue eliminado");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    //Envio de correo
    private void sendMail(String to, String subject, String body) {
        try {
            emailBean.sendMail(to, subject, body);
            JsfUtil.addSuccessMessage("Mensaje Enviado Exitosamente");
        } catch (NamingException | MessagingException ex) {
            JsfUtil.addErrorMessage("Error sending message " + ex.getClass().getName());
        }
    }

    private void sendMailRegistroTec() {
        String subject = " PLAN DE ACCIÓN CREADO CON CONSECUTIVO No." + planAccionActual.getIdPlanAccion();
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CONSECUTIVO No. ");
        mensaje.append(planAccionActual.getIdPlanAccion());
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(planAccionActual.getResponsable().toString().toUpperCase());
        mensaje.append("\nSUBPROCESO: ");
        mensaje.append(planAccionActual.getIdSubproceso().getNombreSubproceso().toUpperCase());
        mensaje.append("\nNOMBRE DEL PLAN DE ACCIÓN: ");
        mensaje.append(planAccionActual.getTituloPlan().toUpperCase());
        mensaje.append("\nFECHA DEL ANALISIS: ");
        mensaje.append(planAccionActual.getFechaAnalisis().toLocaleString());
        mensaje.append("\nORIGEN DEL PLAN DE ACCIÓN : ");
        mensaje.append(planAccionActual.getOrigenAccion().toUpperCase());
        mensaje.append("\nTIPO DE ACCIÓN : ");
        mensaje.append(planAccionActual.getTipoPlan().toUpperCase());
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail("juan.cordoba@cfiprovidencia.com " + " " + " claudia.canar@cfiprovidencia.com " + " " + " nathalia.yusti@cfiprovidencia.com " + " " + "vivian.calero@cfiprovidencia.com", subject, mensaje.toString());
    }

    private void sendMailRegistroUser() {
        String subject = "SU PLAN DE ACCIÓN FUE CREADO CON CONSECUTIVO No." + planAccionActual.getIdPlanAccion();
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CONSECUTIVO No. ");
        mensaje.append(planAccionActual.getIdPlanAccion());
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(planAccionActual.getResponsable().toString().toUpperCase());
        mensaje.append("\nSUBPROCESO: ");
        mensaje.append(planAccionActual.getIdSubproceso().getNombreSubproceso().toUpperCase());
        mensaje.append("\nNOMBRE DEL PLAN DE ACCIÓN: ");
        mensaje.append(planAccionActual.getTituloPlan().toUpperCase());
        mensaje.append("\nFECHA DEL ANALISIS: ");
        mensaje.append(planAccionActual.getFechaAnalisis().toLocaleString());
        mensaje.append("\nORIGEN DEL PLAN DE ACCIÓN : ");
        mensaje.append(planAccionActual.getOrigenAccion().toUpperCase());
        mensaje.append("\nTIPO DE ACCIÓN : ");
        mensaje.append(planAccionActual.getTipoPlan().toUpperCase());
        mensaje.append("\n\nPRONTO UNO DE NUESTROS FUNCIONARIOS ESTARA REALIZANDO LOS SEGUIMIENTOS RESPECTIVOS.");
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail(planAccionActual.getResponsable().getCorreoUsuario(), subject, mensaje.toString());
    }

    public void onRowToggle(ToggleEvent event) {
        seguimientoAccionActual = (CalidadSeguimientoAccion) event.getComponent().getAttributes().get("item");
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

    public CalidadPlanAccion getCalidadPlanAccion(java.lang.Integer id) {
        return getPlanAccionFacade().find(id);
    }

    @FacesConverter(forClass = CalidadPlanAccion.class)
    public static class CalidadPlanAccionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CalidadPlanAccionController controller = (CalidadPlanAccionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "calidadPlanAccionController");
            return controller.getCalidadPlanAccion(getKey(value));
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
            if (object instanceof CalidadPlanAccion) {
                CalidadPlanAccion o = (CalidadPlanAccion) object;
                return getStringKey(o.getIdPlanAccion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CalidadPlanAccion.class.getName());
            }
        }
    }
}
