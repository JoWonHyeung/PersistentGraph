package org.dfpl.lecture.Graph;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Set;

public class PersistentEdge implements Edge {
    Connection connection;
    Statement stmt;
    Graph graph;
    Vertex outv;
    Vertex inv;
    String label;
    String id;

    public PersistentEdge(Graph graph, Vertex outv,String label,Vertex inv,Statement stmt) throws Exception{
        this.graph = graph;
        this.outv = outv;
        this.inv = inv;
        this.label = label;
        this.stmt = stmt;
        this.id = getEdgeId(outv, label, inv);
    }

    @Override
    public Vertex getVertex(Direction direction) throws IllegalArgumentException {
        if(direction.equals(Direction.OUT)){
            return outv;
        }
        else if(direction.equals(Direction.IN)){
            return inv;
        }
        return null;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void remove() {
        try{
            String query = "DELETE FROM e WHERE id = '" + id + "' AND g = '" + graph + "';";
            stmt.executeUpdate(query);
        }catch (Exception e){}
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Object getProperty(String key) {
        return null;
    }

    @Override
    public Set<String> getPropertyKeys() {
        return null;
    }

    @Override
    public void setProperty(String key, Object value) {

    }

    @Override
    public Object removeProperty(String key) {
        return null;
    }

    @Override
    public String toString() {
        return id;
    }

    public String getEdgeId(Vertex outv, String label, Vertex inv) {
        return outv.getId() + label + inv.getId();
    }
}
