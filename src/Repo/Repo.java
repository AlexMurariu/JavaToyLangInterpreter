package Repo;

import Model.Exp.FileException;
import Model.FileHandling.FileData;
import Model.FileHandling.FileTableInterface;
import Model.ProgramState;
import Model.Statements.StmtInterface;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;
import Model.Utils.ListInterface;
import Model.Utils.StackInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repo implements RepoInterface {

    private List<ProgramState> listPrg = new ArrayList<>();
    private int current = 0;
    @Override
    public void addPrg(ProgramState prg) {
        listPrg.add(prg);
    }

    @Override
    public List<ProgramState> getPrgList() { return listPrg; }

    @Override
    public void setPrgList(List<ProgramState> list) { listPrg = list; }

    @Override
    public String toString(){
        StringBuilder bld = new StringBuilder();

        for (ProgramState state : listPrg){
            bld.append(state).append("\n");
        }
        return bld.toString();
    }

    @Override
    public void logPrgStateExec(ProgramState state) {
        try(PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter("text.txt", true)))){

            StackInterface<StmtInterface> stack  = state.getStack();
            DictionaryInterface<String, Integer> dic = state.getSymTable();
            ListInterface<Integer> out = state.getList();
            FileTableInterface<Integer, FileData> fileTable = state.getFileTable();
            HeapInterface<Integer, Integer> heap = state.getHeap();

            logFile.println("\nCurrent program:" + state.getId() + "\n");

            logFile.println("\nExeStack:\n");
            for(StmtInterface S : stack.getElements())
                logFile.println(S);

            logFile.println("\nSymTable\n");
            for(String key : dic.getElements())
                logFile.println(key + "-->" + dic.getValue(key) + "\n");

            logFile.println("\nOut\n");
            for(Integer i : out.getElements())
                logFile.println(i + "\n");

            logFile.println("\nFileTable\n");
            for(Integer i : fileTable.getElements())
                logFile.println(i + "-->" + fileTable.get(i).getFileName() + "\n");

            logFile.println("\nHeap\n");
            for(Integer i : heap.getKeys())
                logFile.println(i + "-->" + heap.getVal(i) + "\n");

            logFile.println("\n");

        }catch(FileNotFoundException e){
            throw new FileException("File not found in PrintWriter");
        }catch(IOException e){
            throw new FileException("IO exception at PrintWriter");
        }
    }
}

