package com.csg.cs.cro.dsl.engine.model;

/**
 * Supported expression formats.
 *
 * @author vpardes3
 */
public enum ExpressionFormat {

    SQL("SQL"),
    JPA("JPA");

    private String type;
    ExpressionFormat(String type) {
        this.type = type;
    }

    public static ExpressionFormat getFormat(String type) {
        for (ExpressionFormat format : ExpressionFormat.values()) {
            if (format.type.equals(type)) {
                return format;
            }
        }
        return SQL;
    }
}