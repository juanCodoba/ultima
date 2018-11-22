package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.MaquinaConfecciones;
import com.proyectoCFIP.entities.Modelo;
import com.proyectoCFIP.entities.SistemasEquiposUsuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(Marca.class)
public class Marca_ { 

    public static volatile ListAttribute<Marca, MaquinaConfecciones> maquinaConfeccionesList;
    public static volatile ListAttribute<Marca, Modelo> modeloList;
    public static volatile SingularAttribute<Marca, Integer> idMarca;
    public static volatile SingularAttribute<Marca, String> nombreMarca;
    public static volatile ListAttribute<Marca, SistemasEquiposUsuarios> sistemasEquiposUsuariosList;

}