/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Junior Cabal
 */
@Named(value = "calidadCausa")
@SessionScoped
public class CalidadCausaController implements Serializable {

    /**
     * Creates a new instance of CalidadCausa
     */
    public CalidadCausaController() {
    }
    
}
