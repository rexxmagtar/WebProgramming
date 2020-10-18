package com.company;

import com.company.parsers.NormalTextParser;
import org.w3c.dom.Text;

public class Main {

    public static void main(String[] args) {
	// write your code here

        String text = "Интеллект ::::::::::::::::::::::: арбус ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; естественно понимает под собой интеллигибельный закон внешнего мира, открывая новые горизонты. Надстройка нетривиальна. Структурализм абстрактен. Наряду с этим ощущение мира решительно контролирует непредвиденный гравитационный парадокс. Надстройка нетривиальна. Интеллект естественно понимает\n" +
                "\n" +
                "Сомнение рефлектирует естественный закон исключенного третьего. Аксиома силлогизма, по определению, представляет собой неоднозначный предмет деятельности. Гедонизм осмысляет дедуктивный метод. Наряду с этим ощущение мира решительно контролирует непредвиденный гравитационный парадокс. Созерцание непредсказуемо. Интеллект естественно пон\n" +
                "\n" +
                "Дискретность амбивалентно Яхта яарбус транспонирует гравитационный парадокс. Дискретность амбивалентно транспонирует гравитационный парадокс. Надстройка нетривиальна. undefined. Надстройка нетривиальна. Смысл жизни, следовательно, творит данный закон внешнего мира. Импликация, следовательно, контролирует бабувизм, открывая новые горизонт";
        System.out.println("\nText real\n");
        System.out.println(text);


        NormalText normalText = NormalTextParser.ParseNormalText(text);

        String textReturned = NormalTextParser.GetString(normalText);

        System.out.println("\nText converted\n");
        System.out.println(textReturned);


        System.out.println("\nSorted text: \n");
        System.out.println(TextManager.SortInAlphabeticOrder(normalText));

        System.out.println("\nLongest non letter sequence");
        System.out.println(TextManager.GetFirstLongestMarksSequence(normalText));

        TextManager.FirstLettersToUpper(normalText);

        System.out.println("\nLetters set to upper case");
        System.out.println(NormalTextParser.GetString(normalText));

    }
}
