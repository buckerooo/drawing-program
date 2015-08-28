package com.springer.draw.commands;

import com.springer.draw.ProgramExiter;

public class QuitCommand implements DrawCommand {
    private final ProgramExiter exiter;

    public QuitCommand(ProgramExiter exiter) {
        this.exiter = exiter;
    }

    @Override
    public void draw(String command) {
        exiter.exit();
    }
}
