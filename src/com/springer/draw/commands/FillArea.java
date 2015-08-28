package com.springer.draw.commands;

import com.springer.draw.Canvas;
import com.springer.draw.Point;

import java.util.Stack;

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

        Stack<Point> stack = new Stack<>();
        stack.push(new Point(x, y));

        while(!stack.empty()) {
            Point point = stack.pop();
            if(canvas.isEmptySpace(point)) {
                canvas.fill(point, fillColor);

                stack.push(new Point(point.x, point.y + 1));
                stack.push(new Point(point.x, point.y - 1));
                stack.push(new Point(point.x + 1, point.y));
                stack.push(new Point(point.x - 1, point.y));
            }
        }

    }
}
