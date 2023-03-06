package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;

import java.util.Objects;

public class PostId {
    private String value;

    public PostId(String value){
        this.value = value;
    }

    public static PostId of(String value){
        return new PostId(value);
    }

    public static PostId generate(){
        return new PostId(TsidCreator.getTsid().toString());
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

    @Override
    public String toString() {
        return this.value;
    }

    // return값이 음수면 other이 더 크고, 0이면 동일한 값, 양수면 other이 더 작다.
    public int compare(PostId other) {
        // compareTo는 String을 사전순으로 비교할 수 있게 해준다.
        return value.compareTo(other.value);
    }
}
