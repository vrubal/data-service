package com.csg.cro.controller;

import com.csg.cro.model.Counterparty;
import com.csg.cro.model.Exposure;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller

public class ExposureController {

    @SchemaMapping( typeName = "Counterparty", field = "exposure")
    public List<Exposure> getExposure(Counterparty counterparty, DataFetchingEnvironment env) {
        return List.of(new Exposure());
    }
}
