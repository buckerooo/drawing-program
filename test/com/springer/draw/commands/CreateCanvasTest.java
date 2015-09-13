package com.springer.draw.commands;

import com.springer.draw.Canvas;
import com.springer.draw.Point;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class CreateCanvasTest {

    @Test
    public void canCreateANewCanvas() {
        Canvas canvas = new Canvas(5, 5);
        new CreateCanvas(5 ,5).draw(Optional.of(canvas));

        assertTrue(canvas.isEmptySpace(new Point(1, 1)));
        assertTrue(canvas.isEmptySpace(new Point(5, 5)));
    }

}