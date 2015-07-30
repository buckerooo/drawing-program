package com.springer.draw;

import org.junit.Test;

import static com.springer.draw.DrawRectangle.INVALID_DRAW_RECTANGLE_INPUTS_MESSAGE;
import static com.springer.draw.TestHelper.willThrowExceptionWithMessage;

public class DrawRectangleTest {
    @Test
    public void fourIntParametersRequired() {
        DrawRectangle drawRectangle = new DrawRectangle(new Canvas());

        willThrowExceptionWithMessage((x) -> drawRectangle.draw("1"), INVALID_DRAW_RECTANGLE_INPUTS_MESSAGE);
        willThrowExceptionWithMessage((x) -> drawRectangle.draw("1 2 3"), INVALID_DRAW_RECTANGLE_INPUTS_MESSAGE);
        willThrowExceptionWithMessage((x) -> drawRectangle.draw("1 2 3 a"), INVALID_DRAW_RECTANGLE_INPUTS_MESSAGE);
    }
}