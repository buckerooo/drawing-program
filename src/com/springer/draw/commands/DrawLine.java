package com.springer.draw.commands;

import com.springer.draw.Canvas;
import com.springer.draw.Point;

import java.util.Optional;

import static com.springer.draw.commands.CreateCanvas.CREATE_CANVAS_COMMAND;

public class DrawLine implements DrawCommand {
    private final Integer x1;
    private final Integer y1;
    private final Integer x2;
    private final Integer y2;

    public DrawLine(Integer x1, Integer y1, Integer x2, Integer y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public Canvas draw(Optional<Canvas> possibleCanvas) {
        if(!x1.equals(x2) && !y1.equals(y2)) {
            throw new UnsupportedOperationException("To draw a line either the x's or the y's must have the same value.");
        }

        Canvas canvas = possibleCanvas.<UnsupportedOperationException>orElseThrow(() -> {
            throw new UnsupportedOperationException("Unable to draw on a blank canvas, please create canvas first using " + CREATE_CANVAS_COMMAND);
        });

        canvas.draw(() -> canvas.fill(new Point(x1, y1), new Point(x2, y2), 'x'));

        return possibleCanvas.get();
    }

}
