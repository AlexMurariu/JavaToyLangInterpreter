package Model.Statements;

import Model.Expression.ExpressionInterface;
import Model.ProgramState;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;
import Model.Utils.StackInterface;

public class WhileStmt implements StmtInterface{

    ExpressionInterface expr;
    StmtInterface stmt;

    public WhileStmt(ExpressionInterface expr, StmtInterface stmt) {
        this.expr = expr;
        this.stmt = stmt;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        StackInterface<StmtInterface> stack = state.getStack();
        HeapInterface<Integer, Integer> heap = state.getHeap();
        DictionaryInterface<String, Integer> dic = state.getSymTable();

        int val = expr.evaluate(dic, heap);

        if(val != 0){
            stack.add(new WhileStmt(expr, stmt));
            stack.add(stmt);
        }
        return null;
    }

    @Override
    public String toString(){
        return "While(" + expr + "){" + stmt + "}";
    }
}
