package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Facturado;
import com.proyectoCFIP.entities.FtFichaTecnica;
import com.proyectoCFIP.entities.Novedad;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-26T10:01:24")
@StaticMetamodel(FtCliente.class)
public class FtCliente_ { 

    public static volatile ListAttribute<FtCliente, FtFichaTecnica> ftFichaTecnicaList;
    public static volatile SingularAttribute<FtCliente, Integer> idFtCliente;
    public static volatile ListAttribute<FtCliente, Facturado> facturadoList;
    public static volatile ListAttribute<FtCliente, Novedad> novedadList;
    public static volatile SingularAttribute<FtCliente, String> nombre;

}