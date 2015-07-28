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
}
