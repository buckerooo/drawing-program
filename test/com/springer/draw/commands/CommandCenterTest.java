package com.springer.draw.commands;

import com.springer.draw.app.ProgramExiter;
import org.junit.Test;

import static com.springer.draw.ExceptionChecker.willThrowExceptionWithMessage;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class CommandCenterTest {

    private ProgramExiter programExiter = () -> {};

    @Test
    public void blowsUpIfNoCommandIsProvided() {
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand(""), "A command must be provided");
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("  "), "A command must be provided");
    }

    @Test
    public void stripsOfAnyLeadingOrTrailingWhitespace() {
        assertThat(new CommandCenter(programExiter).buildCommand(" C 5 10 "), instanceOf(CreateCanvas.class));
        assertThat(new CommandCenter(programExiter).buildCommand(" L 1 2 6 2 "), instanceOf(DrawLine.class));
        assertThat(new CommandCenter(programExiter).buildCommand(" R 16 1 20 3 "), instanceOf(DrawRectangle.class));
        assertThat(new CommandCenter(programExiter).buildCommand(" B 10 3 o "), instanceOf(FillArea.class));
        assertThat(new CommandCenter(programExiter).buildCommand(" Q "), instanceOf(QuitCommand.class));
    }

    @Test
    public void canCreateACanvasCommandGivenValidInputs() {
        assertThat(new CommandCenter(programExiter).buildCommand("C 5 10"), instanceOf(CreateCanvas.class));
        assertThat(new CommandCenter(programExiter).buildCommand("c 5 10"), instanceOf(CreateCanvas.class));
    }

    @Test
    public void blowsUpWhenYouTryAndCreateACanvasWithInvalidInputs() {
        String createCanvasError = "Invalid command: to create a canvas you must provide 2 positive integers. Example 'C 5 10'";

        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("C 10 a"), createCanvasError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("C a 10"), createCanvasError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("C 5 0"), createCanvasError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("C 0 5"), createCanvasError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("C 10 -2"), createCanvasError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("C -2 10"), createCanvasError);
    }

    @Test
    public void canCreateDrawLineCommandGivenValidInputs() {
        assertThat(new CommandCenter(programExiter).buildCommand("L 1 2 6 2"), instanceOf(DrawLine.class));
        assertThat(new CommandCenter(programExiter).buildCommand("l 1 2 6 2"), instanceOf(DrawLine.class));
    }

    @Test
    public void blowsUpWhenYouTryAndDrawALineWithInvalidInputs() {
        String drawLineError = "Invalid command: to draw a line you must provide x1 y1 x2 y2. Example 'L 1 2 6 2'";

        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L 1"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L 1 2"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L 1 2 6"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L 1 2 6 a"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L 1 2 6 a"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L 1 2 b 2"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L 1 c 6 2"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L d 2 6 2"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L -1 2 6 2"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L 1 -2 6 2"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L 1 2 -6 2"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L 1 2 6 -2"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L 0 2 6 2"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L 1 0 6 2"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L 1 2 0 2"), drawLineError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("L 1 2 6 0"), drawLineError);
    }

    @Test
    public void canCreateDrawRectangleCommandGivenValidInputs() {
        assertThat(new CommandCenter(programExiter).buildCommand("R 16 1 20 3"), instanceOf(DrawRectangle.class));
        assertThat(new CommandCenter(programExiter).buildCommand("r 16 1 20 3"), instanceOf(DrawRectangle.class));
    }

    @Test
    public void blowsUpWhenYouTryAndDrawARectangleWithInvalidInputs() {
        String drawRectangleError = "Invalid command: to draw a rectangle you must provide x1 y1 x2 y2. Example 'R 16 1 20 3'";

        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R 1"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R 1 2"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R 1 2 6"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R 1 2 6 a"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R 1 2 6 a"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R 1 2 b 2"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R 1 c 6 2"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R d 2 6 2"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R -1 2 6 2"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R 1 -2 6 2"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R 1 2 -6 2"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R 1 2 6 -2"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R 0 2 6 2"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R 1 0 6 2"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R 1 2 0 2"), drawRectangleError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("R 1 2 6 0"), drawRectangleError);
    }

    @Test
    public void canCreateFillAreaCommandGivenValidInputs() {
        assertThat(new CommandCenter(programExiter).buildCommand("B 10 3 o"), instanceOf(FillArea.class));
        assertThat(new CommandCenter(programExiter).buildCommand("b 10 3 o"), instanceOf(FillArea.class));
    }

    @Test
    public void blowsUpWhenYouTryAndCreateAFillAreaCommandWithInvalidInputs() {
        String createCanvasError = "Invalid command: to fill an area you must provide B x y c . Example 'B 10 3 o'";

        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("B"), createCanvasError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("B 10"), createCanvasError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("B 10 5"), createCanvasError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("B 10 a 0"), createCanvasError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("B a 5 0"), createCanvasError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("B 10 0 o"), createCanvasError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("B 0 5 o"), createCanvasError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("B 10 -5 o"), createCanvasError);
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("B -10 5 o"), createCanvasError);
//        willThrowExceptionWithMessage((x) -> new CommandCenter(new Canvas(10, 10)).buildCommand("B 10 5 o"), createCanvasError);
    }

    @Test
    public void canCreateAQuitCommand() {
        assertThat(new CommandCenter(() -> {}).buildCommand("Q"), instanceOf(QuitCommand.class));
        assertThat(new CommandCenter(() -> {}).buildCommand("q"), instanceOf(QuitCommand.class));
    }

    @Test
    public void blowsUpIfYouPassInAUnknownCommand() {
        willThrowExceptionWithMessage(() -> new CommandCenter(programExiter).buildCommand("F"), "The command 'F' is not supported");
    }
}