package com.nlp.nlptools.service;

import static com.nlp.nlptools.service.PhoneticsPTBR.*;
import static com.nlp.nlptools.service.Treatment.*;

public class Normalization {

    public static String normalize(String phrase) {

        phrase = phrase.toUpperCase();
        return stemWord(
                characterTreatment(
                        phoneticsPTBR(
                                stopwordsPTBR(
                                        internetesPTBR(phrase)
                                )
                        )
                )
        );
    }
}
