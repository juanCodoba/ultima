/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.AuPlanAuditoria;
import com.proyectoCFIP.entities.AuProcesoEvaluado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author junio
 */
@Stateless
public class AuProcesoEvaluadoFacade extends AbstractFacade<AuProcesoEvaluado> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuProcesoEvaluadoFacade() {
        super(AuProcesoEvaluado.class);
    }
    public List<AuProcesoEvaluado> consultaProcesoPlan(AuPlanAuditoria idPlanAuditoria) {
          Query q= getEntityManager().createNamedQuery("AuProcesoEvaluado.findByIdAuPlanEvaluado");
          q.setParameter("idPlanAuditoria",idPlanAuditoria);
          return q.getResultList();
    }
}
