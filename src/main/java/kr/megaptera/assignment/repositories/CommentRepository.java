package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {
    private final Map<CommentId, Comment> comments;

    public CommentRepository() {
        this.comments = new HashMap<>();
    }

    public List<Comment> findAll(PostId postId) {
        return comments.values().stream()
            .filter(comment -> comment.postId().equals(postId))
            .toList();
    }

    public Comment findComment(CommentId commentId) {
        Comment comment = comments.get(commentId);
        if (comment == null) {
            throw new CommentNotFound();
        }
        return comment;
    }

    public void save(Comment comment) {
        comments.put(comment.commentId(), comment);
    }

    public void delete(Comment comment) {
        comments.remove(comment.commentId());
    }
}
