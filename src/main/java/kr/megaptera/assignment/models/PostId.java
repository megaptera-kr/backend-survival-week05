package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.*;

import java.util.*;

public class PostId {
    private String value;

    public PostId(String value) {
        this.value = value;
    }

    public static PostId generate() {
         return new PostId(TsidCreator.getTsid().toString());
    }

    public static PostId of(String id) {
        return new PostId(id);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        PostId otherPostId = (PostId) other;

        return Objects.equals(value, otherPostId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
