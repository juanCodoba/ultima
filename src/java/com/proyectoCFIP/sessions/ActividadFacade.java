/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Actividad;
import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.CronogramaMantenimientos;
import com.proyectoCFIP.entities.EstadoCronograma;
import com.proyectoCFIP.entities.MaeFuncion;
import com.proyectoCFIP.entities.TipoMantenimiento;
import java.util.ArrayList;
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
public class ActividadFacade extends AbstractFacade<Actividad> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActividadFacade() {
        super(Actividad.class);
    }

    public List<Actividad> consultaUsuarioTipo(MaeFuncion idMaeFuncion, Cargos idCargo) {
        Query q = getEntityManager().createNamedQuery("Actividad.findByIdMaeFuncion");
        q.setParameter("idMaeFuncion", idMaeFuncion);
        q.setParameter("idCargo", idCargo);

        return q.getResultList();
    }
        public List<Actividad> consultaUsuarioTipo(Cargos idCargo) {
        Query q = getEntityManager().createNamedQuery("Actividad.findByIdCargo");
        q.setParameter("idCargo", idCargo);
        return q.getResultList();
    }

}
