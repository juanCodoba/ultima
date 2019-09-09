/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.CalidadCausa;
import com.proyectoCFIP.entities.EstadoConsecPlanilla;
import static com.proyectoCFIP.entities.Facturado_.m;
import com.proyectoCFIP.entities.OpPlanilla;
import com.proyectoCFIP.entities.Planilla;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.EstadoConsecPlanillaFacade;
import com.proyectoCFIP.sessions.OpPlanillaFacade;
import com.proyectoCFIP.sessions.PlanillaFacade;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class PlanillaEditController implements Serializable {

    @EJB
    private PlanillaFacade planillaFacade;
    @EJB
    private OpPlanillaFacade opPlanillaFacade;

    private Planilla planillaActual;
    private List<Planilla> planillaList;
    private List<Planilla> planillaListaActual;
    private List<Planilla> planillaListaPorOp;
    private List<Planilla> planillaListaActualPrueba;

    public Integer value;

    private OpPlanilla opPlanillaActual;
    private List<OpPlanilla> listOpPlanilla;
    private List<Planilla> opPlanillaListaActual;

    private EstadoConsecPlanilla estadoConsecPlanilla;
    private EstadoConsecPlanillaFacade estadoConsecPlanillaFacade;
    private List<EstadoConsecPlanilla> estadoConsecPlanillaLista;

    private Usuario usuarioActual;
    public String nombreImagen;

//    @EJB
//    private DiagnosticoPlanillaFacade diagnosticoPlanillaFacade;
//    private DiagnosticoPlanilla diagnosticoPlanillaActual;
//    private List<DiagnosticoPlanilla> diagnosticoPlanillaList;
    @EJB
    EmailSessionBean emailBean;

    public PlanillaEditController() {
    }

    public List<Planilla> getPlanillaListaPorOp() {
        return planillaListaPorOp = planillaFacade.consultaPlanillaOp(opPlanillaActual);
    }

    public void setPlanillaListaPorOp(List<Planilla> planillaListaPorOp) {
        this.planillaListaPorOp = planillaListaPorOp;
    }
    
    

    public Integer getValue(Planilla planilla) {

        for (Planilla p : planillaList) {
            value += planilla.getValorSubtotal();
        }
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    

    public List<Planilla> getOpPlanillaListaActual() {
        return opPlanillaListaActual;
    }

    public void setOpPlanillaListaActual(List<Planilla> opPlanillaListaActual) {
        this.opPlanillaListaActual = opPlanillaListaActual;
    }

    public EstadoConsecPlanilla getEstadoConsecPlanilla() {
        return estadoConsecPlanilla;
    }

    public void setEstadoConsecPlanilla(EstadoConsecPlanilla estadoConsecPlanilla) {
        this.estadoConsecPlanilla = estadoConsecPlanilla;
    }

    public EstadoConsecPlanillaFacade getEstadoConsecPlanillaFacade() {
        return estadoConsecPlanillaFacade;
    }

    public void setEstadoConsecPlanillaFacade(EstadoConsecPlanillaFacade estadoConsecPlanillaFacade) {
        this.estadoConsecPlanillaFacade = estadoConsecPlanillaFacade;
    }

    public List<EstadoConsecPlanilla> getEstadoConsecPlanillaLista() {
        return estadoConsecPlanillaLista = estadoConsecPlanillaFacade.findAll();
    }

    public void setEstadoConsecPlanillaLista(List<EstadoConsecPlanilla> estadoConsecPlanillaLista) {
        this.estadoConsecPlanillaLista = estadoConsecPlanillaLista;
    }

    public List<Planilla> getPlanillaListaActual() {
        return planillaListaActual;
    }

    public void setPlanillaListaActual(List<Planilla> planillaListaActual) {
        this.planillaListaActual = planillaListaActual;
    }

    public OpPlanilla getOpPlanillaActual() {
        return opPlanillaActual;
    }

    public void setOpPlanillaActual(OpPlanilla opPlanillaActual) {
        this.opPlanillaActual = opPlanillaActual;
    }

    public OpPlanillaFacade getOpPlanillaFacade() {
        return opPlanillaFacade;
    }

    public void setOpPlanillaFacade(OpPlanillaFacade opPlanillaFacade) {
        this.opPlanillaFacade = opPlanillaFacade;
    }

    public List<OpPlanilla> getListOpPlanilla() {
        return listOpPlanilla = opPlanillaFacade.findAll();
    }

    public void setListOpPlanilla(List<OpPlanilla> listOpPlanilla) {
        this.listOpPlanilla = listOpPlanilla;
    }

    public EmailSessionBean getEmailBean() {
        return emailBean;
    }

    public void setEmailBean(EmailSessionBean emailBean) {
        this.emailBean = emailBean;
    }

    public PlanillaFacade getPlanillaFacade() {
        return planillaFacade;
    }

    public void setPlanillaFacade(PlanillaFacade planillaFacade) {
        this.planillaFacade = planillaFacade;
    }

    public Planilla getPlanillaActual() {
        return planillaActual;
    }

    public void setPlanillaActual(Planilla planillaActual) {
        this.planillaActual = planillaActual;
    }

    public List<Planilla> getPlanillaList() {
        return planillaList = getPlanillaFacade().findAll();
    }

    public void setPlanillaList(List<Planilla> planillaList) {
        this.planillaList = planillaList;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public List<Planilla> getPlanillaListaActualPrueba() throws SQLException {
        return planillaListaActualPrueba = planillaFacade.redondedoValorSubTotal(opPlanillaActual);
    }

    public void setPlanillaListaActualPrueba(List<Planilla> planillaListaActualPrueba) {
        this.planillaListaActualPrueba = planillaListaActualPrueba;
    }

//    public List<Planilla> getPlanillaListSinDiag() {
//        return planillaList = getPlanillaFacade().consultaReporteEstadoSinDiagnosticar(new EstadoTicket(1, "Abierto"), new EstadoTicket(2, "Siesa IT"));
//    }
    public String prepareViewReportes() {
        return "/administrador/modSoporteIT/calendarioMantenimiento/diagnosticos/verDiagnosticosERP";
    }

    public String prepareView() {
        return "verError";
    }

    public Planilla getPlanilla(java.lang.Integer id) {
        return getPlanillaFacade().find(id);
    }

    public int calcularTotal() {
        return (int) (Math.random() * 10000);

    }

    public int calcularTotal2() {
        return (int) (Math.random() * 10000);

    }
        public void prepareAspecto() {
        planillaActual = new Planilla();
    }

    public String prepareCreate() {
        opPlanillaActual = new OpPlanilla();
        planillaActual = new Planilla();
        planillaListaActual = new ArrayList<>();

        return "/administrador/modPlanilla/create2";
    }
    
        public List<Planilla> getListaProcesoEvaluadoConsulta() {
        return planillaListaActual = getPlanillaFacade().consultaPlanillaOp(opPlanillaActual);
    }

    public String prepareEdit() throws SQLException {
        return "/administrador/modPlanilla/edit";
    }

    public String prepareCreatePrueba() {
        opPlanillaActual = new OpPlanilla();
//        planillaActual = new Planilla();
//        planillaListaActual = new ArrayList<>();
        return "/administrador/modPlanilla/createPrueba";
    }

    public String prepareListEstadoTicket() {
        return "/usuario/modSoporteSiesa/ticketsValoraciones/listaEstadoTicket";
    }

    public void recargarLista() {
        listOpPlanilla = null;
    }

    public String addOpPlanilla() {
        try {
            if (planillaListaActual.isEmpty()) {
                addErrorMessage("Op planilla no creada", "No contiene pvs e items");
                return null;
            }
            //opPlanillaActual.setOpPlanilla(planillaActual.getOp());
            opPlanillaActual.setPlanillaList(planillaListaActual);
            opPlanillaActual.setIdUsuarioAsig(usuarioActual);
            getOpPlanillaFacade().create(opPlanillaActual);
            //addPvsCompelt();
            addSuccessMessage("Datos registrados", "El registro fue creado con consecutivo No. ");
            return "/usuario/modPlanilla/list";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public void handleKeyEvent() {
        Integer value12 = opPlanillaActual.getOpPlanilla();
        Integer value2 = planillaActual.getOp();

        value12 = value2;
    }





   

    public void update() {
        try {
            getPlanillaFacade().edit(planillaActual);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
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

    @FacesConverter(forClass = Planilla.class)
    public static class PlanillaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PlanillaEditController controller = (PlanillaEditController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "planillaEditController");
            return controller.getPlanilla(getKey(value));
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
            if (object instanceof Planilla) {
                Planilla o = (Planilla) object;
                return getStringKey(o.getIdPlanilla());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Planilla.class.getName());
            }
        }

    }

    //Reportes Siesa
}
