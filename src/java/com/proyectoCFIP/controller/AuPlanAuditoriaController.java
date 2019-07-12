/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.AuAspectoEvaluar;
import com.proyectoCFIP.entities.AuPeriodoPlanAuditoria;
import com.proyectoCFIP.entities.AuPlanAuditoria;
import com.proyectoCFIP.entities.AuProcesoEvaluado;
import com.proyectoCFIP.entities.TipoAuditoria;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.AuAspectoEvaluarFacade;
import com.proyectoCFIP.sessions.AuPlanAuditoriaFacade;
import com.proyectoCFIP.sessions.AuProcesoEvaluadoFacade;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.UsuarioFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;
import javax.naming.NamingException;

import org.primefaces.context.RequestContext;

/**
 *
 * @author junio
 */
@Named(value = "auPlanAuditoriaController")
@SessionScoped
public class AuPlanAuditoriaController implements Serializable {

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

    @EJB
    private UsuarioFacade usuarioFacade;
    private Usuario usuarioActual;
    private List<Usuario> listaUsuario;

    @EJB
    EmailSessionBean emailBean;

    private String tipoAuditoria;

    private AuPeriodoPlanAuditoria periodoPlanAuditoriaActual;

    public AuPlanAuditoriaController() {
    }

    public boolean isExterna() {
        return planAuditoriaActual.getIdTipoAuditoria() == null ? false
                : planAuditoriaActual.getIdTipoAuditoria().getIdTipoAuditoria() == (short) 1;
    }

    public boolean isInterna() {
        return planAuditoriaActual.getIdTipoAuditoria() == null ? false
                : planAuditoriaActual.getIdTipoAuditoria().getIdTipoAuditoria() == (short) 2 ? false
                        : planAuditoriaActual.getIdTipoAuditoria().getIdTipoAuditoria() == (short) 3;
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
        return listaPlanAuditoria = getPlanAuditoriaFacade().findAll();
    }

    public List<AuPlanAuditoria> getListaPlanAuditoriaAnio() {
        return listaPlanAuditoria = getPlanAuditoriaFacade().consultaPlanAuditoriaAnio(periodoPlanAuditoriaActual);
    }

    public List<AuPlanAuditoria> getListaPlanAuditoriaControl() {
        return listaPlanAuditoria = getPlanAuditoriaFacade().consultaTipoPlanAuditoria();
    }

