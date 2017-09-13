package com.neppo.NLP.NaturalLanguageProcessing.domain;

import java.util.List;

public class Dataset {

    private String datasetName;
    private List<Intent> intents;


    public String getDatasetName() {

        return datasetName;
    }
    public Dataset datasetName(String datasetName) {
        this.datasetName = datasetName;
        return this;
    }
    public List<Intent> getIntents() {

        return intents;
    }
    public Dataset intents(List<Intent> intents) {
        this.intents = intents;
        return this;
    }
}
