package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.DiagnosticoTicket;
import com.proyectoCFIP.entities.Formatos;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(CambioRegistro.class)
public class CambioRegistro_ { 

    public static volatile SingularAttribute<CambioRegistro, Boolean> estadoCambio;
    public static volatile ListAttribute<CambioRegistro, DiagnosticoTicket> diagnosticoTicketList;
    public static volatile SingularAttribute<CambioRegistro, String> descripcionCambio;
    public static volatile SingularAttribute<CambioRegistro, Date> fechaCambio;
    public static volatile SingularAttribute<CambioRegistro, Formatos> idFormato;
    public static volatile SingularAttribute<CambioRegistro, Usuario> idUsuario;
    public static volatile SingularAttribute<CambioRegistro, byte[]> documentoDemo;
    public static volatile SingularAttribute<CambioRegistro, Integer> idCambioRegistro;

}