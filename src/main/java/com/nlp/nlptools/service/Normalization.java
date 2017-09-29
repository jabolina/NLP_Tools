package com.nlp.nlptools.service;

import static com.nlp.nlptools.service.PhoneticsPTBR.*;
import static com.nlp.nlptools.service.Treatment.*;

public class Normalization {

    public static String normalize(String phrase) {

        phrase = phrase.toUpperCase();

        return characterTreatment(phoneticsPTBR(basicStopwordsPTBR(internetesPTBR(stemWord(phrase)))));
        //return characterTreatment(phoneticsPTBR(stopwordsExpPTBR(internetesPTBR(stemWord(phrase)))));
    }
}
