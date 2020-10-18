package com.company.parsers;

import com.company.NormalText;
import com.company.Paragraph;
import com.company.Sentence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class NormalTextParser {

    public static NormalText ParseNormalText(String text){

        NormalText normalText = new NormalText();

        ArrayList<Paragraph> paragraphs = new ArrayList<>();

        ArrayList<String> paragraphStrings = new ArrayList<>();

        String regex = "\\n";

       paragraphStrings = new ArrayList<>( Arrays.asList( text.split(regex)));

        for (int i = 0; i < paragraphStrings.size(); i++) {
           /* if(paragraphStrings.get(i).equals("")){
                continue;
            }*/
            paragraphs.add(ParagraphParser.ParseParagraph(paragraphStrings.get(i)));
        }

        normalText.paragraphs = paragraphs;

        return normalText;
    }

    public static String GetString(NormalText text){

        String result = "";

        for (int i = 0; i < text.paragraphs.size(); i++) {
            result += ParagraphParser.GetString(text.paragraphs.get(i)) + "\n";
        }

        return result;
    }

}
