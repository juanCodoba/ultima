package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.EstadoLibro;
import com.proyectoCFIP.entities.Genero;
import com.proyectoCFIP.entities.ReservaLibrosBiblioteca;
import com.proyectoCFIP.entities.Rotulo;
import com.proyectoCFIP.entities.TipoMantenimiento;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(Libro.class)
public class Libro_ { 

    public static volatile SingularAttribute<Libro, String> editorial;
    public static volatile SingularAttribute<Libro, String> descripcion;
    public static volatile SingularAttribute<Libro, String> descripcionNormal;
    public static volatile SingularAttribute<Libro, String> codigo;
    public static volatile SingularAttribute<Libro, Date> fechaReporte;
    public static volatile SingularAttribute<Libro, Date> fechaDiagnostico;
    public static volatile SingularAttribute<Libro, String> autor;
    public static volatile SingularAttribute<Libro, EstadoLibro> idEstadoLibro;
    public static volatile SingularAttribute<Libro, Integer> copiasDelLibro;
    public static volatile SingularAttribute<Libro, Integer> idLibro;
    public static volatile SingularAttribute<Libro, TipoMantenimiento> idTipoMantenimiento;
    public static volatile SingularAttribute<Libro, Usuario> idUsuaroLib;
    public static volatile SingularAttribute<Libro, Rotulo> idRotulo;
    public static volatile SingularAttribute<Libro, Integer> costoLibro;
    public static volatile SingularAttribute<Libro, Date> fechaModific;
    public static volatile SingularAttribute<Libro, Date> horaReporte;
    public static volatile SingularAttribute<Libro, Genero> idGenero;
    public static volatile ListAttribute<Libro, ReservaLibrosBiblioteca> reservaLibrosBibliotecas2;
    public static volatile SingularAttribute<Libro, String> tituloLibro;
    public static volatile SingularAttribute<Libro, Boolean> activo;
    public static volatile ListAttribute<Libro, ReservaLibrosBiblioteca> reservaLibrosBibliotecas1;

}