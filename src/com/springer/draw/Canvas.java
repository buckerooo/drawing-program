package com.springer.draw;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static com.springer.draw.commands.CreateCanvas.CREATE_CANVAS_COMMAND;

public class Canvas {
    private List<List<Character>> canvas = new ArrayList<>();

    public void create(int width, int height) {
        canvas.add(charsOfSize(width + 2, '-'));

        for(int rowPosition = 1; rowPosition <= height; rowPosition++) {
            List<Character> row = new ArrayList<>();
            row.addAll(charsOfSize(1, '|'));
            row.addAll(charsOfSize(width, ' '));
            row.addAll(charsOfSize(1, '|'));
            canvas.add(rowPosition, row);
        }

        canvas.add(height + 1, charsOfSize(width + 2, '-'));
    }

    public void fill(Point from, Point to, char x) {
        hasCanvasBeenCreated();
        arePointsWithinBounds(from, to);

        Point topPoint = from.y < to.y ? from : to;
        Point bottomPoint = from.y > to.y ? from : to;
        Point leftPoint = from.x < to.x ? from : to;
        Point rightPoint = from.x > to.x ? from : to;

        List<List<Character>> allRowsThatNeedABitOfFilling = canvas.subList(topPoint.y, bottomPoint.y + 1);
        allRowsThatNeedABitOfFilling.forEach(row1 -> row1.subList(leftPoint.x, rightPoint.x + 1).replaceAll(character -> x));
    }

    public void fill(Point point, char fillColor) {
        fill(point, point, fillColor);
    }

    public boolean isEmptySpace(Point point) {
        return canvas.get(point.y).get(point.x) == ' ';
    }

    public void printCanvas(PrintStream printStream) {
        canvas.iterator().forEachRemaining(characters -> {
            characters.iterator().forEachRemaining(printStream::print);
            printStream.print("\n");
        });
    }

    private void hasCanvasBeenCreated() {
        if (canvas.size() == 0) {
            throw new UnsupportedOperationException("Unable to draw on a blank canvas, please create canvas first using " + CREATE_CANVAS_COMMAND);
        }
    }

    private void arePointsWithinBounds(Point from, Point to) {
        int maximumX = canvas.get(0).size() - 2;
        int maximumY = canvas.size() - 2;
        if(from.x == 0 || from.y == 0 || to.x == 0 || to.y == 0 ||
                from.x > maximumX || to.x > maximumX ||
                from.y > maximumY || to.y > maximumY) {
            throw new UnsupportedOperationException("Unable to draw out of canvas, bounds are (1,1) to (" + maximumX + "," + maximumY + ")");
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
        return canvas.get(point.y).get(point.x);
    }
}
