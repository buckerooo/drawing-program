package com.springer.draw;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static com.springer.draw.commands.CreateCanvas.CREATE_CANVAS_COMMAND;

public class Canvas {
    private List<List<Character>> canvas = new ArrayList<>();

    private int width;
    private int height;

    public void create(int width, int height) {
        if(!canvas.isEmpty()) {
            throw new IllegalArgumentException("Unable to create a canvas as one as already been created");
        }

        this.width = width;
        this.height = height;

        for(int rowPosition = 0; rowPosition < height; rowPosition++) {
            List<Character> row = new ArrayList<>();
            row.addAll(charsOfSize(width, ' '));
            canvas.add(rowPosition, row);
        }
    }

    public void fill(Point from, Point to, char x) {
        hasCanvasBeenCreated();
        arePointsWithinBounds(from, to);

        Point topPoint = from.y < to.y ? from : to;
        Point bottomPoint = from.y > to.y ? from : to;
        Point leftPoint = from.x < to.x ? from : to;
        Point rightPoint = from.x > to.x ? from : to;

        List<List<Character>> allRowsThatNeedABitOfFilling = canvas.subList(topPoint.y - 1, bottomPoint.y);
        allRowsThatNeedABitOfFilling.forEach(row1 -> row1.subList(leftPoint.x - 1, rightPoint.x).replaceAll(character -> x));
    }

    public void fill(Point point, char fillColor) {
        fill(point, point, fillColor);
    }

    public boolean isEmptySpace(Point point) {
        hasCanvasBeenCreated();
        return canvas.get(point.y - 1).get(point.x -1 ) == ' ';
    }

    public void printCanvas(PrintStream printStream) {
        printStream.print(charXTimes(width + 2, '-'));
        printStream.print("\n");
        canvas.iterator().forEachRemaining(characters -> {
            printStream.print("|");
            characters.iterator().forEachRemaining(printStream::print);
            printStream.print("|");
            printStream.print("\n");
        });
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
        if (canvas.size() == 0) {
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

    private List<Character> charsOfSize(int width, char c) {
        List<Character> chars = new ArrayList<>();

        for(int x = 0; x < width; x++) {
            chars.add(c);
        }

        return chars;
    }

    public char atPoint(Point point) {
        return canvas.get(point.y - 1).get(point.x - 1);
    }
}
