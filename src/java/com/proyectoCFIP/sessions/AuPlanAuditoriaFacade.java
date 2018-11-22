 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.AuPeriodoPlanAuditoria;
import com.proyectoCFIP.entities.AuPlanAuditoria;
import com.proyectoCFIP.entities.TipoAuditoria;
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
public class AuPlanAuditoriaFacade extends AbstractFacade<AuPlanAuditoria> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;
    public String consultaTipoPlanAuditoriaCalidad;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuPlanAuditoriaFacade() {
        super(AuPlanAuditoria.class);
    }

    
    
       public List<AuPlanAuditoria> consultaTipoPlanAuditoria() {
        Query q = getEntityManager().createNamedQuery("AuPlanAuditoria.findByTipoAuditoria");   
        return q.getResultList();
    }

    public List<AuPlanAuditoria> consultaPlanAuditoriaAnio( AuPeriodoPlanAuditoria idAuPeriodoPlanAuditoria){
         Query q= getEntityManager().createNamedQuery("AuPlanAuditoria.findByTipoAnio");
         q.setParameter("idAuPeriodoPlanAuditoria", idAuPeriodoPlanAuditoria);
        return q.getResultList();
    }
    
    public List<AuPlanAuditoria> consultaTipoPlanAuditoriaCalidad() {
        Query q = getEntityManager().createNamedQuery("AuPlanAuditoria.findByTipoAuditoriaCalidad");
        return q.getResultList();
    }
//
    public List<AuPlanAuditoria> consultaTipoPlanAuditoriaExterna() {
        Query q = getEntityManager().createNamedQuery("AuPlanAuditoria.findByTipoAuditoriaExterna");
        return q.getResultList();
    }

//    public List<AuPlanAuditoria> ConsultaTipoPlanPrueba(String tipoAuditoria){
//        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<AuPlanAuditoria> cq = cb.createQuery(AuPlanAuditoria.class);
//        Root<AuPlanAuditoria> auPlanAudoria = cq.from(AuPlanAuditoria.class);
//        
//        Predicate data = cb.conjunction();
//        if(tipoAuditoria != null){
//            data = cb.and(data, cb.like(
//                    auPlanAudoria.get(AuPlanAuditoria_.tipoAuditoria),"%" + tipoAuditoria + "%" ));
//        }
//                
//    }

}
