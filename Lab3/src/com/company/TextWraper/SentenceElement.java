package com.company.TextWraper;

import java.security.InvalidParameterException;

/**
 * Sentence element wrapper
 */
public class SentenceElement {
    public String value;

    public enum type{
        mark,
        word,
    }

    public type elementType;

    /**
     * Throws InvalidParameterException if created element is a mark symbol and is longer than 1 char.
     * @param value
     * @param type
     */
    public SentenceElement(String value, type type) {

        if(type == SentenceElement.type.mark && value.length()>1){
            throw new InvalidParameterException("mark cannot be longer than 1 char");
        }

        this.value = value;
        this.elementType = type;
    }
}
