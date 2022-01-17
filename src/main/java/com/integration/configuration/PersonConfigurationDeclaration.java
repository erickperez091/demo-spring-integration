package com.integration.configuration;

import com.integration.aggregator.PersonAggregator;
import com.integration.filter.PersonFilter;
import com.integration.handler.PersonFemaleHandler;
import com.integration.handler.PersonHandler;
import com.integration.handler.PersonMaleHandler;
import com.integration.handler.PersonReaderHandler;
import com.integration.transformer.PersonTransformer;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonConfigurationDeclaration {
    @Autowired
    protected PersonReaderHandler personReaderHandler;

    @Autowired
    protected PersonHandler personHandler;

    @Autowired
    protected PersonMaleHandler personMaleHandler;

    @Autowired
    protected PersonFemaleHandler personFemaleHandler;

    @Autowired
    protected PersonAggregator personAggregator;

    @Autowired
    protected PersonFilter personFilter;

    @Autowired
    protected PersonTransformer personTransformer;
}
