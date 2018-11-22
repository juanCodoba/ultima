package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Documentos;
import com.proyectoCFIP.entities.Formatos;
import com.proyectoCFIP.entities.Subprocesos;
import com.proyectoCFIP.entities.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(Procesos.class)
public class Procesos_ { 

    public static volatile ListAttribute<Procesos, Usuario> usuarioList;
    public static volatile SingularAttribute<Procesos, String> nombreProceso;
    public static volatile ListAttribute<Procesos, Formatos> formatosList;
    public static volatile ListAttribute<Procesos, Documentos> documentosList;
    public static volatile SingularAttribute<Procesos, Integer> idProceso;
    public static volatile ListAttribute<Procesos, Subprocesos> subprocesosList;

}