package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Documentos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(HistorialDocumento.class)
public class HistorialDocumento_ { 

    public static volatile SingularAttribute<HistorialDocumento, String> descripcion;
    public static volatile SingularAttribute<HistorialDocumento, Date> fecha;
    public static volatile SingularAttribute<HistorialDocumento, Documentos> idDocumento;
    public static volatile SingularAttribute<HistorialDocumento, String> nombreDocumento;
    public static volatile SingularAttribute<HistorialDocumento, String> proceso;
    public static volatile SingularAttribute<HistorialDocumento, Integer> idHistorialDocumento;
    public static volatile SingularAttribute<HistorialDocumento, Date> fechaHistorial;
    public static volatile SingularAttribute<HistorialDocumento, byte[]> documento;
    public static volatile SingularAttribute<HistorialDocumento, Short> version;
    public static volatile SingularAttribute<HistorialDocumento, String> subproceso;

}