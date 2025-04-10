package com.example.string_search_tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TreePrinter {
    public static void printTree(@Nullable IPrintableNode root) {
        if (root == null) return;
        System.out.println(getDesc(root, -1));

        printChildren(root, "");
    }

    private static void printNode(@Nullable IPrintableNode node, int indexInParent, String prefix, boolean isLastChild) {
        if (node == null) return;

        // TODO: 2025/4/10 三向查找树这里有问题
        String connector = isLastChild ? "└── " : "├── ";
        System.out.println(prefix + connector + getDesc(node, indexInParent));


        String newPrefix = prefix + (isLastChild ? "    " : "│   ");

        printChildren(node, newPrefix);
    }

    private static void printChildren(@NotNull IPrintableNode node, String newPrefix) {
//        System.out.println("printChildren() called with: node = [" + node.getName(-1) + "], newPrefix = [" + newPrefix + "]");
        IPrintableNode lastNode = getLastChild(node);
        for (int i = 0; i < node.getChildrenSize(); i++) {
            IPrintableNode child = node.getChild(i);
            boolean isLastChild = lastNode != null ? child == lastNode : i == node.getChildrenSize() - 1;
            printNode(child, i, newPrefix, isLastChild);
        }
    }

    @NotNull
    private static String getDesc(@NotNull IPrintableNode node, int indexInParent) {
        String value = node.getValue();
        return node.getName(indexInParent) + (value == null ? "" : ":" + value);
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