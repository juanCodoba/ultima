package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CronogramaManteDispositivo;
import com.proyectoCFIP.entities.HistorialOtroDispositivo;
import com.proyectoCFIP.entities.Modelo;
import com.proyectoCFIP.entities.Seccion;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(OtroDispositivo.class)
public class OtroDispositivo_ { 

    public static volatile SingularAttribute<OtroDispositivo, String> codigoDispositivo;
    public static volatile ListAttribute<OtroDispositivo, CronogramaManteDispositivo> cronogramaManteDispositivoList;
    public static volatile SingularAttribute<OtroDispositivo, String> caracteristica;
    public static volatile ListAttribute<OtroDispositivo, HistorialOtroDispositivo> historialOtroDispositivoList;
    public static volatile SingularAttribute<OtroDispositivo, Usuario> idUsuario;
    public static volatile SingularAttribute<OtroDispositivo, Modelo> idModelo;
    public static volatile SingularAttribute<OtroDispositivo, Seccion> idSeccion;
    public static volatile SingularAttribute<OtroDispositivo, Integer> costoDispositivo;
    public static volatile SingularAttribute<OtroDispositivo, Boolean> estadoDispositivo;
    public static volatile SingularAttribute<OtroDispositivo, Integer> idOtroDispositivo;
    public static volatile SingularAttribute<OtroDispositivo, Boolean> estadoProgramadoDis;
    public static volatile SingularAttribute<OtroDispositivo, Date> fechaCambioRepuesto;

}