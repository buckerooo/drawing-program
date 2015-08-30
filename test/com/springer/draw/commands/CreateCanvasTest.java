package com.springer.draw.commands;

import com.springer.draw.Canvas;
import com.springer.draw.Point;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CreateCanvasTest {

    @Test
    public void canCreateANewCanvas() {
        Canvas canvas = new Canvas();
        new CreateCanvas(5 ,5, canvas).draw();

        assertTrue(canvas.isEmptySpace(new Point(1, 1)));
        assertTrue(canvas.isEmptySpace(new Point(5, 5)));
    }

}