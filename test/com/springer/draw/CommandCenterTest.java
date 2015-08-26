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

    @Test
    public void canCreateDrawLineCommandGivenValidInputs() {
        assertThat(new CommandCenter(new Canvas()).buildCommand("L 1 2 6 2"), instanceOf(DrawLine.class));
    }

    @Test
    public void blowsUpWhenYouTryAndDrawALineWithInvalidInputs() {
        String drawLineError = "Invalid command: to draw a line you must provide x1 y1 x2 y2. Example 'L 1 2 6 2'";

        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L 1"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L 1 2"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L 1 2 6"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L 1 2 6 a"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L 1 2 6 a"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L 1 2 b 2"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L 1 c 6 2"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L d 2 6 2"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L -1 2 6 2"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L 1 -2 6 2"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L 1 2 -6 2"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L 1 2 6 -2"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L 0 2 6 2"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L 1 0 6 2"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L 1 2 0 2"), drawLineError);
        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas()).buildCommand("L 1 2 6 0"), drawLineError);

    }

}