package com.springer.draw;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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
        /* This bit takes care of the horizontal line */
        List<Character> row = canvas.get(from.y);
        row.subList(from.x, to.x + 1).replaceAll(character -> 'x');

        /* This bit takes care of the vertical line */
        List<List<Character>> allRowsForVerticalLine = canvas.subList(from.y, to.y + 1);
        allRowsForVerticalLine.forEach(row1 -> row1.subList(from.x, to.x + 1).replaceAll(character -> x));
    }

    public void fill(Point point, char fillColor) {
        fill(point, point, fillColor);
    }

    public void printCanvas(PrintStream printStream) {
        canvas.iterator().forEachRemaining(characters -> {
            characters.iterator().forEachRemaining(printStream::print);
            printStream.print("\n");
        });
    }

    public boolean isEmptySpace(Point point) {
        return canvas.get(point.y).get(point.x) == ' ';
    }

    private List<Character> charsOfSize(int width, char c) {
        List<Character> chars = new ArrayList<>();

        for(int x = 0; x < width; x++) {
            chars.add(c);
        }

        return chars;
    }
}
