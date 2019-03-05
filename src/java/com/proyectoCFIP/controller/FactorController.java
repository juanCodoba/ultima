/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.Factores;
import com.proyectoCFIP.entities.Importancia;
import com.proyectoCFIP.entities.Oportunidad;
import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.entities.Riesgo;
import com.proyectoCFIP.sessions.CargosFacade;
import com.proyectoCFIP.sessions.FactoresFacade;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.PieChartModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class FactorController implements Serializable {

    @EJB
    private FactoresFacade factoresFacade;
    private List<Factores> listaFactoresActual;
    private List<Factores> listaFactores;
    private List<Factores> listreq;
    private List<Factores> listreChart;

    private Factores factoresActual;
    private Factores factores;

    @EJB
    private CargosFacade cargosFacade;
    private Cargos cargosActual;
    private List<Cargos> listaCaegos;

    private Riesgo riesgoActual;
    private Oportunidad oportunidadActual;
    private Importancia importanciaActual;
    private Procesos procesoActual;

    private Integer total;
    private Integer total1;
    private Integer total2;
    private final Integer puntuacion = 0;

    public FactorController() {

    }

    public boolean isOtro() {
        return factoresActual.getIdItemSituacion() == null ? false
                : factoresActual.getIdItemSituacion().getIdItemsSituacion() == (short) 3;
    }

    public boolean isOtroDos() {
        return factoresActual.getIdItemSituacion() == null ? false
                : factoresActual.getIdItemSituacion().getIdItemsSituacion() == (short) 3;
    }

    public FactoresFacade getFactoressFacade() {
        return factoresFacade;
    }

    public void setFactoressFacade(FactoresFacade factoressFacade) {
        this.factoresFacade = factoressFacade;
    }

    public List<Factores> getListaFactoresActual() {
        return listaFactoresActual;
    }

    public void setListaFactoresActual(List<Factores> listaFactoresActual) {
        this.listaFactoresActual = listaFactoresActual;
    }

    public List<Factores> getListaFactores() {
        return listaFactores = factoresFacade.findAll();
    }

    public void setListaFactores(List<Factores> listaFactores) {
        this.listaFactores = listaFactores;
    }

    public Factores getFactoresActual() {
        return factoresActual;
    }

    public void setFactoresActual(Factores factoresActual) {
        this.factoresActual = factoresActual;
    }

    public List<Factores> getListreChart() {
        return listreChart = factoresFacade.consultaPieChart(procesoActual);
    }

    public List<Factores> getListreq() {
        return listreq = factoresFacade.consultaCargo(procesoActual);
    }

    public List<Factores> getListreq2() {
        return listreq = factoresFacade.consultaCargo2(procesoActual);
    }

    public List<Factores> getListreq3() {
        return listreq = factoresFacade.consultaCargo3(procesoActual);
    }

    public List<Factores> getListreGlo() {
        return listreq = factoresFacade.consultaGlobal();
    }

    public List<Factores> getListreGlo2() {
        return listreq = factoresFacade.consultaGlobal2();
    }

    public List<Factores> getListreGlo3() {
        return listreq = factoresFacade.consultaGlobal3();
    }

    public Procesos getProcesoActual() {
        return procesoActual;
    }

    public void setProcesoActual(Procesos procesoActual) {
        this.procesoActual = procesoActual;
    }

    public FactoresFacade getFactoresFacade() {
        return factoresFacade;
    }

    public void setFactoresFacade(FactoresFacade factoresFacade) {
        this.factoresFacade = factoresFacade;
    }

    public Factores getFactores() {
        return factores;
    }

    public void setFactores(Factores factores) {
        this.factores = factores;
    }

    public Riesgo getRiesgoActual() {
        return riesgoActual;
    }

    public void setRiesgoActual(Riesgo riesgoActual) {
        this.riesgoActual = riesgoActual;
    }

    public Oportunidad getOportunidadActual() {
        return oportunidadActual;
    }

    public void setOportunidadActual(Oportunidad oportunidadActual) {
        this.oportunidadActual = oportunidadActual;
    }

    public Importancia getImportanciaActual() {
        return importanciaActual;
    }

    public void setImportanciaActual(Importancia importanciaActual) {
        this.importanciaActual = importanciaActual;
    }

    public void setListreq(List<Factores> listreq) {
        this.listreq = listreq;
    }

    public String prepareAspecto() {
        factoresActual = new Factores();
        listaFactoresActual = new ArrayList<>();
        return "/administrador/modPestel/editFactores";

    }

    public String prepareAspectoGlobal() {
        factoresActual = new Factores();
        listaFactoresActual = new ArrayList<>();
        return "/administrador/modPestel/verGlobal";

    }

    public String prepareEdit() {
        recargarLista();
        return "/administrador/modPestel/edit";
    }

    public String prepareView() {
        return "";
    }

    public String list() {
        return "/administrador/modPestel/list";
    }

    public String prepareCreate() {
        factoresActual = new Factores();
        listaFactoresActual = new ArrayList<>();
        return "/administrador/modPestel/create2";
    }

    public Factores getFactoress() {
        return factores;
    }

    public void setFactoress(Factores factoress) {
        this.factores = factoress;
    }

    public void deleteFactoresLista() {
        try {
            getFactoressFacade().remove(factoresActual);
            listaFactoresActual.remove(factoresActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "El Registro fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no se elimino", "El Registro no fue eliminado correctamente intentalo de nuevo!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void recargarLista() {
        listaFactoresActual = null;
    }

    public CargosFacade getCargosFacade() {
        return cargosFacade;
    }

    public void setCargosFacade(CargosFacade cargosFacade) {
        this.cargosFacade = cargosFacade;
    }

    public Cargos getCargosActual() {
        return cargosActual;
    }

    public void setCargosActual(Cargos cargosActual) {
        this.cargosActual = cargosActual;
    }

    public List<Cargos> getListaCaegos() {
        return listaCaegos;
    }

    public void setListaCaegos(List<Cargos> listaCaegos) {
        this.listaCaegos = listaCaegos;
    }

    public void add() {
        try {

//            factoresActual.setIdCargo(cargosActual);
//            factoresActual.setEstado(Boolean.TRUE);
            //factoresActual.setPuntuacion(ObtenerPuntuacion(importanciaActual.getIdImportancia(), riesgoActual.getIdRiesgo(), oportunidadActual.getIdOportunidad()));
            getFactoressFacade().create(factoresActual);
            listaFactoresActual.add(factoresActual);
            factoresActual = new Factores();
            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }

    public void add2() {
        try {

            factoresActual.setIdProceso(procesoActual);
            getFactoressFacade().create(factoresActual);
            listaFactoresActual.add(factoresActual);
            factoresActual = new Factores();
            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
    }

    public String editFactores() {
        try {
//            factoresActual.setEstado(Boolean.TRUE);
            getFactoressFacade().edit(factoresActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Factores editado", "se asignado el factores al cargo");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "/administrador/modCalidad/maestrosCargos/tipoFactores/list";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/administrador/modCalidad/maestrosCargos/tipoFactores/list";

        }
    }

    public void delete() {
        try {
            getFactoressFacade().remove(factoresActual);
            listaFactoresActual.remove(factoresActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "El Registro fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Registro no se elimino", "El Registro no fue eliminado correctamente intentalo de nuevo!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public String prepareList2() {
        return "/administrador/modCalidad/maestrosCargos/tipoFactores/list";
    }

    public String prepareEdit2() {
        return "/administrador/modCalidad/maestrosFactores/tipoFactores/edit";
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

    public Factores getFactores(java.lang.Integer id) {
        return getFactoressFacade().find(id);
    }

    @FacesConverter(forClass = Factores.class)
    public static class FactoresControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FactorController controller = (FactorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "factoressController");
            return controller.getFactores(getKey(value));
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
            if (object instanceof Factores) {
                Factores o = (Factores) object;
                return getStringKey(o.getIdFactor());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Factores.class.getName());
            }
        }
    }

    public class ConexionMySQL {

        // Librería de MySQL
        public String driver = "com.mysql.jdbc.Driver";

        // Nombre de la base de datos
        public String database = "proyectocfip";

        // Host
        public String hostname = "localhost";

        // Puerto
        public String port = "3306";

        // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
        public String url = "jdbc:mysql://localhost:3306/proyectocfip?zeroDateTimeBehavior=convertToNull";

        // Nombre de usuario
        public String username = "root";

        // Clave de usuario
        public String password = "1234";

        public Connection conectarMySQL() throws SQLException {
            Connection conn = null;

            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            return conn;
        }

    }

    private PieChartModel pieModel;
    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarChartModel;
    private PieChartModel pieModelGlobal;
    private HorizontalBarChartModel horizontalBarChartModelGlobal;
    private HorizontalBarChartModel horizontalBarChartModelItemGlobal;
    private HorizontalBarChartModel horizontalBarChartModelItemP;

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public HorizontalBarChartModel getHorizontalBarChartModel() {
        return horizontalBarChartModel;
    }

    public void setHorizontalBarChartModel(HorizontalBarChartModel horizontalBarChartModel) {
        this.horizontalBarChartModel = horizontalBarChartModel;
    }

    public PieChartModel getPieModelGlobal() {
        return pieModelGlobal;
    }

    public void setPieModelGlobal(PieChartModel pieModelGlobal) {
        this.pieModelGlobal = pieModelGlobal;
    }

    public HorizontalBarChartModel getHorizontalBarChartModelGlobal() {
        return horizontalBarChartModelGlobal;
    }

    public void setHorizontalBarChartModelGlobal(HorizontalBarChartModel horizontalBarChartModelGlobal) {
        this.horizontalBarChartModelGlobal = horizontalBarChartModelGlobal;
    }

    public HorizontalBarChartModel getHorizontalBarChartModelItemGlobal() {
        return horizontalBarChartModelItemGlobal;
    }

    public void setHorizontalBarChartModelItemGlobal(HorizontalBarChartModel horizontalBarChartModelItemGlobal) {
        this.horizontalBarChartModelItemGlobal = horizontalBarChartModelItemGlobal;
    }

    public HorizontalBarChartModel getHorizontalBarChartModelItemP() {
        return horizontalBarChartModelItemP;
    }

    public void setHorizontalBarChartModelItemP(HorizontalBarChartModel horizontalBarChartModelItemP) {
        this.horizontalBarChartModelItemP = horizontalBarChartModelItemP;
    }

    public void intit() {
        graficar();
        initBarModel();
    }

    public void listardos() throws SQLException{

        try {
            factoresActual = new Factores();
            listaFactores = getFactoresFacade().listar(procesoActual);
            graficar();
            listaFactores = getFactoresFacade().listar2(procesoActual);
            initBarModel();
            listaFactores = getFactoresFacade().listarItem(procesoActual);
            initBarModelItem();
            recargarLista();

        } catch (Exception e) {
            System.out.println("com.proyectoCFIP.controller.FactorController.listardos()");
        }
    }

    public PieChartModel graficar() {
        pieModel = new PieChartModel();

        for (Factores fact : listaFactores) {
            pieModel.set(fact.getDescripcionFactor(), fact.getPuntuacion());
            pieModel.setTitle("Promedios");
            pieModel.setLegendPosition("e");
            pieModel.setFill(false);
            pieModel.setShowDataLabels(true);
            pieModel.setMouseoverHighlight(true);
            pieModel.setShadow(true);
            pieModel.setDiameter(400);
            //pieModel.setExtender("removeLegend");
        }
        return pieModel;

    }

    private BarChartModel initBarModel() {
        barModel = new BarChartModel();
        horizontalBarChartModel = new HorizontalBarChartModel();
        for (Factores fact2 : listaFactores) {

            ChartSeries serie = new ChartSeries();
            serie.setLabel(fact2.getDescripcionFactor());

            serie.set("Puntajes", fact2.getPuntuacion());
            horizontalBarChartModel.addSeries(serie);
            horizontalBarChartModel.setTitle("modelo");
            horizontalBarChartModel.setLegendPosition("e");
            horizontalBarChartModel.setShowPointLabels(true);
            horizontalBarChartModel.setAnimate(true);
            //  horizontalBarChartModel.setExtender("removeLegend");

        }

        return horizontalBarChartModel;
    }

    private BarChartModel initBarModelItem() {
        barModel = new BarChartModel();
        horizontalBarChartModelItemP = new HorizontalBarChartModel();
        for (Factores fact2 : listaFactores) {

            ChartSeries serie = new ChartSeries();
            serie.setLabel(fact2.getDescripcionFactor());
            serie.set("puntajes", fact2.getPuntuacion());
            horizontalBarChartModelItemP.addSeries(serie);
            horizontalBarChartModelItemP.setTitle("modelo");
            horizontalBarChartModelItemP.setLegendPosition("e");
            horizontalBarChartModelItemP.setShowPointLabels(true);
            horizontalBarChartModelItemP.setAnimate(true);
            //horizontalBarChartModelItemP.setExtender("removeLegend");

        }

        return horizontalBarChartModel;
    }

    public void listarGlobal() {

        try {
            factoresActual = new Factores();
            listaFactores = factoresFacade.listarGlobal();
            graficarGolbal();
            listaFactores = getFactoresFacade().listarHorGlobal();
            initBarModelGolbal();
            listaFactores = getFactoresFacade().listarItemGlobal();
            initBarModelItemGlobal();

            recargarLista();

        } catch (Exception e) {
            System.out.println("com.proyectoCFIP.controller.FactorController.listardos()");
        }
    }

    public PieChartModel graficarGolbal() {
        pieModelGlobal = new PieChartModel();

        for (Factores fact : listaFactores) {
            pieModelGlobal.set(fact.getDescripcionFactor(), fact.getPuntuacion());
            pieModelGlobal.setTitle("Promedios");
            pieModelGlobal.setLegendPosition("e");
            pieModelGlobal.setFill(false);
            pieModelGlobal.setShowDataLabels(true);
            pieModelGlobal.setMouseoverHighlight(true);
            pieModelGlobal.setShadow(true);
            pieModelGlobal.setDiameter(400);
            //pieModelGlobal.setExtender("removeLegend");
        }
        return pieModelGlobal;

    }

    private BarChartModel initBarModelGolbal() {
        //barModel = new BarChartModel();
        horizontalBarChartModelGlobal = new HorizontalBarChartModel();
        for (Factores fact2 : listaFactores) {

            ChartSeries serie = new ChartSeries();
            serie.setLabel(fact2.getDescripcionFactor());
            serie.set("Puntajes", fact2.getPuntuacion());
            horizontalBarChartModelGlobal.addSeries(serie);
            horizontalBarChartModelGlobal.setTitle("modelo");
            horizontalBarChartModelGlobal.setLegendPosition("e");
            horizontalBarChartModelGlobal.setShowPointLabels(true);
            horizontalBarChartModelGlobal.setAnimate(true);
            // horizontalBarChartModelGlobal.setExtender("removeLegend");

        }

        return horizontalBarChartModelGlobal;
    }

    private BarChartModel initBarModelItemGlobal() {
        //barModel = new BarChartModel();
        horizontalBarChartModelItemGlobal = new HorizontalBarChartModel();
        for (Factores fact2 : listaFactores) {

            ChartSeries serie = new ChartSeries();
            serie.setLabel(fact2.getDescripcionFactor());
            serie.set("Puntajes", fact2.getPuntuacion());
            horizontalBarChartModelItemGlobal.addSeries(serie);
            horizontalBarChartModelItemGlobal.setTitle("modelo");
            horizontalBarChartModelItemGlobal.setLegendPosition("e");
            horizontalBarChartModelItemGlobal.setShowPointLabels(true);
            horizontalBarChartModelItemGlobal.setAnimate(true);
            //horizontalBarChartModelItemGlobal.setExtender("removeLegend");

        }

        return horizontalBarChartModelItemGlobal;
    }
}
