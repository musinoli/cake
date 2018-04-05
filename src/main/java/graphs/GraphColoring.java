package graphs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://www.interviewcake.com/question/java/graph-coloring
 */
class GraphColoring {

    // O(N+M) time where N is the no. of nodes and M is the number of edges (as we check all neighbours)
    // O(D) space where D is the maximum degree for a node (as we store all the neighbours' colors)
    static void color(GraphNode[] nodes, String[] colors) {
        for (GraphNode node : nodes) {
            exitIfLoop(node);
            Set<String> alreadyUsedColors = node.getNeighbours().stream()
                    .filter(GraphNode::hasColor).map(GraphNode::getColor)
                    .collect(Collectors.toSet());
            for (String color : colors) {
                if (!alreadyUsedColors.contains(color)) {
                    node.setColor(color);
                    break;
                }
            }
        }
    }

    private static void exitIfLoop(GraphNode node) {
        if (node.getNeighbours().contains(node)) {
            throw new UnsupportedOperationException(
                    String.format("Graph coloring cannot be implemented for node with loop: %s.", node.getValue()));
        }
    }

    private class GraphNode {
        private int value;
        private Set<GraphNode> neighbours;
        private String color;

        GraphNode(int value) {
            this.value = value;
            neighbours = new HashSet<>();
        }

        int getValue() {
            return value;
        }

        Set<GraphNode> getNeighbours() {
            return Collections.unmodifiableSet(neighbours);
        }

        void addNeighbour(GraphNode node) {
            neighbours.add(node);
        }

        boolean hasColor() {
            return color != null;
        }

        String getColor() {
            return color;
        }

        void setColor(String color) {
            this.color = color;
        }
    }
}
