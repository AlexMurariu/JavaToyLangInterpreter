package Model.FileHandling;

public class KeyGenerator {
    public static int counter = 0;
    public static int genID(){
        return counter++;
    }
}
