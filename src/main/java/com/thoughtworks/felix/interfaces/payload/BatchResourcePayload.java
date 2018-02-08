package com.thoughtworks.felix.interfaces.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatchResourcePayload<T> {
    @Valid
    @JsonProperty("data")
    private List<HicResource<T>> resources;

    @JsonProperty("meta")
    private Map<String, Object> meta;

    @JsonProperty("links")
    private List<Link> links;

    public List<HicResource<T>> getResources() {
        return resources;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public List<Link> getLinks() {
        return links;
    }
}
