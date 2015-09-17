package com.springer.draw.app;

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

    public boolean isEmptySpace(Point point) {
        return canvas[point.y - 1][point.x -1] == null;
    }

    public char atPoint(Point point) {
        return canvas[point.y - 1][point.x - 1];
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

    public void draw(DrawingAction command) {
        command.draw(new DrawingTool() {
            public boolean fill(Point point, char fillColor) {
                return attemptFill(point, fillColor);
            }

            public void fill(Point from, Point to, char fillColor) {
                Point topPoint = from.y < to.y ? from : to;
                Point bottomPoint = from.y > to.y ? from : to;
                Point leftPoint = from.x < to.x ? from : to;
                Point rightPoint = from.x > to.x ? from : to;

                for (int row = topPoint.y; row <= bottomPoint.y; row++) {
                    for(int cell = leftPoint.x; cell <= rightPoint.x; cell++) {
                        attemptFill(new Point(cell, row), fillColor);
                    }
                }
            }

            private boolean attemptFill(Point point, char fillColor) {
                if(point.x == 0 || point.y == 0 ||
                        point.x > width || point.y > height) {
                    return false;
                }

                Character currentChar = canvas[point.y - 1][point.x - 1];

                if(currentChar != null) {
                    return false;
                }

                canvas[point.y - 1][point.x - 1] = fillColor;
                return true;
            }
        });
    }

    public interface DrawingTool {
        boolean fill(Point point, char fillColor);
        void fill(Point from, Point to, char fillColor);
    }

    public interface DrawingAction {
        void draw(DrawingTool drawingTool);
    }
}
