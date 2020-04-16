package com.momenton.company.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testGetValue() {
        Node<Integer> node = new Node<>(100);
        assertEquals(100, node.getValue());
    }


    @Test
    void testNoChild() {
        Node<Integer> node = new Node<>(100);
        assertTrue(node.getChildren().isEmpty());
    }

    @Test
    void testAddOneChild() {
        Node<Integer> node = new Node<>(100);
        node.addChild(200);
        assertEquals(1, node.getChildren().size());
        assertEquals(200, node.getChildren().get(0).getValue());
    }

    @Test
    void testAddManyChildren() {
        Node<Integer> node = new Node<>(100);
        node.addChild(200);
        node.addChild(300);
        node.addChild(400);
        assertEquals(3, node.getChildren().size());
        assertEquals(200, node.getChildren().get(0).getValue());
        assertEquals(300, node.getChildren().get(1).getValue());
        assertEquals(400, node.getChildren().get(2).getValue());
    }
}