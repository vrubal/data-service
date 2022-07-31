package com.csg.cs.cro.dsl.engine.model;

/**
 * @author vpardes3
 * @created
 * @project data-service
 */
public class Aggregation {
    private String fieldName;
    private AggregationType aggregationType;

    public Aggregation(String fieldName, AggregationType aggregationType) {
        this.fieldName = fieldName;
        this.aggregationType = aggregationType;
    }
}
