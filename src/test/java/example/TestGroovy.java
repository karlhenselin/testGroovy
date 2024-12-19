package example;

import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.control.MultipleCompilationErrorsException;
import org.junit.Test;

public class TestGroovy {
    @Test(expected = MultipleCompilationErrorsException.class)
    public void testFailingNewWayDoesNotWork() {
        // Expect the next line to throw a compilation error
        System.setProperty("groovy.antlr4", "true");
        new GroovyShell().parse("this is not valid;asdf;asdf;asdf;asdf;asdf ");

    }

    @Test(expected = MultipleCompilationErrorsException.class)
    public void testFailingOldWay() {
        System.setProperty("groovy.antlr4", "false");
        new GroovyShell().parse("this is not valid;asdf;asdf;asdf;asdf;asdf ");
    }
}