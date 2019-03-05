package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.AuPlanAuditoria;
import com.proyectoCFIP.entities.AuProcesoEvaluado;
import com.proyectoCFIP.entities.CalidadGestionConocimiento;
import com.proyectoCFIP.entities.CalidadPlanAccion;
import com.proyectoCFIP.entities.CambioRegistro;
import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.CronogramaActividadesEdificios;
import com.proyectoCFIP.entities.CronogramaAnual;
import com.proyectoCFIP.entities.CronogramaMantenimientoMaquina;
import com.proyectoCFIP.entities.CronogramaMantenimientos;
import com.proyectoCFIP.entities.DiagnosticoManteDispositivo;
import com.proyectoCFIP.entities.DiagnosticoMantenimiento;
import com.proyectoCFIP.entities.DiagnosticoMantenimientoDisCon;
import com.proyectoCFIP.entities.DispositivoConectividad;
import com.proyectoCFIP.entities.Documentos;
import com.proyectoCFIP.entities.FtFichaTecnica;
import com.proyectoCFIP.entities.HistorialComputador;
import com.proyectoCFIP.entities.Idea;
import com.proyectoCFIP.entities.Libro;
import com.proyectoCFIP.entities.OtroDispositivo;
import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.entities.ReporteSiesa;
import com.proyectoCFIP.entities.ReservaLibrosBiblioteca;
import com.proyectoCFIP.entities.Roles;
import com.proyectoCFIP.entities.SolicitudEdificio;
import com.proyectoCFIP.entities.SstReporteUsuario;
import com.proyectoCFIP.entities.Valoracion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile ListAttribute<Usuario, FtFichaTecnica> ftFichaTecnicaList;
    public static volatile ListAttribute<Usuario, CambioRegistro> cambioRegistroList;
    public static volatile ListAttribute<Usuario, DiagnosticoManteDispositivo> diagnosticoManteDispositivoList;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile ListAttribute<Usuario, ReporteSiesa> reporteSiesaList;
    public static volatile ListAttribute<Usuario, CronogramaAnual> cronogramaAnualList;
    public static volatile ListAttribute<Usuario, CronogramaMantenimientos> cronogramaMantenimientosList;
    public static volatile ListAttribute<Usuario, CronogramaActividadesEdificios> cronogramaActividadesEdificiosList;
    public static volatile SingularAttribute<Usuario, String> nombreUsuario;
    public static volatile ListAttribute<Usuario, AuPlanAuditoria> auPlanAuditoriaList;
    public static volatile SingularAttribute<Usuario, Cargos> idCargo;
    public static volatile SingularAttribute<Usuario, Boolean> estadoContra;
    public static volatile ListAttribute<Usuario, AuProcesoEvaluado> auProcesoEvaluadoList;
    public static volatile SingularAttribute<Usuario, String> correoUsuario;
    public static volatile SingularAttribute<Usuario, String> contraUsuario;
    public static volatile ListAttribute<Usuario, HistorialComputador> historialComputadorList;
    public static volatile ListAttribute<Usuario, Documentos> documentosList;
    public static volatile ListAttribute<Usuario, CalidadGestionConocimiento> calidadGestionConocimientoList;
    public static volatile ListAttribute<Usuario, DiagnosticoMantenimientoDisCon> diagnosticoMantenimientoDisConList;
    public static volatile ListAttribute<Usuario, Valoracion> valoracionList;
    public static volatile ListAttribute<Usuario, Computador> computadorList;
    public static volatile ListAttribute<Usuario, CronogramaMantenimientoMaquina> cronogramaMantenimientoMaquinaList;
    public static volatile ListAttribute<Usuario, Procesos> procesosList;
    public static volatile SingularAttribute<Usuario, String> apellidoUsuario;
    public static volatile ListAttribute<Usuario, FtFichaTecnica> ftFichaTecnicaList1;
    public static volatile ListAttribute<Usuario, Libro> idUsuarioLibList;
    public static volatile ListAttribute<Usuario, Idea> ideaList;
    public static volatile ListAttribute<Usuario, SstReporteUsuario> sstReporteUsuarioList;
    public static volatile ListAttribute<Usuario, CalidadPlanAccion> calidadPlanAccionList;
    public static volatile ListAttribute<Usuario, DispositivoConectividad> dispositivoConectividadList;
    public static volatile ListAttribute<Usuario, ReservaLibrosBiblioteca> idBibliotearioList;
    public static volatile ListAttribute<Usuario, OtroDispositivo> otroDispositivoList;
    public static volatile ListAttribute<Usuario, Roles> rolesList;
    public static volatile ListAttribute<Usuario, DiagnosticoMantenimiento> diagnosticoMantenimientoList;
    public static volatile SingularAttribute<Usuario, Boolean> estadoUsuario;
    public static volatile ListAttribute<Usuario, ReservaLibrosBiblioteca> idUsuarioPrestamoList;
    public static volatile SingularAttribute<Usuario, Boolean> auditorInterno;
    public static volatile ListAttribute<Usuario, SolicitudEdificio> solicitudEdificioList;

}