package com.springer.draw.commands;

import com.springer.draw.Canvas;
import com.springer.draw.Point;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class FillAreaTest {

    @Test
    public void canFillALargeArea() {
        int width = 100;
        int height = 100;

        Canvas canvas = new Canvas();
        canvas.create(width, height);

        new FillArea(1, 1, 'o', canvas).draw();

        for (int x = 1; x < width; x++) {
            for (int y = 1; y < height; y++) {
                 assertThat(canvas.atPoint(new Point(x, y)), equalTo('o'));

            }
        }
    }
}