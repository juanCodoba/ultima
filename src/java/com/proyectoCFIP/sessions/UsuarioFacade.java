/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.Documentos;
//import com.proyectoCFIP.entities.Documentos_;
import com.proyectoCFIP.entities.Roles;
import com.proyectoCFIP.entities.Usuario;
//import com.proyectoCFIP.entities.Usuario_;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario findByCorreoElectronico(String query) {
        Query q = getEntityManager().createNamedQuery("Usuario.findByCorreoUsuario");
        q.setParameter("correoUsuario", query);
        try {
            return (Usuario) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }

    /*public List<Usuario> consultaUsuarioRol(Roles rol) {
       CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
       CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
       Root<Usuario> usuario = cq.from(Usuario.class);
       cq.where(cb.isMember(rol, usuario.get(Usuario_.rolesList)));
       TypedQuery<Usuario> q = getEntityManager().createQuery(cq);
       try {
           return q.getResultList();
       } catch (NoResultException ex) {
           return null;
       }
   }
    public List<Usuario> consultaUsuarioDocumento(Documentos documento) {
       CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
       CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
       Root<Usuario> usuario = cq.from(Usuario.class);
       cq.where(cb.isMember(documento, usuario.get(Usuario_.documentosList)));
       TypedQuery<Usuario> q = getEntityManager().createQuery(cq);
       try {
           return q.getResultList();
       } catch (NoResultException ex) {
           return null;
       }
   }*/
    public List<Usuario> consultaUsuarioRol(Roles rol) {
        Query q = getEntityManager().createNamedQuery("Usuario.findByRol");
        q.setParameter("rol", rol);
        q.setMaxResults(18);
        return q.getResultList();
    }

    public List<Usuario> consultaUsuarioCargos() {
        Query q = getEntityManager().createNamedQuery("Usuario.findByIdCargo");
        return q.getResultList();
    }

    public List<Usuario> consultaUsuariorRoles() {
        Query q = getEntityManager().createNamedQuery("Usuario.findByEstadoUsuarioAuditor");
        return q.getResultList();
    }

    public List<Usuario> consultaUsuarioCargosEst() {
        Query q = getEntityManager().createNamedQuery("Usuario.findByIdCargoEs");
        return q.getResultList();
    }

    public List<Usuario> consultaUsuarioDocente() {
        Query q = getEntityManager().createNamedQuery("Usuario.findByIdCargoDocentes");
        return q.getResultList();
    }

    public List<Usuario> consultaUsuarioAutonoma() {
        Query q = getEntityManager().createNamedQuery("Usuario.findByIdCargoAutoma");
        return q.getResultList();
    }

    public List<Usuario> consultaUsuarioTecnicos() {
        Query q = getEntityManager().createNamedQuery("Usuario.findByTecnicos");
        return q.getResultList();
    }

    public List<Usuario> consultaUsuarioDocumento(Documentos documento) {
        Query q = getEntityManager().createNamedQuery("Usuario.findByDocumento");
        q.setParameter("documento", documento);
        return q.getResultList();
    }

    public List<Usuario> consultaUsuarioEstado() {
        Query q = getEntityManager().createNamedQuery("Usuario.findByEstadoUsuario");
        return q.getResultList();
    }

    public List<Usuario> consultaTodos() {
        Query q = getEntityManager().createNamedQuery("Usuario.findAll");
        return q.getResultList();
    }
}
