package com.example.string_search_tree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class TSTTest {
    private TST<Integer> trieST;

    private List<String> getSortedResults(Iterable<String> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .sorted()
                .collect(Collectors.toList());
    }

    @BeforeEach
    public void init() {
        trieST = new TST<>();
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

    @Test
    void put() {
    }

    @Test
    void delete() {
        String[] keys = {"by", "sea", "sells", "she", "shells", "the"};
        trieST.delete("");
        assertIterableEquals(
                getSortedResults(Arrays.asList(keys)),
                getSortedResults(trieST.keys())
        );
        trieST.delete("she");
        assertIterableEquals(
                getSortedResults(Arrays.asList("by", "sea", "sells", "shells", "the")),
                getSortedResults(trieST.keys()));
        trieST.delete("sea");
        assertIterableEquals(
                getSortedResults(Arrays.asList("by", "sells", "shells", "the")),
                getSortedResults(trieST.keys()));
    }

    @Test
    void contains() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void longestPrefix() {
    }

    @Test
    void keysWithPrefix() {
        assertIterableEquals(Arrays.asList("sea", "sells"), trieST.keysWithPrefix("se"));
        assertIterableEquals(Arrays.asList("she", "shells"), trieST.keysWithPrefix("she"));
        assertIterableEquals(List.of(), trieST.keysWithPrefix("shen"));
    }

    @Test
    void keysThatMatch() {
        assertIterableEquals(List.of("she"), trieST.keysThatMatch("she"));
        assertIterableEquals(List.of("she", "the"), trieST.keysThatMatch(".he"));
        assertIterableEquals(List.of("she"), trieST.keysThatMatch("s.e"));
        assertIterableEquals(List.of("sea", "she", "the"), getSortedResults(trieST.keysThatMatch("...")));
        assertIterableEquals(List.of(), trieST.keysThatMatch("......."));
        assertIterableEquals(List.of(), trieST.keysThatMatch(""));
    }

    @Test
    void size() {
        assertEquals(6, trieST.size());
    }

    @Test
    void keys() {
        String[] keys = {"by", "sea", "sells", "she", "shells", "the"};
        assertIterableEquals(Arrays.asList(keys), trieST.keys());
    }
}