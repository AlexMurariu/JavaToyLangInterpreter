package Model.FileHandling;

import java.io.BufferedReader;

public class FileData {
    String fileName;
    BufferedReader reader;

    public FileData(String name, BufferedReader buff){
        fileName = name;
        reader = buff;
    }

    public String getFileName() {
        return fileName;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public String toString(){
        return fileName;
    }

}