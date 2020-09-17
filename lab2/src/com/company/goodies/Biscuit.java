package com.company.goodies;

public class Biscuit extends Goody {

    public enum Form{
        Round,
        Quad,
    }

    private Form form;

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Biscuit(float wight, float calories, float shelfLife, String name, Form form) {
        super(wight, calories, shelfLife, name);
        this.form = form;
    }

    @Override
    public String toString() {
        return "Biscuit{" +
                "form=" + form +
                ", weight=" + weight +
                ", calories=" + calories +
                ", shelfLife=" + shelfLife +
                ", name='" + name + '\'' +
                '}';
    }
}
