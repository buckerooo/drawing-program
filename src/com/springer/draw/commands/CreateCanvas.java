package com.springer.draw.commands;

import com.springer.draw.Canvas;

public class CreateCanvas implements DrawCommand {

    public static final String CREATE_CANVAS_COMMAND = "C w h";

    private final Integer width;
    private final Integer height;
    private final Canvas canvas;

    public CreateCanvas(Integer width, Integer height, Canvas canvas) {
        this.width = width;
        this.height = height;
        this.canvas = canvas;
    }

    @Override
    public void draw(String command) {
        canvas.create(width, height);
    }
}
