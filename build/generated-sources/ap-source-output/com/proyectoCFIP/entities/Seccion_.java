package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Area;
import com.proyectoCFIP.entities.Bloque;
import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.CronogramaActividadesEdificios;
import com.proyectoCFIP.entities.OtroDispositivo;
import com.proyectoCFIP.entities.SolicitudEdificio;
import com.proyectoCFIP.entities.SstReporteUsuario;
import com.proyectoCFIP.entities.TipoSeccion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(Seccion.class)
public class Seccion_ { 

    public static volatile ListAttribute<Seccion, OtroDispositivo> otroDispositivoList;
    public static volatile SingularAttribute<Seccion, Area> idArea;
    public static volatile SingularAttribute<Seccion, Bloque> idBloque;
    public static volatile SingularAttribute<Seccion, TipoSeccion> idTipoSeccion;
    public static volatile ListAttribute<Seccion, CronogramaActividadesEdificios> cronogramaActividadesEdificiosList;
    public static volatile ListAttribute<Seccion, SstReporteUsuario> sstReporteUsuarioList;
    public static volatile ListAttribute<Seccion, SolicitudEdificio> solicitudEdificioList;
    public static volatile SingularAttribute<Seccion, Integer> idSeccion;
    public static volatile SingularAttribute<Seccion, String> nombreSeccion;
    public static volatile ListAttribute<Seccion, Computador> computadorList;

}