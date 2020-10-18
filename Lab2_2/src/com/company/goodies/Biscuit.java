package com.company.goodies;

import java.util.Objects;

/**
 * Class describes Biscuit goodies
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Biscuit biscuit = (Biscuit) o;
        return form == biscuit.form;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), form);
    }
}
