package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Libro;
import com.proyectoCFIP.entities.TipoEstudiante;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(ReservaLibrosBiblioteca.class)
public class ReservaLibrosBiblioteca_ { 

    public static volatile SingularAttribute<ReservaLibrosBiblioteca, String> area;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, Integer> idReservaLibros;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, Boolean> sinPreserva;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, Boolean> estadoUsuarioReservas;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, String> apellidoEgresado;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, Usuario> idUsuarioPrestamo;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, String> observacionesLib;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, String> gradoEstudiante;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, TipoEstudiante> idTipoEstudiante;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, Date> fechaInicioPrestamo;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, Libro> idLib1;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, Boolean> estadoUsuarioReservasCompletMe;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, String> nombreEgresado;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, Usuario> idBibliotecario;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, Date> fechaFinPrestamo;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, Libro> idLib2;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, String> añoEgresado;
    public static volatile SingularAttribute<ReservaLibrosBiblioteca, Boolean> activo;

}