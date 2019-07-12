/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.sessions;

import static com.lowagie.text.xml.simpleparser.EntitiesToSymbol.map;
import com.proyectoCFIP.controller.FactorController;
import com.proyectoCFIP.entities.Categoria;
import com.proyectoCFIP.entities.Factores;
import static com.proyectoCFIP.entities.Factores_.idItemSituacion;
import com.proyectoCFIP.entities.ItemSituacion;
import com.proyectoCFIP.entities.Procesos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
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
public class FactoresFacade extends AbstractFacade<Factores> {

    @PersistenceContext(unitName = "ProyectoV2CFIPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FactoresFacade() {
        super(Factores.class);
    }

    public List<Factores> consultaCargo(Procesos idProceso) {
        Query q = getEntityManager().createNamedQuery("Factores.findByIdFactores");
        q.setParameter("idProceso", idProceso);
        q.setMaxResults(5);

        return q.getResultList();
    }

    public List<Factores> consultaPieChart(Procesos idProceso) {
        Query q = getEntityManager().createNativeQuery("SELECT categoria.nombre_categoria,  sum(puntuacion) as total \n"
                + "from factores inner join categoria on factores.id_categoria = categoria.id_categoria where factores.id_proceso = ?idProceso  group by factores.id_categoria ;", Factores.class);
        q.setParameter("idProceso", idProceso);
        return q.getResultList();
    }

    public List<Factores> consultaCargo2(Procesos idProceso) {
        Query q = getEntityManager().createNamedQuery("Factores.findByIdFactores2");
        q.setParameter("idProceso", idProceso);
        q.setMaxResults(5);
        return q.getResultList();
    }

    public List<Factores> consultaCargo3(Procesos idProceso) {
        Query q = getEntityManager().createNamedQuery("Factores.findByIdFactores3");
        q.setParameter("idProceso", idProceso);
        q.setMaxResults(5);
        return q.getResultList();
    }
        public List<Factores> consultaCargo4(Procesos idProceso) {
        Query q = getEntityManager().createNamedQuery("Factores.findByIdFactores4");
        q.setParameter("idProceso", idProceso);
        q.setMaxResults(5);
        return q.getResultList();
    }
            public List<Factores> consultaCargo5(Procesos idProceso) {
        Query q = getEntityManager().createNamedQuery("Factores.findByIdFactores5");
        q.setParameter("idProceso", idProceso);
        q.setMaxResults(5);
        return q.getResultList();
    }
                public List<Factores> consultaCargo6(Procesos idProceso) {
        Query q = getEntityManager().createNamedQuery("Factores.findByIdFactores6");
        q.setParameter("idProceso", idProceso);
        q.setMaxResults(5);
        return q.getResultList();
    }

    public List<Factores> consultaPuntucion() {
        Query q = getEntityManager().createNamedQuery("Factores.findByPuntuacion");
        q.setMaxResults(5);
        return q.getResultList();
    }

    public List<Factores> consultaGlobal() {
        Query q = getEntityManager().createNamedQuery("Factores.findByIdFactoresGlobal");
        q.setMaxResults(5);
        return q.getResultList();
    }

    public List<Factores> consultaGlobal2() {
        Query q = getEntityManager().createNamedQuery("Factores.findByIdFactoresGlobal2");
        q.setMaxResults(5);
        return q.getResultList();
    }

    public List<Factores> consultaGlobal3() {
        Query q = getEntityManager().createNamedQuery("Factores.findByIdFactoresGlobal3");
        q.setMaxResults(5);
        return q.getResultList();
    }
    
    public List<Factores> consultaGlobal4() {
        Query q = getEntityManager().createNamedQuery("Factores.findByIdFactoresGlobal4");
        q.setMaxResults(5);
        return q.getResultList();
    }
    
    public List<Factores> consultaGlobal5() {
        Query q = getEntityManager().createNamedQuery("Factores.findByIdFactoresGlobal5");
        q.setMaxResults(5);
        return q.getResultList();
    }
    
    public List<Factores> consultaGlobal6() {
        Query q = getEntityManager().createNamedQuery("Factores.findByIdFactoresGlobal6");
        q.setMaxResults(5);
        return q.getResultList();
    }

//    public class ConexionMySQL {
//
//        // Librería de MySQL
//        public String driver = "com.mysql.jdbc.Driver";
//
//        // Nombre de la base de datos
//        public String database = "proyectocfip";
//
//        // Host
//        public String hostname = "localhost";
//
//        // Puerto
//        public String port = "3306";
//
//        // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
//        public String url = "jdbc:mysql://localhost:3306/proyectocfip?zeroDateTimeBehavior=convertToNull";
//
//        // Nombre de usuario
//        public String username = "root";
//
//        // Clave de usuario
//        public String password = "1234";
//
//        public Connection conectarMySQL() throws SQLException {
//            Connection conn = null;
//
//            try {
//                Class.forName(driver);
//                conn = DriverManager.getConnection(url, username, password);
//            } catch (ClassNotFoundException | SQLException e) {
//                e.printStackTrace();
//            }
//
//            return conn;
//        }
//
//    }
    
    public class ConexionMySQL {

        public String driver = "com.mysql.jdbc.Driver";

        public String database = "proyectocfip";

        public String hostname = "localhost";

        public String port = "3306";

        public String url = "jdbc:mysql://localhost:3306/proyectocfip?zeroDateTimeBehavior=convertToNull";

        public String username = "root";

        public String password = "1234";

        public Connection conectarMySQL() throws SQLException {
            Connection conn = null;

            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            return conn;
        }

    }

