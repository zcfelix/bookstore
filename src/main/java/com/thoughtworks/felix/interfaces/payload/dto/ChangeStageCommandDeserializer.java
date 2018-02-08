package com.thoughtworks.felix.interfaces.payload.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.thoughtworks.felix.domain.change.ChangeStageCommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.thoughtworks.felix.domain.change.ChangeStageCommand.*;

public class ChangeStageCommandDeserializer extends JsonDeserializer {
    private static final Map<String, ChangeStageCommand> COMMAND_MAP = new HashMap<String, ChangeStageCommand>(){{
        put("after_storage", afterStorage);
        put("after_discard", afterDiscard);
        put("after_reverted", afterRevered);
    }};

    @Override
    public ChangeStageCommand deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        final String key = p.getText().toLowerCase();
        return COMMAND_MAP.get(key);
    }
}
