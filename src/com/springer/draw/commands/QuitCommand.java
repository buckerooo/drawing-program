package com.springer.draw.commands;

import com.springer.draw.Canvas;
import com.springer.draw.ProgramExiter;

import java.util.Optional;

public class QuitCommand implements DrawCommand {
    private final ProgramExiter exiter;

    public QuitCommand(ProgramExiter exiter) {
        this.exiter = exiter;
    }

    @Override
    public Canvas draw(Optional<Canvas> canvas) {
        exiter.exit();
        return null;
    }
}
