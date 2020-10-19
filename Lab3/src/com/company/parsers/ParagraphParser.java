package com.company.parsers;

import com.company.TextWraper.Paragraph;
import com.company.TextWraper.Sentence;

import java.util.ArrayList;
import java.util.Arrays;

public class ParagraphParser {

    public static Paragraph ParseParagraph(String paragraph) {

        Paragraph paragraph1 = new Paragraph();

        ArrayList<Sentence> sentences = new ArrayList<>();

        ArrayList<String> sentenceStrings = new ArrayList<>();


        String regex = "\\.";

        sentenceStrings =new ArrayList<>( Arrays.asList(paragraph.split(regex)));

        for (int i = 0; i < sentenceStrings.size(); i++) {
            if(sentenceStrings.get(i).equals(" ")){
                continue;
            }
            sentences.add(SentenceParser.ParseSentence(sentenceStrings.get(i)));
        }

        paragraph1.sentences = sentences;

        return paragraph1;
    }

    public static String GetString(Paragraph paragraph) {
        String result = "";

        for (int i = 0; i < paragraph.sentences.size(); i++) {
            result += SentenceParser.GetString(paragraph.sentences.get(i)) + " ";
        }

        return result;

    }
}
