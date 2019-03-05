package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CambioRegistro;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(DiagnosticoTicket.class)
public class DiagnosticoTicket_ { 

    public static volatile SingularAttribute<DiagnosticoTicket, String> descripcionDiagnostico;
    public static volatile SingularAttribute<DiagnosticoTicket, Integer> idDiagnosticoTicket;
    public static volatile SingularAttribute<DiagnosticoTicket, Usuario> idUsuario;
    public static volatile SingularAttribute<DiagnosticoTicket, CambioRegistro> idCambioRegistro;
    public static volatile SingularAttribute<DiagnosticoTicket, Date> fechaDiagnostico;

}