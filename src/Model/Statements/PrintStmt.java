package Model.Statements;

import Model.Expression.ExpressionInterface;
import Model.ProgramState;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;
import Model.Utils.ListInterface;

public class PrintStmt implements StmtInterface{

    private ExpressionInterface expr;

    public PrintStmt(ExpressionInterface expression){
        expr = expression;
    }

    @Override
    public ProgramState execute(ProgramState state){
        ListInterface<Integer> list = state.getList();
        HeapInterface<Integer, Integer> heap = state.getHeap();
        DictionaryInterface<String, Integer> dic = state.getSymTable();
        int val = expr.evaluate(dic, heap);
        list.add(val);
        return null;
    }

    @Override
    public String toString(){
        return "print(" + expr + ");";
    }
}
