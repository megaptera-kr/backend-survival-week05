package kr.megaptera.assignment.models;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PostTextContent {
    private List<String> textContent;

    public PostTextContent(String textContent) {
        this.textContent = Arrays.asList(textContent.split("\n"));
    }

    public static PostTextContent of(String content) {
        return new PostTextContent(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostTextContent that = (PostTextContent) o;
        return Objects.equals(textContent, that.textContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textContent);
    }

    @Override
    public String toString() {
        return textContent.stream().collect(Collectors.joining("\n"));
    }
}
