package com.languagetools.LanguageTools.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class Treatment {

    public static String characterTreatment(String phrase) {

        Map<Pattern, String> substs = new HashMap<Pattern, String>();
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

        if (phrase.matches("\\bNAO\\b"))
            phrase = phrase.replaceAll("\\bN[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\bS[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\bD[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\b(?:PR|P[AEIOU])(?:[AEIOU]|R)[AEIOU]*\\b" , " ");
        phrase = phrase.replaceAll("\\b[AEIOU]{1,}S*\\b", " ");
        phrase = phrase.replaceAll("\\bU*(?:M|NS)*[AEIOU]*S*\\b", " ");
        phrase = phrase.replaceAll("\\bC[AEIOU](?:M|N)*\\b", " ");
        phrase = phrase.replaceAll("\\bK{1,}[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\bQ[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\b[^AEIOU]{1,}\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]{1,}\\b", " ");

        return phrase;
    }
}
