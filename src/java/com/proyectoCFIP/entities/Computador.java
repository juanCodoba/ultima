/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "computador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Computador.findAll", query = "SELECT c FROM Computador c"),
    @NamedQuery(name = "Computador.findByIdComputador", query = "SELECT c FROM Computador c WHERE c.idComputador = :idComputador"),
    @NamedQuery(name = "Computador.findByIdLam", query = "SELECT c FROM Computador c WHERE c.idLam = :idLam"),
    @NamedQuery(name = "Computador.findByCodigoComputador", query = "SELECT c FROM Computador c WHERE c.codigoComputador = :codigoComputador"),
    @NamedQuery(name = "Computador.findByDiscoDuro", query = "SELECT c FROM Computador c WHERE c.discoDuro = :discoDuro"),
    @NamedQuery(name = "Computador.findByMemoriaRam", query = "SELECT c FROM Computador c WHERE c.memoriaRam = :memoriaRam"),
    @NamedQuery(name = "Computador.findByCosto", query = "SELECT c FROM Computador c WHERE c.costo = :costo"),
    @NamedQuery(name = "Computador.findByUsuario", query = "SELECT c FROM Computador c WHERE c.idUsuario = :idUsuario"),
    @NamedQuery(name = "Computador.findByUsuarioTipo", query = "SELECT c FROM Computador c WHERE c.idModelo.idTipo = :idTipo AND c.idUsuario = :idUsuario"),
    @NamedQuery(name = "Computador.findByArea", query = "SELECT c FROM Computador c WHERE c.idModelo.idTipo = :idTipo AND c.idSeccion.idArea = :idArea ORDER BY c.idSeccion.nombreSeccion"),
    @NamedQuery(name = "Computador.findByReport", query = "SELECT cr.idCronogramaMantenimientos, cr.descripcionProblema, cr.idComputador, cr.fechaProgMantenimiento, cr.fechaInicioMantenimiento, cr.estadoMantenimiento, cr.idTipoMantenimiento, c.codigoComputador FROM Computador c INNER JOIN CronogramaMantenimientos cr ON c.idComputador = cr.idComputador"),
    @NamedQuery(name = "Computador.findByEstadoComputador", query = "SELECT c FROM Computador c WHERE c.estadoComputador = :estadoComputador"),
    @NamedQuery(name = "Computador.findByEstadoProgramado", query = "SELECT c FROM Computador c WHERE c.estadoProgramado = :estadoProgramado AND c.estadoComputador =:estadoComputador")})
