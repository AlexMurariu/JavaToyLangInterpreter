package Model.Utils;

import java.util.ArrayDeque;
import java.util.Deque;

public class Stack<T> implements StackInterface<T> {

    private Deque<T> elements = new ArrayDeque<>();

    @Override
    public void add(T x) {
        elements.push(x);
    }

    @Override
    public Iterable<T> getElements() {
        return elements;
    }

    @Override
    public boolean isEmpty() {
        if(elements.size() == 0)
            return true;
        return false;
    }

    @Override
    public T peek(){ return elements.peek(); }

    @Override
    public T pop() {
        return elements.pop();
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