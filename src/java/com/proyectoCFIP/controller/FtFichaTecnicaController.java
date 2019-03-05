/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.lowagie.text.DocumentException;
import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.FtFichaTecnica;
import com.proyectoCFIP.entities.FtModificaciones;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.FtFichaTecnicaFacade;
import com.proyectoCFIP.sessions.FtModificacionesFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author junio
 */
@Named(value = "ftFichaTecnica")
@SessionScoped
public class FtFichaTecnicaController implements Serializable {

    @EJB
    private FtFichaTecnicaFacade fichaTecnicaFacade;
    private FtFichaTecnica fichaTecnicaActual;
    private List<FtFichaTecnica> listaFichaTecnica;

    private FtFichaTecnica fichaTecnicaVersion;

    private Usuario usuarioActual;
    private StreamedContent imgF = new DefaultStreamedContent();
    private StreamedContent imgP = new DefaultStreamedContent();
    private StreamedContent imgC1 = new DefaultStreamedContent();
    private StreamedContent imgC2 = new DefaultStreamedContent();
    private StreamedContent imgC3 = new DefaultStreamedContent();
    private StreamedContent imgC4 = new DefaultStreamedContent();
    private StreamedContent file;

    private String etiqueta1;
    private String etiqueta2;
    private String etiqueta3;
    private String etiqueta4;
    private String etiqueta5;
    private String etiqueta6;
    private String etiqueta7;
    private String etiqueta8;
    private String etiqueta9;
    private String etiqueta10;
    private String etiqueta11;

    @EJB
    private FtModificacionesFacade modificacionesFacade;
    private FtModificaciones modificacionesActual;
    private List<FtModificaciones> listaModificaciones;
    @EJB
    EmailSessionBean emailBean;
    

    private StreamedContent fileBordado;

