/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.MaeHerramienta;
import com.proyectoCFIP.entities.MaeTipoHerramienta;
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
public class MaeHerramientaFacade extends AbstractFacade<MaeHerramienta> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeHerramientaFacade() {
        super(MaeHerramienta.class);
    }
     public List<MaeHerramienta> consultaTipoHerramienta(MaeTipoHerramienta idMaeTipoHerramienta){
        Query q= getEntityManager().createNamedQuery("MaeHerramienta.findByIdTipoMaeHerramienta");
        q.setParameter("idMaeTipoHerramienta", idMaeTipoHerramienta);
        return q.getResultList();
    }
}
