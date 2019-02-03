package Model.Expression;

import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;
import Model.Exp.InvalidOperator;
public class BooleanExpression implements ExpressionInterface {
    private String op;
    private ExpressionInterface left, right;

    public BooleanExpression(String op, ExpressionInterface left, ExpressionInterface right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }


    @Override
    public int evaluate(DictionaryInterface<String, Integer> dic, HeapInterface<Integer, Integer> heap) {
        int left = this.left.evaluate(dic, heap);
        int right = this.right.evaluate(dic, heap);

        switch (op){
            case "==":
            {
                if(left == right)
                    return 1;
                return 0;
            }
            case "!=":
            {
                if(left != right)
                    return 1;
                return 0;
            }
            case "<":
            {
                if(left < right)
                    return 1;
                return 0;
            }
            case ">":
            {
                if(left > right)
                    return 1;
                return 0;
            }
            case "<=":
            {
                if(left <= right)
                    return 1;
                return 0;
            }
            case ">=":
            {
                if(left >= right)
                    return 1;
                return 0;
            }
        }
        throw new InvalidOperator("Invalid operator!");
    }

    @Override
    public String toString(){ return "(" + left + op + right + ")"; }
}
