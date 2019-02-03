package Model.Statements;

import Model.ProgramState;

public class Await implements StmtInterface {

    private String varName;

    public Await(String varName) {
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        if(!state.getSymTable().exists(varName))
            throw new RuntimeException("This" + varName + " does not exist in SymTable");

        int id = state.getSymTable().getValue(varName);

        if(!state.getBarrierTable().contains(id))
            throw new RuntimeException("This" + id + " does not exist in BarrierTable");

        synchronized (state.getBarrierTable()){
            if(state.getBarrierTable().get(id).getFirst().size() < state.getBarrierTable().get(id).getSecond())
                if(state.getBarrierTable().get(id).getFirst().contains(state.getId()))
                    state.getStack().add(this);
                else {
                    state.getBarrierTable().get(id).getFirst().add(state.getId());
                    state.getStack().add(this);
                }
        }
        return null;
    }

    @Override
    public String toString(){
        return "Await("+varName+")";
    }
}
