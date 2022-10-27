package org.dfpl.lecture.Graph;

public class GraphTest {
    public static void main(String[] args) throws Exception{
        String id = "root";
        String pwd = "dnjsgud@12";
        String dbName = "testDB";
        String port = "3307";

        PersistentGraph g = new PersistentGraph(id,pwd,dbName,"test",port);
        Vertex v = g.addVertex("1");
        System.out.println(v);
    }
}
