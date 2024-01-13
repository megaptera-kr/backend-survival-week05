package kr.megaptera.assignment.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record MultiLineText(List<String> lines) {

    public static MultiLineText of(String... text) {
        List<String> result = new ArrayList<>();
        for (String line : text) {
            result.addAll(Arrays.asList(line.split("\n")));
        }
        return new MultiLineText(result);
    }

    public String text() {
        return String.join("\n", lines);
    }

    @Override
    public String toString() {
        return String.join("\n", lines);
    }
}
