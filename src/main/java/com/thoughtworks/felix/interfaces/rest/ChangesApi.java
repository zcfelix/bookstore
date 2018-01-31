package com.thoughtworks.felix.interfaces.rest;

import com.thoughtworks.felix.interfaces.dto.ChangeStagesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/changes/stage")
public class ChangesApi {

//    @PutMapping
//    public ResponseEntity updateChanges(@RequestBody List<ChangeStageDTO> dto) {
////        dto.getChangeId();
//        dto.get(0).getChangeId();
//        return ResponseEntity.noContent().build();
//    }

    @PutMapping
    public ResponseEntity updateChanges(@RequestBody ChangeStagesDTO dto) {
        dto.getChangeStageDTOS();
        return ResponseEntity.noContent().build();
    }
}
