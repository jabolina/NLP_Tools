package com.neppo.NLP.NaturalLanguageProcessing.service;

import com.neppo.NLP.NaturalLanguageProcessing.domain.Dataset;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.*;

public class Classify {

    private static Classifier intentClassifier = null;
    private static Classifier entityClassifier = null;
    private static Instances intentTrainingSet;
    private static Instances entityTrainingSet;
    private Classify(){}


    /// A singleton Classify
    private static Classifier getEntityClassifier() {

        return (entityClassifier == null) ? new NaiveBayes() : entityClassifier;
    }

    private static Classifier getIntentClassifier() {

        return (intentClassifier == null) ? new NaiveBayes() : intentClassifier;
    }


    public static void trainIntentClassifier(Dataset dataset) throws Exception {

        FastVector attrb = FileConverter.intentToARFF(dataset);
        intentTrainingSet = new Instances("train", attrb,
                dataset.getIntents().size());
        intentTrainingSet.setClassIndex(intentTrainingSet.numAttributes() - 1);
        System.out.println(attrb);
        intentTrainingSet.add(fillInstance(dataset, attrb));
        intentClassifier = new NaiveBayes();
        getIntentClassifier().buildClassifier(intentTrainingSet);
    }


    public static void trainEntityClassifier(Dataset dataset, int size) throws Exception {

        FastVector attrb = FileConverter.entityToARFF(dataset);
        entityTrainingSet = new Instances("train", attrb, size);
        entityTrainingSet.setClassIndex(1);
        entityTrainingSet.add(fillInstance(dataset, attrb, size));
        entityClassifier = new NaiveBayes();
        getEntityClassifier().buildClassifier(entityTrainingSet);
    }


    // TODO predict the intent, filter the entities to use only related to the intent, then predict the entity
    public static String predict(String phrase) throws Exception {

        phrase = TextNormalization.normalization(phrase);

        FastVector attrb = FileConverter.toARFF(phrase);
        Instances unlabeled = new Instances("unlabeled", attrb, 1);
        unlabeled.setClassIndex(0);


        System.out.println(intentTrainingSet);
        intentTrainingSet.forEach(instance -> System.out.println(instance));

/*
        unlabeled.forEach(instance -> {
            try {
                System.out.println("eae");
                System.out.println(intentClassifier.distributionForInstance(instance));
                System.out.println(intentClassifier.classifyInstance(instance));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        System.out.println(unlabeled);

        Instance predict = new DenseInstance(1);
        predict.setValue((Attribute)attrb.elementAt(0), phrase);
        predict.setDataset(unlabeled);

        System.out.println("unlabeled");
        System.out.println(predict);

        double[] distribution = intentClassifier.distributionForInstance();
*/
        return phrase;
    }

    private static Instance fillInstance(Dataset dataset, FastVector instance) {

        Instance train = new DenseInstance(dataset.getIntents().size());

        System.out.println(instance);

        dataset.getIntents().forEach(intent ->
                train.setValue((Attribute)instance.elementAt(0),
                        String.join(" ", intent.getKeywords()))
        );

        return train;
    }

    private static Instance fillInstance(Dataset dataset, FastVector instance, int size) {

        Instance train = new DenseInstance(size);

        dataset.getIntents().forEach(intent ->
                intent.getEntities().forEach(entity ->
                        train.setValue((Attribute)instance.elementAt(0),
                                String.join(" ", entity.getKeywords()))
                )
        );

        return train;
    }

}
