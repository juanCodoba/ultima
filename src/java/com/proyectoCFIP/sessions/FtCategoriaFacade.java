/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.FtCategoria;
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
public class FtCategoriaFacade extends AbstractFacade<FtCategoria> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FtCategoriaFacade() {
        super(FtCategoria.class);
    }
    
        public List<FtCategoria> consultaCategoria(){
        Query q= getEntityManager().createNamedQuery("FtCategoria.findByIdFtCategoria");
        return q.getResultList();
    }
    
}
