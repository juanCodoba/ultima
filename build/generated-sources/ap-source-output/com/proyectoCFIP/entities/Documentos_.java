package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Formatos;
import com.proyectoCFIP.entities.HistorialDocumento;
import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.entities.Subprocesos;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(Documentos.class)
public class Documentos_ { 

    public static volatile SingularAttribute<Documentos, String> codigoDocumento;
    public static volatile SingularAttribute<Documentos, String> linkDocumento;
    public static volatile SingularAttribute<Documentos, Date> fecha;
    public static volatile ListAttribute<Documentos, Usuario> usuarioList;
    public static volatile ListAttribute<Documentos, HistorialDocumento> historialDocumentoList;
    public static volatile SingularAttribute<Documentos, Integer> idDocumento;
    public static volatile SingularAttribute<Documentos, String> nombreDocumento;
    public static volatile SingularAttribute<Documentos, Integer> idTipoCalidad;
    public static volatile ListAttribute<Documentos, Formatos> formatosList;
    public static volatile SingularAttribute<Documentos, Procesos> idProceso;
    public static volatile SingularAttribute<Documentos, Short> version;
    public static volatile SingularAttribute<Documentos, Subprocesos> idSubproceso;

}