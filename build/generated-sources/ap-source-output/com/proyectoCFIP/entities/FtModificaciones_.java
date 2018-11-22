package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.FtFichaTecnica;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(FtModificaciones.class)
public class FtModificaciones_ { 

    public static volatile SingularAttribute<FtModificaciones, String> descripcion;
    public static volatile SingularAttribute<FtModificaciones, Date> fecha;
    public static volatile SingularAttribute<FtModificaciones, String> estado;
    public static volatile SingularAttribute<FtModificaciones, String> pqs;
    public static volatile SingularAttribute<FtModificaciones, Integer> idModificaciones;
    public static volatile SingularAttribute<FtModificaciones, String> usuario;
    public static volatile SingularAttribute<FtModificaciones, FtFichaTecnica> ftFichaTecnica;

}