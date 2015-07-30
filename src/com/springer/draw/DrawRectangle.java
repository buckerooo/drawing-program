package com.springer.draw;

public class DrawRectangle implements DrawCommand {
    private final Canvas canvas;

    public static final String INVALID_DRAW_RECTANGLE_INPUTS_MESSAGE = "Creating a rectangle must match (R x1 y1 x2 y2), where x and y values are all integer values";

    public DrawRectangle(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void draw(String command) {
        String[] positions = command.split(" ");

        if(positions.length != 4) {
            throw new IllegalArgumentException(INVALID_DRAW_RECTANGLE_INPUTS_MESSAGE);
        }

        int upperLeftX = intValue(positions[0]);
        int upperLeftY = intValue(positions[1]);
        int lowerRightX = intValue(positions[2]);
        int lowerRightY = intValue(positions[3]);

        char fillChar = 'x';

        canvas.fill(new Point(upperLeftX, upperLeftY), new Point(upperLeftX, lowerRightY), fillChar);
        canvas.fill(new Point(upperLeftX, upperLeftY), new Point(lowerRightX, upperLeftY), fillChar);
        canvas.fill(new Point(upperLeftX, lowerRightY), new Point(lowerRightX, lowerRightY), fillChar);
        canvas.fill(new Point(lowerRightX, upperLeftY), new Point(lowerRightX, lowerRightY), fillChar);
    }

    private Integer intValue(String position) {
        try {
            return Integer.valueOf(position);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DRAW_RECTANGLE_INPUTS_MESSAGE);
        }
    }
}
