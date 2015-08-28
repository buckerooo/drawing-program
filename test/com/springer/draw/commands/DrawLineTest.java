package com.springer.draw.commands;

import com.springer.draw.Canvas;
import com.springer.draw.Point;
import org.junit.Test;

import static com.springer.draw.TestHelper.willThrowExceptionWithMessage;
import static com.springer.draw.commands.DrawLine.INVALID_DRAW_LINE_INPUTS_MESSAGE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

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

    @Test
    public void canDrawLineRightToLeft() {
        Canvas canvas = new Canvas();
        canvas.create(20, 20);

        new DrawLine(canvas).draw("10 5 8 5");

        assertThat(canvas.atPoint(new Point(8, 5)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(9, 5)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(10, 5)), equalTo('x'));
    }

    @Test
    public void canDrawLineLeftToRight() {
        Canvas canvas = new Canvas();
        canvas.create(20, 20);

        new DrawLine(canvas).draw("8 5 10 5");

        assertThat(canvas.atPoint(new Point(8, 5)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(9, 5)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(10, 5)), equalTo('x'));
    }

    @Test
    public void canDrawLineTopToBottom() {
        Canvas canvas = new Canvas();
        canvas.create(20, 20);

        new DrawLine(canvas).draw("2 8 2 10");

        assertThat(canvas.atPoint(new Point(2, 8)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 9)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 10)), equalTo('x'));
    }

    @Test
    public void canDrawLineBottomToTop() {
        Canvas canvas = new Canvas();
        canvas.create(20, 20);

        new DrawLine(canvas).draw("2 10 2 5");

        assertThat(canvas.atPoint(new Point(2, 8)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 9)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 10)), equalTo('x'));
    }
}