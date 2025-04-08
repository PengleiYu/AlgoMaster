package com.example.string_search_tree;

interface ITrieNode {

    int getChildrenSize();

    ITrieNode getChild(int index);
}