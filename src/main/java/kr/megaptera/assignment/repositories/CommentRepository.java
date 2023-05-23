package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.domains.model.Comment;
import kr.megaptera.assignment.domains.model.CommentId;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {
    private final Map<PostId, Map<CommentId, Comment>> comments;

    public CommentRepository() {
        this.comments = new HashMap<>();
    }

    public Comment find(CommentId id, PostId postId) {
        Map<CommentId, Comment> commentsOnPost = comments.get(postId);
        if (commentsOnPost == null)
            throw new CommentNotFound();

        return commentsOnPost.get(id);
    }

    public List<Comment> findByPost(PostId postId) {
        Map<CommentId, Comment> commentsOnPost = comments.get(postId);
        if (commentsOnPost == null)
            commentsOnPost = new HashMap<>();

        return commentsOnPost.values().stream().toList();
    }

    public Comment save(PostId postId, Comment comment) {
        Map<CommentId, Comment> commentsOfPost = comments.get(postId);
        if (commentsOfPost == null)
            commentsOfPost = new HashMap<>();
        commentsOfPost.put(comment.id(), comment);
        comments.put(postId, commentsOfPost);
        return comment;
    }

    public Comment delete(CommentId id, PostId postId) {
        Map<CommentId, Comment> commentsByPost = comments.get(postId);
        if (commentsByPost == null)
            throw new CommentNotFound();

        Comment comment = commentsByPost.get(id);
        if (comment == null)
            throw new CommentNotFound();

        commentsByPost.remove(id);
        comments.put(postId, commentsByPost);

        return comment;
    }
}
