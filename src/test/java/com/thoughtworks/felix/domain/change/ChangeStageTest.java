package com.thoughtworks.felix.domain.change;

import org.junit.Before;
import org.junit.Test;

import static com.thoughtworks.felix.domain.change.ChangeStageCommand.afterStorage;
import static com.thoughtworks.felix.domain.change.StageEnum.INCLUDED;
import static com.thoughtworks.felix.domain.change.StageEnum.PENDING;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ChangeStageTest {

    private Data data;

    @Before
    public void setUp() {
        data = mock(Data.class);
    }

    @Test
    public void should_change_stage_from_pending_to_included_when_execute_after_storage_command() throws Exception {
        final Change change = new Change("1", data, new ChangeStage("1", PENDING));
        change.updateStage(afterStorage);
        assertThat(change.getStage(), is(INCLUDED));
    }

    @Test(expected = StageTransferException.class)
    public void should_throw_exception_when_execute_after_storage_command_not_in_pending() throws Exception {
        final Change change = new Change("1", data, new ChangeStage("1", INCLUDED));
        change.updateStage(afterStorage);
    }
    
}