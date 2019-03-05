package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.AuProcesoEvaluado;
import com.proyectoCFIP.entities.CalidadGestionConocimiento;
import com.proyectoCFIP.entities.CalidadPlanAccion;
import com.proyectoCFIP.entities.Documentos;
import com.proyectoCFIP.entities.Formatos;
import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.entities.SistemasEquiposUsuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(Subprocesos.class)
public class Subprocesos_ { 

    public static volatile SingularAttribute<Subprocesos, String> nombreSubproceso;
    public static volatile ListAttribute<Subprocesos, CalidadPlanAccion> calidadPlanAccionList;
    public static volatile ListAttribute<Subprocesos, AuProcesoEvaluado> auProcesoEvaluadoList;
    public static volatile ListAttribute<Subprocesos, Formatos> formatosList;
    public static volatile ListAttribute<Subprocesos, Documentos> documentosList;
    public static volatile ListAttribute<Subprocesos, CalidadGestionConocimiento> calidadGestionConocimientoList;
    public static volatile SingularAttribute<Subprocesos, Procesos> idProceso;
    public static volatile ListAttribute<Subprocesos, SistemasEquiposUsuarios> sistemasEquiposUsuariosList;
    public static volatile SingularAttribute<Subprocesos, Integer> idSubproceso;

}