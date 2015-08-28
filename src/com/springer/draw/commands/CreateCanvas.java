package com.springer.draw.commands;

import com.springer.draw.Canvas;

import static com.springer.draw.InputsHelper.positiveIntValueFrom;

public class CreateCanvas implements DrawCommand {

    public static final String CREATE_CANVAS_COMMAND = "C w h";

    public static String INVALID_CREATE_CANVAS_INPUTS_MESSAGE = "Creating a canvas must match (" + CREATE_CANVAS_COMMAND + "), where x and y values are all positive integer values";

    private final Canvas canvas;

    public CreateCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void draw(String command) {
        String[] inputs = command.split(" ");

        if(inputs.length != 2) {
            throw new IllegalArgumentException(INVALID_CREATE_CANVAS_INPUTS_MESSAGE);
        }

        int width = positiveIntValueFrom(inputs[0], INVALID_CREATE_CANVAS_INPUTS_MESSAGE);
        int height = positiveIntValueFrom(inputs[1], INVALID_CREATE_CANVAS_INPUTS_MESSAGE);

        canvas.create(width, height);
    }
}
