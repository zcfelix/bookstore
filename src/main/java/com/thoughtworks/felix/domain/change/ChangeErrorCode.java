package com.thoughtworks.felix.domain.change;

import com.thoughtworks.felix.ErrorCode;
import org.springframework.http.HttpStatus;

public enum ChangeErrorCode implements ErrorCode {

    Transfer_Error(400, "AUDIT-400-001", "Stage transfer failure");

    private HttpStatus httpStatus;
    private String code;
    private String title;

    ChangeErrorCode(int httpStatus, String code, String title) {
        this.httpStatus = HttpStatus.valueOf(httpStatus);
        this.code = code;
        this.title = title;
    }

    @Override
    public HttpStatus status() {
        return httpStatus;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String title() {
        return title;
    }
}
