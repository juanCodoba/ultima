/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.lowagie.text.DocumentException;
import com.proyectoCFIP.entities.Actividad;
import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.MaeFuncion;
import com.proyectoCFIP.entities.NivelHabilidad;
import com.proyectoCFIP.entities.Profesiograma;
import com.proyectoCFIP.entities.Requisito;
import com.proyectoCFIP.entities.TipoProfesiograma;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.ActividadFacade;
import com.proyectoCFIP.sessions.CargosFacade;
import com.proyectoCFIP.sessions.MaeFuncionFacade;
import com.proyectoCFIP.sessions.NivelHabilidadFacade;
import com.proyectoCFIP.sessions.ProfesiogramaFacade;
import com.proyectoCFIP.sessions.RequisitoFacade;
import com.proyectoCFIP.sessions.TipoProfesiogramaFacade;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class CargosController implements Serializable {

    @EJB
    private CargosFacade cargosFacade;
    private List<Cargos> listaCargos = null;
    private List<Cargos> listaCargosUser ;
    private Cargos cargosActual;

    @EJB
    private NivelHabilidadFacade nivelHabilidadFacade;
    private NivelHabilidad nivelHabilidadActual;
    private List<NivelHabilidad> nivelAbilidadList;

    ///////////////REQUISITO//////////////////////
    @EJB
    private RequisitoFacade requisitosFacade;
    private List<Requisito> listaRequisitoActual;
    private List<Requisito> listreq;
    private Requisito requisitoActual;
    private Requisito requisitos;
    ///////////////REQUISITO//////////////////////

    ///////////////FUNCIONES//////////////////////
    private MaeFuncionFacade maeFuncionFacade;
    private List<MaeFuncion> listaMaeFuncion;
    private List<MaeFuncion> listaMaeFuncionActual;
    private MaeFuncion MaeFuncionActual;
    ///////////////FUNCIONES//////////////////////

    ///////////////ACTIVIDADES////////////////////
    @EJB
    private ActividadFacade actividadFacade;
    private Actividad ActividadActual;
    private List<Actividad> listaActividad;
    private List<Actividad> listaActividadTipo = null;
    private List<Actividad> listaActividadActual;

    ///////////////ACTIVIDADES////////////////////
    private java.util.Date fechaManual;
    private Usuario usuarioActual;

    @EJB
    private ProfesiogramaFacade profesiogramaFacade;
    private List<Profesiograma> listaProfesiograma = null;
    private Profesiograma profesiogramaActual;

    @EJB
    private TipoProfesiogramaFacade tipoProfesiogramaFacade;
    private List<TipoProfesiograma> listaTipoProfesiograma = null;
    private TipoProfesiograma tipoProfesiogramaActual;

    public CargosController() {

    }

    public List<Cargos> getListaCargos() {
        return listaCargos = getCargosFacade().findAll();
    }

    public CargosFacade getCargosFacade() {
        return cargosFacade;
    }

    public Cargos getCargosActual() {
        if (cargosActual == null) {
            cargosActual = new Cargos();
        }
        return cargosActual;
    }

    public void setCargosActual(Cargos cargosActual) {
        this.cargosActual = cargosActual;
    }

    public ProfesiogramaFacade getProfesiogramaFacade() {
        return profesiogramaFacade;
    }

    public void setProfesiogramaFacade(ProfesiogramaFacade profesiogramaFacade) {
        this.profesiogramaFacade = profesiogramaFacade;
    }

    public List<Profesiograma> getListaProfesiograma() {
        return listaProfesiograma;
    }

    public void setListaProfesiograma(List<Profesiograma> listaProfesiograma) {
        this.listaProfesiograma = listaProfesiograma;
    }

    public Profesiograma getProfesiogramaActual() {
        return profesiogramaActual;
    }

    public void setProfesiogramaActual(Profesiograma profesiogramaActual) {
        this.profesiogramaActual = profesiogramaActual;
    }

    public TipoProfesiogramaFacade getTipoProfesiogramaFacade() {
        return tipoProfesiogramaFacade;
    }

    public void setTipoProfesiogramaFacade(TipoProfesiogramaFacade tipoProfesiogramaFacade) {
        this.tipoProfesiogramaFacade = tipoProfesiogramaFacade;
    }

    public List<TipoProfesiograma> getListaTipoProfesiograma() {
        return listaTipoProfesiograma;
    }

    public void setListaTipoProfesiograma(List<TipoProfesiograma> listaTipoProfesiograma) {
        this.listaTipoProfesiograma = listaTipoProfesiograma;
    }

    public TipoProfesiograma getTipoProfesiogramaActual() {
        return tipoProfesiogramaActual;
    }

    public void setTipoProfesiogramaActual(TipoProfesiograma tipoProfesiogramaActual) {
        this.tipoProfesiogramaActual = tipoProfesiogramaActual;
    }

    public RequisitoFacade getRequisitosFacade() {
        return requisitosFacade;
    }

    public void setRequisitosFacade(RequisitoFacade requisitosFacade) {
        this.requisitosFacade = requisitosFacade;
    }

    public List<Requisito> getListaRequisitoActual() {
        return listaRequisitoActual;
    }

    public void setListaRequisitoActual(List<Requisito> listaRequisitoActual) {
        this.listaRequisitoActual = listaRequisitoActual;
    }

    public List<Requisito> getListreq() {
        return listreq;
    }

    public List<Cargos> getListaCargosUser() {
        return listaCargosUser;
    }

    public void setListaCargosUser(List<Cargos> listaCargosUser) {
        this.listaCargosUser = listaCargosUser;
    }

    
    
    public List<Requisito> getListrequisitoCargo() {
        return listreq = requisitosFacade.consultaCargo(cargosActual);
    }
    
        public List<Cargos> getListaProcesoUser() {
        listaCargosUser = new ArrayList<>();
        listaCargosUser = getCargosFacade().consultaUsuario(usuarioActual );
        return listaCargosUser;
    }

    //Consulta para agilizar el  select dependiente
    public List<MaeFuncion> getListFuncionCargo() {
        return listaMaeFuncion = maeFuncionFacade.consultaUsuarioTipo(cargosActual);
    }
    //Cierre de Consulta para agilizar el  select dependiente

    public void setListreq(List<Requisito> listreq) {
        this.listreq = listreq;
    }

    public Requisito getRequisitoActual() {
        return requisitoActual;
    }

    public void setRequisitoActual(Requisito requisitoActual) {
        this.requisitoActual = requisitoActual;
    }

    public Requisito getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(Requisito requisitos) {
        this.requisitos = requisitos;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public MaeFuncionFacade getMaeFuncionFacade() {
        return maeFuncionFacade;
    }

    public void setMaeFuncionFacade(MaeFuncionFacade maeFuncionFacade) {
        this.maeFuncionFacade = maeFuncionFacade;
    }

    public MaeFuncion getMaeFuncionActual() {
        return MaeFuncionActual;
    }

    public void setMaeFuncionActual(MaeFuncion MaeFuncionActual) {
        this.MaeFuncionActual = MaeFuncionActual;
    }

    public List<MaeFuncion> getListaMaeFuncion() {
        return listaMaeFuncion;
    }

    public void setListaMaeFuncion(List<MaeFuncion> listaMaeFuncion) {
        this.listaMaeFuncion = listaMaeFuncion;
    }

    public List<MaeFuncion> getListaMaeFuncionActual() {
        return listaMaeFuncionActual;
    }

    public void setListaMaeFuncionActual(List<MaeFuncion> listaMaeFuncionActual) {
        this.listaMaeFuncionActual = listaMaeFuncionActual;
    }

    public Actividad getActividadActual() {
        return ActividadActual;
    }

    public void setActividadActual(Actividad ActividadActual) {
        this.ActividadActual = ActividadActual;
    }

    public List<Actividad> getListaActividad() {
        return listaActividad;
    }

    public void setListaActividad(List<Actividad> listaActividad) {
        this.listaActividad = listaActividad;
    }

    public List<Actividad> getListaActividadTipo() {
        return listaActividadTipo;
    }

    public void setListaActividadTipo(List<Actividad> listaActividadTipo) {
        this.listaActividadTipo = listaActividadTipo;
    }

    public ActividadFacade getActividadFacade() {
        return actividadFacade;
    }

    public void setActividadFacade(ActividadFacade actividadFacade) {
        this.actividadFacade = actividadFacade;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    //Fecha manual
    public Date getFechaManual() {
        return fechaManual = new Date();
    }

    public void setFechaManual(Date fechaManual) {
        this.fechaManual = fechaManual;
    }

    private void recargarLista() {
        listaCargos = new ArrayList<>();
    }

    public NivelHabilidadFacade getNivelHabilidadFacade() {
        return nivelHabilidadFacade;
    }

    public void setNivelHabilidadFacade(NivelHabilidadFacade nivelHabilidadFacade) {
        this.nivelHabilidadFacade = nivelHabilidadFacade;
    }

    public NivelHabilidad getNivelHabilidadActual() {
        return nivelHabilidadActual;
    }

    public void setNivelHabilidadActual(NivelHabilidad nivelHabilidadActual) {
        this.nivelHabilidadActual = nivelHabilidadActual;
    }

    public List<NivelHabilidad> getNivelAbilidadList() {
        return nivelAbilidadList;
    }

    public void setNivelAbilidadList(List<NivelHabilidad> nivelAbilidadList) {
        this.nivelAbilidadList = nivelAbilidadList;
    }

    public List<Actividad> getListaActividadActual() {
        return listaActividadActual;
    }

    public void setListaActividadActual(List<Actividad> listaActividadActual) {
        this.listaActividadActual = listaActividadActual;
    }

    //Crear Cargos inicio
    public String prepareCreate() {
        cargosActual = new Cargos();
        requisitoActual = new Requisito();
        listaRequisitoActual = new ArrayList<>();
        MaeFuncionActual = new MaeFuncion();
        listaMaeFuncionActual = new ArrayList<>();

        return "/administrador/modCalidad/cargos/create";
    }

    public String prepareEdit() {
        return "/administrador/modCalidad/cargos/edit";
    }

    public String prepareView() {
        return "";
    }

    public String list() {
        return "/usuario/modCalidad/cargos/list";
    }

///////////////////////////////////////////////////////////CARGOS//////////////////////////////////////////////////////////////////////////////////////
    public String add() {
        try {
            getCargosFacade().create(cargosActual);
            addSuccessMessage("Cargo creado correctamente", "El cargo " + cargosActual + " fue creado correctamente");
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String updateCargos() {
        try {
            getCargosFacade().edit(cargosActual);
            recargarLista();
            addSuccessMessage("Cargo Actualizado correctamente", "El cargo " + cargosActual + " fue Actualizado correctamente");

            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "";
        }
    }

    public String deleteCargos() {
        try {
            getCargosFacade().remove(cargosActual);
            recargarLista();
            addSuccessMessage("Cargo Eliminado correctamente", "El cargo " + " fue Elimiado correctamente");

        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        return "/administrador/modCalidad/cargos/List";
    }

///////////////////////////////////////////////////////////CARGOS//////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////REQUISITOS//////////////////////////////////////////////////////////////////////////////////////
    public void addRequisito() {
        try {

            requisitoActual.setEstado(Boolean.TRUE);
            getRequisitosFacade().create(requisitoActual);
            listaRequisitoActual.add(requisitoActual);
            requisitoActual = new Requisito();
            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }

    public void editRequisito() {
        try {
            requisitoActual.setEstado(true);
            getRequisitosFacade().edit(requisitoActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Requisito editado", "se asignado el requisito al cargo");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void deleteRequisitoLista() {
        try {
            getRequisitosFacade().remove(requisitoActual);
            listaRequisitoActual.remove(requisitoActual);
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

///////////////////////////////////////////////////////////REQUISITOS//////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////FUNCIONES//////////////////////////////////////////////////////////////////////////////////////
    public void add3() {

        try {
            MaeFuncionActual.setEstado(true);
            getMaeFuncionFacade().create(MaeFuncionActual);
            listaMaeFuncionActual.add(MaeFuncionActual);
            MaeFuncionActual = new MaeFuncion();
            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }

    public String pasarSig() {

        try {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Funciones Asignadas ", "las funciones fueron asginada al cargo correspondiente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "/administrador/modCalidad/cargos/create4";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "";

        }
    }

    public String update() {
        try {
            getMaeFuncionFacade().edit(MaeFuncionActual);
            recargarLista();
            addSuccessMessage("Causa creada", "La causa fue creada");
            return "/administrador/modCalidad/cargos/list";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "";
        }
    }

    public void delete() {
        try {
            getMaeFuncionFacade().remove(MaeFuncionActual);
            listaMaeFuncionActual.remove(MaeFuncionActual);
            addSuccessMessage("datos removidos", "la funcion fue removida del cargo ");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }
///////////////////////////////////////////////////////////FUNCIONES//////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////ACTIVIDADES/////////////////////////////////////////////////////////////////////////////////
    public void add4() {

        try {
            ActividadActual.setEstado(Boolean.TRUE);
            getActividadFacade().create(ActividadActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actividad creada", "La Actividad fue creada correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();

        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }

    public String GuardarTodo() {

        try {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actividad creado", "El Actividad fue creado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "/administrador/modCalidad/cargos/list";

        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/administrador/modCalidad/cargos/list";

        }
    }

    public String update3() {
        try {

            getActividadFacade().edit(ActividadActual);
            recargarLista();
            return "/usuario/modBiblioteca/ListarActividad/lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/usuario/modBiblioteca/ListarActividad/lista";
        }
    }

    public void deleteActividad() {
        try {
            getActividadFacade().remove(ActividadActual);
            listaActividadActual.remove(ActividadActual);
            addSuccessMessage("datos removidos", "la funcion fue removida del cargo ");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }
///////////////////////////////////////////////////////////ACTIVIDADES/////////////////////////////////////////////////////////////////////////////////

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

    public Cargos getCargos(java.lang.Integer id) {
        return getCargosFacade().find(id);
    }

    @FacesConverter(forClass = Cargos.class, value = "cargosConverter")
    public static class CargosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CargosController controller = (CargosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cargosController");
            return controller.getCargos(getKey(value));
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
            if (object instanceof Cargos) {
                Cargos o = (Cargos) object;
                return getStringKey(o.getIdCargo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Cargos.class.getName());
            }
        }
    }

    private StreamedContent file;

    public void obtenerFichaLogo() throws IOException {
        if (cargosActual.getIdProfesiograma().getUrlProfesiograma() == null) {
            addErrorMessage("Documento sin acceso", "el documento no tiene acceso");
        } else {
            InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(cargosActual.getIdProfesiograma().getUrlProfesiograma());
            file = new DefaultStreamedContent(stream, "file/pdf", "downloaded_optimus.pdf");
        }
    }

    private StreamedContent archivoDescarga;

    public StreamedContent getArchivoDescarga() throws FileNotFoundException {
        try {
            if (cargosActual.getIdProfesiograma().getUrlProfesiograma() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe documento", "El documento no tiene acceso");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return null;
            } else {
                InputStream stream = new FileInputStream(cargosActual.getIdProfesiograma().getUrlProfesiograma());
                return archivoDescarga = new DefaultStreamedContent(stream, "application/pdf", "file.pdf");
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe documento", "no puede descargarse");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
    }

    public void createManualPDF() throws FileNotFoundException, MalformedURLException, DocumentException, IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String url;
        //url = "http://servidor/saintFichTec/faces/usuario/modFichaTecnica/fileCliente_1.xhtml;jsessionid=" + session.getId() + "?pdf=true";
        //url = "http://localhost:8080/fichasTecnicas/faces/administrador/modCalidad/cargos/ManualExport.xhtml;jsessionid=" + session.getId() + "?pdf=true";
        url = "http://167.114.11.220:8080/saint/faces/administrador/modCalidad/cargos/ManualExport.xhtml;jsessionid=" + session.getId() + "?pdf=true";

        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(new URL(url).toString());
            renderer.layout();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=\"print-file.pdf\"");
            OutputStream outputStream = response.getOutputStream();
            renderer.createPDF(outputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        facesContext.responseComplete();
    }

}
