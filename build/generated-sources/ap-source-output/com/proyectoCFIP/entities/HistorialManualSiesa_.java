package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.ManualSiesa;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(HistorialManualSiesa.class)
public class HistorialManualSiesa_ { 

    public static volatile SingularAttribute<HistorialManualSiesa, String> codigo;
    public static volatile SingularAttribute<HistorialManualSiesa, String> responsable;
    public static volatile SingularAttribute<HistorialManualSiesa, ManualSiesa> idManualSiesa;
    public static volatile SingularAttribute<HistorialManualSiesa, Date> fechaActualizacion;
    public static volatile SingularAttribute<HistorialManualSiesa, byte[]> manual;
    public static volatile SingularAttribute<HistorialManualSiesa, Integer> idHistorialManualSiesa;
    public static volatile SingularAttribute<HistorialManualSiesa, String> nombre;
    public static volatile SingularAttribute<HistorialManualSiesa, Double> version;

}