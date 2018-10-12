package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Marca;
import com.proyectoCFIP.entities.Modelo;
import com.proyectoCFIP.entities.Modulo;
import com.proyectoCFIP.entities.TipoMaquinaConfecciones;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(MaquinaConfecciones.class)
public class MaquinaConfecciones_ { 

    public static volatile SingularAttribute<MaquinaConfecciones, String> codigo;
    public static volatile SingularAttribute<MaquinaConfecciones, Boolean> estado;
    public static volatile SingularAttribute<MaquinaConfecciones, String> serial;
    public static volatile SingularAttribute<MaquinaConfecciones, Integer> idMaquinaConfecciones;
    public static volatile SingularAttribute<MaquinaConfecciones, Modulo> idModulo;
    public static volatile SingularAttribute<MaquinaConfecciones, Boolean> especial;
    public static volatile SingularAttribute<MaquinaConfecciones, Modelo> idModelo;
    public static volatile SingularAttribute<MaquinaConfecciones, Marca> idMarca;
    public static volatile SingularAttribute<MaquinaConfecciones, TipoMaquinaConfecciones> idTipoMaquinaConfecciones;

}