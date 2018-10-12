package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Seccion;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(SstReporteUsuario.class)
public class SstReporteUsuario_ { 

    public static volatile SingularAttribute<SstReporteUsuario, String> descripcion;
    public static volatile SingularAttribute<SstReporteUsuario, String> recomendacion;
    public static volatile SingularAttribute<SstReporteUsuario, Date> fecha;
    public static volatile SingularAttribute<SstReporteUsuario, String> estado;
    public static volatile SingularAttribute<SstReporteUsuario, String> tipo;
    public static volatile SingularAttribute<SstReporteUsuario, Integer> idSstIncidente;
    public static volatile SingularAttribute<SstReporteUsuario, Usuario> idUsuario;
    public static volatile SingularAttribute<SstReporteUsuario, String> observaciones;
    public static volatile SingularAttribute<SstReporteUsuario, String> sstReporteUsuariocol;
    public static volatile SingularAttribute<SstReporteUsuario, Seccion> idSeccion;

}