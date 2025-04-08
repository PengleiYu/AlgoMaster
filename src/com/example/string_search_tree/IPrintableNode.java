package com.example.string_search_tree;

import org.jetbrains.annotations.Nullable;

interface IPrintableNode {
    @Nullable
    String getValue();

    int getChildrenSize();

    IPrintableNode getChild(int index);
}