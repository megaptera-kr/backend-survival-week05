package kr.megaptera.assignment.models;

import java.util.List;

public class MultilineText {

    private final List<String> value;

    private MultilineText(String lines) {
        this.value = List.of(lines.split("\n"));
    }

    public static MultilineText from(String lines) {
        return new MultilineText(lines);
    }

    @Override
    public String toString() {
        return String.join("\n", value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultilineText that)) return false;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
