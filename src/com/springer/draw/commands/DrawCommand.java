package com.springer.draw.commands;

import com.springer.draw.app.Canvas;

import java.util.Optional;

public interface DrawCommand {
    Canvas draw(Optional<Canvas> canvas);
}
