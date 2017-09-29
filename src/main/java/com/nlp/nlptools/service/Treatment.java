package com.nlp.nlptools.service;

import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.portugueseStemmer;

import java.util.*;
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
        phrase = phrase.replace("^\\b(.)", "$1");

        return phrase;
    }

    public static String stopwordsExpPTBR(String phrase) {

        if (!phrase.contains("NAO"))
            phrase = phrase.replaceAll("\\bN[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\bS[AEIOU]{1,2}[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\bD[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\b(?:PR|P[AEIOU])(?:[AEIOU]|R)[AEIOU]*\\b" , " ");
        phrase = phrase.replaceAll("\\bT[AEIOU]+\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+S*\\b", " ");
        phrase = phrase.replaceAll("\\bH*U+(?:M|NS)*[AEIOU]*S*\\b", " ");
        phrase = phrase.replaceAll("\\bC[AEIOU][MN]*\\b", " ");
        phrase = phrase.replaceAll("\\bK+[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\bQ[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\b[^AEIOU]+\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+H+\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+F+\\b", " ");
        phrase = phrase.replaceAll("\\bhttps://(?:\\w*\\W*)*\\b", " ");

        return phrase;
    }

    public static String basicStopwordsPTBR(String phrase) {

        phrase = phrase.replaceAll("\\b(?:PR|P[AEIOU])(?:[AEIOU]|R)[AEIOU]*\\b" , " ");
        phrase = phrase.replaceAll("\\bT[AEIOU]+\\b", " ");
        phrase = phrase.replaceAll("\\bH*U+(?:M|NS)*[AEIOU]*S*\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+H+\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+F+\\b", " ");
        phrase = phrase.replaceAll("\\bhttps://(?:\\w*\\W*)*\\b", " ");

        return phrase;
    }

    public static String stemWord(String phrase) {
        List<String> stemmed = new ArrayList<>();

        SnowballStemmer stemmer = new portugueseStemmer();
        for(String word: phrase.split(" ")) {
            stemmer.setCurrent(word);
            stemmer.stem();
            stemmed.add(stemmer.getCurrent());
        }

        return String.join(" ", stemmed);
    }
}
