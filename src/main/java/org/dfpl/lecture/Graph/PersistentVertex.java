package org.dfpl.lecture.Graph;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PersistentVertex implements Vertex {
    private String id;
    private Statement stmt;

    public PersistentVertex(String id, Statement stmt) {
        this.id = id;
        this.stmt = stmt;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Object getProperty(String key) {
        try {
            String json_path = "$." + key;
            String query = "SELECT JSON_VALUE(properties,'" + json_path + "') FROM v WHERE id = '" + id + "';";
            ResultSet rs = stmt.executeQuery(query); rs.next();
            /* type check */
            if(isInt(rs.getString(1))){
                return Integer.parseInt(rs.getString(1));
            }else if(isBoolean(rs.getString(1))){
                return Boolean.parseBoolean(rs.getString(1));
            }else if(isDouble(rs.getString(1))){
                return Double.parseDouble(rs.getString(1));
            }else{
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Set<String> getPropertyKeys() {
        try {
            String query = "SELECT JSON_KEYS(properties) FROM v WHERE id = '" + id + "';";
            ResultSet rs = stmt.executeQuery(query); rs.next();
            if (rs.getString(1) == null) return null;
            JSONArray jsonArray = new JSONArray(rs.getString(1));
            Set<String> keys = new HashSet<>();
            jsonArray.forEach(e -> keys.add(e.toString()));
            return keys;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void setProperty(String key, Object value) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT properties FROM v WHERE id = '" + id + "'"); rs.next();
            String jsonValue = rs.getString(1);
            if (jsonValue.equals("null")) {
                stmt.executeUpdate("UPDATE v SET properties = '" +
                        new JSONObject().put(key, value.toString()) + "' " + "WHERE id = '" + id + "'");
            } else {
                /* key 값 중복 check */
                JSONObject jsonObject = new JSONObject(jsonValue);
                if (jsonObject.keySet().contains(key)) return;
                /* table update */
                stmt.executeUpdate("UPDATE v SET properties = '" +
                        jsonObject.put(key, value.toString()) + "' " + "WHERE id = '" + id + "'");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Object removeProperty(String key) {
        try {
            String json_path = "$." + key;
            String query = "SELECT JSON_VALUE(properties,'" + json_path + "') FROM v WHERE id = '" + id + "';";
            ResultSet rs = stmt.executeQuery(query); rs.next();
            if (rs.getString(1) == null) return null;
            JSONObject result = new JSONObject().put(key, rs.getString(1));
            query = "SELECT JSON_REMOVE(properties,'" + json_path + "') FROM v WHERE id = '" + id + "';";
            rs = stmt.executeQuery(query);
            rs.next();
            stmt.executeUpdate("UPDATE v SET properties = '" + rs.getObject(1) + "' WHERE id = '" + id + "';");
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
                            arrayList.add(new PersistentEdge(
                                    new PersistentVertex(rs.getString(1), stmt),
                                    label,
                                    new PersistentVertex(rs.getString(2), stmt),
                                    stmt));
                        }
                    }
                    return arrayList;
                }
                String query = "SELECT o, i, label FROM e WHERE o = '" + id + "';";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    arrayList.add(new PersistentEdge(
                            new PersistentVertex(rs.getString(1), stmt),
                            rs.getString(3),
                            new PersistentVertex(rs.getString(2), stmt),
                            stmt));
                }
                return arrayList;
            } else if (direction.equals(Direction.IN)) {
                if (labels.length != 0) {
                    for (String label : labels) {
                        String query = "SELECT o, i FROM e WHERE label = '" + label + "' AND i = '" + id + "';";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            arrayList.add(new PersistentEdge(
                                    new PersistentVertex(rs.getString(1), stmt),
                                    label,
                                    new PersistentVertex(rs.getString(2), stmt),
                                    stmt));
                        }
                    }
                    return arrayList;
                }
                String query = "SELECT o, i, label FROM e WHERE i = '" + id + "';";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    arrayList.add(new PersistentEdge(
                            new PersistentVertex(rs.getString(1), stmt),
                            rs.getString(3),
                            new PersistentVertex(rs.getString(2), stmt),
                            stmt));
                }
                return arrayList;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
                            arrayList.add(new PersistentVertex(rs.getString(1), stmt));
                        }
                    }
                    return arrayList;
                }
                String query = "SELECT i FROM e WHERE o = '" + id + "';";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    arrayList.add(new PersistentVertex(rs.getString(1), stmt));
                }
                return arrayList;
            } else if (direction.equals(Direction.IN)) {
                if (labels.length != 0) {
                    for (String label : labels) {
                        String query = "SELECT o FROM e WHERE label = '" + label + "' AND i = '" + id + "';";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            arrayList.add(new PersistentVertex(rs.getString(1), stmt));
                        }
                    }
                    return arrayList;
                }
                String query = "SELECT o FROM e WHERE i = '" + id + "';";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    arrayList.add(new PersistentVertex(rs.getString(1), stmt));
                }
                return arrayList;
            } else if (direction.equals(Direction.BOTH)){

            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Collection<Vertex> getVertices(Direction direction, String key, Object value, String... labels) throws IllegalArgumentException {
//        try {
//            ArrayList<Vertex> arrayList = new ArrayList<>();
//            if (direction.equals(Direction.OUT)) {
//                for (String label : labels) {
//                    /* key, value check */
//                    JSONObject jsonObject = new JSONObject().put(key, value);
//                    String query = "SELECT id FROM v WHERE id = (SELECT i FROM e WHERE label = '" + label + "' AND o = '" + id + "');";
//                    ResultSet rs = stmt.executeQuery(query);
//                    while (rs.next()) {
//                        arrayList.add(new PersistentVertex(graph, rs.getString(1), stmt));
//                    }
//                }
//                return arrayList;
//            } else if (direction.equals(Direction.IN)) {
//                for (String label : labels) {
//                    /* key, value check */
//                    JSONObject jsonObject = new JSONObject().put(key, value);
//                    String query = "SELECT id FROM v WHERE id = (SELECT o FROM e WHERE label = '" + label + "' AND i = '" + id + "');";
//                    ResultSet rs = stmt.executeQuery(query);
//                    while (rs.next()) {
//                        arrayList.add(new PersistentVertex(graph, rs.getString(1), stmt));
//                    }
//                }
//                return arrayList;
//            }
//        } catch (Exception e) {
//        }
        return null;
    }

    @Override
    public void remove() {
        try {
            /* vertex delete */
            String query = "DELETE FROM v WHERE id = '" + id + "';";
            stmt.executeUpdate(query);

            /* edge delete */
            query = "DELETE FROM e WHERE (o = '" + id + "' OR i = '" + id + "');";
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return id;
    }

    public static boolean isInt(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isBoolean(String strNum) {
        if(Boolean.parseBoolean(strNum)) return Boolean.TRUE;
        else return Boolean.FALSE;
    }

    public static boolean isDouble(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double d = Double.parseDouble(strNum);
        } catch (Exception nfe) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.id.equals(obj.toString())){
            return true;
        }else{
            return false;
        }
    }

}
