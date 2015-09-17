package com.springer.draw.commands;

import com.springer.draw.app.ProgramExiter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;

public class CommandCenter {

    private final Map<String, CommandBuilder> drawOptions = new HashMap<>();

    public CommandCenter(ProgramExiter programExiter) {
        /* note: this would not support a user entering 02 for example as a number */

        this.drawOptions.put("C", inputs -> {
            Matcher matcher = compile("C ([1-9]\\d*) ([1-9]\\d*)", CASE_INSENSITIVE).matcher(inputs);
            if (matcher.matches()) {
                Integer x = Integer.valueOf(matcher.group(1));
                Integer y = Integer.valueOf(matcher.group(2));

                return new CreateCanvas(x, y);
            } else {
                throw new IllegalArgumentException("Invalid command: to create a canvas you must provide 2 positive integers. Example 'C 5 10'");
            }
        });
        this.drawOptions.put("L", inputs -> {
            Matcher matcher = compile("L ([1-9]\\d*) ([1-9]\\d*) ([1-9]\\d*) ([1-9]\\d*)", CASE_INSENSITIVE).matcher(inputs);
            if (matcher.matches()) {
                Integer x1 = Integer.valueOf(matcher.group(1));
                Integer y1 = Integer.valueOf(matcher.group(2));
                Integer x2 = Integer.valueOf(matcher.group(3));
                Integer y2 = Integer.valueOf(matcher.group(4));
                return new DrawLine(x1, y1, x2, y2);
            } else {
                throw new IllegalArgumentException("Invalid command: to draw a line you must provide x1 y1 x2 y2. Example 'L 1 2 6 2'");
            }
        });
        this.drawOptions.put("R", inputs -> {
            Matcher matcher = compile("R ([1-9]\\d*) ([1-9]\\d*) ([1-9]\\d*) ([1-9]\\d*)", CASE_INSENSITIVE).matcher(inputs);
            if (matcher.matches()) {
                Integer upperLeftX = Integer.valueOf(matcher.group(1));
                Integer upperLeftY = Integer.valueOf(matcher.group(2));
                Integer lowerRightX = Integer.valueOf(matcher.group(3));
                Integer lowerRightY = Integer.valueOf(matcher.group(4));
                return new DrawRectangle(upperLeftX, upperLeftY, lowerRightX, lowerRightY);
            } else {
                throw new IllegalArgumentException("Invalid command: to draw a rectangle you must provide x1 y1 x2 y2. Example 'R 16 1 20 3'");
            }
        });
        this.drawOptions.put("B", inputs -> {
            /* todo: the pattern only picks out the first char for the fill. Look into blowing up */
            Matcher matcher = compile("B ([1-9]\\d*) ([1-9]\\d*) (\\w{1})", CASE_INSENSITIVE).matcher(inputs);
            if (matcher.matches()) {
                Integer x = Integer.valueOf(matcher.group(1));
                Integer y = Integer.valueOf(matcher.group(2));
                char fill = matcher.group(3).charAt(0);
                return new FillArea(x, y, fill);
            } else {
                throw new IllegalArgumentException("Invalid command: to fill an area you must provide B x y c . Example 'B 10 3 o'");
            }
        });
        this.drawOptions.put("Q", inputs -> new QuitCommand(programExiter));
    }

    private interface CommandBuilder {
        DrawCommand create(String inputs);
    }

    public DrawCommand buildCommand(String input) {
        if(input.trim().isEmpty()) {
            throw new IllegalArgumentException("A command must be provided");
        }

        String command = input.split(" ")[0].toUpperCase();

        CommandBuilder commandBuilder = drawOptions.get(command);

        if(commandBuilder == null) {
            throw new IllegalArgumentException("The command '" + command  + "' is not supported");
        }

        return commandBuilder.create(input);
    }
}
