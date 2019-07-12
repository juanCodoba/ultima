package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.AccionCorrectiva;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-12T07:30:05")
@StaticMetamodel(CalidadSeguimientoCorrectiva.class)
public class CalidadSeguimientoCorrectiva_ { 

    public static volatile SingularAttribute<CalidadSeguimientoCorrectiva, Date> fecha;
    public static volatile SingularAttribute<CalidadSeguimientoCorrectiva, Boolean> estado;
    public static volatile SingularAttribute<CalidadSeguimientoCorrectiva, Integer> idCalidadSeguimientoCorrectiva;
    public static volatile SingularAttribute<CalidadSeguimientoCorrectiva, String> responsable;
    public static volatile SingularAttribute<CalidadSeguimientoCorrectiva, String> resultado;
    public static volatile SingularAttribute<CalidadSeguimientoCorrectiva, AccionCorrectiva> IdaccionCorrectiva;

}