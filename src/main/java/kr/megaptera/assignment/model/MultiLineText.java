package kr.megaptera.assignment.model;

import java.util.Arrays;
import java.util.List;

public class MultiLineText {
    private List<String> lines;

    public MultiLineText(String text) {
        this.lines = Arrays.asList(text.split("\n"));
    }

    @Override
    public String toString(){
        return String.join("\n", lines);
    }
}
