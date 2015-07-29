package com.springer.draw;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class DrawLineTest {

    @Test
    public void eitherXMustBeSameOrYTheSame() {
        Canvas canvas = new Canvas();
        canvas.create(10, 10);

        try {
            new DrawLine(canvas).draw("1 2 3 4");
            fail("Either x's or y's musy be the same");
        } catch(UnsupportedOperationException expected) {
          assertThat(expected.getMessage(), equalTo("To draw a line either the x's or the y's must have the same value."));
        }
    }
}