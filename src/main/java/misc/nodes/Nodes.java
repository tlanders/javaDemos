package misc.nodes;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by tlanders on 6/4/2016.
 */
public class Nodes {
    public static void main(String [] args) throws Exception {
        System.out.println("Nodes starting...");

        Files.lines(Paths.get(args[0])).map(str -> {
//            System.out.println("line: " + str);
            String [] lineSplit = str.split(" ");
            List<Map<Integer, String>> nodes = new ArrayList<Map<Integer, String>>();
            if(lineSplit.length > 1) {
                Map<Integer, String> n1 = new HashMap<>();
                n1.put(Integer.parseInt(lineSplit[0]), lineSplit[1]);
                Map<Integer, String> n2 = new HashMap<>();
                n2.put(Integer.parseInt(lineSplit[1]), lineSplit[0]);
                nodes.add(n1);
                nodes.add(n2);
            }
            return nodes;
        }).flatMap(List::stream)
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.counting())).entrySet().stream()
                .forEach(entry -> System.out.println("Node " + entry.getKey() + " has a degree of " + entry.getValue()));

        System.out.println("Nodes second method...");
        Files.lines(Paths.get(args[0])).skip(1)
            .map(str -> {
                String [] lineSplit = str.split(" ");
                List<Node<Integer, Integer>> nodes = new ArrayList<Node<Integer, Integer>>();
                Node<Integer,Integer> n = new Node<Integer, Integer>(new Integer(lineSplit[0]), new Integer(lineSplit[1]));
                nodes.add(n);
                nodes.add(new Node<Integer, Integer>(n.getY(), n.getX()));
                return nodes;
            })
            .flatMap(List::stream)
            .collect(Collectors.groupingBy(Node::getX, Collectors.counting()))
            .entrySet().stream()
            .forEach(result -> System.out.println("Node " + result.getKey() + " has a degree of " + result.getValue()));

        // dzone solution 1
        System.out.println("dzone first method...");
        Files.lines(Paths.get(args[0]))
                .skip(1)
                .flatMap(it -> Stream.of(it.split(" ")))
                .collect(Collectors.groupingBy(Integer::new, Collectors.counting()))
                .forEach((node, count) -> System.out.printf("Node %s has degree of %s\n", node, count));

        //System.out.println("res=" + res + ", class=" + res.getClass().getName());
        /*
        Files.lines(Paths.get(args[0])).forEach(line -> {
            String [] lineSplit = line.split(" ");
            System.out.println(line + " - " + lineSplit.length);
            if(lineSplit.length == 1) {
                // initialize nodes
                int totalNodes = Integer.parseInt(lineSplit[0]);
                nodes = (Set<String> []) new Object[totalNodes];
                for(int i = 0; i < totalNodes; i++) {
                    nodes[i] = new HashSet<String>();
                }
            }
        });
*/
        System.out.println("Nodes done.");
    }
}
