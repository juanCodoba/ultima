/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.EstadoLibro;
import com.proyectoCFIP.entities.Libro;
import com.proyectoCFIP.entities.ReporteBiblioteca;
import com.proyectoCFIP.entities.ReservaLibrosBiblioteca;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.EstadoLibroFacade;
import com.proyectoCFIP.sessions.LibroFacade;
import com.proyectoCFIP.sessions.ReporteBibliotecaFacade;
import com.proyectoCFIP.sessions.ReservaLibrosBibliotecaFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

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

    private Date fechaParametro1;
    private Date fechaParametro2;
    private Date fechaActualDos;
    private long total;

    private ReporteBiblioteca reporteBibliotecaActual;
    private ReporteBibliotecaFacade reporteFacade;
    private List<ReporteBiblioteca> reporteListadoActual;
    private List<ReporteBiblioteca> reporteBibliotecaListSize;

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

    public List<ReporteBiblioteca> getReporteListadoActual() {
        return reporteListadoActual;
    }

    public void setReporteListadoActual(List<ReporteBiblioteca> reporteListadoActual) {
        this.reporteListadoActual = reporteListadoActual;
    }

    public ReporteBiblioteca getReporteBibliotecaActual() {
        return reporteBibliotecaActual;
    }

    public void setReporteBibliotecaActual(ReporteBiblioteca reporteBibliotecaActual) {
        this.reporteBibliotecaActual = reporteBibliotecaActual;
    }

    public ReporteBibliotecaFacade getReporteFacade() {
        return reporteFacade;
    }

    public void setReporteFacade(ReporteBibliotecaFacade reporteFacade) {
        this.reporteFacade = reporteFacade;
    }

//    public List<ReporteBiblioteca> getListaTicketsSinCerrar() {
//        reporteListadoActual = null;
//        return reporteListadoActual = getReporteFacade().consultaEstadoTickets(usuarioActual);
//    }
    public void llenarListaTipoFic() {
        listaReservaLib = getReservaLibrosBibliotecaFacade().consultaTipoF(reservaLibActual.getIdTipoEstudiante());
    }

    public List<ReservaLibrosBiblioteca> getConsultaLibrosResevados() {
        listaReservaLib = new ArrayList<>();
        return listaReservaLib = getReservaLibFacade().consultaTipoEgre(getFechaParametro1(), getFechaParametro2());
    }

//    public void preparePaginaPrincipal() {
//        int tamaño = getListaTicketsSinCerrar().size();
//        if (tamaño < 1) {
//        } else {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Cierre de tickets", "Tienes tickets sin cerrar, para continuar por favor valora cada servicio");
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
//        }
//    }
//    public List<ReservaLibrosBiblioteca> llenarEstudiante() {
//        listaReservaLib = new ArrayList<>();
//        return listaReservaLib = getReservaLibrosBibliotecaFacade().consultaEstudiante();
//    }
    public List<ReservaLibrosBiblioteca> llenarEgresado() {
        listaReservaLib = new ArrayList<>();
        return listaReservaLib = getReservaLibrosBibliotecaFacade().consultaTipoEgre(getFechaParametro1(), getFechaParametro2());
    }

    public List<ReservaLibrosBiblioteca> llenarEstudient() {
        listaReservaLib = new ArrayList<>();
        return listaReservaLib = getReservaLibrosBibliotecaFacade().consultaTipoEstudiante1(getFechaParametro1(), getFechaParametro2());
    }

    public List<ReservaLibrosBiblioteca> llenarTipoDoce() {
        listaReservaLib = new ArrayList<>();
        return listaReservaLib = getReservaLibrosBibliotecaFacade().consultaTipoDocen(getFechaParametro1(), getFechaParametro2());
    }

    public List<ReservaLibrosBiblioteca> llenarTrabajador() {
        listaReservaLib = new ArrayList<>();
        return listaReservaLib = getReservaLibrosBibliotecaFacade().consultaTipoTrabajador(getFechaParametro1(), getFechaParametro2());
    }

