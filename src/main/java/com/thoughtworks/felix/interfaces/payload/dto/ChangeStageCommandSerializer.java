package com.thoughtworks.felix.interfaces.payload.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.thoughtworks.felix.domain.change.ChangeStageCommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.thoughtworks.felix.domain.change.ChangeStageCommand.*;
import static com.thoughtworks.felix.domain.change.ChangeStageCommand.afterRevered;

public class ChangeStageCommandSerializer extends JsonSerializer {
    private static final Map<ChangeStageCommand, String> COMMAND_STRING_MAP =  new HashMap<ChangeStageCommand, String>(){{
        put(afterStorage, "after_storage");
        put(afterDiscard, "after_discard");
        put(afterRevered, "after_reverted");
    }};

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeString(COMMAND_STRING_MAP.get(value));
    }
}
