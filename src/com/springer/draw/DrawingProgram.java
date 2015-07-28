package com.springer.draw;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawingProgram {
    private List<List<Character>> canvas = new ArrayList<>();

    private Map<String, DrawCommand> drawOptions = new HashMap<>();

    private final PrintStream printStream;

    public DrawingProgram(PrintStream printStream) {
        this.printStream = printStream;

        this.drawOptions.put("C", new CreateCanvas(canvas));
    }

    public void enterCommand(String command) {
        drawOptions.get(command.split(" ")[0])
                .draw(command.substring(2));

        printCommand(command);
        printCanvas();
        printStream.flush();
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
}
