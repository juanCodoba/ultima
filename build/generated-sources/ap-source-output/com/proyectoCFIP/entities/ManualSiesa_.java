package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.HistorialManualSiesa;
import com.proyectoCFIP.entities.SubprocesoSuit;
import com.proyectoCFIP.entities.Suit;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(ManualSiesa.class)
public class ManualSiesa_ { 

    public static volatile SingularAttribute<ManualSiesa, String> descripcion;
    public static volatile ListAttribute<ManualSiesa, HistorialManualSiesa> historialManualSiesaList;
    public static volatile SingularAttribute<ManualSiesa, String> codigo;
    public static volatile SingularAttribute<ManualSiesa, Integer> idManualSiesa;
    public static volatile SingularAttribute<ManualSiesa, Usuario> idUsuario;
    public static volatile SingularAttribute<ManualSiesa, Date> fechaActualizacion;
    public static volatile SingularAttribute<ManualSiesa, byte[]> manual;
    public static volatile SingularAttribute<ManualSiesa, String> nombre;
    public static volatile SingularAttribute<ManualSiesa, Double> version;
    public static volatile SingularAttribute<ManualSiesa, Suit> idSuit;
    public static volatile SingularAttribute<ManualSiesa, SubprocesoSuit> idSubprocesoSuit;

}