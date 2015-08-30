package com.springer.draw.commands;

import com.springer.draw.Canvas;
import com.springer.draw.Point;

public class DrawRectangle implements DrawCommand {
    private final Integer upperLeftX;
    private final Integer upperLeftY;
    private final Integer lowerRightX;
    private final Integer lowerRightY;
    private final Canvas canvas;

    public static final String INVALID_DRAW_RECTANGLE_INPUTS_MESSAGE = "Creating a rectangle must match (R x1 y1 x2 y2), where x and y values are all integer values";

    public DrawRectangle(Integer upperLeftX, Integer upperLeftY, Integer lowerRightX, Integer lowerRightY, Canvas canvas) {
        this.upperLeftX = upperLeftX;
        this.upperLeftY = upperLeftY;
        this.lowerRightX = lowerRightX;
        this.lowerRightY = lowerRightY;
        this.canvas = canvas;
    }

    @Override
    public void draw() {
        char fillChar = 'x';

        canvas.fill(new Point(upperLeftX, upperLeftY), new Point(upperLeftX, lowerRightY), fillChar);
        canvas.fill(new Point(upperLeftX, upperLeftY), new Point(lowerRightX, upperLeftY), fillChar);
        canvas.fill(new Point(upperLeftX, lowerRightY), new Point(lowerRightX, lowerRightY), fillChar);
        canvas.fill(new Point(lowerRightX, upperLeftY), new Point(lowerRightX, lowerRightY), fillChar);
    }
}
