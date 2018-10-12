/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.HistorialOtroDispositivo;
import com.proyectoCFIP.entities.Modelo;
import com.proyectoCFIP.entities.OtroDispositivo;
import com.proyectoCFIP.entities.Seccion;
import com.proyectoCFIP.entities.TipoActualizacion;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.HistorialOtroDispositivoFacade;
import com.proyectoCFIP.sessions.ModeloFacade;
import com.proyectoCFIP.sessions.OtroDispositivoFacade;
import com.proyectoCFIP.sessions.SeccionFacade;
import com.proyectoCFIP.sessions.UsuarioFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class OtroDispositivoController implements Serializable{

  
    @EJB
    private OtroDispositivoFacade otroDispositivoFacade;
    @EJB
    private ModeloFacade modeloFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private SeccionFacade seccionFacade;
    @EJB
    private HistorialOtroDispositivoFacade historialOtroDispositivoFacade;
    private List<OtroDispositivo> listaOtroDispositivo = null;
    private OtroDispositivo otroDispositivoActual;
    private HistorialOtroDispositivo historialOtroDispositivoActual;
    private List<HistorialOtroDispositivo> listaHistorialOtroDispositivo;
    private Usuario usuarioActual;
   
    
        
    // Actualizar Otro dispositivo

    public OtroDispositivoFacade getOtroDispositivoFacade() {
        return otroDispositivoFacade;
    }

    public void setOtroDispositivoFacade(OtroDispositivoFacade otroDispositivoFacade) {
        this.otroDispositivoFacade = otroDispositivoFacade;
    }

    public OtroDispositivo getOtroDispositivoActual() {
        return otroDispositivoActual;
    }

    public void setOtroDispositivoActual(OtroDispositivo otroDispositivoActual) {
        this.otroDispositivoActual = otroDispositivoActual;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    public String updateOtroDispositivo() {
        try {
            historialOtroDispositivoActual.setIdOtroDispositivo(otroDispositivoActual);
            if(otroDispositivoActual.getIdSeccion()==null){
                historialOtroDispositivoActual.setSeccion("PENDIENTE");
            }else{
                historialOtroDispositivoActual.setSeccion(otroDispositivoActual.getIdSeccion().toString());
            }
            if(otroDispositivoActual.getIdUsuario()==null){
                historialOtroDispositivoActual.setUsuario("PENDIENTE");
            }else{
                historialOtroDispositivoActual.setUsuario(otroDispositivoActual.getIdUsuario().toString());
            }
            historialOtroDispositivoActual.setUsuario(otroDispositivoActual.getIdUsuario().toString());
            
            historialOtroDispositivoActual.setIdTipoActualizacion(new TipoActualizacion(2, "actualizacion de lugar"));
            historialOtroDispositivoActual.setFechaActualizacion(new Date());
            getHistorialOtroDispositivoFacade().create(historialOtroDispositivoActual);
            getOtroDispositivoFacade().edit(otroDispositivoActual);
            addSuccessMessage("Dispositivo actualizado", "Su dispositivo se actualizo correctamente");
            return "adminViewHistorialOtroDispositivo";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String prepareEditOtroDispositivo() {
        historialOtroDispositivoActual = new HistorialOtroDispositivo();
        return "adminEditOtroDispositivo";
    }
    
    //Historial Otro dispositivo
    public List<HistorialOtroDispositivo> getListaHistorialOtroDispositivo() {
        listaHistorialOtroDispositivo=null;
          if (listaHistorialOtroDispositivo == null) {
            try {
                listaHistorialOtroDispositivo = getHistorialOtroDispositivoFacade().consultaDispositivo(otroDispositivoActual);
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return listaHistorialOtroDispositivo;
    }
    public HistorialOtroDispositivo getHistorialOtroDispositivoActual() {
        return historialOtroDispositivoActual;
    }

    public void setHistorialOtroDispositivoActual(HistorialOtroDispositivo historialOtroDispositivoActual) {
        this.historialOtroDispositivoActual = historialOtroDispositivoActual;
    }

    public HistorialOtroDispositivoFacade getHistorialOtroDispositivoFacade() {
        return historialOtroDispositivoFacade;
    }

    public void setHistorialOtroDispositivoFacade(HistorialOtroDispositivoFacade historialOtroDispositivoFacade) {
        this.historialOtroDispositivoFacade = historialOtroDispositivoFacade;
    }
    
    public void prepareViewHistorialDispositivo(ActionEvent event) {
        otroDispositivoActual = new OtroDispositivo();
        otroDispositivoActual = (OtroDispositivo) event.getComponent().getAttributes().get("otroDispositivo");
        listaHistorialOtroDispositivo = new ArrayList<>();
        //listaActividadAprendizaje=actividadActual.getActividadAprendizajeList(); 
    }
    public String viewHistorialDispositivo(){
        return "/Admin/moduloConfigComputador/adminViewHistorialOtroDispositivo";
    }

    
    
    //Select one Responsable
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }
    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }
    public List<Usuario> getListaUsuarioSelectOne() {
        return getUsuarioFacade().findAll();
    }
    
    //Select one Seccion
    public SeccionFacade getSeccionFacade() {
        return seccionFacade;
    }
    public void setSeccionFacade(SeccionFacade seccionFacade) {
        this.seccionFacade = seccionFacade;
    }
    public List<Seccion> getListaSeccionSelectOne() {
        return getSeccionFacade().findAll();
    }

    //Select one  Modelo pc
    public ModeloFacade getModeloFacade() {
        return modeloFacade;
    }
    public void setModeloFacade(ModeloFacade modeloFacade) {
        this.modeloFacade = modeloFacade;
    }
    public List<Modelo> getListaModeloSelectOne() {
        return getModeloFacade().consultaTipoDispo();
    }

 // Otros Dispositivos
    public List<OtroDispositivo> getListaOtroDispositivo() {
        listaOtroDispositivo = new ArrayList<>();
        listaOtroDispositivo = getOtroDispositivoFacade().findAll();
        return listaOtroDispositivo;
    }
    public List<OtroDispositivo> getListaOtroDispositivoActivo() {
        listaOtroDispositivo = new ArrayList<>();
        listaOtroDispositivo = getOtroDispositivoFacade().consultaOtroDispositivoActivo(true);
        return listaOtroDispositivo;
    }
    public List<OtroDispositivo> getListaOtroDispositivoUser() {
        listaOtroDispositivo = new ArrayList<>();
        listaOtroDispositivo = getOtroDispositivoFacade().consultaUsuario(usuarioActual);
        return listaOtroDispositivo;
    }
     public List<OtroDispositivo> getListaOtroDispositivoInactico() {
        listaOtroDispositivo = new ArrayList<>();
        listaOtroDispositivo = getOtroDispositivoFacade().consultaOtroDispositivoActivo(false);
        return listaOtroDispositivo;
    }
    public List<OtroDispositivo> getListaOtroDispositivoProg() {
        listaOtroDispositivo = new ArrayList<>();
        listaOtroDispositivo = getOtroDispositivoFacade().consultaOtroDispositivoProg(false);
        return listaOtroDispositivo;
    }



    private void recargarLista() {
        listaHistorialOtroDispositivo = null;
        listaOtroDispositivo = null;
    }
    public String prepareViewOtroDispositivoSinProgramar(){
        return "";
    }
    public String prepareCreate() {
        otroDispositivoActual = new OtroDispositivo();
        historialOtroDispositivoActual = new HistorialOtroDispositivo();
        return "/Admin/moduloConfigComputador/adminCrearOtroDispositivo";
    }
     public String prepareDarBaja() {
        historialOtroDispositivoActual = new HistorialOtroDispositivo();
        return "adminCrearOtroDisInactivo";
    }
    
    
  
    public String prepareView() {
        return "";
    }
    
    public String prepareList() {
        recargarLista();
        return "";
    }
 
    public String addOtroDispositivo() {
        try {
            otroDispositivoActual.setEstadoDispositivo(true);
            otroDispositivoActual.setEstadoProgramadoDis(false);
            getOtroDispositivoFacade().create(otroDispositivoActual);
            historialOtroDispositivoActual.setIdOtroDispositivo(otroDispositivoActual);
              if(otroDispositivoActual.getIdSeccion()==null){
                historialOtroDispositivoActual.setSeccion("PENDIENTE");
            }else{
                historialOtroDispositivoActual.setSeccion(otroDispositivoActual.getIdSeccion().toString());
            }
            if(otroDispositivoActual.getIdUsuario()==null){
                historialOtroDispositivoActual.setUsuario("PENDIENTE");
            }else{
                historialOtroDispositivoActual.setUsuario(otroDispositivoActual.getIdUsuario().toString());
            }
            historialOtroDispositivoActual.setIdTipoActualizacion(new TipoActualizacion(4, "creación inicial"));
            historialOtroDispositivoActual.setFechaActualizacion(new Date());
            historialOtroDispositivoActual.setDescripcionActualizacion("Otro dispositivo creado en el sistema");
            getHistorialOtroDispositivoFacade().create(historialOtroDispositivoActual);
            recargarLista();
            addSuccessMessage(otroDispositivoActual.getIdModelo().getIdTipo().getNombreTipo().toUpperCase()+" guardado ", otroDispositivoActual.getIdModelo().getIdTipo().getNombreTipo()+ " con service tag o (S/N): "+ otroDispositivoActual.getCodigoDispositivo().toUpperCase() +" guardado exitosamente");
            return viewHistorialDispositivo();
        } catch (Exception e) {
            addErrorMessage("Error al guardar dispositivo ", "Por favor intentelo de nuevo");
            return null;
        }
    }
    public String addOtroDisInactivo() {
        try {
            historialOtroDispositivoActual.setIdOtroDispositivo(otroDispositivoActual);
            historialOtroDispositivoActual.setIdTipoActualizacion(new TipoActualizacion(5, "dado de baja"));
            historialOtroDispositivoActual.setFechaActualizacion(new Date());
            getHistorialOtroDispositivoFacade().create(historialOtroDispositivoActual);
            otroDispositivoActual.setEstadoDispositivo(false);
            otroDispositivoActual.setIdUsuario(null);
            otroDispositivoActual.setIdSeccion(null);
            getOtroDispositivoFacade().edit(otroDispositivoActual);
            
            
            recargarLista();
            return "adminViewHistorialOtroDispositivo";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    
   
    
    public void validarOtroDispositivo(FacesContext contex, UIComponent component, Object value)throws ValidatorException{
        OtroDispositivo dispositivoConsulta=getOtroDispositivoFacade().findByCodigoOtroDispositivo((String)value);
        if(dispositivoConsulta != null){
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Service tag o (S/N) repetido","Service tag o (S/N) ya se encuentra registrado, intente con otro"));   
        }else{
            String codigo=(String) value;
            otroDispositivoActual.setCodigoDispositivo(codigo);
        }
    }
   

    public String deleteOtroDispositivo() {
        try {
            getOtroDispositivoFacade().remove(otroDispositivoActual);
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
    public OtroDispositivo getOtroDispositivo(java.lang.Integer id) {
        return getOtroDispositivoFacade().find(id);
    }
    @FacesConverter(forClass = OtroDispositivo.class, value="otroDispositivoConverter")
    public static class OtroDispositivoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OtroDispositivoController controller = (OtroDispositivoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "otroDispositivoController");
            return controller.getOtroDispositivo(getKey(value));
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
            if (object instanceof OtroDispositivo) {
                OtroDispositivo o = (OtroDispositivo) object;
                return getStringKey(o.getIdOtroDispositivo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + OtroDispositivo.class.getName());
            }
        }

    }
}
