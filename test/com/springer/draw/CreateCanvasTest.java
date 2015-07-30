package com.springer.draw;

import org.junit.Test;

import static com.springer.draw.CreateCanvas.INVALID_CREATE_CANVAS_INPUTS_MESSAGE;
import static com.springer.draw.TestHelper.willThrowExceptionWithMessage;

public class CreateCanvasTest {

    @Test
    public void mustHaveValidInputParameters() {
        Canvas canvas = new Canvas();

        willThrowExceptionWithMessage((x) -> new CreateCanvas(canvas).draw("10"), INVALID_CREATE_CANVAS_INPUTS_MESSAGE);
        willThrowExceptionWithMessage((x) -> new CreateCanvas(canvas).draw("1 a"), INVALID_CREATE_CANVAS_INPUTS_MESSAGE);
        willThrowExceptionWithMessage((x) -> new CreateCanvas(canvas).draw("1 -7"), INVALID_CREATE_CANVAS_INPUTS_MESSAGE);
    }

}