package com.thoughtworks.felix.interfaces.rest;

import com.thoughtworks.felix.interfaces.payload.dto.ChangeStageDTO;
import com.thoughtworks.felix.interfaces.payload.BatchResourcePayload;
import com.thoughtworks.felix.interfaces.payload.HicResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static com.thoughtworks.felix.application.service.BindingResultResolver.parseErrors;

@RestController
@RequestMapping("/changes/stage")
public class ChangesApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChangesApi.class);

    @PutMapping
    public ResponseEntity updateChanges(@Valid @RequestBody BatchResourcePayload<ChangeStageDTO> payload,
                                        BindingResult result,
                                        HttpServletRequest request) {
        LOGGER.info(request.toString());
//        Link link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ChangesApi.class).updateChanges(payload, result, request)).withSelfRel();
        if (result.hasErrors()) {
            throw new BadRequestException(parseErrors(result));
        }
        final List<HicResource<ChangeStageDTO>> resources = payload.getResources();
        return ResponseEntity.noContent().build();
    }
}
