package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Subprocesos;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(CalidadGestionConocimiento.class)
public class CalidadGestionConocimiento_ { 

    public static volatile SingularAttribute<CalidadGestionConocimiento, Date> fecha;
    public static volatile SingularAttribute<CalidadGestionConocimiento, String> situacionPresentada;
    public static volatile SingularAttribute<CalidadGestionConocimiento, Usuario> idUsuario;
    public static volatile SingularAttribute<CalidadGestionConocimiento, String> titulo;
    public static volatile SingularAttribute<CalidadGestionConocimiento, Integer> idGestionConocimiento;
    public static volatile SingularAttribute<CalidadGestionConocimiento, String> leccionAprendida;
    public static volatile SingularAttribute<CalidadGestionConocimiento, Subprocesos> idSubproceso;

}