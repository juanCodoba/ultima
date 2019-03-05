package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(Cargos.class)
public class Cargos_ { 

    public static volatile SingularAttribute<Cargos, Integer> idCargo;
    public static volatile SingularAttribute<Cargos, String> nombreCargo;
    public static volatile ListAttribute<Cargos, Usuario> usuarioList;

}