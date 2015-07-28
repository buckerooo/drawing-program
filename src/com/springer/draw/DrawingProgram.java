package com.springer.draw;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class DrawingProgram {
    private List<List<Character>> canvas = new ArrayList<>();

    private final PrintStream printStream;

    public DrawingProgram(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void enterCommand(String command) {
        String[] inputs = command.split(" ");
        int width = Integer.valueOf(inputs[1]);
        int height = Integer.valueOf(inputs[2]);

        canvas.add(charsOfSize(width + 2, '-'));

        for(int rowPosition = 1; rowPosition <= height; rowPosition++) {
            List<Character> row = new ArrayList<>();
            row.addAll(charsOfSize(1, '|'));
            row.addAll(charsOfSize(width, ' '));
            row.addAll(charsOfSize(1, '|'));
            canvas.add(rowPosition, row);
        }

        canvas.add(height + 1, charsOfSize(width + 2, '-'));

        printCommand(command);
        printCanvas();
    }

    private void printCommand(String command) {
        printStream.println("enter command: " + command);
    }

    private void printCanvas() {
        canvas.iterator().forEachRemaining(characters -> {
            characters.iterator().forEachRemaining(printStream::print);
            printStream.print("\n");
        });
    }

    private List<Character> charsOfSize(int width, char c) {
        List<Character> chars = new ArrayList<>();

        for(int x = 0; x < width; x++) {
            chars.add(c);
        }

        return chars;
    }
}
