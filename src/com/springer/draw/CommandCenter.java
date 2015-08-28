package com.springer.draw;

import java.util.HashMap;
import java.util.Map;

import static java.util.regex.Pattern.compile;

public class CommandCenter {

    private final Map<String, CommandBuilder> drawOptions = new HashMap<>();

    /* todo: do i want to support upper and lower case chars */

    public CommandCenter(Canvas canvas) {
        /* note: this would not support a user entering 02 for example as a number */

        this.drawOptions.put("C", inputs -> {
            if (compile("C ([1-9]\\d*) ([1-9]\\d*)").matcher(inputs).matches()) {
                return new CreateCanvas(canvas);
            } else {
                throw new IllegalArgumentException("Invalid command: to create a canvas you must provide 2 positive integers. Example 'C 5 10'");
            }
        });
        this.drawOptions.put("L", inputs -> {
            if (compile("L ([1-9]\\d*) ([1-9]\\d*) ([1-9]\\d*) ([1-9]\\d*)").matcher(inputs).matches()) {
                return new DrawLine(canvas);
            } else {
                throw new IllegalArgumentException("Invalid command: to draw a line you must provide x1 y1 x2 y2. Example 'L 1 2 6 2'");
            }
        });
        this.drawOptions.put("R", inputs -> {
            if (compile("R ([1-9]\\d*) ([1-9]\\d*) ([1-9]\\d*) ([1-9]\\d*)").matcher(inputs).matches()) {
                return new DrawRectangle(canvas);
            } else {
                throw new IllegalArgumentException("Invalid command: to draw a rectangle you must provide x1 y1 x2 y2. Example 'R 16 1 20 3'");
            }
        });
        this.drawOptions.put("B", inputs -> {
            /* todo: the pattern only picks out the first char for the fill. Look into blowing up */
            if (compile("B ([1-9]\\d*) ([1-9]\\d*) (\\w{1})").matcher(inputs).matches()) {
                return new FillArea(canvas);
            } else {
                throw new IllegalArgumentException("Invalid command: to fill an area you must provide B x y c . Example 'B 10 3 o'");
            }
        });
    }

    private interface CommandBuilder {
        DrawCommand create(String inputs);
    }

    public DrawCommand buildCommand(String input) {
        String command = input.split(" ")[0];

        return drawOptions.get(command).create(input);
    }
}
