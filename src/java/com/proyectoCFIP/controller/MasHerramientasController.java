/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Cumpleanos;
import com.proyectoCFIP.entities.Telefonos;
import com.proyectoCFIP.sessions.CumpleanosFacade;
import com.proyectoCFIP.sessions.TelefonosFacade;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Luis Carlos Cabal
 */
@ManagedBean
@SessionScoped
public class MasHerramientasController implements Serializable {

    @EJB
    private TelefonosFacade telefonoFacade;
    private List<Telefonos> listaTelefonos;
    @EJB
    private CumpleanosFacade cumpleañosFacade;
    private List<Cumpleanos> listaCumpleaños;
    public MasHerramientasController() {
    }

    public TelefonosFacade getTelefonoFacade() {
        return telefonoFacade;
    }

    public void setTelefonoFacade(TelefonosFacade telefonoFacade) {
        this.telefonoFacade = telefonoFacade;
    }

    public List<Telefonos> getListaTelefonos() {
        return listaTelefonos;
    }

    public void setListaTelefonos(List<Telefonos> listaTelefonos) {
        this.listaTelefonos = listaTelefonos;
    }

    public CumpleanosFacade getCumpleañosFacade() {
        return cumpleañosFacade;
    }

    public void setCumpleañosFacade(CumpleanosFacade cumpleañosFacade) {
        this.cumpleañosFacade = cumpleañosFacade;
    }

    public List<Cumpleanos> getListaCumpleaños() {
        return listaCumpleaños = getCumpleañosFacade().findAll();
    }

    public void setListaCumpleaños(List<Cumpleanos> listaCumpleaños) {
        this.listaCumpleaños = listaCumpleaños;
    }
    
   
}
