package com.springer.draw;

public class DrawLine implements DrawCommand {
    private final Canvas canvas;

    public DrawLine(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void draw(String command) {
        String[] positions = command.split(" ");

        int x1 = Integer.valueOf(positions[0]);
        int y1 = Integer.valueOf(positions[1]);
        int x2 = Integer.valueOf(positions[2]);
        int y2 = Integer.valueOf(positions[3]);

        if(x1 != x2 && y1 != y2) {
            throw new UnsupportedOperationException("To draw a line either the x's or the y's must have the same value.");
        }

        canvas.fill(new Point(x1, y1), new Point(x2, y2), 'x');
    }
}