    public List<Factores> listar(Procesos idProceso) {

        List<Factores> lista = null;
        ResultSet rs;
        //Integer id = idProceso.getIdProceso();

        //Factores factor = new Factores();
        try {
            //String proceso = idProceso.getIdProceso().toString();

            ConexionMySQL SQL = new ConexionMySQL();
            Connection conn = SQL.conectarMySQL();

            PreparedStatement st = conn.prepareStatement("SELECT categoria.nombre_categoria,  sum(puntuacion) as total \n"
                    + "from factores inner join categoria on factores.id_categoria = categoria.id_categoria where factores.id_proceso = ? "
                    + " group by factores.id_categoria ;");

            st.setInt(1, idProceso.getIdProceso());
            st.execute();
            rs = st.executeQuery();
            st.addBatch();
            lista = new ArrayList();

            while (rs.next()) {
                Factores factD = new Factores();
                factD.setDescripcionFactor(rs.getString("nombre_categoria"));
                factD.setPuntuacion(rs.getInt("total"));
                lista.add(factD);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;

    }

    public List<Factores> listar2(Procesos idProceso) throws RuntimeException {

        List<Factores> lista2 = null;
        ResultSet rs;
        Integer id = idProceso.getIdProceso();
        try {

            ConexionMySQL SQL = new ConexionMySQL();
            Connection conn = SQL.conectarMySQL();

            PreparedStatement st = conn.prepareStatement("SELECT categoria.nombre_categoria,  sum(puntuacion) as total \n"
                    + "from factores inner join categoria on factores.id_categoria = categoria.id_categoria where factores.id_proceso = ? "
                    + " group by factores.id_categoria ;");
            st.setInt(1, id);
            st.execute();
            rs = st.executeQuery();
            st.addBatch();
            st.clearParameters();

            lista2 = new ArrayList();

            while (rs.next()) {
                Factores factD = new Factores();
                factD.setDescripcionFactor(rs.getString("nombre_categoria"));
                factD.setPuntuacion(rs.getInt("total"));
                lista2.add(factD);

            }


        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return lista2;
    }

    public List<Factores> listarItem(Procesos idProceso) throws RuntimeException {

        List<Factores> lista2 = null;
        ResultSet rs;
        Integer id = idProceso.getIdProceso();
        try {

            ConexionMySQL SQL = new ConexionMySQL();
            Connection conn = SQL.conectarMySQL();

            if (id != null) {
                PreparedStatement st = conn.prepareStatement("SELECT items_situacion.descripcion,  sum(puntuacion) as total \n"
                        + "from factores inner join items_situacion on factores.id_items_situacion = items_situacion.id_items_situacion where factores.id_proceso = ?  group by factores.id_items_situacion ;");
                st.setInt(1, id);
                st.execute();
                rs = st.executeQuery();
                st.addBatch();
                lista2 = new ArrayList();

                while (rs.next()) {
                    Factores factD = new Factores();
                    factD.setDescripcionFactor(rs.getString("descripcion"));
                    factD.setPuntuacion(rs.getInt("total"));
                    lista2.add(factD);

                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return lista2;
    }

    public List<Factores> listarHorGlobal() throws SQLException {

        List<Factores> lista = null;
        ResultSet rs;

        try {

            ConexionMySQL SQL = new ConexionMySQL();
            Connection conn = SQL.conectarMySQL();

            PreparedStatement st = conn.prepareStatement("SELECT categoria.nombre_categoria,  sum(puntuacion) as total \n"
                    + "from factores inner join categoria on factores.id_categoria = categoria.id_categoria group by factores.id_categoria  ;");

            rs = st.executeQuery();

            lista = new ArrayList();

            while (rs.next()) {
                Factores fact = new Factores();
                fact.setDescripcionFactor(rs.getString("nombre_categoria"));
                fact.setPuntuacion(rs.getInt("total"));
                lista.add(fact);

            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return lista;
    }

    public List<Factores> listarItemGlobal() throws SQLException {

        List<Factores> lista2 = null;
        ResultSet rs;
        //Integer id = idProceso.getIdProceso();
        try {

            ConexionMySQL SQL = new ConexionMySQL();
            Connection conn = SQL.conectarMySQL();

            PreparedStatement st = conn.prepareStatement("SELECT  items_situacion.descripcion,  sum(puntuacion) as total \n"
                    + "from factores inner join items_situacion on factores.id_items_situacion = items_situacion.id_items_situacion group by factores.id_items_situacion ;");
            st.execute();
            rs = st.executeQuery();
            st.addBatch();
            lista2 = new ArrayList();
            while (rs.next()) {
                Factores factD = new Factores();
                factD.setDescripcionFactor(rs.getString("descripcion"));
                factD.setPuntuacion(rs.getInt("total"));
                lista2.add(factD);

            }
            rs.close();
            return lista2;

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return lista2;
    }

    public List<Factores> listarGlobal() throws SQLException {

        List<Factores> lista = null;
        ResultSet rs;

        try {

            ConexionMySQL SQL = new ConexionMySQL();
            Connection conn = SQL.conectarMySQL();

            PreparedStatement st = conn.prepareStatement("SELECT categoria.nombre_categoria,  sum(puntuacion) as total \n"
                    + "from factores inner join categoria on factores.id_categoria = categoria.id_categoria group by factores.id_categoria  ;");

            rs = st.executeQuery();

            lista = new ArrayList();

            while (rs.next()) {
                Factores fact = new Factores();
                fact.setDescripcionFactor(rs.getString("nombre_categoria"));
                fact.setPuntuacion(rs.getInt("total"));
                lista.add(fact);

            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return lista;
    }
}
