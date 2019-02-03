package Repo;


import Model.ProgramState;

import java.util.List;

public interface RepoInterface {
    void addPrg(ProgramState prg);
    List<ProgramState> getPrgList();
    void setPrgList(List<ProgramState> list);
    void logPrgStateExec(ProgramState state);
}