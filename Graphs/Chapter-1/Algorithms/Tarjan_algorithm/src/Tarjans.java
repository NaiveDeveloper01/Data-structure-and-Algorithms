import java.util.*;

public class Tarjans {
    private List<List<Integer>> sccList;
    private Map<Integer, Integer> inTimeManger;
    private Map<Integer, Integer> outTimeManager;
    private Stack<Integer> stack;
    private Integer timeStamp = 0;

    public Tarjans() {
        this.sccList = new LinkedList<>();
        this.inTimeManger = new HashMap<>();
        this.outTimeManager = new HashMap<>();
        this.stack = new Stack<>();
    }

    public List<List<Integer>> getRootsForScc(Map<Integer, List<Integer>> graph) {
        for (Integer key : graph.keySet()) {
            if (!inTimeManger.containsKey(key)) {
                Dfs(key, graph);
            }
        }
        return this.sccList;
    }

    private void Dfs(Integer parent, Map<Integer, List<Integer>> graph) {
        inTimeManger.put(parent, timeStamp);
        outTimeManager.put(parent, timeStamp);
        stack.push(parent);
        this.timeStamp++;
        for (Integer child : graph.getOrDefault(parent, Collections.emptyList())) {
            if (!inTimeManger.containsKey(child)) {
                Dfs(child, graph);
                outTimeManager.put(parent, Math.min(outTimeManager.get(parent), outTimeManager.get(child)));
            } else if (stack.contains(child)) {
                outTimeManager.put(parent, Math.min(outTimeManager.get(parent), outTimeManager.get(child)));
            }
        }
        if (outTimeManager.get(parent).compareTo(inTimeManger.get(parent)) == 0) {
            Integer removed;
            List<Integer> list = new LinkedList<>();
            do {
                removed = stack.pop();
                list.add(removed);
            } while (!removed.equals(parent));
            this.sccList.add(list);
        }
    }
}
