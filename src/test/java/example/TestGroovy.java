package example;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.transform.CompileStatic;
import groovy.transform.TypeChecked;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.MultipleCompilationErrorsException;
import org.codehaus.groovy.control.ParserPluginFactory;
import org.codehaus.groovy.control.customizers.ASTTransformationCustomizer;
import org.junit.Test;

public class TestGroovy {

    @Test(expected = MultipleCompilationErrorsException.class)
    public void testFailingNewWayDoesNotWork() {
        CompilerConfiguration config = new CompilerConfiguration();
        // Expect the next line to throw a compilation error, but it doesn't.
        new GroovyShell(config).parse("this is not valid");
    }

    @Test(expected = MultipleCompilationErrorsException.class)
    public void testFailingOldWay() {
        CompilerConfiguration config = new CompilerConfiguration();
        config.setPluginFactory(ParserPluginFactory.antlr2());
        new GroovyShell(config).parse("this is not valid");
    }

    @Test(expected = MultipleCompilationErrorsException.class)
    public void testNewIdea() {
        CompilerConfiguration config = new CompilerConfiguration();
        config.addCompilationCustomizers(new ASTTransformationCustomizer(TypeChecked.class));
        new GroovyShell(config).parse("this is not valid");
    }

    @Test(expected = MultipleCompilationErrorsException.class)
    public void testNewIdea2() {
        CompilerConfiguration config = new CompilerConfiguration();
        config.addCompilationCustomizers(new ASTTransformationCustomizer(NoUnresolvedVariablesAnnotation.class));
        GroovyShell shell = new GroovyShell(config);
        shell.parse("this is not valid");
    }

    @Test
    public void testMissingVarIsOk() {
        CompilerConfiguration config = new CompilerConfiguration();
        config.addCompilationCustomizers(new ASTTransformationCustomizer(CompileStatic.class));
        GroovyShell shell = new GroovyShell(config);
        shell.setVariable("x", 3);
        shell.parse("return x + 3");
    }

    @Test(expected = MultipleCompilationErrorsException.class)
    public void testDaggettIdeaRainy() {
        CompilerConfiguration config = new CompilerConfiguration();
        config.addCompilationCustomizers(new ASTTransformationCustomizer(TypeChecked.class));
        config.setScriptBaseClass(MyScript.class.getCanonicalName());
        GroovyShell shell = new GroovyShell(this.getClass().getClassLoader(), config);
        shell.parse("this is not valid");
    }

    @Test
    public void testDaggettIdeaSunny() {
        CompilerConfiguration config = new CompilerConfiguration();
        config.addCompilationCustomizers(new ASTTransformationCustomizer(TypeChecked.class));
        config.setScriptBaseClass(MyScript.class.getCanonicalName());
        GroovyShell shell = new GroovyShell(this.getClass().getClassLoader(), config);
        shell.parse("x + 3");
    }

    abstract static class MyScript extends Script {
        protected int x = 3;
    }
    @Test(expected = MultipleCompilationErrorsException.class)
    public void testDaggettIdea2Rainy() {
        CompilerConfiguration config = new CompilerConfiguration();
        config.addCompilationCustomizers(new ASTTransformationCustomizer(TypeChecked.class));
        config.setScriptBaseClass(BaseScript.class.getCanonicalName());
        GroovyShell shell = new GroovyShell(this.getClass().getClassLoader(), config);
        shell.parse("this is not valid");
    }

    @Test
    public void testDaggettIdea2Sunny() {
        CompilerConfiguration config = new CompilerConfiguration();
        config.addCompilationCustomizers(new ASTTransformationCustomizer(TypeChecked.class));
        config.setScriptBaseClass(BaseScript.class.getCanonicalName());
        GroovyShell shell = new GroovyShell(this.getClass().getClassLoader(), config);
        shell.parse("x + 3");
    }

}