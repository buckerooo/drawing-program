package com.springer.draw.commands;

import com.springer.draw.Canvas;
import org.junit.Test;

import static com.springer.draw.TestHelper.willThrowExceptionWithMessage;
import static com.springer.draw.commands.CreateCanvas.INVALID_CREATE_CANVAS_INPUTS_MESSAGE;

public class CreateCanvasTest {

    @Test
    public void mustHaveValidInputParameters() {
        Canvas canvas = new Canvas();

        willThrowExceptionWithMessage((x) -> new CreateCanvas(canvas).draw("10"), INVALID_CREATE_CANVAS_INPUTS_MESSAGE);
        willThrowExceptionWithMessage((x) -> new CreateCanvas(canvas).draw("1 a"), INVALID_CREATE_CANVAS_INPUTS_MESSAGE);
        willThrowExceptionWithMessage((x) -> new CreateCanvas(canvas).draw("1 -7"), INVALID_CREATE_CANVAS_INPUTS_MESSAGE);
    }

}