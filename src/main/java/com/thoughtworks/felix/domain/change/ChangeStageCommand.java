package com.thoughtworks.felix.domain.change;

import com.thoughtworks.felix.util.Try;
import com.thoughtworks.felix.util.Try.Failure;
import com.thoughtworks.felix.util.Try.Success;

import static com.thoughtworks.felix.domain.change.StageEnum.*;
import static com.thoughtworks.felix.domain.change.StageTransferErrorCode.CHANGE_STAGE_TRANSFER_ERROR;

@FunctionalInterface
public interface ChangeStageCommand {
    Try<StageEnum> update(ChangeStage changeStage);

    ChangeStageCommand afterStorage = changeStage -> changeStage.getStage().equals(PENDING) ?
            new Success<>(INCLUDED) :
            new Failure<>(new StageTransferException(CHANGE_STAGE_TRANSFER_ERROR.formatMessage(changeStage.getStage().toString(), INCLUDED.toString())));

    ChangeStageCommand afterDiscard = changeStage -> changeStage.getStage().equals(PENDING) ?
            new Success<>(EXCLUDED) :
            new Failure<>(new StageTransferException(CHANGE_STAGE_TRANSFER_ERROR.formatMessage(changeStage.getStage().toString(), EXCLUDED.toString())));

    ChangeStageCommand afterRevered = changeStage -> changeStage.getStage().equals(INCLUDED) ?
            new Success<>(REVERTED) :
            new Failure<>(new StageTransferException(CHANGE_STAGE_TRANSFER_ERROR.formatMessage(changeStage.getStage().toString(), REVERTED.toString())));
}
