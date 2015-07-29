package com.springer.draw;

public class FillArea implements DrawCommand {
    private final Canvas canvas;

    public FillArea(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void draw(String command) {
        String[] positions = command.split(" ");

        int x = Integer.valueOf(positions[0]);
        int y = Integer.valueOf(positions[1]);
        char fillColor = positions[2].charAt(0);

        fill(new Point(x, y), fillColor);
    }

    private void fill(Point point, char fillColor) {
        if(!canvas.isEmptySpace(point)) {
            return;
        }

        canvas.fill(point, fillColor);

        fill(new Point(point.x, point.y + 1), fillColor);
        fill(new Point(point.x, point.y - 1), fillColor);
        fill(new Point(point.x + 1, point.y), fillColor);
        fill(new Point(point.x - 1, point.y), fillColor);

    }
}
