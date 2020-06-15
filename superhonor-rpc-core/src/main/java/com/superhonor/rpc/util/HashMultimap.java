package com.superhonor.rpc.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 类HashMap<K, Set<V>>的集合工具
 * @author liuweidong
 */
public class HashMultimap<K, V> {

    private HashMap<K, Set<V>> hashMap = new HashMap<>();

    private HashMultimap() {
    }

    /**
     * 创建一个HashMultimap实例
     *
     * @return
     */
    public static <K, V> HashMultimap<K, V> create() {
        return new HashMultimap<>();
    }

    /**
     * 添加键值对，支持链式调用
     *
     * @param key
     * @param value
     * @return 当前HashMultimap实例
     */
    public HashMultimap<K, V> put(K key, V value) {
        synchronized (key) {
            Set<V> set = hashMap.get(key);
            if (Objects.isNull(set)) {
                set = new HashSet<>();
            }
            set.add(value);
            hashMap.put(key, set);
        }
        return this;
    }

    /**
     * 添加键值对，已存key在则覆盖，支持链式调用
     *
     * @param key
     * @param values
     * @return 当前HashMultimap实例
     */
    public HashMultimap<K, V> put(K key, Set<V> values) {
        hashMap.put(key, values);
        return this;
    }

    /**
     * 删除指定key的所有value
     *
     * @param key
     * @return
     */

    public boolean remove(K key) {
        if (hashMap.containsKey(key)) {
            hashMap.remove(key);
            return true;
        }
        return false;
    }

    /**
     * 删除指定key里面的指定value
     *
     * @param key
     * @param value
     * @return
     */
    public boolean remove(K key, V value) {
        if (hashMap.containsKey(key)) {
            Set<V> set = hashMap.get(key);
            if (set.contains(value)) {
                set.remove(value);
                hashMap.put(key, set);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 获取指定key的值集合，键不存在时返回空集合
     *
     * @param key
     * @return
     */
    public Set<V> get(K key) {
        if (hashMap.containsKey(key)) {
            return hashMap.get(key);
        }
        return new HashSet<>();
    }

    /**
     * 是否包含指定的key
     *
     * @param key
     * @return
     */
    public boolean containsKey(K key) {
        return hashMap.containsKey(key);
    }
}
