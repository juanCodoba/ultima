package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CronogramaActividadesEdificios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(DiagnosticoActividadEdificios.class)
public class DiagnosticoActividadEdificios_ { 

    public static volatile SingularAttribute<DiagnosticoActividadEdificios, String> descripcionDiagnostico;
    public static volatile SingularAttribute<DiagnosticoActividadEdificios, Date> fechaRevision;
    public static volatile SingularAttribute<DiagnosticoActividadEdificios, String> descripcionRevision;
    public static volatile SingularAttribute<DiagnosticoActividadEdificios, Integer> idDiagnosticoActividadEdificios;
    public static volatile SingularAttribute<DiagnosticoActividadEdificios, CronogramaActividadesEdificios> idCronogramaActividadesEdificios;
    public static volatile SingularAttribute<DiagnosticoActividadEdificios, Date> fechaDiagnostico;

}