package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Aplicacion;
import com.proyectoCFIP.entities.ComputadorInactivo;
import com.proyectoCFIP.entities.CronogramaComponente;
import com.proyectoCFIP.entities.CronogramaMantenimientos;
import com.proyectoCFIP.entities.HistorialComputador;
import com.proyectoCFIP.entities.Licencia;
import com.proyectoCFIP.entities.LicenciaPaqueteOffice;
import com.proyectoCFIP.entities.Modelo;
import com.proyectoCFIP.entities.Monitor;
import com.proyectoCFIP.entities.PaqueteOffice;
import com.proyectoCFIP.entities.Seccion;
import com.proyectoCFIP.entities.SistemaOperativo;
import com.proyectoCFIP.entities.Tipo;
import com.proyectoCFIP.entities.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(Computador.class)
public class Computador_ { 

    public static volatile SingularAttribute<Computador, PaqueteOffice> idPaqueteOffice;
    public static volatile ListAttribute<Computador, Aplicacion> aplicacionList;
    public static volatile SingularAttribute<Computador, Integer> idComputador;
    public static volatile SingularAttribute<Computador, Usuario> idUsuario;
    public static volatile ListAttribute<Computador, CronogramaMantenimientos> cronogramaMantenimientosList;
    public static volatile SingularAttribute<Computador, String> idLam;
    public static volatile SingularAttribute<Computador, Monitor> idMonitor;
    public static volatile SingularAttribute<Computador, Double> discoDuro;
    public static volatile SingularAttribute<Computador, Double> memoriaRam;
    public static volatile ListAttribute<Computador, HistorialComputador> historialComputadorList;
    public static volatile SingularAttribute<Computador, Modelo> idModelo;
    public static volatile SingularAttribute<Computador, Seccion> idSeccion;
    public static volatile SingularAttribute<Computador, String> activoFijo;
    public static volatile SingularAttribute<Computador, String> direccionIpFija;
    public static volatile SingularAttribute<Computador, Integer> costo;
    public static volatile ListAttribute<Computador, CronogramaComponente> cronogramaComponenteList;
    public static volatile SingularAttribute<Computador, String> codigoComputador;
    public static volatile SingularAttribute<Computador, String> procesador;
    public static volatile SingularAttribute<Computador, SistemaOperativo> idSistemaOperativo;
    public static volatile SingularAttribute<Computador, LicenciaPaqueteOffice> idLicenciaPaqueteOffice;
    public static volatile ListAttribute<Computador, ComputadorInactivo> computadorInactivoList;
    public static volatile SingularAttribute<Computador, String> computadorCritico;
    public static volatile SingularAttribute<Computador, Licencia> idLicencia;
    public static volatile SingularAttribute<Computador, Tipo> idTipo;
    public static volatile SingularAttribute<Computador, Boolean> estadoComputador;
    public static volatile SingularAttribute<Computador, Boolean> estadoProgramado;

}