package Model.Expression;

import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;

public interface ExpressionInterface {
    int evaluate(DictionaryInterface<String, Integer> dic, HeapInterface<Integer, Integer> heap);
}
