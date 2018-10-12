

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.DispositivoConectividad;
import com.proyectoCFIP.entities.HistorialDispositivoConectividad;
import com.proyectoCFIP.entities.OtroDispositivo;
import com.proyectoCFIP.entities.TipoActualizacion;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.DispositivoConectividadFacade;
import com.proyectoCFIP.sessions.HistorialDispositivoConectividadFacade;
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
public class DispositivoConectividadController implements Serializable{

    @EJB
    private DispositivoConectividadFacade dispositivoConectividadFacade;
    @EJB
    private HistorialDispositivoConectividadFacade historialDispoConecFacade;
    private DispositivoConectividad dispositivoConectividadActual;
    private List<DispositivoConectividad> listaDispositivoConectividad;
    private HistorialDispositivoConectividad historialDispoConecActua;
    private List<HistorialDispositivoConectividad> listaHistorialDispositivoConectividad;
    private Usuario usuarioActual;
    
    public DispositivoConectividadController() {
    }
    
    
    public boolean isTipoCanaleta(){
        return dispositivoConectividadActual.getIdTipo()== null? false : dispositivoConectividadActual.getIdTipo().getIdTipo()== (short) 5;
    }
    
    public boolean isTipoSwitch(){
        return dispositivoConectividadActual.getIdTipo()== null? false : dispositivoConectividadActual.getIdTipo().getIdTipo()== (short) 6;
    }
    
    //GET AND SET

    public DispositivoConectividadFacade getDispositivoConectividadFacade() {
        return dispositivoConectividadFacade;
    }

    public void setDispositivoConectividadFacade(DispositivoConectividadFacade dispositivoConectividadFacade) {
        this.dispositivoConectividadFacade = dispositivoConectividadFacade;
    }

    public DispositivoConectividad getDispositivoConectividadActual() {
        return dispositivoConectividadActual;
    }

    public void setDispositivoConectividadActual(DispositivoConectividad dispositivoConectividadActual) {
        this.dispositivoConectividadActual = dispositivoConectividadActual;
    }

    public List<DispositivoConectividad> getListaDispositivoConectividad() {
        return listaDispositivoConectividad = dispositivoConectividadFacade.findAll();
    }
     public List<DispositivoConectividad> getListaDispositivoConectividadUser() {
        return getDispositivoConectividadFacade().consultaUsuario(usuarioActual);
    }

