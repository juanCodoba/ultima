package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CronogramaManteDisCon;
import com.proyectoCFIP.entities.HistorialDispositivoConectividad;
import com.proyectoCFIP.entities.Modelo;
import com.proyectoCFIP.entities.Seccion;
import com.proyectoCFIP.entities.Tipo;
import com.proyectoCFIP.entities.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(DispositivoConectividad.class)
public class DispositivoConectividad_ { 

    public static volatile SingularAttribute<DispositivoConectividad, Boolean> estado;
    public static volatile SingularAttribute<DispositivoConectividad, String> codigo;
    public static volatile SingularAttribute<DispositivoConectividad, Integer> idDispositivoConectividad;
    public static volatile SingularAttribute<DispositivoConectividad, Usuario> idUsuario;
    public static volatile ListAttribute<DispositivoConectividad, CronogramaManteDisCon> cronogramaManteDisConList;
    public static volatile SingularAttribute<DispositivoConectividad, Tipo> idTipo;
    public static volatile SingularAttribute<DispositivoConectividad, Modelo> idModelo;
    public static volatile ListAttribute<DispositivoConectividad, HistorialDispositivoConectividad> historialDispositivoConectividadList;
    public static volatile SingularAttribute<DispositivoConectividad, Seccion> idSeccion;
    public static volatile SingularAttribute<DispositivoConectividad, Boolean> estadoProgramado;

}