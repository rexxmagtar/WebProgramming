package com.company;

import com.company.TextWraper.NormalText;
import com.company.TextWraper.TextManager;
import com.company.parsers.NormalTextParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.logging.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here

        ResourceBundle mainBundle = ResourceBundle.getBundle("MyBundle");

        Logger logger = Logger.getLogger("MyLogger");

        FileHandler handler = new FileHandler("C:\\BSU_Season5\\Webprogramming\\Lab3\\src\\logs.txt");


        logger.addHandler(handler);

        logger.setUseParentHandlers(false);

        SimpleFormatter formatter = new SimpleFormatter();

        handler.setFormatter(formatter);

        logger.info("Programm started");

        String text;


        try {
            text = new String(Files.readAllBytes(Path.of("C:\\BSU_Season5\\Webprogramming\\Lab3\\src\\input.txt")));

        } catch (Exception e) {
            logger.severe("Failed to read file:\n " + e.getMessage());
            return;
        }

        System.out.println("\n" + mainBundle.getString("RealText") + "\n");
        System.out.println(text);


        NormalText normalText = NormalTextParser.ParseNormalText(text);

        String textReturned = NormalTextParser.GetString(normalText);

        System.out.println("\n" + mainBundle.getString("ConvertedText") + " \n");
        System.out.println(textReturned);


        System.out.println("\n" + mainBundle.getString("SortedText") + " \n");
        System.out.println(TextManager.SortInAlphabeticOrder(normalText));

        System.out.println("\n" + mainBundle.getString("LongestNonLetterSequence"));
        System.out.println(TextManager.GetFirstLongestMarksSequence(normalText));

        TextManager.FirstLettersToUpper(normalText);

        System.out.println("\n" + mainBundle.getString("LettersToUpperCase"));
        System.out.println(NormalTextParser.GetString(normalText));

        /*logger.info("Some info");*/

        //Testing error handling.
       /* try {
            SentenceElement element = new SentenceElement("ab", SentenceElement.type.mark);
        } catch (InvalidParameterException e) {
            logger.log(Level.SEVERE,"Got error!!!",e);
            return;
        }*/

    }
}
