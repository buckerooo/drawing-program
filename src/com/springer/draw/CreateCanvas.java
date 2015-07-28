package com.springer.draw;

import java.util.ArrayList;
import java.util.List;

public class CreateCanvas implements DrawCommand {

    private final List<List<Character>> canvas;

    public CreateCanvas(List<List<Character>> canvas) {
        this.canvas = canvas;
    }

    @Override
    public void draw(String command) {
        String[] inputs = command.split(" ");
        int width = Integer.valueOf(inputs[0]);
        int height = Integer.valueOf(inputs[1]);

        canvas.add(charsOfSize(width + 2, '-'));

        for(int rowPosition = 1; rowPosition <= height; rowPosition++) {
            List<Character> row = new ArrayList<>();
            row.addAll(charsOfSize(1, '|'));
            row.addAll(charsOfSize(width, ' '));
            row.addAll(charsOfSize(1, '|'));
            canvas.add(rowPosition, row);
        }

        canvas.add(height + 1, charsOfSize(width + 2, '-'));
    }

    private List<Character> charsOfSize(int width, char c) {
        List<Character> chars = new ArrayList<>();

        for(int x = 0; x < width; x++) {
            chars.add(c);
        }

        return chars;
    }
}
