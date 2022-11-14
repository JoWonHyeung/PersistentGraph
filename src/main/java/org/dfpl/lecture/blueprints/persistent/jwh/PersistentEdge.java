package org.dfpl.lecture.blueprints.persistent.jwh;

import org.dfpl.lecture.revised.Direction;
import org.dfpl.lecture.revised.Edge;
import org.dfpl.lecture.revised.Vertex;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class PersistentEdge implements Edge {
    Connection connection;
    Statement stmt;
    Vertex outv;
    Vertex inv;
    String label;
    String id;

    public PersistentEdge(Vertex outv,String label,Vertex inv,Statement stmt) throws Exception {
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
        throw new IllegalArgumentException("Direction.BOTH is not allowed");
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void remove() {
        try{
            String query = "DELETE FROM e WHERE id = '" + id + "';";
            stmt.executeUpdate(query);
        }catch (Exception e){}
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Object getProperty(String key) {
        try {
            String json_path = "$." + key;
            String query = "SELECT JSON_VALUE(properties,'" + json_path + "') FROM e WHERE id = '" + id + "';";
            ResultSet rs = stmt.executeQuery(query); rs.next();
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
            String query = "SELECT JSON_KEYS(properties) FROM e WHERE id = '" + id + "';";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
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
            ResultSet rs = stmt.executeQuery("SELECT properties FROM e WHERE id = '" + id + "'"); rs.next();
            String jsonValue = rs.getString(1);
            if (jsonValue.equals("null")) {
                stmt.executeUpdate("UPDATE e SET properties = '" +
                        new JSONObject().put(key, value) + "' " + "WHERE id = '" + id + "'");
            } else {
                /* key 값 중복 check */
                JSONObject jsonObject = new JSONObject(jsonValue);
                if (jsonObject.keySet().contains(key)) return;
                /* table update */
                stmt.executeUpdate("UPDATE e SET properties = '" +
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
            String query = "SELECT JSON_VALUE(properties,'" + json_path + "') FROM e WHERE id = '" + id + "';";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            if (rs.getString(1) == null) return null;
            JSONObject result = new JSONObject().put(key, rs.getString(1));
            query = "SELECT JSON_REMOVE(properties,'" + json_path + "') FROM e WHERE id = '" + id + "';";
            rs = stmt.executeQuery(query);
            rs.next();
            stmt.executeUpdate("UPDATE e SET properties = '" + rs.getObject(1) + "' WHERE id = '" + id + "';");
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return id;
    }

    public String getEdgeId(Vertex outv, String label, Vertex inv) {
        return outv.getId() + label + inv.getId();
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
