package Model.Statements;

import Model.FileHandling.FileData;
import Model.FileHandling.FileTableInterface;
import Model.ProgramState;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;
import Model.Utils.ListInterface;
import Model.Utils.*;

public class ForkStmt implements StmtInterface {

    StmtInterface stmt;

    public ForkStmt(StmtInterface stmt) {
        this.stmt = stmt;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        StackInterface<StmtInterface> stack2 = new Stack<>();
        ListInterface<Integer> out2 = state.getList();
        HeapInterface<Integer, Integer> heap2 = state.getHeap();
        FileTableInterface<Integer, FileData> fileTable2 = state.getFileTable();
        DictionaryInterface<String, Integer> dic2 = state.getSymTable().copy();
        BarrierTableInterface<Integer, MyPair> barrierTable2 = state.getBarrierTable();

        stack2.add(stmt);
        int id = GenForkId.getID();

        return new ProgramState(stack2, dic2, out2, fileTable2, heap2, barrierTable2, id);
    }

    @Override
    public String toString(){
        return "fork(" + stmt + ")";
    }
}
