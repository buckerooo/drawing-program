package com.springer.draw.commands;

import com.springer.draw.Canvas;
import com.springer.draw.Point;

public class DrawLine implements DrawCommand {
    private final Integer x1;
    private final Integer y1;
    private final Integer x2;
    private final Integer y2;
    private final Canvas canvas;

    public static String INVALID_DRAW_LINE_INPUTS_MESSAGE = "Creating a line must match (L x1 y1 x2 y2), where x and y values are all integer values";

    public DrawLine(Integer x1, Integer y1, Integer x2, Integer y2, Canvas canvas) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.canvas = canvas;
    }

    @Override
    public void draw() {
        if(!x1.equals(x2) && !y1.equals(y2)) {
            throw new UnsupportedOperationException("To draw a line either the x's or the y's must have the same value.");
        }

        canvas.fill(new Point(x1, y1), new Point(x2, y2), 'x');
    }

}
