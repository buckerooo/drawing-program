package com.springer.draw.commands;

import com.springer.draw.app.Canvas;
import com.springer.draw.app.Point;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class FillAreaTest {

    @Test
    public void canFillALargeArea() {
        int width = 100;
        int height = 100;

        Canvas canvas = new Canvas(width, height);

        new FillArea(1, 1, 'o').draw(Optional.of(canvas));

        for (int x = 1; x < width; x++) {
            for (int y = 1; y < height; y++) {
                 assertThat(canvas.atPoint(new Point(x, y)), equalTo('o'));
            }
        }
    }

    @Test
    public void canFillAnAreaInsideARectangle() {
        int width = 5;
        int height = 5;
        Canvas canvas = new Canvas(width, height);

        new DrawRectangle(1, 1, 4, 4).draw(Optional.of(canvas));

        new FillArea(2, 2, 'o').draw(Optional.of(canvas));

        assertThat(canvas.atPoint(new Point(2, 2)), equalTo('o'));
        assertThat(canvas.atPoint(new Point(3, 2)), equalTo('o'));
        assertThat(canvas.atPoint(new Point(2, 3)), equalTo('o'));
        assertThat(canvas.atPoint(new Point(3, 3)), equalTo('o'));

        /* check the outside has not been filled */
        for(int y = 1; y <= height; y++) {
            assertTrue(canvas.isEmptySpace(new Point(width, y)));
        }
        for(int x = 1; x <= width; x++) {
            assertTrue(canvas.isEmptySpace(new Point(x, height)));
        }
    }

    @Test
    public void canFillTheOutsideOfARectangle() {
        int width = 5;
        int height = 5;
        Canvas canvas = new Canvas(width, height);

        new DrawRectangle(1, 1, 4, 4).draw(Optional.of(canvas));

        new FillArea(5, 1, 'o').draw(Optional.of(canvas));

        assertTrue(canvas.isEmptySpace(new Point(2, 2)));
        assertTrue(canvas.isEmptySpace(new Point(3, 2)));
        assertTrue(canvas.isEmptySpace(new Point(2, 3)));
        assertTrue(canvas.isEmptySpace(new Point(3, 3)));

        /* check the outside has been filled */
        for(int y = 1; y <= height; y++) {
            assertThat(canvas.atPoint(new Point(width, y)), equalTo('o'));
        }
        for(int x = 1; x <= width; x++) {
            assertThat(canvas.atPoint(new Point(x, height)), equalTo('o'));
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void cannotDrawIfThereIsNoCanvas() {
        new FillArea(1, 1, 'o').draw(Optional.empty());
    }
}