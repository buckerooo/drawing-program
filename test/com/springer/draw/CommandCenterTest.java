package com.springer.draw;

import org.junit.Test;

import static com.springer.draw.TestHelper.willThrowExceptionWithMessage;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class CommandCenterTest {

    @Test
    public void canCreateACanvasCommandGivenValidInputs() {
        assertThat(new CommandCenter(new Canvas()).buildCommand("C 5 10"), instanceOf(CreateCanvas.class));
    }

    @Test
    public void blowsUpWhenYouTryAndCreateACanvasWithInvalidInputs() {
        String createCanvasError = "Invalid command: to create a canvas you must provide 2 positive integers. Example 'C 5 10'";

        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("C 10 a"), createCanvasError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("C a 10"), createCanvasError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("C 5 0"), createCanvasError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("C 0 5"), createCanvasError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("C 10 -2"), createCanvasError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("C -2 10"), createCanvasError);

    }
}