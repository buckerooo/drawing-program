package com.springer.draw;

import org.junit.Test;

import static com.springer.draw.DrawLine.INVALID_DRAW_LINE_INPUTS_MESSAGE;
import static com.springer.draw.TestHelper.willThrowExceptionWithMessage;

public class DrawLineTest {

    @Test
    public void eitherXMustBeSameOrYTheSame() {
        Canvas canvas = new Canvas();
        canvas.create(10, 10);

        willThrowExceptionWithMessage((x) -> new DrawLine(canvas).draw("1 2 3 4"),
                "To draw a line either the x's or the y's must have the same value.");
    }

    @Test
    public void mustHaveValidInputParameters() {
        Canvas canvas = new Canvas();

        willThrowExceptionWithMessage((x) -> new DrawLine(canvas).draw("1"), INVALID_DRAW_LINE_INPUTS_MESSAGE);
        willThrowExceptionWithMessage((x) -> new DrawLine(canvas).draw("1 2"), INVALID_DRAW_LINE_INPUTS_MESSAGE);
        willThrowExceptionWithMessage((x) -> new DrawLine(canvas).draw("1 2 3"), INVALID_DRAW_LINE_INPUTS_MESSAGE);
        willThrowExceptionWithMessage((x) -> new DrawLine(canvas).draw("1 2 3 a"), INVALID_DRAW_LINE_INPUTS_MESSAGE);
    }
}