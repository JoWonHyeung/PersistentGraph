package org.dfpl.lecture.Graph;


import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

public class P1 {
    public static void main(String[] args) {
        Graph g = new TinkerGraph();

        Vertex a = g.addVertex("A");
        Vertex b = g.addVertex("B");
        Vertex c = g.addVertex("C");

        Edge aLikesb = g.addEdge("A|likes|B", a, b, "likes");
        Edge aLovesb = g.addEdge("A|loves|B",a,b,"loves");
        Edge aLikesc = g.addEdge("A|likes|C",a,c,"likes");
        Edge cLikesc = g.addEdge("C|likes|C",c,c,"likes");

        Iterable<Vertex> iterable = g.getVertices();
        Iterator<Vertex> iterator = iterable.iterator();

//        while(iterator.hasNext()){
//            Vertex v = iterator.next();
//            System.out.println(v);
//        }
//
//
//        Iterable<Vertex> iterable2 = g.getVertices();
//        for(Vertex v: iterable2){
//            System.out.println(v);
//        }
//
//        g.getVertices().forEach(new Consumer<Vertex>() {
//            @Override
//            public void accept(Vertex vertex) {
//                System.out.println(vertex);
//            }
//        });
//
//        System.out.println("--------------");
//
//        for(Vertex v: a.getVertices(Direction.OUT,"likes","loves")){
//            System.out.println(v);
//        }
//
//        System.out.println("---------------");
//
//        System.out.println(aLikesc.getLabel());
//
//        System.out.println("---------------");
//
//        //aLikesc.remove();
//
//        for(Edge e: g.getEdges()){
//            System.out.println(e);
//        }
//
//        System.out.println("---------------");
//        for(Edge e: a.getEdges(Direction.OUT)){
//            System.out.println(e.getVertex(Direction.IN));
//        }
//
//        System.out.println("---------------");

//        Set<String> keySet = a.getPropertyKeys();
//
//        for(String key: keySet){
//            System.out.println(key + "->" + a.getProperty(key));
//        }

    }
}
