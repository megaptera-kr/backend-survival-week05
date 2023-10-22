package kr.megaptera.assignment.models.posts;

import com.github.f4b6a3.tsid.TsidCreator;

import java.util.Objects;

public class PostId {
    String value;

    public PostId() {
        this.value = TsidCreator.getTsid().toString();
    }

    public PostId(String value) {
        this.value = value;
    }

    public static PostId of(String value) {
        return new PostId(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostId postId = (PostId) o;
        return Objects.equals(value, postId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
