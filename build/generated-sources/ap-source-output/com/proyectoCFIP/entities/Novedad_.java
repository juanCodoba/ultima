package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.FtCliente;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-26T10:01:24")
@StaticMetamodel(Novedad.class)
public class Novedad_ { 

    public static volatile SingularAttribute<Novedad, Date> fechaActual;
    public static volatile SingularAttribute<Novedad, Integer> op;
    public static volatile SingularAttribute<Novedad, FtCliente> idFtCliente;
    public static volatile SingularAttribute<Novedad, Integer> item;
    public static volatile SingularAttribute<Novedad, Usuario> idUsuarioCreacion;
    public static volatile SingularAttribute<Novedad, Integer> idNovedad;
    public static volatile SingularAttribute<Novedad, String> evidenciaFoto;
    public static volatile SingularAttribute<Novedad, Usuario> idUsuarioReporta;
    public static volatile SingularAttribute<Novedad, String> descripcionNovedad;

}