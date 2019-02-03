package Model.Statements;

import Model.Expression.ExpressionInterface;
import Model.Expression.NotExpression;
import Model.ProgramState;
import Model.Utils.StackInterface;

public class DoWhileStmt implements StmtInterface {

    private StmtInterface stmt;
    private ExpressionInterface expr;

    public DoWhileStmt(StmtInterface stmt, ExpressionInterface expr) {
        this.stmt = stmt;
        this.expr = expr;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        StackInterface<StmtInterface> stack = state.getStack();
        StmtInterface s = new WhileStmt(new NotExpression(expr), stmt);

        stack.add(new CompStmt(stmt, s));

        return null;
    }
}
