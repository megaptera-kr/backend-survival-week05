package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.*;

import java.util.*;

public class CommentId {
    private String value;

    public CommentId(String value) {
        this.value = value;
    }

    public static CommentId generate(){return new CommentId(TsidCreator.getTsid().toString());}

    public static CommentId of(String id) {
        return new CommentId(id);
    }
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        CommentId otherCommentId = (CommentId) other;

        return Objects.equals(value, otherCommentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
