package com.springer.draw.commands;

import com.springer.draw.Canvas;
import com.springer.draw.ProgramExiter;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Optional;

public class QuitCommandTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    private final ProgramExiter exiter = context.mock(ProgramExiter.class);

    @Test
    public void willCallTheExiterWhenCommandIsCalled() {
        context.checking(new Expectations(){{
            oneOf(exiter).exit();
        }});
        new QuitCommand(exiter).draw(Optional.of(new Canvas(5, 5)));
    }

    @Test
    public void doNotNeedACanvasToQuit() {
        context.checking(new Expectations(){{
            oneOf(exiter).exit();
        }});
        new QuitCommand(exiter).draw(Optional.empty());
    }
}