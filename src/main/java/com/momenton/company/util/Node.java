package com.momenton.company.util;

import java.util.LinkedList;
import java.util.List;

public class Node<T> {
    private T value;
    private List<Node<T>> children;

    public Node(T value) {
        this.value = value;
        this.children = new LinkedList<>();
    }

    public void addChild(T value) {
        Node<T> node = new Node<>(value);
        this.children.add(node);
    }

    public T getValue() {
        return value;
    }

    public List<Node<T>> getChildren() {
        return children;
    }
}
