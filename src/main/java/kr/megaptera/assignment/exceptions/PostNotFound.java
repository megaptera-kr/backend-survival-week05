package kr.megaptera.assignment.exceptions;

import kr.megaptera.assignment.models.Post;

public class PostNotFound extends RuntimeException {
    public PostNotFound() {
        super("게시글을 찾을 수 없습니다.");
    }
}
