package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CronogramaManteDisCon;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(DiagnosticoMantenimientoDisCon.class)
public class DiagnosticoMantenimientoDisCon_ { 

    public static volatile SingularAttribute<DiagnosticoMantenimientoDisCon, Integer> idDiagnosticoManteDisCon;
    public static volatile SingularAttribute<DiagnosticoMantenimientoDisCon, Date> fechaRevisionDisCon;
    public static volatile SingularAttribute<DiagnosticoMantenimientoDisCon, Date> fechaEntragaDisCon;
    public static volatile SingularAttribute<DiagnosticoMantenimientoDisCon, String> revisionDisCon;
    public static volatile SingularAttribute<DiagnosticoMantenimientoDisCon, Usuario> idUsuario;
    public static volatile SingularAttribute<DiagnosticoMantenimientoDisCon, String> diagnosticoDisCon;
    public static volatile SingularAttribute<DiagnosticoMantenimientoDisCon, CronogramaManteDisCon> idCronogramaManteDisCon;
    public static volatile SingularAttribute<DiagnosticoMantenimientoDisCon, Boolean> mantenimientoCorrectivo;

}