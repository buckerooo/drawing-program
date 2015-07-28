package com.springer.draw;

import java.util.List;

public class DrawLine implements DrawCommand {
    private final List<List<Character>> canvas;

    public DrawLine(List<List<Character>> canvas) {
        this.canvas = canvas;
    }

    @Override
    public void draw(String command) {
        String[] positions = command.split(" ");

        int x1 = Integer.valueOf(positions[0]);
        int y1 = Integer.valueOf(positions[1]);
        int x2 = Integer.valueOf(positions[2]);
        int y2 = Integer.valueOf(positions[3]);

        /* This bit takes care of the horizontal line */
        List<Character> row = canvas.get(y1);
        row.subList(x1, x2 + 1).replaceAll(character -> 'x');

        /* This bit takes care of the vertical line */
        List<List<Character>> allRowsForVerticalLine = canvas.subList(y1, y2 + 1);
        allRowsForVerticalLine.forEach(row1 -> row1.subList(x1, x2 + 1).replaceAll(character -> 'x'));
    }
}
