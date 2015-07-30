package com.springer.draw;

import org.junit.Test;

import static com.springer.draw.CreateCanvas.CREATE_CANVAS_COMMAND;
import static com.springer.draw.TestHelper.willThrowExceptionWithMessage;

public class CanvasTest {

    @Test
    public void cannotDrawOnABlankCanvas() {
        Canvas canvas = new Canvas();

        willThrowExceptionWithMessage((x) -> canvas.fill(new Point(1, 1), 'x'), "Unable to draw on a blank canvas, please create canvas first using " + CREATE_CANVAS_COMMAND);
    }

    @Test
    public void cannotDrawOnTheEdgeOfTheCanvas() {
        Canvas canvas = new Canvas();
        canvas.create(10, 5);

        willThrowExceptionWithMessage((x) -> canvas.fill(new Point(0, 1), 'x'), "Unable to draw out of canvas, bounds are (1,1) to (10,5)");
        willThrowExceptionWithMessage((x) -> canvas.fill(new Point(1, 0), 'x'), "Unable to draw out of canvas, bounds are (1,1) to (10,5)");
        willThrowExceptionWithMessage((x) -> canvas.fill(new Point(11, 1), 'x'), "Unable to draw out of canvas, bounds are (1,1) to (10,5)");
        willThrowExceptionWithMessage((x) -> canvas.fill(new Point(9, 6), 'x'), "Unable to draw out of canvas, bounds are (1,1) to (10,5)");
    }

}