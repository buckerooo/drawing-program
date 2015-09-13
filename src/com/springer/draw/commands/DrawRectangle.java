package com.springer.draw.commands;

import com.springer.draw.Canvas;
import com.springer.draw.Point;

import java.util.Optional;

import static com.springer.draw.commands.CreateCanvas.CREATE_CANVAS_COMMAND;

public class DrawRectangle implements DrawCommand {
    private final Integer upperLeftX;
    private final Integer upperLeftY;
    private final Integer lowerRightX;
    private final Integer lowerRightY;

    public DrawRectangle(Integer upperLeftX, Integer upperLeftY, Integer lowerRightX, Integer lowerRightY) {
        this.upperLeftX = upperLeftX;
        this.upperLeftY = upperLeftY;
        this.lowerRightX = lowerRightX;
        this.lowerRightY = lowerRightY;
    }

    @Override
    public Canvas draw(Optional<Canvas> possibleCanvas) {
        char fillChar = 'x';

        Canvas canvas = possibleCanvas.<UnsupportedOperationException>orElseThrow(() -> {
            throw new UnsupportedOperationException("Unable to draw on a blank canvas, please create canvas first using " + CREATE_CANVAS_COMMAND);
        });

        canvas.draw(() -> {
            canvas.fill(new Point(upperLeftX, upperLeftY), new Point(upperLeftX, lowerRightY), fillChar);
            canvas.fill(new Point(upperLeftX, upperLeftY), new Point(lowerRightX, upperLeftY), fillChar);
            canvas.fill(new Point(upperLeftX, lowerRightY), new Point(lowerRightX, lowerRightY), fillChar);
            canvas.fill(new Point(lowerRightX, upperLeftY), new Point(lowerRightX, lowerRightY), fillChar);
        });

        return canvas;
    }
}
