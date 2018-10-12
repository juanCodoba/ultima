/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.EstadoLibro;
import com.proyectoCFIP.entities.Libro;
import com.proyectoCFIP.entities.ReservaLibrosBiblioteca;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.EstadoLibroFacade;
import com.proyectoCFIP.sessions.LibroFacade;
import com.proyectoCFIP.sessions.ReservaLibrosBibliotecaFacade;
import java.io.Console;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author junio
 */
@Named(value = "reservaLibrosController")
@SessionScoped
public class ReservaLibrosController implements Serializable {

    @EJB
    private ReservaLibrosBibliotecaFacade reservaLibFacade;
    private ReservaLibrosBiblioteca reservaLibActual;
    private List<ReservaLibrosBiblioteca> listaReservaLib;
    private List<ReservaLibrosBiblioteca> listaReservaLib1 = new ArrayList<>();
    @EJB
    EmailSessionBean emailBean;

    @EJB
    private LibroFacade libroFacade;
    private Libro LibroActual;
    private List<Libro> LibroReservaLib;

    @EJB
    private EstadoLibroFacade EstadoLibroFacade;
    private EstadoLibro EstadoLibroActual;
    private List<EstadoLibro> EstadoLibroList;

    private Usuario usuarioActual;

    public ReservaLibrosController() {
    }

    public ReservaLibrosBibliotecaFacade getReservaLibrosBibliotecaFacade() {
        return reservaLibFacade;
    }

    public void setReservaLibrosBibliotecaFacade(ReservaLibrosBibliotecaFacade reservaLibFacade) {
        this.reservaLibFacade = reservaLibFacade;
    }

    public ReservaLibrosBiblioteca getReservaLibActual() {
        return reservaLibActual;
    }

