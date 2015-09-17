package com.springer.draw.commands;

import com.springer.draw.Canvas;
import com.springer.draw.Point;

import java.util.Optional;
import java.util.Stack;

import static com.springer.draw.commands.CreateCanvas.CREATE_CANVAS_COMMAND;

public class FillArea implements DrawCommand {
    private final Integer x;
    private final Integer y;
    private final char fillColor;

    public FillArea(Integer x, Integer y, char fillColor) {
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
    }

    @Override
    public Canvas draw(Optional<Canvas> possibleCanvas) {
        Canvas canvas = possibleCanvas.<UnsupportedOperationException>orElseThrow(() -> {
            throw new UnsupportedOperationException("Unable to draw on a blank canvas, please create canvas first using " + CREATE_CANVAS_COMMAND);
        });

        canvas.draw((canvasFiller) -> {
            Stack<Point> stack = new Stack<>();
            stack.push(new Point(x, y));

            while (!stack.empty()) {
                Point point = stack.pop();
                if (canvasFiller.fill(point, fillColor)) {

                    stack.push(new Point(point.x, point.y + 1));
                    stack.push(new Point(point.x, point.y - 1));
                    stack.push(new Point(point.x + 1, point.y));
                    stack.push(new Point(point.x - 1, point.y));
                }
            }
        });

        return canvas;
    }
}
