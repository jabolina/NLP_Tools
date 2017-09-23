package com.languagetools.LanguageTools.service;

public class Normalization {

    public static String normalize(String phrase) {

        phrase = phrase.toUpperCase();
        return Treatment.characterTreatment(
                PhoneticsPTBR.phoneticsPTBR(
                        Treatment.stopwordsPTBR(
                                PhoneticsPTBR.internetesPTBR(phrase)
                        )
                )
        );
    }
}
