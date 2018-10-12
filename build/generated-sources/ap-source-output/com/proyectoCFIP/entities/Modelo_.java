package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.MaquinaConfecciones;
import com.proyectoCFIP.entities.Marca;
import com.proyectoCFIP.entities.OtroDispositivo;
import com.proyectoCFIP.entities.Tipo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(Modelo.class)
public class Modelo_ { 

    public static volatile ListAttribute<Modelo, MaquinaConfecciones> maquinaConfeccionesList;
    public static volatile ListAttribute<Modelo, OtroDispositivo> otroDispositivoList;
    public static volatile SingularAttribute<Modelo, byte[]> imagenModelo;
    public static volatile SingularAttribute<Modelo, Tipo> idTipo;
    public static volatile SingularAttribute<Modelo, Integer> idModelo;
    public static volatile SingularAttribute<Modelo, Marca> idMarca;
    public static volatile ListAttribute<Modelo, Computador> computadorList;
    public static volatile SingularAttribute<Modelo, String> nombreModelo;

}