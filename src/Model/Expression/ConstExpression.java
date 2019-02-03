package Model.Expression;

import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;

public class ConstExpression implements ExpressionInterface{

    private int number;

    public ConstExpression(int nr){
        number = nr;
    }

    @Override
    public int evaluate(DictionaryInterface<String, Integer> dic, HeapInterface<Integer, Integer> heap) {
        return number;
    }

    @Override
    public String toString(){
        return "" + number;
    }
}