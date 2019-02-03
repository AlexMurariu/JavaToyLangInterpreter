package Model.Utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DictionaryInterface<K, V> {
    void setValue(K key, V value);
    void delete(K key);
    void update(K key, V value);
    void setMap(Map<K, V> dic);
    V getValue(K key);
    List<V> getAllValues();
    boolean exists(K key);
    Iterable<K> getElements();
    Collection<V> values();
    Map<K, V> getMap();
    DictionaryInterface<K, V> copy();
}

