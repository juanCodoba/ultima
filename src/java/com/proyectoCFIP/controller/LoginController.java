/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.UsuarioFacade;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    private String username;
    private String password;
    private Usuario usuario;
    private Usuario usuarioActual;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    EmailSessionBean emailBean;

    /*public LoginController() {
     HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }*/
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public String getUsername() {
        return username;
    }

    private UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticated() {
        return getRequest().getUserPrincipal() != null;
    }

    public Principal getPrincipal() {
        return getRequest().getUserPrincipal();
    }

    private HttpServletRequest getRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object request = externalContext.getRequest();
        return request instanceof HttpServletRequest ? (HttpServletRequest) request : null;
    }

    private String getLogueado() {
        return getPrincipal().getName();
    }

    public Usuario getUserLogueado() {
        return getUsuarioFacade().findByCorreoElectronico(getLogueado());
    }

    public boolean isUserDisn() {
        return getRequest().isUserInRole("DISN");
    }

    public boolean isUserDesp() {
        return getRequest().isUserInRole("DESP");
    }

    public boolean isAdminCalidad() {
        return getRequest().isUserInRole("ADMINCDAD");
    }

    public boolean isAdminMante() {
        return getRequest().isUserInRole("MANTE");
    }

    public boolean isAdminConfe() {
        return getRequest().isUserInRole("CONFE");
    }

    public boolean isAdminCalidoso() {
        return getRequest().isUserInRole("ADMINCDOSO");
    }

    public boolean isUserCaliadad() {
        return getRequest().isUserInRole("CALIDAD");
    }

    public boolean isAdmin() {
        return getRequest().isUserInRole("ADMIN");
    }

    public boolean isSaludOcupacional() {
        return getRequest().isUserInRole("OCU");
    }

    public boolean isFichaTecnica() {
        return getRequest().isUserInRole("FT");
    }

    public boolean isFichaTecnicaDESA() {
        return getRequest().isUserInRole("FTDESA");
    }

    public boolean isFichaTecnicaPRINT() {
        return getRequest().isUserInRole("FTPRINT");
    }

    public boolean isFichaTecnicaSCAM() {
        return getRequest().isUserInRole("FTSCAM");
    }

    public boolean isFichaTecnicaVALI1() {
        return getRequest().isUserInRole("FTVALI1");
    }

    public boolean isFichaTecnicaVALI2() {
        return getRequest().isUserInRole("FTVALI2");
    }

    public boolean isUser() {
        return getRequest().isUserInRole("USER");
    }

    public boolean isDocente() {
        return getRequest().isUserInRole("DOCENTE");
    }

    public boolean isAuditor() {
        return getRequest().isUserInRole("AUD");
    }

    public boolean isBibliotecario() {
        return getRequest().isUserInRole("BIBLIOTEC");
    }

    public boolean isAuditorInterno() {
        return getRequest().isUserInRole("AUDITORI");
    }

    public boolean isJefe() {
        return getRequest().isUserInRole("JEFE");
    }

    public boolean isComercial() {
        return getRequest().isUserInRole("COMR");
    }

    public String login() {
        try {
            //Login via the Servlet Context
            getRequest().login(username, password);
            usuario = getUserLogueado();
            limpiar();
            if (usuario.getEstadoUsuario() == false) {
                logout();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Inactivo", null));
                return "/index";
            } else if (usuario.getEstadoUsuario() == isJefe()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@ ", usuario.toString()));
                return "usuario/desayuno/reporte_diario";
            }
            //Redirigir a la página de portada
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@ ", usuario.toString()));
            return "usuario/modulos";

        } catch (ServletException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede iniciar sesión", "Los datos ingresados no son correctos, por favor intentalo nuevamente"));
            return null;
        }
    }

    public String loginFT() {
        try {
            //Login ficha tecnica
            getRequest().login(username, password);
            usuario = getUserLogueado();
            limpiar();
            if (usuario.getEstadoUsuario() == false) {
                logout();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Inactivo", null));
                return "/indexFichaTecnica";
            }
            //Redirigir a la página de portada
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@ ", usuario.toString()));
            return "usuario/modFichaTecnica/lista";

        } catch (ServletException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede iniciar sesión", "Los datos ingresados no son correctos, por favor intentalo nuevamente"));
            return null;
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();
            limpiar();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Haz cerrado sesión correctamente", "Gracias por usar los servicios de SaINT, hasta pronto");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "/index";
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
            return "/AccessDenied";
        }
    }

    public String preparePaginaPrincipalCalidad() {
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Acceso Denegado", "Nos encontramos trabajando en este módulo, pronto se encontrara habilitado");
        //RequestContext.getCurrentInstance().showMessageInDialog(message);
        //return "eleccionModulo";
        return "/usuario/modCalidad/paginaPrincipal";
    }

    public String preparePaginaPrincipalSiesa() {
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Acceso Denegado", "Nos encontramos trabajando en este módulo, pronto se encontrara habilitado");
        //RequestContext.getCurrentInstance().showMessageInDialog(message);
        return "/usuario/modDocumentoSiesa/listaDocumentoSiesa";
    }

    public String ayuda() {
        return "/ayuda";
    }

    public String preparePaginaSaludOcupacional() {
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede ingresar a este modulo", "Este modulo se encuentra desactivado por el momento");
        //RequestContext.getCurrentInstance().showMessageInDialog(message);
        return "modSaludOcupacional/paginaPrincipal";
    }

    public String preparePaginaFT() {
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede ingresar a este modulo", "Este modulo se encuentra desactivado por el momento");
        //RequestContext.getCurrentInstance().showMessageInDialog(message);
        return "/usuario/modFichaTecnica/lista";
    }

    public String preparePaginaAuditoria() {
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede ingresar a este modulo", "Este modulo se encuentra desactivado por el momento");
        //RequestContext.getCurrentInstance().showMessageInDialog(message);
        return "modAuditoria/planAuditoria/lista";
    }

    public String preparePaginaBiblioteca() {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede ingresar a este modulo", "Este modulo se encuentra desactivado por el momento");
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
//        return null;
        return "modBiblioteca/ListarLibro/lista";
    }

    public String preparePaginaManual() {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede ingresar a este modulo", "Este modulo se encuentra desactivado por el momento");
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
//        return null;
        return "modCalidad/cargos/list";
    }

    public String preparePaginaPestel() {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede ingresar a este modulo", "Este modulo se encuentra desactivado por el momento");
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
//        return null;
        return "modPestel/list";
    }

    public String preparePaginaDesayuno() {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede ingresar a este modulo", "Este modulo se encuentra desactivado por el momento");
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
//        return null;
        return "desayuno/paginaPrincipal";
    }

    public String preparePaginaPlanilla() {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede ingresar a este modulo", "Este modulo se encuentra desactivado por el momento");
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
//        return null;
        return "modPlanilla/list";
    }

    public String preparePaginaMasHerramientas() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Acceso Denegado", "Modulo no disponible");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        //return "masHerramientas/paginaPrincipalMasHerramientas";
        return null;
    }

    public String preparePaginaReporteSiesa() {
        if (isDocente()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede ingresar a este modulo", "Este modulo se encuentra desactivado para usuarios docentes");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        } else {
            return "/usuario/modSoporteSiesa/paginaPrincipal";
        }
//            return "/usuario/modSoporteSiesa/paginaPrincipal";

    }

    public String preparePaginaEdificios() {
        return "/usuario/modManteEdificios/paginaPrincipal";
    }

    public String preparePaginaPrincipal() {
        return "paginaPrincipal";
    }

    public void preparePaginaPrincipalMaquinas() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede ingresar a este modulo", "Este modulo se encuentra desactivado por el momento");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        /*if(isDocente()){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede ingresar a este modulo", "Este modulo se encuentra desactivado para usuarios docentes");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }else{
            return "paginaPrincipalMaquinasConfecciones";
         }*/
    }

    public String preparePaginaEleccionModulo() {
        return "/User/eleccionModulo";
    }

    public String prepareEditContra() {
        return "/usuario/configUser/userEditPassword";
    }

    public String updateContrasenaUser() {
        try {
            sendMailCambioCorreo();
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Has restablecido tu contraseña", "Hemos enviado a tu correo electrónico tus nuevos datos de acceso."));
            usuarioActual.setContraUsuario(password);
            usuarioActual.setEstadoContra(false);
            getUsuarioFacade().edit(usuarioActual);
            limpiar();
            return "/usuario/modulos";
        } catch (Exception e) {
            return null;
        }
    }

    private void limpiar() {
        username = "";
        password = "";
    }

    //correo cambio de contraseña//
    private void sendMail(String to, String subject, String body) {
        try {
            emailBean.sendMail(to, subject, body);
            JsfUtil.addSuccessMessage("Mensaje Enviado Exitosamente");
        } catch (NamingException | MessagingException ex) {
            JsfUtil.addErrorMessage("Error sending message " + ex.getClass().getName());
        }
    }

    public String volverIndex() {
        if (isAuthenticated() == false) {
            return "/index";
        } else {
            return "/usuario/modulos";
        }
    }

    public boolean isCambiarContra() {
        return getUserLogueado().getEstadoContra() == true;
    }

    public boolean isNoCambiarContra() {
        return getUserLogueado().getEstadoContra() == false;
    }

    public boolean isEstadoContra() {
        return getUserLogueado().getEstadoContra() == false;
    }

    private void sendMailCambioCorreo() {
        String subject = "ATENCIÓN!! SE RESTABLECIÓ EL ACCESO A LA PLATAFORMA SAINT ";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Buen dia ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append(".");
        mensaje.append("\n\nSus nuevos datos de ingreso a SaINT son: ");
        mensaje.append("\n\nCorreo: ");
        mensaje.append(usuarioActual.getCorreoUsuario());
        mensaje.append("\nContraseña: ");
        mensaje.append(password);
        mensaje.append("\n\nTu opinión cuenta, no te olvides de calificar nuestros servicios, esto nos ayudara a mejorar. ");
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail(usuarioActual.getCorreoUsuario(), subject, mensaje.toString());
    }
}
