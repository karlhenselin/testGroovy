package example;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.transform.TypeChecked;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.MultipleCompilationErrorsException;
import org.codehaus.groovy.control.ParserPluginFactory;
import org.codehaus.groovy.control.customizers.ASTTransformationCustomizer;
import org.junit.Assert;
import org.junit.Test;

public class TestGroovy2 {



    @Test(expected = MultipleCompilationErrorsException.class)
    public void testDaggett3Rainy() {

        String props = "";
        props += "\n public abstract class MyBaseScript1 extends groovy.lang.Script {";
        props += "\n   String x";
        props += "\n   int y";
        props += "\n }";

        GroovyClassLoader gcl = new GroovyClassLoader(this.getClass().getClassLoader());
        Class base = gcl.parseClass(props);

        CompilerConfiguration config = new CompilerConfiguration();
        config.addCompilationCustomizers(new ASTTransformationCustomizer(TypeChecked.class));
        config.setScriptBaseClass(base.getName());

        GroovyShell gs = new GroovyShell(gcl, config);
        gs.parse("this is not valid");
    }

    @Test
    public void testDaggett3Sunny() {

        String props = "";
        props += "\n public abstract class MyBaseScript1 extends groovy.lang.Script {";
        props += "\n   String x";
        props += "\n   int y";
        props += "\n }";

        GroovyClassLoader gcl = new GroovyClassLoader(this.getClass().getClassLoader());
        Class base = gcl.parseClass(props);

        CompilerConfiguration config = new CompilerConfiguration();
        config.addCompilationCustomizers(new ASTTransformationCustomizer(TypeChecked.class));
        config.setScriptBaseClass(base.getName());

        GroovyShell gs = new GroovyShell(gcl, config);
        String in = "println x;\ny = y+1";
        Script s = gs.parse(in);
        Binding b = new Binding();
        b.setVariable("x", "This is a test.");
        b.setVariable("y", 13);
        s.setBinding(b);
        s.evaluate(in);
        Assert.assertEquals(14, b.getVariable("y"));
    }

}