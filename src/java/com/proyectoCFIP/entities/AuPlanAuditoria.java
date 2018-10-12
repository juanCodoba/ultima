/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author junio
 */
@Entity
@Table(name = "au_plan_auditoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuPlanAuditoria.findAll", query = "SELECT a FROM AuPlanAuditoria a"),
    @NamedQuery(name = "AuPlanAuditoria.findByIdPlanAuditoria", query = "SELECT a FROM AuPlanAuditoria a WHERE a.idPlanAuditoria = :idPlanAuditoria "),
    @NamedQuery(name = "AuPlanAuditoria.findByTipoAuditoria", query = "SELECT a FROM AuPlanAuditoria a WHERE a.tipoAuditoria = :tipoAuditoriaCo ORDER BY a.idPlanAuditoria DESC "),
    @NamedQuery(name = "AuPlanAuditoria.findByTipoAuditoriaCalidad", query = "SELECT a FROM AuPlanAuditoria a WHERE a.tipoAuditoria = :tipoAuditoriaC ORDER BY a.idPlanAuditoria DESC "),
    @NamedQuery(name = "AuPlanAuditoria.findByTipoAuditoriaExterna", query = "SELECT a FROM AuPlanAuditoria a WHERE a.tipoAuditoria = :tipoAuditoriaE ORDER BY a.idPlanAuditoria DESC"),
    @NamedQuery(name = "AuPlanAuditoria.findByFecha", query = "SELECT a FROM AuPlanAuditoria a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "AuPlanAuditoria.findByTipoAnio", query = "SELECT a FROM AuPlanAuditoria a WHERE a.idAuPeriodoPlanAuditoria =:idAuPeriodoPlanAuditoria ORDER BY a.idPlanAuditoria DESC"),
    @NamedQuery(name = "AuPlanAuditoria.findByDocumento1", query = "SELECT a FROM AuPlanAuditoria a WHERE a.documento1 = :documento1"),
    @NamedQuery(name = "AuPlanAuditoria.findByDocumento2", query = "SELECT a FROM AuPlanAuditoria a WHERE a.documento2 = :documento2"),
    @NamedQuery(name = "AuPlanAuditoria.findByDocumento3", query = "SELECT a FROM AuPlanAuditoria a WHERE a.documento3 = :documento3"),
    @NamedQuery(name = "AuPlanAuditoria.findByDocumento4", query = "SELECT a FROM AuPlanAuditoria a WHERE a.documento4 = :documento4")})
public class AuPlanAuditoria implements Serializable {

    @JoinColumn(name = "id_au_periodo_plan_auditoria", referencedColumnName = "id_au_periodo_plan_auditoria")
    @ManyToOne
    private AuPeriodoPlanAuditoria idAuPeriodoPlanAuditoria;

    @Column(name = "enero")
    private Boolean enero;
    @Column(name = "febrero")
    private Boolean febrero;
    @Column(name = "marzo")
    private Boolean marzo;
    @Column(name = "abril")
    private Boolean abril;
    @Column(name = "mayo")
    private Boolean mayo;
    @Column(name = "junio")
    private Boolean junio;
    @Column(name = "julio")
    private Boolean julio;
    @Column(name = "agosto")
    private Boolean agosto;
    @Column(name = "septiembre")
    private Boolean septiembre;
    @Column(name = "octubre")
    private Boolean octubre;
    @Column(name = "noviembre")
    private Boolean noviembre;
    @Column(name = "diciembre")
    private Boolean diciembre;
    @Size(max = 155)
    @Column(name = "informe_final")
    private String informeFinal;
    @Size(max = 150)
    @Column(name = "acta_socializacion")
    private String actaSocializacion;

    @OneToMany(mappedBy = "idPlanAuditoria")
    private List<CalidadPlanAccion> calidadPlanAccionList;

    @Size(max = 150)
    @Column(name = "titulo")
    private String titulo;

    @OneToMany(mappedBy = "idPlanAuditoria")
    private List<AuProcesoEvaluado> auProcesoEvaluadoList;



