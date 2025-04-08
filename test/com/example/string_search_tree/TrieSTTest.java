package com.example.string_search_tree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieSTTest {

    private TrieST<Integer> trieST;

    @BeforeEach
    public void init() {
        trieST = new TrieST<>();
        trieST.put("by", 4);
        trieST.put("sea", 2);
        trieST.put("sells", 1);
        trieST.put("she", 0);
        trieST.put("shells", 3);
        trieST.put("the", 5);
    }

    @AfterEach
    public void clear() {
        trieST = null;
    }

    @Test
    void printTrie() {
        TreePrinter.printTree(trieST.root);
    }

    @Test
    void get() {
        assertEquals(4, trieST.get("by"));
        assertEquals(2, trieST.get("sea"));
        assertNull(trieST.get("hello"));
    }
}