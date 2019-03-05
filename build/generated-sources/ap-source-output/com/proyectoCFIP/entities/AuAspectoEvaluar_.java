package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.AuProcesoEvaluado;
import com.proyectoCFIP.entities.CalidadPlanAccion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(AuAspectoEvaluar.class)
public class AuAspectoEvaluar_ { 

    public static volatile SingularAttribute<AuAspectoEvaluar, String> recomendacion;
    public static volatile ListAttribute<AuAspectoEvaluar, CalidadPlanAccion> calidadPlanAccionList;
    public static volatile SingularAttribute<AuAspectoEvaluar, String> cumplimiento;
    public static volatile SingularAttribute<AuAspectoEvaluar, String> estado;
    public static volatile SingularAttribute<AuAspectoEvaluar, String> aspecto;
    public static volatile ListAttribute<AuAspectoEvaluar, AuProcesoEvaluado> auProcesoEvaluadoList;
    public static volatile SingularAttribute<AuAspectoEvaluar, String> evidencia;
    public static volatile SingularAttribute<AuAspectoEvaluar, String> requisito;
    public static volatile SingularAttribute<AuAspectoEvaluar, AuProcesoEvaluado> idAuProcesoEvaluado;
    public static volatile SingularAttribute<AuAspectoEvaluar, String> comentario;
    public static volatile SingularAttribute<AuAspectoEvaluar, Integer> idAuAspectoEvaluar;

}