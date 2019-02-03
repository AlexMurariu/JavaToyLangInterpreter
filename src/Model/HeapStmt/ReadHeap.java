package Model.HeapStmt;

import Model.Exp.VariableNotDefined;
import Model.Expression.ExpressionInterface;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;

public class ReadHeap implements ExpressionInterface {
    private String varName;

    public ReadHeap(String varName) {
        this.varName = varName;
    }
    @Override
    public int evaluate(DictionaryInterface<String, Integer> dic, HeapInterface<Integer, Integer> heap) {
        if(!dic.exists(varName))
            throw  new VariableNotDefined("The key is not in the symTable!");

        int heapID = dic.getValue(varName);

        if(!heap.contains(heapID))
            throw new VariableNotDefined("The key is not in the heap!");

        return heap.getVal(heapID);
    }

    @Override
    public String toString() {
        return "ReadHeap(" + varName + ")";
    }
}
