package com.company.parsers;

import com.company.Pair;
import com.company.TextWraper.NormalText;
import com.company.TextWraper.Paragraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
Used to parse normal text to wrapper object and wise versa.
 */
public class NormalTextParser {

    /**
     * Parses text to wrapper object
     * @param text
     * @return
     */
    public static NormalText ParseNormalText(String text) {

        NormalText normalText = new NormalText();

        ArrayList<Paragraph> paragraphs = new ArrayList<>();

        ArrayList<String> codeBlocks = new ArrayList<>();

        ArrayList<Pair<String, Integer>> codes = new ArrayList<>();

        String codeBlockRegex = "<code>(\\n|.)*<\\/code>";

        Matcher m = Pattern.compile(codeBlockRegex).matcher(text);

        while (m.find()) {
            codeBlocks.add(m.group());
        }

        text = text.replaceAll(codeBlockRegex, "<code></code>");

       /* System.out.println("Code wrapped: ");
        System.out.println(text);*/

        ArrayList<String> paragraphStrings = new ArrayList<>();

        String regex = "\\n";

        paragraphStrings = new ArrayList<>(Arrays.asList(text.split(regex)));

        int codeBlockNumber = 0;
        for (int i = 0; i < paragraphStrings.size(); i++) {
           /* if(paragraphStrings.get(i).equals("")){
                continue;
            }*/

            if (paragraphStrings.get(i).matches(codeBlockRegex)) {
                codes.add(new Pair<String, Integer>(codeBlocks.get(codeBlockNumber), i));
                codeBlockNumber++;
            }
            else {
                paragraphs.add(ParagraphParser.ParseParagraph(paragraphStrings.get(i)));
            }
        }

        normalText.paragraphs = paragraphs;
        normalText.codeBlocks = codes;

        return normalText;
    }

    /**
     * Gets String from Parsed object
     * @param text
     * @return
     */
    public static String GetString(NormalText text) {

        String result = "";

        for (int i = 0; i < text.paragraphs.size(); i++) {
            result += ParagraphParser.GetString(text.paragraphs.get(i)) + "\n";
            int finalI = i;

           Supplier< Stream<Pair<String,Integer>>> found = ()-> text.codeBlocks.stream().filter(new Predicate<Pair<String, Integer>>() {
                @Override
                public boolean test(Pair<String, Integer> stringIntegerPair) {
                    return stringIntegerPair.getElement1() == finalI +1;
                }
            });

            if(found.get().findFirst().isPresent()){
                result+=found.get().findFirst().get().getElement0()+"\n";
            }
        }

        return result;
    }

}
