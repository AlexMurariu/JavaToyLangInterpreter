package Model.HeapStmt;

import Model.Expression.ExpressionInterface;
import Model.ProgramState;
import Model.Statements.StmtInterface;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;

public class HeapAlloc implements StmtInterface {
    private String varName;
    private ExpressionInterface exp;

    public HeapAlloc(String varName, ExpressionInterface exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        DictionaryInterface<String, Integer> dic = state.getSymTable();
        HeapInterface<Integer, Integer> heap = state.getHeap();

        int id = GenHeapID.getID();
        int val = exp.evaluate(dic, heap);

        heap.add(id, val);
        if(dic.exists(varName))
            dic.update(varName, id);
        else
            dic.setValue(varName, id);
        return null;
    }

    @Override
    public String toString(){
        return "new(" + varName + ", " + exp + ")";
    }
}