    @Size(max = 45)
    @Column(name = "estado")
    private String estado;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan_auditoria")
    private Integer idPlanAuditoria;
    @Size(max = 45)
    @Column(name = "tipo_auditoria")
    private String tipoAuditoria;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "objetivo")
    private String objetivo;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "alcance")
    private String alcance;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "criterios")
    private String criterios;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 100)
    @Column(name = "documento1")
    private String documento1;
    @Size(max = 100)
    @Column(name = "documento2")
    private String documento2;
    @Size(max = 100)
    @Column(name = "documento3")
    private String documento3;
    @Size(max = 100)
    @Column(name = "documento4")
    private String documento4;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @Column(name = "fecha_inicio_auditoria")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIAuditoria;
    @Column(name = "fecha_fin_auditoria")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafAuditoria;

    public AuPlanAuditoria() {
    }

    public AuPlanAuditoria(Integer idPlanAuditoria) {
        this.idPlanAuditoria = idPlanAuditoria;
    }

    public Integer getIdPlanAuditoria() {
        return idPlanAuditoria;
    }

    public void setIdPlanAuditoria(Integer idPlanAuditoria) {
        this.idPlanAuditoria = idPlanAuditoria;
    }

    public String getTipoAuditoria() {
        return tipoAuditoria;
    }

    public void setTipoAuditoria(String tipoAuditoria) {
        this.tipoAuditoria = tipoAuditoria;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public String getCriterios() {
        return criterios;
    }

    public void setCriterios(String criterios) {
        this.criterios = criterios;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDocumento1() {
        return documento1;
    }

    public void setDocumento1(String documento1) {
        this.documento1 = documento1;
    }

    public String getDocumento2() {
        return documento2;
    }

    public void setDocumento2(String documento2) {
        this.documento2 = documento2;
    }

    public String getDocumento3() {
        return documento3;
    }

    public void setDocumento3(String documento3) {
        this.documento3 = documento3;
    }

    public String getDocumento4() {
        return documento4;
    }

    public void setDocumento4(String documento4) {
        this.documento4 = documento4;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaIAuditoria() {
        return fechaIAuditoria;
    }

    public void setFechaIAuditoria(Date fechaIAuditoria) {
        this.fechaIAuditoria = fechaIAuditoria;
    }

    public Date getFechafAuditoria() {
        return fechafAuditoria;
    }

    public void setFechafAuditoria(Date fechafAuditoria) {
        this.fechafAuditoria = fechafAuditoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanAuditoria != null ? idPlanAuditoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuPlanAuditoria)) {
            return false;
        }
        AuPlanAuditoria other = (AuPlanAuditoria) object;
        if ((this.idPlanAuditoria == null && other.idPlanAuditoria != null) || (this.idPlanAuditoria != null && !this.idPlanAuditoria.equals(other.idPlanAuditoria))) {
            return false;
        }
        return true;
    }

    public String getFechaAñoString() {
        String convertido;
        DateFormat fechaString = new SimpleDateFormat("yyyy");
        convertido = fechaString.format(fecha);
        return convertido;
    }

    public String getFechaMesString() {
        String convertido;
        DateFormat fechaString = new SimpleDateFormat("MMMM");
        convertido = fechaString.format(fecha);
        return convertido;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.AuPlanAuditoria[ idPlanAuditoria=" + idPlanAuditoria + " ]";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<AuProcesoEvaluado> getAuProcesoEvaluadoList() {
        return auProcesoEvaluadoList;
    }

    public void setAuProcesoEvaluadoList(List<AuProcesoEvaluado> auProcesoEvaluadoList) {
        this.auProcesoEvaluadoList = auProcesoEvaluadoList;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlTransient
    public List<CalidadPlanAccion> getCalidadPlanAccionList() {
        return calidadPlanAccionList;
    }

    public void setCalidadPlanAccionList(List<CalidadPlanAccion> calidadPlanAccionList) {
        this.calidadPlanAccionList = calidadPlanAccionList;
    }

    public String getInformeFinal() {
        return informeFinal;
    }

    public void setInformeFinal(String informeFinal) {
        this.informeFinal = informeFinal;
    }

    public String getActaSocializacion() {
        return actaSocializacion;
    }

    public void setActaSocializacion(String actaSocializacion) {
        this.actaSocializacion = actaSocializacion;
    }

    public Boolean getEnero() {
        return enero;
    }

    public void setEnero(Boolean enero) {
        this.enero = enero;
    }

    public Boolean getFebrero() {
        return febrero;
    }

    public void setFebrero(Boolean febrero) {
        this.febrero = febrero;
    }

    public Boolean getMarzo() {
        return marzo;
    }

    public void setMarzo(Boolean marzo) {
        this.marzo = marzo;
    }

    public Boolean getAbril() {
        return abril;
    }

    public void setAbril(Boolean abril) {
        this.abril = abril;
    }

    public Boolean getMayo() {
        return mayo;
    }

    public void setMayo(Boolean mayo) {
        this.mayo = mayo;
    }

    public Boolean getJunio() {
        return junio;
    }

    public void setJunio(Boolean junio) {
        this.junio = junio;
    }

    public Boolean getJulio() {
        return julio;
    }

    public void setJulio(Boolean julio) {
        this.julio = julio;
    }

    public Boolean getAgosto() {
        return agosto;
    }

    public void setAgosto(Boolean agosto) {
        this.agosto = agosto;
    }

    public Boolean getSeptiembre() {
        return septiembre;
    }

    public void setSeptiembre(Boolean septiembre) {
        this.septiembre = septiembre;
    }

    public Boolean getOctubre() {
        return octubre;
    }

    public void setOctubre(Boolean octubre) {
        this.octubre = octubre;
    }

    public Boolean getNoviembre() {
        return noviembre;
    }

    public void setNoviembre(Boolean noviembre) {
        this.noviembre = noviembre;
    }

    public Boolean getDiciembre() {
        return diciembre;
    }

    public void setDiciembre(Boolean diciembre) {
        this.diciembre = diciembre;
    }

    public String getEneroLetra() {
        if (enero == false) {
            return " ";
        } else {
            return "X";
        }
    }

    public String getFebreroLetra() {
        if (febrero == false) {
            return " ";
        } else {
            return "X";
        }
    }

    public String getMarzoLetra() {
        if (marzo == false) {
            return " ";
        } else {
            return "X";
        }
    }

    public String getAbrilLetra() {
        if (abril == false) {
            return " ";
        } else {
            return "X";
        }
    }

    public String getMayoLetra() {
        if (mayo == false) {
            return " ";
        } else {
            return "X";
        }
    }

    public String getJunioLetra() {
        if (junio == false) {
            return " ";
        } else {
            return "X";
        }
    }

    public String getJulioLetra() {
        if (julio == false) {
            return " ";
        } else {
            return "X";
        }
    }

    public String getAgostoLetra() {
        if (agosto == false) {
            return " ";
        } else {
            return "X";
        }
    }

    public String getSeptiembreLetra() {
        if (septiembre == false) {
            return " ";
        } else {
            return "X";
        }
    }

    public String getOctubreLetra() {
        if (octubre == false) {
            return " ";
        } else {
            return "X";
        }
    }

    public String getNoviembreLetra() {
        if (noviembre == false) {
            return " ";
        } else {
            return "X";
        }
    }

    public String getDiciembreLetra() {
        if (diciembre == false) {
            return " ";
        } else {
            return "X";
        }
    }

    public AuPeriodoPlanAuditoria getIdAuPeriodoPlanAuditoria() {
        return idAuPeriodoPlanAuditoria;
    }

    public void setIdAuPeriodoPlanAuditoria(AuPeriodoPlanAuditoria idAuPeriodoPlanAuditoria) {
        this.idAuPeriodoPlanAuditoria = idAuPeriodoPlanAuditoria;
    }


    
    



}
