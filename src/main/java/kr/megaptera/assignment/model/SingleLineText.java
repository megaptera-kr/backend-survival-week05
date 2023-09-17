package kr.megaptera.assignment.model;

import java.util.Objects;

public class SingleLineText {
    private String text;

    public SingleLineText(String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return text;
    }

    @Override
    public boolean equals(Object other) {
        SingleLineText otherPostId = (SingleLineText) other;

        return Objects.equals(text, otherPostId.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
