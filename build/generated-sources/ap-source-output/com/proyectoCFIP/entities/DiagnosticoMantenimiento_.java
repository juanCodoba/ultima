package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CronogramaMantenimientos;
import com.proyectoCFIP.entities.TipoDiagnostico;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(DiagnosticoMantenimiento.class)
public class DiagnosticoMantenimiento_ { 

    public static volatile SingularAttribute<DiagnosticoMantenimiento, Usuario> tecnicoResponsable;
    public static volatile SingularAttribute<DiagnosticoMantenimiento, TipoDiagnostico> idTipoDiagnostico;
    public static volatile SingularAttribute<DiagnosticoMantenimiento, String> diagnostico;
    public static volatile SingularAttribute<DiagnosticoMantenimiento, Integer> idMantenimiento;
    public static volatile SingularAttribute<DiagnosticoMantenimiento, Date> fechaRevision;
    public static volatile SingularAttribute<DiagnosticoMantenimiento, CronogramaMantenimientos> idCronogramaMantenimientos;
    public static volatile SingularAttribute<DiagnosticoMantenimiento, Date> fechaEntrega;
    public static volatile SingularAttribute<DiagnosticoMantenimiento, Boolean> mantenimientoCorrectivo;
    public static volatile SingularAttribute<DiagnosticoMantenimiento, String> revision;

}