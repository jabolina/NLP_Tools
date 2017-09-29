package com.nlp.nlptools;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.nlp.nlptools.service.Normalization.normalize;

@SpringBootApplication
public class NlptoolsApplication {
    public static void main(String[] args) {

        String phrase = "galera eh tao aii sororidade manasss apoio as minas support your local girls e quando acontece alguma coisa so querem saber de fofocar affff";

        phrase = normalize(phrase);

        System.out.println(phrase);
    }
}
