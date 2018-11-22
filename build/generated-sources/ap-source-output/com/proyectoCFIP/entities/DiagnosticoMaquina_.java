package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CronogramaMantenimientoMaquina;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(DiagnosticoMaquina.class)
public class DiagnosticoMaquina_ { 

    public static volatile SingularAttribute<DiagnosticoMaquina, String> diagnostico;
    public static volatile SingularAttribute<DiagnosticoMaquina, Integer> idDiagnosticoMaquina;
    public static volatile SingularAttribute<DiagnosticoMaquina, Date> fechaRevision;
    public static volatile SingularAttribute<DiagnosticoMaquina, Date> fechaEntrega;
    public static volatile SingularAttribute<DiagnosticoMaquina, CronogramaMantenimientoMaquina> idCronogramaMantenimientoMaquina;
    public static volatile SingularAttribute<DiagnosticoMaquina, Boolean> mantenimientoCp;
    public static volatile SingularAttribute<DiagnosticoMaquina, String> revision;

}