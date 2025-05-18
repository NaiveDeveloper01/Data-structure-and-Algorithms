import java.util.List;
import java.util.Map;

public class Driver {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = Map.of(
                0, List.of(1),
                1, List.of(2),
                2, List.of(0)
        );

        Gabows gabows = new Gabows();
        System.out.println(gabows.getSccList(graph).toString());

    }
}
