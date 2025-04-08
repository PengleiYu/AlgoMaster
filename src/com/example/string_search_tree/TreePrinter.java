package com.example.string_search_tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TreePrinter {
    public static void printTree(IPrintableNode root) {
        System.out.println("Root");

        IPrintableNode lastNode = getLastChild(root);
        for (int i = 0; i < root.getChildrenSize(); i++) {
            IPrintableNode child = root.getChild(i);
            printNode(child, i, "", child == lastNode);
        }
    }

    private static void printNode(@Nullable IPrintableNode node, int index, String prefix, boolean isLastChild) {
        if (node == null) return;

        String connector = isLastChild ? "└── " : "├── ";
        String value = node.getValue();
        System.out.println(prefix + connector + (char) index + (value == null ? "" : ":" + value));


        String newPrefix = prefix + (isLastChild ? "    " : "│   ");

        IPrintableNode lastNode = getLastChild(node);
        for (int i = 0; i < node.getChildrenSize(); i++) {
            IPrintableNode child = node.getChild(i);
            printNode(child, i, newPrefix, child == lastNode);
        }
    }

    @Nullable
    private static IPrintableNode getLastChild(@NotNull IPrintableNode node) {
        IPrintableNode lastNode = null;
        for (int i = node.getChildrenSize() - 1; i >= 0; i--) {
            if (node.getChild(i) != null) {
                lastNode = node.getChild(i);
                break;
            }
        }
        return lastNode;
    }

}