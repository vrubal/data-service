package com.csg.cro.controller;

import com.csg.cro.model.Counterparty;
import com.csg.cs.cro.dsl.engine.model.AggregationExpression;
import com.csg.cs.cro.dsl.engine.model.FilterExpression;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CounterpartyController {

    @QueryMapping
    public Counterparty counterpartyById(DataFetchingEnvironment env) {
        return new Counterparty(1L,"test", null);
    }

    @QueryMapping
    public List<Counterparty> searchCounterParties(DataFetchingEnvironment env) {
        FilterExpression.FilterExpressionBuilder builder = FilterExpression.newFilterExpressionBuilder();
        FilterExpression filterExpression = builder.args(env.getArguments()).build();
        AggregationExpression.AggregationExpressionBuilder aggregationBuilder = AggregationExpression.newAggregationExpressionBuilder();
        AggregationExpression aggregationExpression = aggregationBuilder.args(env.getArguments()).build();
        System.out.println(filterExpression.toString());
        return List.of(new Counterparty(1L,"test", null));
    }
}
