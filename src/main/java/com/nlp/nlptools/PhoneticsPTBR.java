package com.nlp.nlptools;

public class PhoneticsPTBR {

    public static String phoneticsPTBR(String phrase) {

        phrase = Treatment.removeDuplicates(phrase);

        phrase = phrase.replaceAll("\\bEX([AEIOU])", " EZ$1");
        phrase = phrase.replaceAll("C([EI])", "S$1");
        phrase = phrase.replaceAll("([AEIOU])S([AEIOU])", "$1Z$2");
        phrase = phrase.replaceAll("Ç","S");
        phrase = phrase.replaceAll("CH", "X");
        phrase = phrase.replaceAll("S{2,}", "S");
        phrase = phrase.replaceAll("RR", "R");
        phrase = phrase.replaceAll("C([AOU])", "K$1");
        phrase = phrase.replaceAll("\\bPOR(?:QUE)|(?: QUE)\\b", "PQ");
        phrase = phrase.replaceAll("QU([AEIO])", "K$1");
        phrase = phrase.replaceAll("G([EI])", "J$1");
        phrase = phrase.replaceAll("N([PB])", "M$1");
        phrase = phrase.replaceAll("\\bH", "");
        phrase = phrase.replaceAll("Y", "I");

        return phrase;
    }

    public static String internetesPTBR(String phrase) {

        phrase = phrase.replaceAll("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", " ");
        phrase = phrase.replaceAll("(?<=^|(?<=[^a-zA-Z0-9-_.]))@([A-Za-z]+[A-Za-z0-9]+)", " ");
        phrase = phrase.replaceAll("\\b(HTTPS?|FTP|FILE)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", " ");
        phrase = phrase.replaceAll("^(HTTPS?|FTP|FILE)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", " ");
        phrase = phrase.replaceAll("\\bM[UIN]*T[O]\\b", "MUITO");
        phrase = phrase.replaceAll("\\bN\\b", "NAO");
        phrase = phrase.replaceAll("\\bL[E]*G[AUL]+","LEGAL");


        return phrase;
    }
}
