package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.Licencia;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(SistemaOperativo.class)
public class SistemaOperativo_ { 

    public static volatile SingularAttribute<SistemaOperativo, String> bits;
    public static volatile SingularAttribute<SistemaOperativo, Integer> idSistemaOperativo;
    public static volatile ListAttribute<SistemaOperativo, Computador> computadorList;
    public static volatile ListAttribute<SistemaOperativo, Licencia> licenciaList;
    public static volatile SingularAttribute<SistemaOperativo, String> nombreSistemaOperativo;

}