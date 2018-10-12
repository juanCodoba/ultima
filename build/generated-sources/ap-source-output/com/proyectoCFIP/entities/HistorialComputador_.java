package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.TipoActualizacion;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(HistorialComputador.class)
public class HistorialComputador_ { 

    public static volatile SingularAttribute<HistorialComputador, String> descripcion;
    public static volatile SingularAttribute<HistorialComputador, String> sistemaOperativo;
    public static volatile SingularAttribute<HistorialComputador, String> responsable;
    public static volatile ListAttribute<HistorialComputador, Usuario> usuarioList;
    public static volatile SingularAttribute<HistorialComputador, Integer> costo;
    public static volatile SingularAttribute<HistorialComputador, Computador> idComputador;
    public static volatile SingularAttribute<HistorialComputador, TipoActualizacion> idTipoActualizacion;
    public static volatile SingularAttribute<HistorialComputador, Integer> idHistorialComputador;
    public static volatile SingularAttribute<HistorialComputador, String> monitor;
    public static volatile SingularAttribute<HistorialComputador, String> idLam;
    public static volatile SingularAttribute<HistorialComputador, String> seccion;
    public static volatile SingularAttribute<HistorialComputador, Date> fechaHistorial;
    public static volatile SingularAttribute<HistorialComputador, Double> discoDuro;
    public static volatile SingularAttribute<HistorialComputador, Double> memoriaRam;

}