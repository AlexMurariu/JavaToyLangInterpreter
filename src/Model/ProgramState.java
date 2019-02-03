package Model;

import Model.Exp.EmptyStack;
import Model.FileHandling.FileData;
import Model.FileHandling.FileTableInterface;
import Model.Statements.StmtInterface;
import Model.Utils.*;

import java.util.Map;
import java.util.stream.Collectors;

public class ProgramState {
    private StackInterface<StmtInterface> stack;
    private DictionaryInterface<String, Integer> SymTable;
    private ListInterface<Integer> list;
    private FileTableInterface<Integer, FileData> fileTable;
    private HeapInterface<Integer, Integer> heap;
    private BarrierTableInterface<Integer, MyPair> barrierTable;
    private Integer id;

    public ProgramState(StackInterface<StmtInterface> stack, DictionaryInterface<String, Integer> symTable,
                        ListInterface<Integer> list, FileTableInterface<Integer, FileData> fileTable,
                        HeapInterface<Integer, Integer> heap, BarrierTableInterface<Integer, MyPair> barrierTable, Integer id) {
        this.stack = stack;
        this.SymTable = symTable;
        this.list = list;
        this.fileTable = fileTable;
        this.heap = heap;
        this.barrierTable = barrierTable;
        this.id = id;
    }

    public BarrierTableInterface<Integer, MyPair> getBarrierTable() {
        return barrierTable;
    }

    public void setBarrierTable( BarrierTableInterface<Integer, MyPair> barrierTable) {
        this.barrierTable = barrierTable;
    }

    public Integer getId(){return id;}

    public StackInterface<StmtInterface> getStack() {
        return stack;
    }

    public void setStack(StackInterface<StmtInterface> stack) {
        this.stack = stack;
    }

    public DictionaryInterface<String, Integer> getSymTable() {
        return SymTable;
    }

    public void setSymTable(DictionaryInterface<String, Integer> symTable) {
        SymTable = symTable;
    }

    public ListInterface<Integer> getList() {
        return list;
    }

    public void setList(ListInterface<Integer> list) {
        this.list = list;
    }

    public FileTableInterface<Integer, FileData> getFileTable() { return fileTable; }

    public void setFileTable(FileTableInterface<Integer, FileData> fileTable) { this.fileTable = fileTable; }

    public HeapInterface<Integer, Integer> getHeap() { return heap; }

    public void setHeap(HeapInterface<Integer, Integer> heap) { this.heap = heap; }

    public boolean isNotCompleted() {
        if(!stack.isEmpty())
            return true;
        return false;
    }

    public ProgramState oneStep() {
        if (stack.isEmpty())
            throw new EmptyStack("The stack is empty!");
        StmtInterface currentStmt = stack.pop();
        return currentStmt.execute(this);
    }

    public void clearFileTable(){ this.fileTable.clear(); }

    public void clearFilesFromSymTable(){
        SymTable.setMap(SymTable.getMap().entrySet().stream().filter(e->fileTable.getMap().containsKey(e))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();

        bld.append("\nCurrent program: " + id + "\n");

        bld.append("\nExeStack\n");
        for(StmtInterface s : stack.getElements()) {
            bld.append(s).append("\n");
        }

        bld.append("\nSymTable\n");
        for(String key : SymTable.getElements()){
            bld.append(key).append(" : ").append(SymTable.getValue(key)).append("\n");
        }

        bld.append("\nOut:\n");
        for(Integer i : list.getElements()){
            bld.append(i).append("\n");
        }

        bld.append("\nFileTable:\n");
        for(Integer i : fileTable.getElements()) {
            bld.append(i).append(fileTable.get(i).getFileName()).append("\n");
        }

        bld.append("\nHeap:\n");
        for(Integer key : heap.getKeys()){
            bld.append(key).append(" : ").append(heap.getVal(key)).append("\n");
        }

        return bld.toString();
    }
}
