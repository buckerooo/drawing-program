package com.springer.draw;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class QuitCommandTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    private final ProgramExiter exiter = context.mock(ProgramExiter.class);

    @Test
    public void willCallTheExiterWhenCommandIsCalled() {
        context.checking(new Expectations(){{
            oneOf(exiter).exit();
        }});
        new QuitCommand(exiter).draw("Q");
    }
}