    public void setReservaLibActual(ReservaLibrosBiblioteca reservaLibActual) {
        this.reservaLibActual = reservaLibActual;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public List<ReservaLibrosBiblioteca> getListaReserva() {
        return listaReservaLib = getReservaLibrosBibliotecaFacade().findAll();
    }

    public List<ReservaLibrosBiblioteca> getListaReservaEstado() {
        return listaReservaLib = getReservaLibrosBibliotecaFacade().consultaEstado();
    }

    public List<ReservaLibrosBiblioteca> getListaReservaEstadoFalso() {
        return listaReservaLib = getReservaLibrosBibliotecaFacade().consultaEstadoFalso();
    }

    public void setListaReserva(List<ReservaLibrosBiblioteca> listaReservaLib) {
        this.listaReservaLib = listaReservaLib;
    }

    public LibroFacade getLibroFacade() {
        return libroFacade;
    }

    public void setLibroFacade(LibroFacade libroFacade) {
        this.libroFacade = libroFacade;
    }

    public Libro getLibroActual() {
        return LibroActual;
    }

    public void setLibroActual(Libro libroActual) {
        this.LibroActual = libroActual;
    }

    public ReservaLibrosBibliotecaFacade getReservaLibFacade() {
        return reservaLibFacade;
    }

    public void setReservaLibFacade(ReservaLibrosBibliotecaFacade reservaLibFacade) {
        this.reservaLibFacade = reservaLibFacade;
    }

    public List<ReservaLibrosBiblioteca> getListaReservaLib() {
        return listaReservaLib;
    }

    public void setListaReservaLib(List<ReservaLibrosBiblioteca> listaReservaLib) {
        this.listaReservaLib = listaReservaLib;
    }

    public List<ReservaLibrosBiblioteca> getListaReservaLib1() {
        return listaReservaLib1;
    }

    public void setListaReservaLib1(List<ReservaLibrosBiblioteca> listaReservaLib1) {
        this.listaReservaLib1 = listaReservaLib1;
    }

    public EmailSessionBean getEmailBean() {
        return emailBean;
    }

    public void setEmailBean(EmailSessionBean emailBean) {
        this.emailBean = emailBean;
    }

    public EstadoLibroFacade getEstadoLibroFacade() {
        return EstadoLibroFacade;
    }

    public void setEstadoLibroFacade(EstadoLibroFacade EstadoLibroFacade) {
        this.EstadoLibroFacade = EstadoLibroFacade;
    }

    public EstadoLibro getEstadoLibroActual() {
        return EstadoLibroActual;
    }

    public void setEstadoLibroActual(EstadoLibro EstadoLibroActual) {
        this.EstadoLibroActual = EstadoLibroActual;
    }

    public List<EstadoLibro> getEstadoLibroList() {
        return EstadoLibroList;
    }

    public void setEstadoLibroList(List<EstadoLibro> EstadoLibroList) {
        this.EstadoLibroList = EstadoLibroList;
    }

    public List<Libro> getLibroReservaLib() {
        return LibroReservaLib;
    }

    public void setLibroReservaLib(List<Libro> LibroReservaLib) {
        this.LibroReservaLib = LibroReservaLib;
    }

    public void recargarLista() {
        listaReservaLib = null;
    }

    public void llenarListaTipoFic() {
        listaReservaLib = getReservaLibrosBibliotecaFacade().consultaTipoF(reservaLibActual.getIdTipoEstudiante());
    }

    public boolean isEstudiante() {
        return reservaLibActual.getIdTipoEstudiante() == null ? false
                : reservaLibActual.getIdTipoEstudiante().getIdTipoEstudiante() == (short) 1;
    }

    public boolean isEgresado() {
        return reservaLibActual.getIdTipoEstudiante() == null ? false
                : reservaLibActual.getIdTipoEstudiante().getIdTipoEstudiante() == (short) 2;
    }

    public boolean isAutonoma() {
        return reservaLibActual.getIdTipoEstudiante() == null ? false
                : reservaLibActual.getIdTipoEstudiante().getIdTipoEstudiante() == (short) 3;
    }

    public boolean isTrabajador() {
        return reservaLibActual.getIdTipoEstudiante() == null ? false
                : reservaLibActual.getIdTipoEstudiante().getIdTipoEstudiante() == (short) 4;
    }

    public boolean isTecnico() {
        return reservaLibActual.getIdTipoEstudiante() == null ? false
                : reservaLibActual.getIdTipoEstudiante().getIdTipoEstudiante() == (short) 5;
    }

    public boolean isDoce() {
        return reservaLibActual.getIdTipoEstudiante() == null ? false
                : reservaLibActual.getIdTipoEstudiante().getIdTipoEstudiante() == (short) 6;
    }

    public ReservaLibrosBiblioteca getLibro(java.lang.Integer id) {
        return getReservaLibrosBibliotecaFacade().find(id);
    }

    public String prepareEdit() {
        return "/administrador/reservaLibro/editar/editar";
    }

    public String prepareEdit1() {
        return "/administrador/reservaLibro/editar/editar1L";
    }

    public String prepareCreate() {
        reservaLibActual = new ReservaLibrosBiblioteca();
        return "/administrador/reservaLibro/crear/crear";
    }

    public String prepareView() {
        return "/administrador/reservaLibro/ver/ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/reservaLibro/listar/listarRe";
    }

    public String prepareListH() {
        recargarLista();
        return "/administrador/reservaLibro/listar/listarReD";
    }

    public String prepareListUser() {
        recargarLista();
        return "/usuario/ReservaLib/lista";
    }

    public String prepareListusuario() {
        recargarLista();
        return "/usuario/modSaludOcupacional/reporte/lista";
    }

    public String prepareEditP() {
        return "/administrador/reservaLibro/listar/LibOcupR";
    }

    public String prepareHisotrial() {
        return "/administrador/reservaLibro/listar/historialReservas";
    }

    private void sendMailAdd2() {

        String subject = "NUEVO PRESTAMO, ENTERATE DE ESTA NUEVA ACTUALIZACION EN TUS PRESTAMOS  ";
        StringBuilder mensaje = new StringBuilder();

        mensaje.append("\nCod Libro N°1: ");
        mensaje.append(reservaLibActual.getIdLib1().getCodigo()).append("LET-CAÑ");

        mensaje.append("\nCod Libro N°2: ");
        mensaje.append(reservaLibActual.getIdLib2().getCodigo()).append("LET-CAÑ");

        mensaje.append("\nTitulo del Libro N°1: ");
        mensaje.append(reservaLibActual.getIdLib1().getTituloLibro());
        mensaje.append("\nTitulo del Libro N°2: ");
        mensaje.append(reservaLibActual.getIdLib2().getTituloLibro());

        mensaje.append("\nTipo estudiante: ");
        mensaje.append(reservaLibActual.getIdTipoEstudiante());

        mensaje.append("\nUsuario al que se entrego: ");
        mensaje.append(reservaLibActual.getIdUsuarioPrestamo().toString().toUpperCase());

        mensaje.append("\nBibliotecario: ");
        mensaje.append(reservaLibActual.getIdBibliotecario().toString().toUpperCase());

        mensaje.append("\nGrado: ");
        mensaje.append(reservaLibActual.getGradoEstudiante());

        mensaje.append("\nArea de trabajo: ");
        mensaje.append(reservaLibActual.getArea());

        mensaje.append("\nNombre Egresado: ");
        mensaje.append(reservaLibActual.getNombreEgresado());
        mensaje.append("\nEmail Egresado: ");
        mensaje.append(reservaLibActual.getApellidoEgresado());

        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail("juan.cordoba@cfiprovidencia.com " + " " + " angelica.barreiro@cfiprovidencia.com " + "sistemas@cfiprovidencia.com", subject, mensaje.toString());

    }

    private void sendMail(String to, String subject, String body) {
        try {
            emailBean.sendMail(to, subject, body);
            JsfUtil.addSuccessMessage("Mensaje Enviado Exitosamente");
        } catch (NamingException | MessagingException ex) {
            JsfUtil.addErrorMessage("Error sending message " + ex.getClass().getName());
        }
    }

    public String add() {

        try {

            reservaLibActual.setIdBibliotecario(usuarioActual);
            reservaLibActual.setActivo(Boolean.TRUE);
            reservaLibActual.setEstadoUsuarioReservas(Boolean.TRUE);
            getReservaLibrosBibliotecaFacade().create(reservaLibActual);
            sendMailAdd2();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "prestamo creado", "EL Prestamo fue creado satisfactoriamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);

            recargarLista();

            return "/usuario/modBiblioteca/ListarLibro/lista";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al crear el prestamo", "no se pudo crear el prestamo");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/usuario/modBiblioteca/ListarLibro/lista";

        }

    }

    public String updateInReserva() {
        try {
            LibroActual.setIdUsuaroLib(usuarioActual);
            getLibroFacade().edit(LibroActual);
            LibroActual = new Libro();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "el estado del libro", "ha sido editado satisfactoriamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();

            return "/administrador/reservaLibro/editar/editar";
        } catch (Exception e) {
            addErrorMessage("Error Al Editar " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/administrador/reservaLibro/editar/editar";
        }
    }

    public List<ReservaLibrosBiblioteca> getListaReservaUser() {
        listaReservaLib1 = new ArrayList<>();
        listaReservaLib1 = getReservaLibrosBibliotecaFacade().consultaUsuario(usuarioActual);
        return listaReservaLib1;
    }

    public String update() {
        try {
            getReservaLibrosBibliotecaFacade().edit(reservaLibActual);
            recargarLista();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "el estado del prestamo", "ha sido editado satisfactoriamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "/usuario/modBiblioteca/ListarLibro/lista";
        } catch (Exception e) {
            addErrorMessage("Error Al Editar " + e.getClass().getName(), "Message: " + e.getMessage());

            return "/usuario/modBiblioteca/ListarLibro/lista";
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

    @FacesConverter(forClass = ReservaLibrosBiblioteca.class)
    public static class reservaLibrosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReservaLibrosController controller = (ReservaLibrosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reservaLibrosController");
            return controller.getLibro(getKey(value));
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
            if (object instanceof ReservaLibrosBiblioteca) {
                ReservaLibrosBiblioteca o = (ReservaLibrosBiblioteca) object;
                return getStringKey(o.getIdReservaLibros());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ReservaLibrosBiblioteca.class.getName());
            }
        }
    }

}
