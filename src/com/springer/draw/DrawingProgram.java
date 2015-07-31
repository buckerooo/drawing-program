package com.springer.draw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class DrawingProgram {
    private Canvas canvas = new Canvas();

    private Map<String, DrawCommand> drawOptions = new HashMap<>();

    private final PrintStream printStream;
    private final ProgramExiter programExiter;

    public DrawingProgram() {
        this(System.out, () -> System.exit(0));
    }

    public interface ProgramExiter {
        void exit();
    }

    public DrawingProgram(PrintStream printStream, ProgramExiter programExiter) {
        this.printStream = printStream;
        this.programExiter = programExiter;

        this.drawOptions.put("C", new CreateCanvas(canvas));
        this.drawOptions.put("L", new DrawLine(canvas));
        this.drawOptions.put("R", new DrawRectangle(canvas));
        this.drawOptions.put("B", new FillArea(canvas));
    }

    public void enterCommand(String command) {
        if(command.equals("Q")) {
            programExiter.exit();
            return;
        }

        try {
            drawOptions.get(command.split(" ")[0])
                    .draw(command.substring(2));

            canvas.printCanvas(printStream);
        } catch (Exception e) {
            printStream.println(e.getMessage());
        }

        printStream.flush();
    }

    private void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;

        printStream.print("enter command: ");

        while((input=br.readLine())!=null){
            enterCommand(input);
            printStream.print("enter command: ");
        }
    }

    public static void main(String[] args) throws IOException {
        new DrawingProgram().start();
    }
}
