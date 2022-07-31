package com.csg.cs.cro.dsl.engine.tree;


/**
 * Represents an expression value node
 * in the expression tree.
 *
 * @author vpardes3
 */
public class ExpressionValue<V> implements Expression {

    private V value;

    public ExpressionValue(V value) {
        this.value = value;
    }

    /**
     * Returns the string field value.
     * @return
     */
    @Override
    public String infix() {
        StringBuilder infix = new StringBuilder("");
        String result = null;
        if (value != null) {
            if (value instanceof Iterable) {
                Iterable<V> vals = (Iterable<V>) value;
                for (V val : vals) {
                    infix.append(value.toString()).append(",");
                }
                result = infix.toString() == "" ? "" : infix.substring(0, infix.length()-1);
            } else {
                infix.append(value);
                result = infix.toString();
            }
        }
        return result;
    }

    /**
     * Returns the value.
     * @return
     */
    public V value() {
        return value;
    }
}
