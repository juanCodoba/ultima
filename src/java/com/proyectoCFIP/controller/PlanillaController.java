/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.EstadoConsecPlanilla;
import com.proyectoCFIP.entities.OpPlanilla;
import com.proyectoCFIP.entities.Planilla;
import com.proyectoCFIP.entities.UrlPlanilla;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.EstadoConsecPlanillaFacade;
import com.proyectoCFIP.sessions.OpPlanillaFacade;
import com.proyectoCFIP.sessions.PlanillaFacade;
import com.proyectoCFIP.sessions.UrlPlanillaFacade;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class PlanillaController implements Serializable {

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

    private UrlPlanilla urlPlanillaActual;
    private UrlPlanillaFacade urlPlanillaFacade;

    private EstadoConsecPlanilla estadoConsecPlanilla;
    private EstadoConsecPlanillaFacade estadoConsecPlanillaFacade;
    private List<EstadoConsecPlanilla> estadoConsecPlanillaLista;

    private Usuario usuarioActual;
    public String urlImmportada;

    String opPlanilla;
    String idFtCliente;
    String idUsuario;

    Integer precio;
    Integer planilla;
//    @EJB
//    private DiagnosticoPlanillaFacade diagnosticoPlanillaFacade;
//    private DiagnosticoPlanilla diagnosticoPlanillaActual;
//    private List<DiagnosticoPlanilla> diagnosticoPlanillaList;
    @EJB
    EmailSessionBean emailBean;

    public UrlPlanilla getUrlPlanillaActual() {
        return urlPlanillaActual;
    }

    public void setUrlPlanillaActual(UrlPlanilla urlPlanillaActual) {
        this.urlPlanillaActual = urlPlanillaActual;
    }

    public UrlPlanillaFacade getUrlPlanillaFacade() {
        return urlPlanillaFacade;
    }

    public void setUrlPlanillaFacade(UrlPlanillaFacade urlPlanillaFacade) {
        this.urlPlanillaFacade = urlPlanillaFacade;
    }

 
    public String getUrlImmportada() {
        return urlImmportada;
    }

    public void setUrlImmportada(String urlImmportada) {
        this.urlImmportada = urlImmportada;
    }



    public PlanillaController() {
    }

    public List<Planilla> getPlanillaListaPorOp() {
        return planillaListaPorOp = planillaFacade.consultaPlanillaOp(opPlanillaActual);
    }

    public void setPlanillaListaPorOp(List<Planilla> planillaListaPorOp) {
        this.planillaListaPorOp = planillaListaPorOp;
    }

    public Integer getPlanilla() {
        return planilla;
    }

    public void setPlanilla(Integer planilla) {
        this.planilla = planilla;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public Integer getValue(Planilla planilla) {

        for (Planilla p : planillaList) {
            value += planilla.getValorSubtotal();
        }
        return value;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
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

    public List<Planilla> getPlanillaListaActualPrueba() {
        return planillaListaActualPrueba = planillaFacade.consultaPlanillaOp(opPlanillaActual);
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

    public void prepareAspectoDos() {
        opPlanillaActual = new OpPlanilla();
    }

    public String prepareCreateUrlPlanilla() {
        urlPlanillaActual = new UrlPlanilla();

        return "/administrador/modPlanilla/datosEnMasa";
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

    public List<OpPlanilla> getListaUsuarioDelMes1() {
        listOpPlanilla = new ArrayList<>();
        return listOpPlanilla = getOpPlanillaFacade().consultaUsuariosDelMes1(planilla);
    }

    public String prepareListEstadoTicket() {
        return "/usuario/modSoporteSiesa/ticketsValoraciones/listaEstadoTicket";
    }

    public void recargarLista() {
        listOpPlanilla = null;
    }

    public Integer valueTotal(Planilla planillaIt) {
        Integer precioTotal = 0;
        for (Planilla planilla : planillaListaActualPrueba) {
            if (planillaIt.getValorSubtotal().equals(planilla.getValorSubtotal())) {
                precioTotal += planilla.getValorSubtotal();
            }
        }
        precioTotal = precio;
        System.out.println("" + precioTotal);
        return precio;
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

    public String addPrueba() {
        try {

            //opPlanillaActual.setPlanillaList(planillaListaActual);
            //addPvsCompelt();
            addSuccessMessage("Datos Actualizados", "El registro fue Actualizados con consecutivo No. " + opPlanillaActual.getOpPlanilla());
            return "/usuario/modPlanilla/list";
        } catch (Exception e) {
            return null;
        }
    }

    public void addPvsCompelt() {
        try {
            getPlanillaFacade().create(planillaActual);
            planillaListaActual.add(planillaActual);
            planillaActual = new Planilla();
            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void addPvsCompeltEdit() {
        try {

            planillaActual.setUndPendientes(planillaActual.getCantidad() - planillaActual.getConfeccion());
            if (planillaActual.getUndPendientes() == 0) {
                planillaActual.setIdEstadoConsecPlanilla(new EstadoConsecPlanilla(1));
            } else {
                planillaActual.setIdEstadoConsecPlanilla(new EstadoConsecPlanilla(2));
            }
            getPlanillaFacade().edit(planillaActual);
            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void addPvs() {
        try {
            planillaActual.setUndPendientes(planillaActual.getCantidad() - planillaActual.getConfeccion());
            planillaActual.setCliente(opPlanillaActual.getIdFtCliente().getNombre());
            planillaActual.setIdOpPlanilla(opPlanillaActual);
            planillaListaActual.add(planillaActual);
            planillaActual = new Planilla();
            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void addArchivo() {
        try {
            if (urlPlanillaActual.getUrl() == null) {
                System.out.println("No hay una archivo pendiente ");
            }
            urlPlanillaFacade.create(urlPlanillaActual);
            addSuccessMessage("Causa creada", "La causa fue creada");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void update() {
        try {
            opPlanillaActual.setIdUsuarioAsig(usuarioActual);
            getOpPlanillaFacade().edit(opPlanillaActual);
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
            PlanillaController controller = (PlanillaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "planillaController");
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
    public class ConexionMySQL {

        Connection conn = null;
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
        //public String password = "cfiprovidencia1";
        public String password = "1234";

        public Connection conectarMySQL() throws SQLException {

            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            return conn;
        }

        public Connection desconectar() throws SQLException {

            try {
                conn.close();
                //System.out.println("cerrlando conexion");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
            return conn;
        }
    }

    JasperPrint jasperPrint;

    public void printPO(ActionEvent actionEvent) throws JRException, IOException {

        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            HashMap parametros = new HashMap();
            parametros.put("opPlanilla", opPlanillaActual.getOpPlanilla());
            ConexionMySQL SQL = new ConexionMySQL();
            Connection conn = SQL.conectarMySQL();

            String report = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reporteOps.jasper");

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=ItinerarioOps.pdf");

            jasperPrint = JasperFillManager.fillReport(report, parametros, conn);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            addErrorMessage("Error Al Editar " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public void cargarFichaProduccion(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = "//172.16.0.240/Volume_1/pruebaManejo/" + "-datos.xls";
        //String newFileName = "/root/alojamientoFichasImg/02FICHASTECNICAS/" + "-datos.xls";

        urlPlanillaActual.setUrl(newFileName);
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch (IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
    }

    public void leerArchivo() {
        PlanillaController planillaExcel = new PlanillaController();
        planillaExcel.leerArchivoExcel(urlPlanillaActual.getUrl());
    }

    public void leerArchivoExcel(String archivoDestino) {
        int contador = 1;

        try {
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
            //recorre cada hoja
            for (int hojas = 0; hojas < archivoExcel.getNumberOfSheets(); hojas++) {
                Sheet hoja = archivoExcel.getSheet(hojas);
                int numColumnas = hoja.getColumns();
                int numFilas = hoja.getRows();
                String dato;
                ///recorre las filas de la hoja
                for (int fila = 1; fila < numFilas; fila++) {
                    for (int columna = 1; columna < numColumnas; columna++) {
                        //dato = hoja.getCell(columna,fila).getContents();
                        dato = hoja.getCell(columna, fila).getContents();
                        System.out.println(dato + "");
                        //////////instruccion switch asigna varialbe contador 
                        switch (contador) {
                            case 1:
                                opPlanilla = dato;
                                contador++ ;
                                break;
                            case 2:
                                idFtCliente = dato;
                                contador++ ;
                                break;
                            case 3:
                                idUsuario = dato;
                                contador = 1;
                                break;
                        }
                    }
                    ConexionMySQL con = new ConexionMySQL();
                    Connection cn = con.conectarMySQL();
                    String sentenciaSql = "INSERT INTO `op_planilla` (`op_planilla`, `id_ft_cliente`, `id_usuario`) VALUES ('" + opPlanilla + "','" + idFtCliente + "','" + idUsuario + "');";
                    PreparedStatement pst = cn.prepareStatement(sentenciaSql);
                    pst.executeUpdate();
                    con.desconectar();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
