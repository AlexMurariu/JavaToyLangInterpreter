package GUI;

import Model.Expression.ArithmExpression;
import Model.HeapStmt.HeapAlloc;
import Model.HeapStmt.ReadHeap;
import Model.HeapStmt.WriteInHeap;
import Model.Statements.*;
import Model.Expression.ConstExpression;
import Model.Expression.VarExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectController {
    @FXML
    private ListView<String> listView;

    @FXML
    private Button selectButton;

    private List<StmtInterface> stmtList = new ArrayList<StmtInterface>();

    @FXML
    public void initialize(){
        StmtInterface a1 = new AssignStmt("a", new ConstExpression(1));
        StmtInterface a2 = new AssignStmt("b", new ConstExpression(2));
        StmtInterface a3 = new AssignStmt("c", new ConstExpression(5));

        StmtInterface c1 = new CompStmt(a1, new CompStmt(a2, a3));

        StmtInterface s1 = new SwitchStmt(new ArithmExpression("*", new VarExpression("a"), new ConstExpression(10)),
                new ArithmExpression("*", new VarExpression("b"), new VarExpression("c")), new ConstExpression(10),
                new CompStmt(new PrintStmt(new VarExpression("a")), new PrintStmt(new VarExpression("b"))), new CompStmt(
                new PrintStmt(new ConstExpression(100)), new PrintStmt(new ConstExpression(200))), new PrintStmt(new
                ConstExpression(300)));

        StmtInterface p1 = new PrintStmt(new ConstExpression(300));

        StmtInterface expr1 = new CompStmt(c1, new CompStmt(s1, p1));

        StmtInterface s20 = new HeapAlloc("v1", new ConstExpression(2));
        StmtInterface s2 = new HeapAlloc("v2", new ConstExpression(3));
        StmtInterface s3 = new HeapAlloc("v3", new ConstExpression(4));
        StmtInterface s4 = new NewBarrier("cnt", new ReadHeap("v2"));

        StmtInterface s5 = new Await("cnt");
        StmtInterface s6 = new PrintStmt(new ReadHeap("v1"));
        StmtInterface s7 = new PrintStmt(new ReadHeap("v2"));
        StmtInterface s8 = new PrintStmt(new ReadHeap("v3"));

        StmtInterface s9 = new WriteInHeap("v1", new ArithmExpression("*", new ReadHeap("v1"), new ConstExpression(10)));
        StmtInterface s10 = new WriteInHeap("v2", new ArithmExpression("*", new ReadHeap("v2"), new ConstExpression(10)));

        StmtInterface f1 = new CompStmt(new CompStmt(s5,s9),s6);
        StmtInterface f2 = new CompStmt(s5, new CompStmt(new CompStmt(s10, s10), s7) );
        StmtInterface f3 = new CompStmt(s5, s8);

        StmtInterface c10 = new CompStmt(new CompStmt(s20,s2), new CompStmt(s3,s4));
        StmtInterface c2 = new CompStmt(c10, new CompStmt(new ForkStmt(f1), new ForkStmt(f2)));

        StmtInterface expr2 = new CompStmt(c2, f3);

        stmtList.add(expr1);
        stmtList.add(expr2);

        ObservableList<String> list = FXCollections.observableArrayList();

        for(StmtInterface s : stmtList)
            list.add("" + s);

        listView.setItems(list);

        listView.getSelectionModel().selectIndices(0);

    }

    public void buttonRun() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RunWindow.fxml"));
        loader.setController(new RunController(stmtList.get(listView.getSelectionModel().getSelectedIndex())));

        Stage stage = new Stage();
        Parent root =  loader.load();
        stage.setTitle("Running program");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
