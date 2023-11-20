package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;

public class Comment {
    private PostId postId;
    private CommentId id;
    private CommentAuthor author;
    private MultilineText content;

    public Comment(PostId postId, CommentAuthor author, MultilineText content) {
        this.postId = postId;
        this.id = CommentId.of(generate());
        this.author = author;
        this.content = content;
    }

    public PostId postId() {
        return postId;
    }

    public CommentId id() {
        return id;
    }

    public CommentAuthor author() {
        return author;
    }

    public MultilineText content() {
        return content;
    }

    private static String generate() {
        return TsidCreator.getTsid().toString();
    }

    public void update(MultilineText content) {
        this.content = content;
    }
}