    public void setListaDispositivoConectividad(List<DispositivoConectividad> listaDispositivoConectividad) {
        this.listaDispositivoConectividad = listaDispositivoConectividad;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    
    //GET AND SET HISTORIAL

    public HistorialDispositivoConectividadFacade getHistorialDispoConecFacade() {
        return historialDispoConecFacade;
    }

    public void setHistorialDispoConecFacade(HistorialDispositivoConectividadFacade historialDispoConecFacade) {
        this.historialDispoConecFacade = historialDispoConecFacade;
    }

    public HistorialDispositivoConectividad getHistorialDispoConecActua() {
        return historialDispoConecActua;
    }

    public void setHistorialDispoConecActua(HistorialDispositivoConectividad historialDispoConecActua) {
        this.historialDispoConecActua = historialDispoConecActua;
    }

    public List<HistorialDispositivoConectividad> getListaHistorialDispositivoConectividad() {
        return getHistorialDispoConecFacade().consultaDispoConec(dispositivoConectividadActual);
    }

    public void setListaHistorialDispositivoConectividad(List<HistorialDispositivoConectividad> listaHistorialDispositivoConectividad) {
        this.listaHistorialDispositivoConectividad = listaHistorialDispositivoConectividad;
    }
    
    public List<DispositivoConectividad> getListaDispositivoProg() {
        return getDispositivoConectividadFacade().consultaOtroDispositivoProg(false);
        
    }

    public String prepareList(){
        return "";
    }
    public String prepareEdit(){
        historialDispoConecActua = new HistorialDispositivoConectividad();
        return "/Admin/moduloConfigComputador/dispositivoConectividad/adminEditDispositivoConectividad";
    }
    public String prepareCreate(){
        dispositivoConectividadActual = new DispositivoConectividad();
        historialDispoConecActua = new HistorialDispositivoConectividad();
        
        return "/Admin/moduloConfigComputador/dispositivoConectividad/adminCrearDispositivoCon";
    }
    public String prepareHistorialDispositivoCon(){
        return "";
    }
    public String addDispositivoConectividad(){
        try {
            dispositivoConectividadActual.setEstado(true);
            dispositivoConectividadFacade.create(dispositivoConectividadActual);
            historialDispoConecActua.setIdDispositivoConectividad(dispositivoConectividadActual);
            
            if(dispositivoConectividadActual.getIdSeccion()==null){
                 historialDispoConecActua.setSeccion("PENDIENTE");
            }else{
                 historialDispoConecActua.setSeccion(dispositivoConectividadActual.getIdSeccion().toString());
            }
            if(dispositivoConectividadActual.getIdUsuario()==null){
                historialDispoConecActua.setUsuario("PENDIENTE");
            }else{
                historialDispoConecActua.setUsuario(dispositivoConectividadActual.getIdUsuario().toString());
            }
           
            historialDispoConecActua.setIdTipoActualizacion(new TipoActualizacion(4, "creación inicial"));
            historialDispoConecActua.setFechaActualizacion(new Date());
            historialDispoConecActua.setDescripcionActualizacion("Dispositivo de conectividad creado en el sistema");
            getHistorialDispoConecFacade().create(historialDispoConecActua);
            addSuccessMessage("Dispositivo guardado", "El dispositivo de conectividad fue guardado satisfactoriamente");
            recargarLista();
            return viewHistorialDispositivo();
        } catch (Exception e) {
            addErrorMessage("Error al guardar dispositivo ", "Por favor intentelo de nuevo");
            return null;
        }
    }
    private void recargarLista() {
        listaDispositivoConectividad =null;
        listaHistorialDispositivoConectividad = null;
    }
    public String editDispositivoConectividad(){
        try {
            historialDispoConecActua.setIdDispositivoConectividad(dispositivoConectividadActual);
            
            if(dispositivoConectividadActual.getIdSeccion()==null){
                 historialDispoConecActua.setSeccion("PENDIENTE");
            }else{
                 historialDispoConecActua.setSeccion(dispositivoConectividadActual.getIdSeccion().toString());
            }
            if(dispositivoConectividadActual.getIdUsuario()==null){
                historialDispoConecActua.setUsuario("PENDIENTE");
            }else{
                historialDispoConecActua.setUsuario(dispositivoConectividadActual.getIdUsuario().toString());
            }
           
            historialDispoConecActua.setIdTipoActualizacion(new TipoActualizacion(1, "actualizacion de lugar"));
            historialDispoConecActua.setFechaActualizacion(new Date());
            getHistorialDispoConecFacade().create(historialDispoConecActua);
            dispositivoConectividadFacade.edit(dispositivoConectividadActual);
            return "adminViewHistorialDispoConec";
        } catch (Exception e) {
            addErrorMessage("Error al guardar dispositivo ", "Por favor intentelo de nuevo");
            return null;
        }
    }
    public void validarDispositivoCon(FacesContext contex, UIComponent component, Object value)throws ValidatorException{
        DispositivoConectividad dispositivoConsulta=getDispositivoConectividadFacade().findByCodigoDispositivo((String)value);
        if(dispositivoConsulta != null){
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Service tag o (S/N) repetido","Service tag o (S/N) ya se encuentra registrado, intente con otro"));   
        }else{
            String codigo=(String) value;
            dispositivoConectividadActual.setCodigo(codigo);
        }
    }
    
    public void prepareViewHistorialDispositivo(ActionEvent event) {
        dispositivoConectividadActual = new DispositivoConectividad();
        dispositivoConectividadActual = (DispositivoConectividad) event.getComponent().getAttributes().get("dispositivo");
        listaHistorialDispositivoConectividad = new ArrayList<>();
        //listaActividadAprendizaje=actividadActual.getActividadAprendizajeList(); 
    }
    public String viewHistorialDispositivo(){
        return "/Admin/moduloConfigComputador/dispositivoConectividad/adminViewHistorialDispoConec";
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
    
    public DispositivoConectividad getDispositivoConectividad(java.lang.Integer id) {
        return getDispositivoConectividadFacade().find(id);
    }
    @FacesConverter(forClass = DispositivoConectividad.class, value="dispositivoConverter")
    public static class DispositivoConectividadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DispositivoConectividadController controller = (DispositivoConectividadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "dispositivoConectividadController");
            return controller.getDispositivoConectividad(getKey(value));
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
            if (object instanceof DispositivoConectividad) {
                DispositivoConectividad o = (DispositivoConectividad) object;
                return getStringKey(o.getIdDispositivoConectividad());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DispositivoConectividad.class.getName());
            }
        }

    }
    
    
}
