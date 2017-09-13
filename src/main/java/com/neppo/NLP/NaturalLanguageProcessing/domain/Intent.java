package com.neppo.NLP.NaturalLanguageProcessing.domain;

import java.util.List;

public class Intent {

    private String key;
    private List<String> keywords;
    private List<Entity> entities;


    public String getKey() {
        return key;
    }
    public Intent key(String key) {
        this.key = key;
        return this;
    }
    public List<String> getKeywords() {
        return keywords;
    }
    public Intent keywords(List<String> keywords) {
        this.keywords = keywords;
        return this;
    }
    public List<Entity> getEntities() {
        return entities;
    }
    public Intent entities(List<Entity> entities) {
        this.entities = entities;
        return this;
    }
}
