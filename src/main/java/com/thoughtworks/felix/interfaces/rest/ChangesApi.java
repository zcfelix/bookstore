package com.thoughtworks.felix.interfaces.rest;

import com.thoughtworks.felix.interfaces.dto.ChangeStageDTO;
import com.thoughtworks.felix.interfaces.request.BatchPayloadResource;
import com.thoughtworks.felix.interfaces.request.HicResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/changes/stage")
public class ChangesApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChangesApi.class);

    @PutMapping
    public ResponseEntity updateChanges(@Valid @RequestBody BatchPayloadResource<ChangeStageDTO> payload,
                                        BindingResult result,
                                        HttpServletRequest request) {
        LOGGER.info(request.toString());
        Link link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ChangesApi.class).updateChanges(payload, result, request)).withSelfRel();
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            throw new RequestInvalidException(result, link);
        }
        final List<HicResource<ChangeStageDTO>> resources = payload.getResources();
        return ResponseEntity.noContent().build();
    }
}
