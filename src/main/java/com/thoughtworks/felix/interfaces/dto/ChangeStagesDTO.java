package com.thoughtworks.felix.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ChangeStagesDTO {

    @JsonProperty("data")
    private List<ChangeStageDTO> changeStageDTOS;

    public List<ChangeStageDTO> getChangeStageDTOS() {
        return changeStageDTOS;
    }

    public void setChangeStageDTOS(List<ChangeStageDTO> changeStageDTOS) {
        this.changeStageDTOS = changeStageDTOS;
    }
}
