package com.csg.cs.cro.dsl.engine.tree;


/**
 * Base interface for an expression node
 * in the expression tree.
 *
 * @author vpardes3
 */
public interface Expression{

    /**
     * Returns the infix string representation of the
     * filter expression.
     * @return
     */
    public String infix();
}
