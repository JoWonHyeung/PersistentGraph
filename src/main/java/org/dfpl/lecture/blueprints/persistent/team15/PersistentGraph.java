package org.dfpl.lecture.blueprints.persistent.team15;

import org.dfpl.lecture.revised.Edge;
import org.dfpl.lecture.revised.Graph;
import org.dfpl.lecture.revised.Vertex;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PersistentGraph implements Graph {

    private Connection connection;
    private Statement stmt;
    private String user_id;
    private String user_pwd;
    private String dbName;

    public PersistentGraph(String user_id, String user_pwd, String dbName) throws SQLException {
        this.user_id = user_id;
        this.user_pwd = user_pwd;

        connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", user_id, user_pwd);

        stmt = connection.createStatement();
        /* database create */
        stmt.executeUpdate("CREATE OR REPLACE DATABASE " + dbName + ";");
        /* Use database */
        stmt.executeUpdate("USE " + dbName + ";");
        /* table create*/
        stmt.executeUpdate("CREATE OR REPLACE TABLE e(o VARCHAR(10),i VARCHAR(10),label VARCHAR(50),id VARCHAR(70) PRIMARY KEY,properties JSON);");
        stmt.executeUpdate("CREATE OR REPLACE TABLE v(id VARCHAR(50) PRIMARY KEY,properties JSON);");
    }

    @Override
    public Vertex addVertex(String id) throws IllegalArgumentException {
        try {
            if (id.contains("|")) {
                throw new IllegalArgumentException("id cannot contain '|'");
            }
            stmt.executeUpdate("INSERT IGNORE INTO v VALUES ('" + id + "','" + null + "');");
            return new PersistentVertex(id, stmt);
        } catch (Exception e) {
            System.out.println("[5] " + e.getMessage());
        }
        return null;
    }

    @Override
    public Vertex getVertex(String id) throws IllegalArgumentException {
        try {
            String query = "SELECT id FROM v WHERE id = '" + id + "'";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            if (rs.getString(1) == null) return null;
            return new PersistentVertex(rs.getString(1), stmt);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void removeVertex(Vertex vertex) {
        try {
            PersistentVertex v = (PersistentVertex) vertex;
            String query = "SELECT id FROM v WHERE id = '" + v.getId() + "';";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            if (rs.getString(1) == null) return;
            query = "DELETE FROM v WHERE g = '" + this + "' AND id = '" + rs.getString(1) + "';";
            stmt.executeUpdate(query);
        } catch (Exception e) {
        }
    }

    @Override
    public Collection<Vertex> getVertices() {
        try {
            ArrayList<Vertex> arrayList = new ArrayList<>();
            String query = "SELECT id FROM v;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                arrayList.add(new PersistentVertex(rs.getString(1), stmt));
            }
            return arrayList;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Collection<Vertex> getVertices(String key, Object value) {
        try {
            ArrayList<Vertex> arrayList = new ArrayList<>();
            String json_path = "$." + key;
            String query = "SELECT id, JSON_CONTAINS(properties,JSON_OBJECT('" + key + "','" + value + "')) FROM v;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getString(2).equals("1")) {
                    arrayList.add(new PersistentVertex(rs.getString(1), stmt));
                }
            }
            return arrayList;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Edge addEdge(Vertex outVertex, Vertex inVertex, String label) {
        try {
            if (label.contains("|")) {
                throw new IllegalArgumentException("[18] " + "label cannot contain '|'");
            }

            if (outVertex == null) {
                throw new NullPointerException("outVertex cannot be null");
            }

            if (inVertex == null) {
                throw new NullPointerException("[17] " + "inVertex cannot be null");
            }
            String edge = outVertex.getId() + "|" + label + "|" + inVertex.getId();
            stmt.executeUpdate("INSERT IGNORE INTO e VALUES ('" +
                    outVertex.getId() + "','" +
                    inVertex.getId() + "','" +
                    label + "','" +
                    edge + "','" +
                    null +
                    "');");
            return new PersistentEdge(outVertex, label, inVertex, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Edge getEdge(Vertex outVertex, Vertex inVertex, String label) {
        try {
            String query = "SELECT label FROM e WHERE " +
                    "o = '" + outVertex.getId() + "' AND " +
                    "i = '" + inVertex.getId() + "' AND " +
                    "label = '" + label +
                    "';";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            if (rs.getString(1) == null) return null;
            return new PersistentEdge(outVertex, label, inVertex, stmt);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Edge getEdge(String id) {
        try {
            String query = "SELECT o,i,label,id FROM e WHERE id = '" + id + "';";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            if (rs.getString(1) == null) return null;
            String o = rs.getString(1);
            String i = rs.getString(2);
            String label = rs.getString(3);
            return new PersistentEdge(
                    new PersistentVertex(o, stmt),
                    label,
                    new PersistentVertex(i, stmt),
                    stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeEdge(Edge edge) {
        try {
            String query = "DELETE FROM e WHERE g = '" + this + "' AND id = '" + edge.getId() + "';";
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection<Edge> getEdges() {
        try {
            ArrayList<Edge> arrayList = new ArrayList<>();
            String query = "SELECT * FROM e;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PersistentVertex outv = new PersistentVertex(rs.getString(2), stmt);
                PersistentVertex inv = new PersistentVertex(rs.getString(3), stmt);
                String label = rs.getString(4);
                PersistentEdge edge = new PersistentEdge(outv, label, inv, stmt);
                arrayList.add(edge);
            }
            return arrayList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Collection<Edge> getEdges(String key, Object value) {
        try {
            ArrayList<Edge> arrayList = new ArrayList<>();
            String query = "";
            if (value.equals(false) | value.equals(true)) {
                query = "SELECT o, i, label, JSON_CONTAINS(properties,JSON_OBJECT('" + key + "'," + value + ")) FROM e;";
            } else {
                query = "SELECT o, i, label, JSON_CONTAINS(properties,JSON_OBJECT('" + key + "','" + value + "')) FROM e;";
            }
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getString(4).equals("1")) {
                    arrayList.add(new PersistentEdge(
                            new PersistentVertex(rs.getString(1), stmt),
                            rs.getString(2),
                            new PersistentVertex(rs.getString(2), stmt),
                            stmt));
                }
            }
            return arrayList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void shutdown() {

    }
}
