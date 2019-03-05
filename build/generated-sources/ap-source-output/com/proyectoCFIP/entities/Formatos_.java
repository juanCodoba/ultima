package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CambioRegistro;
import com.proyectoCFIP.entities.Documentos;
import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.entities.Subprocesos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(Formatos.class)
public class Formatos_ { 

    public static volatile ListAttribute<Formatos, CambioRegistro> cambioRegistroList;
    public static volatile SingularAttribute<Formatos, Integer> idFormato;
    public static volatile SingularAttribute<Formatos, String> almacenamiento;
    public static volatile SingularAttribute<Formatos, String> recuperacion;
    public static volatile SingularAttribute<Formatos, String> linkFormato;
    public static volatile SingularAttribute<Formatos, Documentos> idDocumentoInstructivo;
    public static volatile SingularAttribute<Formatos, Double> version;
    public static volatile SingularAttribute<Formatos, Subprocesos> idSubproceso;
    public static volatile SingularAttribute<Formatos, String> tipoDocumento;
    public static volatile SingularAttribute<Formatos, String> codigoFormato;
    public static volatile SingularAttribute<Formatos, String> nombreFormato;
    public static volatile SingularAttribute<Formatos, String> tiempoRetencion;
    public static volatile SingularAttribute<Formatos, Date> fechaActualizacion;
    public static volatile SingularAttribute<Formatos, String> metodo;
    public static volatile SingularAttribute<Formatos, Procesos> idProceso;
    public static volatile SingularAttribute<Formatos, String> diligenciado;

}