package com.thoughtworks.felix.domain.change;

import java.text.MessageFormat;

public enum StageTransferErrorCode implements ErrorCode {
    CHANGE_STAGE_TRANSFER_ERROR("状态{0}不能迁移到状态{1}");

    private final MessageFormat messageFormat;

    StageTransferErrorCode(String format) {
        this.messageFormat = new MessageFormat(format);
    }

    @Override
    public String message() {
        return null;
    }

    @Override
    public String title() {
        return null;
    }

    @Override
    public String detail() {
        return null;
    }

    public String formatMessage(String... params) {
        return messageFormat.format(params);
    }
}
