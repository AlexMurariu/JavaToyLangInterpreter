package GUI;

import Model.FileHandling.FileData;
import Model.FileHandling.FileTable;
import Model.FileHandling.FileTableInterface;
import Model.ProgramState;
import Model.Statements.*;
import Model.Utils.*;
import Model.UtilsTables.BarrierTableView;
import Model.UtilsTables.FileTableView;
import Model.UtilsTables.HeapTableView;
import Model.UtilsTables.SymTableView;
import Repo.Repo;
import Repo.RepoInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RunController {

    @FXML private TableView<BarrierTableView> barrierTable;
    @FXML private TableColumn<BarrierTableView, Integer> barrierID;
    @FXML private TableColumn<BarrierTableView, Integer> barrierValue;
    @FXML private TableColumn<BarrierTableView, String> barrierList;

    @FXML private TableView<HeapTableView> heapTable;
    @FXML private TableColumn<HeapTableView, Integer> heapAddressColumn;
    @FXML private TableColumn<HeapTableView, Integer> heapValueColumn;

    @FXML private TableView<FileTableView> fileTable;
    @FXML private TableColumn<FileTableView, Integer> fileTableIdentifier;
    @FXML private TableColumn<FileTableView, String> fileTableName;

    @FXML private TableView<SymTableView> symTable;
    @FXML private TableColumn<SymTableView, String> symTableVarName;
    @FXML private TableColumn<SymTableView, Integer> symTableValue;

    @FXML private ListView<String> outList;
    @FXML private ListView<String> exeStackList;
    @FXML private ListView<String> noPrgStateID;
    @FXML private Button oneStepButton;
    @FXML private TextField noPrgState;

    private Controller ctrl;
    private StmtInterface stmt;

    public RunController(StmtInterface stmt){
        this.stmt = stmt;
    }


    @FXML
    public void initialize(){

        StackInterface<StmtInterface> ExeStack = new Stack<>();
        DictionaryInterface<String, Integer> SymTable = new Dictionary<String, Integer>();
        ListInterface<Integer> out = new MyList<Integer>();
        FileTableInterface<Integer, FileData> fileTable = new FileTable<>();
        HeapInterface<Integer, Integer> heapTable = new Heap<>();
        BarrierTableInterface<Integer, MyPair> barrierTable = new BarrierTable<>();

        ExeStack.add(stmt);

        ProgramState prg = new ProgramState(ExeStack, SymTable, out, fileTable, heapTable, barrierTable, 1);
        RepoInterface repo = new Repo();
        repo.addPrg(prg);
        ctrl = new Controller(repo);

        this.barrierID.setCellValueFactory(new PropertyValueFactory<>("address"));
        this.barrierValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        this.barrierList.setCellValueFactory(new PropertyValueFactory<>("list"));
        this.heapAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        this.heapValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        this.fileTableIdentifier.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.fileTableName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        this.symTableVarName.setCellValueFactory(new PropertyValueFactory<>("varName"));
        this.symTableValue.setCellValueFactory(new PropertyValueFactory<>("value"));

        setNoPrgState();
        setNoPrgStateID();
        noPrgStateID.getSelectionModel().select(0);
        setExeStack();
    }

    private void setNoPrgState(){
        Integer number = ctrl.noPrgStates();
        noPrgState.setText(String.valueOf(number));
    }

    private void setNoPrgStateID(){
        noPrgStateID.setItems(ctrl.getPrgStateID());
    }

    ProgramState getCurrentPrgState(){
        int index = noPrgStateID.getSelectionModel().getSelectedIndex();
        if(index == -1)
            index = 0;
        return ctrl.getPrgStateByIndex(index);
    }

   private void setBarrierTable(){

        ObservableList<BarrierTableView> list = FXCollections.observableArrayList();
        ProgramState programState = getCurrentPrgState();

        for(Integer key: programState.getBarrierTable().getAll()){

            String l = new String();

            for(Integer s : programState.getBarrierTable().get(key).getFirst())
                l += " "+s;

            list.add(new BarrierTableView(key, programState.getBarrierTable().get(key).getSecond(), l) );
        }


        barrierTable.setItems(list);
    }

    private void setExeStack(){
        ObservableList<String> list = FXCollections.observableArrayList();
        ProgramState prg = getCurrentPrgState();

        for(StmtInterface s : prg.getStack().getElements())
            list.add("" + s);

        exeStackList.setItems(list);
    }

    private void setHeapTable(){
        ObservableList<HeapTableView> list = FXCollections.observableArrayList();
        ProgramState prg = getCurrentPrgState();

        for(Integer key : prg.getHeap().getKeys())
            list.add(new HeapTableView(key, prg.getHeap().getVal(key)));

        heapTable.setItems(list);
    }

    private void setFileTable(){
        ObservableList<FileTableView> list = FXCollections.observableArrayList();
        ProgramState prg = getCurrentPrgState();

        for(Integer key : prg.getFileTable().getElements())
            list.add(new FileTableView(key, prg.getFileTable().get(key).getFileName()));

        fileTable.setItems(list);
    }

    private void setSymTable(){
        ObservableList<SymTableView> list = FXCollections.observableArrayList();
        ProgramState prg = getCurrentPrgState();

        for(String key : prg.getSymTable().getElements())
            list.add(new SymTableView(key, prg.getSymTable().getValue(key)));

        symTable.setItems(list);
    }

    private void setOutList(){
        ObservableList<String> list = FXCollections.observableArrayList();
        ProgramState prg = getCurrentPrgState();

        for(Integer i : prg.getList().getElements())
            list.add("" + i);

        outList.setItems(list);
    }

    private void setAll(){
        setNoPrgState();
        setNoPrgStateID();
        setExeStack();
        setHeapTable();
        setFileTable();
        setSymTable();
        setOutList();
        setBarrierTable();
    }
    @FXML
    public void mouseClick(){
        if(ctrl.noPrgStates() > 0){
            setAll();
        }
    }
    public void buttonExecute(ActionEvent ev){
        try{
            if(ctrl.oneStepGUI())
                setAll();
            else{
                setNoPrgState();
                setNoPrgStateID();
                symTable.getItems().clear();
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Program finished!");
                a.show();
            }
        }
        catch (RuntimeException e){
            Node source = (Node) ev.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            Alert a = new Alert(Alert.AlertType.ERROR, e.getMessage());
            a.show();
            stage.close();
        }
    }

}
