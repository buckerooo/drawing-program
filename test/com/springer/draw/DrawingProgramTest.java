package com.springer.draw;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DrawingProgramTest {

    @Test
    public void shouldBeAbleToCreateACanvas() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();

        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(actualOutput), ()->{});
        drawingProgram.enterCommand("C 20 4");

        String expectedOutput =
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

        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(actualOutput), ()->{});
        drawingProgram.enterCommand("C 20 4");

        actualOutput.reset();

        drawingProgram.enterCommand("L 1 2 6 2");

        String expectedOutput =
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

        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(actualOutput), ()->{});
        drawingProgram.enterCommand("C 20 4");

        actualOutput.reset();

        drawingProgram.enterCommand("L 1 2 1 4");

        String expectedOutput =
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

        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(actualOutput), ()->{});
        drawingProgram.enterCommand("C 20 4");

        actualOutput.reset();

        drawingProgram.enterCommand("R 16 1 20 3");

        String expectedOutput =
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

        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(actualOutput), ()->{});

        drawingProgram.enterCommand("C 20 4");

        drawingProgram.enterCommand("L 1 2 6 2");
        drawingProgram.enterCommand("L 6 3 6 4");
        drawingProgram.enterCommand("R 16 1 20 3");

        actualOutput.reset();

        drawingProgram.enterCommand("B 10 3 o");

        String expectedOutput =
                        "----------------------\n" +
                        "|oooooooooooooooxxxxx|\n" +
                        "|xxxxxxooooooooox   x|\n" +
                        "|     xoooooooooxxxxx|\n" +
                        "|     xoooooooooooooo|\n" +
                        "----------------------\n" ;

        assertThat(actualOutput.toString(), equalTo(expectedOutput));
    }

    @Test
    public void shouldBeAbleToQuitTheProgram() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();

        AtomicBoolean programHasBeenTerminated = new AtomicBoolean(false);

        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(actualOutput), () -> {programHasBeenTerminated.set(true);});

        drawingProgram.enterCommand("C 20 4");

        assertThat(programHasBeenTerminated.get(), equalTo(false));
        drawingProgram.enterCommand("Q");
        assertThat(programHasBeenTerminated.get(), equalTo(true));
    }

    @Test
    public void makeSureAnyExceptionsGoToPrintStream() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();

        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(actualOutput), ()->{});
        drawingProgram.enterCommand("C 20 4");

        actualOutput.reset();

        drawingProgram.enterCommand("L 1 2 3 4");

        assertThat(actualOutput.toString(), equalTo("To draw a line either the x's or the y's must have the same value.\n"));
    }
}
