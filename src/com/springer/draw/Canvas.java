package com.springer.draw;

import java.io.PrintStream;

import static com.springer.draw.commands.CreateCanvas.CREATE_CANVAS_COMMAND;

public class Canvas {
    private Character[][] canvas;
    private int width;
    private int height;

    public void create(int width, int height) {
        if(canvas != null) {
            throw new IllegalArgumentException("Unable to create a canvas as one as already been created");
        }

        this.width = width;
        this.height = height;

        this.canvas = new Character[height][width];

        for(int rowPosition = 0; rowPosition < height; rowPosition++) {
            canvas[rowPosition] = new Character[width];
        }
    }

    public void fill(Point from, Point to, char x) {
        hasCanvasBeenCreated();
        arePointsWithinBounds(from, to);

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
        hasCanvasBeenCreated();
        return canvas[point.y - 1][point.x -1] == null;
    }

    public void printCanvas(PrintStream printStream) {
        printStream.print(charXTimes(width + 2, '-'));
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

        printStream.print(charXTimes(width + 2, '-'));
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
        hasCanvasBeenCreated();
        return point.x > 0 && point.y > 0 && point.y <= height && point.x <= width;
    }

    private void hasCanvasBeenCreated() {
        if (canvas == null) {
            throw new UnsupportedOperationException("Unable to draw on a blank canvas, please create canvas first using " + CREATE_CANVAS_COMMAND);
        }
    }

    private void arePointsWithinBounds(Point from, Point to) {
        if(from.x == 0 || from.y == 0 || to.x == 0 || to.y == 0 ||
                from.x > width || to.x > width ||
                from.y > height || to.y > height) {
            throw new UnsupportedOperationException("Unable to draw out of canvas, bounds are (1,1) to (" + width + "," + height + ")");
        }
    }

    public char atPoint(Point point) {
        return canvas[point.y - 1][point.x - 1];
    }
}
