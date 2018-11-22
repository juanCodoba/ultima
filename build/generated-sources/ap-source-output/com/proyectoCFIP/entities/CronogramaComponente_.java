package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.ItemComponente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(CronogramaComponente.class)
public class CronogramaComponente_ { 

    public static volatile SingularAttribute<CronogramaComponente, String> descripcion;
    public static volatile SingularAttribute<CronogramaComponente, Date> fechaCompra;
    public static volatile SingularAttribute<CronogramaComponente, Date> fechaCambio;
    public static volatile SingularAttribute<CronogramaComponente, Integer> costo;
    public static volatile SingularAttribute<CronogramaComponente, ItemComponente> idItemComponente;
    public static volatile SingularAttribute<CronogramaComponente, Computador> idComputador;
    public static volatile SingularAttribute<CronogramaComponente, Integer> idComponente;

}