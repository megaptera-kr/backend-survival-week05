package kr.megaptera.assignment.domains.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class MultilineText {

    private final List<String> content;

    public MultilineText(String text) {

        this.content = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(text, "\n");

        while (st.hasMoreElements()) {
            content.add(st.nextToken());
        }
    }

    public static MultilineText of(String content) {
        return new MultilineText(content);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        
        for (String line : content) {
            sb.append(line).append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultilineText that)) return false;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
