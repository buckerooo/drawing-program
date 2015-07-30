package com.springer.draw;

import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class TestHelper {
    public static void willThrowExceptionWithMessage(Consumer<Void> consumer, String message) {
        try {
            consumer.accept(null);
            fail("Should have thrown an exception");
        } catch (Exception expected) {
            assertThat(expected.getMessage(), equalTo(message));
        }
    }
}
