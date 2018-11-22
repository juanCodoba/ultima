package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.Modelo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(Tipo.class)
public class Tipo_ { 

    public static volatile SingularAttribute<Tipo, String> nombreTipo;
    public static volatile ListAttribute<Tipo, Modelo> modeloList;
    public static volatile SingularAttribute<Tipo, Integer> idTipo;
    public static volatile ListAttribute<Tipo, Computador> computadorList;

}