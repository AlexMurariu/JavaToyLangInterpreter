package Model.Statements;

import Model.Expression.ExpressionInterface;
import Model.ProgramState;
import Model.Utils.StackInterface;

public class CondAsgnStmt implements StmtInterface {

    private String id;
    private ExpressionInterface expr1, expr2, expr3;

    public CondAsgnStmt(String id, ExpressionInterface expr1, ExpressionInterface expr2, ExpressionInterface expr3) {
        this.id = id;
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.expr3 = expr3;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        StackInterface<StmtInterface> stack = state.getStack();

        StmtInterface stmt = new IfStmt(expr1, new AssignStmt(id, expr2), new AssignStmt(id, expr3));

        stack.add(stmt);

        return null;
    }

    @Override
    public String toString(){
        return id + "=" + expr1 + "?" + expr2 + ":" + expr3;
    }
}
