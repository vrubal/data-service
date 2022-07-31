package com.csg.cs.cro.dsl.engine.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author vpardes3
 * @created
 * @project data-service
 *
 */
public class AggregationExpression {
    private List<Aggregation> aggregationList;

    public AggregationExpression(AggregationExpressionBuilder expressionBuilder) {
        this.aggregationList = expressionBuilder.aggregationList;
    }

    /**
     * Builder class responsible for building the
     *  instance of AggregationExpression class.
     */
    public static class AggregationExpressionBuilder {
        private Map args;
        private List<Aggregation> aggregationList;
        private final String AGGREGATION_ARG = "aggregation";
        private final String AGGREGATION_TYPE = "aggregationType";
        private final String FIELD_NAME = "fieldName";

        public AggregationExpression build() {
            if (args != null) {
                List aggregation = (List) args.get(AGGREGATION_ARG);
                if (aggregation != null) {
                    aggregationList = (List<Aggregation>) aggregation.stream().map(
                            new Function() {
                                @Override
                                public Object apply(Object e) {
                                    return new Aggregation((String) ((LinkedHashMap<?, ?>) e).get(FIELD_NAME), AggregationType.valueOf(
                                            (String) ((LinkedHashMap) e).get(AGGREGATION_TYPE)));
                                }
                            }).collect(Collectors.toList());
                }
            }
            return new AggregationExpression(this);
        }

        public AggregationExpressionBuilder args(Map arguments) {
            this.args=arguments;
            return this;
        }
    }
    public static AggregationExpression.AggregationExpressionBuilder newAggregationExpressionBuilder() {
        return new AggregationExpressionBuilder();
    }
}
