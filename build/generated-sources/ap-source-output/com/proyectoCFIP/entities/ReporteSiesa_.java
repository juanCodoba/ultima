package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.DiagnosticoReporteSiesa;
import com.proyectoCFIP.entities.EstadoTicket;
import com.proyectoCFIP.entities.TipoError;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(ReporteSiesa.class)
public class ReporteSiesa_ { 

    public static volatile SingularAttribute<ReporteSiesa, String> descripcionValoracion;
    public static volatile SingularAttribute<ReporteSiesa, EstadoTicket> idEstadoTicket;
    public static volatile SingularAttribute<ReporteSiesa, Boolean> estadoReporte;
    public static volatile SingularAttribute<ReporteSiesa, String> descripcionReporte;
    public static volatile ListAttribute<ReporteSiesa, DiagnosticoReporteSiesa> diagnosticoReporteSiesaList;
    public static volatile SingularAttribute<ReporteSiesa, Usuario> idUsuario;
    public static volatile SingularAttribute<ReporteSiesa, byte[]> imagen;
    public static volatile SingularAttribute<ReporteSiesa, Integer> valoracion;
    public static volatile SingularAttribute<ReporteSiesa, TipoError> idTipoError;
    public static volatile SingularAttribute<ReporteSiesa, Date> fechaValoracion;
    public static volatile SingularAttribute<ReporteSiesa, Date> fechaReporte;
    public static volatile SingularAttribute<ReporteSiesa, Integer> idReporteSiesa;

}