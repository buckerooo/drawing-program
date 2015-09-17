package com.springer.draw.commands;

import com.springer.draw.app.Canvas;

import java.util.Optional;

public class CreateCanvas implements DrawCommand {

    public static final String CREATE_CANVAS_COMMAND = "C w h";

    private final Integer width;
    private final Integer height;

    public CreateCanvas(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Canvas draw(Optional<Canvas> canvas) {
        return new Canvas(width, height);
    }
}
