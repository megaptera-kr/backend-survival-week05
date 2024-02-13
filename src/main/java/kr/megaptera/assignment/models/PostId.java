package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;

import java.util.Objects;

public class PostId {

    private final String value;

    private PostId(String value) {
        this.value = value;
    }

    public static PostId from(String value) {
        return new PostId(value);
    }

    public static PostId generate() {
        return new PostId(TsidCreator.getTsid().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostId postId)) return false;

        return Objects.equals(value, postId.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value;
    }

    public int compareTo(PostId other) {
        return value.compareTo(other.value);
    }
}
