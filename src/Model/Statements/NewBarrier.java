package Model.Statements;

import Model.Expression.ExpressionInterface;
import Model.ProgramState;
import Model.Utils.MyPair;

import java.util.ArrayList;

public class NewBarrier implements StmtInterface {
    String varName;
    ExpressionInterface exp;
    private static int id = 0;

    public NewBarrier(String varName, ExpressionInterface exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        int number = exp.evaluate(state.getSymTable(), state.getHeap());

        synchronized (state.getBarrierTable()){
            state.getBarrierTable().add(id, new MyPair(new ArrayList<Integer>(), number));
            if(state.getSymTable().exists(varName))
                state.getSymTable().update(varName,id);
            else
                state.getSymTable().setValue(varName, id);
        }
        return null;
    }

    @Override
    public String toString(){
        return "newBarrier("+ varName +","+exp+")";
    }
}