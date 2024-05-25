package t4;

import java.util.*;

public class Task4 {

    static class Edge {
        String destination;
        int weight;

        Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        String name;
        int distance;

        Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static Map<String, Integer> dijkstra(Map<String, List<Edge>> graph, String start, Map<String, String> previousNodes) {
        Map<String, Integer> distances = new HashMap<>();
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(start, 0));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();

            for (Edge edge : graph.get(currentNode.name)) {
                int newDist = distances.get(currentNode.name) + edge.weight;
                if (newDist < distances.get(edge.destination)) {
                    distances.put(edge.destination, newDist);
                    priorityQueue.add(new Node(edge.destination, newDist));
                    previousNodes.put(edge.destination, currentNode.name);
                }
            }
        }

        return distances;
    }

    public static List<String> getShortestPath(Map<String, String> previousNodes, String end) {
        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Map<String, List<Edge>> graph = new HashMap<>();
        graph.put("Edinburgh", Arrays.asList(new Edge("Stirling", 50), new Edge("Perth", 100)));
        graph.put("Stirling", Arrays.asList(new Edge("Edinburgh", 50), new Edge("Glasgow", 50), new Edge("Perth", 40)));
        graph.put("Glasgow", Collections.singletonList(new Edge("Stirling", 50)));
        graph.put("Perth", Arrays.asList(new Edge("Stirling", 40), new Edge("Dundee", 60)));
        graph.put("Dundee", Collections.singletonList(new Edge("Perth", 60)));

        String start = "Edinburgh";
        String end = "Dundee";

        Map<String, String> previousNodes = new HashMap<>();
        Map<String, Integer> distances = dijkstra(graph, start, previousNodes);
        List<String> path = getShortestPath(previousNodes, end);

        System.out.println("Shortest distance from " + start + " to " + end + ": " + distances.get(end));
        System.out.println("Path: " + String.join(" -> ", path));
    }
}
