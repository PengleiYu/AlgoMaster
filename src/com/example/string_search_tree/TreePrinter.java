package com.example.string_search_tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TreePrinter {
    public static void printTree(ITrieNode root) {
        System.out.println("Root");

        ITrieNode lastNode = getLastChild(root);
        for (int i = 0; i < root.getChildrenSize(); i++) {
            ITrieNode child = root.getChild(i);
            printNode(child, i, "", child == lastNode);
        }
    }

    private static void printNode(@Nullable ITrieNode node, int index, String prefix, boolean isLastChild) {
        if (node == null) return;

        String connector = isLastChild ? "└── " : "├── ";
        System.out.println(prefix + connector + (char) index);


        String newPrefix = prefix + (isLastChild ? "    " : "│   ");

        ITrieNode lastNode = getLastChild(node);
        for (int i = 0; i < node.getChildrenSize(); i++) {
            ITrieNode child = node.getChild(i);
            printNode(child, i, newPrefix, child == lastNode);
        }
    }

    @Nullable
    private static ITrieNode getLastChild(@NotNull ITrieNode node) {
        ITrieNode lastNode = null;
        for (int i = node.getChildrenSize() - 1; i >= 0; i--) {
            if (node.getChild(i) != null) {
                lastNode = node.getChild(i);
                break;
            }
        }
        return lastNode;
    }

}