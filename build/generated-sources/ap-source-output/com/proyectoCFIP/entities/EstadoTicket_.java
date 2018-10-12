package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.ReporteSiesa;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(EstadoTicket.class)
public class EstadoTicket_ { 

    public static volatile SingularAttribute<EstadoTicket, Integer> idEstadoTicket;
    public static volatile ListAttribute<EstadoTicket, ReporteSiesa> reporteSiesaList;
    public static volatile SingularAttribute<EstadoTicket, String> nombre;

}