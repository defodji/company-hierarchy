package com.momenton.company.util;


import java.io.PrintStream;
import java.util.stream.IntStream;

public class TreePrinter<T> {
    private Tree<T> tree;

    public TreePrinter(Tree<T> tree) {
        this.tree = tree;
    }

    public void print(PrintStream ps) {
        if (tree != null) {
            StringBuilder output = new StringBuilder();
            traversePreOrder(output, this.tree.getRoot(), 0);
            ps.print(output.toString());
        }
    }

    private void traversePreOrder(StringBuilder output, Node<T> currentNode, int nodeDepth) {
        if (currentNode != null) {
            IntStream.range(0, nodeDepth).forEach(i -> output.append("\t"));
            output.append(currentNode.getValue().toString());
            output.append("\n");

            currentNode.getChildren().forEach(n -> traversePreOrder(output, n, nodeDepth + 1));
        }
    }
}
