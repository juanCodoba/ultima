package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Marca;
import com.proyectoCFIP.entities.Subprocesos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(SistemasEquiposUsuarios.class)
public class SistemasEquiposUsuarios_ { 

    public static volatile SingularAttribute<SistemasEquiposUsuarios, Integer> idsistemasequiposUsuarios;
    public static volatile SingularAttribute<SistemasEquiposUsuarios, Boolean> estado;
    public static volatile SingularAttribute<SistemasEquiposUsuarios, String> cedula;
    public static volatile SingularAttribute<SistemasEquiposUsuarios, Date> fechaVencimiento;
    public static volatile SingularAttribute<SistemasEquiposUsuarios, String> apelldios;
    public static volatile SingularAttribute<SistemasEquiposUsuarios, String> modelo;
    public static volatile SingularAttribute<SistemasEquiposUsuarios, String> mac;
    public static volatile SingularAttribute<SistemasEquiposUsuarios, String> nombres;
    public static volatile SingularAttribute<SistemasEquiposUsuarios, Subprocesos> idSubproceso;
    public static volatile SingularAttribute<SistemasEquiposUsuarios, Date> fechaPermiso;
    public static volatile SingularAttribute<SistemasEquiposUsuarios, String> ciudad;
    public static volatile SingularAttribute<SistemasEquiposUsuarios, String> serie;
    public static volatile SingularAttribute<SistemasEquiposUsuarios, Marca> idMarca;
    public static volatile SingularAttribute<SistemasEquiposUsuarios, String> escaneo;

}