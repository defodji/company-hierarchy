package com.momenton.company.util;

public class Tree<T> {
    private final Node<T> root;

    public Tree(T value) {
        this.root = new Node<>(value);
    }

    public Node<T> getRoot() {
        return root;
    }

}
