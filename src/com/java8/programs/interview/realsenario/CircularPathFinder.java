package com.java8.programs.interview.realsenario;

import java.util.*;
//DFS Cycle Detection
public class CircularPathFinder {
    private static Set<Integer> visited = new HashSet<>();
    private static Set<Integer> recursionStack = new HashSet<>();
    private static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {

        int[][] pairs = {
                {86, 60},
                {17, 2},
                {5, 2},
                {10, 34},
                {56, 2},
                {77, 41},
                {98, 18},
                {29, 95},
                {100, 5},
                {12, 73},
                {18, 10},
                {5, 58},
                {10, 70},
                {44, 70},
                {70, 18}
        };

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] pair : pairs) {
            graph.computeIfAbsent(pair[0], k -> new ArrayList<>())
                    .add(pair[1]);
        }

        for (Integer node : graph.keySet()) {
            if (dfs(node, graph)) {
                return;
            }
        }

        System.out.println("Circular Path Doesn't Exist");
    }

    private static boolean dfs(int node,
                               Map<Integer, List<Integer>> graph) {

        if (recursionStack.contains(node)) {

            int start = path.indexOf(node);

            System.out.print("Circular Path Exist: ");

            for (int i = start; i < path.size(); i++) {
                System.out.print(path.get(i) + ", ");
            }

            System.out.println(node);
            return true;
        }

        if (visited.contains(node)) {
            return false;
        }

        visited.add(node);
        recursionStack.add(node);
        path.add(node);

        for (Integer neighbour :
                graph.getOrDefault(node, Collections.emptyList())) {

            if (dfs(neighbour, graph)) {
                return true;
            }
        }

        recursionStack.remove(node);
        path.remove(path.size() - 1);

        return false;
    }
}
