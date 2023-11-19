package kr.megaptera.assignment.models;

public class PostId extends Id {

    public PostId(String id) {
        super(id);
    }
    public static PostId of(String id) {
        return new PostId(id);
    }
}
