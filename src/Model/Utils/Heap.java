package Model.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Heap<K, V> implements HeapInterface<K, V>{

    Map<K, V> dic = new HashMap<>();

    @Override
    public void add(K key, V value) {
        dic.put(key, value);
    }

    @Override
    public boolean contains(K key) {
        return dic.containsKey(key);
    }

    @Override
    public void remove(K key) {
        dic.remove(key);
    }

    @Override
    public void update(K key, V value){
        dic.put(key, value);
    }

    @Override
    public V getVal(K key) {
        return dic.get(key);
    }

    @Override
    public Iterable<K> getKeys() {
        return dic.keySet();
    }

    @Override
    public void setContent(Map<K, V> m) {
        dic = m;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return dic.entrySet();
    }

    @Override
    public Map<K, V> getContent(){ return dic;}

    @Override
    public String toString(){
        StringBuilder bld = new StringBuilder();
        for(Map.Entry<K,V> e:dic.entrySet())
            bld.append(e.getKey()).append("->").append(e.getValue()).append("\n");
        return bld.toString();
    }
}
