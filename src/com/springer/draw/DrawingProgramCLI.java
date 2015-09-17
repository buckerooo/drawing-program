package com.springer.draw;

import com.springer.draw.app.DrawingProgram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class DrawingProgramCLI {
    public static void main(String[] args) throws IOException {
        PrintStream printStream = System.out;
        DrawingProgram drawingProgram = new DrawingProgram(printStream, () -> System.exit(0));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;

        printStream.print("enter command: ");

        while((input=br.readLine())!=null){
            drawingProgram.enterCommand(input);
            printStream.print("enter command: ");
        }
    }
}
