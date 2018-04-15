package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static com.company.GraphMaker.*;

public class Main {



    public static void main(String[] args) {
        String s ="";
        Graph graph= null;
        ArrayList<Node> vertices = new ArrayList<>();
        ArrayList<String> edges = new ArrayList<>();
        ArrayList<HashMap<String,Float>> allRes = new ArrayList<>();
     /*   Scanner scanner = new Scanner(System.in);
        while(!s.equals("quit")) {
            s = scanner.nextLine();
            vertices.add(new Node(s));
            graph = new Graph(vertices);

         }*/


        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");


        vertices.add(a);
        vertices.add(b);
        vertices.add(c);
        vertices.add(d);
        vertices.add(e);
        vertices.add(f);



        graph = new Graph(vertices);
        addEdge(a,b,graph);
        addEdge(a,d,graph);
        addEdge(b,c,graph);
        addEdge(c,f,graph);
        addEdge(d,e,graph);
        addEdge(b,e,graph);
        addEdge(e,f,graph);

        edges.add("ab");
        edges.add("ad");
        edges.add("bc");
        edges.add("cf");
        edges.add("de");
        edges.add("be");
        edges.add("ef");
        //input the edges alphabetically correct

        for (Node n:vertices) {
            HashMap<Node,Integer> levels = BFS(graph,n);
            HashMap<Node,Integer> NoN = NSP(graph,n,levels);
            HashMap<String,Float> aof  = AOF(graph,NoN,levels);
            allRes.add(aof);
        }

        HashMap<String,Float> sums = new HashMap<>();
        for (HashMap<String,Float> res:allRes) {
            for (String edge: edges) {
                if(res.get(edge)!=null) {
                    if (sums.get(edge) == null) {
                        sums.put(edge,0f);
                    }
                        sums.put(edge, sums.get(edge) + res.get(edge));

                }
            }
        }

        for (HashMap.Entry<String,Float> entry: sums.entrySet()) {
            System.out.println(entry.getKey() + " res "+ entry.getValue()/2);
        }

    }


}
