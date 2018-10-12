package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(Idea.class)
public class Idea_ { 

    public static volatile SingularAttribute<Idea, String> descripcion;
    public static volatile SingularAttribute<Idea, Date> fecha;
    public static volatile SingularAttribute<Idea, Integer> idIdea;
    public static volatile SingularAttribute<Idea, String> estado;
    public static volatile SingularAttribute<Idea, Usuario> idUsuario;

}