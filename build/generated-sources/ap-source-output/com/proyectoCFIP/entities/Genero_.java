package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Libro;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(Genero.class)
public class Genero_ { 

    public static volatile SingularAttribute<Genero, String> descripcion;
    public static volatile ListAttribute<Genero, Libro> idLibroList;
    public static volatile SingularAttribute<Genero, Integer> idGenero;
    public static volatile SingularAttribute<Genero, String> nombre;

}