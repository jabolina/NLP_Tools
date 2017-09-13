package com.neppo.NLP.NaturalLanguageProcessing.domain;

import java.util.List;

public class Entity {
    private String key;
    private List<String> keywords;



    public String getKey() {
        return key;
    }
    public Entity key(String key) {
        this.key = key;
        return this;
    }
    public List<String> getKeywords() {
        return keywords;
    }
    public Entity keywords(List<String> keywords) {
        this.keywords = keywords;
        return this;
    }
}
