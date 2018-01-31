package com.thoughtworks.felix.domain.change;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.felix.interfaces.dto.ChangeStageDTO;
import com.thoughtworks.felix.interfaces.dto.ChangeStagesDTO;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.felix.domain.change.ChangeStageCommand.afterDiscard;
import static com.thoughtworks.felix.domain.change.ChangeStageCommand.afterStorage;
import static com.thoughtworks.felix.support.TestHelper.readJsonFrom;
import static java.util.Arrays.asList;

public class MapperTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void should_write_list_as_string() throws JsonProcessingException {
        List<ChangeStageDTO> dtos = asList(
                new ChangeStageDTO("1", afterStorage),
                new ChangeStageDTO("2", afterDiscard)
        );
        final String content = mapper.writeValueAsString(dtos);
        System.out.println(content);
    }

    @Test
    public void should_read_simple_value() throws IOException {
        ChangeStageDTO dto = mapper.readValue(readJsonFrom("request/update-change-simple.json"), ChangeStageDTO.class);
        System.out.println(dto);
    }

    @Test
    public void should_read_list_as_map() throws IOException {
        final TypeReference<Map<String, List<ChangeStageDTO>>> ref = new TypeReference<Map<String, List<ChangeStageDTO>>>() {};
        final Map<String, List<ChangeStageDTO>> object = mapper.readValue(readJsonFrom("request/update-change.json"), ref);
        System.out.println(object);
    }

    @Test
    public void should_read_list_as_list_of_object() throws IOException {
        final TypeReference<ChangeStagesDTO> ref = new TypeReference<ChangeStagesDTO>() {};
        final ChangeStagesDTO dto = mapper.readValue(readJsonFrom("request/update-change.json"), ref);
        System.out.println(dto);
    }
}
