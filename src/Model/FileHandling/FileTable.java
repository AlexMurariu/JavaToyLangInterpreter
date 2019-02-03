package Model.FileHandling;

import java.io.BufferedReader;
import java.util.*;

public class FileTable<K, V> implements FileTableInterface<K, V>{

    private Map<K, V> dic = new HashMap<>();

    @Override
    public void add(K key, V val) { dic.put(key, val); }

    @Override
    public void delete(K key) {
        dic.remove(key);
    }

    @Override
    public V get(K key) {
        return dic.get(key);
    }

    @Override
    public boolean contains(K key) {
        return dic.containsKey(key);
    }

    @Override
    public Iterable<K> getElements() {
        return dic.keySet();
    }

    @Override
    public Collection<V> getAllElems() {
        return dic.values();
    }

    @Override
    public void clear() { dic.clear(); }

    @Override
    public Map<K, V> getMap() { return dic; }

    @Override
    public String toString(){
        StringBuilder bld = new StringBuilder();
        for(Map.Entry<K, V> e : dic.entrySet()){
            bld.append("<").append(e.getKey()).append(",").append(e.getValue()).append(">;");
        }
        return bld.toString();
    }
}

