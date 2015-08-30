package com.springer.draw;

import java.io.PrintStream;

public class DrawingProgram {
    private final CommandCenter commandCenter;
    private final Canvas canvas = new Canvas();

    private final PrintStream printStream;

    public DrawingProgram(PrintStream printStream, ProgramExiter programExiter) {
        this.printStream = printStream;
        this.commandCenter = new CommandCenter(canvas, programExiter);
    }

    public void enterCommand(String command) {
        try {
            commandCenter.buildCommand(command).draw();
            canvas.printCanvas(printStream);
        } catch (Exception e) {
            printStream.println(e.getMessage());
        }

        printStream.flush();
    }
}
