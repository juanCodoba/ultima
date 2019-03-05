package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(Valoracion.class)
public class Valoracion_ { 

    public static volatile SingularAttribute<Valoracion, Integer> idValoracion;
    public static volatile SingularAttribute<Valoracion, String> descripcion;
    public static volatile SingularAttribute<Valoracion, Usuario> idUsuario;
    public static volatile SingularAttribute<Valoracion, String> titulo;
    public static volatile SingularAttribute<Valoracion, Integer> valoracion;
    public static volatile SingularAttribute<Valoracion, Date> fechaValoracion;

}