package Model.Statements;

import Model.Expression.ExpressionInterface;
import Model.ProgramState;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;

public class AssignStmt implements StmtInterface {
    private String id;
    private ExpressionInterface expr;

    public AssignStmt(String key, ExpressionInterface expression){
        id = key;
        expr = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        DictionaryInterface<String, Integer> dic = state.getSymTable();
        HeapInterface<Integer, Integer> heap = state.getHeap();
        int val = expr.evaluate(dic, heap);
        dic.setValue(id, val);
        return null;
    }

    @Override
    public String toString(){
        return id + "=" + expr + "; ";
    }
}
