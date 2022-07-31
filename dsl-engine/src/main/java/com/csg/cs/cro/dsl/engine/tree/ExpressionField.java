package com.csg.cs.cro.dsl.engine.tree;

/**
 * This class represents the operand field in
 * the expression tree.
 *
 * @author vpardes3
 */
public class ExpressionField implements Expression {

    private String fieldName;

    public ExpressionField(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Returns the field name.
     * @return
     */
    @Override
    public String infix() {
        return fieldName;
    }
}
