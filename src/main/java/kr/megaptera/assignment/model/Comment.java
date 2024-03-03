package kr.megaptera.assignment.model;

import com.github.f4b6a3.tsid.TsidCreator;
import kr.megaptera.assignment.dtos.CommentDto;

public class Comment {
    private String postId;
    private String id;
    private String author;
    private String content;

    public Comment() {
    }

    public Comment(String postId, String id, String author, String content) {
        this.postId = postId;
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public Comment(CommentDto dto) {
        this(dto.getPostId(), dto.getId(), dto.getAuthor(), dto.getContent());
    }

    public Comment(String postId, String author, String content) {
        this.id = TsidCreator.getTsid().toString();
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public String postId() {
        return postId;
    }

    public String id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }
}
