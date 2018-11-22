package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.AuPeriodoPlanAuditoria;
import com.proyectoCFIP.entities.AuProcesoEvaluado;
import com.proyectoCFIP.entities.CalidadPlanAccion;
import com.proyectoCFIP.entities.TipoAuditoria;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(AuPlanAuditoria.class)
public class AuPlanAuditoria_ { 

    public static volatile SingularAttribute<AuPlanAuditoria, String> estado;
    public static volatile SingularAttribute<AuPlanAuditoria, String> criterios;
    public static volatile SingularAttribute<AuPlanAuditoria, Usuario> idUsuario;
    public static volatile SingularAttribute<AuPlanAuditoria, String> titulo;
    public static volatile SingularAttribute<AuPlanAuditoria, AuPeriodoPlanAuditoria> idAuPeriodoPlanAuditoria;
    public static volatile SingularAttribute<AuPlanAuditoria, String> alcance;
    public static volatile SingularAttribute<AuPlanAuditoria, String> objetivo;
    public static volatile SingularAttribute<AuPlanAuditoria, String> documento1;
    public static volatile SingularAttribute<AuPlanAuditoria, Boolean> abril;
    public static volatile SingularAttribute<AuPlanAuditoria, String> documento3;
    public static volatile SingularAttribute<AuPlanAuditoria, String> documento2;
    public static volatile SingularAttribute<AuPlanAuditoria, Date> fechaIAuditoria;
    public static volatile ListAttribute<AuPlanAuditoria, AuProcesoEvaluado> auProcesoEvaluadoList;
    public static volatile SingularAttribute<AuPlanAuditoria, Boolean> mayo;
    public static volatile SingularAttribute<AuPlanAuditoria, Boolean> enero;
    public static volatile SingularAttribute<AuPlanAuditoria, Boolean> febrero;
    public static volatile SingularAttribute<AuPlanAuditoria, String> tipoAuditoria;
    public static volatile SingularAttribute<AuPlanAuditoria, Boolean> agosto;
    public static volatile SingularAttribute<AuPlanAuditoria, Boolean> noviembre;
    public static volatile SingularAttribute<AuPlanAuditoria, String> actaSocializacion;
    public static volatile SingularAttribute<AuPlanAuditoria, Boolean> junio;
    public static volatile SingularAttribute<AuPlanAuditoria, Integer> idPlanAuditoria;
    public static volatile SingularAttribute<AuPlanAuditoria, Date> fechafAuditoria;
    public static volatile SingularAttribute<AuPlanAuditoria, Boolean> julio;
    public static volatile SingularAttribute<AuPlanAuditoria, Boolean> diciembre;
    public static volatile SingularAttribute<AuPlanAuditoria, String> documento4;
    public static volatile ListAttribute<AuPlanAuditoria, CalidadPlanAccion> calidadPlanAccionList;
    public static volatile SingularAttribute<AuPlanAuditoria, Date> fecha;
    public static volatile SingularAttribute<AuPlanAuditoria, TipoAuditoria> idTipoAuditoria;
    public static volatile SingularAttribute<AuPlanAuditoria, Boolean> septiembre;
    public static volatile SingularAttribute<AuPlanAuditoria, String> informeFinal;
    public static volatile SingularAttribute<AuPlanAuditoria, Boolean> octubre;
    public static volatile SingularAttribute<AuPlanAuditoria, Boolean> marzo;

}