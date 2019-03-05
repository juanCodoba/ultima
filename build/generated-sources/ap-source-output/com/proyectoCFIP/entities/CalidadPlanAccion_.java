package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.AuAspectoEvaluar;
import com.proyectoCFIP.entities.AuPlanAuditoria;
import com.proyectoCFIP.entities.CalidadAccionImplementar;
import com.proyectoCFIP.entities.CalidadCausa;
import com.proyectoCFIP.entities.Subprocesos;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(CalidadPlanAccion.class)
public class CalidadPlanAccion_ { 

    public static volatile SingularAttribute<CalidadPlanAccion, String> revisor;
    public static volatile SingularAttribute<CalidadPlanAccion, String> descripcion;
    public static volatile SingularAttribute<CalidadPlanAccion, Usuario> responsable;
    public static volatile SingularAttribute<CalidadPlanAccion, AuPlanAuditoria> idPlanAuditoria;
    public static volatile SingularAttribute<CalidadPlanAccion, String> tituloPlan;
    public static volatile SingularAttribute<CalidadPlanAccion, String> origenAccion;
    public static volatile SingularAttribute<CalidadPlanAccion, String> criterio1;
    public static volatile SingularAttribute<CalidadPlanAccion, String> criterio2;
    public static volatile SingularAttribute<CalidadPlanAccion, Date> fechaAnalisis;
    public static volatile SingularAttribute<CalidadPlanAccion, String> tipoPlan;
    public static volatile SingularAttribute<CalidadPlanAccion, String> observacionGeneral;
    public static volatile SingularAttribute<CalidadPlanAccion, String> estadoPlan;
    public static volatile ListAttribute<CalidadPlanAccion, CalidadAccionImplementar> calidadAccionImplementarList;
    public static volatile SingularAttribute<CalidadPlanAccion, String> accionEficaz;
    public static volatile SingularAttribute<CalidadPlanAccion, Subprocesos> idSubproceso;
    public static volatile SingularAttribute<CalidadPlanAccion, Date> fechaCierre;
    public static volatile ListAttribute<CalidadPlanAccion, CalidadCausa> calidadCausaList;
    public static volatile SingularAttribute<CalidadPlanAccion, Integer> idPlanAccion;
    public static volatile SingularAttribute<CalidadPlanAccion, AuAspectoEvaluar> idAuAspectoEvaluar;
    public static volatile SingularAttribute<CalidadPlanAccion, String> comentarios;

}