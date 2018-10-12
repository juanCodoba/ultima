package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Computador;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(Monitor.class)
public class Monitor_ { 

    public static volatile SingularAttribute<Monitor, Integer> pulgadasMonitor;
    public static volatile ListAttribute<Monitor, Computador> computadorList;
    public static volatile SingularAttribute<Monitor, Integer> idMonitor;
    public static volatile SingularAttribute<Monitor, String> marcaMonitor;

}