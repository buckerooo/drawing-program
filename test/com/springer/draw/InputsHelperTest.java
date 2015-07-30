package com.springer.draw;

import org.junit.Test;

import static com.springer.draw.InputsHelper.positiveIntValueFrom;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class InputsHelperTest {
    @Test
    public void blowsUpIfInputIsNotAInteger() {
        String errorMessage = "my error message";
        try {
            positiveIntValueFrom("a", errorMessage);
            fail("should fail as input is a string");
        } catch(IllegalArgumentException expected) {
            assertThat(expected.getMessage(), equalTo(errorMessage));
        }
    }

    @Test
    public void blowsUpIfInputIntegerIsNotGreaterThanZero() {
        String errorMessage = "my error message";
        try {
            positiveIntValueFrom("0", errorMessage);
            fail("should fail as input is a string");
        } catch(IllegalArgumentException expected) {
            assertThat(expected.getMessage(), equalTo(errorMessage));
        }
    }
}