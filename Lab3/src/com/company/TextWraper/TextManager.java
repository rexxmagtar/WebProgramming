package com.company.TextWraper;

import com.company.parsers.SentenceParser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextManager {

    public static String SortInAlphabeticOrder(NormalText text) {

        ArrayList<String> words = GetAllWords(text);

        words.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                if (o1.compareToIgnoreCase(o2) > 0) {
                    return 1;
                }

                if (o1.compareToIgnoreCase(o2) < 0) {
                    return -1;
                }

                return 0;
            }
        });

        String result = "";

        for (int i = 0; i < words.size(); i++) {

            result += words.get(i) + " ";

            if (i + 1 < words.size() && words.get(i + 1).charAt(0) != (words.get(i).charAt(0))) {
                result += "\n";
            }
        }
        return result;
    }

    public static String GetFirstLongestMarksSequence(NormalText text) {

        ArrayList<Sentence> sentences = new ArrayList<>();

        String answer = "";

        for (Paragraph paragraph : text.paragraphs
        ) {
            for (Sentence sentence : paragraph.sentences
            ) {
                sentences.add(sentence);

                String sentenceText = SentenceParser.GetString(sentence);

                String regex = "[^a-zA-Zа-яА-Я]+";

                Matcher m = Pattern.compile(regex)
                        .matcher(sentenceText);

                while (m.find()) {
                    if (m.group().length() > answer.length()) {
                        answer = m.group();
                    }
                }
            }
        }
        return answer;
    }

    public static void FirstLettersToUpper(NormalText normalText) {

        for (Paragraph paragraph : normalText.paragraphs
        ) {
            for (Sentence sentence : paragraph.sentences
            ) {
                for (SentenceElement element : sentence.sentenceElements
                ) {
                    if (element.elementType == SentenceElement.type.word) {

                        String word = element.value;

                        StringBuilder builder = new StringBuilder(word);

                        builder.setCharAt(0, word.toUpperCase().charAt(0));

                        element.value = builder.toString();
                    }
                }
            }
        }
    }

    public static ArrayList<String> GetAllWords(NormalText text) {

        ArrayList<String> words = new ArrayList<>();

        for (Paragraph paragraph : text.paragraphs
        ) {
            for (Sentence sentence : paragraph.sentences
            ) {
                for (SentenceElement element : sentence.sentenceElements
                ) {
                    if (element.elementType == SentenceElement.type.word) {
                        words.add(element.value);
                    }
                }
            }
        }
        return words;
    }

}
