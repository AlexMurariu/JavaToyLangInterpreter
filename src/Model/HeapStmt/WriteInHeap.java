package Model.HeapStmt;

import Model.Exp.VariableNotDefined;
import Model.Expression.ExpressionInterface;
import Model.ProgramState;
import Model.Statements.StmtInterface;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;

public class WriteInHeap implements StmtInterface {
    private String varName;
    private ExpressionInterface exp;

    public WriteInHeap(String varName, ExpressionInterface exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        DictionaryInterface<String, Integer> dic = state.getSymTable();
        HeapInterface<Integer, Integer> heap = state.getHeap();

        if(!dic.exists(varName))
            throw new VariableNotDefined("The key is not in the symTable!");

        int heapID = dic.getValue(varName);

        if(!heap.contains(heapID))
            throw new VariableNotDefined("The key is not in the heap!");

        int val = exp.evaluate(dic, heap);
        heap.update(heapID, val);
        return null;
    }

    @Override
    public String toString(){
        return "wirteInHeap(" + varName + ", " + exp + ")";
    }
}
