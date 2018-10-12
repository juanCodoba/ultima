/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.EstadoLibro;
import com.proyectoCFIP.entities.Genero;
import com.proyectoCFIP.entities.Libro;
import com.proyectoCFIP.entities.TipoMantenimiento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Luis Carlos Cabal
 */
@Stateless
public class LibroFacade extends AbstractFacade<Libro> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LibroFacade() {
        super(Libro.class);
    }

    public List<Libro> consultaLibEstado() {
        Query q = getEntityManager().createNamedQuery("Libro.findByActivo");
        return q.getResultList();
    }

    public List<Libro> consultaLibTipoMant(TipoMantenimiento idTipoMantenimiento) {
        Query q = getEntityManager().createNamedQuery("Libro.findByConsultaCronograma");
        q.setParameter("idTipoMantenimiento", idTipoMantenimiento);

        return q.getResultList();
    }

    public List<Libro> consultaLibId() {
        Query q = getEntityManager().createNamedQuery("Libro.findByIdLibro");
        return q.getResultList();
    }

    public List<Libro> consultaLibGenero(Genero idGenero) {
        Query q = getEntityManager().createNamedQuery("Libro.findByIdGenero");
        q.setParameter("idGenero", idGenero);

        return q.getResultList();
    }

    public List<Libro> consultaTodos() {
        Query q = getEntityManager().createNamedQuery("Libro.findByTotalDisponibilidad");
        return q.getResultList();
    }

    public List<Libro> consultaOtrosP() {
        Query q = getEntityManager().createNamedQuery("Libro.findByTipoTotalPrev");
        return q.getResultList();
    }

    public List<Libro> consultaDisponibiLibrosReservas() {
        Query q = getEntityManager().createNamedQuery("Libro.findByTotalDisponibilidadReserva");
        return q.getResultList();
    }

    public List<Libro> consultaOtrosDeBaja() {
        Query q = getEntityManager().createNamedQuery("Libro.findByTipoTotalDeBaja");
        return q.getResultList();
    }

    public List<Libro> consultaOtrosC() {
        Query q = getEntityManager().createNamedQuery("Libro.findByTipoTotalCorre");
        return q.getResultList();
    }
    
        public List<Libro> consultaReservas() {
        Query q = getEntityManager().createNamedQuery("Libro.findByTipoTotalReservados");
        return q.getResultList();
    }

    public List<Libro> consultaCronograma(TipoMantenimiento idTipoMantenimiento, EstadoLibro idEstadoLibro) {
        Query q = getEntityManager().createNamedQuery("Libro.findByConsultaCronograma");
        q.setParameter("idEstadoLibro", idEstadoLibro);
        q.setParameter("idTipoMantenimiento", idTipoMantenimiento);
        return q.getResultList();
    }

}
