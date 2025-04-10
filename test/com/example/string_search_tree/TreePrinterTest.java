package com.example.string_search_tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class TreePrinterTest {

    @org.junit.jupiter.api.Test
    void printTree() {

        TrieNode root = new TrieNode("RootValue");

        TrieNode child1 = new TrieNode("Child1");
        TrieNode child2 = new TrieNode("Child2");

        root.addChild(child1, 'a');
        root.addChild(child2, 'b');

        child1.addChild(new TrieNode("Grandchild1"), 'a');
        child2.addChild(new TrieNode("Grandchild2"), 'e');
        child2.addChild(new TrieNode("Grandchild3"), 'g');

        TreePrinter.printTree(root);
    }

    static class TrieNode implements IPrintableNode {
        String name;
        TrieNode[] children;

        public TrieNode(String name) {
            this.name = name;
            this.children = new TrieNode[256];
        }

        public void addChild(TrieNode child, int index) {
            children[index] = child;
        }

        @Override
        public @NotNull String getName(int indexInParent) {
            return indexInParent == -1 ? "root" : String.valueOf((char) indexInParent);
        }

        @Override
        public @Nullable String getValue() {
            return name;
        }

        @Override
        public int getChildrenSize() {
            return children.length;
        }

        @Override
        public IPrintableNode getChild(int index) {
            return children[index];
        }
    }
}