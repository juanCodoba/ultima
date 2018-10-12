/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Documentos;
import com.proyectoCFIP.entities.SistemasEquiposUsuarios;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.SistemasEquiposUsuariosFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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


/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class EquiposUsuariosController implements Serializable{
    
    @EJB
    private SistemasEquiposUsuariosFacade sistemasEquiposUsuariosFacade;
    private List<SistemasEquiposUsuarios> listaSistemasEquiposUsuarios = new ArrayList<SistemasEquiposUsuarios>();
    private SistemasEquiposUsuarios sistemasEquiposUsuariosActual;

    public SistemasEquiposUsuariosFacade getSistemasEquiposUsuariosFacade() {
        return sistemasEquiposUsuariosFacade;
    }

    public void setSistemasEquiposUsuariosFacade(SistemasEquiposUsuariosFacade sistemasEquiposUsuariosFacade) {
        this.sistemasEquiposUsuariosFacade = sistemasEquiposUsuariosFacade;
    }

    public List<SistemasEquiposUsuarios> getListaSistemasEquiposUsuarios() {
        return listaSistemasEquiposUsuarios = getSistemasEquiposUsuariosFacade().findAll();
    }

    public void setListaSistemasEquiposUsuarios(List<SistemasEquiposUsuarios> listaSistemasEquiposUsuarios) {
        this.listaSistemasEquiposUsuarios = listaSistemasEquiposUsuarios;
    }

    public SistemasEquiposUsuarios getSistemasEquiposUsuariosActual() {
        return sistemasEquiposUsuariosActual;
    }

    public void setSistemasEquiposUsuariosActual(SistemasEquiposUsuarios sistemasEquiposUsuariosActual) {
        this.sistemasEquiposUsuariosActual = sistemasEquiposUsuariosActual;
    }

    private void recargarLista() {
        listaSistemasEquiposUsuarios = null;
    }

    public String prepareCreate() {
        sistemasEquiposUsuariosActual = new SistemasEquiposUsuarios();
        return "/administrador/modSoporteIT/equiposUsuarios/crear";
    }
    public String prepareEdit() {
        return "/administrador/modSoporteIT/equiposUsuarios/editar";
    }

    public String prepareView() {
        return "";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modSoporteIT/equiposUsuarios/lista";
    }
public static Date sumaFecha(Date fecha){
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fecha); 
      calendar.add(calendar.DAY_OF_YEAR, 183); 
      return calendar.getTime(); 
}
    public String add() {
        try {
            sistemasEquiposUsuariosActual.setFechaVencimiento(sumaFecha(sistemasEquiposUsuariosActual.getFechaPermiso()));
            getSistemasEquiposUsuariosFacade().create(sistemasEquiposUsuariosActual);
            recargarLista();
            addSuccessMessage("Nueva declaración de uso guardada", "El consecutivo "+sistemasEquiposUsuariosActual.getIdsistemasequiposUsuarios()+ " fue guardado exitosamente");
            return "/administrador/modSoporteIT/equiposUsuarios/lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String update() {
        try {
            sistemasEquiposUsuariosActual.setFechaVencimiento(sumaFecha(sistemasEquiposUsuariosActual.getFechaPermiso()));
            getSistemasEquiposUsuariosFacade().edit(sistemasEquiposUsuariosActual);
            addSuccessMessage("Declaración de uso guardada modificada", "El consecutivo "+sistemasEquiposUsuariosActual.getIdsistemasequiposUsuarios()+ " fue modificado exitosamente");
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

   public String deleteUsuario() {
        try {
            getSistemasEquiposUsuariosFacade().remove(sistemasEquiposUsuariosActual);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        return "lista";
    }
   public void prepareEscaneo(ActionEvent event){
        sistemasEquiposUsuariosActual = new SistemasEquiposUsuarios();
        sistemasEquiposUsuariosActual = (SistemasEquiposUsuarios) event.getComponent().getAttributes().get("item");
    }   
   
    public void obtenerEscaneo() throws IOException{
        if(sistemasEquiposUsuariosActual.getEscaneo()==null){
            addErrorMessage("Documento sin acceso","el documento no tiene acceso");
        }else{
            FacesContext.getCurrentInstance().getExternalContext().redirect(sistemasEquiposUsuariosActual.getEscaneo());
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

    public SistemasEquiposUsuarios getSistemasEquiposUsuarios(java.lang.Integer id) {
        return getSistemasEquiposUsuariosFacade().find(id);
    }

    @FacesConverter(forClass = Usuario.class)
    public static class EquiposUsuariosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EquiposUsuariosController controller = (EquiposUsuariosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "EquiposUsuariosController");
            return controller.getSistemasEquiposUsuarios(getKey(value));
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
            if (object instanceof SistemasEquiposUsuarios) {
                SistemasEquiposUsuarios o = (SistemasEquiposUsuarios) object;
                return getStringKey(o.getIdsistemasequiposUsuarios());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + SistemasEquiposUsuarios.class.getName());
            }
        }
    
    }
}