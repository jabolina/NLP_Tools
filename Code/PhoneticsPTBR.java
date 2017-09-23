package com.languagetools.LanguageTools.service;

public class PhoneticsPTBR {

    public static String phoneticsPTBR(String phrase) {

        phrase = phrase.replaceAll("\\bEX([AEIOU])", " EZ$1");
        phrase = phrase.replaceAll("(?:\\b?)([AEIOU])S([AEIOU])(?:\\s?)", "$1Z$2");
        phrase = phrase.replaceAll("Ç","S");
        phrase = phrase.replaceAll("C([EI])", "S$1");
        phrase = phrase.replaceAll("S{2,}", "S");
        phrase = phrase.replaceAll("RR", "R");
        phrase = phrase.replaceAll("C([AOU])", "K$1");
        phrase = phrase.replaceAll("QU([AEIO])", "K$1");
        phrase = phrase.replaceAll("\\bH", "");

        return phrase;
    }

    public static String internetesPTBR(String phrase) {

        phrase = phrase.replaceAll("\\b(?:VC|VCS)\\b", "VOCE");
        phrase = phrase.replaceAll("\\bTD\\b", "TUDO");
        phrase = phrase.replaceAll("\\bTB[EM]*", "TAMBEM");
        phrase = phrase.replaceAll("\\bHJ\\b", "HOJE");


        return phrase;
    }
}
