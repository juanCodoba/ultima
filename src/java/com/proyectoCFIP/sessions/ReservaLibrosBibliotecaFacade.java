/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.ReservaLibrosBiblioteca;
import com.proyectoCFIP.entities.TipoEstudiante;
import com.proyectoCFIP.entities.Usuario;
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
    public List<ReservaLibrosBiblioteca> consultaTipoF(TipoEstudiante idtipoEstudiante) {
        Query q = getEntityManager().createNamedQuery("ReservaLibrosBiblioteca.findByIdTipoEstudiante");
        q.setParameter("idtipoEstudiante", idtipoEstudiante);
        return q.getResultList();
    }
}
