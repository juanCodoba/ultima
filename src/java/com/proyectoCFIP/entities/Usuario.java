/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import com.proyectoCFIP.controller.util.DigestUtil;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u ORDER BY u.estadoUsuario DESC"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Usuario.findByApellidoUsuario", query = "SELECT u FROM Usuario u WHERE u.apellidoUsuario = :apellidoUsuario"),
    @NamedQuery(name = "Usuario.findByCorreoUsuario", query = "SELECT u FROM Usuario u WHERE u.correoUsuario = :correoUsuario"),
    @NamedQuery(name = "Usuario.findByRol", query = "SELECT u FROM Usuario u WHERE u.rolesList = :rol"),
    @NamedQuery(name = "Usuario.findByDocumento", query = "SELECT u FROM Usuario u WHERE u.documentosList = :documento"),
    @NamedQuery(name = "Usuario.findByContraUsuario", query = "SELECT u FROM Usuario u WHERE u.contraUsuario = :contraUsuario"),
    @NamedQuery(name = "Usuario.findByIdCargo", query = "SELECT u FROM Usuario u WHERE   u.estadoUsuario = 1 and u.idCargo.idCargo != 53"),
    @NamedQuery(name = "Usuario.findByIdCargoEs", query = "SELECT u FROM Usuario u WHERE   u.estadoUsuario = 1 and u.idCargo.idCargo = 53"),
    @NamedQuery(name = "Usuario.findByIdCargoAutoma", query = "SELECT u FROM Usuario u WHERE   u.estadoUsuario = 1 and u.idCargo.idCargo = 54"),
    @NamedQuery(name = "Usuario.findByIdCargoDocentes", query = "SELECT u FROM Usuario u WHERE   u.estadoUsuario = 1 and u.idCargo.idCargo = 12"),
    @NamedQuery(name = "Usuario.findByEstadoUsuario", query = "SELECT u FROM Usuario u WHERE   u.estadoUsuario = 1"),
    @NamedQuery(name = "Usuario.findByEstadoUsuarioAuditor", query = "SELECT u FROM Usuario u WHERE u.auditorInterno = 1 "),

    @NamedQuery(name = "Usuario.findByTecnicos", query = "SELECT u FROM Usuario u WHERE   u.estadoUsuario = 1 and u.idCargo.idCargo = 55")})

