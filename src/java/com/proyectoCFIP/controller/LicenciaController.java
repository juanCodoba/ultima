/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Licencia;
import com.proyectoCFIP.entities.TipoLicencia;
import com.proyectoCFIP.sessions.LicenciaFacade;
import com.proyectoCFIP.sessions.TipoLicenciaFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@RequestScoped
public class LicenciaController {
    
    @EJB
    private LicenciaFacade LicenciaFacade;
    private List<Licencia> listaLicencia = null;
    private Licencia licenciaActual;
    private TipoLicenciaFacade tipoLicenciaFacade;
    private List<TipoLicencia> listaTipoLicencia= null;

    /**
     * Creates a new instance of LicenciaController
     */
    public LicenciaController() {
    }
    
    
    public boolean isSistemaOperativo(){
        return licenciaActual.getIdTipoLicencia()== null? false : licenciaActual.getIdTipoLicencia().getIdTipoLicencia()== (short) 2;
    }
    
    
    
    public List<Licencia> getListaLicencia() {
        listaLicencia = new ArrayList<>();
        listaLicencia = getLicenciaFacade().findAll();
        return listaLicencia;
    }

    public List<Licencia> getListaLicenciaSOSelectOne() {
        return  getLicenciaFacade().consultaTipoLicencia(new TipoLicencia(1));
    }

    
    public List<Licencia> getListaLicenciaAplicativoSelectOne() {
        return  getLicenciaFacade().consultaTipoLicenciaAplicativo();
    }

    public LicenciaFacade getLicenciaFacade() {
        return LicenciaFacade;
    }

    public Licencia getLicenciaActual() {
        if (licenciaActual == null) {
            licenciaActual = new Licencia();
        }
        return licenciaActual;
    }

    public void setLicenciaActual(Licencia licenciaActual) {
        this.licenciaActual = licenciaActual;
    }


    private void recargarLista() {
        listaLicencia = null;
    }

    public String prepareCreate() {
        listaTipoLicencia = null;
        licenciaActual = new Licencia();
        return "/Admin/configuracionesGenerales/adminCrearLicencia";
    }

    public String prepareEdit() {
        return "";
    }

    public String prepareView() {
        return "";
    }

    public String prepareList() {
        recargarLista();
        return "";
    }

    public String addLicencia() {
        try {
            getLicenciaFacade().create(licenciaActual);
            recargarLista();
            return "/User/paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateLicencia() {
        try {
            getLicenciaFacade().edit(licenciaActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public void validarLicencia(FacesContext contex, UIComponent component, Object value)throws ValidatorException{
        Licencia licenciaConsulta=getLicenciaFacade().findByidLicencia((String)value);
        if(licenciaConsulta != null){
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Codigo de Licencia repetida","El codigo de la licencia ya se encuentra registrada, intente con otro"));   
        }else{
            String codigo=(String) value;
            licenciaActual.setCodigoLicencia(codigo);
        }
    }

    public String deleteLicencia() {
        try {
            getLicenciaFacade().remove(licenciaActual);
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
    
    public Licencia getLicencia(java.lang.Integer id) {
        return getLicenciaFacade().find(id);
    }
    
    @FacesConverter(forClass = Licencia.class)
    public static class LicenciaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LicenciaController controller = (LicenciaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "licenciaController");
            return controller.getLicencia(getKey(value));
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
            if (object instanceof Licencia) {
                Licencia o = (Licencia) object;
                return getStringKey(o.getIdLicencia());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Licencia.class.getName());
            }
        }

    }

}
