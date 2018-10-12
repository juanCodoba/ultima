/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Area;
import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.Tipo;
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
 * @author Junior Cabal
 */
@Stateless
public class ComputadorFacade extends AbstractFacade<Computador> {
    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComputadorFacade() {
        super(Computador.class);
    }
    public Computador findByCodigoComputador(String query){
          Query q= getEntityManager().createNamedQuery("Computador.findByCodigoComputador");
          q.setParameter("codigoComputador",query);
          try {
            return (Computador) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex){
            return null;
        }
          
    }
    public List<Computador> findByNombre (String nombre){
        Query q= getEntityManager().createNamedQuery("Computador.findByCodigoComputador");
        q.setParameter("codigoComputador",nombre +"%");
        return q.getResultList();
    }
    public List<Computador> consultaComputadorActivo(boolean estado){
        Query q= getEntityManager().createNamedQuery("Computador.findByEstadoComputador");
        q.setParameter("estadoComputador", estado);
        return q.getResultList();
    }
    public List<Computador> consultaUsuario(Usuario usuario){
        Query q= getEntityManager().createNamedQuery("Computador.findByUsuario");
        q.setParameter("idUsuario", usuario);
        return q.getResultList();
    }
    public List<Computador> consultaUsuarioTipo(Usuario usuario, Tipo tipo){
        Query q= getEntityManager().createNamedQuery("Computador.findByUsuarioTipo");
        q.setParameter("idUsuario", usuario);
        q.setParameter("idTipo", tipo);
        return q.getResultList();
    }
    public List<Computador> consultaArea(Area area, Tipo tipo){
        Query q= getEntityManager().createNamedQuery("Computador.findByArea");
        q.setParameter("idArea", area);
        q.setParameter("idTipo", tipo);
        return q.getResultList();
    }
    public List<Computador> consultaComputadorProgramado(boolean estadoProgramado,boolean estadoActivo){
        Query q= getEntityManager().createNamedQuery("Computador.findByEstadoProgramado");
        q.setParameter("estadoProgramado", estadoProgramado);
        q.setParameter("estadoComputador", estadoActivo);
        return q.getResultList();
    }
    public List<Computador> consultaReport(){
        Query q= getEntityManager().createNamedQuery("Computador.findByReport");
        return q.getResultList();
    }
}
