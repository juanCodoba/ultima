package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.PaqueteOffice;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(LicenciaPaqueteOffice.class)
public class LicenciaPaqueteOffice_ { 

    public static volatile SingularAttribute<LicenciaPaqueteOffice, PaqueteOffice> idPaqueteOffice;
    public static volatile SingularAttribute<LicenciaPaqueteOffice, Date> fechaTerminoLic;
    public static volatile SingularAttribute<LicenciaPaqueteOffice, String> tipoActivacion;
    public static volatile SingularAttribute<LicenciaPaqueteOffice, Date> fechaInicioLic;
    public static volatile SingularAttribute<LicenciaPaqueteOffice, Boolean> licSinFin;
    public static volatile SingularAttribute<LicenciaPaqueteOffice, String> codigoLic;
    public static volatile SingularAttribute<LicenciaPaqueteOffice, Integer> idLicenciaPaqueteOffice;
    public static volatile SingularAttribute<LicenciaPaqueteOffice, String> nombreLic;
    public static volatile ListAttribute<LicenciaPaqueteOffice, Computador> computadorList;
    public static volatile SingularAttribute<LicenciaPaqueteOffice, String> claveActivacion;

}