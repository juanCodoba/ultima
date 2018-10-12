/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "ft_ficha_tecnica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtFichaTecnica.findAll", query = "SELECT f FROM FtFichaTecnica f "),
    @NamedQuery(name = "FtFichaTecnica.findByIdFtFichaTecnica", query = "SELECT f FROM FtFichaTecnica f WHERE f.idFtFichaTecnica = :idFtFichaTecnica"),
    @NamedQuery(name = "FtFichaTecnica.findByIdItem", query = "SELECT f.idItem FROM FtFichaTecnica f WHERE f.idItem = :idItem and f.idItem like '___'  "),
    @NamedQuery(name = "FtFichaTecnica.findByIdFtCategoria", query = "SELECT f FROM FtFichaTecnica f WHERE f.idFtCategoria = :idFtCategoria "),
    @NamedQuery(name = "FtFichaTecnica.findByCamisa", query = "SELECT f FROM FtFichaTecnica f WHERE f.idFtCategoria.idFtCategoria = 2"),
    @NamedQuery(name = "FtFichaTecnica.findByCamibuso", query = "SELECT f FROM FtFichaTecnica f WHERE f.idFtCategoria.idFtCategoria = 3"),
    @NamedQuery(name = "FtFichaTecnica.findByIdTipoFicha", query = "SELECT f FROM FtFichaTecnica f WHERE f.idTipoFicha = :idTipoFicha "),
    @NamedQuery(name = "FtFichaTecnica.findByFichaTec", query = "SELECT f FROM FtFichaTecnica f WHERE f.idTipoFicha.idTipoFicha = 1"),
    @NamedQuery(name = "FtFichaTecnica.findByOpm", query = "SELECT f FROM FtFichaTecnica f WHERE f.idTipoFicha.idTipoFicha = 2"),
    @NamedQuery(name = "FtFichaTecnica.findByDescripcionCorta", query = "SELECT f FROM FtFichaTecnica f WHERE f.descripcionCorta = :descripcionCorta"),
    @NamedQuery(name = "FtFichaTecnica.findByTelaPrincipal", query = "SELECT f FROM FtFichaTecnica f WHERE f.telaPrincipal = :telaPrincipal"),
    @NamedQuery(name = "FtFichaTecnica.findByTelaContraste", query = "SELECT f FROM FtFichaTecnica f WHERE f.telaContraste = :telaContraste"),
    @NamedQuery(name = "FtFichaTecnica.findByFechaModificacion", query = "SELECT f FROM FtFichaTecnica f WHERE f.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "FtFichaTecnica.findByVersion", query = "SELECT f FROM FtFichaTecnica f WHERE f.version = :version"),
    @NamedQuery(name = "FtFichaTecnica.findByEstado", query = "SELECT f FROM FtFichaTecnica f WHERE f.estado = :estado ORDER BY f.fechaModificacion DESC"),
    @NamedQuery(name = "FtFichaTecnica.findByEstadoVersion", query = "SELECT f FROM FtFichaTecnica f WHERE f.estado = 'VERSION' AND f.idItem = :idItem ORDER BY f.fechaModificacion DESC"),
    @NamedQuery(name = "FtFichaTecnica.findByEstadoDesarrollador", query = "SELECT f FROM FtFichaTecnica f WHERE f.estado = 'DESARROLLO' OR f.estado = 'MODIFICACION PQS' ORDER BY f.fechaModificacion DESC"),
    @NamedQuery(name = "FtFichaTecnica.findByEstadoAprovado", query = "SELECT f FROM FtFichaTecnica f WHERE f.estado = :estado"),
    @NamedQuery(name = "FtFichaTecnica.findByRutaImgFrontal", query = "SELECT f FROM FtFichaTecnica f WHERE f.rutaImgFrontal = :rutaImgFrontal"),
    @NamedQuery(name = "FtFichaTecnica.findByRutaImgPosterior", query = "SELECT f FROM FtFichaTecnica f WHERE f.rutaImgPosterior = :rutaImgPosterior"),
    @NamedQuery(name = "FtFichaTecnica.findByRutaImgCaracteristica1", query = "SELECT f FROM FtFichaTecnica f WHERE f.rutaImgCaracteristica1 = :rutaImgCaracteristica1"),
    @NamedQuery(name = "FtFichaTecnica.findByRutaImgCaracteristica2", query = "SELECT f FROM FtFichaTecnica f WHERE f.rutaImgCaracteristica2 = :rutaImgCaracteristica2"),
    @NamedQuery(name = "FtFichaTecnica.findByRutaImgCaracteristica3", query = "SELECT f FROM FtFichaTecnica f WHERE f.rutaImgCaracteristica3 = :rutaImgCaracteristica3"),
    @NamedQuery(name = "FtFichaTecnica.findByRutaImgCaracteristica4", query = "SELECT f FROM FtFichaTecnica f WHERE f.rutaImgCaracteristica4 = :rutaImgCaracteristica4"),
    @NamedQuery(name = "FtFichaTecnica.findByTelaPrincipal", query = "SELECT f FROM FtFichaTecnica f WHERE f.telaPrincipal = :telaPrincipal")})
