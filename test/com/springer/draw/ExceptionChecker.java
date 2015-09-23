package com.springer.draw;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ExceptionChecker {
    public static void willThrowExceptionWithMessage(Runnable consumer, String message) {
        try {
            consumer.run();
            fail("Should have thrown an exception");
        } catch (Exception expected) {
            assertThat(expected.getMessage(), equalTo(message));
        }
    }
}
