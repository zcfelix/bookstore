package com.thoughtworks.felix.interfaces.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Error {
    private String id;
    private List<Link> links;
    private String status;
    private String code;
    private String title;
    private String detail;

    @JsonProperty("source")
    private ErrorSource source;

    public Error() {
        this.links = new ArrayList<>();
        this.source = new ErrorSource();
    }

    public String getId() {
        return id;
    }

    public Error setId(String id) {
        this.id = id;
        return this;
    }

    public List<Link> getLinks() {
        return links;
    }

    public Error setLinks(List<Link> links) {
        this.links = links;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Error setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Error setCode(String code) {
        this.code = code;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Error setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public Error setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public ErrorSource getSource() {
        return source;
    }

    public Error setSource(ErrorSource source) {
        this.source = source;
        return this;
    }

    @JsonIgnore
    public Error setPointer(String pointer) {
        this.source.setPointer(pointer);
        return this;
    }

    @JsonIgnore
    public Error setParameter(String parameter) {
        this.source.setParameter(parameter);
        return this;
    }
}
