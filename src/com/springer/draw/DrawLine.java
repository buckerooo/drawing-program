package com.springer.draw;

import static com.springer.draw.InputsHelper.positiveIntValueFrom;

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

        int x1 = positiveIntValueFrom(positions[0], INVALID_DRAW_LINE_INPUTS_MESSAGE);
        int y1 = positiveIntValueFrom(positions[1], INVALID_DRAW_LINE_INPUTS_MESSAGE);
        int x2 = positiveIntValueFrom(positions[2], INVALID_DRAW_LINE_INPUTS_MESSAGE);
        int y2 = positiveIntValueFrom(positions[3], INVALID_DRAW_LINE_INPUTS_MESSAGE);

        if(x1 != x2 && y1 != y2) {
            throw new UnsupportedOperationException("To draw a line either the x's or the y's must have the same value.");
        }

        canvas.fill(new Point(x1, y1), new Point(x2, y2), 'x');
    }

}
