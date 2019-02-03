package Model.FileHandling;

import Model.Exp.FileException;
import Model.Expression.ExpressionInterface;
import Model.ProgramState;
import Model.Statements.StmtInterface;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFile implements StmtInterface {

    private ExpressionInterface exp;

    public CloseFile(ExpressionInterface exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state){

        DictionaryInterface<String, Integer> dic = state.getSymTable();
        HeapInterface<Integer, Integer> heap = state.getHeap();
        FileTableInterface<Integer, FileData> fileTable = state.getFileTable();
        int id = exp.evaluate(dic, heap);

        if(!fileTable.contains(id)){
            throw new FileException("The file doesn't exist!");
        }

        FileData fileData = fileTable.get(id);
        BufferedReader reader = fileData.getReader();

        try{
            reader.close();
        }catch (IOException e){
            throw new FileException("Can't close the file!");
        }

        fileTable.delete(id);

        return null;
    }

    @Override
    public String toString(){
        return "close(" + exp + ")";
    }
}
