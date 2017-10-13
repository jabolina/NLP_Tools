package com.nlp;

import static com.nlp.PhoneticsPTBR.*;
import static com.nlp.Treatment.*;

public class Normalization {

    public static String normalize(String phrase) {

        phrase = phrase.toUpperCase();

        //return characterTreatment(phoneticsPTBR(basicStopwordsPTBR(internetesPTBR(characterTreatment(stemWord(phrase))))));
        return characterTreatment(phoneticsPTBR(stopwordsExpPTBR(internetesPTBR(stemWord(phrase)))));
    }
}
