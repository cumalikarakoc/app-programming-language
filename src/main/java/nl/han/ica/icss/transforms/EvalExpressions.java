package nl.han.ica.icss.transforms;

import com.google.errorprone.annotations.Var;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;

import javax.naming.OperationNotSupportedException;
import java.util.HashMap;
import java.util.List;

public class EvalExpressions implements Transform {

    private HashMap<String, Literal> variableValues = new HashMap<>();

    public EvalExpressions() {
    }

    @Override
    public void apply(AST ast) {
        setVariableValues(ast);
        for (ASTNode declaration : ast.getDeclarations()) {
            transformVariables(declaration);
        }
    }

    private void transformVariables(ASTNode declaration) {
        if (declaration instanceof Declaration) {
            covertVariableReferenceToLiteral(declaration, ((Declaration) declaration).expression);
            return;
        }

        if (declaration instanceof IfClause) {
            covertVariableReferenceToLiteral(declaration, ((IfClause) declaration).conditionalExpression);
            for (ASTNode ifClauseDeclaration : ((IfClause) declaration).body)
                transformVariables(ifClauseDeclaration);
        }
    }

    private Literal covertVariableReferenceToLiteral(ASTNode declaration, Expression expression) {
        if (expression instanceof Literal) {
            return (Literal) expression;
        }
        if (expression instanceof VariableReference) {
            Literal literal = variableValues.get(((VariableReference) expression).name);

            if (declaration instanceof Declaration) {
                ((Declaration) declaration).expression = literal;
                return literal;
            }

            if (declaration instanceof IfClause) {
                ((IfClause) declaration).conditionalExpression = literal;
            }
        }
        if (expression instanceof Operation) {
            Literal lhs = covertVariableReferenceToLiteral(declaration, ((Operation) expression).lhs);
            Literal rhs = covertVariableReferenceToLiteral(declaration, ((Operation) expression).rhs);
            Literal res = null;
            if (expression instanceof AddOperation) {
                res = add(lhs, rhs);
            }
            if (expression instanceof MultiplyOperation) {
                res = multiply(lhs, rhs);
            }

            if (declaration instanceof Declaration) {
                ((Declaration) declaration).expression = res;
            }
            return res;
        }
        return null;
    }

    private void setVariableValues(AST ast) {
        List<ASTNode> assignments = ast.getVariableAssignments();
        for (ASTNode node : assignments) {
            if (node instanceof VariableAssignment) {
                variableValues.put(((VariableAssignment) node).name.name, (Literal) ((VariableAssignment) node).expression);
            }
        }
    }

    private Literal add(Literal lhs, Literal rhs) {
        if (lhs instanceof PixelLiteral && rhs instanceof PixelLiteral) {
            return new PixelLiteral(((PixelLiteral) lhs).value + ((PixelLiteral) rhs).value);
        }
        if (lhs instanceof PercentageLiteral && rhs instanceof PercentageLiteral) {
            return new PercentageLiteral(((PercentageLiteral) lhs).value + ((PercentageLiteral) rhs).value);
        }

        throw new UnsupportedOperationException();
    }

    private Literal subtract(Literal lhs, Literal rhs) {
        if (lhs instanceof PixelLiteral && rhs instanceof PixelLiteral) {
            return new PixelLiteral(((PixelLiteral) lhs).value - ((PixelLiteral) rhs).value);
        }
        if (lhs instanceof PercentageLiteral && rhs instanceof PercentageLiteral) {
            return new PercentageLiteral(((PercentageLiteral) lhs).value - ((PercentageLiteral) rhs).value);
        }

        throw new UnsupportedOperationException();
    }


    private Literal multiply(Literal lhs, Literal rhs) {
        if (lhs instanceof ScalarLiteral) {
            return getResultOfMultiplication((ScalarLiteral) lhs, rhs);
        }
        if (rhs instanceof ScalarLiteral) {
            return getResultOfMultiplication((ScalarLiteral) rhs, lhs);
        }

        throw new UnsupportedOperationException();
    }

    private Literal getResultOfMultiplication(ScalarLiteral lhs, Literal rhs) {
        if (rhs instanceof PixelLiteral) {
            return new PixelLiteral(lhs.value * ((PixelLiteral) rhs).value);
        }
        if (rhs instanceof PercentageLiteral) {
            return new PercentageLiteral(lhs.value * ((PercentageLiteral) rhs).value);
        }

        throw new UnsupportedOperationException();
    }
}
