/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.ActividadNovedad;
import com.proyectoCFIP.entities.Novedad;
import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.ActividadNovedadFacade;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.NovedadFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.imageio.stream.FileImageOutputStream;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author junio
 */
@Named(value = "NovedadCotroller")
@SessionScoped
public class NovedadCotroller implements Serializable {

    @EJB
    private NovedadFacade novedadFacade;
    private Novedad NovedadActual;
    private List<Novedad> listaNovedad;
    private List<Novedad> listaNovedadActual;
    private List<Novedad> listaNovedadCargo;
    private Usuario usuarioActual;
    @EJB
    EmailSessionBean emailBean;

    private StreamedContent imgF = new DefaultStreamedContent();

    private ActividadNovedadFacade ActividadNovedadFacade;
    private ActividadNovedad ActividadNovedadActual;
    private List<ActividadNovedad> listaActividadNovedadActual;

    private Cargos cargosActual;

    public NovedadCotroller() {
    }

    public EmailSessionBean getEmailBean() {
        return emailBean;
    }

    public void setEmailBean(EmailSessionBean emailBean) {
        this.emailBean = emailBean;
    }

    public List<ActividadNovedad> getListaActividadNovedadActual() {
        return listaActividadNovedadActual;
    }

    public void setListaActividadNovedadActual(List<ActividadNovedad> listaActividadNovedadActual) {
        this.listaActividadNovedadActual = listaActividadNovedadActual;
    }

    public ActividadNovedadFacade getActividadNovedadFacade() {
        return ActividadNovedadFacade;
    }

    public void setActividadNovedadFacade(ActividadNovedadFacade ActividadNovedadFacade) {
        this.ActividadNovedadFacade = ActividadNovedadFacade;
    }

    public ActividadNovedad getActividadNovedadActual() {
        return ActividadNovedadActual;
    }

    public void setActividadNovedadActual(ActividadNovedad ActividadNovedadActual) {
        this.ActividadNovedadActual = ActividadNovedadActual;
    }

    public NovedadFacade getNovedadFacade() {
        return novedadFacade;
    }

    public void setNovedadFacade(NovedadFacade novedadFacade) {
        this.novedadFacade = novedadFacade;
    }

    public Novedad getNovedadActual() {
        return NovedadActual;
    }

    public void setNovedadActual(Novedad NovedadActual) {
        this.NovedadActual = NovedadActual;
    }

    public List<Novedad> getListaNovedad() {
        return listaNovedad = getNovedadFacade().consultaTodo();
    }

    public List<Novedad> getListaNovedadCargo() {
        return listaNovedadCargo;
    }

    public void setListaNovedadCargo(List<Novedad> listaNovedadCargo) {
        this.listaNovedadCargo = listaNovedadCargo;
    }

    public List<Novedad> getListaPorTipoDeUsuario2() {
        return listaNovedadCargo = getNovedadFacade().consultaEstado();
    }

