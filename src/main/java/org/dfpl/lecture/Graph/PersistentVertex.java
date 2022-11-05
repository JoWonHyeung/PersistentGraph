package org.dfpl.lecture.Graph;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class PersistentVertex implements Vertex {
    private String id;
    private Graph graph;
    private Statement stmt;

    public PersistentVertex(Graph graph, String id, Statement stmt) {
        this.id = id;
        this.graph = graph;
        this.stmt = stmt;
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
        try {
            ResultSet rs = stmt.executeQuery("SELECT properties FROM v WHERE id = '" + id + "'"); rs.next();
            String jsonValue = rs.getString(1);
            if(jsonValue.equals("null")){
                stmt.executeUpdate("UPDATE v SET properties = '" +
                        new JSONObject().put(key, value) + "' " + "WHERE id = '" + id + "'");
            }else{
                /* key 값 중복 check */
                JSONObject jsonObject = new JSONObject(jsonValue);
                if(jsonObject.keySet().contains(key)) return;
                /* table update */
                stmt.executeUpdate("UPDATE v SET properties = '" +
                        jsonObject.put(key, value) + "' " + "WHERE id = '" + id + "'");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Object removeProperty(String key) {
        return null;
    }

    @Override
    public Collection<Edge> getEdges(Direction direction, String... labels) throws IllegalArgumentException {
        try {
            ArrayList<Edge> arrayList = new ArrayList<>();
            if (direction.equals(Direction.OUT)) {
                if (labels.length != 0) {
                    for (String label : labels) {
                        String query = "SELECT o, i FROM e WHERE label = '" + label + "' AND o = '" + id + "';";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            arrayList.add(new PersistentEdge(graph,
                                    new PersistentVertex(graph, rs.getString(1), stmt),
                                    label,
                                    new PersistentVertex(graph, rs.getString(2), stmt),
                                    stmt));
                        }
                    }
                    return arrayList;
                }
                String query = "SELECT o, i, label FROM e WHERE o = '" + id + "';";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    arrayList.add(new PersistentEdge(graph,
                            new PersistentVertex(graph, rs.getString(1), stmt),
                            rs.getString(3),
                            new PersistentVertex(graph, rs.getString(2), stmt),
                            stmt));
                }
                return arrayList;
            } else if (direction.equals(Direction.IN)) {
                if (labels.length != 0) {
                    for (String label : labels) {
                        String query = "SELECT o, i FROM e WHERE label = '" + label + "' AND i = '" + id + "';";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            arrayList.add(new PersistentEdge(graph,
                                    new PersistentVertex(graph, rs.getString(1), stmt),
                                    label,
                                    new PersistentVertex(graph, rs.getString(2), stmt),
                                    stmt));
                        }
                    }
                    return arrayList;
                }
                String query = "SELECT o, i, label FROM e WHERE i = '" + id + "';";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    arrayList.add(new PersistentEdge(graph,
                            new PersistentVertex(graph, rs.getString(1), stmt),
                            rs.getString(3),
                            new PersistentVertex(graph, rs.getString(2), stmt),
                            stmt));
                }
                return arrayList;
            }
        } catch (Exception e) {
        }
        return null;
    }


    @Override
    public Collection<Vertex> getVertices(Direction direction, String... labels) throws IllegalArgumentException {
        try {
            ArrayList<Vertex> arrayList = new ArrayList<>();
            if (direction.equals(Direction.OUT)) {
                if (labels.length != 0) {
                    for (String label : labels) {
                        String query = "SELECT i FROM e WHERE label = '" + label + "' AND o = '" + id + "';";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            arrayList.add(new PersistentVertex(graph, rs.getString(1), stmt));
                        }
                    }
                    return arrayList;
                }
                String query = "SELECT i FROM e WHERE o = '" + id + "';";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    arrayList.add(new PersistentVertex(graph, rs.getString(1), stmt));
                }
                return arrayList;
            } else if (direction.equals(Direction.IN)) {
                if (labels.length != 0) {
                    for (String label : labels) {
                        String query = "SELECT o FROM e WHERE label = '" + label + "' AND i = '" + id + "';";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            arrayList.add(new PersistentVertex(graph, rs.getString(1), stmt));
                        }
                    }
                    return arrayList;
                }
                String query = "SELECT o FROM e WHERE i = '" + id + "';";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    arrayList.add(new PersistentVertex(graph, rs.getString(1), stmt));
                }
                return arrayList;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Collection<Vertex> getVertices(Direction direction, String key, Object value, String... labels) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void remove() {
        try {
            /* vertex delete */
            String query = "DELETE FROM v WHERE id = '" + id + "' AND g = '" + graph + "';";
            System.out.println(query);
            stmt.executeUpdate(query);

            /* edge delete */
            query = "DELETE FROM e WHERE (o = '" + id + "' OR i = '" + id + "') AND g = '" + graph + "';";
            System.out.println(query);
            stmt.executeUpdate(query);
        } catch (Exception e) {
        }
    }

    @Override
    public String toString() {
        return id;
    }
}
