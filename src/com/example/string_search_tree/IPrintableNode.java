package com.example.string_search_tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

interface IPrintableNode {
    @NotNull
    String getName(int indexInParent);

    @Nullable
    String getValue();

    int getChildrenSize();

    IPrintableNode getChild(int index);
}