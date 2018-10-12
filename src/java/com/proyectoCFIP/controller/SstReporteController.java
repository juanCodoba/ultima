/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.SstReporteUsuario;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.SstReporteUsuarioFacade;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.naming.NamingException;

@Named(value = "sstReporteController")
@SessionScoped
public class SstReporteController implements Serializable {

    @EJB
    private SstReporteUsuarioFacade sstReporteUsuarioFacade;
    private SstReporteUsuario sstReporteUsuarioActual;
    private List<SstReporteUsuario> listaSstReporteActual;
    @EJB
    EmailSessionBean emailBean;
    
    private Usuario usuarioActual;
    public SstReporteController() {
    }

    public SstReporteUsuarioFacade getSstReporteUsuarioFacade() {
        return sstReporteUsuarioFacade;
    }

    public void setSstReporteUsuarioFacade(SstReporteUsuarioFacade sstReporteUsuarioFacade) {
        this.sstReporteUsuarioFacade = sstReporteUsuarioFacade;
    }

    public SstReporteUsuario getSstReporteUsuarioActual() {
        return sstReporteUsuarioActual;
    }

    public void setSstReporteUsuarioActual(SstReporteUsuario sstReporteUsuarioActual) {
        this.sstReporteUsuarioActual = sstReporteUsuarioActual;
    }

    public List<SstReporteUsuario> getListaSstReporteActual() {
        return listaSstReporteActual = getSstReporteUsuarioFacade().findAll();
    }
    public List<SstReporteUsuario> getListaSstReporteUsuario() {
        return listaSstReporteActual = getSstReporteUsuarioFacade().consultaUsuario(usuarioActual);
    }
    public void setListaSstReporteActual(List<SstReporteUsuario> listaSstReporteActual) {
        this.listaSstReporteActual = listaSstReporteActual;
    }
     private void recargarLista() {
        listaSstReporteActual = null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public String prepareCreate() {
        sstReporteUsuarioActual = new SstReporteUsuario();
        return "/usuario/modSaludOcupacional/reporte/crear";
    }

    public String prepareEdit() {
        return "/administrador/modSaludOcupacional/reporte/editar";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modSaludOcupacional/reporte/lista";
    }
    public String prepareListusuario() {
        recargarLista();
        return "/usuario/modSaludOcupacional/reporte/lista";
    }

    public String add() {
        try {
            sstReporteUsuarioActual.setEstado("OBSERVACION");
            sstReporteUsuarioActual.setIdUsuario(usuarioActual);
            sstReporteUsuarioActual.setFecha(new Date());
            getSstReporteUsuarioFacade().create(sstReporteUsuarioActual);
            sendMailRegistroTec();
            sendMailRegistroUser();
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String update() {
        try {
            getSstReporteUsuarioFacade().edit(sstReporteUsuarioActual);
            recargarLista();
            return "lista";
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
        String subject = " REPORTE DE INCIDENTE, CONDICION Y ACTO INSEGURO Y CONDICIONES DE SALUD CREADO CON CONSECUTIVO No."+ sstReporteUsuarioActual.getIdSstIncidente();
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CONSECUTIVO No. ");
        mensaje.append(sstReporteUsuarioActual.getIdSstIncidente());
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(sstReporteUsuarioActual.getIdUsuario().toString().toUpperCase());
        mensaje.append("\nTIPO INCIDENTE: ");
        mensaje.append(sstReporteUsuarioActual.getTipo().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN: ");
        mensaje.append(sstReporteUsuarioActual.getDescripcion().toUpperCase());
        mensaje.append("\nRECOMENDACIÓN: ");
        mensaje.append(sstReporteUsuarioActual.getRecomendacion().toUpperCase());
        mensaje.append("\nFECHA DEL REPORTE: ");
        mensaje.append(sstReporteUsuarioActual.getFecha().toLocaleString());
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2018.");
        sendMail("luis.cabal@cfiprovidencia.com "+" "+" natalia.gomez@cfiprovidencia.com "+" "+" haidy.perdomo@cfiprovidencia.com ", subject, mensaje.toString());  
    }
    private void sendMailRegistroUser() {
        String subject = "SU REPORTE CREADO CON CONSECUTIVO No."+ sstReporteUsuarioActual.getIdSstIncidente();
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CONSECUTIVO No. ");
        mensaje.append(sstReporteUsuarioActual.getIdSstIncidente());
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(sstReporteUsuarioActual.getIdUsuario().toString().toUpperCase());
        mensaje.append("\nTIPO INCIDENTE: ");
        mensaje.append(sstReporteUsuarioActual.getTipo().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN: ");
        mensaje.append(sstReporteUsuarioActual.getDescripcion().toUpperCase());
        mensaje.append("\nRECOMENDACIÓN: ");
        mensaje.append(sstReporteUsuarioActual.getRecomendacion().toUpperCase());
        mensaje.append("\nFECHA DEL REPORTE: ");
        mensaje.append(sstReporteUsuarioActual.getFecha().toLocaleString());
        mensaje.append("\n\nPRONTO UNO DE NUESTROS FUNCIONARIOS ESTARA REALIZANDO LOS SEGUIMIENTOS RESPECTIVOS.");
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2018.");
        sendMail(sstReporteUsuarioActual.getIdUsuario().getCorreoUsuario(), subject, mensaje.toString());  
    }
    
}
