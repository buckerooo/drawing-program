package com.springer.draw;

public class DrawRectangle implements DrawCommand {
    private final Canvas canvas;

    public DrawRectangle(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void draw(String command) {
        String[] positions = command.split(" ");

        int upperLeftX = Integer.valueOf(positions[0]);
        int upperLeftY = Integer.valueOf(positions[1]);
        int lowerRightX = Integer.valueOf(positions[2]);
        int lowerRightY = Integer.valueOf(positions[3]);

        canvas.fill(new Point(upperLeftX, upperLeftY), new Point(upperLeftX, lowerRightY), 'x');
        canvas.fill(new Point(upperLeftX, upperLeftY), new Point(lowerRightX, upperLeftY), 'x');
        canvas.fill(new Point(upperLeftX, lowerRightY), new Point(lowerRightX, lowerRightY), 'x');
        canvas.fill(new Point(lowerRightX, upperLeftY), new Point(lowerRightX, lowerRightY), 'x');
    }
}
