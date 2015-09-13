package com.springer.draw;

import java.io.PrintStream;

public class Canvas {
    private final Character[][] canvas;
    private final int width;
    private final int height;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;

        this.canvas = new Character[height][width];

        for(int rowPosition = 0; rowPosition < height; rowPosition++) {
            canvas[rowPosition] = new Character[width];
        }
    }

    public void fill(Point from, Point to, char x) {
        if(from.x == 0 || from.y == 0 || to.x == 0 || to.y == 0 ||
                from.x > width || to.x > width ||
                from.y > height || to.y > height) {
            throw new UnsupportedOperationException("Unable to draw out of canvas, bounds are (1,1) to (" + width + "," + height + ")");
        }

        Point topPoint = from.y < to.y ? from : to;
        Point bottomPoint = from.y > to.y ? from : to;
        Point leftPoint = from.x < to.x ? from : to;
        Point rightPoint = from.x > to.x ? from : to;

        for (int row = topPoint.y - 1; row < bottomPoint.y; row++) {
            for(int cell = leftPoint.x - 1; cell < rightPoint.x; cell++) {
                canvas[row][cell] = x;
            }
        }
    }

    public void fill(Point point, char fillColor) {
        fill(point, point, fillColor);
    }

    public boolean isEmptySpace(Point point) {
        return canvas[point.y - 1][point.x -1] == null;
    }

    public void printCanvas(PrintStream printStream) {
        String horizontalBorder = charXTimes(width + 2, '-');
        printStream.print(horizontalBorder);
        printStream.print("\n");

        for(int row = 0; row < height; row++) {
            printStream.print("|");
            for(int cell = 0; cell < width; cell++) {
                Character character = canvas[row][cell];
                printStream.print(character == null ? ' ' : character);
            }
            printStream.print("|");
            printStream.print("\n");
        }

        printStream.print(horizontalBorder);
        printStream.print("\n");
    }

    private String charXTimes(int numberOfCoppies, char character) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numberOfCoppies; i++) {
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    public boolean isOnCanvas(Point point) {
        return point.x > 0 && point.y > 0 && point.y <= height && point.x <= width;
    }

    public char atPoint(Point point) {
        return canvas[point.y - 1][point.x - 1];
    }

    public void draw(DrawingAction command) {
        command.draw();
    }

    public interface DrawingAction {
        void draw();
    }
}
