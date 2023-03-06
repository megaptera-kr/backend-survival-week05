package kr.megaptera.assignment.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MultiLineText {
    private List<String> lines = new ArrayList<>();

    public MultiLineText of(String text){
        return new MultiLineText(text);
    }

    // 개행 단위로 나눈 배열을 리스트로 저장한다.
    public MultiLineText(String text){
        this.lines = Arrays.asList(text.split("\n"));
    }

    @Override
    public String toString() {
        return String.join("\n", lines);
    }

    @Override
    public boolean equals(Object other) {
        MultiLineText multiLineText = (MultiLineText) other;
        return Objects.equals(lines, multiLineText.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lines);
    }
}
