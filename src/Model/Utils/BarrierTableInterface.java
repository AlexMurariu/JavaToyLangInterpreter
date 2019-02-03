package Model.Utils;

import java.util.Map;
import java.util.Set;

public interface BarrierTableInterface<K,V>{
    void add(K key, V value);
    boolean contains(K key);
    void update(K key, V value);
    void remove(K key);
    V get(K key);
    Iterable<K> getAll();
    void setContent(Map<K, V> m);
    Set<Map.Entry<K, V>> entrySet() ;
}