package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.ReservaLibrosBiblioteca;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(TipoEstudiante.class)
public class TipoEstudiante_ { 

    public static volatile SingularAttribute<TipoEstudiante, String> descripcion;
    public static volatile SingularAttribute<TipoEstudiante, Integer> idTipoEstudiante;
    public static volatile ListAttribute<TipoEstudiante, ReservaLibrosBiblioteca> ReservaLibrosBibliotecaList;

}