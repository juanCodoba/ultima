/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.lowagie.text.DocumentException;
import com.proyectoCFIP.entities.Facturado;
import com.proyectoCFIP.entities.TipoCarga;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.FacturadoFacade;
import com.proyectoCFIP.sessions.TipoCargaFacade;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.model.StreamedContent;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class FacturadoController implements Serializable {

    @EJB
    private FacturadoFacade facturadoFacade;
    private List<Facturado> listaFacturado = null;
    private List<Facturado> listaFecha = null;
    private List<Facturado> listaFacturadoUser;
    private Facturado facturadoActual;
    public Usuario usuarioActual;
    private TipoCarga tipoCargaActual;
    private List<TipoCarga> tipoCargaList = null;
    private TipoCargaFacade tipoCargaFacade;
    public Date fechaParametro1;
    public Date fechaParametro2;

    public FacturadoController() {

    }

    public List<Facturado> getListaFecha() {
        return listaFecha;
    }

    public void setListaFecha(List<Facturado> listaFecha) {
        this.listaFecha = listaFecha;
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
    

    public TipoCarga getTipoCargaActual() {
        return tipoCargaActual;
    }

    public void setTipoCargaActual(TipoCarga tipoCargaActual) {
        this.tipoCargaActual = tipoCargaActual;
    }

    public List<TipoCarga> getTipoCargaList() {
        return tipoCargaList = getTipoCargaFacade().findAll();
    }

    public void setTipoCargaList(List<TipoCarga> tipoCargaList) {
        this.tipoCargaList = tipoCargaList;
    }

    public TipoCargaFacade getTipoCargaFacade() {
        return tipoCargaFacade;
    }

    public void setTipoCargaFacade(TipoCargaFacade tipoCargaFacade) {
        this.tipoCargaFacade = tipoCargaFacade;
    }

    public List<Facturado> getListaFacturado() {
        return listaFacturado = getFacturadoFacade().consultaTodo();
    }
    

    public FacturadoFacade getFacturadoFacade() {
        return facturadoFacade;
    }

    public Facturado getFacturadoActual() {
        if (facturadoActual == null) {
            facturadoActual = new Facturado();
        }
        return facturadoActual;
    }

    public void setFacturadoActual(Facturado facturadoActual) {
        this.facturadoActual = facturadoActual;
    }

    public List<Facturado> getListaFacturadoUser() {
        return listaFacturadoUser;
    }

    public void setListaFacturadoUser(List<Facturado> listaFacturadoUser) {
        this.listaFacturadoUser = listaFacturadoUser;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public StreamedContent getArchivoDescarga() {
        return archivoDescarga;
    }

    public void setArchivoDescarga(StreamedContent archivoDescarga) {
        this.archivoDescarga = archivoDescarga;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    private void recargarLista() {
        listaFacturado = new ArrayList<>();
    }

    //Crear Facturado inicio
    public String prepareCreate() {
        facturadoActual = new Facturado();

        return "/administrador/desayuno/create";
    }

    public String prepareEdit() {
        return "/administrador/desayuno/edit";
    }

    public String prepareViewRep() {
        return "reportePorFecha";
    }

    public String list() {
        return "/usuario/desayuno/list?faces-redirect=true";
    }

///////////////////////////////////////////////////////////CARGOS//////////////////////////////////////////////////////////////////////////////////////
    public String add() {
        try {
            facturadoActual.setIdUsuario(usuarioActual);
            getFacturadoFacade().create(facturadoActual);
            addSuccessMessage("Envio creado correctamente", "El codigo de envio es  " + facturadoActual + " fue creado correctamente");
            recargarLista();
            return "/usuario/desayuno/list?faces-redirect=true";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String updateFacturado() {
        try {
            getFacturadoFacade().edit(facturadoActual);
            recargarLista();
            addSuccessMessage("Cargo Actualizado correctamente", "El cargo " + facturadoActual + " fue Actualizado correctamente");

            return "/usuario/desayuno/list?faces-redirect=true";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "";
        }
    }

///////////////////////////////////////////////////////////CARGOS//////////////////////////////////////////////////////////////////////////////////////
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

    public Facturado getFacturado(java.lang.Integer id) {
        return getFacturadoFacade().find(id);
    }

    @FacesConverter(forClass = Facturado.class, value = "facturadoConverter")
    public static class FacturadoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FacturadoController controller = (FacturadoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "facturadoController");
            return controller.getFacturado(getKey(value));
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
            if (object instanceof Facturado) {
                Facturado o = (Facturado) object;
                return getStringKey(o.getIdFacturado());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Facturado.class.getName());
            }
        }
    }

    private StreamedContent file;

//    public void obtenerFichaLogo() throws IOException {
//        if (facturadoActual.getIdProfesiograma().getUrlProfesiograma() == null) {
//            addErrorMessage("Documento sin acceso", "el documento no tiene acceso");
//        } else {
//            InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(facturadoActual.getIdProfesiograma().getUrlProfesiograma());
//            file = new DefaultStreamedContent(stream, "file/pdf", "downloaded_optimus.pdf");
//        }
//    }
    private StreamedContent archivoDescarga;

//    public StreamedContent getArchivoDescarga() throws FileNotFoundException {
//        try {
//            if (facturadoActual.getIdProfesiograma().getUrlProfesiograma() == null) {
//                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe documento", "El documento no tiene acceso");
//                RequestContext.getCurrentInstance().showMessageInDialog(message);
//                return null;
//            } else {
//                InputStream stream = new FileInputStream(facturadoActual.getIdProfesiograma().getUrlProfesiograma());
//                return archivoDescarga = new DefaultStreamedContent(stream, "application/pdf", "file.pdf");
//            }
//        } catch (Exception e) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe documento", "no puede descargarse");
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
//            return null;
//        }
//    }
    public void createManualPDF() throws FileNotFoundException, MalformedURLException, DocumentException, IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String url;
        //url = "http://localhost:8080/fichasTecnicas/faces/administrador/desayuno/ManualExport.xhtml;jsessionid=" + session.getId() + "?pdf=true";
        url = "http://167.114.11.220:8080/saint/faces/administrador/desayuno/ManualExport.xhtml;jsessionid=" + session.getId() + "?pdf=true";
        //url = "http://localhost:8080/fichasTecnicas/faces/administrador/desayuno/ManualExport.xhtml;jsesecnicas/faces/administrador/desayuno/ManualExport.xhtml;jsessionid=" + session.getId() + "?pdf=true";

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

    public List<Facturado> getListaReporteTiempo() {
        listaFecha = new ArrayList<>();
        return listaFecha = getFacturadoFacade().consultaReporteCorrectivoTiempo(getFechaParametro1(), getFechaParametro2());
    }

}
