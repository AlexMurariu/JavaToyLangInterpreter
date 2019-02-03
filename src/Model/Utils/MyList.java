package Model.Utils;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements ListInterface<T> {

    private List<T> elements = new ArrayList<>();

    @Override
    public void add(T x) { elements.add(x); }

    @Override
    public Iterable<T> getElements() {
        return elements;
    }

    @Override
    public void remove(T x) {
        elements.remove(x);
    }

    @Override
    public String toString(){
        StringBuilder bld = new StringBuilder();

        for(T i : elements){
            bld.append(i).append(" ");
        }
        return bld.toString();
    }
}