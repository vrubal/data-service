package com.csg.cs.cro.dsl.engine.model;

import com.csg.cs.cro.dsl.engine.parser.FilterExpressionParser;
import com.csg.cs.cro.dsl.engine.tree.Expression;
import graphql.language.Field;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used by clients of this
 * library to get the target filter expression
 * of specified format.
 *
 * @author vpardes3
 */

public class FilterExpression {

    private Field field;
    private Map<String,String> fieldMap;
    private Expression expressionTree;

    private FilterExpression(FilterExpressionBuilder expressionBuilder) {
        this.field = expressionBuilder.field;
        this.fieldMap = expressionBuilder.fieldMap;
        this.expressionTree = expressionBuilder.expressionTree;
    }

    /**
     * Builder class responsible for building the
     * instance of FilterExpression class.
     */
    public static class FilterExpressionBuilder {

        private Field field;
        private Map<String,String> fieldMap;
        private Expression expressionTree;
        private Map args;
        private final String FILTER_ARG = "filter";

        private FilterExpressionBuilder () {
            fieldMap = new HashMap<>();
        }

        public FilterExpressionBuilder field(Field field) {
            this.field = field;
            return this;
        }

        public FilterExpressionBuilder map(String source, String target) {
            fieldMap.put(source,target);
            return this;
        }

        public FilterExpressionBuilder map(Map<String, String> fieldMap) {
            this.fieldMap = fieldMap;
            return this;
        }

        public FilterExpressionBuilder args(Map filterArgs) {
            this.args = filterArgs;
            return this;
        }

        public FilterExpression build() {
            FilterExpressionParser expressionParser = new FilterExpressionParser();
            if (args != null) {
                Object filter = args.get(FILTER_ARG);
                if (filter != null) {
                    expressionTree = expressionParser.parseFilterExpression((Map) filter);
                }
            }
            FilterExpression expression = new FilterExpression(this);
            return expression;
        }
    }

    public static FilterExpressionBuilder newFilterExpressionBuilder() {
        return new FilterExpressionBuilder();
    }
}
