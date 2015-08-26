package com.springer.draw;

import java.util.HashMap;
import java.util.Map;

public class CommandCenter {

    private final Map<String, DrawCommand> drawOptions = new HashMap<>();

    public CommandCenter(Canvas canvas) {
        this.drawOptions.put("C", new CreateCanvas(canvas));
        this.drawOptions.put("L", new DrawLine(canvas));
        this.drawOptions.put("R", new DrawRectangle(canvas));
        this.drawOptions.put("B", new FillArea(canvas));
    }

    public DrawCommand buildCommand(String input) {
        return drawOptions.get(input.split(" ")[0]);
    }
}
