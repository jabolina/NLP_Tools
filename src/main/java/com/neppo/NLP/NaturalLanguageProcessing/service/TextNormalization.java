package com.neppo.NLP.NaturalLanguageProcessing.service;

import mtfn.MetaphoneInternet;
import mtfn.MetaphonePtBr;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.portugueseStemmer;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;


public class TextNormalization {

    public static String normalization(String phrase) {

        return stemWords(
                phoneticsInternetSlang(
                        phoneticsTreatment(
                                characterTreatment(phrase)
                        )
                )
        );
    }

    private static String phoneticsTreatment(String phrase) {

        return new MetaphonePtBr(phrase).toString();
    }


    // TODO Improve internet phonetics
    private static String phoneticsInternetSlang(String phrase) {

        return new MetaphoneInternet(phrase).toString();
    }

    private static String stemWords(String phrase) {

        SnowballStemmer stemmer = new portugueseStemmer();
        stemmer.setCurrent(phrase);
        stemmer.stem();

        return stemmer.getCurrent();
    }

    private static String characterTreatment(String phrase) {

        Map<Pattern, String> substs = new HashMap<Pattern, String>();
        substs.put(compile("[âãáàäÂÃÁÀÄ]"), "a");
        substs.put(compile("[éèêëÉÈÊË]"), "e");
        substs.put(compile("[íìîïÍÌÎÏ]"), "i");
        substs.put(compile("[óòôõöÓÒÔÕÖ]"), "o");
        substs.put(compile("[úùûüÚÙÛÜ]"), "u");

        for (Map.Entry<Pattern, String> subst : substs.entrySet()) {
            Pattern accents = subst.getKey();
            String noAccent = subst.getValue();
            phrase = accents.matcher(phrase).replaceAll(noAccent);
        }

        return phrase;
    }
}
