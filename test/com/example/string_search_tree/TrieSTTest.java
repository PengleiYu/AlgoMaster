package com.example.string_search_tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieSTTest {

    @Test
    void put() {
        TrieST<Integer> trieST = new TrieST<>();
        trieST.put("by", 4);
        trieST.put("sea", 2);
        trieST.put("sells", 1);
        trieST.put("she", 0);
        trieST.put("shells", 3);
        trieST.put("the", 5);
        TreePrinter.printTree(trieST.root);
    }

    @Test
    void get() {
    }
}