package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Libro;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(EstadoLibro.class)
public class EstadoLibro_ { 

    public static volatile SingularAttribute<EstadoLibro, Integer> idEstadoLibro;
    public static volatile ListAttribute<EstadoLibro, Libro> LibrosList;
    public static volatile SingularAttribute<EstadoLibro, String> nombreEstado;

}