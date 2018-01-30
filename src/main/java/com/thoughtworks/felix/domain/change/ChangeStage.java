package com.thoughtworks.felix.domain.change;

import static com.thoughtworks.felix.domain.TimeHelper.currentTime;

public class ChangeStage {
    private String changeId;
    private StageEnum stage;
    private String createdAt;
    private String updatedAt;

    public ChangeStage(String changeId, StageEnum stage) {
        this.changeId = changeId;
        this.stage = stage;
        this.createdAt = currentTime();
        this.updatedAt = currentTime();
    }

    public String getChangeId() {
        return changeId;
    }

    public StageEnum getStage() {
        return stage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void updateStage(ChangeStageCommand command) throws Exception {
        stage = command.update(this).checkedGet();
    }
}
