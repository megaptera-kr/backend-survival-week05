package kr.megaptera.assignment.model.comment;

import com.github.f4b6a3.tsid.*;
import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.model.post.*;

public class Comment {
    private CommentId id;
    private PostId postId;
    private String author;
    private String content;

    public Comment(PostId postId, String author, String content) {
        this.id = CommentId.of(TsidCreator.getTsid().toString());
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment(CommentId id, PostId postId, String author, String content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public CommentId id() {
        return id;
    }

    public PostId postId() {
        return postId;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }

    public void update(CommentUpdateDto commentUpdateDto) {
        this.content = commentUpdateDto.getContent();
    }
}
