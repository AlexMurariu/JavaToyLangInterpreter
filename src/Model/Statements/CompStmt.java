package Model.Statements;

import Model.ProgramState;
import Model.Utils.StackInterface;

public class CompStmt implements StmtInterface {

    private  StmtInterface first, second;

    public CompStmt(StmtInterface f, StmtInterface s){
        first = f;
        second = s;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        StackInterface<StmtInterface> stack = state.getStack();
        stack.add(second);
        stack.add(first);

        return null;
    }

    @Override
    public String toString(){
        return "[" + first + " , " + second + "]";
    }
}
