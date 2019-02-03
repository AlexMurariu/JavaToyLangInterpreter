package Model.Utils;

import java.util.*;
import Model.Exp.VariableNotDefined;

public class Dictionary<K, V> implements DictionaryInterface<K, V>{

    private Map<K, V> elements = new HashMap<>();

    @Override
    public void setValue(K key, V value) {
        elements.put(key, value);
    }

    @Override
    public void delete(K key) {
        elements.remove(key);
    }

    @Override
    public void update(K key, V value){ elements.put(key, value);}

    @Override
    public V getValue(K key) {
        if(!exists(key))
            throw new VariableNotDefined("Variable not defined in dictionary!");
        return elements.get(key);
    }

    @Override
    public List<V> getAllValues() {
        List<V> list = new ArrayList<>();

        for(K key : this.getElements()){
            list.add(this.getValue(key));
        }

        return list;
    }

    @Override
    public boolean exists(K key) {
        return elements.containsKey(key);
    }

    @Override
    public Iterable<K> getElements() {
        return elements.keySet();
    }

    @Override
    public DictionaryInterface<K, V> copy() {
        DictionaryInterface<K, V> table = new Dictionary<>();
        for(Map.Entry<K, V> e :  elements.entrySet()){
            table.setValue(e.getKey(), e.getValue());
        }
        return table;
    }
    @Override
    public Collection<V> values(){
        return elements.values();
    }

    @Override
    public Map<K, V> getMap() { return elements; }

    @Override
    public void setMap(Map<K, V> dic) { elements = dic; }

    @Override
    public String toString(){
        StringBuilder bld = new StringBuilder();

        for(Map.Entry<K, V> e : elements.entrySet()){
            bld.append("<").append(e.getKey()).append(", ").append(e.getValue()).append(">; ");
        }
        return bld.toString();
    }
}