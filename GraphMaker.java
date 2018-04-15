package com.company;

import java.util.*;
import java.util.logging.Level;

public class GraphMaker {
  static class Graph {
    Map<Node, LinkedList<Node>> adjListArr;

    Graph(ArrayList<Node> vertices){
      adjListArr = new HashMap<>();
      for (Node n:vertices) {
        adjListArr.put(n,new LinkedList<>());
      }
    }


  }

    static void addEdge(Node src,Node dest,Graph graph){
      graph.adjListArr.get(src).addLast(dest);
      graph.adjListArr.get(dest).addLast(src);

    }

     static void printGraph(Graph graph)
    {
      for (Map.Entry<Node,LinkedList<Node>> entry : graph.adjListArr.entrySet()) {
        System.out.print(entry.getKey().name+" ");
        for (Node n:entry.getValue()) {
          System.out.print(n.name + " ,");
        }
        System.out.println();
      }
    }

    static HashMap BFS(Graph graph,Node initialNode){
      LinkedList<Node> tempq = new LinkedList<>();
      LinkedList<Node> visited = new LinkedList<>();
      HashMap<Node,Integer> levels = new HashMap<>();
      tempq.addLast(initialNode);
      visited.addLast(initialNode);
      levels.put(initialNode,0);
      while(tempq.size() != 0){
        Node n = tempq.removeFirst();
        System.out.println(n.name);
          for(Node nod: graph.adjListArr.get(n)) {
            if(!visited.contains(nod)){     //if node isn`t visited
              levels.put(nod,levels.get(n)+1);
              tempq.addLast(nod);
              visited.addLast(nod);
            }
          }
        }

      for (HashMap.Entry<Node,Integer> entry: levels.entrySet()) {
        System.out.println(entry.getKey().name + " "+ entry.getValue());
      }

        return levels;

      }

      static HashMap NSP(Graph graph,Node initialNode,HashMap<Node,Integer> levels) {
        LinkedList<Node> tempq = new LinkedList<>();
        LinkedList<Node> visited = new LinkedList<>();
        HashMap<Node,Integer> numOfShortestPathOfaNode = new HashMap<>();
        numOfShortestPathOfaNode.put(initialNode,1);
        visited.addLast(initialNode);
        tempq.addLast(initialNode);
        while(tempq.size() != 0) {
          Node n = tempq.removeFirst();
          for (Node nod : graph.adjListArr.get(n)) {
            if(levels.get(nod) == (levels.get(n)+1) ){
              if(numOfShortestPathOfaNode.get(nod) == null){
                numOfShortestPathOfaNode.put(nod,0);
              }
              numOfShortestPathOfaNode.put(nod,numOfShortestPathOfaNode.get(nod)+numOfShortestPathOfaNode.get(n));
            }
            if(!visited.contains(nod)) {
              visited.addLast(nod);
              tempq.addLast(nod);
            }
          }
        }

        for (HashMap.Entry<Node,Integer> entry: numOfShortestPathOfaNode.entrySet()) {
          System.out.println(entry.getKey().name + " NSP "+ entry.getValue());
        }
        return numOfShortestPathOfaNode;
      }

      static HashMap AOF(Graph graph,HashMap<Node,Integer> numOfShortestPathOfaNode,HashMap<Node,Integer> levels){
        Map.Entry<Node, Integer> maxEntry = null;
        for (Map.Entry<Node,Integer> entry : levels.entrySet())
        {
          if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
          {
            maxEntry = entry;
          }
        }
        ArrayList<Node> temp = new ArrayList<>();
        Node initialNode = maxEntry.getKey();
        LinkedList<Node> tempq = new LinkedList<>();
        LinkedList<Node> visited = new LinkedList<>();
        HashMap<String,Float> result = new HashMap<>();
        HashMap<Node,Float> remainingForNode = new HashMap<>();
        visited.addLast(initialNode);
        tempq.addLast(initialNode);
        float cValue=0;
        float flow = 0;
        float remaining=0;
        while(tempq.size() != 0) {
          Node n = tempq.removeFirst();
          cValue=0;
          flow = 1;
          temp.clear();
          for (Node nod : graph.adjListArr.get(n)) {
            if(levels.get(nod) == (levels.get(n)-1) ){
                temp.add(nod);
                cValue += numOfShortestPathOfaNode.get(nod);
            }
            if(!visited.contains(nod)) {
              visited.addLast(nod);
              tempq.addLast(nod);
            }
          }
          for (Node node:temp) {
            if (remainingForNode.get(n) == null) {
              remainingForNode.put(n, 0.0f);
            }
            flow = remainingForNode.get(n) + 1;
            remaining = numOfShortestPathOfaNode.get(node) * flow / cValue;
            if (remainingForNode.get(node) == null) {
              remainingForNode.put(node, 0.0f);
            }
            remainingForNode.put(node, remainingForNode.get(node) + remaining);
            if (node.name.compareTo(n.name) < 0) {
              result.put(node.name + n.name, remaining);
            }
            else {
              result.put(n.name + node.name, remaining);
            }
          }

        }

        for (HashMap.Entry<String,Float> entry: result.entrySet()) {
          System.out.println(entry.getKey() + " AOF "+ entry.getValue());
        }
          return result;
      }

  }