//    public List<ReservaLibrosBiblioteca> llenardIdLib1() {
//        listaReservaLib = new ArrayList<>();
//        return listaReservaLib = getReservaLibrosBibliotecaFacade().consultaTiP(getFechaParametro1(), getFechaParametro2());
//    }
//
//    public List<ReservaLibrosBiblioteca> llenardIdLib2() {
//        listaReservaLib = new ArrayList<>();
//        return listaReservaLib = getReservaLibrosBibliotecaFacade().consultaTiP2(getFechaParametro1(), getFechaParametro2());
//    }
    public List<ReservaLibrosBiblioteca> getListaReporteTiempo() {
        listaReservaLib = new ArrayList<>();
        return listaReservaLib = getReservaLibrosBibliotecaFacade().consultaReporteCorrectivoTiempo(getFechaParametro1(), getFechaParametro2());
    }

    public List<ReservaLibrosBiblioteca> getListaReporteTiempo1() {
        listaReservaLib = new ArrayList<>();
        return listaReservaLib = getReservaLibrosBibliotecaFacade().consultaReporteCorrectivoTiempo1(getFechaParametro1(), getFechaParametro2());
    }

    public List<ReservaLibrosBiblioteca> getListaReporteTiempo2() {
        listaReservaLib = new ArrayList<>();
        return listaReservaLib = getReservaLibrosBibliotecaFacade().consultaReporteCorrectivoTiempo2(getFechaParametro1(), getFechaParametro2());
    }

    public List<ReservaLibrosBiblioteca> getListaUsuarioDelMes() {
        listaReservaLib = new ArrayList<>();
        return listaReservaLib = getReservaLibrosBibliotecaFacade().consultaUsuariosDelMes(getFechaParametro1(), getFechaParametro2());
    }

    public List<ReservaLibrosBiblioteca> getListaUsuarioDelMes1() {
        listaReservaLib = new ArrayList<>();
        return listaReservaLib = getReservaLibrosBibliotecaFacade().consultaUsuariosDelMes1(getFechaParametro1(), getFechaParametro2());
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

//    public List <ReservaLibrosBiblioteca> getUsuarios(){
//        return getReservaLibFacade().ejecutarUsuario();
//    }
    public ReservaLibrosBiblioteca getLibro(java.lang.Integer id) {
        return getReservaLibrosBibliotecaFacade().find(id);
    }

    public Date getFechaParametro1() {
        return fechaParametro1;
    }

    public void setFechaParametro1(Date fechaParametro1) {
        this.fechaParametro1 = fechaParametro1;
    }

    public Date getFechaParametro2() {
        return fechaParametro2;
    }

    public void setFechaParametro2(Date fechaParametro2) {
        this.fechaParametro2 = fechaParametro2;
    }

    public Date getFechaActualDos() {

        return fechaActualDos = new java.util.Date();
    }

    public void setFechaActualDos(Date fechaActualDos) {
        this.fechaActualDos = fechaActualDos;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
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

    public String prepareViewIndicador() {
        return "/usuario/modBiblioteca/indicadores/indMantenimiento/indicador1";
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

    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd MMMMM yyyy");
        return formateador.format(ahora);
    }

    public List<ReporteBiblioteca> getReporteBibliotecaListSize() {
        reporteBibliotecaListSize = null;
        return reporteBibliotecaListSize = reporteFacade.consultaEstadoTickets(usuarioActual);
    }

    public void setReporteBibliotecaListSize(List<ReporteBiblioteca> reporteBibliotecaListSize) {
        this.reporteBibliotecaListSize = reporteBibliotecaListSize;
    }

    public void preparePaginaPrincipal() {
        int tamaño = getReporteBibliotecaListSize().size();
        if (tamaño < 1) {
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Cierre de tickets", "Tienes tickets sin cerrar, para continuar por favor valora cada servicio");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

//    private void sendMailAdd2() {
//
//        String subject = "NUEVO PRESTAMO, ENTERATE DE ESTA NUEVA ACTUALIZACION EN TUS PRESTAMOS  ";
//        StringBuilder mensaje = new StringBuilder();
//
//        mensaje.append("\nCod Libro N°1: ");
//        mensaje.append(reservaLibActual.getIdLib1().getCodigo()).append("LET-CAÑ");
//
//        mensaje.append("\nCod Libro N°2: ");
//        mensaje.append(reservaLibActual.getIdLib2().getCodigo()).append("LET-CAÑ");
//
//        mensaje.append("\nTitulo del Libro N°1: ");
//        mensaje.append(reservaLibActual.getIdLib1().getTituloLibro());
//        mensaje.append("\nTitulo del Libro N°2: ");
//        mensaje.append(reservaLibActual.getIdLib2().getTituloLibro());
//
//        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
//        sendMail("juan.cordoba@cfiprovidencia.com " + " angelica.barreiro@cfiprovidencia.com " + "sistemas@cfiprovidencia.com", subject, mensaje.toString());
//
//    }
    private void sendMailAdd3() {

        String subject = "NUEVO PRESTAMO, ENTERATE DE ESTA NUEVA ACTUALIZACION EN TUS PRESTAMOS  ";
        StringBuilder mensaje = new StringBuilder();

        mensaje.append("\nUsuario al cual se le presto: ");
        mensaje.append(reservaLibActual.getIdUsuarioPrestamo().toString());

        mensaje.append("\nGrado del Estudiante: ");
        mensaje.append(reservaLibActual.getIdGrado());

        mensaje.append("\nTitulo o tutulos reservados");
        mensaje.append(reservaLibActual.getLibroList());

        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail("juan.cordoba@cfiprovidencia.com " + " angelica.barreiro@cfiprovidencia.com ", subject, mensaje.toString());

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
//            if (reservaLibActual.getIdLib2() == null) {
//                sendMailAdd3();
//                recargarLista();
//            } else if (reservaLibActual.getIdLib1() != null || reservaLibActual.getIdLib2() != null) {
//                sendMailAdd2();
//                recargarLista();
//            } else {
//                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al crear el prestamo", "no se pudo crear el prestamo");
//                RequestContext.getCurrentInstance().showMessageInDialog(message);
//                return "/usuario/modBiblioteca/ListarLibro/lista";
//            } 
            reservaLibActual.setIdBibliotecario(usuarioActual);
            reservaLibActual.setActivo(Boolean.TRUE);
            reservaLibActual.setAhora(new java.util.Date());
            getReservaLibrosBibliotecaFacade().create(reservaLibActual);
            sendMailAdd3();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "prestamo creado", "EL Prestamo fue creado satisfactoriamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al crear el prestamo", "no se pudo crear el prestamo");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/usuario/modBiblioteca/ListarLibro/lista";

        }
        return "/usuario/modBiblioteca/ListarLibro/lista";

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

    @FacesValidator("limitCheckboxMenuValidator")
    public static class reservaLibrosLimitCheckboxMenuValidator implements Validator {

        @Override
        public void validate(FacesContext context, UIComponent component,
                Object value) throws ValidatorException {
            //get limit
            Integer minLimit = Integer.parseInt((String) component.getAttributes().get("minLimit"));
            SelectCheckboxMenu myComponent = (SelectCheckboxMenu) component;

            if (((String[]) myComponent.getSubmittedValue()).length > minLimit) {
                FacesMessage msg
                        = new FacesMessage("Limit failed",
                                "Min selection must be " + minLimit);
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }
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

    JasperPrint jasperPrint;

//    public class ConexionMySQL {
//
//        // Librería de MySQL
//        public String driver = "com.mysql.jdbc.Driver";
//
//        // Nombre de la base de datos
//        public String database = "proyectocfip";
//
//        // Host
//        public String hostname = "localhost";
//
//        // Puerto
//        public String port = "3306";
//
//        // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
//        public String url = "jdbc:mysql://localhost:3306/proyectocfip?zeroDateTimeBehavior=convertToNull";
//
//        // Nombre de usuario
//        public String username = "root";
//
//        // Clave de usuario
//        public String password = "1234";
//
//        public Connection conectarMySQL() throws SQLException {
//            Connection conn = null;
//
//            try {
//                Class.forName(driver);
//                conn = DriverManager.getConnection(url, username, password);
//            } catch (ClassNotFoundException | SQLException e) {
//                e.printStackTrace();
//            }
//
//            return conn;
//        }
//
//    }
    public class ConexionMySQL {

        // Librería de MySQL
        public String driver = "com.mysql.jdbc.Driver";

        // Nombre de la base de datos
        public String database = "proyectocfip";

        // Host
        public String hostname = "localhost";

        // Puerto
        public String port = "3306";

        // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
        public String url = "jdbc:mysql://localhost:3306/proyectocfip?zeroDateTimeBehavior=convertToNull";

        // Nombre de usuario
        public String username = "root";

        // Clave de usuario
        public String password = "cfiprovidencia1";

        public Connection conectarMySQL() throws SQLException {
            Connection conn = null;

            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            return conn;
        }

    }

    public void printPO(ActionEvent actionEvent) throws JRException, IOException {

        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            HashMap parametros = new HashMap();
            parametros.put("fechaInicio", fechaParametro1);
            parametros.put("fechaFin", fechaParametro2);
            ConexionMySQL SQL = new ConexionMySQL();
            Connection conn = SQL.conectarMySQL();

            String report = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reporteBiblioteca.jasper");

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorEstudiantes.pdf");

            jasperPrint = JasperFillManager.fillReport(report, parametros, conn);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            addErrorMessage("Error Al Editar " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void printLibM(ActionEvent actionEvent) throws JRException, IOException {

        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            HashMap parametros = new HashMap();
            parametros.put("fechaInicio", fechaParametro1);
            parametros.put("fechaFin", fechaParametro2);
            ConexionMySQL SQL = new ConexionMySQL();
            Connection conn = SQL.conectarMySQL();

            String report = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/libroMasLeido.jasper");

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorLibros.pdf");

            jasperPrint = JasperFillManager.fillReport(report, parametros, conn);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            addErrorMessage("Error Al Editar " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void printGrados(ActionEvent actionEvent) throws JRException, IOException {

        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            HashMap parametros = new HashMap();
            parametros.put("fechaInicio", fechaParametro1);
            parametros.put("fechaFin", fechaParametro2);
            ConexionMySQL SQL = new ConexionMySQL();
            Connection conn = SQL.conectarMySQL();

            String report = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/grados.jasper");

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorLibros.pdf");

            jasperPrint = JasperFillManager.fillReport(report, parametros, conn);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            addErrorMessage("Error Al Editar " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public String prepareViewCalendario() {
        init();
        recargarLista();

        return "/administrador/modBiblioteca/calendarioMan/calendario";
    }

    public List<ReservaLibrosBiblioteca> recargarListaMantenimientoRe() {
        listaReservaLib = new ArrayList<>();
        listaReservaLib = getReservaLibFacade().consultaCronogramaReserva();
        return listaReservaLib;
    }

    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    DefaultScheduleEvent man = new DefaultScheduleEvent();

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public DefaultScheduleEvent getMan() {
        return man;
    }

    public void setMan(DefaultScheduleEvent man) {
        this.man = man;
    }

    public void init() {
        eventModel = new DefaultScheduleModel();
        String Usuario = "";
        //String libros = "";
        String grado = "";
        //Calendario de Prestamos

        for (Iterator<ReservaLibrosBiblioteca> it = recargarListaMantenimientoRe().iterator(); it.hasNext();) {
            ReservaLibrosBiblioteca i = it.next();

            if (i.getIdUsuarioPrestamo() == null) {
                Usuario = i.getNombreEgresado() + "\n-CORR:" + i.getApellidoEgresado() + "\n-AÑO:" + i.getAñoEgresado();
            } else {
                Usuario = i.getIdUsuarioPrestamo().toString() + "\n-" + i.getIdUsuarioPrestamo().getCorreoUsuario() + "\n-" + i.getIdTipoEstudiante().getDescripcion();
            }
            if (i.getIdGrado() == null) {
                grado = "N/A";
            } else {
                grado = i.getIdGrado().getGrado();
            }

            eventModel.addEvent(new DefaultScheduleEvent("-LEC-CAÑ " + i.getIdReservaLibros()
                    + "\n- USR-PRE:" + Usuario.toUpperCase()
                    + "\n- GRAD: " + grado
                    + "\n- LIB:" + i.getLibroList().toString().toUpperCase()
                    + "\n  ", i.getFechaFinPrestamo(), i.getFechaInicioPrestamo()));
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

}
