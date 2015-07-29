package com.springer.draw;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DrawingProgramTest {

    @Test
    public void shouldBeAbleToCreateACanvas() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();

        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(actualOutput));
        drawingProgram.enterCommand("C 20 4");

        String expectedOutput =
                "enter command: C 20 4\n"  +
                "----------------------\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n" ;

        assertThat(actualOutput.toString(), equalTo(expectedOutput));
    }

    @Test
    public void shouldBeAbleToDrawAHorizontalLineOnTheCanvas() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();

        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(actualOutput));
        drawingProgram.enterCommand("C 20 4");

        actualOutput.reset();

        drawingProgram.enterCommand("L 1 2 6 2");

        String expectedOutput =
                        "enter command: L 1 2 6 2\n"  +
                        "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n" ;

        assertThat(actualOutput.toString(), equalTo(expectedOutput));
    }

    @Test
    public void shouldBeAbleToDrawAVerticalLineOnTheCanvas() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();

        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(actualOutput));
        drawingProgram.enterCommand("C 20 4");

        actualOutput.reset();

        drawingProgram.enterCommand("L 1 2 1 4");

        String expectedOutput =
                        "enter command: L 1 2 1 4\n"  +
                        "----------------------\n" +
                        "|                    |\n" +
                        "|x                   |\n" +
                        "|x                   |\n" +
                        "|x                   |\n" +
                        "----------------------\n" ;

        assertThat(actualOutput.toString(), equalTo(expectedOutput));
    }

    @Test
    public void shouldBeAbleToDrawARectangleOnTheCanvas() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();

        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(actualOutput));
        drawingProgram.enterCommand("C 20 4");

        actualOutput.reset();

        drawingProgram.enterCommand("R 16 1 20 3");

        String expectedOutput =
                        "enter command: R 16 1 20 3\n"  +
                        "----------------------\n" +
                        "|               xxxxx|\n" +
                        "|               x   x|\n" +
                        "|               xxxxx|\n" +
                        "|                    |\n" +
                        "----------------------\n" ;

        assertThat(actualOutput.toString(), equalTo(expectedOutput));
    }

    @Test
    public void shouldBeAbleToFillAnAreaOnTheCanvasWithAColor() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();

        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(actualOutput));

        drawingProgram.enterCommand("C 20 4");

        drawingProgram.enterCommand("L 1 2 6 2");
        drawingProgram.enterCommand("L 6 3 6 4");
        drawingProgram.enterCommand("R 16 1 20 3");

        actualOutput.reset();

        drawingProgram.enterCommand("B 10 3 o");

        String expectedOutput =
                "enter command: B 10 3 o\n"  +
                        "----------------------\n" +
                        "|oooooooooooooooxxxxx|\n" +
                        "|xxxxxxooooooooox   x|\n" +
                        "|     xoooooooooxxxxx|\n" +
                        "|     xoooooooooooooo|\n" +
                        "----------------------\n" ;

        assertThat(actualOutput.toString(), equalTo(expectedOutput));
    }
}
