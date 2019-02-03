package Model.FileHandling;

import Model.Exp.FileException;
import Model.Expression.ExpressionInterface;
import Model.ProgramState;
import Model.Statements.StmtInterface;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;

import java.io.BufferedReader;
import java.io.IOException;


public class ReadFile implements StmtInterface {
    ExpressionInterface exp;
    String varName;

    public ReadFile(ExpressionInterface exp, String varName) {
        this.exp = exp;
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        DictionaryInterface<String, Integer> dic =  state.getSymTable();
        HeapInterface<Integer, Integer> heap = state.getHeap();
        FileTableInterface<Integer, FileData> fileTable = state.getFileTable();
        int id = exp.evaluate(dic, heap);

        if(!fileTable.contains(id))
            throw new FileException("This ID is not in the file table!");

        BufferedReader reader = fileTable.get(id).getReader();

        String line = null;

        try{
            line = reader.readLine();
        }catch(IOException e){
            throw new FileException("Can't read line!");
        }

        int value = 0;
        if(line != null)
            value = Integer.parseInt(line);

        dic.setValue(varName, value);

        return null;
    }

    @Override
    public String toString() {
        return "read(" + varName + ", " + exp + ")";
    }
}
