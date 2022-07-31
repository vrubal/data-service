package com.csg.cs.cro.dsl.engine.tree;

/**
 * This class represents an UnaryExpression node
 * in the expression tree with just NOT operator
 * and a single operand which can either be a
 * BinaryExpression or CompoundExpression.
 *
 * @author vpardes3
 */
public class UnaryExpression extends AbstractExpression {

    /**
     * Default constructor.
     */
    public UnaryExpression() {
        super();
    }

    /**
     * Parameterized constructor.
     *
     * @param leftOperand
     * @param operator
     * @param rightOperand
     */
    public UnaryExpression(Expression leftOperand, Operator operator, Expression rightOperand) {
        super(leftOperand, operator, rightOperand);
    }
}
