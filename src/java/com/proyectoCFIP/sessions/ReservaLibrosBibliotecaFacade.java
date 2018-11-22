/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.ReservaLibrosBiblioteca;
import com.proyectoCFIP.entities.TipoEstudiante;
import com.proyectoCFIP.entities.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Luis Carlos Cabal
 */
@Stateless
public class ReservaLibrosBibliotecaFacade extends AbstractFacade<ReservaLibrosBiblioteca> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservaLibrosBibliotecaFacade() {
        super(ReservaLibrosBiblioteca.class);
    }

    public List<ReservaLibrosBiblioteca> consultaUsuario(Usuario usuario) {
        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.findByUsuario");
        q.setParameter("idUsuarioPrestamo", usuario);
        return q.getResultList();
    }

    public List<ReservaLibrosBiblioteca> consultaEstado() {
        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.estadoTrue");
        return q.getResultList();
    }

    public List<ReservaLibrosBiblioteca> consultaEstadoFalso() {
        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.estadoFalse");
        return q.getResultList();
    }

//    public List<ReservaLibrosBiblioteca> consultaCronogramaBibliotecario() {
//        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.findByBibliotecario");
//        return q.getResultList();
//    }
//    public List<ReservaLibrosBiblioteca> ejecutarUsuario(){
//        try{
//            String sql = "select nombre_usuario,apellido_usuario,id_usuario_prestamo, COUNT(id_usuario_prestamo) as total from reserva_libros_biblioteca \n" +
//                    "inner join usuario on reserva_libros_biblioteca.id_usuario_prestamo = usuario.id_usuario GROUP BY id_usuario_prestamo ;";
//            Query query = em.createQuery(sql);
//           
//            return  query.getResultList();
//
//            
//        }catch(Exception e ){
//            throw e;
//        }
//    }
    public List<ReservaLibrosBiblioteca> consultaTipoF(TipoEstudiante idtipoEstudiante) {
        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.findByIdTipoEstudiante");
        q.setParameter("idtipoEstudiante", idtipoEstudiante);
        return q.getResultList();
    }

    public List<ReservaLibrosBiblioteca> consultaTipoEgre(Date fecha1, Date fecha2) {
        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.findByIdTipoEgresdo");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }

    public List<ReservaLibrosBiblioteca> consultaUsuariosDelMes(Date fecha1, Date fecha2) {
        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.findByIdUsuarioPrestamo");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }

    public List<ReservaLibrosBiblioteca> consultaUsuariosDelMes1(Date fecha1, Date fecha2) {
        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.findByIdUsuarioPrestamo1");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }

    public List<ReservaLibrosBiblioteca> consultaTipoTrabajador(Date fecha1, Date fecha2) {
        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.findByIdTipoTrabajador");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }

    public List<ReservaLibrosBiblioteca> consultaTipoDocen(Date fecha1, Date fecha2) {
        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.findByIdTipoDocente");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }

    public List<ReservaLibrosBiblioteca> consultaTipoEstudiante1(Date fecha1, Date fecha2) {
        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.findByIdTipo1Es");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }

    public List<ReservaLibrosBiblioteca> consultaTiP(Date fecha1, Date fecha2) {
        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.findByIdLib1");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }

    public List<ReservaLibrosBiblioteca> consultaTiP2(Date fecha1, Date fecha2) {
        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.findByIdLib2");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }

//    public String consultaEstudiante() throws SQLException {
//        Query q = em.createNativeQuery("select nombre_usuario,apellido_usuario,id_usuario_prestamo, COUNT(id_usuario_prestamo) as total from reserva_libros_biblioteca \n"
//                + "inner join usuario on reserva_libros_biblioteca.id_usuario_prestamo = usuario.id_usuario GROUP BY id_usuario_prestamo ;");
//        
//       return q.getSingleResult().getClass().getCanonicalName();
//    }
    public List<ReservaLibrosBiblioteca> consultaReporteCorrectivoTiempo(Date fecha1, Date fecha2) {
        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.finbyIndicador");
        q.setParameter("fecha1", fecha1);
        q.setParameter("fecha2", fecha2);
        return q.getResultList();
    }

}
