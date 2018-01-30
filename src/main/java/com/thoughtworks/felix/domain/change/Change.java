package com.thoughtworks.felix.domain.change;

public class Change {
    private String id;
    private Data data;
    private ChangeStage changeStage;

    public Change(String id, Data data, ChangeStage changeStage) {
        this.id = id;
        this.data = data;
        this.changeStage = changeStage;
    }

    public String getId() {
        return id;
    }

    public Data getData() {
        return data;
    }

    public ChangeStage getChangeStage() {
        return changeStage;
    }

    public StageEnum getStage() {
        return changeStage.getStage();
    }

    public void updateStage(ChangeStageCommand command) throws Exception {
        changeStage.updateStage(command);
    }
}
