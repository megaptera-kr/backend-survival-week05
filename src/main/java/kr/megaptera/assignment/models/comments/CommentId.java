package kr.megaptera.assignment.models.comments;

import com.github.f4b6a3.tsid.TsidCreator;

import java.util.Objects;

public class CommentId {
    String value;

    public CommentId() {
        this.value = TsidCreator.getTsid().toString();
    }

    public CommentId(String value) {
        this.value = value;
    }

    public static CommentId of(String value) {
        return new CommentId(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentId commentId = (CommentId) o;
        return Objects.equals(value, commentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
