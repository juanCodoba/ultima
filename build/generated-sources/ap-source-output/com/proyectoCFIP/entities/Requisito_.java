package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Cargos;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-16T16:18:46")
@StaticMetamodel(Requisito.class)
public class Requisito_ { 

    public static volatile SingularAttribute<Requisito, String> descripcion;
    public static volatile SingularAttribute<Requisito, String> formacion;
    public static volatile SingularAttribute<Requisito, Cargos> idCargo;
    public static volatile SingularAttribute<Requisito, Boolean> estado;
    public static volatile SingularAttribute<Requisito, String> otrosConocimientos;
    public static volatile SingularAttribute<Requisito, String> experiencia;
    public static volatile SingularAttribute<Requisito, String> educacion;
    public static volatile SingularAttribute<Requisito, Integer> idRequisito;

}