    public List<AuPlanAuditoria> getListaPlanAuditoriaCalidad() {
        return listaPlanAuditoria = getPlanAuditoriaFacade().consultaTipoPlanAuditoriaCalidad();
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

    public AuProcesoEvaluado getProcesoEvaluadoActualAspecto() {
        return procesoEvaluadoActualAspecto;
    }

    public void setProcesoEvaluadoActualAspecto(AuProcesoEvaluado procesoEvaluadoActualAspecto) {
        this.procesoEvaluadoActualAspecto = procesoEvaluadoActualAspecto;
    }

    public void setProcesoEvaluadoActual(AuProcesoEvaluado procesoEvaluadoActual) {
        this.procesoEvaluadoActual = procesoEvaluadoActual;
    }

    public List<AuProcesoEvaluado> getListaProcesoEvaluado() {
        return listaProcesoEvaluado;
    }

    public List<AuProcesoEvaluado> getListaProcesoEvaluadoConsulta() {
        return listaProcesoEvaluado = getProcesoEvaluadoFacade().consultaProcesoPlan(planAuditoriaActual);
    }

    public void setListaProcesoEvaluado(List<AuProcesoEvaluado> listaProcesoEvaluado) {
        this.listaProcesoEvaluado = listaProcesoEvaluado;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getTipoAuditoria() {
        return tipoAuditoria;
    }

    public void setTipoAuditoria(String tipoAuditoria) {
        this.tipoAuditoria = tipoAuditoria;
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

    public List<AuAspectoEvaluar> getListaAspectoEvaluarProceso() {
        listaAspectoEvaluar = new ArrayList<>();
        if (getAspectoEvaluarFacade().consultaProceso(procesoEvaluadoActualAspecto) == null) {
            return listaAspectoEvaluar;
        } else {
            return listaAspectoEvaluar = getAspectoEvaluarFacade().consultaProceso(procesoEvaluadoActualAspecto);
        }
    }

    public void prepareDocumento(ActionEvent event) {
        procesoEvaluadoActual = new AuProcesoEvaluado();
        procesoEvaluadoActual = (AuProcesoEvaluado) event.getComponent().getAttributes().get("item");
    }

    public void setListaAspectoEvaluar(List<AuAspectoEvaluar> listaAspectoEvaluar) {
        this.listaAspectoEvaluar = listaAspectoEvaluar;
    }

    public void recargarLista() {
        listaPlanAuditoria = null;
    }

    public AuPeriodoPlanAuditoria getPeriodoPlanAuditoriaActual() {
        return periodoPlanAuditoriaActual;
    }

    public void setPeriodoPlanAuditoriaActual(AuPeriodoPlanAuditoria periodoPlanAuditoriaActual) {
        this.periodoPlanAuditoriaActual = periodoPlanAuditoriaActual;
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

    public String prepareCreate() {
        listaAspectoEvaluarTotal = new ArrayList<>();
        listaAspectoEvaluar = new ArrayList<>();
        listaProcesoEvaluado = new ArrayList<>();
        planAuditoriaActual = new AuPlanAuditoria();
        procesoEvaluadoActual = new AuProcesoEvaluado();
        procesoEvaluadoActualAspecto = new AuProcesoEvaluado();
        return "/administrador/modAuditoria/planAuditoria/crear";
    }

    public String prepareCreateAuInterna() {
        listaAspectoEvaluarTotal = new ArrayList<>();
        listaAspectoEvaluar = new ArrayList<>();
        listaProcesoEvaluado = new ArrayList<>();
        planAuditoriaActual = new AuPlanAuditoria();
        procesoEvaluadoActual = new AuProcesoEvaluado();
        procesoEvaluadoActualAspecto = new AuProcesoEvaluado();
        return "/administrador/modAuditoria/planAuditoria/crearAuInterna";
    }

    public String prepareView() {
        recargarLista();

        return "/administrador/modAuditoria/planAuditoria/ver";
    }

    public String prepareViewExterna() {
        recargarLista();

        return "/administrador/modAuditoria/planAuditoria/ver2";
    }

    public String prepareViewAuInterna() {
        recargarLista();

        return "/administrador/modAuditoria/planAuditoria/verAuInterna";
    }

    public String prepareVievAspectoEvaluar() {
        recargarLista();

        return "/administrador/modAuditoria/planAuditoria/crear";
    }

    public void prepareAspecto() {
        recargarLista();
        aspectoEvaluarActual = new AuAspectoEvaluar();
    }

    public String add() {
        try {
            planAuditoriaActual.setIdUsuario(usuarioActual);
            planAuditoriaActual.setFecha(new Date());
            planAuditoriaActual.setEstado("Desarrollo");

            getPlanAuditoriaFacade().create(planAuditoriaActual);

            for (AuProcesoEvaluado items : listaProcesoEvaluado) {
                items.setIdPlanAuditoria(planAuditoriaActual);
                items.setEstado(true);
                getProcesoEvaluadoFacade().edit(items);
            }
            for (AuAspectoEvaluar items2 : listaAspectoEvaluarTotal) {
                items2.setEstado("Sin auditar");
                getAspectoEvaluarFacade().edit(items2);
            }
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Auditoria Generada  ", "La Auditoria fue generada correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "/usuario/modAuditoria/planAuditoria/lista";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo completar la acción", "");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "";
        }
    }

    public void addProcesoAuditar() {
        try {
            procesoEvaluadoActual.setEstado(false);
            listaProcesoEvaluado.add(procesoEvaluadoActual);
            getProcesoEvaluadoFacade().create(procesoEvaluadoActual);
            procesoEvaluadoActual = new AuProcesoEvaluado();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Auditoria Generada  ", "Elsubroceso a auditar se a generado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            sendMailAdd3();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void deleteProcesoAuditar() {
        try {
            listaProcesoEvaluado.remove(procesoEvaluadoActual);
            getProcesoEvaluadoFacade().remove(procesoEvaluadoActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "El Registro fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }

    public void addAspectoEvaluar() {
        try {
            aspectoEvaluarActual.setIdAuProcesoEvaluado(procesoEvaluadoActualAspecto);
            aspectoEvaluarActual.setEstado("creacion");
            getAspectoEvaluarFacade().create(aspectoEvaluarActual);

            addSuccessMessage("Item guardado", "Proceso a evaluar creado");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void editarEstadoAspectoEvaluar() {
        try {
            if (aspectoEvaluarActual.getCumplimiento().equals("CP")
                    || aspectoEvaluarActual.getCumplimiento().equals("NC")
                    || aspectoEvaluarActual.getCumplimiento().equals("Oportunidad de mejora")
                    || aspectoEvaluarActual.getCumplimiento().equals("Con exepción")) {
                aspectoEvaluarActual.setEstado("SIN ACCIÓN DE MEJORA");
            } else {
                aspectoEvaluarActual.setEstado("CERRADO");
            }
            getAspectoEvaluarFacade().edit(aspectoEvaluarActual);
            addSuccessMessage("Item guardado", "Proceso a evaluar creado");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void addConclusionesProcesos() {
        try {
            procesoEvaluadoFacade.edit(procesoEvaluadoActualAspecto);
            addSuccessMessage("Item guardado", "Proceso a evaluar creado");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void addApectos() {
        try {
            for (AuAspectoEvaluar items : listaAspectoEvaluar) {
                items.setIdAuProcesoEvaluado(procesoEvaluadoActual);
                getAspectoEvaluarFacade().create(items);
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo completar la acción", "");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public String edit() {
        try {

            recargarLista();
            return "/usuario/modProyectos/bancoIdea/listaBancoIdea";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo completar la acción", "");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "";
        }
    }

    public String prepareList() {
        return "";
    }

    public String prepareEdit() {
        return "/administrador/modAuditoria/planAuditoria/editar";
    }

    public void prepareConclusionesProceso() {
        procesoEvaluadoActualAspecto = new AuProcesoEvaluado();
    }

    public String prepareEditEstado() {
        try {
            //planAuditoriaActual.setEstado("TERMINADA");
            getPlanAuditoriaFacade().edit(planAuditoriaActual);
            recargarLista();
            return "/usuario/modAuditoria/planAuditoria/lista";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo completar la acción", "");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "";
        }
    }

    public String preparePlanAuditoriaAnual() {
        return "planAuditoriaAnual";
    }

    public void obtenerEvidencia() throws IOException {
        if (aspectoEvaluarActual.getEvidencia() == null) {
            addErrorMessage("Documento sin acceso", "el documento no tiene acceso");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect(aspectoEvaluarActual.getEvidencia());
        }
    }

    public void obtenerInformeFinal() throws IOException {
        if (planAuditoriaActual.getInformeFinal() == null) {
            addErrorMessage("Documento sin acceso", "el documento no tiene acceso");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect(planAuditoriaActual.getInformeFinal());
        }
    }

    public void obtenerActaSocializacion() throws IOException {
        if (planAuditoriaActual.getActaSocializacion() == null) {
            addErrorMessage("Documento sin acceso", "el documento no tiene acceso");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect(planAuditoriaActual.getActaSocializacion());
        }
    }

    public void obtenerPresentacion() throws IOException {
        if (periodoPlanAuditoriaActual.getLinkPresentacion() == null) {
            addErrorMessage("Documento sin acceso", "el documento no tiene acceso");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect(periodoPlanAuditoriaActual.getLinkPresentacion());
        }
    }

    public void obtenerAnexo() throws IOException {
        if (periodoPlanAuditoriaActual.getLinkAnexo() == null) {
            addErrorMessage("Documento sin acceso", "el documento no tiene acceso");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect(periodoPlanAuditoriaActual.getLinkAnexo());
        }
    }
    //Envio de correos

    private void sendMailAdd3() {
        String correo = "";
        String subject = "NUEVO PRESTAMO, ENTERATE DE ESTA NUEVA ACTUALIZACION EN TUS PRESTAMOS  ";
        StringBuilder mensaje = new StringBuilder();

        mensaje.append("\nCOD. DE LA AUDITORIA: ");
        mensaje.append(procesoEvaluadoActual.getIdPlanAuditoria().getIdPlanAuditoria()).append("-AUD-CFIP");

        mensaje.append("\nFECHA DE INICIO DE AUDITORIA: ");
        mensaje.append(procesoEvaluadoActual.getIdPlanAuditoria().getFechaIAuditoria().toLocaleString());

        mensaje.append("\nFECHA FIN DE AUDITORIA: ");
        mensaje.append(procesoEvaluadoActual.getIdPlanAuditoria().getFechafAuditoria().toLocaleString());

        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail("juan.cordoba@cfiprovidencia.com " + " angelica.barreiro@cfiprovidencia.com " + "sistemas@cfiprovidencia.com", subject, mensaje.toString());

    }

    private void sendMail(String to, String subject, String body) {
        try {
            emailBean.sendMail(to, subject, body);
            JsfUtil.addSuccessMessage("Mensaje Enviado Exitosamente");
        } catch (NamingException | MessagingException ex) {
            JsfUtil.addErrorMessage("Error sending message " + ex.getClass().getName());
        }
    }

}
