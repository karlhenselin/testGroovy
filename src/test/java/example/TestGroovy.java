package example;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.MultipleCompilationErrorsException;
import org.codehaus.groovy.control.ParserPluginFactory;
import org.junit.Test;

public class TestGroovy {
    @Test(expected = MultipleCompilationErrorsException.class)
    public void testFailing2() {
        // Expect the next line to throw a compilation error
        Script script = new GroovyShell().parse("this is not valid;asdf;asdf;asdf;asdf;asdf ");

    }
}