    public void setListaNovedad(List<Novedad> listaNovedad) {
        this.listaNovedad = listaNovedad;
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

    public List<Novedad> getListaNovedadActual() {
        return listaNovedadActual;
    }

    public void setListaNovedadActual(List<Novedad> listaNovedadActual) {
        this.listaNovedadActual = listaNovedadActual;
    }

    public void recargarLista() {
        listaNovedad = null;
    }

        public Date getFechaActual() {
        return new Date();
    }
    public Novedad getNovedad(java.lang.Integer id) {
        return getNovedadFacade().find(id);
    }

    public String prepareListNovedad() {
        return "/administrador/modFichaTecnica/novedades/lista";
    }

    public String prepareAdminNovedad() {
        return "/administrador/modFichaTecnica/novedades/administrar";
    }

    public String prepareVerNovedad() {
        return "/administrador/modFichaTecnica/novedades/ver";
    }

    public String prepareCreateNovedad() {
        NovedadActual = new Novedad();
        return "/administrador/modFichaTecnica/novedades/crear";
    }

    public String prepareEditNovedad() {
        ActividadNovedadActual = new ActividadNovedad();
        listaActividadNovedadActual = new ArrayList<>();
//        listaActividadNovedadActual = new ArrayList<>();
//        listaNovedadActual = new ArrayList<>();

        return "/administrador/modFichaTecnica/novedades/editar";
    }

    public String prepareEditActividadNovedad() {
        return "/administrador/modFichaTecnica/actiNovedades/editar";
    }

    public String prepareCreate() {
        NovedadActual = new Novedad();
        ActividadNovedadActual = new ActividadNovedad();
        listaNovedadActual = new ArrayList<>();
        return "/administrador/modCalidad/cargos/create4";
    }

    public String add() {
        try {
            NovedadActual.setFechaActual(getFechaActual());
            NovedadActual.setIdUsuarioCreacion(usuarioActual);
            NovedadActual.setEstado("ABIERTO");
            addSuccessMessage("Plan de acción creado", "El plan de acción fue creado con consecutivo No. ");
            getNovedadFacade().create(NovedadActual);
            sendMailRegistroTec();
            return "lista?faces-redirect=true";
        } catch (Exception e) {
            addErrorMessage("Error Al crear " + e.getClass().getName(), "Message: " + e.getMessage());

            return "lista?faces-redirect=true";

        }
    }

    public void addNov() {
        try {
//            ActividadNovedadActual.setIdUsuarioCreacion(usuarioActual);
            ActividadNovedadActual.setIdNovedad(NovedadActual);
            ActividadNovedadActual.setEstadoNoConformeDiseño("DESARROLLO");
            getActividadNovedadFacade().create(ActividadNovedadActual);
            listaActividadNovedadActual.add(ActividadNovedadActual);
            ActividadNovedadActual = new ActividadNovedad();
            addSuccessMessage("ActividadNovedad", "La ActividadNovedad con el codigo " + ActividadNovedadActual.getIdActividadNovedad() + " fue creada exitosamente");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }

    public void add2() {

        try {
            NovedadActual.setIdUsuarioCreacion(usuarioActual);
            NovedadActual.setEstado("ABIERTO");
            getNovedadFacade().create(NovedadActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado", "El Registro fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no Eliminado", "El Registro no fue eliminado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public String editar() {
        try {
            NovedadActual.setFechaActual(getFechaActual());
            NovedadActual.setIdUsuarioCreacion(usuarioActual);
            getNovedadFacade().edit(NovedadActual);
            addSuccessMessage("Novedad", "La Novedad con el codigo " + NovedadActual.getIdNovedad() + " fue editada");
            sendMailAdminNov();
            return "lista?faces-redirect=true";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "lista?faces-redirect=true";

        }
    }

    public String prepareAspecto() {
        NovedadActual = new Novedad();
        listaNovedadActual = new ArrayList<>();
        return "/administrador/modCalidad/cargos/editCargo/editNovedad";

    }

    public String update() {
        try {

            getNovedadFacade().edit(NovedadActual);
            recargarLista();
            return "/usuario/modBiblioteca/ListarNovedad/lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/usuario/modBiblioteca/ListarNovedad/lista";
        }
    }

    public String updateMantenimiento() {
        try {

            getNovedadFacade().edit(NovedadActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "el estado del libro", "ha sido editado satisfactoriamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();

            return "/administrador/reservaNovedad/editar/editar";
        } catch (Exception e) {
            addErrorMessage("Error Al Editar " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/administrador/reservaNovedad/editar/editar";
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

    public void deleteNovedad() {
        try {
            getNovedadFacade().remove(NovedadActual);
            listaNovedadActual.remove(NovedadActual);
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
            getNovedadFacade().remove(NovedadActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "El Registro fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no Eliminado", "El Registro no fue eliminado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
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
        String subject = "NOVEDAD GENARADA CON EL CONSECUTIVO No." + NovedadActual.getItem();
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("DESCRIPCION DE LA NOVEDAD  ");
        mensaje.append(NovedadActual.getDescripcionNovedad());
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(NovedadActual.getIdUsuarioReporta().toString().toUpperCase());
        mensaje.append("\n CLIENTE: ");
        mensaje.append(NovedadActual.getIdFtCliente().getNombre().toUpperCase());
        mensaje.append("\n OP: ");
        mensaje.append(NovedadActual.getOp());
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail(" juan.varela@cfiprovidencia.com  " + " nathalia.yusti@cfiprovidencia.com " + " " + " camilo.buitrago@cfiprovidencia.com " + "claudia.canar@cfiprovidencia.com " + " juan.cordoba@cfiprovidencia.com " + " " + NovedadActual.getIdUsuarioReporta().getCorreoUsuario(), subject, mensaje.toString());
    }

    private void sendMailAdminNov() {
        String subject = "NOVEDAD ACTUALIZADA CON EL CONSECUTIVO No." + NovedadActual.getItem();
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("PARA MAS INFO. INGRESA A SAINT AL MODULO DE NOVEDADES");
        mensaje.append("DESCRIPCION DE LA NOVEDAD  ");
        mensaje.append(NovedadActual.getDescripcionNovedad());
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(NovedadActual.getIdUsuarioReporta().toString().toUpperCase());
        mensaje.append("\n CLIENTE: ");
        mensaje.append(NovedadActual.getIdFtCliente().getNombre().toUpperCase());
        mensaje.append("\n OP: ");
        mensaje.append(NovedadActual.getOp());
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail(" juan.varela@cfiprovidencia.com  " + " nathalia.yusti@cfiprovidencia.com " + " camilo.buitrago@cfiprovidencia.com " + "claudia.canar@cfiprovidencia.com " + " juan.cordoba@cfiprovidencia.com " + " " + NovedadActual.getIdUsuarioReporta().getCorreoUsuario(), subject, mensaje.toString());
    }

    public void intit() {
        sendMailNovAtrazada();
    }

    private void sendMailNovAtrazada() {
        String subject = "NOVEDAD SIN MOVIMIENTO O ATRAZADA CON EL CONSECUTIVO No." + NovedadActual.getItem();
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("PARA MAS INFO. INGRESA A SAINT AL MODULO DE NOVEDADES");
        mensaje.append("DESCRIPCION DE LA NOVEDAD  ");
        mensaje.append(NovedadActual.getDescripcionNovedad());
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(NovedadActual.getIdUsuarioReporta().toString().toUpperCase());
        mensaje.append("\n CLIENTE: ");
        mensaje.append(NovedadActual.getIdFtCliente().getNombre().toUpperCase());
        mensaje.append("\n OP: ");
        mensaje.append(NovedadActual.getOp());
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail(" juan.varela@cfiprovidencia.com  " + " nathalia.yusti@cfiprovidencia.com " + " camilo.buitrago@cfiprovidencia.com " + "claudia.canar@cfiprovidencia.com " + " juan.cordoba@cfiprovidencia.com " + " " + NovedadActual.getIdUsuarioReporta().getCorreoUsuario(), subject, mensaje.toString());
    }

    public void cargarImgF(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        if (NovedadActual.getIdFtCliente() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar imagen", "Debes seleccionar primero el cliente para guardar la imagen");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else if (NovedadActual.getItem() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al guardar imagen", "Debes seleccionar primero el item para guardar la imagen");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            //String newFileName = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + NovedadActual.getIdFtCliente().getNombre().toUpperCase() + "/" + NovedadActual.getItem() + "-f.jpg";
            String newFileName = "/root/alojamientoNovedades/07NOVEDADES/novedades/02FICHASTECNICAS/" + NovedadActual.getIdFtCliente().getNombre().toUpperCase() + "/" + NovedadActual.getItem() + "-f.jpg";

            NovedadActual.setEvidenciaFoto(newFileName);
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
            String rutaImgF = NovedadActual.getEvidenciaFoto();
            imgF = new DefaultStreamedContent(new FileInputStream(rutaImgF), "image/jpg");
        } catch (FileNotFoundException e) {
            // String rutaImgF = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg";
            String rutaImgF = "/root/alojamientoNovedades/07NOVEDADES/novedades/02FICHASTECNICAS/fotos/" + 0 + "-f.jpg";

            imgF = new DefaultStreamedContent(new FileInputStream(rutaImgF), "image/jpg");
        }
    }

    public StreamedContent getImgF() throws FileNotFoundException {
        if (NovedadActual.getEvidenciaFoto() == null) {
            //String rutaImgF = "//172.16.0.241/Volume_1/02FICHASTECNICAS/" + 0 + "-f.jpg";
            String rutaImgF = "/root/alojamientoNovedades/07NOVEDADES/novedades/02FICHASTECNICAS/fotos/" + 0 + "-f.jpg";

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
        NovedadActual.setEvidenciaFoto("/root/alojamientoNovedades/07NOVEDADES/novedades/02FICHASTECNICAS/fotos/" + 0 + "-f.jpg");

        getImgF();
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

    @FacesConverter(forClass = Novedad.class)
    public static class NovedadCotrollerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NovedadCotroller controller = (NovedadCotroller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "NovedadCotroller");
            return controller.getNovedad(getKey(value));
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
            if (object instanceof Novedad) {
                Novedad o = (Novedad) object;
                return getStringKey(o.getIdNovedad());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Novedad.class.getName());
            }
        }

    }

}
