package com.springer.draw;

public class DrawLine implements DrawCommand {
    private final Canvas canvas;

    public static String INVALID_DRAW_LINE_INPUTS_MESSAGE = "Creating a line must match (L x1 y1 x2 y2), where x and y values are all integer values";

    public DrawLine(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void draw(String command) {
        String[] positions = command.split(" ");

        if(positions.length != 4) {
            throw new IllegalArgumentException(INVALID_DRAW_LINE_INPUTS_MESSAGE);
        }

        int x1 = intValue(positions[0]);
        int y1 = intValue(positions[1]);
        int x2 = intValue(positions[2]);
        int y2 = intValue(positions[3]);

        if(x1 != x2 && y1 != y2) {
            throw new UnsupportedOperationException("To draw a line either the x's or the y's must have the same value.");
        }

        canvas.fill(new Point(x1, y1), new Point(x2, y2), 'x');
    }

    private Integer intValue(String position) {
        try {
            return Integer.valueOf(position);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DRAW_LINE_INPUTS_MESSAGE);
        }
    }
}
