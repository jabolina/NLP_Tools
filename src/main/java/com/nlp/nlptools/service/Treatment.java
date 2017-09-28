package com.nlp.nlptools.service;

import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.portugueseStemmer;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class Treatment {

    public static String characterTreatment(String phrase) {

        Map<Pattern, String> substs = new HashMap<>();
        substs.put(compile("[ÂÃÁÀÄ]"), "A");
        substs.put(compile("[ÉÈÊË]"), "E");
        substs.put(compile("[ÍÌÎÏ]"), "I");
        substs.put(compile("[ÓÒÔÕÖ]"), "O");
        substs.put(compile("[ÚÙÛÜ]"), "U");

        for (Map.Entry<Pattern, String> subst : substs.entrySet()) {
            Pattern accents = subst.getKey();
            String noAccent = subst.getValue();
            phrase = accents.matcher(phrase).replaceAll(noAccent);
        }

        phrase = phrase.replaceAll("[^\\w]", " ");
        phrase = phrase.replaceAll("\\s{2,}", " ");

        return phrase;
    }

    public static String stopwordsPTBR(String phrase) {

        if (!phrase.contains("NAO"))
            phrase = phrase.replaceAll("\\bN[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\bS[AEIOU]{1,2}[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\bD[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\b(?:PR|P[AEIOU])(?:[AEIOU]|R)[AEIOU]*\\b" , " ");
        phrase = phrase.replaceAll("\\bT[AEIOU]+\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+S*\\b", " ");
        phrase = phrase.replaceAll("\\bU*(?:M|NS)*[AEIOU]*S*\\b", " ");
        phrase = phrase.replaceAll("\\bC[AEIOU][MN]*\\b", " ");
        phrase = phrase.replaceAll("\\bK+[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\bQ[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\b[^AEIOU]+\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+\\b", " ");

        return phrase;
    }

    public static String stemWord(String phrase) {
        String stemmed = "";

        SnowballStemmer stemmer = new portugueseStemmer();
        for(String word: phrase.split(" ")) {
            stemmer.setCurrent(word);
            stemmed = stemmed + ' ' + stemmer.getCurrent();
        }

        return stemmed;
    }
}
