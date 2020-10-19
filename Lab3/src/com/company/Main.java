package com.company;

import com.company.TextWraper.NormalText;
import com.company.TextWraper.TextManager;
import com.company.parsers.NormalTextParser;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;
import java.util.logging.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here

        ResourceBundle mainBundle = ResourceBundle.getBundle("MyBundle");

        Logger logger = Logger.getLogger("MyLogger");

        FileHandler handler= new FileHandler("C:\\BSU_Season5\\Webprogramming\\Lab3\\src\\logs.txt");


        logger.addHandler(handler);

        logger.setUseParentHandlers(false);

        SimpleFormatter formatter = new SimpleFormatter();

        handler.setFormatter(formatter);

        String text = "Интеллект ::::::::::::::::::::::: арбус ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; естественно понимает под собой интеллигибельный закон внешнего мира, открывая новые горизонты. Надстройка нетривиальна. Структурализм абстрактен. Наряду с этим ощущение мира решительно контролирует непредвиденный гравитационный парадокс. Надстройка нетривиальна. Интеллект естественно понимает\n" +
                "               \n" +
                "                Сомнение рефлектирует естественный закон исключенного третьего. Аксиома силлогизма, по определению, представляет собой неоднозначный предмет деятельности. Гедонизм осмысляет дедуктивный метод. Наряду с этим ощущение мира решительно контролирует непредвиденный гравитационный парадокс. Созерцание непредсказуемо. Интеллект естественно пон\n" +
                "\n" +
                "                Дискретность амбивалентно Яхта яарбус транспонирует гравитационный парадокс. Дискретность амбивалентно транспонирует гравитационный парадокс. Надстройка нетривиальна. undefined. Надстройка нетривиальна. Смысл жизни, следовательно, творит данный закон внешнего мира. Импликация, следовательно, контролирует бабувизм, открывая новые горизонт\n" +
                "\n" +
                "<code>\n" +
                "\n" +
                "int Main(){\n" +
                "\n" +
                "System.out.println(\"Hello Ivan\");\n" +
                "}\n" +
                "\n" +
                "</code>";

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


        //Testing error handling.
       /* try {
            SentenceElement element = new SentenceElement("ab", SentenceElement.type.mark);
        } catch (InvalidParameterException e) {
            logger.log(Level.SEVERE,"Got error!!!",e);
            return;
        }*/

    }
}
