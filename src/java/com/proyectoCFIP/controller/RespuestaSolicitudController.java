/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.RespuestaSolicitud;
import com.proyectoCFIP.entities.SolicitudEdificio;
import com.proyectoCFIP.sessions.RespuestaSolicitudFacade;
import com.proyectoCFIP.sessions.SolicitudEdificioFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class RespuestaSolicitudController implements Serializable{

    @EJB
    private RespuestaSolicitudFacade respuestaSolicitudFacade;
    @EJB
    private SolicitudEdificioFacade solicitudEdificioFacade;
    private RespuestaSolicitud respuestaSolicitudActual;
    private List<RespuestaSolicitud> respuestaSolicitudList;
    private SolicitudEdificio SolicitudEdificioActual;
    
    public RespuestaSolicitudController() {
    }

    public RespuestaSolicitudFacade getRespuestaSolicitudFacade() {
        return respuestaSolicitudFacade;
    }

    public void setRespuestaSolicitudFacade(RespuestaSolicitudFacade respuestaSolicitudFacade) {
        this.respuestaSolicitudFacade = respuestaSolicitudFacade;
    }

    public RespuestaSolicitud getRespuestaSolicitudActual() {
        return respuestaSolicitudActual;
    }

    public void setRespuestaSolicitudActual(RespuestaSolicitud respuestaSolicitudActual) {
        this.respuestaSolicitudActual = respuestaSolicitudActual;
    }

    public List<RespuestaSolicitud> getRespuestaSolicitudList() {
        return respuestaSolicitudList = getRespuestaSolicitudFacade().findAll();
    }

    public void setRespuestaSolicitudList(List<RespuestaSolicitud> respuestaSolicitudList) {
        this.respuestaSolicitudList = respuestaSolicitudList;
    }

   

    public SolicitudEdificioFacade getSolicitudEdificioFacade() {
        return solicitudEdificioFacade;
    }

    public void setSolicitudEdificioFacade(SolicitudEdificioFacade solicitudEdificioFacade) {
        this.solicitudEdificioFacade = solicitudEdificioFacade;
    }

    public SolicitudEdificio getSolicitudEdificioActual() {
        return SolicitudEdificioActual;
    }

    public void setSolicitudEdificioActual(SolicitudEdificio SolicitudEdificioActual) {
        this.SolicitudEdificioActual = SolicitudEdificioActual;
    }
    
    public void prepareCreate(){
        respuestaSolicitudActual = new RespuestaSolicitud();
    }
    public void add(){
        respuestaSolicitudActual.setIdSolicitudEdificio(SolicitudEdificioActual);
        respuestaSolicitudActual.setFechaRespuesta(new Date());
        respuestaSolicitudFacade.create(respuestaSolicitudActual);
        solicitudEdificioFacade.edit(SolicitudEdificioActual);
    }
    public String List(){
        return "";
    }
}