    public boolean isCamisa() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 2;
    }

    public boolean isCamibuso() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 3;
    }

    public boolean isBatola() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 5;
    }

    public boolean isChaqueta() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 6;
    }

    public boolean isJean() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 7;
    }

    public boolean isPantalonAdmin() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 8;
    }

    public boolean isSlackAdmin() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 9;
    }

    public boolean isChaleco() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 10;
    }

    public boolean isCofia() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 12;
    }

    public boolean isTapaboca() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 13;
    }

    public boolean isBlusaAdmin() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 14;
    }

    public boolean isBlusaOper() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 15;
    }

    public boolean isPantalonOper() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 16;
    }

    public boolean isSlackOper() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 17;
    }

    public boolean isOverol() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 18;
    }

    public boolean isCamisaOper() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 19;
    }

    public boolean isDelantal() {
        return fichaTecnicaActual.getIdFtCategoria() == null ? false
                : fichaTecnicaActual.getIdFtCategoria().getIdFtCategoria() == (short) 23;
    }

    public boolean isFichaO() {
        return fichaTecnicaActual.getIdTipoFicha() == null ? false
                : fichaTecnicaActual.getIdTipoFicha().getIdTipoFicha() == (short) 1;
    }

    public boolean isFichaN() {
        return fichaTecnicaActual.getIdTipoFicha() == null ? false
                : fichaTecnicaActual.getIdTipoFicha().getIdTipoFicha() == (short) 2;
    }

    public FtFichaTecnicaFacade getFichaTecnicaFacade() {
        return fichaTecnicaFacade;
    }

    public void setFichaTecnicaFacade(FtFichaTecnicaFacade fichaTecnicaFacade) {
        this.fichaTecnicaFacade = fichaTecnicaFacade;
    }

    public FtFichaTecnica getFichaTecnicaActual() {
        return fichaTecnicaActual;
    }

    public void setFichaTecnicaActual(FtFichaTecnica fichaTecnicaActual) {
        this.fichaTecnicaActual = fichaTecnicaActual;
    }

    public List<FtFichaTecnica> getListaFichaTecnicaPrimeraValidacion() {
        return listaFichaTecnica = getFichaTecnicaFacade().consultaEstado("PRIMERA VALIDACION");
    }

    public List<FtFichaTecnica> getListaFichaTecnicaPrimeraValidacionq() {
        return listaFichaTecnica = getFichaTecnicaFacade().consultaEstado1("PRIMERA VALIDACION");
    }

    public List<FtFichaTecnica> getListaFichaTecnicaSegundaValidacion() {
        return listaFichaTecnica = getFichaTecnicaFacade().consultaEstado("SEGUNDA VALIDACION");
    }

    public List<FtFichaTecnica> getListaFichaTecnicaElaborador() {
        return listaFichaTecnica = getFichaTecnicaFacade().consultaEstadoDesarrollador();
    }

    public List<FtFichaTecnica> getListaFichaTecnicaElaborador1() {
        return listaFichaTecnica = getFichaTecnicaFacade().consultaEstadoDesarrolladorq();
    }

    public List<FtFichaTecnica> getListaFichaTecnicaUser() {
        return listaFichaTecnica = getFichaTecnicaFacade().consultaEstado("TERMINADO");
    }

    public List<FtFichaTecnica> getListaFichaTecnicaVersion() {
        return listaFichaTecnica = getFichaTecnicaFacade().consultaVersion(fichaTecnicaActual.getIdItem());
    }

    public List<FtFichaTecnica> getListaFichaTecnicaItem() {
        return listaFichaTecnica = getFichaTecnicaFacade().consultaItem(fichaTecnicaActual.getIdItem());
    }

    public void setListaFichaTecnica(List<FtFichaTecnica> listaFichaTecnica) {
        this.listaFichaTecnica = listaFichaTecnica;
    }

    public void recargarLista() {
        listaFichaTecnica = null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public Date getFechaActual() {
        return new Date();
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public void llenarListaTipo() {
        listaFichaTecnica = getFichaTecnicaFacade().consultaTipo(fichaTecnicaActual.getIdFtCategoria());
    }

    public void llenarListaTipoFic() {
        listaFichaTecnica = getFichaTecnicaFacade().consultaTipoF(fichaTecnicaActual.getIdTipoFicha());
    }

    public String getEtiqueta1() {
        return etiqueta1;
    }

    public void setEtiqueta1(String etiqueta1) {
        this.etiqueta1 = etiqueta1;
    }

    public String getEtiqueta2() {
        return etiqueta2;
    }

    public void setEtiqueta2(String etiqueta2) {
        this.etiqueta2 = etiqueta2;
    }

    public String getEtiqueta3() {
        return etiqueta3;
    }

    public void setEtiqueta3(String etiqueta3) {
        this.etiqueta3 = etiqueta3;
    }

    public String getEtiqueta4() {
        return etiqueta4;
    }

    public void setEtiqueta4(String etiqueta4) {
        this.etiqueta4 = etiqueta4;
    }

    public String getEtiqueta5() {
        return etiqueta5;
    }

    public void setEtiqueta5(String etiqueta5) {
        this.etiqueta5 = etiqueta5;
    }

    public String getEtiqueta6() {
        return etiqueta6;
    }

    public void setEtiqueta6(String etiqueta6) {
        this.etiqueta6 = etiqueta6;
    }

    public String getEtiqueta7() {
        return etiqueta7;
    }

    public void setEtiqueta7(String etiqueta7) {
        this.etiqueta7 = etiqueta7;
    }

    public String getEtiqueta8() {
        return etiqueta8;
    }

    public void setEtiqueta8(String etiqueta8) {
        this.etiqueta8 = etiqueta8;
    }

    public String getEtiqueta9() {
        return etiqueta9;
    }

    public void setEtiqueta9(String etiqueta9) {
        this.etiqueta9 = etiqueta9;
    }

    public String getEtiqueta10() {
        return etiqueta10;
    }

    public void setEtiqueta10(String etiqueta10) {
        this.etiqueta10 = etiqueta10;
    }

    public String getEtiqueta11() {
        return etiqueta11;
    }

    public void setEtiqueta11(String etiqueta11) {
        this.etiqueta11 = etiqueta11;
    }

    public FtFichaTecnica getFichaTecnicaVersion() {
        return fichaTecnicaVersion;
    }

    public void setFichaTecnicaVersion(FtFichaTecnica fichaTecnicaVersion) {
        this.fichaTecnicaVersion = fichaTecnicaVersion;
    }

    public String prepareCreate() {
        String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";
        fichaTecnicaActual = new FtFichaTecnica();
        return "/administrador/modFichaTecnica/desarrollo/crear";
    }

    public String prepareCreateDuplicado() {
        String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";
        fichaTecnicaActual.setObservaciones("");
        fichaTecnicaActual.setRutaImgFrontal(newFileName);
        fichaTecnicaActual.setRutaImgPosterior(newFileName);
        fichaTecnicaActual.setRutaImgCaracteristica1(newFileName);
        fichaTecnicaActual.setRutaImgCaracteristica2(newFileName);
        fichaTecnicaActual.setRutaImgCaracteristica3(newFileName);
        fichaTecnicaActual.setRutaImgCaracteristica4(newFileName);
        return "/administrador/modFichaTecnica/desarrollo/crear";
    }

    public String prepareCreateDuplicadoOPM() {
        String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";
        fichaTecnicaActual.setObservaciones("");
        fichaTecnicaActual.setRutaImgFrontal(newFileName);
        fichaTecnicaActual.setRutaImgPosterior(newFileName);
        fichaTecnicaActual.setRutaImgCaracteristica1(newFileName);
        fichaTecnicaActual.setRutaImgCaracteristica2(newFileName);
        fichaTecnicaActual.setRutaImgCaracteristica3(newFileName);
        fichaTecnicaActual.setRutaImgCaracteristica4(newFileName);
        return "/administrador/modFichaTecnica/desarrollo/crearOPM";
    }

    public String prepareEditDesarrollo() {
        return "editar";
    }

    public String prepareVerDesarrollo() {
        return "ver";
    }

    public String prepareEdit() {
        fichaTecnicaVersion = new FtFichaTecnica();
        return "editar";
    }

    public String prepareVer() {
        /*copiarImgF();
        copiarImgP();
        copiarImgC1();
        copiarImgC2();
        copiarImgC3();
        copiarImgC4();*/
        return "ver";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareListSolicitudesModificacionTodas() {
        recargarLista();
        return "/usuario/modFichaTecnica/modificacion/lista";
    }

    public String prepareList() {
        recargarLista();
        return "/usuario/modFichaTecnica/lista";
    }

    public String prepareListVersiones() {
        recargarLista();
        return "listaVersion";
    }

    public String prepareListPrincipal() {

        recargarLista();
        return "/usuario/modFichaTecnica/lista";
    }

    public String prepareListDesarrollo() {
        recargarLista();
        return "/administrador/modFichaTecnica/desarrollo/lista";
    }

    public String prepareListPrimeraValidacion() {

        recargarLista();
        return "/administrador/modFichaTecnica/primeraValidacion/lista";
    }

    public String prepareListSegundaValidacion() {
        recargarLista();
        return "/administrador/modFichaTecnica/segundaValidacion/lista";
    }

    public String prepareListusuario() {
        recargarLista();
        return "/usuario/modSaludOcupacional/reporte/lista";
    }

    public String add() {
        try {
            if (fichaTecnicaActual.getRutaImgFrontal() == null) {
                String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";
                fichaTecnicaActual.setRutaImgFrontal(newFileName);
            }
            if (fichaTecnicaActual.getRutaImgPosterior() == null) {
                String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";
                fichaTecnicaActual.setRutaImgPosterior(newFileName);
            }
            if (fichaTecnicaActual.getRutaImgCaracteristica1() == null) {
                String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";
                fichaTecnicaActual.setRutaImgCaracteristica1(newFileName);
            }
            if (fichaTecnicaActual.getRutaImgCaracteristica2() == null) {
                String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";
                fichaTecnicaActual.setRutaImgCaracteristica2(newFileName);
            }
            if (fichaTecnicaActual.getRutaImgCaracteristica3() == null) {
                String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";
                fichaTecnicaActual.setRutaImgCaracteristica3(newFileName);
            }
            if (fichaTecnicaActual.getRutaImgCaracteristica4() == null) {
                String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";
                fichaTecnicaActual.setRutaImgCaracteristica4(newFileName);
            }
            if (fichaTecnicaActual.getRutaFichaLogo() == null) {
                String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";
                fichaTecnicaActual.setRutaFichaLogo(newFileName);
            }
            fichaTecnicaActual.setFechaModificacion(new Date());
            fichaTecnicaActual.setElaborado(usuarioActual);
            fichaTecnicaActual.setVersion(0);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ficha técnica creada", "La ficha técnica con codigo de item " + fichaTecnicaActual.getIdItem() + " fue creada con exito");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            getFichaTecnicaFacade().create(fichaTecnicaActual);
            if (fichaTecnicaActual.getEstado().equals("PRIMERA VALIDACION")) {
                sendMailRegistroPrimeraValidacion();
            }
            recargarLista();
            return "lista?faces-redirect=true";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "lista?faces-redirect=true";
        }
    }

    public String update() {
        try {
            fichaTecnicaActual.setFechaModificacion(new Date());
            fichaTecnicaActual.setElaborado(usuarioActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ficha técnica actualizada", "La ficha técnica con codigo de item " + fichaTecnicaActual.getIdItem() + " fue actualizada con exito");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            getFichaTecnicaFacade().edit(fichaTecnicaActual);
            if (fichaTecnicaActual.getEstado().equals("PRIMERA VALIDACION")) {
                sendMailRegistroPrimeraValidacion();
            }
            recargarLista();
            return "lista?faces-redirect=true";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "lista?faces-redirect=true";
        }
    }

    public String updatePrimeraValidacion() {
        try {
            fichaTecnicaActual.setAprobo(usuarioActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ficha técnica actualizada", "La ficha técnica con codigo de item " + fichaTecnicaActual.getIdItem() + " fue actuializada con exito");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            fichaTecnicaActual.setFechaModificacion(new Date());
            getFichaTecnicaFacade().edit(fichaTecnicaActual);
            if (fichaTecnicaActual.getEstado().equals("TERMINADO")) {
                fichaTecnicaActual.setVersion(fichaTecnicaActual.getVersion() + 1);
                getFichaTecnicaFacade().edit(fichaTecnicaActual);
                fichaTecnicaVersion = fichaTecnicaActual;
                String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";
                fichaTecnicaVersion.setEstado("VERSION");
                fichaTecnicaVersion.setObservaciones("CAMBIO DE VERSIÓN");
                fichaTecnicaVersion.setRutaImgFrontal(newFileName);
                fichaTecnicaVersion.setRutaImgPosterior(newFileName);
                fichaTecnicaVersion.setRutaImgCaracteristica1(newFileName);
                fichaTecnicaVersion.setRutaImgCaracteristica2(newFileName);
                fichaTecnicaVersion.setRutaImgCaracteristica3(newFileName);
                fichaTecnicaVersion.setRutaImgCaracteristica4(newFileName);
                getFichaTecnicaFacade().create(fichaTecnicaVersion);
                sendMailRegistroTerminado();
            }
//            /if(fichaTecnicaActual.getEstado().equals("SEGUNDA VALIDACION")){
//            /    sendMailRegistroSegundaValidacion();
//            /}
            recargarLista();
            return "lista?faces-redirect=true";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "lista?faces-redirect=true";
        }
    }

    public String updateSegundaValidacion() {
        try {
            fichaTecnicaActual.setValidacion(usuarioActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ficha técnica actualizada", "La ficha técnica con codigo de item " + fichaTecnicaActual.getIdItem() + " fue actuializada con exito");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            getFichaTecnicaFacade().edit(fichaTecnicaActual);
            if (fichaTecnicaActual.getEstado().equals("TERMINADO")) {
                fichaTecnicaActual.setVersion(fichaTecnicaActual.getVersion() + 1);
                sendMailRegistroTerminado();
            }
            recargarLista();
            return "lista?faces-redirect=true";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "lista?faces-redirect=true";
        }
    }

    public String delete() {
        try {
            getFichaTecnicaFacade().remove(fichaTecnicaActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "El Registro fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no Eliminado", "El Registro no fue eliminado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        return "lista";
    }

    public String deletePriV() {
        try {
            getFichaTecnicaFacade().remove(fichaTecnicaActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "El Registro fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no Eliminado", "El Registro no fue eliminado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        return "lista?faces-redirect=true";
    }

    private void sendMail(String to, String subject, String body) {
        try {
            emailBean.sendMail(to, subject, body);
            JsfUtil.addSuccessMessage("Mensaje Enviado Exitosamente");
        } catch (NamingException | MessagingException ex) {
            JsfUtil.addErrorMessage("Error sending message " + ex.getClass().getName());
        }
    }

    private void sendMailRegistroPrimeraValidacion() {
        String observaciones = "";
        if (fichaTecnicaActual.getObservaciones() == null) {
            observaciones = "SIN OBSERVACIONES";
        } else {
            observaciones = fichaTecnicaActual.getObservaciones().toUpperCase();
        }
        String subject = "ITEM. " + fichaTecnicaActual.getIdItem() + " PRIMERA VALIDACIÓN FICHA TÉCNICA ";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CODIGO FICHA: ");
        mensaje.append(fichaTecnicaActual.getIdFtFichaTecnica());
        mensaje.append("FTEC");
        mensaje.append("\nCODIGO ITEM: ");
        mensaje.append(fichaTecnicaActual.getIdItem());
        mensaje.append("\nDESCRIPCIÓN: ");
        mensaje.append(fichaTecnicaActual.getDescripcionCorta());
        mensaje.append("\nELABORADOR: ");
        mensaje.append(fichaTecnicaActual.getElaborado().toString().toUpperCase());
        mensaje.append("\nCLIENTE: ");
        mensaje.append(fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase());
        mensaje.append("\nFECHA CAMBIO DE ESTADO: ");
        mensaje.append(fichaTecnicaActual.getFechaModificacion().toLocaleString());
        mensaje.append("\nOBSERVACIONES: ");
        mensaje.append(observaciones);
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail("luis.cabal@cfiprovidencia.com " + " " + " yamileth.collazos@cfiprovidencia.com " + "juan.cordoba@cfiprovidencia.com " + fichaTecnicaActual.getElaborado().getCorreoUsuario(), subject, mensaje.toString());
    }

    private void sendMailRegistroSegundaValidacion() {
        String observaciones = "";
        if (fichaTecnicaActual.getObservaciones() == null) {
            observaciones = "SIN OBSERVACIONES";
        } else {
            observaciones = fichaTecnicaActual.getObservaciones().toUpperCase();
        }
        String subject = "ITEM. " + fichaTecnicaActual.getIdItem() + " SEGUNDA VALIDACIÓN FICHA TÉCNICA ";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CODIGO FICHA: ");
        mensaje.append(fichaTecnicaActual.getIdFtFichaTecnica());
        mensaje.append("FTEC");
        mensaje.append("\nCODIGO ITEM: ");
        mensaje.append(fichaTecnicaActual.getIdItem());
        mensaje.append("\nDESCRIPCIÓN: ");
        mensaje.append(fichaTecnicaActual.getDescripcionCorta());
        mensaje.append("\nELABORADOR: ");
        mensaje.append(fichaTecnicaActual.getElaborado().toString().toUpperCase());
        mensaje.append("\nCLIENTE: ");
        mensaje.append(fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase());
        mensaje.append("\nFECHA CAMBIO DE ESTADO: ");
        mensaje.append(fichaTecnicaActual.getFechaModificacion().toLocaleString());
        mensaje.append("\nOBSERVACIONES: ");
        mensaje.append(observaciones);
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail("luis.cabal@cfiprovidencia.com " + " " + " luz.mejia@cfiprovidencia.com " + " camilo.buitrago@cfiprovidencia.com", subject, mensaje.toString());
    }

    private void sendMailRegistroTerminado() {
        String observaciones = "";
        if (fichaTecnicaActual.getObservaciones() == null) {
            observaciones = "SIN OBSERVACIONES";
        } else {
            observaciones = fichaTecnicaActual.getObservaciones().toUpperCase();
        }
        String subject = "ITEM. " + fichaTecnicaActual.getIdItem() + " FICHA TÉCNICA TERMINADA";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CODIGO FICHA: ");
        mensaje.append(fichaTecnicaActual.getIdFtFichaTecnica());
        mensaje.append("FTEC");
        mensaje.append("\nCODIGO ITEM: ");
        mensaje.append(fichaTecnicaActual.getIdItem());
        mensaje.append("\nDESCRIPCIÓN: ");
        mensaje.append(fichaTecnicaActual.getDescripcionCorta());
        mensaje.append("\nELABORADOR: ");
        mensaje.append(fichaTecnicaActual.getElaborado().toString().toUpperCase());
        mensaje.append("\nCLIENTE: ");
        mensaje.append(fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase());
        mensaje.append("\nFECHA CAMBIO DE ESTADO: ");
        mensaje.append(fichaTecnicaActual.getFechaModificacion().toLocaleString());
        mensaje.append("\nOBSERVACIONES: ");
        mensaje.append(observaciones);
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail("luis.cabal@cfiprovidencia.com " + " camilo.buitrago@cfiprovidencia.com " + " yamileth.collazos@cfiprovidencia.com " + fichaTecnicaActual.getElaborado().getCorreoUsuario(), subject, mensaje.toString());
    }

    private void sendMailRegistroModificacion() {
        String observaciones = "";
        if (fichaTecnicaActual.getObservaciones() == null) {
            observaciones = "SIN OBSERVACIONES";
        } else {
            observaciones = fichaTecnicaActual.getObservaciones().toUpperCase();
        }
        String subject = "ITEM. " + fichaTecnicaActual.getIdItem() + "SOLICITUD DE MODIFICACIÓN DE FICHA TÉCNICA";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CODIGO FICHA: ");
        mensaje.append(fichaTecnicaActual.getIdFtFichaTecnica());
        mensaje.append("FTEC");
        mensaje.append("\nCODIGO ITEM: ");
        mensaje.append(fichaTecnicaActual.getIdItem());
        mensaje.append("\nDESCRIPCIÓN: ");
        mensaje.append(fichaTecnicaActual.getDescripcionCorta());
        mensaje.append("\nELABORADOR: ");
        mensaje.append(fichaTecnicaActual.getElaborado().toString().toUpperCase());
        mensaje.append("\nCLIENTE: ");
        mensaje.append(fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase());
        mensaje.append("\nFECHA CAMBIO DE ESTADO: ");
        mensaje.append(fichaTecnicaActual.getFechaModificacion().toLocaleString());
        mensaje.append("\nOBSERVACIONES: ");
        mensaje.append(observaciones);
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail("luis.cabal@cfiprovidencia.com " + " " + " yamileth.collazos@cfiprovidencia.com " + " " + fichaTecnicaActual.getElaborado().getCorreoUsuario() + " " + usuarioActual.getCorreoUsuario(), subject, mensaje.toString());
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

    public void cargarImgF(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        if (fichaTecnicaActual.getIdFtCliente() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar imagen", "Debes seleccionar primero el cliente para guardar la imagen");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else if (fichaTecnicaActual.getIdItem() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar imagen", "Debes seleccionar primero el item para guardar la imagen");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
          //String newFileName = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase() + "/" + fichaTecnicaActual.getIdItem() + "-f.jpg";
            String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase() + "/" + fichaTecnicaActual.getIdItem() + "-f.jpg";

            fichaTecnicaActual.setRutaImgFrontal(newFileName);
            FileImageOutputStream imageOutput;
            try {
                imageOutput = new FileImageOutputStream(new File(newFileName));
                imageOutput.write(data, 0, data.length);
                imageOutput.close();
            } catch (IOException e) {
                throw new FacesException("Error in writing captured image.", e);
            }
        }
    }

    public void cargarImgP(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        if (fichaTecnicaActual.getIdFtCliente() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar imagen", "Debes seleccionar primero el cliente para guardar la imagen");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else if (fichaTecnicaActual.getIdItem() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar imagen", "Debes seleccionar primero el item para guardar la imagen");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            //String newFileName = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase() + "/" + fichaTecnicaActual.getIdItem() + "-p.jpg";
            String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase() + "/" + fichaTecnicaActual.getIdItem() + "-p.jpg";

            fichaTecnicaActual.setRutaImgPosterior(newFileName);
            FileImageOutputStream imageOutput;
            try {
                imageOutput = new FileImageOutputStream(new File(newFileName));
                imageOutput.write(data, 0, data.length);
                imageOutput.close();
            } catch (IOException e) {
                throw new FacesException("Error in writing captured image.", e);
            }
        }
    }

    public void cargarImgC1(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        if (fichaTecnicaActual.getIdFtCliente() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar imagen", "Debes seleccionar primero el cliente para guardar la imagen");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else if (fichaTecnicaActual.getIdItem() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar imagen", "Debes seleccionar primero el item para guardar la imagen");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            //String newFileName = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase() + "/" + fichaTecnicaActual.getIdItem() + "-c1.jpg";
            String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase() + "/" + fichaTecnicaActual.getIdItem() + "-c1.jpg";

            fichaTecnicaActual.setRutaImgCaracteristica1(newFileName);
            FileImageOutputStream imageOutput;
            try {
                imageOutput = new FileImageOutputStream(new File(newFileName));
                imageOutput.write(data, 0, data.length);
                imageOutput.close();
            } catch (IOException e) {
                throw new FacesException("Error in writing captured image.", e);
            }
        }
    }

    public void cargarImgC2(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        if (fichaTecnicaActual.getIdFtCliente() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar imagen", "Debes seleccionar primero el cliente para guardar la imagen");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else if (fichaTecnicaActual.getIdItem() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar imagen", "Debes seleccionar primero el item para guardar la imagen");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            //String newFileName = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase() + "/" + fichaTecnicaActual.getIdItem() + "-c2.jpg";
            String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase() + "/" + fichaTecnicaActual.getIdItem() + "-c2.jpg";

            fichaTecnicaActual.setRutaImgCaracteristica2(newFileName);
            FileImageOutputStream imageOutput;
            try {
                imageOutput = new FileImageOutputStream(new File(newFileName));
                imageOutput.write(data, 0, data.length);
                imageOutput.close();
            } catch (IOException e) {
                throw new FacesException("Error in writing captured image.", e);
            }
        }
    }

    public void cargarImgC3(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        if (fichaTecnicaActual.getIdFtCliente() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar imagen", "Debes seleccionar primero el cliente para guardar la imagen");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else if (fichaTecnicaActual.getIdItem() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar imagen", "Debes seleccionar primero el item para guardar la imagen");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            // String newFileName = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase() + "/" + fichaTecnicaActual.getIdItem() + "-c3.jpg";
            String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase() + "/" + fichaTecnicaActual.getIdItem() + "-c3.jpg";

            fichaTecnicaActual.setRutaImgCaracteristica3(newFileName);
            FileImageOutputStream imageOutput;
            try {
                imageOutput = new FileImageOutputStream(new File(newFileName));
                imageOutput.write(data, 0, data.length);
                imageOutput.close();
            } catch (IOException e) {
                throw new FacesException("Error in writing captured image.", e);
            }
        }
    }

    public void cargarImgC4(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        if (fichaTecnicaActual.getIdFtCliente() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar imagen", "Debes seleccionar primero el cliente para guardar la imagen");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else if (fichaTecnicaActual.getIdItem() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar imagen", "Debes seleccionar primero el item para guardar la imagen");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            //String newFileName = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase() + "/" + fichaTecnicaActual.getIdItem() + "-c4.jpg";
            String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase() + "/" + fichaTecnicaActual.getIdItem() + "-c4.jpg";

            fichaTecnicaActual.setRutaImgCaracteristica4(newFileName);
            FileImageOutputStream imageOutput;
            try {
                imageOutput = new FileImageOutputStream(new File(newFileName));
                imageOutput.write(data, 0, data.length);
                imageOutput.close();
            } catch (IOException e) {
                throw new FacesException("Error in writing captured image.", e);
            }
        }
    }

    public void cargarFichaLogo(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        if (fichaTecnicaActual.getIdFtCliente() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar pdf", "Debes seleccionar primero el cliente para guardar el pdf");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else if (fichaTecnicaActual.getIdItem() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar pdf", "Debes seleccionar primero el item para guardar el pdf");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            //String newFileName = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase() + "/" + fichaTecnicaActual.getIdItem() + "-logo.pdf";
            String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + fichaTecnicaActual.getIdFtCliente().getNombre().toUpperCase() + "/" + fichaTecnicaActual.getIdItem() + "-logo.jpg";

            fichaTecnicaActual.setRutaFichaLogo(newFileName);
            FileImageOutputStream imageOutput;
            try {
                imageOutput = new FileImageOutputStream(new File(newFileName));
                imageOutput.write(data, 0, data.length);
                imageOutput.close();
            } catch (IOException e) {
                throw new FacesException("Error in writing captured image.", e);
            }
        }
    }

    public void prepararImagenF() throws FileNotFoundException {
        try {
            String rutaImgF = fichaTecnicaActual.getRutaImgFrontal();
            imgF = new DefaultStreamedContent(new FileInputStream(rutaImgF), "image/jpg");
        } catch (FileNotFoundException e) {
            // String rutaImgF = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg";
            String rutaImgF = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";

            imgF = new DefaultStreamedContent(new FileInputStream(rutaImgF), "image/jpg");
        }
    }

    public void prepararImagenP() throws FileNotFoundException {
        try {
            String rutaImgP = fichaTecnicaActual.getRutaImgPosterior();
            imgP = new DefaultStreamedContent(new FileInputStream(rutaImgP), "image/jpg");
        } catch (FileNotFoundException e) {
            //String rutaImgP = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg";
            String rutaImgP = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";

            imgP = new DefaultStreamedContent(new FileInputStream(rutaImgP), "image/jpg");
        }
    }

    public void prepararImagenC1() throws FileNotFoundException {
        try {
            String rutaImgC1 = fichaTecnicaActual.getRutaImgCaracteristica1();
            imgC1 = new DefaultStreamedContent(new FileInputStream(rutaImgC1), "image/jpg");
        } catch (FileNotFoundException e) {
            //String rutaImgC1 = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg";
            String rutaImgC1 = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";

            imgC1 = new DefaultStreamedContent(new FileInputStream(rutaImgC1), "image/jpg");
        }
    }

    public void prepararImagenC2() throws FileNotFoundException {
        try {
            String rutaImgC2 = fichaTecnicaActual.getRutaImgCaracteristica2();
            imgC2 = new DefaultStreamedContent(new FileInputStream(rutaImgC2), "image/jpg");
        } catch (FileNotFoundException e) {
            //String rutaImgC2 = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg";
            String rutaImgC2 = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";

            imgC2 = new DefaultStreamedContent(new FileInputStream(rutaImgC2), "image/jpg");
        }
    }

    public void prepararImagenC3() throws FileNotFoundException {
        try {
            String rutaImgC3 = fichaTecnicaActual.getRutaImgCaracteristica3();
            imgC3 = new DefaultStreamedContent(new FileInputStream(rutaImgC3), "image/jpg");
        } catch (FileNotFoundException e) {
            //String rutaImgC3 = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg";
            String rutaImgC3 = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";

            imgC3 = new DefaultStreamedContent(new FileInputStream(rutaImgC3), "image/jpg");
        }
    }

    public void prepararImagenC4() throws FileNotFoundException {
        try {
            String rutaImgC4 = fichaTecnicaActual.getRutaImgCaracteristica4();
            imgC4 = new DefaultStreamedContent(new FileInputStream(rutaImgC4), "image/jpg");
        } catch (Exception e) {
            //String rutaImgC4 = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-c4.jpg";
            String rutaImgC4 = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-c4.jpg";

            imgC4 = new DefaultStreamedContent(new FileInputStream(rutaImgC4), "image/jpg");
        }
    }

    public void prepareFicha(ActionEvent event) {
        fichaTecnicaActual = new FtFichaTecnica();
        fichaTecnicaActual = (FtFichaTecnica) event.getComponent().getAttributes().get("ficha");
    }

    public void obtenerRutaFrontal() throws IOException {
        if (fichaTecnicaActual.getRutaImgFrontal() == null) {
            addErrorMessage("Documento sin acceso", "el documento no tiene acceso");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect(fichaTecnicaActual.getRutaImgFrontal());
        }
    }

    public StreamedContent getImgF() throws FileNotFoundException {
        if (fichaTecnicaActual.getRutaImgFrontal() == null) {
            //String rutaImgF = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg";
            String rutaImgF = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";

            imgF = new DefaultStreamedContent(new FileInputStream(rutaImgF), "image/jpg");
            return imgF;
        } else {
            prepararImagenF();
            return imgF;
        }
    }

    public void setImgF(StreamedContent imgF) {
        this.imgF = imgF;
    }

    public void borrarImgF() throws FileNotFoundException {
        // fichaTecnicaActual.setRutaImgFrontal("//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg");
        fichaTecnicaActual.setRutaImgFrontal("/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg");

        getImgF();
    }

    public StreamedContent getImgP() throws FileNotFoundException {
        if (fichaTecnicaActual.getRutaImgPosterior() == null) {
            // String rutaImgP = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg";                    
            String rutaImgP = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";

            imgP = new DefaultStreamedContent(new FileInputStream(rutaImgP), "image/jpg");
            return imgP;
        } else {
            prepararImagenP();
            return imgP;
        }
    }

    public void setImgP(StreamedContent imgP) {
        this.imgP = imgP;
    }

    public void borrarImgP() throws FileNotFoundException {
        //fichaTecnicaActual.setRutaImgPosterior("//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg");
        fichaTecnicaActual.setRutaImgPosterior("/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg");

        getImgP();
    }

    public StreamedContent getImgC1() throws FileNotFoundException {
        if (fichaTecnicaActual.getRutaImgCaracteristica1() == null) {
            //String rutaImgC1 = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg";
            String rutaImgC1 = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";

            imgC1 = new DefaultStreamedContent(new FileInputStream(rutaImgC1), "image/jpg");
            return imgC1;
        } else {
            prepararImagenC1();
            return imgC1;
        }
    }

    public void setImgC1(StreamedContent imgC1) {
        this.imgC1 = imgC1;
    }

    public void borrarImgC1() throws FileNotFoundException {
        //fichaTecnicaActual.setRutaImgCaracteristica1("//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg");
        fichaTecnicaActual.setRutaImgCaracteristica1("/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg");

        getImgC1();
    }

    public StreamedContent getImgC2() throws FileNotFoundException {
        if (fichaTecnicaActual.getRutaImgCaracteristica2() == null) {
            //String rutaImgC2 = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg";
            String rutaImgC2 = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";

            imgC2 = new DefaultStreamedContent(new FileInputStream(rutaImgC2), "image/jpg");
            return imgC2;
        } else {
            prepararImagenC2();
            return imgC2;
        }
    }

    public void setImgC2(StreamedContent imgC2) {
        this.imgC2 = imgC2;
    }

    public void borrarImgC2() throws FileNotFoundException {
        //fichaTecnicaActual.setRutaImgCaracteristica2("//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg");
        fichaTecnicaActual.setRutaImgCaracteristica2("/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg");

        getImgC2();
    }

    public StreamedContent getImgC3() throws FileNotFoundException {
        if (fichaTecnicaActual.getRutaImgCaracteristica3() == null) {
            //String rutaImgC3 = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg";
            String rutaImgC3 = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";

            imgC3 = new DefaultStreamedContent(new FileInputStream(rutaImgC3), "image/jpg");
            return imgC3;
        } else {
            prepararImagenC3();
            return imgC3;
        }

    }

    public void setImgC3(StreamedContent imgC3) {
        this.imgC3 = imgC3;
    }

    public void borrarImgC3() throws FileNotFoundException {
        //fichaTecnicaActual.setRutaImgCaracteristica3("//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg");
        fichaTecnicaActual.setRutaImgCaracteristica3("/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg");

        getImgC3();
    }

    public StreamedContent getImgC4() throws FileNotFoundException {
        if (fichaTecnicaActual.getRutaImgCaracteristica4() == null) {
            //String rutaImgC4 = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg";
            String rutaImgC4 = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg";

            imgC4 = new DefaultStreamedContent(new FileInputStream(rutaImgC4), "image/jpg");
            return imgC4;
        } else {
            prepararImagenC4();
            return imgC4;
        }
    }

    public void setImgC4(StreamedContent imgC4) {
        this.imgC4 = imgC4;
    }

    public void borrarImgC4() throws FileNotFoundException {
        fichaTecnicaActual.setRutaImgCaracteristica4("/root/alojamientoFichasImg/02FICHASTECNICAS/" + 0 + "-f.jpg");
        getImgC4();
    }

    public void createFichaPDFAdmin() throws FileNotFoundException, MalformedURLException, DocumentException, IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String url;
        //url = "http:/servidor/saintFichTec/faces/usuario/modFichaTecnica/fileCliente_1.xhtml;jsessionid=" + session.getId() + "?pdf=true";
        //url = "http:/localhost:8080/fichasTecnicas/faces/usuario/modFichaTecnica/fileCliente_1.xhtml;jsessionid=" + session.getId() + "?pdf=true";
        url = "http://167.114.11.220:8080/saint/faces/usuario/modFichaTecnica/fileCliente_1.xhtml;jsessionid=" + session.getId() + "?pdf=true";

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

    public void createFichaPDFCliente() throws FileNotFoundException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String url;
        //url = "http:/servidor:8080/saintFichTec/faces/usuario/modFichaTecnica/fileCliente.xhtml;jsessionid=" + session.getId() + "?pdf=true";
        //url = "http:/localhost:8080/fichasTecnicas/faces/usuario/modFichaTecnica/fileCliente.xhtml;jsessionid=" + session.getId() + "?pdf=true";
        url = "http://167.114.11.220:8080/saint/faces/usuario/modFichaTecnica/fileCliente.xhtml;jsessionid=" + session.getId() + "?pdf=true";

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

    public void createFichaPDFRevisar() throws FileNotFoundException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String url;
        //url = "http:/servidor:8080/saintFichTec/faces/usuario/modFichaTecnica/fileRevision.xhtml;jsessionid=" + session.getId() + "?pdf=true";
        //url = "http:/localhost:8080/fichasTecnicas/faces/usuario/modFichaTecnica/fileRevision.xhtml;jsessionid=" + session.getId() + "?pdf=true";
        url = "http://167.114.11.220:8080/saint/faces/usuario/modFichaTecnica/fileRevision.xhtml;jsessionid=" + session.getId() + "?pdf=true";

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

    public void createFichaPDFEnd() throws FileNotFoundException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String url;
       // /url = "http:/servidor:8080/saintFichTec/faces/usuario/modFichaTecnica/Desarrollo.xhtml;jsessionid=" + session.getId() + "?pdf=true";
        //url = "http://localhost:8080/fichasTecnicas/faces/usuario/modFichaTecnica/Desarrollo.xhtml;jsessionid=" + session.getId() + "?pdf=true";
       url = "http://167.114.11.220:8080/saint/faces/usuario/modFichaTecnica/Desarrollo.xhtml;jsessionid=" + session.getId() + "?pdf=true";

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

    public void obtenerDocumento() throws IOException {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpSession session = (HttpSession) externalContext.getSession(true);
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            response.reset();
            externalContext.redirect(fichaTecnicaActual.getRutaImgFrontal());
        } catch (Exception e) {
            throw new FacesException("Redirection failed");
        }
    }

    /// MODIFICACIONES
    public FtModificacionesFacade getModificacionesFacade() {
        return modificacionesFacade;
    }

    public void setModificacionesFacade(FtModificacionesFacade modificacionesFacade) {
        this.modificacionesFacade = modificacionesFacade;
    }

    public FtModificaciones getModificacionesActual() {
        return modificacionesActual;
    }

    public void setModificacionesActual(FtModificaciones modificacionesActual) {
        this.modificacionesActual = modificacionesActual;
    }

    public List<FtModificaciones> getListaModificaciones() {
        return listaModificaciones = getModificacionesFacade().consultaFicha(fichaTecnicaActual);
    }

    public List<FtModificaciones> getListaModificacionesTodas() {
        return listaModificaciones = getModificacionesFacade().consultaTodas();
    }

    public void setListaModificaciones(List<FtModificaciones> listaModificaciones) {
        this.listaModificaciones = listaModificaciones;
    }

    public void recargarListaModificaciones() {
        listaModificaciones = null;
    }

    public String prepareEditModificaciones() {
        return "/administrador/modSaludOcupacional/reporte/editar?faces-redirect=true";
    }

    public String prepareCreateModificaciones() {
        modificacionesActual = new FtModificaciones();
        return "modificacion/crear?faces-redirect=true";
    }

    public String prepareViewModificaciones() {
        return "ver?faces-redirect=true";
    }

    public String prepareListModificaciones() {
        recargarLista();
        return "/usuario/modFichaTecnica/lista?faces-redirect=true";
    }

    public String addModificaciones() {
        try {
            fichaTecnicaActual.setObservaciones("SE REALIZO SOLICITUD DE MODIFICACIÓN DE ESTÁ FICHA TÉCNICA");
            fichaTecnicaActual.setEstado("MODIFICACION PQS");
            fichaTecnicaActual.setFechaModificacion(new Date());
            modificacionesActual.setUsuario(usuarioActual.toString());
            modificacionesActual.setFtFichaTecnica(fichaTecnicaActual);
            modificacionesActual.setEstado("OBSERVACIÓN");
            modificacionesActual.setFecha(new Date());
            getFichaTecnicaFacade().edit(fichaTecnicaActual);
            getModificacionesFacade().create(modificacionesActual);
            sendMailRegistroModificacion();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud de modificación enviada", "La ficha técnica con codigo de item " + fichaTecnicaActual.getIdItem() + " pasa a estado de desarrollo");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "/usuario/modFichaTecnica/lista?faces-redirect=true";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String updateModificaciones() {
        try {
            getModificacionesFacade().edit(modificacionesActual);
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public void obtenerFichaLogo() throws IOException {
        if (fichaTecnicaActual.getRutaFichaLogo() == null) {
            addErrorMessage("Documento sin acceso", "el documento no tiene acceso");
        } else {
            InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(fichaTecnicaActual.getRutaFichaLogo());
            file = new DefaultStreamedContent(stream, "file/pdf", "downloaded_optimus.pdf");
        }
    }

    private StreamedContent archivoDescarga;

    public StreamedContent getArchivoDescarga() throws FileNotFoundException {
        try {
            if (fichaTecnicaActual.getRutaFichaLogo() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe documento", "El documento no tiene acceso");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return null;
            } else {
                InputStream stream = new FileInputStream(fichaTecnicaActual.getRutaFichaLogo());
                return archivoDescarga = new DefaultStreamedContent(stream, "application/pdf", "file.pdf");
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe documento", "El documento no tiene acceso");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
    }

}
