package Model.Utils;

public interface StackInterface<T> {
    void add(T x);
    Iterable<T> getElements();
    boolean isEmpty();
    T peek();
    T pop();
}
