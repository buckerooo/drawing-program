package com.springer.draw.app;

import com.springer.draw.commands.CommandCenter;

import java.io.PrintStream;
import java.util.Optional;

public class DrawingProgram {
    private final CommandCenter commandCenter;
    private final PrintStream printStream;

    private Optional<Canvas> canvas = Optional.empty();

    public DrawingProgram(PrintStream printStream, ProgramExiter programExiter) {
        this.printStream = printStream;
        this.commandCenter = new CommandCenter(programExiter);
    }

    public void enterCommand(String command) {
        try {

            canvas = Optional.ofNullable(commandCenter.buildCommand(command).draw(canvas));

            canvas.get().printCanvas(printStream);
        } catch (Exception e) {
            e.printStackTrace();
            printStream.println(e.getMessage());
        }

        printStream.flush();
    }
}
