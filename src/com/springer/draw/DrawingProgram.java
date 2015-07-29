package com.springer.draw;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class DrawingProgram {
    private Canvas canvas = new Canvas();

    private Map<String, DrawCommand> drawOptions = new HashMap<>();

    private final PrintStream printStream;

    public DrawingProgram(PrintStream printStream) {
        this.printStream = printStream;

        this.drawOptions.put("C", new CreateCanvas(canvas));
        this.drawOptions.put("L", new DrawLine(canvas));
        this.drawOptions.put("R", new DrawRectangle(canvas));
        this.drawOptions.put("B", new FillArea(canvas));
    }

    public void enterCommand(String command) {
        drawOptions.get(command.split(" ")[0])
                .draw(command.substring(2));

        printStream.println("enter command: " + command);
        canvas.printCanvas(printStream);
        printStream.flush();
    }
}
