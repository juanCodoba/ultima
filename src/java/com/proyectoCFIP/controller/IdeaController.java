/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Idea;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.IdeaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Luis Carlos Cabal
 */
@Named(value = "ideaController")
@SessionScoped
public class IdeaController implements Serializable {

    @EJB
    private IdeaFacade ideaFacade;
    private Idea ideaActual;
    private Usuario usuarioActual;
    private List<Idea> listaIdea;
    
    public IdeaController() {
    }

    public IdeaFacade getIdeaFacade() {
        return ideaFacade;
    }

    public void setIdeaFacade(IdeaFacade ideaFacade) {
        this.ideaFacade = ideaFacade;
    }

    public Idea getIdeaActual() {
        return ideaActual;
    }

    public void setIdeaActual(Idea ideaActual) {
        this.ideaActual = ideaActual;
    }

    public List<Idea> getListaIdea() {
        return getIdeaFacade().findAll();
    }

    public void setListaIdea(List<Idea> listaIdea) {
        this.listaIdea = listaIdea;
    }
    
    public void recargarLista(){
        ideaActual = new Idea();
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    public String add(){
        try {
            ideaActual.setIdUsuario(usuarioActual);
            ideaActual.setEstado("Espera");
            ideaActual.setFecha(new Date());
            getIdeaFacade().create(ideaActual);
            recargarLista();
            return "/usuario/modProyectos/bancoIdea/listaBancoIdea";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo completar la acción","");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "";
        }
    }
    public String edit(){
        return "";
    }
    public String prepareList(){
        return "";
    }
    public String preparePaginaPMO() {
        ideaActual = new Idea();
        return "/usuario/modProyectos/paginaPrincipal";
    }  
    public String prepareListIdeasBuenas(){
        return "/usuario/modProyectos/bancoIdea/listaBancoIdea";
    }
}
