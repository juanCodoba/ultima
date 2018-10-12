package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.Licencia;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(Aplicacion.class)
public class Aplicacion_ { 

    public static volatile SingularAttribute<Aplicacion, String> descripcionAplicacion;
    public static volatile SingularAttribute<Aplicacion, Integer> idAplicacion;
    public static volatile SingularAttribute<Aplicacion, Date> fechaInicioUso;
    public static volatile SingularAttribute<Aplicacion, Licencia> idLicencia;
    public static volatile SingularAttribute<Aplicacion, String> descripcionFinUso;
    public static volatile SingularAttribute<Aplicacion, String> versionAplicacion;
    public static volatile SingularAttribute<Aplicacion, Date> fechaFinUso;
    public static volatile ListAttribute<Aplicacion, Computador> computadorList;
    public static volatile SingularAttribute<Aplicacion, String> nombreAplicacion;

}