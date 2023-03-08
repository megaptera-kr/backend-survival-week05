package kr.megaptera.assignment.model.post;

import java.util.*;

public class PostContent {
    private List<String> content;

    public PostContent() {
    }

    public PostContent(String content) {
        this.content = Arrays.stream(content.split("\n")).toList();
    }

    public static PostContent of(String content) {
        return new PostContent(content);
    }

    @Override
    public String toString() {
        return String.join("\n", content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostContent that = (PostContent) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
