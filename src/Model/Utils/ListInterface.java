package Model.Utils;

public interface ListInterface<T> {
    void add(T x);

    Iterable<T> getElements();

    void remove(T x);
}
