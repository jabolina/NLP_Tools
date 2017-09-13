package com.neppo.NLP.NaturalLanguageProcessing.service;

import com.neppo.NLP.NaturalLanguageProcessing.domain.Dataset;

import java.util.stream.Collectors;

public class ServiceConfiguration {


    public static Dataset initialize(Dataset dataset) throws Exception {

        dataset.getIntents().forEach(intent -> {
            intent.keywords(intent.getKeywords().stream()
                    .map(s -> TextNormalization.normalization(s)).collect(Collectors.toList())
            );
            intent.getEntities().forEach(entity -> {
                entity.keywords(entity.getKeywords().stream()
                        .map(s -> TextNormalization.normalization(s)).collect(Collectors.toList()));
            });
        });

        Classify.trainIntentClassifier(dataset);
        Classify.trainEntityClassifier(dataset, getSize(dataset));

        return dataset;
    }

    private static int getSize(Dataset dataset) {
        int[] size = {0};

        dataset.getIntents().forEach(intent -> size[0] += intent.getEntities().size());

        return size[0];
    }
}
