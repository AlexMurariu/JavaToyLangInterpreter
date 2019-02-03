package Model.FileHandling;

import Model.Exp.FileException;
import Model.ProgramState;
import Model.Statements.StmtInterface;
import Model.Utils.DictionaryInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenFile implements StmtInterface {

    private String fileName;
    private String varName;

    public OpenFile(String fileName, String varName) {
        this.fileName = fileName;
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        FileTableInterface<Integer, FileData> fileTable = state.getFileTable();
        DictionaryInterface<String, Integer> dic = state.getSymTable();
        BufferedReader reader;

        for(FileData data : fileTable.getAllElems()){
            if(data.getFileName().equals(fileName)){
                throw new FileException("The file is already in FileTable!");
            }
        }
        try{
            reader = new BufferedReader(new FileReader(fileName));
        }catch(IOException e){
            throw new FileException("File not found!");
        }

        int id = KeyGenerator.genID();

        fileTable.add(id, new FileData(fileName, reader));
        dic.setValue(varName, id);
        return null;
    }

    @Override
    public String toString(){
        return "open(" + varName + ", " + fileName + ")";
    }
}
