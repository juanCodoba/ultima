package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(Roles.class)
public class Roles_ { 

    public static volatile SingularAttribute<Roles, String> idRol;
    public static volatile ListAttribute<Roles, Usuario> usuarioList;
    public static volatile SingularAttribute<Roles, String> nomRol;

}