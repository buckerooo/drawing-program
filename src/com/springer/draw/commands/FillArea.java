package com.springer.draw.commands;

import com.springer.draw.Canvas;
import com.springer.draw.Point;

import java.util.Stack;

public class FillArea implements DrawCommand {
    private final Integer x;
    private final Integer y;
    private final char fillColor;
    private final Canvas canvas;

    public FillArea(Integer x, Integer y, char fillColor, Canvas canvas) {
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
        this.canvas = canvas;
    }

    @Override
    public void draw() {
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
