package org.dfpl.lecture.Graph;

import java.sql.*;
import java.util.Collection;

public class PersistentGraph implements Graph {

    private Connection connection;
    private Statement stmt;
    private String tableName;

    public PersistentGraph(String id, String pwd, String dbName, String tableName, String port) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mariadb://localhost:" + port,id,pwd);

        stmt = connection.createStatement();
        /* database create */
        stmt.executeUpdate("CREATE OR REPLACE DATABASE " + dbName);
        /* Use database */
        stmt.executeUpdate("USE " + dbName);
        /* table create*/
        this.tableName = tableName;
        stmt.executeUpdate("CREATE OR REPLACE TABLE " + tableName +"(g VARCHAR(50),v BOOLEAN,v_id VARCHAR(10),e BOOLEAN, i BOOLEAN, o BOOLEAN);");

    }


    @Override
    public Vertex addVertex(String id) throws IllegalArgumentException, SQLException {
        PersistentVertex vertex = new PersistentVertex(this, id);

        /* overlap check */
        if(vertex != null);

        /* Insert into */
        stmt.executeUpdate("INSERT INTO " + tableName + " VALUES('" +
                this.toString() + "'," + //graph
                Boolean.TRUE + ",'" + //vertex
                id + "'," +
                Boolean.FALSE + "," + //edge
                Boolean.FALSE + "," + //in
                Boolean.FALSE + ");"); // out

        return vertex;
    }

    @Override
    public Vertex getVertex(String id) throws IllegalArgumentException {
        try {
            ResultSet rs = stmt.executeQuery("SELECT v_id FROM " + tableName +
                    " WHERE g = '" + this + "' AND v_id = '" + id + "';");

            rs.next();
            PersistentVertex vertex = new PersistentVertex(this, rs.getString(1));
            return vertex;
        }catch(Exception e){

        }
        return null;
    }

    @Override
    public void removeVertex(Vertex vertex) {

    }

    @Override
    public Collection<Vertex> getVertices() {
        return null;
    }

    @Override
    public Collection<Vertex> getVertices(String key, Object value) {
        return null;
    }

    @Override
    public Edge addEdge(Vertex outVertex, Vertex inVertex, String label) throws IllegalArgumentException, NullPointerException {
        return null;
    }

    @Override
    public Edge getEdge(Vertex outVertex, Vertex inVertex, String label) {
        return null;
    }

    @Override
    public Edge getEdge(String id) {
        return null;
    }

    @Override
    public void removeEdge(Edge edge) {

    }

    @Override
    public Collection<Edge> getEdges() {
        return null;
    }

    @Override
    public Collection<Edge> getEdges(String key, Object value) {
        return null;
    }

    @Override
    public void shutdown() {

    }
}
