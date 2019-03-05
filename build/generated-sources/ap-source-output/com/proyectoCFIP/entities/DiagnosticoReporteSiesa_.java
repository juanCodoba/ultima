package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.ReporteSiesa;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(DiagnosticoReporteSiesa.class)
public class DiagnosticoReporteSiesa_ { 

    public static volatile SingularAttribute<DiagnosticoReporteSiesa, Integer> idDiagnosticoReporteSiesa;
    public static volatile SingularAttribute<DiagnosticoReporteSiesa, String> descripcionTicketSiesa;
    public static volatile SingularAttribute<DiagnosticoReporteSiesa, String> diagnostico;
    public static volatile SingularAttribute<DiagnosticoReporteSiesa, Date> fechaRevision;
    public static volatile SingularAttribute<DiagnosticoReporteSiesa, Usuario> idUsuario;
    public static volatile SingularAttribute<DiagnosticoReporteSiesa, String> codigoTicketSiesa;
    public static volatile SingularAttribute<DiagnosticoReporteSiesa, Date> fechaDiagnostico;
    public static volatile SingularAttribute<DiagnosticoReporteSiesa, ReporteSiesa> idReporteSiesa;
    public static volatile SingularAttribute<DiagnosticoReporteSiesa, String> revision;

}