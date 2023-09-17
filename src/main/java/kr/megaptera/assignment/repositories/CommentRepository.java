package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentRepository {
    private Map<CommentId, Comment> comments;

    public CommentRepository() {
        comments = new HashMap<>();
    }

    public List<Comment> findAll(String postId) {
        return comments.values().stream().filter(comment -> comment.postId().equals(postId)).toList();
    }

    public void save(Comment comment) {
        comments.put(comment.id(), comment);
    }

    public Comment find(CommentId commentId) {
        return comments.get(commentId);
    }

    public void delete(CommentId commentId) {
        comments.remove(commentId);
    }
}
