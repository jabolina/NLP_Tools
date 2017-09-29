package com.nlp.nlptools.service;

import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.portugueseStemmer;

import java.util.*;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class Treatment {

    private static List<String> STOPWORDS_LIST = new ArrayList<>(Arrays.asList("a","ainda","alem","ambas","ambos","antes",
            "ao","aonde","aos","apos","aquele","aqueles",
            "as","assim","com","como","contra","contudo",
            "cuja","cujas","cujo","cujos","da","das","de",
            "dela","dele","deles","demais","depois","desde",
            "desta","deste","dispoe","dispoem","diversa",
            "diversas","diversos","do","dos","durante","e",
            "ela","elas","ele","eles","em","entao","entre",
            "essa","essas","esse","esses","esta","estas",
            "este","estes","ha","isso","isto","logo","mais",
            "mas","mediante","menos","mesma","mesmas","mesmo",
            "mesmos","na","nas","nas","nem","nesse","neste",
            "nos","o","os","ou","outra","outras","outro","outros",
            "pelas","pelas","pelo","pelos","perante","pois","por",
            "porque","portanto","proprio","propios","quais","qual",
            "qualquer","quando","quanto","que","quem","quer","se",
            "seja","sem","sendo","seu","seus","sob","sobre","sua",
            "suas","tal","tambem","teu","teus","toda","todas","todo",
            "todos","tua","tuas","tudo","um","uma","umas","uns"));

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

    public static String stopwordsListPTBR(String phrase) {

        for (String word: phrase.split(" ")) {
            if (STOPWORDS_LIST.contains(word)) {
                phrase = phrase.replaceAll(word, " ");
            }
        }
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
        phrase = phrase.replaceAll("\\bU*(?:M|NS)*[AEIOU]*S*\\b", " ");
        phrase = phrase.replaceAll("\\bC[AEIOU][MN]*\\b", " ");
        phrase = phrase.replaceAll("\\bK+[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\bQ[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\b[^AEIOU]+\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+H+\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+F+\\b", " ");

        return phrase;
    }

    public static String stemWord(String phrase) {
        String stemmed = "";

        SnowballStemmer stemmer = new portugueseStemmer();
        for(String word: phrase.split(" ")) {
            stemmer.setCurrent(word);
            stemmer.stem();
            stemmed = stemmed + ' ' + stemmer.getCurrent();
        }

        return stemmed;
    }
}
