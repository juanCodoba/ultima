/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Licencia;
import com.proyectoCFIP.entities.LicenciaPaqueteOffice;
import com.proyectoCFIP.entities.PaqueteOffice;
import com.proyectoCFIP.sessions.LicenciaPaqueteOfficeFacade;
import com.proyectoCFIP.sessions.PaqueteOfficeFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class LicenciaPaqueteOfficeController implements Serializable{

    @EJB
    private LicenciaPaqueteOfficeFacade licenciaPaqueteOfficeFacade;
    @EJB
    private PaqueteOfficeFacade paqueteOfficeFacade;
    private LicenciaPaqueteOffice licenciaPaqueteOfficeActual;
    private List<LicenciaPaqueteOffice> listaLicenciaPaqueteOffice;
    private List<PaqueteOffice> listaPaqueteOfice;
    
    public LicenciaPaqueteOfficeController() {
    }

    public LicenciaPaqueteOfficeFacade getLicenciaPaqueteOfficeFacade() {
        return licenciaPaqueteOfficeFacade;
    }

    public void setLicenciaPaqueteOfficeFacade(LicenciaPaqueteOfficeFacade licenciaPaqueteOfficeFacade) {
        this.licenciaPaqueteOfficeFacade = licenciaPaqueteOfficeFacade;
    }

    public LicenciaPaqueteOffice getLicenciaPaqueteOfficeActual() {
        return licenciaPaqueteOfficeActual;
    }

    public void setLicenciaPaqueteOfficeActual(LicenciaPaqueteOffice licenciaPaqueteOffice) {
        this.licenciaPaqueteOfficeActual = licenciaPaqueteOffice;
    }

    public List<LicenciaPaqueteOffice> getListaLicenciaPaqueteOffice() {
        return listaLicenciaPaqueteOffice = getLicenciaPaqueteOfficeFacade().findAll();
    }

    public void setListaLicenciaPaqueteOffice(List<LicenciaPaqueteOffice> listaLicenciaPaqueteOffice) {
        this.listaLicenciaPaqueteOffice = listaLicenciaPaqueteOffice;
    }

    public PaqueteOfficeFacade getPaqueteOfficeFacade() {
        return paqueteOfficeFacade;
    }

    public void setPaqueteOfficeFacade(PaqueteOfficeFacade paqueteOfficeFacade) {
        this.paqueteOfficeFacade = paqueteOfficeFacade;
    }

    public List<PaqueteOffice> getListaPaqueteOfice() {
        return listaPaqueteOfice =getPaqueteOfficeFacade().findAll();
    }

    public void setListaPaqueteOfice(List<PaqueteOffice> listaPaqueteOfice) {
        this.listaPaqueteOfice = listaPaqueteOfice;
    }
    
    
    
    private void recargarLista() {
        listaLicenciaPaqueteOffice = null;
    }
    public String prepareCreate() {
        licenciaPaqueteOfficeActual = new LicenciaPaqueteOffice();
        return "/Admin/configuracionesGenerales/adminCrearLicenciaPaqueteOffice";
    }

    public String prepareEdit() {
        return "";
    }

    public String prepareView() {
        return "";
    }

    public String prepareList() {
        return "";
    }

    public String addLicenciaPaquete() {
        try {
            getLicenciaPaqueteOfficeFacade().create(licenciaPaqueteOfficeActual);
            recargarLista();
            return "/User/paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateLicenciaPaquete() {
        try {
            getLicenciaPaqueteOfficeFacade().edit(licenciaPaqueteOfficeActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteSistemaOperativo() {
        try {
            getLicenciaPaqueteOfficeFacade().remove(licenciaPaqueteOfficeActual);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        return "List";
    }
       public void validarLicencia(FacesContext contex, UIComponent component, Object value)throws ValidatorException{
        LicenciaPaqueteOffice licenciaPaqueteConsulta=getLicenciaPaqueteOfficeFacade().findByidLicenciaPaquete((String)value);
        if(licenciaPaqueteConsulta != null){
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Codigo de Licencia repetida","El codigo de la licencia ya se encuentra registrada, intente con otro"));   
        }else{
            String codigo=(String) value;
            licenciaPaqueteOfficeActual.setCodigoLic(codigo);
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
    
    public LicenciaPaqueteOffice getLicenciaPaqueteOffice(java.lang.Integer id) {
        return getLicenciaPaqueteOfficeFacade().find(id);
    }
     @FacesConverter(forClass = LicenciaPaqueteOffice.class)
    public static class LicenciaPaqueteOfficeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LicenciaPaqueteOfficeController controller = (LicenciaPaqueteOfficeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "licenciaPaqueteOfficeController");
            return controller.getLicenciaPaqueteOffice(getKey(value));
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
            if (object instanceof LicenciaPaqueteOffice) {
                LicenciaPaqueteOffice o = (LicenciaPaqueteOffice) object;
                return getStringKey(o.getIdLicenciaPaqueteOffice());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + LicenciaPaqueteOffice.class.getName());
            }
        }

    }

    
}

