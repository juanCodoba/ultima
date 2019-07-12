/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.ReporteBiblioteca;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.ReporteBibliotecaFacade;
import java.io.Serializable;
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
public class ReporteBibliotecaController implements Serializable {

    @EJB
    private ReporteBibliotecaFacade reporteBibliotecaFacade;
    private ReporteBiblioteca reporteBibliotecaActual;
    private List<ReporteBiblioteca> reporteBibliotecaList;
    private List<ReporteBiblioteca> reporteBibliotecaListSize;
    private Usuario usuarioActual;

    @EJB
    EmailSessionBean emailBean;

    public ReporteBibliotecaController() {
    }

    public ReporteBibliotecaFacade getReporteBibliotecaFacade() {
        return reporteBibliotecaFacade;
    }

    public void setReporteBibliotecaFacade(ReporteBibliotecaFacade reporteBibliotecaFacade) {
        this.reporteBibliotecaFacade = reporteBibliotecaFacade;
    }

    public ReporteBiblioteca getReporteBibliotecaActual() {
        return reporteBibliotecaActual;
    }

    public void setReporteBibliotecaActual(ReporteBiblioteca reporteBibliotecaActual) {
        this.reporteBibliotecaActual = reporteBibliotecaActual;
    }

    public List<ReporteBiblioteca> getReporteBibliotecaList() {
        return reporteBibliotecaList = getReporteBibliotecaFacade().findAll();
    }

    public void setReporteBibliotecaList(List<ReporteBiblioteca> reporteBibliotecaList) {
        this.reporteBibliotecaList = reporteBibliotecaList;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public List<ReporteBiblioteca> getReporteBibliotecaListSize() {
        reporteBibliotecaListSize = null;
        return reporteBibliotecaListSize = getReporteBibliotecaFacade().consultaEstadoTickets(usuarioActual);
    }

    public void setReporteBibliotecaListSize(List<ReporteBiblioteca> reporteBibliotecaListSize) {
        this.reporteBibliotecaListSize = reporteBibliotecaListSize;
    }

    public EmailSessionBean getEmailBean() {
        return emailBean;
    }

    public void setEmailBean(EmailSessionBean emailBean) {
        this.emailBean = emailBean;
    }

    public void preparePaginaPrincipal() {
        int tamaño = getReporteBibliotecaListSize().size();
        if (tamaño < 1) {
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Cierre de tickets", "Tienes tickets sin cerrar, para continuar por favor valora cada servicio");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public ReporteBiblioteca getReporteBiblioteca(java.lang.Integer id) {
        return getReporteBibliotecaFacade().find(id);
    }

    public String prepareCreate() {
        reporteBibliotecaActual = new ReporteBiblioteca();
        return "/administrador/modBiblioteca/reportar/crearReporte";
    }

    public String prepareListEstadoTicket() {
        return "/administrador/modBiblioteca/reportar/listarReportes";
    }

    public void recargarLista() {
        reporteBibliotecaList = null;
    }

    public void update() {
        try {
            getReporteBibliotecaFacade().edit(reporteBibliotecaActual);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public String add() {
        try {
//            reporteBibliotecaActual.setFechaReporte(new Date());
//            reporteBibliotecaActual.setEstadoReporte(false);
//            reporteBibliotecaActual.setIdEstadoTicket(new EstadoTicket(1, "Abierto"));
            reporteBibliotecaActual.setIdUsuario(usuarioActual);
            getReporteBibliotecaFacade().create(reporteBibliotecaActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Su reporte ha sido registrado", "El reporte con numero de ticket " + reporteBibliotecaActual.getIdReporte() + "fue reaizado correctamente");
            recargarLista();
            return "/administrador/modBiblioteca/reportar/listarReportes";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
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

    @FacesConverter(forClass = ReporteBiblioteca.class)
    public static class ReporteBibliotecaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReporteBibliotecaController controller = (ReporteBibliotecaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reporteBibliotecaController");
            return controller.getReporteBiblioteca(getKey(value));
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
            if (object instanceof ReporteBiblioteca) {
                ReporteBiblioteca o = (ReporteBiblioteca) object;
                return getStringKey(o.getIdReporte());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ReporteBiblioteca.class.getName());
            }
        }

    }

//    
//    private void sendMailDiagnostico() {
//        String codigoSiesa ="";
//        String descripcionSiesa ="";
//
//        SimpleDateFormat fecha = new SimpleDateFormat("MMM/dd/yyyy");
//        String subject = "DIAGNOSTICO DEL TICKET #" + reporteBibliotecaActual.getIdReporte()+"CFIPERP";
//        StringBuilder mensaje = new StringBuilder();
//        mensaje.append("CODIGO DEL DIAGNOSTICO ");
//        mensaje.append(diagnosticoReporteBibliotecaActual.getIdReporteBiblioteca().getIdReporteBiblioteca());
//        mensaje.append("CFIPDIAG-E");
//        
//        mensaje.append("\n---------------------------------------------------------------------");
//        mensaje.append("\nDATOS DEL REPORTE ");
//        mensaje.append("\nTIPO DE ERROR: ");
//        mensaje.append(diagnosticoReporteBibliotecaActual.getIdReporteBiblioteca().getIdTipoError().getNombreError().toUpperCase());
//        mensaje.append("\nDESCRIPCIÓN DE ERROR: ");
//        mensaje.append(diagnosticoReporteBibliotecaActual.getIdReporteBiblioteca().getDescripcionReporte().toUpperCase());
//        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
//        mensaje.append(diagnosticoReporteBibliotecaActual.getIdReporteBiblioteca().getFechaReporte().toLocaleString());
//        mensaje.append("\nUSUARIO QUE REALIZO EL REPORTE: ");
//        mensaje.append(diagnosticoReporteBibliotecaActual.getIdReporteBiblioteca().getIdUsuario().toString().toUpperCase());
//        
//        mensaje.append("\n---------------------------------------------------------------------");
//        mensaje.append("\nDATOS DEL DIAGNOSTICO:");
//        mensaje.append("\n---------------------------------------------------------------------");
//        mensaje.append("\nREVISION: ");
//        mensaje.append(diagnosticoReporteBibliotecaActual.getRevision().toUpperCase());
//        mensaje.append("\nFECHA DE LA REVISION: ");
//        mensaje.append(fecha.format(diagnosticoReporteBibliotecaActual.getFechaRevision()));
//        mensaje.append("\nDIAGNOSTICO: ");
//        mensaje.append(diagnosticoReporteBibliotecaActual.getDiagnostico().toUpperCase());
//        mensaje.append("\nFECHA QUE SE CERRO EL TICKET: ");
//        mensaje.append(fecha.format(diagnosticoReporteBibliotecaActual.getFechaDiagnostico()));
//        mensaje.append("\nCODIGO DE SIESA IT: ");
//        mensaje.append(codigoSiesa.toUpperCase());
//        mensaje.append("\nDIAGNOSTICO DE SIESA IT: ");
//        mensaje.append(descripcionSiesa.toUpperCase());
//        
//        mensaje.append("\nTECNICO QUE ATENDIO EL CASO: ");
//        mensaje.append(diagnosticoReporteBibliotecaActual.getIdUsuario().toString());
//        
//        
//        sendMail(diagnosticoReporteBibliotecaActual.getIdReporteBiblioteca().getIdUsuario().getCorreoUsuario() +" "+" lccabal@misena.edu.co "+" "+" sistemas@cfiprovidencia.com "+" "+" auxsistemas@cfiprovidencia.com ", subject, mensaje.toString());
//    }
}
