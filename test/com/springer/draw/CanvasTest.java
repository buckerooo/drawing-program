package com.springer.draw;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.springer.draw.TestHelper.willThrowExceptionWithMessage;
import static com.springer.draw.commands.CreateCanvas.CREATE_CANVAS_COMMAND;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CanvasTest {

    @Test
    public void cannotDrawOnABlankCanvas() {
        Canvas canvas = new Canvas();

        willThrowExceptionWithMessage((x) -> canvas.fill(new Point(1, 1), 'x'), "Unable to draw on a blank canvas, please create canvas first using " + CREATE_CANVAS_COMMAND);
    }

    @Test
    public void cannotCreateACanvasThe() {
        Canvas canvas = new Canvas();

        canvas.create(10, 5);

        willThrowExceptionWithMessage((x) -> canvas.create(10, 5), "Unable to create a canvas as one as already been created");
    }

    @Test
    public void canFillInASinglePointOnTheCanvas() {
        Canvas canvas = new Canvas();

        canvas.create(3, 3);

        canvas.fill(new Point(2, 2), 'x');

        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
        assertTrue(canvas.isEmptySpace(new Point(1, 2)));
        assertTrue(canvas.isEmptySpace(new Point(2, 1)));
        assertTrue(canvas.isEmptySpace(new Point(3, 2)));
        assertTrue(canvas.isEmptySpace(new Point(2, 3)));
    }

    @Test
    public void canFillInAnAreaOnTheCanvas() {
        Canvas canvas = new Canvas();

        canvas.create(4, 4);

        canvas.fill(new Point(2, 2), new Point(3, 3), 'x');

        assertTrue(canvas.isEmptySpace(new Point(1, 2)));
        assertTrue(canvas.isEmptySpace(new Point(2, 1)));
        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(3, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(3, 3)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 3)), equalTo('x'));
        assertTrue(canvas.isEmptySpace(new Point(4, 3)));
        assertTrue(canvas.isEmptySpace(new Point(3, 4)));
    }

    @Test
    public void canFillALineFromTopToBottomOnTheCanvas() {
        Canvas canvas = new Canvas();

        canvas.create(4, 4);

        canvas.fill(new Point(2, 1), new Point(2, 4), 'x');

        assertThat(canvas.atPoint(new Point(2, 1)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 3)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 4)), equalTo('x'));
    }

    @Test
    public void canFillALineFromBottomToTopOnTheCanvas() {
        Canvas canvas = new Canvas();

        canvas.create(4, 4);

        canvas.fill(new Point(2, 4), new Point(2, 1), 'x');

        assertThat(canvas.atPoint(new Point(2, 1)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 3)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 4)), equalTo('x'));
    }

    @Test
    public void canFillALineFromLeftToRightOnTheCanvas() {
        Canvas canvas = new Canvas();

        canvas.create(4, 4);

        canvas.fill(new Point(2, 2), new Point(4, 2), 'x');

        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(3, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(4, 2)), equalTo('x'));
    }

    @Test
    public void canFillALineFromRightToLeftOnTheCanvas() {
        Canvas canvas = new Canvas();

        canvas.create(4, 4);

        canvas.fill(new Point(2, 2), new Point(4, 2), 'x');

        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(3, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(4, 2)), equalTo('x'));
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

    @Test
    public void whenPrintingTheCanvasInSticksABorderRoundTheEdge() {
        Canvas canvas = new Canvas();
        canvas.create(4, 2);

        String expectedOutput =
                        "------\n" +
                        "|    |\n" +
                        "|    |\n" +
                        "------\n";

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        canvas.printCanvas(new PrintStream(actualOutput));

        assertThat(actualOutput.toString(), equalTo(expectedOutput));
    }

}