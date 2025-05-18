import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Tarjans t = new Tarjans();

        /*Map<Integer, List<Integer>> graph = new HashMap<>();*/
        Map<Integer, List<Integer>> graph = Map.of(
                0, List.of(1),
                1, List.of(0),
                2, List.of(3),
                3, List.of(4),
                4, List.of()
        );


        System.out.println(t.getRootsForScc(graph).toString());
    }
}
