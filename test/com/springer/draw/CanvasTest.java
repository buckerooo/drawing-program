package com.springer.draw;

import org.junit.Test;

import java.util.function.Consumer;

import static com.springer.draw.CreateCanvas.CREATE_CANVAS_COMMAND;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class CanvasTest {

    @Test
    public void cannotDrawOnABlankCanvas() {
        Canvas canvas = new Canvas();

        try {
            canvas.fill(new Point(1, 1), 'x');
            fail("Should have thrown an excpetion as the canvas has not been created yet");
        } catch (UnsupportedOperationException expected) {
            assertThat(expected.getMessage(), equalTo("Unable to draw on a blank canvas, please create canvas first using " + CREATE_CANVAS_COMMAND));
        }
    }

    @Test
    public void cannotDrawOnTheEdgeOfTheCanvas() {
        Canvas canvas = new Canvas();
        canvas.create(10, 5);

        willThrowExceptionWithMessage((x) -> canvas.fill(new Point(0, 1), 'x'), "Unable to draw out of canvas, bounds are (1,1) to (10,5)", canvas);
        willThrowExceptionWithMessage((x) -> canvas.fill(new Point(1, 0), 'x'), "Unable to draw out of canvas, bounds are (1,1) to (10,5)", canvas);
        willThrowExceptionWithMessage((x) -> canvas.fill(new Point(11, 1), 'x'), "Unable to draw out of canvas, bounds are (1,1) to (10,5)", canvas);
        willThrowExceptionWithMessage((x) -> canvas.fill(new Point(9, 6), 'x'), "Unable to draw out of canvas, bounds are (1,1) to (10,5)", canvas);
    }

    private void willThrowExceptionWithMessage(Consumer<Void> consumer, String message, Canvas canvas) {
        try {
            consumer.accept(null);
            canvas.printCanvas(System.out);
            fail("Should have thrown an exception as it was trying to draw out of the canvas");
        } catch (UnsupportedOperationException expected) {
            assertThat(expected.getMessage(), equalTo(message));
        }
    }

}