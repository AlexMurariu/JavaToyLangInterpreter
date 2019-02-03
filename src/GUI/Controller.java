package GUI;

import Model.Exp.ControllerException;
import Model.ProgramState;
import Repo.RepoInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {

    private RepoInterface repo;
    private ExecutorService executor;

    public Controller(RepoInterface repo) {

        this.repo = repo;
        this.executor = Executors.newFixedThreadPool(5);
    }

    public ProgramState getPrgStateByIndex(int index){
        return repo.getPrgList().get(index);
    }

    public ObservableList<String> getPrgStateID(){
        ObservableList<String> list = FXCollections.observableArrayList();
        for(ProgramState ps : repo.getPrgList())
            list.add(String.valueOf(ps.getId()));
        return list;
    }

    public int noPrgStates() {
        return repo.getPrgList().size();
    }

    private Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer, Integer> heap) {
        return heap.entrySet().stream().filter(e -> symTableValues.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey,
                Map.Entry::getValue));
    }

    public ProgramState closeAllFiles(ProgramState state) {
        state.getFileTable().getAllElems().stream().forEach(item -> {
            try {
                item.getReader().close();
            } catch (IOException e){
                System.out.println("File can't be close!");
            }
        });
        state.clearFileTable();
        state.clearFilesFromSymTable();
        return null;
    }

    List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList){
        return inPrgList.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
    }

    public void oneStepForAll(List<ProgramState> prgList){
        prgList.forEach(prg->repo.logPrgStateExec(prg));

        List<Callable<ProgramState>> callList = prgList.stream().map((ProgramState p) -> (Callable<ProgramState>)
                (() -> {return p.oneStep();})).collect(Collectors.toList());
        List<ProgramState> newPrgList = null;
        try {
            newPrgList = executor.invokeAll(callList).stream().map(future -> {try{return future.get();}
            catch (InterruptedException e){ throw  new ControllerException(e.getMessage());}
            catch (ExecutionException e) {throw new ControllerException(e.getMessage());}
            }).filter(p -> p != null).collect(Collectors.toList());
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        prgList.addAll(newPrgList);
        prgList.forEach(prg->repo.logPrgStateExec(prg));

        repo.setPrgList(prgList);
    }

    public void allSteps() {
        List<ProgramState> prgList = removeCompletedPrg(repo.getPrgList());
        while(prgList.size() > 0){
            prgList.forEach(prg->prg.getHeap().setContent(conservativeGarbageCollector(prg.getSymTable().values(), prg.getHeap().getContent())));
            oneStepForAll(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdown();
        prgList.forEach(prg->closeAllFiles(prg));
        repo.setPrgList(prgList);
    }

    public boolean oneStepGUI(){
        List<ProgramState> prgList = removeCompletedPrg(repo.getPrgList());

        if(prgList.size() > 0){
            oneStepForAll(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());
            return true;
        }
        else{
            executor.shutdownNow();
            repo.setPrgList(prgList);
            return false;
        }
    }

    @Override
    public String toString() {
        return "Controller: " + repo.toString();
    }
}