public class Usuario implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<CalidadGestionConocimiento> calidadGestionConocimientoList;

    @ManyToMany(mappedBy = "usuarioList")
    private List<AuProcesoEvaluado> auProcesoEvaluadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<AuPlanAuditoria> auPlanAuditoriaList;

    @OneToMany(mappedBy = "elaborado")
    private List<FtFichaTecnica> ftFichaTecnicaList;
    @OneToMany(mappedBy = "aprobo")
    private List<FtFichaTecnica> ftFichaTecnicaList1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<SstReporteUsuario> sstReporteUsuarioList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsable")
    private List<CalidadPlanAccion> calidadPlanAccionList;

    @OneToMany(mappedBy = "idUsuario")
    private List<CronogramaMantenimientos> cronogramaMantenimientosList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Idea> ideaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBibliotecario")
    private List<ReservaLibrosBiblioteca> idBibliotearioList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuaroLib")
    private List<Libro> idUsuarioLibList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioPrestamo")
    private List<ReservaLibrosBiblioteca> idUsuarioPrestamoList;

    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_contra")
    private boolean estadoContra;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")

    private List<CronogramaActividadesEdificios> cronogramaActividadesEdificiosList;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idUsuario")
    private List<CronogramaMantenimientoMaquina> cronogramaMantenimientoMaquinaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<CronogramaAnual> cronogramaAnualList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<SolicitudEdificio> solicitudEdificioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<OtroDispositivo> otroDispositivoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apellido_usuario")
    private String apellidoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "correo_usuario")
    private String correoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "contra_usuario")
    private String contraUsuario;
    @ManyToMany(mappedBy = "usuarioList")
    private List<HistorialComputador> historialComputadorList;
    @JoinTable(name = "usuario_has_roles", joinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")}, inverseJoinColumns = {
        @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")})
    @ManyToMany
    private List<Roles> rolesList;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Procesos> procesosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<DiagnosticoManteDispositivo> diagnosticoManteDispositivoList;
    @OneToMany(mappedBy = "idUsuario")
    private List<Computador> computadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tecnicoResponsable")
    private List<DiagnosticoMantenimiento> diagnosticoMantenimientoList;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne(optional = false)
    private Cargos idCargo;
    @Column(name = "estado_usuario")
    private boolean estadoUsuario;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Documentos> documentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<DiagnosticoMantenimientoDisCon> diagnosticoMantenimientoDisConList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<DispositivoConectividad> dispositivoConectividadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<CambioRegistro> cambioRegistroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Valoracion> valoracionList;

    @Column(name = "auditor_interno")
    private boolean auditorInterno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<ReporteSiesa> reporteSiesaList;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String nombreUsuario, String apellidoUsuario, String correoUsuario, String contraUsuario, boolean estadoUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.correoUsuario = correoUsuario;
        this.contraUsuario = contraUsuario;
        this.estadoUsuario = estadoUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        if (nombreUsuario == null) {
            return "";
        }
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContraUsuario() {
        return contraUsuario;
    }

    public void setContraUsuario(String contraUsuario) {
        try {
            this.contraUsuario = DigestUtil.generateDigest(contraUsuario);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @XmlTransient
    public List<Documentos> getDocumentosList() {
        return documentosList;
    }

    public void setDocumentosList(List<Documentos> documentosList) {
        this.documentosList = documentosList;
    }

    @XmlTransient
    public List<HistorialComputador> getHistorialComputadorList() {
        return historialComputadorList;
    }

    public void setHistorialComputadorList(List<HistorialComputador> historialComputadorList) {
        this.historialComputadorList = historialComputadorList;
    }

    @XmlTransient
    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    @XmlTransient
    public List<Computador> getComputadorList() {
        return computadorList;
    }

    public void setComputadorList(List<Computador> computadorList) {
        this.computadorList = computadorList;
    }

    @XmlTransient
    public List<DiagnosticoManteDispositivo> getDiagnosticoManteDispositivoList() {
        return diagnosticoManteDispositivoList;
    }

    public void setDiagnosticoManteDispositivoList(List<DiagnosticoManteDispositivo> diagnosticoManteDispositivoList) {
        this.diagnosticoManteDispositivoList = diagnosticoManteDispositivoList;
    }

    @XmlTransient
    public List<DispositivoConectividad> getDispositivoConectividadList() {
        return dispositivoConectividadList;
    }

    public void setDispositivoConectividadList(List<DispositivoConectividad> dispositivoConectividadList) {
        this.dispositivoConectividadList = dispositivoConectividadList;
    }

    @XmlTransient
    public List<DiagnosticoMantenimiento> getDiagnosticoMantenimientoList() {
        return diagnosticoMantenimientoList;
    }

    public void setDiagnosticoMantenimientoList(List<DiagnosticoMantenimiento> diagnosticoMantenimientoList) {
        this.diagnosticoMantenimientoList = diagnosticoMantenimientoList;
    }

    public boolean getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(boolean estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public Cargos getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargos idCargo) {
        this.idCargo = idCargo;
    }

    @XmlTransient
    public List<Valoracion> getValoracionList() {
        return valoracionList;
    }

    public void setValoracionList(List<Valoracion> valoracionList) {
        this.valoracionList = valoracionList;
    }

    @XmlTransient
    public List<CambioRegistro> getCambioRegistroList() {
        return cambioRegistroList;
    }

    public void setCambioRegistroList(List<CambioRegistro> cambioRegistroList) {
        this.cambioRegistroList = cambioRegistroList;
    }

    public List<ReservaLibrosBiblioteca> getIdBibliotearioList() {
        return idBibliotearioList;
    }

    public void setIdBibliotearioList(List<ReservaLibrosBiblioteca> idBibliotearioList) {
        this.idBibliotearioList = idBibliotearioList;
    }

    public List<ReservaLibrosBiblioteca> getIdUsuarioPrestamoList() {
        return idUsuarioPrestamoList;
    }

    public void setIdUsuarioPrestamoList(List<ReservaLibrosBiblioteca> idUsuarioPrestamoList) {
        this.idUsuarioPrestamoList = idUsuarioPrestamoList;
    }

    public List<Libro> getIdUsuarioLibList() {
        return idUsuarioLibList;
    }

    public void setIdUsuarioLibList(List<Libro> idUsuarioLibList) {
        this.idUsuarioLibList = idUsuarioLibList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @XmlTransient
    public List<DiagnosticoMantenimientoDisCon> getDiagnosticoMantenimientoDisConList() {
        return diagnosticoMantenimientoDisConList;
    }

    public void setDiagnosticoMantenimientoDisConList(List<DiagnosticoMantenimientoDisCon> diagnosticoMantenimientoDisConList) {
        this.diagnosticoMantenimientoDisConList = diagnosticoMantenimientoDisConList;
    }

    @XmlTransient
    public List<Procesos> getProcesosList() {
        return procesosList;
    }

    public void setProcesosList(List<Procesos> procesosList) {
        this.procesosList = procesosList;
    }

    @Override
    public String toString() {
        return getNombreUsuario().toUpperCase() + " " + getApellidoUsuario().toUpperCase();
    }

    @XmlTransient
    public List<OtroDispositivo> getOtroDispositivoList() {
        return otroDispositivoList;
    }

    public void setOtroDispositivoList(List<OtroDispositivo> otroDispositivoList) {
        this.otroDispositivoList = otroDispositivoList;
    }

    @XmlTransient
    public List<SolicitudEdificio> getSolicitudEdificioList() {
        return solicitudEdificioList;
    }

    public void setSolicitudEdificioList(List<SolicitudEdificio> solicitudEdificioList) {
        this.solicitudEdificioList = solicitudEdificioList;
    }

    @XmlTransient
    public List<CronogramaAnual> getCronogramaAnualList() {
        return cronogramaAnualList;
    }

    public void setCronogramaAnualList(List<CronogramaAnual> cronogramaAnualList) {
        this.cronogramaAnualList = cronogramaAnualList;
    }

    @XmlTransient
    public List<CronogramaMantenimientoMaquina> getCronogramaMantenimientoMaquinaList() {
        return cronogramaMantenimientoMaquinaList;
    }

    public void setCronogramaMantenimientoMaquinaList(List<CronogramaMantenimientoMaquina> cronogramaMantenimientoMaquinaList) {
        this.cronogramaMantenimientoMaquinaList = cronogramaMantenimientoMaquinaList;
    }

    @XmlTransient
    public List<CronogramaActividadesEdificios> getCronogramaActividadesEdificiosList() {
        return cronogramaActividadesEdificiosList;
    }

    public void setCronogramaActividadesEdificiosList(List<CronogramaActividadesEdificios> cronogramaActividadesEdificiosList) {
        this.cronogramaActividadesEdificiosList = cronogramaActividadesEdificiosList;
    }

    public boolean getEstadoContra() {
        return estadoContra;
    }

    public void setEstadoContra(boolean estadoContra) {
        this.estadoContra = estadoContra;
    }

    @XmlTransient
    public List<Idea> getIdeaList() {
        return ideaList;
    }

    public void setIdeaList(List<Idea> ideaList) {
        this.ideaList = ideaList;
    }

    @XmlTransient
    public List<CronogramaMantenimientos> getCronogramaMantenimientosList() {
        return cronogramaMantenimientosList;
    }

    public void setCronogramaMantenimientosList(List<CronogramaMantenimientos> cronogramaMantenimientosList) {
        this.cronogramaMantenimientosList = cronogramaMantenimientosList;
    }

    @XmlTransient
    public List<CalidadPlanAccion> getCalidadPlanAccionList() {
        return calidadPlanAccionList;
    }

    public void setCalidadPlanAccionList(List<CalidadPlanAccion> calidadPlanAccionList) {
        this.calidadPlanAccionList = calidadPlanAccionList;
    }

    @XmlTransient
    public List<SstReporteUsuario> getSstReporteUsuarioList() {
        return sstReporteUsuarioList;
    }

    public void setSstReporteUsuarioList(List<SstReporteUsuario> sstReporteUsuarioList) {
        this.sstReporteUsuarioList = sstReporteUsuarioList;
    }

    @XmlTransient
    public List<FtFichaTecnica> getFtFichaTecnicaList() {
        return ftFichaTecnicaList;
    }

    public void setFtFichaTecnicaList(List<FtFichaTecnica> ftFichaTecnicaList) {
        this.ftFichaTecnicaList = ftFichaTecnicaList;
    }

    @XmlTransient
    public List<FtFichaTecnica> getFtFichaTecnicaList1() {
        return ftFichaTecnicaList1;
    }

    public void setFtFichaTecnicaList1(List<FtFichaTecnica> ftFichaTecnicaList1) {
        this.ftFichaTecnicaList1 = ftFichaTecnicaList1;
    }

    @XmlTransient
    public List<AuProcesoEvaluado> getAuProcesoEvaluadoList() {
        return auProcesoEvaluadoList;
    }

    public void setAuProcesoEvaluadoList(List<AuProcesoEvaluado> auProcesoEvaluadoList) {
        this.auProcesoEvaluadoList = auProcesoEvaluadoList;
    }

    @XmlTransient
    public List<AuPlanAuditoria> getAuPlanAuditoriaList() {
        return auPlanAuditoriaList;
    }

    public void setAuPlanAuditoriaList(List<AuPlanAuditoria> auPlanAuditoriaList) {
        this.auPlanAuditoriaList = auPlanAuditoriaList;
    }

    @XmlTransient
    public List<CalidadGestionConocimiento> getCalidadGestionConocimientoList() {
        return calidadGestionConocimientoList;
    }

    public void setCalidadGestionConocimientoList(List<CalidadGestionConocimiento> calidadGestionConocimientoList) {
        this.calidadGestionConocimientoList = calidadGestionConocimientoList;
    }

    public boolean isAuditorInterno() {
        return auditorInterno;
    }

    public void setAuditorInterno(boolean auditorInterno) {
        this.auditorInterno = auditorInterno;
    }

    public List<ReporteSiesa> getReporteSiesaList() {
        return reporteSiesaList;
    }

    public void setReporteSiesaList(List<ReporteSiesa> reporteSiesaList) {
        this.reporteSiesaList = reporteSiesaList;
    }
    
    

}
