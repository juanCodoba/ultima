package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.AuPlanAuditoria;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(TipoAuditoria.class)
public class TipoAuditoria_ { 

    public static volatile SingularAttribute<TipoAuditoria, Integer> idTipoAuditoria;
    public static volatile ListAttribute<TipoAuditoria, AuPlanAuditoria> AuPlanAuditoriaList;
    public static volatile SingularAttribute<TipoAuditoria, String> nombre;

}