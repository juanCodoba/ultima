/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author junio
 */
@Named(value = "PruebaSqlController")
@SessionScoped
public class PrubaSqlServerController implements Serializable{
    
   private Date date_entered ;
   private Date date_modified ;
   private String modified_user_id  ;
   private String created_by;
   private String name ;
   private String description ;

   public PrubaSqlServerController() {
       
    }
    
   

    

}
