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
        System.setProperty("groovy.antlr4.sll.threshold", "-1");
        final CompilerConfiguration CONFIG = new CompilerConfiguration();
        CONFIG.setPluginFactory(ParserPluginFactory.antlr4());
        CONFIG.setTolerance(0);

        String pScriptCode = "this is not valid;asdf;asdf;asdf;asdf;asdf ";
        ClassLoader cl = this.getClass().getClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(cl);
        GroovyShell shell = new GroovyShell(loader, new Binding(), CONFIG);
        // Expect the next line to throw a compilation error
        Script script = shell.parse(pScriptCode);

    }
}