public class Computador implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComputador")
    private List<CronogramaComponente> cronogramaComponenteList;

    @Size(max = 80)
    @Column(name = "activo_fijo")
    private String activoFijo;

    @Size(max = 75)
    @Column(name = "procesador")
    private String procesador;

    @Size(max = 45)
    @Column(name = "computador_critico")
    private String computadorCritico;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
    @ManyToOne(optional = false)
    private Tipo idTipo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_computador")
    private Integer idComputador;
    @Size(max = 45)
    @Column(name = "id_lam")
    private String idLam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "codigo_computador")
    private String codigoComputador;
    @Basic(optional = false)
    @Column(name = "disco_duro")
    private Double discoDuro;
    @Column(name = "memoria_ram")
    private Double memoriaRam;
    @Column(name = "costo")
    private Integer costo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_computador")
    private boolean estadoComputador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_programado")
    private boolean estadoProgramado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComputador")
    private List<HistorialComputador> historialComputadorList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "id_modelo", referencedColumnName = "id_modelo")
    @ManyToOne(optional = false)
    private Modelo idModelo;
    @JoinColumn(name = "id_monitor", referencedColumnName = "id_monitor")
    @ManyToOne
    private Monitor idMonitor;
    @JoinColumn(name = "id_seccion", referencedColumnName = "id_seccion")
    @ManyToOne
    private Seccion idSeccion;
    @JoinColumn(name = "id_sistema_operativo", referencedColumnName = "id_sistema_operativo")
    @ManyToOne
    private SistemaOperativo idSistemaOperativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComputador")
    private List<CronogramaMantenimientos> cronogramaMantenimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComputador")
    private List<ComputadorInactivo> computadorInactivoList;
    @JoinTable(name = "computador_has_aplicacion", joinColumns = {
        @JoinColumn(name = "id_computador", referencedColumnName = "id_computador")}, inverseJoinColumns = {
        @JoinColumn(name = "id_aplicacion", referencedColumnName = "id_aplicacion")})
    @ManyToMany
    private List<Aplicacion> aplicacionList;
    @JoinColumn(name = "id_licencia", referencedColumnName = "id_licencia")
    @ManyToOne
    private Licencia idLicencia;
    @JoinColumn(name = "id_licencia_paquete_office", referencedColumnName = "id_licencia_paquete_office")
    @ManyToOne
    private LicenciaPaqueteOffice idLicenciaPaqueteOffice;
    @JoinColumn(name = "id_paquete_office", referencedColumnName = "id_paquete_office")
    @ManyToOne
    private PaqueteOffice idPaqueteOffice;
    @Size(max = 45)
    @Column(name = "direccion_ip_fija")
    private String direccionIpFija;
    public Computador() {
    }

    public Computador(Integer idComputador) {
        this.idComputador = idComputador;
    }

    public Computador(Integer idComputador, String codigoComputador, String procesador, boolean estadoComputador, boolean estadoProgramado) {
        this.idComputador = idComputador;
        this.codigoComputador = codigoComputador;
        this.procesador = procesador;
        this.estadoComputador = estadoComputador;
        this.estadoProgramado = estadoProgramado;
    }

    public Integer getIdComputador() {
        return idComputador;
    }

    public void setIdComputador(Integer idComputador) {
        this.idComputador = idComputador;
    }

    public String getIdLam() {
        return idLam;
    }

    public void setIdLam(String idLam) {
        this.idLam = idLam;
    }

    public String getCodigoComputador() {
        return codigoComputador;
    }

    public void setCodigoComputador(String codigoComputador) {
        this.codigoComputador = codigoComputador;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public Double getDiscoDuro() {
        return discoDuro;
    }

    public void setDiscoDuro(Double discoDuro) {
        this.discoDuro = discoDuro;
    }

    public Double getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(Double memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public boolean getEstadoComputador() {
        return estadoComputador;
    }

    public void setEstadoComputador(boolean estadoComputador) {
        this.estadoComputador = estadoComputador;
    }

    public boolean getEstadoProgramado() {
        return estadoProgramado;
    }

    public void setEstadoProgramado(boolean estadoProgramado) {
        this.estadoProgramado = estadoProgramado;
    }

    @XmlTransient
    public List<HistorialComputador> getHistorialComputadorList() {
        return historialComputadorList;
    }

    public void setHistorialComputadorList(List<HistorialComputador> historialComputadorList) {
        this.historialComputadorList = historialComputadorList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Modelo getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Modelo idModelo) {
        this.idModelo = idModelo;
    }

    public Monitor getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(Monitor idMonitor) {
        this.idMonitor = idMonitor;
    }

    public Seccion getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Seccion idSeccion) {
        this.idSeccion = idSeccion;
    }

    public SistemaOperativo getIdSistemaOperativo() {
        return idSistemaOperativo;
    }

    public void setIdSistemaOperativo(SistemaOperativo idSistemaOperativo) {
        this.idSistemaOperativo = idSistemaOperativo;
    }

    public PaqueteOffice getIdPaqueteOffice() {
        return idPaqueteOffice;
    }

    public void setIdPaqueteOffice(PaqueteOffice idPaqueteOffice) {
        this.idPaqueteOffice = idPaqueteOffice;
    }

    
    public LicenciaPaqueteOffice getIdLicenciaPaqueteOffice() {
        return idLicenciaPaqueteOffice;
    }

    public void setIdLicenciaPaqueteOffice(LicenciaPaqueteOffice idLicenciaPaqueteOffice) {
        this.idLicenciaPaqueteOffice = idLicenciaPaqueteOffice;
    }

    public String getDireccionIpFija() {
        return direccionIpFija;
    }

    public void setDireccionIpFija(String direccionIpFija) {
        this.direccionIpFija = direccionIpFija;
    }
    
    public String getEstado(){
        if(estadoProgramado== true){
            return "Programado";
        }else{
            return "Sin programar";
        }
    }
    
    @XmlTransient
    public List<CronogramaMantenimientos> getCronogramaMantenimientosList() {
        return cronogramaMantenimientosList;
    }

    public void setCronogramaMantenimientosList(List<CronogramaMantenimientos> cronogramaMantenimientosList) {
        this.cronogramaMantenimientosList = cronogramaMantenimientosList;
    }
    @XmlTransient
    public List<Aplicacion> getAplicacionList() {
        return aplicacionList;
    }

    public void setAplicacionList(List<Aplicacion> aplicacionList) {
        this.aplicacionList = aplicacionList;
    }
        public Licencia getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(Licencia idLicencia) {
        this.idLicencia = idLicencia;
    }
    @XmlTransient
    public List<ComputadorInactivo> getComputadorInactivoList() {
        return computadorInactivoList;
    }

    public void setComputadorInactivoList(List<ComputadorInactivo> computadorInactivoList) {
        this.computadorInactivoList = computadorInactivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComputador != null ? idComputador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Computador)) {
            return false;
        }
        Computador other = (Computador) object;
        if ((this.idComputador == null && other.idComputador != null) || (this.idComputador != null && !this.idComputador.equals(other.idComputador))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Lugar: "+getIdSeccion().getNombreSeccion().toUpperCase()+"  /   Codigo del equipo: "+getCodigoComputador().toUpperCase();
    }

    public String getComputadorCritico() {
        return computadorCritico;
    }

    public void setComputadorCritico(String computadorCritico) {
        this.computadorCritico = computadorCritico;
    }
    public Tipo getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Tipo idTipo) {
        this.idTipo = idTipo;
    }

    public String getActivoFijo() {
        return activoFijo;
    }

    public void setActivoFijo(String activoFijo) {
        this.activoFijo = activoFijo;
    }

    @XmlTransient
    public List<CronogramaComponente> getCronogramaComponenteList() {
        return cronogramaComponenteList;
    }

    public void setCronogramaComponenteList(List<CronogramaComponente> cronogramaComponenteList) {
        this.cronogramaComponenteList = cronogramaComponenteList;
    }
    
}
