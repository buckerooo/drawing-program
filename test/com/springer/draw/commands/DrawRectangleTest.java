package com.springer.draw.commands;

import com.springer.draw.Canvas;
import org.junit.Test;

import static com.springer.draw.TestHelper.willThrowExceptionWithMessage;
import static com.springer.draw.commands.DrawRectangle.INVALID_DRAW_RECTANGLE_INPUTS_MESSAGE;

public class DrawRectangleTest {
    @Test
    public void fourIntParametersRequired() {
        DrawRectangle drawRectangle = new DrawRectangle(new Canvas());

        willThrowExceptionWithMessage((x) -> drawRectangle.draw("1"), INVALID_DRAW_RECTANGLE_INPUTS_MESSAGE);
        willThrowExceptionWithMessage((x) -> drawRectangle.draw("1 2 3"), INVALID_DRAW_RECTANGLE_INPUTS_MESSAGE);
        willThrowExceptionWithMessage((x) -> drawRectangle.draw("1 2 3 a"), INVALID_DRAW_RECTANGLE_INPUTS_MESSAGE);
    }
}