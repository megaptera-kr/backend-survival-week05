package kr.megaptera.assignment.models;

public class CommentId extends Id{

    public CommentId(String id) {
        super(id);
    }
    public static CommentId of(String id) {
        return new CommentId(id);
    }
}
