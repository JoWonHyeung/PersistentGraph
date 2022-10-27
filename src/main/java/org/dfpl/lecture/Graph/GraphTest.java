package org.dfpl.lecture.Graph;

public class GraphTest {
    public static void main(String[] args) throws Exception{
        String id = "root";
        String pwd = "dnjsgud@12";
        String dbName = "testDB";
        String port = "3307";

        PersistentGraph g = new PersistentGraph(id,pwd,dbName,"test",port);

        /* addVertex test */
        System.out.println("[addVertex test]");
        Vertex v1 = g.addVertex("2");
        System.out.println(v1);

        /* getVertex test*/
        System.out.println("[getVertex test]");
        Vertex v2 = g.getVertex("2");
        System.out.println(v2);
    }
}
