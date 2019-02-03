package Model.Expression;

import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;

public class VarExpression implements ExpressionInterface{
    String id;
    public VarExpression(String key){
        id = key;
    }

    @Override
    public int evaluate(DictionaryInterface<String, Integer> dic, HeapInterface<Integer, Integer> heap) {
        return dic.getValue(id);
    }

    @Override
    public String toString(){
        return id;
    }
}