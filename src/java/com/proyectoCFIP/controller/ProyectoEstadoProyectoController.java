/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.ProyectoEstadoProyecto;
import com.proyectoCFIP.sessions.ProyectoEstadoProyectoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Junior Cabal
 */
@Named(value = "proyectoEstadoProyecto")
@SessionScoped
public class ProyectoEstadoProyectoController implements Serializable {

    @EJB
    private ProyectoEstadoProyectoFacade proyectoEstadoFacade;
    private ProyectoEstadoProyecto proyectoActual;
    private List<ProyectoEstadoProyecto> listaProyecto;

    public List<ProyectoEstadoProyecto> getListaProyecto() {
        return listaProyecto = getProyectoEstadoFacade().findAll();
    }

    public void setListaProyecto(List<ProyectoEstadoProyecto> listaProyecto) {
        this.listaProyecto = listaProyecto;
    }
    
    public ProyectoEstadoProyectoController() {
    }

    public ProyectoEstadoProyectoFacade getProyectoEstadoFacade() {
        return proyectoEstadoFacade;
    }

    public void setProyectoEstadoFacade(ProyectoEstadoProyectoFacade proyectoEstadoFacade) {
        this.proyectoEstadoFacade = proyectoEstadoFacade;
    }

    public ProyectoEstadoProyecto getProyectoActual() {
        return proyectoActual;
    }

    public void setProyectoActual(ProyectoEstadoProyecto proyectoActual) {
        this.proyectoActual = proyectoActual;
    }

    
}
