package com.company.parsers;

import com.company.Paragraph;
import com.company.Sentence;
import com.company.SentenceElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            sentences.add(SentenceParses.ParseSentence(sentenceStrings.get(i)));
        }

        paragraph1.sentences = sentences;

        return paragraph1;
    }

    public static String GetString(Paragraph paragraph) {
        String result = "";

        for (int i = 0; i < paragraph.sentences.size(); i++) {
            result += SentenceParses.GetString(paragraph.sentences.get(i)) + " ";
        }

        return result;

    }
}
