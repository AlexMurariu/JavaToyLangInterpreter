package Model.Utils;

import java.util.Map;
import java.util.Set;

public interface HeapInterface<K, V> {
    void add(K key, V value);
    boolean contains(K key);
    void remove(K key);
    void update(K key, V value);
    V getVal(K key);
    Iterable<K> getKeys();
    void setContent(Map<K,V> m);
    Set<Map.Entry<K,V>> entrySet();
    Map<K, V> getContent();
}
