package com.thoughtworks.felix.interfaces.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thoughtworks.felix.domain.change.ChangeStageCommand;
import com.thoughtworks.felix.interfaces.validation.StageCommand;

public class ChangeStageDTO {
    @JsonProperty("change_id")
    private String changeId;

    @JsonProperty("stage_command")
    @JsonSerialize(using = ChangeStageCommandSerializer.class)
    @JsonDeserialize(using = ChangeStageCommandDeserializer.class)
    @StageCommand
    private ChangeStageCommand changeStageCommand;

    public ChangeStageDTO(String changeId, ChangeStageCommand changeStageCommand) {
        this.changeId = changeId;
        this.changeStageCommand = changeStageCommand;
    }

    private ChangeStageDTO() {
    }

    public String getChangeId() {
        return changeId;
    }

    public void setChangeId(String changeId) {
        this.changeId = changeId;
    }

    public ChangeStageCommand getChangeStageCommand() {
        return changeStageCommand;
    }

    public void setChangeStageCommand(ChangeStageCommand changeStageCommand) {
        this.changeStageCommand = changeStageCommand;
    }
}
