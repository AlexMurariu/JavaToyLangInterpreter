package Model.Statements;

import Model.Expression.BooleanExpression;
import Model.Expression.ExpressionInterface;
import Model.ProgramState;
import Model.Utils.StackInterface;

public class SwitchStmt implements StmtInterface {

    ExpressionInterface expr, expr1, expr2;
    StmtInterface stmt1, stmt2, stmt3;

    public SwitchStmt(ExpressionInterface expr, ExpressionInterface expr1, ExpressionInterface expr2,
                      StmtInterface stmt1, StmtInterface stmt2, StmtInterface stmt3) {
        this.expr = expr;
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        StackInterface<StmtInterface> stack = state.getStack();

        StmtInterface s = new IfStmt(new BooleanExpression("==", expr, expr1), stmt1, new IfStmt(
                new BooleanExpression("==", expr, expr2), stmt2, stmt3));

        stack.add(s);

        return null;
    }

    @Override
    public String toString(){
        return "switch(" + expr + ") (" + expr1 + ":" + stmt1 + ") (" + expr2 + ":" + stmt2 + ") (default" + stmt3 + ")";
    }
}
