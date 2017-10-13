package com.nlp.nlptools;

import static com.nlp.nlptools.PhoneticsPTBR.*;
import static com.nlp.nlptools.Treatment.*;

public class Normalization {

    public static String normalize(String phrase) {

        phrase = phrase.toUpperCase();

        //return characterTreatment(phoneticsPTBR(basicStopwordsPTBR(internetesPTBR(characterTreatment(stemWord(phrase))))));
        return characterTreatment(phoneticsPTBR(stopwordsExpPTBR(internetesPTBR(stemWord(phrase)))));
    }
}
