package Model.Statements;

import Model.Expression.ExpressionInterface;
import Model.ProgramState;

public class ForStmt implements StmtInterface {

    StmtInterface s1, s2, s3;
    ExpressionInterface expr;

    public ForStmt(StmtInterface s1, StmtInterface s2, StmtInterface s3, ExpressionInterface expr) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.expr = expr;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        StmtInterface stmt = new CompStmt(s1, new WhileStmt(expr, new CompStmt(s3, s2)));

        state.getStack().add(stmt);

        return null;
    }

    @Override
    public String toString(){
        return "for(" + s1 + ";" + expr + "; " + s2 + ")" + "{" + s3 + "}";
    }

}
