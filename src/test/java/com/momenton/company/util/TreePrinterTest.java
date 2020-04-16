package com.momenton.company.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TreePrinterTest {
    @Mock
    PrintStream printStream;

    @Test
    void testPrintTreeWithNull() {
        TreePrinter<Integer> treePrinter = new TreePrinter<>(null);
        treePrinter.print(printStream);
        Mockito.verifyNoMoreInteractions(printStream);
    }


    @Test
    void testPrintTreeWithRootOnly() {
        Tree<Integer> tree = new Tree<>(100);
        TreePrinter<Integer> treePrinter = new TreePrinter<>(tree);
        treePrinter.print(printStream);
        Mockito.verify(printStream).print("100\n");
    }

    @Test
    void testPrintTreeWithDepth() {
        Tree<Integer> tree = new Tree<>(100);
        tree.getRoot().addChild(200);
        tree.getRoot().addChild(500);
        tree.getRoot().getChildren().get(0).addChild(300);
        tree.getRoot().getChildren().get(0).addChild(400);
        TreePrinter<Integer> treePrinter = new TreePrinter<>(tree);
        treePrinter.print(printStream);
        Mockito.verify(printStream).print("100\n\t200\n\t\t300\n\t\t400\n\t500\n");
    }

}