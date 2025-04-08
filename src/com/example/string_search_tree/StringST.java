package com.example.string_search_tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StringST<Value> {
    // 插入，若val为null则删除key
    void put(@NotNull String key, @Nullable Value val);

    // 查询
    @Nullable Value get(@NotNull String key);

    // 删除
    void delete(@NotNull String key);

    // 是否包含
    boolean contains(@NotNull String key);

    boolean isEmpty();

    // s中最长的前缀key
    String longestPrefix(@NotNull String s);

    // 以s为前缀的所有key
    Iterable<String> keysWithPrefix(@NotNull String s);

    // 前缀和s匹配的所有key
    Iterable<String> keysThatMatch(@NotNull String s);

    int size();

    // 所有key
    Iterable<String> keys();
}
