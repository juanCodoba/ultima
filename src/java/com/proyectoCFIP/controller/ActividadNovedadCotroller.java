/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.ActividadNovedad;
import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.Novedad;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.ActividadNovedadFacade;
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
import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.rmi.CORBA.Util;
import org.primefaces.context.RequestContext;

/**
 *
 * @author junio
 */
@Named(value = "ActividadNovedadCotroller")
@SessionScoped
public class ActividadNovedadCotroller implements Serializable {

    @EJB
    private ActividadNovedadFacade novedadFacade;
    private ActividadNovedad ActividadNovedadActual;
    private List<ActividadNovedad> listaActividadNovedad;
    private List<ActividadNovedad> listaActividadNovedadActual;
    private List<ActividadNovedad> listaActividadNovedadCargo;
    private Usuario usuarioActual;
    private Novedad NovedadActual;
    @EJB
    EmailSessionBean emailBean;

    private Cargos cargosActual;

    public ActividadNovedadCotroller() {
    }

    public ActividadNovedadFacade getActividadNovedadFacade() {
        return novedadFacade;
    }

    public void setActividadNovedadFacade(ActividadNovedadFacade novedadFacade) {
        this.novedadFacade = novedadFacade;
    }

    public ActividadNovedad getActividadNovedadActual() {
        return ActividadNovedadActual;
    }

    public void setActividadNovedadActual(ActividadNovedad ActividadNovedadActual) {
        this.ActividadNovedadActual = ActividadNovedadActual;
    }

//    public List<ActividadNovedad> getListaActividadNovedad() {
//        return listaActividadNovedad = getActividadNovedadFacade().consultaTodo();
//    }
    public List<ActividadNovedad> getListaActividadNovedadCargo() {
        return listaActividadNovedadCargo = getActividadNovedadFacade().consultaTodo();
    }

    public void setListaActividadNovedadCargo(List<ActividadNovedad> listaActividadNovedadCargo) {
        this.listaActividadNovedadCargo = listaActividadNovedadCargo;
    }

    public List<ActividadNovedad> getListaPorTipoDeUsuario2() {
        return listaActividadNovedadCargo = getActividadNovedadFacade().consultaNovedad(NovedadActual);
    }

