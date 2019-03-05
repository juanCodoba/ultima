package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Actividad;
import com.proyectoCFIP.entities.Cargos;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-30T17:28:11")
@StaticMetamodel(MaeFuncion.class)
public class MaeFuncion_ { 

    public static volatile SingularAttribute<MaeFuncion, String> accion;
    public static volatile SingularAttribute<MaeFuncion, Integer> importancia;
    public static volatile SingularAttribute<MaeFuncion, Boolean> estado;
    public static volatile ListAttribute<MaeFuncion, Actividad> actividadList;
    public static volatile SingularAttribute<MaeFuncion, Boolean> responsable;
    public static volatile SingularAttribute<MaeFuncion, Integer> idMaeFuncion;
    public static volatile SingularAttribute<MaeFuncion, Boolean> aprobado;
    public static volatile SingularAttribute<MaeFuncion, String> objeto;
    public static volatile ListAttribute<MaeFuncion, Cargos> cargosList;
    public static volatile SingularAttribute<MaeFuncion, String> nombre;
    public static volatile SingularAttribute<MaeFuncion, String> condicion;
    public static volatile SingularAttribute<MaeFuncion, Boolean> consultado;
    public static volatile SingularAttribute<MaeFuncion, Boolean> informado;

}