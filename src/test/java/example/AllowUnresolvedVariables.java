package example;

import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.transform.stc.AbstractTypeCheckingExtension;
import org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor;

public class AllowUnresolvedVariables
        extends AbstractTypeCheckingExtension
{

    public AllowUnresolvedVariables(final StaticTypeCheckingVisitor typeCheckingVisitor)
    {
        super(typeCheckingVisitor);
    }

    @Override
    public boolean handleUnresolvedVariableExpression(final VariableExpression vexp)
    {
        return true;
    }

}