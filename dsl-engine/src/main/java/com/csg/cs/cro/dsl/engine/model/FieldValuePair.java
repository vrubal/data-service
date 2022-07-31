package com.csg.cs.cro.dsl.engine.model;

/**
 * Field value pair for providing custom
 * field name and value for customizing
 * filter field.
 *
 * @author vpardes3
 */
public class FieldValuePair <V> {
    private String fieldName;
    private V value;

    public FieldValuePair(String fieldName, V value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    /**
     * Returns the field name.
     *
     * @return
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Returns the field value.
     * @return
     */
    public V getValue() {
        return value;
    }
}
