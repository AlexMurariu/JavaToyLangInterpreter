package Model.Expression;

import Model.Exp.DivisionByZero;
import Model.Exp.InvalidOperator;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;

public class ArithmExpression implements ExpressionInterface {

    private ExpressionInterface left, right;
    private String operator;

    public ArithmExpression(String op, ExpressionInterface l, ExpressionInterface r){
        operator = op;
        left = l;
        right = r;
    }

    @Override
    public int evaluate(DictionaryInterface<String, Integer> dic, HeapInterface<Integer, Integer> heap) {
        int left = this.left.evaluate(dic, heap);
        int right = this.right.evaluate(dic, heap);

        switch (operator){
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                if(right == 0)
                    throw new DivisionByZero("Division by 0 is not permited!");
                return left/right;
        }
        throw new InvalidOperator("Invalid operator!");
    }

    @Override
    public String toString(){
        return "" + left + operator + right;
    }
}

