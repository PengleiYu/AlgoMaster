package com.example.string_search_tree;

class TreePrinterTest {

    @org.junit.jupiter.api.Test
    void printTree() {

        TrieNode root = new TrieNode("Root");

        TrieNode child1 = new TrieNode("Child1");
        TrieNode child2 = new TrieNode("Child2");

        root.addChild(child1, 'a');
        root.addChild(child2, 'b');

        child1.addChild(new TrieNode("Grandchild1"), 'a');
        child2.addChild(new TrieNode("Grandchild2"), 'e');
        child2.addChild(new TrieNode("Grandchild3"), 'g');

        TreePrinter.printTree(root);
    }

    static class TrieNode implements ITrieNode {
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
        public int getChildrenSize() {
            return children.length;
        }

        @Override
        public ITrieNode getChild(int index) {
            return children[index];
        }
    }
}