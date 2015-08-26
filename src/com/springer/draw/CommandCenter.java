package com.springer.draw;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;

public class CommandCenter {

    private final Map<String, CommandBuilder> drawOptions = new HashMap<>();

    /* todo: do i want to support upper and lower case chars */
    public CommandCenter(Canvas canvas) {
        this.drawOptions.put("C", inputs -> {
            /* note: this would not support a user entering 02 for example as a number */
            Matcher matcher = compile("C ([1-9]\\d*) ([1-9]\\d*)").matcher(inputs);

            if(matcher.matches()) {
                return new CreateCanvas(canvas);
            } else {
                throw new IllegalArgumentException("Invalid command: to create a canvas you must provide 2 positive integers. Example 'C 5 10'");
            }
        });
        this.drawOptions.put("L", inputs -> {
            Matcher matcher = compile("L ([1-9]\\d*) ([1-9]\\d*) ([1-9]\\d*) ([1-9]\\d*)").matcher(inputs);

            if(matcher.matches()) {
                return new DrawLine(canvas);
            } else {
                throw new IllegalArgumentException("Invalid command: to draw a line you must provide x1 y1 x2 y2. Example 'L 1 2 6 2'");
            }
        });
        this.drawOptions.put("R", inputs -> new DrawRectangle(canvas));
        this.drawOptions.put("B", inputs -> new FillArea(canvas));
    }

    private interface CommandBuilder {
        DrawCommand create(String inputs);
    }

    public DrawCommand buildCommand(String input) {
        String command = input.split(" ")[0];

        return drawOptions.get(command).create(input);
    }
}
