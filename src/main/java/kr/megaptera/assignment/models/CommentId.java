package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;

import java.util.Objects;

public class CommentId {

    private final String value;

    private CommentId(String value) {
        this.value = value;
    }

    public static CommentId generate() {
        return new CommentId(TsidCreator.getTsid().toString());
    }

    public static CommentId from(String id) {
        return new CommentId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentId commentId)) return false;

        return Objects.equals(value, commentId.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value;
    }

    public int compareTo(CommentId other) {
        return value.compareTo(other.value);
    }
}
