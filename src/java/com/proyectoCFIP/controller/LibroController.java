/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.proyectoCFIP.entities.EstadoLibro;
import com.proyectoCFIP.entities.Genero;
import com.proyectoCFIP.entities.Libro;
import com.proyectoCFIP.entities.ReservaLibrosBiblioteca;
import com.proyectoCFIP.entities.TipoMantenimiento;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.GeneroFacade;
import com.proyectoCFIP.sessions.LibroFacade;
import com.proyectoCFIP.sessions.ReservaLibrosBibliotecaFacade;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.StreamedContent;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author junio
 */
@Named(value = "LibroController")
@SessionScoped
public class LibroController implements Serializable {

    @EJB
    private LibroFacade libroFacade;
    private Libro LibroActual;
    private List<Libro> listaLibro;

    @EJB
    private GeneroFacade generoFacade;
    private Genero generoActual;
    private List<Genero> listaGenero;
    @EJB
    EmailSessionBean emailBean;
    private Date fechaParametro1;
    private Date fechaParametro2;

    private Usuario usuarioActual;

    @EJB
    private ReservaLibrosBibliotecaFacade reservaLibFacade;
    private ReservaLibrosBiblioteca reservaLibActual;
    private List<ReservaLibrosBiblioteca> listaReservaLib;
    private List<ReservaLibrosBiblioteca> listaReservaLib1 = new ArrayList<>();

    public LibroController() {

    }

    public boolean isCuento() {
        return LibroActual.getIdGenero() == null ? false
                : LibroActual.getIdGenero().getIdGenero() == (short) 1;
    }

    public GeneroFacade getGeneroFacade() {
        return generoFacade;
    }

    public void setGeneroFacade(GeneroFacade generoFacade) {
        this.generoFacade = generoFacade;
    }

    public Genero getGeneroActual() {
        return generoActual;
    }

    public void setGeneroActual(Genero generoActual) {
        this.generoActual = generoActual;
    }

    public List<Genero> getListaGenero() {
        return listaGenero;
    }

    public void setListaGenero(List<Genero> listaGenero) {
        this.listaGenero = listaGenero;
    }

    public EmailSessionBean getEmailBean() {
        return emailBean;
    }

