package com.springer.draw;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;

public class CommandCenter {

    private final Map<String, CommandBuilder> drawOptions = new HashMap<>();

    public CommandCenter(Canvas canvas) {
        this.drawOptions.put("C", inputs -> {
            /* note: this would not support a user entering 02 for example as a number */
            Matcher matcher = compile("C ([1-9]\\d*) ([1-9]\\d*)").matcher(inputs);

            if(matcher.matches()) {
                return new CreateCanvas(canvas);
            } else {
                throw new IllegalArgumentException("Invalid command: to create a canvas you must provide 2 positive integers. Example 'C 5 10'");
            }
        }); /* C 20 4 */
        this.drawOptions.put("L", inputs -> new DrawLine(canvas));
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
