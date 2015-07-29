package com.springer.draw;

public class CreateCanvas implements DrawCommand {

    public static final String CREATE_CANVAS_COMMAND = "C w h";

    private final Canvas canvas;

    public CreateCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void draw(String command) {
        String[] inputs = command.split(" ");
        int width = Integer.valueOf(inputs[0]);
        int height = Integer.valueOf(inputs[1]);

        canvas.create(width, height);
    }
}
