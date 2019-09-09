/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.Documentos;
import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.entities.Roles;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.entities.Valoracion;
import com.proyectoCFIP.sessions.CargosFacade;
import com.proyectoCFIP.sessions.DocumentosFacade;
import com.proyectoCFIP.sessions.RolesFacade;
import com.proyectoCFIP.sessions.UsuarioFacade;
import com.proyectoCFIP.sessions.ValoracionFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class UsuarioController implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private RolesFacade rolFacade;
    @EJB
    private CargosFacade cargosFacade;
    @EJB
    private DocumentosFacade documentosFacade;
    @EJB
    private ValoracionFacade valoracionFacade;
    private List<Roles> listaRol = null;
    private Usuario usuarioActual;
    private List<Usuario> listaUsuarioReporte = new ArrayList<Usuario>();
    private List<Usuario> listaUsuarios;
    private Documentos documentosActual;
    private List<Valoracion> listaValoracion = new ArrayList<>();
    private Valoracion valoracionActual;
    private boolean permisoCalidad;
    
    private Procesos procesoActual;

    //Roles
    public RolesFacade getRolFacade() {
        return rolFacade;
    }

    public void setRolFacade(RolesFacade rolFacade) {
        this.rolFacade = rolFacade;
    }

    public List<Roles> getListaRol() {
        return listaRol;
    }

    public List<Roles> getListaRolSelectOne() {
        return rolFacade.findAll();
    }

    public void setListaRol(List<Roles> listaRol) {
        this.listaRol = listaRol;
    }
    //documentos

    public Documentos getDocumentosActual() {
        return documentosActual;
    }

    public void setDocumentosActual(Documentos documentosActual) {
        this.documentosActual = documentosActual;
    }

    public DocumentosFacade getDocumentosFacade() {
        return documentosFacade;
    }

    public void setDocumentosFacade(DocumentosFacade documentosFacade) {
        this.documentosFacade = documentosFacade;
    }

    public boolean isPermisoCalidad() {
        return permisoCalidad;
    }

    public void setPermisoCalidad(boolean permisoCalidad) {
        this.permisoCalidad = permisoCalidad;
    }

    public Procesos getProcesoActual() {
        return procesoActual;
    }

    public void setProcesoActual(Procesos procesoActual) {
        this.procesoActual = procesoActual;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    
    
    //Select one Cargos
    public CargosFacade getCargosFacade() {
        return cargosFacade;
    }

    public void setCargosFacade(CargosFacade cargosFacade) {
        this.cargosFacade = cargosFacade;
    }

    public List<Cargos> getListaCargosSelectOne() {
        return getCargosFacade().findAll();
    }

    public UsuarioController() {
    }

    public List<Usuario> getListaUsuarios() {
        return getUsuarioFacade().consultaTodos();
    }

    public List<Usuario> getListaUsuariosEstado() {
        return getUsuarioFacade().consultaUsuarioEstado();
    }

    public List<Usuario> getListaUsuarioCargo() {
        return getUsuarioFacade().consultaUsuarioCargos();
    }

    public List<Usuario> getListaUsuarioCargoEs() {
        return getUsuarioFacade().consultaUsuarioCargosEst();
    }

    public List<Usuario> getListaUsuarioCargoAu() {
        return getUsuarioFacade().consultaUsuarioAutonoma();
    }

    public List<Usuario> getListaUsuarioCargoDo() {
        return getUsuarioFacade().consultaUsuarioDocente();
    }

    public List<Usuario> getListaUsuarioCargoTe() {
        return getUsuarioFacade().consultaUsuarioTecnicos();
    }

    public List<Usuario> getListaUsuarioRol12() {
        return getUsuarioFacade().consultaUsuariorRoles();
    }
    
        public List<Usuario> getListUsuarioComer() {
        return getUsuarioFacade().consultaUsuarioComerciales();
    }

    public List<Usuario> getListaUsuariosRol() {
        return listaUsuarios = getUsuarioFacade().consultaUsuarioRol(new Roles("CALIDAD", "Usuario calidad"));

    }

    public List<Usuario> getListaUsuariosDocumentos() {
        return listaUsuarios = getUsuarioFacade().consultaUsuarioDocumento(documentosActual);
    }

    //Valorarion 
    public Valoracion getValoracionActual() {
        return valoracionActual;
    }

    public Valoracion getValoracionNew() {
        return valoracionActual = new Valoracion();
    }

    public void setValoracionActual(Valoracion valoracionActual) {
        this.valoracionActual = valoracionActual;
    }

    public ValoracionFacade getValoracionFacade() {
        return valoracionFacade;
    }

    public void setValoracionFacade(ValoracionFacade valoracionFacade) {
        this.valoracionFacade = valoracionFacade;
    }

    public List<Valoracion> getListaValoracion() {
        return getValoracionFacade().consultaFecha();

    }

    public String prepareViewValoracion() {
        valoracionActual = new Valoracion();
        valoracionActual.setValoracion(1);
        return "/usuario/modSoporteIT/valoracionesGenerales/listaValoraciones";
    }

    public void prepareCreateValoracion() {
        valoracionActual = new Valoracion();
    }

    public void addValoracion() {
        try {
            valoracionActual.setIdUsuario(usuarioActual);
            valoracionActual.setFechaValoracion(new Date());
            getValoracionFacade().create(valoracionActual);
            recargarLista();
            addSuccessMessage("Servicio valorado", "Gracias por valorar nuestro servicio de soporte it");
            valoracionActual = new Valoracion();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public String updateUsuarioEmail() {
        try {
            getUsuarioFacade().edit(usuarioActual);
            addSuccessMessage("Correo electronico editado exitosamente", "El correo del usuario " + usuarioActual.toString() + " fue actualizado exitosamente");
            return "editarUsuario";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String updateUsuarioContra() {
        try {
            getUsuarioFacade().edit(usuarioActual);
            recargarLista();
            addSuccessMessage("Contraseña editada exitosamente", "La contraseña del usuario " + usuarioActual.toString() + " fue actualizada exitosamente");
            return "editarUsuario";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String prepareEditContra() {
        return "editarContraUsuario";
    }

    public String prepareEditEmail() {
        return "editarCorreoUsuario";
    }

    public String prepareEditDistribucion() {
        return "adminEditDistribucion";
    }

    public String editDistribucion() {
        try {
            getDocumentosFacade().edit(documentosActual);
            return "adminViewDistribucionDocumento";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public List<Usuario> getListaUsuarioReporte() {
        listaUsuarios = usuarioFacade.findAll();
        return listaUsuarioReporte;
    }

    public void setListaUsuarioReporte(List<Usuario> listaUsuarioReporte) {
        this.listaUsuarioReporte = listaUsuarioReporte;
    }

    private void recargarLista() {
        listaUsuarios = null;
    }

    public String prepareCreate() {
        usuarioActual = new Usuario();
        return "/administrador/modSoporteIT/usuariosSistema/crearUsuario";
    }

    public void prepareRolCalidad(ActionEvent event) {
        usuarioActual = new Usuario();
        usuarioActual = (Usuario) event.getComponent().getAttributes().get("usuario");
    }

    public void prepareEditRolCalidad() {
        for (Roles items : usuarioActual.getRolesList()) {
            if (items.getIdRol().equals("CALIDAD")) {
                permisoCalidad = true;
                usuarioActual.getRolesList().remove(new Roles("CALIDAD"));
                break;
            } else {
                permisoCalidad = false;
            }
        }
    }

    public String prepareEdit() {
        listaRol = usuarioActual.getRolesList();
        return "/administrador/modSoporteIT/usuariosSistema/editarUsuario";
    }

    public String prepareView() {
        return "";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modSoporteIT/usuariosSistema/listaUsuario";
    }

    public String addUsuario() {
        try {
            listaRol.add(new Roles("USER"));
            usuarioActual.setRolesList(listaRol);
            usuarioActual.setEstadoUsuario(true);
            getUsuarioFacade().create(usuarioActual);
            recargarLista();
            addSuccessMessage("Nuevo usuario guardado", "El usuario " + usuarioActual.toString() + " fue guardado exitosamente");
            return "/administrador/modSoporteIT/usuariosSistema/listaUsuario";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public void validarCorreo(FacesContext contex, UIComponent component, Object value) throws ValidatorException {
        Usuario usuarioConsulta = getUsuarioFacade().findByCorreoElectronico((String) value);
        if (usuarioConsulta != null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo repetido", "La cuenta de correo electronico se encuentra registrada"));
        } else {
            String correo = (String) value;
            usuarioActual.setCorreoUsuario(correo);
        }
    }

    public String updateUsuarioCalidad() {
        try {
            if (permisoCalidad == true) {
                usuarioActual.getRolesList().add(new Roles("CALIDAD"));
                usuarioActual.setRolesList(usuarioActual.getRolesList());
                getUsuarioFacade().edit(usuarioActual);
                addSuccessMessage("Usuario modificado", "Al usuario " + usuarioActual.toString() + " se le permitirá visualizar documentos de calidad");
            } else if (permisoCalidad == false) {
                usuarioActual.getRolesList().remove(new Roles("CALIDAD"));
                usuarioActual.setRolesList(usuarioActual.getRolesList());
                getUsuarioFacade().edit(usuarioActual);
                addSuccessMessage("Usuario modificado", "Al usuario " + usuarioActual.toString() + " no se le permitirá visualizar documentos de calidad");
            }
            return "editarPermisoDocumento";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String updateUsuario() {
        try {
            usuarioActual.setRolesList(listaRol);
            listaRol.add(new Roles("USER"));
            usuarioActual.setRolesList(listaRol);
            getUsuarioFacade().edit(usuarioActual);
            recargarLista();
            addSuccessMessage("Usuario editado", "los datos del usuario " + usuarioActual.toString() + " fueron actualizados exitosamente");
            return "/administrador/modSoporteIT/usuariosSistema/listaUsuario";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteUsuario() {
        try {
            getUsuarioFacade().remove(usuarioActual);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        return "List";
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

    public Usuario getUsuario(java.lang.Integer id) {
        return getUsuarioFacade().find(id);
    }

    @FacesConverter(forClass = Usuario.class, value = "usuarioConverter")
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getIdUsuario());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Usuario.class.getName());
            }
        }

    }

    public List<Valoracion> getListaReporte() {
        listaValoracion = new ArrayList<>();

        return listaValoracion = getValoracionFacade().findAll();
    }
    JasperPrint jasperPrint;

    public void init() throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(getListaReporte());
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportValoracion.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
    }

    public void PDF(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=reporte.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void DOCX(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.docx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRDocxExporter docxExporter = new JRDocxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void XLSX(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter docxExporter = new JRXlsxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void ODT(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.odt");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JROdtExporter docxExporter = new JROdtExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void PPT(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pptx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRPptxExporter docxExporter = new JRPptxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

}
