package kr.megaptera.assignment.domains.model;

import kr.megaptera.assignment.domains.dto.CommentCreateDto;
import kr.megaptera.assignment.domains.dto.CommentUpdateDto;

public class Comment {

    private CommentId id;

    private PostId postId;

    private CommentAuthor author;

    private MultilineText content;

    public Comment(CommentId id, PostId postId, CommentAuthor author, MultilineText content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment(PostId postId, CommentCreateDto commentCreateDto) {
        this.id = new CommentId();
        this.postId = postId;
        this.author = CommentAuthor.of(commentCreateDto.getAuthor());
        this.content = MultilineText.of(commentCreateDto.getContent());
    }

    public CommentId id() {
        return this.id;
    }

    public PostId postId() {
        return this.postId;
    }

    public CommentAuthor author() {
        return this.author;
    }

    public MultilineText content() {
        return this.content;
    }

    public void updateComment(CommentUpdateDto commentUpdateDto) {
        this.content = MultilineText.of(commentUpdateDto.getContent());
    }
}
