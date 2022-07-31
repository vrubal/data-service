package com.csg.cs.cro.dsl.engine.tree;

/**
 * This class represents a BinaryExpression node
 * in the expression tree, it will contain left
 * operand in the form of ExpressionField and right
 * operand in the form of ExpressionValue. Operator
 * can be any of relational operators.
 *
 * @author vpardes3
 */
public class BinaryExpression extends AbstractExpression {

    /**
     * Constructor to create a binary expression node
     * with left operand, operator and right operand.
     * @param leftOperand
     * @param operator
     * @param rightOperand
     */
    public BinaryExpression(Expression leftOperand, Operator operator, Expression rightOperand) {
        super(leftOperand,operator,rightOperand);
    }

    /**
     * Default constructor.
     */
    public BinaryExpression() {
        super();
    }

}
