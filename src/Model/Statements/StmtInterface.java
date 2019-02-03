package Model.Statements;

import Model.ProgramState;

public interface StmtInterface {
    ProgramState execute(ProgramState state);
}