    public void setEmailBean(EmailSessionBean emailBean) {
        this.emailBean = emailBean;
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

    public DefaultScheduleEvent getMan() {
        return man;
    }

    public void setMan(DefaultScheduleEvent man) {
        this.man = man;
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

    public void setLibroActual(Libro LibroActual) {
        this.LibroActual = LibroActual;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public List<Libro> getListaLibro() {
        return listaLibro = getLibroFacade().findAll();
    }

    public List<Libro> getConsulta() {
        return listaLibro = getLibroFacade().consultaTodos();
    }

    public List<Libro> getConsultaOtros() {
        return listaLibro = getLibroFacade().consultaOtrosP();
    }

    public List<Libro> getConsultaOtrosC() {
        return listaLibro = getLibroFacade().consultaOtrosC();
    }

    public List<Libro> getConsultaOtrosDeB() {
        return listaLibro = getLibroFacade().consultaOtrosDeBaja();
    }

    public List<Libro> getConsultaReservas() {
        return listaLibro = getLibroFacade().consultaDisponibiLibrosReservas();
    }

    public List<Libro> getConsultaLibrosResevados() {
        return listaLibro = getLibroFacade().consultaReservas();
    }

    public List<Libro> getConsultaId() {
        return listaLibro = getLibroFacade().consultaLibId();
    }



    public ReservaLibrosBibliotecaFacade getReservaLibFacade() {
        return reservaLibFacade;
    }

    public void setReservaLibFacade(ReservaLibrosBibliotecaFacade reservaLibFacade) {
        this.reservaLibFacade = reservaLibFacade;
    }

    public ReservaLibrosBiblioteca getReservaLibActual() {
        return reservaLibActual;
    }

    public void setReservaLibActual(ReservaLibrosBiblioteca reservaLibActual) {
        this.reservaLibActual = reservaLibActual;
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
    
    

    public void setListaLibro(List<Libro> listaLibro) {
        this.listaLibro = listaLibro;
    }

    public void recargarLista() {
        listaLibro = null;
    }

    public Libro getLibro(java.lang.Integer id) {
        return getLibroFacade().find(id);
    }

    public List<Libro> getListaLibroEstado() {
        return getLibroFacade().consultaLibEstado();
    }

    public String prepareEdit() {
        return "/administrador/modBiblioteca/editar/editarLibroBi";
    }

    public String prepareEditLibRes() {
        return "/administrador/modBiblioteca/editar/editarLibroBi";
    }

    public String prepareEditP() {
        return "/administrador/reservaLibro/listar/LibOcupR";
    }

    public String prepareCreate() {
        LibroActual = new Libro();
        return "/administrador/modBiblioteca/crear/crearLibroBi";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modBiblioteca/listar/lista";
    }

    public String prepareListusuario() {
        recargarLista();
        return "/usuario/modSaludOcupacional/reporte/lista";
    }

    public String prepareListPreven() {
        recargarLista();
        return "/administrador/modBiblioteca/mantenimientos/preventivo/preventivo";
    }

    public String prepareListCorrect() {
        recargarLista();
        return "/administrador/modBiblioteca/mantenimientos/correctivo/correctivo";
    }

    public String prepareUpdateMant() {
        recargarLista();
        return "/administrador/modBiblioteca/mantenimientos/preventivo/editarP";
    }

    public String prepareListDeBaja() {
        recargarLista();
        return "/administrador/modBiblioteca/mantenimientos/deBaja/deBaja";
    }

    public String prepareCreateMan() {
        recargarLista();
        return "/administrador/modBiblioteca/mantenimientos/preventivo/crearMaP";
    }

    public String prepareViewCalendar() {
        init();
        return "/administrador/modBiblioteca/calendarioMan/calendario";

    }

    public String add() {

        try {
            LibroActual.setIdUsuaroLib(usuarioActual);
            LibroActual.setActivo(Boolean.TRUE);
            LibroActual.setFechaModific(new Date());
            getLibroFacade().create(LibroActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Libro creado", "El Libro fue creado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();

            return "/usuario/modBiblioteca/ListarLibro/lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

            return "/usuario/modBiblioteca/ListarLibro/lista";

        }
    }

    public String update() {
        try {
            LibroActual.setIdUsuaroLib(usuarioActual);
            LibroActual.setFechaModific(new Date());

            getLibroFacade().edit(LibroActual);
            recargarLista();
            return "/usuario/modBiblioteca/ListarLibro/lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/usuario/modBiblioteca/ListarLibro/lista";
        }
    }

    public String updateMantenimiento() {
        try {
            LibroActual.setIdUsuaroLib(usuarioActual);
            LibroActual.setFechaModific(new Date());
            getLibroFacade().edit(LibroActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "el estado del libro", "ha sido editado satisfactoriamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();

            return "/administrador/reservaLibro/editar/editar";
        } catch (Exception e) {
            addErrorMessage("Error Al Editar " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/administrador/reservaLibro/editar/editar";
        }
    }

    public String updateInReserva() {
        try {
            LibroActual.setIdUsuaroLib(usuarioActual);
            getLibroFacade().edit(LibroActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "el estado del libro", "ha sido editado satisfactoriamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            LibroActual = new Libro();

            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error Al Editar " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        return "";
    }

    public String updateInReserva1() {
        try {
            LibroActual.setIdUsuaroLib(usuarioActual);
            LibroActual.setFechaModific(new Date());
            getLibroFacade().edit(LibroActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "el estado del libro", "ha sido editado satisfactoriamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();

            return "/administrador/reservaLibro/editar/editar1L";
        } catch (Exception e) {
            addErrorMessage("Error Al Editar " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/administrador/reservaLibro/editar/editar1L";
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

    @FacesConverter(forClass = Libro.class, value = "libroConverter")
    public static class LibroControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LibroController controller = (LibroController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "LibroController");
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
            if (object instanceof Libro) {
                Libro o = (Libro) object;
                return getStringKey(o.getIdLibro());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Libro.class.getName());
            }
        }

    }

    public List<Libro> recargarListaActividadesDaños() {
        listaLibro = new ArrayList<>();
        listaLibro = getLibroFacade().consultaCronograma(new TipoMantenimiento(1, "preventivo"), new EstadoLibro(1, "cerrado"));
        return listaLibro;
    }

    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    DefaultScheduleEvent man = new DefaultScheduleEvent();

    public void init() {
        eventModel = new DefaultScheduleModel();
        //ciclo para  mostrar datos en el event que muestra el calendario 
        for (Iterator<Libro> it = recargarListaActividadesDaños().iterator(); it.hasNext();) {
            Libro i = it.next();
            String horaReporte;
            DateFormat fecha1 = new SimpleDateFormat("hh:mma");
            horaReporte = fecha1.format(i.getHoraReporte());
            i.getDescripcion();
            eventModel.addEvent(new DefaultScheduleEvent(" - " + i.getIdUsuaroLib().toString() + "\n - #TICKET " + i.getIdLibro()
                    + "\n - HORA: " + horaReporte, i.getFechaReporte(), i.getHoraReporte(), "DAÑO_INFRAESTRUCTURA"));

        }

    }

    ;
    
        public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void createIndicadorMant() throws FileNotFoundException, MalformedURLException, DocumentException, IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String url;
        //url = "http://servidor/saintFichTec/faces/usuario/modFichaTecnica/fileCliente_1.xhtml;jsessionid=" + session.getId() + "?pdf=true";
          url = "http://localhost:8080/fichasTecnicas/faces/usuario/modBiblioteca/indicadores/indMantenimiento/indicador1.xhtml;jsessionid=" + session.getId() + "?pdf=true";
        //url = "http://167.114.11.220:8080/saint/faces/usuario/modFichaTecnica/fileCliente_1.xhtml;jsessionid=" + session.getId() + "?pdf=true";

        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(new URL(url).toString());
            renderer.layout();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            response.reset();
            Document document = new Document(PageSize.LEGAL.rectangle(40, 50));
            document.setPageSize(PageSize.LEGAL);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=\"Indicador-biblioteca.pdf\"");
            OutputStream outputStream = response.getOutputStream();
            renderer.createPDF(outputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        facesContext.responseComplete();
    }
    
        private StreamedContent archivoDescarga;



}
