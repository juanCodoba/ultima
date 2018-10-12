/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.Documentos;
import com.proyectoCFIP.entities.HistorialComputador;
import com.proyectoCFIP.entities.HistorialDocumento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Junior Cabal
 */
@Stateless
public class HistorialDocumentoFacade extends AbstractFacade<HistorialDocumento> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialDocumentoFacade() {
        super(HistorialDocumento.class);
    }
       public List<HistorialDocumento> consultaDocu(Documentos docu){
        Query q= getEntityManager().createNamedQuery("HistorialDocumento.findByConsultaDocu");
        q.setParameter("idDocumento", docu);
        return q.getResultList();
    }
    
}