public class FtFichaTecnica implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ftFichaTecnica")
    private List<FtModificaciones> ftModificacionesList;

    @JoinColumn(name = "validacion", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario validacion;

    @Lob
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;

    @Lob
    @Size(max = 2147483647)
    @Column(name = "medidas")
    private String medidas;

    @JoinColumn(name = "id_ft_cliente", referencedColumnName = "id_ft_cliente")
    @ManyToOne
    private FtCliente idFtCliente;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ft_ficha_tecnica")
    private Integer idFtFichaTecnica;
    @Size(max = 100)
    @Column(name = "id_item")
    private String idItem;
    @Size(max = 300)
    @Column(name = "descripcion_corta")
    private String descripcionCorta;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "puntos_criticos")
    private String descripcionLarga;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion_logo")
    private String descripcionLogo;
    @Size(max = 600)
    @Column(name = "tela_principal")
    private String telaPrincipal;
    @Size(max = 600)
    @Column(name = "tela_contraste")
    private String telaContraste;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "version")
    private Integer version;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @Size(max = 600)
    @Column(name = "ruta_img_frontal")
    private String rutaImgFrontal;
    @Size(max = 600)
    @Column(name = "ruta_img_posterior")
    private String rutaImgPosterior;
    @Size(max = 600)
    @Column(name = "ruta_img_caracteristica1")
    private String rutaImgCaracteristica1;
    @Size(max = 600)
    @Column(name = "ruta_img_caracteristica2")
    private String rutaImgCaracteristica2;
    @Size(max = 600)
    @Column(name = "ruta_img_caracteristica3")
    private String rutaImgCaracteristica3;
    @Size(max = 600)
    @Column(name = "ruta_img_caracteristica4")
    private String rutaImgCaracteristica4;
    @Size(max = 600)
    @Column(name = "ruta_ficha_logo")
    private String rutaFichaLogo;
    @JoinColumn(name = "elaborado", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario elaborado;
    @JoinColumn(name = "aprobo", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario aprobo;

    @JoinColumn(name = "id_ft_categoria", referencedColumnName = "id_ft_categoria")
    @ManyToOne(optional = false)
    private FtCategoria idFtCategoria;

    @JoinColumn(name = "id_tipo_ficha", referencedColumnName = "id_tipo_ficha")
    @ManyToOne(optional = false)
    private TipoFicha idTipoFicha;

    @Size(max = 10)
    @Column(name = "genero")
    private String genero;

    @Size(max = 600)
    @Column(name = "cuellos")
    private String cuello;
    @Size(max = 600)
    @Column(name = "puños")
    private String puños;
    @Size(max = 600)
    @Column(name = "bolsillos")
    private String bolsillos;
    @Size(max = 600)
    @Column(name = "espalda")
    private String espalda;
    @Size(max = 600)
    @Column(name = "costados")
    private String costados;
    @Size(max = 600)
    @Column(name = "aberturas")
    private String aberturas;
    @Size(max = 600)
    @Column(name = "dobladillo")
    private String dobladillo;
    @Size(max = 600)
    @Column(name = "marquilla")
    private String marquilla;
    @Size(max = 600)
    @Column(name = "reflectivo")
    private String reflectivo;
    @Size(max = 600)
    @Column(name = "adicionales")
    private String adicionales;
    @Size(max = 300)
    @Column(name = "bordado")
    private String bordado;
    @Size(max = 600)
    @Column(name = "estampado")
    private String estampado;
    @Size(max = 600)
    @Column(name = "planchado")
    private String planchado;
    @Size(max = 600)
    @Column(name = "empaque")
    private String empaque;
    @Size(max = 600)
    @Column(name = "ojal")
    private String ojal;
    @Size(max = 600)
    @Column(name = "boton")
    private String boton;
    @Size(max = 600)
    @Column(name = "presillas")
    private String presillas;
    @Size(max = 600)
    @Column(name = "puntadas")
    private String puntadas;
    @Size(max = 600)
    @Column(name = "cartera")
    private String cartera;
    @Size(max = 600)
    @Column(name = "figurado")
    private String figurado;
    @Size(max = 600)
    @Column(name = "pasadores")
    private String pasadores;
    @Size(max = 600)
    @Column(name = "tiros_laterales")
    private String tirosLaterales;
    @Size(max = 600)
    @Column(name = "entrepierna")
    private String entrepierna;
    @Size(max = 600)
    @Column(name = "bota")
    private String bota;
    @Size(max = 600)
    @Column(name = "prelavado")
    private String prelavado;
    @Size(max = 600)
    @Column(name = "pretina")
    private String pretina;
    @Size(max = 600)
    @Column(name = "hombros")
    private String hombros;
    @Size(max = 600)
    @Column(name = "mangas")
    private String mangas;

    @Size(max = 600)
    @Column(name = "union_mangas")
    private String unionMangas;
    @Size(max = 600)
    @Column(name = "ajuste_frente")
    private String ajusteFrente;
    @Size(max = 600)
    @Column(name = "capucha")
    private String capucha;
    @Size(max = 600)
    @Column(name = "pinzas")
    private String pinzas;
    @Size(max = 600)
    @Column(name = "prespuntes")
    private String prespuntes;

    @Size(max = 600)
    @Column(name = "tiras")
    private String tiras;

    @Size(max = 600)
    @Column(name = "union_hombros")
    private String unionHombros;

    @Size(max = 600)
    @Column(name = "cortes")
    private String cortes;

    @Size(max = 600)
    @Column(name = "cerrado_costados")
    private String cerradoCostados;

    @Size(max = 600)
    @Column(name = "cierre")
    private String cierre;

    @Size(max = 600)
    @Column(name = "cotilla")
    private String cotilla;

    public FtFichaTecnica() {
    }

    public FtFichaTecnica(Integer idFtFichaTecnica) {
        this.idFtFichaTecnica = idFtFichaTecnica;
    }

    public Integer getIdFtFichaTecnica() {
        return idFtFichaTecnica;
    }

    public void setIdFtFichaTecnica(Integer idFtFichaTecnica) {
        this.idFtFichaTecnica = idFtFichaTecnica;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public String getDescripcionLogo() {
        return descripcionLogo;
    }

    public void setDescripcionLogo(String descripcionLogo) {
        this.descripcionLogo = descripcionLogo;
    }

    public String getTelaPrincipal() {
        return telaPrincipal;
    }

    public void setTelaPrincipal(String telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    public String getTelaContraste() {
        return telaContraste;
    }

    public void setTelaContraste(String telaContraste) {
        this.telaContraste = telaContraste;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRutaImgFrontal() {
        return rutaImgFrontal;
    }

    public void setRutaImgFrontal(String rutaImgFrontal) {
        this.rutaImgFrontal = rutaImgFrontal;
    }

    public String getRutaImgPosterior() {
        return rutaImgPosterior;
    }

    public void setRutaImgPosterior(String rutaImgPosterior) {
        this.rutaImgPosterior = rutaImgPosterior;
    }

    public String getRutaImgCaracteristica1() {
        return rutaImgCaracteristica1;
    }

    public void setRutaImgCaracteristica1(String rutaImgCaracteristica1) {
        this.rutaImgCaracteristica1 = rutaImgCaracteristica1;
    }

    public String getRutaImgCaracteristica2() {
        return rutaImgCaracteristica2;
    }

    public void setRutaImgCaracteristica2(String rutaImgCaracteristica2) {
        this.rutaImgCaracteristica2 = rutaImgCaracteristica2;
    }

    public String getRutaImgCaracteristica3() {
        return rutaImgCaracteristica3;
    }

    public void setRutaImgCaracteristica3(String rutaImgCaracteristica3) {
        this.rutaImgCaracteristica3 = rutaImgCaracteristica3;
    }

    public String getRutaImgCaracteristica4() {
        return rutaImgCaracteristica4;
    }

    public void setRutaImgCaracteristica4(String rutaImgCaracteristica4) {
        this.rutaImgCaracteristica4 = rutaImgCaracteristica4;
    }

    public Usuario getElaborado() {
        return elaborado;
    }

    public void setElaborado(Usuario elaborado) {
        this.elaborado = elaborado;
    }

    public Usuario getAprobo() {
        return aprobo;
    }

    public void setAprobo(Usuario aprobo) {
        this.aprobo = aprobo;
    }

    public FtCategoria getIdFtCategoria() {
        return idFtCategoria;
    }

    public void setIdFtCategoria(FtCategoria idFtCategoria) {
        this.idFtCategoria = idFtCategoria;
    }

    public TipoFicha getIdTipoFicha() {
        return idTipoFicha;
    }

    public void setIdTipoFicha(TipoFicha idTipoFicha) {
        this.idTipoFicha = idTipoFicha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFtFichaTecnica != null ? idFtFichaTecnica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtFichaTecnica)) {
            return false;
        }
        FtFichaTecnica other = (FtFichaTecnica) object;
        if ((this.idFtFichaTecnica == null && other.idFtFichaTecnica != null) || (this.idFtFichaTecnica != null && !this.idFtFichaTecnica.equals(other.idFtFichaTecnica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "COD ITEM. " + getIdItem() + " | " + getDescripcionCorta();
    }

    public FtCliente getIdFtCliente() {
        return idFtCliente;
    }

    public void setIdFtCliente(FtCliente idFtCliente) {
        this.idFtCliente = idFtCliente;
    }

    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Usuario getValidacion() {
        return validacion;
    }

    public void setValidacion(Usuario validacion) {
        this.validacion = validacion;
    }

    @XmlTransient
    public List<FtModificaciones> getFtModificacionesList() {
        return ftModificacionesList;
    }

    public void setFtModificacionesList(List<FtModificaciones> ftModificacionesList) {
        this.ftModificacionesList = ftModificacionesList;
    }

    public String getRutaFichaLogo() {
        return rutaFichaLogo;
    }

    public void setRutaFichaLogo(String rutaFichaLogo) {
        this.rutaFichaLogo = rutaFichaLogo;
    }

    public void setImagenesList(String newFileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCuello() {
        return cuello;
    }

    public void setCuello(String cuello) {
        this.cuello = cuello;
    }

    public String getPuños() {
        return puños;
    }

    public void setPuños(String puños) {
        this.puños = puños;
    }

    public String getBolsillos() {
        return bolsillos;
    }

    public void setBolsillos(String bolsillos) {
        this.bolsillos = bolsillos;
    }

    public String getEspalda() {
        return espalda;
    }

    public void setEspalda(String espalda) {
        this.espalda = espalda;
    }

    public String getCostados() {
        return costados;
    }

    public void setCostados(String costados) {
        this.costados = costados;
    }

    public String getAberturas() {
        return aberturas;
    }

    public void setAberturas(String aberturas) {
        this.aberturas = aberturas;
    }

    public String getDobladillo() {
        return dobladillo;
    }

    public void setDobladillo(String dobladillo) {
        this.dobladillo = dobladillo;
    }

    public String getMarquilla() {
        return marquilla;
    }

    public void setMarquilla(String marquilla) {
        this.marquilla = marquilla;
    }

    public String getReflectivo() {
        return reflectivo;
    }

    public void setReflectivo(String reflectivo) {
        this.reflectivo = reflectivo;
    }

    public String getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(String adicionales) {
        this.adicionales = adicionales;
    }

    public String getBordado() {
        return bordado;
    }

    public void setBordado(String bordado) {
        this.bordado = bordado;
    }

    public String getEstampado() {
        return estampado;
    }

    public void setEstampado(String estampado) {
        this.estampado = estampado;
    }

    public String getPlanchado() {
        return planchado;
    }

    public void setPlanchado(String planchado) {
        this.planchado = planchado;
    }

    public String getEmpaque() {
        return empaque;
    }

    public void setEmpaque(String empaque) {
        this.empaque = empaque;
    }

    public String getOjal() {
        return ojal;
    }

    public void setOjal(String ojal) {
        this.ojal = ojal;
    }

    public String getBoton() {
        return boton;
    }

    public void setBoton(String boton) {
        this.boton = boton;
    }

    public String getPresillas() {
        return presillas;
    }

    public void setPresillas(String presillas) {
        this.presillas = presillas;
    }

    public String getPuntadas() {
        return puntadas;
    }

    public void setPuntadas(String puntadas) {
        this.puntadas = puntadas;
    }

    public String getCartera() {
        return cartera;
    }

    public void setCartera(String cartera) {
        this.cartera = cartera;
    }

    public String getFigurado() {
        return figurado;
    }

    public void setFigurado(String figurado) {
        this.figurado = figurado;
    }

    public String getPasadores() {
        return pasadores;
    }

    public void setPasadores(String pasadores) {
        this.pasadores = pasadores;
    }

    public String getTirosLaterales() {
        return tirosLaterales;
    }

    public void setTirosLaterales(String tirosLaterales) {
        this.tirosLaterales = tirosLaterales;
    }

    public String getEntrepierna() {
        return entrepierna;
    }

    public void setEntrepierna(String entrepierna) {
        this.entrepierna = entrepierna;
    }

    public String getBota() {
        return bota;
    }

    public void setBota(String bota) {
        this.bota = bota;
    }

    public String getPrelavado() {
        return prelavado;
    }

    public void setPrelavado(String prelavado) {
        this.prelavado = prelavado;
    }

    public String getPretina() {
        return pretina;
    }

    public void setPretina(String pretina) {
        this.pretina = pretina;
    }

    public String getHombros() {
        return hombros;
    }

    public void setHombros(String hombros) {
        this.hombros = hombros;
    }

    public String getMangas() {
        return mangas;
    }

    public void setMangas(String mangas) {
        this.mangas = mangas;
    }

    public String getUnionMangas() {
        return unionMangas;
    }

    public void setUnionMangas(String unionMangas) {
        this.unionMangas = unionMangas;
    }

    public String getAjusteFrente() {
        return ajusteFrente;
    }

    public void setAjusteFrente(String ajusteFrente) {
        this.ajusteFrente = ajusteFrente;
    }

    public String getCapucha() {
        return capucha;
    }

    public void setCapucha(String capucha) {
        this.capucha = capucha;
    }

    public String getPinzas() {
        return pinzas;
    }

    public void setPinzas(String pinzas) {
        this.pinzas = pinzas;
    }

    public String getPrespuntes() {
        return prespuntes;
    }

    public void setPrespuntes(String prespuntes) {
        this.prespuntes = prespuntes;
    }

    public String getTiras() {
        return tiras;
    }

    public void setTiras(String tiras) {
        this.tiras = tiras;
    }

    public String getUnionHombros() {
        return unionHombros;
    }

    public void setUnionHombros(String unionHombros) {
        this.unionHombros = unionHombros;
    }

    public String getCortes() {
        return cortes;
    }

    public void setCortes(String cortes) {
        this.cortes = cortes;
    }

    public String getCerradoCostados() {
        return cerradoCostados;
    }

    public void setCerradoCostados(String cerradoCostados) {
        this.cerradoCostados = cerradoCostados;
    }

    public String getCierre() {
        return cierre;
    }

    public void setCierre(String cierre) {
        this.cierre = cierre;
    }

    public String getCotilla() {
        return cotilla;
    }

    public void setCotilla(String cotilla) {
        this.cotilla = cotilla;
    }

    
    
    
    
}