    public void setListaActividadNovedad(List<ActividadNovedad> listaActividadNovedad) {
        this.listaActividadNovedad = listaActividadNovedad;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public Cargos getCargosActual() {
        return cargosActual;
    }

    public void setCargosActual(Cargos cargosActual) {
        this.cargosActual = cargosActual;
    }

    public ActividadNovedadFacade getNovedadFacade() {
        return novedadFacade;
    }

    public void setNovedadFacade(ActividadNovedadFacade novedadFacade) {
        this.novedadFacade = novedadFacade;
    }

    public Novedad getNovedadActual() {
        return NovedadActual;
    }

    public void setNovedadActual(Novedad NovedadActual) {
        this.NovedadActual = NovedadActual;
    }

    public List<ActividadNovedad> getListaActividadNovedadActual() {
        return listaActividadNovedadActual;
    }

    public void setListaActividadNovedadActual(List<ActividadNovedad> listaActividadNovedadActual) {
        this.listaActividadNovedadActual = listaActividadNovedadActual;
    }

    public void recargarLista() {
        listaActividadNovedad = null;
    }

    public ActividadNovedad getActividadNovedad(java.lang.Integer id) {
        return getActividadNovedadFacade().find(id);
    }

    public String prepareCreate() {
        ActividadNovedadActual = new ActividadNovedad();
        listaActividadNovedadActual = new ArrayList<>();
        return "/administrador/modFichaTecnica/actiNovedades/crear";
    }

    public String prepareListNovedad() {
        return "/administrador/modFichaTecnica/actiNovedades/lista?faces-redirect=true";
    }

    public void add() {
        try {
            java.util.Date fecha = new Date();
//            ActividadNovedadActual.setIdUsuarioCreacion(usuarioActual);
            ActividadNovedadActual.setFechaActual(fecha);
            ActividadNovedadActual.setIdNovedad(NovedadActual);
            ActividadNovedadActual.setEstadoNoConformeDiseño("DESARROLLO");
            addSuccessMessage("ActividadNovedad", "La ActividadNovedad con el codigo " + ActividadNovedadActual.getIdActividadNovedad() + " fue creada exitosamente");
            getActividadNovedadFacade().create(ActividadNovedadActual);
            
            listaActividadNovedadActual.add(ActividadNovedadActual);
            ActividadNovedadActual = new ActividadNovedad();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado", "El Registro fue creado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            sendMailRegistroTec();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }

    public void add2() {

        try {
//            ActividadNovedadActual.setIdUsuarioCreacion(usuarioActual);
//            ActividadNovedadActual.setEstado(Boolean.TRUE);
            getActividadNovedadFacade().edit(ActividadNovedadActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado", "El Registro fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no Eliminado", "El Registro no fue eliminado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public String guardar() {

        try {
//            ActividadNovedadActual.setIdUsuarioCreacion(usuarioActual);
//            ActividadNovedadActual.setEstado(Boolean.TRUE);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado", "El Registro fue Creado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            
            
            return "/administrador/modFichaTecnica/novedades/lista?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no Eliminado", "El Registro no fue eliminado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "";
        }

    }

    public String prepareAspecto() {
        ActividadNovedadActual = new ActividadNovedad();
        listaActividadNovedadActual = new ArrayList<>();
        return "/administrador/modCalidad/cargos/editCargo/editActividadNovedad";

    }

    public String GuardarTodo() {

        try {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ActividadNovedad creado", "El ActividadNovedad fue creado correctamente");
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

            getActividadNovedadFacade().edit(ActividadNovedadActual);
            sendMailAdminNov();
            recargarLista();
            return "/usuario/modBiblioteca/ListarActividadNovedad/lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/usuario/modBiblioteca/ListarActividadNovedad/lista";
        }
    }

    private void sendMail(String to, String subject, String body) {
        try {
            emailBean.sendMail(to, subject, body);
            JsfUtil.addSuccessMessage("Mensaje Enviado Exitosamente");
        } catch (NamingException | MessagingException ex) {
            JsfUtil.addErrorMessage("Error sending message " + ex.getClass().getName());
        }
    }

    private void sendMailRegistroTec() {
        String subject = "ACTIVIDAD ACTUALIZADA CON EL CONSECUTIVO No." + ActividadNovedadActual.getIdNovedad() + "E ITEM No." + ActividadNovedadActual.getIdNovedad().getItem();
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("\nDESCRIPCION DE LA NOVEDAD  ");
        mensaje.append(ActividadNovedadActual.getIdNovedad().getDescripcionNovedad());
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(ActividadNovedadActual.getIdNovedad().getIdUsuarioReporta().toString().toUpperCase());
        mensaje.append("\n CLIENTE: ");
        mensaje.append(ActividadNovedadActual.getIdNovedad().getIdFtCliente().getNombre().toUpperCase());
        mensaje.append("\n OP: ");
        mensaje.append(ActividadNovedadActual.getIdNovedad().getOp());
        mensaje.append("\n TAREA A REALIZAR: ");
        mensaje.append(ActividadNovedadActual.getDescripcion());
        mensaje.append("\n ESTADO: ");
        mensaje.append(ActividadNovedadActual.getEstadoNoConformeDiseño());
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail("camilo.buitrago@cfiprovidencia.com " + "nathalia.yusti@cfiprovidencia.com " + "mayra.munoz@cfiprovidencia.com " + "claudia.canar@cfiprovidencia.com " + "juan.cordoba@cfiprovidencia.com " + ActividadNovedadActual.getIdNovedad().getIdUsuarioCreacion().getCorreoUsuario(), subject, mensaje.toString());
    }

    private void sendMailAdminNov() {
        String subject = "ACTIVIDAD ACTUALIZADA CON EL CONSECUTIVO No." + ActividadNovedadActual.getIdNovedad() + "E ITEM No." + ActividadNovedadActual.getIdNovedad().getItem();
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("\nDESCRIPCION DE LA NOVEDAD  ");
        mensaje.append(ActividadNovedadActual.getIdNovedad().getDescripcionNovedad());
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(ActividadNovedadActual.getIdNovedad().getIdUsuarioReporta().toString().toUpperCase());
        mensaje.append("\n CLIENTE: ");
        mensaje.append(ActividadNovedadActual.getIdNovedad().getIdFtCliente().getNombre().toUpperCase());
        mensaje.append("\n OP: ");
        mensaje.append(ActividadNovedadActual.getIdNovedad().getOp());
        mensaje.append("\n TAREA A REALIZAR: ");
        mensaje.append(ActividadNovedadActual.getDescripcion());
        mensaje.append("\n ESTADO: ");
        mensaje.append(ActividadNovedadActual.getEstadoNoConformeDiseño());
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail("camilo.buitrago@cfiprovidencia.com " + "nathalia.yusti@cfiprovidencia.com " + "mayra.munoz@cfiprovidencia.com " + "claudia.canar@cfiprovidencia.com " + "juan.cordoba@cfiprovidencia.com" + ActividadNovedadActual.getIdNovedad().getIdUsuarioCreacion().getCorreoUsuario(), subject, mensaje.toString());
    }

    public String updateMantenimiento() {
        try {

            getActividadNovedadFacade().edit(ActividadNovedadActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "el estado del libro", "ha sido editado satisfactoriamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();

            return "/administrador/reservaActividadNovedad/editar/editar";
        } catch (Exception e) {
            addErrorMessage("Error Al Editar " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/administrador/reservaActividadNovedad/editar/editar";
        }
    }

    public void deleteActividadNovedad() {
        try {
            getActividadNovedadFacade().remove(ActividadNovedadActual);
            listaActividadNovedadActual.remove(ActividadNovedadActual);
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
            getActividadNovedadFacade().remove(ActividadNovedadActual);
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

    @FacesConverter(forClass = ActividadNovedad.class)
    public static class ActividadNovedadCotrollerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ActividadNovedadCotroller controller = (ActividadNovedadCotroller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ActividadNovedadCotroller");
            return controller.getActividadNovedad(getKey(value));
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
            if (object instanceof ActividadNovedad) {
                ActividadNovedad o = (ActividadNovedad) object;
                return getStringKey(o.getIdActividadNovedad());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ActividadNovedad.class.getName());
            }
        }

    }

}
