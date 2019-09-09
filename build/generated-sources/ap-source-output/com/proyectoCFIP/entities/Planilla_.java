package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.EstadoConsecPlanilla;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-13T12:30:42")
@StaticMetamodel(Planilla.class)
public class Planilla_ { 

    public static volatile SingularAttribute<Planilla, String> moduloProd;
    public static volatile SingularAttribute<Planilla, Integer> idPlanilla;
    public static volatile SingularAttribute<Planilla, String> talla;
    public static volatile SingularAttribute<Planilla, Integer> valorSubtotal;
    public static volatile SingularAttribute<Planilla, Integer> undPendientes;
    public static volatile SingularAttribute<Planilla, EstadoConsecPlanilla> idEstadoConsecPlanilla;
    public static volatile SingularAttribute<Planilla, Date> fechaEntrCliente;
    public static volatile SingularAttribute<Planilla, Date> fechaProd;
    public static volatile SingularAttribute<Planilla, Date> fechaConfeccion;
    public static volatile SingularAttribute<Planilla, String> confeccion;
    public static volatile SingularAttribute<Planilla, Usuario> idUsuarioVendedor;
    public static volatile SingularAttribute<Planilla, String> bordado;
    public static volatile SingularAttribute<Planilla, Integer> corte;
    public static volatile SingularAttribute<Planilla, Date> fecha;
    public static volatile SingularAttribute<Planilla, Date> fechaCorte;
    public static volatile SingularAttribute<Planilla, String> oc;
    public static volatile SingularAttribute<Planilla, Usuario> idUsuarioAsig;
    public static volatile SingularAttribute<Planilla, String> pvPlanilla;
    public static volatile SingularAttribute<Planilla, String> descripcionPlanilla;
    public static volatile SingularAttribute<Planilla, String> observaciones;
    public static volatile SingularAttribute<Planilla, String> logo;
    public static volatile SingularAttribute<Planilla, String> estampado;
    public static volatile SingularAttribute<Planilla, Integer> cantidad;
    public static volatile SingularAttribute<Planilla, String> idItemPlanilla;

}