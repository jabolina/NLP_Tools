package com.neppo.NLP.NaturalLanguageProcessing.service;

import com.neppo.NLP.NaturalLanguageProcessing.domain.Dataset;
import com.neppo.NLP.NaturalLanguageProcessing.domain.Intent;
import org.w3c.dom.Attr;
import weka.core.Attribute;
import weka.core.FastVector;

import java.util.ArrayList;
import java.util.List;


public class FileConverter {

    public static FastVector toARFF(String phrase) {

        FastVector predict = new FastVector();
        FastVector allAttrb = new FastVector();

        predict.addElement(phrase);
        allAttrb.addElement(new Attribute("Predict", predict));

        return allAttrb;
    }

    public static FastVector intentToARFF(Dataset dataset) {

        FastVector allAttrb = new FastVector();
        FastVector classAttrb = new FastVector();
        FastVector attrb = new FastVector();

        dataset.getIntents().forEach(intent -> {
            classAttrb.addElement(intent.getKey());
            attrb.addElement(String.join(" ", intent.getKeywords()));
        });

        allAttrb.addElement(new Attribute(dataset.getDatasetName(), attrb));
        allAttrb.addElement(new Attribute("intent_class", classAttrb));

        System.out.println(allAttrb);

        return allAttrb;
    }

    public static FastVector entityToARFF(Dataset dataset) {
        FastVector allAttrb = new FastVector();
        FastVector classAttrb = new FastVector();
        FastVector attrb = new FastVector();

        dataset.getIntents().forEach(intent ->
                intent.getEntities().forEach(entity -> {
                    classAttrb.addElement(entity.getKey());
                    attrb.addElement(String.join(" ", entity.getKeywords()));
                })
            );

        allAttrb.addElement(new Attribute(dataset.getDatasetName(), attrb));
        allAttrb.addElement(new Attribute("entity_class", classAttrb));

        return allAttrb;
    }

    public static Dataset toDataset(List<Attribute> attributes) {
        Dataset dataset = new Dataset();


        return dataset;
    }
}
