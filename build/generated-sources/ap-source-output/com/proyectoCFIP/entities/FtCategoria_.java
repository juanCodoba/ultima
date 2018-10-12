package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.FtFichaTecnica;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(FtCategoria.class)
public class FtCategoria_ { 

    public static volatile SingularAttribute<FtCategoria, String> descripcion;
    public static volatile ListAttribute<FtCategoria, FtFichaTecnica> ftFichaTecnicaList;
    public static volatile SingularAttribute<FtCategoria, Integer> idFtCategoria;

}