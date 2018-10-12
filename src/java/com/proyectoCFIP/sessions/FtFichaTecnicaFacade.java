/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.FtCategoria;
import com.proyectoCFIP.entities.FtFichaTecnica;
import com.proyectoCFIP.entities.TipoFicha;
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
public class FtFichaTecnicaFacade extends AbstractFacade<FtFichaTecnica> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FtFichaTecnicaFacade() {
        super(FtFichaTecnica.class);
    }

    public List<FtFichaTecnica> consultaEstado(String estado) {
        Query q = getEntityManager().createNamedQuery("FtFichaTecnica.findByEstado");
        q.setParameter("estado", estado);
        return q.getResultList();
    }

    public List<FtFichaTecnica> consultaEstado1(String estado) {
        Query q = getEntityManager().createNamedQuery("FtFichaTecnica.findByEstado");
        q.setParameter("estado", estado);

        return q.getResultList();
    }

    public List<FtFichaTecnica> consultaItem(String idItem) {
        Query q = getEntityManager().createNamedQuery("FtFichaTecnica.findByIdItem");
        q.setParameter("idItem", idItem);
        return q.getResultList();
    }

    public List<FtFichaTecnica> consultaVersion(String idItem) {
        Query q = getEntityManager().createNamedQuery("FtFichaTecnica.findByEstadoVersion");
        q.setParameter("idItem", idItem);
        return q.getResultList();
    }

    public List<FtFichaTecnica> consultaEstadoDesarrollador() {
        Query q = getEntityManager().createNamedQuery("FtFichaTecnica.findByEstadoDesarrollador");
        return q.getResultList();
    }

    public List<FtFichaTecnica> consultaEstadoDesarrolladorq() {
        Query q = getEntityManager().createNamedQuery("FtFichaTecnica.findByEstadoDesarrollador");
        return q.getResultList();
    }

    public List<FtFichaTecnica> consultaTipo(FtCategoria idFtCategoria) {
        Query q = getEntityManager().createNamedQuery("FtFichaTecnica.findByIdFtCategoria");
        q.setParameter("idFtCategoria", idFtCategoria);
        return q.getResultList();
    }

    public List<FtFichaTecnica> consultaTipoF(TipoFicha idTipoFicha) {
        Query q = getEntityManager().createNamedQuery("FtFichaTecnica.findByIdTipoFicha");
        q.setParameter("idTipoFicha", idTipoFicha);
        return q.getResultList();
    }

    public List<FtFichaTecnica> consultaTipoCamibuso() {
        Query q = getEntityManager().createNamedQuery("FtFichaTecnica.findByCamibuso");
        return q.getResultList();
    }

    public List<FtFichaTecnica> consultaTipoCamisa() {
        Query q = getEntityManager().createNamedQuery("FtFichaTecnica.findByCamisa");
        return q.getResultList();
    }

    public List<FtFichaTecnica> consultaTipoFT() {
        Query q = getEntityManager().createNamedQuery("FtFichaTecnica.findByFichaTec");
        return q.getResultList();
    }

    public List<FtFichaTecnica> consultaTipoOp() {
        Query q = getEntityManager().createNamedQuery("FtFichaTecnica.findByOpm");
        return q.getResultList();
    }

//     
//    public List<FtFichaTecnica> consultaUrlImagesForId(){
//        Query q = getEntityManager().createNativeQuery(" SELECT imagenes.image_url, imagenes.id_ficha_tecnica FROM  imagenes, ft_ficha_tecnica WHERE ft_ficha_tecnica.id_ft_ficha_tecnica =  imagenes.id_ficha_tecnica");
//        return q.getResultList();
//    }
}
