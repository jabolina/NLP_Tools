package com.nlp.nlptools;

import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.portugueseStemmer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        phrase = removeDuplicates(phrase);

        phrase = phrase.replaceAll("\\b(\\w)(\\W)\\b", "$1 $2");
        phrase = phrase.replaceAll("[\"\'_*.,@!?&%=€#®ŧ←↓→øþ´ªº~łĸ̉ŋđðßæ«»©“”nµ¹²³£¢¬─·$+]+", " ");
        phrase = phrase.replaceAll("\\e+", "");
        phrase = phrase.replaceAll("\\s{2,}", " ");
        phrase = phrase.replaceAll("^\\s+", "");
        phrase = phrase.replaceAll("\\s+\\z","");

        return phrase;
    }

    public static String removeDuplicates(String phrase) {

        phrase = phrase.replaceAll("(.)A{2,}(.)", "$1A$2");
        phrase = phrase.replaceAll("(.)B{2,}(.)", "$1B$2");
        phrase = phrase.replaceAll("(.)C{2,}(.)", "$1C$2");
        phrase = phrase.replaceAll("(.)D{2,}(.)", "$1D$2");
        phrase = phrase.replaceAll("(.)E{2,}(.)", "$1E$2");
        phrase = phrase.replaceAll("(.)F{2,}(.)", "$1F$2");
        phrase = phrase.replaceAll("(.)G{2,}(.)", "$1G$2");
        phrase = phrase.replaceAll("(.)I{2,}(.)", "$1I$2");
        phrase = phrase.replaceAll("(.)J{2,}(.)", "$1J$2");
        phrase = phrase.replaceAll("(.)L{2,}(.)", "$1L$2");
        phrase = phrase.replaceAll("(.)M{2,}(.)", "$1M$2");
        phrase = phrase.replaceAll("(.)N{2,}(.)", "$1N$2");
        phrase = phrase.replaceAll("(.)O{2,}(.)", "$1O$2");
        phrase = phrase.replaceAll("(.)P{2,}(.)", "$1P$2");
        phrase = phrase.replaceAll("(.)R{2,}(.)", "$1R$2");
        phrase = phrase.replaceAll("(.)T{2,}(.)", "$1T$2");
        phrase = phrase.replaceAll("(.)U{2,}(.)", "$1U$2");
        phrase = phrase.replaceAll("(.)V{2,}(.)", "$1V$2");
        phrase = phrase.replaceAll("(.)W{2,}(.)", "$1W$2");
        phrase = phrase.replaceAll("(.)X{2,}(.)", "$1X$2");
        phrase = phrase.replaceAll("(.)Z{2,}(.)", "$1Z$2");

        return phrase;
    }

    public static String stopwordsExpPTBR(String phrase) {

        if (!phrase.contains("NAO"))
            phrase = phrase.replaceAll("\\bN[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]{5,}\\b", " ");
        phrase = phrase.replaceAll("\\b[^AEIOU]+\\b", " ");
        phrase = phrase.replaceAll("\\bS[AEIOU]{1,2}[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\bD[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\b(?:PR|P[AEIOU])(?:[AEIOU]|R)[AEIOU]*\\b" , " ");
        phrase = phrase.replaceAll("\\bT[AEIOU]+[SZRGWMLYDHJKX]*\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+S+\\b", " ");
        phrase = phrase.replaceAll("\\bH*U+(?:M|NS)*[AEIOU]*S*\\b", " ");
        phrase = phrase.replaceAll("\\bC[AEIOU]+[MN]*\\b", " ");
        phrase = phrase.replaceAll("\\bK+[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\bQ[AEIOU]*[^AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+H+\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+F+\\b", " ");
        phrase = phrase.replaceAll("\\bT[AM]*B[EM]*\\b", " ");
        phrase = phrase.replaceAll("\\bT[AEIOU]*D[AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\bM[AEIOU]+[SZ]\\b", " ");
        phrase = phrase.replaceAll("\\b(?:VC|VCS|VS)\\b", " ");
        phrase = phrase.replaceAll("\\bH[O]J[E]\\b", " ");
        phrase = phrase.replaceAll("\\b[QK][AEIOU]*ND[AEIOU]*\\b", " ");
        phrase = phrase.replaceAll("\\bSL[CK]+", " ");

        return phrase;
    }

    public static String basicStopwordsPTBR(String phrase) {

        phrase = phrase.replaceAll("\\b(?:PR|P[AEIOU])(?:[AEIOU]|R)[AEIOU]*\\b" , " ");
        phrase = phrase.replaceAll("\\bT[AEIOU]\\b", " ");
        phrase = phrase.replaceAll("\\bH*U+(?:M|NS)*[AEIOU]*S*\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+H+\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]+F+\\b", " ");
        phrase = phrase.replaceAll("\\b[AEIOU]{5,}\\b", " ");
        phrase = phrase.replaceAll("\\b[^AEIOU]{3,}\\b", " ");

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
