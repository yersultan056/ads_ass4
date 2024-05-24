import java.util.*;

public class Dfs {

    private Map<String, List<String>> graph;
    private Set<String> visited;

    public Dfs() {
        graph = new HashMap<>();
        visited = new HashSet<>();

        graph.put("A", Arrays.asList("C", "B", "D"));
        graph.put("B", Arrays.asList("A", "C", "E", "G"));
        graph.put("C", Arrays.asList("A", "B", "D"));
        graph.put("D", Arrays.asList("C", "A"));
        graph.put("E", Arrays.asList("G", "F", "B"));
        graph.put("F", Arrays.asList("G", "E"));
        graph.put("G", Arrays.asList("F", "B"));
    }

    public void dfs(String node) {
        visited.add(node);
        System.out.print(node + " ");

        for (String neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor);
            }
        }
    }

    public static void main(String[] args) {
        Dfs dfsExample = new Dfs();
        System.out.print("Order of nodes visited (DFS): ");
        dfsExample.dfs("A");
    }
}
