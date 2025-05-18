import java.util.*;

public class Gabows {
    private Stack<Integer> trackingStack;
    private Stack<Integer> possibleRootStack;
    private Set<Integer> visited;
    private final List<List<Integer>> sccList;

    public Gabows() {
        this.possibleRootStack = new Stack<>();
        this.trackingStack = new Stack<>();
        this.visited = new HashSet<>();
        this.sccList = new LinkedList<>();
    }

    public List<List<Integer>> getSccList(Map<Integer, List<Integer>> graph) {
        for (Integer key : graph.keySet()) {
            if (!visited.contains(key)) {
                gabowsDfs(key, graph);
            }
        }
        return sccList;
    }

    private void gabowsDfs(Integer parent, Map<Integer, List<Integer>> graph) {
        trackingStack.push(parent);
        possibleRootStack.push(parent);
        visited.add(parent);

        for (Integer child : graph.getOrDefault(parent, Collections.emptyList())) {
            if (!visited.contains(child)) {
                gabowsDfs(child, graph);
            } else if (this.trackingStack.contains(child)) {
                while (!possibleRootStack.peek().equals(child)) {
                    possibleRootStack.pop();
                }
            }
        }

        if (parent.equals(possibleRootStack.peek())) {
            Integer removed;
            List<Integer> component = new LinkedList<>();
            do {
                removed = trackingStack.pop();
                component.add(removed);
            } while (!removed.equals(parent));
            sccList.add(component);
            possibleRootStack.pop();
        }
    }

}
