package com.springer.draw.app;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class CanvasTest {

    @Test
    public void canFillInASinglePointOnTheCanvas() {
        Canvas canvas = new Canvas(3, 3);

        canvas.draw(canvasFiller -> canvasFiller.fill(new Point(2, 2), 'x', false));

        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
        assertTrue(canvas.isEmptySpace(new Point(1, 2)));
        assertTrue(canvas.isEmptySpace(new Point(2, 1)));
        assertTrue(canvas.isEmptySpace(new Point(3, 2)));
        assertTrue(canvas.isEmptySpace(new Point(2, 3)));
    }

    @Test
    public void canOverwriteASinglePointOnTheCanvas() {
        Canvas canvas = new Canvas(3, 3);

        canvas.draw(canvasFiller -> canvasFiller.fill(new Point(2, 2), 'x', false));
        canvas.draw(canvasFiller -> assertTrue(canvasFiller.fill(new Point(2, 2), 'o', true)));

        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('o'));
    }

    @Test
    public void canNotOverwriteASinglePointOnTheCanvas() {
        Canvas canvas = new Canvas(3, 3);

        canvas.draw(canvasFiller -> canvasFiller.fill(new Point(2, 2), 'x', false));
        canvas.draw(canvasFiller -> assertFalse(canvasFiller.fill(new Point(2, 2), 'o', false)));

        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
    }

    @Test
    public void canOverwriteAnAreaOnTheCanvas() {
        Canvas canvas = new Canvas(4, 4);

        canvas.draw(canvasFiller -> canvasFiller.fill(new Point(2, 2), new Point(3, 3), 'x', false));
        canvas.draw(canvasFiller -> canvasFiller.fill(new Point(2, 2), new Point(3, 3), 'o', true));

        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('o'));
        assertThat(canvas.atPoint(new Point(3, 2)), equalTo('o'));
        assertThat(canvas.atPoint(new Point(3, 3)), equalTo('o'));
        assertThat(canvas.atPoint(new Point(2, 3)), equalTo('o'));
    }

    @Test
    public void canNotOverwriteAnAreaOnTheCanvas() {
        Canvas canvas = new Canvas(4, 4);

        canvas.draw(canvasFiller -> canvasFiller.fill(new Point(2, 2), new Point(3, 3), 'x', false));
        canvas.draw(canvasFiller -> canvasFiller.fill(new Point(2, 2), new Point(3, 3), 'o', false));

        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(3, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(3, 3)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 3)), equalTo('x'));
    }

    @Test
    public void canFillInAnAreaOnTheCanvas() {
        Canvas canvas = new Canvas(4, 4);

        canvas.draw(canvasFiller -> canvasFiller.fill(new Point(2, 2), new Point(3, 3), 'x', true));

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
        Canvas canvas = new Canvas(4, 4);

        canvas.draw(canvasFiller -> canvasFiller.fill(new Point(2, 1), new Point(2, 4), 'x', true));

        assertThat(canvas.atPoint(new Point(2, 1)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 3)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 4)), equalTo('x'));
    }

    @Test
    public void canFillALineFromBottomToTopOnTheCanvas() {
        Canvas canvas = new Canvas(4, 4);

        canvas.draw(canvasFiller -> canvasFiller.fill(new Point(2, 4), new Point(2, 1), 'x', true));

        assertThat(canvas.atPoint(new Point(2, 1)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 3)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 4)), equalTo('x'));
    }

    @Test
    public void canFillALineFromLeftToRightOnTheCanvas() {
        Canvas canvas = new Canvas(4, 4);

        canvas.draw(canvasFiller -> canvasFiller.fill(new Point(2, 2), new Point(4, 2), 'x', true));

        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(3, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(4, 2)), equalTo('x'));
    }

    @Test
    public void canFillALineFromRightToLeftOnTheCanvas() {
        Canvas canvas = new Canvas(4, 4);

        canvas.draw(canvasFiller -> canvasFiller.fill(new Point(2, 2), new Point(4, 2), 'x', true));

        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(3, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(4, 2)), equalTo('x'));
    }

    @Test
    public void cannotDrawOnTheEdgeOfTheCanvas() {
        Canvas canvas = new Canvas(10, 5);

        canvas.draw(canvasFiller -> assertThat(canvasFiller.fill(new Point(0, 1), 'x', false), equalTo(false)));
        canvas.draw(canvasFiller -> assertThat(canvasFiller.fill(new Point(1, 0), 'x', false), equalTo(false)));
        canvas.draw(canvasFiller -> assertThat(canvasFiller.fill(new Point(11, 1), 'x', false), equalTo(false)));
        canvas.draw(canvasFiller -> assertThat(canvasFiller.fill(new Point(9, 6), 'x', false), equalTo(false)));
    }

    @Test
    public void whenPrintingTheCanvasInSticksABorderRoundTheEdge() {
        Canvas canvas = new Canvas(4, 2);

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