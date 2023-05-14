package kr.megaptera.assignment.exceptions;

public class CommentNotFound extends RuntimeException {
    public CommentNotFound() {
        super("댓글을 찾을 수 없습니다.");
    }
}
