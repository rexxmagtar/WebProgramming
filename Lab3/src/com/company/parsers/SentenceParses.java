package com.company.parsers;

import com.company.Sentence;
import com.company.SentenceElement;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParses {

    public static Sentence ParseSentence(String sentence) {
        Sentence sentence1 = new Sentence();

        ArrayList<SentenceElement> sentenceElements = new ArrayList<>();

        String regex = "[a-zA-z0-9а-яА-Я]+|,|;|:|\\.";

        Matcher m = Pattern.compile(regex)
                .matcher(sentence);

        while (m.find()) {
            if(m.group().equals(" ")){
                continue;
            }
            if (m.group().matches(",|\\.|:|;")) {
                sentenceElements.add(new SentenceElement(m.group(), SentenceElement.type.mark));
            } else {
                sentenceElements.add(new SentenceElement(m.group(), SentenceElement.type.word));
            }
        }

        sentence1.sentenceElements = sentenceElements;

        return sentence1;
    }

    public static String GetString(Sentence sentence) {
        String result = "";

        for (int i = 0; i < sentence.sentenceElements.size(); i++) {

            if (i != 0 && sentence.sentenceElements.get(i).elementType == SentenceElement.type.word) {
                result+=" ";
            }

            result += sentence.sentenceElements.get(i).value;


        }

        if(sentence.sentenceElements.size() ==0){
            return result;
        }
        result += ".";

        return result;
    }
}