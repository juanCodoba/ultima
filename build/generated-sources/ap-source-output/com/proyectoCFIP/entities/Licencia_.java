package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Aplicacion;
import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.FabricanteLicencia;
import com.proyectoCFIP.entities.ModalidadesLicencia;
import com.proyectoCFIP.entities.SistemaOperativo;
import com.proyectoCFIP.entities.TipoLicencia;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(Licencia.class)
public class Licencia_ { 

    public static volatile ListAttribute<Licencia, Aplicacion> aplicacionList;
    public static volatile SingularAttribute<Licencia, Date> fechaInicioLicencia;
    public static volatile SingularAttribute<Licencia, String> tipoActivacion;
    public static volatile SingularAttribute<Licencia, ModalidadesLicencia> idModalidadesLicencia;
    public static volatile SingularAttribute<Licencia, Boolean> licenciaSinFin;
    public static volatile SingularAttribute<Licencia, SistemaOperativo> idSistemaOperativo;
    public static volatile SingularAttribute<Licencia, Date> fechaTerminoLicencia;
    public static volatile SingularAttribute<Licencia, String> nombreLicencia;
    public static volatile SingularAttribute<Licencia, String> claveActivacion;
    public static volatile SingularAttribute<Licencia, Integer> idLicencia;
    public static volatile SingularAttribute<Licencia, String> codigoLicencia;
    public static volatile ListAttribute<Licencia, Computador> computadorList;
    public static volatile SingularAttribute<Licencia, TipoLicencia> idTipoLicencia;
    public static volatile SingularAttribute<Licencia, FabricanteLicencia> idFabricanteLicencia;

}