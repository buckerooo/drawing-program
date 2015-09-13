package com.springer.draw.commands;

import com.springer.draw.Canvas;
import com.springer.draw.Point;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DrawRectangleTest {
    @Test
    public void canCreateARectangle() {
        Canvas canvas = new Canvas(10, 10);

        new DrawRectangle(2, 2, 5, 4).draw(Optional.of(canvas));

        /* top line */
        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(3, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(4, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(5, 2)), equalTo('x'));

        /* left line */
        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 3)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(2, 4)), equalTo('x'));

        /* right line */
        assertThat(canvas.atPoint(new Point(5, 2)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(5, 3)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(5, 4)), equalTo('x'));

        /* bottom line */
        assertThat(canvas.atPoint(new Point(2, 4)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(3, 4)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(4, 4)), equalTo('x'));
        assertThat(canvas.atPoint(new Point(5, 4)), equalTo('x'));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void cannotDrawIfThereIsNoCanvas() {
        new DrawRectangle(2, 10, 2, 5).draw(Optional.empty());
    }
}