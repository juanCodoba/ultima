package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.LicenciaPaqueteOffice;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(PaqueteOffice.class)
public class PaqueteOffice_ { 

    public static volatile SingularAttribute<PaqueteOffice, String> nombrePaquete;
    public static volatile SingularAttribute<PaqueteOffice, Integer> idPaqueteOffice;
    public static volatile SingularAttribute<PaqueteOffice, String> bits;
    public static volatile ListAttribute<PaqueteOffice, Computador> computadorList;
    public static volatile ListAttribute<PaqueteOffice, LicenciaPaqueteOffice> licenciaPaqueteOfficeList;

}