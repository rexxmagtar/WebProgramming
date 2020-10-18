package com.company;

public class SentenceElement {
    public String value;

    public enum type{
        mark,
        word
    }

    public type elementType;

    public SentenceElement(String value, type type) {
        this.value = value;
        this.elementType = type;
    }
}
