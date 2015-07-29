package com.springer.draw;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Canvas {
    private List<List<Character>> canvas = new ArrayList<>();

    public void fill(Point from, Point to, char x) {
        /* This bit takes care of the horizontal line */
        List<Character> row = canvas.get(from.y);
        row.subList(from.x, to.x + 1).replaceAll(character -> 'x');

        /* This bit takes care of the vertical line */
        List<List<Character>> allRowsForVerticalLine = canvas.subList(from.y, to.y + 1);
        allRowsForVerticalLine.forEach(row1 -> row1.subList(from.x, to.x + 1).replaceAll(character -> x));
    }

    public void add(List<Character> characters) {
        canvas.add(characters);
    }

    public void add(int rowPosition, List<Character> row) {
        canvas.add(rowPosition, row);
    }

    public void printCanvas(PrintStream printStream) {
        canvas.iterator().forEachRemaining(characters -> {
            characters.iterator().forEachRemaining(printStream::print);
            printStream.print("\n");
        });
    }

    public void fill(Point point, char fillColor) {
        fill(point, point, fillColor);
    }

    public boolean isEmptySpace(Point point) {
        return canvas.get(point.y).get(point.x) == ' ';
    }
}
