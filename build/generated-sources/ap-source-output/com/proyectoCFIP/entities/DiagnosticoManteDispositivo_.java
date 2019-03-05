package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CronogramaManteDispositivo;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(DiagnosticoManteDispositivo.class)
public class DiagnosticoManteDispositivo_ { 

    public static volatile SingularAttribute<DiagnosticoManteDispositivo, Integer> idDiagnosticoManteDispositivo;
    public static volatile SingularAttribute<DiagnosticoManteDispositivo, Boolean> cambioRepuesto;
    public static volatile SingularAttribute<DiagnosticoManteDispositivo, String> revisionDis;
    public static volatile SingularAttribute<DiagnosticoManteDispositivo, CronogramaManteDispositivo> idCronogramaManteDispositivo;
    public static volatile SingularAttribute<DiagnosticoManteDispositivo, Usuario> idUsuario;
    public static volatile SingularAttribute<DiagnosticoManteDispositivo, String> diagnosticoDis;
    public static volatile SingularAttribute<DiagnosticoManteDispositivo, Date> fechaEntregaDis;
    public static volatile SingularAttribute<DiagnosticoManteDispositivo, Date> fechaRevisionDis;
    public static volatile SingularAttribute<DiagnosticoManteDispositivo, Boolean> mantenimientoCorrectivo;

}