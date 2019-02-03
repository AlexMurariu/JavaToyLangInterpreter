package Model.Statements;

public class GenForkId {
    private static int nr=2;
    public static  int getID(){
        return nr++;
    }
}

