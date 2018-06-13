package com.aisera.fuzzy;

import org.apache.commons.text.similarity.FuzzyScore;
import org.apache.commons.text.similarity.JaccardSimilarity;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        LevenshteinDistance ld = new LevenshteinDistance();

        String target = "what are steps to install on Android";
        String[] tokens = target.split(" ");
        StringBuffer targetSB = new StringBuffer();
        for (String token : tokens) {
            if (token.trim().length() > 2) {
                targetSB.append(token + " ");
            }
        }

        System.out.println("The initial string is '" + targetSB + "'");
        System.out.println("");

        String[] strings = {
                "install " + "what",
                "installation steps for iOS",
                "i want to install on iOS",
                "How to install on iOS",
                "I need to install iOS Application",
                "How can I install on iOS",
                "How do I reset my password",
                "My password doesn't work"};

        for (String string : strings) {
            String[] t2 = string.split(" ");
            StringBuffer sb = new StringBuffer();
            for (String token : t2) {
                if (token.trim().length() > 2) {
                    sb.append(token + " ");
                }
            }
            int dist = ld.apply(sb.toString(), targetSB.toString());
            System.out.print("Dist " + dist + " for '" + string + "'");
            if (dist > 15) System.out.print(" in label");
            System.out.println("");
        }

        System.out.println("");

        Locale locale = new Locale("en");

        FuzzyScore fs = new FuzzyScore(locale);
        for (String string : strings) {
            String[] t = string.split(" ");
            StringBuffer sb = new StringBuffer();
            for (String token : t) {
                if (token.trim().length() > 2) {
                    sb.append(token + " ");
                }
            }
            int score = fs.fuzzyScore(targetSB.toString(), sb.toString());
            System.out.print("FuzzyScore " + score + " for '" + sb.toString() + "'");
            if (score > 10) System.out.print(" in label");
            System.out.println("");
        }

        System.out.println("");

        JaccardSimilarity js = new JaccardSimilarity();
        for (String string : strings) {
            String[] tw = string.split(" ");
            StringBuffer sb = new StringBuffer();
            for (String token : tw) {
                if (token.trim().length() > 2) {
                    sb.append(token + " ");
                }
            }
            double score = js.apply(targetSB.toString(), sb.toString());
            System.out.print("Jacquard " + score + " for '" + sb.toString() + "'");
            if (score > 0.5) System.out.print(" in label");
            System.out.println("");
        }
    }
}
