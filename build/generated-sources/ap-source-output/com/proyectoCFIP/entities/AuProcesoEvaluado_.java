package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.AuAspectoEvaluar;
import com.proyectoCFIP.entities.AuPlanAuditoria;
import com.proyectoCFIP.entities.Subprocesos;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(AuProcesoEvaluado.class)
public class AuProcesoEvaluado_ { 

    public static volatile SingularAttribute<AuProcesoEvaluado, Boolean> estado;
    public static volatile ListAttribute<AuProcesoEvaluado, Usuario> usuarioList;
    public static volatile SingularAttribute<AuProcesoEvaluado, AuPlanAuditoria> idPlanAuditoria;
    public static volatile SingularAttribute<AuProcesoEvaluado, String> oportunidadMejora;
    public static volatile SingularAttribute<AuProcesoEvaluado, Date> hora;
    public static volatile SingularAttribute<AuProcesoEvaluado, Integer> idAuProcesoEvaluado;
    public static volatile SingularAttribute<AuProcesoEvaluado, Date> fechaFin;
    public static volatile SingularAttribute<AuProcesoEvaluado, Usuario> idAuditado;
    public static volatile ListAttribute<AuProcesoEvaluado, AuAspectoEvaluar> auAspectoEvaluarList;
    public static volatile SingularAttribute<AuProcesoEvaluado, Subprocesos> idSubproceso;
    public static volatile SingularAttribute<AuProcesoEvaluado, Date> horaFin;
    public static volatile SingularAttribute<AuProcesoEvaluado, String> noConformidades;
    public static volatile SingularAttribute<AuProcesoEvaluado, Date> fecha;
    public static volatile SingularAttribute<AuProcesoEvaluado, String> fortalezas;

}