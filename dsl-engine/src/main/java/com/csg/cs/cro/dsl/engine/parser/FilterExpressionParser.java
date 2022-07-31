package com.csg.cs.cro.dsl.engine.parser;

import com.csg.cs.cro.dsl.engine.tree.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * GraphQL Filter Expression Parser.
 *
 * @author vpardes3
 */
public class FilterExpressionParser {
    private static final Logger log = LoggerFactory.getLogger(FilterExpressionParser.class);

    /**
     * Parses the given graphql filter expression AST.
     * @param filterArgs
     * @return
     */
    public Expression parseFilterExpression(Map filterArgs) {
        return createExpressionTree(filterArgs);

    }

    private Expression createExpressionTree(Map filterMap) {
        if (filterMap == null || filterMap.isEmpty() || filterMap.size() > 1) {
            return null;
        }
        Deque<Expression> expressionStack = new ArrayDeque<>();
        Expression expression = null;
        Set<Map.Entry> entries =  filterMap.entrySet();
        for (Map.Entry entry : entries) {
            String key = entry.getKey().toString();
            if (isOperator(key)) {
                String kind = Operator.getOperatorKind(key);
                switch (kind) {

                    /* Case to handle the compound expression.*/
                    case "COMPOUND":
                        List values = (List)entry.getValue();
                        for (Object o : values) {
                            Expression right = createExpressionTree((Map)o);
                            Expression left = expressionStack.peek();
                            if (validateExpression(right) && validateExpression(left)) {
                                left = expressionStack.pop();
                                Expression newExp = new CompoundExpression(left, Operator.getOperator(key), right);
                                expressionStack.push(newExp);
                            } else {
                                expressionStack.push(right);
                            }
                        }
                        expression = expressionStack.pop();
                        break;

                    /* Case to handle the binary expression.*/
                    case "BINARY":
                        BinaryExpression binaryExpression = new BinaryExpression();
                        binaryExpression.setOperator(Operator.getOperator(key));
                        if (entry.getValue() instanceof Collection) {
                            List<Comparable> expressionValues = new ArrayList<>();
                            List<Comparable> operandValues = (List<Comparable>) entry.getValue();
                            for (Comparable value : operandValues) {
                                expressionValues.add(convertIfDate(value));
                            }
                            ExpressionValue<List> expressionValue = new ExpressionValue(expressionValues);
                            binaryExpression.setRightOperand(expressionValue);
                        } else {
                            ExpressionValue<Comparable> expressionValue = new ExpressionValue<>(convertIfDate((Comparable) entry.getValue()));
                            binaryExpression.setRightOperand(expressionValue);
                        }
                        expression = binaryExpression;
                        break;

                    case "UNARY":
                        Expression operand = createExpressionTree((Map)entry.getValue());
                        expression = new UnaryExpression(operand,Operator.getOperator(key), null);
                        break;
                }
            } else {
                /* Case to handle the Field expression.*/
                ExpressionField leftOperand = new ExpressionField(entry.getKey().toString());
                BinaryExpression binaryExpression = (BinaryExpression) createExpressionTree((Map)entry.getValue());
                binaryExpression.setLeftOperand(leftOperand);
                expression = binaryExpression;
            }
        }

        return expression;
    }

    private boolean isOperator(String key) {
        Operator operator = null;
        try {
            operator = Operator.getOperator(key);
        } catch (Exception ex) {
            log.debug("Invalid key:{} as operator in filter expression",
                    key);
        }
        return operator != null;
    }

    private Comparable convertIfDate(Comparable value) {
        if (value == null) {
            return null;
        }
        if (value instanceof LocalDate) {
            LocalDate localDate = (LocalDate) value;
            value = Date.from(localDate.atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
        } else if (value instanceof LocalDateTime) {
            LocalDateTime localDateTime = (LocalDateTime) value;
            value = Date
                    .from(localDateTime.atZone(ZoneId.systemDefault())
                            .toInstant());
        } else if (value instanceof OffsetDateTime) {
            OffsetDateTime offsetDateTime = (OffsetDateTime) value;
            value = Date
                    .from(offsetDateTime.toInstant());
        }
        return value;
    }

    /**
     * Validates if the given expression is
     * instance of Binary or Compound expression.
     * @param expression
     * @return
     */
    private boolean validateExpression(Expression expression) {
        if (expression != null && (expression instanceof BinaryExpression
                || expression instanceof CompoundExpression
                || expression instanceof UnaryExpression)) {
            return true;
        }
        return false;
    }

}

