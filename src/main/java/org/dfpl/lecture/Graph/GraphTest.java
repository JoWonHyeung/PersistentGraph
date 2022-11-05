package org.dfpl.lecture.Graph;

import java.util.ArrayList;

public class GraphTest {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";
        String dbName = "testDB1";
        String port = "3307";

        PersistentGraph g1 = new PersistentGraph(id, pwd, dbName, port);

        /* addVertex test */
        System.out.println("[addVertex test]");
        Vertex v1 = g1.addVertex("1");
        Vertex v2 = g1.addVertex("2");
        Vertex v3 = g1.addVertex("3");
        Vertex v4 = g1.addVertex("4");
//        Vertex v4 = g2.addVertex("2");
//        Vertex v5 = g2.addVertex("3");
//
        System.out.println("v1 = " + v1);
        System.out.println("v2 = " + v2);
        System.out.println("v3 = " + v3);
//
//        /* getVertex test*/
        System.out.println("[getVertex test]");
        Vertex v1_get = g1.getVertex("1");
        Vertex v2_get = g1.getVertex("2");
        Vertex v3_get = g1.getVertex("3");
        Vertex v4_get = g1.getVertex("4");
        System.out.println("v1 = " + v1_get);
        System.out.println("v2 = " + v2_get);
        System.out.println("v3 = " + v3_get);
//
        /* Delete Vertex test*/
        System.out.println("[delete vertex test]");
//        g1.removeVertex(v2);

        /* getVertices test*/
        System.out.println("[getVertices test]");
        System.out.println(g1.getVertices());
//        System.out.println(g2.getVertices());
        System.out.println("g1 vertex size = " + g1.getVertices().size());
//        System.out.println("g2 vertex size = " + g2.getVertices().size());

        /* addEdge test */
        System.out.println("[addEdge test]");
        Edge e1 = g1.addEdge(v1_get, v2_get, "loves");
        Edge e2 = g1.addEdge(v1_get, v3_get, "likes");
        Edge e3 = g1.addEdge(v1_get, v4_get, "likes");
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);

        /* getEdge1 test */
        System.out.println("[getEdge1 test]");
        System.out.println(g1.getEdge(v1_get, v2_get, "loves"));

        /* getEdge2 test */
        System.out.println("[getEdge2 test]");
        System.out.println(g1.getEdge(v1_get.getId() + "loves" + v2_get.getId()));

        /* remove Edge test */
//        System.out.println("[remote edge]");
//        g1.removeEdge(e1);

        /* edges test */
        System.out.println("[getEdges test]");
        System.out.println(g1.getEdges());
        ;

        /* PersistentEdge getEdge test */
        System.out.println("[PersistentEdge getEdge test]");
        System.out.println(e1.getVertex(Direction.IN));

        /* delete edge */
//        System.out.println("[delete edge test]");
//        e1.remove();
//        System.out.println("g1 edge size : " + g1.getEdges().size());

        /* delete vertex */
//        System.out.println("[delete vertex]");
//        v1.remove();

        /* Vertex getVertices */
        System.out.println("[vertex getVertices test1]");
        System.out.println(v1.getVertices(Direction.OUT,"likes","loves"));

        /* Vertex getVertices */
        System.out.println("[vertex getVertices test2]");
        System.out.println(v2.getVertices(Direction.IN,"loves"));

        /* Vertex getEdges */
        System.out.println("[vertex getEdges test]");
        System.out.println((v1.getEdges(Direction.OUT,"likes")));

        /* Edge setProperties */
        System.out.println("[edge setProperties test]");
        e1.setProperty("name","jo");

        /* Edge setProperties */
        System.out.println("[edge setProperties test]");
        e1.setProperty("name","jo");

        /* Vertex setProperties */
        System.out.println("[vertex setProperties test]");
        v1.setProperty("name","jo");
        v1.setProperty("name2","jo2");
        v1.setProperty("name3","jo2");
    }
}
