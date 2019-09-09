/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import com.proyectoCFIP.entities.OpPlanilla;
import com.proyectoCFIP.entities.Planilla;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
public class PlanillaFacade extends AbstractFacade<Planilla> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanillaFacade() {
        super(Planilla.class);
    }

    public List<Planilla> consultaPlanillaOp(OpPlanilla idOpPlanilla) {
        Query q = getEntityManager().createNamedQuery("Planilla.findByIdOpPlanilla");
        q.setParameter("idOpPlanilla", idOpPlanilla);
        return q.getResultList();
    }

    public List<Planilla> redondedoValorSubTotal(OpPlanilla idOpPlanilla) throws SQLException {
        List<Planilla> planillaListaActualPrueba = null;
        Integer op = idOpPlanilla.getIdOpPlanilla();
        ResultSet rs;
        try {
            Statement stmt = null;

            String user = "root";
            //String password = "cfiprovidencia1";
            String password = "1234";
            String con = "jdbc:mysql://localhost:3306/proyectocfip?user=" + user + "&password=" + password;
            Connection conn = DriverManager.getConnection(con);
            //Integer id = idProceso.getIdProceso();

            //Factores factor = new Factores();
            //String proceso = idProceso.getIdProceso().toString();
            PreparedStatement st = conn.prepareStatement("SELECT \n"
                    + "SUM(valor_subtotal) AS valor_subotal \n"
                    + "FROM planilla where op = ? ;");

            planillaListaActualPrueba = new ArrayList<>();
            st.setInt(1, op);
            rs = st.executeQuery();

            while (rs.next()) {
                Planilla planilla = new Planilla();
                planilla.setValorTotal(rs.getInt("valor_subotal"));

                planillaListaActualPrueba.add(planilla);
            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return planillaListaActualPrueba;

    }

}
